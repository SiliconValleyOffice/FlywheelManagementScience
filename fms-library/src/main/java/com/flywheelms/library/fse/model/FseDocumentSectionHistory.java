/* @(#)FseDocumentSectionHistory.java
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

import com.flywheelms.library.fca.FlywheelCommunityAuthentication;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.enumerator.FseHistoryCompression;
import com.flywheelms.library.fse.util.FseDocumentSerialization;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class FseDocumentSectionHistory extends FseDocumentSection {
	
	private FseHistoryCompression historyCompression = FseHistoryCompression.NONE;
	private ArrayList<FseDocumentTransaction> documentTransactionList = new ArrayList<FseDocumentTransaction>();
	
	public FseDocumentSectionHistory() {
		super(FseDocumentSectionType.HISTORY);
	}
	
	public FseDocumentSectionHistory(JSONObject aJsonObject) {
		super(FseDocumentSectionType.HISTORY);
		try {
			this.historyCompression = FseHistoryCompression.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__DOCUMENT_SECTION__HISTORY_COMPRESSION));
			JSONArray theJsonArray = aJsonObject.getJSONArray(FseDocumentSerialization.key__TRANSACTION_LIST);
			for(int i=0;i < theJsonArray.length();i++){                                     
				JSONObject theTransactionJsonObject = theJsonArray.getJSONObject(i);
				this.documentTransactionList.add(new FseDocumentTransaction(theTransactionJsonObject));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__HISTORY_COMPRESSION, this.historyCompression.getName());
			JSONArray theJsonArray = new JSONArray();
			for (FseDocumentTransaction theDocumentTransaction : this.documentTransactionList) {
				theJsonArray.put(theDocumentTransaction.getJsonObject());
			}
			theJsonObject.put(FseDocumentSerialization.key__TRANSACTION_LIST, theJsonArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}
	
	public ArrayList<FseDocumentTransaction> getDocumentTransactionList() {
		return this.documentTransactionList;
	}

	@Override
	public FseDocumentSectionHistory getClone() {
		return new FseDocumentSectionHistory(this.getJsonObject());
	}
	
	public int getLatestTransactionNumber() {
		return this.documentTransactionList.size();
	}

	public void updateHistory(
			Date aTimestamp,
			FseDocumentTransactionType aDocumentTransactionType,
			String aComment,
			FseDocumentSectionParagraphAudit aStoryAudit,
			FseDocumentSectionParagraphAudit aNotesAudit,
			FseDocument anFseDocument ) {
		// TODO - implement compression, based upon the value of this.historyCompression
		this.documentTransactionList.add(new FseDocumentTransaction(
				getLatestTransactionNumber() + 1,
				aTimestamp,
				FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString(),
				aDocumentTransactionType,
				aComment,
				aStoryAudit,
				aNotesAudit,
				anFseDocument ));
	}

	@Override
	public boolean validateSerializationFormatVersion(String aString) {
		// TODO Auto-generated method stub
		return false;
	}

}
