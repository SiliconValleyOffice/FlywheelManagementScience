/* @(#)FmmDatabaseMediator.java
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

package com.flywheelms.library.fmm;

import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fca.FlywheelCommunityAuthentication;
import com.flywheelms.library.fmm.database.sqlite.dao.StrategicAssetDaoSqLite;
import com.flywheelms.library.fmm.helper.FmmHelper;
import com.flywheelms.library.fmm.interfaces.WorkAsset;
import com.flywheelms.library.fmm.meta_data.CommunityMemberMetaData;
import com.flywheelms.library.fmm.meta_data.CompletableNodeMetaData;
import com.flywheelms.library.fmm.meta_data.FiscalYearHolidayBreakMetaData;
import com.flywheelms.library.fmm.meta_data.FiscalYearMetaData;
import com.flywheelms.library.fmm.meta_data.FlywheelCadenceMetaData;
import com.flywheelms.library.fmm.meta_data.FlywheelTeamMetaData;
import com.flywheelms.library.fmm.meta_data.FmmConfigurationMetaData;
import com.flywheelms.library.fmm.meta_data.FmsOrganizationMetaData;
import com.flywheelms.library.fmm.meta_data.FragLockMetaData;
import com.flywheelms.library.fmm.meta_data.GovernanceNodeMetaData;
import com.flywheelms.library.fmm.meta_data.HeadlineNodeMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragAuditBlockMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragCompletionMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragFseDocumentMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragGovernanceMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragTribKnQualityMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragWorkTaskBudgetMetaData;
import com.flywheelms.library.fmm.meta_data.PdfPublicationMetaData;
import com.flywheelms.library.fmm.meta_data.ProjectAssetMetaData;
import com.flywheelms.library.fmm.meta_data.ProjectMetaData;
import com.flywheelms.library.fmm.meta_data.SequencedLinkNodeMetaData;
import com.flywheelms.library.fmm.meta_data.StrategicCommitmentMetaData;
import com.flywheelms.library.fmm.meta_data.StrategicMilestoneMetaData;
import com.flywheelms.library.fmm.meta_data.WorkPackageMetaData;
import com.flywheelms.library.fmm.meta_data.WorkPlanMetaData;
import com.flywheelms.library.fmm.meta_data.WorkTaskMetaData;
import com.flywheelms.library.fmm.node.FmmNodeInfo;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.commitment.StrategicCommitment;
import com.flywheelms.library.fmm.node.impl.enumerator.CompletionCommitmentType;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceRole;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceTarget;
import com.flywheelms.library.fmm.node.impl.event.PdfPublication;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.FlywheelCadence;
import com.flywheelms.library.fmm.node.impl.governable.FlywheelTeam;
import com.flywheelms.library.fmm.node.impl.governable.FmsOrganization;
import com.flywheelms.library.fmm.node.impl.governable.Portfolio;
import com.flywheelms.library.fmm.node.impl.governable.Project;
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fmm.node.impl.governable.StrategicAsset;
import com.flywheelms.library.fmm.node.impl.governable.StrategicMilestone;
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fmm.node.impl.governable.WorkPlan;
import com.flywheelms.library.fmm.node.impl.governable.WorkTask;
import com.flywheelms.library.fmm.node.impl.headline.FiscalYearHolidayBreak;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fmm.node.impl.link.OrganizationCommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.CompletionNodeTrash;
import com.flywheelms.library.fmm.node.impl.nodefrag.FragLock;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragCompletion;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragFseDocument;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragGovernance;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragTribKnQuality;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragWorkTaskBudget;
import com.flywheelms.library.fmm.node.interfaces.FmmSequencedNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmCompletionNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmGovernableNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fmm.persistence.PersistenceTechnologyDelegate;
import com.flywheelms.library.fmm.repository.FmmConfiguration;
import com.flywheelms.library.fse.model.FseDocument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

/*
 * An instance of FmmDatabaseMediator is created for each configured Repository Service.
 * The fmmDatabaseMediatorMap data member is a container for all FmmDatabaseMediator instances,
 * indexed by the configuration of the database instance.
 */
public class FmmDatabaseMediator {

	private static HashMap<String, FmmDatabaseMediator> fmmDatabaseMediatorMap = new HashMap<String, FmmDatabaseMediator>();  // mediator for each database server
	private static FmmDatabaseMediator activeFmmDatabaseMediator;
	protected static FmmConfiguration requestedFmmConfiguration;  // for creating a new database
	private PersistenceTechnologyDelegate persistenceTechnologyDelegate;
	protected FmmConfiguration fmmConfiguration;
	private FmsOrganization fmmOwner;
	private Date fmmTimestamp = GcgDateHelper.getCurrentDateTime();
	private boolean inTransaction = false;

	protected FmmDatabaseMediator(FmmConfiguration anFmmConfiguration) {
		initMetaData();
		FmmDatabaseMediator.requestedFmmConfiguration = this.fmmConfiguration = anFmmConfiguration;
		this.persistenceTechnologyDelegate = this.fmmConfiguration.getPersistenceTechnologyDelegate();
		this.persistenceTechnologyDelegate.setActiveDatabase(this.fmmConfiguration);
		this.persistenceTechnologyDelegate.synchronizeFmmConfigurationRowWithConfigFile(this.fmmConfiguration);
		this.fmmOwner = this.persistenceTechnologyDelegate.dbGetFmmOwner();
	}

	public void reOpenDatabase() {
		this.persistenceTechnologyDelegate.setActiveDatabase(this.fmmConfiguration);
	}

	private static void initMetaData() {
		CommunityMemberMetaData.init();
		CompletableNodeMetaData.init();
		FiscalYearMetaData.init();
        FiscalYearHolidayBreakMetaData.init();
        FlywheelCadenceMetaData.init();
		FlywheelTeamMetaData.init();
		FmmConfigurationMetaData.init();
		FmsOrganizationMetaData.init();
		FragLockMetaData.init();
		GovernanceNodeMetaData.init();
		HeadlineNodeMetaData.init();
		IdNodeMetaData.init();
		NodeFragAuditBlockMetaData.init();
		NodeFragCompletionMetaData.init();
		NodeFragFseDocumentMetaData.init();
		NodeFragGovernanceMetaData.init();
		NodeFragMetaData.init();
		NodeFragTribKnQualityMetaData.init();
		NodeFragWorkTaskBudgetMetaData.init();
		PdfPublicationMetaData.init();
		ProjectAssetMetaData.init();
		ProjectMetaData.init();
		StrategicMilestoneMetaData.init();
		WorkPackageMetaData.init();
		WorkPlanMetaData.init();
	}

	public boolean startTransaction() {
		this.inTransaction = true;
		FmmDatabaseMediator.getActiveMediator().getPersistenceTechnologyDelegate().startTransaction();
		return true;
	}

	public void endTransaction(boolean bSuccess) {
		this.inTransaction = false;
		FmmDatabaseMediator.getActiveMediator().getPersistenceTechnologyDelegate().endTransaction(bSuccess);
		FmmDatabaseMediator.getActiveMediator().notifyDatabaseListeners();
	}

	public boolean inTransaction() {
		return this.inTransaction;
	}

    ////  START SEQUENCE  ///////////

	public void swapSequence(FmmHeadlineNode aTargetNode, FmmHeadlineNode aPeerNode, FmmHeadlineNode aParentNode) {
		this.persistenceTechnologyDelegate.dbSwapSequence(aParentNode, aTargetNode, aPeerNode);
	}

	public void sequenceFirst(FmmHeadlineNode aTargetNode, FmmHeadlineNode aParentNode) {
		if(aTargetNode.getFmmNodeDefinition().isPrimarySequenceNode(aParentNode.getFmmNodeDefinition())) {
			this.persistenceTechnologyDelegate.dbIncrementSequence(
					aTargetNode.getFmmNodeDefinition().getTableName(),
					aParentNode.getFmmNodeDefinition().getTableName() + "__id", aParentNode.getNodeIdString() );
			this.persistenceTechnologyDelegate.dbSetPrimarySequence(aTargetNode, 1);
		} else if(aTargetNode.getFmmNodeDefinition().getSecondaryLinkNodeDefinition() != null) {
			this.persistenceTechnologyDelegate.dbIncrementSequence(
					aTargetNode.getFmmNodeDefinition().getSecondaryLinkNodeDefinition().getTableName(),
					aParentNode.getFmmNodeDefinition().getTableName() + "__id", aParentNode.getNodeIdString() );
			this.persistenceTechnologyDelegate.dbSetLinkNodeSequence(aTargetNode, 1);
		} else {
			this.persistenceTechnologyDelegate.dbIncrementSequence(
					aTargetNode.getFmmNodeDefinition().getTableName(),
					aParentNode.getFmmNodeDefinition().getSecondaryParentNodeDefinition() + "__id", aParentNode.getNodeIdString() );
			this.persistenceTechnologyDelegate.dbSetSecondarySequence(aTargetNode, 1);
		}
	}

	public void sequenceLast(FmmHeadlineNode aTargetNode, FmmHeadlineNode aParentNode) {
		if(aTargetNode.getFmmNodeDefinition().isPrimarySequenceNode(aParentNode.getFmmNodeDefinition())) {
			this.persistenceTechnologyDelegate.dbResequenceOnRemove(
					aTargetNode.getFmmNodeDefinition().getTableName(),
					aParentNode.getFmmNodeDefinition().getTableName() + "__id",
					aParentNode.getNodeIdString(),
					((FmmCompletionNode)  aTargetNode).getSequence());
			this.persistenceTechnologyDelegate.dbSetPrimarySequence(
					aTargetNode,
					this.persistenceTechnologyDelegate.dbGetLastSequence(
							aTargetNode.getFmmNodeDefinition().getTableName(),
							aParentNode.getFmmNodeDefinition().getTableName() + "__id",
							aParentNode.getNodeIdString() ) + 1 );
		} else if(aTargetNode.getFmmNodeDefinition().getSecondaryLinkNodeDefinition() != null) {
			this.persistenceTechnologyDelegate.dbResequenceOnRemove(
					aTargetNode.getFmmNodeDefinition().getSecondaryLinkNodeDefinition().getTableName(),
					aParentNode.getFmmNodeDefinition().getTableName() + "__id",
					aParentNode.getNodeIdString(),
					this.persistenceTechnologyDelegate.dbGetLinkNodeSequence(aTargetNode) );
			this.persistenceTechnologyDelegate.dbSetLinkNodeSequence(
					aTargetNode,
					this.persistenceTechnologyDelegate.dbGetLastSequence(
							aTargetNode.getFmmNodeDefinition().getSecondaryLinkNodeDefinition().getTableName(),
							aParentNode.getFmmNodeDefinition().getTableName() + "__id",
							aParentNode.getNodeIdString() ) + 1 );
		} else {
			this.persistenceTechnologyDelegate.dbResequenceOnRemove(
					aTargetNode.getFmmNodeDefinition().getTableName(),
					aParentNode.getFmmNodeDefinition().getSecondaryParentNodeDefinition().getTableName() + "__id",
					aParentNode.getNodeIdString(),
					this.persistenceTechnologyDelegate.dbGetSecondarySequence(aTargetNode));
			this.persistenceTechnologyDelegate.dbSetSecondarySequence(aTargetNode,
					this.persistenceTechnologyDelegate.dbGetLastSequence(
							aTargetNode.getFmmNodeDefinition().getTableName(),
							aParentNode.getFmmNodeDefinition().getSecondaryParentNodeDefinition().getTableName() + "__id",
							aParentNode.getNodeIdString() ) + 1 );
		}
	}

    private int initializeNewSequenceNumberForTable(
            FmmNodeDefinition anFmmNodeDefinition,
            String aParentIdColumnName,
            String aParentId,
            boolean bSequenceAtEnd ) {
        return initializeNewSequenceNumberForTable(
                anFmmNodeDefinition,
                aParentIdColumnName,
                aParentId,
                -1,
                bSequenceAtEnd );
    }

    private int initializeNewSequenceNumberForTable(
            FmmNodeDefinition anFmmNodeDefinition,
            String aParentIdColumnName,
            FmmHeadlineNode aParentNode,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd) {
        return initializeNewSequenceNumberForTable(
                anFmmNodeDefinition,
                aParentIdColumnName,
                aParentNode,
                aPeerNode,
                bSequenceAtEnd,
                CompletableNodeMetaData.column_SEQUENCE );
    }

    private int initializeNewSequenceNumberForTable(
            FmmNodeDefinition anFmmNodeDefinition,
            String aParentIdColumnName,
            FmmHeadlineNode aParentNode,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd,
            String aSequenceColumnName ) {
        return initializeNewSequenceNumberForTable(
            anFmmNodeDefinition,
            aParentIdColumnName,
            aParentNode.getNodeIdString(),
            aPeerNode == null ? -1 : ((FmmSequencedNode)aPeerNode).getSequence(),
            bSequenceAtEnd,
            aSequenceColumnName );
    }

    private int initializeNewSequenceNumberForTable(
            FmmNodeDefinition anFmmNodeDefinition,
            String aParentIdColumnName,
            String aParentId,
            int aPeerNodeSequence,
            boolean bSequenceAtEnd ) {
        return initializeNewSequenceNumberForTable(
                anFmmNodeDefinition,
                aParentIdColumnName,
                aParentId,
                aPeerNodeSequence,
                bSequenceAtEnd,
                CompletableNodeMetaData.column_SEQUENCE );
    }

    private int initializeNewSequenceNumberForTable(
            FmmNodeDefinition anFmmNodeDefinition,
            String aParentIdColumnName,
            String aParentId,
            int aPeerNodeSequence,
            boolean bSequenceAtEnd,
            String aSequenceColumnName ) {
        int theNewSequenceNumber;
        if(aPeerNodeSequence < 0) {  // sequence as the first/last child node of parent node
            if(bSequenceAtEnd) {  // last child node of parent
                theNewSequenceNumber = this.persistenceTechnologyDelegate.dbGetLastSequence(
                        anFmmNodeDefinition.getTableName(),
                        aParentIdColumnName,
                        aParentId,
                        aSequenceColumnName );
                theNewSequenceNumber = theNewSequenceNumber + 1;
            } else {  // first child node of parent
                theNewSequenceNumber = 1;
                this.persistenceTechnologyDelegate.dbIncrementSequence(
                        anFmmNodeDefinition.getTableName(),
                        aParentIdColumnName,
                        aParentId,
                        aSequenceColumnName );
            }
        } else { // sequence before/after peer node
            if(bSequenceAtEnd) {  // sequence after peer
                theNewSequenceNumber = aPeerNodeSequence + 1;
            } else { // sequence before peer
                theNewSequenceNumber = aPeerNodeSequence;
            }
            this.persistenceTechnologyDelegate.dbIncrementSequence(
                    anFmmNodeDefinition.getTableName(),
                    aParentIdColumnName,
                    aParentId,
                    theNewSequenceNumber,
                    aSequenceColumnName );
        }
        return theNewSequenceNumber;
    }

