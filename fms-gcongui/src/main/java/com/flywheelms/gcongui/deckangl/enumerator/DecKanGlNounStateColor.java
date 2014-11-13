/* @(#)DecKanGlNounStateColor.java
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

import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.deckangl.DecKanGlHelper;

import java.util.HashMap;

public enum DecKanGlNounStateColor {

    GRAY (R.string.deckangl__status_color__gray, R.drawable.deckangl__work_status__gray, 0),
    YELLOW (R.string.deckangl__status_color__yellow, R.drawable.deckangl__work_status__yellow, 1),
    BLACK(R.string.deckangl__status_color__black, R.drawable.deckangl__work_status__black, 2),
    PINK (R.string.deckangl__status_color__pink, R.drawable.deckangl__work_status__pink, 3),
    ORANGE (R.string.deckangl__status_color__orange, R.drawable.deckangl__work_status__orange, 4),
    GREEN (R.string.deckangl__status_color__green, R.drawable.deckangl__work_status__green, 5);

    private final int nameResourceId;
    private String name;
    private final int drawableResourceId;
    private Drawable drawable;
    private final int nounResourceArrayIndex;
    
    private static HashMap<String, DecKanGlNounStateColor> nameMap = new HashMap<String, DecKanGlNounStateColor>();
    static {
    	DecKanGlNounStateColor.nameMap.put(GRAY.getName(), GRAY);
    	DecKanGlNounStateColor.nameMap.put(YELLOW.getName(), YELLOW);
        DecKanGlNounStateColor.nameMap.put(BLACK.getName(), BLACK);
        DecKanGlNounStateColor.nameMap.put(PINK.getName(), PINK);
        DecKanGlNounStateColor.nameMap.put(ORANGE.getName(), ORANGE);
        DecKanGlNounStateColor.nameMap.put(GREEN.getName(), GREEN);
    }
    
    public static DecKanGlNounStateColor getObjectForName(String aName) {
    	return DecKanGlNounStateColor.nameMap.get(aName);
    }
    
    DecKanGlNounStateColor(int aNameResourceId, int aDrawableResourceId, int aNounResourceArrayIndex ) {
    	this.nameResourceId = aNameResourceId;
    	this.drawableResourceId = aDrawableResourceId;
    	this.nounResourceArrayIndex = aNounResourceArrayIndex;
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

	public Drawable getDrawable() {
		if(this.drawable == null) {
			this.drawable = DecKanGlHelper.getResources().getDrawable(this.drawableResourceId);
		}
		return this.drawable;
	}

	public int getNameResourceId() {
		return this.nameResourceId;
	}

	public int getDrawableResourceId() {
		return this.drawableResourceId;
	}
	
	public int getNounResourceArrayIndex() {
		return this.nounResourceArrayIndex;
	}

}