/* @(#)FseDocumentSectionTribKn.java
** 
** Copyright (C) 2012 by Steven D. Stamps
**
**             Trademarks & Copyrights
** Flywheel Management Science(TM), Flywheel Management Model(TM),
** Flywheel Story Editor(TM) and FlywheelMS(TM) are exclusive trademarks
** of Steven D. Stamps and may only be used freely for the purpose of
** identifying the unforked version of this software.  Subsequent forks
** may not use these trademarks.  All other rights are reserved.
**
** DecKanGL (Decorated Kanban Glyph Language) and TribKn (Tribal Knowledge)
** are also exclusive trademarks of Steven D. Stamps.  These may be used
** freely within the unforked FlywheelMS application and documentation.
** All other rights are reserved.
**
** gConGUI (game Controller Graphical User Interface) is an exclusive
** trademark of Steven D. Stamps.  This trademark may be used freely
** within the unforked FlywheelMS application and documentation.
** All other rights are reserved.
**
** Trademark information is available at
** <http://www.flywheelms.com/trademarks>
**
** Flywheel Management Science(TM) is a copyrighted body of management
** metaphors, governance processes, and leadership techniques that is
** owned by Steven D. Stamps.  These copyrighted materials may be freely
** used, without alteration, by the community (users and developers)
** surrounding this GPL3-licensed software.  Additional copyright
** information is available at <http://www.flywheelms.org/copyrights>
**
**              GPL3 Software License
** This program is free software: you can use it, redistribute it and/or
** modify it under the terms of the GNU General Public License, version 3,
** as published by the Free Software Foundation. This program is distributed
** in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
** even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
** PURPOSE.  See the GNU General Public License for more details. You should
** have received a copy of the GNU General Public License, in a file named
** COPYING, along with this program.  If you cannot find your copy, see
** <http://www.gnu.org/licenses/gpl-3.0.html>.
*/

package com.flywheelms.library.fse.model;

import com.flywheelms.gcongui.deckangl.glyph.DecKanGlGlyph;
import com.flywheelms.gcongui.deckangl.serialization.DecKanGlGlyphSerialization;
import com.flywheelms.library.fmm.deckangl.FmmDecKanGlDictionary;
import com.flywheelms.library.fmm.meta_data.NodeFragGovernanceMetaData;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragGovernance;
import com.flywheelms.library.fse.FseDocumentSectionType;

import org.json.JSONException;
import org.json.JSONObject;

public class FseDocumentSectionTribKn extends FseDocumentSection {

	private static final String key__HEADLINE = "Headline";
	String headline;
	DecKanGlGlyph decKanGlGlyph;
	NodeFragGovernance nodeFragGovernance;
//	FmmConfiguration activeFmmConfiguration;

	public FseDocumentSectionTribKn() {
		super(FseDocumentSectionType.TRIBKN);
	}

	// create a new document section that has never been serialized
	public FseDocumentSectionTribKn(
			String aHeadline,
			DecKanGlGlyph aDecKanGlGlyph,
			NodeFragGovernance aNodeFragGovernance ) {
		super(FseDocumentSectionType.TRIBKN);
		this.headline = aHeadline;
		this.decKanGlGlyph = aDecKanGlGlyph;
		this.nodeFragGovernance = aNodeFragGovernance;
//		this.activeFmmConfiguration = anFmmConfiguration;
	}
	
	// de-serialize a document section
	public FseDocumentSectionTribKn(JSONObject aJsonObject) {
		super(FseDocumentSectionType.TRIBKN);
		try {
			this.headline = aJsonObject.getString(key__HEADLINE);
			this.decKanGlGlyph = DecKanGlGlyphSerialization.getDecKanGlGlyph(
							aJsonObject.getJSONObject(DecKanGlGlyphSerialization.key__DECKANGL_GLYPH),
							FmmDecKanGlDictionary.getInstance() );
			this.nodeFragGovernance = new NodeFragGovernance(
					aJsonObject.getJSONObject(NodeFragGovernanceMetaData.key__NODE_FRAG__GOVERNANCE));
//			this.activeFmmConfiguration =
//				FmmConfigurationSerialization.retrieveFmmConfiguration(aJsonObject.getJSONObject(FmmConfigurationSerialization.key__FMM_CONFIGURATION));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(key__HEADLINE, this.headline);
			theJsonObject.put(DecKanGlGlyphSerialization.key__DECKANGL_GLYPH, DecKanGlGlyphSerialization.getJsonObject(this.decKanGlGlyph));
			theJsonObject.put(NodeFragGovernanceMetaData.key__NODE_FRAG__GOVERNANCE, this.nodeFragGovernance.getJsonObject());
//			theJsonObject.put(FmmConfigurationSerialization.key__FMM_CONFIGURATION, FmmConfigurationSerialization.createFmmConfigurationObject(this.decKanGlGlyph));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}

	@Override
	public FseDocumentSectionTribKn getClone() {
		return new FseDocumentSectionTribKn(getJsonObject());
	}

	public String getHeadline() {
		return this.headline;
	}

	public void setHeadline(String headline) {
		this.headline = headline;
	}

	public DecKanGlGlyph getDecKanGlGlyph() {
		return this.decKanGlGlyph;
	}

	public void setDecKanGlGlyph(DecKanGlGlyph decKanGlGlyph) {
		this.decKanGlGlyph = decKanGlGlyph;
	}

	public NodeFragGovernance getNodeFragGovernance() {
		return this.nodeFragGovernance;
	}

	public void setNodeFragGovernance(NodeFragGovernance nodeFragGovernance) {
		this.nodeFragGovernance = nodeFragGovernance;
	}

	@Override
	public boolean validateSerializationFormatVersion(String aString) {
		// TODO Auto-generated method stub
		return false;
	}

//	public FmmConfiguration retrieveFmmConfiguration() {
//		return this.activeFmmConfiguration;
//	}
//
//	public void setFmmConfiguration(FmmConfiguration activeFmmConfiguration) {
//		this.activeFmmConfiguration = activeFmmConfiguration;
//	}

}
