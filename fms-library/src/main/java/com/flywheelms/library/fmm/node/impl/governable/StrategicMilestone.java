/* @(#)StrategicMilestone.java
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

package com.flywheelms.library.fmm.node.impl.governable;

import android.content.Intent;

import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.gcongui.deckangl.interfaces.DecKanGlDecorator;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.gcongui.gcg.widget.date.GcgMonth;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorChildFractals;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorCompletion;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitationIssue;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitator;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFlywheelCommitment;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorGovernance;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorParentFractals;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorSequence;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorStory;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorStrategicCommitment;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkTaskBudget;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkTeam;
import com.flywheelms.library.fmm.meta_data.SequencedLinkNodeMetaData;
import com.flywheelms.library.fmm.meta_data.StrategicMilestoneMetaData;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.completable.FmmCompletableNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class StrategicMilestone extends FmmCompletableNodeImpl implements Comparable<StrategicMilestone>{
	
	private static final long serialVersionUID = 1550533600687519183L;
	private String fiscalYearNodeIdString;
	private FiscalYear fiscalYear;
	private int targetMonthEnd = 0;
	private Date targetDate = GcgDateHelper.NULL_DATE;
    private boolean targetIsReversePlanning = false;
	private ArrayList<ProjectAsset> projectAssetList;
	
	// create a new Strategic Milestone
	public StrategicMilestone(NodeId aNodeId, String aHeadline, String aFiscalYearNodeIdString) {
		super(aNodeId);
		setHeadline(aHeadline);
		setFiscalYearNodeIdString(aFiscalYearNodeIdString);
	}

	public StrategicMilestone(String aNodeIdString, String aFiscalYearNodeId) {
		super(StrategicMilestone.class, aNodeIdString);
		this.fiscalYearNodeIdString = aFiscalYearNodeId;
	}

	public StrategicMilestone(JSONObject aJsonObject) {
		super(StrategicMilestone.class, aJsonObject);
		try {
			validateSerializationFormatVersion(aJsonObject.getString(JsonHelper.key__SERIALIZATION_FORMAT_VERSION));
			setSequence(aJsonObject.getInt(SequencedLinkNodeMetaData.column_SEQUENCE));
			setFiscalYearNodeIdString(aJsonObject.getString(StrategicMilestoneMetaData.column_FISCAL_YEAR_ID));
			setTargetMonthEnd(aJsonObject.getInt(StrategicMilestoneMetaData.column_TARGET_MONTH_END));
			setTargetDate(aJsonObject.getLong(StrategicMilestoneMetaData.column_TARGET_DATE));
            setTargetIsReversePlanning(aJsonObject.getInt(StrategicMilestoneMetaData.column_TARGET_IS_REVERSE_PLANNING));
			setProjectAssetList(aJsonObject.getJSONArray(StrategicMilestoneMetaData.child_fractals_PROJECT_ASSET));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setProjectAssetList(JSONArray aJsonArray) {
		this.projectAssetList = new ArrayList<ProjectAsset>();
		for(int i=0; i < aJsonArray.length(); ++i) {
			try {
				this.projectAssetList.add(FmmDatabaseMediator.getActiveMediator().getProjectAsset(
						aJsonArray.getString(i) ));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

    public static final String SERIALIZATION_FORMAT_VERSION = "0.1";

    @Override
    public JSONObject getJsonObject() {
        JSONObject theJsonObject = super.getJsonObject();
        try {
            theJsonObject.put(JsonHelper.key__SERIALIZATION_FORMAT_VERSION, SERIALIZATION_FORMAT_VERSION);
            theJsonObject.put(SequencedLinkNodeMetaData.column_SEQUENCE, getSequence());
            theJsonObject.put(StrategicMilestoneMetaData.column_FISCAL_YEAR_ID, getFiscalYearNodeIdString());
            theJsonObject.put(StrategicMilestoneMetaData.column_TARGET_MONTH_END, getTargetMonthEnd());
            theJsonObject.put(StrategicMilestoneMetaData.column_TARGET_DATE, getTargetDateFormattedUtcLong());
            theJsonObject.put(StrategicMilestoneMetaData.column_TARGET_IS_REVERSE_PLANNING, targetIsReversePlanningAsInt());
            theJsonObject.put(StrategicMilestoneMetaData.child_fractals_PROJECT_ASSET, getProjectAssetNodeIdStringJsonArray());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return theJsonObject;
    }

    private JSONArray getProjectAssetNodeIdStringJsonArray() {
        JSONArray theJsonArray = new JSONArray();
        for(ProjectAsset theProjectAsset : getProjectAssetList()) {
            theJsonArray.put(theProjectAsset.getNodeIdString());
        }
        return theJsonArray;
    }

    @Override
    public StrategicMilestone getClone() {
        return new StrategicMilestone(getJsonObject());
    }

	public String getFiscalYearNodeIdString() {
		return this.fiscalYearNodeIdString;
	}
	
	public FiscalYear getFiscalYear() {
			if(this.fiscalYear == null && this.fiscalYearNodeIdString != null) {
				this.fiscalYear =
						FmmDatabaseMediator.getActiveMediator().getFiscalYear(this.fiscalYearNodeIdString);
			}
			return this.fiscalYear;
		}

	public void setFiscalYearNodeIdString(String aNodeIdString) {
		this.fiscalYearNodeIdString = aNodeIdString;
        if(this.fiscalYear != null && !this.fiscalYear.getNodeIdString().equals(aNodeIdString)) {
            this.fiscalYear = null;
        }
	}

	public void setFiscalYear(FiscalYear aFiscalYear) {
		this.fiscalYear = aFiscalYear;
		this.fiscalYearNodeIdString = aFiscalYear.getNodeId().getNodeIdString();
	}

	@Override
	protected void initializeNodeCompletionSummaryMap() {
		super.initializeNodeCompletionSummaryMap();
        initializeNodeCompletionSummaryMap(FmmPerspective.STRATEGIC_PLANNING, FmmNodeDefinition.PROJECT_ASSET);
	}

	@Override
	public void updateNodeCompletionSummary(FmmPerspective anFmmPerspective, NodeCompletionSummary aNodeSummary) {
        if(anFmmPerspective == FmmPerspective.STRATEGIC_PLANNING) {
			Collection<ProjectAsset> theProjectAssetCollection = getProjectAssetCollection();
			if(theProjectAssetCollection.size() > 0) {
				aNodeSummary.setShowNodeSummary(true);
				aNodeSummary.setSummaryPrefix("( " + countGreenProjectAssets(theProjectAssetCollection) + " ");
				aNodeSummary.setSummarySuffix(" of " + theProjectAssetCollection.size() + " )");
			} else {
				aNodeSummary.setShowNodeSummary(false);
			}
		}
	}
	
	@Override
	public int compareTo(StrategicMilestone anOtherStrategicMilestone) {
		return (getSequence() < anOtherStrategicMilestone.getSequence() ? -1 :
			(getSequence() == anOtherStrategicMilestone.getSequence() ? 0 : 1));
	}

	private Collection<ProjectAsset> getProjectAssetCollection() {
		return FmmDatabaseMediator.getActiveMediator().listProjectAsset(this);
	}

	public ArrayList<ProjectAsset> getProjectAssetList() {
		if(this.projectAssetList == null) {
			this.projectAssetList = new ArrayList<ProjectAsset>(
					FmmDatabaseMediator.getActiveMediator().listProjectAssetForStrategicMilestone(this.getNodeIdString()) );
		}
		return this.projectAssetList;
	}

	private static int countGreenProjectAssets(Collection<ProjectAsset> aProjectAssetCollection) {
		int theGreenCount = 0;
		for(ProjectAsset theProjectAsset : aProjectAssetCollection) {
			if(theProjectAsset.isGreen()) {
				++theGreenCount;
			}
		}
		return theGreenCount;
	}
	
	/////////////////////////////////////////////////
	//////  TEMPORARY  //////////////////////////

	@Override
	public HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> getUpdatedDecKanGlDecoratorMap() {
		HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> theDecKanGlDecoratorMap =
				new HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator>();
		theDecKanGlDecoratorMap.put(
				FmsDecoratorGovernance.CONFIRMED_GOVERNANCE.getDecoratorCanvasLocation(),
				FmsDecoratorGovernance.CONFIRMED_GOVERNANCE );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorFacilitationIssue.NO_FACILITATION_ISSUE.getDecoratorCanvasLocation(),
				FmsDecoratorFacilitationIssue.NO_FACILITATION_ISSUE );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorFacilitator.CONFIRMED_FACILITATOR.getDecoratorCanvasLocation(),
				FmsDecoratorFacilitator.CONFIRMED_FACILITATOR );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorParentFractals.ONE_CONFIRMED_PARENT_FRACTAL.getDecoratorCanvasLocation(),
				FmsDecoratorParentFractals.ONE_CONFIRMED_PARENT_FRACTAL );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorChildFractals.SUGGESTED_CHILD_FRACTALS.getDecoratorCanvasLocation(),
				FmsDecoratorChildFractals.SUGGESTED_CHILD_FRACTALS );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorStrategicCommitment.PROPOSED_STRATEGIC_COMMITMENT.getDecoratorCanvasLocation(),
				FmsDecoratorStrategicCommitment.PROPOSED_STRATEGIC_COMMITMENT );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorFlywheelCommitment.PROPOSED_FLYWHEEL_COMMITMENT.getDecoratorCanvasLocation(),
				FmsDecoratorFlywheelCommitment.PROPOSED_FLYWHEEL_COMMITMENT );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorWorkTaskBudget.PROPOSED_TASK_BUDGET.getDecoratorCanvasLocation(),
				FmsDecoratorWorkTaskBudget.PROPOSED_TASK_BUDGET );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorWorkTeam.PROPOSED_TEAM.getDecoratorCanvasLocation(),
				FmsDecoratorWorkTeam.PROPOSED_TEAM );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorStory.SUGGESTED_STORY.getDecoratorCanvasLocation(),
				FmsDecoratorStory.SUGGESTED_STORY );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorSequence.SEQUENCE_SWAG.getDecoratorCanvasLocation(),
				FmsDecoratorSequence.SEQUENCE_SWAG );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorCompletion.COMPLETION_NOT_SCHEDULED.getDecoratorCanvasLocation(),
				FmsDecoratorCompletion.COMPLETION_NOT_SCHEDULED );
		return theDecKanGlDecoratorMap;
	}
	
	public static void startNodeEditorActivity(GcgActivity anActivity, String aNodeListParentNodeId, ArrayList<FmmHeadlineNodeShallow> aHeadlineNodeShallowList, String anInitialNodeIdToDisplay) {
		FmmHeadlineNodeImpl.startNodeEditorActivity(
				anActivity,
				aNodeListParentNodeId,
				aHeadlineNodeShallowList,
				anInitialNodeIdToDisplay,
				FmmNodeDefinition.STRATEGIC_MILESTONE );
	}
	
	public static void startNodePickerActivity(GcgActivity anActivity, ArrayList<String> aNodeIdExclusionList, String aWhereClause, String aListActionLabel) {
		FmsActivityHelper.startHeadlineNodePickerActivity(anActivity, FmmNodeDefinition.STRATEGIC_MILESTONE, aNodeIdExclusionList, aWhereClause, aListActionLabel);
	}
	
	public static StrategicMilestone getFmmConfiguration(Intent anIntent) {
		return FmmDatabaseMediator.getActiveMediator().retrieveStrategicMilestone(NodeId.getNodeIdString(anIntent));
	}

	public int getTargetMonthEnd() {
		return this.targetMonthEnd;
	}

	public void setTargetMonthEnd(int targetMonthEnd) {
		this.targetMonthEnd = targetMonthEnd;
	}

	public Date getTargetDate() {
		return this.targetDate;
	}

	public Long getTargetDateFormattedUtcLong() {
		return GcgDateHelper.getFormattedUtcLong(this.targetDate);
	}

	public void setTargetDate(Date aTargetDate) {
		this.targetDate = aTargetDate;
	}

	public void setTargetDate(Long aLongDate) {
		this.targetDate = GcgDateHelper.getDateFromFormattedUtcLong(aLongDate);
	}
	
	@Override
	public String getTargetDateString() {
		String theString = null;
		if(this.targetMonthEnd != 0) {
            theString = GcgMonth.getMonthForNumber(this.targetMonthEnd).getMonthName() + " month end";
        } else if(this.targetDate != GcgDateHelper.NULL_DATE) {
            theString = GcgDateHelper.getGuiDateString4(this.targetDate);
        }
        if(theString != null) {
            if(this.targetIsReversePlanning) {
                theString = "** " + theString;
            }
        } else {
            theString = "";
        }
		return theString;
	}
	
	public boolean hasTargetDate() {
		return this.targetMonthEnd != 0 || ! this.targetDate.equals(GcgDateHelper.NULL_DATE);
	}

    @Override
    public boolean hasSecondaryHeadline() {
        return hasTargetDate();
    }

    @Override
    public String getSecondaryHeadline() {
        return getTargetDateString();
    }

    public boolean targetIsReversePlanning() {
        return this.targetIsReversePlanning;
    }

    public void setTargetIsReversePlanning(boolean bTargetIsReversePlanning) {
        this.targetIsReversePlanning = bTargetIsReversePlanning;
    }

    public void setTargetIsReversePlanning(int bTargetIsReversePlanningInt) {
        this.targetIsReversePlanning = bTargetIsReversePlanningInt == 0 ? false : true;
    }

    public int targetIsReversePlanningAsInt() {
        return this.targetIsReversePlanning ? 1 : 0;
    }

    @Override
    public ArrayList<? extends FmmHeadlineNode> getChildList(FmmNodeDefinition aChildNodeDefinition) {
        ArrayList<? extends FmmHeadlineNodeImpl> theList = null;
        switch(aChildNodeDefinition) {
            case STRATEGIC_ASSET:
                theList = FmmDatabaseMediator.getActiveMediator().listStrategicAssets(this);
                break;
        }
        return theList;
    }
}
