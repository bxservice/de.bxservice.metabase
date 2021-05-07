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
/** Generated Model - DO NOT CHANGE */
package de.bxservice.metabase.model;

import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;

/** Generated Model for BXS_MBDashboardAccess
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_BXS_MBDashboardAccess extends PO implements I_BXS_MBDashboardAccess, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210507L;

    /** Standard Constructor */
    public X_BXS_MBDashboardAccess (Properties ctx, int BXS_MBDashboardAccess_ID, String trxName)
    {
      super (ctx, BXS_MBDashboardAccess_ID, trxName);
      /** if (BXS_MBDashboardAccess_ID == 0)
        {
			setBXS_MBDashboardAccess_ID (0);
			setBXS_MBDashboard_ID (0);
			setBXS_MBIsDefault (false);
// N
        } */
    }

    /** Load Constructor */
    public X_BXS_MBDashboardAccess (Properties ctx, ResultSet rs, String trxName)
    {
      super (ctx, rs, trxName);
    }

    /** AccessLevel
      * @return 6 - System - Client 
      */
    protected int get_AccessLevel()
    {
      return accessLevel.intValue();
    }

    /** Load Meta Data */
    protected POInfo initPO (Properties ctx)
    {
      POInfo poi = POInfo.getPOInfo (ctx, Table_ID, get_TrxName());
      return poi;
    }

    public String toString()
    {
      StringBuilder sb = new StringBuilder ("X_BXS_MBDashboardAccess[")
        .append(get_ID()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Role getAD_Role() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Role)MTable.get(getCtx(), org.compiere.model.I_AD_Role.Table_Name)
			.getPO(getAD_Role_ID(), get_TrxName());	}

	/** Set Role.
		@param AD_Role_ID 
		Responsibility Role
	  */
	public void setAD_Role_ID (int AD_Role_ID)
	{
		if (AD_Role_ID < 0) 
			set_Value (COLUMNNAME_AD_Role_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Role_ID, Integer.valueOf(AD_Role_ID));
	}

	/** Get Role.
		@return Responsibility Role
	  */
	public int getAD_Role_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Role_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_User getAD_User() throws RuntimeException
    {
		return (org.compiere.model.I_AD_User)MTable.get(getCtx(), org.compiere.model.I_AD_User.Table_Name)
			.getPO(getAD_User_ID(), get_TrxName());	}

	/** Set User/Contact.
		@param AD_User_ID 
		User within the system - Internal or Business Partner Contact
	  */
	public void setAD_User_ID (int AD_User_ID)
	{
		if (AD_User_ID < 1) 
			set_Value (COLUMNNAME_AD_User_ID, null);
		else 
			set_Value (COLUMNNAME_AD_User_ID, Integer.valueOf(AD_User_ID));
	}

	/** Get User/Contact.
		@return User within the system - Internal or Business Partner Contact
	  */
	public int getAD_User_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_User_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Metabase Dashboard Access.
		@param BXS_MBDashboardAccess_ID Metabase Dashboard Access	  */
	public void setBXS_MBDashboardAccess_ID (int BXS_MBDashboardAccess_ID)
	{
		if (BXS_MBDashboardAccess_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BXS_MBDashboardAccess_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BXS_MBDashboardAccess_ID, Integer.valueOf(BXS_MBDashboardAccess_ID));
	}

	/** Get Metabase Dashboard Access.
		@return Metabase Dashboard Access	  */
	public int getBXS_MBDashboardAccess_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BXS_MBDashboardAccess_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BXS_MBDashboardAccess_UU.
		@param BXS_MBDashboardAccess_UU BXS_MBDashboardAccess_UU	  */
	public void setBXS_MBDashboardAccess_UU (String BXS_MBDashboardAccess_UU)
	{
		set_ValueNoCheck (COLUMNNAME_BXS_MBDashboardAccess_UU, BXS_MBDashboardAccess_UU);
	}

	/** Get BXS_MBDashboardAccess_UU.
		@return BXS_MBDashboardAccess_UU	  */
	public String getBXS_MBDashboardAccess_UU () 
	{
		return (String)get_Value(COLUMNNAME_BXS_MBDashboardAccess_UU);
	}

	public I_BXS_MBDashboard getBXS_MBDashboard() throws RuntimeException
    {
		return (I_BXS_MBDashboard)MTable.get(getCtx(), I_BXS_MBDashboard.Table_Name)
			.getPO(getBXS_MBDashboard_ID(), get_TrxName());	}

	/** Set Metabase Dashboard.
		@param BXS_MBDashboard_ID Metabase Dashboard	  */
	public void setBXS_MBDashboard_ID (int BXS_MBDashboard_ID)
	{
		if (BXS_MBDashboard_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BXS_MBDashboard_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BXS_MBDashboard_ID, Integer.valueOf(BXS_MBDashboard_ID));
	}

	/** Get Metabase Dashboard.
		@return Metabase Dashboard	  */
	public int getBXS_MBDashboard_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BXS_MBDashboard_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Is Default for User.
		@param BXS_MBIsDefault Is Default for User	  */
	public void setBXS_MBIsDefault (boolean BXS_MBIsDefault)
	{
		set_Value (COLUMNNAME_BXS_MBIsDefault, Boolean.valueOf(BXS_MBIsDefault));
	}

	/** Get Is Default for User.
		@return Is Default for User	  */
	public boolean isBXS_MBIsDefault () 
	{
		Object oo = get_Value(COLUMNNAME_BXS_MBIsDefault);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}
}