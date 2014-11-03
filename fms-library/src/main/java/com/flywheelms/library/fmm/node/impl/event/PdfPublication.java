/* @(#)PdfPublication.java
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

package com.flywheelms.library.fmm.node.impl.event;

import com.flywheelms.library.fmm.meta_data.PdfPublicationMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

public class PdfPublication extends FmmNodeImpl {

	public static final String SERIALIZATION_FORMAT_VERSION = "0.1";
	private String communityMemberNodeIdString;
	private String headlineNodeIdString;
	private String headlineNodeTypeCode;
	private String contentsSummary;
	private String destinationSummary1;
	private String destinationSummary2;

	public PdfPublication(String aNodeIdString, String aCommunityMemberNodeIdString, String aTargetNodeIdString) {
		this(NodeId.hydrate(PdfPublication.class, aNodeIdString), aCommunityMemberNodeIdString, aTargetNodeIdString);
	}

	public PdfPublication(NodeId aNodeId, String aCommunityMemberNodeIdString, String aTargetNodeIdString) {
		super(aNodeId);
		this.communityMemberNodeIdString = aCommunityMemberNodeIdString;
		this.headlineNodeIdString = aTargetNodeIdString;
	}
	
	public PdfPublication(JSONObject aJsonObject) {
		super(PdfPublication.class, aJsonObject);
		try {
			validateSerializationFormatVersion(aJsonObject.getString(JsonHelper.key__SERIALIZATION_FORMAT_VERSION));
			setContentsSummary(aJsonObject.getString(PdfPublicationMetaData.column_CONTENT_SUMMARY));
			setHeadlineNodeTypeCode(aJsonObject.getString(PdfPublicationMetaData.column_HEADLINE_NODE_TYPE_CODE));
			setDestinationSummary1(aJsonObject.getString(PdfPublicationMetaData.column_DESTINATION_SUMMARY_1));
			setDestinationSummary2(aJsonObject.getString(PdfPublicationMetaData.column_DESTINATION_SUMMARY_2));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(JsonHelper.key__SERIALIZATION_FORMAT_VERSION, SERIALIZATION_FORMAT_VERSION);
			theJsonObject.put(PdfPublicationMetaData.column_COMMUNITY_MEMBER_ID, getCommunityMemberNodeIdString());
			theJsonObject.put(PdfPublicationMetaData.column_HEADLINE_NODE_ID, getHeadlineNodeIdString());
			theJsonObject.put(PdfPublicationMetaData.column_HEADLINE_NODE_TYPE_CODE, getHeadlineNodeTypeCode());
			theJsonObject.put(PdfPublicationMetaData.column_CONTENT_SUMMARY, getContentsSummary());
			theJsonObject.put(PdfPublicationMetaData.column_DESTINATION_SUMMARY_1, getDestinationSummary1());
			theJsonObject.put(PdfPublicationMetaData.column_DESTINATION_SUMMARY_2, getDestinationSummary2());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public String getContentsSummary() {
		return this.contentsSummary;
	}

	public void setContentsSummary(String contentsSummary) {
		this.contentsSummary = contentsSummary;
	}

	public String getDestinationSummary1() {
		return this.destinationSummary1;
	}

	public void setDestinationSummary1(String destinationSummary1) {
		this.destinationSummary1 = destinationSummary1;
	}

	public String getDestinationSummary2() {
		return this.destinationSummary2;
	}

	public void setDestinationSummary2(String destinationSummary2) {
		this.destinationSummary2 = destinationSummary2;
	}

	public String getCommunityMemberNodeIdString() {
		return this.communityMemberNodeIdString;
	}

	public String getHeadlineNodeIdString() {
		return this.headlineNodeIdString;
	}

    public String getHeadlineNodeTypeCode() {
        if(this.headlineNodeTypeCode == null && this.headlineNodeIdString != null) {
            this.headlineNodeTypeCode = NodeId.getNodeTypeCodeFromNodeIdString(this.headlineNodeIdString);
        }
        return headlineNodeTypeCode;
    }

    public void setHeadlineNodeTypeCode(String aHeadlineNodeTypeCode) {
        this.headlineNodeTypeCode = aHeadlineNodeTypeCode;
    }
}
