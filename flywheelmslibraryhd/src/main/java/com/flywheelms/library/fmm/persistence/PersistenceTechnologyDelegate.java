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

import com.flywheelms.library.fmm.node.impl.commitment.StrategicCommitment;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceRole;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceTarget;
import com.flywheelms.library.fmm.node.impl.event.PdfPublication;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.FlywheelTeam;
import com.flywheelms.library.fmm.node.impl.governable.FmsOrganization;
import com.flywheelms.library.fmm.node.impl.governable.Portfolio;
import com.flywheelms.library.fmm.node.impl.governable.Project;
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fmm.node.impl.governable.StrategicMilestone;
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fmm.node.impl.governable.WorkTask;
import com.flywheelms.library.fmm.node.impl.link.OrganizationCommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.CompletionNodeTrash;
import com.flywheelms.library.fmm.node.impl.nodefrag.FragLock;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragCompletion;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragFseDocument;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragGovernance;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragTribKnQuality;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragWorkTaskBudget;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fmm.repository.FmmConfiguration;

import java.util.ArrayList;
import java.util.Collection;

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

	public abstract boolean dbDeleteRowsWithValue(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aValue, boolean bAtomicTransaction);
	
	public abstract ArrayList<String> dbGetRowIdList(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aColumnValue);
	
	public abstract ArrayList<String> dbGetRowIdList(FmmNodeDefinition anFmmNodeDefinition, String aWhereClause);
	
	public abstract ArrayList<String> dbGetRowIdListSorted(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aColumnValue, String aSortColumnName, boolean bAscending);
	
	public abstract ArrayList<String> dbGetRowIdListSorted(FmmNodeDefinition anFmmNodeDefinition, String aWhereClause, String aSortColumnName, boolean bAscending);

	public abstract boolean dbUpdateHeadlineNodeHeadline(FmmHeadlineNode aHeadlineNode, boolean bAtomicTransaction);
	
	////////////    Sequencing    ///////////////

	public abstract int dbGetLastSequence(
			String aTableName,
			String aWhereColumnName,
			String aWhereColumnValue );
	
	public abstract void dbIncrementSequence(
			String aTableName,
			String aWhereColumnName,
			String aWhereColumnValue );
	
	public abstract void dbIncrementSequence(
			String aTableName,
			String aWhereColumnName,
			String aWhereColumnValue,
			int aFirstSequenceToIncrement );
	
	public abstract void dbResequenceOnRemove(
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
	//////  Node - COMMUNITY MEMBER  ////////////////////////////////////////////////////////////////////////////

	public abstract ArrayList<CommunityMember> dbRetrieveCommunityMemberList();

	public abstract ArrayList<CommunityMember> dbRetrieveCommunityMemberListForOrganization(String anOrganizationId);

	public abstract CommunityMember dbRetrieveCommunityMember(String aNodeIdString);

	public abstract boolean dbExistsCommunityMember(String aNodeIdString);
	
	public abstract boolean dbInsertCommunityMember(CommunityMember aCommunityMember, boolean bAtomicTransaction);

	public abstract boolean dbUpdateCommunityMember(CommunityMember aCommunityMember, boolean bAtomicTransaction);

	public abstract boolean dbDeleteCommunityMember(CommunityMember aCommunityMember, boolean bAtomicTransaction);
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - COMMUNITY MEMBER, ORGANIZATION, GOVERNANCE AUTHORITY  ////////////////////////////////////////

	public abstract ArrayList<CommunityMember> getGovernanceCandidates(FmsOrganization anFmsOrganization, GovernanceTarget aGovernanceTarget, GovernanceRole aGovernanceRole);

	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - COMPLETION NODE TRASH  ///////////////////////////////////////////////////////////////////////////////////

	public abstract Collection<CompletionNodeTrash> dbRetrieveCompletionNodeTrashList();

	public abstract CompletionNodeTrash dbRetrieveCompletionNodeTrash(String aNodeIdString);

	public abstract boolean dbInsertCompletionNodeTrash(CompletionNodeTrash aCompletionNodeTrash, boolean bAtomicTransaction);

	public abstract boolean dbDeleteCompletionNodeTrash(CompletionNodeTrash aCompletionNodeTrash, boolean bAtomicTransaction);
	

	//////  Node - PORTFOLIO  ////////////////////////////////////////////////////////////////////////////////

    public abstract ArrayList<Portfolio> dbListPortfolio(FmsOrganization anOrganization, Portfolio aPortfolioException);

    public abstract boolean dbInsertPortfolio(Portfolio aPortfolio, boolean bAtomicTransaction);

	//////  Node - PROJECT  ////////////////////////////////////////////////////////////////////////////////

    public abstract ArrayList<Project> dbListProject(Portfolio aPortfolio);

    public abstract ArrayList<Project> dbListProject(Portfolio aPortfolio, Project aProjectException);

    public abstract ArrayList<Project> dbListProject(String aPortfolioId);

    public abstract ArrayList<Project> dbListProject(String aPortfolioId, String aProjectExceptionId);
    
	//////  Node - FISCAL YEAR  ////////////////////////////////////////////////////////////////////////////////

	public abstract ArrayList<FiscalYear> dbListFiscalYear(FmsOrganization anOrganization, FiscalYear aFiscalYearException);

	public abstract int dbCountFiscalYearForStrategicMilestoneMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException);

	public abstract ArrayList<FiscalYear> dbListFiscalYearForStrategicMilestoneMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException);

	public abstract int dbCountFiscalYearForProjectAssetMoveTarget(FmsOrganization anOrganization, StrategicMilestone aStrategicMilestonException);

	public abstract ArrayList<FiscalYear> dbListFiscalYearForProjectAssetMoveTarget(FmsOrganization anOrganization, StrategicMilestone aStrategicMilestonException);

	public abstract int dbCountFiscalYearForWorkPackageMoveTarget(FmsOrganization anOrganization, ProjectAsset aProjectAssetException);

	public abstract ArrayList<FiscalYear> dbListFiscalYearForWorkPackageMoveTarget(FmsOrganization anOrganization, ProjectAsset aProjectAssetException);

	public abstract int dbCountFiscalYearForFlywheelMilestoneMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException);

	public abstract ArrayList<FiscalYear> dbListFiscalYearForFlywheelMilestoneMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException);
	
	public abstract FiscalYear dbRetrieveFiscalYear(String aNodeIdString);

	public abstract boolean dbExistsFiscalYear(String aNodeIdString);
	
	public abstract boolean dbInsertFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction);

	public abstract boolean dbUpdateFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction);

	public abstract boolean dbDeleteFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction);


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FLYWHEEL TEAM  ///////////////////////////////////////////////////////////////////////////////

	public abstract ArrayList<FlywheelTeam> dbRetrieveFlywheelTeamList();

	public abstract ArrayList<FlywheelTeam> dbRetrieveFlywheelTeamList(String anOrganizationId);

	public abstract ArrayList<FlywheelTeam> dbRetrieveFlywheelTeamList(FmsOrganization anOrganization);

	public abstract FlywheelTeam dbRetrieveFlywheelTeam(String aNodeIdString);

	public abstract boolean dbInsertFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction);

	public abstract boolean dbUpdateFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction);

	public abstract boolean dbDeleteFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction);


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FRAG LOCK  ////////////////////////////////////////////////////////////////////////

	public abstract ArrayList<FragLock> dbRetrieveFragLockList();

	public abstract FragLock dbRetrieveFragLock(String aNodeIdString);

	public abstract FragLock dbRetrieveFragLockForParent(String aParentId);

	public abstract FragLock dbRetrieveFragLockForGrandparent(String aGrandparentId);

	public abstract boolean dbInsertFragLock(FragLock aFragLock, boolean bAtomicTransaction);

	public abstract boolean dbUpdateFragLock(FragLock aFragLock, boolean bAtomicTransaction);

	public abstract boolean dbDeleteFragLock(FragLock aFragLock, boolean bAtomicTransaction);


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG AUDIT BLOCK  ////////////////////////////////////////////////////////////////////////

	public abstract ArrayList<NodeFragAuditBlock> dbRetrieveNodeFragAuditBlockList();

	public abstract NodeFragAuditBlock dbRetrieveNodeFragAuditBlock(String aNodeIdString);

	public abstract NodeFragAuditBlock dbRetrieveNodeFragAuditBlockForParent(String aParentId);

	public abstract boolean dbInsertNodeFragAuditBlock(NodeFragAuditBlock aNodeFragAuditBlock, boolean bAtomicTransaction);

	public abstract boolean dbUpdateNodeFragAuditBlock(NodeFragAuditBlock aNodeFragAuditBlock, boolean bAtomicTransaction);

	public abstract boolean dbDeleteNodeFragAuditBlock(NodeFragAuditBlock aNodeFragAuditBlock, boolean bAtomicTransaction);

	public abstract boolean dbDeleteNodeFragAuditBlockForParent(String aParentId, boolean bAtomicTransaction);


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG COMPLETION  ////////////////////////////////////////////////////////////////////////

	public abstract ArrayList<NodeFragCompletion> dbRetrieveNodeFragCompletionList();

	public abstract NodeFragCompletion dbRetrieveNodeFragCompletion(String aNodeIdString);

	public abstract NodeFragCompletion dbRetrieveNodeFragCompletionForParent(String aParentId);

	public abstract boolean dbInsertNodeFragCompletion(NodeFragCompletion aNodeFragCompletion, boolean bAtomicTransaction);

	public abstract boolean dbUpdateNodeFragCompletion(NodeFragCompletion aNodeFragCompletion, boolean bAtomicTransaction);

	public abstract boolean dbDeleteNodeFragCompletion(NodeFragCompletion aNodeFragCompletion, boolean bAtomicTransaction);

	public abstract boolean dbDeleteNodeFragCompletionForParent(String aParentId, boolean bAtomicTransaction);


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG FSE DOCUMENT  //////////////////////////////////////////////////////////////////////

	public abstract ArrayList<NodeFragFseDocument> dbRetrieveNodeFragFseDocumentList();

	public abstract boolean dbExistsNodeFragFseDocument(String aNodeIdString);

	public abstract NodeFragFseDocument dbRetrieveNodeFragFseDocument(String aNodeIdString);

	public abstract NodeFragFseDocument dbRetrieveNodeFragFseDocumentForDocumentId(String aDocumentId);

	public abstract NodeFragFseDocument dbRetrieveNodeFragFseDocumentForParent(String aParentId);

	public abstract boolean dbInsertNodeFragFseDocument(NodeFragFseDocument aNodeFragFseDocument, boolean bAtomicTransaction);

	public abstract boolean dbUpdateNodeFragFseDocument(NodeFragFseDocument aNodeFragFseDocument, boolean bAtomicTransaction);

	public abstract boolean dbDeleteNodeFragFseDocument(String aNodeIdString, boolean bAtomicTransaction);

	public abstract boolean dbDeleteNodeFragFseDocumentForParent(String aParentId, boolean bAtomicTransaction);

	public abstract boolean dbDeleteNodeFragFseDocument(NodeFragFseDocument aNodeFragFseDocument, boolean bAtomicTransaction);


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG GOVERNANCE  ////////////////////////////////////////////////////////////////////////

	public abstract ArrayList<NodeFragGovernance> dbRetrieveNodeFragGovernanceList();

	public abstract NodeFragGovernance dbRetrieveNodeFragGovernance(String aNodeIdString);

	public abstract NodeFragGovernance dbRetrieveNodeFragGovernanceForParent(String aParentId);

	public abstract boolean dbInsertNodeFragGovernance(NodeFragGovernance aNodeFragGovernance, boolean bAtomicTransaction);

	public abstract boolean dbUpdateNodeFragGovernance(NodeFragGovernance aNodeFragGovernance, boolean bAtomicTransaction);

	public abstract boolean dbDeleteNodeFragGovernance(NodeFragGovernance aNodeFragGovernance, boolean bAtomicTransaction);

	public abstract boolean dbDeleteNodeFragGovernanceForParent(String aParentId, boolean bAtomicTransaction);


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG TRIBKN QUALITY  ////////////////////////////////////////////////////////////////////////

	public abstract ArrayList<NodeFragTribKnQuality> dbRetrieveNodeFragTribKnQualityList();

	public abstract NodeFragTribKnQuality dbRetrieveNodeFragTribKnQuality(String aNodeIdString);

	public abstract NodeFragTribKnQuality dbRetrieveNodeFragTribKnQualityForParent(String aParentId);

	public abstract boolean dbInsertNodeFragTribKnQuality(NodeFragTribKnQuality aNodeFragTribKnQuality, boolean bAtomicTransaction);

	public abstract boolean dbUpdateNodeFragTribKnQuality(NodeFragTribKnQuality aNodeFragTribKnQuality, boolean bAtomicTransaction);

	public abstract boolean dbDeleteNodeFragTribKnQuality(NodeFragTribKnQuality aNodeFragTribKnQuality, boolean bAtomicTransaction);

	public abstract boolean dbDeleteNodeFragTribKnQualityForParent(String aParentId, boolean bAtomicTransaction);


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG WORK TASK BUDGET  //////////////////////////////////////////////////////////////////

	public abstract ArrayList<NodeFragWorkTaskBudget> dbRetrieveNodeFragWorkTaskBudgetList();

	public abstract NodeFragWorkTaskBudget dbRetrieveNodeFragWorkTaskBudget(String aNodeIdString);

	public abstract NodeFragWorkTaskBudget dbRetrieveNodeFragWorkTaskBudgetForParent(String aParentId);

	public abstract boolean dbInsertNodeFragWorkTaskBudget(NodeFragWorkTaskBudget aNodeFragWorkTaskBudget, boolean bAtomicTransaction);

	public abstract boolean dbUpdateNodeFragWorkTaskBudget(NodeFragWorkTaskBudget aNodeFragWorkTaskBudget, boolean bAtomicTransaction);

	public abstract boolean dbDeleteNodeFragWorkTaskBudget(NodeFragWorkTaskBudget aNodeFragWorkTaskBudget, boolean bAtomicTransaction);

	public abstract boolean dbDeleteNodeFragWorkTaskBudgetForParent(String aParentId, boolean bAtomicTransaction);


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FMM CONFIGURATION  ////////////////////////////////////////////////////////////////////////////////

	public abstract ArrayList<FmmConfiguration> dbRetrieveFmmConfigurationList();

	public abstract FmmConfiguration dbRetrieveFmmConfiguration(String aNodeIdString);

	public abstract boolean dbInsertFmmConfiguration(FmmConfiguration anFmmConfiguration, boolean bAtomicTransaction);

	public abstract boolean dbUpdateFmmConfiguration(FmmConfiguration anFmmConfiguration, boolean bAtomicTransaction);

	public abstract boolean dbDeleteFmmConfiguration(FmmConfiguration anFmmConfiguration, boolean bAtomicTransaction);

	public abstract void dbSetConfigurationForFmm(FmmConfiguration anFmmConfiguration);

	public abstract FmmConfiguration dbGetConfigurationForFmm();


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FMS ORGANIZATION  ////////////////////////////////////////////////////////////////////////////////

	public abstract ArrayList<FmsOrganization> dbRetrieveFmsOrganizationList();

	public abstract FmsOrganization dbRetrieveFmsOrganization(String aNodeIdString);

	public abstract boolean dbInsertFmsOrganization(FmsOrganization anFmsOrganization, boolean bAtomicTransaction);

	public abstract boolean dbUpdateFmsOrganization(FmsOrganization anFmsOrganization, boolean bAtomicTransaction);

	public abstract boolean dbDeleteFmsOrganization(FmsOrganization anFmsOrganization, boolean bAtomicTransaction);

	public abstract FmsOrganization dbGetFmmOwner();

	public abstract void dbSetFmmOwner(FmsOrganization anOrganization);

	public abstract void dbSetFmmOwnership(FmsOrganization aNewOrganization);


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - ORGANIZATION COMMUNITY MEMBER  ///////////////////////////////////////////////////////////////

	public abstract ArrayList<OrganizationCommunityMember> dbRetrieveOrganizationCommunityMemberList();

	public abstract OrganizationCommunityMember dbRetrieveOrganizationCommunityMember(String aNodeIdString);

	public abstract boolean dbInsertOrganizationCommunityMember(OrganizationCommunityMember anOrganizationCommunityMember, boolean bIsAtomicTransacction);

	public abstract boolean dbUpdateOrganizationCommunityMember(OrganizationCommunityMember anOrganizationCommunityMember, boolean bAtomicTransaction);

	public abstract boolean dbDeleteOrganizationCommunityMember(OrganizationCommunityMember anOrganizationCommunityMember, boolean bAtomicTransaction);

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - PDF PUBLICATION  /////////////////////////////////////////////////////////////////////////////

		public abstract ArrayList<PdfPublication> dbRetrievePdfPublicationList();

		public abstract PdfPublication dbRetrievePdfPublication(String aNodeIdString);
		
		public abstract ArrayList<PdfPublication> dbRetrievePdfPublicationLisForTargetNode(String aTargetId);
		
		public abstract ArrayList<PdfPublication> dbRetrievePdfPublicationLisForTargetNodeAndCommunityMember(
				String aTargetId, String aCommunityMemberId);

		public abstract boolean dbInsertPdfPublication(PdfPublication aPdfPublication, boolean bAtomicTransaction);

		public abstract boolean dbUpdatePdfPublication(PdfPublication anPdfPublication, boolean bAtomicTransaction);

		public abstract boolean dbDeletePdfPublication(PdfPublication aPdfPublication, boolean bAtomicTransaction);
		

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////  Node - PROJECT  /////////////////////////////////////////////////////////////////////////////////////

		public abstract ArrayList<Project> dbRetrieveProjectList();

		public abstract ArrayList<Project> dbRetrieveProjectList(StrategicMilestone aStrategicMilestone);

		public abstract Project dbRetrieveProject(String aNodeIdString);

		public abstract boolean dbInsertProject(Project aProject, boolean bAtomicTransaction);

		public abstract boolean dbUpdateProject(Project aProject, boolean bAtomicTransaction);

		public abstract boolean dbDeleteProject(Project aProject, boolean bAtomicTransaction);
		

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////  Node - PROJECT ASSET  ///////////////////////////////////////////////////////////////////////////////

		public abstract ArrayList<ProjectAsset> dbListProjectAsset(Project aProject);
		
		public abstract ArrayList<ProjectAsset> dbListProjectAsset(Project aProject, ProjectAsset aProjectAssetException);
		
		public abstract ArrayList<ProjectAsset> dbListProjectAssetForProject(String aProjectId, String aProjectAssetExceptionId);
		
		public abstract ArrayList<ProjectAsset> dbListProjectAsset(StrategicMilestone aStrategicMilestone);
		
		public abstract ArrayList<ProjectAsset> dbListProjectAsset(StrategicMilestone aStrategicMilestone, ProjectAsset aProjectAssetException);
		
		public abstract ArrayList<ProjectAsset> dbListProjectAssetForStrategicMilestone(String aStrategicMilestoneId, String aProjectAssetExceptionId);

		public abstract ArrayList<ProjectAsset> dbListProjectAssetForWorkPackageMoveTarget(String aStrategicMilestoneId, String aProjectAssetExceptionId, boolean bPrimaryParent);

		public abstract ArrayList<ProjectAsset> dbListProjectAssetOrphansFromProject();

		public abstract ArrayList<ProjectAsset> dbListProjectAssetOrphansFromStrategicMilestone();
		
		public abstract ProjectAsset dbRetrieveProjectAsset(String aNodeIdString);

		public abstract boolean dbExistsProjectAsset(String aNodeIdString);

		public abstract boolean dbInsertProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction);

		public abstract boolean dbUpdateProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction);

		public abstract boolean dbMoveSingleProjectAssetToStrategicMilestone(
				String aProjectAssetId,
				String anOriginalStrategicMilestoneId,
				String aDestinationStrategicMilestoneId,
				boolean bSequenceAtEnd,
				boolean bAtomicTransaction);
		
		public abstract boolean dbMoveAllProjectAssetsToStrategicMilestone(
				String aSourceStrateticMilestoneId,
				String aDestinationStrategicMilestoneId,
				boolean bSequenceAtEnd,
				boolean bAtomicTransaction);

		public abstract boolean dbMoveSingleProjectAssetToProject(
				String aProjectAssetId,
				String aSourceProjectId,
				String aDestinationProjectId,
				boolean bSequenceAtEnd,
				boolean bAtomicTransaction);

		public abstract boolean dbMoveAllProjectAssetsToProject(
				String aSourceProjectId,
				String aDestinationProjectId,
				boolean bSequenceAtEnd,
				boolean bAtomicTransaction);

		public abstract boolean dbOrphanAllProjectAssetsFromProject(String aProjectId, boolean bAtomicTransaction);

		public abstract boolean dbOrphanSingleProjectAssetFromProject(String aProjectAssetId, String aProjectId, boolean bAtomicTransaction);
		
		public abstract boolean dbOrphanAllProjectAssetsFromStrategicMilestone(String aStrategicMilestoneId, boolean bAtomicTransaction);
		
		public abstract boolean dbOrphanSingleProjectAssetFromStrategicMilestone(String aProjectAssetId, String aStrategicMilestoneId, boolean bAtomicTransaction);

		public abstract boolean dbAdoptOrphanProjectAssetIntoProject(
				String aProjectAssetId,
				String aProjectId,
				boolean bSequenceAtEnd,
				boolean bAtomicTransaction );

		public abstract boolean dbDeleteProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction);

		public abstract int dbGetMoveTargetWorkPackageCount(ProjectAsset aProjectAsset, WorkPackage aWorkPackageException);

		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////  Node - STRATEGIC COMMITMENT  ////////////////////////////////////////////////////////////////////////

		public abstract ArrayList<StrategicCommitment> dbRetrieveStrategicCommitmentList();

		public abstract ArrayList<StrategicCommitment> dbRetrieveStrategicCommitmentList(FiscalYear aStrategicCommitment);

		public abstract ArrayList<StrategicCommitment> dbRetrieveStrategicCommitmentListForStrategicMilestone(String aStrategicMilestoneId);
		
		public abstract StrategicCommitment dbRetrieveStrategicCommitment(String aNodeIdString);

		public abstract StrategicCommitment dbRetrieveStrategicCommitment(String aStrategicMilestoneId, String aProjectAssetId);

		public abstract StrategicCommitment dbRetrieveStrategicCommitmentForProjectAsset(String aProjectAssetId);

		public abstract boolean dbInsertStrategicCommitment(StrategicCommitment aStrategicCommitment, boolean bAtomicTransaction);

		public abstract boolean dbUpdateStrategicCommitment(StrategicCommitment aStrategicCommitment, boolean bAtomicTransaction);

		public abstract boolean dbDeleteStrategicCommitment(StrategicCommitment aStrategicCommitment, boolean bAtomicTransaction);


		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////  Node - STRATEGIC MILESTONE  /////////////////////////////////////////////////////////////////////////

		public abstract ArrayList<StrategicMilestone> dbListStrategicMilestone(FiscalYear aFiscalYear);

		public abstract ArrayList<StrategicMilestone> dbListStrategicMilestone(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestoneException);

		public abstract ArrayList<StrategicMilestone> dbListStrategicMilestone(String aFiscalYearId);

		public abstract ArrayList<StrategicMilestone> dbListStrategicMilestone(String aFiscalYearId, String aStrategicMilestoneExceptiionId);
		
		public abstract int dbCountStrategicMilestoneForProjectAssetMoveTarget(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestoneException);

		public abstract ArrayList<StrategicMilestone> dbListStrategicMilestoneForProjectAssetMoveTarget(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestoneException);
		
		public abstract int dbCountStrategicMilestoneForWorkPackageMoveTarget(FiscalYear aFiscalYear, ProjectAsset aProjectAssetException);
		
		public abstract ArrayList<StrategicMilestone> dbListStrategicMilestoneForWorkPackageMoveTarget(FiscalYear aFiscalYear, ProjectAsset aProjectAssetException);
		
		public abstract StrategicMilestone dbRetrieveStrategicMilestone(String aNodeIdString);

		public abstract boolean dbExistsStrategicMilestone(String aNodeIdString);

		public abstract boolean dbInsertStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction);

		public abstract boolean dbUpdateStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction);

		public abstract boolean dbUpdateTargetDate(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction);
		
		public abstract boolean dbDeleteStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction);

		public abstract int dbMoveAllStrategicMilestonesToFiscalYear(
				String aCurrentFiscalYearId,
				String aDestinationFiscalYearId,
				boolean bSequenceAtEnd,
				boolean bAtomicTransaction );

		public abstract boolean dbMoveSingleStrategicMilestoneToFiscalYear(
				String aStrategicMilestoneId,
				String anOriginalFiscalYearId,
				String aDestinationFiscalYearId,
				boolean bSequenceAtEnd, boolean bAtomicTransaction);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////  Node - WORK PACKAGE  ////////////////////////////////////////////////////////////////////////////////

		public abstract ArrayList<WorkPackage> dbRetrieveWorkPackageList();

		public abstract ArrayList<WorkPackage> dbListWorkPackageForProjectAsset(String aProjectAssetId);

		public abstract ArrayList<WorkPackage> dbListWorkPackageForFlywheelMilestone(String aFlywheelMilestoneId);

		public abstract ArrayList<WorkPackage> dbListWorkPackageForWorkTaskMoveTarget(String aParentNodeId, String aWorkPackageExceptionId, boolean bPrimaryParent);
		
		public abstract WorkPackage dbRetrieveWorkPackage(String aNodeIdString);

		public abstract boolean dbInsertWorkPackage(WorkPackage aWorkPackage, boolean bAtomicTransaction);

		public abstract boolean dbUpdateWorkPackage(WorkPackage aWorkPackage, boolean bAtomicTransaction);

		public abstract boolean dbOrphanAllWorkPackagesFromProjectAsset(String aProjectAssetNodeId, boolean bAtomicTransaction);

		public abstract ArrayList<WorkPackage> dbListWorkPackageOrphansFromProjectAsset();

		public abstract ArrayList<WorkPackage> dbListWorkPackageOrphansFromFlywheelMilestone();

		public abstract boolean dbAdoptOrphanWorkPackageIntoProjectAsset(
				String aWorkPackageId,
				String aProjectAssetId,
				boolean bSequenceAtEnd,
				boolean bAtomicTransaction );

		public abstract boolean dbMoveAllWorkPackagesToProjectAsset(
				String aSourceProjectAssetId,
				String aDestinationProjectAssetId, boolean bSequenceAtEnd,
				boolean bAtomicTransaction);

		public abstract boolean dbDeleteWorkPackage(WorkPackage aWorkPackage, boolean bAtomicTransaction);

		public abstract <T extends FmmConfiguration> void synchronizeFmmConfigurationRowWithConfigFile(T fmmConfiguration);

		/////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//////  Node - WORK TASK  ///////////////////////////////////////////////////////////////////////////////////

		public abstract Collection<WorkTask> dbRetrieveWorkTaskList();

		public abstract WorkTask dbRetrieveWorkTask(String aNodeIdString);

		public abstract boolean dbInsertWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction);

		public abstract boolean dbUpdateWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction);
		
		public abstract boolean dbDeleteWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction);
}
