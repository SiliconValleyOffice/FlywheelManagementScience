/* @(#)Project.java
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

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.meta_data.ProjectMetaData;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.completable.FmmCompletableNodeImpl;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fmm.transaction.FmmNodeGlyphType;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;

public class Project extends FmmCompletableNodeImpl {

	private static final long serialVersionUID = -4072230008356590343L;
    private String portfolioNodeIdString;
    private Portfolio portfolio;
    private ArrayList<ProjectAsset> projectAssetList;

    public Project(NodeId aNodeId, String aHeadline, String aPortfolioNodeIdString) {
        super(aNodeId);
        setHeadline(aHeadline);
        setPortfolioNodeIdString(aPortfolioNodeIdString);
    }

	public Project(NodeId aNodeId) {
		super(aNodeId);
	}
	
	public Project(String aExistingNodeIdString) {
		super(NodeId.hydrate(
				Project.class,
				aExistingNodeIdString ));
	}

    public Project(JSONObject aJsonObject) {
        super(Project.class, aJsonObject);
        try {
            validateSerializationFormatVersion(aJsonObject.getString(JsonHelper.key__SERIALIZATION_FORMAT_VERSION));
            setPortfolioNodeIdString(aJsonObject.getString(ProjectMetaData.column_PORTFOLIO_ID));
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
	
	public static void startNodeEditorActivity(GcgActivity anActivity, String aNodeListParentNodeId, ArrayList<FmmHeadlineNodeShallow> aHeadlineNodeShallowList, String anInitialNodeIdToDisplay) {
		FmmHeadlineNodeImpl.startNodeEditorActivity(
				anActivity,
				aNodeListParentNodeId,
				aHeadlineNodeShallowList,
				anInitialNodeIdToDisplay,
				FmmNodeDefinition.PROJECT );
	}
	
	public static void startNodePickerActivity(GcgActivity anActivity, ArrayList<String> aNodeIdExclusionList, String aWhereClause, String aListActionLabel) {
		FmsActivityHelper.startHeadlineNodePickerActivity(anActivity, FmmNodeDefinition.PROJECT, aNodeIdExclusionList, aWhereClause, aListActionLabel);
	}

	public boolean isProjectAssetMoveTarget() {
		return true;
	}

    public String getPortfolioNodeIdString() {
        return this.portfolioNodeIdString;
    }

    public Portfolio getPortfolio() {
        if(this.portfolio == null && this.portfolioNodeIdString != null) {
            this.portfolio =
                    FmmDatabaseMediator.getActiveMediator().getPortfolio(this.portfolioNodeIdString);
        }
        return this.portfolio;
    }

    public void setPortfolioNodeIdString(String aNodeIdString) {
        this.portfolioNodeIdString = aNodeIdString;
        if(this.portfolio != null && !this.portfolio.getNodeIdString().equals(aNodeIdString)) {
            this.portfolio = null;
        }
    }

    public void setPortfolio(Portfolio aPortfolio) {
        this.portfolio = aPortfolio;
        this.portfolioNodeIdString = aPortfolio.getNodeId().getNodeIdString();
    }

    public Collection<ProjectAsset> getProjectAssetCollection() {
        return getProjectAssetList();
    }

    public ArrayList<ProjectAsset> getProjectAssetList() {
        if(this.projectAssetList == null) {
            this.projectAssetList = FmmDatabaseMediator.getActiveMediator().listProjectAssetsForProject(this.getNodeIdString());
        }
        return this.projectAssetList;
    }

    @Override
    protected void initializeNodeCompletionSummaryMap() {
        super.initializeNodeCompletionSummaryMap();
        NodeCompletionSummary theNodeCompletionSummary = new NodeCompletionSummary();
        theNodeCompletionSummary.setSummaryDrawableResourceId(
                FmmNodeDefinition.PROJECT_ASSET.getUndecoratedGlyphResourceId(FmmNodeGlyphType.GREEN) );
        updateNodeCompletionSummary(FmmPerspective.WORK_BREAKDOWN, theNodeCompletionSummary);
        this.nodeCompletionSummaryMap.put(FmmPerspective.WORK_BREAKDOWN, theNodeCompletionSummary);
    }

    @Override
    public void updateNodeCompletionSummary(FmmPerspective anFmmPerspective, NodeCompletionSummary aNodeSummary) {
        if(anFmmPerspective == FmmPerspective.WORK_BREAKDOWN) {
            Collection<ProjectAsset> theProjectAssetCollection = getProjectAssetCollection();
            if(theProjectAssetCollection.size() > 0) {
                aNodeSummary.setShowNodeSummary(true);
                aNodeSummary.setSummaryPrefix("( " + countGreenProjectAssets() + " ");
                aNodeSummary.setSummarySuffix(" of " + theProjectAssetCollection.size() + " )");
            } else {
                aNodeSummary.setShowNodeSummary(false);
            }
        }
    }

    private int countGreenProjectAssets() {
        int theGreenCount = 0;
        for(ProjectAsset theProjectAsset : getProjectAssetCollection()) {
            if(theProjectAsset.isGreen()) {
                ++theGreenCount;
            }
        }
        return theGreenCount;
    }

}
