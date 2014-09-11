/* @(#)FseDocumentSerialization.java
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

package com.flywheelms.library.fse.util;

import android.text.ParcelableSpan;

import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.model.FseDocumentSectionParagraphAudit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class FseDocumentSerialization {
	
	public static final String SERIALIZATION_FORMAT_VERSION = "0.1";
	public static final String key__SERIALIZATION_FORMAT_VERSION = "SerializationFormatVersion";

	public static final String key__NODE_FRAG_NODE_ID = "NodeFragNodeId";

	public static final String key__DOCUMENT_IS_NEW = "DocumentIsNew";
	public static final String key__DOCUMENT_BASELINE = "DocumentBaseline";
	public static final String key__DOCUMENT_BODY = "DocumentBody";
	public static final String key__DOCUMENT_MARGIN__LEFT = "DocumentMarginLeft";
	public static final String key__DOCUMENT_INITIAL_SECTION_TAB = "InitialDocumentSectionTab";
	public static final String key__DOCUMENT_HEADLINE = "Headline";
	public static final String key__DOCUMENT_SECTION__TRIBKN = "DocumentSectionTribKn";
	public static final String key__DOCUMENT_SECTION__COLLABORATORS = "DocumentSectionCollaborators";
	public static final String key__DOCUMENT_SECTION__HISTORY = "DocumentSectionHistory";
	public static final String key__DOCUMENT_SECTION__HISTORY_COMPRESSION = "DocumentSectionHistoryCompression";
	public static final String key__DOCUMENT_SECTION__STORY = "DocumentSectionStory";
	public static final String key__DOCUMENT_SECTION__NOTES = "DocumentSectionNotes";
	public static final String key__DOCUMENT_SECTION__LOCK_MODIFICATION_STATE = "DocumentSectionLockModificationState";
	public static final String key__DOCUMENT_SECTION__INSERT_MODIFICATION_STATE = "DocumentSectionInsertModificationState";
	public static final String key__DOCUMENT_SECTION__CONTENT_MODIFICATION_STATE = "DocumentSectionContentModificationState";
	public static final String key__DOCUMENT_SECTION__STYLE_MODIFICATION_STATE = "DocumentSectionStyleModificationState";
	public static final String key__DOCUMENT_SECTION__SEQUENCE_MODIFICATION_STATE = "DocumentSectionSequenceModificationState";
	public static final String key__DOCUMENT_SECTION__NUMBERING_MODIFICATION_STATE = "DocumentSectionNumberingModificationState";

	public static final String key__BROWSER_MODE = "BrowserMode";
	
	public static final String key__FOR_EXPORT = "ForExport";
	public static final String key__FOR_OFFLINE_COLLABORATION = "ForOfflineCollaboration";

	public static final String key__PARAGRAPH_SECTION__INITIAL_PARAGRAPH_FOCUS = "InitialParagraphFocusId";
	public static final String key__PARAGRAPH_SECTION__INITIAL_CURSOR_POSITION = "InitialParagraphFocusCursorPosition";
	public static final String key__PARAGRAPH_SECTION__PARAGRAPH_ARRAY = "ParagraphArray";
	
	public static final String key__PARAGRAPH = "Paragraph";
	public static final String key__PARAGRAPH_ID = "ParagraphId";
	public static final String key__PREVIOUS_PARAGRAPH_ID = "PreviousParagraphId";
	public static final String key__INITIAL_PREVIOUS_PARAGRAPH_ID = "InitialPreviousParagraphId";
	public static final String key__NEXT_PARAGRAPH_ID = "NextParagraphId";
	public static final String key__INITIAL_NEXT_PARAGRAPH_ID = "InitialNextParagraphId";
	public static final String key__PARAGRAPH_STYLE = "ParagraphStyle";
	public static final String key__PARAGRAPH_INITIAL_STYLE = "ParagraphInitialStyle";
	public static final String key__PARAGRAPH_NUMBERING_STYLE = "NumberingStyle";
	public static final String key__PARAGRAPH_NUMBERING_SEQUENCE = "NumberingSequence";
	public static final String key__PARAGRAPH_NUMBERING_STRING = "NumberingString";
	public static final String key__PARAGRAPH_INITIAL_NUMBERING_STRING = "InitialNumberingString";
	public static final String key__PARAGRAPH_TEXT_CONTENT = "TextContent";
	public static final String key__INITIAL_PARAGRAPH_TEXT_CONTENT = "InitialTextContent";
	public static final String key__PARAGRAPH_INITIAL_TEXT_CONTENT = "InitialTextContent";
	public static final String key__PARAGRAPH_ADDITIONAL_FORMATTING = "AdditionalFormattingArray";
	public static final String key__PARAGRAPH_IMMUTABLE_SPANS = "ImmutableSpanArray";
	public static final String key__PARAGRAPH_CONTENT_MODIFICATION_STATE = "ContentModificationState";
	public static final String key__PARAGRAPH_INITIALLY_LOCKED = "InitiallyLocked";
	public static final String key__PARAGRAPH_IS_LOCKED = "InitiallyLocked";
	public static final String key__PARAGRAPH_LOCK_MODIFICATION_STATE = "LockModificationState";
	public static final String key__PARAGRAPH_STYLE_MODIFICATION_STATE = "StyleModificationState";
	public static final String key__PARAGRAPH_SEQUENCE_MODIFICATION_STATE = "SequenceModificationState";
	public static final String key__PARAGRAPH_NUMBERING_MODIFICATION_STATE = "NumberingModificationState";
	public static final String key__PARAGRAPH_NODE_FRAG_AUDIT_BLOCK = "NodeFragAuditBlock";

	public static final String key__TRANSACTION_LIST = "TransactionList";
	public static final String key__TRANSACTION_NUMBER = "TransactionNumber";
	public static final String key__TRANSACTION_TIMESTAMP = "TransactionTimestamp";
	public static final String key__TRANSACTION_TYPE = "TransactionType";
	public static final String key__TRANSACTION_COMMUNITY_MEMBER = "TransactionCommunityMember";
	public static final String key__TRANSACTION_STORY_BODY_AUDIT = "TransactionStoryBodyAudit";
	public static final String key__TRANSACTION_NOTES_BODY_AUDIT = "TransactionStoryNotesAudit";
	public static final String key__TRANSACTION_SERIALIZED_FSE_DOCUMENT = "TransactionSerializedFseDocument";
	public static final String key__TRANSACTION_IS_CHECKPOINT = "TransactionIsCheckpoint";
	public static final String key__TRANSACTION_COMMENT = "TransactionCheckpointComment";
	
	public static final String key__AUDIT_PARAGRAPH_NEW_COUNT = "AuditParagraphNewCount";
	public static final String key__AUDIT_PARAGRAPH_MODIFIED_COUNT = "AuditParagraphModifiedCount";
	public static final String key__AUDIT_PARAGRAPH_DELETED_COUNT = "AuditParagraphDeletedCount";
	public static final String key__AUDIT_PARAGRAPH_UNCHANGED_COUNT = "AuditParagraphUnchangedCount";
	public static final String key__AUDIT_PARAGRAPH_LOCKED_COUNT = "AuditParagraphLockedCount";
	public static final String key__AUDIT_PARAGRAPH_UNLOCKED_COUNT = "AuditParagraphUnlockedCount";
	
	public static final String key__COLLABORATOR_SUMMARY = "CollaboratorSummary";
	public static final String key__COLLABORATOR_LIST = "CollaboratorList";
	public static final String key__PARTICIPATION__COMMUNITY_MEMBER_ID = "CommunityMemberId";
	public static final String key__PARTICIPATION__FIRST_CONTRIBUTION = "FirstContribution";
	public static final String key__PARTICIPATION__LAST_CONTRIBUTION = "LastContribution";
	public static final String key__PARTICIPATION__STORY_PARAGRAPHS_NEW = "StoryParagraphsNew";
	public static final String key__PARTICIPATION__STORY_PARAGRAPHS_MODIFIED = "StoryParagraphsModified";
	public static final String key__PARTICIPATION__STORY_PARAGRAPHS_DELETED = "StoryParagraphsDeleted";
	public static final String key__PARTICIPATION__STORY_PARAGRAPHS_LOCKED = "StoryParagraphsLocked";
	public static final String key__PARTICIPATION__STORY_PARAGRAPHS_UNLOCKED = "StoryParagraphsUnlocked";
	public static final String key__PARTICIPATION__NOTES_PARAGRAPHS_NEW = "NotesParagraphsNew";
	public static final String key__PARTICIPATION__NOTES_PARAGRAPHS_MODIFIED = "NotesParagraphsModified";
	public static final String key__PARTICIPATION__NOTES_PARAGRAPHS_DELETED = "NotesParagraphsDeleted";
	public static final String key__PARTICIPATION__NOTES_PARAGRAPHS_LOCKED = "NotesParagraphsLocked";
	public static final String key__PARTICIPATION__NOTES_PARAGRAPHS_UNLOCKED = "NotesParagraphsUnlocked";

	public static FseDocumentSectionParagraphAudit getDocumentBodyAudit(
			JSONObject aJsonObject,
			FseDocumentSectionType aDocumentSectionType ) {
		FseDocumentSectionParagraphAudit theDocumentBodyAudit = new FseDocumentSectionParagraphAudit(aDocumentSectionType);
		try {
			theDocumentBodyAudit.setParagraphsNewCount(aJsonObject.getInt(FseDocumentSerialization.key__AUDIT_PARAGRAPH_NEW_COUNT));
			theDocumentBodyAudit.setParagraphsModifiedCount(aJsonObject.getInt(FseDocumentSerialization.key__AUDIT_PARAGRAPH_MODIFIED_COUNT));
			theDocumentBodyAudit.setParagraphsDeletedCount(aJsonObject.getInt(FseDocumentSerialization.key__AUDIT_PARAGRAPH_DELETED_COUNT));
			theDocumentBodyAudit.setParagraphsUnchangedCount(aJsonObject.getInt(FseDocumentSerialization.key__AUDIT_PARAGRAPH_UNCHANGED_COUNT));
			theDocumentBodyAudit.setParagraphsLockedCount(aJsonObject.getInt(FseDocumentSerialization.key__AUDIT_PARAGRAPH_LOCKED_COUNT));
			theDocumentBodyAudit.setParagraphsUnlockedCount(aJsonObject.getInt(FseDocumentSerialization.key__AUDIT_PARAGRAPH_UNLOCKED_COUNT));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theDocumentBodyAudit;
	}

	public static void putDocumentBodyAudit(
			JSONObject aParentJsonObject,
			String aJsonKey,
			FseDocumentSectionParagraphAudit aDocumentBodyAudit ) {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(FseDocumentSerialization.key__AUDIT_PARAGRAPH_NEW_COUNT, aDocumentBodyAudit.getParagraphsNewCount());
			theJsonObject.put(FseDocumentSerialization.key__AUDIT_PARAGRAPH_MODIFIED_COUNT, aDocumentBodyAudit.getParagraphsModifiedCount());
			theJsonObject.put(FseDocumentSerialization.key__AUDIT_PARAGRAPH_DELETED_COUNT, aDocumentBodyAudit.getParagraphsDeletedCount());
			theJsonObject.put(FseDocumentSerialization.key__AUDIT_PARAGRAPH_UNCHANGED_COUNT, aDocumentBodyAudit.getParagraphsUnchangedCount());
			theJsonObject.put(FseDocumentSerialization.key__AUDIT_PARAGRAPH_LOCKED_COUNT, aDocumentBodyAudit.getParagraphsLockedCount());
			theJsonObject.put(FseDocumentSerialization.key__AUDIT_PARAGRAPH_UNLOCKED_COUNT, aDocumentBodyAudit.getParagraphsUnlockedCount());
			aParentJsonObject.put(aJsonKey, theJsonObject);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static JSONArray createParcelableSpanArray(ParcelableSpan[] aParcelableSpan) {
		JSONArray theJsonArray = null;
		if(aParcelableSpan == null) {
			theJsonArray = new JSONArray();
		} else {
			// TODO - a real implementation is needed
			theJsonArray = new JSONArray(Arrays.asList(aParcelableSpan));
		}
		return theJsonArray;
	}
	
	public static ParcelableSpan[] getParcelableSpanArray(JSONObject aJsonObject, String aKey) {
		ParcelableSpan[] theParcelableSpanArray = null;
		try {
			if(aJsonObject.getJSONArray(aKey).equals(new JSONArray())) {
				return theParcelableSpanArray;
			}
//			} else {
//				// TODO - a real implementation is needed
//				// TODO - parse/create array
//			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
