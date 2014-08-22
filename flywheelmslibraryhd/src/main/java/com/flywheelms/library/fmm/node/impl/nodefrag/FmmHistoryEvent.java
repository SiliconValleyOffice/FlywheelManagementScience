/* @(#)FmmHistoryEvent.java
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

import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.flywheelms.library.fmm.node.impl.FmmHistoryNodeImpl;
import com.flywheelms.library.gcg.widget.date.GcgDateHelper;

public class FmmHistoryEvent {

	public static final String key__HISTORY_EVENT__SEQUENCE = "Sequence";
	public static final String key__HISTORY_EVENT__DATE = "Date";
	public static final String key__HISTORY_EVENT__COMMUNITY_MEMBER__NODE_ID = "CommunityMemberNodeId";
	public static final String key__HISTORY_EVENT__EVENT_TYPE = "EventType";
	public static final String key__HISTORY_EVENT__DATA__LIST = "DataList";
	
	protected int sequence;
	protected Date date;
	protected String communityMemberNodeIdString;
	protected String eventType;
	protected ArrayList<HistoryEventData> historyEventDataList = new ArrayList<HistoryEventData>();
	
	public FmmHistoryEvent(
			int aSequence, Date aDate,
			String aCommunityMember,
			String anEventType ) {
		this.sequence = aSequence;
		this.date = aDate;
		this.communityMemberNodeIdString = aCommunityMember;
		this.eventType = anEventType;
		this.historyEventDataList = new ArrayList<HistoryEventData>();
	}
	
	public FmmHistoryEvent(
			int aSequence, Date aDate,
			String aCommunityMember,
			String anEventType,
			ArrayList<HistoryEventData> aHistoryEventDataList ) {
		this.sequence = aSequence;
		this.date = aDate;
		this.communityMemberNodeIdString = aCommunityMember;
		this.eventType = anEventType;
		this.historyEventDataList = aHistoryEventDataList == null ? new ArrayList<HistoryEventData>() : aHistoryEventDataList;
	}
	
	public FmmHistoryEvent(
			int aSequence, Date aDate,
			String aCommunityMember,
			String anEventType,
			FmmHistoryNodeImpl anFmmHistoryNodeImpl ) {
		this.sequence = aSequence;
		this.date = aDate;
		this.communityMemberNodeIdString = aCommunityMember;
		this.eventType = anEventType;
		this.historyEventDataList = anFmmHistoryNodeImpl.getHistoryEventDataList();
	}
	
	public FmmHistoryEvent(JSONObject aJsonObject) {
		try {
			this.sequence = aJsonObject.getInt(key__HISTORY_EVENT__SEQUENCE);
			this.date = GcgDateHelper.getDateFromIso8601DateString(aJsonObject.getString(key__HISTORY_EVENT__DATE));
			this.communityMemberNodeIdString = aJsonObject.getString(key__HISTORY_EVENT__COMMUNITY_MEMBER__NODE_ID);
			this.eventType = aJsonObject.getString(key__HISTORY_EVENT__EVENT_TYPE);
			JSONArray theJsonArray = aJsonObject.getJSONArray(key__HISTORY_EVENT__DATA__LIST);
			JSONObject theJsonObject;
			for(int theIndex = 0 ; theIndex < theJsonArray.length() ; ++theIndex) {
				theJsonObject = theJsonArray.getJSONObject(theIndex);
				this.historyEventDataList.add(new HistoryEventData(theJsonObject));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(key__HISTORY_EVENT__SEQUENCE, this.sequence);
			theJsonObject.put(key__HISTORY_EVENT__DATE, GcgDateHelper.getIso8601DateString(this.date));
			theJsonObject.put(key__HISTORY_EVENT__COMMUNITY_MEMBER__NODE_ID, this.communityMemberNodeIdString);
			theJsonObject.put(key__HISTORY_EVENT__EVENT_TYPE, this.eventType);
			theJsonObject.put(key__HISTORY_EVENT__DATA__LIST, getDataListJsonArray());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public String getSerializedDataList() {
		return getDataListJsonArray().toString();
	}

	private JSONArray getDataListJsonArray() {
		JSONArray theJsonArray= new JSONArray();
		for(HistoryEventData theHistoryEventData : this.historyEventDataList) {
			theJsonArray.put(theHistoryEventData.getJsonObject());
		}
		return theJsonArray;
	}

	public void setSequence(int aSequence) {
		this.sequence = aSequence;
	}
	
	public class HistoryEventData {

		static final String key__HISTORY_EVENT__DATA_LABEL = "DataLabel";
		static final String key__HISTORY_EVENT__DATA_TEXT = "DataText";
		String labelText;
		String dataText;
		
		HistoryEventData(String aLabelText, String aDataText) {
			this.labelText = aLabelText;
			this.dataText = aDataText;
		}
		
		HistoryEventData(JSONObject aJsonObject) {
			try {
				this.labelText = aJsonObject.getString(key__HISTORY_EVENT__DATA_LABEL);
				this.dataText = aJsonObject.getString(key__HISTORY_EVENT__DATA_TEXT);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public JSONObject getJsonObject() {
			JSONObject theJsonObject = new JSONObject();
			try {
				theJsonObject.put(key__HISTORY_EVENT__DATA_LABEL, this.labelText);
				theJsonObject.put(key__HISTORY_EVENT__DATA_TEXT, this.dataText);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return theJsonObject;
		}
		
		public String getSerialized() {
			return getJsonObject().toString();
		}

		public String getLabelText() {
			return this.labelText;
		}

		public void setLabelText(String labelText) {
			this.labelText = labelText;
		}

		public String getDataText() {
			return this.dataText;
		}

		public void setDataText(String dataText) {
			this.dataText = dataText;
		}
		
	}

}
