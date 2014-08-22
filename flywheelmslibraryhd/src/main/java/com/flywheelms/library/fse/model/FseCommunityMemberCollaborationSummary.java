/* @(#)FseCommunityMemberCollaborationSummary.java
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

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import com.flywheelms.library.fse.util.FseDocumentSerialization;
import com.flywheelms.library.gcg.interfaces.GcgSerialization;
import com.flywheelms.library.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.util.JsonHelper;

public class FseCommunityMemberCollaborationSummary implements GcgSerialization {

	String communityMemberNodeIdString;
	Date firstContributionTimestamp = GcgDateHelper.getNullDate();
	Date lastContributionTimestamp = GcgDateHelper.getNullDate();
	int countStoryParagraphsNew;
	int countStoryParagraphsModified;
	int countStoryParagraphsDeleted;
	int countStoryParagraphsLocked;
	int countStoryParagraphsUnlocked;
	int countNotesParagraphsNew;
	int countNotesParagraphsModified;
	int countNotesParagraphsDeleted;
	int countNotesParagraphsLocked;
	int countNotesParagraphsUnlocked;

	public FseCommunityMemberCollaborationSummary(
			String aCommunityMemberNodeIdString,
			Date aFirstContributionTimestamp,
			Date alastContributionTimestamp,
			int aCountStoryParagraphsNew,
			int aCountStoryParagraphsModified,
			int aCountStoryParagraphsDeleted,
			int aCountStoryParagraphsLocked,
			int aCountStoryParagraphsUnlocked,
			int aCountNotesParagraphsNew,
			int aCountNotesParagraphsModified,
			int aCountNotesParagraphsDeleted,
			int aCountNotesParagraphsLocked,
			int aCountNotesParagraphsUnlocked ) {
		this.communityMemberNodeIdString = aCommunityMemberNodeIdString;
		this.firstContributionTimestamp = aFirstContributionTimestamp;
		this.lastContributionTimestamp = alastContributionTimestamp;
		this.countStoryParagraphsNew = aCountStoryParagraphsNew;
		this.countStoryParagraphsModified = aCountStoryParagraphsModified;
		this.countStoryParagraphsDeleted = aCountStoryParagraphsDeleted;
		this.countStoryParagraphsLocked = aCountStoryParagraphsLocked;
		this.countStoryParagraphsUnlocked = aCountStoryParagraphsUnlocked;
		this.countNotesParagraphsNew = aCountNotesParagraphsNew;
		this.countNotesParagraphsModified = aCountNotesParagraphsModified;
		this.countNotesParagraphsDeleted = aCountNotesParagraphsDeleted;
		this.countNotesParagraphsLocked = aCountNotesParagraphsLocked;
		this.countNotesParagraphsUnlocked = aCountNotesParagraphsUnlocked;
	}

	public FseCommunityMemberCollaborationSummary(JSONObject aJsonObject) {
		try {
			this.communityMemberNodeIdString = aJsonObject.getString(FseDocumentSerialization.key__PARTICIPATION__COMMUNITY_MEMBER_ID);
			this.firstContributionTimestamp = JsonHelper.getDate(aJsonObject, FseDocumentSerialization.key__PARTICIPATION__FIRST_CONTRIBUTION);
			this.lastContributionTimestamp = JsonHelper.getDate(aJsonObject, FseDocumentSerialization.key__PARTICIPATION__LAST_CONTRIBUTION);
			this.countStoryParagraphsNew = aJsonObject.getInt(FseDocumentSerialization.key__PARTICIPATION__STORY_PARAGRAPHS_NEW);
			this.countStoryParagraphsModified = aJsonObject.getInt(FseDocumentSerialization.key__PARTICIPATION__STORY_PARAGRAPHS_MODIFIED);
			this.countStoryParagraphsDeleted = aJsonObject.getInt(FseDocumentSerialization.key__PARTICIPATION__STORY_PARAGRAPHS_DELETED);
			this.countStoryParagraphsLocked = aJsonObject.getInt(FseDocumentSerialization.key__PARTICIPATION__STORY_PARAGRAPHS_LOCKED);
			this.countStoryParagraphsUnlocked = aJsonObject.getInt(FseDocumentSerialization.key__PARTICIPATION__STORY_PARAGRAPHS_UNLOCKED);
			this.countNotesParagraphsNew = aJsonObject.getInt(FseDocumentSerialization.key__PARTICIPATION__NOTES_PARAGRAPHS_NEW);
			this.countNotesParagraphsModified = aJsonObject.getInt(FseDocumentSerialization.key__PARTICIPATION__NOTES_PARAGRAPHS_MODIFIED);
			this.countNotesParagraphsDeleted = aJsonObject.getInt(FseDocumentSerialization.key__PARTICIPATION__NOTES_PARAGRAPHS_DELETED);
			this.countNotesParagraphsLocked = aJsonObject.getInt(FseDocumentSerialization.key__PARTICIPATION__NOTES_PARAGRAPHS_LOCKED);
			this.countNotesParagraphsUnlocked = aJsonObject.getInt(FseDocumentSerialization.key__PARTICIPATION__NOTES_PARAGRAPHS_UNLOCKED);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(FseDocumentSerialization.key__PARTICIPATION__COMMUNITY_MEMBER_ID, this.communityMemberNodeIdString);
			JsonHelper.putDate(theJsonObject, FseDocumentSerialization.key__PARTICIPATION__FIRST_CONTRIBUTION, this.firstContributionTimestamp);
			JsonHelper.putDate(theJsonObject, FseDocumentSerialization.key__PARTICIPATION__LAST_CONTRIBUTION, this.lastContributionTimestamp);
			theJsonObject.put(FseDocumentSerialization.key__PARTICIPATION__STORY_PARAGRAPHS_NEW, this.countStoryParagraphsNew);
			theJsonObject.put(FseDocumentSerialization.key__PARTICIPATION__STORY_PARAGRAPHS_MODIFIED, this.countStoryParagraphsModified);
			theJsonObject.put(FseDocumentSerialization.key__PARTICIPATION__STORY_PARAGRAPHS_DELETED, this.countStoryParagraphsDeleted);
			theJsonObject.put(FseDocumentSerialization.key__PARTICIPATION__STORY_PARAGRAPHS_LOCKED, this.countStoryParagraphsLocked);
			theJsonObject.put(FseDocumentSerialization.key__PARTICIPATION__STORY_PARAGRAPHS_UNLOCKED, this.countStoryParagraphsUnlocked);
			theJsonObject.put(FseDocumentSerialization.key__PARTICIPATION__NOTES_PARAGRAPHS_NEW, this.countNotesParagraphsNew);
			theJsonObject.put(FseDocumentSerialization.key__PARTICIPATION__NOTES_PARAGRAPHS_MODIFIED, this.countNotesParagraphsModified);
			theJsonObject.put(FseDocumentSerialization.key__PARTICIPATION__NOTES_PARAGRAPHS_DELETED, this.countNotesParagraphsDeleted);
			theJsonObject.put(FseDocumentSerialization.key__PARTICIPATION__NOTES_PARAGRAPHS_LOCKED, this.countNotesParagraphsLocked);
			theJsonObject.put(FseDocumentSerialization.key__PARTICIPATION__NOTES_PARAGRAPHS_UNLOCKED, this.countNotesParagraphsUnlocked);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public String getCommunityMemberNodeIdString() {
		return this.communityMemberNodeIdString;
	}

	public Date getFirstContributionTimestamp() {
		return this.firstContributionTimestamp;
	}

	public Date getLastContributionTimestamp() {
		return this.lastContributionTimestamp;
	}

	public int getCountStoryParagraphsNew() {
		return this.countStoryParagraphsNew;
	}

	public int getCountStoryParagraphsModified() {
		return this.countStoryParagraphsModified;
	}

	public int getCountStoryParagraphsDeleted() {
		return this.countStoryParagraphsDeleted;
	}

	public int getCountStoryParagraphsLocked() {
		return this.countStoryParagraphsLocked;
	}

	public int getCountStoryParagraphsUnlocked() {
		return this.countStoryParagraphsUnlocked;
	}

	public int getCountNotesParagraphsNew() {
		return this.countNotesParagraphsNew;
	}

	public int getCountNotesParagraphsModified() {
		return this.countNotesParagraphsModified;
	}

	public int getCountNotesParagraphsDeleted() {
		return this.countNotesParagraphsDeleted;
	}

	public int getCountNotesParagraphsLocked() {
		return this.countNotesParagraphsLocked;
	}

	public int getCountNotesParagraphsUnlocked() {
		return this.countNotesParagraphsUnlocked;
	}
	
	/////////////////////////////////////

	public void updateLastContributionTimestamp(Date aLastContributionTimestamp) {
		this.lastContributionTimestamp = aLastContributionTimestamp;
	}

	public void updateCountStoryParagraphsNew(int aCountStoryParagraphsNew) {
		this.countStoryParagraphsNew += aCountStoryParagraphsNew;
	}

	public void updateCountStoryParagraphsModified(int aCountStoryParagraphsModified) {
		this.countStoryParagraphsModified += aCountStoryParagraphsModified;
	}

	public void updateCountStoryParagraphsDeleted(int aCountStoryParagraphsDeleted) {
		this.countStoryParagraphsDeleted += aCountStoryParagraphsDeleted;
	}

	public void updateCountStoryParagraphsLocked(int aCountStoryParagraphsLocked) {
		this.countStoryParagraphsLocked += aCountStoryParagraphsLocked;
	}

	public void updateCountStoryParagraphsUnlocked(int aCountStoryParagraphsUnlocked) {
		this.countStoryParagraphsUnlocked += aCountStoryParagraphsUnlocked;
	}

	public void updateCountNotesParagraphsNew(int aCountNotesParagraphsNew) {
		this.countNotesParagraphsNew += aCountNotesParagraphsNew;
	}

	public void updateCountNotesParagraphsModified(int aCountNotesParagraphsModified) {
		this.countNotesParagraphsModified += aCountNotesParagraphsModified;
	}

	public void updateCountNotesParagraphsDeleted(int aCountNotesParagraphsDeleted) {
		this.countNotesParagraphsDeleted += aCountNotesParagraphsDeleted;
	}

	public void updateCountNotesParagraphsLocked(int aCountNotesParagraphsLocked) {
		this.countNotesParagraphsLocked += aCountNotesParagraphsLocked;
	}

	public void updateCountNotesParagraphsUnlocked(int aCountNotesParagraphsUnlocked) {
		this.countNotesParagraphsUnlocked += aCountNotesParagraphsUnlocked;
	}

	@Override
	public String getSerialized() {
		return "";
	}

	@Override
	public boolean validateSerializationFormatVersion(String aString) {
		return false;
	}
	
}