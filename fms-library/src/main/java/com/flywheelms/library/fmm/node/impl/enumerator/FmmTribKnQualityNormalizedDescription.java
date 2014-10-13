/* @(#)FmmTribKnQualityNormalizedDescription.java
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

import com.flywheelms.gcongui.deckangl.glyph.DecKanGlTribKnQualityNormalizedDescription;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.FmmEnumNode;

import java.util.ArrayList;
import java.util.Date;

public class FmmTribKnQualityNormalizedDescription extends DecKanGlTribKnQualityNormalizedDescription implements FmmEnumNode {

	public static final FmmTribKnQualityNormalizedDescription AT_RISK = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__at_risk);
    public static final FmmTribKnQualityNormalizedDescription CONFIRMED = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__confirmed);
    public static final FmmTribKnQualityNormalizedDescription EXCESSIVE = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__excessive);
    public static final FmmTribKnQualityNormalizedDescription FACILITATION_ISSUE = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__facilitation_issue);
    public static final FmmTribKnQualityNormalizedDescription FUTURE = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__future);
    public static final FmmTribKnQualityNormalizedDescription GOOD = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__good);
    public static final FmmTribKnQualityNormalizedDescription INACTIVE = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__inactive);
    public static final FmmTribKnQualityNormalizedDescription INCOMPLETE = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__incomplete);
    public static final FmmTribKnQualityNormalizedDescription MISSED = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__missed);
    public static final FmmTribKnQualityNormalizedDescription MODIFIED_SINCE_CONFIRMED = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__modified_since_confirmed);
    public static final FmmTribKnQualityNormalizedDescription MULTIPLE_SPECIFIED = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__multiple_specified);
    public static final FmmTribKnQualityNormalizedDescription NOT_APPLICABLE = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__not_applicable);
    public static final FmmTribKnQualityNormalizedDescription NONE = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__none);
    public static final FmmTribKnQualityNormalizedDescription NOT_ENABLED = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__not_enabled);
    public static final FmmTribKnQualityNormalizedDescription NOT_SCHEDULED = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__not_scheduled);
    public static final FmmTribKnQualityNormalizedDescription ONE_SPECIFIED = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__one_specified);
    public static final FmmTribKnQualityNormalizedDescription OVER_BUDGET = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__over_budget);
    public static final FmmTribKnQualityNormalizedDescription PAST_DUE = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__past_due);
    public static final FmmTribKnQualityNormalizedDescription PROPOSED = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__proposed);
    public static final FmmTribKnQualityNormalizedDescription SUGGESTED = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__suggested);
    public static final FmmTribKnQualityNormalizedDescription SWAG = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__swag);
    public static final FmmTribKnQualityNormalizedDescription UNCONFIRMED = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__unconfirmed);
    public static final FmmTribKnQualityNormalizedDescription UNKNOWN = new FmmTribKnQualityNormalizedDescription(R.string.tribkn_quality__normalized_description__unspecified);
    
    private static final ArrayList<FmmTribKnQualityNormalizedDescription> VALUES = new ArrayList<FmmTribKnQualityNormalizedDescription>();

    static {
        FmmTribKnQualityNormalizedDescription.VALUES.add(AT_RISK);
        FmmTribKnQualityNormalizedDescription.VALUES.add(CONFIRMED);
        FmmTribKnQualityNormalizedDescription.VALUES.add(EXCESSIVE);
        FmmTribKnQualityNormalizedDescription.VALUES.add(FACILITATION_ISSUE);
        FmmTribKnQualityNormalizedDescription.VALUES.add(FUTURE);
        FmmTribKnQualityNormalizedDescription.VALUES.add(GOOD);
        FmmTribKnQualityNormalizedDescription.VALUES.add(INACTIVE);
        FmmTribKnQualityNormalizedDescription.VALUES.add(INCOMPLETE);
        FmmTribKnQualityNormalizedDescription.VALUES.add(MISSED);
        FmmTribKnQualityNormalizedDescription.VALUES.add(MODIFIED_SINCE_CONFIRMED);
        FmmTribKnQualityNormalizedDescription.VALUES.add(MULTIPLE_SPECIFIED);
        FmmTribKnQualityNormalizedDescription.VALUES.add(NOT_APPLICABLE);
        FmmTribKnQualityNormalizedDescription.VALUES.add(NONE);
        FmmTribKnQualityNormalizedDescription.VALUES.add(NOT_ENABLED);
        FmmTribKnQualityNormalizedDescription.VALUES.add(NOT_SCHEDULED);
        FmmTribKnQualityNormalizedDescription.VALUES.add(ONE_SPECIFIED);
        FmmTribKnQualityNormalizedDescription.VALUES.add(OVER_BUDGET);
        FmmTribKnQualityNormalizedDescription.VALUES.add(PAST_DUE);
        FmmTribKnQualityNormalizedDescription.VALUES.add(PROPOSED);
        FmmTribKnQualityNormalizedDescription.VALUES.add(SUGGESTED);
        FmmTribKnQualityNormalizedDescription.VALUES.add(SWAG);
        FmmTribKnQualityNormalizedDescription.VALUES.add(UNCONFIRMED);
        FmmTribKnQualityNormalizedDescription.VALUES.add(UNKNOWN);
    }

    public static ArrayList<FmmTribKnQualityNormalizedDescription> getImplementationValues() {  // call from super-class through reflection
        return FmmTribKnQualityNormalizedDescription.VALUES;
    }
	
	public static final String name_COLUMN_1 = "tribkn_quality_normalized_description";
	
	public static FmmTribKnQualityNormalizedDescription getImplementationObjectForName(String aName) {  // call from super-class through reflection
		for(FmmTribKnQualityNormalizedDescription theNodeQualityNormalizedDescription : FmmTribKnQualityNormalizedDescription.VALUES) {
			if(theNodeQualityNormalizedDescription.equals(aName)) {
				return theNodeQualityNormalizedDescription;
			}
		}
		return null;
	}

	private static FmmNodeDefinition fmmNodeDictionaryEntry = FmmNodeDefinition.getEntryForClass(FmmTribKnQualityNormalizedDescription.class);

	public static GcgGuiable getStaticSubclassInstance() {  // call from super-class through reflection
		return AT_RISK;
	}

	private NodeId nodeId;
	private Date timestamp = new Date(0);

	FmmTribKnQualityNormalizedDescription(int aNameResourceId) {
        super(aNameResourceId);
		this.nodeId = new NodeId(
				FmmNodeDefinition.getEntryForClass(FmmTribKnQualityNormalizedDescription.class),
				getName() );
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
	public String getTypeCodeForNodeId() {
		return getFmmNodeDefinition().getTypeCodeForNodeId();
	}

	@Override
	public String getNodeTypeName() {
		return getFmmNodeDefinition().getNodeTypeName();
	}

    @Override
    public String getNodeTypeCode() {
        return getFmmNodeDefinition().getNodeTypeCode();
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
		return false;
	}

	@Override
	public FmmNodeImpl getClone() {
		return null;
	}
	
	@Override
	public Date updateRowTimestamp() { return null; }

    public static void init() {
        DecKanGlTribKnQualityNormalizedDescription.subClass = FmmTribKnQualityNormalizedDescription.class;
    }
}
