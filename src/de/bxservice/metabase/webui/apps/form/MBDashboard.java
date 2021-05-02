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
 * - Carlos Ruiz                                                       *
 **********************************************************************/

package de.bxservice.metabase.webui.apps.form;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.editor.WEditor;
import org.adempiere.webui.editor.WSearchEditor;
import org.adempiere.webui.event.ValueChangeEvent;
import org.adempiere.webui.event.ValueChangeListener;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.panel.CustomForm;
import org.adempiere.webui.panel.IFormController;
import org.adempiere.webui.util.ZKUpdateUtil;
import org.compiere.model.MColumn;
import org.compiere.model.MLookup;
import org.compiere.model.MLookupFactory;
import org.compiere.model.MProduct;
import org.compiere.util.CLogger;
import org.compiere.util.DisplayType;
import org.compiere.util.Env;
import org.compiere.util.Language;
import org.compiere.util.Msg;
import org.zkoss.json.JSONObject;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Center;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Iframe;
import org.zkoss.zul.North;
import org.zkoss.zul.Space;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 *
 * @author Carlos Ruiz
 *
 */
public class MBDashboard implements IFormController, EventListener<Event>, ValueChangeListener {

	public static CLogger log = CLogger.getCLogger(MBDashboard.class);

	private CustomForm		mbForm = new CustomForm();
	private int         	m_WindowNo = 0;
	private Borderlayout	mainLayout	= new Borderlayout();
	private Panel			northPanel = new Panel();
	private Panel			centerPanel = new Panel();
	private Hlayout 		northLayout = new Hlayout();
	private Hlayout 		centerLayout = new Hlayout();
	private Iframe			iframe = new Iframe();
	private Label			labelProductYes = new Label();
	private WSearchEditor   fieldProductYes;
	private Label			labelProductNo = new Label();
	private WSearchEditor   fieldProductNo;
	
	private int m_product_yes;
	private int m_product_no;

	public MBDashboard() {
		super();
		m_WindowNo = mbForm.getWindowNo();
		try {
			dynInit();
			jbInit();
		} catch (Exception ex) {
			log.log(Level.SEVERE, "init", ex);
		}
	}

	/**
	 * 	Static init
	 *	@throws Exception
	 */
	private void jbInit() throws Exception {

		ZKUpdateUtil.setWidth(mbForm, "99%");
		ZKUpdateUtil.setHeight(mbForm, "100%");
		mbForm.setStyle("position: absolute; padding: 0; margin: 0");
		mbForm.appendChild (mainLayout);
		ZKUpdateUtil.setHflex(mainLayout, "1");
		ZKUpdateUtil.setHeight(mainLayout, "100%");
		northPanel.appendChild(northLayout);
		centerPanel.appendChild(centerLayout);
		ZKUpdateUtil.setVflex(centerPanel, "min");
		
		labelProductYes.setText (Msg.getElement(Env.getCtx(), "M_Product_ID"));
		labelProductNo.setText (Msg.getElement(Env.getCtx(), "M_Product_ID"));
		
		North north = new North();
		north.appendChild(northPanel);
		ZKUpdateUtil.setVflex(north, "min");
		ZKUpdateUtil.setWidth(northPanel, "100%");
		mainLayout.appendChild(north);

		northLayout.setValign("middle");
		northLayout.setStyle("padding: 4px;");
		northLayout.appendChild(labelProductYes.rightAlign());
		northLayout.appendChild(fieldProductYes.getComponent());
		northLayout.appendChild(new Space());
		northLayout.appendChild(labelProductNo.rightAlign());
		northLayout.appendChild(fieldProductNo.getComponent());

		Center center = new Center();
		center.appendChild(centerPanel);
		centerPanel.appendChild(iframe);
		ZKUpdateUtil.setVflex(iframe, "max");
		ZKUpdateUtil.setWidth(iframe, "100%");
		ZKUpdateUtil.setHeight(iframe, "100%");
		ZKUpdateUtil.setVflex(center, "max");
		ZKUpdateUtil.setWidth(centerPanel, "100%");
		ZKUpdateUtil.setHeight(centerPanel, "100%");
		mainLayout.appendChild(center);
		
		centerLayout.setValign("middle");
		centerLayout.setStyle("padding: 4px");
		ZKUpdateUtil.setHflex(centerLayout, "1");
		ZKUpdateUtil.setVflex(centerLayout, "max");
	}	//	jbInit

