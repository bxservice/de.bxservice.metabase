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

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.Properties;
import org.compiere.model.*;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;

/** Generated Model for BXS_MBDashboard
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_BXS_MBDashboard extends PO implements I_BXS_MBDashboard, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210512L;

    /** Standard Constructor */
    public X_BXS_MBDashboard (Properties ctx, int BXS_MBDashboard_ID, String trxName)
    {
      super (ctx, BXS_MBDashboard_ID, trxName);
      /** if (BXS_MBDashboard_ID == 0)
        {
			setBXS_MBDashboard_ID (0);
			setBXS_MBDashboardNo (0);
			setBXS_MBIsBordered (false);
// N
			setBXS_MBIsTitled (true);
// Y
			setBXS_MBServer_ID (0);
			setName (null);
        } */
    }

    /** Load Constructor */
    public X_BXS_MBDashboard (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_BXS_MBDashboard[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Auto Refresh in Seconds.
		@param BXS_AutoRefresh 
		If zero do not autorefresh
	  */
	public void setBXS_AutoRefresh (int BXS_AutoRefresh)
	{
		set_Value (COLUMNNAME_BXS_AutoRefresh, Integer.valueOf(BXS_AutoRefresh));
	}

	/** Get Auto Refresh in Seconds.
		@return If zero do not autorefresh
	  */
	public int getBXS_AutoRefresh () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BXS_AutoRefresh);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Dashboard.
		@param BXS_MBDashboard_ID Dashboard	  */
	public void setBXS_MBDashboard_ID (int BXS_MBDashboard_ID)
	{
		if (BXS_MBDashboard_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BXS_MBDashboard_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BXS_MBDashboard_ID, Integer.valueOf(BXS_MBDashboard_ID));
	}

	/** Get Dashboard.
		@return Dashboard	  */
	public int getBXS_MBDashboard_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BXS_MBDashboard_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Dashboard #.
		@param BXS_MBDashboardNo Dashboard #	  */
	public void setBXS_MBDashboardNo (int BXS_MBDashboardNo)
	{
		set_Value (COLUMNNAME_BXS_MBDashboardNo, Integer.valueOf(BXS_MBDashboardNo));
	}

	/** Get Dashboard #.
		@return Dashboard #	  */
	public int getBXS_MBDashboardNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BXS_MBDashboardNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BXS_MBDashboard_UU.
		@param BXS_MBDashboard_UU BXS_MBDashboard_UU	  */
	public void setBXS_MBDashboard_UU (String BXS_MBDashboard_UU)
	{
		set_Value (COLUMNNAME_BXS_MBDashboard_UU, BXS_MBDashboard_UU);
	}

	/** Get BXS_MBDashboard_UU.
		@return BXS_MBDashboard_UU	  */
	public String getBXS_MBDashboard_UU () 
	{
		return (String)get_Value(COLUMNNAME_BXS_MBDashboard_UU);
	}

	/** Set Bordered.
		@param BXS_MBIsBordered Bordered	  */
	public void setBXS_MBIsBordered (boolean BXS_MBIsBordered)
	{
		set_Value (COLUMNNAME_BXS_MBIsBordered, Boolean.valueOf(BXS_MBIsBordered));
	}

	/** Get Bordered.
		@return Bordered	  */
	public boolean isBXS_MBIsBordered () 
	{
		Object oo = get_Value(COLUMNNAME_BXS_MBIsBordered);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set Titled.
		@param BXS_MBIsTitled Titled	  */
	public void setBXS_MBIsTitled (boolean BXS_MBIsTitled)
	{
		set_Value (COLUMNNAME_BXS_MBIsTitled, Boolean.valueOf(BXS_MBIsTitled));
	}

	/** Get Titled.
		@return Titled	  */
	public boolean isBXS_MBIsTitled () 
	{
		Object oo = get_Value(COLUMNNAME_BXS_MBIsTitled);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	public I_BXS_MBServer getBXS_MBServer() throws RuntimeException
    {
		return (I_BXS_MBServer)MTable.get(getCtx(), I_BXS_MBServer.Table_Name)
			.getPO(getBXS_MBServer_ID(), get_TrxName());	}

	/** Set Metabase Server.
		@param BXS_MBServer_ID Metabase Server	  */
	public void setBXS_MBServer_ID (int BXS_MBServer_ID)
	{
		if (BXS_MBServer_ID < 1) 
			set_Value (COLUMNNAME_BXS_MBServer_ID, null);
		else 
			set_Value (COLUMNNAME_BXS_MBServer_ID, Integer.valueOf(BXS_MBServer_ID));
	}

	/** Get Metabase Server.
		@return Metabase Server	  */
	public int getBXS_MBServer_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BXS_MBServer_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), String.valueOf(getBXS_MBServer_ID()));
    }

	/** night = night */
	public static final String BXS_MBTHEME_Night = "night";
	/** Set Theme.
		@param BXS_MBTheme Theme	  */
	public void setBXS_MBTheme (String BXS_MBTheme)
	{

		set_Value (COLUMNNAME_BXS_MBTheme, BXS_MBTheme);
	}

	/** Get Theme.
		@return Theme	  */
	public String getBXS_MBTheme () 
	{
		return (String)get_Value(COLUMNNAME_BXS_MBTheme);
	}

	/** Set Token Expiration in Seconds.
		@param BXS_MBTokenExpiration Token Expiration in Seconds	  */
	public void setBXS_MBTokenExpiration (int BXS_MBTokenExpiration)
	{
		set_Value (COLUMNNAME_BXS_MBTokenExpiration, Integer.valueOf(BXS_MBTokenExpiration));
	}

	/** Get Token Expiration in Seconds.
		@return Token Expiration in Seconds	  */
	public int getBXS_MBTokenExpiration () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BXS_MBTokenExpiration);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set Height.
		@param Height Height	  */
	public void setHeight (BigDecimal Height)
	{
		set_Value (COLUMNNAME_Height, Height);
	}

	/** Get Height.
		@return Height	  */
	public BigDecimal getHeight () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Height);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}

	/** Set Comment/Help.
		@param Help 
		Comment or Hint
	  */
	public void setHelp (String Help)
	{
		set_Value (COLUMNNAME_Help, Help);
	}

	/** Get Comment/Help.
		@return Comment or Hint
	  */
	public String getHelp () 
	{
		return (String)get_Value(COLUMNNAME_Help);
	}

	/** Set Name.
		@param Name 
		Alphanumeric identifier of the entity
	  */
	public void setName (String Name)
	{
		set_Value (COLUMNNAME_Name, Name);
	}

	/** Get Name.
		@return Alphanumeric identifier of the entity
	  */
	public String getName () 
	{
		return (String)get_Value(COLUMNNAME_Name);
	}

	/** Set Width.
		@param Width Width	  */
	public void setWidth (BigDecimal Width)
	{
		set_Value (COLUMNNAME_Width, Width);
	}

	/** Get Width.
		@return Width	  */
	public BigDecimal getWidth () 
	{
		BigDecimal bd = (BigDecimal)get_Value(COLUMNNAME_Width);
		if (bd == null)
			 return Env.ZERO;
		return bd;
	}
}