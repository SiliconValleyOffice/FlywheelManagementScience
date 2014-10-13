/* @(#)LockType.java
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

import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.helper.FmmHelper;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.FmmEnumNode;

import java.util.Date;

public enum LockType implements FmmEnumNode {
	
	BUDGET (R.string.lock_type__budget, R.string.lock_type__budget__description, R.drawable.work_task_budget__gray, false, false),
	NODE (R.string.lock_type__node, R.string.lock_type__node__description, R.drawable.fmm_noun__lock_type, false, false),
	STORY (R.string.lock_type__story, R.string.lock_type__story__description, R.drawable.fse__story__gray, false, false),
	NOTES (R.string.lock_type__notes, R.string.lock_type__notes__description, R.drawable.fse__notes__gray, false, false),
	FISCAL_YEARS (R.string.lock_type__fiscal_years, R.string.lock_type__fiscal_years__description, R.drawable.fmm_noun__fiscal_year__gray, false, false),
	PROJECT_ASSETS (R.string.lock_type__project_assets, R.string.lock_type__project_assets__description, R.drawable.fmm_noun__project_asset__gray, false, false),
	SERVICE_REQUESTS (R.string.lock_type__service_requests, R.string.lock_type__service_requests__description, R.drawable.fmm_noun__service_request__gray, true, false),
	STRATEGIC_MILESTONES (R.string.lock_type__strategic_milestones, R.string.lock_type__strategic_milestones__description, R.drawable.fmm_noun__strategic_milestone__gray, false, false),
	TACTICAL_MILESTONES (R.string.lock_type__flywheel_cadences, R.string.lock_type__flywheel_cadences__description, R.drawable.fmm_noun__flywheel_cadence__gray, false, false),
	WORK_PACKAGES (R.string.lock_type__work_packages, R.string.lock_type__work_packages__description, R.drawable.fmm_noun__work_package__gray, false, false),
	WORK_PLANS (R.string.lock_type__work_plans, R.string.lock_type__work_plans__description, R.drawable.fmm_noun__work_plan__gray, false, false),
	WORK_TASKS (R.string.lock_type__work_tasks, R.string.lock_type__work_tasks__description, R.drawable.fmm_noun__work_task__gray, true, false);
	
	public static final String name_COLUMN_1 = "lock_type";
	public static final String name_COLUMN_2 = "description";
	public static final String name_COLUMN_3 = "may_resequence";
	public static final String name_COLUMN_4 = "may_change_headline";

	
	public static LockType getObjectForName(String aName) {
		for(LockType theLockType : LockType.values()) {
			if(theLockType.equals(aName)) {
				return theLockType;
			}
		}
		return null;
	}

	private static FmmNodeDefinition fmmNodeDictionaryEntry = FmmNodeDefinition.getEntryForClass(LockType.class);
	private static String labelText = fmmNodeDictionaryEntry.getLabelText();
	private static Drawable labelDrawable = fmmNodeDictionaryEntry.getLabelDrawable();

	private NodeId nodeId;
	private Date timestamp = new Date(0);
	private int nameStringResourceId;
	private String name;
	private int descriptionStringResourceId;
	private String description;
	private int drawableResourceId;
	private Drawable drawable;
	boolean mayResequence;
	boolean mayChangeHeadline;
	
	LockType(int aNameStringResourceId, int aDescriptionStringResourceId, int aDrawableResourceId, boolean bMayResequence, boolean bMayChangeHeadline) {
		this.nameStringResourceId = aNameStringResourceId;
		this.name = FmmHelper.getContext().getResources().getString(this.nameStringResourceId);
		this.descriptionStringResourceId = aDescriptionStringResourceId;
		this.description = FmmHelper.getContext().getResources().getString(this.descriptionStringResourceId);
		this.drawableResourceId = aDrawableResourceId;
		this.drawable = FmmHelper.getContext().getResources().getDrawable(this.drawableResourceId);
		this.mayResequence = bMayResequence;
		this.mayChangeHeadline = bMayChangeHeadline;
		this.nodeId = new NodeId(
				FmmNodeDefinition.getEntryForClass(LockType.class),
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
	
	@Override
	public String toString() {
		return getName();
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public boolean isMayResequence() {
		return this.mayResequence;
	}

	public boolean isMayChangeHeadline() {
		return this.mayChangeHeadline;
	}

	@Override
	public Drawable getDataDrawable() {
		return this.drawable;
	}

	@Override
	public String getDataText() {
		return getName();
	}
	
	public static GcgGuiable getStaticInstance() {
		return NODE;
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
