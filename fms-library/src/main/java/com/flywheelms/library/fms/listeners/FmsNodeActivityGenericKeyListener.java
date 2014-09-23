/* @(#)FmsNodeActivityGenericKeyListener.java
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

package com.flywheelms.library.fms.listeners;

import android.text.Editable;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;

import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.library.fse.perspective_flipper.FsePerspectiveFlipper;

public class FmsNodeActivityGenericKeyListener implements KeyListener {
	
	private FsePerspectiveFlipper fseDocumentViewFlipper;
	private boolean eatKeyStrokes;
	
	public FmsNodeActivityGenericKeyListener(FsePerspectiveFlipper anFseDocumentView, boolean bEatKeyStrokes) {
		this.fseDocumentViewFlipper = anFseDocumentView;
		this.eatKeyStrokes = bEatKeyStrokes;
	}
	
	@Override
	public boolean onKeyUp(View view, Editable text, int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean onKeyOther(View view, Editable text, KeyEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean onKeyDown(View aView, Editable anEditable, int aKeyCode, KeyEvent aKeyEvent) {
	    if (aKeyCode == KeyEvent.KEYCODE_RIGHT_BRACKET) {
	    	if(aKeyEvent.isCtrlPressed()) {
		    	if(aKeyEvent.isShiftPressed()) {
		    		this.fseDocumentViewFlipper.getFmsNodeActivity().navigateRightButtonClick();
		    	} else {
		    		this.fseDocumentViewFlipper.flipNext();
		    	}
	    		return true;
	    	}
	    	if(aKeyEvent.isAltPressed()) {
		    	if(aKeyEvent.isShiftPressed()) {
		    		this.fseDocumentViewFlipper.getFmsNodeActivity().navigateLastButtonClick();
		    	} else {
		    		// something in the future
		    	}
	    		return true;
	    	}
	    	return true;
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_LEFT_BRACKET) {
	    	if(aKeyEvent.isCtrlPressed()) {
		    	if(aKeyEvent.isShiftPressed()) {
		    		this.fseDocumentViewFlipper.getFmsNodeActivity().navigateLeftButtonClick();
		    	} else {
		    		this.fseDocumentViewFlipper.flipPrevious();
		    	}
	    		return true;
	    	}
	    	if(aKeyEvent.isAltPressed()) {
		    	if(aKeyEvent.isShiftPressed()) {
		    		this.fseDocumentViewFlipper.getFmsNodeActivity().navigateFirstButtonClick();
		    	} else {
		    		// something in the future
		    	}
	    		return true;
	    	}
	    	return true;
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_BACKSLASH) {
	    	if(aKeyEvent.isCtrlPressed() && aKeyEvent.isShiftPressed()) {
	    		this.fseDocumentViewFlipper.getFmsNodeActivity().navigationSpinnerSelect();
	    	}
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_S && aKeyEvent.isCtrlPressed()) {
	    	this.fseDocumentViewFlipper.getFmsNodeActivity().saveAllDataModifications();
	    	return true;
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_N && aKeyEvent.isCtrlPressed()) {
	    	this.fseDocumentViewFlipper.getFmsNodeActivity().newFractal();
	    	return true;
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_R && aKeyEvent.isCtrlPressed()) {
	    	this.fseDocumentViewFlipper.getFmsNodeActivity().requestRevertAllModifiedData();
	    	return true;
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_PERIOD && aKeyEvent.isShiftPressed()) {
	    	if(aKeyEvent.isCtrlPressed()) {
	    		this.fseDocumentViewFlipper.getActiveDocumentSectionView().rightMenuActivateSpinner();
	    		return true;
	    	}
	    	return true;
	    }
	    if (aKeyCode == KeyEvent.KEYCODE_COMMA && aKeyEvent.isShiftPressed()) {
	    	if(aKeyEvent.isCtrlPressed()) {
	    		this.fseDocumentViewFlipper.getFmsNodeActivity().leftMenuClickSpinner();
	    		return true;
	    	}
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
	    		this.fseDocumentViewFlipper.getFmsNodeActivity().leftMenuClickItem(Integer.valueOf(GcgHelper.getIntegerFromKeycode(aKeyCode)));
	    		return true;
	    	}
	    	if(aKeyEvent.isCtrlPressed() && aKeyEvent.isAltPressed()) {
//	    		FseParagraphContentTextView.this.getParagraphView().
	    		return true;
	    	}
	    	return false;
		}
	    return this.eatKeyStrokes;
	}
	
	@Override
	public int getInputType() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void clearMetaKeyState(View view, Editable content, int states) {
		// TODO Auto-generated method stub
	}

}
