/* @(#)FdkKeyboard.java
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

package com.flywheelms.library.fdk.widget;

import android.view.KeyEvent;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.flywheelms.library.R;
import com.flywheelms.library.fdk.FdkHostSupport;
import com.flywheelms.library.fdk.enumerator.FdkKeyboardState;
import com.flywheelms.library.fdk.enumerator.FdkKeyboardStyle;
import com.flywheelms.library.fdk.interfaces.FdkDictationResultsConsumer;
import com.flywheelms.library.fdk.interfaces.FdkHost;
import com.flywheelms.library.fdk.interfaces.FdkListener;
import com.flywheelms.library.gcg.button.multi_shift.GcgMultiShiftButton;
import com.flywheelms.library.gcg.button.multi_shift.GcgMultiShiftKeysetController;
import com.flywheelms.library.gcg.button.multi_shift.GcgMultiShiftState;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.interfaces.GcgMultiShiftControllerParent;

import java.util.ArrayList;
import java.util.Hashtable;

public class FdkKeyboard implements GcgMultiShiftControllerParent {

	private FdkKeyboardStyle keyboardStyle;
	private FdkKeyboardState keyboardState;
	private boolean keyboardActive = false;
	private boolean keyboardEnabled = true;
	private FrameLayout leftThumbpad;
	private ViewGroup leftKeypadContainer;
	private View leftKeypadPeerView;
	private FrameLayout rightThumbpad;
	private ViewGroup rightKeypadContainer;
	private View rightKeypadPeerView;
	private FdkLeftKeypad leftKeypad;
	private Hashtable<FdkKeyboardStyle, FdkLeftKeypad> leftKeypadHashtable = new Hashtable<FdkKeyboardStyle, FdkLeftKeypad>(); 
	private FdkRightKeypad rightKeypad;
	private Hashtable<FdkKeyboardStyle, FdkRightKeypad> rightKeypadHashtable = new Hashtable<FdkKeyboardStyle, FdkRightKeypad>(); 
	private ArrayList<GcgMultiShiftButton> multiShiftKeysetButtons;
	private GcgMultiShiftKeysetController multiShiftKeysetController;
	private Hashtable<FdkKeyboardStyle, GcgMultiShiftKeysetController> multiShiftKeysetControllerHashtable = new Hashtable<FdkKeyboardStyle, GcgMultiShiftKeysetController>(); 
	private FdkListener fdkListener;
	private final FdkHost fdkHost;
	private boolean listeningForDictation = false;
	private boolean waitingForDictationResults = false;
	private boolean processingDictation = false;
	private static final int EMPTY_KEYCODE_BUFFER = 0;
	private int keycodeBuffer = EMPTY_KEYCODE_BUFFER;
	private boolean keycodeShifted = false;
	private FdkDictationResultsConsumer dictationResultsConsumer;
	
	public FdkKeyboard(
			FdkKeyboardStyle aKeyboardStyle,
			FdkKeyboardState aKeyboardState,
			FrameLayout aLeftThumbpad,
			View aLeftKeypadPeerView,
			FrameLayout aRightThumbpad,
			View aRightKeypadPeerView,
			FdkListener anFdkListener,
			FdkHost anFdkHost) {
		this.keyboardStyle = aKeyboardStyle;
		this.keyboardState = aKeyboardState;
		this.leftThumbpad = aLeftThumbpad;
		this.leftKeypadPeerView = aLeftKeypadPeerView;
		this.rightThumbpad = aRightThumbpad;
		this.rightKeypadPeerView = aRightKeypadPeerView;
		this.fdkListener = anFdkListener;
		this.fdkHost = anFdkHost;
		initializeKeypads();
		initializeMultiShiftController();
	}
	
	private void initializeMultiShiftController() {
		if(this.keyboardStyle.hasMultiShiftKeyset()) {
			this.multiShiftKeysetButtons = new ArrayList<GcgMultiShiftButton>();
			this.multiShiftKeysetButtons.add(this.leftKeypad.getMultiShiftButton());
			this.multiShiftKeysetButtons.add(this.rightKeypad.getMultiShiftButton());
			this.multiShiftKeysetController = new GcgMultiShiftKeysetController(this);
			this.multiShiftKeysetControllerHashtable.put(this.keyboardStyle, this.multiShiftKeysetController);
		}
	}

	public FdkKeyboard(
			FdkKeyboardStyle aKeyboardStyle,
			FrameLayout aLeftThumbpad,
			FrameLayout aRightThumbpad,
			FdkListener anFdkListener,
			FdkHost anFdkActivity) {
		this(aKeyboardStyle, FdkKeyboardState.ALWAYS_ACTIVE, aLeftThumbpad, null, aRightThumbpad, null, anFdkListener, anFdkActivity);
	}
	
	public FdkHost getFdkHost() {
		return this.fdkHost;
	}

	public View getLeftKeypadPeerView() {
		return this.leftKeypadPeerView;
	}

	public View getRightKeypadPeerView() {
		return this.rightKeypadPeerView;
	}

	public void initializeKeypads() {
		this.leftKeypadContainer = (ViewGroup) this.leftThumbpad.findViewById(R.id.fdk__left_keypad__container);
		initLeftKeypad();
		this.rightKeypadContainer = (ViewGroup) this.rightThumbpad.findViewById(R.id.fdk__right_keypad__container);
		initRightKeypad();
	}

	private void initRightKeypad() {
		this.rightKeypad = new FdkRightKeypad(this);
		this.rightKeypadHashtable.put(this.keyboardStyle, this.rightKeypad);
		setKeypadContainerView(this.rightKeypadContainer, this.rightKeypad);
	}

	private void initLeftKeypad() {
		this.leftKeypad = new FdkLeftKeypad(this);
		this.leftKeypadHashtable.put(this.keyboardStyle, this.leftKeypad);
		setKeypadContainerView(this.leftKeypadContainer, this.leftKeypad);
	}
	
	private static void setKeypadContainerView(ViewGroup aKeypadContainer, FdkKeypad aKeypad) {
		aKeypadContainer.removeAllViews();
		aKeypadContainer.addView(aKeypad);
	}

	public void changeKeyboardStyle(FdkKeyboardStyle aKeyboardStyle) {
		changeKeyboardStyle(aKeyboardStyle, this.keyboardStyle.getDefaultMultiShiftState());
	}

	public void changeKeyboardStyle(FdkKeyboardStyle aKeyboardStyle, GcgMultiShiftState aMultiShiftState) {
		if(this.keyboardStyle == aKeyboardStyle) {
			return;
		}
		this.keyboardStyle = aKeyboardStyle;
		if(this.leftKeypadHashtable.get(this.keyboardStyle) == null) {
			initLeftKeypad();
			initRightKeypad();
		} else {
			this.leftKeypad = this.leftKeypadHashtable.get(this.keyboardStyle);
			setKeypadContainerView(this.leftKeypadContainer, this.leftKeypad);
			this.rightKeypad = this.rightKeypadHashtable.get(this.keyboardStyle);
			setKeypadContainerView(this.rightKeypadContainer, this.rightKeypad);
		}
		if(this.keyboardStyle.hasMultiShiftKeyset()) {
			if(this.multiShiftKeysetControllerHashtable.get(this.keyboardStyle) == null) {
				this.multiShiftKeysetButtons = new ArrayList<GcgMultiShiftButton>();
				this.multiShiftKeysetButtons.add(this.leftKeypad.getMultiShiftButton());
				this.multiShiftKeysetButtons.add(this.rightKeypad.getMultiShiftButton());
				this.multiShiftKeysetController =  new GcgMultiShiftKeysetController(this);
				this.multiShiftKeysetControllerHashtable.put(this.keyboardStyle, this.multiShiftKeysetController);
			} else {
				this.multiShiftKeysetController = this.multiShiftKeysetControllerHashtable.get(this.keyboardStyle);
			}
			setMultiShiftKeysetControllerState(aMultiShiftState);
		} else {
			this.multiShiftKeysetController =  null;
		}
		if(this.keyboardActive) {
			this.leftKeypad.activate();
			this.rightKeypad.activate();
		}
	}

	private void setMultiShiftKeysetControllerState(GcgMultiShiftState aMultiShiftState) {
		if((this.multiShiftKeysetController == null)) {
			return;
		}
		this.multiShiftKeysetController.setState(aMultiShiftState);
	}

	public void activate(boolean aBoolean) {
		this.keyboardActive = aBoolean;
		if(this.keyboardActive) {
			this.leftKeypad.activate();
			this.rightKeypad.activate();
		} else {
			this.leftKeypad.deactivate();
			this.rightKeypad.deactivate();
		}
	}

	public void reactivate() {
		this.leftKeypad.activate();
		this.rightKeypad.activate();
	}
	
	public FdkKeyboardStyle getKeyboardStyle() {
		return this.keyboardStyle;
	}
	
	public boolean disableSoftKeyboard() {
		return this.keyboardState == FdkKeyboardState.ACTIVE || this.keyboardState == FdkKeyboardState.ALWAYS_ACTIVE;
	}

	public FdkKeyboardState getKeyboardState() {
		return this.keyboardState;
	}

	public void setKeyboardState(FdkKeyboardState aKeyboardState) {
		this.keyboardState = aKeyboardState;
	}

	public boolean isActive() {
		return this.keyboardActive;
	}

	public ViewGroup getLeftKeypadContainer() {
		return this.leftKeypadContainer;
	}

	public ViewGroup getRightKeypadContainer() {
		return this.rightKeypadContainer;
	}

	public FdkLeftKeypad getLeftKeypad() {
		return this.leftKeypad;
	}

	public FdkRightKeypad getRightKeypad() {
		return this.rightKeypad;
	}

	public void registerListener(FdkListener anFdkListener) {
		this.fdkListener = anFdkListener;
	}

	public void stopDictation() {
		if(this.listeningForDictation) {
			this.listeningForDictation = false;
			this.fdkHost.stopDictation();
			this.leftKeypad.getStartStopButton().setBackgroundResource(R.drawable.fdk_key__start);
		}
	}
	
	public void dictationResults(ArrayList<String> aWordList) {
		this.waitingForDictationResults = false;
		if(aWordList.size() > 0) {
			this.processingDictation = true;
			if(hasMultiShiftKeyset()) {
				aWordList.set(0, applyMultiShiftKeyset(aWordList));
			}
			this.fdkListener.fdkDictationResults(aWordList);
			this.processingDictation = false;
			if(this.listeningForDictation) {
				this.listeningForDictation = false;
				this.leftKeypad.getStartStopButton().setBackgroundResource(R.drawable.fdk_key__start);
			}
			dispatchKeyEvent();
		} else {
			this.keycodeBuffer = EMPTY_KEYCODE_BUFFER;
		}
	}
	
	private String applyMultiShiftKeyset(ArrayList<String> aWordList) {
		String theDictationResults = aWordList.get(0);
		if(this.multiShiftKeysetControllerIsOff()) {
			return theDictationResults;
		}
		GcgMultiShiftState theMultiShiftState = this.multiShiftKeysetController.getMultiShiftState();
		this.multiShiftKeysetController.resetStateAfterDictation();
		switch(theMultiShiftState) {
			case INITIAL_CAPS:
				theDictationResults = FdkHostSupport.capitalizeFirstLetter(aWordList.get(0));
				break;
			case CAMEL_CASE:
				theDictationResults = FdkHostSupport.capitalizeCamelCase(aWordList.get(0));
				break;
			case ALL_CAPS:
				theDictationResults = FdkHostSupport.capitalizeAllLetters(aWordList.get(0));
				break;
			default:
		}
		return theDictationResults;
	}

	private boolean multiShiftKeysetControllerIsOff() {
		return this.multiShiftKeysetController == null ? true : this.multiShiftKeysetController.isOff();
	}

	public void dispatchKeyEvent() {
		if(this.listeningForDictation || this.waitingForDictationResults || this.processingDictation) {
			return;
		}
		if(this.keycodeBuffer != EMPTY_KEYCODE_BUFFER) {
			if(this.keycodeShifted) {
				this.keycodeShifted = false;
				this.fdkListener.fdkDispatchShiftedKeyEvent(this.keycodeBuffer);
			} else {
				this.fdkListener.fdkDispatchKeyEvent(this.keycodeBuffer);
			}
			this.keycodeBuffer = EMPTY_KEYCODE_BUFFER;
		}
	}

	public void onEndOfSpeech() {
		this.listeningForDictation = false;
		this.waitingForDictationResults = true;
		this.fdkHost.stopDictation();
		this.leftKeypad.getStartStopButton().setBackgroundResource(R.drawable.fdk_key__start);
	}

	public void cancelDictation() {
		if(this.listeningForDictation == true) {
			this.fdkHost.cancelDictation();
			this.leftKeypad.getStartStopButton().setBackgroundResource(R.drawable.fdk_key__start);
			this.listeningForDictation = false;
			GcgHelper.makeToast("Cancel dictation...");
		}
	}

	private void processButtonClick(int aKeycode) {
		this.keycodeBuffer = aKeycode;
		if(this.listeningForDictation) {
			stopDictation();
		} else if( ! this.processingDictation) {
			dispatchKeyEvent();
		}
	}
	
	////// process keypad buttons
	
	public void onStartButtonClick() {
		if(this.listeningForDictation) {
			stopDictation();
		} else {
			this.listeningForDictation = true;
			this.fdkHost.startDictation();
			this.leftKeypad.getStartStopButton().setBackgroundResource(R.drawable.fdk_key__stop);
		}
	}

	public void onStartButtonLongClick() {
		cancelDictation();
		this.fdkHost.toggleSoftKeyboardActive();
//		GcgApplication.getInputMethodManager().toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
	}

	public void onNewlineButtonClick() {
		this.fdkListener.fdkDispatchShiftedKeyEvent(KeyEvent.KEYCODE_ENTER);
	}

	public void onClearSearchButtonClick() {
		this.fdkListener.fdkDispatchShiftedKeyEvent(KeyEvent.KEYCODE_GRAVE);
	}

	public void onDeleteAllButtonClick() {
		this.multiShiftKeysetController.setFirstState();
		this.fdkListener.fdkDispatchKeyEvent(KeyEvent.KEYCODE_PERIOD);
	}

	public void onDeleteAllButtonLongClick() {
		this.rightKeypad.playSoundEffect(SoundEffectConstants.CLICK);
		this.fdkListener.fdkDispatchDeleteAll();
	}

	public void onDeleteWordButtonClick() {
		this.fdkListener.fdkDispatchKeyEvent(KeyEvent.KEYCODE_COMMA);
	}

	public void onDeleteWordButtonLongClick() {
		this.rightKeypad.playSoundEffect(SoundEffectConstants.CLICK);
		this.fdkListener.fdkDispatchDeleteWord();
	}

	public void onDeleteButtonClick() {
		this.fdkListener.fdkDispatchKeyEvent(KeyEvent.KEYCODE_DEL);
	}

	public void onDeleteButtonLongClick() {
		this.leftKeypad.playSoundEffect(SoundEffectConstants.CLICK);
		this.fdkListener.fdkDispatchKeyEvent(KeyEvent.KEYCODE_FORWARD_DEL);
	}

	public void onCursorLeftButtonClick() {
		this.fdkListener.fdkDispatchKeyEvent(KeyEvent.KEYCODE_DPAD_LEFT);
	}

	public void onCursorLeftButtonLongClick() {
		this.leftKeypad.playSoundEffect(SoundEffectConstants.CLICK);
		this.fdkListener.fdkDispatchAltKeyEvent(KeyEvent.KEYCODE_DPAD_LEFT);
	}

	public void onCursorUpButtonClick() {
		this.fdkListener.fdkFocusPreviousConsumer();
	}
	
	public void onPeriodButtonClick() {
		processButtonClick(KeyEvent.KEYCODE_PERIOD);
	}

	public void onPeriodButtonLongClick() {
		this.leftKeypad.playSoundEffect(SoundEffectConstants.CLICK);
		this.keycodeShifted = true;
		processButtonClick(KeyEvent.KEYCODE_SLASH);
	}

	public void onSpaceButtonClick() {
		processButtonClick(KeyEvent.KEYCODE_SPACE);
	}

	public void onSpaceButtonLongClick() {
		this.leftKeypad.playSoundEffect(SoundEffectConstants.CLICK);
		this.keycodeShifted = true;
		processButtonClick(KeyEvent.KEYCODE_SEMICOLON);
	}

	public void onCommaButtonClick() {
		this.keycodeBuffer = KeyEvent.KEYCODE_COMMA;
		processButtonClick(KeyEvent.KEYCODE_COMMA);
	}

	public void onCommaButtonLongClick() {
		this.leftKeypad.playSoundEffect(SoundEffectConstants.CLICK);
		this.keycodeShifted = true;
		processButtonClick(KeyEvent.KEYCODE_1);
	}

	public void onCursorRightButtonClick() {
		this.fdkListener.fdkDispatchKeyEvent(KeyEvent.KEYCODE_DPAD_RIGHT);
	}

	public void onCursorRightButtonLongClick() {
		this.leftKeypad.playSoundEffect(SoundEffectConstants.CLICK);
		this.fdkListener.fdkDispatchAltKeyEvent(KeyEvent.KEYCODE_DPAD_RIGHT);
	}

	public void onCursorDownButtonClick() {
		this.fdkListener.fdkFocusNextConsumer();
	}

	public void onCursorDownButtonLongClick() {
		this.leftKeypad.playSoundEffect(SoundEffectConstants.CLICK);
		this.fdkListener.fdkDispatchKeyEvent(KeyEvent.KEYCODE_MINUS);
	}

	public void onCursorUpButtonLongClick() {
		this.leftKeypad.playSoundEffect(SoundEffectConstants.CLICK);
		this.fdkListener.fdkDispatchKeyEvent(KeyEvent.KEYCODE_SLASH);
	}

	public GcgMultiShiftKeysetController getMultiShiftController() {
		return this.multiShiftKeysetController;
	}
	
	public boolean hasMultiShiftKeyset() {
		return this.multiShiftKeysetController != null;
	}

	@Override
	public boolean isMultipleSelections() {
		return false;
	}

	@Override
	public ArrayList<GcgMultiShiftState> getSupportedMultiShiftStateList() {
		return this.keyboardStyle.getSupportedMultiShiftStateList();
	}

	@Override
	public ArrayList<? extends GcgMultiShiftButton> getShiftButtonList() {
		return this.multiShiftKeysetButtons;
	}

	public void setFdkDictationResultsConsumer(FdkDictationResultsConsumer anFdkDictationResultsConsumer) {
		if(this.dictationResultsConsumer == anFdkDictationResultsConsumer) {
			enableKeyboard(true);
			return;
		}
		this.dictationResultsConsumer = anFdkDictationResultsConsumer;
		if(this.keyboardStyle != this.dictationResultsConsumer.getKeyboardStyle()) {
			changeKeyboardStyle(this.dictationResultsConsumer.getKeyboardStyle());
		}
		setMultiShiftState(this.dictationResultsConsumer.getMultiShiftState());
	}

	public GcgMultiShiftState getMultiShiftCancelState() {
		return this.multiShiftKeysetController.getCancelState();
	}

	public void enableKeyboard(boolean aBoolean) {
		if(this.keyboardEnabled == aBoolean) {
			return;
		}
		this.leftKeypad.enable(aBoolean);
		this.rightKeypad.enable(aBoolean);
	}
	
	public void onNextInputWidgetButton() {
		this.fdkListener.fdkFocusNextConsumer();
	}

	public void setMultiShiftState(GcgMultiShiftState aMultiShiftState) {
		if(this.multiShiftKeysetController != null) {
			this.multiShiftKeysetController.setState(aMultiShiftState);
		}
	}

	public GcgMultiShiftState getMultiShiftState() {
		return this.multiShiftKeysetController == null ? null : this.multiShiftKeysetController.getMultiShiftState();
	}
	
	public void resetMultiShiftStateAfterDictation(boolean aBoolean) {
		if(this.multiShiftKeysetController == null) {
			return;
		}
		this.multiShiftKeysetController.resetStateAfterDictation(aBoolean);
	}
	
	public void setFirstMultiShiftState() {
		this.multiShiftKeysetController.setFirstState();
	}
	
}
