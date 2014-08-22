/* @(#)FseParagraphEditor.java
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.flywheelms.library.R;
import com.flywheelms.library.fse.enumerator.FseContentModificationState;
import com.flywheelms.library.fse.enumerator.FseInsertModificationState;
import com.flywheelms.library.fse.enumerator.FseLockModificationState;
import com.flywheelms.library.fse.enumerator.FseNumberingModificationState;
import com.flywheelms.library.fse.enumerator.FseParagraphContentType;
import com.flywheelms.library.fse.enumerator.FseParagraphNumberingStyle;
import com.flywheelms.library.fse.enumerator.FseSequenceModificationState;
import com.flywheelms.library.fse.enumerator.FseStyleModificationState;
import com.flywheelms.library.fse.interfaces.FseParagraphContentView;
import com.flywheelms.library.fse.model.FseDocumentSectionParagraphEditorContent;
import com.flywheelms.library.fse.model.FseParagraph;
import com.flywheelms.library.fse.perspective_flipper.FsePerspectiveFlipper;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionParagraphEditorPerspective;
import com.flywheelms.library.fse.widget.GcgClickState;
import com.flywheelms.library.gcg.GcgActivity;
import com.flywheelms.library.gcg.button.multi_shift.GcgMultiShiftState;
import com.flywheelms.library.gcg.interfaces.GcgMultiShiftStateListener;

public class FseParagraphEditor extends LinearLayout 
		implements GcgMultiShiftStateListener {

	private FseDocumentSectionParagraphEditorPerspective documentSectionParagraphEditorPerspective; 
	private LinkedList<FseParagraphView> paragraphViewList = new LinkedList<FseParagraphView>();
	private LinkedList<FseParagraphView> paragraphViewSelectionList = new LinkedList<FseParagraphView>();
	private FseParagraphView paragraphViewFocus;
	private int lastVerticalScrollPosition = -99;
	private boolean verticalScrollPositionAtEndOfLine = false;
	private String originalInitialParagraphFocusId;
	private String initialParagraphFocusId;
	private int originalInitialParagraphFocusCursorPosition;
	private int initialParagraphFocusCursorPosition;
	private GcgMultiShiftState paragraphSpinnerSelectionMode = GcgMultiShiftState.OFF;
	private ArrayList<GcgMultiShiftState> supportedParagraphSpinnerSelectionModeArray = new ArrayList<GcgMultiShiftState>(Arrays.asList(
			GcgMultiShiftState.OFF,
			GcgMultiShiftState.CTL,
			GcgMultiShiftState.SHIFT ) );
	private boolean isHistory = false;

	public FseParagraphEditor(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}
	
	public void initialize(FseDocumentSectionParagraphEditorPerspective aDocumentSectionParagraphEditorPerspective) {
		this.documentSectionParagraphEditorPerspective = aDocumentSectionParagraphEditorPerspective;
	}

	public void viewDocumentSectionAsHistory(FseDocumentSectionParagraphEditorContent aDocumentSectionParagraphEditorContent) {
		this.isHistory = true;
		viewDocumentSection(aDocumentSectionParagraphEditorContent);
	}

	public void viewDocumentSection(FseDocumentSectionParagraphEditorContent aDocumentSectionParagraphEditorContent) {
		removeAllViewsInLayout();
		this.paragraphViewList.clear();
		for(FseParagraph theFseParagraph : aDocumentSectionParagraphEditorContent.getParagraphList().values()) {
			FseParagraphView theParagraphView = newFseParagraphView(theFseParagraph, this.isHistory);
			synchronizedParagraphAddLast(theParagraphView);
			theParagraphView.getSpinner().setLostFocus();
		}
		this.documentSectionParagraphEditorPerspective.enableModificationStateUpdates(true);
		this.documentSectionParagraphEditorPerspective.setLockModificationState(aDocumentSectionParagraphEditorContent.getLockModificationState());
		this.documentSectionParagraphEditorPerspective.setContentModificationState(aDocumentSectionParagraphEditorContent.getContentModificationState());
		this.documentSectionParagraphEditorPerspective.setInsertModificationState(aDocumentSectionParagraphEditorContent.getInsertModificationState());
		this.documentSectionParagraphEditorPerspective.setStyleModificationState(aDocumentSectionParagraphEditorContent.getStyleModificationState());
		this.originalInitialParagraphFocusId = this.initialParagraphFocusId = aDocumentSectionParagraphEditorContent.getInitialParagraphFocusId();
		this.paragraphViewFocus = getParagraphForId(this.initialParagraphFocusId);
		this.originalInitialParagraphFocusCursorPosition = this.initialParagraphFocusCursorPosition = aDocumentSectionParagraphEditorContent.getInitialParagraphFocusCursorPosition();
		this.lastVerticalScrollPosition = this.initialParagraphFocusCursorPosition;
//		resetFocus();
	}
	
	private FseParagraphView getParagraphForId(String aParagraphId) {
		for(FseParagraphView theParagraphView : this.paragraphViewList) {
			if(theParagraphView.getParagraphId().equals(aParagraphId)) {
				return theParagraphView;
			}
		}
		return null;
	}

	public String getOriginalInitialParagraphFocusId() {
		return this.originalInitialParagraphFocusId;
	}

	public int getOriginalInitialParagraphFocusCursorPosition() {
		return this.originalInitialParagraphFocusCursorPosition;
	}

	public void updateInitialFocus() {
		if(this.paragraphViewFocus == null) {
			return;
		}
		this.initialParagraphFocusId = this.paragraphViewFocus.getParagraphId();
		this.initialParagraphFocusCursorPosition = this.paragraphViewFocus.getContentTextView().getSelectionStart(); 
	}

	public LinkedList<FseParagraphView> getParagraphViewList() {
		return this.paragraphViewList;
	}

	public void setParagraphViewList(LinkedList<FseParagraphView> aParagraphViewList) {
		this.paragraphViewList = aParagraphViewList;
	}

	public String getInitialParagraphFocusId() {
		return this.initialParagraphFocusId;
	}

	public void setInitialParagraphFocusId(String anInitialParagraphFocusId) {
		this.initialParagraphFocusId = anInitialParagraphFocusId;
	}

	public int getInitialParagraphFocusCursorPosition() {
		return this.initialParagraphFocusCursorPosition;
	}

	public void setInitialParagraphFocusCursorPosition(int aninitialParagraphFocusCursorPosition) {
		this.initialParagraphFocusCursorPosition = aninitialParagraphFocusCursorPosition;
	}

	public LinkedList<FseParagraphView> getParagraphViewSelectionList() {
		return this.paragraphViewSelectionList;
	}

	public void setParagraphViewSelectionList(LinkedList<FseParagraphView> aParagraphViewSelectionList) {
		this.paragraphViewSelectionList = aParagraphViewSelectionList;
	}

	public FseParagraphView getParagraphViewFocus() {
		return this.paragraphViewFocus;
	}

	public void setParagraphViewFocus(FseParagraphView aParagraphViewFocus) {
		this.paragraphViewFocus = aParagraphViewFocus;
	}

	public int getLastVerticalScrollPosition() {
		return this.lastVerticalScrollPosition;
	}

	public void setLastVerticalScrollPosition(int aPosition) {
		this.lastVerticalScrollPosition = aPosition;
	}

	public boolean isVerticalScrollPositionAtEndOfLine() {
		return this.verticalScrollPositionAtEndOfLine;
	}

	public void setVerticalScrollPositionAtEndOfLine(boolean aBoolean) {
		this.verticalScrollPositionAtEndOfLine = aBoolean;
	}

	public void manageVerticalScrollPosition(FseParagraphView aNewParagraphFocus) {
		int theVerticalScrollPosition = 0;
		if((aNewParagraphFocus.isTextContent())) {
			if(this.verticalScrollPositionAtEndOfLine) {
				theVerticalScrollPosition =
						aNewParagraphFocus.getContentTextView().length();
			} else {
				if(this.lastVerticalScrollPosition < 0) { // document entry
					this.lastVerticalScrollPosition = aNewParagraphFocus.getContentTextView().getSelectionStart() -
							aNewParagraphFocus.getContentTextView().getText().length();
					this.verticalScrollPositionAtEndOfLine =
							this.lastVerticalScrollPosition == aNewParagraphFocus.getContentTextView().getText().length() ?
									true : false;
				} else {
					theVerticalScrollPosition =
							this.lastVerticalScrollPosition <= aNewParagraphFocus.getContentTextView().length() ?
									this.lastVerticalScrollPosition :
										aNewParagraphFocus.getContentTextView().length();
				}
			}
			int theAdjustedScrollPosition = theVerticalScrollPosition + aNewParagraphFocus.getNumberingStringLength();
			aNewParagraphFocus.getContentTextView().setSelection(
					theAdjustedScrollPosition > aNewParagraphFocus.getContentTextView().getText().length() ?
							aNewParagraphFocus.getContentTextView().getText().length() : theAdjustedScrollPosition );
		}
	}

	public void setLastVerticalScrollPosition(FseParagraphContentTextView anFseParagraphContentTextView) {
		if(anFseParagraphContentTextView.getSelectionStart() != anFseParagraphContentTextView.getInitialSelectionStart()) {
			this.lastVerticalScrollPosition = anFseParagraphContentTextView.getSelectionStart() - anFseParagraphContentTextView.getParagraphView().getNumberingStringLength();
			this.verticalScrollPositionAtEndOfLine =
					this.lastVerticalScrollPosition == anFseParagraphContentTextView.getText().length() ?
							true : false;
		}
	}

	//////////////////////////////////////////////////////////////

	private FseParagraphView newFseParagraphView(FseParagraph anFseParagraph, boolean bIsHistory) {
		FseParagraphView theFseParagraphView = null;
		switch (anFseParagraph.getStyle().getContentType()) {
			case DRAWING:
				// BOGUS CODE - see TEXT implementation
				break;
			case IMAGE:
				// BOGUS CODE - see TEXT implementation
				break;
			case TEXT:
				theFseParagraphView = newFseParagraphViewWithContentText(anFseParagraph, bIsHistory);
				break;
			default:
				// TODO - throw a nasty exception about bad programmers!  ;-)
		}
		return theFseParagraphView;
	}

	// create view from a plain paragraph
	private FseParagraphViewWithTextContent newFseParagraphViewWithContentText(FseParagraph anFseParagraph, boolean bIsHistory) {
		FseParagraphViewWithTextContent theFseParagraphView;
		theFseParagraphView = (FseParagraphViewWithTextContent)LayoutInflater.from(getContext()).inflate(R.layout.fse__paragraph__content_text, null);
		theFseParagraphView.initialize(this, anFseParagraph, bIsHistory);
		return theFseParagraphView;
	}

	// create view to add after an existing paragraph view
	private FseParagraphViewWithTextContent newFseParagraphViewWithContentText(FseParagraphView aParagraphView) {
		FseParagraphViewWithTextContent theFseParagraphView;
		theFseParagraphView = (FseParagraphViewWithTextContent) LayoutInflater.from(getContext()).inflate(R.layout.fse__paragraph__content_text, null);
		theFseParagraphView.initialize(this, aParagraphView.getDefaultNextStyle(), aParagraphView.getDefaultNextNumberingStyle());
		return theFseParagraphView;
	}

	private void synchronizedParagraphAdd(FseParagraphView aParagraphView, int aPosition) {
		this.paragraphViewList.add(aPosition, aParagraphView);
		this.addView(aParagraphView, aPosition);
	}

//	private void synchronizedParagraphAddFirst(FseParagraphView aParagraphView) {
//		this.paragraphViewList.addFirst(aParagraphView);
//		this.addView(aParagraphView, 0);
//	}

	private void synchronizedParagraphAddLast(FseParagraphView aParagraphView) {
		this.paragraphViewList.addLast(aParagraphView);
		this.addView(aParagraphView);
	}

	private void synchronizedParagraphMoveAfter(FseParagraphView aParagraphView, FseParagraphView aReferenceParagraphView) {
		boolean manualFocusManagement = hasFocus(aParagraphView);
		if(manualFocusManagement) {
			setFocus(aReferenceParagraphView);
		}
		synchronizedParagraphTemporaryDelete(aParagraphView);
		int theNewInsertionPoint = this.paragraphViewList.indexOf(aReferenceParagraphView) + 1;
		synchronizedParagraphAdd(aParagraphView, theNewInsertionPoint);
		if(manualFocusManagement) {
			setFocus(aParagraphView);
		}
	}

	private void synchronizedParagraphMoveBefore(FseParagraphView aParagraphView, FseParagraphView aReferenceParagraphView) {
		boolean manualFocusManagement = hasFocus(aParagraphView);
		if(manualFocusManagement) {
			setFocus(aReferenceParagraphView);
		}
		synchronizedParagraphTemporaryDelete(aParagraphView);
		int theNewInsertionPoint = this.paragraphViewList.indexOf(aReferenceParagraphView);
		synchronizedParagraphAdd(aParagraphView, theNewInsertionPoint);
		if(manualFocusManagement) {
			setFocus(aParagraphView);
		}
	}

	private void synchronizedParagraphDelete(FseParagraphView aParagraphToDelete) {
		this.paragraphViewList.remove(aParagraphToDelete);
		this.paragraphViewSelectionList.remove(aParagraphToDelete);
		this.removeView(aParagraphToDelete);
	}

	private void synchronizedParagraphTemporaryDelete(FseParagraphView aParagraphToDelete) {
		this.paragraphViewList.remove(aParagraphToDelete);
		this.removeView(aParagraphToDelete);
	}

	public void addParagraph(FseParagraphView aParagraphView) {
		addParagraphToBottom(aParagraphView);
	}

	public void addParagraphToTop(FseParagraphView aParagraphView) {
		synchronizedParagraphAdd(aParagraphView, 0);
	}

	public void addParagraphToBottom(FseParagraphView aParagraphView) {
		synchronizedParagraphAdd(aParagraphView, this.paragraphViewList.size());
	}

	@SuppressWarnings("incomplete-switch")
	public void createParagraphOnKeycodeEnter(FseParagraphView aParagraphView) {
		FseParagraphContentType theContentType = aParagraphView.getStyle().getDefaultNextStyle().getContentType();
		FseParagraphView theNewParagraphView = null;
		switch (theContentType) {
			case DRAWING:
				break;
			case IMAGE:
				break;
			case TEXT:
				if(aParagraphView.isLocked() && aParagraphView.getContentTextView().getSelectionStart() != aParagraphView.getContentTextView().length()) {
					return;
				}
				theNewParagraphView = newFseParagraphViewWithContentText(aParagraphView);
				int theNewLocation = 0;
				if(aParagraphView.getContentTextView().cursorAtBeginning() && aParagraphView.getContentTextView().getTextContents().length() != 0) {
					theNewLocation = this.paragraphViewList.indexOf(aParagraphView);
				} else {
					String theTextToMove = aParagraphView.cutTextToEnd().toString();
					((FseParagraphViewWithTextContent) theNewParagraphView).setInitialNumberingStringAndText("", theTextToMove);
					theNewLocation = this.paragraphViewList.indexOf(aParagraphView) + 1;
				}
				synchronizedParagraphAdd(theNewParagraphView, theNewLocation);
//				theNewParagraphView.getContentView().setSelectionAtEnd();
				renumberPeerParagraphs(theNewParagraphView);
				onParagraphSpinnerTouch(theNewParagraphView);
		}
//		renumberPeerParagraphs(theNewParagraphView);
		updateInsertModificationState();
	}

	public void deleteParagraphOnKeycodeDelete(FseParagraphView anFseParagraphViewToDelete) {
		if(this.paragraphViewList.getFirst() == anFseParagraphViewToDelete) {
			return;  // do nothing if first paragraph
		}
		int theIndexForParagraphToDelete = this.paragraphViewList.indexOf(anFseParagraphViewToDelete);
		FseParagraphView thePreviousParagraph = this.paragraphViewList.get(theIndexForParagraphToDelete-1);
		if(thePreviousParagraph.getContentTextView().getTextContents().length() == 0) {
			synchronizedParagraphDelete(thePreviousParagraph);
		} else {
			String theTextToBringBack = anFseParagraphViewToDelete.getContentTextView().getTextContents();
			synchronizedParagraphDelete(anFseParagraphViewToDelete);
			thePreviousParagraph.getContentTextView().requestFocus();
			int theSelectionStartPosition = thePreviousParagraph.getContentTextView().getText().length();
			thePreviousParagraph.getContentTextView().append(theTextToBringBack);
			thePreviousParagraph.getContentTextView().setSelection(theSelectionStartPosition);
		}
		updateContentModificationState();
		updateInsertModificationState();
		updateSequenceModificationState();
		updateNumberingModificationState();
		getDocumentView().onDocumentStateChange();
	}

	public void mergeParagraphOnKeycodeForwardDelete(FseParagraphView aBaseParagraphView) {
		if(this.paragraphViewList.getLast() == aBaseParagraphView) {
			return;  // do nothing if last paragraph
		}
		int theIndexForParagraphToMerge = this.paragraphViewList.indexOf(aBaseParagraphView) + 1;
		FseParagraphView theNextParagraphView = this.paragraphViewList.get(theIndexForParagraphToMerge);
		String theTextToBringBack = theNextParagraphView.getContentTextView().getText().toString();
		synchronizedParagraphDelete(theNextParagraphView);
		int theSelectionStartPosition = aBaseParagraphView.getContentTextView().getText().length();
		aBaseParagraphView.getContentTextView().append(theTextToBringBack);
		aBaseParagraphView.getContentTextView().setSelection(theSelectionStartPosition);
		updateContentModificationState();
		updateInsertModificationState();
		updateSequenceModificationState();
		updateNumberingModificationState();
	}

	public void focusNextParagraphMaintainingVerticalScrollPosition(FseParagraphView aParagraphView) {
		int theParagraphIndex = this.paragraphViewList.indexOf(aParagraphView);
		if(theParagraphIndex != this.paragraphViewList.size() -1) {
			focusParagraphContentMaintainingVerticalScrollPosition(this.paragraphViewList.get(theParagraphIndex + 1));
		}
	}

	public void focusPreviousParagraphMaintainingVerticalScrollPosition(FseParagraphView aParagraphView) {
		int theParagraphIndex = this.paragraphViewList.indexOf(aParagraphView);
		if(theParagraphIndex != 0) {
			focusParagraphContentMaintainingVerticalScrollPosition(this.paragraphViewList.get(theParagraphIndex - 1));
		}
	}

	public void focusNextParagraphAtBeginning(FseParagraphView aParagraphView) {
		int theParagraphIndex = this.paragraphViewList.indexOf(aParagraphView);
		if(theParagraphIndex != this.paragraphViewList.size() -1) {
			focusParagraphContentWithSelectionAtBeginning(this.paragraphViewList.get(theParagraphIndex + 1));
		}
	}

	public void focusPreviousParagraphAtEnd(FseParagraphView aParagraphView) {
		int theParagraphIndex = this.paragraphViewList.indexOf(aParagraphView);
		if(theParagraphIndex != 0) {
			focusParagraphContentWithSelectionAtEnd(this.paragraphViewList.get(theParagraphIndex - 1));
		}
	}

	private void focusParagraphContentWithSelectionAtEnd(FseParagraphView aParagraphView) {
		moveParagraphFocus(aParagraphView);
		aParagraphView.getContentTextView().setSelectionAtEnd();
	}

	private void focusParagraphContentWithSelectionAtBeginning(FseParagraphView aParagraphView) {
		moveParagraphFocus(aParagraphView);
		aParagraphView.getContentTextView().setSelectionAtBeginning();
	}

	private void focusParagraphContentMaintainingVerticalScrollPosition(FseParagraphView aParagraphView) {
		moveParagraphFocus(aParagraphView);
	}

	public void resetParagraphViewSelectionList(FseParagraphView aParagraphView) {
		this.paragraphViewSelectionList.clear();
		this.paragraphViewSelectionList.add(aParagraphView);
		setParagraphViewFocus(aParagraphView);
	}

	public void resetParagraphViewSelectionList(LinkedList<FseParagraphView> aParagraphViewList) {
		this.paragraphViewSelectionList = aParagraphViewList;
		// leave paragraphFocus where it is, but need to verify it is in the list, else no focus ???
	}

	public LinkedList<FseParagraphView> getParagraphViewDescendentList(FseParagraphView aParagraphView) {
		LinkedList<FseParagraphView> theParagraphViewList = new LinkedList<FseParagraphView>();
		int theStartingIndex = this.paragraphViewList.indexOf(aParagraphView) + 1;
		for(FseParagraphView theParagraphView : this.paragraphViewList.subList(theStartingIndex, this.paragraphViewList.size())) {
			if(theParagraphView.isDescendentStyle(aParagraphView)) {
				theParagraphViewList.add(theParagraphView);
			} else {
				break;
			}
		}
		return theParagraphViewList;
	}

	public ArrayList<FseParagraphView> getParagraphViewChildList(FseParagraphView aParagraphView) {
		ArrayList<FseParagraphView> theParagraphViewList = new ArrayList<FseParagraphView>();
		int theStartingIndex = this.paragraphViewList.indexOf(aParagraphView) + 1;
		for(FseParagraphView theParagraphView : this.paragraphViewList.subList(theStartingIndex, this.paragraphViewList.size())) {
			if(theParagraphView.isChildStyle(aParagraphView)) {
				theParagraphViewList.add(theParagraphView);
			} else {
				break;
			}
		}
		return theParagraphViewList;
	}

	public ArrayList<FseParagraphView> getParagraphViewSubsequentContiguousPeerList(FseParagraphView aParagraphView) {
		ArrayList<FseParagraphView> theParagraphViewList = new ArrayList<FseParagraphView>();
		int theStartingIndex = this.paragraphViewList.indexOf(aParagraphView) + 1;
		for(FseParagraphView theParagraphView : this.paragraphViewList.subList(theStartingIndex, this.paragraphViewList.size())) {
			if(theParagraphView.isPeerStyle(aParagraphView)) {
				theParagraphViewList.add(theParagraphView);
			} else {
				break;
			}
		}
		return theParagraphViewList;
	}

	public ArrayList<FseParagraphView> getParagraphViewSubsequentPeerList(FseParagraphView aParagraphView) {
		ArrayList<FseParagraphView> theParagraphViewList = new ArrayList<FseParagraphView>();
		int theStartingIndex = this.paragraphViewList.indexOf(aParagraphView) + 1;
		for(FseParagraphView theParagraphView : this.paragraphViewList.subList(theStartingIndex, this.paragraphViewList.size())) {
			if(theParagraphView.getStyle() == aParagraphView.getStyle()) {
				theParagraphViewList.add(theParagraphView);
			} else if(aParagraphView.isPeerBreak(theParagraphView)) {
				break;
			}
		}
		return theParagraphViewList;
	}
	
	public ArrayList<FseParagraphView> getParagraphViewPeerList(FseParagraphView aParagraphView) {
		return getParagraphViewPeerList(aParagraphView, true);
	}

	public ArrayList<FseParagraphView> getParagraphViewPeerList(FseParagraphView aParagraphView, boolean bAddTarget) {
		ArrayList<FseParagraphView> theParagraphViewList = new ArrayList<FseParagraphView>();
		ArrayList<FseParagraphView> theReversedArrayList = new ArrayList<FseParagraphView>(this.paragraphViewList);
		Collections.reverse(theReversedArrayList);
		int theStartingIndex = theReversedArrayList.indexOf(aParagraphView) + 1;
		for(FseParagraphView theParagraphView : theReversedArrayList.subList(theStartingIndex, this.paragraphViewList.size())) {
			if(theParagraphView.getStyle() == aParagraphView.getStyle()) {
				theParagraphViewList.add(theParagraphView);
			} else if(aParagraphView.isPeerBreak(theParagraphView)) {
				break;
			}
		}
		Collections.reverse(theParagraphViewList);
		if(bAddTarget) {
			theParagraphViewList.add(aParagraphView);
		}
		theParagraphViewList.addAll(getParagraphViewSubsequentPeerList(aParagraphView));
		return theParagraphViewList;
	}

	public void addParagraphToSelectionList(FseParagraphView aParagraphView) {
		this.paragraphViewSelectionList.add(aParagraphView);
	}

	public void removeParagraphFromSelectionList(FseParagraphView aParagraphView) {
		this.paragraphViewSelectionList.remove(aParagraphView);
	}

	private List<FseParagraphView> getParagraphSelectionList() {
		return this.paragraphViewSelectionList;
	}

	public void moveParagraphFocus(FseParagraphView aParagraphView) {
		aParagraphView.getContentView().setFocus();
		onParagraphContentTouch(aParagraphView);
	}

	public void onParagraphContentTouch(FseParagraphView aParagraphView) {
		switch(aParagraphView.getStyle().getContentType()) {
			case DRAWING:
				break;
			case IMAGE:
				break;
			case TEXT:
				onParagraphTextTouch(aParagraphView);
				break;
			default:
				// TODO - throw a nasty exception about bad programmers!  ;-)
		}
		this.documentSectionParagraphEditorPerspective.resetMultiShiftControllerState();
		resetParagraphViewSelectionList(aParagraphView);
	}

	public void onParagraphTextTouch(FseParagraphView aParagraphView) {
		if(aParagraphView == this.paragraphViewFocus && this.paragraphViewSelectionList.size() == 1) {
			return;
		}
//		setParagraphViewFocus(aParagraphView);
		setParagraphSpinnerFocusLost();
		setParagraphSpinnerFocus(aParagraphView);
		resetParagraphViewSelectionList(aParagraphView);
	}

	public void onParagraphSpinnerTouch(FseParagraphView aParagraphView) {
		onParagraphSpinnerTouch(aParagraphView, GcgClickState.SINGLE);
	}

	public void onParagraphSpinnerTouch(FseParagraphView aParagraphView, GcgClickState aClickState) {
		if(this.documentSectionParagraphEditorPerspective.isMultiShiftControllerParagraphSelectionMode()) {
			updateParagraphViewSelectionList(aParagraphView);
		} else {
			FseParagraphContentView theParagraphContentView = aParagraphView.getContentView();
			switch (aClickState) {
			case TRIPLE:
				theParagraphContentView.selectAllContents();
				break;
			case DOUBLE:
				theParagraphContentView.setSelectionAtBeginning();
				break;
			default:  // SINGLE
//						if(! this.documentSectionParagraphEditorView.getMultiShiftController().isItemSelectionState()) {
//							resetParagraphViewSelectionList(aParagraphView);
//							setParagraphSpinnerFocusLost();
//							setParagraphSpinnerFocus(aParagraphView);
//						}
				aParagraphView.getSpinner().setFocus();
				theParagraphContentView.setFocus();
				theParagraphContentView.setSelectionAtEnd();
				setParagraphViewFocus(aParagraphView);
			}
		}
	}

	private void updateParagraphViewSelectionList(FseParagraphView aParagraphView) {
		aParagraphView.getSpinner().setFocus();
		switch (this.documentSectionParagraphEditorPerspective.getMultiShiftController().getMultiShiftState()) {
			case CTL:
				updateParagraphViewSelectionListForCtlTouch(aParagraphView);
				break;
			case SHIFT:
				updateParagraphViewSelectionListForShiftTouch(aParagraphView);
				break;
			default:
		}
	}

	private void updateParagraphViewSelectionListForCtlTouch(FseParagraphView aParagraphView) {
		if(this.paragraphViewSelectionList.contains(aParagraphView)) {
			aParagraphView.getSpinner().setLostFocus();
			this.paragraphViewSelectionList.remove(aParagraphView);
		} else {
			aParagraphView.getSpinner().setFocus();
			synchronizedParagraphViewSelectionListAdd(aParagraphView);
		}
	}

	private void updateParagraphViewSelectionListForShiftTouch(FseParagraphView aParagraphView) {
		if(this.paragraphViewSelectionList.contains(aParagraphView)) {
			aParagraphView.getSpinner().setLostFocus();
			this.paragraphViewSelectionList.remove(aParagraphView);
		} else {
			aParagraphView.getSpinner().setFocus();
			synchronizedParagraphViewSelectionListAdd(aParagraphView);
				for(FseParagraphView theParagraphView : getParagraphViewShiftGroupList(aParagraphView)) {
					theParagraphView.getSpinner().setFocus();
					synchronizedParagraphViewSelectionListAdd(theParagraphView);
				}
		}
	}

	private ArrayList<FseParagraphView> getParagraphViewShiftGroupList(FseParagraphView anEndingParagraphView) {
		/**
		 * Get all contiguous previous items in the list that do not have focus,
		 * up to, but not including the next previous item with focus.
		 * If no previous items have focus, then this list is empty.
		 */
		ArrayList<FseParagraphView> theViewShiftGroupList = new ArrayList<FseParagraphView>();
		int theEndingParagraphIndex = this.paragraphViewList.indexOf(anEndingParagraphView);
		int theBeginningParagraphIndex = this.paragraphViewList.indexOf(this.paragraphViewFocus);
		@SuppressWarnings("unchecked")
		List<FseParagraphView> theParagraphViewArrayListClone = (List<FseParagraphView>) this.paragraphViewList.clone();
		List<FseParagraphView> theParagraphViewArraySubList = null;
		if(theEndingParagraphIndex > theBeginningParagraphIndex) {
			if(theEndingParagraphIndex == 0) {
				return theViewShiftGroupList;
			}
			theParagraphViewArraySubList =
					theParagraphViewArrayListClone.subList(0, theEndingParagraphIndex);
			Collections.reverse(theParagraphViewArraySubList);
		} else {
			if(theEndingParagraphIndex == this.paragraphViewList.size() - 1) {
				return theViewShiftGroupList;
			}
			theParagraphViewArraySubList =
					theParagraphViewArrayListClone.subList(theEndingParagraphIndex + 1, theParagraphViewArrayListClone.size());
		}
		for(FseParagraphView theParagraphView : theParagraphViewArraySubList) {
			if(theParagraphView.hasSpinnerFocus()) {
				break;
			}
			theViewShiftGroupList.add(theParagraphView);
		}
		if(theViewShiftGroupList.size() == theParagraphViewArraySubList.size()) {
			theViewShiftGroupList.clear();
		}
		return theViewShiftGroupList;
	}

	private void synchronizedParagraphViewSelectionListAdd(FseParagraphView aParagraphView) {
		if(this.paragraphViewSelectionList.size() == 0) {
			this.paragraphViewSelectionList.add(aParagraphView);
			return;
		}
		for(FseParagraphView theParagraphView : this.paragraphViewSelectionList) {
			if(isBeforeParagraph(aParagraphView, theParagraphView)) {
				this.paragraphViewSelectionList.add(
						this.paragraphViewSelectionList.indexOf(theParagraphView),
						aParagraphView);
				return;
			}
		}
		this.paragraphViewSelectionList.addLast(aParagraphView);
	}

	private boolean isBeforeParagraph(FseParagraphView aBeforeParagraphView,
			FseParagraphView anAfterParagraphView) {
		for(FseParagraphView theParagraphView : this.paragraphViewList) {
			if(aBeforeParagraphView == theParagraphView) {
				return true;
			}
			if(anAfterParagraphView == theParagraphView) {
				return false;
			}
		}
		return false;
	}

	public void setParagraphSpinnerFocusLost() {
		for(FseParagraphView theParagraphView : getParagraphSelectionList()) {
			theParagraphView.getSpinner().setLostFocus();
		}
	}

	public void setParagraphSpinnerFocus(FseParagraphView aParagraphView) {
		aParagraphView.getSpinner().setFocus();
	}

	public void setParagraphSpinnerFocus(LinkedList<FseParagraphView> aParagraphViewList) {
		for(FseParagraphView theParagraphView : aParagraphViewList) {
			theParagraphView.getSpinner().setFocus();
		}
	}

	public void styleIndentParagraphViewList(List<FseParagraphView> aParagraphViewList) {
		for(FseParagraphView theParagraphView : aParagraphViewList) {
			ArrayList<FseParagraphView> thePreviousPeerList = getParagraphViewPeerList(theParagraphView, false);
			theParagraphView.styleIndent();
			renumberOnParagraphStyleChange(theParagraphView, thePreviousPeerList);
		}
	}

	public void styleOutdentParagraphViewList(List<FseParagraphView> aParagraphViewList) {
		for(FseParagraphView theParagraphView : aParagraphViewList) {
			ArrayList<FseParagraphView> thePreviousPeerList = getParagraphViewPeerList(theParagraphView, false);
			theParagraphView.styleOutdent();
			renumberOnParagraphStyleChange(theParagraphView, thePreviousPeerList);
		}
	}

	public void stylePromoteParagraphViewList(List<FseParagraphView> aParagraphViewList) {
		for(FseParagraphView theParagraphView : aParagraphViewList) {
			ArrayList<FseParagraphView> thePreviousPeerList = getParagraphViewPeerList(theParagraphView, false);
			theParagraphView.stylePromote();
			renumberOnParagraphStyleChange(theParagraphView, thePreviousPeerList);
		}
	}

	public void styleDemoteParagraphViewList(List<FseParagraphView> aParagraphViewList) {
		for(FseParagraphView theParagraphView : aParagraphViewList) {
			ArrayList<FseParagraphView> thePreviousPeerList = getParagraphViewPeerList(theParagraphView, false);
			theParagraphView.styleDemote();
			renumberOnParagraphStyleChange(theParagraphView, thePreviousPeerList);
		}
	}

	public boolean canIndentList(LinkedList<FseParagraphView> aParagraphViewList) {
		for(FseParagraphView theParagraphView : aParagraphViewList) {
			if(theParagraphView.getStyle() == theParagraphView.getStyle().getIndentStyle()) {
				return false;
			}
		}
		return true;
	}

	public boolean canOutdentList(LinkedList<FseParagraphView> aParagraphViewList) {
		for(FseParagraphView theParagraphView : aParagraphViewList) {
			if(theParagraphView.getStyle() == theParagraphView.getStyle().getOutdentStyle()) {
				return false;
			}
		}
		return true;
	}

	public boolean canPromoteList(LinkedList<FseParagraphView> aParagraphViewList) {
		for(FseParagraphView theParagraphView : aParagraphViewList) {
			if(theParagraphView.getStyle().getStyleType() == theParagraphView.getStyle().getPromotionStyle().getStyleType()) {
				return false;
			}
		}
		return true;
	}

	public boolean canDemoteList(LinkedList<FseParagraphView> aParagraphViewList) {
		for(FseParagraphView theParagraphView : aParagraphViewList) {
			if(theParagraphView.getStyle().getStyleType() == theParagraphView.getStyle().getDemotionStyle().getStyleType()) {
				return false;
			}
		}
		return true;
	}

	public void lockParagraphViewList(LinkedList<FseParagraphView> aParagraphViewList) {
		if(aParagraphViewList.size() < 1) {
			return;
		}
		for(FseParagraphView theParagraphView : aParagraphViewList) {
			theParagraphView.lock();
		}
		resetParagraphViewSelectionList(aParagraphViewList);
		setParagraphSpinnerFocusLost();
		setParagraphSpinnerFocus(aParagraphViewList);
	}

	public void unlockParagraphViewList(LinkedList<FseParagraphView> aParagraphViewList) {
		if(aParagraphViewList.size() < 1) {
			return;
		}
		for(FseParagraphView theParagraphView : aParagraphViewList) {
			theParagraphView.unlock();
		}
		resetParagraphViewSelectionList(aParagraphViewList);
		setParagraphSpinnerFocusLost();
		setParagraphSpinnerFocus(aParagraphViewList);
	}

	public boolean canSequenceListUp(LinkedList<FseParagraphView> aParagraphViewList) {
		if(! isContiguousParagraphViewList(aParagraphViewList)) {
			return true;
		}
		FseParagraphView theFirstParagraphView = this.paragraphViewList.getFirst();
		for(FseParagraphView theParagraphView : aParagraphViewList) {
			if(theParagraphView == theFirstParagraphView) {
				return false;
			}
		}
		return true;
	}

	public void sequenceUpParagraphViewList(LinkedList<FseParagraphView> aParagraphViewList) {
		if(aParagraphViewList.size() < 1) {
			return;
		}
		if(isContiguousParagraphViewList(aParagraphViewList)) {
			int theIndexOfParagraphViewToMove = this.paragraphViewList.indexOf(aParagraphViewList.get(0)) - 1;
			FseParagraphView theParagraphViewToMove = this.paragraphViewList.get(theIndexOfParagraphViewToMove);
			synchronizedParagraphMoveAfter(theParagraphViewToMove, aParagraphViewList.get(aParagraphViewList.size() - 1));
		} else {
			consolidateParagraphViewListUp(aParagraphViewList);
		}
		renumberPeerParagraphs(aParagraphViewList);
	}

	public boolean canSequenceListDown(LinkedList<FseParagraphView> aParagraphViewList) {
		if(! isContiguousParagraphViewList(aParagraphViewList)) {
			return true;
		}
		FseParagraphView theLastParagraphView = this.paragraphViewList.getLast();
		for(FseParagraphView theParagraphView : aParagraphViewList) {
			if(theParagraphView == theLastParagraphView) {
				return false;
			}
		}
		return true;
	}

	public void sequenceDownParagraphViewList(LinkedList<FseParagraphView> aParagraphViewList) {
		if(aParagraphViewList.size() < 1) {
			return;
		}
		if(isContiguousParagraphViewList(aParagraphViewList)) {
			int theIndexOfParagraphViewToMove = this.paragraphViewList.indexOf(aParagraphViewList.get(aParagraphViewList.size() - 1)) + 1;
			FseParagraphView theParagraphViewToMove = this.paragraphViewList.get(theIndexOfParagraphViewToMove);
			synchronizedParagraphMoveBefore(theParagraphViewToMove, aParagraphViewList.get(0));
		} else {
			consolidateParagraphViewListDown(aParagraphViewList);
		}
		renumberPeerParagraphs(aParagraphViewList);
	}

	private void consolidateParagraphViewListDown(LinkedList<FseParagraphView> aParagraphViewList) {
		FseParagraphView thePreviousParagraphView = aParagraphViewList.getLast();
		List<FseParagraphView> theReverseList = aParagraphViewList.subList(0, aParagraphViewList.size() - 1);
		Collections.reverse(theReverseList);
		for(FseParagraphView theParagraphView : theReverseList) {
			if(! isAdjoiningPositionsFromBottom(thePreviousParagraphView, theParagraphView)) {
				synchronizedParagraphMoveBefore(theParagraphView, thePreviousParagraphView);
			}
			thePreviousParagraphView = theParagraphView;
		}
	}

	private void consolidateParagraphViewListUp(LinkedList<FseParagraphView> aParagraphViewList) {
		FseParagraphView thePreviousParagraphView = aParagraphViewList.getFirst();
		for(FseParagraphView theParagraphView : aParagraphViewList.subList(1, aParagraphViewList.size())) {
			if(! isAdjoiningPositionsFromTop(thePreviousParagraphView, theParagraphView)) {
				synchronizedParagraphMoveAfter(theParagraphView, thePreviousParagraphView);
			}
			thePreviousParagraphView = theParagraphView;
		}
	}

	private boolean isContiguousParagraphViewList(LinkedList<FseParagraphView> aParagraphViewList) {
		if(aParagraphViewList.size() < 2) {
			return true;
		}
		FseParagraphView thePreviousParagraphView = aParagraphViewList.getFirst();
		for(FseParagraphView theParagraphView : aParagraphViewList.subList(1, aParagraphViewList.size())) {
			if(! isAdjoiningPositionsFromTop(thePreviousParagraphView, theParagraphView)) {
				return false;
			}
			thePreviousParagraphView = theParagraphView;
		}
		return true;
	}

	private boolean isAdjoiningPositionsFromTop(FseParagraphView aPreviousParagraphView, FseParagraphView aNextParagraphView) {
		int thePreviousPosition = this.paragraphViewList.indexOf(aPreviousParagraphView);
		int theNextPosition = this.paragraphViewList.indexOf(aNextParagraphView);
		return thePreviousPosition + 1 == theNextPosition ? true : false;
	}

	private boolean isAdjoiningPositionsFromBottom(FseParagraphView aPreviousParagraphView, FseParagraphView aNextParagraphView) {
		int thePreviousPosition = this.paragraphViewList.indexOf(aPreviousParagraphView);
		int theNextPosition = this.paragraphViewList.indexOf(aNextParagraphView);
		return thePreviousPosition - 1 == theNextPosition ? true : false;
	}

	public void setParagraphSpinnerSelectionModeColor(GcgMultiShiftState aMultiShiftState) {
		for(FseParagraphView theParagraphView : this.paragraphViewList) {
			theParagraphView.getSpinner().setTextColorResourceId(aMultiShiftState.getTextColorResourceId());
		}
	}

	public void resetParagraphSpinnerSelectionMode() {
		setParagraphSpinnerSelectionModeColor(GcgMultiShiftState.OFF);
	}

	public void resetParagraphSpinnerSelections() {
		setParagraphSpinnerFocusLost();
		for(FseParagraphView theParagraphView : this.paragraphViewSelectionList) {
			theParagraphView.getSpinner().setFocus();
		}
	}

	public ArrayList<GcgMultiShiftState> getSupportedParagraphSpinnerSelectionModeArray() {
		return this.supportedParagraphSpinnerSelectionModeArray;
	}

	public void endMultipleSelection(FseParagraphView aParagraphView) {
		if(this.paragraphViewSelectionList.size() != 1) {
			setParagraphSpinnerFocusLost();
			setParagraphSpinnerFocus(aParagraphView);
			resetParagraphViewSelectionList(aParagraphView);
			this.documentSectionParagraphEditorPerspective.resetMultiShiftControllerState();
		}
	}

	@Override
	public void stateChange(GcgMultiShiftState aMultiShiftState) {
		if(this.paragraphSpinnerSelectionMode != aMultiShiftState) {
			if(supportedParagraphSpinnerSelectionMode(aMultiShiftState)) {
				setParagraphSpinnerSelectionModeColor(aMultiShiftState);
				this.paragraphSpinnerSelectionMode = aMultiShiftState;
			} else {
				setParagraphSpinnerSelectionModeColor(GcgMultiShiftState.OFF);
				this.paragraphSpinnerSelectionMode = GcgMultiShiftState.OFF;
			}
		}
	}

	private boolean supportedParagraphSpinnerSelectionMode(GcgMultiShiftState aMultiShiftState) {
		return this.supportedParagraphSpinnerSelectionModeArray.contains(aMultiShiftState);
	}

	public boolean isMultipleSelections() {
		return this.paragraphViewSelectionList.size() > 1 ? true : false;
	}

	private static void setFocus(FseParagraphView aParagraphView) {
		switch (aParagraphView.getStyle().getContentType()) {
			case DRAWING:
				// BOGUS CODE - see TEXT implementation
				break;
			case IMAGE:
				// BOGUS CODE - see TEXT implementation
				break;
			case TEXT:
				aParagraphView.getContentTextView().setFocus();
				break;
			default:
				// TODO - throw a nasty exception about bad programmers!  ;-)
		}
	}

	private static boolean hasFocus(FseParagraphView aParagraphView) {
		boolean theBoolean = false;
		switch (aParagraphView.getStyle().getContentType()) {
			case DRAWING:
				// BOGUS CODE - see TEXT implementation
				break;
			case IMAGE:
				// BOGUS CODE - see TEXT implementation
				break;
			case TEXT:
				theBoolean = aParagraphView.getContentTextView().hasFocus();
				break;
			default:
				// TODO - throw a nasty exception about bad programmers!  ;-)
		}
		return theBoolean;
	}

	public GcgMultiShiftState getParagraphSpinnerSelectionMode() {
		return this.paragraphSpinnerSelectionMode;
	}

	public FsePerspectiveFlipper getDocumentView() {
		return this.documentSectionParagraphEditorPerspective.getDocumentPerspectiveFlipper();
	}

	public FseDocumentSectionParagraphEditorPerspective getDocumentSectionParagraphEditorPerspective() {
		return this.documentSectionParagraphEditorPerspective;
	}

	public void resetFocus() {
		FseParagraphView theParagraphViewToFocus = getParagraphForId(this.initialParagraphFocusId);
		if(theParagraphViewToFocus == null) {
			theParagraphViewToFocus = this.paragraphViewList.get(0);
			this.initialParagraphFocusId = theParagraphViewToFocus.getParagraphId();
		}
		theParagraphViewToFocus.getSpinner().setFocus();
		theParagraphViewToFocus.getContentView().setFocus();
		if(theParagraphViewToFocus.isTextContent()) {
			int theContentTextLength =
					((FseParagraphViewWithTextContent) theParagraphViewToFocus).getContentTextView().getText().length();
			int theVerticalScrollPosition = this.initialParagraphFocusCursorPosition <= theContentTextLength ?
					this.initialParagraphFocusCursorPosition : theContentTextLength;
			theParagraphViewToFocus.getContentTextView().setSelection(theVerticalScrollPosition);
			this.lastVerticalScrollPosition = theVerticalScrollPosition;
			this.verticalScrollPositionAtEndOfLine =
					this.lastVerticalScrollPosition == theContentTextLength ? true : false;
		}
	}

	public void moveParagraphFocusToNext() {
		if(this.documentSectionParagraphEditorPerspective.isMultiShiftControllerParagraphSelectionMode()) {
			return;
		}
		int theIndex = this.paragraphViewList.indexOf(this.paragraphViewFocus);
		if(theIndex < this.paragraphViewList.size() - 1) {
			onParagraphSpinnerTouch(this.paragraphViewList.get(theIndex + 1));
		}
	}

	public void moveParagraphFocusToPrevious() {
		if(this.documentSectionParagraphEditorPerspective.isMultiShiftControllerParagraphSelectionMode()) {
			return;
		}
		int theIndex = this.paragraphViewList.indexOf(this.paragraphViewFocus);
		if(theIndex != 0) {
			onParagraphSpinnerTouch(this.paragraphViewList.get(theIndex - 1));
		}
	}

	public void moveParagraphFocusToFirst() {
		if(this.documentSectionParagraphEditorPerspective.isMultiShiftControllerParagraphSelectionMode()) {
			return;
		}
		onParagraphSpinnerTouch(this.paragraphViewList.get(0));
	}

	public void moveParagraphFocusToLast() {
		if(this.documentSectionParagraphEditorPerspective.isMultiShiftControllerParagraphSelectionMode()) {
			return;
		}
		int theIndex = this.paragraphViewList.size() - 1;
		onParagraphSpinnerTouch(this.paragraphViewList.get(theIndex));
	}

	public void updateContentModificationState() {
		this.documentSectionParagraphEditorPerspective.updateContentModificationState();
	}

	public void updateInsertModificationState() {
		this.documentSectionParagraphEditorPerspective.updateInsertModificationState();
	}

	public void updateContentModificationState(FseContentModificationState aModificationState) {
		this.documentSectionParagraphEditorPerspective.updateContentModificationState(aModificationState);
	}

	@SuppressWarnings("incomplete-switch")
	public void updateContentModificationStateOnInput(FseContentModificationState aParagraphModificationState) {
		if(this.documentSectionParagraphEditorPerspective.disableModificationStateUpdates()) {
			return;
		}
		switch (aParagraphModificationState) {
			case NEW:
				if(this.documentSectionParagraphEditorPerspective.getInsertModificationState() == FseInsertModificationState.UNCHANGED) {
					this.documentSectionParagraphEditorPerspective.updateInsertModificationState(FseInsertModificationState.MODIFIED);
				}
				break;
			case MODIFIED:
				if(this.documentSectionParagraphEditorPerspective.getContentModificationState() == FseContentModificationState.UNCHANGED) {
					this.documentSectionParagraphEditorPerspective.updateContentModificationState(FseContentModificationState.MODIFIED);
				}
				break;
			case UNCHANGED:
				if(this.documentSectionParagraphEditorPerspective.getContentModificationState() == FseContentModificationState.MODIFIED) {
					if(noModifiedParagraphs()) {
						this.documentSectionParagraphEditorPerspective.updateContentModificationState(FseContentModificationState.UNCHANGED);
					}
				}
		}
	}

