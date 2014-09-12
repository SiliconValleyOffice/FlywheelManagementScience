/* @(#)CompletionCommitmentType.java
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
package com.flywheelms.library.fmm.node.impl.enumerator;

import android.graphics.drawable.Drawable;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.helper.FmmHelper;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.FmmEnumNode;
import com.flywheelms.library.gcg.interfaces.GcgGuiable;

import java.util.Date;

public enum CompletionCommitmentType implements FmmEnumNode {
	
	// DO NOT change first argument.  Used as database key.
	SUGGESTED ("Suggested", R.string.completion_commitment_type__suggested, R.string.completion_commitment_type__suggested__description, R.drawable.commitment__suggested),
	PROPOSED ("Proposed", R.string.completion_commitment_type__proposed, R.string.completion_commitment_type__proposed__description, R.drawable.commitment__proposed),
	CONFIRMED ("Confirmed", R.string.completion_commitment_type__confirmed, R.string.completion_commitment_type__confirmed__description, R.drawable.commitment__confirmed),
	NONE ("None", R.string.completion_commitment_type__none, R.string.completion_commitment_type__none__description, R.drawable.commitment__none),
	WITHDRAWN ("Withdrawn", R.string.completion_commitment_type__withdrawn, R.string.completion_commitment_type__withdrawn__description, R.drawable.commitment__withdrawn),
	DECLINED ("Refused", R.string.completion_commitment_type__declined, R.string.completion_commitment_type__declined__description, R.drawable.commitment__declined);

	public static final String name_COLUMN_1 = "completion_commitment_type";
	public static final String name_COLUMN_2 = "name";
	public static final String name_COLUMN_3 = "description";
	
	public static CompletionCommitmentType getObjectForName(String aName) {
		for(CompletionCommitmentType theCompletionCommitmentType : CompletionCommitmentType.values()) {
			if(theCompletionCommitmentType.equals(aName)) {
				return theCompletionCommitmentType;
			}
		}
		return null;
	}

	private static FmmNodeDefinition fmmNodeDictionaryEntry = FmmNodeDefinition.getEntryForClass(CompletionCommitmentType.class);
	private static String labelText = fmmNodeDictionaryEntry.getLabelText();
	private static Drawable labelDrawable = fmmNodeDictionaryEntry.getLabelDrawable();
	public static GcgGuiable getStaticInstance() {
		return PROPOSED;
	}

	@Override
	public String getLabelText() {
		return labelText;
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
		return this.drawableResourceId;
	}

	private NodeId nodeId;
	private Date timestamp = new Date(0);
	private String completionCommitmentType;
	private int nameStringResourceId;
	private String name;
	private int descriptionStringResourceId;
	private String description;
	private int drawableResourceId;
	private Drawable drawable;
	
	CompletionCommitmentType(String aCompletionCommitmentType, int aNameStringResourceId, int aDescriptionStringResourceId, int aDrawableResourceId) {
		this.completionCommitmentType = aCompletionCommitmentType;
		this.nameStringResourceId = aNameStringResourceId;
		this.name = FmmHelper.getContext().getResources().getString(this.nameStringResourceId);
		this.descriptionStringResourceId = aDescriptionStringResourceId;
		this.description = FmmHelper.getContext().getResources().getString(this.descriptionStringResourceId);
		this.drawableResourceId = aDrawableResourceId;
		this.drawable = FmmHelper.getContext().getResources().getDrawable(this.drawableResourceId);
		this.nodeId = new NodeId(
				FmmNodeDefinition.getEntryForClass(CompletionCommitmentType.class),
				getName() );
	}
	
	public String getCompletionCommitmentType() {
		return this.completionCommitmentType;
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public String getNodeIdString() {
		return getNodeId().getNodeIdString();
	}
	
	@Override
	public String getAbbreviatedNodeIdString() {
		return getNodeId().getAbbreviatedNodeIdString();
	}
	
	public String getDescription() {
		return this.description;
	}

	@Override
	public Drawable getDataDrawable() {
		return this.drawable;
	}

	@Override
	public String getDataText() {
		return getName();
	}

	@Override
	public NodeId getNodeId() {
		return this.nodeId;
	}

	@Override
	public Date getRowTimestamp() {
		return this.timestamp;
	}

	@Override
	public void setRowTimestamp(Date aTimestamp) {
		this.timestamp = aTimestamp;
	}

	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		return fmmNodeDictionaryEntry;
	}

	@Override
	public String getNodeTypeCode() {
		return getFmmNodeDefinition().getNodeTypeCode();
	}

	@Override
	public String getNodeTypeName() {
		return getFmmNodeDefinition().getNodeTypeName();
	}
	
	@Override
	public int getNodeEditorActivityRequestCode() {
		return getFmmNodeDefinition().getNodeEditorActivityRequestCode();
	}
	
	@Override
	public int getNodeCreateActivityRequestCode() {
		return getFmmNodeDefinition().getNodeCreateActivityRequestCode();
	}
	
	@Override
	public int getNodePickerActivityRequestCode() {
		return getFmmNodeDefinition().getNodePickerActivityRequestCode();
	}

	@Override
	public boolean isModified(String aSerializedObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FmmNodeImpl getClone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Date updateRowTimestamp() { return null; }

}