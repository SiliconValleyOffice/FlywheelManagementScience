/* @(#)FmmConfigurationTemplateSource.java
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

package com.flywheelms.library.fmm.repository;

import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.R;

import java.util.ArrayList;
import java.util.Arrays;

public enum FmmConfigurationTemplateSource implements GcgGuiable {
	
	PRIVATE ("Private", R.drawable.fmm_repository__scope__private),
	SHARED ("Shared", R.drawable.fmm_repository__scope__shared),
	TEAM ("Team", R.drawable.fmm_repository__scope__team),
	ASSETS ("FlywheelMS", R.drawable.flywheel_ms__16),
	CLOUD ("Template Cloud", R.drawable.gcg__cloud__16);
	
	private static final ArrayList<GcgGuiable> guiableList = new ArrayList<GcgGuiable>(Arrays.asList(values()));

	public static FmmConfigurationTemplateSource getObjectForName(String aName) {
		FmmConfigurationTemplateSource theFmmConfigurationTemplateSource = null;
		for(FmmConfigurationTemplateSource theTemplateSource : FmmConfigurationTemplateSource.values()) {
			if(theTemplateSource.getName().equals(aName)) {
				theFmmConfigurationTemplateSource = theTemplateSource;
				break;
			}
		}
		return theFmmConfigurationTemplateSource;
	}
	
	private final String name;
	private final int drawableResourceId;
	private Drawable drawable;
	
	private FmmConfigurationTemplateSource(String aName, int aDrawableResourceId) {
		this.name = aName;
		this.drawableResourceId = aDrawableResourceId;
	}

	@Override
	public String getLabelText() {
		return GcgApplication.getAppResources().getString(getLabelTextResourceId());
	}

	public static int getLabelTextResourceId() {
		return R.string.fmm__repository_scope;
	}

	@Override
	public Drawable getLabelDrawable() {
		return null;
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.gcg__null_drawable;
	}

	@Override
	public int getDataDrawableResourceId() {
		return getDrawableResourceId();
	}

	@Override
	public String getDataText() {
		return this.name;
	}

	@Override
	public Drawable getDataDrawable() {
		return GcgHelper.getDrawable(getDrawableResourceId());
	}

	public static ArrayList<GcgGuiable> getGuiableList() {
		return FmmConfigurationTemplateSource.guiableList;
	}
	
	public Drawable getDrawable() {
		if(this.drawable == null) {
			this.drawable = GcgHelper.getDrawable(this.drawableResourceId);
		}
		return this.drawable;
	}
	
	public int getDrawableResourceId() {
		return this.drawableResourceId;
	}
	
	public String getName() {
		return this.name;
	}
	
}
