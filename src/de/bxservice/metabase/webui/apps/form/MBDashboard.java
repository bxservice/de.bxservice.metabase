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

package de.bxservice.metabase.webui.apps.form;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.GridFactory;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Listbox;
import org.adempiere.webui.component.ListboxFactory;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WebEditorFactory;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.theme.ThemeManager;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.compiere.model.GridField;
import org.compiere.model.GridFieldVO;
import org.compiere.util.CLogger;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.json.JSONObject;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Cell;
import org.zkoss.zul.Center;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.North;
import org.zkoss.zul.Separator;
import org.zkoss.zul.Timer;

import de.bxservice.metabase.model.MBXSMBDashboard;
import de.bxservice.metabase.model.MBXSMBDashboardAccess;
import de.bxservice.metabase.model.MBXSMBDashboardParam;

/**
 *
 * @author Carlos Ruiz
 *
 */
public class MBDashboard implements IFormController, EventListener<Event>, ValueChangeListener {

	/* TODO: Show comment from dashboard - could be in the QuickInfo section */
	/* TODO: Show message ... auto-refresh every N seconds - next refresh on ... */
	/* TODO: Cache for MBXSMBDashboard */

	private static final String MB_REFRESH_BUTTON_ID = "refreshMB";

	public static CLogger log = CLogger.getCLogger(MBDashboard.class);

	private CustomForm		mbForm = new CustomForm();
	// private int         	m_WindowNo = 0;
	private Borderlayout	mainLayout	= new Borderlayout();
	private Panel			northPanel = new Panel();
	private Panel			centerPanel = new Panel();
	private Iframe			iframe = new Iframe();

	private Label lDashboardh = new Label();
	private Listbox dashboardBox = ListboxFactory.newDropdownListbox();
	private Button bRefresh = new Button();
	private int dashboardId = -1;
	private MBXSMBDashboard dashboard;;

	private Timer timer = new Timer();

	//Parameters
	private Hbox paramPanelHbox = new Hbox();
	private Div boardParamsDiv;
	private ArrayList<WEditor> m_sEditors = new ArrayList<WEditor>();
	private Map<MBXSMBDashboardParam, WEditor> mapEditorParameter = new HashMap<MBXSMBDashboardParam, WEditor>();

	public MBDashboard() {
		super();
		// m_WindowNo = mbForm.getWindowNo();
		try {
			dynInit();
			jbInit();
		} catch (Exception ex) {
			log.log(Level.SEVERE, "init", ex);
		}
	}

	/**
	 * 	Static init
	 *	@throws Exception
	 */
	private void jbInit() throws Exception {
		mbForm.setClosable(true);
		mbForm.setMaximizable(true);
		ZKUpdateUtil.setWidth(mbForm, "99%");
		ZKUpdateUtil.setHeight(mbForm, "100%");
		mbForm.appendChild (mainLayout);
		mbForm.setStyle("position: absolute; padding: 0; margin: 0");

		ZKUpdateUtil.setHflex(mainLayout, "1");
		ZKUpdateUtil.setHeight(mainLayout, "100%");
		ZKUpdateUtil.setVflex(centerPanel, "min");

		//North Panel
		Grid gridLayout = GridFactory.newGridLayout();

		North north = new North();
		north.appendChild(northPanel);
		ZKUpdateUtil.setVflex(north, "min");
		ZKUpdateUtil.setWidth(northPanel, "100%");
		mainLayout.appendChild(north);

		lDashboardh.setText(Msg.translate(Env.getCtx(), "BXS_MBDashboard_ID"));
		Rows rows = gridLayout.newRows();
		Row row = rows.newRow();
		if (ThemeManager.isUseFontIconForImage())
			bRefresh.setIconSclass("z-icon-Refresh");
		else
			bRefresh.setImage(ThemeManager.getThemeResource("images/Refresh16.png"));
		bRefresh.setId(MB_REFRESH_BUTTON_ID);
		bRefresh.setTooltiptext(Msg.getMsg(Env.getCtx(), "Refresh"));
		bRefresh.addEventListener(Events.ON_CLICK, this);

		Hbox northPanelHbox = new Hbox();
		northPanelHbox.appendChild(lDashboardh.rightAlign());
		northPanelHbox.appendChild(dashboardBox);
		northPanelHbox.appendChild(bRefresh);
		Cell cell = new Cell();
		cell.setColspan(3);
		cell.setRowspan(1);
		cell.setAlign("left");
		cell.appendChild(northPanelHbox);
		row.appendChild(cell);
		northPanel.appendChild(northPanelHbox);

		row.appendChild(paramPanelHbox);

		northPanel.appendChild(gridLayout);

		Center center = new Center();
		center.appendChild(centerPanel);
		centerPanel.appendChild(iframe);
		ZKUpdateUtil.setVflex(iframe, "max");
		ZKUpdateUtil.setWidth(iframe, "100%");
		ZKUpdateUtil.setHeight(iframe, "100%");
		ZKUpdateUtil.setVflex(center, "max");
		ZKUpdateUtil.setWidth(centerPanel, "100%");
		ZKUpdateUtil.setHeight(centerPanel, "100%");
		mainLayout.appendChild(center);

		timer.addEventListener(Events.ON_TIMER, this);
		timer.setVisible(false);
		centerPanel.appendChild(timer);
	}	//	jbInit

