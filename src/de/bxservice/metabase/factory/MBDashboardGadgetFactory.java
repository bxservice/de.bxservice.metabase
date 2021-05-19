/**********************************************************************
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

import org.adempiere.webui.factory.IDashboardGadgetFactory;
import org.compiere.model.MDashboardContent;
import org.zkoss.zk.ui.Component;

import de.bxservice.metabase.webui.dashboard.MBDashboardPanel;

public class MBDashboardGadgetFactory implements IDashboardGadgetFactory {

	/**
	 * Return metabase gadget
	 */
	public Component getGadget(String uri, Component parent, MDashboardContent dc) {

		if (uri != null && uri.equals("metabase") && dc != null) {
			return new MBDashboardPanel(dc);
		}

		return null;
	}

	/**
	 * Not to be used - for backward compatibility
	 */
	@Override
	public Component getGadget(String uri, Component parent) {
		return getGadget(uri, parent, null);
	}

}