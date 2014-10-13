/* @(#)FseDocumentTransaction.java
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

import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.gcg.interfaces.GcgSerialization;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.util.FseDocumentSerialization;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class FseDocumentTransaction implements FmmNode, GcgSerialization {
	
	private int transactionNumber;
	private Date timestamp;
	private FseDocumentTransactionType transactionType;
	private String communityMemberNodeIdString;
	private CommunityMember communityMember;
	private FseDocumentSectionParagraphAudit storyBodyAudit;
	private FseDocumentSectionParagraphAudit notesBodyAudit;
	private String comment = "";
	private FseDocument fseDocument;
	
	private FseDocumentTransaction() {
		// for bogus argument
	}
	
	public FseDocumentTransaction(
			int aTransactionNumber,
			Date aTimestamp,
			String aCommunityMemberNodeIdString,
			FseDocumentTransactionType aTransactionType,
			String aComment,
			FseDocumentSectionParagraphAudit aStoryBodyAudit,
			FseDocumentSectionParagraphAudit aNotesBodyAudit,
			FseDocument anFseDocument ) {
		this.transactionNumber = aTransactionNumber;
		this.timestamp = aTimestamp;
		this.communityMemberNodeIdString = aCommunityMemberNodeIdString;
		this.transactionType = aTransactionType;
		this.comment = aComment;
		this.storyBodyAudit = aStoryBodyAudit;
		this.notesBodyAudit = aNotesBodyAudit;
		this.fseDocument = anFseDocument;
	}

	public FseDocumentTransaction(JSONObject theJsonObject) {
		try {
			this.transactionNumber = theJsonObject.getInt(FseDocumentSerialization.key__TRANSACTION_NUMBER);
			this.timestamp = JsonHelper.getDate(theJsonObject, FseDocumentSerialization.key__TRANSACTION_TIMESTAMP);
			this.transactionType = FseDocumentTransactionType.getObjectForName(theJsonObject.getString(FseDocumentSerialization.key__TRANSACTION_TYPE));
			this.communityMemberNodeIdString = theJsonObject.getString(FseDocumentSerialization.key__TRANSACTION_COMMUNITY_MEMBER);
			this.storyBodyAudit = FseDocumentSerialization.getDocumentBodyAudit(
					theJsonObject.getJSONObject(FseDocumentSerialization.key__TRANSACTION_STORY_BODY_AUDIT),
					FseDocumentSectionType.STORY );
			this.notesBodyAudit = FseDocumentSerialization.getDocumentBodyAudit(
					theJsonObject.getJSONObject(FseDocumentSerialization.key__TRANSACTION_NOTES_BODY_AUDIT),
					FseDocumentSectionType.NOTES );
			this.fseDocument = new FseDocument(theJsonObject.getString(FseDocumentSerialization.key__TRANSACTION_SERIALIZED_FSE_DOCUMENT), this);
			this.comment = theJsonObject.getString(FseDocumentSerialization.key__TRANSACTION_COMMENT);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getTransactionNumber() {
		return this.transactionNumber;
	}

	public void setTransactionNumber(int transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	@Override
	public Date getRowTimestamp() {
		return this.timestamp;
	}

	public FseDocumentTransactionType getTransactionType() {
		return this.transactionType;
	}

	public String getCommunityMemberNodeIdString() {
		return this.communityMemberNodeIdString;
	}

	public int getParagraphNewCount(FseDocumentSectionType aDocumentSectionType) {
		switch (aDocumentSectionType) {
			case STORY:
				return this.storyBodyAudit.getParagraphsNewCount();
			case NOTES:
				return this.notesBodyAudit.getParagraphsNewCount();
			default:
				return 0;
		}
	}

	public int getParagraphNewCountStory() {
		return getParagraphNewCount(FseDocumentSectionType.STORY);
	}

	public int getParagraphNewCountNotes() {
		return getParagraphNewCount(FseDocumentSectionType.NOTES);
	}
	
	public int getParagraphModifiedCount(FseDocumentSectionType aDocumentSectionType) {
		switch (aDocumentSectionType) {
		case STORY:
			return this.storyBodyAudit.getParagraphsModifiedCount();
		case NOTES:
			return this.notesBodyAudit.getParagraphsModifiedCount();
		default:
			return 0;
		}
	}

	public int getParagraphModifiedCountStory() {
		return getParagraphModifiedCount(FseDocumentSectionType.STORY);
	}

	public int getParagraphModifiedCountNotes() {
		return getParagraphModifiedCount(FseDocumentSectionType.NOTES);
	}
	
	public int getParagraphDeletedCount(FseDocumentSectionType aDocumentSectionType) {
		switch (aDocumentSectionType) {
		case STORY:
			return this.storyBodyAudit.getParagraphsDeletedCount();
		case NOTES:
			return this.notesBodyAudit.getParagraphsDeletedCount();
		default:
			return 0;
		}
	}

	public int getParagraphDeletedCountStory() {
		return getParagraphDeletedCount(FseDocumentSectionType.STORY);
	}

	public int getParagraphDeletedCountNotes() {
		return getParagraphDeletedCount(FseDocumentSectionType.NOTES);
	}
	
	public int getParagraphUnchangedCount(FseDocumentSectionType aDocumentSectionType) {
		switch (aDocumentSectionType) {
		case STORY:
			return this.storyBodyAudit.getParagraphsUnchangedCount();
		case NOTES:
			return this.notesBodyAudit.getParagraphsUnchangedCount();
		default:
			return 0;
		}
	}

	public int getParagraphUnchangedCountStory() {
		return getParagraphUnchangedCount(FseDocumentSectionType.STORY);
	}

	public int getParagraphUnchangedCountNotes() {
		return getParagraphUnchangedCount(FseDocumentSectionType.NOTES);
	}
	
	public int getParagraphLockedCount(FseDocumentSectionType aDocumentSectionType) {
		switch (aDocumentSectionType) {
		case STORY:
			return this.storyBodyAudit.getParagraphsLockedCount();
		case NOTES:
			return this.notesBodyAudit.getParagraphsLockedCount();
		default:
			return 0;
		}
	}

	public int getParagraphLockedCountStory() {
		return getParagraphLockedCount(FseDocumentSectionType.STORY);
	}

	public int getParagraphLockedCountNotes() {
		return getParagraphLockedCount(FseDocumentSectionType.NOTES);
	}
	
	public int getParagraphUnlockedCount(FseDocumentSectionType aDocumentSectionType) {
		switch (aDocumentSectionType) {
		case STORY:
			return this.storyBodyAudit.getParagraphsUnlockedCount();
		case NOTES:
			return this.notesBodyAudit.getParagraphsUnlockedCount();
		default:
			return 0;
		}
	}

	public int getParagraphUnlockedCountStory() {
		return getParagraphUnlockedCount(FseDocumentSectionType.STORY);
	}

	public int getParagraphUnlockedCountNotes() {
		return getParagraphUnlockedCount(FseDocumentSectionType.NOTES);
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isCheckpoint() {
		return this.transactionType == FseDocumentTransactionType.CHECKPOINT;
	}

	@Override
	public String getSerialized() {
		return getJsonObject().toString();
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(FseDocumentSerialization.key__TRANSACTION_NUMBER, this.transactionNumber);
			JsonHelper.putDate(theJsonObject, FseDocumentSerialization.key__TRANSACTION_TIMESTAMP, this.timestamp);
			theJsonObject.put(FseDocumentSerialization.key__TRANSACTION_TYPE, this.transactionType.getDocumentTransactionType());
			theJsonObject.put(FseDocumentSerialization.key__TRANSACTION_COMMUNITY_MEMBER, this.communityMemberNodeIdString);
			FseDocumentSerialization.putDocumentBodyAudit(
					theJsonObject,
					FseDocumentSerialization.key__TRANSACTION_STORY_BODY_AUDIT,
					this.storyBodyAudit );
			FseDocumentSerialization.putDocumentBodyAudit(
					theJsonObject,
					FseDocumentSerialization.key__TRANSACTION_NOTES_BODY_AUDIT,
					this.notesBodyAudit );
			theJsonObject.put(FseDocumentSerialization.key__TRANSACTION_SERIALIZED_FSE_DOCUMENT, this.fseDocument.getJsonTransactionObject());
			theJsonObject.put(FseDocumentSerialization.key__TRANSACTION_COMMENT, this.comment);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}

	@Override
	public boolean validateSerializationFormatVersion(String aString) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public CommunityMember getCommunityMember() {
		if(this.communityMember == null) {
			this.communityMember = FmmDatabaseMediator.getActiveMediator().getCommunityMember(this.communityMemberNodeIdString);
		}
		return this.communityMember;
	}
	
	public String getHeadline() {
		return getCommunityMember().getHeadline() + " -> " + this.timestamp.toString();
	}

	@Override
	public String getLabelText() {
		return null;
	}

	@Override
	public Drawable getLabelDrawable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDataText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Drawable getDataDrawable() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeId getNodeId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNodeIdString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAbbreviatedNodeIdString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRowTimestamp(Date aTimestamp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNodeTypeCode() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNodeTypeName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNodeEditorActivityRequestCode() {
		return 0;
	}
	
	@Override
	public int getNodeCreateActivityRequestCode() {
		return 0;
	}
	
	@Override
	public int getNodePickerActivityRequestCode() {
		return 0;
	}

	@Override
	public boolean isModified(String aSerializedObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FmmNodeImpl getClone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString() {
		StringBuffer theStringBuffer = new StringBuffer();
		theStringBuffer.append(getRowTimestamp().toString() + "      ");
		theStringBuffer.append(getCommunityMember().getName() + "      ");
		theStringBuffer.append(getTransactionType().getDocumentTransactionType());
		return theStringBuffer.toString();
	}

	public FseDocument getFseDocument() {
				return this.fseDocument;
	}

	public static FseDocumentTransaction getBogusArg() {
		return new FseDocumentTransaction();
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.gcg__null_drawable;
	}

	@Override
	public int getDataDrawableResourceId() {
		return R.drawable.gcg__null_drawable;
	}
	
	@Override
	public Date updateRowTimestamp() { return null; }

}
