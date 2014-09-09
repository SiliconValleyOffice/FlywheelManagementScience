/* @(#)FseParagraphSpinner.java
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

package com.flywheelms.library.fse.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.fse.enumerator.FseParagraphStyle;
import com.flywheelms.library.fse.perspective_flipper.FsePerspectiveFlipper;
import com.flywheelms.library.fse.views.FseParagraphView;
import com.flywheelms.library.gcg.button.multi_shift.GcgMultiShiftState;

@SuppressLint("ViewConstructor")
public class FseParagraphSpinner extends Spinner {
	
	private static final int spinner_background__FOCUS = R.drawable.fse__paragraph_spinner__focus;
	private static final int spinner_background__FOCUS_LOCKED = R.drawable.fse__paragraph_spinner__focus__locked;
	private static final int spinner_background__FOCUS_LOST = R.drawable.fse__paragraph_spinner__focus_lost;
	private static final int spinner_background__FOCUS_LOST__LOCKED = R.drawable.fse__paragraph_spinner__focus_lost__locked;
	private FseParagraphView paragraphView;
	private boolean isHistory = false;
	
	public FseParagraphSpinner(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public void initializeParagraphSpinner(FseParagraphView anFseParagraphView) {
		this.paragraphView = anFseParagraphView;
		configureAdapter(this.paragraphView);
		this.isHistory = anFseParagraphView.isHistory();
		createListeners();
	}

	private void configureAdapter(FseParagraphView anFseParagraphView) {
		ArrayAdapter<FseParagraphStyle> theArrayAdapter = new ArrayAdapter<FseParagraphStyle>(
				anFseParagraphView.getParagraphEditor().getContext(),
				R.layout.fse__paragraph_spinner__view,
				anFseParagraphView.getStyle().getParagraphSpinnerOptions() ) {

					@Override
					public View getDropDownView(int anItemPosition, View anExistingView, ViewGroup aParentView) {
				        TextView text = new TextView(getContext());
				        text.setTextColor(Color.BLUE);
				        text.setText(getItemAtPosition(anItemPosition).toString());
				        text.setPadding(5, 15, 0, 15);
				        return text;
					}
				};
		this.setAdapter(theArrayAdapter);
		this.setSelection(theArrayAdapter.getPosition(anFseParagraphView.getStyle()), true);
	}
	
//	public View getCustomView(int anItemPosition, View anExistingView, ViewGroup aParentView) {
//		TextView theTextView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.fse__paragraph_spinner__item_view, aParentView, false);
//		theTextView.setText(getItemAtPosition(anItemPosition).toString());
//		return theTextView;
//	}
	
	public FseParagraphView getParagraph() {
		return this.paragraphView;
	}

	private void createListeners() {
		setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> anAdapterView, View aView,
					int aPosition, long anId) {
				FseParagraphStyle theParagraphStyle = (FseParagraphStyle) anAdapterView.getItemAtPosition(aPosition);
				getParagraph().changeStyle(theParagraphStyle);
				getParagraph().getSpinner().setTextColorResourceId(
						FseParagraphSpinner.this.paragraphView.getParagraphEditor().getParagraphSpinnerSelectionMode().getTextColorResourceId());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				return;
			}
		});
		
		setOnTouchListener(new OnTouchListener() {

			GcgClickState clickState = GcgClickState.SINGLE;
			long doubleClickTimer;
			long tripleClickTimer;
			long longClickTimer;

			@Override
			public boolean onTouch(View aView, MotionEvent aMotionEvent) {
				if (aMotionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					ViewGroup aViewGroup = (ViewGroup) aView;
					aViewGroup.getChildAt(0).setBackgroundResource(R.color.WhiteSmoke);
					long theCurrentTime = System.currentTimeMillis();
					switch (this.clickState) {
					case TRIPLE:
						this.clickState = GcgClickState.SINGLE;
						this.doubleClickTimer = theCurrentTime;
						break;
					case DOUBLE:
						this.clickState = (theCurrentTime - this.tripleClickTimer) < GcgClickState.TRIPLE.getClickDuration() ? GcgClickState.TRIPLE : GcgClickState.SINGLE;
						break;
					default:  // SINGLE
						this.clickState = (theCurrentTime - this.doubleClickTimer) < GcgClickState.DOUBLE.getClickDuration() ? GcgClickState.DOUBLE : GcgClickState.SINGLE;
						if(this.clickState == GcgClickState.DOUBLE) {
							this.tripleClickTimer = theCurrentTime;
						} else {
							this.doubleClickTimer = theCurrentTime;
						}
					}
					this.longClickTimer = theCurrentTime;
				}
				if (aMotionEvent.getAction() == MotionEvent.ACTION_UP) {
					((ViewGroup) aView).getChildAt(0).setBackgroundResource(R.color.pdf__transparent);
					if(isParagraphSpinnerSelectionModeOff() && System.currentTimeMillis() - this.longClickTimer > GcgClickState.LONG.getClickDuration()) {  // long click
						FseParagraphSpinner.this.performClick();
					} else {
						FseParagraphSpinner.this.paragraphView.getParagraphEditor().onParagraphSpinnerTouch(
								FseParagraphSpinner.this.getParagraph(), this.clickState);
					}
					setFocus();
				}
				return true;
			}
		} );
	}
	
	protected FsePerspectiveFlipper getDocumentView() {
		return getParagraph().getParagraphEditor().getDocumentView();
	}
	
	public void setFocus() {
		if(this.paragraphView.isLocked()) {
			this.setBackgroundResource(spinner_background__FOCUS_LOCKED);
		} else {
			this.setBackgroundResource(spinner_background__FOCUS);
		}
	}
	
	public void setLostFocus() {
		if(this.paragraphView.isLocked()) {
			this.setBackgroundResource(spinner_background__FOCUS_LOST__LOCKED);
		} else {
			this.setBackgroundResource(spinner_background__FOCUS_LOST);
		}
		getChildAt(0).setBackgroundResource(R.color.pdf__transparent);  // extra cleanup
	}

	public void setStyle(FseParagraphStyle aParagraphStyle) {
		setSelection(getIndex(aParagraphStyle), true);
	}
	
	private int getIndex(FseParagraphStyle aParagraphStyle) {
		int theIndex = 0;
		for (int i=0; i < getCount(); i++) {
			if (getItemAtPosition(i).equals(aParagraphStyle)){
				theIndex = i;
			}
		}
		return theIndex;
	}
	
	private TextView getTextView() {
		return (TextView) findViewById(R.id.paragraph_spinner_text);
	}
	
	public void setTextColorResourceId(int aTextColorResourceId) {
		TextView theTextView = getTextView();
		if(theTextView != null) {
			theTextView.setTextColor(getResources().getColor(aTextColorResourceId));
		}
	}

	public void lock() {
		if(this.paragraphView.hasEditorFocus()) {
			this.setBackgroundResource(R.color.fse__paragraph_spinner__focus__locked);
		} else {
			this.setBackgroundResource(R.color.fse__paragraph_spinner__focus_lost__locked);
		}
	}

	public void unlock() {
		if(this.paragraphView.hasEditorFocus()) {
			this.setBackgroundResource(R.color.fse__paragraph_spinner__focus);
		} else {
			this.setBackgroundResource(R.color.fse__paragraph_spinner__focus_lost);
		}
	}
	
	public boolean isParagraphSpinnerSelectionModeOff() {
		return this.paragraphView.getParagraphEditor().getParagraphSpinnerSelectionMode() == GcgMultiShiftState.OFF;
	}
	
	public void setIsHistory(boolean aBoolean) {
		this.isHistory = aBoolean;
	}

}
