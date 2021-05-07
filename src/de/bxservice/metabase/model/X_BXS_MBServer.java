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

/** Generated Model for BXS_MBServer
 *  @author iDempiere (generated) 
 *  @version Release 8.2 - $Id$ */
public class X_BXS_MBServer extends PO implements I_BXS_MBServer, I_Persistent 
{

	/**
	 *
	 */
	private static final long serialVersionUID = 20210507L;

    /** Standard Constructor */
    public X_BXS_MBServer (Properties ctx, int BXS_MBServer_ID, String trxName)
    {
      super (ctx, BXS_MBServer_ID, trxName);
      /** if (BXS_MBServer_ID == 0)
        {
			setBXS_MBSecretKey (null);
			setBXS_MBServer_ID (0);
			setName (null);
			setURL (null);
        } */
    }

    /** Load Constructor */
    public X_BXS_MBServer (Properties ctx, ResultSet rs, String trxName)
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
      StringBuilder sb = new StringBuilder ("X_BXS_MBServer[")
        .append(get_ID()).append(",Name=").append(getName()).append("]");
      return sb.toString();
    }

	/** Set Embedding Secret Key.
		@param BXS_MBSecretKey Embedding Secret Key	  */
	public void setBXS_MBSecretKey (String BXS_MBSecretKey)
	{
		set_Value (COLUMNNAME_BXS_MBSecretKey, BXS_MBSecretKey);
	}

	/** Get Embedding Secret Key.
		@return Embedding Secret Key	  */
	public String getBXS_MBSecretKey () 
	{
		return (String)get_Value(COLUMNNAME_BXS_MBSecretKey);
	}

	/** Set Metabase Server.
		@param BXS_MBServer_ID Metabase Server	  */
	public void setBXS_MBServer_ID (int BXS_MBServer_ID)
	{
		if (BXS_MBServer_ID < 1) 
			set_ValueNoCheck (COLUMNNAME_BXS_MBServer_ID, null);
		else 
			set_ValueNoCheck (COLUMNNAME_BXS_MBServer_ID, Integer.valueOf(BXS_MBServer_ID));
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

	/** Set BXS_MBServer_UU.
		@param BXS_MBServer_UU BXS_MBServer_UU	  */
	public void setBXS_MBServer_UU (String BXS_MBServer_UU)
	{
		set_Value (COLUMNNAME_BXS_MBServer_UU, BXS_MBServer_UU);
	}

	/** Get BXS_MBServer_UU.
		@return BXS_MBServer_UU	  */
	public String getBXS_MBServer_UU () 
	{
		return (String)get_Value(COLUMNNAME_BXS_MBServer_UU);
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

    /** Get Record ID/ColumnName
        @return ID/ColumnName pair
      */
    public KeyNamePair getKeyNamePair() 
    {
        return new KeyNamePair(get_ID(), getName());
    }

	/** Set URL.
		@param URL 
		Full URL address - e.g. http://www.idempiere.org
	  */
	public void setURL (String URL)
	{
		set_ValueNoCheck (COLUMNNAME_URL, URL);
	}

	/** Get URL.
		@return Full URL address - e.g. http://www.idempiere.org
	  */
	public String getURL () 
	{
		return (String)get_Value(COLUMNNAME_URL);
	}
}