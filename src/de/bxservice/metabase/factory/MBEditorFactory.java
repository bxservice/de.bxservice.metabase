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

package de.bxservice.metabase.factory;

import org.adempiere.webui.editor.IEditorConfiguration;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.factory.IEditorFactory;
import org.compiere.model.GridField;
import org.compiere.model.GridTab;
import org.compiere.model.MDashboardContent;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;

import de.bxservice.metabase.model.MBXSMBDashboard;
import de.bxservice.metabase.webui.editor.WMBEditor;

public class MBEditorFactory implements IEditorFactory {

	@Override
	public WEditor getEditor(GridTab gridTab, GridField gridField, boolean tableEditor) {
		return getEditor(gridTab, gridField, tableEditor, null);
	}

	@Override
	public WEditor getEditor(GridTab gridTab, GridField gridField, boolean tableEditor, IEditorConfiguration editorConfiguration) {
		if (gridField == null)
			return null;

		WEditor editor = null;
		int displayType = gridField.getDisplayType();

		/** Not a Field */
		if (gridField.isHeading())
			return null;

		if (displayType == DisplayType.DashboardContent) {
			int dcId = gridField.getPA_DashboardContent_ID();
			if (dcId > 0) {
				MDashboardContent dc = new MDashboardContent(Env.getCtx(), dcId, null);
				int dbId = dc.get_ValueAsInt("BXS_MBDashboard_ID");
				if (dbId > 0) {
					MBXSMBDashboard db = new MBXSMBDashboard(Env.getCtx(), dbId, null);
					editor = new WMBEditor(gridField, (gridTab == null ? 0 : gridTab.getWindowNo()), tableEditor, editorConfiguration, db);
					return editor;
				}
			}
		}

		return null;
	}
}
