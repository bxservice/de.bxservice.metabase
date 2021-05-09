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

package de.bxservice.metabase.model;

import java.sql.ResultSet;
import java.util.Properties;

/**
 *	Metabase Server Model
 *
 *  @author Carlos Ruiz - globalqss
 */
public class MBXSMBServer extends X_BXS_MBServer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 576316256276471576L;

	/**
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param BXS_MBDashboard_ID id
	 *	@param trxName transaction
	 */
	public MBXSMBServer (Properties ctx, int BXS_MBServer_ID, String trxName) {
		super(ctx, BXS_MBServer_ID, trxName);
	}	//	MBXSMBServer

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MBXSMBServer(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}	//	MBXSMBServer

}	//	MBXSMBServer
