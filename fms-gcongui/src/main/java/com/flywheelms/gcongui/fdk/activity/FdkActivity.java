/* @(#)FdkActivity.java
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

package com.flywheelms.gcongui.fdk.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.fdk.FdkHostSupport;
import com.flywheelms.gcongui.fdk.interfaces.FdkDictationResultsConsumer;
import com.flywheelms.gcongui.fdk.interfaces.FdkListener;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;

public abstract class FdkActivity extends GcgActivity implements FdkListener {
	
	protected LinearLayout activityBodyParent;
	private static final int ACTIVITY_BODY_INDEX = 0;

	public FdkActivity(String anInitialHelpContextUrlString) {
		super(anInitialHelpContextUrlString);
	}

	@Override
	protected void onCreate(Bundle aSavedInstanceState) {
		super.onCreate(aSavedInstanceState);
	}

	@Override
	protected void initializeGui() {
		super.initializeGui();
		this.activityBodyParent = (LinearLayout) findViewById(R.id.fdk__activity__body);
		this.activityBodyParent.addView(getActivityBodyView(), ACTIVITY_BODY_INDEX);
		initializeGcgApplicationContextHeader();
	}

	@Override
	public FrameLayout getGcgThumbpadLeft() {
		return (FrameLayout) findViewById(R.id.gcg__thumbpad__left);
	}

	public FrameLayout getGcgThumbpadRight() {
		return (FrameLayout) findViewById(R.id.gcg__thumbpad__right);
	}

	protected abstract View getActivityBodyView();

	protected int getLeftMenuResourceId() {
		return 0;
	}

	protected int getRightMenuResourceId() {
		return 0;
	}

	@Override
	protected int getContentViewResourceId() {
		return R.layout.fdk_activity__base_layout;
	}

	@Override
	public void fdkDispatchDeleteAll() {
		this.fdkHostSupport.dispatchDeleteAll(this);
	}

	@Override
	public void fdkDispatchDeleteWord() {
		FdkHostSupport.dispatchDeleteWord(this);
	}

	@Override
	public void fdkDispatchKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchKeyEventDownUp(this, aKeycode);
	}

	@Override
	public void fdkDispatchAltKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchAltKeyEvent(this, aKeycode);
	}

	@Override
	public void fdkDispatchAltShiftedKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchAltShiftedKeyEvent(this, aKeycode);
	}

	@Override
	public void fdkDispatchControlKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchControlKeyEvent(this, aKeycode);
	}

	@Override
	public void fdkDispatchShiftedKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchShiftedKeyEvent(this, aKeycode);
	}

	@Override
	public void fdkDispatchControlShiftedKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchControlShiftedKeyEvent(this, aKeycode);
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

	@Override
	public void fdkResetMultiShiftState() {
		// TODO Auto-generated method stub
	}

	@Override
	public void initFdkDictationResultsConsumerMap() { return; }
	
	// for debug purposes
    @Override
	public boolean dispatchKeyEvent(KeyEvent event) {
    	return super.dispatchKeyEvent(event);
    }

}
