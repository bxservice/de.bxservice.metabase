/******************************************************************************
 * Product: iDempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2012 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software, you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program, if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/
package de.bxservice.metabase.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import org.compiere.model.*;
import org.compiere.util.KeyNamePair;

/** Generated Interface for BXS_MBDashboard
 *  @author iDempiere (generated) 
 *  @version Release 8.2
 */
@SuppressWarnings("all")
public interface I_BXS_MBDashboard 
{

    /** TableName=BXS_MBDashboard */
    public static final String Table_Name = "BXS_MBDashboard";

    /** AD_Table_ID=1000001 */
    public static final int Table_ID = MTable.getTable_ID(Table_Name);

    KeyNamePair Model = new KeyNamePair(Table_ID, Table_Name);

    /** AccessLevel = 6 - System - Client 
     */
    BigDecimal accessLevel = BigDecimal.valueOf(6);

    /** Load Meta Data */

    /** Column name AD_Client_ID */
    public static final String COLUMNNAME_AD_Client_ID = "AD_Client_ID";

	/** Get Client.
	  * Client/Tenant for this installation.
	  */
	public int getAD_Client_ID();

    /** Column name AD_Org_ID */
    public static final String COLUMNNAME_AD_Org_ID = "AD_Org_ID";

	/** Set Organization.
	  * Organizational entity within client
	  */
	public void setAD_Org_ID (int AD_Org_ID);

	/** Get Organization.
	  * Organizational entity within client
	  */
	public int getAD_Org_ID();

    /** Column name BXS_AutoRefresh */
    public static final String COLUMNNAME_BXS_AutoRefresh = "BXS_AutoRefresh";

	/** Set Auto Refresh in Seconds.
	  * If zero do not autorefresh
	  */
	public void setBXS_AutoRefresh (int BXS_AutoRefresh);

	/** Get Auto Refresh in Seconds.
	  * If zero do not autorefresh
	  */
	public int getBXS_AutoRefresh();

    /** Column name BXS_MBDashboard_ID */
    public static final String COLUMNNAME_BXS_MBDashboard_ID = "BXS_MBDashboard_ID";

	/** Set Dashboard	  */
	public void setBXS_MBDashboard_ID (int BXS_MBDashboard_ID);

	/** Get Dashboard	  */
	public int getBXS_MBDashboard_ID();

    /** Column name BXS_MBDashboardNo */
    public static final String COLUMNNAME_BXS_MBDashboardNo = "BXS_MBDashboardNo";

	/** Set Dashboard #	  */
	public void setBXS_MBDashboardNo (int BXS_MBDashboardNo);

	/** Get Dashboard #	  */
	public int getBXS_MBDashboardNo();

    /** Column name BXS_MBDashboard_UU */
    public static final String COLUMNNAME_BXS_MBDashboard_UU = "BXS_MBDashboard_UU";

	/** Set BXS_MBDashboard_UU	  */
	public void setBXS_MBDashboard_UU (String BXS_MBDashboard_UU);

	/** Get BXS_MBDashboard_UU	  */
	public String getBXS_MBDashboard_UU();

    /** Column name BXS_MBIsBordered */
    public static final String COLUMNNAME_BXS_MBIsBordered = "BXS_MBIsBordered";

	/** Set Bordered	  */
	public void setBXS_MBIsBordered (boolean BXS_MBIsBordered);

	/** Get Bordered	  */
	public boolean isBXS_MBIsBordered();

    /** Column name BXS_MBIsTitled */
    public static final String COLUMNNAME_BXS_MBIsTitled = "BXS_MBIsTitled";

	/** Set Titled	  */
	public void setBXS_MBIsTitled (boolean BXS_MBIsTitled);

	/** Get Titled	  */
	public boolean isBXS_MBIsTitled();

    /** Column name BXS_MBServer_ID */
    public static final String COLUMNNAME_BXS_MBServer_ID = "BXS_MBServer_ID";

	/** Set Metabase Server	  */
	public void setBXS_MBServer_ID (int BXS_MBServer_ID);

	/** Get Metabase Server	  */
	public int getBXS_MBServer_ID();

	public I_BXS_MBServer getBXS_MBServer() throws RuntimeException;

    /** Column name BXS_MBTheme */
    public static final String COLUMNNAME_BXS_MBTheme = "BXS_MBTheme";

	/** Set Theme	  */
	public void setBXS_MBTheme (String BXS_MBTheme);

	/** Get Theme	  */
	public String getBXS_MBTheme();

    /** Column name BXS_MBTokenExpiration */
    public static final String COLUMNNAME_BXS_MBTokenExpiration = "BXS_MBTokenExpiration";

	/** Set Token Expiration in Seconds	  */
	public void setBXS_MBTokenExpiration (int BXS_MBTokenExpiration);

	/** Get Token Expiration in Seconds	  */
	public int getBXS_MBTokenExpiration();

    /** Column name Created */
    public static final String COLUMNNAME_Created = "Created";

	/** Get Created.
	  * Date this record was created
	  */
	public Timestamp getCreated();

    /** Column name CreatedBy */
    public static final String COLUMNNAME_CreatedBy = "CreatedBy";

	/** Get Created By.
	  * User who created this records
	  */
	public int getCreatedBy();

    /** Column name Height */
    public static final String COLUMNNAME_Height = "Height";

	/** Set Height	  */
	public void setHeight (BigDecimal Height);

	/** Get Height	  */
	public BigDecimal getHeight();

    /** Column name Help */
    public static final String COLUMNNAME_Help = "Help";

	/** Set Comment/Help.
	  * Comment or Hint
	  */
	public void setHelp (String Help);

	/** Get Comment/Help.
	  * Comment or Hint
	  */
	public String getHelp();

    /** Column name IsActive */
    public static final String COLUMNNAME_IsActive = "IsActive";

	/** Set Active.
	  * The record is active in the system
	  */
	public void setIsActive (boolean IsActive);

	/** Get Active.
	  * The record is active in the system
	  */
	public boolean isActive();

    /** Column name Name */
    public static final String COLUMNNAME_Name = "Name";

	/** Set Name.
	  * Alphanumeric identifier of the entity
	  */
	public void setName (String Name);

	/** Get Name.
	  * Alphanumeric identifier of the entity
	  */
	public String getName();

    /** Column name Updated */
    public static final String COLUMNNAME_Updated = "Updated";

	/** Get Updated.
	  * Date this record was updated
	  */
	public Timestamp getUpdated();

    /** Column name UpdatedBy */
    public static final String COLUMNNAME_UpdatedBy = "UpdatedBy";

	/** Get Updated By.
	  * User who updated this records
	  */
	public int getUpdatedBy();

    /** Column name Width */
    public static final String COLUMNNAME_Width = "Width";

	/** Set Width	  */
	public void setWidth (BigDecimal Width);

	/** Get Width	  */
	public BigDecimal getWidth();
}
