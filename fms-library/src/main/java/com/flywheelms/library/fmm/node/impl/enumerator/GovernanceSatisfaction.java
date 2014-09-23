/* @(#)GovernanceSatisfaction.java
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

import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.FmmEnumNode;

import java.util.Date;
import java.util.Hashtable;

public enum GovernanceSatisfaction implements FmmEnumNode {
	
	UNRATED (0, R.string.governance_satisfaction__0__name, R.string.governance_satisfaction__0__description, R.drawable.governance_satisfaction__0),
	ONE (1, R.string.governance_satisfaction__1__name, R.string.governance_satisfaction__1__description, R.drawable.governance_satisfaction__1),
	TWO (2, R.string.governance_satisfaction__2__name, R.string.governance_satisfaction__2__description, R.drawable.governance_satisfaction__2),
	THREE (3, R.string.governance_satisfaction__3__name, R.string.governance_satisfaction__3__description, R.drawable.governance_satisfaction__3),
	FOUR (4, R.string.governance_satisfaction__4__name, R.string.governance_satisfaction__4__description, R.drawable.governance_satisfaction__4),
	FIVE (5, R.string.governance_satisfaction__5__name, R.string.governance_satisfaction__5__description, R.drawable.governance_satisfaction__5),
	SIX (6, R.string.governance_satisfaction__6__name, R.string.governance_satisfaction__6__description, R.drawable.governance_satisfaction__6),
	SEVEN (7, R.string.governance_satisfaction__7__name, R.string.governance_satisfaction__7__description, R.drawable.governance_satisfaction__7),
	EIGHT (8, R.string.governance_satisfaction__8__name, R.string.governance_satisfaction__8__description, R.drawable.governance_satisfaction__8),
	NINE (9, R.string.governance_satisfaction__9__name, R.string.governance_satisfaction__9__description, R.drawable.governance_satisfaction__9),
	TEN (10, R.string.governance_satisfaction__10__name, R.string.governance_satisfaction__10__description, R.drawable.governance_satisfaction__10);
	
	public static final String name_COLUMN_1 = "score";
	public static final String name_COLUMN_2 = "name";
	public static final String name_COLUMN_3 = "description";
	
	private static final Hashtable<String, GovernanceSatisfaction> nameHashtable = new Hashtable<String, GovernanceSatisfaction>();
	static {
		for(GovernanceSatisfaction theGovernanceSatisfaction : values()) {
			GovernanceSatisfaction.nameHashtable.put(theGovernanceSatisfaction.getName(), theGovernanceSatisfaction);
		}
	}
	
	public static GovernanceSatisfaction getObjectForName(String aName) {
		return GovernanceSatisfaction.nameHashtable.get(aName);
	}
	
	private static final Hashtable<Integer, GovernanceSatisfaction> scoreHashtable = new Hashtable<Integer, GovernanceSatisfaction>();
	static {
		for(GovernanceSatisfaction theGovernanceSatisfaction : values()) {
			GovernanceSatisfaction.scoreHashtable.put(theGovernanceSatisfaction.getScore(), theGovernanceSatisfaction);
		}
	}
	
	public static GovernanceSatisfaction getObjectForScore(int aScore) {
		return GovernanceSatisfaction.scoreHashtable.get(aScore);
	}

	private static FmmNodeDefinition fmmNodeDictionaryEntry = FmmNodeDefinition.getEntryForClass(GovernanceSatisfaction.class);
	private static String labelText = fmmNodeDictionaryEntry.getLabelText();
	private static Drawable labelDrawable = fmmNodeDictionaryEntry.getLabelDrawable();
	public static GcgGuiable getStaticInstance() {
		return TEN;
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

	private final NodeId nodeId;
	private Date timestamp = new Date(0);
	private final int score;
	private final int nameResourceId;
	private String name;
	private final int descriptionResourceId;
	private String description;
	private final int drawableResourceId;
	private Drawable drawable;
	
	GovernanceSatisfaction(int aScoreValue, int aNameResourceId, int aDescriptionResourceId, int aDrawableResourceId) {
		this.nodeId = new NodeId(
				FmmNodeDefinition.getEntryForClass(GovernanceSatisfaction.class),
				GcgApplication.getAppResources().getString(aNameResourceId) );
		this.score = aScoreValue;
		this.nameResourceId = aNameResourceId;
		this.descriptionResourceId = aDescriptionResourceId;
		this.drawableResourceId = aDrawableResourceId;
	}
	
	public String getName() {
		if(this.name == null) {
			this.name = GcgApplication.getAppResources().getString(this.nameResourceId);
		}
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
	
	public int getScore() {
		return this.score;
	}
	
	public String getDescription() {
		if(this.description == null) {
			this.description = GcgApplication.getAppResources().getString(this.descriptionResourceId);
		}
		return this.description;
	}

	@Override
	public String getDataText() {
		return getName();
	}

	@Override
	public Drawable getDataDrawable() {
		if(this.drawable == null) {
			this.drawable = GcgApplication.getAppResources().getDrawable(this.drawableResourceId);
		}
		return this.drawable;
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
	public String toString() {
		return getName();
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
