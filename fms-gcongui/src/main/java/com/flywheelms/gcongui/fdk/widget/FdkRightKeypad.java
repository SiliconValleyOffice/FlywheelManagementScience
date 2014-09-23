/* @(#)FdkRightKeypad.java
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

package com.flywheelms.gcongui.fdk.widget;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.gcg.button.multi_shift.GcgMultiShiftButton;

@SuppressLint("ViewConstructor")
public class FdkRightKeypad extends FdkKeypad {

	private Button clearSearchButton;
	private Button deleteAllButton;
	private Button deleteWordButton;
	private Button periodButton;
	private Button spaceButton;
	private Button commaButton;
	private Button cursorRightButton;
	private Button cursorDownButton;
	private GcgMultiShiftButton multiShiftButton;

	public FdkRightKeypad(FdkKeyboard aKeyboard) {
		super(aKeyboard);
	}

	@Override
	public View getPeerView() {
		return this.keyboard.getRightKeypadPeerView();
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public void initializeKeypad() {
		inflate(this.getContext(), this.keyboard.getKeyboardStyle().getRightKeypadResourceId(), this);
		switch (this.keyboard.getKeyboardStyle()) {
		case widget_input__PARAGRAPH:
			initButtonPeriod();
			initButtonSpace();
			initButtonComma();
			initButtonCursorDown();
			initButtonCursorRight();
			initMultiShiftButton();
			break;
		case widget_input__SEARCH:
			initButtonClearSearch();
			initButtonSpaceSimple();
			initButtonDeleteWordSimple();
			initButtonCursorRightSimple();
			break;
		case widget_input__SPINNER:
			initButtonCursorDownSimple();
			break;
		case widget_input__EDIT_TEXT:
			initButtonDeleteAll();
			initButtonSpace();
			initButtonDeleteWord();
			initButtonCursorDown();
			initButtonCursorRight();
			initMultiShiftButton();
			break;
		case widget_input__FILE_NAME:
			initButtonDeleteAll();
			initButtonCursorDownSimple();
			initButtonCursorRight();
			initMultiShiftButton();
			break;
		case EXTENDED:
		}
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void enable(boolean aBoolean) {
		switch (this.keyboard.getKeyboardStyle()) {
		case widget_input__PARAGRAPH:
			this.periodButton.setEnabled(aBoolean);
			this.spaceButton.setEnabled(aBoolean);
			this.commaButton.setEnabled(aBoolean);
			this.cursorDownButton.setEnabled(aBoolean);
			this.cursorRightButton.setEnabled(aBoolean);
			this.multiShiftButton.setEnabled(aBoolean);
			break;
		case widget_input__SEARCH:
			this.clearSearchButton.setEnabled(aBoolean);
			this.spaceButton.setEnabled(aBoolean);
			this.deleteWordButton.setEnabled(aBoolean);
			this.cursorRightButton.setEnabled(aBoolean);
			break;
		case widget_input__SPINNER:
			this.cursorDownButton.setEnabled(aBoolean);
			break;
		case widget_input__EDIT_TEXT:
			this.deleteAllButton.setEnabled(aBoolean);
			this.spaceButton.setEnabled(aBoolean);
			this.deleteWordButton.setEnabled(aBoolean);
			this.cursorDownButton.setEnabled(aBoolean);
			this.cursorRightButton.setEnabled(aBoolean);
			this.multiShiftButton.setEnabled(aBoolean);
			break;
		case widget_input__FILE_NAME:
			this.deleteAllButton.setEnabled(aBoolean);
			this.cursorDownButton.setEnabled(aBoolean);
			this.cursorRightButton.setEnabled(aBoolean);
			this.multiShiftButton.setEnabled(aBoolean);
			break;
		case EXTENDED:
		}
	}

	private void initMultiShiftButton() {
		this.multiShiftButton = (GcgMultiShiftButton) findViewById(R.id.multi_shift__right_button);
		this.multiShiftButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkRightKeypad.this.keyboard.getMultiShiftController().onRightButtonClick();
			}
		});
	}
	
	public GcgMultiShiftButton getMultiShiftButton() {
		return this.multiShiftButton;
	}

	@Override
	public void updateKeypadContainer() {
		if(this.keyboard.isActive()) {
			this.keyboard.getRightKeypadContainer().removeAllViews();
			this.keyboard.getRightKeypadContainer().addView(this);
		}
	}
	
	////  button initialization  ////

	private void initButtonCursorRightSimple() {
		this.cursorRightButton = (Button) findViewById(R.id.fdk__button__cursor_right);
		this.cursorRightButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkRightKeypad.this.keyboard.onCursorRightButtonClick();
			}
		});
	}

	private void initButtonCursorRight() {
		initButtonCursorRightSimple();
		this.cursorRightButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				FdkRightKeypad.this.keyboard.onCursorRightButtonLongClick();
				return true;
			}
		});
	}

	private void initButtonCursorDownSimple() {
		this.cursorDownButton = (Button) findViewById(R.id.fdk__button__cursor_down);
		this.cursorDownButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkRightKeypad.this.keyboard.onCursorDownButtonClick();
			}
		});
	}

	private void initButtonCursorDown() {
		initButtonCursorDownSimple();
		this.cursorDownButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				FdkRightKeypad.this.keyboard.onCursorDownButtonLongClick();
				return true;
			}
		});
	}

	private void initButtonComma() {
		this.commaButton = (Button) findViewById(R.id.fdk__Button__comma);
		this.commaButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkRightKeypad.this.keyboard.onCommaButtonClick();
			}
		});
		this.commaButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				FdkRightKeypad.this.keyboard.onCommaButtonLongClick();
				return true;
			}
		});
	}

	private void initButtonSpaceSimple() {
		this.spaceButton = (Button) findViewById(R.id.fdk__button__space);
		this.spaceButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkRightKeypad.this.keyboard.onSpaceButtonClick();
			}
		});
	}

	private void initButtonSpace() {
		initButtonSpaceSimple();
		this.spaceButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				FdkRightKeypad.this.keyboard.onSpaceButtonLongClick();
				return true;
			}
		});
	}

	private void initButtonPeriod() {
		this.periodButton = (Button) findViewById(R.id.fdk__button__period);
		this.periodButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkRightKeypad.this.keyboard.onPeriodButtonClick();
			}
		});
		this.periodButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				FdkRightKeypad.this.keyboard.onPeriodButtonLongClick();
				return true;
			}
		});
	}

	private void initButtonDeleteAllSimple() {
		this.deleteAllButton = (Button) findViewById(R.id.fdk__button__delete_all);
		this.deleteAllButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkRightKeypad.this.keyboard.onDeleteAllButtonClick();
			}
		});
	}

	private void initButtonDeleteAll() {
		initButtonDeleteAllSimple();
		this.deleteAllButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				FdkRightKeypad.this.keyboard.onDeleteAllButtonLongClick();
				return true;
			}
		});
	}

	private void initButtonDeleteWordSimple() {
		this.deleteWordButton = (Button) findViewById(R.id.fdk__button__delete_word);
		this.deleteWordButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkRightKeypad.this.keyboard.onDeleteWordButtonClick();
			}
		});
	}

	private void initButtonDeleteWord() {
		initButtonDeleteWordSimple();
		this.deleteWordButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				FdkRightKeypad.this.keyboard.onDeleteWordButtonLongClick();
				return true;
			}
		});
	}

	private void initButtonClearSearch() {
		this.clearSearchButton = (Button) findViewById(R.id.fdk__button__clear_search);
		this.clearSearchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkRightKeypad.this.keyboard.onClearSearchButtonClick();
			}
		});
	}

	////  getters  ////

	public Button getPeriodButton() {
		return this.periodButton;
	}

	public Button getSpaceButton() {
		return this.spaceButton;
	}

	public Button getCommaButton() {
		return this.commaButton;
	}

	public Button getRightArrowButton() {
		return this.cursorRightButton;
	}

	public Button getDownArrowButton() {
		return this.cursorDownButton;
	}
	
}
