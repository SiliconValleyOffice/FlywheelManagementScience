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

//////  YES, I KNOW this Class is HUGE.  :-)

package com.flywheelms.library.fmm;

import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fmm.helper.FmmHelper;
import com.flywheelms.library.fmm.interfaces.WorkAsset;
import com.flywheelms.library.fmm.meta_data.BookshelfLinkToNotebookMetaData;
import com.flywheelms.library.fmm.meta_data.BookshelfMetaData;
import com.flywheelms.library.fmm.meta_data.CadenceMetaData;
import com.flywheelms.library.fmm.meta_data.CommunityMemberMetaData;
import com.flywheelms.library.fmm.meta_data.CompletableNodeMetaData;
import com.flywheelms.library.fmm.meta_data.DiscussionTopicLinkToNodeFragAuditBlockMetaData;
import com.flywheelms.library.fmm.meta_data.FiscalYearHolidayBreakMetaData;
import com.flywheelms.library.fmm.meta_data.FiscalYearMetaData;
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
import com.flywheelms.library.fmm.meta_data.NotebookLinkToDiscussionTopicMetaData;
import com.flywheelms.library.fmm.meta_data.OrganizationCommunityMemberMetaData;
import com.flywheelms.library.fmm.meta_data.PdfPublicationMetaData;
import com.flywheelms.library.fmm.meta_data.PortfolioMetaData;
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
import com.flywheelms.library.fmm.node.impl.governable.Bookshelf;
import com.flywheelms.library.fmm.node.impl.governable.Cadence;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.governable.DiscussionTopic;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.FlywheelTeam;
import com.flywheelms.library.fmm.node.impl.governable.FmsOrganization;
import com.flywheelms.library.fmm.node.impl.governable.Notebook;
import com.flywheelms.library.fmm.node.impl.governable.Portfolio;
import com.flywheelms.library.fmm.node.impl.governable.Project;
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fmm.node.impl.governable.StrategicAsset;
import com.flywheelms.library.fmm.node.impl.governable.StrategicMilestone;
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fmm.node.impl.governable.WorkPlan;
import com.flywheelms.library.fmm.node.impl.governable.WorkTask;
import com.flywheelms.library.fmm.node.impl.headline.FiscalYearHolidayBreak;
import com.flywheelms.library.fmm.node.impl.link.BookshelfLinkToNotebook;
import com.flywheelms.library.fmm.node.impl.link.DiscussionTopicLinkToNodeFragAuditBlock;
import com.flywheelms.library.fmm.node.impl.link.NotebookLinkToDiscussionTopic;
import com.flywheelms.library.fmm.node.impl.link.OrganizationCommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.FragLock;
import com.flywheelms.library.fmm.node.impl.nodefrag.HeadlineNodeTrash;
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
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.fmm.persistence.PersistenceTechnologyDelegate;
import com.flywheelms.library.fmm.repository.FmmConfiguration;
import com.flywheelms.library.fse.model.FseDocument;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

/*
 * An instance of FmmDatabaseMediator is created for each configured Repository Service.
 * The fmmDatabaseMediatorMap data member is a container for all FmmDatabaseMediator instances,
 * indexed by the configuration of the database instance.
 */
public class FmmDatabaseMediator {

    private static final String sort_spec__HEADLINE = " LOWER (" + HeadlineNodeMetaData.column_HEADLINE + ") ASC";
    private static final String sort_spec__SEARCHABLE_HEADLINE = " LOWER (" + NodeFragAuditBlockMetaData.column_SEARCHABLE_HEADLINE + ") ASC";
    private static final String sort_spec__SEQUENCE = CompletableNodeMetaData.column_SEQUENCE + " ASC";
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

    // to solve an earlier development problem with timely initialization of static data.  May not be needed.
	private static void initMetaData() {
		CommunityMemberMetaData.init();
		CompletableNodeMetaData.init();
		FiscalYearMetaData.init();
        FiscalYearHolidayBreakMetaData.init();
        CadenceMetaData.init();
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
    
    ////  ID LIST - start  ////////////

    private ArrayList<String> getFmmNodeIdList(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aColumnValue) {
        return this.persistenceTechnologyDelegate.getFmmNodeIdList(anFmmNodeDefinition, aColumnName, aColumnValue);
    }

    private ArrayList<String> getFmmNodeIdList(FmmNodeDefinition anFmmNodeDefinition, String aWhereClause) {
        return this.persistenceTechnologyDelegate.getFmmNodeIdList(anFmmNodeDefinition, aWhereClause);
    }

    ////  ID LIST - end  ////////////


    ////  SIMPLE TABLE - start  ////////////

    // INSERT & UPDATE

    private boolean insertSimpleIdTable(FmmNode anFmmNode, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.insertSimpleIdTable(anFmmNode, bAtomicTransaction);
    }
    
    private boolean updateSimpleIdTable(FmmNode anFmmNode, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.updateSimpleIdTable(anFmmNode, bAtomicTransaction);
    }

    // RETRIEVE

    public boolean existsSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aNodeIdString) {
        return this.persistenceTechnologyDelegate.existsSimpleIdTable(anFmmNodeDefinition, aNodeIdString);
    }

