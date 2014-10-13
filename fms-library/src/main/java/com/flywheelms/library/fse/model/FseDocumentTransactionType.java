/* @(#)FseDocumentTransactionType.java
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

package com.flywheelms.library.fse.model;

import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.interfaces.FmmEnumNode;

import java.util.Date;

public enum FseDocumentTransactionType implements FmmEnumNode {
	
	CREATE("Create", ""),
	MODIFY("Modify", ""),
	CHECKPOINT("Checkpoint", ""),
	LOCK("Lock", ""),
	UNLOCK("Unlock", ""),
	REVERT_ALL("Revert All", ""),
	REVERT_STORY("Revert Story", ""),
	REVERT_NOTES("Revert Notes", "");

	public static final String name_COLUMN_1 = "document_transaction_type";
	public static final String name_COLUMN_2 = "description";

	private static FmmNodeDefinition fmmNodeDictionaryEntry = FmmNodeDefinition.FSE_DOCUMENT_TRANSACTION__TYPE;
	private static Drawable labelDrawable = fmmNodeDictionaryEntry.getLabelDrawable();
	public static GcgGuiable getStaticInstance() {
		return CREATE;
	}

	private NodeId nodeId;
	private String documentTransactionType;
	private String description;
	
	private FseDocumentTransactionType(String aDocumentTransactionType, String aDescription) {
		this.documentTransactionType = aDocumentTransactionType;
		this.description = aDescription;
		this.nodeId = new NodeId(
				FmmNodeDefinition.getEntryForClass(FseDocumentTransactionType.class),
				getDocumentTransactionType() );
	}

	public static FseDocumentTransactionType getObjectForName(String aName) {
		for(FseDocumentTransactionType theTransactionType : values()) {
			if(theTransactionType.getDocumentTransactionType().equals(aName)) {
				return theTransactionType;
			}
		}
		return null;
	}
	
	public String getDocumentTransactionType() {
		return this.documentTransactionType;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public String toString() {
		return getDocumentTransactionType();
	}

	@Override
	public NodeId getNodeId() {
		return this.nodeId;
	}
	
	@Override
	public String getNodeIdString() {
		return getNodeId().getNodeIdString();
	}
	
	@Override
	public String getAbbreviatedNodeIdString() {
		return getNodeId().getAbbreviatedNodeIdString();
	}

	@Override
	public Date getRowTimestamp() {
		return GcgDateHelper.NULL_DATE;
	}

	@Override
	public void setRowTimestamp(Date aTimestamp) { /* N/A */ }

	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return fmmNodeDictionaryEntry;
	}

	@Override
	public String getTypeCodeForNodeId() {
		return getFmmNodeDefinition().getTypeCodeForNodeId();
	}
    @Override
    public String getNodeTypeCode() {
        return null;
    }

	@Override
	public String getNodeTypeName() {
		return getFmmNodeDefinition().getNodeTypeName();
	}

	@Override
	public int getNodeEditorActivityRequestCode() {
		return 0;
	}

	@Override
	public int getNodeCreateActivityRequestCode() {
		return 0;
	}

	@Override
	public int getNodePickerActivityRequestCode() {
		return 0;
	}

	@Override
	public boolean isModified(String aSerializedObject) {
		return false;
	}

	@Override
	public FmmNodeImpl getClone() {
		return null;
	}

	@Override
	public String getLabelText() {
		return GcgApplication.getContext().getResources().getString(getLabelTextResourceId());
	}

	public static int getLabelTextResourceId() {
		return fmmNodeDictionaryEntry.getLabelTextResourceId();
	}

	@Override
	public Drawable getLabelDrawable() {
		return labelDrawable;
	}

	@Override
	public int getLabelDrawableResourceId() {
		return fmmNodeDictionaryEntry.getLabelDrawableResourceId();
	}

	@Override
	public int getDataDrawableResourceId() {
		return R.drawable.gcg__null_drawable;
	}

	@Override
	public String getDataText() {
		return getDocumentTransactionType();
	}

	@Override
	public Drawable getDataDrawable() {
		return null;
	}
	
	@Override
	public Date updateRowTimestamp() { return null; }

}
