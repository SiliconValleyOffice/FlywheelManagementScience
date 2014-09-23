/* @(#)FseStyleModificationState.java
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

package com.flywheelms.library.fse.enumerator;

import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.library.R;

import java.util.Hashtable;

public enum FseStyleModificationState implements ModificationState {
	
	MODIFIED ("Modified", R.drawable.fse__paragraph_style, R.drawable.fse__paragraph_markup__style, 0, R.drawable.pdf_style_modified),
	UNCHANGED ("Unchanged", R.drawable.gcg__null_drawable_32, R.drawable.gcg__null_drawable, 0, R.drawable.pdf_unchanged);
	
	private static final Hashtable<String, FseStyleModificationState> nameTable = new Hashtable<String, FseStyleModificationState>();
	static {
		FseStyleModificationState.nameTable.put(MODIFIED.getName(), MODIFIED);
		FseStyleModificationState.nameTable.put(UNCHANGED.getName(), UNCHANGED);
	}
	public static FseStyleModificationState getObjectForName(String aName) {
		return FseStyleModificationState.nameTable.get(aName);
	}
	
	private final String name;
	private Drawable summaryDrawable;
	private final int summaryDrawableResourceId;
	private final int markupDrawableResourceId;
	private final int pdfSummaryDrawableResourceId;
	private final int pdfMarkupDrawableResourceId;
	
	private FseStyleModificationState(String aName, int aSumamryDrawableResourceId, int aMarkupDrawableResourceId, int aPdfSumamryDrawableResourceId, int aPdfMarkupDrawableResourceId) {
		this.name = aName;
		this.summaryDrawableResourceId = aSumamryDrawableResourceId;
		this.markupDrawableResourceId = aMarkupDrawableResourceId;
		this.pdfSummaryDrawableResourceId = aPdfSumamryDrawableResourceId;
		this.pdfMarkupDrawableResourceId = aPdfMarkupDrawableResourceId;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getSummaryDrawableResourceId() {
		return this.summaryDrawableResourceId;
	}
	
	@Override
	public int getMarkupDrawableResourceId() {
		return this.markupDrawableResourceId;
	}
	
	@Override
	public int getPdfSummaryDrawableResourceId() {
		return this.pdfSummaryDrawableResourceId;
	}

	@Override
	public int getPdfMarkupDrawableResourceId() {
		return this.pdfMarkupDrawableResourceId;
	}

	@Override
	public Drawable getSummaryDrawable() {
		if(this.summaryDrawable == null) {
			this.summaryDrawable = GcgApplication.getContext().getResources().getDrawable(getSummaryDrawableResourceId());
		}
		return this.summaryDrawable;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	@Override
	public boolean isUnchanged() {
		return this.equals(UNCHANGED);
	}
	
}
