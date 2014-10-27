/* @(#)FiscalYear.java
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

import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.gcongui.deckangl.interfaces.DecKanGlDecorator;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
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
import com.flywheelms.library.fmm.meta_data.FiscalYearMetaData;
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
import java.util.HashMap;

public class FiscalYear extends FmmCompletableNodeImpl implements Comparable<FiscalYear> {
	
	private static final long serialVersionUID = -2661300227094514671L;
    public static final String SERIALIZATION_FORMAT_VERSION = "0.1";
	private String organizationNodeIdString;
	private FmsOrganization organization;
	private int year = 0;
    private int firstMonthOfFiscalYear = 1;
    private int cadenceDuration = 3;
    private String workPlanFirstDayOfWeek = "Tue";
	private ArrayList<StrategicMilestone> strategicMilestoneList;
	private ArrayList<Cadence> flywheelCadenceList;

	// create a new Fiscal Year
	public FiscalYear(NodeId aNodeId, String anOrganizationNodeIdString, String aYear) {
		super(aNodeId);
		setYear(aYear);
		setOrganizationId(anOrganizationNodeIdString);
	}
	
	// create a new Fiscal Year
	public FiscalYear(NodeId aNodeId, FmsOrganization anOrganization, String aYear) {
		super(aNodeId);
		setYear(aYear);
		setOrganization(anOrganization);
	}

	@Override
	public void setHeadline(String headline) {  /*  N/A  */  }

	@Override
	public int getSequence() {
		return 0;
	}

	@Override
	public void setSequence(int aSequence) {  /*  N/A  */  }
	
	public FiscalYear(String anExistingNodeIdString) {
		super(NodeId.hydrate(
				FiscalYear.class,
				anExistingNodeIdString ));
	}
	
	public FiscalYear(JSONObject aJsonObject) {
		super(FiscalYear.class, aJsonObject);
		try {
			validateSerializationFormatVersion(aJsonObject.getString(JsonHelper.key__SERIALIZATION_FORMAT_VERSION));
			setOrganizationId(aJsonObject.getString(FiscalYearMetaData.column_ORGANIZATION_ID));
			setYear(aJsonObject.getString(FiscalYearMetaData.column_YEAR_NUMBER));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    @Override
    public JSONObject getJsonObject() {
        JSONObject theJsonObject = super.getJsonObject();
        try {
            theJsonObject.put(JsonHelper.key__SERIALIZATION_FORMAT_VERSION, SERIALIZATION_FORMAT_VERSION);
            theJsonObject.put(FiscalYearMetaData.column_ORGANIZATION_ID, getOrganizationNodeIdString());
            theJsonObject.put(FiscalYearMetaData.column_YEAR_NUMBER, getYearString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return theJsonObject;
    }

    public JSONArray getStrategicMilestoneNodeIdStringJsonArray() {
        JSONArray theJsonArray = new JSONArray();
        for(StrategicMilestone theStrategicMilestone : getStrategicMilestoneList()) {
            theJsonArray.put(theStrategicMilestone.getNodeIdString());
        }
        return theJsonArray;
    }

    @Override
    public FiscalYear getClone() {
        return new FiscalYear(getJsonObject());
    }

	public void setStrategicMilestoneList(JSONArray aJsonArray) {
		this.strategicMilestoneList = new ArrayList<StrategicMilestone>();
		for(int i=0; i < aJsonArray.length(); ++i) {
			try {
				this.strategicMilestoneList.add(FmmDatabaseMediator.getActiveMediator().retrieveStrategicMilestone(
                        aJsonArray.getString(i)));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getOrganizationNodeIdString() {
		return this.organizationNodeIdString;
	}

	public void setOrganizationId(String anOrganizationNodeId) {
		this.organizationNodeIdString = anOrganizationNodeId;
	}
	
	public FmsOrganization getOrganization() {
		if(this.organization == null && this.organizationNodeIdString != null) {
			this.organization = FmmDatabaseMediator.getActiveMediator().getFmsOrganization(this.organizationNodeIdString);
		}
		return this.organization;
	}
	
	public void setOrganization(FmsOrganization anOrganization) {
		this.organization = anOrganization;
		this.organizationNodeIdString = anOrganization.getNodeIdString();
	}
	
	public String getYearString() {
		return this.headline;
	}
	
	@Override
	public String getDataText() {
		return getYearString();
	}
	
	public int getYearAsInt() {
		return this.year;
	}

	public void setYear(String aYear) {
		this.year = Integer.decode(aYear);
		this.headline = aYear;
	}
	
	@Override
	public int compareTo(FiscalYear anOtherFiscalYear) {
		return (getYearAsInt() < anOtherFiscalYear.getYearAsInt() ? -1 :
			(getYearAsInt() == anOtherFiscalYear.getYearAsInt() ? 0 : 1));
	}

	@Override
	protected void initializeNodeCompletionSummaryMap() {
		super.initializeNodeCompletionSummaryMap();
        initializeNodeCompletionSummaryMap(FmmPerspective.STRATEGIC_PLANNING, FmmNodeDefinition.STRATEGIC_MILESTONE);
        initializeNodeCompletionSummaryMap(FmmPerspective.WORK_PLANNING, FmmNodeDefinition.CADENCE);
	}

	@Override
	public void updateNodeCompletionSummary(FmmPerspective anFmmPerspective, NodeCompletionSummary aNodeSummary) {
        if(anFmmPerspective == FmmPerspective.STRATEGIC_PLANNING) {
			Collection<StrategicMilestone> theStrategicMilestoneCollection = getStrategicMilestoneCollection();
			if(theStrategicMilestoneCollection.size() > 0) {
				aNodeSummary.setShowNodeSummary(true);
				aNodeSummary.setSummaryPrefix("( " + countGreenStrategicMilestones() + " ");
				aNodeSummary.setSummarySuffix(" of " + theStrategicMilestoneCollection.size() + " )");
			} else {
				aNodeSummary.setShowNodeSummary(false);
			}
		} else if(anFmmPerspective == FmmPerspective.WORK_PLANNING) {
            Collection<Cadence> theCadenceCollection = getCadenceCollection();
            if(theCadenceCollection.size() > 0) {
                aNodeSummary.setShowNodeSummary(true);
                aNodeSummary.setSummaryPrefix("( " + countGreenCadences() + " ");
                aNodeSummary.setSummarySuffix(" of " + theCadenceCollection.size() + " )");
            } else {
                aNodeSummary.setShowNodeSummary(false);
            }
        }
	}

	public Collection<StrategicMilestone> getStrategicMilestoneCollection() {
		return getStrategicMilestoneList();
	}

	public ArrayList<StrategicMilestone> getStrategicMilestoneList() {
		if(this.strategicMilestoneList == null) {
			this.strategicMilestoneList = FmmDatabaseMediator.getActiveMediator().getStrategicMilestoneListForFiscalYear(getNodeIdString());
		}
		return this.strategicMilestoneList;
	}

	private int countGreenStrategicMilestones() {
		int theGreenCount = 0;
		for(StrategicMilestone theStrategicMilestone : getStrategicMilestoneCollection()) {
			if(theStrategicMilestone.isGreen()) {
				++theGreenCount;
			}
		}
		return theGreenCount;
	}

    public Collection<Cadence> getCadenceCollection() {
        return getCadenceList();
    }

    public ArrayList<Cadence> getCadenceList() {
        if(this.flywheelCadenceList == null) {
            this.flywheelCadenceList = FmmDatabaseMediator.getActiveMediator().getCadenceListForFiscalYear(getNodeIdString());
        }
        return this.flywheelCadenceList;
    }

    private int countGreenCadences() {
        int theGreenCount = 0;
        for(Cadence theCadence : getCadenceCollection()) {
            if(theCadence.isGreen()) {
                ++theGreenCount;
            }
        }
        return theGreenCount;
    }
	
	/////////////////////////////////////////////////////////////////////////
	//////  TEMPORARY for development scaffolding  //////////////////////////
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
				FmsDecoratorChildFractals.PROPOSED_CHILD_FRACTALS.getDecoratorCanvasLocation(),
				FmsDecoratorChildFractals.PROPOSED_CHILD_FRACTALS );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorStrategicCommitment.PROPOSED_STRATEGIC_COMMITMENT.getDecoratorCanvasLocation(),
				FmsDecoratorStrategicCommitment.PROPOSED_STRATEGIC_COMMITMENT );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorFlywheelCommitment.PROPOSED_FLYWHEEL_COMMITMENT.getDecoratorCanvasLocation(),
				FmsDecoratorFlywheelCommitment.PROPOSED_FLYWHEEL_COMMITMENT );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorWorkTaskBudget.CONFIRMED_TASK_BUDGET.getDecoratorCanvasLocation(),
				FmsDecoratorWorkTaskBudget.CONFIRMED_TASK_BUDGET );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorWorkTeam.CONFIRMED_TEAM.getDecoratorCanvasLocation(),
				FmsDecoratorWorkTeam.CONFIRMED_TEAM );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorStory.PROPOSED_STORY.getDecoratorCanvasLocation(),
				FmsDecoratorStory.PROPOSED_STORY );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorSequence.PROPOSED_SEQUENCE.getDecoratorCanvasLocation(),
				FmsDecoratorSequence.PROPOSED_SEQUENCE );
		theDecKanGlDecoratorMap.put(
				FmsDecoratorCompletion.NO_COMPLETION_ISSUES.getDecoratorCanvasLocation(),
				FmsDecoratorCompletion.NO_COMPLETION_ISSUES );
		return theDecKanGlDecoratorMap;
	}
	
	public static void startNodeEditorActivity(GcgActivity anActivity, String aNodeListParentNodeId, ArrayList<FmmHeadlineNodeShallow> aHeadlineNodeShallowList, String anInitialNodeIdToDisplay) {
		FmmHeadlineNodeImpl.startNodeEditorActivity(
				anActivity,
				aNodeListParentNodeId,
				aHeadlineNodeShallowList,
				anInitialNodeIdToDisplay,
				FmmNodeDefinition.FISCAL_YEAR );
	}
	
	public static void startNodePickerActivity(GcgActivity anActivity, ArrayList<String> aNodeIdExclusionList, String aWhereClause, String aListActionLabel) {
		FmsActivityHelper.startHeadlineNodePickerActivity(anActivity, FmmNodeDefinition.FISCAL_YEAR, aNodeIdExclusionList, aWhereClause, aListActionLabel);
	}

    public int getCadenceDuration() {
        return this.cadenceDuration;
    }

    public void setCadenceDuration(int cadenceDuration) {
        this.cadenceDuration = cadenceDuration;
    }

    public String getWorkPlanFirstDayOfWeek() {
        return this.workPlanFirstDayOfWeek;
    }

    public void setWorkPlanFirstDayOfWeek(String workPlanFirstDayOfWeek) {
        this.workPlanFirstDayOfWeek = workPlanFirstDayOfWeek;
    }

    @Override
    public ArrayList<? extends FmmHeadlineNode> getChildList(FmmNodeDefinition aChildNodeDefinition) {
        ArrayList<? extends FmmHeadlineNodeImpl> theList = null;
        switch(aChildNodeDefinition) {
            case STRATEGIC_MILESTONE:
                theList = FmmDatabaseMediator.getActiveMediator().getStrategicMilestoneList(this);
                break;
            case CADENCE:
                theList = FmmDatabaseMediator.getActiveMediator().getCadenceList(this);
                break;
        }
        return theList;
    }

    public void setPrimaryParentId(String aNodeIdString) {
        setOrganizationId(aNodeIdString);
    }

    public int getFirstMonthOfFiscalYear() {
        return this.firstMonthOfFiscalYear;
    }

    public void setFirstMonthOfFiscalYear(int firstMonthOfFiscalYear) {
        this.firstMonthOfFiscalYear = firstMonthOfFiscalYear;
    }
}
