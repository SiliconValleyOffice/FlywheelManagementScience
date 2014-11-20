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

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fmm.meta_data.BookshelfLinkToNotebookMetaData;
import com.flywheelms.library.fmm.meta_data.BookshelfMetaData;
import com.flywheelms.library.fmm.meta_data.CadenceCommitmentMetaData;
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
import com.flywheelms.library.fmm.meta_data.StrategicAssetMetaData;
import com.flywheelms.library.fmm.meta_data.StrategicCommitmentMetaData;
import com.flywheelms.library.fmm.meta_data.StrategicMilestoneMetaData;
import com.flywheelms.library.fmm.meta_data.WorkAssetMetaData;
import com.flywheelms.library.fmm.meta_data.WorkPackageMetaData;
import com.flywheelms.library.fmm.meta_data.WorkPlanMetaData;
import com.flywheelms.library.fmm.meta_data.WorkTaskMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.commitment.CadenceCommitment;
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
import com.flywheelms.library.fmm.node.impl.governable.WorkAsset;
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fmm.node.impl.governable.WorkPlan;
import com.flywheelms.library.fmm.node.impl.governable.WorkTask;
import com.flywheelms.library.fmm.node.impl.headline.FiscalYearHolidayBreak;
import com.flywheelms.library.fmm.node.impl.link.BookshelfLinkToNotebook;
import com.flywheelms.library.fmm.node.impl.link.DiscussionTopicLinkToNodeFragAuditBlock;
import com.flywheelms.library.fmm.node.impl.link.NotebookLinkToDiscussionTopic;
import com.flywheelms.library.fmm.node.impl.link.OrganizationCommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.FmmNodeFragLockableImpl;
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


//////  YES, I KNOW this Class is HUGE.  :-)
// Just temporary until I finish distilling the persistence framework (it is much more efficient
// and accurate to do this in 1 file).
// Most of the code will be moved out into DAOs at the end of the "Science Project" phase.

public class FmmDatabaseService extends Service {

    public static final String sort_spec__HEADLINE = " LOWER (" + HeadlineNodeMetaData.column_HEADLINE + ") ASC";
    public static final String sort_spec__SEARCHABLE_HEADLINE = " LOWER (" + NodeFragAuditBlockMetaData.column_SEARCHABLE_HEADLINE + ") ASC";
    public static final String sort_spec__SEQUENCE = CompletableNodeMetaData.column_SEQUENCE + " ASC";
    public static final String sort_spec__ROW_TIMESTAMP = IdNodeMetaData.column_ROW_TIMESTAMP + " ASC";
    public static final String sort_spec__SCHEDULED_END_DATE = CadenceMetaData.column_SCHEDULED_END_DATE + " ASC";
	private static HashMap<String, FmmDatabaseService> fmmDatabaseMediatorMap = new HashMap<String, FmmDatabaseService>();  // mediator for each database server
	private static FmmDatabaseService activeFmmDatabaseMediator;
	protected static FmmConfiguration requestedFmmConfiguration;  // for creating a new database
	private PersistenceTechnologyDelegate persistenceTechnologyDelegate;
	protected static FmmConfiguration activeFmmConfiguration;
	protected FmmConfiguration fmmConfiguration;
	private FmsOrganization fmmOwner;
	private Date fmmTimestamp = GcgDateHelper.getCurrentDateTime();
	private boolean inTransaction = false;

	protected FmmDatabaseService(FmmConfiguration anFmmConfiguration) {
		initMetaData();
		FmmDatabaseService.requestedFmmConfiguration = FmmDatabaseService.activeFmmConfiguration = this.fmmConfiguration = anFmmConfiguration;
		this.persistenceTechnologyDelegate = this.activeFmmConfiguration.getPersistenceTechnologyDelegate();
		this.persistenceTechnologyDelegate.setActiveDatabase(this.activeFmmConfiguration);
		synchronizeFmmConfigurationRowWithConfigFile(this.activeFmmConfiguration);
		this.fmmOwner = this.persistenceTechnologyDelegate.getFmmOwner();
	}

    private void synchronizeFmmConfigurationRowWithConfigFile(FmmConfiguration fmmConfiguration) {
        // TODO
    }

    public void reOpenDatabase() {
		this.persistenceTechnologyDelegate.setActiveDatabase(this.activeFmmConfiguration);
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
		FmmDatabaseService.getActiveMediator().getPersistenceTechnologyDelegate().startTransaction();
		return true;
	}

	public void endTransaction(boolean bSuccess) {
		this.inTransaction = false;
		FmmDatabaseService.getActiveMediator().getPersistenceTechnologyDelegate().endTransaction(bSuccess);
		FmmDatabaseService.getActiveMediator().notifyDatabaseListeners();
	}

    public void notifyDatabaseListeners() {
        // notify table listeners and row listeners
    }

	public boolean inTransaction() {
		return this.inTransaction;
	}

    ///////////////////////////////////////////
    //////////  ID LIST  //////////////////////

    private ArrayList<String> getFmmNodeIdList(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aColumnValue, String aSortSpec) {
        return this.persistenceTechnologyDelegate.getFmmNodeIdList(anFmmNodeDefinition, aColumnName, aColumnValue, aSortSpec);
    }

    private ArrayList<String> getFmmNodeIdList(FmmNodeDefinition anFmmNodeDefinition, String aWhereClause, String aSortSpec) {
        return this.persistenceTechnologyDelegate.getFmmNodeIdList(anFmmNodeDefinition, aWhereClause, aSortSpec);
    }


    ///////////////////////////////////////////
    ////////  SIMPLE ID TABLE  ////////////////

    //-->>> INSERT & UPDATE

    private <T extends FmmNode> boolean insertSimpleIdTableRow(T anFmmNode, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.insertSimpleIdTable(anFmmNode, bAtomicTransaction);
    }
    
    private <T extends FmmNode> boolean updateSimpleIdTableRow(T anFmmNode, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.updateSimpleIdTable(anFmmNode, bAtomicTransaction);
    }

    private <T extends FmmNode> boolean conditionalUpdateSimpleIdTableRow(Date theOriginalTribKnRowTimestamp, T theFmmNode, boolean bAtomicTransaction) {
        boolean isSuccess = true;
        if(! theOriginalTribKnRowTimestamp.equals(theFmmNode.getRowTimestamp())) {
            isSuccess &= updateSimpleIdTableRow(theFmmNode, bAtomicTransaction);
        }
        return isSuccess;
    }

    //-->>> RETRIEVE

    public ArrayList<FmmHeadlineNode> retrieveFmmHeadlineNodeList(FmmNodeDefinition anFmmNodeDefinition) {
        return this.persistenceTechnologyDelegate.retrieveFmmHeadlineNodeListFromSimpleIdTable(anFmmNodeDefinition);
    }

