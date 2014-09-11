/* @(#)FseDocumentSection.java
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

import com.flywheelms.library.fmm.enumerator.FmmLockStatus;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.enumerator.FseContentModificationState;
import com.flywheelms.library.fse.enumerator.FseInsertModificationState;
import com.flywheelms.library.fse.enumerator.FseLockModificationState;
import com.flywheelms.library.fse.enumerator.FseNumberingModificationState;
import com.flywheelms.library.fse.enumerator.FseParagraphNumberingStyle;
import com.flywheelms.library.fse.enumerator.FseSequenceModificationState;
import com.flywheelms.library.fse.enumerator.FseStyleModificationState;
import com.flywheelms.library.fse.util.FseDocumentSerialization;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.ListIterator;

// will be abstract
public abstract class FseDocumentSectionParagraphEditorContent extends FseDocumentSection {
	
	private String initialParagraphFocusId;
	private int initialParagraphFocusCursorPosition;
	private LinkedHashMap<String, FseParagraph> paragraphList = new LinkedHashMap<String, FseParagraph>();
	private FseLockModificationState lockModificationState = FseLockModificationState.UNCHANGED;
	private FseInsertModificationState insertModificationState = FseInsertModificationState.UNCHANGED;
	private FseContentModificationState contentModificationState = FseContentModificationState.UNCHANGED;
	private FseStyleModificationState styleModificationState = FseStyleModificationState.UNCHANGED;
	private FseSequenceModificationState sequenceModificationState = FseSequenceModificationState.UNCHANGED;
	private FseNumberingModificationState numberingModificationState = FseNumberingModificationState.UNCHANGED;
	
	public FseDocumentSectionParagraphEditorContent(FseDocumentSectionType aDocumentSectionType, JSONObject aJsonObject) {
		super(aDocumentSectionType);
		try {
			this.initialParagraphFocusId = aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_SECTION__INITIAL_PARAGRAPH_FOCUS);
			this.initialParagraphFocusCursorPosition = aJsonObject.getInt(FseDocumentSerialization.key__PARAGRAPH_SECTION__INITIAL_CURSOR_POSITION);
			this.lockModificationState = FseLockModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__DOCUMENT_SECTION__LOCK_MODIFICATION_STATE));
			this.insertModificationState = FseInsertModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__DOCUMENT_SECTION__INSERT_MODIFICATION_STATE));
			this.contentModificationState = FseContentModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__DOCUMENT_SECTION__CONTENT_MODIFICATION_STATE));
			this.styleModificationState = FseStyleModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__DOCUMENT_SECTION__STYLE_MODIFICATION_STATE));
			this.sequenceModificationState = FseSequenceModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__DOCUMENT_SECTION__SEQUENCE_MODIFICATION_STATE));
			this.numberingModificationState = FseNumberingModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__DOCUMENT_SECTION__NUMBERING_MODIFICATION_STATE));
			JSONArray theJsonArray = aJsonObject.getJSONArray(FseDocumentSerialization.key__PARAGRAPH_SECTION__PARAGRAPH_ARRAY);         
			for(int i=0;i < theJsonArray.length();i++){                                     
			    JSONObject theParagraphJsonObject = theJsonArray.getJSONObject(i);
			    FseParagraph theFseParagraph = new FseParagraph(theParagraphJsonObject);
				this.paragraphList.put(theFseParagraph.getParagraphId(), theFseParagraph);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public FseDocumentSectionParagraphEditorContent(FseDocumentSectionType aDocumentSectionType) {
		super(aDocumentSectionType);
	}

	public LinkedHashMap<String, FseParagraph> getParagraphList() {
		return this.paragraphList;
	}
	public void setParagraphList(LinkedHashMap<String, FseParagraph> aParagraphList) {
		this.paragraphList = aParagraphList;
	}
	public String getInitialParagraphFocusId() {
		return this.initialParagraphFocusId;
	}
	public void setInitialParagraphFocusId(String anInitialParagraphFocusId) {
		this.initialParagraphFocusId = anInitialParagraphFocusId;
	}
	public int getInitialParagraphFocusCursorPosition() {
		int theNumberingStringLength = this.paragraphList.get(this.initialParagraphFocusId).getNumberingString().length();
		if(theNumberingStringLength > this.initialParagraphFocusCursorPosition) {
			this.initialParagraphFocusCursorPosition = theNumberingStringLength;
		}
		return this.initialParagraphFocusCursorPosition;
	}
	public void setInitialParagraphFocusCursorPosition(int anInitialParagraphFocusCursorPosition) {
		this.initialParagraphFocusCursorPosition = anInitialParagraphFocusCursorPosition;
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_SECTION__INITIAL_PARAGRAPH_FOCUS, this.initialParagraphFocusId);
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_SECTION__INITIAL_CURSOR_POSITION, this.initialParagraphFocusCursorPosition);
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__LOCK_MODIFICATION_STATE, this.lockModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__INSERT_MODIFICATION_STATE, this.insertModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__CONTENT_MODIFICATION_STATE, this.contentModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__STYLE_MODIFICATION_STATE, this.styleModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__SEQUENCE_MODIFICATION_STATE, this.sequenceModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__NUMBERING_MODIFICATION_STATE, this.numberingModificationState.toString());
			JSONArray theJsonArray = new JSONArray();
			for (FseParagraph theParagraph : this.paragraphList.values()) {
				theJsonArray.put(theParagraph.getJsonObject());
			}
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_SECTION__PARAGRAPH_ARRAY, theJsonArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public FseLockModificationState getLockModificationState() {
		return this.lockModificationState;
	}
	
	public void setLockModificationState(FseLockModificationState aModificationState) {
		this.lockModificationState = aModificationState;
	}

	public FseInsertModificationState getInsertModificationState() {
		return this.insertModificationState;
	}

	public void setInsertModificationState(FseInsertModificationState insertModificationState) {
		this.insertModificationState = insertModificationState;
	}

	public FseContentModificationState getContentModificationState() {
		return this.contentModificationState;
	}
	
	public void setContentModificationState(FseContentModificationState aModificationState) {
		this.contentModificationState = aModificationState;
	}

	public FseStyleModificationState getStyleModificationState() {
		return this.styleModificationState;
	}
	
	public void setStyleModificationState(FseStyleModificationState aModificationState) {
		this.styleModificationState = aModificationState;
	}
	
	public FseSequenceModificationState getSequenceModificationState() {
		return this.sequenceModificationState;
	}

	public void setSequenceModificationState(FseSequenceModificationState sequenceModificationState) {
		this.sequenceModificationState = sequenceModificationState;
	}

	public FseNumberingModificationState getNumberingModificationState() {
		return this.numberingModificationState;
	}

	public void setNumberingModificationState(FseNumberingModificationState aNumberingModificationState) {
		this.numberingModificationState = aNumberingModificationState;
	}

	public void resetModificationState() {
		this.lockModificationState = FseLockModificationState.UNCHANGED;
		this.contentModificationState = FseContentModificationState.UNCHANGED;
		this.styleModificationState = FseStyleModificationState.UNCHANGED;
		this.sequenceModificationState = FseSequenceModificationState.UNCHANGED;
		this.numberingModificationState = FseNumberingModificationState.UNCHANGED;
//		for(FseParagraph theFseParagraph: this.paragraphList.values()) {
//			theFseParagraph.resetModificationState();
//		}
		String thePreviousParagraphNodeId = "";
		for(FseParagraph theFseParagraph : this.paragraphList.values()) {
			theFseParagraph.resetModificationState();
			theFseParagraph.setPreviousParagraphId(thePreviousParagraphNodeId);
			thePreviousParagraphNodeId = theFseParagraph.getParagraphId();
		}
		String theNextParagraphNodeId = "";
		ListIterator<FseParagraph> theListIterator = new ArrayList<FseParagraph>(this.paragraphList.values()).listIterator(this.paragraphList.size());
		while(theListIterator.hasPrevious()) {
			FseParagraph theParagraph = theListIterator.previous(); 
			theParagraph.setNextParagraphId(theNextParagraphNodeId);
			theNextParagraphNodeId = theParagraph.getParagraphId();
		}
	}

	public FseDocumentSectionParagraphAudit getInitialParagraphAudit() {
		FseDocumentSectionParagraphAudit theParagraphAudit = new FseDocumentSectionParagraphAudit(this.getSectionType());
		theParagraphAudit.setParagraphsNewCount(getParagraphsNewCount());
		theParagraphAudit.setParagraphsModifiedCount(getParagraphsContentModifiedCount());
		theParagraphAudit.setParagraphsUnchangedCount(getParagraphsUnchangedCount());
		theParagraphAudit.setParagraphsLockedCount(getParagraphsLockedCount());
		theParagraphAudit.setParagraphsUnlockedCount(0);
		theParagraphAudit.setParagraphsDeletedCount(0);
		return theParagraphAudit;
	}

	private int getParagraphsLockedCount() {
		int theCount = 0;
		for(FseParagraph theParagraph : this.paragraphList.values()) {
			if(theParagraph.getLockStatus() == FmmLockStatus.LOCKED) {
				++ theCount;
			}
		}
		return theCount;
	}

	private int getParagraphsUnchangedCount() {
		int theCount = 0;
		for(FseParagraph theParagraph : this.paragraphList.values()) {
			if(theParagraph.getContentModificationState() == FseContentModificationState.UNCHANGED) {
				++ theCount;
			}
		}
		return theCount;
	}

	private int getParagraphsContentModifiedCount() {
		int theCount = 0;
		for(FseParagraph theParagraph : this.paragraphList.values()) {
			if(theParagraph.getContentModificationState() == FseContentModificationState.MODIFIED) {
				++ theCount;
			}
		}
		return theCount;
	}

	private int getParagraphsNewCount() {
		int theCount = 0;
		for(FseParagraph theParagraph : this.paragraphList.values()) {
			if(theParagraph.getContentModificationState() == FseContentModificationState.NEW) {
				++ theCount;
			}
		}
		return theCount;
	}

	public boolean equalsContent(FseDocumentSectionParagraphEditorContent aDocumentSectionParagraphEditorContent) {
		if(this.paragraphList.size() != aDocumentSectionParagraphEditorContent.paragraphList.size() ||
				this.contentModificationState != aDocumentSectionParagraphEditorContent.contentModificationState ||
				this.lockModificationState != aDocumentSectionParagraphEditorContent.lockModificationState ||
				this.styleModificationState != aDocumentSectionParagraphEditorContent.styleModificationState ||
				this.sequenceModificationState != aDocumentSectionParagraphEditorContent.sequenceModificationState ||
				this.numberingModificationState != aDocumentSectionParagraphEditorContent.numberingModificationState ) {
			return false;
		}
		for(FseParagraph theParagraph : this.paragraphList.values()) {
			FseParagraph theOtherParagraph = aDocumentSectionParagraphEditorContent.getParagraph(theParagraph.getParagraphId());
			if(theOtherParagraph == null) {
				return false;
			}
			if(! theParagraph.getTextContent().equals(theOtherParagraph.getTextContent())) {
				return false;
			}
		}
		return true;
	}

	private FseParagraph getParagraph(String aParagraphId) {
		return this.paragraphList.get(aParagraphId);
	}

	public void renumberAll() {
		for(FseParagraph theFseParagraph : getPeerGroupLeadersToRenumber()) {
			renumberPeerParagraphs(theFseParagraph);
		}
	}

	public ArrayList<FseParagraph> getPeerGroupLeadersToRenumber() {
		ArrayList<FseParagraph> thePeerGroupLeaderParagraphList = new ArrayList<FseParagraph>();
		ArrayList<FseParagraph> theIgnoreParagraphList = new ArrayList<FseParagraph>();
		for(FseParagraph theFseParagraph : this.paragraphList.values()) {
			if(theIgnoreParagraphList.contains(theFseParagraph) || theFseParagraph.getAggregateNumberingStyle() == FseParagraphNumberingStyle.NONE) {
				continue;
			}
			thePeerGroupLeaderParagraphList.add(theFseParagraph);
			theIgnoreParagraphList.addAll(getParagraphPeerList(theFseParagraph));
		}
		return thePeerGroupLeaderParagraphList;
	}

	public void renumberPeerParagraphs(FseParagraph aParagraph) {
		int theNumberingSeqence = 0;
		for(FseParagraph theParagraph : getParagraphPeerList(aParagraph)) {
			++ theNumberingSeqence;
			theParagraph.setNumberingSequence(theNumberingSeqence);
		}
	}

	public ArrayList<FseParagraph> getParagraphPeerList(FseParagraph aParagraph) {
		ArrayList<FseParagraph> thePeerParagraphList = new ArrayList<FseParagraph>();
		ArrayList<FseParagraph> theReversedParagraphList = new ArrayList<FseParagraph>(this.paragraphList.values());
		Collections.reverse(theReversedParagraphList);
		int theStartingIndex = theReversedParagraphList.indexOf(aParagraph) + 1;
		for(FseParagraph theParagraph : theReversedParagraphList.subList(theStartingIndex, theReversedParagraphList.size())) {
			if(theParagraph.getStyle() == aParagraph.getStyle()) {
				thePeerParagraphList.add(theParagraph);
			} else if(aParagraph.isPeerBreak(theParagraph)) {
				break;
			}
		}
		Collections.reverse(thePeerParagraphList);
		thePeerParagraphList.add(aParagraph);
		thePeerParagraphList.addAll(getParagraphSubsequentPeerList(aParagraph));
		return thePeerParagraphList;
	}

	public ArrayList<FseParagraph> getParagraphSubsequentPeerList(FseParagraph aParagraph) {
		ArrayList<FseParagraph> theOriginalParagraphList = new ArrayList<FseParagraph>(this.paragraphList.values());
		ArrayList<FseParagraph> theSubsequentParagraphPeerList = new ArrayList<FseParagraph>();
		int theStartingIndex = theOriginalParagraphList.indexOf(aParagraph) + 1;
		for(FseParagraph theParagraph : theOriginalParagraphList.subList(theStartingIndex, theOriginalParagraphList.size())) {
			if(theParagraph.getStyle() == aParagraph.getStyle()) {
				theSubsequentParagraphPeerList.add(theParagraph);
			} else if(aParagraph.isPeerBreak(theParagraph)) {
				break;
			}
		}
		return theSubsequentParagraphPeerList;
	}

}
