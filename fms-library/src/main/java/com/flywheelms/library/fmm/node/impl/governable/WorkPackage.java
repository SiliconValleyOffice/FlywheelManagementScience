/* @(#)WorkPackage.java
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

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.library.fmm.FmmDatabaseService;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.meta_data.WorkPackageMetaData;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.commitment.CadenceCommitment;
import com.flywheelms.library.fmm.node.impl.completable.FmmCompletionNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fmm.transaction.FmmNodeGlyphType;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class WorkPackage extends FmmCompletionNodeImpl implements Comparable<WorkPackage> {

	private static final long serialVersionUID = -297745950602591732L;
	private String workAssetId;
    private WorkAsset workAsset;
	private String cadenceCommitmentId;
	private CadenceCommitment cadenceCommitment;
    private ArrayList<WorkTask> workTaskList;

	public WorkPackage(NodeId aNodeId) {
		super(aNodeId);
		// TODO Auto-generated constructor stub
	}

	public WorkPackage(String aHeadline, WorkAsset aWorkAsset) {
		super(new NodeId(WorkPackage.class));
		setHeadline(aHeadline);
		setWorkAsset(aWorkAsset);
	}

    public WorkPackage(NodeId aNodeId, String aHeadline) {
        super(aNodeId, aHeadline);
    }

	public WorkPackage(String anExistingNodeIdString) {
		super(NodeId.hydrate(WorkPackage.class, anExistingNodeIdString));
		// TODO Auto-generated constructor stub
	}

    public WorkPackage(JSONObject aJsonObject) {
        super(WorkPackage.class, aJsonObject);
        try {
            validateSerializationFormatVersion(aJsonObject.getString(JsonHelper.key__SERIALIZATION_FORMAT_VERSION));
            setWorkAssetId(aJsonObject.getString(WorkPackageMetaData.column_WORK_ASSET_ID));
            setCadenceCommitmentId(aJsonObject.getString(WorkPackageMetaData.column_CADENCE_COMMITMENT_ID));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

	@Override
	public int compareTo(WorkPackage another) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public String getCadenceCommitmentId() {
		return this.cadenceCommitmentId;
	}

	public void setCadenceCommitmentId(String aNodeIdString) {
		this.cadenceCommitmentId = aNodeIdString;
		if(this.cadenceCommitment != null && ! this.cadenceCommitment.getNodeIdString().equals(aNodeIdString)) {
			this.cadenceCommitment = null;
		}
	}
	
	public CadenceCommitment getCadenceCommitment() {
		if(this.cadenceCommitment == null) {
			if(this.cadenceCommitmentId != null && this.cadenceCommitmentId.length() > 0) {
				FmmDatabaseService.getActiveMediator().retrieveCadenceCommitment(this.cadenceCommitmentId);
			}
		}
		return this.cadenceCommitment;
	}
	
	public void setCadenceCommitment(CadenceCommitment aFlywheelCommitment) {
		this.cadenceCommitment = aFlywheelCommitment;
		this.cadenceCommitmentId = aFlywheelCommitment.getNodeIdString();
	}

    public String getWorkAssetId() {
        return this.workAssetId;
    }

    public WorkAsset getWorkAsset() {
        if(this.workAsset == null && this.workAssetId != null) {
            this.workAsset =
                    FmmDatabaseService.getActiveMediator().retrieveWorkAsset(this.workAssetId);
        }
        return this.workAsset;
    }

    public void setWorkAssetId(String aWorkAssetId) {
        this.workAssetId = aWorkAssetId;
        if(this.workAsset != null && !this.workAsset.getNodeIdString().equals(aWorkAssetId)) {
            this.workAsset = null;
        }
    }

    public void setWorkAsset(WorkAsset aWorkAsset) {
        this.workAsset = aWorkAsset;
        this.workAssetId = aWorkAsset.getNodeIdString();
    }

	public static void startNodeEditorActivity(GcgActivity anActivity, String aNodeListParentNodeId, ArrayList<FmmHeadlineNodeShallow> aHeadlineNodeShallowList, String anInitialNodeIdToDisplay) {
		FmmHeadlineNodeImpl.startNodeEditorActivity(
				anActivity,
				aNodeListParentNodeId,
				aHeadlineNodeShallowList,
				anInitialNodeIdToDisplay,
				FmmNodeDefinition.WORK_PACKAGE );
	}
	
	public static void startNodePickerActivity(GcgActivity anActivity, ArrayList<String> aNodeIdExclusionList, String aWhereClause, String aListActionLabel) {
		FmsActivityHelper.startHeadlineNodePickerActivity(anActivity, FmmNodeDefinition.WORK_PACKAGE, aNodeIdExclusionList, aWhereClause, aListActionLabel);
	}
	
	public static WorkPackage getFmmConfiguration(Intent anIntent) {
		return FmmDatabaseService.getActiveMediator().retrieveWorkPackage(NodeId.getNodeIdString(anIntent));
	}
	
	public ProjectAsset getProjectAsset() {
		return FmmDatabaseService.getActiveMediator().retrieveProjectAsset(this.workAssetId);
	}

	public boolean isWorkTaskMoveTarget() {
		return true;
	}

    public Collection<WorkTask> getWorkTaskCollection() {
        return getWorkTaskList();
    }

    public ArrayList<WorkTask> getWorkTaskList() {
        if(this.workTaskList == null) {
            this.workTaskList = FmmDatabaseService.getActiveMediator().retrieveWorkTaskList(this);
        }
        return this.workTaskList;
    }

    private int countGreenWorkTasks() {
        int theGreenCount = 0;
        for(WorkTask theWorkTask : getWorkTaskCollection()) {
            if(theWorkTask.isGreen()) {
                ++theGreenCount;
            }
        }
        return theGreenCount;
    }

    @Override
    protected void initializeNodeCompletionSummaryMap() {
        super.initializeNodeCompletionSummaryMap();
        NodeCompletionSummary theNodeCompletionSummary = new NodeCompletionSummary();
        theNodeCompletionSummary.setSummaryDrawableResourceId(
                FmmNodeDefinition.WORK_TASK.getUndecoratedGlyphResourceId(FmmNodeGlyphType.GREEN));
        updateNodeCompletionSummary(FmmPerspective.WORK_BREAKDOWN, theNodeCompletionSummary);
        this.nodeCompletionSummaryMap.put(FmmPerspective.WORK_BREAKDOWN, theNodeCompletionSummary);
    }

    @Override
    public void updateNodeCompletionSummary(FmmPerspective anFmmPerspective, NodeCompletionSummary aNodeSummary) {
        if(anFmmPerspective == FmmPerspective.WORK_BREAKDOWN) {
            Collection<WorkTask> theWorkTaskCollection = getWorkTaskCollection();
            if(theWorkTaskCollection.size() > 0) {
                aNodeSummary.setShowNodeSummary(true);
                aNodeSummary.setSummaryPrefix("( " + countGreenWorkTasks() + " ");
                aNodeSummary.setSummarySuffix(" of " + theWorkTaskCollection.size() + " )");
            } else {
                aNodeSummary.setShowNodeSummary(false);
            }
        }
    }

    @Override
    public ArrayList<? extends FmmHeadlineNode> getPeerHeadlineNodeShallowList(FmmHeadlineNode aParentHeadlineNode) {
        ArrayList<WorkPackage> theList;
        switch(aParentHeadlineNode.getFmmNodeDefinition()) {
            case PROJECT_ASSET:
                theList = FmmDatabaseService.getActiveMediator().retrieveWorkPackageListForWorkAsset(aParentHeadlineNode.getNodeIdString(), null);
                break;
            case CADENCE:
                theList = FmmDatabaseService.getActiveMediator().retrieveWorkPackageListForCadence(aParentHeadlineNode.getNodeIdString(), null);
                break;
            default:
                theList = new ArrayList<WorkPackage>();
                theList.add(this);
        }
        return theList;
    }

    @Override
    public int getChildNodeCount(GcgPerspective aGcgPerspective) {  // only implemented for TreeView leaf nodes
        return getWorkTaskList() == null ? 0 : getWorkTaskList().size();
    }

    @Override
    public ArrayList<? extends FmmHeadlineNode> getChildList(FmmNodeDefinition aChildNodeDefinition) {
        ArrayList<? extends FmmHeadlineNodeImpl> theList = null;
        switch(aChildNodeDefinition) {
            case WORK_TASK:
                theList = FmmDatabaseService.getActiveMediator().retrieveWorkTaskList(this);
                break;
        }
        return theList;
    }

    public void setPrimaryParentId(String aNodeIdString) {
        setWorkAssetId(aNodeIdString);
    }

}
