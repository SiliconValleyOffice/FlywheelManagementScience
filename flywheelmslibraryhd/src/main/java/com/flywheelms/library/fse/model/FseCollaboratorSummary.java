/* @(#)FseCommunityMemberParticipationSummary.java
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

import java.util.Collection;
import java.util.Hashtable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.util.FseDocumentSerialization;
import com.flywheelms.library.gcg.interfaces.GcgSerialization;

public class FseCollaboratorSummary implements GcgSerialization {
	
	private final Hashtable<String, FseCommunityMemberCollaborationSummary> collaboratorList = new Hashtable<String, FseCommunityMemberCollaborationSummary>();
	
	public FseCollaboratorSummary() {
	}
	
	public FseCollaboratorSummary(JSONObject aJsonObject) {
		JSONArray theJsonArray = new JSONArray();
		try {
			theJsonArray = aJsonObject.getJSONArray(FseDocumentSerialization.key__COLLABORATOR_LIST);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0; i < theJsonArray.length(); i++) {
			JSONObject theJsonObject;
			try {
				theJsonObject = theJsonArray.getJSONObject(i);
				FseCommunityMemberCollaborationSummary theCommunityMemberParticipation = new FseCommunityMemberCollaborationSummary(theJsonObject); 
				this.collaboratorList.put(theCommunityMemberParticipation.getCommunityMemberNodeIdString(), theCommunityMemberParticipation);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public FseCollaboratorSummary(
			FseDocumentSectionHistory aDocumentSectionHistory) {
		updateCommunityMemberParticipationList(aDocumentSectionHistory);
	}

	public Collection<FseCommunityMemberCollaborationSummary> getCommunityMemberParticipationList() {
		return this.collaboratorList.values();
	}
	
	public void updateCommunityMemberParticipationList(FseDocumentSectionHistory aDocumentSectionHistory) {
		this.collaboratorList.clear();
		for(FseDocumentTransaction theDocumentTransaction : aDocumentSectionHistory.getDocumentTransactionList()) {
			if(this.collaboratorList.containsKey(theDocumentTransaction.getCommunityMemberNodeIdString())) {
				updateCommunityMemberParticipation(theDocumentTransaction);
			} else {
				FseCommunityMemberCollaborationSummary theCommunityMemberParticipation =
						new FseCommunityMemberCollaborationSummary(
								theDocumentTransaction.getCommunityMemberNodeIdString(),
								theDocumentTransaction.getRowTimestamp(),
								theDocumentTransaction.getRowTimestamp(),
								theDocumentTransaction.getParagraphNewCount(FseDocumentSectionType.STORY),
								theDocumentTransaction.getParagraphModifiedCount(FseDocumentSectionType.STORY),
								theDocumentTransaction.getParagraphDeletedCount(FseDocumentSectionType.STORY),
								theDocumentTransaction.getParagraphLockedCount(FseDocumentSectionType.STORY),
								theDocumentTransaction.getParagraphUnlockedCount(FseDocumentSectionType.STORY),
								theDocumentTransaction.getParagraphNewCount(FseDocumentSectionType.NOTES),
								theDocumentTransaction.getParagraphModifiedCount(FseDocumentSectionType.NOTES),
								theDocumentTransaction.getParagraphDeletedCount(FseDocumentSectionType.NOTES),
								theDocumentTransaction.getParagraphLockedCount(FseDocumentSectionType.NOTES),
								theDocumentTransaction.getParagraphUnlockedCount(FseDocumentSectionType.NOTES) );
				this.collaboratorList.put(theCommunityMemberParticipation.getCommunityMemberNodeIdString(), theCommunityMemberParticipation);
			}
		}
	}

	private void updateCommunityMemberParticipation(FseDocumentTransaction theDocumentTransaction) {
		FseCommunityMemberCollaborationSummary theCommunityMemberParticipation =
				this.collaboratorList.get(theDocumentTransaction.getCommunityMemberNodeIdString());
		theCommunityMemberParticipation.updateLastContributionTimestamp(theDocumentTransaction.getRowTimestamp());
		theCommunityMemberParticipation.updateCountStoryParagraphsNew(theDocumentTransaction.getParagraphNewCount(FseDocumentSectionType.STORY));
		theCommunityMemberParticipation.updateCountStoryParagraphsModified(theDocumentTransaction.getParagraphModifiedCount(FseDocumentSectionType.STORY));
		theCommunityMemberParticipation.updateCountStoryParagraphsDeleted(theDocumentTransaction.getParagraphDeletedCount(FseDocumentSectionType.STORY));
		theCommunityMemberParticipation.updateCountStoryParagraphsLocked(theDocumentTransaction.getParagraphLockedCount(FseDocumentSectionType.STORY));
		theCommunityMemberParticipation.updateCountStoryParagraphsUnlocked(theDocumentTransaction.getParagraphUnlockedCount(FseDocumentSectionType.STORY));
		theCommunityMemberParticipation.updateCountNotesParagraphsNew(theDocumentTransaction.getParagraphNewCount(FseDocumentSectionType.NOTES));
		theCommunityMemberParticipation.updateCountNotesParagraphsModified(theDocumentTransaction.getParagraphModifiedCount(FseDocumentSectionType.NOTES));
		theCommunityMemberParticipation.updateCountNotesParagraphsDeleted(theDocumentTransaction.getParagraphDeletedCount(FseDocumentSectionType.NOTES));
		theCommunityMemberParticipation.updateCountNotesParagraphsLocked(theDocumentTransaction.getParagraphLockedCount(FseDocumentSectionType.NOTES));
		theCommunityMemberParticipation.updateCountNotesParagraphsUnlocked(theDocumentTransaction.getParagraphUnlockedCount(FseDocumentSectionType.NOTES));
	}

//	private void generateTestData() {
//		this.communityMemberParticipationList.add(new FseCommunityMemberParticipation(
//				"Adam Dye",
//				FmmDateHelper.getCurrentDateTime(),
//				FmmDateHelper.getCurrentDateTime(),
//				5,
//				18,
//				3,
//				7,
//				1,
//				7,
//				14,
//				1,
//				8,
//				2) );
//		this.communityMemberParticipationList.add(new FseCommunityMemberParticipation(
//				"Steve Stamps",
//				FmmDateHelper.getCurrentDateTime(),
//				FmmDateHelper.getCurrentDateTime(),
//				15,
//				23,
//				7,
//				11,
//				3,
//				11,
//				18,
//				2,
//				14,
//				4) );
//	}

	@Override
	public String getSerialized() {
		return getJsonObject().toString();
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(FseDocumentSerialization.key__COLLABORATOR_LIST, getCollaboratorList());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}

	private JSONArray getCollaboratorList() {
		JSONArray theJsonArray = new JSONArray();
		for(FseCommunityMemberCollaborationSummary theCommunityMemberParticipation : this.collaboratorList.values()) {
			theJsonArray.put(theCommunityMemberParticipation.getJsonObject());
		}
		return theJsonArray;
	}

	@Override
	public boolean validateSerializationFormatVersion(String aString) {
		// TODO Auto-generated method stub
		return false;
	}

}
