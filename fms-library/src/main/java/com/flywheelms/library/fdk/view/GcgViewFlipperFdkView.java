/* @(#)GcgViewFlipperFdkView.java
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

package com.flywheelms.library.fdk.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.flywheelms.library.fdk.FdkHostSupport;
import com.flywheelms.library.fdk.enumerator.FdkKeyboardState;
import com.flywheelms.library.fdk.enumerator.FdkKeyboardStyle;
import com.flywheelms.library.fdk.interfaces.FdkDictationResultsConsumer;
import com.flywheelms.library.fdk.interfaces.FdkListener;
import com.flywheelms.library.fdk.widget.FdkKeyboard;
import com.flywheelms.library.gcg.GcgActivity;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipperChildView;

import java.util.ArrayList;

public abstract class GcgViewFlipperFdkView extends GcgViewFlipperChildView implements FdkListener {
	
	protected FdkKeyboard localFdkKeyboard;

	public GcgViewFlipperFdkView(Context aContext, AttributeSet attrs) {
		super(aContext, attrs);
	}
	
	@Override
	public void initialize(GcgActivity anGcgActivity, GcgViewFlipper aViewFlipper, int aSpinnableMenu, int aPageNumber) {
		super.initialize(anGcgActivity, aViewFlipper, aSpinnableMenu, aPageNumber);
		initLocalFdkKeyboard();
	}

	protected void initLocalFdkKeyboard() {
		this.localFdkKeyboard = new FdkKeyboard(
				getFdkKeyboardStyle(),
				fdkInitialKeyboardState(),
				getGcgThumbpadLeft(),
				getFdkKeypadPeerViewLeft(),
				getGcgThumbpadRight(),
				getFdkKeypadPeerViewRight(),
				this,
				this.gcgActivity );
	}
	
	protected abstract View getFdkKeypadPeerViewLeft();
	
	protected abstract FrameLayout getGcgThumbpadLeft();

	protected abstract View getFdkKeypadPeerViewRight();

	protected abstract FrameLayout getGcgThumbpadRight();

	protected FdkKeyboardStyle getFdkKeyboardStyle() {
		return FdkKeyboardStyle.widget_input__EDIT_TEXT;
	}

	protected FdkKeyboardState fdkInitialKeyboardState() {
		return FdkKeyboardState.AVAILABLE;
	}

	@Override
	protected void deactivateView() {
		super.deactivateView();
		if(this.localFdkKeyboard != null) {
			this.localFdkKeyboard.cancelDictation();
		}
	}
	
	@Override
	protected void activateView() {
		super.activateView();
		this.gcgActivity.setFdkKeyboard(this.localFdkKeyboard);
	}

	@Override
	public abstract void fdkDictationResults(ArrayList<String> aWordList);

	@Override
	public void fdkDispatchKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchKeyEventDownUp(getGcgActivity(), aKeycode);
	}

	@Override
	public void fdkDispatchShiftedKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchShiftedKeyEvent(getGcgActivity(), aKeycode);
	}

	@Override
	public void fdkDispatchAltKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchAltKeyEvent(getGcgActivity(), aKeycode);
	}

	@Override
	public void fdkDispatchAltShiftedKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchAltShiftedKeyEvent(getGcgActivity(), aKeycode);
	}

	@Override
	public void fdkDispatchDeleteAll() {
		getGcgActivity().fdkHostSupport.dispatchDeleteAll(getGcgActivity());
	}

	@Override
	public void fdkDispatchDeleteWord() {
		FdkHostSupport.dispatchDeleteWord(getGcgActivity());
		
	}

	@Override
	public void fdkDispatchControlKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchControlKeyEvent(getGcgActivity(), aKeycode);
	}

	@Override
	public void fdkDispatchControlShiftedKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchControlShiftedKeyEvent(getGcgActivity(), aKeycode);
	}

	@Override
	public void fdkFocusFirstConsumer() {
		return;
	}

	@Override
	public void fdkFocusNextConsumer() {
		return;
	}

	@Override
	public void fdkFocusPreviousConsumer() {
		return;
	}

	@Override
	public void fdkFocusConsumer(FdkDictationResultsConsumer anFdkDictationResultsConsumer) {
		// TODO
	}

	@Override
	public void fdkFocusConsumerFromTouch(FdkDictationResultsConsumer anFdkDictationResultsConsumer) {
		// TODO
	}

}
