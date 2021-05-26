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

package de.bxservice.metabase.event;

import java.util.LinkedHashMap;
import java.util.Map;

import org.adempiere.base.event.AbstractEventHandler;
import org.compiere.model.MStatusLine;
import org.compiere.model.PO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.Util;
import org.osgi.service.event.Event;

import de.bxservice.metabase.model.MBXSMBDashboard;
import de.bxservice.metabase.model.MBXSMBDashboardParam;

/**
 *	Event Handler for ODIN
 */
public class MBEventHandler extends AbstractEventHandler
{
	/**	Logger			*/
	private static CLogger log = CLogger.getCLogger(MBEventHandler.class);

	private static final String BEFORE_PARSE_STATUS_LINE = "idempiere/statusLine/beforeParse"; // MStatusLine.BEFORE_PARSE_STATUS_LINE;
	private static final String EVENT_WINDOWNO = "event.windowno"; // MStatusLine.EVENT_WINDOWNO;

	/**
	 *	Initialize Validation
	 */
	@Override
	protected void initialize() {
		log.warning("");

		registerEvent(BEFORE_PARSE_STATUS_LINE);
	}	//	initialize

	/**
	 *	Model Change of a monitored Table.
	 *	Called after PO.beforeSave/PO.beforeDelete
	 *	when you called addModelChange for the table
	 *  @param event
	 *	@exception Exception if the recipient wishes the change to be not accept.
	 */
	@Override
	protected void doHandleEvent(Event event) {
		String type = event.getTopic();

		PO po = getPO(event);
		log.info(po + " Type: " + type);

		if (po instanceof MStatusLine && (type.equals(BEFORE_PARSE_STATUS_LINE))) {
			int windowNo = getEventProperty(event, EVENT_WINDOWNO);
			MStatusLine sl = (MStatusLine)po;
			setContextForMetabase(sl, windowNo);
		}

	}	//	doHandleEvent

	/**
	 * Set context variable @METABASE_IFRAME@ based on the dashboard and context variables
	 * @param sl
	 * @param windowNo
	 */
	private void setContextForMetabase(MStatusLine sl, int windowNo) {
		int db = sl.get_ValueAsInt("BXS_MBDashboard_ID");
		if (db > 0) {
			MBXSMBDashboard dashboard = new MBXSMBDashboard(sl.getCtx(), db, sl.get_TrxName());
			// fill params and call URL
			Map<MBXSMBDashboardParam, Object> parammap = new LinkedHashMap<MBXSMBDashboardParam, Object>();
			for (MBXSMBDashboardParam param : dashboard.getParameters()) {
				String value = Env.getContext(sl.getCtx(), windowNo, param.getColumnName(), false);
				value = Env.getContext(Env.getCtx(), windowNo, param.getColumnName(), false);
				if (Util.isEmpty(value) && param.isReadOnly()) {
					log.info("Could not find parameter " + param.getColumnName() + " for dashboard " + dashboard.getName());
					return;
				} else {
					parammap.put(param, value);
				}
			}

			String url = dashboard.getMetabaseEmbeddedUrl(parammap);

			// HTML content
			StringBuilder htmlContent = new StringBuilder("<iframe src=\"")
					.append(url)
					.append("\"")
					.append(" frameborder=\"0\"");
			if (dashboard.getWidth().signum() > 0)
				htmlContent.append(" width=\"").append(dashboard.getWidth()).append("\"");
			if (dashboard.getHeight().signum() > 0)
				htmlContent.append(" height=\"").append(dashboard.getHeight()).append("\"");
			htmlContent.append("allowtransparency></iframe>");
			Env.setContext(Env.getCtx(), windowNo, "METABASE_IFRAME", htmlContent.toString());
		}
	}

}	//	MBEventHandler
