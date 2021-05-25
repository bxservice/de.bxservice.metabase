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

package de.bxservice.metabase.webui.editor;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;

import org.adempiere.webui.editor.IEditorConfiguration;
import org.adempiere.webui.editor.WEditor;
import org.compiere.model.GridField;
import org.compiere.util.CLogger;
import org.compiere.util.Util;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Html;
import org.zkoss.zul.Panel;
import org.zkoss.zul.Panelchildren;

import de.bxservice.metabase.model.MBXSMBDashboard;
import de.bxservice.metabase.model.MBXSMBDashboardParam;

public class WMBEditor extends WEditor {

	private static final String ON_RENDER_CONTENT = "onRenderContent";
	private MBXSMBDashboard dashboard;
	private String fieldStyle;

	private final static CLogger logger  = CLogger.getCLogger(WMBEditor.class);

	/**
	 * 
	 * @param gridField
	 * @param windowNo
	 */
	public WMBEditor(GridField gridField, int windowNo) {
		this(gridField, windowNo, false, null, null);
	}

	/**
	 * 
	 * @param gridField
	 * @param windowNo
	 * @param tableEditor
	 * @param editorConfiguration
	 * @param db 
	 */
	public WMBEditor(GridField gridField, int windowNo, boolean tableEditor, IEditorConfiguration editorConfiguration, MBXSMBDashboard db) {
		super(new Panel(), gridField, tableEditor, editorConfiguration);

		dashboard = db;
		Panelchildren pc = new Panelchildren();
		getComponent().appendChild(pc);		
/*
		if (dashboard.get_ID() > 0) {
			Caption caption = new Caption(dashboard.get_Translation(MDashboardContent.COLUMNNAME_Name));
			getComponent().appendChild(caption);
		}
*/
		getComponent().addEventListener(ON_RENDER_CONTENT, this);
	}

	/* (non-Javadoc)
	 * @see org.zkoss.zk.ui.event.EventListener#onEvent(org.zkoss.zk.ui.event.Event)
	 */
	@Override
	public void onEvent(Event event) throws Exception {
		if (event.getName().equals(ON_RENDER_CONTENT)) {
			try {
				render();
			} catch (Exception e) {
				logger.log(Level.SEVERE, e.getLocalizedMessage(), e);
			}		
		}
	}

	/* (non-Javadoc)
	 * @see org.adempiere.webui.editor.WEditor#setReadWrite(boolean)
	 */
	@Override
	public void setReadWrite(boolean readWrite) {
	}

	/* (non-Javadoc)
	 * @see org.adempiere.webui.editor.WEditor#isReadWrite()
	 */
	@Override
	public boolean isReadWrite() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.adempiere.webui.editor.WEditor#setValue(java.lang.Object)
	 */
	@Override
	public void setValue(Object value) {
	}

	/* (non-Javadoc)
	 * @see org.adempiere.webui.editor.WEditor#getValue()
	 */
	@Override
	public Object getValue() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.adempiere.webui.editor.WEditor#getDisplay()
	 */
	@Override
	public String getDisplay() {
		return null;
	}

	@Override
	public void dynamicDisplay() {
		super.dynamicDisplay();
		Events.postEvent(ON_RENDER_CONTENT, getComponent(), null);
	}

	/* (non-Javadoc)
	 * @see org.adempiere.webui.editor.WEditor#getComponent()
	 */
	@Override
	public Panel getComponent() {
		return (Panel) super.getComponent();
	}

	private void render() throws Exception {
		Panel panel = getComponent();
		panel.setSclass("dashboard-field-panel");
		Panelchildren pc = panel.getPanelchildren();
		pc.getChildren().clear();
		Div div = new Div();		
		if (!Util.isEmpty(fieldStyle))
			div.setStyle(fieldStyle);
		render(div, dashboard);
		pc.appendChild(div);
	}

	/* (non-Javadoc)
	 * @see org.adempiere.webui.editor.WEditor#setFieldStyle(java.lang.String)
	 */
	@Override
	protected void setFieldStyle(String style) {
		fieldStyle = style;
	}


	public void render(Component content, MBXSMBDashboard dc) throws Exception {
		// fill params and call URL
		Map<MBXSMBDashboardParam, Object> parammap = new LinkedHashMap<MBXSMBDashboardParam, Object>();
		for (MBXSMBDashboardParam param : dc.getParameters()) {
			Object value = gridField.getGridTab().getValue(param.getColumnName());
			if (value == null)
				logger.warning("Could not find parameter " + param.getColumnName() + " for dashboard " + dc.getName());
			else
				parammap.put(param, value);
		}

		String url = dashboard.getMetabaseEmbeddedUrl(parammap);

		// HTML content
		StringBuilder htmlContent = new StringBuilder("<iframe src=\"")
				.append(url)
				.append("\"")
				.append(" frameborder=\"0\"");
		if (dc.getWidth().signum() > 0) {
			if (content instanceof HtmlBasedComponent)
				((HtmlBasedComponent)content).setWidth(dc.getWidth() + "px");
			htmlContent.append(" width=\"").append(dc.getWidth()).append("\"");
		}
		if (dc.getHeight().signum() > 0) {
			if (content instanceof HtmlBasedComponent)
				((HtmlBasedComponent)content).setHeight(dc.getHeight() + "px");
			htmlContent.append(" height=\"").append(dc.getHeight()).append("\"");
		}
		htmlContent.append("allowtransparency></iframe>");

		StringBuilder result = new StringBuilder(htmlContent);

		Html html = new Html();
		html.setContent(result.toString());
		content.appendChild(html);
	}

}