	/**
	 *  Initialize List of dashboards with permission
	 * @throws Exception 
	 */
	private void dynInit() throws Exception {
		int dashboardDefaultId = MBXSMBDashboardAccess.getDefaultDashboardList();
		for (KeyNamePair db : MBXSMBDashboard.getDashboardList()) {
			dashboardBox.addItem(db);
			if (db.getKey() == dashboardDefaultId) {
				dashboardBox.setSelectedKeyNamePair(db);
				dashboardId = dashboardDefaultId;
				dashboard = new MBXSMBDashboard(Env.getCtx(), dashboardId, null);
			}
		}
		dashboardBox.addEventListener(Events.ON_SELECT, this);

		initParameters();
		dashboardRefresh();
	}   //  dynList

	/**************************************************************************
	 *  Action Listener
	 *  @param e event
	 */
	public void onEvent(Event e) {
		if (Events.ON_SELECT.equals(e.getName()) && e.getTarget() instanceof Listbox) {
			if (dashboardBox.getSelectedIndex() != -1) {
				KeyNamePair dbknp = null;
				dashboardId = -1;
				dashboard = null;
				dbknp = (KeyNamePair)dashboardBox.getSelectedItem().toKeyNamePair();	
				if (dbknp != null) {
					dashboardId = dbknp.getKey();
					dashboard = new MBXSMBDashboard(Env.getCtx(), dashboardId, null);
					initParameters();
				}
				dashboardRefresh();
			}
		} else if (Events.ON_CLICK.equals(e.getName()) && e.getTarget() instanceof Button) {
			Button clickedButton = (Button) e.getTarget();
			if (clickedButton.getId().equals(MB_REFRESH_BUTTON_ID)) {
				if (dashboardId != -1) {
					dashboardRefresh();
				}
			}
		} else if (Events.ON_TIMER.equals(e.getName())) {
			//Auto refresh
			if (dashboardId != -1) {
				dashboardRefresh();
			}
		}
	} //onEvent

	@Override
	public void valueChange(ValueChangeEvent evt) {
		if (evt != null && evt.getSource() instanceof WEditor) {
			WEditor editor = (WEditor) evt.getSource();
			MBXSMBDashboardParam param = null;
			for (Entry<MBXSMBDashboardParam, WEditor> es : mapEditorParameter.entrySet()) {
				if (es.getValue() == editor) {
					param = es.getKey();
					break;
				}
			}
			if (param != null && param.isBXS_MBIsTriggerRefresh()) {
				dashboardRefresh();
			}
		}
	}

	public ADForm getForm() {
		return mbForm;
	}

	private void dashboardRefresh() {
		if (dashboardId <= 0) {
			iframe.setSrc(null);
		} else {
			JSONObject params = new JSONObject();
			for (MBXSMBDashboardParam param : dashboard.getParameters()) {
				WEditor editor = mapEditorParameter.get(param);
				params.put(param.getColumnName(), editor.getValue());
			}

			String url = dashboard.getMetabaseEmbeddedUrl(params);

			if (dashboard.getWidth() != null && dashboard.getWidth().intValue() > 0) {
				ZKUpdateUtil.setWidth(iframe, dashboard.getWidth().intValue() + "px");
			}
			if (dashboard.getHeight() != null && dashboard.getHeight().intValue() > 0) {
				ZKUpdateUtil.setHeight(iframe, dashboard.getHeight().intValue() + "px");
			}
			iframe.setSrc(url);

			// Auto refresh in seconds
			setTimer(dashboard.getBXS_AutoRefresh());
		}
	}

	private void setTimer(int refreshInterval) {
		if (refreshInterval > 0) {
			timer.setDelay(refreshInterval * 1000);
			timer.start();
		} else {
			timer.stop();
		}
	}

	/**
	 * Load parameters for Kanban Board
	 */
	private void initParameters() {
		if (boardParamsDiv != null)
			paramPanelHbox.removeChild(boardParamsDiv);
		boardParamsDiv = new Div();
		boardParamsDiv.setSclass("padding-left: 5px;");

		if (dashboardId > 0 && dashboard.getParameters().size() > 0) {

			fillParameterEditors();
			for (int i = 0; i < m_sEditors.size(); i++) {
				WEditor editor = m_sEditors.get(i);
				boardParamsDiv.appendChild(new Separator("vertical"));
				boardParamsDiv.appendChild(editor.getLabel());
				editor.getLabel().setStyle("padding-right:3px;");
				boardParamsDiv.appendChild(editor.getComponent());
			}
		}
		boardParamsDiv.appendChild(new Separator("vertical"));
		paramPanelHbox.appendChild(boardParamsDiv);
	}

	private void fillParameterEditors() {
		m_sEditors.clear();
		mapEditorParameter.clear();
		
		for (MBXSMBDashboardParam param : dashboard.getParameters()) {
			GridField field = getGridField(param);
			WEditor editor = WebEditorFactory.getEditor(field, true);
			editor.setMandatory(false);
			editor.setReadWrite(true);
			editor.addValueChangeListener(this);

			Label label = editor.getLabel();
			label.setValue(field.getHeader());

			Object defaultValue = field.getDefaultForPanel();
			if (defaultValue != null) {
				editor.setValue(defaultValue);
			}

			if (field.isReadOnly()) {
				editor.setReadWrite(false);
			}
			
			m_sEditors.add(editor);
			mapEditorParameter.put(param, editor);
		}
	}

	private GridField getGridField(MBXSMBDashboardParam param) {
		GridFieldVO gvo = GridFieldVO.createParameter(Env.getCtx(), mbForm.getWindowNo(), 0, 0, 0,
				param.getColumnName(), param.getName(), param.getAD_Reference_ID(), param.getAD_Reference_Value_ID(),
				false, false, null);
		gvo.DefaultValue = param.getDefaultValue();
		gvo.IsReadOnly = param.isReadOnly();
		GridField field = new GridField(gvo);
		return field;
	}

}
