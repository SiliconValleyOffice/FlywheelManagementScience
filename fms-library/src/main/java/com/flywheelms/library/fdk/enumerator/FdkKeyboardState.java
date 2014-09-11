/* @(#)FdkState.java
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

package com.flywheelms.library.fdk.enumerator;

import android.graphics.drawable.Drawable;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.GcgApplication;

import java.util.Hashtable;

public enum FdkKeyboardState {
	
	// NOTE - this is the keyboard state, not the dictation state (which is boolean)

	ACTIVE ("Active", R.drawable.flywheel_dictation_keyboard_off, R.color.green),
	ALWAYS_ACTIVE ("AlwaysActive", R.drawable.gcg__null_drawable_32, R.color.green),
	AVAILABLE ("Available", R.drawable.flywheel_dictation_keyboard, R.color.yellow),
	DISABLED ("Disabled", R.drawable.gcg__null_drawable_32, R.color.gray);
	
	private static final Hashtable<String, FdkKeyboardState> nameTable = new Hashtable<String, FdkKeyboardState>();
	static {
		FdkKeyboardState.nameTable.put(AVAILABLE.getName(), AVAILABLE);
		FdkKeyboardState.nameTable.put(ACTIVE.getName(), ACTIVE);
		FdkKeyboardState.nameTable.put(ALWAYS_ACTIVE.getName(), ALWAYS_ACTIVE);
		FdkKeyboardState.nameTable.put(DISABLED.getName(), DISABLED);
	}
	public static FdkKeyboardState getObjectForName(String aName) {
		return FdkKeyboardState.nameTable.get(aName);
	}
	
	private final String name;
	private final int drawableResourceId;
	private Drawable drawable;
	private final int colorResourceId;
	
	private FdkKeyboardState(String aName, int aDrawableResourceId, int aColorResourceId) {
		this.name = aName;
		this.drawableResourceId = aDrawableResourceId;
		this.colorResourceId = aColorResourceId;
	}

	public String getName() {
		return this.name;
	}

	public int getDrawableResourceId() {
		return this.drawableResourceId;
	}

	public Drawable getDrawable() {
		if(this.drawable == null) {
			this.drawable = GcgApplication.getContext().getResources().getDrawable(this.drawableResourceId);
		}
		return this.drawable;
	}
	
	public int getColorResourceId() {
		return this.colorResourceId;
	}
	
	@Override
	public String toString() {
		return getName();
	}

}