	/**
	 *  Initialize List of existing processes
	 * @throws Exception 
	 */
	private void dynInit() throws Exception {
		Properties ctx = Env.getCtx();
		Language language = Language.getLoginLanguage(); // Base Language
		MLookup m_fieldProductYes = MLookupFactory.get(ctx, m_WindowNo,
				MColumn.getColumn_ID(MProduct.Table_Name, "M_Product_ID"),
				DisplayType.Search, language, MProduct.COLUMNNAME_M_Product_ID, 0, false,
				" M_Product.IsSummary = 'N'");
		fieldProductYes = new WSearchEditor("M_Product_ID", true, false, true, m_fieldProductYes);
		fieldProductYes.addValueChangeListener(this);
		MLookup m_fieldProductNo = MLookupFactory.get(ctx, m_WindowNo,
				MColumn.getColumn_ID(MProduct.Table_Name, "M_Product_ID"),
				DisplayType.Search, language, MProduct.COLUMNNAME_M_Product_ID, 0, false,
				" M_Product.IsSummary = 'N'");
		fieldProductNo = new WSearchEditor("M_Product_ID", true, false, true, m_fieldProductNo);
		fieldProductNo.addValueChangeListener(this);
	}   //  dynList

	/**************************************************************************
	 *  Action Listener
	 *  @param e event
	 */
	public void onEvent(Event e) {
	}//onEvent

	@Override
	public void valueChange(ValueChangeEvent evt) {
		if (evt != null && evt.getSource() instanceof WEditor) {
			WEditor changedEditor = (WEditor)evt.getSource();
			Object value = evt.getNewValue();
			if (changedEditor == fieldProductNo) {
				m_product_no = (value == null ? 0 : (Integer) value);
			} else if (changedEditor == fieldProductYes) {
				m_product_yes = (value == null ? 0 : (Integer) value);
			}
			if (m_product_no > 0 && m_product_yes > 0) {
				String metabaseSecretKey = "ef9eb4104db8f2deca8cd7f15e255b57c09269c98559a9cde4ca2dd30a8b2e26";
				String metabaseSiteURL = "http://localhost:3000";
				Map<String, Object> payload = new HashMap<String, Object>();
				JSONObject resource = new JSONObject();
				resource.put("dashboard", 1);
				payload.put("resource", resource);

				JSONObject params = new JSONObject();
				params.put("prod1", String.valueOf(m_product_yes));
				params.put("prod2", String.valueOf(m_product_no));
				params.put("userid", String.valueOf(Env.getAD_User_ID(Env.getCtx())));
				payload.put("params", params);

				String url = getMetabaseEmbeddedUrl(metabaseSecretKey, payload, metabaseSiteURL);
				// iframe.setSrc("http://192.168.0.18:3000/public/question/e2f8f626-a4bf-49d8-b963-738df917d24d?prod1="+m_product_yes+"&prod2="+m_product_no);
				iframe.setSrc(url + "#bordered=true&titled=true");
			}
        }
	}

	public ADForm getForm() {
		return mbForm;
	}

	public String getMetabaseEmbeddedUrl(String metabaseSecretKey, Map<String, Object> payload, String metabaseUrl) {
		// Need to encode the secret key
		String metaBaseEncodedSecretKey = Base64.getEncoder().encodeToString(metabaseSecretKey.getBytes());
		final Date createdDate = new Date();
	    final Date expirationDate = new Date(System.currentTimeMillis() + 10000); // 10 seconds
		String jwtToken = Jwts.builder()
				.setHeaderParam("typ", "JWT")
				.setClaims(payload)
				.signWith(SignatureAlgorithm.HS256, metaBaseEncodedSecretKey)
				.setIssuedAt(createdDate)
				.setExpiration(expirationDate)
				.compact();
		System.out.println(jwtToken);
		return metabaseUrl + "/embed/dashboard/" + jwtToken;
	}

}
