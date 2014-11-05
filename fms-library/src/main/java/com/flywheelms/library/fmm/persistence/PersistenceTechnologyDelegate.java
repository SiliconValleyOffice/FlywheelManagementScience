/* @(#)FmmTechnologyDelegate.java
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

package com.flywheelms.library.fmm.persistence;

import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.fmm.database.dao.FmmNodeDao;
import com.flywheelms.library.fmm.interfaces.WorkAsset;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceRole;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceTarget;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.FmsOrganization;
import com.flywheelms.library.fmm.node.impl.governable.Portfolio;
import com.flywheelms.library.fmm.node.impl.governable.Project;
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fmm.node.impl.governable.StrategicAsset;
import com.flywheelms.library.fmm.node.impl.governable.StrategicMilestone;
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fmm.node.impl.governable.WorkTask;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.fmm.repository.FmmConfiguration;

import java.util.ArrayList;

public abstract class PersistenceTechnologyDelegate {
	
	protected final PersistenceTechnology persistenceTechnology;
	protected String activeDatabaseName;

	public PersistenceTechnologyDelegate(PersistenceTechnology aPersistenceTechnology) {
		this.persistenceTechnology = aPersistenceTechnology;
	}
	
	public abstract <T extends FmmConfiguration> void setActiveDatabase(T anFmmConfiguration);
	
	public String getActiveDatabaseName() {
		return this.activeDatabaseName;
	}

	public abstract void dbPragma();

	public abstract void startTransaction();

	public abstract void endTransaction(boolean bSuccess);

	public abstract void dbClose();

	public abstract ArrayList<String> getFmmNodeIdList(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aColumnValue, String aSortSpec);
	
	public abstract ArrayList<String> getFmmNodeIdList(FmmNodeDefinition anFmmNodeDefinition, String aWhereClause, String aSortSpec);
	
	////////////    Sequencing    ///////////////

	public abstract int getLastSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue);

    public abstract int getLastSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue,
            String aSequenceColumnName);
	
	public abstract void incrementSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue);

    public abstract void incrementSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue,
            String aSequenceColumnName);
	
	public abstract void incrementSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue,
            int aFirstSequenceToIncrement);

    public abstract void incrementSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue,
            int aFirstSequenceToIncrement,
            String aSequenceColumnName);
	
	public abstract void resequenceOnRemove(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue,
            int aMissingSequence);

	public abstract void dbSwapSequence(FmmHeadlineNode aParentNode, FmmHeadlineNode aTargetNode, FmmHeadlineNode aPeerNode);
	
	public abstract int dbGetPrimarySequence(FmmHeadlineNode aTargetNode);

	public abstract void dbSetPrimarySequence(FmmHeadlineNode aHeadlineNode, int aNewSequence);
	
	public abstract int dbGetSecondarySequence(FmmHeadlineNode aTargetNode);

	public abstract void dbSetSecondarySequence(FmmHeadlineNode aHeadlineNode, int aNewSequence);
	
	public abstract int dbGetLinkNodeSequence(FmmHeadlineNode aTargetNode);

	public abstract void dbSetLinkNodeSequence(FmmHeadlineNode aHeadlineNode, int aNewSequence);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////


	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - COMMUNITY MEMBER, ORGANIZATION, GOVERNANCE AUTHORITY  ////////////////////////////////////////

	public abstract ArrayList<CommunityMember> getGovernanceCandidates(FmsOrganization anFmsOrganization, GovernanceTarget aGovernanceTarget, GovernanceRole aGovernanceRole);

	


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - PORTFOLIO  ////////////////////////////////////////////////////////////////////////////////

    public abstract int countPortfolioForProjectMoveTarget(FmsOrganization anFmsOrganization, Portfolio aPortfolioException);

    public abstract ArrayList<? extends GcgGuiable> retrievePortfolioListForProjectMoveTarget(FmsOrganization anFmsOrganization, Portfolio aPortfolioException);

    public abstract ArrayList<Portfolio> retrievePortfolioListForWorkAssetMoveTarget(FmsOrganization anFmsOrganization, Project aProjectException);

    public abstract ArrayList<Portfolio> retrievePortfolioListForWorkPackageMoveTarget(FmsOrganization anFmsOrganization, WorkAsset aWorkAssetException);

    public abstract ArrayList<? extends GcgGuiable> retrievePortfolioListForWorkTaskMoveTarget(FmsOrganization anFmsOrganization, WorkPackage aWorkPackageException);

    public abstract boolean adoptOrphanProjectIntoPortfolio(String aProjectId, String aPortfolioId, boolean bSequenceAtEnd, boolean bAtomicTransaction);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FISCAL YEAR  ////////////////////////////////////////////////////////////////////////////////


	public abstract int countFiscalYearForStrategicMilestoneMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException);

	public abstract ArrayList<FiscalYear> retrieveFiscalYearListForStrategicMilestoneMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException);

	public abstract int countFiscalYearForProjectAssetMoveTarget(FmsOrganization anOrganization, StrategicMilestone aStrategicMilestonException);

	public abstract ArrayList<FiscalYear> retrieveFiscalYearForProjectAssetMoveTarget(FmsOrganization anOrganization, StrategicMilestone aStrategicMilestonException);

	public abstract int countFiscalYearForWorkPackageMoveTarget(FmsOrganization anOrganization, ProjectAsset aProjectAssetException);

	public abstract ArrayList<FiscalYear> retrieveFiscalYearListForWorkPackageMoveTarget(FmsOrganization anOrganization, ProjectAsset aProjectAssetException);

	public abstract int countFiscalYearForCadenceMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException);

	public abstract ArrayList<FiscalYear> retrieveFiscalYearListForCadenceMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException);
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FMM CONFIGURATION  ////////////////////////////////////////////////////////////////////////////////

    public abstract FmsOrganization getFmmOwner();

    public abstract void setFmmOwnership(FmsOrganization aNewOrganization);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - PROJECT  /////////////////////////////////////////////////////////////////////////////////////

    public abstract boolean orphanSingleProjectFromPortfolio(String aProjectId, String aPortfolioId, boolean bAtomicTransaction);

    public abstract ArrayList<Project> retrieveProjectOrphanListFromPortfolio();

    public abstract int countProjectsForProjectAssetMoveTarget(Portfolio aPortfolio, Project aProjectException);

    public abstract ArrayList<Project> retrieveProjectListForProjectAssetMoveTarget(Portfolio aPortfolio, Project aProjectException);

    public abstract ArrayList<Project> retrieveProjectListForWorkPackageMoveTarget(Portfolio aPortfolio, WorkAsset aWorkAssetException);

    public abstract ArrayList<Project> retrieveProjectListForWorkTaskMoveTarget(Portfolio aPortfolio, WorkPackage aWorkPackageException);

    public abstract boolean orphanAllProjectsFromPortfolio(String aPortfolioId, boolean bAtomicTransaction);

    public abstract boolean moveSingleProjectIntoPortfolio(String aProjectId, String aPortfolioId, boolean bAtomicTransaction);

    public abstract boolean moveAllProjectsIntoPortfolio(String aCurrentPortfolioId, String aTargetPortfolioId, boolean bAtomicTransaction);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - PROJECT ASSET  ///////////////////////////////////////////////////////////////////////////////

    // TODO - sort out StrategicAsset versus ProjectAsset versus WorkAsset
    public abstract ArrayList<ProjectAsset> retrieveStrategicAssetListForWorkPackageMoveTarget(String aStrategicMilestoneId, String aStrategicAssetExceptionId);

    public abstract ArrayList<ProjectAsset> retrieveWorkAssetListForWorkTaskMoveTarget(String aProjectId, String aWorkPackageException);

    public abstract ArrayList<ProjectAsset> retrieveWorkAssetOrphanListFromProject();

    public abstract ArrayList<StrategicAsset> retrieveStrategicAssetOrphanListFromProject();

    public abstract boolean moveSingleProjectAssetIntoProject(
            String aProjectAssetId,
            String aSourceProjectId,
            String aDestinationProjectId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction);

    public abstract boolean moveAllProjectAssetsIntoProject(
            String aSourceProjectId,
            String aDestinationProjectId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction);

    public abstract boolean orphanAllProjectAssetsFromProject(String aProjectId, boolean bAtomicTransaction);

    public abstract boolean orphanSingleProjectAssetFromProject(String aProjectAssetId, String aProjectId, boolean bAtomicTransaction);

    public abstract boolean orphanAllProjectAssetsFromStrategicMilestone(String aStrategicMilestoneId, boolean bAtomicTransaction);

    public abstract boolean orphanSingleProjectAssetFromStrategicMilestone(String aProjectAssetId, String aStrategicMilestoneId, boolean bAtomicTransaction);

    // TODO - ????
    public abstract int dbGetMoveTargetWorkPackageCount(ProjectAsset aProjectAsset, WorkPackage aWorkPackageException);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - STRATEGIC MILESTONE  /////////////////////////////////////////////////////////////////////////


    public abstract int countStrategicMilestoneForProjectAssetMoveTarget(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestoneException);

    public abstract ArrayList<StrategicMilestone> retrieveStrategicMilestoneListForProjectAssetMoveTarget(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestoneException);

    public abstract int countStrategicMilestoneForWorkPackageMoveTarget(FiscalYear aFiscalYear, ProjectAsset aProjectAssetException);

    public abstract ArrayList<StrategicMilestone> retrieveStrategicMilestoneListForWorkPackageMoveTarget(FiscalYear aFiscalYear, ProjectAsset aProjectAssetException);

    public abstract int moveAllStrategicMilestonesIntoFiscalYear(
            String aCurrentFiscalYearId,
            String aDestinationFiscalYearId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction);

    public abstract boolean moveSingleStrategicMilestoneIntoFiscalYear(
            String aStrategicMilestoneId,
            String anOriginalFiscalYearId,
            String aDestinationFiscalYearId,
            boolean bSequenceAtEnd, boolean bAtomicTransaction);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - WORK PACKAGE  ////////////////////////////////////////////////////////////////////////////////

    public abstract ArrayList<WorkPackage> retrieveWorkPackageListForWorkTaskMoveTarget(String aParentNodeId, String aWorkPackageExceptionId, boolean bPrimaryParent);

    public abstract boolean orphanAllWorkPackagesFromProjectAsset(String aProjectAssetNodeId, boolean bAtomicTransaction);

    public abstract boolean orphanAllWorkPackagesFromCadence(String aCadenceId, boolean bAtomicTransaction);

    public abstract boolean orphanSingleWorkPackageFromProjectAsset(String aWorkPackageId, String aProjectAssetId, boolean bAtomicTransaction);

    public abstract boolean orphanSingleWorkPackageFromCadence(String aWorkPackageId, String aCadenceId, boolean bAtomicTransaction);

    public abstract ArrayList<WorkPackage> retrieveWorkPackageOrphanListFromProjectAsset();

    public abstract ArrayList<WorkPackage> retrieveWorkPackageOrphanListFromCadence();

    public abstract boolean adoptOrphanWorkPackageIntoProjectAsset(
            String aWorkPackageId,
            String aProjectAssetId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction);

    public abstract boolean moveAllWorkPackagesIntoProjectAsset(
            String aSourceProjectAssetId,
            String aDestinationProjectAssetId, boolean bSequenceAtEnd,
            boolean bAtomicTransaction);

    public abstract boolean moveSingleWorkPackageIntoProjectAsset(
            String aWorkPackageId,
            String anOriginalProjectAssetId,
            String aDestinationProjectAssetId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction);

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - WORK TASK  ///////////////////////////////////////////////////////////////////////////////////

    public abstract boolean moveAllWorkTasksIntoWorkPackage(String aSourceWorkPackageId, String aDestinationWorkPackageId, boolean bSequenceAtEnd, boolean bAtomicTransaction);

    public abstract boolean orphanAllWorkTasksFromWorkPackage(String aWorkPackageId, boolean bAtomicTransaction);

    public abstract boolean orphanSingleWorkTaskFromWorkPackage(String aWorkTaskId, String aWorkPackageId, boolean bAtomicTransaction);

    public abstract ArrayList<WorkTask> retrieveWorkTaskOrphanListFromWorkPackage();

    public abstract ArrayList<WorkTask> retrieveWorkTaskOrphanListFromWorkPlan();

    public abstract boolean adoptOrphanWorkTaskIntoWorkPackage(String aWorkTaskId, String aWorkPackageId, boolean bSequenceAtEnd, boolean bAtomicTransaction);


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - STRATEGIC ASSET  ///////////////////////////////////////////////////////////////////////////////////

    public abstract boolean moveAllStrategicAssetsIntoStrategicMilestone(String aSourceStrateticMilestoneId, String aDestinationStrategicMilestoneId, boolean bSequenceAtEnd, boolean bAtomicTransaction);

    public abstract boolean moveSingleStrategicAssetIntoStrategicMilestone(String aStrategicAssetId, String anOriginalStrategicMilestonetId, String aDestinationStrategicMilestoneId, boolean bSequenceAtEnd, boolean bAtomicTransaction);

    public abstract boolean moveSingleStrategicAssetIntoProject(
            String aStrategicAssetId,
            String aSourceProjectId,
            String aDestinationProjectId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction);

    public abstract boolean orphanSingleStrategicAssetFromProject(String aStrategicAssetId, String aProjectId, boolean bAtomicTransaction);


    /////////////////////////////////////////
    //////////  GENERIC METHODS  ////////////
    /////////////////////////////////////////

    public abstract boolean deleteRowFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aNodeIdString, boolean bAtomicTransaction);

    public abstract boolean deleteRowFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aWhereColumnName, String aWhereColumnValue, boolean bAtomicTransaction);

    public abstract int getLinkTableNodeSequence(FmmNodeDefinition aLinkTableFmmNodeDefinition, String aParentIdColumnName, String aParentNodeId, String aChildIdColumnName, String aPeerNodeId);

    public abstract FmmNodeDao getDao(FmmNodeDefinition anFmmNodeDefinition);

    // EXISTS & RETRIEVE

    public abstract boolean existsSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aNodeIdString);

    public abstract boolean existsSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aWhereColumnName, String aWhereColumnValue);

    public abstract <T extends FmmNode> T retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aNodeIdString);

    public abstract <T extends FmmNode> T retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aColumnValue);

    public abstract <T extends FmmNode> T retrieveFmmNodeFromTableForParent(FmmNodeDefinition anFmmNodeDefinition, String aParentId);

    public abstract ArrayList<FmmHeadlineNode> retrieveFmmHeadlineNodeListFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition);

    public abstract <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromSimpleIdTable(
            FmmNodeDefinition aNodeDefinition,
            String aWhereColumnName,
            String aWhereColumnValue,
            String anExceptionId,
            String aSortSpec);

    public abstract <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromSimpleIdTable(
            FmmNodeDefinition aNodeDefinition,
            String aWhereClause,
            String anExceptionId,
            String aSortSpec);

    public abstract <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromSimpleIdLeftTableFromLink(
            FmmNodeDefinition aLeftTableDefinition,
            String aLeftColumnName,
            String aLefgColumnExceptionValue,
            FmmNodeDefinition aRightTableDefinition,
            String aRightColumnName,
            String anAndSpec,
            String aSortSpec);

    // INSERT & UPDATE

    public abstract boolean insertSimpleIdTable(FmmNode anFmmNode, boolean bAtomicTransaction);

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public abstract <V extends FmmNode> boolean updateSimpleIdTable(V anFmmNode, boolean bAtomicTransaction);

    public abstract boolean fractalUpdateNodeHeadline(FmmHeadlineNode anFmmHeadlineNode);

    // GENERATE SQL

    public String getInnerJoinQueryWithAndSpecSorted(
            FmmNodeDefinition aLeftTableDefinition,
            String aLeftColumnName,
            String aLeftColumnExceptionValue,
            FmmNodeDefinition aRightTableDefinition,
            String aRightColumnName,
            String aAndSpec,
            String aSortSpec )  {
        StringBuffer theQuery = new StringBuffer();
        theQuery.append(
                "SELECT DISTINCT " + aLeftTableDefinition.getTableName() + ".* FROM " + aLeftTableDefinition.getTableName() +
                " INNER JOIN " + aRightTableDefinition.getTableName() +
                " ON " + aLeftTableDefinition.getTableName() + "." + aLeftColumnName + " = " + aRightTableDefinition.getTableName() + "." + aRightColumnName +
                " AND " + aAndSpec );
        if(aLeftColumnExceptionValue != null) {
            theQuery.append(" AND " + aLeftTableDefinition.getTableName() + "." + aLeftColumnName + " != '" + aLeftColumnExceptionValue + "'");
        }
        if(aSortSpec != null) {
            theQuery.append(" ORDER BY " + aSortSpec);
        }
        return theQuery.toString();
    }
}