    private int resequenceChildTableForAlphaSort(
            FmmNodeDefinition anFmmNodeDefinition,
            String aParentIdColumnName,
            String aParentId,
            String aSequenceColumnName) {
        return 0;  // TODO
    }

    private int initializeNewSequenceNumberForLinkTable(
            FmmNodeDefinition aLinkTableFmmNodeDefinition,
            String aParentIdColumnName,
            String aParentNodeId,
            String aChildIdColumnName,
            String aPeerNodeId,
            boolean bSequenceAtEnd ) {
        int theNewSequenceNumber;
        if(aPeerNodeId == null) {  // sequence as the first/last child node of parent node
            if(bSequenceAtEnd) {  // last child node of parent
                theNewSequenceNumber = this.persistenceTechnologyDelegate.dbGetLastSequence(
                        aLinkTableFmmNodeDefinition.getTableName(),
                        aParentIdColumnName,
                        aParentNodeId,
                        SequencedLinkNodeMetaData.column_SEQUENCE );
                theNewSequenceNumber += theNewSequenceNumber;
            } else {  // first child node of parent
                theNewSequenceNumber = 1;
                this.persistenceTechnologyDelegate.dbIncrementSequence(
                        aLinkTableFmmNodeDefinition.getTableName(),
                        aParentIdColumnName,
                        aParentNodeId,
                        SequencedLinkNodeMetaData.column_SEQUENCE );
            }
        } else { // sequence before/after peer node
            int thePeerNodeSequence = this.persistenceTechnologyDelegate.getLinkTableNodeSequence(
                    aLinkTableFmmNodeDefinition,
                    aParentIdColumnName,
                    aParentNodeId,
                    aChildIdColumnName,
                    aPeerNodeId);
            if(bSequenceAtEnd) {  // sequence after peer
                theNewSequenceNumber = thePeerNodeSequence + 1;
            } else { // sequence before peer
                theNewSequenceNumber = thePeerNodeSequence;
            }
            this.persistenceTechnologyDelegate.dbIncrementSequence(
                    aLinkTableFmmNodeDefinition.getTableName(),
                    aParentIdColumnName,
                    aParentNodeId,
                    theNewSequenceNumber,
                    SequencedLinkNodeMetaData.column_SEQUENCE );
        }
        return theNewSequenceNumber;
    }

    /////  END SEQUENCE  /////////

	public boolean newFmmRootNode(
			FmmNodeDefinition anFmmNodeDefinition,
			String aHeadline ) {
		switch(anFmmNodeDefinition) {
		case BOOKSHELF:
			return false;
		case FISCAL_YEAR:
			return false;
		case PORTFOLIO:
			return false;
		default:
			return false;
		}
	}

