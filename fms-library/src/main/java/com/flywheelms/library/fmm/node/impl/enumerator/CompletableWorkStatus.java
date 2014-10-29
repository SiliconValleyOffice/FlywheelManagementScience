/* @(#)CompletableWorkStatus.java
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

package com.flywheelms.library.fmm.node.impl.enumerator;

import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlNounStateColor;
import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.FmmEnumNode;
import com.flywheelms.library.fmm.transaction.FmmNodeGlyphType;

import java.util.Date;

public enum CompletableWorkStatus implements FmmEnumNode {

	// DO NOT change code value.  Used as data key.
	NOT_STARTED (
			"Not Started",
			DecKanGlNounStateColor.GRAY,
			FmmNodeGlyphType.GRAY,
			R.string.work_status__not_started,
			R.string.work_status__not_started__description ),
	STARTED (
			"Started",
			DecKanGlNounStateColor.YELLOW,
			FmmNodeGlyphType.YELLOW,
			R.string.work_status__started,
			R.string.work_status__started__description ),
	ON_HOLD (
			"On Hold",
			DecKanGlNounStateColor.ORANGE,
			FmmNodeGlyphType.ORANGE,
			R.string.work_status__on_hold,
			R.string.work_status__on_hold__description ),
	READY_FOR_REVIEW (
			"Ready For Review",
			DecKanGlNounStateColor.PINK,
			FmmNodeGlyphType.PINK,
			R.string.work_status__work_ready_for_review,
			R.string.work_status__work_ready_for_review__description ),
	COMPLETE (
			"Complete",
			DecKanGlNounStateColor.GREEN,
			FmmNodeGlyphType.GREEN,
			R.string.work_status__work_complete,
			R.string.work_status__work_complete__description );

	public static final String name_COLUMN_1 = "work_status";
	public static final String name_COLUMN_2 = "name";
	public static final String name_COLUMN_3 = "description";
	public static final String name_COLUMN_4 = "color";
	
	public static CompletableWorkStatus getObjectForCode(String aWorkStatusCode) {
		for(CompletableWorkStatus theWorkStatus : CompletableWorkStatus.values()) {
			if(theWorkStatus.getWorkStatusCode().equals(aWorkStatusCode)) {
				return theWorkStatus;
			}
		}
		return null;
	}

	private static FmmNodeDefinition fmmNodeDictionaryEntry = FmmNodeDefinition.getEntryForClass(CompletableWorkStatus.class);
	private static String labelText = fmmNodeDictionaryEntry.getLabelText();
	private static Drawable labelDrawable = fmmNodeDictionaryEntry.getLabelDrawable();

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

	private NodeId nodeId;
	private Date timestamp = new Date(0);
	private String workStatusCode;
	private DecKanGlNounStateColor workStateColor;
	private FmmNodeGlyphType nodeGlyphType;
	private int nameStringResourceId;
	private String name;
	private int descriptionStringResourceId;
	private String description;
	private Drawable drawable;
	
	CompletableWorkStatus(String aWorkStatusCode, DecKanGlNounStateColor aWorkStateColor, FmmNodeGlyphType anFmmNodeGlyphType, int aNameStringResourceId, int aDescriptionStringResourceId) {
		this.workStatusCode = aWorkStatusCode;
		this.nodeGlyphType = anFmmNodeGlyphType;
		this.workStateColor = aWorkStateColor;
		this.nameStringResourceId = aNameStringResourceId;
		this.name = GcgApplication.getAppResources().getString(this.nameStringResourceId);
		this.descriptionStringResourceId = aDescriptionStringResourceId;
		this.description = GcgApplication.getAppResources().getString(this.descriptionStringResourceId);
		this.drawable = GcgApplication.getAppResources().getDrawable(this.workStateColor.getDrawableResourceId());
		this.nodeId = new NodeId(
				FmmNodeDefinition.getEntryForClass(CompletableWorkStatus.class),
				getWorkStatusCode() );
	}
	
	public String getWorkStatusCode() {
		return this.workStatusCode;
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

	public DecKanGlNounStateColor getWorkStateColor() {
		return this.workStateColor;
	}
	
	@Override
	public String getDataText() {
		return getName();
	}
	
	@Override
	public Drawable getDataDrawable() {
		return this.drawable;
	}
	
	@Override
	public int getDataDrawableResourceId() {
		return this.workStateColor.getDrawableResourceId();
	}

	public String getWorkStateColorName() {
		return getWorkStateColor().getName();
	}

	public static GcgGuiable getStaticInstance() {
		return NOT_STARTED;
	}
	
	public static String getGuiableLabelText() {
		return getStaticInstance().getLabelText();
	}
	
	public static Drawable getGuiableLabelDrawable() {
		return getStaticInstance().getLabelDrawable();
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

	public boolean isGreen() {
		return this.workStateColor == DecKanGlNounStateColor.GREEN;
	}

	public boolean isPink() {
		return this.workStateColor == DecKanGlNounStateColor.PINK;
	}

	public boolean isOrange() {
		return this.workStateColor == DecKanGlNounStateColor.ORANGE;
	}

	public boolean isYellow() {
		return this.workStateColor == DecKanGlNounStateColor.YELLOW;
	}

	public boolean isGray() {
		return this.workStateColor == DecKanGlNounStateColor.GRAY;
	}

	public FmmNodeGlyphType getFmmNodeGlyphType() {
		return this.nodeGlyphType;
	}
	
	@Override
	public Date updateRowTimestamp() { return null; }

}
