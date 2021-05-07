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
import org.compiere.util.KeyNamePair;

/** Generated Model for BXS_MBDashboardParam
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_BXS_MBDashboardParam extends PO implements I_BXS_MBDashboardParam, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210507L;

    /** Standard Constructor */
    public X_BXS_MBDashboardParam (Properties ctx, int BXS_MBDashboardParam_ID, String trxName)
    {
      super (ctx, BXS_MBDashboardParam_ID, trxName);
      /** if (BXS_MBDashboardParam_ID == 0)
        {
			setAD_Reference_ID (0);
			setBXS_MBDashboard_ID (0);
			setBXS_MBDashboardParam_ID (0);
			setBXS_MBIsTriggerRefresh (true);
// Y
			setColumnName (null);
			setIsReadOnly (false);
// N
			setName (null);
			setSeqNo (0);
// @SQL=SELECT NVL(MAX(SeqNo),0)+10 AS DefaultValue FROM BXS_MBDashboardParam WHERE BXS_MBDashboard_ID=@BXS_MBDashboard_ID@
        } */
    }

    /** Load Constructor */
    public X_BXS_MBDashboardParam (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_BXS_MBDashboardParam[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	public org.compiere.model.I_AD_Reference getAD_Reference() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Reference)MTable.get(getCtx(), org.compiere.model.I_AD_Reference.Table_Name)
			.getPO(getAD_Reference_ID(), get_TrxName());	}

	/** Set Reference.
		@param AD_Reference_ID 
		System Reference and Validation
	  */
	public void setAD_Reference_ID (int AD_Reference_ID)
	{
		if (AD_Reference_ID < 1) 
			set_Value (COLUMNNAME_AD_Reference_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Reference_ID, Integer.valueOf(AD_Reference_ID));
	}

	/** Get Reference.
		@return System Reference and Validation
	  */
	public int getAD_Reference_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Reference_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	public org.compiere.model.I_AD_Reference getAD_Reference_Value() throws RuntimeException
    {
		return (org.compiere.model.I_AD_Reference)MTable.get(getCtx(), org.compiere.model.I_AD_Reference.Table_Name)
			.getPO(getAD_Reference_Value_ID(), get_TrxName());	}

	/** Set Reference Key.
		@param AD_Reference_Value_ID 
		Required to specify, if data type is Table or List
	  */
	public void setAD_Reference_Value_ID (int AD_Reference_Value_ID)
	{
		if (AD_Reference_Value_ID < 1) 
			set_Value (COLUMNNAME_AD_Reference_Value_ID, null);
		else 
			set_Value (COLUMNNAME_AD_Reference_Value_ID, Integer.valueOf(AD_Reference_Value_ID));
	}

	/** Get Reference Key.
		@return Required to specify, if data type is Table or List
	  */
	public int getAD_Reference_Value_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_AD_Reference_Value_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
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

	/** Set Metabase Dashboard Parameters.
		@param BXS_MBDashboardParam_ID Metabase Dashboard Parameters	  */
	public void setBXS_MBDashboardParam_ID (int BXS_MBDashboardParam_ID)
	{
		if (BXS_MBDashboardParam_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BXS_MBDashboardParam_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BXS_MBDashboardParam_ID, Integer.valueOf(BXS_MBDashboardParam_ID));
	}

	/** Get Metabase Dashboard Parameters.
		@return Metabase Dashboard Parameters	  */
	public int getBXS_MBDashboardParam_ID () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_BXS_MBDashboardParam_ID);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}

	/** Set BXS_MBDashboardParam_UU.
		@param BXS_MBDashboardParam_UU BXS_MBDashboardParam_UU	  */
	public void setBXS_MBDashboardParam_UU (String BXS_MBDashboardParam_UU)
	{
		set_Value (COLUMNNAME_BXS_MBDashboardParam_UU, BXS_MBDashboardParam_UU);
	}

	/** Get BXS_MBDashboardParam_UU.
		@return BXS_MBDashboardParam_UU	  */
	public String getBXS_MBDashboardParam_UU () 
	{
		return (String)get_Value(COLUMNNAME_BXS_MBDashboardParam_UU);
	}

	/** Set Trigger Refresh.
		@param BXS_MBIsTriggerRefresh Trigger Refresh	  */
	public void setBXS_MBIsTriggerRefresh (boolean BXS_MBIsTriggerRefresh)
	{
		set_Value (COLUMNNAME_BXS_MBIsTriggerRefresh, Boolean.valueOf(BXS_MBIsTriggerRefresh));
	}

	/** Get Trigger Refresh.
		@return Trigger Refresh	  */
	public boolean isBXS_MBIsTriggerRefresh () 
	{
		Object oo = get_Value(COLUMNNAME_BXS_MBIsTriggerRefresh);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
	}

	/** Set DB Column Name.
		@param ColumnName 
		Name of the column in the database
	  */
	public void setColumnName (String ColumnName)
	{
		set_Value (COLUMNNAME_ColumnName, ColumnName);
	}

	/** Get DB Column Name.
		@return Name of the column in the database
	  */
	public String getColumnName () 
	{
		return (String)get_Value(COLUMNNAME_ColumnName);
	}

	/** Set Default Logic.
		@param DefaultValue 
		Default value hierarchy, separated by ;
	  */
	public void setDefaultValue (String DefaultValue)
	{
		set_Value (COLUMNNAME_DefaultValue, DefaultValue);
	}

	/** Get Default Logic.
		@return Default value hierarchy, separated by ;
	  */
	public String getDefaultValue () 
	{
		return (String)get_Value(COLUMNNAME_DefaultValue);
	}

	/** Set Description.
		@param Description 
		Optional short description of the record
	  */
	public void setDescription (String Description)
	{
		set_Value (COLUMNNAME_Description, Description);
	}

	/** Get Description.
		@return Optional short description of the record
	  */
	public String getDescription () 
	{
		return (String)get_Value(COLUMNNAME_Description);
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

	/** Set Read Only.
		@param IsReadOnly 
		Field is read only
	  */
	public void setIsReadOnly (boolean IsReadOnly)
	{
		set_Value (COLUMNNAME_IsReadOnly, Boolean.valueOf(IsReadOnly));
	}

	/** Get Read Only.
		@return Field is read only
	  */
	public boolean isReadOnly () 
	{
		Object oo = get_Value(COLUMNNAME_IsReadOnly);
		if (oo != null) 
		{
			 if (oo instanceof Boolean) 
				 return ((Boolean)oo).booleanValue(); 
			return "Y".equals(oo);
		}
		return false;
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set Sequence.
		@param SeqNo 
		Method of ordering records; lowest number comes first
	  */
	public void setSeqNo (int SeqNo)
	{
		set_Value (COLUMNNAME_SeqNo, Integer.valueOf(SeqNo));
	}

	/** Get Sequence.
		@return Method of ordering records; lowest number comes first
	  */
	public int getSeqNo () 
	{
		Integer ii = (Integer)get_Value(COLUMNNAME_SeqNo);
		if (ii == null)
			 return 0;
		return ii.intValue();
	}
}