/***********************************************************************
 * This file is part of iDempiere ERP Open Source                      *
 * http://www.idempiere.org                                            *
 *                                                                     *
 * Copyright (C) Contributors                                          *
 *                                                                     *
 * This program is free software; you can redistribute it and/or       *
 * modify it under the terms of the GNU General Public License         *
 * as published by the Free Software Foundation; either version 2      *
 * of the License, or (at your option) any later version.              *
 *                                                                     *
 * This program is distributed in the hope that it will be useful,     *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of      *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the        *
 * GNU General Public License for more details.                        *
 *                                                                     *
 * You should have received a copy of the GNU General Public License   *
 * along with this program; if not, write to the Free Software         *
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,          *
 * MA 02110-1301, USA.                                                 *
 *                                                                     *
 * Contributors:                                                       *
 * - Carlos Ruiz - globalqss - BX Service                              *
 **********************************************************************/

package de.bxservice.metabase.webui.dashboard;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import org.adempiere.webui.dashboard.DashboardPanel;
import org.adempiere.webui.util.ServerPushTemplate;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.compiere.model.MDashboardContent;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.zkoss.zul.Iframe;

import de.bxservice.metabase.model.MBXSMBDashboard;
import de.bxservice.metabase.model.MBXSMBDashboardParam;

public class MBDashboardPanel extends DashboardPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1876043373394398186L;

	private Iframe iframe = new Iframe();
	private MBXSMBDashboard dashboard = null;
	private MDashboardContent dc;

	private final static CLogger logger = CLogger.getCLogger(MBDashboardPanel.class);

	public MBDashboardPanel(MDashboardContent ldc) {
		super();

		this.dc = ldc;

		int dbid = ldc.get_ValueAsInt(MBXSMBDashboard.COLUMNNAME_BXS_MBDashboard_ID);
		if (dbid > 0) {
			dashboard = new MBXSMBDashboard(Env.getCtx(), dbid, null);

			if (dashboard.getHeight() != null && dashboard.getHeight().intValue() > 0)
				ZKUpdateUtil.setHeight(this, dashboard.getHeight().intValue() + "px");
			else
				ZKUpdateUtil.setHeight(this, "600px");   // default to 600
		}

		// Timer not considered  for dashboard

		this.appendChild(iframe);
	}

	@Override
	public void refresh(ServerPushTemplate template) {
		if (dashboard == null) {
			iframe.setSrc(null);
		} else {
			Map<MBXSMBDashboardParam, Object> parammap = new LinkedHashMap<MBXSMBDashboardParam, Object>();
			fillParameter(parammap, dc.get_ValueAsString("BXSMBDashboardParameters"));

			String url = dashboard.getMetabaseEmbeddedUrl(parammap);

			if (dashboard.getWidth() != null && dashboard.getWidth().intValue() > 0)
				ZKUpdateUtil.setWidth(iframe, dashboard.getWidth().intValue() + "px");
			else
				ZKUpdateUtil.setWidth(iframe, "100%");
			if (dashboard.getHeight() != null && dashboard.getHeight().intValue() > 0)
				ZKUpdateUtil.setHeight(iframe, dashboard.getHeight().intValue() + "px");
			else
				ZKUpdateUtil.setHeight(iframe, "100%");
			iframe.setSrc(url);
		}
	}

	/**
	 * Method adapted from DashboardController.fillParameter
	 * @param pInstance
	 * @param parameters
	 */
	private void fillParameter(Map<MBXSMBDashboardParam, Object> parammap, String parameters) {		
		if (parameters != null && parameters.trim().length() > 0) {
			Map<String, String> paramMap = new HashMap<String, String>();
			String[] params = parameters.split("[,]");
			for (String s : params)
			{
				int pos = s.indexOf("=");
				String key = s.substring(0, pos);
				String value = s.substring(pos + 1);
				paramMap.put(key, value);
			}
			List<MBXSMBDashboardParam> iParams = dashboard.getParameters();
			for (MBXSMBDashboardParam iPara : iParams)
			{
				String variable = paramMap.get(iPara.getColumnName());

				if (Util.isEmpty(variable))
					continue;

				for (String paramValue : variable.split(";")) {

					//	Value - Constant/Variable
					Object value = paramValue;
					if (paramValue == null
							|| (paramValue != null && paramValue.length() == 0))
						value = null;
					else if (paramValue.startsWith("@SQL=")) {
						String sql = paramValue.substring(5);
						sql = Env.parseContext(Env.getCtx(), 0, sql, false, false);	//	replace variables
						if (!Util.isEmpty(sql)) {
							PreparedStatement stmt = null;
							ResultSet rs = null;
							try {
								stmt = DB.prepareStatement(sql, null);
								rs = stmt.executeQuery();
								if (rs.next()) {
									if (   DisplayType.isNumeric(iPara.getAD_Reference_ID()) 
											|| DisplayType.isID(iPara.getAD_Reference_ID()))
										value = rs.getBigDecimal(1);
									else if (DisplayType.isDate(iPara.getAD_Reference_ID()))
										value = rs.getTimestamp(1);
									else
										value = rs.getString(1);
								} else {
									if (logger.isLoggable(Level.INFO))
										logger.log(Level.INFO, "(" + iPara.getColumnName() + ") - no Result: " + sql);
								}
							}
							catch (SQLException e) {
								logger.log(Level.WARNING, "(" + iPara.getColumnName() + ") " + sql, e);
							}
							finally{
								DB.close(rs, stmt);
								rs = null;
								stmt = null;
							}
						}
					}	//	SQL Statement
					else if (paramValue.indexOf('@') != -1)	//	we have a variable
					{
						value = Env.parseContext(Env.getCtx(), 0, paramValue, false, false);
					}	//	@variable@

					//	No Value
					if (value == null)
					{
						continue;
					}

					//	Convert to Type				
					if (DisplayType.isNumeric(iPara.getAD_Reference_ID()) 
							|| DisplayType.isID(iPara.getAD_Reference_ID()))
					{
						BigDecimal bd = null;
						if (value instanceof BigDecimal)
							bd = (BigDecimal)value;
						else if (value instanceof Integer)
							bd = new BigDecimal (((Integer)value).intValue());
						else
							bd = new BigDecimal (value.toString());
						parammap.put(iPara, bd);
					}
					else if (DisplayType.isDate(iPara.getAD_Reference_ID()))
					{
						Timestamp ts = null;
						if (value instanceof Timestamp)
							ts = (Timestamp)value;
						else
							ts = Timestamp.valueOf(value.toString());
						parammap.put(iPara, ts);
					}
					else
					{
						parammap.put(iPara, value.toString());
					}
				}
			}
		}				
	}

}
