/* @(#)FdkLeftKeypad.java
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
public class FdkLeftKeypad extends FdkKeypad {

	private Button startStopButton;
	private Button newlineButton;
	private Button backspaceButton;
	private Button cursorUpButton;
	private Button cursorLeftButton;
	private GcgMultiShiftButton multiShiftButton;

	public FdkLeftKeypad(FdkKeyboard aKeyboard) {
		super(aKeyboard);
	}

	@Override
	public View getPeerView() {
		return this.keyboard.getLeftKeypadPeerView();
	}
	
	@SuppressWarnings("incomplete-switch")
	@Override
	public void initializeKeypad() {
		inflate(this.getContext(), this.keyboard.getKeyboardStyle().getLeftKeypadResourceId(), this);
		switch (this.keyboard.getKeyboardStyle()) {
		case widget_input__PARAGRAPH:
			initButtonStartStop();
			initButtonNewline();
			initButtonBackspace();
			initButtonCursorUp();
			initButtonCursorLeft();
			initMultiShiftButton();
			break;
		case widget_input__SEARCH:
			initButtonStartStop();
			initButtonBackspace();
			initButtonCursorLeftSimple();
			break;
		case widget_input__SPINNER:
			initButtonStartStop();
			initButtonCursorUpSimple();
			break;
		case widget_input__EDIT_TEXT:
			initButtonStartStop();
			initButtonBackspace();
			initButtonCursorUp();
			initButtonCursorLeft();
			initMultiShiftButton();
			break;
		case widget_input__FILE_NAME:
			initButtonStartStop();
			initButtonBackspace();
			initButtonCursorUpSimple();
			initButtonCursorLeft();
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
			this.startStopButton.setEnabled(aBoolean);
			this.newlineButton.setEnabled(aBoolean);
			this.backspaceButton.setEnabled(aBoolean);
			this.cursorUpButton.setEnabled(aBoolean);
			this.cursorLeftButton.setEnabled(aBoolean);
			this.multiShiftButton.setEnabled(aBoolean);
			break;
		case widget_input__SEARCH:
			this.startStopButton.setEnabled(aBoolean);
			this.backspaceButton.setEnabled(aBoolean);
			this.cursorLeftButton.setEnabled(aBoolean);
			break;
		case widget_input__SPINNER:
			this.startStopButton.setEnabled(aBoolean);
			this.cursorUpButton.setEnabled(aBoolean);
			break;
		case widget_input__EDIT_TEXT:
			this.startStopButton.setEnabled(aBoolean);
			this.backspaceButton.setEnabled(aBoolean);
			this.cursorUpButton.setEnabled(aBoolean);
			this.cursorLeftButton.setEnabled(aBoolean);
			this.multiShiftButton.setEnabled(aBoolean);
			break;
		case widget_input__FILE_NAME:
			this.startStopButton.setEnabled(aBoolean);
			this.backspaceButton.setEnabled(aBoolean);
			this.cursorUpButton.setEnabled(aBoolean);
			this.cursorLeftButton.setEnabled(aBoolean);
			this.multiShiftButton.setEnabled(aBoolean);
			break;
		case EXTENDED:
		}
	}

	private void initMultiShiftButton() {
		this.multiShiftButton = (GcgMultiShiftButton) findViewById(R.id.multi_shift__left_button);
		this.multiShiftButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkLeftKeypad.this.keyboard.getMultiShiftController().onLeftButtonClick();
			}
		});
	}
	
	public GcgMultiShiftButton getMultiShiftButton() {
		return this.multiShiftButton;
	}

	@Override
	public void updateKeypadContainer() {
		if(this.keyboard.isActive()) {
			this.keyboard.getLeftKeypadContainer().removeAllViews();
			this.keyboard.getLeftKeypadContainer().addView(this);
		}
	}
	
	////  button initialization  ////

	private void initButtonCursorLeftSimple() {
		this.cursorLeftButton = (Button) findViewById(R.id.fdk__button__cursor_left);
		this.cursorLeftButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkLeftKeypad.this.keyboard.onCursorLeftButtonClick();
			}
		});
	}

	private void initButtonCursorLeft() {
		initButtonCursorLeftSimple();
		this.cursorLeftButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				FdkLeftKeypad.this.keyboard.onCursorLeftButtonLongClick();
				return true;
			}
		});
	}

	private void initButtonCursorUpSimple() {
		this.cursorUpButton = (Button) findViewById(R.id.fdk__button__cursor_up);
		this.cursorUpButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkLeftKeypad.this.keyboard.onCursorUpButtonClick();
			}
		});
	}

	private void initButtonCursorUp() {
		initButtonCursorUpSimple();
		this.cursorUpButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				FdkLeftKeypad.this.keyboard.onCursorUpButtonLongClick();
				return true;
			}
		});
	}

	private void initButtonBackspace() {
		this.backspaceButton = (Button) findViewById(R.id.fdk__button__delete);
		this.backspaceButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkLeftKeypad.this.keyboard.onDeleteButtonClick();
			}
		});
		this.backspaceButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				FdkLeftKeypad.this.keyboard.onDeleteButtonLongClick();
				return true;
			}
		});
	}

	private void initButtonNewline() {
		this.newlineButton = (Button) findViewById(R.id.fdk__button__newline);
		this.newlineButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkLeftKeypad.this.keyboard.onNewlineButtonClick();
			}
		});
	}

	private void initButtonStartStop() {
		this.startStopButton = (Button) findViewById(R.id.fdk__button__start);
		this.startStopButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FdkLeftKeypad.this.keyboard.onStartButtonClick();
			}
		});
		this.startStopButton.setOnLongClickListener(new OnLongClickListener() {
			
			@Override
			public boolean onLongClick(View v) {
				FdkLeftKeypad.this.keyboard.onStartButtonLongClick();
				return true;
			}
		});
	}
	
	////  getters  ////

	public Button getStartStopButton() {
		return this.startStopButton;
	}

	public Button getNewlineButton() {
		return this.newlineButton;
	}

	public Button getDeleteButton() {
		return this.backspaceButton;
	}

	public Button getLeftArrowButton() {
		return this.cursorLeftButton;
	}

	public Button getUpArrowButton() {
		return this.cursorUpButton;
	}

}
