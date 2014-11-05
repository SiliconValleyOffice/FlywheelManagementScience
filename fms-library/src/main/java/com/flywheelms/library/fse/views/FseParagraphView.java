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
package com.flywheelms.library.fse.views;

import android.content.Context;
import android.text.ParcelableSpan;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fca.FlywheelCommunityAuthentication;
import com.flywheelms.library.fmm.enumerator.FmmLockStatus;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNodeAudit;
import com.flywheelms.library.fse.enumerator.FseContentModificationState;
import com.flywheelms.library.fse.enumerator.FseLockModificationState;
import com.flywheelms.library.fse.enumerator.FseNumberingModificationState;
import com.flywheelms.library.fse.enumerator.FseParagraphNumberingStyle;
import com.flywheelms.library.fse.enumerator.FseParagraphStyle;
import com.flywheelms.library.fse.enumerator.FseSequenceModificationState;
import com.flywheelms.library.fse.enumerator.FseStyleModificationState;
import com.flywheelms.library.fse.history.FseParagraphHistoryDialog;
import com.flywheelms.library.fse.interfaces.FseParagraphContentView;
import com.flywheelms.library.fse.model.FseParagraph;
import com.flywheelms.library.fse.model.FseTemplateParagraph;
import com.flywheelms.library.fse.perspective_flipper.FsePerspectiveFlipper;
import com.flywheelms.library.fse.util.FseDocumentSerialization;
import com.flywheelms.library.fse.widget.FseParagraphSpinner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public abstract class FseParagraphView extends LinearLayout
		implements FmmNodeAudit {
	
	private static final int child_view__IMAGE_VIEW__SEQUENCE = 0;
	private static final int child_view__IMAGE_VIEW__LOCKING = 1;
	private static final int child_view__IMAGE_VIEW__CONTENT = 2;
	private static final int child_view__IMAGE_VIEW__NUMBERING = 3;
	private static final int child_view__IMAGE_VIEW__STYLE = 4;
	private static final int child_view__SPINNER = 5;
	protected static final int child_view__FSE_PARAGRAPH_CONTENT_VIEW = 6;

	protected FseParagraphHistoryDialog paragraphHistoryDialog;
	protected FseParagraphEditor paragraphEditor;
	private FseParagraphSpinner spinner;
	protected FseParagraphContentView contentView;
	private boolean newParagraph = false;
	private String paragraphId;
	private String initialNextParagraphId;
	private String nextParagraphId;
	private String initialPreviousParagraphId;
	private String previousParagraphId;
	protected FseParagraphStyle initialStyle;
	protected FseParagraphStyle style;
	private FseParagraphNumberingStyle numberingStyle;
	private int numberingSequence = 0;
	protected String numberingString = "";
	protected String initialNumberingString = "";
	private ParcelableSpan[] additionalFormattingSpanArray;
	protected String initialTextContent = "";  // TODO - should this be pushed down to FseParagraphViewWithTextContent ???
	protected boolean initiallyLocked;
	protected boolean isLocked;
	private NodeFragAuditBlock auditBlock;
	private FseContentModificationState contentModificationState;
	private ImageView contentModificationStateImageView;
	private FseLockModificationState lockModificationState;
	private ImageView lockModificationStateImageView;
	private FseStyleModificationState styleModificationState;
	private ImageView styleModificationStateImageView;
	private FseSequenceModificationState sequenceModificationState;
	private ImageView sequenceModificationStateImageView;
	private FseNumberingModificationState numberingModificationState;
	private ImageView numberingModificationStateImageView;
	private boolean doNotChangeModificationState = true;
	private boolean isHistory = false;

	public FseParagraphView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FseParagraphView(Context context, AttributeSet attrs, JSONObject aJsonObject) {
		super(context, attrs);
		try {
			this.paragraphId = aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_ID);
			this.previousParagraphId = aJsonObject.getString(FseDocumentSerialization.key__PREVIOUS_PARAGRAPH_ID);
			this.initialPreviousParagraphId = aJsonObject.getString(FseDocumentSerialization.key__INITIAL_PREVIOUS_PARAGRAPH_ID);
			this.nextParagraphId = aJsonObject.getString(FseDocumentSerialization.key__NEXT_PARAGRAPH_ID);
			this.initialNextParagraphId = aJsonObject.getString(FseDocumentSerialization.key__INITIAL_NEXT_PARAGRAPH_ID);
			this.style = FseParagraphStyle.getStyleForName(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_STYLE));
			this.initialStyle = FseParagraphStyle.getStyleForName(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_INITIAL_STYLE));
			this.numberingStyle = FseParagraphNumberingStyle.getStyleForString(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_STYLE));
			this.numberingSequence = aJsonObject.getInt(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_SEQUENCE);
			this.numberingString = aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_STRING);
			this.initialNumberingString = aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_INITIAL_NUMBERING_STRING);
			this.additionalFormattingSpanArray = FseDocumentSerialization.getParcelableSpanArray(aJsonObject, FseDocumentSerialization.key__PARAGRAPH_ADDITIONAL_FORMATTING);
			this.initiallyLocked = aJsonObject.getBoolean(FseDocumentSerialization.key__PARAGRAPH_INITIALLY_LOCKED);
			this.isLocked = aJsonObject.getBoolean(FseDocumentSerialization.key__PARAGRAPH_IS_LOCKED);
			this.lockModificationState = FseLockModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_LOCK_MODIFICATION_STATE));
			this.contentModificationState = FseContentModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_CONTENT_MODIFICATION_STATE));
			this.styleModificationState = FseStyleModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_STYLE_MODIFICATION_STATE));
			this.sequenceModificationState = FseSequenceModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_SEQUENCE_MODIFICATION_STATE));
			this.numberingModificationState = FseNumberingModificationState.getObjectForName(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_MODIFICATION_STATE));
			this.getContentTextView().setText(aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_TEXT_CONTENT));
			this.initialTextContent = aJsonObject.getString(FseDocumentSerialization.key__PARAGRAPH_INITIAL_TEXT_CONTENT);
			setNodeFragAuditBlock(new NodeFragAuditBlock(aJsonObject.getJSONObject(FseDocumentSerialization.key__PARAGRAPH_NODE_FRAG_AUDIT_BLOCK)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// used for instantiating from an existing FseDocument
	public void initialize(FseParagraphEditor aParagraphEditor, FseParagraph anFseParagraph, boolean bIsHistory) {
		initialize(
			aParagraphEditor,
			anFseParagraph.getParagraphId(),
			anFseParagraph.getNextParagraphId(),
			anFseParagraph.getNextParagraphId(),
			anFseParagraph.getPreviousParagraphId(),
			anFseParagraph.getPreviousParagraphId(),
			anFseParagraph.getStyle(),
			anFseParagraph.getStyle(),
			anFseParagraph.getNumberingStyle(),
			anFseParagraph.getNumberingSequence(),
			anFseParagraph.getNumberingString(),
			anFseParagraph.getNumberingString(),
			anFseParagraph.getTextContent(),
			anFseParagraph.isLocked(),
			anFseParagraph.isLocked(),
			anFseParagraph.getSequenceModificationState(),
			anFseParagraph.getLockModificationState(),
			anFseParagraph.getContentModificationState(),
			anFseParagraph.getNumberingModificationState(),
			anFseParagraph.getStyleModificationState(),
			bIsHistory,
			anFseParagraph.getNodeFragAuditBlock().getClone() );
	}

	// used to add a new paragraph to an existing document
	public void initialize(
			FseParagraphEditor aParagraphEditorView,
			FseParagraphStyle anFseParagraphStyle,
			FseParagraphNumberingStyle aNumberingStyle ) {
		String theParagraphId = FseParagraph.generateParagraphId();
		initialize(
			aParagraphEditorView, 
			theParagraphId,
			"",
			"",
			"",
			"",
			null,
			anFseParagraphStyle,
			aNumberingStyle,
			0,
			"",
			"",
			"",
			false,
			false,
			FseSequenceModificationState.UNCHANGED,
			FseLockModificationState.UNCHANGED,
			FseContentModificationState.NEW,
			FseNumberingModificationState.UNCHANGED,
			FseStyleModificationState.UNCHANGED,
			false,
			new NodeFragAuditBlock(theParagraphId) );
		this.newParagraph = true;
	}

	// used for instantiating a new document from a template
	public void initialize(FseParagraphEditor aParagraphEditorView, FseTemplateParagraph anFseTemplateParagraph) {
		initialize(
			aParagraphEditorView,
			FseParagraph.generateParagraphId(),
			"",
			"",
			"",
			"",
			null,
			anFseTemplateParagraph.getParagraphStyle(),
			anFseTemplateParagraph.getParagraphStyle().getNumberingStyle(),
			0,
			"",
			"",
			anFseTemplateParagraph.getInitialText(),
			isLocked(),
			false,
			FseSequenceModificationState.UNCHANGED,
			FseLockModificationState.UNCHANGED,
			FseContentModificationState.UNCHANGED,
			FseNumberingModificationState.UNCHANGED,
			FseStyleModificationState.UNCHANGED,
			false,
			new NodeFragAuditBlock(this.getDocumentView().getDocumentId()) );
	}

	// the "real" constructor
	public void initialize(
			FseParagraphEditor aParagraphEditorView,
			String aParagraphId,
			String anInitialNextParagraphId,
			String aNextParagraphId,
			String anInitialPreviousParagraphId,
			String aPreviousParagraphId,
			FseParagraphStyle anFseParagraphInitialStyle,
			FseParagraphStyle anFseParagraphStyle,
			FseParagraphNumberingStyle aNumberingStyle,
			int aNumberingSequence,
			String aNumberingString,
			String anInitialNumberingString,
			String anInitialTextString,
			boolean bInitiallyLocked,
			boolean bIsLocked,
			FseSequenceModificationState aSequenceModificationState,
			FseLockModificationState aLockModificationState,
			FseContentModificationState aContentModificationState,
			FseNumberingModificationState aNumberingModificationState,
			FseStyleModificationState aStyleModificationState,
			boolean bIsHistory,
			NodeFragAuditBlock anAuditBlock ) {
		this.paragraphEditor = aParagraphEditorView;
		this.paragraphId = aParagraphId;
		this.initialNextParagraphId = anInitialNextParagraphId;
		this.nextParagraphId = aNextParagraphId;
		this.initialPreviousParagraphId = anInitialPreviousParagraphId;
		this.previousParagraphId = aPreviousParagraphId;
		this.initialStyle = anFseParagraphInitialStyle;
		this.style = anFseParagraphStyle;
		this.numberingStyle = aNumberingStyle;
		this.numberingSequence = aNumberingSequence;
		this.numberingString = aNumberingString;
		this.initialNumberingString = anInitialNumberingString;
		this.initialTextContent = anInitialTextString;
		this.initiallyLocked = bInitiallyLocked;
		this.isLocked = bIsLocked;
		this.sequenceModificationStateImageView = (ImageView) getChildAt(child_view__IMAGE_VIEW__SEQUENCE);
		this.lockModificationStateImageView = (ImageView) getChildAt(child_view__IMAGE_VIEW__LOCKING);
		this.contentModificationStateImageView = (ImageView) getChildAt(child_view__IMAGE_VIEW__CONTENT);
		this.numberingModificationStateImageView = (ImageView) getChildAt(child_view__IMAGE_VIEW__NUMBERING);
		this.styleModificationStateImageView = (ImageView) getChildAt(child_view__IMAGE_VIEW__STYLE);
		this.isHistory = bIsHistory;
		setSequenceModificationState(aSequenceModificationState);
		setLockModificationState(aLockModificationState);
		setContentModificationState(aContentModificationState);
		setNumberingModificationState(aNumberingModificationState);
		setStyleModificationState(aStyleModificationState);
		this.auditBlock = anAuditBlock;
		this.spinner = (FseParagraphSpinner) getChildAt(child_view__SPINNER);
		this.spinner.initializeParagraphSpinner(this);
		initializeContentView();
		this.paragraphHistoryDialog = new FseParagraphHistoryDialog(this.paragraphEditor.getGcgActivity());
	}
	
	public void showParagraphHistoryDialog() {
		getDocumentView().getGcgActivity().startDialog(this.paragraphHistoryDialog);
	}
	
	public void dismissParagraphHistoryDialog() {
		this.paragraphHistoryDialog.dismiss();
	}

	abstract protected void initializeContentView();
	
	public String getParagraphId() {
		return this.paragraphId;
	}

	public FseParagraphNumberingStyle getNumberingStyle() {
		return this.numberingStyle;
	}

	public String getNumberingString() {
		return this.numberingString;
	}
	
	public int getNumberingStringLength() {
		return this.numberingString == null ? 0 : this.numberingString.length();
	}

	public int getNumberingSequence() {
		return this.numberingSequence;
	}

	public void setNumberingSequence(int aNumberingSequence) {
		this.numberingSequence = aNumberingSequence;
		this.numberingString = this.getNumberingStyle().getNumberingString(this.numberingSequence);
		updateNumberingModificationState();
	}

	public FseParagraphContentTextView getContentTextView() {
		return isTextContent() ? (FseParagraphContentTextView) this.contentView : null;
	}
	
	public FseParagraphContentImageView getContentImageView() {
		return isImageContent() ? (FseParagraphContentImageView)this.contentView : null;
	}
	
	public FseParagraphContentDrawingPadView getContentDrawingView() {
		return isDrawingPadContent() ? (FseParagraphContentDrawingPadView)this.contentView : null;
	}

	public ParcelableSpan[] getStyleFormattingSpanArray() {
		return this.style.getFormattingArray();
	}

	public ParcelableSpan[] getAdditionalFormattingSpanArray() {
		return this.additionalFormattingSpanArray;
	}
	
	public void setAdditionalFormattingSpanArray(ParcelableSpan[] aParcelableSpanArray) {
		this.additionalFormattingSpanArray = aParcelableSpanArray;
	}
	
	public FseParagraphStyle getInitialStyle() {
		return this.initialStyle;
	}

	public FseParagraphStyle getStyle() {
		return this.style;
	}
	
	public FseParagraphNumberingStyle getNetNumberingStyle() {
		if(this.numberingStyle.equals(FseParagraphNumberingStyle.DEFAULT)) {
			return this.style.getNumberingStyle();
		}
		return this.numberingStyle;
	}
	
	public FseParagraphNumberingStyle getRawNumberingStyle() {
		return this.numberingStyle;
	}

	public void setNumberingStyle(FseParagraphNumberingStyle aNumberingStyle) {
			setNumberingStyle(aNumberingStyle, this.numberingSequence);
	}

	public void setNumberingStyle(FseParagraphNumberingStyle aNumberingStyle, int aNumberingSeqence) {
		this.numberingStyle = aNumberingStyle;
		if(aNumberingStyle == FseParagraphNumberingStyle.NONE) {
			setNumberingSequence(0);
		} else {
			setNumberingSequence(aNumberingSeqence);
		}
	}
	
	public boolean isNumbered() {
		if(this.numberingStyle.equals(FseParagraphNumberingStyle.DEFAULT)) {
			return ! this.style.getNumberingStyle().equals(FseParagraphNumberingStyle.NONE);
		}
		return ! this.numberingStyle.equals(FseParagraphNumberingStyle.NONE);
	}

	public void changeStyle(FseParagraphStyle aParagraphStyle) {
		if(aParagraphStyle == this.style) {
			return;
		}
		this.style = aParagraphStyle;
		this.getSpinner().setStyle(this.style);
		this.contentView.setPadding();
		updateStyleModificationState();
	}

	public String getInitialTextContent() {
		return this.initialTextContent;
	}

	public String getInitialNumberingString() {
		return this.initialNumberingString;
	}

	@Override
	public String toString() {
		return this.style.getName() + " : "
				+ this.contentView.toString();
	}

	public FseParagraphContentView getContentView() {
		return this.contentView;
	}
	
	public FseParagraphSpinner getSpinner() {
		return this.spinner;
	}

	public CharSequence cutTextToEnd() {
		int theParagraphTextLength = this.getContentTextView().length();
		if(theParagraphTextLength == 0) {
			return "";
		}
		int theCursorPosition = this.getContentTextView().getSelectionStart();
		if(theParagraphTextLength == theCursorPosition) {
			return "";
		}
		CharSequence theRemainingText =
				this.getContentTextView().getEditableText().subSequence(this.getContentTextView().getFirstMutablePosition(), theCursorPosition);
		CharSequence theCutText =
				this.getContentTextView().getEditableText().subSequence(theCursorPosition, theParagraphTextLength);
		this.getContentTextView().replaceText(theRemainingText.toString());
		SpannableStringBuilder theSpannableStringBuilder = new SpannableStringBuilder(theCutText);
		theSpannableStringBuilder.clearSpans();
		return theSpannableStringBuilder;
	}

	public boolean isTextContent() {
		return this.style.getContentViewClass() == FseParagraphContentTextView.class ? true : false;
	}

	public boolean isImageContent() {
		return this.style.getContentViewClass() == FseParagraphContentImageView.class ? true : false;
	}

	public boolean isDrawingPadContent() {
		return this.style.getContentViewClass() == FseParagraphContentDrawingPadView.class ? true : false;
	}

	public FseParagraphEditor getParagraphEditor() {
		return this.paragraphEditor;
	}

	public boolean isDescendentStyle(FseParagraphView aParagraphView) {
		return this.style.isDescendentStyle(aParagraphView.getStyle());
	}

	public boolean isChildStyle(FseParagraphView aParagraphView) {
		return this.style.isChildStyle(aParagraphView.getStyle());
	}

	public boolean isParentStyle(FseParagraphView aParagraphView) {
		return this.style.isParentStyle(aParagraphView.getStyle());
	}

	public boolean isPeerStyle(FseParagraphView aParagraphView) {
		return this.style.isPeerStyle(aParagraphView.getStyle());
	}

	public void styleIndent() {
		changeStyle(this.style.getIndentStyle());
	}

	public void styleOutdent() {
		changeStyle(this.style.getOutdentStyle());
	}

	public void stylePromote() {
		changeStyle(this.style.getPromotionStyle());
	}

	public void styleDemote() {
		changeStyle(this.style.getDemotionStyle());
	}

	public boolean isFirst() {
		return this == getParagraphEditor().getParagraphViewList().getFirst();
	}

	public boolean isLast() {
		return this == getParagraphEditor().getParagraphViewList().getLast();
	}

	public FsePerspectiveFlipper getDocumentView() {
		return this.paragraphEditor.getDocumentView();
	}
	
	public boolean hasEditorFocus() {
		return this.paragraphEditor.getParagraphViewFocus() == this;
	}
	
	public boolean hasSpinnerFocus() {
		return this.paragraphEditor.getParagraphViewSelectionList().contains(this);
	}
	
	////  START Audit Block  Wrapper ////
	
	@Override
	public NodeFragAuditBlock getNodeFragAuditBlock() {
		return this.auditBlock;
	}

	@Override
	public void setNodeFragAuditBlock(NodeFragAuditBlock anAuditBlock) {
		this.auditBlock = anAuditBlock;
	}
	
	//  Created  //
	
	@Override
	public String getCreatedByNodeIdString() {
		return getNodeFragAuditBlock().getCreatedByNodeIdString();
	}
	
	@Override
	public void setCreatedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setCreatedBy(aNodeIdString);
	}
	
	@Override
	public CommunityMember getCreatedByCommunityMember() {
		return getNodeFragAuditBlock().getCreatedByCommunityMember();
	}

	@Override
	public void setCreatedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setCreatedBy(aCommunityMember);
	}

	@Override
	public Date getCreatedTimestamp() {
		return getNodeFragAuditBlock().getCreatedTimestamp();
	}
	
	@Override
	public void setCreatedTimestamp(Date aCreatedTimestamp) {
		getNodeFragAuditBlock().setCreatedTimestamp(aCreatedTimestamp);
	}
	
	//  Updated  //

	@Override
	public String getLastUpdatedByNodeIdString() {
		return getNodeFragAuditBlock().getLastUpdatedByNodeIdString();
	}

	@Override
	public void setLastUpdatedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setLastUpdatedBy(aNodeIdString);
	}

	@Override
	public CommunityMember getLastUpdatedByCommunityMember() {
		return getNodeFragAuditBlock().getLastUpdatedByCommunityMember();
	}

	@Override
	public void setLastUpdatedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setLastUpdatedBy(aCommunityMember);
	}

	@Override
	public Date getLastUpdatedTimestamp() {
		return getNodeFragAuditBlock().getRowTimestamp();
	}

	public void setLastUpdatedTimestamp(Date aLastUpdate) {
		getNodeFragAuditBlock().setRowTimestamp(aLastUpdate);
	}
	
	//  Locked  //

	@Override
	public CommunityMember getLockedByCommunityMember() {
		return getNodeFragAuditBlock().getLockedByCommunityMember();
	}

	@Override
	public void setLockedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setLockedBy(aCommunityMember);
	}

	@Override
	public NodeId getLockedByNodeId() {
		return getNodeFragAuditBlock().getLockedByNodeId();
	}

	@Override
	public String getLockedByNodeIdString() {
		return getNodeFragAuditBlock().getLockedByNodeId().getNodeIdString();
	}

	@Override
	public void setLockedBy(NodeId aNodeId) {
		getNodeFragAuditBlock().setLockedBy(aNodeId);
	}
	
	@Override
	public void setLockedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setLockedBy(aNodeIdString);
	}

	@Override
	public Date getLockedTimestamp() {
		return getNodeFragAuditBlock().getLockedTimestamp();
	}
	
	@Override
	public void setLockedTimestamp(Date aLockedTimestamp) {
		getNodeFragAuditBlock().setLockedTimestamp(aLockedTimestamp);
	}

	@Override
	public void lock() {
		getNodeFragAuditBlock().lock();
		this.isLocked = true;
		updateLockModificationState();
	}
	

	////  Unlock  ////

	@Override
	public CommunityMember getUnlockedByCommunityMember() {
		return getNodeFragAuditBlock().getUnlockedByCommunityMember();
	}

	@Override
	public void setUnlockedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setUnlockedBy(aCommunityMember);
	}

	@Override
	public NodeId getUnlockedByNodeId() {
		return getNodeFragAuditBlock().getUnlockedByNodeId();
	}

	@Override
	public String getUnlockedByNodeIdString() {
		return getNodeFragAuditBlock().getUnlockedByNodeId().getNodeIdString();
	}

	@Override
	public void setUnlockedBy(NodeId aNodeId) {
		getNodeFragAuditBlock().setUnlockedBy(aNodeId);
	}
	
	@Override
	public void setUnlockedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setUnlockedBy(aNodeIdString);
	}

	@Override
	public Date getUnlockedTimestamp() {
		return getNodeFragAuditBlock().getUnlockedTimestamp();
	}
	
	@Override
	public void setUnlockedTimestamp(Date anUnlockedTimestamp) {
		getNodeFragAuditBlock().setUnlockedTimestamp(anUnlockedTimestamp);
	}

	@Override
	public void unlock() {
		// we want to know who unlocked it, and when
		getNodeFragAuditBlock().setUnlockedBy(FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString());
		getNodeFragAuditBlock().setUnlockedTimestamp(GcgDateHelper.getCurrentDateTime());
		getNodeFragAuditBlock().setIsLocked(false);
		updateLockModificationState();
		this.isLocked = false;
	}
	
	
	/////////////////
	
	@Override
	public void setIsLocked(boolean aBoolean) {
		this.isLocked = aBoolean;  //  HACK ALERT
		if(getNodeFragAuditBlock().isLocked() == aBoolean) {
			return;
		}
		if(aBoolean) {
			getNodeFragAuditBlock().lock();
		} else {
			getNodeFragAuditBlock().unlock();
		}
		updateLockModificationState();
	}

	@Override
	public boolean isLocked() {
//		return retrieveNodeFragAuditBlock().isLocked();
		return this.isLocked;
	}
	
	@Override
	public FmmLockStatus getLockStatus() {
		return getNodeFragAuditBlock().getLockStatus();
	}
	
	////  END Audit Block Wrapper ////
	
	public void enableModificationStateUpdates() {
		this.doNotChangeModificationState = false;
	}

	public boolean stillBuildingDocument() {
		return this.doNotChangeModificationState;
	}
	
	////

	private boolean stillSequencedBelowPrevious() {
		return this.paragraphEditor.stillSequencedBelowPrevious(this);
	}
	
	private boolean stillSequencedAboveNext() {
		return this.paragraphEditor.stillSequencedAboveNext(this);
	}
	
	////////////////////////////////

	public FseContentModificationState getContentModificationState() {
		return this.contentModificationState;
	}

	public boolean isContentModified() {
		boolean isModified = false;
		switch (this.style.getContentType()) {
			case TEXT:
				isModified = isTextContentModified();
				break;
			case IMAGE:
				break;
			case DRAWING:
				break;
			default:
		}
		return isModified;
	}

	public boolean isTextContentModified() {
		if(this.contentModificationState == FseContentModificationState.NEW) {
			return false;
		}
		return (isTextContent() ? ! this.initialTextContent.equals(
				((FseParagraphViewWithTextContent)this).getTextContents()) : false);
	}

	public void setContentModificationState(FseContentModificationState aModificationState) {
		if(this.contentModificationState == aModificationState) {
			return;
		}
		this.contentModificationState = aModificationState;
		this.contentModificationStateImageView.setImageResource(aModificationState.getMarkupDrawableResourceId());
	}

	public void updateContentModificationState(FseContentModificationState aModificationState) {
		if(this.contentModificationState == aModificationState) {
			return;
		}
		setContentModificationState(aModificationState);
		this.paragraphEditor.updateContentModificationStateOnInput(aModificationState);
	}
	
	public boolean isNew() {
		return getContentModificationState() == FseContentModificationState.NEW;
	}

	public void updateContentModificationState() {
		if(this.doNotChangeModificationState ||
				this.contentModificationState == FseContentModificationState.NEW ||
				getParagraphEditor().getDocumentSectionParagraphEditorPerspective().isBrowserMode() ) {
			return;
		}
		boolean theTextIsModified = isTextContentModified();
		switch(this.contentModificationState) {
			case MODIFIED:
				if(! theTextIsModified) {
					updateContentModificationState(FseContentModificationState.UNCHANGED);
				}
				break;
			case UNCHANGED:
				if(theTextIsModified) {
					updateContentModificationState(FseContentModificationState.MODIFIED);
				}
				break;
			default:
				// TODO - throw a nasty exception about bad programmers!  ;-)
		}
		getDocumentView().onDocumentStateChange();
	}

	public void resetContentModificationState() {
		if(this.contentModificationState == FseContentModificationState.UNCHANGED) {
			return;
		}
		setContentModificationState(FseContentModificationState.UNCHANGED);
	}
	
	////

	public FseLockModificationState getLockModificationState() {
		return this.lockModificationState;
	}

	public boolean isLockModified() {
		boolean isModified;
		if(this.newParagraph) {
			isModified = getNodeFragAuditBlock().isLocked();
		} else {
			isModified = getNodeFragAuditBlock().isLocked() != this.initiallyLocked;
		}
		return isModified;
	}

	public void setLockModificationState(FseLockModificationState aModificationState) {
		if(this.lockModificationState == aModificationState) {
			return;
		}
		this.lockModificationState = aModificationState;
		this.lockModificationStateImageView.setBackgroundResource(aModificationState.getMarkupDrawableResourceId());
	}

	public void updateLockModificationState(FseLockModificationState aModificationState) {
		if(this.lockModificationState == aModificationState) {
			return;
		}
		setLockModificationState(aModificationState);
		this.paragraphEditor.updateLockModificationState(aModificationState);
	}

	public void updateLockModificationState() {
		if(this.doNotChangeModificationState) {
			return;
		}
		boolean theLockIsModified = isLockModified();
		switch(this.lockModificationState) {
		case MODIFIED:
			if(! theLockIsModified) {
				updateLockModificationState(FseLockModificationState.UNCHANGED);
			}
			return;
		case UNCHANGED:
			if(theLockIsModified) {
				updateLockModificationState(FseLockModificationState.MODIFIED);
			}
			return;
		default:
			// TODO - throw a nasty exception about bad programmers!  ;-)
		}
	}

	public void resetLockModificationState() {
		if(this.lockModificationState == FseLockModificationState.UNCHANGED) {
			return;
		}
		setLockModificationState(FseLockModificationState.UNCHANGED);
	}
	
	////

	public FseStyleModificationState getStyleModificationState() {
		return this.styleModificationState;
	}

	public boolean isStyleModified() {
		boolean isModified;
		if(this.newParagraph) {
			isModified = false;
		} else {
			isModified = this.style != this.initialStyle;
		}
		return isModified;
	}

	public void setStyleModificationState(FseStyleModificationState aModificationState) {
		if(this.styleModificationState == aModificationState) {
			return;
		}
		this.styleModificationState = aModificationState;
		this.styleModificationStateImageView.setBackgroundResource(aModificationState.getMarkupDrawableResourceId());
	}

	public void updateStyleModificationState(FseStyleModificationState aModificationState) {
		setStyleModificationState(aModificationState);
		this.paragraphEditor.updateStyleModificationState(aModificationState);
	}
	
	public void updateStyleModificationState() {
		if(this.doNotChangeModificationState || this.contentModificationState == FseContentModificationState.NEW) {
			return;
		}
		boolean theStyleIsModified = isStyleModified();
		switch(this.styleModificationState) {
		case MODIFIED:
			if(! theStyleIsModified) {
				updateStyleModificationState(FseStyleModificationState.UNCHANGED);
			}
			return;
		case UNCHANGED:
			if(theStyleIsModified) {
				updateStyleModificationState(FseStyleModificationState.MODIFIED);
			}
			return;
		default:
			// TODO - throw a nasty exception about bad programmers!  ;-)
		}
	}

	public void resetStyleModificationState() {
		if(this.styleModificationState == FseStyleModificationState.UNCHANGED) {
			return;
		}
		setStyleModificationState(FseStyleModificationState.UNCHANGED);
	}
	
	////
	
	public FseSequenceModificationState getSequenceModificationState() {
		return this.sequenceModificationState;
	}

	public boolean isSequenceModified() {
		if(this.contentModificationState == FseContentModificationState.NEW) {
			return false;
		}
		return ! (this.sequenceModificationState == FseSequenceModificationState.UNCHANGED);
	}

	public void setSequenceModificationState(FseSequenceModificationState aModificationState) {
		if(this.sequenceModificationState == aModificationState) {
			return;
		}
		this.sequenceModificationState = aModificationState;
		this.sequenceModificationStateImageView.setBackgroundResource(aModificationState.getMarkupDrawableResourceId());
	}

	public void updateSequenceModificationState(FseSequenceModificationState aModificationState) {
		if(this.sequenceModificationState == aModificationState) {
			return;
		}
		setSequenceModificationState(aModificationState);
		this.paragraphEditor.updateSequenceModificationState(aModificationState);
	}
	
	public void updateSequenceModificationState() {
		if(this.doNotChangeModificationState || this.contentModificationState == FseContentModificationState.NEW) {
			return;
		}
		boolean isBelowPrevious = stillSequencedBelowPrevious();
		boolean isAboveNext = stillSequencedAboveNext();
		if(! isBelowPrevious && ! isAboveNext) {
			updateSequenceModificationState(FseSequenceModificationState.SEQUENCE_PALOOZA);
		} else if(! isAboveNext) {
			if(this.paragraphEditor.existingParagraphNextToPreviousParagraph(this)) {
				updateSequenceModificationState(FseSequenceModificationState.UNCHANGED);
			} else {
				updateSequenceModificationState(FseSequenceModificationState.MOVE_UP);
			}
		} else if(! isBelowPrevious) {
			if(this.paragraphEditor.existingParagraphNextToNextParagraph(this)) {
				updateSequenceModificationState(FseSequenceModificationState.UNCHANGED);
			} else {
				updateSequenceModificationState(FseSequenceModificationState.MOVE_DOWN);
			}
		} else {
			updateSequenceModificationState(FseSequenceModificationState.UNCHANGED);
		}
	}

	public void resetSequenceModificationState() {
		if(this.sequenceModificationState == FseSequenceModificationState.UNCHANGED) {
			return;
		}
		setSequenceModificationState(FseSequenceModificationState.UNCHANGED);
	}
	
	////
	
	public FseNumberingModificationState getNumberingModificationState() {
		return this.numberingModificationState;
	}

	public boolean isNumberingModified() {
		return (! this.initialNumberingString.equals(this.numberingString));
	}

	public void setNumberingModificationState(FseNumberingModificationState aModificationState) {
		if(this.numberingModificationState == aModificationState) {
			return;
		}
		this.numberingModificationState = aModificationState;
		this.numberingModificationStateImageView.setBackgroundColor(getResources().getColor(aModificationState.getMarkupColorResourceId()));
	}

	public void updateNumberingModificationState(FseNumberingModificationState aModificationState) {
		if(this.numberingModificationState == aModificationState) {
			return;
		}
		setNumberingModificationState(aModificationState);
		this.paragraphEditor.updateNumberingModificationState(aModificationState);
	}

	public void updateNumberingModificationState() {
		if(this.doNotChangeModificationState || this.contentModificationState == FseContentModificationState.NEW) {
			return;
		}
		boolean theNumberingIsModified = isNumberingModified();
		switch(this.numberingModificationState) {
			case MODIFIED:
				if(! theNumberingIsModified) {
					updateNumberingModificationState(FseNumberingModificationState.UNCHANGED);
				}
				break;
			case UNCHANGED:
				if(theNumberingIsModified) {
					updateNumberingModificationState(FseNumberingModificationState.MODIFIED);
				}
				break;
			default:
		}
		getDocumentView().onDocumentStateChange();
	}

	public void resetNumberingModificationState() {
		if(this.numberingModificationState == FseNumberingModificationState.UNCHANGED) {
			return;
		}
		setNumberingModificationState(FseNumberingModificationState.UNCHANGED);
	}
	
	////
	
	public void resetModificationState() {
		resetContentModificationState();
		resetLockModificationState();
		resetStyleModificationState();
		resetSequenceModificationState();
		resetNumberingModificationState();
	}

	public void setAsNewBaseline(String aPreviousParagraphNodeId) {
		this.initialTextContent = getContentTextView().getTextContents().toString();
		this.initialNumberingString = this.numberingString;
		this.initialPreviousParagraphId = this.previousParagraphId = aPreviousParagraphNodeId;
		this.initialStyle = this.style;
		this.newParagraph = false;
		resetModificationState();
	}
	
	public FseParagraph generateFseParagraph() {
		FseParagraph theParagraph = new FseParagraph(getParagraphId(), getStyle(), getNetNumberingStyle(), getContentTextView().getTextContents().toString());
		theParagraph.setNumberingSequence(getNumberingSequence());
		theParagraph.setNumberingStyle(getNetNumberingStyle());
		theParagraph.setLockModificationState(getLockModificationState());
		theParagraph.setContentModificationState(getContentModificationState());
		theParagraph.setStyleModificationState(getStyleModificationState());
		theParagraph.setSequenceModificationState(getSequenceModificationState());
		theParagraph.setNumberingModificationState(getNumberingModificationState());
		theParagraph.setCreatedBy(getCreatedByNodeIdString());
		theParagraph.setCreatedTimestamp(getCreatedTimestamp());
		theParagraph.setRowTimestamp(getLastUpdatedTimestamp());
		theParagraph.setLastUpdatedBy(getLastUpdatedByNodeIdString());
		theParagraph.setLockedTimestamp(getLockedTimestamp());
		theParagraph.setLockedBy(getLockedByNodeId());
		theParagraph.setIsLocked(isLocked());
		theParagraph.setNodeFragAuditBlock(getNodeFragAuditBlock());
		return theParagraph;
	}

	public boolean isHierarchyLevelHigher(FseParagraphView aParagraphView) {
		return this.style.isHierarchyLevelLower(aParagraphView.getStyle());
	}

	public boolean isPeerBreak(FseParagraphView aParagraphView) {
		return this.style.isPeerBreak(aParagraphView.getStyle());
	}

	public String getInitialNextParagraphId() {
		return this.initialNextParagraphId;
	}

	public void setInitialNextParagraphId(String anInitialNextParagraphId) {
		this.initialNextParagraphId = anInitialNextParagraphId;
	}

	public String getNextParagraphId() {
		return this.nextParagraphId;
	}

	public void setNextParagraphId(String aNextParagraphId) {
		this.nextParagraphId = aNextParagraphId;
	}

	public String getInitialPreviousParagraphId() {
		return this.initialPreviousParagraphId;
	}

	public void setInitialPreviousParagraphId(String anInitialPreviousParagraphId) {
		this.initialPreviousParagraphId = anInitialPreviousParagraphId;
	}

	public String getPreviousParagraphId() {
		return this.previousParagraphId;
	}

	public void setPreviousParagraphId(String aPreviousParagraphId) {
		this.previousParagraphId = aPreviousParagraphId;
	}

	public boolean isInsertModified() {
		return this.contentModificationState == FseContentModificationState.NEW;
	}
	
	public abstract void updateContents();

	public Spannable getSpannable(String aString) {
		Spannable theSpannable = new SpannableString(aString);
		for(ParcelableSpan theParcelableSpan : getStyle().getFormattingArray()) {
			theSpannable.setSpan(theParcelableSpan, 0, theSpannable.length(), getStyle().getSpanMode());
		}
		return theSpannable;
	}

	public FseParagraphStyle getDefaultNextStyle() {
		return getStyle().getDefaultNextStyle();
	}

	public FseParagraphNumberingStyle getDefaultNextNumberingStyle() {
		if(getDefaultNextStyle() == getStyle()) {
			return getNumberingStyle();
		}
		return getDefaultNextStyle().getNumberingStyle();
	}

	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_ID, getParagraphId());
			theJsonObject.put(FseDocumentSerialization.key__PREVIOUS_PARAGRAPH_ID, getPreviousParagraphId());
			theJsonObject.put(FseDocumentSerialization.key__INITIAL_PREVIOUS_PARAGRAPH_ID, getInitialPreviousParagraphId());
			theJsonObject.put(FseDocumentSerialization.key__NEXT_PARAGRAPH_ID, getNextParagraphId());
			theJsonObject.put(FseDocumentSerialization.key__INITIAL_NEXT_PARAGRAPH_ID, getInitialNextParagraphId());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_INITIAL_STYLE, this.initialStyle.getName());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_STYLE, this.style.getName());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_STYLE, this.numberingStyle.toString());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_SEQUENCE, this.numberingSequence);
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_STRING, this.numberingString);
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_INITIAL_NUMBERING_STRING, this.initialNumberingString);
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_INITIALLY_LOCKED, this.initiallyLocked);
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_IS_LOCKED, this.isLocked);
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_TEXT_CONTENT, getContentTextView().getText());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_INITIAL_TEXT_CONTENT, this.initialTextContent);
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_SEQUENCE_MODIFICATION_STATE, this.sequenceModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_LOCK_MODIFICATION_STATE, this.lockModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_CONTENT_MODIFICATION_STATE, this.contentModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_NUMBERING_MODIFICATION_STATE, this.numberingModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_STYLE_MODIFICATION_STATE, this.styleModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_ADDITIONAL_FORMATTING,
					FseDocumentSerialization.createParcelableSpanArray(this.additionalFormattingSpanArray));
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_NODE_FRAG_AUDIT_BLOCK, getNodeFragAuditBlock().getJsonObject() );
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}
	
	public boolean isNewParagraph() {
		return this.newParagraph;
	}
	
	public void setAsOldParagraph() {
		this.newParagraph = false;
	}

	public void setIsHistory(boolean aBoolean) {
		this.isHistory = aBoolean;
	}
	
	public boolean isHistory() {
		return this.isHistory;
	}

    @Override
    public NodeFragAuditBlock getUpdatedNodeFragAuditBlock() {
        return null;
    }

}
