/* @(#)PdfPublicationDaoSqLite.java
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

import android.database.Cursor;

import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.PdfPublicationMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.event.PdfPublication;

import java.util.HashMap;

public class PdfPublicationDaoSqLite extends IdNodeDaoSqLite<PdfPublication> {

	private static PdfPublicationDaoSqLite singleton;

	public static PdfPublicationDaoSqLite getInstance() {
		if(PdfPublicationDaoSqLite.singleton == null) {
			PdfPublicationDaoSqLite.singleton = new PdfPublicationDaoSqLite();
		}
		return PdfPublicationDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.PDF_PUBLICATION;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, PdfPublicationMetaData.column_COMMUNITY_MEMBER_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, PdfPublicationMetaData.column_HEADLINE_NODE_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, PdfPublicationMetaData.column_HEADLINE_NODE_TYPE_CODE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, PdfPublicationMetaData.column_CONTENT_SUMMARY);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, PdfPublicationMetaData.column_DESTINATION_SUMMARY_1);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, PdfPublicationMetaData.column_DESTINATION_SUMMARY_2);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, PdfPublication aPdfPublication) {
		super.getColumnValues(aHashMap, aCursor, aPdfPublication);
		aPdfPublication.setHeadlineNodeTypeCode(aCursor.getString(aHashMap.get(PdfPublicationMetaData.column_HEADLINE_NODE_TYPE_CODE)));
		aPdfPublication.setContentsSummary(aCursor.getString(aHashMap.get(PdfPublicationMetaData.column_CONTENT_SUMMARY)));
		aPdfPublication.setDestinationSummary1(aCursor.getString(aHashMap.get(PdfPublicationMetaData.column_DESTINATION_SUMMARY_1)));
		aPdfPublication.setDestinationSummary2(aCursor.getString(aHashMap.get(PdfPublicationMetaData.column_DESTINATION_SUMMARY_2)));
	}

	@Override
	protected PdfPublication getNextObjectFromCursor(Cursor aCursor) {
		PdfPublication thePdfPublication = null;
		thePdfPublication = new PdfPublication(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)),
				aCursor.getString(this.columnIndexMap.get(PdfPublicationMetaData.column_COMMUNITY_MEMBER_ID)),
				aCursor.getString(this.columnIndexMap.get(PdfPublicationMetaData.column_HEADLINE_NODE_ID)) );
		getColumnValues(this.columnIndexMap, aCursor, thePdfPublication);
		return thePdfPublication;
	}

}
