/* @(#)NodeFragAuditBlock.java
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

package com.flywheelms.library.fmm.node.impl.nodefrag;

import android.widget.TableRow;

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fca.FlywheelCommunityAuthentication;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.meta_data.NodeFragAuditBlockMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

// implements FmmNodeAudit, except get/set AuditBlock
public class NodeFragAuditBlock extends FmmNodeFragLockableImpl {

	private String parentAbbreviatedNodeIdString;
	private String searchableHeadline = "";
	private int iteration = 0;
	private String createdByNodeIdString;
	private CommunityMember createdByCommunityMember;
	private Date createdTimestamp = GcgDateHelper.getCurrentDateTime();
	private String lastUpdatedByNodeIdString;
	private CommunityMember lastUpdatedByCommunityMember;
	private int eventCount = 0;
	private String serializedNodeHistory;
	private ArrayList<TableRow> eventTableRowList;  // transient
	private ArrayList<FmmHistoryEvent> fmmHistoryEventList;  // transient

	public NodeFragAuditBlock(FmmHeadlineNode anFmmHeadlineNode) {
		super(NodeFragAuditBlock.class, anFmmHeadlineNode.getNodeIdString());
		setCreatedTimestamp(anFmmHeadlineNode.getRowTimestamp());
		setCreatedBy(FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString());
		setLastUpdatedBy(FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString());
		setIsLocked(false);
		setSearchableHeadline(anFmmHeadlineNode.getHeadline());
		FmmHistoryEvent theHistoryEvent = new FmmHistoryEvent(
				1,
				GcgDateHelper.getCurrentDateTime(),
				FlywheelCommunityAuthentication.getInstance().getCommunityMember().getNodeIdString(),
				"Create" );
		this.fmmHistoryEventList = new ArrayList<FmmHistoryEvent>();
		this.fmmHistoryEventList.add(theHistoryEvent);
	}

	// new Audit Block for an FseParagraph
	public NodeFragAuditBlock(String aParentNodeIdString) {
		super(NodeFragAuditBlock.class, aParentNodeIdString);
		setCreatedTimestamp(GcgDateHelper.getCurrentDateTime());
		setCreatedBy(FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString());
		setLastUpdatedBy(FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString());
		setIsLocked(false);
	}

	// a new Document Audit Block
	public NodeFragAuditBlock(String aParentNodeIdString, String aHeadline, Date aTimestamp) {
		super(NodeFragAuditBlock.class, aParentNodeIdString);
		setSearchableHeadline(aHeadline);
		setCreatedBy(FlywheelCommunityAuthentication.getInstance().getCommunityMember());
		setCreatedTimestamp(aTimestamp);
		setLastUpdatedBy(FlywheelCommunityAuthentication.getInstance().getCommunityMember());
	}

	// rehydrate from database
	public NodeFragAuditBlock(String aNodeIdString, String aHeadline, String aParentNodeIdString) {
		super(NodeId.hydrate(NodeFragAuditBlock.class, aNodeIdString), aParentNodeIdString);
		this.setSearchableHeadline(aHeadline);
	}

	// rehydrate from serialization
	public NodeFragAuditBlock(JSONObject aJsonObject) {
		super(NodeFragAuditBlock.class, aJsonObject);
		try {
			setSearchableHeadline(aJsonObject.getString(NodeFragAuditBlockMetaData.column_SEARCHABLE_HEADLINE));
			setIteration(aJsonObject.getInt(NodeFragAuditBlockMetaData.column_ITERATION));
			setCreatedBy(aJsonObject.getString(NodeFragAuditBlockMetaData.column_CREATED_BY));
			setCreatedTimestamp(JsonHelper.getDate(aJsonObject, NodeFragAuditBlockMetaData.column_CREATED_TIMESTAMP));
			setLastUpdatedBy(aJsonObject.getString(NodeFragAuditBlockMetaData.column_LAST_UPDATED_BY));
			setParentAbbreviatedNodeIdString(aJsonObject.getString(NodeFragAuditBlockMetaData.column_PARENT_ABBREVIATED_NODE_ID));
			setSerializedNodeHistory(aJsonObject.getString(NodeFragAuditBlockMetaData.column_SERIALIZED_NODE_HISTORY));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(NodeFragAuditBlockMetaData.column_SEARCHABLE_HEADLINE, getSearchableHeadline());
			theJsonObject.put(NodeFragAuditBlockMetaData.column_ITERATION, getIteration());
			theJsonObject.put(NodeFragAuditBlockMetaData.column_CREATED_BY, getCreatedByNodeIdString());
			JsonHelper.putDate(theJsonObject, NodeFragAuditBlockMetaData.column_CREATED_TIMESTAMP, getCreatedTimestamp());
			theJsonObject.put(NodeFragAuditBlockMetaData.column_LAST_UPDATED_BY, getLastUpdatedByNodeIdString());
			theJsonObject.put(NodeFragAuditBlockMetaData.column_PARENT_ABBREVIATED_NODE_ID, getParentAbbreviatedNodeIdString());
			theJsonObject.put(NodeFragAuditBlockMetaData.column_SERIALIZED_NODE_HISTORY, getSerializedNodeHistory());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public String getSearchableHeadline() {
		return this.searchableHeadline;
	}

	public void setSearchableHeadline(String headline) {
		this.searchableHeadline = headline;
	}

	public int getIteration() {
		return this.iteration;
	}

	public void setIteration(int iteration) {
		this.iteration = iteration;
	}

	public String getCreatedByNodeIdString() {
		return this.createdByNodeIdString;
	}

	public void setCreatedBy(String aNodeIdString) {
		this.createdByNodeIdString = aNodeIdString;
		if(this.createdByCommunityMember != null && ! this.createdByCommunityMember.getNodeIdString().equals(aNodeIdString)) {
			this.createdByCommunityMember = null;
		}
	}

	public CommunityMember getCreatedByCommunityMember() {
		if(this.createdByCommunityMember == null && this.createdByNodeIdString != null && this.createdByNodeIdString.length() > 0) {
			this.createdByCommunityMember = FmmDatabaseMediator.getActiveMediator().retrieveCommunityMember(this.createdByNodeIdString);
		}
		return this.createdByCommunityMember;
	}

	public void setCreatedBy(CommunityMember aCommunityMember) {
		this.createdByCommunityMember = aCommunityMember;
		this.createdByNodeIdString = aCommunityMember.getNodeIdString();
	}

	public Date getCreatedTimestamp() {
		return this.createdTimestamp;
	}

	public void setCreatedTimestamp(Date aTimestamp) {
		this.createdTimestamp = aTimestamp;
	}
	
	public String getLastUpdatedByNodeIdString() {
		return this.lastUpdatedByNodeIdString;
	}

	public void setLastUpdatedBy(String aNodeIdString) {
		this.lastUpdatedByNodeIdString = aNodeIdString;
		if(this.lastUpdatedByCommunityMember != null && ! this.lastUpdatedByCommunityMember.getNodeIdString().equals(aNodeIdString)) {
			this.lastUpdatedByCommunityMember = null;
		}
	}

	public CommunityMember getLastUpdatedByCommunityMember() {
		if(this.lastUpdatedByCommunityMember == null && this.lastUpdatedByNodeIdString != null && this.lastUpdatedByNodeIdString.length() > 0) {
			this.lastUpdatedByCommunityMember = FmmDatabaseMediator.getActiveMediator().retrieveCommunityMember(this.lastUpdatedByNodeIdString);
		}
		if(this.lastUpdatedByCommunityMember == null) {
			setLastUpdatedBy(CommunityMember.getNullValue());
		}
		return this.lastUpdatedByCommunityMember;
	}

	public void setLastUpdatedBy(CommunityMember aCommunityMember) {
		this.lastUpdatedByCommunityMember = aCommunityMember;
		this.lastUpdatedByNodeIdString = aCommunityMember.getNodeIdString();
	}
	
	public int getEventCount() {
		return this.eventCount;
	}

	public void setEventCount(int anEventCount) {
		this.eventCount = anEventCount;
	}

	public int getUpdatedEventCount() {
		++this.eventCount;
		return this.eventCount;
	}

	@Override
	public NodeFragAuditBlock getClone() {
		return new NodeFragAuditBlock(getJsonObject());
	}

	public String getParentAbbreviatedNodeIdString() {
		if((this.parentAbbreviatedNodeIdString == null || this.parentAbbreviatedNodeIdString.length() == 0)) {
			this.parentAbbreviatedNodeIdString = NodeId.getAbbreviatedNodeIdString(getNodeIdString());
		}
		return this.parentAbbreviatedNodeIdString;
	}

	public void setParentAbbreviatedNodeIdString(String abbreviatedNodeIdString) {
		this.parentAbbreviatedNodeIdString = abbreviatedNodeIdString;
	}

	public String getSerializedNodeHistory() {
		if(this.serializedNodeHistory == null) {
			serializeNodeHistory();
		}
		return this.serializedNodeHistory;
	}

	private void serializeNodeHistory() {
		this.serializedNodeHistory = getEventHistoryJsonArray().toString();
	}

	private JSONArray getEventHistoryJsonArray() {
		JSONArray theJsonArray= new JSONArray();
		for(FmmHistoryEvent theHistoryEvent : getFmmHistoryEventList()) {
			theJsonArray.put(theHistoryEvent.getJsonObject());
		}
		return theJsonArray;
	}

	private ArrayList<FmmHistoryEvent> getFmmHistoryEventList() {
		return this.fmmHistoryEventList == null ? deSerializeNodeHistoryEventList() : this.fmmHistoryEventList;
	}

	public void setSerializedNodeHistory(String aSerializedNodeHistory) {
		this.serializedNodeHistory = aSerializedNodeHistory;
		deSerializeNodeHistoryEventList();
	}
	
	private ArrayList<FmmHistoryEvent> deSerializeNodeHistoryEventList() {
		this.fmmHistoryEventList = new ArrayList<FmmHistoryEvent>();
		if(this.serializedNodeHistory != null && ! this.serializedNodeHistory.equals("")) {
			// TODO
		}
		return this.fmmHistoryEventList;
	}

	public void updateHistory(FmmHistoryEvent theHistoryEvent) {
		theHistoryEvent.setSequence(getFmmHistoryEventList().size() + 1);
		getFmmHistoryEventList().add(theHistoryEvent);
		serializeNodeHistory();
		updateRowTimestamp();
	}
	
	public ArrayList<TableRow> getHistoryEventTableRowList() {
		if(this.eventTableRowList == null) {
			buildHistoryEventTableRowList();
		}
		return this.eventTableRowList;
	}

	protected void buildHistoryEventTableRowList() {
		this.eventTableRowList = new ArrayList<TableRow>();
	}

	@Override
	public Date updateRowTimestamp() {
		setLastUpdatedBy(FlywheelCommunityAuthentication.getInstance().getCommunityMember());
		return super.updateRowTimestamp();
	}

    public void update(FmmHeadlineNodeImpl anFmmHeadlineNode) {
        this.setRowTimestamp(anFmmHeadlineNode.getRowTimestamp());
        // TODO - set other attributes
    }
}
