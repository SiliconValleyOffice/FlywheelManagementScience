/* @(#)FmmDatabaseInitializerSqLite.java
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

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.flywheelms.gcongui.deckangl.glyph.DecKanGlDecoratorEnumeration;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlTribKnQualityNormalizedDescription;
import com.flywheelms.library.fmm.database.FmmDatabaseInitializer;
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
import com.flywheelms.library.fmm.node.impl.supporting.TribKnQualityEnumeration;
import com.flywheelms.library.fse.model.FseDocumentTransactionType;

import java.util.List;

public class FmmDatabaseInitializerSqLite extends FmmDatabaseInitializer {

	public static boolean insertRows(SQLiteDatabase aSQLiteDatabase) {
		initializeEnumTables(aSQLiteDatabase);
//		initializeConfigTables();
//		initializeGovernableNodeTables();
//		initializeSupportingNodeTables();
//		initializeLinkNodeTables();
//		initializeNodeFragmentTables();
//		initializeTemplateTables();
		return true;
	}

	private static void initializeEnumTables(SQLiteDatabase aSQLiteDatabase) {
		initializeTribKnElement(aSQLiteDatabase);
		initializeFmmTribKnQualityNormalizedDescription(aSQLiteDatabase);
		initializeTribKnQualityEnumeration(aSQLiteDatabase);
		initializeAssignmentCommitmentType(aSQLiteDatabase);
		initializeCommunityMemberStatus(aSQLiteDatabase);
		initializeCompletionCommitmentType(aSQLiteDatabase);
		initializeFmmNodeDictionary(aSQLiteDatabase);
		initializeFseDocumentTransactionType(aSQLiteDatabase);
		initializeGovernanceParticipationType(aSQLiteDatabase);
		initializeGovernanceRole(aSQLiteDatabase);
		initializeGovernanceSatisfaction(aSQLiteDatabase);
		initializeGovernanceTarget(aSQLiteDatabase);
		initializeIssueStatus(aSQLiteDatabase);
		initializeLockConfig(aSQLiteDatabase);
		initializeLockType(aSQLiteDatabase);
		initializeOrganizationParticipation(aSQLiteDatabase);
		initializeTeamMemberStatus(aSQLiteDatabase);
		initializeCompletableWorkStatus(aSQLiteDatabase);
	}

	private static void initializeTribKnElement(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = FmmTribKnElement.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(FmmTribKnElement theTribKnElement : FmmTribKnElement.values()) {
			theContentValues.put(FmmTribKnElement.name_COLUMN_1, theTribKnElement.getName());
			theContentValues.put(FmmTribKnElement.name_COLUMN_2, theTribKnElement.getDescription());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeFmmTribKnQualityNormalizedDescription(SQLiteDatabase aSQLiteDatabase) {
        FmmTribKnQualityNormalizedDescription.init();
		String theTableName = FmmTribKnQualityNormalizedDescription.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(DecKanGlTribKnQualityNormalizedDescription theTribKnQualityNormalizedDescription : FmmTribKnQualityNormalizedDescription.values()) {
			theContentValues.put(FmmTribKnQualityNormalizedDescription.name_COLUMN_1, theTribKnQualityNormalizedDescription.getName());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeTribKnQualityEnumeration(SQLiteDatabase aSQLiteDatabase) {
		ContentValues theContentValues = new ContentValues();
		insertFmsDecoratorEnumeration(aSQLiteDatabase, theContentValues, FmsDecoratorChildFractals.values());
		insertFmsDecoratorEnumeration(aSQLiteDatabase, theContentValues, FmsDecoratorCompletion.values());
		insertFmsDecoratorEnumeration(aSQLiteDatabase, theContentValues, FmsDecoratorFacilitationIssue.values());
		insertFmsDecoratorEnumeration(aSQLiteDatabase, theContentValues, FmsDecoratorFacilitator.values());
		insertFmsDecoratorEnumeration(aSQLiteDatabase, theContentValues, FmsDecoratorFlywheelCommitment.values());
		insertFmsDecoratorEnumeration(aSQLiteDatabase, theContentValues, FmsDecoratorGovernance.values());
		insertFmsDecoratorEnumeration(aSQLiteDatabase, theContentValues, FmsDecoratorParentFractals.values());
		insertFmsDecoratorEnumeration(aSQLiteDatabase, theContentValues, FmsDecoratorSequence.values());
		insertFmsDecoratorEnumeration(aSQLiteDatabase, theContentValues, FmsDecoratorStory.values());
		insertFmsDecoratorEnumeration(aSQLiteDatabase, theContentValues, FmsDecoratorStrategicCommitment.values());
		insertFmsDecoratorEnumeration(aSQLiteDatabase, theContentValues, FmsDecoratorWorkTaskBudget.values());
		insertFmsDecoratorEnumeration(aSQLiteDatabase, theContentValues, FmsDecoratorWorkTeam.values());
	}

	private static void insertFmsDecoratorEnumeration(
			SQLiteDatabase aSQLiteDatabase,
			ContentValues aContentValues,
			List<DecKanGlDecoratorEnumeration> anEnumerationList) {
		for(DecKanGlDecoratorEnumeration theTribKnQualityEnumeration : anEnumerationList) {
			aContentValues.put(TribKnQualityEnumeration.name_COLUMN_1, theTribKnQualityEnumeration.getName());
			aContentValues.put(TribKnQualityEnumeration.name_COLUMN_2, theTribKnQualityEnumeration.getQualityMetricName());
			aContentValues.put(TribKnQualityEnumeration.name_COLUMN_3, theTribKnQualityEnumeration.getNormalizedDescription().getName());
			aContentValues.put(TribKnQualityEnumeration.name_COLUMN_4, theTribKnQualityEnumeration.getDescription());
			aSQLiteDatabase.insertOrThrow(TribKnQualityEnumeration.class.getSimpleName(), null, aContentValues);
		}
	}

	/////////////
	
	private static void initializeAssignmentCommitmentType(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = AssignmentCommitmentType.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(AssignmentCommitmentType theAssignmentCommitmentType : AssignmentCommitmentType.values()) {
			theContentValues.put(AssignmentCommitmentType.name_COLUMN_1, theAssignmentCommitmentType.getName());
			theContentValues.put(AssignmentCommitmentType.name_COLUMN_2, theAssignmentCommitmentType.getDescription());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeCommunityMemberStatus(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = CommunityMemberStatus.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(CommunityMemberStatus theCommunityMemberStatus : CommunityMemberStatus.values()) {
			theContentValues.put(CommunityMemberStatus.name_COLUMN_1, theCommunityMemberStatus.getCommunityMemberStatusCode());
			theContentValues.put(CommunityMemberStatus.name_COLUMN_2, theCommunityMemberStatus.getName());
			theContentValues.put(CommunityMemberStatus.name_COLUMN_3, theCommunityMemberStatus.getDescription());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeCompletionCommitmentType(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = CompletionCommitmentType.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(CompletionCommitmentType theCompletionCommitmentType : CompletionCommitmentType.values()) {
			theContentValues.put(CompletionCommitmentType.name_COLUMN_1, theCompletionCommitmentType.getCompletionCommitmentType());
			theContentValues.put(CompletionCommitmentType.name_COLUMN_2, theCompletionCommitmentType.getName());
			theContentValues.put(CompletionCommitmentType.name_COLUMN_3, theCompletionCommitmentType.getDescription());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeFmmNodeDictionary(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = FmmNodeDefinition.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(FmmNodeDefinition theFmmNodeDefinition : FmmNodeDefinition.values()) {
			theContentValues.put(FmmNodeDefinition.name_COLUMN_1, theFmmNodeDefinition.getNodeTypeCode());
			theContentValues.put(FmmNodeDefinition.name_COLUMN_2, theFmmNodeDefinition.getNodeTypeName());
			theContentValues.put(FmmNodeDefinition.name_COLUMN_3, theFmmNodeDefinition.getLabelText());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeFseDocumentTransactionType(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = FseDocumentTransactionType.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(FseDocumentTransactionType theFseDocumentTransactionType : FseDocumentTransactionType.values()) {
			theContentValues.put(FseDocumentTransactionType.name_COLUMN_1, theFseDocumentTransactionType.getDocumentTransactionType());
			theContentValues.put(FseDocumentTransactionType.name_COLUMN_2, theFseDocumentTransactionType.getDescription());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeGovernanceRole(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = GovernanceRole.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(GovernanceRole theGovernanceRole : GovernanceRole.values()) {
			theContentValues.put(GovernanceRole.name_COLUMN_1, theGovernanceRole.getName());
			theContentValues.put(GovernanceRole.name_COLUMN_2, theGovernanceRole.getDescription());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeGovernanceSatisfaction(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = GovernanceSatisfaction.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(GovernanceSatisfaction theGovernanceSatisfaction : GovernanceSatisfaction.values()) {
			theContentValues.put(GovernanceSatisfaction.name_COLUMN_1, theGovernanceSatisfaction.getScore());
			theContentValues.put(GovernanceSatisfaction.name_COLUMN_2, theGovernanceSatisfaction.getName());
			theContentValues.put(GovernanceSatisfaction.name_COLUMN_3, theGovernanceSatisfaction.getDescription());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeGovernanceTarget(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = GovernanceTarget.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(GovernanceTarget theGovernanceTarget : GovernanceTarget.values()) {
			theContentValues.put(GovernanceTarget.name_COLUMN_1, theGovernanceTarget.getTargetNodeDictionaryEntry().getNodeTypeName());
			theContentValues.put(GovernanceTarget.name_COLUMN_2, theGovernanceTarget.getSponsorRequirement().toString());
			theContentValues.put(GovernanceTarget.name_COLUMN_3, theGovernanceTarget.getFacilitatorRequirement().toString());
			theContentValues.put(GovernanceTarget.name_COLUMN_4, theGovernanceTarget.getCustomerRequirement().toString());
			theContentValues.put(GovernanceTarget.name_COLUMN_5, theGovernanceTarget.getAdministratorRequirement().toString());
			theContentValues.put(GovernanceTarget.name_COLUMN_6, theGovernanceTarget.isAutoCompletable());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeIssueStatus(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = FacilitationIssueStatus.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(FacilitationIssueStatus theIssueStatus : FacilitationIssueStatus.values()) {
			theContentValues.put(FacilitationIssueStatus.name_COLUMN_1, theIssueStatus.getName());
			theContentValues.put(FacilitationIssueStatus.name_COLUMN_2, theIssueStatus.getDescription());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeLockConfig(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = LockConfig.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(LockConfig theLockConfig : LockConfig.values()) {
			theContentValues.put(LockConfig.name_COLUMN_1, theLockConfig.getTargetNode().getNodeTypeName());
			theContentValues.put(LockConfig.name_COLUMN_2, theLockConfig.getLockType().getName());
			theContentValues.put(LockConfig.name_COLUMN_3, theLockConfig.isSponsorCanLock());
			theContentValues.put(LockConfig.name_COLUMN_4, theLockConfig.isFacilitatorCanLock());
			theContentValues.put(LockConfig.name_COLUMN_5, theLockConfig.isCustomerCanLock());
			theContentValues.put(LockConfig.name_COLUMN_6, theLockConfig.isAdministratorCanLock());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeLockType(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = LockType.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(LockType theLockType : LockType.values()) {
			theContentValues.put(LockType.name_COLUMN_1, theLockType.getName());
			theContentValues.put(LockType.name_COLUMN_2, theLockType.getDescription());
			theContentValues.put(LockType.name_COLUMN_3, theLockType.isMayResequence());
			theContentValues.put(LockType.name_COLUMN_4, theLockType.isMayChangeHeadline());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeOrganizationParticipation(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = OrganizationParticipation.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(OrganizationParticipation theOrganizationParticipation : OrganizationParticipation.values()) {
			theContentValues.put(OrganizationParticipation.name_COLUMN_1, theOrganizationParticipation.getName());
			theContentValues.put(OrganizationParticipation.name_COLUMN_2, theOrganizationParticipation.getDescription());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeTeamMemberStatus(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = TeamMemberStatus.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(TeamMemberStatus theTeamMemberStatus : TeamMemberStatus.values()) {
			theContentValues.put(TeamMemberStatus.name_COLUMN_1, theTeamMemberStatus.getName());
			theContentValues.put(TeamMemberStatus.name_COLUMN_2, theTeamMemberStatus.getDescription());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeGovernanceParticipationType(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = GovernanceParticipationType.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(GovernanceParticipationType theGovernanceParticipationType : GovernanceParticipationType.values()) {
			theContentValues.put(GovernanceParticipationType.name_COLUMN_1, theGovernanceParticipationType.getName());
			theContentValues.put(GovernanceParticipationType.name_COLUMN_2, theGovernanceParticipationType.getDescription());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

	private static void initializeCompletableWorkStatus(SQLiteDatabase aSQLiteDatabase) {
		String theTableName = CompletableWorkStatus.class.getSimpleName();
		ContentValues theContentValues = new ContentValues();
		for(CompletableWorkStatus theWorkStatusType : CompletableWorkStatus.values()) {
			theContentValues.put(CompletableWorkStatus.name_COLUMN_1, theWorkStatusType.getWorkStatusCode());
			theContentValues.put(CompletableWorkStatus.name_COLUMN_2, theWorkStatusType.getName());
			theContentValues.put(CompletableWorkStatus.name_COLUMN_3, theWorkStatusType.getDescription());
			theContentValues.put(CompletableWorkStatus.name_COLUMN_4, theWorkStatusType.getWorkStateColorName());
			aSQLiteDatabase.insertOrThrow(theTableName, null, theContentValues);
		}
	}

}