//	public void updateLockModificationState() {
//		if(this.documentSectionParagraphEditorView.getParagraphsLockedCount() > 0 || this.documentSectionParagraphEditorView.getParagraphsUnlockedCount() > 0 ) {
//			updateLockModificationState(FseParagraphLockModificationState.MODIFIED);
//		} else {
//			updateLockModificationState(FseParagraphLockModificationState.UNCHANGED);
//		}
//		
//	}

	public void updateLockModificationState() {
		this.documentSectionParagraphEditorPerspective.updateLockModificationState();
	}

	public void updateLockModificationState(FseLockModificationState aModificationState) {
		this.documentSectionParagraphEditorPerspective.updateLockModificationState(aModificationState);
	}

	public void updateStyleModificationState() {
		this.documentSectionParagraphEditorPerspective.updateStyleModificationState();
	}

	public void updateStyleModificationState(FseStyleModificationState aModificationState) {
		this.documentSectionParagraphEditorPerspective.updateStyleModificationState(aModificationState);
	}

	public void updateSequenceModificationState() {
		this.documentSectionParagraphEditorPerspective.updateSequenceModificationState();
	}

	public void updateSequenceModificationState(FseSequenceModificationState aModificationState) {
		this.documentSectionParagraphEditorPerspective.updateSequenceModificationState(aModificationState);
	}

	public void updateNumberingModificationState() {
		this.documentSectionParagraphEditorPerspective.updateNumberingModificationState();
	}

	public void updateNumberingModificationState(FseNumberingModificationState aModificationState) {
		this.documentSectionParagraphEditorPerspective.updateNumberingModificationState(aModificationState);
	}

	private boolean noModifiedParagraphs() {
		for(FseParagraphView theParagraphView : this.paragraphViewList) {
			if(theParagraphView.getContentModificationState() != FseContentModificationState.UNCHANGED && theParagraphView.getContentModificationState() != FseContentModificationState.NEW) {
				return false;
			}
		}
		return true;
	}

	public void saveDocument() {
		this.getDocumentView().getFmsNodeActivity().saveAllDataModifications();
	}

	public void newDocument() {
//		this.getDocumentView().newDocument();
	}

	public void revertDocument() {
		this.getDocumentView().discardDataChanges();
	}
	
	private boolean fdkDispatchTarget() {
		return (this.paragraphViewSelectionList.size() == 1 || this.paragraphViewFocus.getStyle().getContentType() == FseParagraphContentType.TEXT);
	}

	public void fdkDictationResults(String theDictationString) {
		if(! fdkDispatchTarget()) {
			return;
		}
		this.paragraphViewFocus.getContentTextView().fdkDictationResults(theDictationString);
		
	}

	// TODO - this can be optimized  SDS
	private void renumberPeerParagraphs(List<FseParagraphView> aParagraphViewList) {
		FseParagraphView thePreviousParagraphView = null;
		for(FseParagraphView theParagraphView : aParagraphViewList) {
			if(thePreviousParagraphView != theParagraphView) { // minor optimization of contiguous lists
				renumberPeerParagraphs(theParagraphView);
			}
			thePreviousParagraphView = theParagraphView;
		}
	}

	private void renumberPeerParagraphs(FseParagraphView aParagraphView) {
		renumberPeerParagraphs(aParagraphView, aParagraphView.getNumberingStyle(), false);
	}

	public void renumberPeerParagraphs(FseParagraphView aParagraphView, FseParagraphNumberingStyle aNumberingStyle, boolean bDisableIfSame) {
		FseParagraphNumberingStyle theNumberingStyle;
		if(bDisableIfSame) {
			theNumberingStyle = aNumberingStyle == aParagraphView.getNumberingStyle() ? FseParagraphNumberingStyle.NONE : aNumberingStyle;
		} else {
			theNumberingStyle = aNumberingStyle;
		}
		int theNumberingSeqence = 0;
		for(FseParagraphView theParagraphView : getParagraphViewPeerList(aParagraphView)) {
			++ theNumberingSeqence;
			theParagraphView.setNumberingStyle(theNumberingStyle, theNumberingSeqence);
		}
		aParagraphView.getContentTextView().resetSelection();
	}
	
	public void renumberOnParagraphStyleChange(FseParagraphView aParagraphView, ArrayList<FseParagraphView> thePreviousPeerList) {
//		aParagraphView.clearFocus();
		renumberPeerParagraphs(thePreviousPeerList);
		ArrayList<FseParagraphView> thePeerParagraphViewList = getParagraphViewPeerList(aParagraphView, false);
		FseParagraphNumberingStyle theNumberingStyle = null;
		if(thePeerParagraphViewList.size() == 0) {
			aParagraphView.setNumberingStyle(FseParagraphNumberingStyle.NONE, 0);
		} else {
			theNumberingStyle = thePeerParagraphViewList.get(0).getNetNumberingStyle();
			thePeerParagraphViewList = getParagraphViewPeerList(aParagraphView, true);
			renumberPeerParagraphs(aParagraphView, theNumberingStyle, false);
			/*
			 * NASTY HACK - leaves the cursor in the wrong place, but does not put the NumberString at risk
			 * does not work if aParagraphView is the last paragraph in the editor
			 */
			if(theNumberingStyle != FseParagraphNumberingStyle.NONE) {
				aParagraphView.getContentTextView().focusNextParagraphAtBeginning();
			}
		}
	}

	public ScrollView getScrollView() {
		return this.documentSectionParagraphEditorPerspective.getScrollView();
	}

	public boolean stillSequencedAboveNext(FseParagraphView aParagraphView) {
		if(aParagraphView.getNextParagraphId().equals("")) {
			return true;
		}
		FseParagraphView[] theParagraphViewArray = getParagraphViewList().toArray(new FseParagraphView[getParagraphViewList().size()]);
		int theStartPosition = getParagraphViewList().indexOf(aParagraphView);
		for(int i=theStartPosition; i < theParagraphViewArray.length; i++) {
			if(theParagraphViewArray[i].getParagraphId().equals(aParagraphView.getNextParagraphId())) {
				return true;
			}
		}
		return false;
//		return existingParagraphNextToPreviousParagraph(aParagraphView);  // TODO - needs more work
	}
	
	public boolean existingParagraphNextToNextParagraph(FseParagraphView aParagraphView) {
		int theListIndex = getNextExistingParagraphIndex(aParagraphView);
		if(theListIndex <= this.paragraphViewList.size()) {
			return aParagraphView.getNextParagraphId().equals(this.paragraphViewList.get(theListIndex).getParagraphId());
		}
		if(aParagraphView.getNextParagraphId().equals("")) {
			return true;
		}
		return false;
	}
	
	private int getNextExistingParagraphIndex(FseParagraphView aParagraphView) {
		int theStartingIndex = this.paragraphViewList.indexOf(aParagraphView) + 1;
		if(theStartingIndex > this.paragraphViewList.size()) {
			return theStartingIndex;
		}
		int theIndex;
		for(theIndex = theStartingIndex; theIndex <= this.paragraphViewList.size(); theIndex++) {
			if(this.paragraphViewList.get(theIndex).getContentModificationState() != FseContentModificationState.NEW) {
				return theIndex;
			}
		}
		return theIndex;
	}

	public boolean existingParagraphNextToPreviousParagraph(FseParagraphView aParagraphView) {
		int theListIndex = getPreviousExistingParagraphIndex(aParagraphView);
		if(theListIndex >= 0) {
			return aParagraphView.getPreviousParagraphId().equals(this.paragraphViewList.get(theListIndex).getParagraphId());
		}
		if(aParagraphView.getPreviousParagraphId().equals("")) {
			return true;
		}
		return false;
	}
	
	private int getPreviousExistingParagraphIndex(FseParagraphView aParagraphView) {
		int theStartingIndex = this.paragraphViewList.indexOf(aParagraphView) - 1;
		if(theStartingIndex < 0) {
			return theStartingIndex;
		}
		int theIndex;
		for(theIndex = theStartingIndex; theIndex >= 0; theIndex--) {
			if(this.paragraphViewList.get(theIndex).getContentModificationState() != FseContentModificationState.NEW) {
				return theIndex;
			}
		}
		return theIndex;
	}

	public boolean stillSequencedBelowPrevious(FseParagraphView aParagraphView) {
		if(aParagraphView.getPreviousParagraphId().equals("")) {
			return true;
		}
		FseParagraphView[] theParagraphViewArray = getParagraphViewList().toArray(new FseParagraphView[getParagraphViewList().size()]);
		int theStartPosition = getParagraphViewList().indexOf(aParagraphView);
		for(int i=theStartPosition; i > -1; i--) {
			if(theParagraphViewArray[i].getParagraphId().equals(aParagraphView.getPreviousParagraphId())) {
				return true;
			}
		}
		return false;
//		return existingParagraphNextToNextParagraph(aParagraphView);  // TODO - needs more work
	}

	public void resetAllParagraphSequenceState() {
		for(FseParagraphView theParagraphView : this.paragraphViewList) {
			theParagraphView.updateSequenceModificationState();
		}
		boolean isBlockMove = false;
		for(FseParagraphView theParagraphView : this.paragraphViewList) {
			if(theParagraphView.getSequenceModificationState() != FseSequenceModificationState.UNCHANGED) {
				continue;
			}
			if(isBlockMove) {
				if(existingParagraphNextToPreviousParagraph(theParagraphView)) {
					if(existingParagraphNextToNextParagraph(theParagraphView)) {
						theParagraphView.setSequenceModificationState(FseSequenceModificationState.BLOCK_MOVE_DOWN);
					} else {
						theParagraphView.setSequenceModificationState(FseSequenceModificationState.BLOCK_MOVE_DOWN__START);
					}
				} else {
					isBlockMove = false;
				}
			} else {
				if(! stillSequencedBelowPrevious(theParagraphView) && ! previousAtEndOfBlock(theParagraphView)) {
					theParagraphView.setSequenceModificationState(FseSequenceModificationState.BLOCK_MOVE_DOWN);
					isBlockMove = true;
				}
			}
		}
	}

	private boolean previousAtEndOfBlock(FseParagraphView aParagraphView) {
		int theStartIndex = this.paragraphViewList.indexOf(aParagraphView) + 1;
		if(theStartIndex >= this.paragraphViewList.size()) {
			return true;
		}
		String thePreviousParagraphId = aParagraphView.getParagraphId();
		for(int i = theStartIndex; i <= this.paragraphViewList.size(); ++i) {
			FseParagraphView theParagraphView = this.paragraphViewList.get(i);
			if(theParagraphView.getPreviousParagraphId().equals(thePreviousParagraphId)) {
				thePreviousParagraphId = theParagraphView.getParagraphId();
				continue;
			}
			if(theParagraphView.getParagraphId().equals(aParagraphView.getPreviousParagraphId())) {
				return true;
			}
			return false;
		}
		return false;
	}
	
	public GcgActivity getGcgActivity() {
		return this.documentSectionParagraphEditorPerspective.getGcgActivity();
	}

}