    public boolean existsSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aNodeIdString) {
        return this.persistenceTechnologyDelegate.existsSimpleIdTable(anFmmNodeDefinition, aNodeIdString);
    }

    public boolean existsSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aWhereColumnName, String aWhereColumnValue) {
        return this.persistenceTechnologyDelegate.existsSimpleIdTable(anFmmNodeDefinition, aWhereColumnName, aWhereColumnValue);
    }

    public <T extends FmmHeadlineNode> T retrievetHeadlineNode(String aNodeIdString) {
        FmmNodeDefinition theFmmNodeDefinition = FmmNodeDefinition.getEntryForNodeIdString(aNodeIdString);
        return retrieveFmmNodeFromSimpleIdTable(theFmmNodeDefinition, aNodeIdString);
    }

    private <T extends FmmNode> T retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aNodeIdString) {
		if(aNodeIdString == null || aNodeIdString.equals("")) {
			return null;
		}
        return this.persistenceTechnologyDelegate.retrieveFmmNodeFromSimpleIdTable(anFmmNodeDefinition, aNodeIdString);
    }

    private <T extends FmmNode> T retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aColumnValue) {
        return this.persistenceTechnologyDelegate.retrieveFmmNodeFromSimpleIdTable(anFmmNodeDefinition, aColumnName, aColumnValue);
    }

    private <T extends FmmNode> T retrieveFmmNodeFromTableForParent(FmmNodeDefinition anFmmNodeDefinition, String aParentId) {
        return this.persistenceTechnologyDelegate.retrieveFmmNodeFromTableForParent(anFmmNodeDefinition, aParentId);
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition aNodeDefinition) {
        return retrieveFmmNodeListFromSimpleIdTable(aNodeDefinition, null);
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition aNodeDefinition, String aSortSpec) {
        return retrieveFmmNodeListFromSimpleIdTable(aNodeDefinition, null, null, null, aSortSpec);
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromSimpleIdTable(
            FmmNodeDefinition aNodeDefinition,
            String aWhereColumnName,
            String aWhereColumnValue,
            String aSortSpec) {
        return retrieveFmmNodeListFromSimpleIdTable(
                aNodeDefinition,
                aWhereColumnName,
                aWhereColumnValue,
                null,
                aSortSpec );
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromSimpleIdTable(
            FmmNodeDefinition aNodeDefinition,
            String aWhereColumnName,
            String aWhereColumnValue,
            String anExceptionId,
            String aSortSpec) {
        return this.persistenceTechnologyDelegate.retrieveFmmNodeListFromSimpleIdTable(aNodeDefinition, aWhereColumnName, aWhereColumnValue, anExceptionId, aSortSpec);
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromSimpleIdTableWhere(
            FmmNodeDefinition aNodeDefinition,
            String aWhereClause,
            String anExceptionId,
            String aSortSpec) {
        return this.persistenceTechnologyDelegate.retrieveFmmNodeListFromSimpleIdTable(aNodeDefinition, aWhereClause, anExceptionId, aSortSpec);
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromLinkTable(
            FmmNodeDefinition aLeftTableDefinition,
            String aLeftColumnExceptionValue,
            FmmNodeDefinition aLinkTableDefinition,
            String aLinkColumnName,
            String aLinkColumnValue,
            String aSortSpec) {
        return retrieveFmmNodeListFromLinkTable(
                aLeftTableDefinition,
                IdNodeMetaData.column_ID,
                aLeftColumnExceptionValue,
                aLinkTableDefinition,
                aLinkColumnName,
                aLinkColumnName + " = '" + aLinkColumnValue + "'",
                aSortSpec );
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromLinkTable(
            FmmNodeDefinition aLeftTableDefinition,
            FmmNodeDefinition aLinkTableDefinition,
            String aLinkColumnName,
            String anAndColumnName,
            String anAndColumnValue,
            String aSortSpec) {
        return retrieveFmmNodeListFromLinkTable(
                aLeftTableDefinition,
                null,
                aLinkTableDefinition,
                aLinkColumnName,
                anAndColumnName,
                anAndColumnValue,
                aSortSpec );
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromLinkTable(
            FmmNodeDefinition aLeftTableDefinition,
            String aLeftColumnExceptionValue,
            FmmNodeDefinition aLinkTableDefinition,
            String aLinkColumnName,
            String anAndColumnName,
            String anAndColumnValue,
            String aSortSpec) {
        return retrieveFmmNodeListFromLinkTable(
                aLeftTableDefinition,
                IdNodeMetaData.column_ID,
                aLeftColumnExceptionValue,
                aLinkTableDefinition,
                aLinkColumnName,
                anAndColumnName + " = '" + anAndColumnValue + "'",
                aSortSpec);
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromLinkTable(
            FmmNodeDefinition aLeftTableDefinition,
            String aLeftColumnName,
            String aLeftColumnExceptionValue,
            FmmNodeDefinition aLinkTableDefinition,
            String aLinkColumnName,
            String anAndColumnName,
            String anAndColumnValue,
            String aSortSpec) {
        return retrieveFmmNodeListFromLinkTable(
                aLeftTableDefinition,
                aLeftColumnName,
                aLeftColumnExceptionValue,
                aLinkTableDefinition,
                aLinkColumnName,
                anAndColumnName + " = '" + anAndColumnValue + "'",
                aSortSpec);
    }

    private <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromLinkTable(
            FmmNodeDefinition aLeftTableDefinition,
            String aLeftColumnName,
            String aLeftColumnExceptionValue,
            FmmNodeDefinition aLinkTableDefinition,
            String aLinkColumnName,
            String anAndSpec,
            String aSortSpec) {
        return this.persistenceTechnologyDelegate.retrieveFmmNodeListFromSimpleIdLeftTableFromLink(aLeftTableDefinition, aLeftColumnName, aLeftColumnExceptionValue, aLinkTableDefinition, aLinkColumnName, anAndSpec, aSortSpec);
    }

    private boolean existsFmmNodeRow(FmmNode anFmmNode) {
        return existsFmmNodeRow(anFmmNode.getFmmNodeDefinition(), anFmmNode.getNodeIdString());
    }

    private boolean existsFmmNodeRow(FmmNodeDefinition anFmmNodeDefinition, String aNodeIdString) {
        // TODO - MAKE THIS MORE EFFICIENT by just looking up the id
        return retrieveFmmNodeFromSimpleIdTable(anFmmNodeDefinition, aNodeIdString) != null;
    }

    //-->>> DELETE

    private boolean deleteSimpleIdTableRow(FmmNode anFmmNode, boolean bAtomicTransaction) {
        return deleteSimpleIdTableRow(anFmmNode.getFmmNodeDefinition(), anFmmNode.getNodeIdString(), bAtomicTransaction);
    }

    private boolean deleteSimpleIdTableRow(FmmNodeDefinition anFmmNodeDefinition, String aNodeIdString, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.deleteRowFromSimpleIdTable(anFmmNodeDefinition, aNodeIdString, bAtomicTransaction);
    }

    private boolean deleteSimpleIdTableRow(FmmNodeDefinition anFmmNodeDefinition, String aWhereColumnName, String aWhereColumnValue, boolean bAtomicTransaction) {
        return this.persistenceTechnologyDelegate.deleteRowFromSimpleIdTable(anFmmNodeDefinition, aWhereColumnName, aWhereColumnValue, bAtomicTransaction);
    }


    ///////////////////////////////////////////
    /////////  SEQUENCE  //////////////////////

	public void swapSequence(FmmHeadlineNode aTargetNode, FmmHeadlineNode aPeerNode, FmmHeadlineNode aParentNode) {
		this.persistenceTechnologyDelegate.dbSwapSequence(aParentNode, aTargetNode, aPeerNode);
	}

	public void sequenceFirst(FmmHeadlineNode aTargetNode, FmmHeadlineNode aParentNode) {
		if(aTargetNode.getFmmNodeDefinition().isPrimarySequenceNode(aParentNode.getFmmNodeDefinition())) {
			this.persistenceTechnologyDelegate.incrementSequence(
                    aTargetNode.getFmmNodeDefinition().getTableName(),
                    aParentNode.getFmmNodeDefinition().getTableName() + "__id", aParentNode.getNodeIdString());
			this.persistenceTechnologyDelegate.dbSetPrimarySequence(aTargetNode, 1);
		} else if(aTargetNode.getFmmNodeDefinition().getSecondaryLinkNodeDefinition() != null) {
			this.persistenceTechnologyDelegate.incrementSequence(
                    aTargetNode.getFmmNodeDefinition().getSecondaryLinkNodeDefinition().getTableName(),
                    aParentNode.getFmmNodeDefinition().getTableName() + "__id", aParentNode.getNodeIdString());
			this.persistenceTechnologyDelegate.dbSetLinkNodeSequence(aTargetNode, 1);
		} else {
			this.persistenceTechnologyDelegate.incrementSequence(
                    aTargetNode.getFmmNodeDefinition().getTableName(),
                    aParentNode.getFmmNodeDefinition().getSecondaryParentNodeDefinition() + "__id", aParentNode.getNodeIdString());
			this.persistenceTechnologyDelegate.dbSetSecondarySequence(aTargetNode, 1);
		}
	}

	public void sequenceLast(FmmHeadlineNode aTargetNode, FmmHeadlineNode aParentNode) {
		if(aTargetNode.getFmmNodeDefinition().isPrimarySequenceNode(aParentNode.getFmmNodeDefinition())) {
			this.persistenceTechnologyDelegate.resequenceOnRemove(
                    aTargetNode.getFmmNodeDefinition().getTableName(),
                    aParentNode.getFmmNodeDefinition().getTableName() + "__id",
                    aParentNode.getNodeIdString(),
                    ((FmmCompletionNode) aTargetNode).getSequence());
			this.persistenceTechnologyDelegate.dbSetPrimarySequence(
					aTargetNode,
					this.persistenceTechnologyDelegate.getLastSequence(
                            aTargetNode.getFmmNodeDefinition().getTableName(),
                            aParentNode.getFmmNodeDefinition().getTableName() + "__id",
                            aParentNode.getNodeIdString()) + 1 );
		} else if(aTargetNode.getFmmNodeDefinition().getSecondaryLinkNodeDefinition() != null) {
			this.persistenceTechnologyDelegate.resequenceOnRemove(
                    aTargetNode.getFmmNodeDefinition().getSecondaryLinkNodeDefinition().getTableName(),
                    aParentNode.getFmmNodeDefinition().getTableName() + "__id",
                    aParentNode.getNodeIdString(),
                    this.persistenceTechnologyDelegate.dbGetLinkNodeSequence(aTargetNode));
			this.persistenceTechnologyDelegate.dbSetLinkNodeSequence(
					aTargetNode,
					this.persistenceTechnologyDelegate.getLastSequence(
                            aTargetNode.getFmmNodeDefinition().getSecondaryLinkNodeDefinition().getTableName(),
                            aParentNode.getFmmNodeDefinition().getTableName() + "__id",
                            aParentNode.getNodeIdString()) + 1 );
		} else {
			this.persistenceTechnologyDelegate.resequenceOnRemove(
                    aTargetNode.getFmmNodeDefinition().getTableName(),
                    aParentNode.getFmmNodeDefinition().getSecondaryParentNodeDefinition().getTableName() + "__id",
                    aParentNode.getNodeIdString(),
                    this.persistenceTechnologyDelegate.dbGetSecondarySequence(aTargetNode));
			this.persistenceTechnologyDelegate.dbSetSecondarySequence(aTargetNode,
					this.persistenceTechnologyDelegate.getLastSequence(
                            aTargetNode.getFmmNodeDefinition().getTableName(),
                            aParentNode.getFmmNodeDefinition().getSecondaryParentNodeDefinition().getTableName() + "__id",
                            aParentNode.getNodeIdString()) + 1 );
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
                theNewSequenceNumber = this.persistenceTechnologyDelegate.getLastSequence(
                        anFmmNodeDefinition.getTableName(),
                        aParentIdColumnName,
                        aParentId,
                        aSequenceColumnName);
                theNewSequenceNumber = theNewSequenceNumber + 1;
            } else {  // first child node of parent
                theNewSequenceNumber = 1;
                this.persistenceTechnologyDelegate.incrementSequence(
                        anFmmNodeDefinition.getTableName(),
                        aParentIdColumnName,
                        aParentId,
                        aSequenceColumnName);
            }
        } else { // sequence before/after peer node
            if(bSequenceAtEnd) {  // sequence after peer
                theNewSequenceNumber = aPeerNodeSequence + 1;
            } else { // sequence before peer
                theNewSequenceNumber = aPeerNodeSequence;
            }
            this.persistenceTechnologyDelegate.incrementSequence(
                    anFmmNodeDefinition.getTableName(),
                    aParentIdColumnName,
                    aParentId,
                    theNewSequenceNumber,
                    aSequenceColumnName);
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
                theNewSequenceNumber = this.persistenceTechnologyDelegate.getLastSequence(
                        aLinkTableFmmNodeDefinition.getTableName(),
                        aParentIdColumnName,
                        aParentNodeId,
                        SequencedLinkNodeMetaData.column_SEQUENCE);
                theNewSequenceNumber += 1;
            } else {  // first child node of parent
                theNewSequenceNumber = 1;
                this.persistenceTechnologyDelegate.incrementSequence(
                        aLinkTableFmmNodeDefinition.getTableName(),
                        aParentIdColumnName,
                        aParentNodeId,
                        SequencedLinkNodeMetaData.column_SEQUENCE);
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
            this.persistenceTechnologyDelegate.incrementSequence(
                    aLinkTableFmmNodeDefinition.getTableName(),
                    aParentIdColumnName,
                    aParentNodeId,
                    theNewSequenceNumber,
                    SequencedLinkNodeMetaData.column_SEQUENCE);
        }
        return theNewSequenceNumber;
    }


    /////////////////////////////////////////
    /////////  GENERIC NODES ////////////////

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
            theHeadlineNode = createProjectForPortfolio(aHeadline, aParentNode);
			break;
		case PROJECT_ASSET:
			theHeadlineNode = createProjectAssetForProject(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
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
			theHeadlineNode =  createStrategicMilestoneForParent(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
			break;
        case STRATEGIC_ASSET:
            theHeadlineNode = createStrategicAssetForStrategicMilestone(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
            break;
		case WORK_PACKAGE:
			theHeadlineNode =  createWorkPackageForParent(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
			break;
		case WORK_PLAN:
            // only created in a "batch" from the Cadence wizard
			break;
		case WORK_TASK:
            theHeadlineNode = createWorkTaskForParent(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
			break;
		default:
			break;
		}
		return theHeadlineNode;
	}

    
    /////////////////////////////////////////////////////////
    ///////////  FRACTAL INSERT  ////////////////////////////

    private boolean fractalInsertFmmHeadlineNode(FmmHeadlineNode anFmmHeadlineNode, boolean bAtomicTransaction) {
        return fractalInsertFmmHeadlineNode(anFmmHeadlineNode, true, bAtomicTransaction);
    }

    private boolean fractalInsertFmmHeadlineNode(FmmHeadlineNode anFmmHeadlineNode, boolean bCreateTribKnQuality, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = insertSimpleIdTableRow(anFmmHeadlineNode, false);
        isSuccess &= createNodeFragAuditBlock(anFmmHeadlineNode);
        isSuccess &= createNodeFragFseDocument(anFmmHeadlineNode);
        if(bCreateTribKnQuality) {
            isSuccess &= createNodeFragTribKnQuality(anFmmHeadlineNode);  // needs to be after all other Node Frags are updated
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    private boolean fractalInsertFmmGovernableNode(FmmGovernableNode anFmmGovernableNode, boolean bAtomicTransaction) {
        return fractalInsertFmmGovernableNode(anFmmGovernableNode, true, bAtomicTransaction);
    }

    private boolean fractalInsertFmmGovernableNode(FmmGovernableNode anFmmGovernableNode, boolean bCreateTribKnQuality, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = fractalInsertFmmHeadlineNode(anFmmGovernableNode, false, false);
        isSuccess &= createNodeFragGovernance(anFmmGovernableNode);
        if(bCreateTribKnQuality) {
            isSuccess &= createNodeFragTribKnQuality(anFmmGovernableNode);  // needs to be after all other Node Frags are updated
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    private boolean fractalInsertFmmCompletionNode(FmmCompletionNode anFmmCompletionNode, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = fractalInsertFmmGovernableNode(anFmmCompletionNode, false, false);
        isSuccess &= createNodeFragCompletion(anFmmCompletionNode);
        isSuccess &= createNodeFragWorkTaskBudget(anFmmCompletionNode);
        isSuccess &= createNodeFragTribKnQuality(anFmmCompletionNode);  // needs to be after all other Node Frags are updated
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }


    /////////////////////////////////////////////////////////////////
    ////////////  SPECIAL UPDATES  ///////////////////////////////////

    public boolean fractalUpdateNodeHeadline(FmmHeadlineNode aHeadlineNode) {
        startTransaction();
        Date theNewRowTimestamp = GcgDateHelper.getCurrentDateTime();
        aHeadlineNode.setRowTimestamp(theNewRowTimestamp);
        boolean isSuccess = updateSimpleIdTableRow(aHeadlineNode, false);
        isSuccess &= updateSimpleIdTableRow(aHeadlineNode.getUpdatedNodeFragAuditBlock(), false);  // always updated when headline node is updated
        endTransaction(isSuccess);
        return isSuccess;
	}


    /////////////////////////////////////////////////////////////////
    ////////////  FRACTAL UPDATE  ///////////////////////////////////

    private boolean fractalUpdateFmmHeadlineNode(FmmHeadlineNode anFmmHeadlineNode, boolean bAtomicTransaction) {
        return fractalUpdateFmmHeadlineNode(anFmmHeadlineNode, GcgDateHelper.getCurrentDateTime(), bAtomicTransaction);
    }

    private boolean fractalUpdateFmmHeadlineNode(FmmHeadlineNode anFmmHeadlineNode, Date aNewRowTimestamp, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        anFmmHeadlineNode.setRowTimestamp(aNewRowTimestamp);
        boolean isSuccess = updateSimpleIdTableRow(anFmmHeadlineNode, false);
        isSuccess &= updateSimpleIdTableRow(anFmmHeadlineNode.getUpdatedNodeFragAuditBlock(), false);  // always updated when headline node is updated
        isSuccess &= conditionalUpdateSimpleIdTableRow(aNewRowTimestamp, anFmmHeadlineNode.getUpdatedNodeFragFseDocument(), false);
        if(bAtomicTransaction) {
            isSuccess &= conditionalUpdateSimpleIdTableRow(aNewRowTimestamp, anFmmHeadlineNode.getUpdatedNodeFragTribKnQuality(), false);
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    private boolean fractalUpdateFmmGovernableNode(FmmGovernableNode anFmmGovernableNode, boolean bAtomicTransaction) {
        return fractalUpdateFmmGovernableNode(anFmmGovernableNode, GcgDateHelper.getCurrentDateTime(), bAtomicTransaction);
    }

    private boolean fractalUpdateFmmGovernableNode(FmmGovernableNode anFmmGovernableNode, Date aNewRowTimestamp, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = fractalUpdateFmmHeadlineNode(anFmmGovernableNode, aNewRowTimestamp, false);
        isSuccess &= conditionalUpdateSimpleIdTableRow(aNewRowTimestamp, anFmmGovernableNode.getUpdatedNodeFragGovernance(), false);
        if(bAtomicTransaction) {
            isSuccess &= conditionalUpdateSimpleIdTableRow(aNewRowTimestamp, anFmmGovernableNode.getUpdatedNodeFragTribKnQuality(), false);
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    private boolean fractalUpdateFmmCompletionNode(FmmCompletionNode anFmmCompletionNode, boolean bAtomicTransaction) {
        return fractalUpdateFmmCompletionNode(anFmmCompletionNode, GcgDateHelper.getCurrentDateTime(), bAtomicTransaction);
    }

    private boolean fractalUpdateFmmCompletionNode(FmmCompletionNode anFmmCompletionNode, Date aNewRowTimestamp, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = fractalUpdateFmmHeadlineNode(anFmmCompletionNode, aNewRowTimestamp, false);
        isSuccess &= conditionalUpdateSimpleIdTableRow(aNewRowTimestamp, anFmmCompletionNode.getUpdatedNodeFragCompletion(), false);
        isSuccess &= conditionalUpdateSimpleIdTableRow(aNewRowTimestamp, anFmmCompletionNode.getUpdatedNodeFragWorkTaskBudget(), false);
        if(bAtomicTransaction) {
            isSuccess &= conditionalUpdateSimpleIdTableRow(aNewRowTimestamp, anFmmCompletionNode.getUpdatedNodeFragTribKnQuality(), false);
            endTransaction(isSuccess);
        }
        return isSuccess;
    }


    /////////////////////////////////////////////////////////////////
    ////////////  FRACTAL DELETE  ///////////////////////////////////

    private HeadlineNodeTrash headlineNodeTrash;

    private boolean fractalDeleteFmmHeadlineNode(FmmHeadlineNode anFmmHeadlineNode, boolean bAtomicTransaction) {
        return fractalDeleteFmmHeadlineNode(anFmmHeadlineNode, true, bAtomicTransaction);
    }

	private boolean fractalDeleteFmmHeadlineNode(FmmHeadlineNode anFmmHeadlineNode, boolean bDeleteHeadlineRow, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        this.headlineNodeTrash = new HeadlineNodeTrash(anFmmHeadlineNode);
		boolean isSuccess = deleteAllFragLocks(anFmmHeadlineNode);
        isSuccess &= deleteNodeFragAuditBlock(anFmmHeadlineNode, false);
		isSuccess &= deleteNodeFragFseDocument(anFmmHeadlineNode, false);
		isSuccess &= deleteNodeFragTribKnQuality(anFmmHeadlineNode, false);
        if(bDeleteHeadlineRow) {
            isSuccess &= deleteSimpleIdTableRow(anFmmHeadlineNode, false);  // delete last for referential integrity
            isSuccess &= insertSimpleIdTableRow(this.headlineNodeTrash, false);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
		return isSuccess;
	}

    private boolean fractalDeleteFmmGovernableNode(FmmGovernableNode anFmmGovernableNode, boolean bAtomicTransaction) {
        return fractalDeleteFmmGovernableNode(anFmmGovernableNode, true, bAtomicTransaction);
    }

    private boolean fractalDeleteFmmGovernableNode(FmmGovernableNode anFmmGovernableNode, boolean bDeleteHeadlineRow, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = fractalDeleteFmmHeadlineNode(anFmmGovernableNode, false, false);
		isSuccess &= deleteNodeFragGovernance(anFmmGovernableNode, false);
        if(bDeleteHeadlineRow) {
            isSuccess &= deleteSimpleIdTableRow(anFmmGovernableNode, false);  // delete last for referential integrity
            isSuccess &= insertSimpleIdTableRow(this.headlineNodeTrash, false);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
	}

	private boolean fractalDeleteFmmCompletionNode(FmmCompletionNode anFmmCompletionNode, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = fractalDeleteFmmGovernableNode(anFmmCompletionNode, false, false);
        isSuccess &= deleteNodeFragCompletion(anFmmCompletionNode, false);
        isSuccess &= deleteNodeFragWorkTaskBudget(anFmmCompletionNode, false);
        isSuccess &= deleteSimpleIdTableRow(anFmmCompletionNode, false);  // delete last for referential integrity
        isSuccess &= insertSimpleIdTableRow(this.headlineNodeTrash, false);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
	}


    /////////////////////////////////////////////////////////////////
    ////////////  ORPHANS  //////////////////////////////////////////


    /////////////////////////////////////////////////////////////////
    ////////////  ADOPTION  /////////////////////////////////

    public boolean adoptPrimaryOrphanIntoParent(FmmCompletionNode anOrphanNode, FmmCompletionNode aParentNode, FmmCompletionNode aPeerNode, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                anOrphanNode.getFmmNodeDefinition(),
                anOrphanNode.getFmmNodeDefinition().getPrimaryParentIdColumnName(),
                aParentNode,
                aPeerNode,
                bSequenceAtEnd);
        anOrphanNode.setPrimaryParentId(aParentNode.getNodeIdString());
        anOrphanNode.setSequence(theNewSequenceNumber);
        return updateSimpleIdTableRow(anOrphanNode, bAtomicTransaction);
    }

    public boolean adoptPrimaryOrphanIntoParentAlphaSort(FmmCompletionNode anOrphanNode, FmmCompletionNode aParentNode, boolean bAtomicTransaction) {
        anOrphanNode.setPrimaryParentId(aParentNode.getNodeIdString());
        return updateSimpleIdTableRow(anOrphanNode, bAtomicTransaction);
    }

    public boolean adoptPrimaryLinkOrphanIntoParent(FmmCompletionNode anOrphanNode, FmmCompletionNode aParentNode, FmmCompletionNode aPeerNode, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        boolean theResult = createStrategicCommitment(aParentNode, aPeerNode, bSequenceAtEnd, (StrategicAsset) anOrphanNode);
        ((StrategicAsset) anOrphanNode).setStrategic(true);
        return updateSimpleIdTableRow(anOrphanNode, bAtomicTransaction);
    }


    /////////////////////////////////////////////////////////////////
    ////////////  FMM CONFIGURATION  ////////////////////////////////

    public PersistenceTechnologyDelegate getPersistenceTechnologyDelegate() {
        return this.persistenceTechnologyDelegate;
    }
    
	public void replaceFmmOwner(FmsOrganization anOrganization) {
		this.persistenceTechnologyDelegate.setFmmOwnership(anOrganization);
	}

	public static FmmConfiguration getRequestedFmmConfiguration() {
		return FmmDatabaseService.requestedFmmConfiguration;
	}

	public static void setRequestedFmmConfigurataion(FmmConfiguration anFmmConfiguration) {
		FmmDatabaseService.requestedFmmConfiguration = anFmmConfiguration;
	}

	private static FmmDatabaseService getInstance(FmmConfiguration anFmmConfiguration) {
		FmmDatabaseService.requestedFmmConfiguration = anFmmConfiguration;
		FmmDatabaseService theFmmDatabaseMediator = FmmDatabaseService.fmmDatabaseMediatorMap.get(anFmmConfiguration.getHeadline());
		if(theFmmDatabaseMediator == null) {
			theFmmDatabaseMediator = new FmmDatabaseService(anFmmConfiguration);
			FmmDatabaseService.fmmDatabaseMediatorMap.put(anFmmConfiguration.getHeadline(),theFmmDatabaseMediator);
		}
		// update FmmConfiguration object, FmmDatabaseMediator from FmmConfiguration row
		return FmmDatabaseService.fmmDatabaseMediatorMap.get(anFmmConfiguration.getHeadline());
	}

	public static FmmDatabaseService getInstanceAndSetActive(FmmConfiguration aFmmConfiguration) {
		FmmDatabaseService.activeFmmDatabaseMediator = getInstance(aFmmConfiguration);
		return FmmDatabaseService.activeFmmDatabaseMediator;
	}

	public static void setActiveMediator(FmmConfiguration anFmmConfiguration) {
		if(FmmDatabaseService.activeFmmDatabaseMediator != null) {
			FmmDatabaseService.activeFmmDatabaseMediator.closeDatabase();
		}
        FmmDatabaseService theFmmDatabaseMediator = null;
        if(FmmDatabaseService.fmmDatabaseMediatorMap != null) {
            theFmmDatabaseMediator = FmmDatabaseService.fmmDatabaseMediatorMap.get(anFmmConfiguration.getHeadline());
        }
		if(theFmmDatabaseMediator != null) {
			theFmmDatabaseMediator.getPersistenceTechnologyDelegate().setActiveDatabase(anFmmConfiguration);
			FmmDatabaseService.activeFmmDatabaseMediator = theFmmDatabaseMediator;
		} else {
			FmmDatabaseService.activeFmmDatabaseMediator = getInstance(anFmmConfiguration);
		}
	}

	public static FmmDatabaseService getActiveMediator() {
		if(FmmDatabaseService.activeFmmDatabaseMediator == null) {
			setActiveMediator(getActiveFmmConfiguration());
//			setActiveMediator(FmmHelper.getLocalFmmConfiguration());
		}
		return FmmDatabaseService.activeFmmDatabaseMediator;
	}

	public static void closeActiveFmm() {
		if(FmmDatabaseService.activeFmmDatabaseMediator == null) {
			return;
		}
		FmmDatabaseService.activeFmmDatabaseMediator.closeDatabase();
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

	public static FmmConfiguration getActiveFmmConfiguration() {
		return FmmDatabaseService.activeFmmConfiguration;
	}

    public static void setActiveFmmConfiguration(FmmConfiguration anFmmConfiguration) {
        FmmDatabaseService.activeFmmConfiguration = anFmmConfiguration;
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



	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - COMMUNITY MEMBER  ////////////////////////////////////////////////////////////////////////////

    public CommunityMember retrieveCommunityMember(String aCommunityMemberId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.COMMUNITY_MEMBER, aCommunityMemberId);
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
                sort_spec__HEADLINE );  // "headline" is unique to both tables
	}

	public boolean insertCommunityMember(CommunityMember aCommunityMember, boolean bAtomicTransaction) {
		return fractalInsertFmmGovernableNode(aCommunityMember, bAtomicTransaction);
	}

	public boolean deleteCommunityMember(CommunityMember aCommunityMember, boolean bAtomicTransaction) {
		return fractalDeleteFmmGovernableNode(aCommunityMember, bAtomicTransaction);
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

	private boolean createHeadlineNodeTrash(FmmHeadlineNode anFmmHeadlineNode) {
		HeadlineNodeTrash theHeadlineNodeTrash = new HeadlineNodeTrash(anFmmHeadlineNode);
		return insertHeadlineNodeTrash(theHeadlineNodeTrash, false);
	}

    public boolean insertHeadlineNodeTrash(HeadlineNodeTrash aHeadlineNodeTrash, boolean bAtomicTransaction) {
        return insertSimpleIdTableRow(aHeadlineNodeTrash, bAtomicTransaction);
    }

	public boolean deleteHeadlineNodeTrash(HeadlineNodeTrash aHeadlineNodeTrash, boolean bAtomicTransaction) {
        return deleteSimpleIdTableRow(aHeadlineNodeTrash, bAtomicTransaction);
	}


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - BOOKSHELF  ///////////////////////////////////////////////////////////////////////////////////

    public Bookshelf retrieveBookshelf(String aBookshelfId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.BOOKSHELF, aBookshelfId);
    }

    public ArrayList<Bookshelf> retrieveBookshelfList(FmsOrganization anFmsOrganization) {
        return retrieveBookshelfList(anFmsOrganization, null);
    }

    public ArrayList<Bookshelf> retrieveBookshelfList(FmsOrganization anFmsOrganization, Bookshelf aBookshelfException) {
        return retrieveBookshelfListForOrganization(
                anFmsOrganization.getNodeIdString(),
                aBookshelfException == null ? null : aBookshelfException.getNodeIdString());
    }

    public ArrayList<Bookshelf> retrieveBookshelfListForOrganization(String anFmsOrganizationId, String aBookshelfExceptionId) {
        return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.BOOKSHELF,
                BookshelfMetaData.column_ORGANIZATION_ID,
                anFmsOrganizationId,
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
                sort_spec__HEADLINE );  // "headline" is unique to both tables
    }

    public Bookshelf createBookshelf(String aHeadline) {
        Bookshelf theBookshelf = new Bookshelf(
                new NodeId(FmmNodeDefinition.BOOKSHELF),
                aHeadline,
                FmmDatabaseService.getActiveMediator().getFmsOrganization() );
        boolean isSuccess = insertBookshelf(theBookshelf, true);
        return isSuccess ? theBookshelf : null;
    }

    public boolean insertBookshelf(Bookshelf aBookshelf, boolean bAtomicTransaction) {
        return fractalInsertFmmGovernableNode(aBookshelf, bAtomicTransaction);
    }

    public boolean updateBookshelf(Bookshelf aBookshelf, boolean bAtomicTransaction) {
        return fractalUpdateFmmGovernableNode(aBookshelf, bAtomicTransaction);
    }

    public boolean existsBookshelf(String aBookshelfId) {
        return existsSimpleIdTable(FmmNodeDefinition.BOOKSHELF, aBookshelfId);
    }
    
    public boolean deleteBookshelf(Bookshelf aBookshelf, boolean bAtomicTransaction) {
        return fractalDeleteFmmGovernableNode(aBookshelf, bAtomicTransaction);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - BOOKSHELF LINK TO NOTEBOOK  //////////////////////////////////////////////////////////////////

    private boolean createBookshelfLinkToNotebook(String aBookshelfId, String aNotebookId, boolean bAtomicTransaction) {
        BookshelfLinkToNotebook theLink = new BookshelfLinkToNotebook(aBookshelfId, aNotebookId);
        return insertSimpleIdTableRow(theLink, bAtomicTransaction);
    }
    
    public boolean deleteAllBookshelfLinks(String aBookshelfId, boolean bAtomicTransaction) {
        return deleteSimpleIdTableRow(
                FmmNodeDefinition.BOOKSHELF_LINK_TO_NOTEBOOK,
                BookshelfLinkToNotebookMetaData.column_BOOKSHELF_ID,
                aBookshelfId,
                bAtomicTransaction);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - NOTEBOOK  ////////////////////////////////////////////////////////////////////////////////////

    public Notebook retrieveNotebook(String aNotebookId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NOTEBOOK, aNotebookId);
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
                sort_spec__HEADLINE );  // "headline" is unique to both tables
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
                sort_spec__HEADLINE );  // "headline" is unique to both tables
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
        return fractalUpdateFmmCompletionNode(aNotebook, bAtomicTransaction);
    }

    public boolean existsNotebook(String aNotebookId) {
        return existsSimpleIdTable(FmmNodeDefinition.NOTEBOOK, aNotebookId);
    }

    public boolean deleteNotebook(Notebook aNotebook, boolean bAtomicTransaction) {
        return fractalDeleteFmmCompletionNode(aNotebook, bAtomicTransaction);
    }

    public boolean deleteNotebookListForBookshelf(String aBookshelfId, boolean bAtomicTransaction) {
        ArrayList<String> theNotebookIdList = getFmmNodeIdList(FmmNodeDefinition.NOTEBOOK, BookshelfLinkToNotebookMetaData.column_BOOKSHELF_ID, aBookshelfId, null);
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
                isSuccess &= fractalDeleteFmmCompletionNode(theNotebook, false);
            }
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    // MOVE & COPY

    // TODO - create generalized routine
    public boolean moveAllNotebooksIntoBookshelf(String aCurrentBookshelfId, String aTargetBookshelfId, boolean bAtomicTransaction) {
        return false;
    }

    // TODO - create generalized routine
    public boolean copyAllNotebooksIntoBookshelf(String aCurrentBookshelfId, String aTargetBookshelfId, boolean bAtomicTransaction) {
        return false;
    }

    // ORPHANS

    // TODO - create generalized routine
    public boolean adoptNotebookIntoBookshelf(
            String aNotebookId, String aBookshelfId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
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
        return insertSimpleIdTableRow(theNewNotebookLinkToDiscussionTopic, bAtomicTransaction);
    }

    public boolean deleteAllNotebookLinks(String aNotebookId, boolean bAtomicTransaction) {
        return deleteSimpleIdTableRow(
                FmmNodeDefinition.NOTEBOOK_LINK_TO_DISCUSSION_TOPIC,
                NotebookLinkToDiscussionTopicMetaData.column_NOTEBOOK_ID,
                aNotebookId,
                bAtomicTransaction);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - DISCUSSION TOPIC  ////////////////////////////////////////////////////////////////////////////

    public DiscussionTopic retrieveDiscussionTopic(String aDiscussionTopicId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.DISCUSSION_TOPIC, aDiscussionTopicId);
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
                FmmNodeDefinition.NOTEBOOK_LINK_TO_DISCUSSION_TOPIC.getTableName() + "." + NotebookLinkToDiscussionTopicMetaData.column_SEQUENCE + " ASC");
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
        boolean isSuccess = insertDiscussionTopic(theDiscussionTopic, false);
        isSuccess &= createNotebookLinkToDiscussionTopic(aNotebook, theDiscussionTopic, aPeerNode, bSequenceAtEnd, false);
        isSuccess &= createNodeFragTribKnQuality(theDiscussionTopic);
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
        return fractalUpdateFmmCompletionNode(aDiscussionTopic, bAtomicTransaction);
    }

    public boolean existsDiscussionTopic(String aDiscussionTopicId) {
        return existsSimpleIdTable(FmmNodeDefinition.DISCUSSION_TOPIC, aDiscussionTopicId);
    }

    public boolean deleteDiscussionTopic(DiscussionTopic aDiscussionTopic, boolean bAtomicTransaction) {
        return fractalDeleteFmmCompletionNode(aDiscussionTopic, bAtomicTransaction);
    }

    public boolean deleteDiscussionTopicsForNotebook(String aNotebookId, boolean bAtomicTransaction) {
        ArrayList<String> theDiscussionTopicIdList = getFmmNodeIdList(FmmNodeDefinition.DISCUSSION_TOPIC, NotebookLinkToDiscussionTopicMetaData.column_NOTEBOOK_ID, aNotebookId, null);
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
                isSuccess &= fractalDeleteFmmCompletionNode(theDiscussionTopic, false);
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
        return insertSimpleIdTableRow(theNewDiscussionTopicLinkToNodeFragAuditBlock, bAtomicTransaction);
    }
    
    public ArrayList<DiscussionTopicLinkToNodeFragAuditBlock> retrieveDiscussionTopicLinkToNodeFragAuditBlockList(DiscussionTopic discussionTopic) {
        ArrayList<DiscussionTopicLinkToNodeFragAuditBlock> theList = new ArrayList<DiscussionTopicLinkToNodeFragAuditBlock>();
        return theList;
    }

    public boolean deleteAllDiscussionTopicLinks(String aDiscussionTopicId, boolean bAtomicTransaction) {
        return deleteSimpleIdTableRow(
                FmmNodeDefinition.DISCUSSION_TOPIC_LINK_TO_NODE_FRAG_AUDIT_BLOCK,
                DiscussionTopicLinkToNodeFragAuditBlockMetaData.column_DISCUSSION_TOPIC_ID,
                aDiscussionTopicId,
                bAtomicTransaction);
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
        return retrievePortfolioListForOrganization(anOrganization.getNodeIdString(), aPortfolioException == null ? null : aPortfolioException.getNodeIdString());
    }

    public ArrayList<Portfolio> retrievePortfolioListForOrganization(String anOrganizationId, String aPortfolioExceptionId) {
        return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.PORTFOLIO,
                PortfolioMetaData.column_ORGANIZATION_ID,
                anOrganizationId,
                aPortfolioExceptionId,
                sort_spec__HEADLINE);
    }

    public Portfolio createPortfolio(String aHeadline) {
        Portfolio theNewPortfolio = new Portfolio(
                new NodeId(FmmNodeDefinition.PORTFOLIO),
                aHeadline,
                FmmDatabaseService.getActiveMediator().getFmsOrganization() );
        boolean isSuccess = insertPortfolio(theNewPortfolio, true);
        return isSuccess ? theNewPortfolio : null;
    }

    public boolean insertPortfolio(Portfolio aPortfolio, boolean bAtomicTransaction) {
        return fractalInsertFmmCompletionNode(aPortfolio, bAtomicTransaction);
    }

    public boolean updatePortfolio(Portfolio aPortfolio, boolean bAtomicTransaction) {
        return fractalUpdateFmmCompletionNode(aPortfolio, bAtomicTransaction);
    }

    public boolean existsPortfolio(String aPortfolioId) {
        return existsSimpleIdTable(FmmNodeDefinition.PORTFOLIO, aPortfolioId);
    }

    public boolean deletePortfolio(Portfolio aPortfolio, boolean bAtomicTransaction) {
        return fractalDeleteFmmCompletionNode(aPortfolio, bAtomicTransaction);
    }

    // MOVE

    // TODO - create generalized routine
    public int countPortfolioForProjectMoveTarget(FmsOrganization anFmsOrganization, Portfolio aPortfolioException) {
        return getPersistenceTechnologyDelegate().countPortfolioForProjectMoveTarget(anFmsOrganization, aPortfolioException);
    }

    // TODO - create generalized routine
    public ArrayList<? extends GcgGuiable> retrievePortfolioListForProjectMoveTarget(FmsOrganization anFmsOrganization, Portfolio aPortfolioException) {
        return getPersistenceTechnologyDelegate().retrievePortfolioListForProjectMoveTarget(anFmsOrganization, aPortfolioException);
    }

    // TODO - create generalized routine
    public ArrayList<? extends GcgGuiable> retrievePortfolioListForWorkAssetMoveTarget(FmsOrganization anFmsOrganization, Project aProjectException) {
        return getPersistenceTechnologyDelegate().retrievePortfolioListForWorkAssetMoveTarget(anFmsOrganization, aProjectException);
    }

    // TODO - create generalized routine
    public ArrayList<? extends GcgGuiable> retrievePortfolioListForWorkPackageMoveTarget(FmsOrganization anFmsOrganization, WorkAsset aWorkAssetException) {
        return getPersistenceTechnologyDelegate().retrievePortfolioListForWorkPackageMoveTarget(anFmsOrganization, aWorkAssetException);
    }

    // TODO - create generalized routine
    public ArrayList<? extends GcgGuiable> retrievePortfolioListForWorkTaskMoveTarget(FmsOrganization anFmsOrganization, WorkPackage aWorkPackageException) {
        return getPersistenceTechnologyDelegate().retrievePortfolioListForWorkTaskMoveTarget(anFmsOrganization, aWorkPackageException);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - PROJECT  /////////////////////////////////////////////////////////////////////////////////////

    public Project retrieveProject(String aProjectId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.PROJECT, aProjectId);
    }
    
    public ArrayList<Project> retrieveProjectList(Portfolio aPortfolio) {
        return retrieveProjectList(aPortfolio, null);
    }

    public ArrayList<Project> retrieveProjectList(Portfolio aPortfolio, Project aProjectException) {
        return retrieveProjectListForPortfolio(aPortfolio.getNodeIdString(), aProjectException == null ? null : aProjectException.getNodeIdString());
    }

    public ArrayList<Project> retrieveProjectListForPortfolio(String aPortfolioId, String aProjectExceptionId) {
        return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.PROJECT,
                ProjectMetaData.column_PORTFOLIO_ID,
                aPortfolioId,
                aProjectExceptionId,
                sort_spec__HEADLINE);
    }

    private Project createProjectForPortfolio(String aProjectHeadline, FmmHeadlineNode aPortfolio) {
        Project theNewProject = new Project(
                new NodeId(FmmNodeDefinition.PROJECT),
                aProjectHeadline,
                (Portfolio) aPortfolio );
        boolean isSuccess = insertProject(theNewProject, true);
        return isSuccess ? theNewProject : null;
    }

    public boolean insertProject(Project aProject, boolean bAtomicTransaction) {
        return fractalInsertFmmCompletionNode(aProject, bAtomicTransaction);
    }

    public boolean updateProject(Project aProject, boolean bAtomicTransaction) {
        return fractalUpdateFmmCompletionNode(aProject, bAtomicTransaction);
    }

    public boolean existsProject(String aProjectId) {
        return existsSimpleIdTable(FmmNodeDefinition.PROJECT, aProjectId);
    }

    public boolean deleteProject(Project aProject, boolean bAtomicTransaction) {
        return fractalDeleteFmmCompletionNode(aProject, bAtomicTransaction);
    }

    public boolean deleteProjectsForPortfolio(String aPortfolioId, boolean bAtomicTransaction) {
        ArrayList<String> theProjectIdList = getFmmNodeIdList(FmmNodeDefinition.PROJECT, ProjectMetaData.column_PORTFOLIO_ID, aPortfolioId, null);
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
            isSuccess &= fractalDeleteFmmCompletionNode(theProject, false);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    // MOVE

    // TODO - create generalized routine
    public int countProjectsForProjectAssetMoveTarget(Portfolio aPortfolio, Project aProjectException) {
        return getPersistenceTechnologyDelegate().countProjectsForProjectAssetMoveTarget(aPortfolio, aProjectException);
    }

    // TODO - create generalized routine
    public ArrayList<Project> retrieveProjectListForProjectAssetMoveTarget(Portfolio aPortfolio, Project aProjectException) {
        return getPersistenceTechnologyDelegate().retrieveProjectListForProjectAssetMoveTarget(aPortfolio, aProjectException);
    }

    // TODO - create generalized routine
    public ArrayList<Project> retrieveProjectListForWorkPackageMoveTarget(Portfolio aPortfolio, WorkAsset aWorkAssetException) {
        return getPersistenceTechnologyDelegate().retrieveProjectListForWorkPackageMoveTarget(aPortfolio, aWorkAssetException);
    }

    // TODO - create generalized routine
    public ArrayList<Project> retrieveProjectListForWorkTaskMoveTarget(Portfolio aPortfolio, WorkPackage aWorkPackageException) {
        return getPersistenceTechnologyDelegate().retrieveProjectListForWorkTaskMoveTarget(aPortfolio, aWorkPackageException);
    }

    // TODO - generalize this routine
    public boolean moveSingleProjectIntoPortfolio(String aProjectId, String aPortfolioId, boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().moveSingleProjectIntoPortfolio(aProjectId, aPortfolioId, bAtomicTransaction);
    }

    // TODO - create generalized routine
    public boolean moveAllProjectsIntoPortfolio(String aCurrentPortfolioId, String aTargetPortfolioId, boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().moveAllProjectsIntoPortfolio(aCurrentPortfolioId, aTargetPortfolioId, bAtomicTransaction);
    }

    // ORPHANS

    // TODO - create generalized routine
    public ArrayList<Project> retrieveProjectOrphanListFromPortfolio() {
        return getPersistenceTechnologyDelegate().retrieveProjectOrphanListFromPortfolio();
    }

    // TODO - create generalized routine
    public boolean adoptOrphanProjectIntoPortfolio(
            String aProjectId, String aPortfolioId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().adoptOrphanProjectIntoPortfolio(
                aProjectId,
                aPortfolioId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

    // TODO - generalize this routine
    public boolean orphanSingleProjectFromPortfolio(String aProjectNodeIdString, String aPortfolioNodeIdString, boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().orphanSingleProjectFromPortfolio(aProjectNodeIdString, aPortfolioNodeIdString, bAtomicTransaction);
    }

    // TODO - generalize this routine
    public boolean orphanAllProjectsFromPortfolio(String aPortfolioId, boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().orphanAllProjectsFromPortfolio(aPortfolioId, bAtomicTransaction);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - WORK ASSET  //////////////////////////////////////////////////////////////////////////////////

    public WorkAsset retrieveWorkAsset(String aWorkAssetId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.WORK_ASSET, aWorkAssetId);
    }

    public ArrayList<WorkAsset> retrieveWorkAssetList(Project aProject) {
        return retrieveWorkAssetList(aProject, null);
    }

    public ArrayList<WorkAsset> retrieveWorkAssetList(Project aProject, WorkAsset aWorkAssetException) {
        return retrieveWorkAssetListForProject(aProject.getNodeIdString(), aWorkAssetException == null ? null : aWorkAssetException.getNodeIdString());
    }

    public ArrayList<WorkAsset> retrieveWorkAssetListForProject(String aProjectId, String aWorkAssetExceptionId) {
        return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.WORK_ASSET,
                WorkAssetMetaData.column_PROJECT_ID,
                aProjectId,
                aWorkAssetExceptionId,
                sort_spec__SEQUENCE );
    }

    // ORPHANS

    // TODO - create generalized routine
    public ArrayList<ProjectAsset> listWorkAssetOrphansFromProject() {
        return getPersistenceTechnologyDelegate().retrieveWorkAssetOrphanListFromProject();
    }

    // TODO - create generalized routine
    public ArrayList<WorkAsset> listWorkAssetOrphansFromStrategicMilestone() {
        return getPersistenceTechnologyDelegate().retrieveWorkAssetOrphanListFromStrategicMilestone();
    }

    // DEMOTE / PROMOTE

    public boolean promoteWorkAssetToStrategicAsset(FmmCompletionNode aWorkAsset, FmmCompletionNode aStrategicMilestone, FmmCompletionNode aPeerNode, boolean bSequenceAtEnd) {
        boolean theResult = createStrategicCommitment(aStrategicMilestone, aPeerNode, bSequenceAtEnd, new StrategicAsset(aWorkAsset.getJsonObject()));
        ((WorkAsset) aWorkAsset).setStrategic(true);
        return theResult && updateSimpleIdTableRow(aWorkAsset, true);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - PROJECT ASSET  ///////////////////////////////////////////////////////////////////////////////

    public ProjectAsset retrieveProjectAsset(String aProjectAssetId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.PROJECT_ASSET, aProjectAssetId);
    }

    public ArrayList<ProjectAsset> retrieveProjectAssetList() {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.PROJECT_ASSET);
    }

    public ArrayList<ProjectAsset> retrieveProjectAssetList(Project aProject) {
        return retrieveProjectAssetList(aProject, null);
    }

    public ArrayList<ProjectAsset> retrieveProjectAssetList(Project aProject, ProjectAsset aProjectAssetException) {
        return retrieveProjectAssetListForProject(aProject.getNodeIdString(), aProjectAssetException == null ? null : aProjectAssetException.getNodeIdString());
    }

    public ArrayList<ProjectAsset> retrieveProjectAssetListForProject(String aProjectId, String aProjectAssetExceptionId) {
        return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.PROJECT_ASSET,
                ProjectAssetMetaData.column_PROJECT_ID,
                aProjectId,
                aProjectAssetExceptionId,
                sort_spec__SEQUENCE);
    }

//    public ArrayList<ProjectAsset> retrieveProjectAssetList(StrategicMilestone aStrategicMilestone) {
//        return retrieveProjectAssetList(aStrategicMilestone, null);
//    }

//    public ArrayList<ProjectAsset> retrieveProjectAssetList(StrategicMilestone aStrategicMilestone, ProjectAsset aProjectAssetException) {
//        return retrieveFmmNodeListFromLinkTable(
//                FmmNodeDefinition.PROJECT_ASSET,
//                aProjectAssetException == null ? null : aProjectAssetException.getNodeIdString(),
//                FmmNodeDefinition.STRATEGIC_COMMITMENT,
//                StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID,
//                aStrategicMilestone.getNodeIdString(),
//                sort_spec__SEQUENCE);
//    }

    private ProjectAsset createProjectAssetForProject(
            String aProjectAssetHeadline,
            FmmHeadlineNode aProjectNode,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd) {
        ProjectAsset theNewProjectAsset = new ProjectAsset(
                new NodeId(FmmNodeDefinition.PROJECT_ASSET),
                aProjectAssetHeadline,
                (Project) aProjectNode );
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                FmmNodeDefinition.PROJECT_ASSET,
                ProjectAssetMetaData.column_PROJECT_ID,
                aProjectNode,
                aPeerNode,
                bSequenceAtEnd );
        theNewProjectAsset.setSequence(theNewSequenceNumber);
        boolean isSuccess = insertProjectAsset(theNewProjectAsset, true);
        return isSuccess ? theNewProjectAsset : null;
    }

    private boolean insertProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction) {
        return fractalInsertFmmCompletionNode(aProjectAsset, bAtomicTransaction);
    }

    public boolean updateProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction) {
        return fractalUpdateFmmCompletionNode(aProjectAsset, bAtomicTransaction);
    }

    public boolean existsProjectAsset(String aProjectAssetId) {
        return existsSimpleIdTable(FmmNodeDefinition.PROJECT_ASSET, aProjectAssetId);
    }

    public boolean deleteProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction) {
        return fractalDeleteFmmCompletionNode(aProjectAsset, bAtomicTransaction);
    }

    // MOVE

    // TODO - create generalized routine
    public ArrayList<ProjectAsset> retrieveStrategicAssetListForWorkPackageMoveTarget(StrategicMilestone aStrategicMilestone, StrategicAsset aStrategicAssetException) {
        return getPersistenceTechnologyDelegate().retrieveStrategicAssetListForWorkPackageMoveTarget(
                aStrategicMilestone.getNodeIdString(),
                aStrategicAssetException == null ? null : aStrategicAssetException.getNodeIdString());
    }

    // TODO - create generalized routine
    public ArrayList<ProjectAsset> retrieveProjectAssetListForWorkTaskMoveTarget(String aProjectId, String aWorkPackageException) {
        return getPersistenceTechnologyDelegate().retrieveWorkAssetListForWorkTaskMoveTarget(
                aProjectId, aWorkPackageException);
    }

    // TODO - create generalized routine
    public boolean moveAllProjectAssetsIntoProject(
            String aSourceStrateticMilestoneId,
            String aDestinationProjectId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().moveAllProjectAssetsIntoProject(
                aSourceStrateticMilestoneId,
                aDestinationProjectId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

    // TODO - create generalized routine
    public boolean moveSingleProjectAssetIntoProject(
            String aProjectAssetId,
            String anOriginalProjectId,
            String aDestinationProjectId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().moveSingleProjectAssetIntoProject(
                aProjectAssetId,
                anOriginalProjectId,
                aDestinationProjectId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

    // TODO - create generalized routine
    public int getMoveTargetWorkPackageCount(WorkAsset aWorkAsset, WorkPackage aWorkPackageException) {
        return getPersistenceTechnologyDelegate().dbGetMoveTargetWorkPackageCount(aWorkAsset, aWorkPackageException);
    }

    // ORPHANS

    // TODO - create generalized routine
    public boolean orphanAllProjectAssetsFromStrategicMilestone(String aStrategicMilestoneId, boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().orphanAllProjectAssetsFromStrategicMilestone(aStrategicMilestoneId, bAtomicTransaction);
    }

    // TODO - create generalized routine
    public boolean orphanAllProjectAssetsFromProject(String aProjectId, boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().orphanAllProjectAssetsFromProject(aProjectId, bAtomicTransaction);
    }

    // TODO - create generalized routine
    public boolean orphanSingleProjectAssetFromStrategicMilestone(String aProjectAssetId, String aStrategicMilestoneId, boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().orphanSingleProjectAssetFromStrategicMilestone(aProjectAssetId, aStrategicMilestoneId, bAtomicTransaction);
    }

    // TODO - create generalized routine
    public boolean orphanSingleProjectAssetFromProject(String aProjectAssetId, String aProjectId, boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().orphanSingleProjectAssetFromProject(aProjectAssetId, aProjectId, bAtomicTransaction);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - STRATEGIC ASSET  ///////////////////////////////////////////////////////////////////////////////
    
    public StrategicAsset retrieveStrategicAsset(String aStrategicAssetId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.STRATEGIC_ASSET, aStrategicAssetId);
    }

    public ArrayList<StrategicAsset> retrieveStrategicAssetList(Project aProject) {
        return retrieveStrategicAssetList(aProject, null);
    }

    public ArrayList<StrategicAsset> retrieveStrategicAssetList(Project aProject, StrategicAsset aStrategicAssetException) {
        return retrieveStrategicAssetListForProject(aProject.getNodeIdString(),
                aStrategicAssetException == null ? null : aStrategicAssetException.getNodeIdString());
    }

    public ArrayList<StrategicAsset> retrieveStrategicAssetListForProject(String aProjectId, String aStrategicAssetExceptionId) {
        return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.STRATEGIC_ASSET,
                StrategicAssetMetaData.column_PROJECT_ID,
                aProjectId,
                aStrategicAssetExceptionId,
                sort_spec__SEQUENCE);
    }

    public ArrayList<StrategicAsset> retrieveStrategicAssetList(StrategicMilestone aStrategicMilestone) {
        return retrieveStrategicAssetList(aStrategicMilestone, null);
    }

    public ArrayList<StrategicAsset> retrieveStrategicAssetList(StrategicMilestone aStrategicMilestone, StrategicAsset aStrategicAssetException) {
        return retrieveStrategicAssetListForStrategicMilestone(aStrategicMilestone.getNodeIdString(),
                aStrategicAssetException == null ? null : aStrategicAssetException.getNodeIdString());
    }

    public ArrayList<StrategicAsset> retrieveStrategicAssetListForStrategicMilestone(String aStrategicMilestoneId, String aStrategicAssetExceptionId) {
        return retrieveFmmNodeListFromLinkTable(
                FmmNodeDefinition.STRATEGIC_ASSET,
                aStrategicAssetExceptionId,
                FmmNodeDefinition.STRATEGIC_COMMITMENT,
                StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID,
                FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName() + "." + StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID,
                aStrategicMilestoneId,
                FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName() + "." + StrategicCommitmentMetaData.column_SEQUENCE + " ASC");
    }

    private StrategicAsset createStrategicAssetForStrategicMilestone(
            String aStrategicAssetHeadline,
            FmmHeadlineNode aStrategicMilestone,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd) {
        startTransaction();
        StrategicAsset theNewStrategicAsset = new StrategicAsset(
                new NodeId(FmmNodeDefinition.STRATEGIC_ASSET),
                aStrategicAssetHeadline);
        boolean isSuccess = insertStrategicAsset(theNewStrategicAsset, false);
        isSuccess &= createStrategicCommitment(aStrategicMilestone, aPeerNode, bSequenceAtEnd, theNewStrategicAsset);
        endTransaction(isSuccess);
        return isSuccess ? theNewStrategicAsset : null;
    }

    private boolean insertStrategicAsset(StrategicAsset aStrategicAsset, boolean bAtomicTransaction) {
        return fractalInsertFmmCompletionNode(aStrategicAsset, bAtomicTransaction);
    }

    public boolean updateStrategicAsset(StrategicAsset aStrategicAsset, boolean bAtomicTransaction) {
        return fractalUpdateFmmCompletionNode(aStrategicAsset, bAtomicTransaction);
    }

    public boolean existsStrategicAsset(String aStrategicAssetId) {
        return existsSimpleIdTable(FmmNodeDefinition.STRATEGIC_ASSET, aStrategicAssetId);
    }

    public boolean deleteStrategicAsset(StrategicAsset aStrategicAsset, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = deleteSimpleIdTableRow(FmmNodeDefinition.STRATEGIC_COMMITMENT, StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID, aStrategicAsset.getNodeIdString(), false);
        isSuccess &= fractalDeleteFmmCompletionNode(aStrategicAsset, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    // MOVE

    public boolean moveAllStrategicAssetsIntoStrategicMilestone(
            String aSourceStrateticMilestoneId,
            String aDestinationStrategicMilestoneId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().moveAllStrategicAssetsIntoStrategicMilestone(
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
        return getPersistenceTechnologyDelegate().moveSingleStrategicAssetIntoStrategicMilestone(
                aStrategicAssetId,
                anOriginalStrategicMilestonetId,
                aDestinationStrategicMilestoneId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

    public boolean moveSingleStrategicAssetIntoProject(
            String aStrategicAssetId,
            String anOriginalProjectId,
            String aDestinationProjectId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().moveSingleStrategicAssetIntoProject(
                aStrategicAssetId,
                anOriginalProjectId,
                aDestinationProjectId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

    // DEMOTE / PROMOTE

    public boolean demoteStrategicAssetToProjectAsset(
            StrategicAsset aStrategicAsset,
            boolean bAtomicTransaction ) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        aStrategicAsset.setStrategic(false);
        boolean isSuccess = updateStrategicAsset(aStrategicAsset,false);
        isSuccess &= deleteStrategicCommitment(aStrategicAsset, false);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    // ORPHANS

    // TODO - create generalized routine
    public boolean orphanSingleStrategicAssetFromProject(String aStrategicAssetId, String aProjectId, boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().orphanSingleStrategicAssetFromProject(aStrategicAssetId, aProjectId, bAtomicTransaction);
    }

    // TODO - create generalized routine
    public ArrayList<StrategicAsset> listStrategicAssetOrphansFromProject() {
        return getPersistenceTechnologyDelegate().retrieveStrategicAssetOrphanListFromProject();
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FISCAL YEAR  /////////////////////////////////////////////////////////////////////////////////

    public FiscalYear retrieveFiscalYear(String aFiscalYearId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.FISCAL_YEAR, aFiscalYearId);
    }

    public ArrayList<FiscalYear> retrieveFiscalYearList(FmsOrganization anOrganization) {
        return retrieveFiscalYearList(anOrganization, null);
    }

	public ArrayList<FiscalYear> retrieveFiscalYearList(FmsOrganization anOrganization, FiscalYear aFiscalYearException) {
		return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.FISCAL_YEAR,
                FiscalYearMetaData.column_ORGANIZATION_ID,
                anOrganization.getNodeIdString(),
                aFiscalYearException == null ? null : aFiscalYearException.getNodeIdString(),
                FiscalYearMetaData.column_YEAR_NUMBER);
	}

    public FiscalYear createFiscalYear(String aYearString) {
        FiscalYear theFiscalYear = new FiscalYear(
                new NodeId(FmmNodeDefinition.FISCAL_YEAR),
                FmmDatabaseService.getActiveMediator().getFmmOwner(),
                aYearString);
        boolean isSuccess = insertFiscalYear(theFiscalYear, true);
        return isSuccess ? theFiscalYear : null;
    }

    public boolean insertFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
        return fractalInsertFmmCompletionNode(aFiscalYear, bAtomicTransaction);
    }

    public boolean updateFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
        return fractalUpdateFmmCompletionNode(aFiscalYear, bAtomicTransaction);
    }

    public boolean existsFiscalYear(String aFiscalYearId) {
        return existsSimpleIdTable(FmmNodeDefinition.FISCAL_YEAR, aFiscalYearId);
    }

    public boolean deleteFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = deleteAllFiscalYearHolidayBreaks(aFiscalYear, false);
        isSuccess &= fractalDeleteFmmCompletionNode(aFiscalYear, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    // MOVE

    // TODO - create generalized routine
	public int countFiscalYearForStrategicMilestoneMoveTarget(FmsOrganization anFmsOrganization, FiscalYear aFiscalYearTargetException) {
		return getPersistenceTechnologyDelegate().countFiscalYearForStrategicMilestoneMoveTarget(anFmsOrganization, aFiscalYearTargetException);
	}

    // TODO - create generalized routine
    public ArrayList<FiscalYear> retrieveFiscalYearListForStrategicMilestoneMoveTarget(FmsOrganization anFmsOrganization, FiscalYear aFiscalYearException) {
        return getPersistenceTechnologyDelegate().retrieveFiscalYearListForStrategicMilestoneMoveTarget(anFmsOrganization, aFiscalYearException);
    }

    // TODO - create generalized routine
	public int countFiscalYearForProjectAssetMoveTarget(FmsOrganization anFmsOrganization, StrategicMilestone aStrategicMilestonException) {
		return getPersistenceTechnologyDelegate().countFiscalYearForProjectAssetMoveTarget(anFmsOrganization, aStrategicMilestonException);
	}

    // TODO - create generalized routine
	public ArrayList<FiscalYear> retrieveFiscalYearListForProjectAssetMoveTarget(FmsOrganization anFmsOrganization, StrategicMilestone aStrategicMilestonException) {
		return getPersistenceTechnologyDelegate().retrieveFiscalYearForProjectAssetMoveTarget(anFmsOrganization, aStrategicMilestonException);
	}

    // TODO - create generalized routine
	public int countFiscalYearForWorkPackageMoveTarget(FmsOrganization anFmsOrganization, ProjectAsset aProjectAssetException) {
		return getPersistenceTechnologyDelegate().countFiscalYearForWorkPackageMoveTarget(anFmsOrganization, aProjectAssetException);
	}

    // TODO - create generalized routine
	public ArrayList<FiscalYear> retrieveFiscalYearListForWorkPackageMoveTarget(FmsOrganization anFmsOrganization, ProjectAsset aProjectAssetException) {
		return getPersistenceTechnologyDelegate().retrieveFiscalYearListForWorkPackageMoveTarget(anFmsOrganization, aProjectAssetException);
	}

    // TODO - create generalized routine
	public int countFiscalYearForCadenceMoveTarget(FmsOrganization anFmsOrganization, FiscalYear aFiscalYearTargetException) {
		return getPersistenceTechnologyDelegate().countFiscalYearForCadenceMoveTarget(anFmsOrganization, aFiscalYearTargetException);
	}

    // TODO - create generalized routine
	public ArrayList<FiscalYear> retrieveFiscalYearListForCadenceMoveTarget(FmsOrganization anFmsOrganization, FiscalYear aFiscalYearTargetException) {
		return getPersistenceTechnologyDelegate().retrieveFiscalYearListForCadenceMoveTarget(anFmsOrganization, aFiscalYearTargetException);
	}


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FLYWHEEL CADENCE  ///////////////////////////////////////////////////////////////////

    public Cadence retrieveCadence(String aCadenceId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.CADENCE, aCadenceId);
    }

    public ArrayList<Cadence> retrieveCadenceList(FiscalYear aFiscalYear) {
        return retrieveCadenceListForFiscalYear(aFiscalYear.getNodeIdString());
    }

    public ArrayList<Cadence> retrieveCadenceListForFiscalYear(String aFiscalYearId) {
        return retrieveFmmNodeListFromSimpleIdTable(
            FmmNodeDefinition.CADENCE,
            CadenceMetaData.column_FISCAL_YEAR_ID,
            aFiscalYearId,
            sort_spec__SCHEDULED_END_DATE);
    }
    
    // created in a batch mode for entire year

    public boolean insertCadence(Cadence aCadence, boolean bAtomicTransaction) {
        return fractalInsertFmmCompletionNode(aCadence, bAtomicTransaction);
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
        return fractalUpdateFmmCompletionNode(aCadence, bAtomicTransaction);
    }

    public boolean existsCadence(String aCadenceId) {
        return existsSimpleIdTable(FmmNodeDefinition.CADENCE, aCadenceId);
    }

    public boolean deleteCadence(Cadence aCadence, boolean bAtomicTransaction) {
        return fractalDeleteFmmCompletionNode(aCadence, bAtomicTransaction);
    }

    public boolean deleteAllCadences(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
        return deleteAllCadencesForFiscalYear(aFiscalYear.getNodeIdString(), bAtomicTransaction);
    }

    public boolean deleteAllCadencesForFiscalYear(String aFiscalYearId, boolean bAtomicTransaction) {
        ArrayList<String> theCadenceIdList = getFmmNodeIdList(FmmNodeDefinition.CADENCE, CadenceMetaData.column_FISCAL_YEAR_ID, aFiscalYearId, null);
        if(theCadenceIdList.size() < 1) {
            return true;
        }
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = true;
        Cadence theCadence;
        for(String theNodeIdString : theCadenceIdList) {
            theCadence = retrieveCadence(theNodeIdString);
            isSuccess &= deleteAllWorkPlans(theCadence, bAtomicTransaction);
            isSuccess &= deleteCadence(theCadence, false);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - CADENCE COMMITMENT  ////////////////////////////////////////////////////////////////////////

    public CadenceCommitment retrieveCadenceCommitment(String aCadenceCommitmentId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.CADENCE_COMMITMENT, aCadenceCommitmentId);
    }

    public CadenceCommitment retrieveCadenceCommitment(WorkPackage aWorkPackage) {
        return retrieveCadenceCommitmentForWorkPackage(aWorkPackage.getNodeIdString());
    }

    public CadenceCommitment retrieveCadenceCommitmentForWorkPackage(String aWorkPackageId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.CADENCE_COMMITMENT, CadenceCommitmentMetaData.column_WORK_PACKAGE_ID, aWorkPackageId);
    }

    public ArrayList<CadenceCommitment> retrieveCadenceCommitmentList() {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.CADENCE_COMMITMENT);
    }

    public ArrayList<CadenceCommitment> retrieveCadenceCommitmentList(Cadence aCadence) {
        return retrieveCadenceCommitmentListForCadence(aCadence.getNodeIdString());
    }

    public ArrayList<CadenceCommitment> retrieveCadenceCommitmentListForCadence(String aCadenceId) {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.CADENCE_COMMITMENT, CadenceCommitmentMetaData.column_CADENCE_ID, aCadenceId, null);
    }

    private boolean createCadenceCommitment(Cadence aCadence, FmmHeadlineNode aPeerNode, boolean bSequenceAtEnd, WorkPackage aWorkPackage) {
        CadenceCommitment theNewCadenceCommitment = new CadenceCommitment(
            aCadence,
            aWorkPackage );
        int theNewSequenceNumber = initializeNewSequenceNumberForLinkTable(
                FmmNodeDefinition.CADENCE_COMMITMENT,
                CadenceCommitmentMetaData.column_CADENCE_ID,
                aCadence.getNodeIdString(),
                CadenceCommitmentMetaData.column_WORK_PACKAGE_ID,
                aPeerNode == null ? null : aPeerNode.getNodeIdString(),
                bSequenceAtEnd);
        theNewCadenceCommitment.setSequence(theNewSequenceNumber);
        theNewCadenceCommitment.setCompletionCommitmentType(CompletionCommitmentType.NONE);
        return insertCadenceCommitment(theNewCadenceCommitment, false);
    }

    public boolean insertCadenceCommitment(CadenceCommitment aCadenceCommitment, boolean bAtomicTransaction) {
        return insertSimpleIdTableRow(aCadenceCommitment, bAtomicTransaction);
    }

    public boolean existsCadenceCommitment(String aCadenceCommitmentId) {
        return existsSimpleIdTable(FmmNodeDefinition.CADENCE_COMMITMENT, aCadenceCommitmentId);
    }

    public boolean deleteCadenceCommitment(CadenceCommitment aCadenceCommitment, boolean bAtomicTransaction) {
        return deleteSimpleIdTableRow(aCadenceCommitment, bAtomicTransaction);
    }

    public boolean deleteCadenceCommitment(WorkPackage aWorkPackage, boolean bAtomicTransaction) {
        return deleteSimpleIdTableRow(FmmNodeDefinition.CADENCE_COMMITMENT, CadenceCommitmentMetaData.column_WORK_PACKAGE_ID, aWorkPackage.getNodeIdString(), bAtomicTransaction);
    }

    public boolean deleteAllCadenceCommitments(Cadence aCadence, boolean bAtomicTransaction) {
        return deleteSimpleIdTableRow(FmmNodeDefinition.CADENCE_COMMITMENT, CadenceCommitmentMetaData.column_CADENCE_ID, aCadence.getNodeIdString(), bAtomicTransaction);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - WORK PLAN  ///////////////////////////////////////////////////////////////////////////////////

    public WorkPlan retrieveWorkPlan(String aWorkPlanId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.WORK_PLAN, aWorkPlanId);
    }

    public ArrayList<WorkPlan> retrieveWorkPlanList(Cadence aCadence) {
        return retrieveWorkPlanListForCadence(aCadence.getNodeIdString());
    }

    public ArrayList<WorkPlan> retrieveWorkPlanListForCadence(String aCadenceId) {
        return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.WORK_PLAN,
                WorkPlanMetaData.column_CADENCE_ID,
                aCadenceId,
                WorkPlanMetaData.column_SEQUENCE );
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

    public boolean insertWorkPlan(WorkPlan aWorkPlan, boolean bAtomicTransaction) {
        return fractalInsertFmmCompletionNode(aWorkPlan, bAtomicTransaction);
    }

    public boolean updateWorkPlan(WorkPlan aWorkPlan, boolean bAtomicTransaction) {
        return updateSimpleIdTableRow(aWorkPlan, bAtomicTransaction);
    }

    public boolean existsWorkPlan(String aWorkPlanId) {
        return existsSimpleIdTable(FmmNodeDefinition.WORK_PLAN, aWorkPlanId);
    }

    public boolean deleteWorkPlan(WorkPlan aWorkPlan, boolean bAtomicTransaction) {
        return fractalDeleteFmmCompletionNode(aWorkPlan, bAtomicTransaction);
    }

    public boolean deleteAllWorkPlans(Cadence aCadence, boolean bAtomicTransaction) {
        ArrayList<String> theWorkPlanIdList = getFmmNodeIdList(FmmNodeDefinition.WORK_PLAN, WorkPlanMetaData.column_CADENCE_ID, aCadence.getNodeIdString(), null);
        if(theWorkPlanIdList.size() < 1) {
            return true;
        }
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = true;
        WorkPlan theWorkPlan;
        for(String theNodeIdString : theWorkPlanIdList) {
            theWorkPlan = retrieveWorkPlan(theNodeIdString);
            isSuccess &= deleteWorkPlan(theWorkPlan, false);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FISCAL YEAR HOLIDAY BREAK  ///////////////////////////////////////////////////////////////////

    public FiscalYearHolidayBreak retrieveFiscalYearHolidayBreak(String aHolidayBreakId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.FISCAL_YEAR_HOLIDAY_BREAK, aHolidayBreakId);
    }

    public ArrayList<FiscalYearHolidayBreak> retrieveFiscalYearHolidayBreakList(FiscalYear aFiscalYear) {
        return retrieveFiscalYearHolidayBreakListForFiscalYear(aFiscalYear.getNodeIdString());
    }

    public ArrayList<FiscalYearHolidayBreak> retrieveFiscalYearHolidayBreakListForFiscalYear(String aFiscalYearId) {
        return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.FISCAL_YEAR_HOLIDAY_BREAK,
                FiscalYearHolidayBreakMetaData.column_FISCAL_YEAR_ID,
                aFiscalYearId,
                FiscalYearHolidayBreakMetaData.column_HOLIDAY_DATE);
    }
    
    // FiscalYearHolidayBreak is created in a "batch" for the entire year

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

    public boolean insertFiscalYearHolidayBreak(FiscalYearHolidayBreak aFiscalYearHolidayBreak, boolean bAtomicTransaction) {
        return insertSimpleIdTableRow(aFiscalYearHolidayBreak, bAtomicTransaction);
    }

    public boolean updateFiscalYearHolidayBreak(FiscalYearHolidayBreak aFiscalYearHolidayBreak, boolean bAtomicTransaction) {
        return updateSimpleIdTableRow(aFiscalYearHolidayBreak, bAtomicTransaction);
    }

    public boolean existsFiscalYearHolidayBreak(String aFiscalYearHolidayBreakId) {
        return existsSimpleIdTable(FmmNodeDefinition.FISCAL_YEAR_HOLIDAY_BREAK, aFiscalYearHolidayBreakId);
    }

    public boolean deleteFiscalYearHolidayBreak(FiscalYearHolidayBreak aFiscalYearHolidayBreak, boolean bAtomicTransaction) {
        return deleteSimpleIdTableRow(aFiscalYearHolidayBreak, bAtomicTransaction);
    }

    public boolean deleteAllFiscalYearHolidayBreaks(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
        ArrayList<String> theFiscalYearHolidayBreakIdList = getFmmNodeIdList(FmmNodeDefinition.FISCAL_YEAR_HOLIDAY_BREAK, FiscalYearHolidayBreakMetaData.column_FISCAL_YEAR_ID, aFiscalYear.getNodeIdString(), null);
        if(theFiscalYearHolidayBreakIdList.size() < 1) {
            return true;
        }
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = true;
        FiscalYearHolidayBreak theFiscalYearHolidayBreak;
        for(String theNodeIdString : theFiscalYearHolidayBreakIdList) {
            theFiscalYearHolidayBreak = retrieveFiscalYearHolidayBreak(theNodeIdString);
            isSuccess &= deleteFiscalYearHolidayBreak(theFiscalYearHolidayBreak, false);
        }
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FLYWHEEL TEAM  ///////////////////////////////////////////////////////////////////////////////

    public FlywheelTeam getFlywheelTeam(String aFlywheelTeamId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.FLYWHEEL_TEAM, aFlywheelTeamId);
    }

    public ArrayList<FlywheelTeam> retrieveFlywheelTeamList(FmsOrganization anFmsOrganization) {
        return retrieveFlywheelTeamList(anFmsOrganization, null);
    }

    public ArrayList<FlywheelTeam> retrieveFlywheelTeamList(FmsOrganization anFmsOrganization, FlywheelTeam aFlywheelTeamException) {
        return retrieveFlywheelTeamListForOrganization(anFmsOrganization.getNodeIdString(), aFlywheelTeamException == null ? null : aFlywheelTeamException.getNodeIdString());
    }

	public ArrayList<FlywheelTeam> retrieveFlywheelTeamListForOrganization(String anOrganizationId, String aFlywheelTeamExceptionId) {
        return retrieveFmmNodeListFromSimpleIdTable(
            FmmNodeDefinition.FLYWHEEL_TEAM,
            FlywheelTeamMetaData.column_ORGANIZATION_ID,
            anOrganizationId,
            aFlywheelTeamExceptionId,
            sort_spec__HEADLINE);
	}

    private FlywheelTeam createFlywheelTeamForFmsOrganization(String aFlywheelTeamHeadline, FmmHeadlineNode anFmsOrganization) {
        FlywheelTeam theNewFlywheelTeam = new FlywheelTeam(
                new NodeId(FmmNodeDefinition.FLYWHEEL_TEAM),
                aFlywheelTeamHeadline,
                (FmsOrganization) anFmsOrganization );
        boolean isSuccess = insertFlywheelTeam(theNewFlywheelTeam, true);
        return isSuccess ? theNewFlywheelTeam : null;
    }

	public boolean insertFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction) {
		return fractalInsertFmmGovernableNode(aFlywheelTeam, bAtomicTransaction);
	}

	public boolean updateFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction) {
		return fractalUpdateFmmGovernableNode(aFlywheelTeam, bAtomicTransaction);
	}

    public boolean existsFlywheelTeam(String aFlywheelTeamId) {
        return existsSimpleIdTable(FmmNodeDefinition.FLYWHEEL_TEAM, aFlywheelTeamId);
    }

	public boolean deleteFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction) {
		return fractalDeleteFmmGovernableNode(aFlywheelTeam, bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FRAG LOCK  ///////////////////////////////////////////////////////////////////////////////////

    public FragLock retrieveFragLock(String aFragLockId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.FRAG_LOCK, aFragLockId);
    }

	public FragLock retrieveFragLockForParent(String aParentId) {
		return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.FRAG_LOCK, FragLockMetaData.column_PARENT_ID, aParentId);
	}

	public FragLock retrieveFragLockForGrandparent(String aGrandparentId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.FRAG_LOCK, FragLockMetaData.column_GRANDPARENT_ID, aGrandparentId);
	}

    private boolean createFragLock(FmmNodeFragLockableImpl anFmmNodeFragLockableImpl) {
        FragLock theFragLock = new FragLock(anFmmNodeFragLockableImpl);
        return insertFragLock(theFragLock, false);  // always part of a transaction
    }

	public boolean insertFragLock(FragLock aFragLock, boolean bAtomicTransaction) {
		return insertSimpleIdTableRow(aFragLock, bAtomicTransaction);
	}

    public boolean updateFragLock(FragLock aFragLock, boolean bAtomicTransaction) {
        return updateSimpleIdTableRow(aFragLock, bAtomicTransaction);
    }

    public boolean existsFragLock(String aFragLockId) {
        return existsSimpleIdTable(FmmNodeDefinition.FRAG_LOCK, aFragLockId);
    }

	public boolean deleteFragLock(FragLock aFragLock, boolean bAtomicTransaction) {
		return deleteSimpleIdTableRow(aFragLock, bAtomicTransaction);
	}

    private boolean deleteAllFragLocks(FmmHeadlineNode aHeadlineNode) {
        return deleteSimpleIdTableRow(FmmNodeDefinition.FRAG_LOCK, FragLockMetaData.column_GRANDPARENT_ID, aHeadlineNode.getNodeIdString(), false);
    }


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG AUDIT BLOCK  ////////////////////////////////////////////////////////////////////////

    public NodeFragAuditBlock retrieveNodeFragAuditBlock(String aNodeFragId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__AUDIT_BLOCK, aNodeFragId);
    }
    
    public NodeFragAuditBlock retrieveNodeFragAuditBlock(FmmHeadlineNode anFmmHeadlineNode) {
        return retrieveNodeFragAuditBlockForParent(anFmmHeadlineNode.getNodeIdString());
    }

    public NodeFragAuditBlock retrieveNodeFragAuditBlockForParent(String aParentId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__AUDIT_BLOCK, NodeFragAuditBlockMetaData.column_PARENT_ID, aParentId);
    }

    public ArrayList<NodeFragAuditBlock> retrieveNodeFragAuditBlockList() {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__AUDIT_BLOCK);
    }

    public ArrayList<NodeFragAuditBlock> retrieveNodeFragAuditBlockList(DiscussionTopic aDiscussionTopic) {
        return retrieveFmmNodeListFromLinkTable(  // TODO - sortSpec
                FmmNodeDefinition.NODE_FRAG__AUDIT_BLOCK,
                FmmNodeDefinition.DISCUSSION_TOPIC_LINK_TO_NODE_FRAG_AUDIT_BLOCK,
                DiscussionTopicLinkToNodeFragAuditBlockMetaData.column_NODE_FRAG_AUDIT_BLOCK_ID,
                FmmNodeDefinition.DISCUSSION_TOPIC_LINK_TO_NODE_FRAG_AUDIT_BLOCK.getTableName() + "." + DiscussionTopicLinkToNodeFragAuditBlockMetaData.column_DISCUSSION_TOPIC_ID,
                aDiscussionTopic.getNodeIdString(),
                FmmNodeDefinition.DISCUSSION_TOPIC_LINK_TO_NODE_FRAG_AUDIT_BLOCK.getTableName() + "." + DiscussionTopicLinkToNodeFragAuditBlockMetaData.column_SEQUENCE + " ASC");
    }

    private boolean createNodeFragAuditBlock(FmmHeadlineNode anFmmHeadlineNode) {
        NodeFragAuditBlock theNodeFragAuditBlock = new NodeFragAuditBlock(anFmmHeadlineNode);
        boolean isSuccess = insertNodeFragAuditBlock(theNodeFragAuditBlock, false);  // always part of a transaction
        isSuccess &= createFragLock(theNodeFragAuditBlock);
        if(isSuccess) {
            anFmmHeadlineNode.setNodeFragAuditBlock(theNodeFragAuditBlock);
        }
        return isSuccess;
    }

    public boolean insertNodeFragAuditBlock(NodeFragAuditBlock aNodeFragAuditBlock, boolean bAtomicTransaction) {
        return insertSimpleIdTableRow(aNodeFragAuditBlock, bAtomicTransaction);
    }

    public boolean existsNodeFragAuditBlock(String aNodeFragId) {
        return existsSimpleIdTable(FmmNodeDefinition.NODE_FRAG__AUDIT_BLOCK, aNodeFragId);
    }

	private boolean deleteNodeFragAuditBlock(FmmHeadlineNode aHeadlineNode, boolean bAtomicTransaction) {
		return deleteSimpleIdTableRow(FmmNodeDefinition.NODE_FRAG__AUDIT_BLOCK, NodeFragMetaData.column_PARENT_ID, aHeadlineNode.getNodeIdString(), bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG COMPLETION  ////////////////////////////////////////////////////////////////////////

	public NodeFragCompletion retrieveNodeFragCompletion(String aNodeFragId) {
		return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__COMPLETION, aNodeFragId);
	}

    public NodeFragCompletion retrieveNodeFragCompletion(FmmCompletionNode anFmmCompletionNode) {
        return retrieveNodeFragCompletionForParent(anFmmCompletionNode.getNodeIdString());
    }

    public NodeFragCompletion retrieveNodeFragCompletionForParent(String aParentId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__COMPLETION, NodeFragCompletionMetaData.column_PARENT_ID, aParentId);
    }

    public ArrayList<NodeFragCompletion> retrieveNodeFragCompletionList() {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__COMPLETION);
    }

    private boolean createNodeFragCompletion(FmmCompletionNode anFmmCompletionNode) {
        NodeFragCompletion theNodeFragCompletion = new NodeFragCompletion(anFmmCompletionNode);
        boolean isSuccess = insertNodeFragCompletion(theNodeFragCompletion, false);  // always part of a transaction
        if(isSuccess) {
            anFmmCompletionNode.setNodeFragCompletion(theNodeFragCompletion);
        }
        return isSuccess;
    }

    public boolean insertNodeFragCompletion(NodeFragCompletion aNodeFragCompletion, boolean bAtomicTransaction) {
        return insertSimpleIdTableRow(aNodeFragCompletion, bAtomicTransaction);
    }

    public boolean existsNodeFragCompletion(String aNodeFragId) {
        return existsSimpleIdTable(FmmNodeDefinition.NODE_FRAG__COMPLETION, aNodeFragId);
    }

	private boolean deleteNodeFragCompletion(FmmCompletionNode aCompletionNode, boolean bAtomicTransaction) {
        return deleteSimpleIdTableRow(FmmNodeDefinition.NODE_FRAG__COMPLETION, NodeFragMetaData.column_PARENT_ID, aCompletionNode.getNodeIdString(), bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG FSE DOCUMENT  //////////////////////////////////////////////////////////////////////

	public NodeFragFseDocument retrieveNodeFragFseDocument(String aNodeFragId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT, aNodeFragId);
	}

    public NodeFragFseDocument retrieveNodeFragFseDocument(FmmHeadlineNode anFmmHeadlineNode) {
        return retrieveNodeFragFseDocumentForParent(anFmmHeadlineNode.getNodeIdString());
    }

    public NodeFragFseDocument retrieveNodeFragFseDocumentForParent(String aParentId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT, NodeFragCompletionMetaData.column_PARENT_ID, aParentId);
    }

    public ArrayList<NodeFragFseDocument> retrieveNodeFragFseDocumentList() {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT);
    }

    public NodeFragFseDocument retrieveNodeFragFseDocumentForDocumentId(String aDocumentId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT, NodeFragFseDocumentMetaData.column_DOCUMENT_ID, aDocumentId);
    }

    private boolean createNodeFragFseDocument(FmmHeadlineNode anFmmHeadlineNode) {
        NodeFragFseDocument theNodeFragFseDocument = new NodeFragFseDocument(anFmmHeadlineNode);
        boolean isSuccess = insertNodeFragFseDocument(theNodeFragFseDocument, false);  // always part of a transaction
        isSuccess &= createFragLock(theNodeFragFseDocument);
        if(isSuccess) {
            anFmmHeadlineNode.setNodeFragFseDocument(theNodeFragFseDocument);
        }
        return isSuccess;
    }

    public boolean insertNodeFragFseDocument(NodeFragFseDocument aNodeFragFseDocument, boolean bAtomicTransaction) {
        return insertSimpleIdTableRow(aNodeFragFseDocument, bAtomicTransaction);
    }

    public boolean updateNodeFragFseDocument(NodeFragFseDocument aNodeFragFseDocument, boolean bAtomicTransaction) {
        return updateSimpleIdTableRow(aNodeFragFseDocument, bAtomicTransaction);
    }

    public boolean existsNodeFragFseDocument(String aNodeFragId) {
        return existsSimpleIdTable(FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT, aNodeFragId);
    }

    public boolean existsNodeFragFseDocumentForDocumentId(String aDocumentId) {
        return existsSimpleIdTable(FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT, NodeFragFseDocumentMetaData.column_DOCUMENT_ID, aDocumentId);
    }

    private boolean deleteNodeFragFseDocument(FmmHeadlineNode aHeadlineNode, boolean bAtomicTransaction) {
        return deleteSimpleIdTableRow(FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT, NodeFragMetaData.column_PARENT_ID, aHeadlineNode.getNodeIdString(), bAtomicTransaction);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FSE DOCUMENT  ////////////////////////////////////////////////////////////////////////////////

    public FseDocument getFseDocument(String aDocumentId) {
        return getFseDocument(aDocumentId, true);
    }

    public FseDocument getFseDocument(String aDocumentId, boolean bResetModificationState) {
        NodeFragFseDocument theNodeFragFseDocument = retrieveNodeFragFseDocumentForDocumentId(aDocumentId);
        if(theNodeFragFseDocument == null) {
            return null;
        }
        FseDocument theFseDocument = theNodeFragFseDocument.getFseDocument();
        if(bResetModificationState) {
            theFseDocument.resetModificationState();
        }
        return theFseDocument;
    }

    public FseDocument getFseDocument(FmmHeadlineNode anFmmHeadlineNode) {
        return getFseDocumentForParent(anFmmHeadlineNode.getNodeIdString());
    }

    public FseDocument getFseDocumentForParent(String aParentId) {
        return getFseDocumentForParent(aParentId, true);
    }

    public FseDocument getFseDocumentForParent(String aParentId, boolean bResetModificationState) {
        NodeFragFseDocument theNodeFragFseDocument = retrieveNodeFragFseDocumentForParent(aParentId);
        if(theNodeFragFseDocument == null) {
            return null;
        }
        FseDocument theFseDocument = theNodeFragFseDocument.getFseDocument();
        if(bResetModificationState) {
            theFseDocument.resetModificationState();
        }
        return theFseDocument;
    }

    public void updateFseDocument(FmmHeadlineNode anFmmHeadlineNode, FseDocument anFseDocument, boolean bAtomicTransaction) {
        NodeFragFseDocument theNodeFragFseDocument = retrieveNodeFragFseDocumentForDocumentId(anFseDocument.getDocumentId());
        theNodeFragFseDocument.setFseDocument(anFseDocument);
        theNodeFragFseDocument.setRowTimestamp(GcgDateHelper.getCurrentDateTime());
        boolean isSuccess = updateNodeFragFseDocument(theNodeFragFseDocument, bAtomicTransaction);
        if(isSuccess) {
            theNodeFragFseDocument.resetDocumentModificationState();
            anFmmHeadlineNode.setNodeFragFseDocument(theNodeFragFseDocument);
        }
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG GOVERNANCE  ////////////////////////////////////////////////////////////////////////

    public NodeFragGovernance retrieveNodeFragGovernance(String aNodeFragId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__GOVERNANCE, aNodeFragId);
    }

    public NodeFragGovernance retrieveNodeFragGovernance(FmmGovernableNode anFmmGovernableNode) {
        return retrieveNodeFragGovernanceForParent(anFmmGovernableNode.getNodeIdString());
    }

    public NodeFragGovernance retrieveNodeFragGovernanceForParent(String aParentId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__GOVERNANCE, NodeFragGovernanceMetaData.column_PARENT_ID, aParentId);
    }

	public ArrayList<NodeFragGovernance> retrieveNodeFragGovernanceList() {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__GOVERNANCE);
	}

    private boolean createNodeFragGovernance(FmmGovernableNode anFmmGovernableNode) {
        NodeFragGovernance theNodeFragGovernance = new NodeFragGovernance(anFmmGovernableNode);
        boolean isSuccess = insertNodeFragGovernance(theNodeFragGovernance, false);  // always part of a transaction
        isSuccess &= createFragLock(theNodeFragGovernance);
        if(isSuccess) {
            anFmmGovernableNode.setNodeFragGovernance(theNodeFragGovernance);
        }
        return isSuccess;
    }

    public boolean insertNodeFragGovernance(NodeFragGovernance aNodeFragGovernance, boolean bAtomicTransaction) {
        return insertSimpleIdTableRow(aNodeFragGovernance, bAtomicTransaction);
    }

    public boolean updateNodeFragGovernance(FmmGovernableNode anFmmGovernableNode, boolean bAtomicTransaction) {
        NodeFragGovernance theNodeFragGovernance = anFmmGovernableNode.getUpdatedNodeFragGovernance();
        return updateSimpleIdTableRow(theNodeFragGovernance, bAtomicTransaction);
    }

    public boolean existsNodeFragGovernance(String aNodeFragId) {
        return existsSimpleIdTable(FmmNodeDefinition.NODE_FRAG__GOVERNANCE, aNodeFragId);
    }

	private boolean deleteNodeFragGovernance(FmmGovernableNode aGovernableNode, boolean bAtomicTransaction) {
		return deleteSimpleIdTableRow(FmmNodeDefinition.NODE_FRAG__GOVERNANCE, NodeFragMetaData.column_PARENT_ID, aGovernableNode.getNodeIdString(), bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG TRIBKN QUALITY  ////////////////////////////////////////////////////////////////////

    public NodeFragTribKnQuality retrieveNodeFragTribKnQuality(String aNodeFragId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__TRIBKN_QUALITY, aNodeFragId);
    }
    public NodeFragTribKnQuality retrieveNodeFragTribKnQuality(FmmHeadlineNode anFmmHeadlineNode) {
        return retrieveNodeFragTribKnQualityForParent(anFmmHeadlineNode.getNodeIdString());
    }

    public NodeFragTribKnQuality retrieveNodeFragTribKnQualityForParent(String aParentId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__TRIBKN_QUALITY, NodeFragGovernanceMetaData.column_PARENT_ID, aParentId);
    }

	public ArrayList<NodeFragTribKnQuality> retrieveNodeFragTribKnQualityList() {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__TRIBKN_QUALITY);
	}

    private boolean createNodeFragTribKnQuality(FmmHeadlineNode anFmmHeadlineNode) {
        NodeFragTribKnQuality theNodeFragTribKnQuality = new NodeFragTribKnQuality(anFmmHeadlineNode);
        boolean isSuccess = insertNodeFragTribKnQuality(theNodeFragTribKnQuality, false);  // always part of a transaction
        if(isSuccess) {
            anFmmHeadlineNode.setNodeFragTribKnQuality(theNodeFragTribKnQuality);
        }
        return isSuccess;
    }

    public boolean insertNodeFragTribKnQuality(NodeFragTribKnQuality aNodeFragTribKnQuality, boolean bAtomicTransaction) {
        return insertSimpleIdTableRow(aNodeFragTribKnQuality, bAtomicTransaction);
    }

    public boolean updateNodeFragTribKnQuality(FmmHeadlineNode anFmmHeadlineNode, boolean bAtomicTransaction) {
        NodeFragTribKnQuality theNodeFragTribKnQuality = anFmmHeadlineNode.getUpdatedNodeFragTribKnQuality();
        return updateSimpleIdTableRow(theNodeFragTribKnQuality, bAtomicTransaction);
    }

    public boolean existsNodeFragTribKnQuality(String aNodeFragId) {
        return existsSimpleIdTable(FmmNodeDefinition.NODE_FRAG__TRIBKN_QUALITY, aNodeFragId);
    }

	private boolean deleteNodeFragTribKnQuality(FmmHeadlineNode aHeadlineNode, boolean bAtomicTransaction) {
        return deleteSimpleIdTableRow(FmmNodeDefinition.NODE_FRAG__TRIBKN_QUALITY, NodeFragMetaData.column_PARENT_ID, aHeadlineNode.getNodeIdString(), bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG WORK TASK BUDGET  //////////////////////////////////////////////////////////////////

    public NodeFragWorkTaskBudget retrieveNodeFragWorkTaskBudget(String aNodeFragId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__WORK_TASK_BUDGET, aNodeFragId);
    }
    public NodeFragWorkTaskBudget retrieveNodeFragWorkTaskBudget(FmmCompletionNode anFmmCompletionNode) {
        return retrieveNodeFragWorkTaskBudgetForParent(anFmmCompletionNode.getNodeIdString());
    }

    public NodeFragWorkTaskBudget retrieveNodeFragWorkTaskBudgetForParent(String aParentId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__WORK_TASK_BUDGET, NodeFragGovernanceMetaData.column_PARENT_ID, aParentId);
    }

    public ArrayList<NodeFragWorkTaskBudget> retrieveNodeFragWorkTaskBudgetList() {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.NODE_FRAG__WORK_TASK_BUDGET);
    }

    private boolean createNodeFragWorkTaskBudget(FmmCompletionNode anFmmCompletionNode) {
        NodeFragWorkTaskBudget theNodeFragWorkTaskBudget = new NodeFragWorkTaskBudget(anFmmCompletionNode);
        boolean isSuccess = insertNodeFragWorkTaskBudget(theNodeFragWorkTaskBudget, false);  // always part of a transaction
        isSuccess &= createFragLock(theNodeFragWorkTaskBudget);
        if(isSuccess) {
            anFmmCompletionNode.setNodeFragWorkTaskBudget(theNodeFragWorkTaskBudget);
        }
        return isSuccess;
    }

    public boolean insertNodeFragWorkTaskBudget(NodeFragWorkTaskBudget aNodeFragWorkTaskBudget, boolean bAtomicTransaction) {
        return insertSimpleIdTableRow(aNodeFragWorkTaskBudget, bAtomicTransaction);
    }

    public boolean updateNodeFragWorkTaskBudget(FmmCompletionNode anFmmCompletionNode, boolean bAtomicTransaction) {
        NodeFragWorkTaskBudget theNodeFragWorkTaskBudget = anFmmCompletionNode.getUpdatedNodeFragWorkTaskBudget();
        return updateSimpleIdTableRow(theNodeFragWorkTaskBudget, bAtomicTransaction);
    }

    public boolean existsNodeFragWorkTaskBudget(String aNodeFragId) {
        return existsSimpleIdTable(FmmNodeDefinition.NODE_FRAG__WORK_TASK_BUDGET, aNodeFragId);
    }

	private boolean deleteNodeFragWorkTaskBudget(FmmCompletionNode aCompletionNode, boolean bAtomicTransaction) {
        return deleteSimpleIdTableRow(FmmNodeDefinition.NODE_FRAG__WORK_TASK_BUDGET, NodeFragMetaData.column_PARENT_ID, aCompletionNode.getNodeIdString(), bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - WORK TASK  ///////////////////////////////////////////////////////////////////////////////////

    public WorkTask retrieveWorkTask(String aWorkTaskId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.WORK_TASK, aWorkTaskId);
    }

    public ArrayList<WorkTask> retrieveWorkTaskList(WorkPackage aWorkPackage) {
        return retrieveWorkTaskList(aWorkPackage, null);
    }

    public ArrayList<WorkTask> retrieveWorkTaskList(WorkPackage aWorkPackage, WorkTask aWorkTaskException) {
        return retrieveWorkTaskListForWorkPackage(aWorkPackage.getNodeIdString(), aWorkTaskException == null ? null : aWorkTaskException.getNodeIdString());
    }

    public ArrayList<WorkTask> retrieveWorkTaskListForWorkPackage(String aWorkPackageId, String aWorkTaskExceptionId) {
        return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.WORK_TASK,
                WorkTaskMetaData.column_WORK_PACKAGE__ID,
                aWorkPackageId,
                aWorkTaskExceptionId,
                sort_spec__SEQUENCE);
    }

    public ArrayList<WorkTask> retrieveWorkTaskList(WorkPlan aWorkPlan) {
        return retrieveWorkTaskList(aWorkPlan, null);
    }

    public ArrayList<WorkTask> retrieveWorkTaskList(WorkPlan aWorkPlan, WorkTask aWorkTaskException) {
        return retrieveWorkTaskListForWorkPlan(aWorkPlan.getNodeIdString(), aWorkTaskException == null ? null : aWorkTaskException.getNodeIdString());
    }

    public ArrayList<WorkTask> retrieveWorkTaskListForWorkPlan(String aWorkPlanId, String aWorkTaskExceptionId) {
        return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.WORK_TASK,
                WorkTaskMetaData.column_WORK_PLAN__ID,
                aWorkPlanId,
                aWorkTaskExceptionId,
                sort_spec__SEQUENCE);
    }

    private WorkTask createWorkTaskForParent(
            String aHeadline,
            FmmHeadlineNode aParentNode,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd) {
        return aParentNode.getFmmNodeDefinition() == FmmNodeDefinition.WORK_PACKAGE ?
                createWorkTaskForWorkPackage(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd) :
                createWorkTaskForWorkPlan(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
    }

    private WorkTask createWorkTaskForWorkPackage(
            String aWorkTaskHeadline,
            FmmHeadlineNode aWorkPackage,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd) {
        WorkTask theNewWorkTask = new WorkTask(
                new NodeId(FmmNodeDefinition.WORK_TASK),
                aWorkTaskHeadline,
                (WorkPackage) aWorkPackage );
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                FmmNodeDefinition.WORK_TASK,
                WorkTaskMetaData.column_WORK_PACKAGE__ID,
                aWorkPackage,
                aPeerNode,
                bSequenceAtEnd );
        theNewWorkTask.setWorkPackageSequence(theNewSequenceNumber);
        boolean isSuccess = insertWorkTask(theNewWorkTask, true);
        return isSuccess ? theNewWorkTask : null;
    }

    private WorkTask createWorkTaskForWorkPlan(
            String aWorkTaskHeadline,
            FmmHeadlineNode aWorkPlan,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd) {
        WorkTask theNewWorkTask = new WorkTask(
                new NodeId(FmmNodeDefinition.WORK_TASK),
                aWorkTaskHeadline,
                (WorkPlan) aWorkPlan);
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                FmmNodeDefinition.WORK_TASK,
                WorkTaskMetaData.column_WORK_PLAN__ID,
                aWorkPlan,
                aPeerNode,
                bSequenceAtEnd,
                WorkTaskMetaData.column_WORK_PLAN_SEQUENCE );
        theNewWorkTask.setWorkPlanSequence(theNewSequenceNumber);
        boolean isSuccess = insertWorkTask(theNewWorkTask, true);
        return isSuccess ? theNewWorkTask : null;
    }
    
	public boolean insertWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction) {
		return fractalInsertFmmCompletionNode(aWorkTask, bAtomicTransaction);
	}

	public boolean updateWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction) {
		return fractalUpdateFmmCompletionNode(aWorkTask, bAtomicTransaction);
	}

    public boolean existsWorkTask(String aWorkTaskId) {
        return existsSimpleIdTable(FmmNodeDefinition.WORK_TASK, aWorkTaskId);
    }

	public boolean deleteWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction) {
		return fractalDeleteFmmCompletionNode(aWorkTask, bAtomicTransaction);
	}

    // MOVE

    // TODO - create generalized routine
    public boolean moveAllWorkTasksIntoWorkPackage(String aSourceWorkPackageId, String aDestinationWorkPackageId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().moveAllWorkTasksIntoWorkPackage(
                aSourceWorkPackageId,
                aDestinationWorkPackageId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

    // ORPHANS

    // TODO - create generalized routine
    public ArrayList<WorkTask> listWorkTaskOrphansFromWorkPackage() {
        return getPersistenceTechnologyDelegate().retrieveWorkTaskOrphanListFromWorkPackage();
    }

    // TODO - create generalized routine
    public ArrayList<WorkTask> listWorkTaskOrphansFromWorkPlan() {
        return getPersistenceTechnologyDelegate().retrieveWorkTaskOrphanListFromWorkPlan();
    }

    // TODO - create generalized routine
    public boolean orphanAllWorkTasksFromWorkPackage(String aWorkPackageId, boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().orphanAllWorkTasksFromWorkPackage(aWorkPackageId, bAtomicTransaction);
    }

    // TODO - create generalized routine
    public boolean orphanSingleWorkTaskFromWorkPackage(String aWorkTaskId, String aWorkPackageId, boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().orphanSingleWorkTaskFromWorkPackage(aWorkTaskId, aWorkPackageId, bAtomicTransaction);
    }

    // TODO - create generalized routine
    public boolean adoptOrphanWorkTaskIntoWorkPackage(String aWorkTaskId, String aWorkPackageId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().adoptOrphanWorkTaskIntoWorkPackage(
                aWorkTaskId,
                aWorkPackageId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FMM CONFIGURATION  ///////////////////////////////////////////////////////////////////////////

    public FmmConfiguration retrieveFmmConfiguration(String anFmmConfigurationId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.FMM_CONFIGURATION, anFmmConfigurationId);
    }

	public ArrayList<FmmConfiguration> retrieveFmmConfigurationList() {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.FMM_CONFIGURATION);
	}

    public ArrayList<FmmConfiguration> retrieveFmmConfigurationListForFmsOrganization(String anFmsOrganizationId) {
        return retrieveFmmConfigurationListForFmsOrganization(anFmsOrganizationId, null);
    }

    public ArrayList<FmmConfiguration> retrieveFmmConfigurationListForFmsOrganization(String anFmsOrganizationId, String anFmmConfigurationExceptionId) {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.FMM_CONFIGURATION, FmmConfigurationMetaData.column_ORGANIZATION_ID, anFmsOrganizationId, anFmmConfigurationExceptionId);
    }

	public boolean insertFmmConfiguration(FmmConfiguration anFmmConfiguration, boolean bAtomicTransaction) {
		return fractalInsertFmmGovernableNode(anFmmConfiguration, bAtomicTransaction);
	}

	public boolean updateFmmConfiguration(FmmConfiguration anFmmConfiguration, boolean bAtomicTransaction) {
		return fractalUpdateFmmGovernableNode(anFmmConfiguration, bAtomicTransaction);
	}

    public boolean existsFmmmConfiguration(String anFmmConfigurationId) {
        return existsSimpleIdTable(FmmNodeDefinition.FMM_CONFIGURATION, anFmmConfigurationId);
    }

	public boolean deleteFmmConfiguration(FmmConfiguration anFmmConfiguration, boolean bAtomicTransaction) {
		return fractalDeleteFmmGovernableNode(anFmmConfiguration, bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FMS ORGANIZATION  ////////////////////////////////////////////////////////////////////////////

    public FmsOrganization retrieveFmsOrganization(String anFmsOrganizationId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.FMS_ORGANIZATION, anFmsOrganizationId);
    }

	public ArrayList<FmsOrganization> retrieveFmsOrganizationList() {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.FMS_ORGANIZATION);
	}

	public boolean insertFmsOrganization(FmsOrganization anFmsOrganization, boolean bAtomicTransaction) {
		return fractalInsertFmmGovernableNode(anFmsOrganization, bAtomicTransaction);
	}

	public boolean updateFmsOrganization(FmsOrganization anFmsOrganization, boolean bAtomicTransaction) {
		return fractalUpdateFmmGovernableNode(anFmsOrganization, bAtomicTransaction);
	}

    public boolean existsFmsOrganization(String anFmsOrganizationId) {
        return existsSimpleIdTable(FmmNodeDefinition.FMS_ORGANIZATION, anFmsOrganizationId);
    }

	public boolean deleteFmsOrganization(FmsOrganization anFmsOrganization, boolean bAtomicTransaction) {
		return fractalDeleteFmmGovernableNode(anFmsOrganization, bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - ORGANIZATION COMMUNITY MEMBER  ///////////////////////////////////////////////////////////////

    public OrganizationCommunityMember retrieveOrganizationCommunityMember(String anOrganizationCommunityMemberId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER, anOrganizationCommunityMemberId);
    }

	public ArrayList<OrganizationCommunityMember> retrieveOrganizationCommunityMemberList() {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER);
	}

	public boolean insertOrganizationCommunityMember(OrganizationCommunityMember anOrganizationCommunityMember, boolean bAtomicTransaction) {
		return insertSimpleIdTableRow(anOrganizationCommunityMember, bAtomicTransaction);
	}

	public boolean deleteOrganizationCommunityMember(OrganizationCommunityMember anOrganizationCommunityMember, boolean bAtomicTransaction) {
		return deleteSimpleIdTableRow(anOrganizationCommunityMember, bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - PDF PUBLICATION  /////////////////////////////////////////////////////////////////////////////

    public PdfPublication retrievePdfPublication(String aPdfPublicationId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.PDF_PUBLICATION, aPdfPublicationId);
    }

	public ArrayList<PdfPublication> retrievePdfPublicationList() {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.PDF_PUBLICATION);
	}

	public ArrayList<PdfPublication> retrievePdfPublicationListForFmmHeadlineNode(FmmHeadlineNode anFmmHeadlineNode) {
		return retrievePdfPublicationListForFmmHeadlineNode(anFmmHeadlineNode.getNodeIdString());
	}

    public ArrayList<PdfPublication> retrievePdfPublicationListForFmmHeadlineNode(String anFmmHeadlineNodeId) {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.PDF_PUBLICATION, PdfPublicationMetaData.column_HEADLINE_NODE_ID, anFmmHeadlineNodeId, sort_spec__ROW_TIMESTAMP);
    }

	public ArrayList<PdfPublication> retrievePdfPublicationList(FmmHeadlineNode anFmmHeadlineNode, CommunityMember aCommunityMember) {
		return retrievePdfPublicationListForFmmHeadlineNodeAndCommunityMember(anFmmHeadlineNode.getNodeIdString(), aCommunityMember.getNodeIdString());
	}

    public ArrayList<PdfPublication> retrievePdfPublicationListForFmmHeadlineNodeAndCommunityMember(String anFmmHeadlineNodeId, String aCommunityMemberId) {
        return retrieveFmmNodeListFromSimpleIdTableWhere(
                FmmNodeDefinition.PDF_PUBLICATION,
                PdfPublicationMetaData.column_HEADLINE_NODE_ID + " = '" + anFmmHeadlineNodeId + "' " +
                    " AND " + PdfPublicationMetaData.column_COMMUNITY_MEMBER_ID + " = '" + aCommunityMemberId + "' ",
                null,
                sort_spec__ROW_TIMESTAMP );
    }

	public boolean insertPdfPublication(PdfPublication aPdfPublication, boolean bAtomicTransaction) {
		return insertSimpleIdTableRow(aPdfPublication, bAtomicTransaction);
	}

	public boolean deletePdfPublication(PdfPublication aPdfPublication, boolean bAtomicTransaction) {
		return deleteSimpleIdTableRow(aPdfPublication, bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - STRATEGIC COMMITMENT  ////////////////////////////////////////////////////////////////////////

    public StrategicCommitment retrieveStrategicCommitment(String aStrategicCommitmentId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.STRATEGIC_COMMITMENT, aStrategicCommitmentId);
    }

    public StrategicCommitment retrieveStrategicCommitment(StrategicAsset aStrategicAsset) {
        return retrieveStrategicCommitmentForStrategicAsset(aStrategicAsset.getNodeIdString());
    }

    public StrategicCommitment retrieveStrategicCommitmentForStrategicAsset(String aStrategicAssetId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.STRATEGIC_COMMITMENT, StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID, aStrategicAssetId);
    }

	public ArrayList<StrategicCommitment> retrieveStrategicCommitmentList() {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.STRATEGIC_COMMITMENT);
	}

    public ArrayList<StrategicCommitment> retrieveStrategicCommitmentList(StrategicMilestone aStrategicMilestone) {
        return retrieveStrategicCommitmentListForStrategicMilestone(aStrategicMilestone.getNodeIdString());
    }

	public ArrayList<StrategicCommitment> retrieveStrategicCommitmentListForStrategicMilestone(String aStrategicMilestoneId) {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.STRATEGIC_COMMITMENT, StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID, aStrategicMilestoneId, null);
	}

    private boolean createStrategicCommitment(FmmHeadlineNode aStrategicMilestone, FmmHeadlineNode aPeerNode, boolean bSequenceAtEnd, StrategicAsset aStrategicAsset) {
        StrategicCommitment theNewStrategicCommitment = new StrategicCommitment(
                (StrategicMilestone) aStrategicMilestone,
                aStrategicAsset );
        int theNewSequenceNumber = initializeNewSequenceNumberForLinkTable(
                FmmNodeDefinition.STRATEGIC_COMMITMENT,
                StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID,
                aStrategicMilestone.getNodeIdString(),
                StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID,
                aPeerNode == null ? null : aPeerNode.getNodeIdString(),
                bSequenceAtEnd);
        theNewStrategicCommitment.setSequence(theNewSequenceNumber);
        theNewStrategicCommitment.setCompletionCommitmentType(CompletionCommitmentType.NONE);
        return insertStrategicCommitment(theNewStrategicCommitment, false);
    }

	public boolean insertStrategicCommitment(StrategicCommitment aStrategicCommitment, boolean bAtomicTransaction) {
        return insertSimpleIdTableRow(aStrategicCommitment, bAtomicTransaction);
	}

    public boolean existsStrategicCommitment(String aStrategicCommitmentId) {
        return existsSimpleIdTable(FmmNodeDefinition.STRATEGIC_COMMITMENT, aStrategicCommitmentId);
    }

	public boolean deleteStrategicCommitment(StrategicCommitment aStrategicCommitment, boolean bAtomicTransaction) {
		return deleteSimpleIdTableRow(aStrategicCommitment, bAtomicTransaction);
	}

    public boolean deleteStrategicCommitment(StrategicAsset aStrategicAsset, boolean bAtomicTransaction) {
        return deleteSimpleIdTableRow(FmmNodeDefinition.STRATEGIC_COMMITMENT, StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID, aStrategicAsset.getNodeIdString(), bAtomicTransaction);
    }


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - STRATEGIC MILESTONE  /////////////////////////////////////////////////////////////////////////

    public StrategicMilestone retrieveStrategicMilestone(String aStrategicMilestoneId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.STRATEGIC_MILESTONE, aStrategicMilestoneId);
    }

    public ArrayList<StrategicMilestone> retrieveStrategicMilestoneList(FiscalYear aFiscalYear) {
        return retrieveStrategicMilestoneList(aFiscalYear, null);
    }

    public ArrayList<StrategicMilestone> retrieveStrategicMilestoneList(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestoneException) {
        return retrieveStrategicMilestoneListForFiscalYear(aFiscalYear.getNodeIdString(), aStrategicMilestoneException == null ? null :aStrategicMilestoneException.getNodeIdString());
    }

	public ArrayList<StrategicMilestone> retrieveStrategicMilestoneListForFiscalYear(String aFiscalYearId, String aStrategicMilestoneExceptionId) {
        return retrieveFmmNodeListFromSimpleIdTable(FmmNodeDefinition.STRATEGIC_MILESTONE, StrategicMilestoneMetaData.column_FISCAL_YEAR_ID, aFiscalYearId, aStrategicMilestoneExceptionId, sort_spec__SEQUENCE);
	}

	private StrategicMilestone createStrategicMilestoneForParent(
            String aHeadline,
            FmmHeadlineNode aFiscalYear,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd) {
		StrategicMilestone theNewStrategicMilestone = new StrategicMilestone(
				new NodeId(FmmNodeDefinition.STRATEGIC_MILESTONE), aHeadline, (FiscalYear) aFiscalYear );
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                FmmNodeDefinition.STRATEGIC_MILESTONE,
                StrategicMilestoneMetaData.column_FISCAL_YEAR_ID,
                aFiscalYear,
                aPeerNode,
                bSequenceAtEnd );
        theNewStrategicMilestone.setSequence(theNewSequenceNumber);
		boolean isSuccess = insertStrategicMilestone(theNewStrategicMilestone, true);
		return isSuccess ? theNewStrategicMilestone : null;
	}

	private boolean insertStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
		return fractalInsertFmmCompletionNode(aStrategicMilestone, bAtomicTransaction);
	}

    public boolean updateTargetDate(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
        // TODO - implement enhanced date/schedule specific logic  ???
        return updateStrategicMilestone(aStrategicMilestone, bAtomicTransaction);
    }

	public boolean updateStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
		return fractalUpdateFmmCompletionNode(aStrategicMilestone, bAtomicTransaction);
	}

    public boolean existsStrategicMilestone(String aStrategicMilestoneId) {
        return existsSimpleIdTable(FmmNodeDefinition.STRATEGIC_MILESTONE, aStrategicMilestoneId);
    }

	public boolean deleteStrategicMilestones(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
		boolean theBoolean = true;
		ArrayList<StrategicMilestone> theStrategicMilestoneList = retrieveStrategicMilestoneList(aFiscalYear);
		for(StrategicMilestone theStrategicMilestone : theStrategicMilestoneList) {
			theBoolean = theBoolean && deleteStrategicMilestone(theStrategicMilestone, bAtomicTransaction);
		}
		return theBoolean;
	}

	public boolean deleteStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
		return fractalDeleteFmmCompletionNode(aStrategicMilestone, bAtomicTransaction);
	}

    // MOVE

    // TODO - create generalized routine
    public int countStrategicMilestoneForProjectAssetMoveTarget(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestonException) {
        return getPersistenceTechnologyDelegate().countStrategicMilestoneForProjectAssetMoveTarget(aFiscalYear, aStrategicMilestonException);
    }

    // TODO - create generalized routine
    public ArrayList<StrategicMilestone> retrieveStrategicMilestoneListForProjectAssetMoveTarget(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestonException) {
        return getPersistenceTechnologyDelegate().retrieveStrategicMilestoneListForProjectAssetMoveTarget(aFiscalYear, aStrategicMilestonException);
    }

    // TODO - create generalized routine
    public int countStrategicMilestoneForWorkPackageMoveTarget(FiscalYear aFiscalYear, ProjectAsset aProjectAssetException) {
        return getPersistenceTechnologyDelegate().countStrategicMilestoneForWorkPackageMoveTarget(aFiscalYear, aProjectAssetException);
    }

    // TODO - create generalized routine
    public ArrayList<StrategicMilestone> retrieveStrategicMilestoneListForWorkPackageMoveTarget(
            FiscalYear aFiscalYear, ProjectAsset aProjectAssetException) {
        return getPersistenceTechnologyDelegate().retrieveStrategicMilestoneListForWorkPackageMoveTarget(aFiscalYear, aProjectAssetException);
    }

    // TODO - create generalized routine
	public boolean moveAllStrategicMilestonesIntoFiscalYear(
            String aCurrentFiscalYearId,
            String aDestinationFiscalYearId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
		int theDbRowCount = getPersistenceTechnologyDelegate().moveAllStrategicMilestonesIntoFiscalYear(
                aCurrentFiscalYearId,
                aDestinationFiscalYearId,
                bSequenceAtEnd,
                bAtomicTransaction);
		return theDbRowCount > 0;  // TODO - what is failure ?
	}

    // TODO - create generalized routine
	public boolean moveSingleStrategicMilestoneIntoFiscalYear(
            String aStrategicMilestoneId,
            String anOriginalFiscalYearId,
            String aDestinationFiscalYearId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
		return getPersistenceTechnologyDelegate().moveSingleStrategicMilestoneIntoFiscalYear(
                aStrategicMilestoneId,
                anOriginalFiscalYearId,
                aDestinationFiscalYearId,
                bSequenceAtEnd,
                bAtomicTransaction);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - WORK PACKAGE  ////////////////////////////////////////////////////////////////////////////////

    public WorkPackage retrieveWorkPackage(String aWorkPackageId) {
        return retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition.WORK_PACKAGE, aWorkPackageId);
    }

    public ArrayList<WorkPackage> retrieveWorkPackageList(WorkAsset aWorkAsset) {
        return retrieveWorkPackageList(aWorkAsset, null);
    }

    public ArrayList<WorkPackage> retrieveWorkPackageList(WorkAsset aWorkAsset, WorkPackage aWorkPackageException) {
        return retrieveWorkPackageListForWorkAsset(aWorkAsset.getNodeIdString(), aWorkPackageException == null ? null : aWorkPackageException.getNodeIdString());
    }

	public ArrayList<WorkPackage> retrieveWorkPackageListForWorkAsset(String aWorkAssetId, String aWorkPackageExceptionId) {
        return retrieveFmmNodeListFromSimpleIdTable(
                FmmNodeDefinition.WORK_PACKAGE,
                WorkPackageMetaData.column_WORK_ASSET_ID,
                aWorkAssetId,
                aWorkPackageExceptionId,
                sort_spec__SEQUENCE);
	}

    public ArrayList<WorkPackage> retrieveWorkPackageList(Cadence aCadence) {
        return retrieveWorkPackageList(aCadence, null);
    }

    public ArrayList<WorkPackage> retrieveWorkPackageList(Cadence aCadence, WorkPackage aWorkPackageException) {
        return retrieveWorkPackageListForCadence(aCadence.getNodeIdString(), aWorkPackageException == null ? null : aWorkPackageException.getNodeIdString());
    }

	public ArrayList<WorkPackage> retrieveWorkPackageListForCadence(String aCadenceId, String aWorkPackageExceptionId) {
        return retrieveFmmNodeListFromLinkTable(
                FmmNodeDefinition.WORK_PACKAGE,
                aWorkPackageExceptionId,
                FmmNodeDefinition.CADENCE_COMMITMENT,
                CadenceCommitmentMetaData.column_WORK_PACKAGE_ID,
                FmmNodeDefinition.CADENCE_COMMITMENT.getTableName() + "." + CadenceCommitmentMetaData.column_CADENCE_ID,
                aCadenceId,
                FmmNodeDefinition.CADENCE_COMMITMENT.getTableName() + "." + CadenceCommitmentMetaData.column_SEQUENCE + " ASC");
	}

    private WorkPackage createWorkPackageForParent(
            String aHeadline,
            FmmHeadlineNode aParentNode,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd) {
        return aParentNode.getFmmNodeDefinition() == FmmNodeDefinition.PROJECT_ASSET ||
                aParentNode.getFmmNodeDefinition() == FmmNodeDefinition.STRATEGIC_ASSET ?
                createWorkPackageForProjectAsset(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd) :
                createWorkPackageForCadence(aHeadline, aParentNode, aPeerNode, bSequenceAtEnd);
    }

    private WorkPackage createWorkPackageForProjectAsset(
            String aHeadline,
            FmmHeadlineNode aWorkAsset,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd) {
        WorkPackage theNewWorkPackage = new WorkPackage(aHeadline, (WorkAsset) aWorkAsset);
        int theNewSequenceNumber = initializeNewSequenceNumberForTable(
                FmmNodeDefinition.WORK_PACKAGE,
                WorkPackageMetaData.column_WORK_ASSET_ID,
                aWorkAsset,
                aPeerNode,
                bSequenceAtEnd );
        theNewWorkPackage.setSequence(theNewSequenceNumber);
        boolean isSuccess = insertWorkPackage(theNewWorkPackage, true);
        return isSuccess ? theNewWorkPackage : null;
    }

    private WorkPackage createWorkPackageForCadence(
            String aWorkPackageHeadline,
            FmmHeadlineNode aCadence,
            FmmHeadlineNode aPeerNode,
            boolean bSequenceAtEnd ) {
        startTransaction();
        WorkPackage theNewWorkPackage = new WorkPackage(
                new NodeId(FmmNodeDefinition.WORK_PACKAGE),
                aWorkPackageHeadline );
        boolean isSuccess = insertWorkPackage(theNewWorkPackage, false);
        isSuccess &= createCadenceCommitment((Cadence) aCadence, aPeerNode, bSequenceAtEnd, theNewWorkPackage);
        endTransaction(isSuccess);
        return isSuccess ? theNewWorkPackage : null;
    }

    public boolean insertWorkPackage(WorkPackage aWorkPackage, boolean bAtomicTransaction) {
        return fractalInsertFmmCompletionNode(aWorkPackage, bAtomicTransaction);
    }

    public boolean updateWorkPackage(WorkPackage aWorkPackage, boolean bAtomicTransaction) {
        return fractalUpdateFmmCompletionNode(aWorkPackage, bAtomicTransaction);
    }

    public boolean existsWorkPackage(String aWorkPackageId) {
        return existsSimpleIdTable(FmmNodeDefinition.WORK_PACKAGE, aWorkPackageId);
    }

    public boolean deleteWorkPackage(WorkPackage aWorkPackage, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = fractalDeleteFmmCompletionNode(aWorkPackage, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    // MOVE

	public ArrayList<WorkPackage> retrieveWorkPackageForWorkTaskMoveTarget(ProjectAsset aProjectAsset, WorkPackage aWorkPackageException) {
		return getPersistenceTechnologyDelegate().retrieveWorkPackageListForWorkTaskMoveTarget(
                aProjectAsset.getNodeIdString(), aWorkPackageException == null ? null : aWorkPackageException.getNodeIdString(), true);
	}

	public ArrayList<WorkPackage> retrieveWorkPackageForWorkTaskMoveTarget(Cadence aCadence, WorkPackage aWorkPackageException) {
		return getPersistenceTechnologyDelegate().retrieveWorkPackageListForWorkTaskMoveTarget(
                aCadence.getNodeIdString(), aWorkPackageException == null ? null : aWorkPackageException.getNodeIdString(), true);
	}

    public boolean moveAllWorkPackagesIntoProjectAsset(
            String aSourceProjectAssetId,
            String aDestinationProjectAssetId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        return getPersistenceTechnologyDelegate().moveAllWorkPackagesIntoProjectAsset(
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
        return getPersistenceTechnologyDelegate().moveSingleWorkPackageIntoProjectAsset(
                aWorkPackageId,
                anOriginalProjectAssetId,
                aDestinationProjectAssetId,
                bSequenceAtEnd,
                bAtomicTransaction);
    }

    // ORPHANS

	public ArrayList<WorkPackage> retrieveWorkPackageOrphansFromProjectAsset() {
		return getPersistenceTechnologyDelegate().retrieveWorkPackageOrphanListFromProjectAsset();
	}

	public ArrayList<WorkPackage> retrieveWorkPackageOrphansFromCadence() {
		return getPersistenceTechnologyDelegate().retrieveWorkPackageOrphanListFromCadence();
	}

	public boolean adoptOrphanWorkPackageIntoProjectAsset(
			String aWorkPackageId,
			String aProjectAssetId,
			boolean bSequenceAtEnd,
			boolean bAtomicTransaction ) {
		return getPersistenceTechnologyDelegate().adoptOrphanWorkPackageIntoProjectAsset(
                aWorkPackageId,
                aProjectAssetId,
                bSequenceAtEnd,
                bAtomicTransaction);
	}

    public boolean orphanSingleWorkPackageFromProjectAsset(String aWorkPackageId, String aProjectAssetId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = getPersistenceTechnologyDelegate().orphanSingleWorkPackageFromProjectAsset(aWorkPackageId, aProjectAssetId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean orphanAllWorkPackagesFromCadence(String aCadenceId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = getPersistenceTechnologyDelegate().orphanAllWorkPackagesFromCadence(aCadenceId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean orphanSingleWorkPackageFromCadence(String aWorkPackageId, String aCadenceId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = getPersistenceTechnologyDelegate().orphanSingleWorkPackageFromCadence(aWorkPackageId, aCadenceId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }

    public boolean orphanAllWorkPackagesFromProjectAsset(String aProjectAssetId, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean isSuccess = getPersistenceTechnologyDelegate().orphanAllWorkPackagesFromProjectAsset(aProjectAssetId, bAtomicTransaction);
        if(bAtomicTransaction) {
            endTransaction(isSuccess);
        }
        return isSuccess;
    }


    /////////////////////////////////////////////////////////////////////////////////////
    ////////////  COMMUNITY MEMBER ORGANIZATION GOVERNANCE AUTHORITY  ///////////////////

	public ArrayList<CommunityMember> getGovernanceCandidates(FmsOrganization anFmsOrganization, GovernanceTarget aGovernanceTarget, GovernanceRole aGovernanceRole) {
		return getPersistenceTechnologyDelegate().getGovernanceCandidates(anFmsOrganization, aGovernanceTarget, aGovernanceRole);
	}


    //////////////////////////////////
    //////  Service  /////////////////

    private final IBinder mBinder = new LocalBinder();

    public class LocalBinder extends Binder {
        FmmDatabaseService getService() {
            return getActiveMediator();
        }
    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
