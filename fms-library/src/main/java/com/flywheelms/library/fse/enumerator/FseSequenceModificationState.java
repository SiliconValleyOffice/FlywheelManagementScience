/* @(#)FseSequenceModificationState.java
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

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.GcgApplication;

import java.util.Hashtable;

public enum FseSequenceModificationState implements ModificationState {
	
	MOVE_UP ("Move Up",		 R.drawable.fse__sequence_modification__down__27, R.drawable.fse__paragraph_markup__sequence__move_up, 	0, 	R.drawable.pdf_sequence_move_up),
	MOVE_DOWN ("Move Down",	 R.drawable.fse__sequence_modification__down__27, R.drawable.fse__paragraph_markup__sequence__move_down, 0,  R.drawable.pdf_sequence_move_down),
	BLOCK_MOVE_UP__START ("Block Move Up - Start", R.drawable.fse__sequence_modification__down__27, R.drawable.fse__paragraph_markup__sequence_block__move_up__start, 0,  R.drawable.pdf_sequence_block_move_up_start),
	BLOCK_MOVE_UP ("Block Move Up", R.drawable.fse__sequence_modification__down__27, R.drawable.fse__paragraph_markup__sequence_block__move_up, 0,  R.drawable.pdf_sequence_block_move_up),
	BLOCK_MOVE_DOWN__START ("Block Move Down - Start", R.drawable.fse__sequence_modification__down__27, R.drawable.fse__paragraph_markup__sequence_block__move_down__start, 0,  R.drawable.pdf_sequence_block_move_down_start),
	BLOCK_MOVE_DOWN ("Block Move Down", R.drawable.fse__sequence_modification__down__27, R.drawable.fse__paragraph_markup__sequence_block__move_down, 0,  R.drawable.pdf_sequence_block_move_down),
	SEQUENCE_PALOOZA ("Sequence Palooza", R.drawable.fse__sequence_modification__down__27, R.drawable.fse__paragraph_markup__sequence_palooza, 0,  R.drawable.pdf_sequence_sequence_palooza),
	UNCHANGED ("Unchanged", R.drawable.gcg__null_drawable_32, R.color.pdf__transparent, 0,  R.drawable.pdf_unchanged);
	
	private static final Hashtable<String, FseSequenceModificationState> nameTable = new Hashtable<String, FseSequenceModificationState>();
	static {
		FseSequenceModificationState.nameTable.put(MOVE_UP.getName(), MOVE_UP);
		FseSequenceModificationState.nameTable.put(MOVE_DOWN.getName(), MOVE_DOWN);
		FseSequenceModificationState.nameTable.put(BLOCK_MOVE_UP__START.getName(), BLOCK_MOVE_UP__START);
		FseSequenceModificationState.nameTable.put(BLOCK_MOVE_UP.getName(), BLOCK_MOVE_UP);
		FseSequenceModificationState.nameTable.put(BLOCK_MOVE_DOWN__START.getName(), BLOCK_MOVE_DOWN__START);
		FseSequenceModificationState.nameTable.put(BLOCK_MOVE_DOWN.getName(), BLOCK_MOVE_DOWN);
		FseSequenceModificationState.nameTable.put(SEQUENCE_PALOOZA.getName(), SEQUENCE_PALOOZA);
		FseSequenceModificationState.nameTable.put(UNCHANGED.getName(), UNCHANGED);
	}
	public static FseSequenceModificationState getObjectForName(String aName) {
		return FseSequenceModificationState.nameTable.get(aName);
	}
	
	private final String name;
	private Drawable summaryDrawable;
	private final int summaryDrawableResourceId;
	private final int markupDrawableResourceId;
	private final int pdfSummaryDrawableResourceId;
	private final int pdfMarkupDrawableResourceId;
	
	private FseSequenceModificationState(String aName, int aSumamryDrawableResourceId, int aMarkupDrawableResourceId, int aPdfSumamryDrawableResourceId, int aPdfMarkupDrawableResourceId) {
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
