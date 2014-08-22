/* @(#)FdkWizardStepView.java
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

import java.util.ArrayList;
import java.util.LinkedHashMap;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.flywheelms.library.R;
import com.flywheelms.library.fdk.FdkHostSupport;
import com.flywheelms.library.fdk.enumerator.FdkKeyboardStyle;
import com.flywheelms.library.fdk.interfaces.FdkDictationResultsConsumer;
import com.flywheelms.library.fdk.interfaces.FdkListener;
import com.flywheelms.library.fdk.widget.FdkKeyboard;
import com.flywheelms.library.gcg.GcgActivity;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipper;
import com.flywheelms.library.gcg.wizard.step.GcgWizardStepView;

public abstract class FdkWizardStepView extends GcgWizardStepView implements FdkListener {
	
	protected FdkKeyboard localFdkKeyboard;
	protected LinkedHashMap<View, FdkDictationResultsConsumer> fdkDictationResultsConsumerMap = new LinkedHashMap<View, FdkDictationResultsConsumer>();
	ArrayList<FdkDictationResultsConsumer> fdkDictationResultsConsumerList = new ArrayList<FdkDictationResultsConsumer>();
	protected FdkDictationResultsConsumer currentFdkDictationResultsConsumer;

	public FdkWizardStepView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View aView, boolean hasFocus) {
				if(! FdkWizardStepView.this.fdkDictationResultsConsumerMap.containsKey(aView)) {
					FdkWizardStepView.this.fdkFocusConsumer(FdkWizardStepView.this.currentFdkDictationResultsConsumer);
				}
			}
		});
	}

	@Override
	protected final int getViewLayoutResourceId() {
		return R.layout.fdk_wizard_step__base_layout;
	}

	protected abstract int getWizardStepBodyResourceId();
	
	protected void addFdkDictationResultsConsumer(FdkDictationResultsConsumer aDictationResultsConsumer) {
		this.fdkDictationResultsConsumerMap.put(aDictationResultsConsumer.getViewToFocus(), aDictationResultsConsumer);
		this.fdkDictationResultsConsumerList.add(aDictationResultsConsumer);
		aDictationResultsConsumer.setFdkCursorEnabled(true);
		aDictationResultsConsumer.setFdkListener(this);
	}
	
	@Override
	public void initialize(GcgActivity aGcgActivity, GcgViewFlipper aViewFlipper, int aPageNumber) {
		aGcgActivity.getLayoutInflater().inflate(
				getWizardStepBodyResourceId(),
				(LinearLayout) findViewById(R.id.fdk__wizard_step__body) );
		super.initialize(aGcgActivity, aViewFlipper, aPageNumber);
		initLocalFdkKeyboard();
		initGuiWidgets();
		initFdkDictationResultsConsumerMap();
		fdkFocusConsumer(getFdkDictationResultsConsumerList().get(0));
	}
	
	@Override
	public abstract void initFdkDictationResultsConsumerMap();

	protected abstract void initGuiWidgets();

	protected void initLocalFdkKeyboard() {
		this.localFdkKeyboard = new FdkKeyboard(
				getFdkKeyboardStyle(),
				getGcgThumbpadLeft(),
				getGcgThumbpadRight(),
				this,
				this.gcgActivity );
	}

	public FrameLayout getGcgThumbpadLeft() {
		return (FrameLayout) findViewById(R.id.gcg__thumbpad__left);
	}

	public FrameLayout getGcgThumbpadRight() {
		return (FrameLayout) findViewById(R.id.gcg__thumbpad__right);
	}

	protected FdkKeyboardStyle getFdkKeyboardStyle() {
		return FdkKeyboardStyle.widget_input__EDIT_TEXT;
	}

	@Override
	protected void deactivateView() {
		super.deactivateView();
		if(this.localFdkKeyboard != null) {
			this.localFdkKeyboard.cancelDictation();
		}
	}
	
	@Override
	public void activateView() {
		super.activateView();
		this.gcgActivity.setFdkKeyboard(this.localFdkKeyboard);
		fdkFocusConsumer(this.currentFdkDictationResultsConsumer);
	}

	@Override
	public void fdkDispatchDeleteAll() {
		getGcgActivity().fdkHostSupport.dispatchDeleteAll(getGcgActivity());
	}

	@Override
	public void fdkDispatchDeleteWord() {
		FdkHostSupport.deleteWord((EditText) this.currentFdkDictationResultsConsumer.getViewToFocus());
	}

	@Override
	public void fdkDispatchKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchKeyEventDownUp(getGcgActivity(), aKeycode);
	}

	@Override
	public void fdkDispatchShiftedKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchShiftedKeyEvent(getGcgActivity(), aKeycode);
	}

	@Override
	public void fdkDispatchControlShiftedKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchControlShiftedKeyEvent(getGcgActivity(), aKeycode);
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
	public void fdkDispatchControlKeyEvent(int aKeycode) {
		FdkHostSupport.dispatchControlKeyEvent(getGcgActivity(), aKeycode);
	}
	
	private ArrayList<FdkDictationResultsConsumer> getFdkDictationResultsConsumerList() {
		if(this.fdkDictationResultsConsumerList == null) {
			this.fdkDictationResultsConsumerList = new ArrayList<FdkDictationResultsConsumer>(this.fdkDictationResultsConsumerMap.values());
		}
		return this.fdkDictationResultsConsumerList;
	}

	@Override
	public void fdkFocusFirstConsumer() {
		fdkFocusConsumer(this.fdkDictationResultsConsumerList.get(0));
	}

	@Override
	public void fdkFocusNextConsumer() {
		FdkDictationResultsConsumer theNextConsumer = FdkHostSupport.getNextFdkDictationResultsConsumer(this.currentFdkDictationResultsConsumer, getFdkDictationResultsConsumerList());
		fdkFocusConsumer(theNextConsumer);
	}

	@Override
	public void fdkFocusPreviousConsumer() {
		FdkDictationResultsConsumer theNextPreviousConsumer = FdkHostSupport.getPreviousFdkDictationResultsConsumer(this.currentFdkDictationResultsConsumer, getFdkDictationResultsConsumerList());
		fdkFocusConsumer(theNextPreviousConsumer);
	}
	
	@Override
	public void fdkDictationResults(ArrayList<String> aWordList) {
		this.currentFdkDictationResultsConsumer.onDictationResults(aWordList);
		this.currentFdkDictationResultsConsumer.getViewToFocus().requestFocusFromTouch();
	}

	@Override
	public void fdkFocusConsumer(FdkDictationResultsConsumer anFdkDictationResultsConsumer) {
		fdkFocusConsumerStuff(anFdkDictationResultsConsumer, false);
	}

	@Override
	public void fdkFocusConsumerFromTouch(FdkDictationResultsConsumer anFdkDictationResultsConsumer) {
		fdkFocusConsumerStuff(anFdkDictationResultsConsumer, true);
	}

	public void fdkFocusConsumerStuff(FdkDictationResultsConsumer anFdkDictationResultsConsumer, boolean bFromTouch) {
		if(anFdkDictationResultsConsumer == null) {
			return;
		}
		if(this.currentFdkDictationResultsConsumer != null && this.currentFdkDictationResultsConsumer != anFdkDictationResultsConsumer) {
			this.currentFdkDictationResultsConsumer.setMultiShiftState(this.localFdkKeyboard.getMultiShiftState());
		}
		this.currentFdkDictationResultsConsumer = anFdkDictationResultsConsumer;
		if(! bFromTouch) {
			this.currentFdkDictationResultsConsumer.getViewToFocus().requestFocusFromTouch();
		}
		this.localFdkKeyboard.changeKeyboardStyle(this.currentFdkDictationResultsConsumer.getKeyboardStyle());
		this.localFdkKeyboard.resetMultiShiftStateAfterDictation(this.currentFdkDictationResultsConsumer.isResetMultiShiftStateAfterDictation());
		this.localFdkKeyboard.setMultiShiftState(this.currentFdkDictationResultsConsumer.getMultiShiftState());
	}

	@Override
	public void fdkResetMultiShiftState() {
		if(this.localFdkKeyboard == null || this.currentFdkDictationResultsConsumer == null) {
			return;
		}
		this.localFdkKeyboard.setMultiShiftState(this.currentFdkDictationResultsConsumer.getInitialMultiShiftState());
	}

}