	public FmmHeadlineNode newChildHeadlineNode(
			FmmNodeDefinition anFmmNodeDefinition,
			String aHeadline,
			FmmHeadlineNode aParentNode,
			FmmHeadlineNode aPeerNode,
			boolean bSequenceAtEnd ) {
		FmmHeadlineNode theHeadlineNode = null;
		switch(anFmmNodeDefinition) {
		case BOOKSHELF:  // handled by newFmmRootNode()
			break;
		case COMMUNITY_MEMBER:
			break;
		case DISCUSSION_TOPIC:
			break;
		case FACILITATION_ISSUE:
			break;
		case FISCAL_YEAR:  // handled by newFmmRootNode()
			break;
		case FLYWHEEL_CADENCE:
            // only created in a "batch" from the wizard
			break;
		case NOTEBOOK:
			break;
		case PORTFOLIO:  // handled by newFmmRootNode()
			break;
		case PROJECT:
            theHeadlineNode = newProjectForParent(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
			break;
		case PROJECT_ASSET:
			theHeadlineNode = newProjectAssetForProject(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
			break;
		case SERVICE_OFFERING:
			break;
		case SERVICE_OFFERING_SLA:
			break;
		case SERVICE_REQUEST:
			break;
		case SERVICE_REQUEST_TRIAGE_LOG:
			break;
		case STRATEGIC_MILESTONE:
			theHeadlineNode =  newStrategicMilestoneForParent(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd );
			break;
        case STRATEGIC_ASSET:
            theHeadlineNode = newStrategicAssetForStrategicMilestone(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
            break;
		case WORK_PACKAGE:
			theHeadlineNode =  newWorkPackageForParent(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd );
			break;
		case WORK_PLAN:
            // only created in a "batch" from the FlywheelCadence wizard
			break;
		case WORK_TASK:
            theHeadlineNode = newWorkTaskForParent(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
			break;
		default:
			// BOOKSHELF, FISCAL_YEAR and PORTFOLIO must be created with newFmmRootNode()
			break;
		}
		return theHeadlineNode;
	}

	private boolean newHeadlineNode(FmmHeadlineNode aHeadlineNode) {
		boolean theBoolean = newNodeFragAuditBlock(aHeadlineNode) != null;
		theBoolean = theBoolean && newNodeFragFseDocument(aHeadlineNode) != null;
		return theBoolean;
	}

	private boolean newGovernableNode(FmmGovernableNode aGovernableNode) {
		boolean theBoolean = newHeadlineNode(aGovernableNode);
		theBoolean = theBoolean && newNodeFragGovernance(aGovernableNode) != null;
		return theBoolean;
	}

	private boolean newCompletionNode(FmmCompletionNode aCompletionNode) {
		boolean theBoolean = newGovernableNode(aCompletionNode);
		theBoolean = theBoolean && newNodeFragCompletion(aCompletionNode) != null;
		theBoolean = theBoolean && newNodeFragWorkTaskBudgetForParent(aCompletionNode) != null;
		return theBoolean;
	}

	private void updateHeadlineNode(FmmHeadlineNode aHeadlineNode) {
		aHeadlineNode.setRowTimestamp(GcgDateHelper.getCurrentDateTime());
		updateNodeFragAuditBlock(aHeadlineNode);
	}

	public boolean updateHeadlineNodeHeadline(FmmHeadlineNode aHeadlineNode) {
		return this.persistenceTechnologyDelegate.dbUpdateHeadlineNodeHeadline(aHeadlineNode, true);
	}

	private boolean deleteHeadlineNode(FmmHeadlineNode aHeadlineNode) {
		boolean theBoolean = deleteNodeFragAuditBlockForParent(aHeadlineNode, false);
		theBoolean = theBoolean && deleteNodeFragFseDocumentForParent(aHeadlineNode, false);
		theBoolean = theBoolean && deleteNodeFragTribKnQualityForParent(aHeadlineNode, false);
		return theBoolean;
	}

	private boolean deleteGovernableNode(FmmGovernableNode aGovernableNode) {
		boolean theBoolean = deleteHeadlineNode(aGovernableNode);
		theBoolean = theBoolean && deleteNodeFragGovernanceForParent(aGovernableNode, false);
		return theBoolean;
	}

	private boolean deleteCompletableNode(FmmCompletionNode aCompletionNode) {
		boolean theBoolean = newCompletionNodeTrash(aCompletionNode);
		theBoolean = theBoolean && deleteGovernableNode(aCompletionNode);
		theBoolean = theBoolean && deleteNodeFragCompletionForParent(aCompletionNode, false);
		theBoolean = theBoolean && deleteNodeFragWorkTaskBudgetForParent(aCompletionNode, false);
		return theBoolean;
	}

	private boolean deleteRowsWithValue(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aValue) {
		return this.persistenceTechnologyDelegate.dbDeleteRowsWithValue(anFmmNodeDefinition, aColumnName, aValue, false);
	}

	public void replaceFmmOwner(FmsOrganization anOrganization) {
		this.persistenceTechnologyDelegate.dbSetFmmOwnership(anOrganization);
	}

	public void setConfigurationForFmm(FmmConfiguration anFmmConfiguration) {
		this.persistenceTechnologyDelegate.dbSetConfigurationForFmm(anFmmConfiguration);
	}

	public static FmmConfiguration getRequestedFmmConfiguration() {
		return FmmDatabaseMediator.requestedFmmConfiguration;
	}

	public static void setRequestedFmmConfigurataion(FmmConfiguration anFmmConfiguration) {
		FmmDatabaseMediator.requestedFmmConfiguration = anFmmConfiguration;
	}

	private static FmmDatabaseMediator getInstance(FmmConfiguration anFmmConfiguration) {
		FmmDatabaseMediator.requestedFmmConfiguration = anFmmConfiguration;
		FmmDatabaseMediator theFmmDatabaseMediator = FmmDatabaseMediator.fmmDatabaseMediatorMap.get(anFmmConfiguration.getHeadline());
		if(theFmmDatabaseMediator == null) {
			theFmmDatabaseMediator = new FmmDatabaseMediator(anFmmConfiguration);
			FmmDatabaseMediator.fmmDatabaseMediatorMap.put(anFmmConfiguration.getHeadline(),theFmmDatabaseMediator);
		}
		// update FmmConfiguration object, FmmDatabaseMediator from FmmConfiguration row
		return FmmDatabaseMediator.fmmDatabaseMediatorMap.get(anFmmConfiguration.getHeadline());
	}

	public static FmmDatabaseMediator getInstanceAndSetActive(FmmConfiguration aFmmConfiguration) {
		FmmDatabaseMediator.activeFmmDatabaseMediator = getInstance(aFmmConfiguration);
		return FmmDatabaseMediator.activeFmmDatabaseMediator;
	}

	public static void setActiveMediator(FmmConfiguration anFmmConfiguration) {
		if(FmmDatabaseMediator.activeFmmDatabaseMediator != null) {
			FmmDatabaseMediator.activeFmmDatabaseMediator.closeDatabase();
		}
		FmmDatabaseMediator theFmmDatabaseMediator = FmmDatabaseMediator.fmmDatabaseMediatorMap.get(anFmmConfiguration.getHeadline());
		if(theFmmDatabaseMediator != null) {
			theFmmDatabaseMediator.getPersistenceTechnologyDelegate().setActiveDatabase(anFmmConfiguration);
			FmmDatabaseMediator.activeFmmDatabaseMediator = theFmmDatabaseMediator;
		} else {
			FmmDatabaseMediator.activeFmmDatabaseMediator = getInstance(anFmmConfiguration);
		}
	}

	public static FmmDatabaseMediator getActiveMediator() {
		if(FmmDatabaseMediator.activeFmmDatabaseMediator == null) {
			setActiveMediator(FmmHelper.getLocalFmmConfiguration());
		}
		return FmmDatabaseMediator.activeFmmDatabaseMediator;
	}

	public static void closeActiveFmm() {
		if(FmmDatabaseMediator.activeFmmDatabaseMediator == null) {
			return;
		}
		FmmDatabaseMediator.activeFmmDatabaseMediator.closeDatabase();
		activeFmmDatabaseMediator = null;
	}

	private void closeDatabase() {
		getPersistenceTechnologyDelegate().dbClose();
	}

	public FmsOrganization getFmmOwner() {
		return this.fmmOwner;
	}

	public boolean ownsThisFmm(FmsOrganization anFmsOrganization) {
		return anFmsOrganization.getNodeIdString().equals(this.fmmOwner.getNodeIdString());
	}

	public FmmConfiguration getFmmConfiguration() {
		return this.fmmConfiguration;
	}

	public Date getFmmTimestamp() {
		return this.fmmTimestamp;
	}

	public void setFmmTimestamp(Date timestamp) {
		this.fmmTimestamp = timestamp;
	}

	public void updateFmmTimestamp(long aTime) {
		this.fmmTimestamp.setTime(aTime);
	}

	public ArrayList<FmmHeadlineNodeImpl> getHeadlineNodeList(String aNodeTypeCode) {
		FmmNodeDefinition theFmmNodeDefinition = FmmNodeDefinition.getEntryForNodeTypeCode(aNodeTypeCode);
		ArrayList<FmmHeadlineNodeImpl> theHeadlineNodeList = null;
		switch (theFmmNodeDefinition) {
		case COMMUNITY_MEMBER:
			theHeadlineNodeList = new ArrayList<FmmHeadlineNodeImpl>(getCommunityMemberCollection());
			break;
		default:
		}
		return theHeadlineNodeList;
	}

	public FmmNodeInfo getFmmNodeSummary(String aNodeIdString) {
		FmmNodeDefinition theDictionaryEntry = FmmNodeDefinition.getEntryForNodeIdString(aNodeIdString);
		switch(theDictionaryEntry) {
		case FISCAL_YEAR:
			return getFiscalYear(aNodeIdString);
		case STRATEGIC_MILESTONE:
			break;
		case PROJECT_ASSET:
			break;
		default:
		}
		return null;
	}

	public FmmHeadlineNodeImpl getHeadlineNode(String aNodeIdString) {
		FmmNodeDefinition theFmmNodeDefinition = FmmNodeDefinition.getEntryForNodeIdString(aNodeIdString);
		switch(theFmmNodeDefinition) {
		case BOOKSHELF:
			//				return getBookshelf(anFmmId);
		case COMMUNITY_MEMBER:
			return getCommunityMember(aNodeIdString);
		case DISCUSSION_TOPIC:
			//				return getDiscussionTopic(anFmmId);
		case FACILITATION_ISSUE:
			//				return getFacilitationIssue(anFmmId);
		case FISCAL_YEAR:
			return getFiscalYear(aNodeIdString);
		case FLYWHEEL_CADENCE:
            return retrieveFlywheelCadence(aNodeIdString);
		case NOTEBOOK:
			//				return getNotebook(anFmmId);
		case PORTFOLIO:
            return getPortfolio(aNodeIdString);
		case PROJECT:
            return getProject(aNodeIdString);
		case PROJECT_ASSET:
			return getProjectAsset(aNodeIdString);
		case SERVICE_OFFERING:
			//				return getServiceOffering(anFmmId);
		case SERVICE_OFFERING_SLA:
			//				return getServiceOfferingSla(anFmmId);
		case SERVICE_REQUEST:
			//				return getServiceRequest(anFmmId);
		case SERVICE_REQUEST_TRIAGE_LOG:
			//				return getServiceRequestTriageLog(anFmmId);
		case STRATEGIC_ASSET:
			return retrieveStrategicAsset(aNodeIdString);
		case STRATEGIC_MILESTONE:
			return retrieveStrategicMilestone(aNodeIdString);
		case WORK_PACKAGE:
			return retrieveWorkPackage(aNodeIdString);
		case WORK_PLAN:
            return retrieveWorkPlan(aNodeIdString);
		case WORK_TASK:
            return retrieveWorkTask(aNodeIdString);
		default:
			return null;
		}
	}

	public void notifyDatabaseListeners() {
		// notify table listeners and row listeners
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - COMMUNITY MEMBER  ////////////////////////////////////////////////////////////////////////////////

	public Collection<CommunityMember> getCommunityMemberCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveCommunityMemberList();
	}

	public Collection<CommunityMember> getCommunityMemberCollection(FmsOrganization anOrganization) {
		return getCommunityMemberCollectionForOrganization(anOrganization.getNodeIdString());
	}

	public Collection<CommunityMember> getCommunityMemberCollectionForOrganization(String anOrganizationId) {
		return this.persistenceTechnologyDelegate.dbRetrieveCommunityMemberListForOrganization(anOrganizationId);
	}

	public Collection<CommunityMember> getCommunityMemberCollectionSorted(FmsOrganization anOrganization) {
		Collection<CommunityMember> theCommunityMemberList = getCommunityMemberCollection(anOrganization);
		Collections.sort((ArrayList<CommunityMember>)theCommunityMemberList);
		return theCommunityMemberList;
	}

	public CommunityMember getCommunityMember(String aNodeIdString) {
		if(aNodeIdString == null || aNodeIdString.equals("")) {
			return null;
		}
		return this.persistenceTechnologyDelegate.dbRetrieveCommunityMember(aNodeIdString);
	}

	public boolean newCommunityMember(CommunityMember aCommunityMember, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertCommunityMember(aCommunityMember, bAtomicTransaction) &&
				newGovernableNode(aCommunityMember);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public boolean deleteCommunityMember(CommunityMember aCommunityMember, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteCommunityMember(aCommunityMember, false) &&
				deleteGovernableNode(aCommunityMember);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public void saveCommunityMember(CommunityMember aCommunityMember, boolean bAtomicTransaction) {
		if(existsCommunityMember(aCommunityMember.getNodeIdString())) {
			updateCommunityMember(aCommunityMember, bAtomicTransaction);
		} else {
			newCommunityMember(aCommunityMember, bAtomicTransaction);
		}
	}

	public boolean updateCommunityMember(CommunityMember aCommunityMember, boolean bAtomicTransaction) {
		updateHeadlineNode(aCommunityMember);
		return this.persistenceTechnologyDelegate.dbUpdateCommunityMember(aCommunityMember, bAtomicTransaction);
	}

	public boolean existsCommunityMember(String aNodeIdString) {
		return getCommunityMember(aNodeIdString) != null;
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - COMPLETION_NODE_TRASH  ////////////////////////////////////////////////////////////////////////////////

	public Collection<CompletionNodeTrash> getCompletionNodeTrashCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveCompletionNodeTrashList();
	}

	public CompletionNodeTrash getCompletionNodeTrash(String aNodeIdString) {
		if(aNodeIdString == null || aNodeIdString.equals("")) {
			return null;
		}
		return this.persistenceTechnologyDelegate.dbRetrieveCompletionNodeTrash(aNodeIdString);
	}

	public boolean newCompletionNodeTrash(CompletionNodeTrash aCompletionNodeTrash, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertCompletionNodeTrash(aCompletionNodeTrash, bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	private boolean newCompletionNodeTrash(FmmCompletionNode aCompletionNode) {
		CompletionNodeTrash theCompletionNodeTrash = new CompletionNodeTrash(aCompletionNode);
		return newCompletionNodeTrash(theCompletionNodeTrash, false);
	}

	public boolean deleteCompletionNodeTrash(CompletionNodeTrash aCompletionNodeTrash, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteCompletionNodeTrash(aCompletionNodeTrash, false);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - PORTFOLIO  ////////////////////////////////////////////////////////////////////////////////

	public ArrayList<Portfolio> getPortfolioList(FmsOrganization anOrganization) {
		return getPortfolioList(anOrganization, null);
	}

    public ArrayList<Portfolio> getPortfolioList(FmsOrganization anOrganization, Portfolio aPortfolioException) {
        return this.persistenceTechnologyDelegate.dbListPortfolio(anOrganization, aPortfolioException);
    }

    public int countPortfolioForProjectMoveTarget(FmsOrganization anFmsOrganization, Portfolio aPortfolioException) {
        return this.persistenceTechnologyDelegate.dbCountPortfolioForProjectMoveTarget(anFmsOrganization, aPortfolioException);
    }

    public ArrayList<? extends GcgGuiable> listPortfolioForProjectMoveTarget(FmsOrganization anFmsOrganization, Portfolio aPortfolioException) {
        return this.persistenceTechnologyDelegate.dbListPortfolioForProjectMoveTarget(anFmsOrganization, aPortfolioException);
    }

    public ArrayList<? extends GcgGuiable> listPortfolioForProjectAssetMoveTarget(FmsOrganization anFmsOrganization, Project aProjectException) {
        return this.persistenceTechnologyDelegate.dbListPortfolioForProjectAssetMoveTarget(anFmsOrganization, aProjectException);
    }

    public ArrayList<? extends GcgGuiable> listPortfolioForWorkPackageMoveTarget(FmsOrganization anFmsOrganization, ProjectAsset aProjectAssetException) {
        return this.persistenceTechnologyDelegate.dbListPortfolioForWorkPackageMoveTarget(anFmsOrganization, aProjectAssetException);
    }

    public ArrayList<? extends GcgGuiable> listPortfolioForWorkTaskMoveTarget(FmsOrganization anFmsOrganization, WorkPackage aWorkPackageException) {
        return this.persistenceTechnologyDelegate.dbListPortfolioForWorkTaskMoveTarget(anFmsOrganization, aWorkPackageException);
    }

    public Portfolio createPortfolio(String aHeadline) {
        Portfolio thePortfoliPortfolio = new Portfolio(
                new NodeId(FmmNodeDefinition.PORTFOLIO),
                aHeadline,
                FmmDatabaseMediator.getActiveMediator().getFmmOwner() );
        return FmmDatabaseMediator.getActiveMediator().newPortfolio(thePortfoliPortfolio, true) ?
                thePortfoliPortfolio : null;
    }

    public boolean newPortfolio(Portfolio aPortfolio, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertPortfolio(aPortfolio, bAtomicTransaction) &&
                newCompletionNode(aPortfolio) &&
                newNodeFragTribKnQuality(aPortfolio) != null;
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public Portfolio getPortfolio(String aNodeIdString) {
        return this.persistenceTechnologyDelegate.dbRetrievePortfolio(aNodeIdString);
    }

    public void savePortfolio(Portfolio aPortfolio, boolean bAtomicTransaction) {
        if(existsPortfolio(aPortfolio.getNodeIdString())) {
            updatePortfolio(aPortfolio, bAtomicTransaction);
        } else {
            newPortfolio(aPortfolio, bAtomicTransaction);
        }
    }

    public boolean existsPortfolio(String aNodeIdString) {
        return getPortfolio(aNodeIdString) != null;
    }

    public boolean updatePortfolio(Portfolio aPortfolio, boolean bAtomicTransaction) {
        updateHeadlineNode(aPortfolio);
        return this.persistenceTechnologyDelegate.dbUpdatePortfolio(aPortfolio, bAtomicTransaction);
    }

    public boolean deletePortfolio(Portfolio aPortfolio, boolean bAtomicTransaction) {
            if(bAtomicTransaction) {
                startTransaction();
            }
            deleteCompletableNode(aPortfolio);
            boolean isSuccess = this.persistenceTechnologyDelegate.dbDeletePortfolio(aPortfolio, false);
            if(bAtomicTransaction) {
                endTransaction(isSuccess);
            }
            return isSuccess;
        }

    public boolean deleteProjectsForPortfolio(String aPortfolioId, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbDeleteProjectsForPortfolio(aPortfolioId, bAtomicTransaction);
    }

    public boolean moveAllProjectsIntoPortfolio(String aCurrentPortfolioId, String aTargetPortfolioId, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbMoveAllProjectsIntoPortfolio(aCurrentPortfolioId, aTargetPortfolioId, bAtomicTransaction);
    }

    public boolean adoptOrphanProjectIntoPortfolio(
            String aProjectId, String aPortfolioId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbAdoptOrphanProjectIntoPortfolio(
                aProjectId,
                aPortfolioId,
                bSequenceAtEnd,
                bAtomicTransaction );
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - PROJECT  ////////////////////////////////////////////////////////////////////////////////

    public ArrayList<Project> getProjectList(Portfolio aPortfolio) {
        return getProjectList(aPortfolio, null);
    }

    public ArrayList<Project> getProjectList(Portfolio aPortfolio, Project aProjectException) {
        return this.persistenceTechnologyDelegate.dbListProject(aPortfolio, aProjectException);
    }

    public int countProjectsForProjectAssetMoveTarget(Portfolio aPortfolio, Project aProjectException) {
        return this.persistenceTechnologyDelegate.dbCountProjectsForProjectAssetMoveTarget(aPortfolio, aProjectException);
    }

    public ArrayList<Project> listProjectsForProjectAssetMoveTarget(Portfolio aPortfolio, Project aProjectException) {
        return this.persistenceTechnologyDelegate.dbListProjectsForProjectAssetMoveTarget(aPortfolio, aProjectException);
    }

    public ArrayList<Project> listProjectsForWorkPackageMoveTarget(Portfolio aPortfolio, ProjectAsset aProjectAssetException) {
        return this.persistenceTechnologyDelegate.dbListProjectsForWorkPackageMoveTarget(aPortfolio, aProjectAssetException);
    }

    public ArrayList<Project> listProjectOrphansFromPortfolio() {
        return this.persistenceTechnologyDelegate.dbListProjectOrphansFromPortfolio();
    }

    public ArrayList<Project> listProjectsForWorkTaskMoveTarget(Portfolio aPortfolio, WorkPackage aWorkPackageException) {
        return this.persistenceTechnologyDelegate.dbListProjectsForWorkTaskMoveTarget(aPortfolio, aWorkPackageException);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - WORK ASSET  ///////////////////////////////////////////////////////////////////////////////

    public ArrayList<WorkAsset> listWorkAssets() {
        return this.persistenceTechnologyDelegate.dbListWorkAssets();
    }

    public ArrayList<WorkAsset> listWorkAssets(Project aProject) {
        return listWorkAssets(aProject, null);
    }

    public ArrayList<WorkAsset> listWorkAssets(Project aProject, WorkAsset aWorkAssetException) {
        return listWorkAssetsForProject(aProject.getNodeIdString(), aWorkAssetException == null ? null : aWorkAssetException.getNodeIdString());
    }

    public ArrayList<WorkAsset> listWorkAssetsForProject(String aProjectId) {
        return listWorkAssetsForProject(aProjectId, null);
    }

    public ArrayList<WorkAsset> listWorkAssetsForProject(String aProjectId, String aWorkAssetExceptionId) {
        return this.persistenceTechnologyDelegate.dbListWorkAssetsForProject(aProjectId, aWorkAssetExceptionId);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - PROJECT ASSET  ///////////////////////////////////////////////////////////////////////////////

    public ArrayList<ProjectAsset> listProjectAssets() {
        return this.persistenceTechnologyDelegate.dbListProjectAssets();
    }

    public ArrayList<ProjectAsset> listProjectAssets(Project aProject) {
        return listProjectAssets(aProject, null);
    }

    public ArrayList<ProjectAsset> listProjectAssets(Project aProject, ProjectAsset aProjectAssetException) {
        return this.persistenceTechnologyDelegate.dbListProjectAssets(aProject, aProjectAssetException);
    }

    public ArrayList<ProjectAsset> listProjectAssetsForProject(String aProjectId) {
        return listProjectAssetsForProject(aProjectId, null);
    }

    public ArrayList<ProjectAsset> listProjectAssetsForProject(String aProjectId, String aProjectAssetExceptionId) {
        return this.persistenceTechnologyDelegate.dbListProjectAssetsForProject(aProjectId, aProjectAssetExceptionId);
    }

    public ArrayList<ProjectAsset> listProjectAsset(StrategicMilestone aStrategicMilestone) {
        return listProjectAsset(aStrategicMilestone, null);
    }

    public ArrayList<ProjectAsset> listProjectAsset(StrategicMilestone aStrategicMilestone, ProjectAsset aProjectAssetException) {
        return this.persistenceTechnologyDelegate.dbListProjectAssets(aStrategicMilestone, aProjectAssetException);
    }

    public ArrayList<ProjectAsset> listProjectAssetForStrategicMilestone(String aStrategicMilestoneId) {
        return this.persistenceTechnologyDelegate.dbListProjectAssetsForStrategicMilestone(aStrategicMilestoneId, null);
    }

    public ArrayList<ProjectAsset> listProjectAssetForWorkPackageMoveTarget(StrategicMilestone aStrategicMilestone, ProjectAsset aProjectAssetException) {
        return this.persistenceTechnologyDelegate.dbListProjectAssetInStrategicPlanningForWorkPackageMoveTarget(
                aStrategicMilestone.getNodeIdString(), aProjectAssetException.getNodeIdString());
    }

    public ArrayList<ProjectAsset> listProjectAssetForWorkTaskMoveTarget(String aProjectId, String aWorkPackageException) {
        return this.persistenceTechnologyDelegate.dbListProjectAssetInWorkBreakdownForWorkTaskMoveTarget(
                aProjectId, aWorkPackageException);
    }

    public ProjectAsset getProjectAsset(String aNodeIdString) {
        return this.persistenceTechnologyDelegate.dbRetrieveProjectAsset(aNodeIdString);
    }

    private boolean newProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertProjectAsset(aProjectAsset, bAtomicTransaction) &&
                newCompletionNode(aProjectAsset);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    private ProjectAsset newProjectAssetForProject(
            String aHeadline,
            FmmHeadlineNode aParentNode,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd ) {
        startTransaction();
        ProjectAsset theNewProjectAsset = new ProjectAsset();
        theNewProjectAsset.setHeadline(aHeadline);
        theNewProjectAsset.setProjectId(aParentNode.getNodeIdString());
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                FmmNodeDefinition.PROJECT_ASSET,
                ProjectAssetMetaData.column_PROJECT_ID,
                aParentNode,
                aPeerNode,
                bSequenceAtEnd );
        theNewProjectAsset.setSequence(theNewSequenceNumber);
        boolean isSuccess = newProjectAsset(theNewProjectAsset, true);
        isSuccess = isSuccess && newNodeFragTribKnQuality(theNewProjectAsset) != null;
        endTransaction(isSuccess);
        return theNewProjectAsset;
    }

    public boolean updateProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction) {
        updateHeadlineNode(aProjectAsset);
        return this.persistenceTechnologyDelegate.dbUpdateProjectAsset(aProjectAsset, bAtomicTransaction);
    }

    public boolean moveAllProjectAssetsIntoStrategicMilestone(
            String aSourceStrateticMilestoneId,
            String aDestinationStrategicMilestoneId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbMoveAllProjectAssetsIntoStrategicMilestone(
                aSourceStrateticMilestoneId,
                aDestinationStrategicMilestoneId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

    public boolean moveSingleProjectAssetIntoStrategicMilestone(
            String aProjectAssetId,
            String anOriginalStrategicMilestonetId,
            String aDestinationStrategicMilestoneId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbMoveSingleProjectAssetIntoStrategicMilestone(
                aProjectAssetId,
                anOriginalStrategicMilestonetId,
                aDestinationStrategicMilestoneId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

    public boolean moveAllProjectAssetsIntoProject(
            String aSourceStrateticMilestoneId,
            String aDestinationProjectId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbMoveAllProjectAssetsIntoProject(
                aSourceStrateticMilestoneId,
                aDestinationProjectId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

    public boolean moveSingleProjectAssetIntoProject(
            String aProjectAssetId,
            String anOriginalProjectId,
            String aDestinationProjectId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbMoveSingleProjectAssetIntoProject(
                aProjectAssetId,
                anOriginalProjectId,
                aDestinationProjectId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

    public boolean orphanAllProjectAssetsFromStrategicMilestone(String aStrategicMilestoneId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanAllProjectAssetsFromStrategicMilestone(aStrategicMilestoneId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean orphanAllProjectAssetsFromProject(String aProjectId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanAllProjectAssetsFromProject(aProjectId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean orphanSingleProjectAssetFromStrategicMilestone(String aProjectAssetId, String aStrategicMilestoneId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanSingleProjectAssetFromStrategicMilestone(aProjectAssetId, aStrategicMilestoneId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean orphanSingleProjectAssetFromProject(String aProjectAssetId, String aProjectId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanSingleProjectAssetFromProject(aProjectAssetId, aProjectId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public ArrayList<ProjectAsset> listProjectAssetOrphansFromProject() {
        return this.persistenceTechnologyDelegate.dbListProjectAssetOrphansFromProject();
    }

    public ArrayList<StrategicAsset> listStrategicAssetOrphansFromProject() {
        return this.persistenceTechnologyDelegate.dbListStrategicAssetOrphansFromProject();
    }

//    public boolean adoptOrphanProjectAssetIntoProject(ProjectAsset aProjectAsset, Project aProject, WorkAsset aPeerNode, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
//
//        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
//                FmmNodeDefinition.PROJECT_ASSET,
//                ProjectAssetMetaData.column_PROJECT_ID,
//                aProject,
//                aPeerNode,  /// could be ProjectAsset or StrategicAsset
//                bSequenceAtEnd );
//        aProjectAsset.setSequence(theNewSequenceNumber);
//
//        return this.persistenceTechnologyDelegate.updateSimpleIdTable(aProjectAsset, ProjectAssetDaoSqLite.getInstance(), bAtomicTransaction);
//    }

    public boolean adoptPrimaryOrphanIntoParent(FmmCompletionNode anOrphanNode, FmmCompletionNode aParentNode, FmmCompletionNode aPeerNode, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                anOrphanNode.getFmmNodeDefinition(),
                anOrphanNode.getFmmNodeDefinition().getPrimaryParentIdColumnName(),
                aParentNode,
                aPeerNode,
                bSequenceAtEnd);
        anOrphanNode.setPrimaryParentId(aParentNode.getNodeIdString());
        anOrphanNode.setSequence(theNewSequenceNumber);
        return this.persistenceTechnologyDelegate.updateSimpleIdTable(anOrphanNode, bAtomicTransaction);
    }

    public boolean adoptPrimaryOrphanIntoParentAlphaSort(FmmCompletionNode anOrphanNode, FmmCompletionNode aParentNode, boolean bAtomicTransaction) {
        anOrphanNode.setPrimaryParentId(aParentNode.getNodeIdString());
        return this.persistenceTechnologyDelegate.updateSimpleIdTable(anOrphanNode, bAtomicTransaction);
    }

    public boolean adoptPrimaryLinkOrphanIntoParent(FmmCompletionNode anOrphanNode, FmmCompletionNode aParentNode, FmmCompletionNode aPeerNode, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        boolean theResult = newStrategicCommitment(aParentNode, aPeerNode, bSequenceAtEnd, (StrategicAsset) anOrphanNode);
        ((StrategicAsset) anOrphanNode).setStrategic(true);
        return this.persistenceTechnologyDelegate.updateSimpleIdTable(anOrphanNode, bAtomicTransaction);
    }

    public boolean adoptProjectAssetIntoStrategicMilestone(FmmCompletionNode anOrphanNode, FmmCompletionNode aParentNode, FmmCompletionNode aPeerNode, boolean bSequenceAtEnd) {
        boolean theResult = newStrategicCommitment(aParentNode, aPeerNode, bSequenceAtEnd, (WorkAsset) anOrphanNode);
        ((WorkAsset) anOrphanNode).setStrategic(true);
        return theResult && this.persistenceTechnologyDelegate.updateSimpleIdTable(anOrphanNode, true);
    }

    public boolean adoptOrphanProjectAssetIntoStrategicMilestone(
            String aProjectAssetId,
            String aStrategicMilestoneId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction ) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                FmmNodeDefinition.STRATEGIC_COMMITMENT,
                StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID,
                aStrategicMilestoneId,
                bSequenceAtEnd );
        StrategicCommitment theNewStrategicCommitment = new StrategicCommitment(
                aStrategicMilestoneId, aProjectAssetId );
        theNewStrategicCommitment.setSequence(theNewSequenceNumber);
        theNewStrategicCommitment.setCompletionCommitmentType(CompletionCommitmentType.NONE);
        boolean isSuccess = newStrategicCommitment(theNewStrategicCommitment, false);
        // isSuccess += UPDATE TribKn
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean deleteProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteProjectAsset(aProjectAsset, bAtomicTransaction) &&
                deleteCompletableNode(aProjectAsset);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public void saveProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction) {
        if(existsProjectAsset(aProjectAsset.getNodeIdString())) {
            updateProjectAsset(aProjectAsset, bAtomicTransaction);
        } else {
            newProjectAsset(aProjectAsset, bAtomicTransaction);
        }
    }

    public boolean existsProjectAsset(String aNodeIdString) {
        return getProjectAsset(aNodeIdString) != null;
    }

    public int getMoveTargetWorkPackageCount(ProjectAsset aProjectAsset, WorkPackage aWorkPackageException) {
        return this.persistenceTechnologyDelegate.dbGetMoveTargetWorkPackageCount(aProjectAsset, aWorkPackageException);
    }







    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - STRATEGIC ASSET  ///////////////////////////////////////////////////////////////////////////////

    public ArrayList<StrategicAsset> listStrategicAssets(Project aProject) {
        return this.persistenceTechnologyDelegate.dbListStrategicAssets(aProject);
    }

    public ArrayList<StrategicAsset> listStrategicAssets(StrategicMilestone aStrategicMilestone) {
        return this.persistenceTechnologyDelegate.dbListStrategicAssets(aStrategicMilestone);
    }

    public ArrayList<StrategicAsset> listStrategicAssets(StrategicMilestone aStrategicMilestone, StrategicAsset aStrategicAssetException) {
        return this.persistenceTechnologyDelegate.dbListStrategicAssets(aStrategicMilestone, aStrategicAssetException);
    }

    public ArrayList<StrategicAsset> listStrategicAssetsForStrategicMilestone(String aStrategicMilestoneId) {
        return listStrategicAssetsForStrategicMilestone(aStrategicMilestoneId, null);
    }

    public ArrayList<StrategicAsset> listStrategicAssetsForStrategicMilestone(String aStrategicMilestoneId, String aStrategicAssetExceptionId) {
        return this.persistenceTechnologyDelegate.dbListStrategicAssetsForStrategicMilestone(aStrategicMilestoneId, aStrategicAssetExceptionId);
    }

    private boolean newStrategicAsset(StrategicAsset aStrategicAsset, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.insertSimpleIdTable(aStrategicAsset, StrategicAssetDaoSqLite.getInstance(), bAtomicTransaction) &&
                newCompletionNode(aStrategicAsset);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    private StrategicAsset newStrategicAssetForStrategicMilestone(
            String aHeadline,
            FmmHeadlineNode aParentNode,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd) {
        startTransaction();
        StrategicAsset theNewStrategicAsset = new StrategicAsset();
        theNewStrategicAsset.setHeadline(aHeadline);
        boolean isSuccess = newStrategicAsset(theNewStrategicAsset, true);
        isSuccess &= newStrategicCommitment(aParentNode, aPeerNode, bSequenceAtEnd, theNewStrategicAsset);
        isSuccess &= newNodeFragTribKnQuality(theNewStrategicAsset) != null;
        endTransaction(isSuccess);
        return theNewStrategicAsset;
    }

    private boolean newStrategicCommitment(FmmHeadlineNode aParentNode, FmmHeadlineNode aPeerNode, boolean bSequenceAtEnd, WorkAsset theNewStrategicAsset) {
        StrategicCommitment theNewStrategicCommitment = new StrategicCommitment(
                aParentNode.getNodeIdString(), theNewStrategicAsset.getNodeIdString() );
        int theNewSequenceNumber = initializeNewSequenceNumberForLinkTable(
                FmmNodeDefinition.STRATEGIC_COMMITMENT,
                StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID,
                aParentNode.getNodeIdString(),
                StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID,
                aPeerNode == null ? null : aPeerNode.getNodeIdString(),
                bSequenceAtEnd);
        theNewStrategicCommitment.setSequence(theNewSequenceNumber);
        theNewStrategicCommitment.setCompletionCommitmentType(CompletionCommitmentType.NONE);
        return newStrategicCommitment(theNewStrategicCommitment, false);
    }

    public boolean updateStrategicAsset(StrategicAsset aStrategicAsset, boolean bAtomicTransaction) {
        updateHeadlineNode(aStrategicAsset);
        return this.persistenceTechnologyDelegate.updateSimpleIdTable(aStrategicAsset, StrategicAssetDaoSqLite.getInstance(), bAtomicTransaction);
    }

    public boolean moveAllStrategicAssetsIntoStrategicMilestone(
            String aSourceStrateticMilestoneId,
            String aDestinationStrategicMilestoneId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbMoveAllStrategicAssetsIntoStrategicMilestone(
                aSourceStrateticMilestoneId,
                aDestinationStrategicMilestoneId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

    public boolean moveSingleStrategicAssetIntoStrategicMilestone(
            String aStrategicAssetId,
            String anOriginalStrategicMilestonetId,
            String aDestinationStrategicMilestoneId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbMoveSingleStrategicAssetIntoStrategicMilestone(
                aStrategicAssetId,
                anOriginalStrategicMilestonetId,
                aDestinationStrategicMilestoneId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

    public ArrayList<StrategicAsset> listStrategicAssetsWithNoProject() {
        return this.persistenceTechnologyDelegate.dbListStrategicAssetsWithNoProject();
    }

    public boolean promoteProjectAssetToStrategicAsset(
            String aProjectAssetId,
            String aStrategicMilestoneId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction ) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                FmmNodeDefinition.STRATEGIC_COMMITMENT,
                StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID,
                aStrategicMilestoneId,
                bSequenceAtEnd );
        this.persistenceTechnologyDelegate.dbUpdateProjectAssetIsStrategic(aProjectAssetId, true);
        StrategicCommitment theNewStrategicCommitment = new StrategicCommitment(
                aStrategicMilestoneId, aProjectAssetId );
        theNewStrategicCommitment.setSequence(theNewSequenceNumber);
        theNewStrategicCommitment.setCompletionCommitmentType(CompletionCommitmentType.NONE);
        boolean isSuccess = newStrategicCommitment(theNewStrategicCommitment, false);
        // isSuccess += UPDATE TribKn
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public StrategicAsset retrieveStrategicAsset(String aNodeIdString) {
        return (StrategicAsset) this.persistenceTechnologyDelegate.retrieveFmmNodeFromSimpleIdTable(aNodeIdString, StrategicAssetDaoSqLite.getInstance());
    }

    // TODO  !!!
    public boolean demoteProjectAssetToStrategicAsset(
            String aProjectAssetId,
            String aStrategicMilestoneId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction ) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteStrategicCommitment(aStrategicMilestoneId, aProjectAssetId, bAtomicTransaction);
        isSuccess &= this.persistenceTechnologyDelegate.dbUpdateProjectAssetIsStrategic(aProjectAssetId, false);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean deleteStrategicAsset(StrategicAsset aStrategicAsset, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.deleteRowFromSimpleIdTable(aStrategicAsset.getNodeIdString(), FmmNodeDefinition.PROJECT_ASSET, bAtomicTransaction) &&
                deleteCompletableNode(aStrategicAsset);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public void saveStrategicAsset(StrategicAsset aStrategicAsset, boolean bAtomicTransaction) {
        if(existsStrategicAsset(aStrategicAsset.getNodeIdString())) {
            updateStrategicAsset(aStrategicAsset, bAtomicTransaction);
        } else {
            newStrategicAsset(aStrategicAsset, bAtomicTransaction);
        }
    }

    public boolean existsStrategicAsset(String aNodeIdString) {
        return retrieveStrategicAsset(aNodeIdString) != null;
    }







    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FISCAL YEAR  ////////////////////////////////////////////////////////////////////////////////

    public ArrayList<FiscalYear> getFiscalYearList(FmsOrganization anOrganization) {
        return getFiscalYearList(anOrganization, null);
    }

	public ArrayList<FiscalYear> getFiscalYearList(FmsOrganization anOrganization, FiscalYear aFiscalYearException) {
		return this.persistenceTechnologyDelegate.dbListFiscalYear(anOrganization, aFiscalYearException);
	}

	public int countFiscalYearForStrategicMilestoneMoveTarget(FmsOrganization anFmsOrganization, FiscalYear aFiscalYearTargetException) {
		return this.persistenceTechnologyDelegate.dbCountFiscalYearForStrategicMilestoneMoveTarget(anFmsOrganization, aFiscalYearTargetException);
	}

	public ArrayList<FiscalYear> listFiscalYearForStrategicMilestoneMoveTarget(FmsOrganization anFmsOrganization, FiscalYear aFiscalYearTargetException) {
		return this.persistenceTechnologyDelegate.dbListFiscalYearForStrategicMilestoneMoveTarget(anFmsOrganization, aFiscalYearTargetException);
	}

	public int countFiscalYearForProjectAssetMoveTarget(FmsOrganization anFmsOrganization, StrategicMilestone aStrategicMilestonException) {
		return this.persistenceTechnologyDelegate.dbCountFiscalYearForProjectAssetMoveTarget(anFmsOrganization, aStrategicMilestonException);
	}

	public ArrayList<FiscalYear> listFiscalYearForProjectAssetMoveTarget(FmsOrganization anFmsOrganization, StrategicMilestone aStrategicMilestonException) {
		return this.persistenceTechnologyDelegate.dbListFiscalYearForProjectAssetMoveTarget(anFmsOrganization, aStrategicMilestonException);
	}

	public int countFiscalYearForWorkPackageMoveTarget(FmsOrganization anFmsOrganization, ProjectAsset aProjectAssetException) {
		return this.persistenceTechnologyDelegate.dbCountFiscalYearForWorkPackageMoveTarget(anFmsOrganization, aProjectAssetException);
	}

	public ArrayList<FiscalYear> listFiscalYearForWorkPackageMoveTarget(FmsOrganization anFmsOrganization, ProjectAsset aProjectAssetException) {
		return this.persistenceTechnologyDelegate.dbListFiscalYearForWorkPackageMoveTarget(anFmsOrganization, aProjectAssetException);
	}

	public int countFiscalYearForFlywheelCadenceMoveTarget(FmsOrganization anFmsOrganization, FiscalYear aFiscalYearTargetException) {
		return this.persistenceTechnologyDelegate.dbCountFiscalYearForFlywheelCadenceMoveTarget(anFmsOrganization, aFiscalYearTargetException);
	}

	public ArrayList<FiscalYear> listFiscalYearForFlywheelCadenceMoveTarget(FmsOrganization anFmsOrganization, FiscalYear aFiscalYearTargetException) {
		return this.persistenceTechnologyDelegate.dbListFiscalYearForFlywheelCadenceMoveTarget(anFmsOrganization, aFiscalYearTargetException);
	}

	public FiscalYear getFiscalYear(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveFiscalYear(aNodeIdString);
	}
	
	public FiscalYear createFiscalYear(String aYearString) {
		FiscalYear theFiscalYear = new FiscalYear(
				new NodeId(FmmNodeDefinition.FISCAL_YEAR),
				FmmDatabaseMediator.getActiveMediator().getFmmOwner(),
				aYearString);
		return FmmDatabaseMediator.getActiveMediator().newFiscalYear(theFiscalYear, true) ?
				theFiscalYear : null;
	}

	public boolean newFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertFiscalYear(aFiscalYear, bAtomicTransaction) &&
				newCompletionNode(aFiscalYear) &&
				newNodeFragTribKnQuality(aFiscalYear) != null;
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public boolean deleteFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		deleteCompletableNode(aFiscalYear);
        deleteAllFiscalYearHolidayBreaks(aFiscalYear, bAtomicTransaction);
        deleteAllFlywheelCadences(aFiscalYear, bAtomicTransaction);
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteFiscalYear(aFiscalYear, false);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public void saveFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
		if(existsFiscalYear(aFiscalYear.getNodeIdString())) {
			updateFiscalYear(aFiscalYear, bAtomicTransaction);
		} else {
			newFiscalYear(aFiscalYear, bAtomicTransaction);
		}
	}

	public boolean updateFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
		updateHeadlineNode(aFiscalYear);
		return this.persistenceTechnologyDelegate.dbUpdateFiscalYear(aFiscalYear, bAtomicTransaction);
	}

	public boolean existsFiscalYear(String aNodeIdString) {
		return getFiscalYear(aNodeIdString) != null;
	}


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FLYWHEEL CADENCE  ///////////////////////////////////////////////////////////////////

    public ArrayList<FlywheelCadence> getFlywheelCadenceList(FiscalYear aFiscalYear) {
        return this.persistenceTechnologyDelegate.dbGetFlywheelCadenceList(aFiscalYear);
    }

    public ArrayList<FlywheelCadence> getFlywheelCadenceListForFiscalYear(String aFiscalYearId) {
        return this.persistenceTechnologyDelegate.dbGetFlywheelCadenceListForFiscalYear(aFiscalYearId);
    }

    public FlywheelCadence retrieveFlywheelCadence(String aNodeIdString) {
        return this.persistenceTechnologyDelegate.dbRetrieveFlywheelCadence(aNodeIdString);
    }

    public boolean insertFlywheelCadence(FlywheelCadence aFlywheelCadence, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbInsertFlywheelCadence(aFlywheelCadence, bAtomicTransaction);
    }

    public boolean insertFlywheelCadenceList(ArrayList<FlywheelCadence> aFlywheelCadenceList, boolean bAtomicTransaction) {
        boolean isSuccess = false;
        if(bAtomicTransaction) {
            startTransaction();
        }
        for(FlywheelCadence theFlywheelCadence : aFlywheelCadenceList) {
            isSuccess = insertFlywheelCadence(theFlywheelCadence, false);
            isSuccess &= insertWorkPlanList(theFlywheelCadence.getWorkPlanList(), false);
            if(! isSuccess) {
                break;
            }
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean updateFlywheelCadence(FlywheelCadence aFlywheelCadence, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbUpdateFlywheelCadence(aFlywheelCadence, bAtomicTransaction);
    }

    public void saveFlywheelCadence(FlywheelCadence aFlywheelCadence, boolean bAtomicTransaction) {
        if(existsFlywheelCadence(aFlywheelCadence.getNodeIdString())) {
            updateFlywheelCadence(aFlywheelCadence, bAtomicTransaction);
        } else {
            insertFlywheelCadence(aFlywheelCadence, bAtomicTransaction);
        }
    }

    public boolean existsFlywheelCadence(String aNodeIdString) {
        return retrieveFlywheelCadence(aNodeIdString) != null;
    }

    public boolean deleteFlywheelCadence(FlywheelCadence aFlywheelCadence, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbDeleteFlywheelCadence(aFlywheelCadence, bAtomicTransaction);
    }

    public boolean deleteFlywheelCadenceForFiscalYear(String aFiscalYearId, boolean bAtomicTransaction) {
        deleteAllWorkPlansForFiscalYear(aFiscalYearId, bAtomicTransaction);
        return this.persistenceTechnologyDelegate.dbDeleteAllFlywheelCadencesForFiscalYear(aFiscalYearId, bAtomicTransaction);
    }

    public boolean deleteAllFlywheelCadences(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
        deleteAllWorkPlans(aFiscalYear, bAtomicTransaction);
        return this.persistenceTechnologyDelegate.dbDeleteAllFlywheelCadences(aFiscalYear, bAtomicTransaction);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - WORK PLAN  ///////////////////////////////////////////////////////////////////////////////////

    public ArrayList<WorkPlan> getWorkPlanList(FlywheelCadence aFlywheelCadence) {
        return this.persistenceTechnologyDelegate.dbGetWorkPlanList(aFlywheelCadence);
    }

    public ArrayList<WorkPlan> getWorkPlanListForFlywheelCadence(String aFlywheelCadenceId) {
        return this.persistenceTechnologyDelegate.dbGetWorkPlanListForFlywheelCadence(aFlywheelCadenceId);
    }

    public WorkPlan retrieveWorkPlan(String aNodeIdString) {
        return this.persistenceTechnologyDelegate.dbRetrieveWorkPlan(aNodeIdString);
    }

    public boolean insertWorkPlan(WorkPlan aWorkPlan, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbInsertWorkPlan(aWorkPlan, bAtomicTransaction);
    }

    public boolean insertWorkPlanList(ArrayList<WorkPlan> aWorkPlanList, boolean bAtomicTransaction) {
        boolean isSuccess = true;
        if(bAtomicTransaction) {
            startTransaction();
        }
        for(WorkPlan theWorkPlan : aWorkPlanList) {
            insertWorkPlan(theWorkPlan, bAtomicTransaction);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean updateWorkPlan(WorkPlan aWorkPlan, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbUpdateWorkPlan(aWorkPlan, bAtomicTransaction);
    }

    public void saveWorkPlan(WorkPlan aWorkPlan, boolean bAtomicTransaction) {
        if(existsWorkPlan(aWorkPlan.getNodeIdString())) {
            updateWorkPlan(aWorkPlan, bAtomicTransaction);
        } else {
            insertWorkPlan(aWorkPlan, bAtomicTransaction);
        }
    }

    public boolean existsWorkPlan(String aNodeIdString) {
        return retrieveWorkPlan(aNodeIdString) != null;
    }

    public boolean deleteWorkPlan(WorkPlan aWorkPlan, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbDeleteWorkPlan(aWorkPlan, bAtomicTransaction);
    }

    public boolean deleteAllWorkPlans(FlywheelCadence aFlywheelCadence, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbDeleteAllWorkPlans(aFlywheelCadence, bAtomicTransaction);
    }

    public boolean deleteAllWorkPlans(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbDeleteAllWorkPlans(aFiscalYear, bAtomicTransaction);
    }

    public boolean deleteAllWorkPlansForFiscalYear(String aFiscalYearId, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbDeleteAllWorkPlansForFiscalYear(aFiscalYearId, bAtomicTransaction);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FISCAL YEAR HOLIDAY BREAK  ///////////////////////////////////////////////////////////////////

    public ArrayList<FiscalYearHolidayBreak> getFiscalYearHolidayBreakList(FiscalYear aFiscalYear) {
        return this.persistenceTechnologyDelegate.dbGetFiscalYearHolidayBreakList(aFiscalYear);
    }

    public FiscalYearHolidayBreak retrieveFiscalYearHolidayBreak(String aNodeIdString) {
        return this.persistenceTechnologyDelegate.dbRetrieveFiscalYearHolidayBreak(aNodeIdString);
    }

    public boolean insertFiscalYearHolidayBreak(FiscalYearHolidayBreak aFiscalYearHolidayBreak, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbInsertFiscalYearHolidayBreak(aFiscalYearHolidayBreak, bAtomicTransaction);
    }

    public boolean insertFiscalYearHolidayBreakList(ArrayList<FiscalYearHolidayBreak> aFiscalYearHolidayBreakList, boolean bAtomicTransaction) {
        boolean isSuccess = true;
        if(bAtomicTransaction) {
            startTransaction();
        }
        for(FiscalYearHolidayBreak theHolidayBreak : aFiscalYearHolidayBreakList) {
            insertFiscalYearHolidayBreak(theHolidayBreak, bAtomicTransaction);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean updateFiscalYearHolidayBreak(FiscalYearHolidayBreak aFiscalYearHolidayBreak, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbUpdateFiscalYearHolidayBreak(aFiscalYearHolidayBreak, bAtomicTransaction);
    }

    public boolean deleteFiscalYearHolidayBreak(FiscalYearHolidayBreak aFiscalYearHolidayBreak, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbDeleteFiscalYearHolidayBreak(aFiscalYearHolidayBreak, bAtomicTransaction);
    }

    public boolean deleteAllFiscalYearHolidayBreaks(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbDeleteAllFiscalYearHolidayBreaks(aFiscalYear, bAtomicTransaction);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FLYWHEEL TEAM  ///////////////////////////////////////////////////////////////////////////////

	public Collection<FlywheelTeam> getFlywheelTeamCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveFlywheelTeamList();
	}

	public ArrayList<FlywheelTeam> getFlywheelTeamList(String anOrganizationNodeId) {
		return this.persistenceTechnologyDelegate.dbRetrieveFlywheelTeamList(anOrganizationNodeId);
	}

	public Collection<FlywheelTeam> getFlywheelTeamCollection(FmsOrganization anOrganization) {
		return getFlywheelTeamList(anOrganization.getNodeIdString());
	}

	public FlywheelTeam getFlywheelTeam(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveFlywheelTeam(aNodeIdString);
	}

	public boolean newFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertFlywheelTeam(aFlywheelTeam, bAtomicTransaction) &&
				newGovernableNode(aFlywheelTeam);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public boolean updateFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction) {
		updateHeadlineNode(aFlywheelTeam);
		return this.persistenceTechnologyDelegate.dbUpdateFlywheelTeam(aFlywheelTeam, bAtomicTransaction);
	}

	public boolean deleteFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteFlywheelTeam(aFlywheelTeam, false) &&
				deleteGovernableNode(aFlywheelTeam);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FRAG LOCK  ////////////////////////////////////////////////////////////////////////

	public Collection<FragLock> getFragLockCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveFragLockList();
	}

	public FragLock getFragLockForParent(String aParentId) {
		return this.persistenceTechnologyDelegate.dbRetrieveFragLockForParent(aParentId);
	}

	public FragLock getFragLockForParentOrCreate(String aParentId, String aGrandparentId) {
		FragLock theFragLockForParent = getFragLockForParent(aParentId);
		if(theFragLockForParent == null) {
			theFragLockForParent = new FragLock(aParentId, aGrandparentId);
			newFragLock(theFragLockForParent, true);
		}
		return theFragLockForParent;
	}

	public FragLock getFragLockForGrandparent(String aGrandparentId) {
		return this.persistenceTechnologyDelegate.dbRetrieveFragLockForGrandparent(aGrandparentId);
	}

	public FragLock getFragLock(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveFragLock(aNodeIdString);
	}

	public void newFragLock(FragLock aFragLock, boolean bAtomicTransaction) {
		this.persistenceTechnologyDelegate.dbInsertFragLock(aFragLock, bAtomicTransaction);
	}

	public boolean deleteFragLock(FragLock aFragLock, boolean bAtomicTransaction) {
		return this.persistenceTechnologyDelegate.dbDeleteFragLock(aFragLock, bAtomicTransaction);
	}	


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG AUDIT BLOCK  ////////////////////////////////////////////////////////////////////////

	public Collection<NodeFragAuditBlock> getNodeFragAuditBlockCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragAuditBlockList();
	}

	public NodeFragAuditBlock getNodeFragAuditBlockForParent(String aParentId) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragAuditBlockForParent(aParentId);
	}

	public NodeFragAuditBlock getNodeFragAuditBlockForParentOrCreate(String aParentId) {
		NodeFragAuditBlock theNodeFragAuditBlock = getNodeFragAuditBlockForParent(aParentId);
		if(theNodeFragAuditBlock == null) {
			String theHeadline = "";
			if(FmmNodeDefinition.isHeadlineSearchNode(aParentId)) {
				getHeadlineNode(aParentId);
			} 
			theNodeFragAuditBlock = new NodeFragAuditBlock(aParentId, theHeadline, GcgDateHelper.getCurrentDateTime());
			newNodeFragAuditBlock(theNodeFragAuditBlock, true);
		}
		return theNodeFragAuditBlock;
	}

	public NodeFragAuditBlock getNodeFragAuditBlock(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragAuditBlock(aNodeIdString);
	}

	public boolean newNodeFragAuditBlock(NodeFragAuditBlock aNodeFragAuditBlock, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertNodeFragAuditBlock(aNodeFragAuditBlock, bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	private NodeFragAuditBlock newNodeFragAuditBlock(FmmHeadlineNode aHeadlineNode) {
		NodeFragAuditBlock theNodeFragAuditBlock = new NodeFragAuditBlock(
				aHeadlineNode.getNodeIdString(),
				aHeadlineNode.getHeadline(),
				GcgDateHelper.getCurrentDateTime() );
		return newNodeFragAuditBlock(theNodeFragAuditBlock, false) ? theNodeFragAuditBlock : null;
	}

	private NodeFragAuditBlock updateNodeFragAuditBlock(FmmHeadlineNode aHeadlineNode) {
		NodeFragAuditBlock theNodeFragAuditBlock = getNodeFragAuditBlockForParentOrCreate(aHeadlineNode.getNodeIdString());
		theNodeFragAuditBlock.setSearchableHeadline(aHeadlineNode.getHeadline());
		theNodeFragAuditBlock.setRowTimestamp(aHeadlineNode.getRowTimestamp());
		theNodeFragAuditBlock.setLastUpdatedBy(FlywheelCommunityAuthentication.getInstance().getCommunityMember());
		this.persistenceTechnologyDelegate.dbUpdateNodeFragAuditBlock(theNodeFragAuditBlock, false);
		return theNodeFragAuditBlock;
	}

	private boolean deleteNodeFragAuditBlockForParent(FmmHeadlineNode aHeadlineNode, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteNodeFragAuditBlockForParent(
				aHeadlineNode.getNodeIdString(), bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG COMPLETION  ////////////////////////////////////////////////////////////////////////

	public Collection<NodeFragCompletion> getNodeFragCompletionCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragCompletionList();
	}

	public NodeFragCompletion getNodeFragCompletionForParent(String aParentId) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragCompletionForParent(aParentId);
	}

	public NodeFragCompletion getNodeFragCompletionForParentOrCreate(String aParentId) {
		NodeFragCompletion theNodeFragCompletion = getNodeFragCompletionForParent(aParentId);
		if(theNodeFragCompletion == null) {
			theNodeFragCompletion = new NodeFragCompletion(aParentId);
			newNodeFragCompletion(theNodeFragCompletion, true);
		}
		return theNodeFragCompletion;
	}

	public NodeFragCompletion getNodeFragCompletion(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragCompletion(aNodeIdString);
	}

	public boolean newNodeFragCompletion(NodeFragCompletion aNodeFragCompletion, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertNodeFragCompletion(aNodeFragCompletion, bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	private NodeFragCompletion newNodeFragCompletion(FmmGovernableNode aGovernableNode) {
		NodeFragCompletion theNodeFragCompletion = new NodeFragCompletion(
				aGovernableNode.getNodeIdString() );
		return newNodeFragCompletion(theNodeFragCompletion, false) ? theNodeFragCompletion : null;
	}

	private boolean deleteNodeFragCompletionForParent(FmmCompletionNode aCompletionNode, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteNodeFragCompletionForParent(
				aCompletionNode.getNodeIdString(), bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG FSE DOCUMENT  //////////////////////////////////////////////////////////////////////

	public Collection<NodeFragFseDocument> getFseDocumentCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragFseDocumentList();
	}

	public NodeFragFseDocument getNodeFragFseDocument(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragFseDocument(aNodeIdString);
	}

	public FseDocument getFseDocument(String aDocumentId) {
		return getFseDocument(aDocumentId, true);
	}

	public FseDocument getFseDocument(String aDocumentId, boolean bResetModificationState) {
		NodeFragFseDocument theNodeFragFseDocument = getNodeFragFseDocumentForDocumentId(aDocumentId);
		if(theNodeFragFseDocument == null) {
			return null;
		}
		FseDocument theFseDocument = theNodeFragFseDocument.getFseDocument();
		if(bResetModificationState) {
			theFseDocument.resetModificationState();
		}
		return theFseDocument;
	}

	public NodeFragFseDocument getNodeFragFseDocumentForDocumentId(String aDocumentId) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragFseDocumentForDocumentId(aDocumentId);
	}

	public FseDocument getFseDocumentForParent(String aParentId) {
		return getFseDocumentForParent(aParentId, true);
	}

	public FseDocument getFseDocumentForParent(String aParentId, boolean bResetModificationState) {
		FseDocument theDocumentForParent = null;
		NodeFragFseDocument theNodeFragFseDocument = this.persistenceTechnologyDelegate.dbRetrieveNodeFragFseDocumentForParent(aParentId);
		if(theNodeFragFseDocument != null) {
			theDocumentForParent = theNodeFragFseDocument.getFseDocument();
		}
		if(theDocumentForParent != null && bResetModificationState) {
			theDocumentForParent.resetModificationState();
		}
		return theDocumentForParent;
	}

	public NodeFragFseDocument getNodeFragFseDocumentForParentOrCreate(String aParentId) {
		NodeFragFseDocument theNodeFragFseDocument = this.persistenceTechnologyDelegate.dbRetrieveNodeFragFseDocumentForParent(aParentId);
		if(theNodeFragFseDocument == null) {
			theNodeFragFseDocument = new NodeFragFseDocument(new FseDocument(aParentId, false));
			newNodeFragFseDocument(theNodeFragFseDocument, true);
		}
		return theNodeFragFseDocument;
	}

	public FseDocument getFseDocumentForParentOrCreate(String aParentId) {
		FseDocument theDocumentForParent = getFseDocumentForParent(aParentId);
		if(theDocumentForParent == null) {
			theDocumentForParent = new FseDocument(aParentId, false);
			newNodeFragFseDocumentForFseDocument(theDocumentForParent, true);
		}
		return theDocumentForParent;
	}

	public void newNodeFragFseDocumentForFseDocument(FseDocument anFseDocument, boolean bAtomicTransaction) {
		NodeFragFseDocument theNodeFragFseDocument = new NodeFragFseDocument(anFseDocument); 
		this.persistenceTechnologyDelegate.dbInsertNodeFragFseDocument(theNodeFragFseDocument, bAtomicTransaction);
		anFseDocument.resetModificationState();
	}

	public boolean newNodeFragFseDocument(NodeFragFseDocument aNodeFragFseDocument, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertNodeFragFseDocument(aNodeFragFseDocument, bAtomicTransaction);
		aNodeFragFseDocument.resetModificationState();
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	private NodeFragFseDocument newNodeFragFseDocument(FmmHeadlineNode aHeadlineNode) {
		NodeFragFseDocument theNodeFragFseDocument = new NodeFragFseDocument(aHeadlineNode );
		return newNodeFragFseDocument(theNodeFragFseDocument, false) ? theNodeFragFseDocument : null;
	}

	public void saveNodeFragFseDocument(NodeFragFseDocument aNodeFragFseDocument, boolean bAtomicTransaction) {
		if(existsNodeFragFseDocument(aNodeFragFseDocument.getNodeIdString())) {
			updateNodeFragFseDocument(aNodeFragFseDocument, bAtomicTransaction);
		} else {
			newNodeFragFseDocument(aNodeFragFseDocument, bAtomicTransaction);
		}
	}

	public void updateFseDocument(FseDocument theFseDocument, boolean bAtomicTransaction) {
		NodeFragFseDocument theNodeFragFseDocument = getNodeFragFseDocumentForDocumentId(theFseDocument.getDocumentId());
		theNodeFragFseDocument.setFseDocument(theFseDocument);
		updateNodeFragFseDocument(theNodeFragFseDocument, bAtomicTransaction);
	}

	public void updateNodeFragFseDocument(NodeFragFseDocument aNodeFragFseDocument, boolean bAtomicTransaction) {
		this.persistenceTechnologyDelegate.dbUpdateNodeFragFseDocument(aNodeFragFseDocument, bAtomicTransaction);
		aNodeFragFseDocument.resetModificationState();
	}

	public boolean existsNodeFragFseDocument(String aNodeIdString) {
		return getNodeFragFseDocument(aNodeIdString) != null;
	}

	public boolean existsNodeFragFseDocumentForDocumentId(String aDocumentId) {
		return getNodeFragFseDocumentForDocumentId(aDocumentId) != null;
	}

	private boolean deleteNodeFragFseDocumentForParent(FmmHeadlineNode aHeadlineNode, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteNodeFragFseDocumentForParent(
				aHeadlineNode.getNodeIdString(), bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG GOVERNANCE  ////////////////////////////////////////////////////////////////////////

	public Collection<NodeFragGovernance> getNodeFragGovernanceCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragGovernanceList();
	}

	public NodeFragGovernance getNodeFragGovernanceForParent(String aParentId) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragGovernanceForParent(aParentId);
	}

	public NodeFragGovernance getNodeFragGovernanceForParentOrCreate(String aParentId) {
		NodeFragGovernance theNodeFragGovernance = getNodeFragGovernanceForParent(aParentId);
		if(theNodeFragGovernance == null) {
			theNodeFragGovernance = new NodeFragGovernance(aParentId);
			newNodeFragGovernance(theNodeFragGovernance, true);
		}
		return theNodeFragGovernance;
	}

	public NodeFragGovernance getNodeFragGovernance(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragGovernance(aNodeIdString);
	}

	public boolean newNodeFragGovernance(NodeFragGovernance aNodeFragGovernance, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertNodeFragGovernance(aNodeFragGovernance, bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	private NodeFragGovernance newNodeFragGovernance(FmmGovernableNode aGovernableNode) {
		NodeFragGovernance theNodeFragGovernance = new NodeFragGovernance(
				aGovernableNode.getNodeIdString() );
		return newNodeFragGovernance(theNodeFragGovernance, false) ? theNodeFragGovernance : null;
	}

	private boolean deleteNodeFragGovernanceForParent(FmmGovernableNode aGovernableNode, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteNodeFragGovernanceForParent(
				aGovernableNode.getNodeIdString(), bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG TRIBKN QUALITY  ////////////////////////////////////////////////////////////////////////

	public Collection<NodeFragTribKnQuality> getNodeFragTribKnQualityCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragTribKnQualityList();
	}

	public NodeFragTribKnQuality getNodeFragTribKnQualityForParent(String aParentId) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragTribKnQualityForParent(aParentId);
	}

	public NodeFragTribKnQuality getNodeFragTribKnQualityOrCreate(FmmHeadlineNode aHeadlineNode) {
		NodeFragTribKnQuality theTribKnQuality = getNodeFragTribKnQualityForParent(aHeadlineNode.getNodeIdString());
		if(theTribKnQuality == null) {
			theTribKnQuality = new NodeFragTribKnQuality(aHeadlineNode);
			newNodeFragTribKnQuality(theTribKnQuality, true);
		}
		return theTribKnQuality;
	}

	public NodeFragTribKnQuality getNodeFragTribKnQualityForParentOrCreate(String aParentId) {
		NodeFragTribKnQuality theNodeFragTribKnQuality = getNodeFragTribKnQualityForParent(aParentId);
		if(theNodeFragTribKnQuality == null) {
			theNodeFragTribKnQuality = new NodeFragTribKnQuality(aParentId);
			newNodeFragTribKnQuality(theNodeFragTribKnQuality, true);
		}
		return theNodeFragTribKnQuality;
	}

	public NodeFragTribKnQuality getNodeFragTribKnQuality(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragTribKnQuality(aNodeIdString);
	}

	public boolean newNodeFragTribKnQuality(NodeFragTribKnQuality aNodeFragTribKnQuality, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertNodeFragTribKnQuality(aNodeFragTribKnQuality, bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	private NodeFragTribKnQuality newNodeFragTribKnQuality(FmmHeadlineNode aHeadlineNode) {
		NodeFragTribKnQuality theNodeFragTribKnQuality = new NodeFragTribKnQuality(aHeadlineNode);
		return newNodeFragTribKnQuality(theNodeFragTribKnQuality, false) ? theNodeFragTribKnQuality : null;
	}

	private boolean deleteNodeFragTribKnQualityForParent(FmmHeadlineNode aHeadlineNode, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteNodeFragTribKnQualityForParent(
				aHeadlineNode.getNodeIdString(), bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG WORK TASK BUDGET  //////////////////////////////////////////////////////////////////

	public Collection<NodeFragWorkTaskBudget> getNodeFragWorkTaskBudgetCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragWorkTaskBudgetList();
	}

	public NodeFragWorkTaskBudget getNodeFragWorkTaskBudgetForParent(String aParentId) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragWorkTaskBudgetForParent(aParentId);
	}

	public NodeFragWorkTaskBudget getNodeFragWorkTaskBudgetForParentOrCreate(String aParentId) {
		NodeFragWorkTaskBudget theNodeFragWorkTaskBudget = getNodeFragWorkTaskBudgetForParent(aParentId);
		if(theNodeFragWorkTaskBudget == null) {
			theNodeFragWorkTaskBudget = new NodeFragWorkTaskBudget(aParentId);
			newNodeFragWorkTaskBudget(theNodeFragWorkTaskBudget, true);
		}
		return theNodeFragWorkTaskBudget;
	}

	public NodeFragWorkTaskBudget getNodeFragWorkTaskBudget(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragWorkTaskBudget(aNodeIdString);
	}

	public boolean newNodeFragWorkTaskBudget(NodeFragWorkTaskBudget aNodeFragWorkTaskBudget, boolean bAtomicTransaction) {
		return this.persistenceTechnologyDelegate.dbInsertNodeFragWorkTaskBudget(aNodeFragWorkTaskBudget, bAtomicTransaction);
	}

	private NodeFragWorkTaskBudget newNodeFragWorkTaskBudgetForParent(FmmGovernableNode aGovernableNode) {
		NodeFragWorkTaskBudget theNodeFragWorkTaskBudget = new NodeFragWorkTaskBudget(
				aGovernableNode.getNodeIdString() );
		return newNodeFragWorkTaskBudget(theNodeFragWorkTaskBudget, false) ? theNodeFragWorkTaskBudget : null;
	}

	private boolean deleteNodeFragWorkTaskBudgetForParent(FmmCompletionNode aCompletionNode, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteNodeFragWorkTaskBudgetForParent(
				aCompletionNode.getNodeIdString(), bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - WORK TASK  //////////////////////////////////////////////////////////////////

    public ArrayList<WorkTask> listWorkTasks(WorkPackage aWorkPackage) {
        return listWorkTasksForWorkPackage(aWorkPackage.getNodeIdString());
    }

    public ArrayList<WorkTask> listWorkTasksForWorkPackage(String aWorkPackageId) {
        return listWorkTasksForWorkPackage(aWorkPackageId, null);
    }

    public ArrayList<WorkTask> listWorkTasks(WorkPackage aWorkPackage, WorkTask aWorkTaskException) {
        return listWorkTasksForWorkPackage(aWorkPackage.getNodeIdString());
    }

    public ArrayList<WorkTask> listWorkTasksForWorkPackage(String aWorkPackageId, String aWorkTaskExceptionId) {
        return this.persistenceTechnologyDelegate.dbListWorkTasksForWorkPackage(aWorkPackageId, aWorkTaskExceptionId);
    }

    public ArrayList<WorkTask> listWorkTasks(WorkPlan aWorkPlan) {
        return listWorkTasksForWorkPlan(aWorkPlan.getNodeIdString());
    }

    public ArrayList<WorkTask> listWorkTasksForWorkPlan(String aWorkPlanId) {
        return listWorkTasksForWorkPlan(aWorkPlanId, null);
    }

    public ArrayList<WorkTask> listWorkTasks(WorkPlan aWorkPlan, WorkTask aWorkTaskException) {
        return listWorkTasksForWorkPlan(aWorkPlan.getNodeIdString());
    }

    public ArrayList<WorkTask> listWorkTasksForWorkPlan(String aWorkPlanId, String aWorkTaskExceptionId) {
        return this.persistenceTechnologyDelegate.dbListWorkTasksForWorkPlan(aWorkPlanId, aWorkTaskExceptionId);
    }

    public ArrayList<WorkTask> listWorkTaskOrphansFromWorkPackage() {
        return this.persistenceTechnologyDelegate.dbListWorkTaskOrphansFromWorkPackage();
    }

    public ArrayList<WorkTask> listWorkTaskOrphansFromWorkPlan() {
        return this.persistenceTechnologyDelegate.dbListWorkTaskOrphansFromWorkPlan();
    }

	public WorkTask retrieveWorkTask(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveWorkTask(aNodeIdString);
	}

    private WorkTask newWorkTaskForParent(
            String aHeadline,
            FmmHeadlineNode aParentNode,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd) {
        return aParentNode.getFmmNodeDefinition() == FmmNodeDefinition.WORK_PACKAGE ?
                newWorkTaskForWorkPackage(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd) :
                newWorkTaskForWorkPlan(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
    }

    private WorkTask newWorkTaskForWorkPackage(
            String aHeadline,
            FmmHeadlineNode aParentNode,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd ) {
        startTransaction();
        WorkTask theNewWorkTask = new WorkTask();
        theNewWorkTask.setHeadline(aHeadline);
        theNewWorkTask.setWorkPackageNodeIdString(aParentNode.getNodeIdString());
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                FmmNodeDefinition.WORK_TASK,
                WorkTaskMetaData.column_WORK_PACKAGE__ID,
                aParentNode,
                aPeerNode,
                bSequenceAtEnd );
        theNewWorkTask.setSequence(theNewSequenceNumber);
        boolean isSuccess = newWorkTask(theNewWorkTask, false);
        isSuccess = isSuccess && newNodeFragTribKnQuality(theNewWorkTask) != null;
        endTransaction(isSuccess);
        return theNewWorkTask;
    }

    private WorkTask newWorkTaskForWorkPlan(
            String aHeadline,
            FmmHeadlineNode aParentNode,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd ) {
        startTransaction();
        WorkTask theNewWorkTask = new WorkTask();
        theNewWorkTask.setHeadline(aHeadline);
        theNewWorkTask.setWorkPlanNodeIdString(aParentNode.getNodeIdString());
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                FmmNodeDefinition.WORK_TASK,
                WorkTaskMetaData.column_WORK_PLAN__ID,
                aParentNode,
                aPeerNode,
                bSequenceAtEnd,
                WorkTaskMetaData.column_WORK_PLAN_SEQUENCE );
        theNewWorkTask.setWorkPlanSequence(theNewSequenceNumber);
        boolean isSuccess = newWorkTask(theNewWorkTask, false);
        isSuccess = isSuccess && newNodeFragTribKnQuality(theNewWorkTask) != null;
        endTransaction(isSuccess);
        return theNewWorkTask;
    }
    
	public boolean newWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertWorkTask(aWorkTask, bAtomicTransaction) &&
				newCompletionNode(aWorkTask);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public boolean updateWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction) {
		updateHeadlineNode(aWorkTask);
		return this.persistenceTechnologyDelegate.dbUpdateWorkTask(aWorkTask, bAtomicTransaction);
	}

    public void saveWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction) {
        if(existsWorkTask(aWorkTask.getNodeIdString())) {
            updateWorkTask(aWorkTask, bAtomicTransaction);
        } else {
            newWorkTask(aWorkTask, bAtomicTransaction);
        }
    }

    public boolean existsWorkTask(String aNodeIdString) {
        return retrieveWorkTask(aNodeIdString) != null;
    }

    public boolean moveAllWorkTasksIntoWorkPackage(String aSourceWorkPackageId, String aDestinationWorkPackageId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbMoveAllWorkTasksIntoWorkPackage(
                aSourceWorkPackageId,
                aDestinationWorkPackageId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

    public boolean orphanAllWorkTasksFromWorkPackage(String aWorkPackageId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanAllWorkTasksFromWorkPackage(aWorkPackageId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean orphanSingleWorkTaskFromWorkPackage(String aWorkTaskId, String aWorkPackageId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanSingleWorkTaskFromWorkPackage(aWorkTaskId, aWorkPackageId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean adoptOrphanWorkTaskIntoWorkPackage(String aWorkTaskId, String aWorkPackageId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbAdoptOrphanWorkTaskIntoWorkPackage(
                aWorkTaskId,
                aWorkPackageId,
                bSequenceAtEnd,
                bAtomicTransaction );
    }

	public boolean deleteWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteWorkTask(aWorkTask, false) &&
				deleteGovernableNode(aWorkTask);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FMM CONFIGURATION  ///////////////////////////////////////////////////////////////////////////

	public Collection<FmmConfiguration> getFmmConfigurationCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveFmmConfigurationList();
	}

	public FmmConfiguration getFmmConfiguration(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveFmmConfiguration(aNodeIdString);
	}

	public boolean newFmmConfiguration(FmmConfiguration anFmmConfiguration, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertFmmConfiguration(anFmmConfiguration, bAtomicTransaction) &&
				newHeadlineNode(anFmmConfiguration);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public boolean updateFmmConfiguration(FmmConfiguration aFmmConfiguration, boolean bAtomicTransaction) {
		updateHeadlineNode(aFmmConfiguration);
		return this.persistenceTechnologyDelegate.dbUpdateFmmConfiguration(aFmmConfiguration, bAtomicTransaction);
	}

	public boolean deleteFmmConfiguration(FmmConfiguration anFmmConfiguration, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteFmmConfiguration(anFmmConfiguration, bAtomicTransaction) &&
				deleteGovernableNode(anFmmConfiguration);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FMS ORGANIZATION  ////////////////////////////////////////////////////////////////////////////////

	public Collection<FmsOrganization> getFmsOrganizationCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveFmsOrganizationList();
	}

	public FmsOrganization getFmsOrganization(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveFmsOrganization(aNodeIdString);
	}

	public boolean newFmsOrganization(FmsOrganization anFmsOrganization, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertFmsOrganization(anFmsOrganization, bAtomicTransaction) &&
				newHeadlineNode(anFmsOrganization);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public boolean updateFmsOrganization(FmsOrganization anFmsOrganization, boolean bAtomicTransaction) {
		updateHeadlineNode(anFmsOrganization);
		return this.persistenceTechnologyDelegate.dbUpdateFmsOrganization(anFmsOrganization, bAtomicTransaction);
	}

	public boolean deleteFmsOrganization(FmsOrganization anFmsOrganization, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteFmsOrganization(anFmsOrganization, bAtomicTransaction) &&
				deleteGovernableNode(anFmsOrganization);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - ORGANIZATION COMMUNITY MEMBER  ///////////////////////////////////////////////////////////////

	public Collection<OrganizationCommunityMember> getOrganizationCommunityMemberCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveOrganizationCommunityMemberList();
	}

	public OrganizationCommunityMember getOrganizationCommunityMember(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveOrganizationCommunityMember(aNodeIdString);
	}

	public boolean newOrganizationCommunityMember(OrganizationCommunityMember anOrganizationCommunityMember, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertOrganizationCommunityMember(anOrganizationCommunityMember, bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public boolean deleteOrganizationCommunityMember(OrganizationCommunityMember anOrganizationCommunityMember, boolean bAtomicTransaction) {
		return this.persistenceTechnologyDelegate.dbDeleteOrganizationCommunityMember(anOrganizationCommunityMember, bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - PDF PUBLICATION  /////////////////////////////////////////////////////////////////////////////

	public Collection<PdfPublication> getPdfPublicationCollection() {
		return this.persistenceTechnologyDelegate.dbRetrievePdfPublicationList();
	}

	public ArrayList<PdfPublication> getPdfPublicationListForTargetNode(String aTargetId) {
		return this.persistenceTechnologyDelegate.dbRetrievePdfPublicationLisForTargetNode(aTargetId);
	}

	public Collection<PdfPublication> getPdfPublicationCollectionForTargetNode(String aTargetId) {
		return getPdfPublicationListForTargetNode(aTargetId);
	}

	public ArrayList<PdfPublication> getPdfPublicationListForTargetNodeAndCommunityMember(String aTargetId, String aCommunityMemberId) {
		return this.persistenceTechnologyDelegate.dbRetrievePdfPublicationLisForTargetNodeAndCommunityMember(aTargetId, aCommunityMemberId);
	}

	public Collection<PdfPublication> getPdfPublicationCollectionForTargetNodeAndCommunityMember(String aTargetId, String aCommunityMemberId) {
		return getPdfPublicationListForTargetNodeAndCommunityMember(aTargetId, aCommunityMemberId);
	}

	public PdfPublication getPdfPublication(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrievePdfPublication(aNodeIdString);
	}

	public boolean newPdfPublication(PdfPublication aPdfPublication, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertPdfPublication(aPdfPublication, bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public boolean deletePdfPublication(PdfPublication aPdfPublication, boolean bAtomicTransaction) {
		return this.persistenceTechnologyDelegate.dbDeletePdfPublication(aPdfPublication, bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - PROJECT  /////////////////////////////////////////////////////////////////////////////////////

	public Collection<Project> getProjectCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveProjectList();
	}

	public Project getProject(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveProject(aNodeIdString);
	}

	public boolean newProject(Project aProject, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertProject(aProject, bAtomicTransaction) &&
				newCompletionNode(aProject);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

    private Project newProjectForParent(
            String aHeadline,
            FmmHeadlineNode aParentNode,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd) {
        startTransaction();
        Project theNewProject = new Project(
                new NodeId(FmmNodeDefinition.PROJECT), aHeadline, aParentNode.getNodeIdString() );
        boolean isSuccess = newProject(theNewProject, false) &&
                newNodeFragTribKnQuality(theNewProject) != null;
        endTransaction(isSuccess);
        return theNewProject;
    }

	public boolean updateProject(Project aProject, boolean bAtomicTransaction) {
		updateHeadlineNode(aProject);
		return this.persistenceTechnologyDelegate.dbUpdateProject(aProject, bAtomicTransaction);
	}

    public void saveProject(Project aProject, boolean bAtomicTransaction) {
        if(existsProject(aProject.getNodeIdString())) {
            updateProject(aProject, bAtomicTransaction);
        } else {
            newProject(aProject, bAtomicTransaction);
        }
    }

    public boolean existsProject(String aNodeIdString) {
        return getProject(aNodeIdString) != null;
    }

    public boolean orphanSingleProjectFromPortfolio(String aProjectNodeIdString, String aPortfolioNodeIdString, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanSingleProjectFromPortfolio(aProjectNodeIdString, aPortfolioNodeIdString, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean orphanAllProjectsFromPortfolio(String aPortfolioId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanAllProjectsFromPortfolio(aPortfolioId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean moveSingleProjectIntoPortfolio(String aProjectId, String aPortfolioId, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbMoveSingleProjectIntoPortfolio(aProjectId, aPortfolioId, bAtomicTransaction);
    }

	public boolean deleteProject(Project aProject, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteProject(aProject, bAtomicTransaction) &&
				deleteCompletableNode(aProject);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - STRATEGIC COMMITMENT  ////////////////////////////////////////////////////////////////////////

	public Collection<StrategicCommitment> getStrategicCommitmentCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveStrategicCommitmentList();
	}

	public ArrayList<StrategicCommitment> getStrategicCommitmentListForStrategicMilestone(StrategicMilestone aStrategicMilestone) {
		return getStrategicCommitmentListForStrategicMilestone(aStrategicMilestone.getNodeIdString());
	}

	public ArrayList<StrategicCommitment> getStrategicCommitmentListForStrategicMilestone(String aStrategicMilestoneNodeId) {
		return this.persistenceTechnologyDelegate.dbRetrieveStrategicCommitmentListForStrategicMilestone(aStrategicMilestoneNodeId);
	}

	public Collection<StrategicCommitment> getStrategicCommitmentCollection(StrategicCommitment aStrategicCommitment) {
		return getStrategicCommitmentListForStrategicMilestone(aStrategicCommitment.getNodeIdString());
	}

	public StrategicCommitment getStrategicCommitment(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveStrategicCommitment(aNodeIdString);
	}

	public StrategicCommitment getStrategicCommitment(String aStrategicMilestoneId, String aProjectAssetId) {
		return this.persistenceTechnologyDelegate.dbRetrieveStrategicCommitment(aStrategicMilestoneId, aProjectAssetId);
	}

	public StrategicCommitment getStrategicCommitmentForProjectAsset(String aProjectAssetId) {
		return this.persistenceTechnologyDelegate.dbRetrieveStrategicCommitmentForProjectAsset(aProjectAssetId);
	}

	public boolean newStrategicCommitment(StrategicCommitment aStrategicCommitment, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertStrategicCommitment(aStrategicCommitment, bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public boolean updateStrategicCommitment(StrategicCommitment aStrategicCommitment, boolean bAtomicTransaction) {
		return this.persistenceTechnologyDelegate.dbUpdateStrategicCommitment(aStrategicCommitment, bAtomicTransaction);
	}

	public boolean deleteStrategicCommitment(StrategicCommitment aStrategicCommitment, boolean bAtomicTransaction) {
		return this.persistenceTechnologyDelegate.dbDeleteStrategicCommitment(aStrategicCommitment, bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - STRATEGIC MILESTONE  /////////////////////////////////////////////////////////////////////////

	public ArrayList<StrategicMilestone> getStrategicMilestoneListForFiscalYear(String aFiscalYearId) {
		return this.persistenceTechnologyDelegate.dbListStrategicMilestone(aFiscalYearId);
	}

	public ArrayList<StrategicMilestone> getStrategicMilestoneList(FiscalYear aFiscalYear) {
		return this.persistenceTechnologyDelegate.dbListStrategicMilestone(aFiscalYear);
	}

	public ArrayList<StrategicMilestone> getStrategicMilestoneListForFiscalYear(String aFiscalYearId, String aStrategicMilestoneExceptionId) {
		return this.persistenceTechnologyDelegate.dbListStrategicMilestone(aFiscalYearId, aStrategicMilestoneExceptionId);
	}

	public ArrayList<StrategicMilestone> getStrategicMilestoneList(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestoneException) {
		return this.persistenceTechnologyDelegate.dbListStrategicMilestone(aFiscalYear, aStrategicMilestoneException);
	}

	public int countStrategicMilestoneForProjectAssetMoveTarget(
			FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestonException) {
		return this.persistenceTechnologyDelegate.dbCountStrategicMilestoneForProjectAssetMoveTarget(aFiscalYear, aStrategicMilestonException);
	}

	public ArrayList<StrategicMilestone> listStrategicMilestoneForProjectAssetMoveTarget(
			FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestonException) {
		return this.persistenceTechnologyDelegate.dbListStrategicMilestoneForProjectAssetMoveTarget(aFiscalYear, aStrategicMilestonException);
	}

	public int countStrategicMilestoneForWorkPackageMoveTarget(
			FiscalYear aFiscalYear, ProjectAsset aProjectAssetException) {
		return this.persistenceTechnologyDelegate.dbCountStrategicMilestoneForWorkPackageMoveTarget(aFiscalYear, aProjectAssetException);
	}

	public ArrayList<StrategicMilestone> listStrategicMilestoneForWorkPackageMoveTarget(
			FiscalYear aFiscalYear, ProjectAsset aProjectAssetException) {
		return this.persistenceTechnologyDelegate.dbListStrategicMilestoneForWorkPackageMoveTarget(aFiscalYear, aProjectAssetException);
	}

	public StrategicMilestone retrieveStrategicMilestone(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveStrategicMilestone(aNodeIdString);
	}

	private StrategicMilestone newStrategicMilestoneForParent(
			String aHeadline,
			FmmHeadlineNode aParentNode,
			FmmHeadlineNode aPeerNode,
			boolean bSequenceAtEnd) {
		startTransaction();
		StrategicMilestone theNewStrategicMilestone = new StrategicMilestone(
				new NodeId(FmmNodeDefinition.STRATEGIC_MILESTONE), aHeadline, aParentNode.getNodeIdString() );
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                FmmNodeDefinition.STRATEGIC_MILESTONE,
                StrategicMilestoneMetaData.column_FISCAL_YEAR_ID,
                aParentNode,
                aPeerNode,
                bSequenceAtEnd );
        theNewStrategicMilestone.setSequence(theNewSequenceNumber);
		boolean isSuccess = newStrategicMilestone(theNewStrategicMilestone, false) &&
				newNodeFragTribKnQuality(theNewStrategicMilestone) != null;
		endTransaction(isSuccess);
		return theNewStrategicMilestone;
	}

	private boolean newStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertStrategicMilestone(aStrategicMilestone, bAtomicTransaction) &&
				newCompletionNode(aStrategicMilestone);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public boolean updateStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
		updateHeadlineNode(aStrategicMilestone);
		return this.persistenceTechnologyDelegate.dbUpdateStrategicMilestone(aStrategicMilestone, bAtomicTransaction);
	}

	public boolean deleteStrategicMilestonesForFiscalYear(String aFiscalYearId, boolean bAtomicTransaction) {
		boolean theBoolean = true;
		ArrayList<StrategicMilestone> theStrategicMilestoneList = getStrategicMilestoneListForFiscalYear(aFiscalYearId);
		for(StrategicMilestone theStrategicMilestone : theStrategicMilestoneList) {
			theBoolean = theBoolean && deleteStrategicMilestone(theStrategicMilestone, bAtomicTransaction);
		}
		return theBoolean;
	}

	public boolean deleteStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteStrategicMilestone(aStrategicMilestone, bAtomicTransaction) &&
				deleteCompletableNode(aStrategicMilestone);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public void saveStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
		if(existsStrategicMilestone(aStrategicMilestone.getNodeIdString())) {
			updateStrategicMilestone(aStrategicMilestone, bAtomicTransaction);
		} else {
			newStrategicMilestone(aStrategicMilestone, bAtomicTransaction);
		}
	}

	public boolean existsStrategicMilestone(String aNodeIdString) {
		return retrieveStrategicMilestone(aNodeIdString) != null;
	}

	public boolean moveAllStrategicMilestonesIntoFiscalYear(
            String aCurrentFiscalYearId,
            String aDestinationFiscalYearId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
		int theDbRowCount = this.persistenceTechnologyDelegate.dbMoveAllStrategicMilestonesIntoFiscalYear(
                aCurrentFiscalYearId,
                aDestinationFiscalYearId,
                bSequenceAtEnd,
                bAtomicTransaction);
		return theDbRowCount > 0;  // TODO - what is failure ?
	}

	public boolean moveSingleStrategicMilestoneIntoFiscalYear(
            String aStrategicMilestoneId,
            String anOriginalFiscalYearId,
            String aDestinationFiscalYearId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
		return this.persistenceTechnologyDelegate.dbMoveSingleStrategicMilestoneIntoFiscalYear(
                aStrategicMilestoneId,
                anOriginalFiscalYearId,
                aDestinationFiscalYearId,
                bSequenceAtEnd,
                bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - WORK PACKAGE  ////////////////////////////////////////////////////////////////////////////////

	public ArrayList<WorkPackage> listWorkPackageForProjectAsset(String aProjectAssetId) {
		return this.persistenceTechnologyDelegate.dbListWorkPackagesForProjectAsset(aProjectAssetId);
	}

    public ArrayList<WorkPackage> listWorkPackage(WorkAsset aWorkAsset) {
        return listWorkPackageForProjectAsset(aWorkAsset.getNodeIdString());
    }

	public ArrayList<WorkPackage> listWorkPackage(ProjectAsset aProjectAsset) {
		return listWorkPackageForProjectAsset(aProjectAsset.getNodeIdString());
	}

    public ArrayList<WorkPackage> listWorkPackage(StrategicAsset aStrategicAsset) {
        return listWorkPackageForProjectAsset(aStrategicAsset.getNodeIdString());
    }

	public ArrayList<WorkPackage> listWorkPackageForFlywheelCadence(String aFlywheelCadenceId) {
		return this.persistenceTechnologyDelegate.dbListWorkPackageForFlywheelCadence(aFlywheelCadenceId);
	}

	public ArrayList<WorkPackage> listWorkPackage(FlywheelCadence aFlywheelCadence) {
		return listWorkPackageForFlywheelCadence(aFlywheelCadence.getNodeIdString());
	}

	public ArrayList<WorkPackage> listWorkPackageForWorkTaskMoveTarget(ProjectAsset aProjectAsset, WorkPackage aWorkPackageException) {
		return this.persistenceTechnologyDelegate.dbListWorkPackagesForWorkTaskMoveTarget(
                aProjectAsset.getNodeIdString(), aWorkPackageException.getNodeIdString(), true);
	}

	public ArrayList<WorkPackage> listWorkPackageForWorkTaskMoveTarget(FlywheelCadence aFlywheelCadence, WorkPackage aWorkPackageException) {
		return this.persistenceTechnologyDelegate.dbListWorkPackagesForWorkTaskMoveTarget(
                aFlywheelCadence.getNodeIdString(), aWorkPackageException.getNodeIdString(), true);
	}

	public ArrayList<WorkPackage> listWorkPackageOrphansFromProjectAsset() {
		return this.persistenceTechnologyDelegate.dbListWorkPackageOrphansFromProjectAsset();
	}

	public ArrayList<WorkPackage> listWorkPackageOrphansFromFlywheelCadence() {
		return this.persistenceTechnologyDelegate.dbListWorkPackageOrphansFromFlywheelCadence();
	}

	public boolean adoptOrphanWorkPackageIntoProjectAsset(
			String aWorkPackageId,
			String aProjectAssetId,
			boolean bSequenceAtEnd,
			boolean bAtomicTransaction ) {
		return this.persistenceTechnologyDelegate.dbAdoptOrphanWorkPackageIntoProjectAsset(
				aWorkPackageId,
				aProjectAssetId,
				bSequenceAtEnd,
				bAtomicTransaction );
	}

	public WorkPackage retrieveWorkPackage(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveWorkPackage(aNodeIdString);
	}

	public boolean newWorkPackage(WorkPackage aWorkPackage, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbInsertWorkPackage(aWorkPackage, bAtomicTransaction) &&
				newCompletionNode(aWorkPackage);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	private WorkPackage newWorkPackageForParent(
			String aHeadline,
			FmmHeadlineNode aParentNode,
			FmmHeadlineNode aPeerNode,
			boolean bSequenceAtEnd ) {
		return aParentNode.getFmmNodeDefinition() == FmmNodeDefinition.PROJECT_ASSET ||
                aParentNode.getFmmNodeDefinition() == FmmNodeDefinition.STRATEGIC_ASSET ?
				newWorkPackageForProjectAsset(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd) :
					newWorkPackageForFlywheelCadence(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
	}

	private WorkPackage newWorkPackageForProjectAsset(
			String aHeadline,
			FmmHeadlineNode aParentNode,
            FmmHeadlineNode aPeerNode,
			boolean bSequenceAtEnd ) {
		startTransaction();
		WorkPackage theNewWorkPackage = new WorkPackage(aHeadline, aParentNode.getNodeIdString());
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                FmmNodeDefinition.WORK_PACKAGE,
                WorkPackageMetaData.column_WORK_ASSET_ID,
                aParentNode,
                aPeerNode,
                bSequenceAtEnd );
		theNewWorkPackage.setSequence(theNewSequenceNumber);
		boolean isSuccess = newWorkPackage(theNewWorkPackage, false);
		isSuccess = isSuccess && newNodeFragTribKnQuality(theNewWorkPackage) != null;
		endTransaction(isSuccess);
		return theNewWorkPackage;
	}

	private WorkPackage newWorkPackageForFlywheelCadence(String aHeadline,
			FmmHeadlineNode aParentNode, FmmHeadlineNode aPeerNode,
			boolean bSequenceBeforeFlag) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateWorkPackage(WorkPackage aWorkPackage, boolean bAtomicTransaction) {
		updateHeadlineNode(aWorkPackage);
		return this.persistenceTechnologyDelegate.dbUpdateWorkPackage(aWorkPackage, bAtomicTransaction);
	}

    public void saveWorkPackage(WorkPackage aWorkPackage, boolean bAtomicTransaction) {
        if(existsWorkPackage(aWorkPackage.getNodeIdString())) {
            updateWorkPackage(aWorkPackage, bAtomicTransaction);
        } else {
            newWorkPackage(aWorkPackage, bAtomicTransaction);
        }
    }

    public boolean existsWorkPackage(String aNodeIdString) {
        return retrieveWorkPackage(aNodeIdString) != null;
    }

	public boolean orphanAllWorkPackagesFromProjectAsset(String aProjectAssetId, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanAllWorkPackagesFromProjectAsset(aProjectAssetId, bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

    public boolean orphanSingleWorkPackageFromProjectAsset(String aWorkPackageId, String aProjectAssetId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanSingleWorkPackageFromProjectAsset(aWorkPackageId, aProjectAssetId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean orphanAllWorkPackagesFromFlywheelCadence(String aFlywheelCadenceId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanAllWorkPackagesFromFlywheelCadence(aFlywheelCadenceId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean orphanSingleWorkPackageFromFlywheelCadence(String aWorkPackageId, String aFlywheelCadenceId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanSingleWorkPackageFromFlywheelCadence(aWorkPackageId, aFlywheelCadenceId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

	public boolean moveAllWorkPackagesIntoProjectAsset(
            String aSourceProjectAssetId,
            String aDestinationProjectAssetId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
	return this.persistenceTechnologyDelegate.dbMoveAllWorkPackagesIntoProjectAsset(
            aSourceProjectAssetId,
            aDestinationProjectAssetId,
            bSequenceAtEnd,
            bAtomicTransaction);
	}

    public boolean moveSingleWorkPackageIntoProjectAsset(
            String aWorkPackageId,
            String anOriginalProjectAssetId,
            String aDestinationProjectAssetId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbMoveSingleWorkPackageIntoProjectAsset(
                aWorkPackageId,
                anOriginalProjectAssetId,
                aDestinationProjectAssetId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

	public boolean deleteWorkPackage(WorkPackage aWorkPackage, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbDeleteWorkPackage(aWorkPackage, bAtomicTransaction) &&
				deleteCompletableNode(aWorkPackage);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public PersistenceTechnologyDelegate getPersistenceTechnologyDelegate() {
		return this.persistenceTechnologyDelegate;
	}

	public ArrayList<CommunityMember> getGovernanceCandidates(FmsOrganization anFmsOrganization, GovernanceTarget aGovernanceTarget, GovernanceRole aGovernanceRole) {
		return this.persistenceTechnologyDelegate.getGovernanceCandidates(anFmsOrganization, aGovernanceTarget, aGovernanceRole);
	}

	public boolean updateTargetDate(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = this.persistenceTechnologyDelegate.dbUpdateTargetDate(aStrategicMilestone, bAtomicTransaction );
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}
}
