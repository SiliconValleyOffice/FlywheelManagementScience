/* @(#)GcgTabSpec.java
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

package com.flywheelms.gcongui.gcg.container.tabbed;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.flywheelms.gcongui.gcg.GcgApplication;

public class GcgTabSpec {
	
	private TextView tabView;
	private final View tabContentView;
	private final int labelDrawableResourceId;
	private Drawable labelDrawable;
	private final int labelTextResourceId;
	private boolean mustView;
	private boolean hasBeenViewed;
	
	public GcgTabSpec(View aTabContentView, int aLabelDrawableResourceId, int aLabelTextResourceId, boolean bMustView) {
		this.tabContentView = aTabContentView;
		this.labelDrawableResourceId = aLabelDrawableResourceId;
		this.labelTextResourceId = aLabelTextResourceId;
		this.tabContentView.setTag(getLabelText());
		this.mustView = bMustView;
	}

	public View getTabContentView() {
		return this.tabContentView;
	}

	public int getLabelDrawableResourceId() {
		return this.labelDrawableResourceId;
	}
	
	public Drawable getLabelDrawable() {
		if(this.labelDrawable == null) {
			this.labelDrawable = GcgApplication.getInstance().getResources().getDrawable(this.labelDrawableResourceId);
		}
		return this.labelDrawable;
	}

	public int getLabelTextResourceId() {
		return this.labelTextResourceId;
	}
	
	public CharSequence getLabelText() {
		return GcgApplication.getInstance().getResources().getString(this.labelTextResourceId);
	}

	public View getTabView() {
		return this.tabView;
	}
	
	public void setTabView(TextView aTabView) {
		this.tabView = aTabView;
	}

	public void setTabBackgroundResourceId(int aResourceId) {
		this.tabView.setBackgroundResource(aResourceId);
	}

	public boolean isMustView() {
		return this.mustView;
	}

	public void setMustView(boolean bMustView) {
		this.mustView = bMustView;
	}

	public boolean isHasBeenViewed() {
		return this.hasBeenViewed;
	}

	public void setHasBeenViewed(boolean bHasBeenViewed) {
		this.hasBeenViewed = bHasBeenViewed;
	}
	
	public boolean mustStillView() {
		return this.mustView && ! this.hasBeenViewed;
	}
	
}
