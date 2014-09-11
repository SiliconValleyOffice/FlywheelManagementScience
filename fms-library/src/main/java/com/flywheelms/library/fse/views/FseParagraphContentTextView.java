/* @(#)FseParagraphContentTextView.java
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

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.EditText;
import android.widget.TextView;

import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.interfaces.FseParagraphContentView;
import com.flywheelms.library.fse.listeners.FseParagraphTextWatcher;
import com.flywheelms.library.fse.perspective_flipper.FsePerspectiveFlipper;
import com.flywheelms.library.gcg.helper.GcgHelper;

import java.util.Locale;

public class FseParagraphContentTextView extends EditText implements FseParagraphContentView {

	private int initialSelectionStart = 0;
	private  FseParagraphView paragraphView;
	
	public FseParagraphContentTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initListeners();
	}

	private void initListeners() {
		initOnFocusChangeListener();
		initOnTouchListener();
		initOnEditorActionListener();
		addTextChangedListener(new FseParagraphTextWatcher(this));
	}

	private void initOnTouchListener() {
		setOnTouchListener(new OnTouchListener(){

			@Override
			public boolean onTouch(View v, MotionEvent event) {
//	            InputMethodManager theInputMethodManager = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//	            if (theInputMethodManager != null) {
//	            	theInputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
//	            }     
				
				FseParagraphContentTextView.this.paragraphView.getParagraphEditor().endMultipleSelection(getParagraphView());
//				if(getSelectionStart() < FseParagraphContentTextView.this.paragraphView.getNumberingStringLength()) {
//					setSelectionAtBeginning();
//					return true;
//				}
				return false;
			}
		} );
	}

	@Override
	public void setSelection(int anIndex) {
		setSelection(anIndex, anIndex);
	}

	@Override
	public void setSelection(int aStartIndex, int aStopIndex) {
		int theFirstImmutablePosition = FseParagraphContentTextView.this.paragraphView.getNumberingStringLength();
		int theAdjustedStartIndex = aStartIndex;
		int theAdjustedStopIndex = aStopIndex;
		if(aStartIndex < theFirstImmutablePosition) {
			theAdjustedStartIndex = theFirstImmutablePosition;
		}
		if(aStopIndex < theFirstImmutablePosition) {
			theAdjustedStopIndex = theFirstImmutablePosition;
		}
		getParagraphView().getParagraphEditor().setInitialParagraphFocusCursorPosition(theAdjustedStartIndex);
		super.setSelection(theAdjustedStartIndex, theAdjustedStopIndex);
	}

	private void initOnFocusChangeListener() {
		setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View aView, boolean hasFocus) {
				if(hasFocus){
					FseParagraphContentTextView.this.paragraphView.getParagraphEditor().manageVerticalScrollPosition(FseParagraphContentTextView.this.getParagraphView());
					FseParagraphContentTextView.this.setInitialSelectionStart(
							FseParagraphContentTextView.this.getSelectionStart());
					FseParagraphContentTextView.this.paragraphView.getParagraphEditor().onParagraphContentTouch(
							FseParagraphContentTextView.this.getParagraphView());
					GcgHelper.keepViewFullyVisibleInScrollView(
							FseParagraphContentTextView.this,
							FseParagraphContentTextView.this.getParagraphView().getParagraphEditor().getScrollView() );
				} else {
					FseParagraphContentTextView.this.paragraphView.getParagraphEditor().setLastVerticalScrollPosition(FseParagraphContentTextView.this);
				}
			}
		} );
	}

	private void initOnEditorActionListener() {
		setOnEditorActionListener(new OnEditorActionListener() {
			@Override
			public boolean onEditorAction(TextView aTextView, int actionId, KeyEvent aKeyEvent) {
				switch (actionId) {
				case EditorInfo.IME_ACTION_NEXT:
					FseParagraphContentTextView.this.focusNextParagraphMaintainingVerticalScrollPosition();
					return true;
				case EditorInfo.IME_ACTION_PREVIOUS:
					FseParagraphContentTextView.this.focusPreviousParagraphMaintainingVerticalScrollPosition();
					return true;
				default:
					if(FseParagraphContentTextView.this.paragraphView.getParagraphEditor().getParagraphViewSelectionList().size() != 1) {
						FseParagraphContentTextView.this.paragraphView.getParagraphEditor().onParagraphContentTouch(FseParagraphContentTextView.this.getParagraphView());
					}
					return true;
				}
			}
		});
	}

	protected void focusPreviousParagraphMaintainingVerticalScrollPosition() {
		this.paragraphView.getParagraphEditor().focusPreviousParagraphMaintainingVerticalScrollPosition(this.getParagraphView());
	}

	protected void focusNextParagraphMaintainingVerticalScrollPosition() {
		this.paragraphView.getParagraphEditor().focusNextParagraphMaintainingVerticalScrollPosition(this.getParagraphView());
	}
	
	protected void focusNextParagraphAtBeginning() {
		this.paragraphView.getParagraphEditor().focusNextParagraphAtBeginning(this.getParagraphView());
	}
	
	protected void focusPreviousParagraphAtEnd() {
		this.paragraphView.getParagraphEditor().focusPreviousParagraphAtEnd(this.getParagraphView());
	}

	@Override
	protected void onSelectionChanged(int aStartPosition, int anEndPosition) {
		if(this.paragraphView == null) {
			super.onSelectionChanged(aStartPosition, anEndPosition);
			return;
		}
		int theFirstImmutablePosition = this.paragraphView.getNumberingStringLength();
		if(aStartPosition < theFirstImmutablePosition) {
			super.setSelection(theFirstImmutablePosition, anEndPosition);
		} else {
			super.onSelectionChanged(aStartPosition, anEndPosition);
		}
	}
	
	public int getInitialSelectionStart() {
		return this.initialSelectionStart;
	}

	public void setInitialSelectionStart(int initialSelectionStart) {
		this.initialSelectionStart = initialSelectionStart;
	}

	@Override
	public FseParagraphView getParagraphView() {
		return this.paragraphView;
	}

	@Override
	public void setParagraphView(FseParagraphView anFseParagraph) {
		this.paragraphView = anFseParagraph;
	}
	
	@Override
	public FsePerspectiveFlipper getDocumentView() {
		return getParagraphView().getDocumentView();
	}
	
	@Override
	public void setSelectionAtBeginning() {
		setSelection(getFirstMutablePosition());
	}

	public int getFirstMutablePosition() {
		return 0 + ((FseParagraphViewWithTextContent) this.paragraphView).getNumberingStringLength();
	}
	
	@Override
	public void setSelectionAtEnd() {
		setSelection(getText().length());
	}
	
	@Override
	public void selectAllContents() {
		setSelection(getFirstMutablePosition(), getText().length());
	}
	
//////////  KeyEvent processing  /////////////////////
	/*
	 * NOTE
	 * Handling of key events in Android is a PAIN IN THE ASS !!!
	 * You must handle soft keyboard input (IME) different from hardware input.
	 * At the level of an EditText view, the developer should not HAVE to care.  :-(
	 */
	
	private boolean processKeycodeDelete() {
		if(! cursorAtBeginning()) {
			return false;  // we did not process this event
		}
		if(activeSelection()) {
			return false;
		}
		this.paragraphView.getParagraphEditor().deleteParagraphOnKeycodeDelete(getParagraphView());
        return true;
	}
	
	private boolean processKeycodeForwardDelete() {
		if(! cursorAtEnd()) {
			return false;  // we did not process this event
		}
		this.paragraphView.getParagraphEditor().mergeParagraphOnKeycodeForwardDelete(getParagraphView());
        return true;
	}

	public boolean cursorAtEnd() {
		return getSelectionStart() == getText().length();
	}

	public boolean cursorAtBeginning() {
		return getSelectionStart() == getFirstMutablePosition();
	}

	private boolean activeSelection() {
		return getSelectionStart() != getSelectionEnd();
	}

    @Override
    public boolean onKeyDown(int aKeyCode, KeyEvent aKeyEvent) {
    	if(aKeyCode==KeyEvent.KEYCODE_CTRL_LEFT || aKeyCode==KeyEvent.KEYCODE_CTRL_RIGHT) {
    		if(aKeyEvent.getAction() == KeyEvent.ACTION_DOWN) {
    			return false;
    		}
    	}
	    if (aKeyCode == KeyEvent.KEYCODE_RIGHT_BRACKET) {
	    	if(aKeyEvent.isCtrlPressed()) {
		    	if(aKeyEvent.isShiftPressed()) {
		    		FseParagraphContentTextView.this.paragraphView.getDocumentView().navigateRightButtonClick();
		    	} else {
		    		FseParagraphContentTextView.this.paragraphView.getDocumentView().flipNext();
		    	}
	    		return true;
	    	}
	    	if(aKeyEvent.isAltPressed()) {
		    	if(aKeyEvent.isShiftPressed()) {
		    		FseParagraphContentTextView.this.paragraphView.getDocumentView().navigateLastButtonClick();
		    	} else {
		    		// something in the future
		    	}
		    	return true;
	    	}
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_LEFT_BRACKET) {
	    	if(aKeyEvent.isCtrlPressed()) {
		    	if(aKeyEvent.isShiftPressed()) {
		    		FseParagraphContentTextView.this.paragraphView.getDocumentView().navigateLeftButtonClick();
		    	} else {
		    		FseParagraphContentTextView.this.paragraphView.getDocumentView().flipPrevious();
		    	}
	    		return true;
	    	}
	    	if(aKeyEvent.isAltPressed()) {
		    	if(aKeyEvent.isShiftPressed()) {
		    		FseParagraphContentTextView.this.paragraphView.getDocumentView().navigateFirstButtonClick();
		    	} else {
		    		// something in the future
		    	}
		    	return true;
	    	}
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_BACKSLASH) {
	    	if(aKeyEvent.isCtrlPressed() && aKeyEvent.isShiftPressed()) {
	    		FseParagraphContentTextView.this.paragraphView.getDocumentView().navigationSpinnerSelect();
	    	}
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_S && aKeyEvent.isCtrlPressed()) {
	    	FseParagraphContentTextView.this.paragraphView.getParagraphEditor().saveDocument();
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_N && aKeyEvent.isCtrlPressed()) {
	    	FseParagraphContentTextView.this.paragraphView.getDocumentView().newFractal();
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_R && aKeyEvent.isCtrlPressed()) {
	    	FseParagraphContentTextView.this.paragraphView.getDocumentView().revertAllModifiedData();
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_TAB) {
	    	if(aKeyEvent.isShiftPressed()) {
	    		FseParagraphContentTextView.this.paragraphView.getParagraphEditor().moveParagraphFocusToPrevious();
	    		return true;
	    	}
    		FseParagraphContentTextView.this.paragraphView.getParagraphEditor().moveParagraphFocusToNext();
    		return true;
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_COMMA && aKeyEvent.isAltPressed()) {
	    	if(aKeyEvent.isCtrlPressed()) {
	    		FseParagraphContentTextView.this.paragraphView.getParagraphEditor().getDocumentSectionParagraphEditorPerspective().rightMenuActivateSpinner();
	    		return true;
	    	}
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_COMMA && aKeyEvent.isShiftPressed()) {
	    	if(aKeyEvent.isCtrlPressed()) {
	    		FseParagraphContentTextView.this.paragraphView.getParagraphEditor().getDocumentSectionParagraphEditorPerspective().leftMenuActivateSpinner();
	    		return true;
	    	}
	    }
		if(aKeyCode==KeyEvent.KEYCODE_DPAD_DOWN) {
			if(getParagraphView().isLast()) {
				return true;
			}
			FseParagraphContentTextView.this.focusNextParagraphMaintainingVerticalScrollPosition();
			return true;
		}
		if(aKeyCode==KeyEvent.KEYCODE_DPAD_UP) {
			if(getParagraphView().isFirst()) {
				return true;
			}
			FseParagraphContentTextView.this.focusPreviousParagraphMaintainingVerticalScrollPosition();
			return true;
		}
		if(aKeyCode==KeyEvent.KEYCODE_DPAD_RIGHT) {
	    	if(aKeyEvent.isCtrlPressed() && aKeyEvent.isAltPressed()) {
	    		FseParagraphContentTextView.this.paragraphView.getParagraphEditor().moveParagraphFocusToLast();
	    		return true;
	    	}
			if(FseParagraphContentTextView.this.getSelectionStart() == FseParagraphContentTextView.this.getText().length()) {
				FseParagraphContentTextView.this.focusNextParagraphAtBeginning();
				return true;
			}
	        return super.onKeyDown(aKeyCode, aKeyEvent);
		}
		if(aKeyCode==KeyEvent.KEYCODE_DPAD_LEFT) {
	    	if(aKeyEvent.isCtrlPressed() && aKeyEvent.isAltPressed()) {
	    		FseParagraphContentTextView.this.paragraphView.getParagraphEditor().moveParagraphFocusToFirst();
	    		return true;
	    	}
	    	if(aKeyEvent.isAltPressed()) {
	    		setSelectionAtBeginning();
	    		return true;
	    	}
			if(FseParagraphContentTextView.this.getSelectionStart() <= getFirstMutablePosition()) {
				if(getParagraphView().isFirst()) {
					return true;
				}
				FseParagraphContentTextView.this.focusPreviousParagraphAtEnd();
				return true;
			}
	        return super.onKeyDown(aKeyCode, aKeyEvent);
		}
    	this.paragraphView.getParagraphEditor().endMultipleSelection(getParagraphView());
    	if (aKeyCode==KeyEvent.KEYCODE_ENTER) {
    		FseParagraphContentTextView.this.paragraphView.getParagraphEditor().createParagraphOnKeycodeEnter(
    				FseParagraphContentTextView.this.getParagraphView());
    		return true;
    	}
		if(aKeyCode==KeyEvent.KEYCODE_1 || aKeyCode==KeyEvent.KEYCODE_NUMPAD_1 ||
				aKeyCode==KeyEvent.KEYCODE_2 || aKeyCode==KeyEvent.KEYCODE_NUMPAD_2 ||
				aKeyCode==KeyEvent.KEYCODE_3 || aKeyCode==KeyEvent.KEYCODE_NUMPAD_3 ||
				aKeyCode==KeyEvent.KEYCODE_4 || aKeyCode==KeyEvent.KEYCODE_NUMPAD_4 ||
				aKeyCode==KeyEvent.KEYCODE_5 || aKeyCode==KeyEvent.KEYCODE_NUMPAD_5 ||
				aKeyCode==KeyEvent.KEYCODE_6 || aKeyCode==KeyEvent.KEYCODE_NUMPAD_6 ||
				aKeyCode==KeyEvent.KEYCODE_7 || aKeyCode==KeyEvent.KEYCODE_NUMPAD_7 ||
				aKeyCode==KeyEvent.KEYCODE_8 || aKeyCode==KeyEvent.KEYCODE_NUMPAD_8 ||
				aKeyCode==KeyEvent.KEYCODE_9 || aKeyCode==KeyEvent.KEYCODE_NUMPAD_9 ) {
	    	if(aKeyEvent.isCtrlPressed() && aKeyEvent.isShiftPressed()) {
	    		FseParagraphContentTextView.this.getParagraphView().getDocumentView().leftMenuClickItem(Integer.valueOf(GcgHelper.getIntegerFromKeycode(aKeyCode)));
	    		return true;
	    	}
	    	if(aKeyEvent.isCtrlPressed() && aKeyEvent.isAltPressed()) {
	    		FseParagraphContentTextView.this.getParagraphView().getParagraphEditor().getDocumentSectionParagraphEditorPerspective().rightMenuClickItem(Integer.valueOf(GcgHelper.getIntegerFromKeycode(aKeyCode)));
	    		return true;
	    	}
	        return super.onKeyDown(aKeyCode, aKeyEvent);
		}
    	if(isDocumentLocked() && isStory()) {
    		return true;
    	}
//    	if(isLocked() &&
//    			(GcgHelper.notArrowKeys(aKeyCode) && aKeyCode!=KeyEvent.KEYCODE_ENTER) ) {
		if(isLocked()) {
    		return true;
    	}
    	/////  for UNLOCKED paragraphs  ///////
        if (aKeyCode==KeyEvent.KEYCODE_DEL) {
        	if(FseParagraphContentTextView.this.processKeycodeDelete()) {
        		return true;
        	}
        }
        if (aKeyCode==KeyEvent.KEYCODE_FORWARD_DEL) {
        	if(FseParagraphContentTextView.this.processKeycodeForwardDelete()) {
        		return true;
        	}
        }
    	if (aKeyCode != KeyEvent.KEYCODE_ENTER) {
    		return super.onKeyDown(aKeyCode, aKeyEvent);
    	}
		return true;
    }

	private boolean isStory() {
		return this.paragraphView.getParagraphEditor().getDocumentSectionParagraphEditorPerspective().getSectionType() == FseDocumentSectionType.STORY;
	}

	private boolean isDocumentLocked() {
		return this.paragraphView.getDocumentView().isLocked();
	}

	@Override
    public InputConnection onCreateInputConnection(EditorInfo anEditorInfo) {
        return new FseParagraphInputConnectionWrapper(super.onCreateInputConnection(anEditorInfo));
    }

    private class FseParagraphInputConnectionWrapper extends InputConnectionWrapper {

        public FseParagraphInputConnectionWrapper(InputConnection anInputConnection) {
            super(anInputConnection, true);
        }

        @Override
        public boolean deleteSurroundingText(int aStartPosition, int anEndPosition) {
        	if(aStartPosition == 1 && anEndPosition == 0) {
            	if(FseParagraphContentTextView.this.processKeycodeDelete()) {
            		return false;
            	}
        	}
            return super.deleteSurroundingText(aStartPosition, anEndPosition);
        }

        @Override
        public boolean sendKeyEvent(KeyEvent aKeyEvent) {
            return super.sendKeyEvent(aKeyEvent);
        }
    }
	
	@Override
	public void setFocus() {
		this.requestFocus();
	}

	@Override
	public void lock() {
//	    setFocusableInTouchMode(false);
//	    setFocusable(false);
	}

	@Override
	public void unlock() {
//	    setFocusableInTouchMode(true);
//	    setFocusable(true);
	}
	
	public boolean isLocked() {
		return this.paragraphView.isLocked();
	}
	
	@Override
	public int getSelectionStart() {
		int theStart = super.getSelectionStart();
		return theStart >= getFirstMutablePosition() ? theStart : getFirstMutablePosition();
	}
	
	@Override
	public int getSelectionEnd() {
		int theEnd = super.getSelectionEnd();
//		return theEnd >= getFirstMutablePosition() ? theEnd : getFirstMutablePosition();
		return theEnd;
	}

	@SuppressLint("DefaultLocale")
	public void fdkDictationResults(String aDictationString) {
		if(aDictationString.length() == 0) {
			return;
		}
		if(getSelectionStart() != getSelectionEnd()) {
			dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_FORWARD_DEL));
		}
		String theOriginalText = getTextContents().toString();
		if(theOriginalText.length() == 0) {
			StringBuffer theStringBuffer = new StringBuffer();
			theStringBuffer.append(aDictationString.substring(0, 1).toUpperCase(Locale.ENGLISH));
			theStringBuffer.append(aDictationString.substring(1));
			append(theStringBuffer.toString());
			setSelectionAtEnd();
			return;
		}
		if(cursorAtBeginning()) {
			StringBuffer theStringBuffer = new StringBuffer();
			theStringBuffer.append(aDictationString.substring(0, 1).toUpperCase(Locale.ENGLISH));
			theStringBuffer.append(aDictationString.substring(1));
			int theCursorPosition = theStringBuffer.length();
			theStringBuffer.append(theOriginalText);
			replaceText(theStringBuffer.toString());
			setSelection(theCursorPosition);
			return;
		}
		if(cursorAtEnd()) {
			StringBuilder theStringBuilder = new StringBuilder(theOriginalText);
			theStringBuilder.append(capitalizeFirstLetter(aDictationString, theStringBuilder.toString()));
			replaceText(theStringBuilder.toString());
			setSelectionAtEnd();
			return;
		}
		StringBuilder theStringBuilder = new StringBuilder(theOriginalText.substring(0, getSelectionStart()));
		insertWhiteSpace(theStringBuilder);
		theStringBuilder.append(capitalizeFirstLetter(aDictationString, theStringBuilder.toString()));
		theStringBuilder.append(theOriginalText.substring(getSelectionEnd(), theOriginalText.length()));
		replaceText(theStringBuilder.toString());
	}

	private static void insertWhiteSpace(StringBuilder theStringBuilder) {
		CharSequence theLastChar = theStringBuilder.subSequence(theStringBuilder.length() -1, theStringBuilder.length());
		if("abcdefghijklmnopqrstuvwxyz".contains(theLastChar)) {
			theStringBuilder.append(" ");
		}
	}

	@SuppressLint("DefaultLocale")
	private static String capitalizeFirstLetter(String aDictationResult, String thePreceedingString) {
		char theLastChar = thePreceedingString.charAt(thePreceedingString.length() -1);
		if(theLastChar == '.' || theLastChar == '?' || theLastChar == '!') {
			return "  " + aDictationResult.substring(0, 1).toUpperCase(Locale.ENGLISH) + aDictationResult.substring(1, aDictationResult.length());
		}
		if(theLastChar != ' ') {
			return " " + aDictationResult;
		}
		return aDictationResult;
	}

	@Override
	public void setPadding(int aLeftPadding, int aTopPadding, int aRightPadding, int aBottomPadding) {
		super.setPadding(aLeftPadding + getParagraphView().getDocumentView().getDocumentMarginLeft(), aTopPadding, aRightPadding, aBottomPadding);
	}

	@Override
	public void setPadding() {
		setPadding(getParagraphView().getStyle().getLeftMargin(), getParagraphView().getStyle().getTopMargin(), 0, 0);
	}

	public void replaceText(String aTextString) {
		setText(getParagraphView().getSpannable(getParagraphView().getNumberingString() + aTextString));
	}
	
	public String getTextContents() {
		return ((FseParagraphViewWithTextContent) getParagraphView()).getTextContents();
	}

	public void resetSelection() {
		int theSelectionStartPosition = getSelectionStart();
		if(theSelectionStartPosition < getFirstMutablePosition() + 1) {
			setSelectionAtBeginning();
		}
	}

}