    private  <T extends FmmNode> T retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aNodeIdString) {
		if(aNodeIdString == null || aNodeIdString.equals("")) {
			return null;
		}
        return this.persistenceTechnologyDelegate.retrieveFmmNodeFromSimpleIdTable(anFmmNodeDefinition, aNodeIdString);
    }

    private FmmNode retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aColumnValue) {
        return this.persistenceTechnologyDelegate.retrieveFmmNodeFromSimpleIdTable(anFmmNodeDefinition, aColumnName, aColumnValue);
    }

    private FmmNode retrieveFmmNodeFromTableForParent(FmmNodeDefinition anFmmNodeDefinition, String aParentId) {
        return this.persistenceTechnologyDelegate.retrieveFmmNodeFromTableForParent(anFmmNodeDefinition, aParentId);
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition aNodeDefinition) {
        return retrieveFmmNodeListFromSimpleIdTable(aNodeDefinition, null);
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition aNodeDefinition, String anOrderBySpec) {
        return retrieveFmmNodeListFromSimpleIdTable(aNodeDefinition, null, null, null, anOrderBySpec);
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromSimpleIdTable(
            FmmNodeDefinition aNodeDefinition,
            String aWhereColumnName,
            String aWhereColumnValue,
            String anExceptionId,
            String anOrderBySpec) {
        return this.persistenceTechnologyDelegate.listSimpleIdTable(aNodeDefinition, aWhereColumnName, aWhereColumnValue, anExceptionId, anOrderBySpec);
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromLinkTable(
            FmmNodeDefinition aLeftTableDefinition,
            String aLeftColumnExceptionValue,
            FmmNodeDefinition aLinkTableDefinition,
            String aLinkColumnName,
            String aLinkColumnValue,
            String anOrderBySpec) {
        return retrieveFmmNodeListFromLinkTable(
                aLeftTableDefinition,
                IdNodeMetaData.column_ID,
                aLeftColumnExceptionValue,
                aLinkTableDefinition,
                aLinkColumnName,
                aLinkColumnName + " = " + aLinkColumnValue,
                anOrderBySpec);
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromLinkTable(
            FmmNodeDefinition aLeftTableDefinition,
            String aLeftColumnExceptionValue,
            FmmNodeDefinition aLinkTableDefinition,
            String aLinkColumnName,
            String anAndColumnName,
            String anAndColumnValue,
            String anOrderBySpec) {
        return retrieveFmmNodeListFromLinkTable(
                aLeftTableDefinition,
                IdNodeMetaData.column_ID,
                aLeftColumnExceptionValue,
                aLinkTableDefinition,
                aLinkColumnName,
                anAndColumnName + " = " + anAndColumnValue,
                anOrderBySpec);
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromLinkTable(
            FmmNodeDefinition aLeftTableDefinition,
            String aLeftColumnName,
            String aLeftColumnExceptionValue,
            FmmNodeDefinition aLinkTableDefinition,
            String aLinkColumnName,
            String anAndColumnName,
            String anAndColumnValue,
            String anOrderBySpec) {
        return retrieveFmmNodeListFromLinkTable(
                aLeftTableDefinition,
                aLeftColumnName,
                aLeftColumnExceptionValue,
                aLinkTableDefinition,
                aLinkColumnName,
                anAndColumnName + " = " + anAndColumnValue,
                anOrderBySpec);
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromLinkTable(
            FmmNodeDefinition aLeftTableDefinition,
            String aLeftColumnName,
            String aLeftColumnExceptionValue,
            FmmNodeDefinition aLinkTableDefinition,
            String aLinkColumnName,
            String anAndSpec,
            String anOrderBySpec) {
        return this.persistenceTechnologyDelegate.listSimpleIdLeftTableFromLink(aLeftTableDefinition, aLeftColumnName, aLeftColumnExceptionValue, aLinkTableDefinition, aLinkColumnName, anAndSpec, anOrderBySpec);
    }

    private boolean existsFmmNode(FmmNode anFmmNode) {
        return existsFmmNode(anFmmNode.getFmmNodeDefinition(), anFmmNode.getNodeIdString());
    }

    private boolean existsFmmNode(FmmNodeDefinition anFmmNodeDefinition, String aNodeIdString) {
        return retrieveFmmNodeFromSimpleIdTable(anFmmNodeDefinition, aNodeIdString) != null;
    }

    // DELETE

    private boolean deleteRowFromSimpleIdTable(FmmNode anFmmNode, boolean bAtomicTransaction) {
        return deleteRowFromSimpleIdTable(anFmmNode.getFmmNodeDefinition(), anFmmNode.getNodeIdString(), bAtomicTransaction);
    }

    private boolean deleteRowFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aNodeIdString, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.deleteRowFromSimpleIdTable(anFmmNodeDefinition, aNodeIdString, bAtomicTransaction);
    }

    private boolean deleteRowFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aWhereColumnName, String aWhereColumnValue, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.deleteRowFromSimpleIdTable(anFmmNodeDefinition, aWhereColumnName, aWhereColumnValue, bAtomicTransaction);
    }
    
    ////  SIMPLE TABLE - end

    
    ////  SEQUENCE - start  ///////////

	private void swapSequence(FmmHeadlineNode aTargetNode, FmmHeadlineNode aPeerNode, FmmHeadlineNode aParentNode) {
		this.persistenceTechnologyDelegate.dbSwapSequence(aParentNode, aTargetNode, aPeerNode);
	}

	private void sequenceFirst(FmmHeadlineNode aTargetNode, FmmHeadlineNode aParentNode) {
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

	private void sequenceLast(FmmHeadlineNode aTargetNode, FmmHeadlineNode aParentNode) {
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

    /////  SEQUENCE - end  /////////
    
    
    /////  ROOT NODE - start ///////////

	public FmmHeadlineNode newChildHeadlineNode(
			FmmNodeDefinition anFmmNodeDefinition,
			String aHeadline,
			FmmHeadlineNode aParentNode,
			FmmHeadlineNode aPeerNode,
			boolean bSequenceAtEnd ) {
		FmmHeadlineNode theHeadlineNode = null;
		switch(anFmmNodeDefinition) {
		case BOOKSHELF:  // root nodes not created with this method()
			break;
		case COMMUNITY_MEMBER:
			break;
		case DISCUSSION_TOPIC:
            theHeadlineNode = createDiscussionTopicForNotebook(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
            break;
		case FACILITATION_ISSUE:
			break;
		case FISCAL_YEAR:  // root nodes not created with this method()
			break;
		case CADENCE:
            // only created in a "batch" from the wizard
			break;
		case NOTEBOOK:
            theHeadlineNode = createNotebookForBookshelf(aHeadline, aParentNode);
            break;
		case PORTFOLIO:  // root nodes not created with this method()
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
            // only created in a "batch" from the Cadence wizard
			break;
		case WORK_TASK:
            theHeadlineNode = newWorkTaskForParent(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
			break;
		default:
			break;
		}
		return theHeadlineNode;
	}

    
    /////////////////////////////////////////////////////////
    ///////////  FRACTAL INSERT - start  //////////////////

    private boolean fractalInsertFmmHeadlineNode(FmmHeadlineNode anFmmHeadlineNode, boolean bPersistTribKnQuality) {
        startTransaction();
        boolean isSuccess = insertSimpleIdTable(anFmmHeadlineNode, false);
        isSuccess &= insertNodeFragAuditBlock(anFmmHeadlineNode);
        isSuccess &= insertNodeFragFseDocument(anFmmHeadlineNode);
        if(bPersistTribKnQuality) {
            isSuccess &= insertNodeFragTribKnQuality(anFmmHeadlineNode);
        }
        return isSuccess;
    }

    private boolean fractalInsertFmmGovernableNode(FmmGovernableNode anFmmGovernableNode, boolean bPersistTribKnQuality) {
        boolean isSuccess = fractalInsertFmmHeadlineNode(anFmmGovernableNode, false);
        isSuccess &= insertNodeFragGovernance(anFmmGovernableNode);
        if(bPersistTribKnQuality) {
            isSuccess &= insertNodeFragTribKnQuality(anFmmGovernableNode);
        }
        return isSuccess;
    }

    private boolean fractalInsertFmmCompletionNode(FmmCompletionNode anFmmCompletionNode, boolean bPersistTribKnQuality) {
        boolean isSuccess = fractalInsertFmmGovernableNode(anFmmCompletionNode, false);
        isSuccess &= insertNodeFragCompletion(anFmmCompletionNode);
        isSuccess &= insertNodeFragWorkTaskBudget(anFmmCompletionNode);
        if(bPersistTribKnQuality) {
            isSuccess &= insertNodeFragTribKnQuality(anFmmCompletionNode);
        }
        return isSuccess;
    }

    ////////////  FRACTAL INSERT - end  ///////////////////////////
    /////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////
    ///////////  NODE FRAG INSERT - start  //////////////////


    private boolean insertNodeFragAuditBlock(FmmHeadlineNode anFmmHeadlineNode) {
        boolean isSuccess = false;
        NodeFragAuditBlock theNodeFragAuditBlock = new NodeFragAuditBlock(
                anFmmHeadlineNode.getNodeIdString(),
                anFmmHeadlineNode.getHeadline(),
                anFmmHeadlineNode.getRowTimestamp() );
        isSuccess = insertSimpleIdTable(theNodeFragAuditBlock, false);
        anFmmHeadlineNode.setNodeFragAuditBlock(theNodeFragAuditBlock);
        return isSuccess;
    }

    private boolean insertNodeFragFseDocument(FmmHeadlineNode anFmmHeadlineNode) {
        NodeFragFseDocument theNodeFragFseDocument = new NodeFragFseDocument(anFmmHeadlineNode );
        boolean isSuccess = insertSimpleIdTable(theNodeFragFseDocument, false);
        theNodeFragFseDocument.resetModificationState();
        anFmmHeadlineNode.setNodeFragFseDocument(theNodeFragFseDocument);
        return isSuccess;
    }

    private boolean insertNodeFragTribKnQuality(FmmHeadlineNode anFmmHeadlineNode) {
        NodeFragTribKnQuality theNodeFragTribKnQuality = new NodeFragTribKnQuality(anFmmHeadlineNode);
        boolean isSuccess = insertSimpleIdTable(theNodeFragTribKnQuality, false);
        anFmmHeadlineNode.setNodeFragTribKnQuality(theNodeFragTribKnQuality);
        return isSuccess;
    }

    private boolean insertNodeFragGovernance(FmmGovernableNode anFmmGovernableNode) {
        NodeFragGovernance theNodeFragGovernance = new NodeFragGovernance(anFmmGovernableNode);
        boolean isSuccess = insertSimpleIdTable(theNodeFragGovernance, false);
        anFmmGovernableNode.setNodeFragGovernance(theNodeFragGovernance);
        return isSuccess;
    }

    private boolean insertNodeFragCompletion(FmmCompletionNode anFmmCompletionNode) {
        NodeFragCompletion theNodeFragCompletion = new NodeFragCompletion(anFmmCompletionNode);
        boolean isSuccess = insertSimpleIdTable(theNodeFragCompletion, false);
        anFmmCompletionNode.setNodeFragCompletion(theNodeFragCompletion);
        return isSuccess;
    }

    private boolean insertNodeFragWorkTaskBudget(FmmCompletionNode anFmmCompletionNode) {
        NodeFragWorkTaskBudget theNodeFragWorkTaskBudget = new NodeFragWorkTaskBudget(anFmmCompletionNode);
        boolean isSuccess = insertSimpleIdTable(theNodeFragWorkTaskBudget, false);
        anFmmCompletionNode.setNodeFragWorkTaskBudget(theNodeFragWorkTaskBudget);
        return isSuccess;
    }

    ///////////  NODE FRAG INSERT - end  ////////////////////
    /////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////////////
    ////////////  FRACTAL UPDATE - start  ///////////////////////////


	private boolean fractalUpdateNodeHeadline(FmmHeadlineNode aHeadlineNode) {
        return this.persistenceTechnologyDelegate.fractalUpdateNodeHeadline(aHeadlineNode);
	}

    private boolean fractalUpdateNodeFragAuditBlock(FmmHeadlineNode anFmmHeadlineNode, boolean bAtomicTransaction) {
        anFmmHeadlineNode.setRowTimestamp(GcgDateHelper.getCurrentDateTime());
        NodeFragAuditBlock theNodeFragAuditBlock = anFmmHeadlineNode.getNodeFragAuditBlock();
        theNodeFragAuditBlock.setRowTimestamp(anFmmHeadlineNode.getRowTimestamp());
        // TODO get other audit information into the Audit Block
        return updateSimpleIdTable(theNodeFragAuditBlock, bAtomicTransaction);
    }

    private boolean fractalUpdateNodeFragGovernance(FmmHeadlineNode anFmmHeadlineNode, boolean bAtomicTransaction) {
        // TODO - determine any changes to TribKn and update NodeFragTribKn
        return true;
    }

    private boolean fractalUpdateNodeFragWorkTaskBudget(FmmHeadlineNode anFmmHeadlineNode, boolean bAtomicTransaction) {
        // TODO - determine any changes to TribKn and update NodeFragTribKn
        return true;
    }

    private boolean fractalUpdateNodeFragCompletion(FmmHeadlineNode anFmmHeadlineNode, boolean bAtomicTransaction) {
        // TODO - determine any changes to TribKn and update NodeFragTribKn
        return true;
    }

    private boolean fractalUpdateNodeFragTribKnQuality(FmmHeadlineNode anFmmHeadlineNode, boolean bAtomicTransaction) {
        // TODO - determine any changes to TribKn and update NodeFragTribKn for all affected nodes
        return true;
    }

    private boolean fractalUpdateFmmHeadlineNode(FmmHeadlineNode anFmmHeadlineNode, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = fractalUpdateNodeFragAuditBlock(anFmmHeadlineNode, false);
        isSuccess &= fractalUpdateNodeFragTribKnQuality(anFmmHeadlineNode, false);
        isSuccess &= updateSimpleIdTable(anFmmHeadlineNode, false);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    private boolean fractalUpdateFmmGovernableNode(FmmGovernableNode anFmmGovernableNode, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = fractalUpdateNodeFragAuditBlock(anFmmGovernableNode, false);
        isSuccess &= fractalUpdateNodeFragGovernance(anFmmGovernableNode, false);
        isSuccess &= fractalUpdateNodeFragTribKnQuality(anFmmGovernableNode, false);
        isSuccess &= updateSimpleIdTable(anFmmGovernableNode, false);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    private boolean fractalUpdateFmmCompletionNode(FmmCompletionNode anFmmCompletionNode, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = fractalUpdateNodeFragAuditBlock(anFmmCompletionNode, false);
        isSuccess &= fractalUpdateNodeFragGovernance(anFmmCompletionNode, false);
        isSuccess &= fractalUpdateNodeFragCompletion(anFmmCompletionNode, false);
        isSuccess &= fractalUpdateNodeFragWorkTaskBudget(anFmmCompletionNode, false);
        isSuccess &= fractalUpdateNodeFragTribKnQuality(anFmmCompletionNode, false);
        isSuccess &= updateSimpleIdTable(anFmmCompletionNode, false);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    ////////////  FRACTAL UPDATE - end  /////////////////////////////
    /////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////
    ///////////  FRACTAL SAVE - start  //////////////////

    private boolean fractalSaveFmmHeadlineNode(FmmHeadlineNode anFmmHeadlineNode, boolean bPersistTribKnQuality) {
        return false;
    }

    private boolean fractalSaveFmmGovernableNode(FmmGovernableNode anFmmGovernableNode, boolean bPersistTribKnQuality) {
        return false;
    }

    private boolean fractalSaveFmmCompletionNode(FmmCompletionNode anFmmCompletionNode, boolean bPersistTribKnQuality) {
        return false;
    }

    ////////////  FRACTAL SAVE - end  ///////////////////////////
    /////////////////////////////////////////////////////////////////


    private boolean fractalUpdateHeadlineNode(FmmHeadlineNode aHeadlineNode, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
//        HeadlineNodeTrash theHeadlineNodeTrash = new HeadlineNodeTrash(aHeadlineNode);
//        boolean isSuccess = deleteNodeFragAuditBlockForParent(aHeadlineNode, false);
//        isSuccess &= deleteNodeFragFseDocumentForParent(aHeadlineNode, false);
//        isSuccess &= deleteNodeFragTribKnQualityForParent(aHeadlineNode, false);
//        isSuccess &= deleteRowFromSimpleIdTable(aHeadlineNode, false);
//        if(isSuccess) {
//            isSuccess &= insertSimpleIdTable(theHeadlineNodeTrash, false);
//        }
        boolean isSuccess = updateSimpleIdTable(aHeadlineNode, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    private boolean fractalUpdateGovernableNode(FmmGovernableNode aGovernableNode, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
//        HeadlineNodeTrash theHeadlineNodeTrash = new HeadlineNodeTrash(aGovernableNode);
//        boolean isSuccess = deleteNodeFragAuditBlockForParent(aGovernableNode, false);
//        isSuccess &= deleteNodeFragFseDocumentForParent(aGovernableNode, false);
//        isSuccess &= deleteNodeFragTribKnQualityForParent(aGovernableNode, false);
//        isSuccess &= deleteNodeFragGovernanceForParent(aGovernableNode, false);
//        if(isSuccess) {
//            isSuccess &= insertSimpleIdTable(theHeadlineNodeTrash, false);
//        }
        boolean isSuccess = updateSimpleIdTable(aGovernableNode, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    private boolean fractalUpdateCompletableNode(FmmCompletionNode aCompletionNode, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
//        HeadlineNodeTrash theHeadlineNodeTrash = new HeadlineNodeTrash(aCompletionNode);
//        boolean isSuccess = deleteNodeFragAuditBlockForParent(aCompletionNode, false);
//        isSuccess &= deleteNodeFragFseDocumentForParent(aCompletionNode, false);
//        isSuccess &= deleteNodeFragTribKnQualityForParent(aCompletionNode, false);
//        isSuccess &= deleteNodeFragGovernanceForParent(aCompletionNode, false);
//        isSuccess &= deleteNodeFragCompletionForParent(aCompletionNode, false);
//        isSuccess &= deleteNodeFragWorkTaskBudgetForParent(aCompletionNode, false);
//        if(isSuccess) {
//            isSuccess &= insertSimpleIdTable(theHeadlineNodeTrash, false);
//        }
        boolean isSuccess = updateSimpleIdTable(aCompletionNode, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }


    /////////////////////////////////////////////////////////////////
    ////////////  FRACTAL DELETE - start  ///////////////////////////

	private boolean fractalDeleteHeadlineNode(FmmHeadlineNode aHeadlineNode, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        HeadlineNodeTrash theHeadlineNodeTrash = new HeadlineNodeTrash(aHeadlineNode);
		boolean isSuccess = deleteNodeFragAuditBlockForParent(aHeadlineNode, false);
		isSuccess &= deleteNodeFragFseDocumentForParent(aHeadlineNode, false);
		isSuccess &= deleteNodeFragTribKnQualityForParent(aHeadlineNode, false);
        isSuccess &= deleteRowFromSimpleIdTable(aHeadlineNode, false);
        if(isSuccess) {
            isSuccess &= insertSimpleIdTable(theHeadlineNodeTrash, false);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
		return isSuccess;
	}

	private boolean fractalDeleteGovernableNode(FmmGovernableNode aGovernableNode, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        HeadlineNodeTrash theHeadlineNodeTrash = new HeadlineNodeTrash(aGovernableNode);
        boolean isSuccess = deleteNodeFragAuditBlockForParent(aGovernableNode, false);
        isSuccess &= deleteNodeFragFseDocumentForParent(aGovernableNode, false);
        isSuccess &= deleteNodeFragTribKnQualityForParent(aGovernableNode, false);
		isSuccess &= deleteNodeFragGovernanceForParent(aGovernableNode, false);
        if(isSuccess) {
            isSuccess &= insertSimpleIdTable(theHeadlineNodeTrash, false);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
	}

	private boolean fractalDeleteCompletableNode(FmmCompletionNode aCompletionNode, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        HeadlineNodeTrash theHeadlineNodeTrash = new HeadlineNodeTrash(aCompletionNode);
        boolean isSuccess = deleteNodeFragAuditBlockForParent(aCompletionNode, false);
        isSuccess &= deleteNodeFragFseDocumentForParent(aCompletionNode, false);
        isSuccess &= deleteNodeFragTribKnQualityForParent(aCompletionNode, false);
        isSuccess &= deleteNodeFragGovernanceForParent(aCompletionNode, false);
        isSuccess &= deleteNodeFragCompletionForParent(aCompletionNode, false);
        isSuccess &= deleteNodeFragWorkTaskBudgetForParent(aCompletionNode, false);
        if(isSuccess) {
            isSuccess &= insertSimpleIdTable(theHeadlineNodeTrash, false);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
	}

    /////////////////////////////////////////////////////////////////
    ////////////  FRACTAL DELETE - end  /////////////////////////////

//	private boolean deleteRowsWithValue(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aValue) {
//		return this.persistenceTechnologyDelegate.dbDeleteRowsWithValue(anFmmNodeDefinition, aColumnName, aValue, false);
//	}

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

    public FmsOrganization getFmsOrganization() {
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

	public FmmNodeInfo getFmmNodeSummary(String aNodeIdString) {
		FmmNodeDefinition theFmmNodeDefinition = FmmNodeDefinition.getEntryForNodeIdString(aNodeIdString);
        return (FmmNodeInfo) retrieveFmmNodeFromSimpleIdTable(theFmmNodeDefinition, aNodeIdString);
	}

	public <T extends FmmHeadlineNode> T getHeadlineNode(String aNodeIdString) {
		FmmNodeDefinition theFmmNodeDefinition = FmmNodeDefinition.getEntryForNodeIdString(aNodeIdString);
        return retrieveFmmNodeFromSimpleIdTable(theFmmNodeDefinition, aNodeIdString);
	}

	public void notifyDatabaseListeners() {
		// notify table listeners and row listeners
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - COMMUNITY MEMBER  ////////////////////////////////////////////////////////////////////////////

    public CommunityMember retrieveCommunityMember(String aNodeIdString) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.COMMUNITY_MEMBER, aNodeIdString);
    }

	public Collection<CommunityMember> retrieveCommunityMemberCollection(FmsOrganization anOrganization) {
        return retrieveCommunityMemberCollection(anOrganization, null);
    }

    public Collection<CommunityMember> retrieveCommunityMemberCollection(FmsOrganization anOrganization, CommunityMember aCommunityMemberException) {
		return retrieveFmmNodeListFromLinkTable(
                FmmNodeDefinition.COMMUNITY_MEMBER,
                aCommunityMemberException == null ? null : aCommunityMemberException.getNodeIdString(),
                FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER,
                OrganizationCommunityMemberMetaData.column_ORGANIZATION_ID,
                anOrganization.getNodeIdString(),
                sort_spec__HEADLINE);
	}

	public boolean insertCommunityMember(CommunityMember aCommunityMember, boolean bAtomicTransaction) {
		return fractalInsertFmmGovernableNode(aCommunityMember, bAtomicTransaction);
	}

	public boolean deleteCommunityMember(CommunityMember aCommunityMember, boolean bAtomicTransaction) {
		return fractalDeleteGovernableNode(aCommunityMember, bAtomicTransaction);
	}

	public boolean updateCommunityMember(CommunityMember aCommunityMember, boolean bAtomicTransaction) {
        return fractalUpdateFmmGovernableNode(aCommunityMember, bAtomicTransaction);
	}

	public boolean existsCommunityMember(String aNodeIdString) {
		return existsSimpleIdTable(FmmNodeDefinition.COMMUNITY_MEMBER, aNodeIdString);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - HEADLINE_NODE_TRASH  ////////////////////////////////////////////////////////////////////////////////

    public HeadlineNodeTrash retrieveCompletionNodeTrash(String aNodeIdString) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.HEADLINE_NODE_TRASH, aNodeIdString);
    }

	public Collection<HeadlineNodeTrash> retrieveHeadlineNodeTrashCollection() {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.HEADLINE_NODE_TRASH);
	}

	private boolean createCompletionNodeTrash(FmmCompletionNode aCompletionNode) {
		HeadlineNodeTrash theHeadlineNodeTrash = new HeadlineNodeTrash(aCompletionNode);
		return insertCompletionNodeTrash(theHeadlineNodeTrash, false);
	}

    public boolean insertCompletionNodeTrash(HeadlineNodeTrash aHeadlineNodeTrash, boolean bAtomicTransaction) {
        return insertSimpleIdTable(aHeadlineNodeTrash, bAtomicTransaction);
    }

	public boolean deleteCompletionNodeTrash(HeadlineNodeTrash aHeadlineNodeTrash, boolean bAtomicTransaction) {
        return deleteRowFromSimpleIdTable(aHeadlineNodeTrash, bAtomicTransaction);
	}


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - BOOKSHELF  ////////////////////////////////////////////////////////////////////////////////

    public Bookshelf retrieveBookshelf(String aBookshelfId) {
        return (Bookshelf) retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.BOOKSHELF, aBookshelfId);
    }

    public ArrayList<Bookshelf> retrieveBookshelfList(FmsOrganization anOrganization) {
        return retrieveBookshelfList(anOrganization, null);
    }

    public ArrayList<Bookshelf> retrieveBookshelfList(FmsOrganization anOrganization, Bookshelf aBookshelfException) {
        return retrieveBookshelfListForOrganization(
                anOrganization.getNodeIdString(),
                aBookshelfException == null ? null : aBookshelfException.getNodeIdString());
    }

    public ArrayList<Bookshelf> retrieveBookshelfListForOrganization(String anOrganizationId, String aBookshelfExceptionId) {
        return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.BOOKSHELF,
                BookshelfMetaData.column_ORGANIZATION_ID,
                anOrganizationId,
                aBookshelfExceptionId,
                sort_spec__HEADLINE);
    }

    public ArrayList<Bookshelf> retrieveBookshelfList(Notebook aNotebook) {
        return retrieveBookshelfList(aNotebook, null);
    }

    public ArrayList<Bookshelf> retrieveBookshelfList(Notebook aNotebook, Bookshelf aBookshelfException) {
        return retrieveBookshelfListForNotebook(aNotebook.getNodeIdString(), aBookshelfException == null ? null : aBookshelfException.getNodeIdString());
    }

    public ArrayList<Bookshelf> retrieveBookshelfListForNotebook(String aNotebookId, String aBookshelfExceptionId) {
        return retrieveFmmNodeListFromLinkTable(
                FmmNodeDefinition.BOOKSHELF,
                aBookshelfExceptionId,
                FmmNodeDefinition.BOOKSHELF_LINK_TO_NOTEBOOK,
                BookshelfLinkToNotebookMetaData.column_NOTEBOOK_ID,
                aNotebookId,
                sort_spec__HEADLINE);
    }

    public Bookshelf createBookshelf(String aHeadline) {
        Bookshelf theBookshelf = new Bookshelf(
                new NodeId(FmmNodeDefinition.BOOKSHELF),
                aHeadline,
                FmmDatabaseMediator.getActiveMediator().getFmsOrganization() );
        boolean isSuccess = insertBookshelf(theBookshelf, true);
        return isSuccess ? theBookshelf : null;
    }

    public boolean insertBookshelf(Bookshelf aBookshelf, boolean bAtomicTransaction) {
        return fractalInsertFmmGovernableNode(aBookshelf, bAtomicTransaction);
    }

    public boolean updateBookshelf(Bookshelf aBookshelf, boolean bAtomicTransaction) {
        return fractalUpdateFmmGovernableNode(aBookshelf, bAtomicTransaction);
    }

    public boolean existsBookshelf(String aNodeIdString) {
        return existsSimpleIdTable(FmmNodeDefinition.BOOKSHELF, aNodeIdString);
    }
    
    public boolean deleteBookshelf(Bookshelf aBookshelf, boolean bAtomicTransaction) {
        return fractalDeleteGovernableNode(aBookshelf, bAtomicTransaction);
    }

    public boolean deleteNotebooksForBookshelf(String aBookshelfId, boolean bAtomicTransaction) {
        ArrayList<String> theNotebookIdList = getFmmNodeIdList(FmmNodeDefinition.NOTEBOOK, BookshelfLinkToNotebookMetaData.column_BOOKSHELF_ID, aBookshelfId);
        if(theNotebookIdList.size() < 1) {
            return true;
        }
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = true;
        Notebook theNotebook;
        for(String theNotebookId : theNotebookIdList) {
            boolean hasOtherReferences = false;  //TODO - only if not referenced by another Bookshelf
            if(! hasOtherReferences) {
                theNotebook = retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NOTEBOOK, theNotebookId);
                isSuccess &= fractalDeleteCompletableNode(theNotebook, false);
            }
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    // TODO - create generalized routine
    public boolean moveAllNotebooksIntoBookshelf(String aCurrentBookshelfId, String aTargetBookshelfId, boolean bAtomicTransaction) {
        return false;
    }

    // TODO - create generalized routine
    public boolean copyAllNotebooksIntoBookshelf(String aCurrentBookshelfId, String aTargetBookshelfId, boolean bAtomicTransaction) {
        return false;
    }

    // TODO - create generalized routine
    public boolean adoptNotebookIntoBookshelf(
            String aNotebookId, String aBookshelfId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        return false;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - BOOKSHELF LINK TO NOTEBOOK  //////////////////////////////////////////////////////////////////

    private boolean createBookshelfLinkToNotebook(String aBookshelfId, String aNotebookId, boolean bAtomicTransaction) {
        BookshelfLinkToNotebook theLink = new BookshelfLinkToNotebook(aBookshelfId, aNotebookId);
        return insertSimpleIdTable(theLink, bAtomicTransaction);
    }
    
    public boolean deleteAllBookshelfLinks(String aBookshelfId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = deleteRowFromSimpleIdTable(
                FmmNodeDefinition.BOOKSHELF_LINK_TO_NOTEBOOK,
                BookshelfLinkToNotebookMetaData.column_BOOKSHELF_ID,
                aBookshelfId,
                bAtomicTransaction );
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - NOTEBOOK  ////////////////////////////////////////////////////////////////////////////////////

    public Notebook retrieveNotebook(String aNotebookId) {
        return (Notebook) retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NOTEBOOK, aNotebookId);
    }
    
    public ArrayList<Notebook> retrieveNotebookList(Bookshelf aBookshelf) {
        return retrieveNotebookList(aBookshelf, null);
    }

    public ArrayList<Notebook> retrieveNotebookList(Bookshelf aBookshelf, Notebook aNotebookException) {
        return retrieveNotebookListForBookshelf(aBookshelf.getNodeIdString(), aNotebookException == null ? null : aNotebookException.getNodeIdString());
    }

    public ArrayList<Notebook> retrieveNotebookListForBookshelf(String aBookshelfId, String aNotebookExceptionId) {
        return retrieveFmmNodeListFromLinkTable(
                FmmNodeDefinition.NOTEBOOK,
                aNotebookExceptionId,
                FmmNodeDefinition.BOOKSHELF_LINK_TO_NOTEBOOK,
                BookshelfLinkToNotebookMetaData.column_NOTEBOOK_ID,
                FmmNodeDefinition.BOOKSHELF_LINK_TO_NOTEBOOK.getTableName() + "." + BookshelfLinkToNotebookMetaData.column_BOOKSHELF_ID,
                aBookshelfId,
                sort_spec__HEADLINE);
    }

    public ArrayList<Notebook> retrieveNotebookList(DiscussionTopic aDiscussionTopic) {
        return retrieveNotebookList(aDiscussionTopic, null);
    }

    public ArrayList<Notebook> retrieveNotebookList(DiscussionTopic aDiscussionTopic, Notebook aNotebookException) {
        return retrieveNotebookListForDiscussionTopic(aDiscussionTopic.getNodeIdString(), aNotebookException == null ? null : aNotebookException.getNodeIdString());
    }

    public ArrayList<Notebook> retrieveNotebookListForDiscussionTopic(String aDiscussionTopicId, String aNotebookExceptionId) {
        return retrieveFmmNodeListFromLinkTable(
                FmmNodeDefinition.NOTEBOOK,
                aNotebookExceptionId,
                FmmNodeDefinition.NOTEBOOK_LINK_TO_DISCUSSION_TOPIC,
                NotebookLinkToDiscussionTopicMetaData.column_DISCUSSION_TOPIC_ID,
                aDiscussionTopicId,
                sort_spec__HEADLINE);
    }
    
    private Notebook createNotebookForBookshelf(
            String aNotebookHeadline,
            FmmHeadlineNode aParentNode) {
        startTransaction();
        Notebook theNewNotebook = new Notebook(
                new NodeId(FmmNodeDefinition.NOTEBOOK),
                aNotebookHeadline );
        boolean isSuccess = insertNotebook(theNewNotebook, false);
        isSuccess &= createBookshelfLinkToNotebook(aParentNode.getNodeIdString(), theNewNotebook.getNodeIdString(), false);
        endTransaction(isSuccess);
        return isSuccess ? theNewNotebook : null;
    }

    private Notebook createNotebook(String aNotebookHeadline) {
        Notebook theNewNotebook = new Notebook(
                new NodeId(FmmNodeDefinition.NOTEBOOK),
                aNotebookHeadline );
        boolean isSuccess = insertNotebook(theNewNotebook, true);
        return isSuccess ? theNewNotebook : null;
    }
    
    public boolean insertNotebook(Notebook aNotebook, boolean bAtomicTransaction) {
        return fractalInsertFmmCompletionNode(aNotebook, bAtomicTransaction);
    }

    public boolean updateNotebook(Notebook aNotebook, boolean bAtomicTransaction) {
        return fractalUpdateCompletableNode(aNotebook, bAtomicTransaction);
    }

    public boolean existsNotebook(String aNodeIdString) {
        return existsSimpleIdTable(FmmNodeDefinition.NOTEBOOK, aNodeIdString);
    }

    public boolean deleteNotebook(Notebook aNotebook, boolean bAtomicTransaction) {
        return fractalDeleteCompletableNode(aNotebook, bAtomicTransaction);
    }

    public boolean deleteDiscussionTopicsForNotebook(String aNotebookId, boolean bAtomicTransaction) {
        ArrayList<String> theDiscussionTopicIdList = getFmmNodeIdList(FmmNodeDefinition.DISCUSSION_TOPIC, NotebookLinkToDiscussionTopicMetaData.column_NOTEBOOK_ID, aNotebookId);
        if(theDiscussionTopicIdList.size() < 1) {
            return true;
        }
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = true;
        DiscussionTopic theDiscussionTopic;
        for(String theDiscussionTopicId : theDiscussionTopicIdList) {
            boolean hasOtherReferences = false;  //TODO - only if not referenced by another Notebook
            if(! hasOtherReferences) {
                theDiscussionTopic = retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.DISCUSSION_TOPIC, theDiscussionTopicId);
                isSuccess &= fractalDeleteCompletableNode(theDiscussionTopic, false);
            }
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    // TODO - create generalized routine
    public boolean moveAllDiscussionTopicsIntoNotebook(String aCurrentNotebookId, String aTargetNotebookId, boolean bAtomicTransaction) {
        return false;
    }

    // TODO - create generalized routine
    public boolean copyAllDiscussionTopicsIntoNotebook(String aCurrentNotebookId, String aTargetNotebookId, boolean bAtomicTransaction) {
        return false;
    }

    // TODO - create generalized routine
    public boolean adoptDiscussionTopicIntoNotebook(
            String aDiscussionTopicId, String aNotebookId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        return false;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - NOTEBOOK LINK TO DISCUSSION TOPIC  ///////////////////////////////////////////////////////////

    private boolean createNotebookLinkToDiscussionTopic(FmmHeadlineNode aParentNotebook, DiscussionTopic aNewDiscussionTopic, FmmHeadlineNode aPeerNode, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        int theNewSequenceNumber = initializeNewSequenceNumberForLinkTable(
                FmmNodeDefinition.NOTEBOOK_LINK_TO_DISCUSSION_TOPIC,
                NotebookLinkToDiscussionTopicMetaData.column_NOTEBOOK_ID,
                aParentNotebook.getNodeIdString(),
                NotebookLinkToDiscussionTopicMetaData.column_DISCUSSION_TOPIC_ID,
                aPeerNode == null ? null : aPeerNode.getNodeIdString(),
                bSequenceAtEnd);
        NotebookLinkToDiscussionTopic theNewNotebookLinkToDiscussionTopic = new NotebookLinkToDiscussionTopic(
                aParentNotebook.getNodeIdString(), aNewDiscussionTopic.getNodeIdString(), theNewSequenceNumber );
        return insertSimpleIdTable(theNewNotebookLinkToDiscussionTopic, bAtomicTransaction);
    }

    public boolean deleteAllNotebookLinks(String aNotebookId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = deleteRowFromSimpleIdTable(
                FmmNodeDefinition.NOTEBOOK_LINK_TO_DISCUSSION_TOPIC,
                NotebookLinkToDiscussionTopicMetaData.column_NOTEBOOK_ID,
                aNotebookId,
                bAtomicTransaction );
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - DISCUSSION TOPIC  ////////////////////////////////////////////////////////////////////////////

    public DiscussionTopic retrieveDiscussionTopic(String aDiscussionTopicId) {
        return (DiscussionTopic) retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.DISCUSSION_TOPIC, aDiscussionTopicId);
    }

    public ArrayList<DiscussionTopic> retrieveDiscussionTopicList(Notebook aNotebook) {
        return retrieveDiscussionTopicList(aNotebook, null);
    }

    public ArrayList<DiscussionTopic> retrieveDiscussionTopicList(Notebook aNotebook, DiscussionTopic aDiscussionTopicException) {
        return retrieveDiscussionTopicListForNotebook(aNotebook.getNodeIdString(), aDiscussionTopicException == null ? null : aDiscussionTopicException.getNodeIdString());
    }

    public ArrayList<DiscussionTopic> retrieveDiscussionTopicListForNotebook(String aNotebookId, String aDiscussionTopicExceptionId) {
        return retrieveFmmNodeListFromLinkTable(  // TODO - sortSpec
                FmmNodeDefinition.DISCUSSION_TOPIC,
                aDiscussionTopicExceptionId,
                FmmNodeDefinition.NOTEBOOK_LINK_TO_DISCUSSION_TOPIC,
                NotebookLinkToDiscussionTopicMetaData.column_DISCUSSION_TOPIC_ID,
                FmmNodeDefinition.NOTEBOOK_LINK_TO_DISCUSSION_TOPIC.getTableName() + "." + NotebookLinkToDiscussionTopicMetaData.column_NOTEBOOK_ID,
                aNotebookId,
                sort_spec__SEQUENCE);
    }

    public ArrayList<DiscussionTopic> retrieveDiscussionTopicListForHeadlineNode(String aHeadlineNodeId, String aDiscussionTopicExceptionId) {
        return retrieveFmmNodeListFromLinkTable(
                FmmNodeDefinition.DISCUSSION_TOPIC,
                aDiscussionTopicExceptionId,
                FmmNodeDefinition.DISCUSSION_TOPIC_LINK_TO_NODE_FRAG_AUDIT_BLOCK,
                DiscussionTopicLinkToNodeFragAuditBlockMetaData.column_NODE_FRAG_AUDIT_BLOCK_ID,
                aHeadlineNodeId,
                sort_spec__SEARCHABLE_HEADLINE);
    }

    private DiscussionTopic createDiscussionTopicForNotebook(
            String aDiscussionTopicHeadline,
            FmmHeadlineNode aNotebook,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd) {
        startTransaction();
        DiscussionTopic theDiscussionTopic = new DiscussionTopic(
                new NodeId(FmmNodeDefinition.DISCUSSION_TOPIC),
                aDiscussionTopicHeadline );
        boolean isSuccess = insertDiscussionTopic(theDiscussionTopic, true);
        isSuccess &= createNotebookLinkToDiscussionTopic(aNotebook, theDiscussionTopic, aPeerNode, bSequenceAtEnd, false);
        isSuccess &= insertNodeFragTribKnQuality(theDiscussionTopic);
        endTransaction(isSuccess);
        return isSuccess ? theDiscussionTopic : null;
    }

    public DiscussionTopic createDiscussionTopic(String aDiscussionTopicHeadline, boolean bAtomicTransaction) {
        DiscussionTopic theNewDiscussionTopic = new DiscussionTopic(
                new NodeId(FmmNodeDefinition.DISCUSSION_TOPIC),
                aDiscussionTopicHeadline );
        boolean isSuccess = insertDiscussionTopic(theNewDiscussionTopic, true);
        return isSuccess ? theNewDiscussionTopic : null;
    }

    public boolean insertDiscussionTopic(DiscussionTopic aDiscussionTopic, boolean bAtomicTransaction) {
        return fractalInsertFmmCompletionNode(aDiscussionTopic, bAtomicTransaction);
    }

    public boolean updateDiscussionTopic(DiscussionTopic aDiscussionTopic, boolean bAtomicTransaction) {
        return fractalUpdateCompletableNode(aDiscussionTopic, bAtomicTransaction);
    }

    public boolean existsDiscussionTopic(String aNodeIdString) {
        return existsSimpleIdTable(FmmNodeDefinition.DISCUSSION_TOPIC, aNodeIdString);
    }

    public boolean deleteDiscussionTopic(DiscussionTopic aDiscussionTopic, boolean bAtomicTransaction) {
        return fractalDeleteCompletableNode(aDiscussionTopic, bAtomicTransaction);
    }

    // TODO - create generalized routine
    public boolean moveAllHeadlineNodesIntoDiscussionTopic(String aCurrentDiscussionTopicId, String aTargetDiscussionTopicId, boolean bAtomicTransaction) {
        return false;
    }

    // TODO - create generalized routine
    public boolean copyAllHeadlineNodesIntoDiscussionTopic(String aCurrentDiscussionTopicId, String aTargetDiscussionTopicId, boolean bAtomicTransaction) {
        return false;
    }

    // TODO - create generalized routine
    public boolean adoptHeadlineNodeIntoDiscussionTopic(
            String aHeadlineNodeId, String aDiscussionTopicId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        return false;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - DISCUSSION TOPIC LINK TO NODE FRAG AUDIT BLOCK  //////////////////////////////////////////////

    private boolean createDiscussionTopicLinkToNodeFragAuditBlock(
            FmmHeadlineNode aParentDiscussionTopic,
            NodeFragAuditBlock aNewNodeFragAuditBlock,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        int theNewSequenceNumber = initializeNewSequenceNumberForLinkTable(
                FmmNodeDefinition.DISCUSSION_TOPIC_LINK_TO_NODE_FRAG_AUDIT_BLOCK,
                DiscussionTopicLinkToNodeFragAuditBlockMetaData.column_DISCUSSION_TOPIC_ID,
                aParentDiscussionTopic.getNodeIdString(),
                DiscussionTopicLinkToNodeFragAuditBlockMetaData.column_NODE_FRAG_AUDIT_BLOCK_ID,
                aPeerNode == null ? null : aPeerNode.getNodeIdString(),
                bSequenceAtEnd);
        DiscussionTopicLinkToNodeFragAuditBlock theNewDiscussionTopicLinkToNodeFragAuditBlock = new DiscussionTopicLinkToNodeFragAuditBlock(
                aParentDiscussionTopic.getNodeIdString(), aNewNodeFragAuditBlock.getNodeIdString(), theNewSequenceNumber );
        return insertSimpleIdTable(theNewDiscussionTopicLinkToNodeFragAuditBlock, bAtomicTransaction);
    }
    
    public ArrayList<DiscussionTopicLinkToNodeFragAuditBlock> retrieveDiscussionTopicLinkToNodeFragAuditBlockList(DiscussionTopic discussionTopic) {
        ArrayList<DiscussionTopicLinkToNodeFragAuditBlock> theList = new ArrayList<DiscussionTopicLinkToNodeFragAuditBlock>();
        return theList;
    }

    public boolean deleteAllDiscussionTopicLinks(String aDiscussionTopicId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = deleteRowFromSimpleIdTable(
                FmmNodeDefinition.DISCUSSION_TOPIC_LINK_TO_NODE_FRAG_AUDIT_BLOCK,
                DiscussionTopicLinkToNodeFragAuditBlockMetaData.column_DISCUSSION_TOPIC_ID,
                aDiscussionTopicId,
                bAtomicTransaction );
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - PORTFOLIO  ///////////////////////////////////////////////////////////////////////////////////

    public Portfolio retrievePortfolio(String aPortfolioId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.PORTFOLIO, aPortfolioId);
    }

	public ArrayList<Portfolio> retrievePortfolioList(FmsOrganization anOrganization) {
		return retrievePortfolioList(anOrganization, null);
	}

    public ArrayList<Portfolio> retrievePortfolioList(FmsOrganization anOrganization, Portfolio aPortfolioException) {
        return retrievePortfolioList(anOrganization.getNodeIdString(), aPortfolioException == null ? null : aPortfolioException.getNodeIdString());
    }

    public ArrayList<Portfolio> retrievePortfolioList(String anOrganizationId, String aPortfolioExceptionId) {
        return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.PORTFOLIO,
                PortfolioMetaData.column_ORGANIZATION_ID,
                anOrganizationId,
                aPortfolioExceptionId,
                sort_spec__HEADLINE);
    }

    public int countPortfolioForProjectMoveTarget(FmsOrganization anFmsOrganization, Portfolio aPortfolioException) {
        return this.persistenceTechnologyDelegate.dbCountPortfolioForProjectMoveTarget(anFmsOrganization, aPortfolioException);
    }

    public ArrayList<? extends GcgGuiable> retrievePortfolioForProjectMoveTarget(FmsOrganization anFmsOrganization, Portfolio aPortfolioException) {
        return this.persistenceTechnologyDelegate.dbListPortfolioForProjectMoveTarget(anFmsOrganization, aPortfolioException);
    }

    public ArrayList<? extends GcgGuiable> retrievePortfolioForWorkAssetMoveTarget(FmsOrganization anFmsOrganization, Project aProjectException) {
        return this.persistenceTechnologyDelegate.dbListPortfolioForWorkAssetMoveTarget(anFmsOrganization, aProjectException);
    }

    public ArrayList<? extends GcgGuiable> retrievePortfolioForWorkPackageMoveTarget(FmsOrganization anFmsOrganization, WorkAsset aWorkAssetException) {
        return this.persistenceTechnologyDelegate.dbListPortfolioForWorkPackageMoveTarget(anFmsOrganization, aWorkAssetException);
    }

    public ArrayList<? extends GcgGuiable> retrievePortfolioForWorkTaskMoveTarget(FmsOrganization anFmsOrganization, WorkPackage aWorkPackageException) {
        return this.persistenceTechnologyDelegate.dbListPortfolioForWorkTaskMoveTarget(anFmsOrganization, aWorkPackageException);
    }

    public Portfolio createPortfolio(String aHeadline) {
        Portfolio theNewPortfolio = new Portfolio(
                new NodeId(FmmNodeDefinition.PORTFOLIO),
                aHeadline,
                FmmDatabaseMediator.getActiveMediator().getFmsOrganization() );
        boolean isSuccess = insertPortfolio(theNewPortfolio, true);
        return isSuccess ? theNewPortfolio : null;
    }

    public boolean insertPortfolio(Portfolio aPortfolio, boolean bAtomicTransaction) {
        return fractalInsertFmmCompletionNode(aPortfolio, bAtomicTransaction);
    }

    public boolean updatePortfolio(Portfolio aPortfolio, boolean bAtomicTransaction) {
        return fractalUpdateCompletableNode(aPortfolio, bAtomicTransaction);
    }

    public boolean existsPortfolio(String aNodeIdString) {
        return existsSimpleIdTable(FmmNodeDefinition.PORTFOLIO, aNodeIdString);
    }

    public boolean deletePortfolio(Portfolio aPortfolio, boolean bAtomicTransaction) {
        return fractalDeleteCompletableNode(aPortfolio, bAtomicTransaction);
    }

    public boolean deleteProjectsForPortfolio(String aPortfolioId, boolean bAtomicTransaction) {
        ArrayList<String> theProjectIdList = getFmmNodeIdList(FmmNodeDefinition.PROJECT, ProjectMetaData.column_PORTFOLIO_ID, aPortfolioId);
        if(theProjectIdList.size() < 1) {
            return true;
        }
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = true;
        Project theProject;
        for(String theNodeIdString : theProjectIdList) {
            theProject = retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.PROJECT, theNodeIdString);
            isSuccess &= fractalDeleteCompletableNode(theProject, false);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    // TODO - create generalized routine
    public boolean moveAllProjectsIntoPortfolio(String aCurrentPortfolioId, String aTargetPortfolioId, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbMoveAllProjectsIntoPortfolio(aCurrentPortfolioId, aTargetPortfolioId, bAtomicTransaction);
    }

    // TODO - create generalized routine
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
    
    public Project retrieveProject(String aProjectId) {
        return (Project) retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.PROJECT, aProjectId);
    }

    public ArrayList<Project> retrieveProjectList(Portfolio aPortfolio) {
        return retrieveProjectList(aPortfolio, null);
    }

    public ArrayList<Project> retrieveProjectList(Portfolio aPortfolio, Project aProjectException) {
        return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.PROJECT,
                ProjectMetaData.column_PORTFOLIO_ID,
                aPortfolio.getNodeIdString(),
                aProjectException.getNodeIdString(),
                sort_spec__HEADLINE);
    }

    public int countProjectsForProjectAssetMoveTarget(Portfolio aPortfolio, Project aProjectException) {
        return this.persistenceTechnologyDelegate.dbCountProjectsForProjectAssetMoveTarget(aPortfolio, aProjectException);
    }

    public ArrayList<Project> retrieveProjectsForProjectAssetMoveTarget(Portfolio aPortfolio, Project aProjectException) {
        return this.persistenceTechnologyDelegate.dbListProjectsForProjectAssetMoveTarget(aPortfolio, aProjectException);
    }

    public ArrayList<Project> retrieveProjectsForWorkPackageMoveTarget(Portfolio aPortfolio, WorkAsset aWorkAssetException) {
        return this.persistenceTechnologyDelegate.dbListProjectsForWorkPackageMoveTarget(aPortfolio, aWorkAssetException);
    }

    public ArrayList<Project> retrieveProjectOrphansFromPortfolio() {
        return this.persistenceTechnologyDelegate.dbListProjectOrphansFromPortfolio();
    }

    public ArrayList<Project> retrieveProjectsForWorkTaskMoveTarget(Portfolio aPortfolio, WorkPackage aWorkPackageException) {
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
        return fractalInsertFmmCompletionNode(aProjectAsset, bAtomicTransaction);
    }

    private ProjectAsset newProjectAssetForProject(
            String aHeadline,
            FmmHeadlineNode aProjectNode,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd ) {
        startTransaction();
        ProjectAsset theNewProjectAsset = new ProjectAsset();
        theNewProjectAsset.setHeadline(aHeadline);
        theNewProjectAsset.setProjectId(aProjectNode.getNodeIdString());
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                FmmNodeDefinition.PROJECT_ASSET,
                ProjectAssetMetaData.column_PROJECT_ID,
                aProjectNode,
                aPeerNode,
                bSequenceAtEnd );
        theNewProjectAsset.setSequence(theNewSequenceNumber);
        boolean isSuccess = newProjectAsset(theNewProjectAsset, true);
        endTransaction(isSuccess);
        return theNewProjectAsset;
    }

    public boolean updateProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction) {
        return updateSimpleIdTable(aProjectAsset, bAtomicTransaction);
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

    public boolean orphanSingleStrategicAssetFromProject(String aStrategicAssetId, String aProjectId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanSingleStrategicAssetFromProject(aStrategicAssetId, aProjectId, bAtomicTransaction);
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
//        return updateSimpleIdTable(aProjectAsset, ProjectAssetDaoSqLite.getInstance(), bAtomicTransaction);
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
        return updateSimpleIdTable(anOrphanNode, bAtomicTransaction);
    }

    public boolean adoptPrimaryOrphanIntoParentAlphaSort(FmmCompletionNode anOrphanNode, FmmCompletionNode aParentNode, boolean bAtomicTransaction) {
        anOrphanNode.setPrimaryParentId(aParentNode.getNodeIdString());
        return updateSimpleIdTable(anOrphanNode, bAtomicTransaction);
    }

    public boolean adoptPrimaryLinkOrphanIntoParent(FmmCompletionNode anOrphanNode, FmmCompletionNode aParentNode, FmmCompletionNode aPeerNode, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        boolean theResult = newStrategicCommitment(aParentNode, aPeerNode, bSequenceAtEnd, (StrategicAsset) anOrphanNode);
        ((StrategicAsset) anOrphanNode).setStrategic(true);
        return updateSimpleIdTable(anOrphanNode, bAtomicTransaction);
    }

    public boolean adoptProjectAssetIntoStrategicMilestone(FmmCompletionNode anOrphanNode, FmmCompletionNode aParentNode, FmmCompletionNode aPeerNode, boolean bSequenceAtEnd) {
        boolean theResult = newStrategicCommitment(aParentNode, aPeerNode, bSequenceAtEnd, (WorkAsset) anOrphanNode);
        ((WorkAsset) anOrphanNode).setStrategic(true);
        return theResult && updateSimpleIdTable(anOrphanNode, true);
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
        boolean isSuccess = insertSimpleIdTable(theNewStrategicCommitment, bAtomicTransaction);
        // isSuccess += UPDATE TribKn
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean deleteProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction) {
        return fractalDeleteCompletableNode(aProjectAsset, bAtomicTransaction);
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
        return fractalInsertFmmCompletionNode(aStrategicAsset, bAtomicTransaction);
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
        endTransaction(isSuccess);
        return theNewStrategicAsset;
    }

    private boolean newStrategicCommitment(FmmHeadlineNode aParentNode, FmmHeadlineNode aPeerNode, boolean bSequenceAtEnd, WorkAsset theNewStrategicAsset) {
        // TODO - need to update TribKn
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
        return insertSimpleIdTable(theNewStrategicCommitment, false);
    }

    public boolean updateStrategicAsset(StrategicAsset aStrategicAsset, boolean bAtomicTransaction) {
        return updateSimpleIdTable(aStrategicAsset, bAtomicTransaction);
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
        boolean isSuccess = insertSimpleIdTable(theNewStrategicCommitment, false);
        // isSuccess += UPDATE TribKn
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean demoteStrategicAssetToProjectAsset(
            StrategicAsset aStrategicAsset,
            boolean bAtomicTransaction ) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        aStrategicAsset.setStrategic(false);
        boolean isSuccess = updateSimpleIdTable(aStrategicAsset,false);
        isSuccess &= this.persistenceTechnologyDelegate.dbDeleteStrategicCommitment(aStrategicAsset.getNodeIdString(), false);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean deleteStrategicAsset(StrategicAsset aStrategicAsset, boolean bAtomicTransaction) {
        return fractalDeleteCompletableNode(aStrategicAsset, bAtomicTransaction);
    }

    public boolean moveSingleStrategicAssetIntoProject(
            String aStrategicAssetId,
            String anOriginalProjectId,
            String aDestinationProjectId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbMoveSingleStrategicAssetIntoProject(
                aStrategicAssetId,
                anOriginalProjectId,
                aDestinationProjectId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FISCAL YEAR  ////////////////////////////////////////////////////////////////////////////////

    public ArrayList<FiscalYear> getFiscalYearList(FmsOrganization anOrganization) {
        return getFiscalYearList(anOrganization, null);
    }

	public ArrayList<FiscalYear> getFiscalYearList(FmsOrganization anOrganization, FiscalYear aFiscalYearException) {
		return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.FISCAL_YEAR,
                FiscalYearMetaData.column_ORGANIZATION_ID,
                anOrganization.getNodeIdString(),
                aFiscalYearException == null ? null : aFiscalYearException.getNodeIdString(),
                FiscalYearMetaData.column_YEAR_NUMBER);
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

	public int countFiscalYearForCadenceMoveTarget(FmsOrganization anFmsOrganization, FiscalYear aFiscalYearTargetException) {
		return this.persistenceTechnologyDelegate.dbCountFiscalYearForCadenceMoveTarget(anFmsOrganization, aFiscalYearTargetException);
	}

	public ArrayList<FiscalYear> listFiscalYearForCadenceMoveTarget(FmsOrganization anFmsOrganization, FiscalYear aFiscalYearTargetException) {
		return this.persistenceTechnologyDelegate.dbListFiscalYearForCadenceMoveTarget(anFmsOrganization, aFiscalYearTargetException);
	}

	public FiscalYear getFiscalYear(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveFiscalYear(aNodeIdString);
	}
	
	public FiscalYear createFiscalYear(String aYearString) {
		FiscalYear theFiscalYear = new FiscalYear(
				new NodeId(FmmNodeDefinition.FISCAL_YEAR),
				FmmDatabaseMediator.getActiveMediator().getFmmOwner(),
				aYearString);
		newFiscalYear(theFiscalYear, true);
        return theFiscalYear;
	}

	public boolean newFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
		return fractalInsertFmmGovernableNode(aFiscalYear, bAtomicTransaction);
	}

	public boolean deleteFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
		return fractalDeleteGovernableNode(aFiscalYear, bAtomicTransaction);
	}

	public boolean updateFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
		return updateSimpleIdTable(aFiscalYear, bAtomicTransaction);
	}

	public boolean existsFiscalYear(String aNodeIdString) {
		return getFiscalYear(aNodeIdString) != null;
	}


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FLYWHEEL CADENCE  ///////////////////////////////////////////////////////////////////

    public ArrayList<Cadence> getCadenceList(FiscalYear aFiscalYear) {
        return this.persistenceTechnologyDelegate.dbGetCadenceList(aFiscalYear);
    }

    public ArrayList<Cadence> getCadenceListForFiscalYear(String aFiscalYearId) {
        return this.persistenceTechnologyDelegate.dbGetCadenceListForFiscalYear(aFiscalYearId);
    }

    public Cadence retrieveCadence(String aNodeIdString) {
        return this.persistenceTechnologyDelegate.dbRetrieveCadence(aNodeIdString);
    }

    public boolean insertCadence(Cadence aCadence, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbInsertCadence(aCadence, bAtomicTransaction);
    }

    public boolean insertCadenceList(ArrayList<Cadence> aCadenceList, boolean bAtomicTransaction) {
        boolean isSuccess = false;
        if(bAtomicTransaction) {
            startTransaction();
        }
        for(Cadence theCadence : aCadenceList) {
            isSuccess = insertCadence(theCadence, false);
            isSuccess &= insertWorkPlanList(theCadence.getWorkPlanList(), false);
            if(! isSuccess) {
                break;
            }
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean updateCadence(Cadence aCadence, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.dbUpdateCadence(aCadence, bAtomicTransaction);
    }

    public boolean deleteCadence(Cadence aCadence, boolean bAtomicTransaction) {
        return fractalDeleteCompletableNode(aCadence, bAtomicTransaction);
    }

    public boolean deleteAllCadences(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
        return deleteAllCadencesForFiscalYear(aFiscalYear.getNodeIdString(), bAtomicTransaction);
    }

    public boolean deleteAllCadencesForFiscalYear(String aFiscalYearId, boolean bAtomicTransaction) {
        ArrayList<String> theCadenceIdList = getFmmNodeIdList(FmmNodeDefinition.CADENCE, CadenceMetaData.column_FISCAL_YEAR_ID, aFiscalYearId);
        if(theCadenceIdList.size() < 1) {
            return true;
        }
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = true;
        Cadence theCadence;
        for(String theNodeIdString : theCadenceIdList) {
            theCadence = retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.CADENCE, theNodeIdString);
            isSuccess &= deleteAllWorkPlans(theCadence, bAtomicTransaction);
            isSuccess &= fractalDeleteCompletableNode(theCadence, false);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - WORK PLAN  ///////////////////////////////////////////////////////////////////////////////////

    public ArrayList<WorkPlan> getWorkPlanList(Cadence aCadence) {
        return getWorkPlanListForCadence(aCadence.getNodeIdString());
    }

    public ArrayList<WorkPlan> getWorkPlanListForCadence(String aCadenceId) {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.WORK_PLAN, WorkPlanMetaData.column_CADENCE_ID, aCadenceId, null, WorkPlanMetaData.column_SEQUENCE);
    }

    public WorkPlan retrieveWorkPlan(String aNodeIdString) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.WORK_PLAN, aNodeIdString);
    }

    public boolean insertWorkPlan(WorkPlan aWorkPlan, boolean bAtomicTransaction) {
        return fractalInsertFmmCompletionNode(aWorkPlan, bAtomicTransaction);
    }

    public boolean insertWorkPlanList(ArrayList<WorkPlan> aWorkPlanList, boolean bAtomicTransaction) {
        boolean isSuccess = true;
        if(bAtomicTransaction) {
            startTransaction();
        }
        for(WorkPlan theWorkPlan : aWorkPlanList) {
            insertWorkPlan(theWorkPlan, false);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean updateWorkPlan(WorkPlan aWorkPlan, boolean bAtomicTransaction) {
        return updateSimpleIdTable(aWorkPlan, bAtomicTransaction);
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

    public boolean deleteAllWorkPlans(Cadence aCadence, boolean bAtomicTransaction) {
        ArrayList<String> theWorkPlanIdList = getFmmNodeIdList(FmmNodeDefinition.WORK_PLAN, WorkPlanMetaData.column_CADENCE_ID, aCadence.getNodeIdString());
        if(theWorkPlanIdList.size() < 1) {
            return true;
        }
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = true;
        WorkPlan theWorkPlan;
        for(String theNodeIdString : theWorkPlanIdList) {
            theWorkPlan = retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.WORK_PLAN, theNodeIdString);
            isSuccess &= fractalDeleteCompletableNode(theWorkPlan, false);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FISCAL YEAR HOLIDAY BREAK  ///////////////////////////////////////////////////////////////////

    public ArrayList<FiscalYearHolidayBreak> getFiscalYearHolidayBreakList(FiscalYear aFiscalYear) {
        return getFiscalYearHolidayBreakList(aFiscalYear.getNodeIdString());
    }

    public ArrayList<FiscalYearHolidayBreak> getFiscalYearHolidayBreakList(String aFiscalYearId) {
        return this.persistenceTechnologyDelegate.dbGetFiscalYearHolidayBreakList(aFiscalYearId);
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
		return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.FLYWHEEL_TEAM, aNodeIdString);
	}

	public boolean newFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction) {
		return fractalInsertFmmGovernableNode(aFlywheelTeam, bAtomicTransaction);
	}

	public boolean updateFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction) {
		return this.persistenceTechnologyDelegate.dbUpdateFlywheelTeam(aFlywheelTeam, bAtomicTransaction);
	}

	public boolean deleteFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction) {
		return fractalDeleteGovernableNode(aFlywheelTeam, bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FRAG LOCK  ////////////////////////////////////////////////////////////////////////

	public Collection<FragLock> getFragLockCollection() {
		return this.persistenceTechnologyDelegate.dbRetrieveFragLockList();
	}

	public FragLock getFragLockForParent(String aParentId) {
		return this.persistenceTechnologyDelegate.dbRetrieveFragLockForParent(aParentId);
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
		return (NodeFragAuditBlock) this.persistenceTechnologyDelegate.dbRetrieveFmmNodeFragForParent(aParentId, FmmNodeDefinition.NODE_FRAG__AUDIT_BLOCK);
	}

    public ArrayList<NodeFragAuditBlock> listNodeFragAuditBlock(DiscussionTopic aDiscussionTopic) {
        ArrayList<NodeFragAuditBlock> theList = new ArrayList<NodeFragAuditBlock>();
        return theList;
    }

	public NodeFragAuditBlock retrieveNodeFragAuditBlock(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragAuditBlock(aNodeIdString);
	}

	private boolean deleteNodeFragAuditBlockForParent(FmmHeadlineNode aHeadlineNode, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = deleteRowFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__AUDIT_BLOCK, NodeFragMetaData.column_PARENT_ID, aHeadlineNode.getNodeIdString(), bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG COMPLETION  ////////////////////////////////////////////////////////////////////////

	public Collection<NodeFragCompletion> getNodeFragCompletionCollection() {
		return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__COMPLETION);
	}

    public NodeFragCompletion getNodeFragCompletionForParent(String aParentId) {
        return (NodeFragCompletion) retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__COMPLETION, NodeFragCompletionMetaData.column_PARENT_ID, aParentId);
    }

	private boolean deleteNodeFragCompletionForParent(FmmCompletionNode aCompletionNode, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
        boolean isSuccess = deleteRowFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__COMPLETION, NodeFragMetaData.column_PARENT_ID, aCompletionNode.getNodeIdString(), bAtomicTransaction);
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

    public NodeFragFseDocument getNodeFragFseDocumentForParent(String aParentId) {
        return (NodeFragFseDocument) this.persistenceTechnologyDelegate.dbRetrieveFmmNodeFragForParent(aParentId, FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT);
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

	public void updateFseDocument(FseDocument theFseDocument, boolean bAtomicTransaction) {
		NodeFragFseDocument theNodeFragFseDocument = getNodeFragFseDocumentForDocumentId(theFseDocument.getDocumentId());
		theNodeFragFseDocument.setFseDocument(theFseDocument);
		updateNodeFragFseDocument(theNodeFragFseDocument, bAtomicTransaction);
	}

	public void updateNodeFragFseDocument(NodeFragFseDocument aNodeFragFseDocument, boolean bAtomicTransaction) {
		updateSimpleIdTable(aNodeFragFseDocument, bAtomicTransaction);
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
        boolean isSuccess = deleteRowFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT, NodeFragMetaData.column_PARENT_ID, aHeadlineNode.getNodeIdString(), bAtomicTransaction);
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
        return (NodeFragGovernance) this.persistenceTechnologyDelegate.dbRetrieveFmmNodeFragForParent(aParentId, FmmNodeDefinition.NODE_FRAG__GOVERNANCE);
    }

	public NodeFragGovernance getNodeFragGovernance(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragGovernance(aNodeIdString);
	}

	private boolean deleteNodeFragGovernanceForParent(FmmGovernableNode aGovernableNode, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = deleteRowFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__GOVERNANCE, NodeFragMetaData.column_PARENT_ID, aGovernableNode.getNodeIdString(), bAtomicTransaction);
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
        return (NodeFragTribKnQuality) this.persistenceTechnologyDelegate.dbRetrieveFmmNodeFragForParent(aParentId, FmmNodeDefinition.NODE_FRAG__TRIBKN_QUALITY);
    }

	public NodeFragTribKnQuality getNodeFragTribKnQuality(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragTribKnQuality(aNodeIdString);
	}

	private boolean deleteNodeFragTribKnQualityForParent(FmmHeadlineNode aHeadlineNode, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
        boolean isSuccess = deleteRowFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__TRIBKN_QUALITY, NodeFragMetaData.column_PARENT_ID, aHeadlineNode.getNodeIdString(), bAtomicTransaction);
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
        return (NodeFragWorkTaskBudget) this.persistenceTechnologyDelegate.dbRetrieveFmmNodeFragForParent(aParentId, FmmNodeDefinition.NODE_FRAG__WORK_TASK_BUDGET);
    }

	public NodeFragWorkTaskBudget getNodeFragWorkTaskBudget(String aNodeIdString) {
		return this.persistenceTechnologyDelegate.dbRetrieveNodeFragWorkTaskBudget(aNodeIdString);
	}

	private boolean deleteNodeFragWorkTaskBudgetForParent(FmmCompletionNode aCompletionNode, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
        boolean isSuccess = deleteRowFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__WORK_TASK_BUDGET, NodeFragMetaData.column_PARENT_ID, aCompletionNode.getNodeIdString(), bAtomicTransaction);
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
        endTransaction(isSuccess);
        return theNewWorkTask;
    }
    
	public boolean newWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction) {
		return fractalInsertFmmCompletionNode(aWorkTask, bAtomicTransaction);
	}

	public boolean updateWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction) {
		return fractalUpdateCompletableNode(aWorkTask, bAtomicTransaction);
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
		boolean isSuccess = fractalDeleteCompletableNode(aWorkTask, bAtomicTransaction);
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
		boolean isSuccess = fractalInsertFmmGovernableNode(anFmmConfiguration, bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public boolean updateFmmConfiguration(FmmConfiguration aFmmConfiguration, boolean bAtomicTransaction) {
		return this.persistenceTechnologyDelegate.dbUpdateFmmConfiguration(aFmmConfiguration, bAtomicTransaction);
	}

	public boolean deleteFmmConfiguration(FmmConfiguration anFmmConfiguration, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = fractalDeleteGovernableNode(anFmmConfiguration, bAtomicTransaction);
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
		boolean isSuccess = fractalInsertFmmGovernableNode(anFmsOrganization, bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public boolean updateFmsOrganization(FmsOrganization anFmsOrganization, boolean bAtomicTransaction) {
		return this.persistenceTechnologyDelegate.dbUpdateFmsOrganization(anFmsOrganization, bAtomicTransaction);
	}

	public boolean deleteFmsOrganization(FmsOrganization anFmsOrganization, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = fractalDeleteGovernableNode(anFmsOrganization, bAtomicTransaction);
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
		boolean isSuccess = fractalInsertFmmCompletionNode(aProject, bAtomicTransaction);
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
        boolean isSuccess = newProject(theNewProject, true);
        endTransaction(isSuccess);
        return theNewProject;
    }

	public boolean updateProject(Project aProject, boolean bAtomicTransaction) {
		return fractalUpdateCompletableNode(aProject, bAtomicTransaction);
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
		boolean isSuccess = fractalDeleteCompletableNode(aProject, bAtomicTransaction);
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
		boolean isSuccess = newStrategicMilestone(theNewStrategicMilestone, true);
		endTransaction(isSuccess);
		return theNewStrategicMilestone;
	}

	private boolean newStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		boolean isSuccess = fractalInsertFmmCompletionNode(aStrategicMilestone, bAtomicTransaction);
		if(bAtomicTransaction) {
			endTransaction(isSuccess);
		}
		return isSuccess;
	}

	public boolean updateStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
		return fractalUpdateCompletableNode(aStrategicMilestone, bAtomicTransaction);
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
		boolean isSuccess = fractalDeleteCompletableNode(aStrategicMilestone, bAtomicTransaction);
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

	public ArrayList<WorkPackage> listWorkPackageForCadence(String aCadenceId) {
		return this.persistenceTechnologyDelegate.dbListWorkPackageForCadence(aCadenceId);
	}

	public ArrayList<WorkPackage> listWorkPackage(Cadence aCadence) {
		return listWorkPackageForCadence(aCadence.getNodeIdString());
	}

	public ArrayList<WorkPackage> listWorkPackageForWorkTaskMoveTarget(ProjectAsset aProjectAsset, WorkPackage aWorkPackageException) {
		return this.persistenceTechnologyDelegate.dbListWorkPackagesForWorkTaskMoveTarget(
                aProjectAsset.getNodeIdString(), aWorkPackageException.getNodeIdString(), true);
	}

	public ArrayList<WorkPackage> listWorkPackageForWorkTaskMoveTarget(Cadence aCadence, WorkPackage aWorkPackageException) {
		return this.persistenceTechnologyDelegate.dbListWorkPackagesForWorkTaskMoveTarget(
                aCadence.getNodeIdString(), aWorkPackageException.getNodeIdString(), true);
	}

	public ArrayList<WorkPackage> listWorkPackageOrphansFromProjectAsset() {
		return this.persistenceTechnologyDelegate.dbListWorkPackageOrphansFromProjectAsset();
	}

	public ArrayList<WorkPackage> listWorkPackageOrphansFromCadence() {
		return this.persistenceTechnologyDelegate.dbListWorkPackageOrphansFromCadence();
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
		boolean isSuccess = fractalInsertFmmCompletionNode(aWorkPackage, bAtomicTransaction);
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
					newWorkPackageForCadence(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
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
		endTransaction(isSuccess);
		return theNewWorkPackage;
	}

	private WorkPackage newWorkPackageForCadence(String aHeadline, FmmHeadlineNode aParentNode, FmmHeadlineNode aPeerNode, boolean bSequenceBeforeFlag) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateWorkPackage(WorkPackage aWorkPackage, boolean bAtomicTransaction) {
		return fractalUpdateCompletableNode(aWorkPackage, bAtomicTransaction);
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

    public boolean orphanAllWorkPackagesFromCadence(String aCadenceId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanAllWorkPackagesFromCadence(aCadenceId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean orphanSingleWorkPackageFromCadence(String aWorkPackageId, String aCadenceId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = this.persistenceTechnologyDelegate.dbOrphanSingleWorkPackageFromCadence(aWorkPackageId, aCadenceId, bAtomicTransaction);
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

    public boolean moveSingleWorkPackageIntoWorkAsset(
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
		boolean isSuccess = fractalDeleteCompletableNode(aWorkPackage, bAtomicTransaction);
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
