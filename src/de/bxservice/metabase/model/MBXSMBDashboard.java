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
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.compiere.model.MSysConfig;
import org.compiere.model.Query;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Util;
import org.zkoss.json.JSONObject;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 *	Metabase Dashboard Model
 *
 *  @author Carlos Ruiz - globalqss
 */
public class MBXSMBDashboard extends X_BXS_MBDashboard {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8257136864285156648L;

	// Dashboard Parameters
	private List<MBXSMBDashboardParam> parameters = null;

	/**
	 * 	Default Constructor
	 *	@param ctx context
	 *	@param BXS_MBDashboard_ID id
	 *	@param trxName transaction
	 */
	public MBXSMBDashboard (Properties ctx, int BXS_MBDashboard_ID, String trxName) {
		super(ctx, BXS_MBDashboard_ID, trxName);
	}	//	MBXSMBDashboard

	/**
	 * 	Load Constructor
	 *	@param ctx context
	 *	@param rs result set
	 *	@param trxName transaction
	 */
	public MBXSMBDashboard(Properties ctx, ResultSet rs, String trxName) {
		super(ctx, rs, trxName);
	}	//	MBXSMBDashboard

	/**
	 * List of dashboards with permission for the current user
	 * @return ID, Name pair for dashboards
	 */
	public static KeyNamePair[] getDashboardList() {
		final String sql = ""
				+ "SELECT d.BXS_MBDashboard_ID, "
				+ "       d.NAME "
				+ "FROM   BXS_MBDashboard d "
				+ "WHERE  d.AD_Client_ID IN ( 0, ? ) "
				+ "       AND d.IsActive = 'Y' "
				+ "       AND d.BXS_MBDashboard_ID IN ( "
				+ "           SELECT BXS_MBDashboard_ID "
				+ "           FROM   BXS_MBDashboardAccess "
				+ "           WHERE  IsActive = 'Y' "
				+ "                  AND ( ( AD_User_ID = ? ) "
				+ "                         OR ( AD_Role_ID = ? "
				+ "                               OR AD_Role_ID IN (SELECT Included_Role_ID "
				+ "                                                 FROM   AD_Role_Included "
				+ "                                                 WHERE  AD_Role_ID = ? "
				+ "                                                        AND IsActive = 'Y')))) "
				+ "ORDER  BY d.NAME";
		int clientId = Env.getAD_Client_ID(Env.getCtx());
		int userId = Env.getAD_User_ID(Env.getCtx());
		int roleId = Env.getAD_Role_ID(Env.getCtx());
		KeyNamePair[] list = DB.getKeyNamePairs(null, sql, true, clientId, userId, roleId, roleId);
		return list;
	}

	public String getMetabaseEmbeddedUrl(JSONObject params) {
		MBXSMBServer mbserver = new MBXSMBServer(getCtx(), getBXS_MBServer_ID(), get_TrxName());
		String metaBaseEncodedSecretKey = Base64.getEncoder().encodeToString(mbserver.getBXS_MBSecretKey().getBytes());
		final Date createdDate = new Date();
	    final Date expirationDate = new Date(System.currentTimeMillis() + (getBXS_MBTokenExpiration() * 10000)); // 10 seconds

		Map<String, Object> payload = new HashMap<String, Object>();
		JSONObject resource = new JSONObject();
		resource.put("dashboard", 4);
		payload.put("resource", resource);
		payload.put("params", params);

		StringBuilder metabaseSiteURL = new StringBuilder(mbserver.getURL());
		
		metabaseSiteURL.append(MSysConfig.getValue("BXS_METABASE_EMBED_LOCATION", "/embed/dashboard/"));
		
	    String jwtToken = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setClaims(payload)
				.signWith(SignatureAlgorithm.HS256, metaBaseEncodedSecretKey)
				.setIssuedAt(createdDate)
				.setExpiration(expirationDate)
				.compact();
		metabaseSiteURL.append(jwtToken);
		
		metabaseSiteURL.append("#bordered=");
		if (isBXS_MBIsBordered())
			metabaseSiteURL.append("true");
		else
			metabaseSiteURL.append("false");
		metabaseSiteURL.append("&titled=");
		if (isBXS_MBIsTitled())
			metabaseSiteURL.append("true");
		else
			metabaseSiteURL.append("false");
		String theme = getBXS_MBTheme();
		if (!Util.isEmpty(theme, true)) {
			metabaseSiteURL.append("&theme=").append(theme);
		}
		return metabaseSiteURL.toString();
	}

	public List<MBXSMBDashboardParam> getParameters() {
		if (parameters == null) {
			parameters = new Query(getCtx(), MBXSMBDashboardParam.Table_Name, "BXS_MBDashboard_ID=? AND AD_Client_ID IN (0, ?) ", get_TrxName())
					.setParameters(new Object[]{getBXS_MBDashboard_ID(),Env.getAD_Client_ID(Env.getCtx())})
					.setOnlyActiveRecords(true)
					.setOrderBy("SeqNo")
					.list();
		}
		return parameters;
	} //getParameters

}	//	MBXSMBDashboard
