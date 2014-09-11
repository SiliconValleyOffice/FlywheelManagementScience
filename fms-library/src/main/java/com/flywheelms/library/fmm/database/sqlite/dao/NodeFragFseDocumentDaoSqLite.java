/* @(#)NodeFragFseDocumentDaoSqLite.java
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

import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragFseDocumentMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragMetaData;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragFseDocument;

import java.util.HashMap;

public class NodeFragFseDocumentDaoSqLite extends NodeFragDaoSqLite<NodeFragFseDocument> {

	private static NodeFragFseDocumentDaoSqLite singleton;

	public static NodeFragFseDocumentDaoSqLite getInstance() {
		if(NodeFragFseDocumentDaoSqLite.singleton == null) {
			NodeFragFseDocumentDaoSqLite.singleton = new NodeFragFseDocumentDaoSqLite();
		}
		return NodeFragFseDocumentDaoSqLite.singleton;
	}
	
	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT;
	}

	@Override
	protected void buildColumnIndexMap(Cursor aCursor) {
		super.buildColumnIndexMap(aCursor);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragFseDocumentMetaData.column_DOCUMENT_ID);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragFseDocumentMetaData.column_DOCUMENT_TRANSACTION_TYPE);
		putColumnIndexMapEntry(this.columnIndexMap, aCursor, NodeFragFseDocumentMetaData.column_SERIALIZED_DOCUMENT);
	}

	@Override
	protected void getColumnValues(HashMap<String, Integer> aHashMap, Cursor aCursor, NodeFragFseDocument aNodeFragFseDocument) {
		// everything is initialized in the constructor
	}

	@Override
	public ContentValues buildContentValues(NodeFragFseDocument aNodeFragFseDocument) {
		ContentValues theContentValues = super.buildContentValues(aNodeFragFseDocument);
		theContentValues.put(NodeFragFseDocumentMetaData.column_DOCUMENT_ID, aNodeFragFseDocument.getDocumentId());
		theContentValues.put(NodeFragFseDocumentMetaData.column_DOCUMENT_TRANSACTION_TYPE, aNodeFragFseDocument.getDocumentTransactionTypeString());
		theContentValues.put(NodeFragFseDocumentMetaData.column_SERIALIZED_DOCUMENT, aNodeFragFseDocument.getSerializedDocument());
		return theContentValues;
	}

	@Override
	public ContentValues buildUpdateContentValues(NodeFragFseDocument aNodeFragFseDocument) {
		ContentValues theContentValues = super.buildUpdateContentValues(aNodeFragFseDocument);
		theContentValues.put(NodeFragFseDocumentMetaData.column_DOCUMENT_TRANSACTION_TYPE, aNodeFragFseDocument.getDocumentTransactionTypeString());
		theContentValues.put(NodeFragFseDocumentMetaData.column_SERIALIZED_DOCUMENT, aNodeFragFseDocument.getSerializedDocument());
		return theContentValues;
	}

	@Override
	public NodeFragFseDocument getNextObjectFromCursor(Cursor aCursor) {
		NodeFragFseDocument theNodeFragFseDocument = new NodeFragFseDocument(
				aCursor.getString(this.columnIndexMap.get(IdNodeMetaData.column_ID)),
				aCursor.getString(this.columnIndexMap.get(NodeFragMetaData.column_PARENT_ID)),
				aCursor.getString(this.columnIndexMap.get(NodeFragFseDocumentMetaData.column_DOCUMENT_TRANSACTION_TYPE)),
				aCursor.getString(this.columnIndexMap.get(NodeFragFseDocumentMetaData.column_SERIALIZED_DOCUMENT)) );
		theNodeFragFseDocument.setDocumentId(aCursor.getString(this.columnIndexMap.get(NodeFragFseDocumentMetaData.column_DOCUMENT_ID)));
		return theNodeFragFseDocument;
	}

}
