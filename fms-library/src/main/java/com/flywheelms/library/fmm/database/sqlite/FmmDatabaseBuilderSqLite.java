/* @(#)FmmDatabaseBuilderSqLite.java
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

package com.flywheelms.library.fmm.database.sqlite;

import android.database.sqlite.SQLiteDatabase;

import com.flywheelms.library.fmm.database.FmmDatabaseBuilder;
import com.flywheelms.library.fmm.helper.FmmAssetsHelper;
import com.flywheelms.library.fmm.node.impl.commitment.CadenceServiceDeliveryCommitment;
import com.flywheelms.library.fmm.node.impl.commitment.CadenceWorkPackageCommitment;
import com.flywheelms.library.fmm.node.impl.commitment.StrategicCommitment;
import com.flywheelms.library.fmm.node.impl.commitment.WorkTaskAssignment;
import com.flywheelms.library.fmm.node.impl.enumerator.AssignmentCommitmentType;
import com.flywheelms.library.fmm.node.impl.enumerator.CommunityMemberStatus;
import com.flywheelms.library.fmm.node.impl.enumerator.CompletableWorkStatus;
import com.flywheelms.library.fmm.node.impl.enumerator.CompletionCommitmentType;
import com.flywheelms.library.fmm.node.impl.enumerator.FacilitationIssueStatus;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmTribKnElement;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmTribKnQualityNormalizedDescription;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceParticipationType;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceRole;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceSatisfaction;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceTarget;
import com.flywheelms.library.fmm.node.impl.enumerator.LockConfig;
import com.flywheelms.library.fmm.node.impl.enumerator.LockType;
import com.flywheelms.library.fmm.node.impl.enumerator.OrganizationParticipation;
import com.flywheelms.library.fmm.node.impl.enumerator.TeamMemberStatus;
import com.flywheelms.library.fmm.node.impl.event.PdfPublication;
import com.flywheelms.library.fmm.node.impl.governable.Bookshelf;
import com.flywheelms.library.fmm.node.impl.governable.Cadence;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.governable.DiscussionTopic;
import com.flywheelms.library.fmm.node.impl.governable.FacilitationIssue;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.FlywheelTeam;
import com.flywheelms.library.fmm.node.impl.governable.FmsOrganization;
import com.flywheelms.library.fmm.node.impl.governable.FunctionalTeam;
import com.flywheelms.library.fmm.node.impl.governable.Notebook;
import com.flywheelms.library.fmm.node.impl.governable.Portfolio;
import com.flywheelms.library.fmm.node.impl.governable.Project;
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fmm.node.impl.governable.ServiceOffering;
import com.flywheelms.library.fmm.node.impl.governable.ServiceOfferingSla;
import com.flywheelms.library.fmm.node.impl.governable.ServiceRequest;
import com.flywheelms.library.fmm.node.impl.governable.StrategicMilestone;
import com.flywheelms.library.fmm.node.impl.governable.StrategyTeam;
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fmm.node.impl.governable.WorkPlan;
import com.flywheelms.library.fmm.node.impl.governable.WorkTask;
import com.flywheelms.library.fmm.node.impl.headline.FiscalYearHolidayBreak;
import com.flywheelms.library.fmm.node.impl.link.BookshelfLinkToNotebook;
import com.flywheelms.library.fmm.node.impl.link.CommunityMemberFlywheelTeamGovernanceAuthority;
import com.flywheelms.library.fmm.node.impl.link.DiscussionTopicLinkToNodeFragAuditBlock;
import com.flywheelms.library.fmm.node.impl.link.FacilitationIssueNodeTarget;
import com.flywheelms.library.fmm.node.impl.link.FlywheelTeamCommunityMember;
import com.flywheelms.library.fmm.node.impl.link.FunctionalTeamCommunityMember;
import com.flywheelms.library.fmm.node.impl.link.NotebookLinkToDiscussionTopic;
import com.flywheelms.library.fmm.node.impl.link.OrganizationCommunityMember;
import com.flywheelms.library.fmm.node.impl.link.OrganizationGovernanceTarget;
import com.flywheelms.library.fmm.node.impl.link.OrganizationLockConfig;
import com.flywheelms.library.fmm.node.impl.link.ServiceRequestLinkToWorkTask;
import com.flywheelms.library.fmm.node.impl.link.StrategicTeamCommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.CompletionNodeTrash;
import com.flywheelms.library.fmm.node.impl.nodefrag.FragLock;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragCompletion;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragFseDocument;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragGovernance;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragTribKnQuality;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragWorkTaskBudget;
import com.flywheelms.library.fmm.node.impl.supporting.ServiceRequestTriageLog;
import com.flywheelms.library.fmm.node.impl.supporting.TribKnQualityEnumeration;
import com.flywheelms.library.fmm.node.impl.wonky.CommunityMemberOrganizationGovernanceAuthority;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.fmm.repository.FmmConfiguration;
import com.flywheelms.library.fse.model.FseDocumentTransactionType;

import java.io.File;

public class FmmDatabaseBuilderSqLite implements FmmDatabaseBuilder {
	
	public static boolean createDatabaseTables(SQLiteDatabase aSqLiteDatabase) {
		createEnumTables(aSqLiteDatabase);
		createConfigTables(aSqLiteDatabase);
		createGovernableNodeTables(aSqLiteDatabase);
		createSupportingNodeTables(aSqLiteDatabase);
		createLinkTables(aSqLiteDatabase);
		createNodeFragmentTables(aSqLiteDatabase);
		createTransactionTables(aSqLiteDatabase);
		createTemplateTables(aSqLiteDatabase);
		FmmDatabaseInitializerSqLite.insertRows(aSqLiteDatabase);
		createIndexes(aSqLiteDatabase);
		createTriggers(aSqLiteDatabase);
		dbPragma(aSqLiteDatabase);
		return true;
	}

	public static void dbPragma(SQLiteDatabase aSqLiteDatabase) {
        aSqLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
	}

	private static void createEnumTables(SQLiteDatabase aSqLiteDatabase) {
		createTable(aSqLiteDatabase, AssignmentCommitmentType.class);
		createTable(aSqLiteDatabase, CommunityMemberStatus.class);
		createTable(aSqLiteDatabase, CompletionCommitmentType.class);
		createTable(aSqLiteDatabase, FmmNodeDefinition.class);
		createTable(aSqLiteDatabase, GovernanceParticipationType.class);
		createTable(aSqLiteDatabase, GovernanceRole.class);
		createTable(aSqLiteDatabase, GovernanceTarget.class);  // TODO
		createTable(aSqLiteDatabase, GovernanceSatisfaction.class);
		createTable(aSqLiteDatabase, FacilitationIssueStatus.class);
		createTable(aSqLiteDatabase, FseDocumentTransactionType.class);
		createTable(aSqLiteDatabase, LockConfig.class);  // TODO
		createTable(aSqLiteDatabase, LockType.class);
		createTable(aSqLiteDatabase, OrganizationParticipation.class);
		createTable(aSqLiteDatabase, FmmTribKnElement.class);
		createTable(aSqLiteDatabase, FmmTribKnQualityNormalizedDescription.class);
		createTable(aSqLiteDatabase, TribKnQualityEnumeration.class);
		createTable(aSqLiteDatabase, TeamMemberStatus.class);
		createTable(aSqLiteDatabase, CompletableWorkStatus.class);
	}

	private static void createGovernableNodeTables(SQLiteDatabase aSqLiteDatabase) {
		createTable(aSqLiteDatabase, Bookshelf.class);
		createTable(aSqLiteDatabase, DiscussionTopic.class);
		createTable(aSqLiteDatabase, FacilitationIssue.class);
		createTable(aSqLiteDatabase, FiscalYear.class);
		createTable(aSqLiteDatabase, FlywheelTeam.class);
		createTable(aSqLiteDatabase, FunctionalTeam.class);
		createTable(aSqLiteDatabase, Notebook.class);
		createTable(aSqLiteDatabase, FmsOrganization.class);
		createTable(aSqLiteDatabase, Portfolio.class);
		createTable(aSqLiteDatabase, Project.class);
		createTable(aSqLiteDatabase, ProjectAsset.class);
		createTable(aSqLiteDatabase, CadenceServiceDeliveryCommitment.class);
		createTable(aSqLiteDatabase, ServiceOffering.class);
		createTable(aSqLiteDatabase, ServiceOfferingSla.class);
		createTable(aSqLiteDatabase, ServiceRequest.class);
		createTable(aSqLiteDatabase, StrategicCommitment.class);
		createTable(aSqLiteDatabase, StrategicMilestone.class);
		createTable(aSqLiteDatabase, StrategyTeam.class);
		createTable(aSqLiteDatabase, CadenceWorkPackageCommitment.class);
		createTable(aSqLiteDatabase, Cadence.class);
		createTable(aSqLiteDatabase, WorkPackage.class);
		createTable(aSqLiteDatabase, WorkPlan.class);
		createTable(aSqLiteDatabase, WorkTask.class);
	}
	
	private static void createSupportingNodeTables(SQLiteDatabase aSqLiteDatabase) {
		createTable(aSqLiteDatabase, CommunityMember.class);
		createTable(aSqLiteDatabase, FiscalYearHolidayBreak.class);
		createTable(aSqLiteDatabase, FmmConfiguration.class);
		createTable(aSqLiteDatabase, ServiceRequestTriageLog.class);
	}

	private static void createNodeFragmentTables(SQLiteDatabase aSqLiteDatabase) {
		createTable(aSqLiteDatabase, FragLock.class);
		createTable(aSqLiteDatabase, NodeFragCompletion.class);
		createTable(aSqLiteDatabase, NodeFragTribKnQuality.class);
		createTable(aSqLiteDatabase, NodeFragAuditBlock.class);
		createTable(aSqLiteDatabase, NodeFragGovernance.class);
		createTable(aSqLiteDatabase, CompletionNodeTrash.class);
		createTable(aSqLiteDatabase, NodeFragFseDocument.class);
		createTable(aSqLiteDatabase, NodeFragWorkTaskBudget.class);
	}

	private static void createTransactionTables(SQLiteDatabase aSqLiteDatabase) {
		createTable(aSqLiteDatabase, PdfPublication.class);
	}
	
	private static void createConfigTables(@SuppressWarnings("unused") SQLiteDatabase aSqLiteDatabase) {
		// TODO
	}
	
	private static void createLinkTables(SQLiteDatabase aSqLiteDatabase) {
		createTable(aSqLiteDatabase, BookshelfLinkToNotebook.class);
		createTable(aSqLiteDatabase, DiscussionTopicLinkToNodeFragAuditBlock.class);
		createTable(aSqLiteDatabase, FacilitationIssueNodeTarget.class);
		createTable(aSqLiteDatabase, FlywheelTeamCommunityMember.class);
		createTable(aSqLiteDatabase, CommunityMemberFlywheelTeamGovernanceAuthority.class);
		createTable(aSqLiteDatabase, FunctionalTeamCommunityMember.class);
		createTable(aSqLiteDatabase, NotebookLinkToDiscussionTopic.class);
		createTable(aSqLiteDatabase, OrganizationCommunityMember.class);
		createTable(aSqLiteDatabase, CommunityMemberOrganizationGovernanceAuthority.class);
		createTable(aSqLiteDatabase, OrganizationGovernanceTarget.class);
		createTable(aSqLiteDatabase, OrganizationLockConfig.class);
//		createTable(aSqLiteDatabase, PortfolioLinkToProject.class);
		createTable(aSqLiteDatabase, ServiceRequestLinkToWorkTask.class);
		createTable(aSqLiteDatabase, StrategicTeamCommunityMember.class);
		createTable(aSqLiteDatabase, WorkTaskAssignment.class);
	}

	private static void createTemplateTables(@SuppressWarnings("unused") SQLiteDatabase aSqLiteDatabase) {
		// TODO
	}

	private static void createIndexes(@SuppressWarnings("unused") SQLiteDatabase aSqLiteDatabase) {
		// TODO
	}

	private static void createTriggers(@SuppressWarnings("unused") SQLiteDatabase aSqLiteDatabase) {
		// TODO
//		createTriggers(aSqLiteDatabase, WorkTaskAssignment.class);
	}
	
	///////////////////////////////////////////////////////////
	
	private static void createTable(SQLiteDatabase aSqLiteDatabase, Class<? extends FmmNode> aClass) {
		aSqLiteDatabase.execSQL(FmmAssetsHelper.getStringForAsset(getDdlDirectory() + aClass.getSimpleName() + ".ddl"));	
	}
	
//	private static void createTable(SQLiteDatabase aSqLiteDatabase, String aTableName) {
//		aSqLiteDatabase.execSQL(FmmAssetsHelper.getStringForAsset(getDdlDirectory() + aTableName + ".ddl"));	
//	}
	
//	private static void createTriggers(SQLiteDatabase aSqLiteDatabase, Class<? extends FmmNode> aClass) {
//		aSqLiteDatabase.execSQL(FmmAssetsHelper.getStringForAsset(getDdlDirectory() + aClass.getSimpleName() + ".triggers"));	
//	}

	public static String getDdlDirectory() {
		return "database" + File.separator + "ddl" + File.separator + "sqlite" + File.separator;
	}
	
}
