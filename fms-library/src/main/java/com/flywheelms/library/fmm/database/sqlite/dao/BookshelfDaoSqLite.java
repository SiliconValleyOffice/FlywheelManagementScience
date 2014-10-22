/* @(#)BookshelfDaoSqLite.java
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

package com.flywheelms.library.fmm.database.sqlite.dao;

import android.content.ContentValues;
import android.database.Cursor;

import com.flywheelms.library.fmm.meta_data.BookshelfMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.Bookshelf;

import java.util.HashMap;

public class BookshelfDaoSqLite extends HeadlineNodeDaoSqLite<Bookshelf> {

	private static BookshelfDaoSqLite singleton;

	public static BookshelfDaoSqLite getInstance() {
		if(BookshelfDaoSqLite.singleton == null) {
			BookshelfDaoSqLite.singleton = new BookshelfDaoSqLite();
		}
		return BookshelfDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.BOOKSHELF;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, BookshelfMetaData.column_ORGANIZATION_ID);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, Bookshelf aBookshelf) {
		super.getColumnValues(aHashMap, aCursor, aBookshelf);
		aBookshelf.setOrganizationId(aCursor.getString(aHashMap.get(BookshelfMetaData.column_ORGANIZATION_ID)));
	}

	@Override
	public ContentValues buildContentValues(Bookshelf aBookshelf) {
		ContentValues theContentValues = super.buildContentValues(aBookshelf);
		theContentValues.put(BookshelfMetaData.column_ORGANIZATION_ID, aBookshelf.getOrganizationNodeIdString());
		return theContentValues;
	}

	@Override
	public ContentValues buildUpdateContentValues(Bookshelf aBookshelf) {
		return buildContentValues(aBookshelf);
	}

	@Override
	protected Bookshelf getNextObjectFromCursor(Cursor aCursor) {
		Bookshelf theBookshelf = null;
		theBookshelf = new Bookshelf(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, theBookshelf);
		return theBookshelf;
	}

}
