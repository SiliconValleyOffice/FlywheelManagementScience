/* @(#)NodeFragFseDocument.java
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

package com.flywheelms.library.fmm.node.impl.nodefrag;

import org.json.JSONException;
import org.json.JSONObject;

import com.flywheelms.library.fmm.meta_data.NodeFragFseDocumentMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fse.model.FseDocument;
import com.flywheelms.library.fse.model.FseDocumentTransactionType;

public class NodeFragFseDocument extends FmmNodeFragLockableImpl {

	private String documentId;
	private String documentTransactionTypeString;
	private FseDocumentTransactionType documentTransactionType;
	private String serializedDocument;
	private FseDocument fseDocument;

	public NodeFragFseDocument(String aParentNodeIdString, String aDocumentTransactionTypeString, String aSerializedDocument) {
		super(NodeFragFseDocument.class, aParentNodeIdString);
		setDocumentTransactionTypeString(aDocumentTransactionTypeString);
		FseDocument theFseDocument = new FseDocument(aSerializedDocument);
		theFseDocument.setNodeFragNodeIdString(getNodeIdString());
		setSerializedDocument(theFseDocument.getSerialized());
	}

	//	rehydrate from database
	public NodeFragFseDocument(String anExistingNodeFragNodeIdString, String aParentNodeIdString, String aDocumentTransactionTypeString, String aSerializedDocument) {
		super(NodeId.hydrate(NodeFragFseDocument.class, anExistingNodeFragNodeIdString), aParentNodeIdString);
		setDocumentTransactionTypeString(aDocumentTransactionTypeString);
		setSerializedDocument(aSerializedDocument);
	}

	// rehydrate
	public NodeFragFseDocument(JSONObject aJsonObject) {
		super(NodeFragFseDocument.class, aJsonObject);
		try {
			setDocumentId(aJsonObject.getString(NodeFragFseDocumentMetaData.column_DOCUMENT_ID));
			setDocumentTransactionTypeString(aJsonObject.getString(NodeFragFseDocumentMetaData.column_DOCUMENT_TRANSACTION_TYPE));
			setSerializedDocument(aJsonObject.getString(NodeFragFseDocumentMetaData.column_SERIALIZED_DOCUMENT));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public NodeFragFseDocument(FseDocument anFseDocument) {
		super(NodeFragFseDocument.class, anFseDocument.getParentNodeIdString());
		setDocumentTransactionType(anFseDocument.getDocumentSectionHistory().getDocumentTransactionList().get(
				anFseDocument.getDocumentSectionHistory().getDocumentTransactionList().size() - 1).getTransactionType() );
		anFseDocument.setNodeFragNodeIdString(getNodeIdString());
		setSerializedDocument(anFseDocument.getSerialized());
	}
	
	public NodeFragFseDocument(FmmHeadlineNode aHeadlineNode) {
		this(new FseDocument(aHeadlineNode.getNodeIdString(), false));
	}

	public String getDocumentTransactionTypeString() {
		return this.documentTransactionTypeString;
	}

	public void setDocumentTransactionTypeString(String aDocumentTransactionTypeString) {
		this.documentTransactionTypeString = aDocumentTransactionTypeString;
		this.documentTransactionType = FseDocumentTransactionType.getObjectForName(this.documentTransactionTypeString);
	}
	
	public FseDocumentTransactionType getFseDocumentTransactionType() {
		if(this.documentTransactionType == null) {
			this.documentTransactionType = FseDocumentTransactionType.getObjectForName(getDocumentTransactionTypeString());
		}
		return this.documentTransactionType;
	}

	public String getSerializedDocument() {
		return this.serializedDocument;
	}

	public void setSerializedDocument(String aSerializedDocument) {
		this.serializedDocument = aSerializedDocument;
		this.fseDocument = null;
		this.documentId = null;
	}

	public void resetModificationState() {
		FseDocument theFseDocument = getFseDocument();
		theFseDocument.resetModificationState();
		this.serializedDocument = theFseDocument.getSerialized();
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(NodeFragFseDocumentMetaData.column_DOCUMENT_ID, getDocumentId());
			theJsonObject.put(NodeFragFseDocumentMetaData.column_DOCUMENT_TRANSACTION_TYPE, getDocumentTransactionTypeString());
			theJsonObject.put(NodeFragFseDocumentMetaData.column_SERIALIZED_DOCUMENT, getSerializedDocument());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}
	
	public FseDocument getFseDocument() {
		if(this.fseDocument == null) {
			this.fseDocument = new FseDocument(this.serializedDocument);
		}
		return this.fseDocument;
	}

	public void setFseDocument(FseDocument anFseDocument) {
		this.fseDocument = anFseDocument;
		this.serializedDocument = anFseDocument.getSerialized();
		this.documentId = anFseDocument.getNodeIdString();
	}

	public FseDocumentTransactionType getDocumentTransactionType() {
		return this.documentTransactionType;
	}

	public void setDocumentTransactionType(FseDocumentTransactionType aDocumentTransactionType) {
		this.documentTransactionType = aDocumentTransactionType;
		this.documentTransactionTypeString = aDocumentTransactionType.getDocumentTransactionType();
	}

	public String getDocumentId() {
		if(this.documentId == null) {
			this.documentId = getFseDocument().getNodeIdString();
		}
		return this.documentId;
	}

	public void setDocumentId(String aDocumentId) {
		this.documentId = aDocumentId;
	}

}
