/* @(#)TribKnElement.java
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

public enum FmmTribKnElement implements FmmEnumNode {
	
	GOVERNANCE (R.string.tribkn_quality_metric__governance, R.string.tribkn_quality_metric__governance__description, R.drawable.tribkn_quality_metric__governance),
	FACILITATION_ISSUE (R.string.tribkn_quality_metric__facilitation_issue, R.string.tribkn_quality_metric__facilitation_issue__description, R.drawable.tribkn_quality_metric__facilitation_issue),
	FACILITATOR (R.string.tribkn_quality_metric__facilitator, R.string.tribkn_quality_metric__facilitator__description, R.drawable.tribkn_quality_metric__facilitator),
	PARENT_FRACTALS (R.string.tribkn_quality_metric__parent_fractals, R.string.tribkn_quality_metric__parent_fractals__description, R.drawable.tribkn_quality_metric__parent_fractals),
	CHILD_FRACTALS (R.string.tribkn_quality_metric__child_fractals, R.string.tribkn_quality_metric__child_fractals__description, R.drawable.tribkn_quality_metric__child_fractals),
	STRATEGIC_COMMITMENT (R.string.tribkn_quality_metric__strategic_commitment, R.string.tribkn_quality_metric__strategic_commitment__description, R.drawable.tribkn_quality_metric__strategic_commitment),
	FLYWHEEL_COMMITMENT (R.string.tribkn_quality_metric__flywheel_commitment, R.string.tribkn_quality_metric__flywheel_commitment__description, R.drawable.tribkn_quality_metric__flywheel_commitment),
	WORK_TEAM (R.string.tribkn_quality_metric__work_team, R.string.tribkn_quality_metric__work_team__description, R.drawable.tribkn_quality_metric__work_team),
	WORK_TASK_BUDGET (R.string.tribkn_quality_metric__work_task_budget, R.string.tribkn_quality_metric__work_task_budget__description, R.drawable.tribkn_quality_metric__work_task_budget),
	STORY (R.string.tribkn_quality_metric__story, R.string.tribkn_quality_metric__story__description, R.drawable.tribkn_quality_metric__story),
	COMPLETION (R.string.tribkn_quality_metric__completion, R.string.tribkn_quality_metric__completion__description, R.drawable.tribkn_quality_metric__completion);
	
	public static final String name_COLUMN_1 = "tribkn_element";
	public static final String name_COLUMN_2 = "description";
	
	public static FmmTribKnElement getObjectForName(String aName) {
		for(FmmTribKnElement theNodeQualityMetric : FmmTribKnElement.values()) {
			if(theNodeQualityMetric.equals(aName)) {
				return theNodeQualityMetric;
			}
		}
		return null;
	}

	private static FmmNodeDefinition fmmNodeDictionaryEntry = FmmNodeDefinition.getEntryForClass(FmmTribKnElement.class);
	private static String labelText = fmmNodeDictionaryEntry.getLabelText();
	private static Drawable labelDrawable = fmmNodeDictionaryEntry.getLabelDrawable();
	
	public static GcgGuiable getStaticInstance() {
		return GOVERNANCE;
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
	private int nameStringResourceId;
	private String name;
	private int descriptionStringResourceId;
	private String description;
	private int drawableResourceId;
	private Drawable drawable;
	
	FmmTribKnElement(int aNameStringResourceId, int aDescriptionStringResourceId, int aDrawableResourceId) {
		this.nameStringResourceId = aNameStringResourceId;
		this.name = FmmHelper.getContext().getResources().getString(this.nameStringResourceId);
		this.descriptionStringResourceId = aDescriptionStringResourceId;
		this.description = FmmHelper.getContext().getResources().getString(this.descriptionStringResourceId);
		this.drawableResourceId = aDrawableResourceId;
		this.drawable = FmmHelper.getContext().getResources().getDrawable(this.drawableResourceId);
		this.nodeId = new NodeId(
				FmmNodeDefinition.getEntryForClass(FmmTribKnElement.class),
				getName() );
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
