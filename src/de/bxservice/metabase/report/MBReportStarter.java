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

package de.bxservice.metabase.report;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import org.adempiere.exceptions.AdempiereException;
import org.adempiere.util.IProcessUI;
import org.adempiere.webui.apps.AEnv;
import org.adempiere.webui.component.Window;
import org.adempiere.webui.part.WindowContainer;
import org.adempiere.webui.session.SessionManager;
import org.compiere.model.MPInstance;
import org.compiere.model.MPInstancePara;
import org.compiere.model.MProcess;
import org.compiere.process.ClientProcess;
import org.compiere.process.ProcessCall;
import org.compiere.process.ProcessInfo;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Trx;
import org.zkoss.zk.ui.Executions;

import de.bxservice.metabase.model.MBXSMBDashboard;
import de.bxservice.metabase.model.MBXSMBDashboardParam;
import de.bxservice.metabase.webui.window.MBViewer;

public class MBReportStarter implements ProcessCall, ClientProcess {

	/** Logger */
	private static final CLogger log = CLogger.getCLogger(MBReportStarter.class);

	@SuppressWarnings("unused")
	private IProcessUI m_processUI;

	/**
	 *  Start the process.
	 *  @param ctx context
	 *  @param pi standard process info
	 *  @param trx
	 *  @return true if success
	 */
	public boolean startProcess(Properties ctx, ProcessInfo pi, Trx trx) {

		MPInstance instance = new MPInstance(Env.getCtx(), pi.getAD_PInstance_ID(), null);
		MPInstancePara[] ppars = instance.getParameters();

		MProcess process = MProcess.get(pi.getAD_Process_ID());
		int dbId = process.get_ValueAsInt("BXS_MBDashboard_ID");
		
		if (dbId <= 0) {
			throw new AdempiereException("Process " + process.getName() + " doesn't have configured dashboard");
		}
		
		boolean newTab = process.get_ValueAsBoolean("BXS_MBIsOpenNewTab");
		MBXSMBDashboard dashboard = new MBXSMBDashboard(Env.getCtx(), dbId, null);
		
		Map<MBXSMBDashboardParam, Object> parammap = new LinkedHashMap<MBXSMBDashboardParam, Object>();
		for (MBXSMBDashboardParam param : dashboard.getParameters()) {
			Object value = null;
			for (MPInstancePara ppar : ppars) {
				if (ppar.getParameterName().equalsIgnoreCase(param.getColumnName())) {
					if (DisplayType.isDate(param.getAD_Reference_ID()))
						value = ppar.getP_Date();
					else if (DisplayType.isNumeric(param.getAD_Reference_ID())
							|| DisplayType.isID(param.getAD_Reference_ID()))
						value = ppar.getP_Number();
					else
						value = ppar.getP_String();
					break;
				}
			}
			if (value == null)
				log.warning("Could not find parameter " + param.getColumnName() + " for dashboard " + dashboard.getName());
			else
				parammap.put(param, value);
		}

		String url = dashboard.getMetabaseEmbeddedUrl(parammap);
		if (newTab)
			openNewTab(url);
		else
			openViewer(pi, url);
		return true;
	}

	@Override
	public void setProcessUI(IProcessUI processUI) {
		m_processUI = processUI;
	}

	public void openNewTab(String url) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Executions.getCurrent().sendRedirect(url, "_blank");
			}
		};
		AEnv.executeAsyncDesktopTask(runnable);
	}

	public void openViewer(final ProcessInfo pi, String url) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				Window viewer = new MBViewer(pi, url);
				viewer.setAttribute(Window.MODE_KEY, Window.MODE_EMBEDDED);
				viewer.setAttribute(Window.INSERT_POSITION_KEY, Window.INSERT_NEXT);
				viewer.setAttribute(WindowContainer.DEFER_SET_SELECTED_TAB, Boolean.TRUE);
				SessionManager.getAppDesktop().showWindow(viewer);
			}
		};
		AEnv.executeAsyncDesktopTask(runnable);
	}

}