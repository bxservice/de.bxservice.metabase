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

import java.sql.ResultSet;

import org.adempiere.base.IModelFactory;
import org.compiere.model.PO;
import org.compiere.util.Env;

import de.bxservice.metabase.model.MBXSMBDashboard;
import de.bxservice.metabase.model.MBXSMBDashboardAccess;
import de.bxservice.metabase.model.MBXSMBDashboardParam;
import de.bxservice.metabase.model.MBXSMBServer;

public class MBModelFactory implements IModelFactory {

	@Override
	public Class<?> getClass(String tableName) {
		if (MBXSMBDashboard.Table_Name.equals(tableName))
			return MBXSMBDashboard.class;
		if (MBXSMBServer.Table_Name.equals(tableName))
			return MBXSMBServer.class;
		if (MBXSMBDashboardParam.Table_Name.equals(tableName))
			return MBXSMBDashboardParam.class;
		if (MBXSMBDashboardAccess.Table_Name.equals(tableName))
			return MBXSMBDashboardAccess.class;
		return null;
	}

	@Override
	public PO getPO(String tableName, int Record_ID, String trxName) {
		if (MBXSMBDashboard.Table_Name.equals(tableName))
			return new MBXSMBDashboard(Env.getCtx(), Record_ID, trxName);
		if (MBXSMBServer.Table_Name.equals(tableName))
			return new MBXSMBServer(Env.getCtx(), Record_ID, trxName);
		if (MBXSMBDashboardParam.Table_Name.equals(tableName))
			return new MBXSMBDashboardParam(Env.getCtx(), Record_ID, trxName);
		if (MBXSMBDashboardAccess.Table_Name.equals(tableName))
			return new MBXSMBDashboardAccess(Env.getCtx(), Record_ID, trxName);
		return null;
	}

	@Override
	public PO getPO(String tableName, ResultSet rs, String trxName) {
		if (MBXSMBDashboard.Table_Name.equals(tableName))
			return new MBXSMBDashboard(Env.getCtx(), rs, trxName);
		if (MBXSMBServer.Table_Name.equals(tableName))
			return new MBXSMBServer(Env.getCtx(), rs, trxName);
		if (MBXSMBDashboardParam.Table_Name.equals(tableName))
			return new MBXSMBDashboardParam(Env.getCtx(), rs, trxName);
		if (MBXSMBDashboardAccess.Table_Name.equals(tableName))
			return new MBXSMBDashboardAccess(Env.getCtx(), rs, trxName);
		return null;
	}

}
