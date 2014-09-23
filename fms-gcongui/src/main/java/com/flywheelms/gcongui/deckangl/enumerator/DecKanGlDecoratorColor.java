/* @(#)DecKanGlDecoratorColor.java
** 
** Copyright (C) 2012 by Steven D. Stamps
**
**             Trademarks & Copyrights
** Flywheel Management Science(TM), Flywheel Management Model(TM),
** Flywheel Story Editor(TM) and FlywheelMS(TM) are exclusive trademarks
** of Steven D. Stamps and may only be used freely for the purpose of
** identifying the unforked version of this software.  Subsequent forks
** may not use these trademarks.  All other rights are reserved.
** and FlywheelMS(TM) are exclusive trademarks of Steven D. Stamps
** and may only be used freely for the purpose of identifying the
** unforked version of this software.  Subsequent forks (if any) may
** not use these trademarks.  All other rights are reserved.
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

package com.flywheelms.gcongui.deckangl.enumerator;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.deckangl.DecKanGlHelper;

public enum DecKanGlDecoratorColor {

	BLUE (R.string.deckangl__decorator_color__blue),
	DISABLED (R.string.deckangl__decorator_color__disabled),
    GRAY (R.string.deckangl__decorator_color__gray),
    GREEN (R.string.deckangl__decorator_color__green),
    ORANGE (R.string.deckangl__decorator_color__orange),
    PINK (R.string.deckangl__decorator_color__pink),
    RED (R.string.deckangl__decorator_color__red),
    TRANSPARENT (R.string.deckangl__decorator_color__transparent),
    YELLOW (R.string.deckangl__decorator_color__yellow);

	private final int nameResourceId;
    private String name;
    
    DecKanGlDecoratorColor(int aNameResourceId) {
    	this.nameResourceId = aNameResourceId;
    }

	@Override
	public String toString() {
		return getName();
	}
	
	public String getName() {
		if(this.name == null) {
			this.name = DecKanGlHelper.getResources().getString(this.nameResourceId);
		}
		return this.name;
	}
	
	public int getNameResourceId() {
		return this.nameResourceId;
	}

}
