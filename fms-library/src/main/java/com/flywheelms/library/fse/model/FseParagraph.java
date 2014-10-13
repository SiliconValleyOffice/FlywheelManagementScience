/* @(#)FseParagraph.java
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

import android.text.ParcelableSpan;

import com.flywheelms.library.fmm.enumerator.FmmLockStatus;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.audit.FmmAuditNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fse.enumerator.FseContentModificationState;
import com.flywheelms.library.fse.enumerator.FseLockModificationState;
import com.flywheelms.library.fse.enumerator.FseNumberingModificationState;
import com.flywheelms.library.fse.enumerator.FseParagraphContentType;
import com.flywheelms.library.fse.enumerator.FseParagraphNumberingStyle;
import com.flywheelms.library.fse.enumerator.FseParagraphStyle;
import com.flywheelms.library.fse.enumerator.FseSequenceModificationState;
import com.flywheelms.library.fse.enumerator.FseStyleModificationState;
import com.flywheelms.library.fse.util.FseDocumentSerialization;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

public class FseParagraph extends FmmAuditNodeImpl {

	private String nextParagraphId = "";
	private String previousParagraphId = "";
	private FseParagraphStyle style;
	private FseParagraphNumberingStyle numberingStyle = FseParagraphNumberingStyle.NONE;
	private int numberingSequence = 0;
	private String numberingString = "";
	private ParcelableSpan[] additionalFormattingSpanArray;
	private ParcelableSpan[] immutableSpanArray;
	private String textContent = "";
	private boolean isLocked = false;
	private FseLockModificationState lockModificationState = FseLockModificationState.UNCHANGED;
	private FseContentModificationState contentModificationState = FseContentModificationState.UNCHANGED;
	private FseStyleModificationState styleModificationState = FseStyleModificationState.UNCHANGED;
	private FseSequenceModificationState sequenceModificationState = FseSequenceModificationState.UNCHANGED;
	private FseNumberingModificationState numberingModificationState = FseNumberingModificationState.UNCHANGED;

	// generate paragraph from a template
	public FseParagraph(FseTemplateParagraph anFseTemplateParagraph) {
		this(anFseTemplateParagraph.getParagraphStyle(), anFseTemplateParagraph.getNumberingStyle(), anFseTemplateParagraph.getInitialText());
		this.getNodeFragAuditBlock().setIsLocked(anFseTemplateParagraph.isLocked());
	}

	public FseParagraph(FseParagraphStyle anFseParagraphStyle, FseParagraphNumberingStyle aNumberingStyle, String anInitialTextString) {
		this(generateParagraphId(), anFseParagraphStyle, aNumberingStyle, anInitialTextString);
	}

	// intermediate constructor,
	public FseParagraph(String aParagraphId, FseParagraphStyle anFseParagraphStyle, FseParagraphNumberingStyle aNumberingStyle, String anInitialTextString) {
		this(aParagraphId, anFseParagraphStyle, aNumberingStyle, anInitialTextString, new NodeFragAuditBlock(aParagraphId));
	}

	// main constructor, only called directly to generate an FseParagraph from an FseParagraphView (without coupling)	
	public FseParagraph(String aParagraphId, FseParagraphStyle anFseParagraphStyle, FseParagraphNumberingStyle aNumberingStyle, String anInitialTextString, NodeFragAuditBlock anAuditBlock) {
		super(NodeId.hydrate(FseParagraph.class, aParagraphId));
		this.style = anFseParagraphStyle;
		this.numberingStyle = aNumberingStyle;
		this.textContent = anInitialTextString;
		setNodeFragAuditBlock(anAuditBlock);
	}

	public FseParagraph(JSONObject aJsonObject) {
		super(NodeId.hydrate(FseParagraph.class, JsonHelper.getString(aJsonObject, FseDocumentSerialization.key__PARAGRAPH_ID)));
		try {
			this.previousParagraphId = aJsonObject.getString(FseDocumentSerialization.key__PREVIOUS_PARAGRAPH_ID);
			this.nextParagraphId = aJsonObject.getString(FseDocumentSerialization.key__NEXT_PARAGRAPH_ID);
			this.style = FseParagraphStyle.getStyleForName(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_STYLE));
			this.numberingStyle = FseParagraphNumberingStyle.getStyleForString(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_STYLE));
			this.numberingSequence = aJsonObject.getInt(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_SEQUENCE);
			this.numberingString = aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_STRING);
			this.isLocked = aJsonObject.getBoolean(FseDocumentSerialization.key__PARAGRAPH_IS_LOCKED);
			this.additionalFormattingSpanArray = FseDocumentSerialization.getParcelableSpanArray(aJsonObject, FseDocumentSerialization.key__PARAGRAPH_ADDITIONAL_FORMATTING);
			this.immutableSpanArray = FseDocumentSerialization.getParcelableSpanArray(aJsonObject, FseDocumentSerialization.key__PARAGRAPH_IMMUTABLE_SPANS);
			this.lockModificationState = FseLockModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_LOCK_MODIFICATION_STATE));
			this.contentModificationState = FseContentModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_CONTENT_MODIFICATION_STATE));
			this.styleModificationState = FseStyleModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_STYLE_MODIFICATION_STATE));
			this.sequenceModificationState = FseSequenceModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_SEQUENCE_MODIFICATION_STATE));
			this.numberingModificationState = FseNumberingModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_MODIFICATION_STATE));
			this.textContent = aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_TEXT_CONTENT);
			setNodeFragAuditBlock(new NodeFragAuditBlock(aJsonObject.getJSONObject(FseDocumentSerialization.key__DOCUMENT_SECTION__COLLABORATORS)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return super.toString() + " --> " + this.textContent;
	}

	public String getParagraphId() {
		return getNodeIdString();
	}

	public String getTextContent() {
		return this.textContent;
	}
	
	public String getTextForPublication() {
		return this.numberingString + this.textContent;
	}

	public String getNextParagraphId() {
		return this.nextParagraphId;
	}

	public void setNextParagraphId(String nextParagraphId) {
		this.nextParagraphId = nextParagraphId;
	}

	public String getPreviousParagraphId() {
		return this.previousParagraphId;
	}

	public void setPreviousParagraphId(String previousParagraphId) {
		this.previousParagraphId = previousParagraphId;
	}

	public FseParagraphStyle getStyle() {
		return this.style;
	}

	public FseParagraphNumberingStyle getAggregateNumberingStyle() {
		if(this.numberingStyle.equals(FseParagraphNumberingStyle.DEFAULT)) {
			return this.style.getNumberingStyle();
		}
		return this.numberingStyle;
	}

	public FseParagraphNumberingStyle getNumberingStyle() {
		return this.numberingStyle;
	}

	public void setNumberingStyle(FseParagraphNumberingStyle numberingStyle) {
		this.numberingStyle = numberingStyle;
	}
	
	public boolean isModified() {
		return !getSequenceModificationState().isUnchanged() 
				|| !getLockModificationState().isUnchanged() 
				|| !getContentModificationState().isUnchanged() 
				|| !getNumberingModificationState().isUnchanged() 
				|| !getStyleModificationState().isUnchanged();
	}
	
	public boolean isNumbered() {
		if(this.numberingStyle.equals(FseParagraphNumberingStyle.DEFAULT)) {
			return ! this.style.getNumberingStyle().equals(FseParagraphNumberingStyle.NONE);
		}
		return ! this.numberingStyle.equals(FseParagraphNumberingStyle.NONE);
	}

	public int getNumberingSequence() {
		return this.numberingSequence;
	}

	public void setNumberingSequence(int aNumberingSequence) {
		this.numberingSequence = aNumberingSequence;
		this.numberingString = this.getNumberingStyle().getNumberingString(this.numberingSequence);
	}

	public String getNumberingString() {
		return this.numberingString;
	}

	public boolean isTextContent() {
		return this.style.getContentType() == FseParagraphContentType.TEXT;
	}

	public boolean isImageContent() {
		return this.style.getContentType() == FseParagraphContentType.IMAGE;
	}

	public boolean isDrawingPadContent() {
		return this.style.getContentType() == FseParagraphContentType.DRAWING;
	}
	
	public static String generateParagraphId() {
		StringBuilder theStringBuilder = new StringBuilder(FmmNodeDefinition.getEntryForClass(FseParagraph.class).getNodeTypeCode() + "-");
		theStringBuilder.append(UUID.randomUUID());
		return theStringBuilder.toString();
	}

	public FseLockModificationState getLockModificationState() {
		return this.lockModificationState;
	}
	
	public void setLockModificationState(FseLockModificationState aLockModificationState) {
		this.lockModificationState = aLockModificationState;
	}

	public FseContentModificationState getContentModificationState() {
		return this.contentModificationState;
	}

	public void setContentModificationState(FseContentModificationState aModificationState) {
		this.contentModificationState = aModificationState;
	}

	public FseSequenceModificationState getSequenceModificationState() {
		return this.sequenceModificationState;
	}

	public void setSequenceModificationState(FseSequenceModificationState aModificationState) {
		this.sequenceModificationState = aModificationState;
	}
	
	public FseNumberingModificationState getNumberingModificationState() {
		return this.numberingModificationState;
	}

	public void setNumberingModificationState(FseNumberingModificationState aModificationState) {
		this.numberingModificationState = aModificationState;
	}

	public FseStyleModificationState getStyleModificationState() {
		return this.styleModificationState;
	}

	public void setStyleModificationState(FseStyleModificationState aModificationState) {
		this.styleModificationState = aModificationState;
	}
	
	@Override
	public FmmLockStatus getLockStatus() {
		return getNodeFragAuditBlock().getLockStatus();
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_ID, getNodeIdString());
			theJsonObject.put(FseDocumentSerialization.key__PREVIOUS_PARAGRAPH_ID, getPreviousParagraphId());
			theJsonObject.put(FseDocumentSerialization.key__NEXT_PARAGRAPH_ID, getNextParagraphId());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_STYLE, this.style.getName());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_STYLE, this.numberingStyle.toString());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_SEQUENCE, this.numberingSequence);
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_STRING, this.numberingString);
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_TEXT_CONTENT, this.textContent);
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_SEQUENCE_MODIFICATION_STATE, this.sequenceModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_LOCK_MODIFICATION_STATE, this.lockModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_IS_LOCKED, this.isLocked);
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_CONTENT_MODIFICATION_STATE, this.contentModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_MODIFICATION_STATE, this.numberingModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_STYLE_MODIFICATION_STATE, this.styleModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_ADDITIONAL_FORMATTING,
					FseDocumentSerialization.createParcelableSpanArray(this.additionalFormattingSpanArray));
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_IMMUTABLE_SPANS,
					FseDocumentSerialization.createParcelableSpanArray(this.immutableSpanArray));
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__COLLABORATORS, getNodeFragAuditBlock().getJsonObject() );
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public void resetModificationState() {
		this.lockModificationState = FseLockModificationState.UNCHANGED;
		this.contentModificationState = FseContentModificationState.UNCHANGED;
		this.styleModificationState = FseStyleModificationState.UNCHANGED;
		this.sequenceModificationState = FseSequenceModificationState.UNCHANGED;
		this.numberingModificationState = FseNumberingModificationState.UNCHANGED;
	}

	@Override
	public boolean isLocked() {  // HACK ALERT  !!!!!!    SDS
		return this.isLocked;
	}

	public void setLocked(boolean aBoolean) {
		this.isLocked = aBoolean;
	}
	
	@Override
	public void setIsLocked(boolean aBoolean) {
		super.setIsLocked(aBoolean);
		this.isLocked = aBoolean;
	}

	public boolean isPeerBreak(FseParagraph aParagraph) {
		return this.style.isPeerBreak(aParagraph.getStyle());
	}
	
}
