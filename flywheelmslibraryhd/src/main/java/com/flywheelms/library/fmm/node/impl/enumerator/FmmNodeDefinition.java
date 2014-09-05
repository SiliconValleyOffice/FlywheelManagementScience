/* @(#)FmmNodeDefinition.java
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

package com.flywheelms.library.fmm.node.impl.enumerator;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.flywheelms.library.R;
import com.flywheelms.library.deckangl.enumerator.DecKanGlNounStateColor;
import com.flywheelms.library.fmm.helper.FmmHelper;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.impl.commitment.FlywheelServiceDeliveryCommitment;
import com.flywheelms.library.fmm.node.impl.commitment.FlywheelWorkPackageCommitment;
import com.flywheelms.library.fmm.node.impl.commitment.StrategicCommitment;
import com.flywheelms.library.fmm.node.impl.commitment.WorkTaskAssignment;
import com.flywheelms.library.fmm.node.impl.event.PdfPublication;
import com.flywheelms.library.fmm.node.impl.governable.Bookshelf;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.governable.DiscussionTopic;
import com.flywheelms.library.fmm.node.impl.governable.FacilitationIssue;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.FlywheelMilestone;
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
import com.flywheelms.library.fmm.node.interfaces.FmmEnumNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.fmm.repository.FmmConfiguration;
import com.flywheelms.library.fmm.transaction.FmmNodeGlyphType;
import com.flywheelms.library.fse.model.FseDocument;
import com.flywheelms.library.fse.model.FseDocumentTransaction;
import com.flywheelms.library.fse.model.FseDocumentTransactionType;
import com.flywheelms.library.fse.model.FseParagraph;
import com.flywheelms.library.gcg.GcgApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;

public enum FmmNodeDefinition implements FmmEnumNode {

	// DO NOT CHANGE NodeIdCode values !!!  
	// THIS IS PERSISTED DATA that is CRITICAL TO THE REFERENTIAL INTEGRITY OF THE MANAGEMENT MODEL !!!
    ASSIGNMENT_COMMITMENT_TYPE (
    		AssignmentCommitmentType.class,
    		"ACT",
    		R.string.fmm_node_definition__assignment_commitment_type__label_text,
    		R.drawable.commitment__none,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10001 ),
    BOOKSHELF (
    		Bookshelf.class,
    		"BKS",
    		R.string.fmm_node_definition__bookshelf__label_text,
    		R.drawable.fmm_noun__bookshelf__gray,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10002 ),
    BOOKSHELF_LINK_TO_NOTEBOOK (
    		BookshelfLinkToNotebook.class,
    		"BLN",
    		R.string.fmm_node_definition__bookshelf_link_to_notebook__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10003 ),
    COMMUNITY_MEMBER (
    		CommunityMember.class,
    		"CMR",
    		R.string.fmm_node_definition__community_member__label_text,
    		R.drawable.fmm_noun__community_member__active,
    		R.drawable.fmm_noun__community_member__unknown,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10004 ),
    COMMUNITY_MEMBER_FLYWHEEL_TEAM_GOVERNANCE_AUTHORITY (
    		CommunityMemberFlywheelTeamGovernanceAuthority.class,
    		"CFG",
    		R.string.fmm_node_definition__community_member_flywheel_team_governance_authority__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10005 ),
    COMMUNITY_MEMBER_ORGANIZATION_GOVERNANCE_AUTHORITY (
    		CommunityMemberOrganizationGovernanceAuthority.class,
    		"COG",
    		R.string.fmm_node_definition__community_member_organization_governance_authority__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10006 ),
    COMMUNITY_MEMBER_STATUS (
    		CommunityMemberStatus.class,
    		"CMS",
    		R.string.fmm_node_definition__community_member_status__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10007 ),
    COMPLETABLE_WORK_STATUS (
    		CompletableWorkStatus.class,
    		"CWS",
    		R.string.fmm_node_definition__completable_work_status__label_text,
    		R.drawable.completable_work_status,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10008 ),
    COMPLETION_COMMITMENT_TYPE (
    		CompletionCommitmentType.class,
    		"CCT",
    		R.string.fmm_node_definition__completion_commitment_type__label_text,
    		R.drawable.commitment__none,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10009 ),
    COMPLETION_NODE_TRASH (
    		CompletionNodeTrash.class,
    		"CNT",
    		R.string.fmm_node_definition__completion_node_trash__label_text,
    		R.drawable.gcg__trash,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10010 ),
    DISCUSSION_TOPIC (
    		DiscussionTopic.class,
    		"DTC",
    		R.string.fmm_node_definition__discussion_topic__label_text,
    		R.drawable.fmm_noun__discussion_topic__gray,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10011 ),
    DISCUSSION_TOPIC_LINK_TO_NODE_FRAG_AUDIT_BLOCK	(
    		DiscussionTopicLinkToNodeFragAuditBlock.class,
    		"DTL",
    		R.string.fmm_node_definition__discussion_topic_link_to_node_frag_audit_block__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10012 ),
    FACILITATION_ISSUE (
    		FacilitationIssue.class,
    		"FIU",
    		R.string.fmm_node_definition__facilitation_issue__label_text,
    		R.drawable.fmm_noun__facilitation_issue__gray,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10013 ),
    FACILITATION_ISSUE_LINK_TO_WORK_TASK (
    		FacilitationIssueNodeTarget.class,
    		"FLW",
    		R.string.fmm_node_definition__facilitation_issue_link_to_work_task__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10014 ),
    FISCAL_YEAR (
    		FiscalYear.class,
    		"FYR",
    		R.string.fmm_node_definition__fiscal_year__label_text,
    		R.drawable.fmm_noun__fiscal_year__gray,
    		0,
    		R.drawable.fms_activity__fiscal_year,
    		R.drawable.fms_dialog__fiscal_year,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10015 ),
	FLYWHEEL_MILESTONE (
			FlywheelMilestone.class,
			"FMS",
			R.string.fmm_node_definition__flywheel_milestone__label_text,
			R.drawable.fmm_noun__flywheel_milestone__gray,
			0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
			new HashMap<FmmNodeGlyphType, Integer>(),
			new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
			new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
			new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
			new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
			10016 ),
    FLYWHEEL_TEAM (
    		FlywheelTeam.class,
    		"FTE",
    		R.string.fmm_node_definition__flywheel_team__label_text,
    		R.drawable.fmm_noun__flywheel_team,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10017 ),
    FLYWHEEL_TEAM_COMMUNITY_MEMBER (
    		FlywheelTeamCommunityMember.class,
    		"FTM",
    		R.string.fmm_node_definition__flywheel_team_community_member__label_text,
    		R.drawable.fmm_noun__community_member__on_leave,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10018 ),
    FLYWHEEL_WORK_PACKAGE_COMMITMENT (
    		FlywheelWorkPackageCommitment.class,
    		"FPC",
    		R.string.fmm_node_definition__flywheel_commitment__label_text,
    		R.drawable.commitment__none,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10019 ),
    FMM_CONFIGURATION (
    		FmmConfiguration.class,
    		"FCF",
    		R.string.fmm_node_definition__fmm_configuration__label_text,
    		R.drawable.fmm_repository__16,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10020 ),
    FRAG_LOCK (
    		FragLock.class,
    		"FRL",
    		R.string.fmm_node_definition__frag_lock__label_text,
    		R.drawable.lock_type__node,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10021 ),
    FSE_DOCUMENT (
    		FseDocument.class,
    		"FSD",
    		R.string.fmm_node_definition__fse_document__label_text,
    		R.drawable.fse_document,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10022 ),
    FSE_PARAGRAPH (
    		FseParagraph.class,
    		"FSP",
    		R.string.fmm_node_definition__fse_paragraph__label_text,
    		R.drawable.fse_paragraph,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10023 ),
    FSE_DOCUMENT_TRANSACTION (
    		FseDocumentTransaction.class,
    		"FST",
    		R.string.fse__document_transaction,
    		R.drawable.fse__document_history,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10024 ),
    FSE_DOCUMENT_TRANSACTION__TYPE (
    		FseDocumentTransactionType.class,
    		"FSY",
    		R.string.fse__document_transaction_type,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10025 ),
    FUNCTIONAL_TEAM (
    		FunctionalTeam.class,
    		"UTE",
    		R.string.fmm_node_definition__functional_team__label_text,
    		R.drawable.fmm_noun__functional_team,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10026 ),
    FUNCTIONAL_TEAM_COMMUNITY_MEMBER (
    		FunctionalTeamCommunityMember.class,
    		"UTM",
    		R.string.fmm_node_definition__functional_team_community_member__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10027 ),
    GOVERNANCE_PARTICIPATION_TYPE (
    		GovernanceParticipationType.class,
    		"GPT",
    		R.string.fmm_node_definition__governance_participation_type__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10028 ),
    GOVERNANCE_ROLE (
    		GovernanceRole.class,
    		"GOR",
    		R.string.fmm_node_definition__governance_role__label_text,
    		R.drawable.fmm_noun__team_role,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10029 ),
    GOVERNANCE_SATISFACTION (
    		GovernanceSatisfaction.class,
    		"GOS",
    		R.string.fmm_node_definition__governance_satisfaction__label_text,
    		R.drawable.fmm_noun__governance_satisfaction,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		11030 ),
    GOVERNANCE_TARGET (
    		GovernanceTarget.class,
    		"GOT",
    		R.string.fmm_node_definition__governance_target__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10031 ),
    ISSUE_STATUS (
    		FacilitationIssueStatus.class,
    		"IST",
    		R.string.fmm_node_definition__issue_status__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10032 ),
    LOCK_CONFIG (
    		LockConfig.class,
    		"LOC",
    		R.string.fmm_node_definition__lock_config__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10033 ),
    LOCK_TYPE (
    		LockType.class,
    		"LKT",
    		R.string.fmm_node_definition__lock_type__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10034 ),
    NODE_FRAG__AUDIT_BLOCK (
    		NodeFragAuditBlock.class,
    		"NFA",
    		R.string.fmm_node_definition__node_frag__audit_block__label_text,
    		R.drawable.audit_block,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10035 ),
    NODE_FRAG__COMPLETION (
    		NodeFragCompletion.class,
    		"NFC",
    		R.string.fmm_node_definition__node_frag__completion__label_text,
    		R.drawable.lock_type__node,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10036 ),
    NODE_FRAG__FSE_DOCUMENT (
    		NodeFragFseDocument.class,
    		"NFD",
    		R.string.fmm_node_definition__fse_document__label_text,
    		R.drawable.fse_document,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10037 ),
    NODE_FRAG__GOVERNANCE (
    		NodeFragGovernance.class,
    		"NFG",
    		R.string.fmm_node_definition__node_frag__governance__label_text,
    		R.drawable.governance,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10038 ),
    NODE_FRAG__TRIBKN_QUALITY (
    		NodeFragTribKnQuality.class,
    		"NFT",
    		R.string.fmm_node_definition__frag_lock__label_text,
    		R.drawable.deckangl__noun_quality__good,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10039 ),
    NODE_FRAG__WORK_TASK_BUDGET (
    		NodeFragWorkTaskBudget.class,
    		"NFB",
    		R.string.fmm_node_definition__node_frag__work_task_budget__label_text,
    		R.drawable.work_task_budget__gray,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10040 ),
    NOTEBOOK (
    		Notebook.class,
    		"NBK",
    		R.string.fmm_node_definition__notebook__label_text,
    		R.drawable.fmm_noun__notebook__gray,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10041 ),
    NOTEBOOK_LINK_TO_FACILITATION_ISSUE (
    		NotebookLinkToDiscussionTopic.class,
    		"NLD",
    		R.string.fmm_node_definition__notebook_link_to_discussion_topic__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10042 ),
    FMS_ORGANIZATION (
    		FmsOrganization.class,
    		"ORG",
    		R.string.fmm_node_definition__organization__label_text,
    		R.drawable.gcg__data_source,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10043 ),
    ORGANIZATION_COMMUNITY_MEMBER (
    		OrganizationCommunityMember.class,
    		"OCM",
    		R.string.fmm_node_definition__organization_community_member__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10044 ),
    ORGANIZATION_GOVERNANCE_TARGET (
    		OrganizationGovernanceTarget.class,
    		"OGT",
    		R.string.fmm_node_definition__organization_governance_target__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10045 ),
    ORGANIZATION_LOCK_CONFIG (
    		OrganizationLockConfig.class,
    		"OLC",
    		R.string.fmm_node_definition__organization_lock_config__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10046 ),
    ORGANIZATION_PARTICIPATION (
    		OrganizationParticipation.class,
    		"OPN",
    		R.string.fmm_node_definition__organization_participation__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10047 ),
    PDF_PUBLICATION (
    		PdfPublication.class,
    		"PDF",
    		R.string.fmm_event__publish_pdf__label_text,
    		R.drawable.fmm_event__publish_pdf,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10048 ),
    PORTFOLIO (
    		Portfolio.class,
    		"PFO",
    		R.string.fmm_node_definition__portfolio__label_text,
    		R.drawable.fmm_noun__portfolio__gray,
    		0,
            R.drawable.fms_activity__portfolio,
            R.drawable.fms_dialog__portfolio,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10049 ),
//    PORTFOLIO_LINK_TO_PROJECT (
//    		PortfolioLinkToProject.class,
//    		"PLP",
//    		R.string.fmm_node_definition__portfolio_linkto_project__label_text,
//    		R.drawable.gcg__null_drawable,
//    		0,
//    		R.drawable.gcg__unspecified_glyph,
//    		R.drawable.gcg__unspecified_glyph,
//    		new HashMap<FmmNodeGlyphType, Integer>(),
//    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
//    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
//    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
//    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
//    		10050 ),
    PROJECT (
    		Project.class,
    		"PRJ",
    		R.string.fmm_node_definition__project__label_text,
    		R.drawable.fmm_noun__project__gray,
    		0,
    		R.drawable.fms_activity__project,
    		R.drawable.fms_dialog__project,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10051 ),
    PROJECT_ASSET (
    		ProjectAsset.class,
    		"PAT",
    		R.string.fmm_node_definition__project_asset__label_text,
    		R.drawable.fmm_noun__project_asset__gray,
    		0,
    		R.drawable.fms_activity__project_asset,
    		R.drawable.fms_dialog__project_asset,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10052 ),
    // 10053 used to be PROJECT_ASSET_LINK_TO_WORK_PACKAGE
    SERVICE_DELIVERY_COMMITMENT (
    		FlywheelServiceDeliveryCommitment.class,
    		"SDC",
    		R.string.fmm_node_definition__service_delivery_commitment__label_text,
    		R.drawable.commitment__none,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10054 ),
    SERVICE_OFFERING (
    		ServiceOffering.class,
    		"SOF",
    		R.string.fmm_node_definition__service_offering__label_text,
    		R.drawable.fmm_noun__service_offering__gray,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10055 ),
    SERVICE_OFFERING_SLA (
    		ServiceOfferingSla.class,
    		"SOS",
    		R.string.fmm_node_definition__service_offering_sla__label_text,
    		R.drawable.fmm_noun__service_level_agreement__gray,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10056 ),
    SERVICE_REQUEST (
    		ServiceRequest.class,
    		"SRT",
    		R.string.fmm_node_definition__service_request__label_text,
    		R.drawable.fmm_noun__service_request__gray,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10057 ),
    SERVICE_REQUEST_LINK_TO_WORK_TASK (
    		ServiceRequestLinkToWorkTask.class,
    		"RLT",
    		R.string.fmm_node_definition__service_request_link_to_work_task__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10058 ),
    SERVICE_REQUEST_TRIAGE_LOG (
    		ServiceRequestTriageLog.class,
    		"RTL",
    		R.string.fmm_node_definition__service_request_triage_log__label_text,
    		R.drawable.fmm_noun__service_request_triage_log__gray,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10059 ),
    STRATEGIC_COMMITMENT (
    		StrategicCommitment.class,
    		"SCT",
    		R.string.fmm_node_definition__strategic_commitment__label_text,
    		R.drawable.commitment__none,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10060 ),
    STRATEGIC_MILESTONE (
    		StrategicMilestone.class,
    		"SMS",
    		R.string.fmm_node_definition__strategic_milestone__label_text,
    		R.drawable.fmm_noun__strategic_milestone__gray,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.fms__strategic_milestone__32,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10061 ),
    STRATEGY_TEAM (
    		StrategyTeam.class,
    		"STT",
    		R.string.fmm_node_definition__strategy_team__label_text,
    		R.drawable.fmm_noun__strategy_team,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10062 ),
    STRATEGIC_TEAM_COMMUNITY_MEMBER (
    		StrategicTeamCommunityMember.class,
    		"STM",
    		R.string.fmm_node_definition__strategy_team_community_member__label_text,
    		R.drawable.fmm_noun__strategy_team,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10063 ),
    TEAM_MEMBER_STATUS (
    		TeamMemberStatus.class,
    		"TMS",
    		R.string.fmm_node_definition__team_member_status__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10064 ),
	TRIBKN_ELEMENT (
			TribKnElement.class,
			"TRE",
			R.string.fmm_node_definition__tribkn_element__label_text,
			R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
			new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10065 ),
	TRIBKN_QUALITY_ENUMERATION (
			TribKnQualityEnumeration.class,
			"TRN",
			R.string.fmm_node_definition__tribkn_quality_enumeration__label_text,
			R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
			new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10066 ),
	TRIBKN_QUALITY_NORMALIZED__DESCRIPTION (
			TribKnQualityNormalizedDescription.class,
			"TRD",
			R.string.fmm_node_definition__tribkn_quality_normalized_description__label_text,
			R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
			new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10067 ),
    WORK_PACKAGE (
    		WorkPackage.class,
    		"WPG",
    		R.string.fmm_node_definition__work_package__label_text,
    		R.drawable.fmm_noun__work_package__gray,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.fms__work_package__32,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10068 ),
    // 10069 previously used by WORK_PACKAGE_LINK_TO_WORK_TASK
    WORK_PLAN (
    		WorkPlan.class,
    		"WPN",
    		R.string.fmm_node_definition__work_plan__label_text,
    		R.drawable.fmm_noun__work_plan__gray,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10070 ),
    WORK_TASK (
    		WorkTask.class,
    		"WTK",
    		R.string.fmm_node_definition__work_task__label_text,
    		R.drawable.fmm_noun__work_task__gray,
    		0,
    		R.drawable.fms_activity__work_task,
    		R.drawable.fms_dialog__work_task,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10071 ),
    WORK_TASK_ASSIGNMENT (
    		WorkTaskAssignment.class,
    		"WTA",
    		R.string.fmm_node_definition__work_task_assignment__label_text,
    		R.drawable.gcg__null_drawable,
    		0,
    		R.drawable.gcg__unspecified_glyph,
    		R.drawable.gcg__unspecified_glyph,
    		new HashMap<FmmNodeGlyphType, Integer>(),
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // tiny drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // small drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // medium drawables
    		new Hashtable<DecKanGlNounStateColor, BitmapDrawable>(),  // large drawables
    		10072 );
	
    public static final String name_COLUMN_1 = "node_type_code";
	public static final String name_COLUMN_2 = "node_name";
	public static final String name_COLUMN_3 = "description";
	
	public static final ArrayList<FmmNodeDefinition> DATABASE_LOAD_ORDER = new ArrayList<FmmNodeDefinition>(
			Arrays.asList(
					FmmNodeDefinition.COMMUNITY_MEMBER,
					FmmNodeDefinition.FMS_ORGANIZATION,
					FmmNodeDefinition.FMM_CONFIGURATION,
					FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER,
					FmmNodeDefinition.COMMUNITY_MEMBER_ORGANIZATION_GOVERNANCE_AUTHORITY,
					FmmNodeDefinition.FLYWHEEL_TEAM,
					FmmNodeDefinition.FISCAL_YEAR,
					FmmNodeDefinition.STRATEGIC_MILESTONE,
					FmmNodeDefinition.PORTFOLIO,
					FmmNodeDefinition.PROJECT,
					FmmNodeDefinition.PROJECT_ASSET,
					FmmNodeDefinition.STRATEGIC_COMMITMENT,
					FmmNodeDefinition.WORK_PACKAGE,
                    FmmNodeDefinition.WORK_TASK) );
	
	private static final HashMap<String, FmmNodeDefinition> NODE_TYPE_CODE_MAP = new HashMap<String, FmmNodeDefinition>();
	static {
		for(FmmNodeDefinition theFmmNodeDefinition : values()) {
			putNodeTypeCodeMapEntry(theFmmNodeDefinition);
		}
	}
	
	private static void putNodeTypeCodeMapEntry(FmmNodeDefinition anFmmNodeDefinition) {
		FmmNodeDefinition.NODE_TYPE_CODE_MAP.put(anFmmNodeDefinition.getNodeTypeCode(), anFmmNodeDefinition);
	}
	
	public static FmmNodeDefinition getEntryForNodeTypeCode(String aNodeTypeCode) {
		return FmmNodeDefinition.NODE_TYPE_CODE_MAP.get(aNodeTypeCode);
	}
	
	public static FmmNodeDefinition getEntryForNodeIdString(String aNodeIdString) {
		return getEntryForNodeTypeCode(aNodeIdString.substring(0, 3));
	}
	
	public static FmmNodeDefinition getEntryForNodeId(NodeId aNodeId) {
		return getEntryForNodeTypeCode(aNodeId.getNodeTypeCode());
	}
	
	private static final HashMap<String, FmmNodeDefinition> NODE_CLASS_NAME_MAP = new HashMap<String, FmmNodeDefinition>();
	static {
		for(FmmNodeDefinition theFmmNodeDefinition : values()) {
			putNodeClassNameMapEntry(theFmmNodeDefinition);
		}
	}
	
	private static void putNodeClassNameMapEntry(FmmNodeDefinition anFmmNodeDefinition) {
		FmmNodeDefinition.NODE_CLASS_NAME_MAP.put(anFmmNodeDefinition.getName(), anFmmNodeDefinition);
	}
	
	public static FmmNodeDefinition getEntryForNodeClassName(String aNodeTypeName) {
		return FmmNodeDefinition.NODE_CLASS_NAME_MAP.get(aNodeTypeName);
	}
	
	private static final HashMap<Class<? extends FmmNode>, FmmNodeDefinition> NODE_CLASS_MAP = new HashMap<Class<? extends FmmNode>, FmmNodeDefinition>();
	static {
		for(FmmNodeDefinition theFmmNodeDefinition : values()) {
			putClassMapEntry(theFmmNodeDefinition);
		}
	}
	
	private static void putClassMapEntry(FmmNodeDefinition anFmmNodeDefinition) {
		FmmNodeDefinition.NODE_CLASS_MAP.put(anFmmNodeDefinition.getNodeClass(), anFmmNodeDefinition);
	}
	
	public static FmmNodeDefinition getEntryForClass(Class<? extends FmmNode> aNodeClass) {
		return FmmNodeDefinition.NODE_CLASS_MAP.get(aNodeClass);
	}
	
	private static final ArrayList<String> HEADLINE_SEARCH__NODE_LIST = new ArrayList<String>();
	static {
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.BOOKSHELF.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.COMMUNITY_MEMBER.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.DISCUSSION_TOPIC.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.FACILITATION_ISSUE.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.FISCAL_YEAR.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.FLYWHEEL_MILESTONE.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.FLYWHEEL_TEAM.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.FUNCTIONAL_TEAM.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.NOTEBOOK.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.FMS_ORGANIZATION.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.PORTFOLIO.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.PROJECT.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.PROJECT_ASSET.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.SERVICE_OFFERING.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.SERVICE_OFFERING_SLA.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.SERVICE_REQUEST.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.STRATEGIC_MILESTONE.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.STRATEGY_TEAM.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.WORK_PACKAGE.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.WORK_PLAN.getNodeTypeCode());
		HEADLINE_SEARCH__NODE_LIST.add(FmmNodeDefinition.WORK_TASK.getNodeTypeCode());
	}

	public static boolean isHeadlineSearchNode(String aNodeIdString) {
		return HEADLINE_SEARCH__NODE_LIST.contains(NodeId.getNodeTypeCodeFromNodeIdString(aNodeIdString));
	}
	
	private static final ArrayList<FmmNodeDefinition> FMM_ROOT__NODE_LIST = new ArrayList<FmmNodeDefinition>();
	static {
		FMM_ROOT__NODE_LIST.add(FmmNodeDefinition.BOOKSHELF);
		FMM_ROOT__NODE_LIST.add(FmmNodeDefinition.FISCAL_YEAR);
		FMM_ROOT__NODE_LIST.add(FmmNodeDefinition.PORTFOLIO);
	}

	public static boolean isFmmRootNode(FmmNodeDefinition anFmmNodeDefinition) {
		return FMM_ROOT__NODE_LIST.contains(anFmmNodeDefinition);
	}
	
	private static final ArrayList<FmmNodeDefinition> DECKANGL__NODE_LIST = new ArrayList<FmmNodeDefinition>();
	static {
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.BOOKSHELF);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.COMMUNITY_MEMBER);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.DISCUSSION_TOPIC);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.FACILITATION_ISSUE);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.FISCAL_YEAR);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.FLYWHEEL_MILESTONE);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.FLYWHEEL_TEAM);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.FUNCTIONAL_TEAM);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.NOTEBOOK);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.FMS_ORGANIZATION);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.PORTFOLIO);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.PROJECT);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.PROJECT_ASSET);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.SERVICE_OFFERING);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.SERVICE_OFFERING_SLA);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.SERVICE_REQUEST);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.STRATEGIC_MILESTONE);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.STRATEGY_TEAM);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.WORK_PACKAGE);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.WORK_PLAN);
		DECKANGL__NODE_LIST.add(FmmNodeDefinition.WORK_TASK);
	}

	public static boolean isDecKanGlNode(FmmNodeDefinition anFmmNodeDefinition) {
		return DECKANGL__NODE_LIST.contains(anFmmNodeDefinition);
	}
	
	public static ArrayList<FmmNodeDefinition> getDecKanGlNodeList() {
		return DECKANGL__NODE_LIST;
	}
	
	private static final ArrayList<String> COMMITMENT__NODE_LIST = new ArrayList<String>();
	static {
		COMMITMENT__NODE_LIST.add(FmmNodeDefinition.FLYWHEEL_WORK_PACKAGE_COMMITMENT.getNodeTypeCode());
		COMMITMENT__NODE_LIST.add(FmmNodeDefinition.STRATEGIC_COMMITMENT.getNodeTypeCode());
		COMMITMENT__NODE_LIST.add(FmmNodeDefinition.WORK_TASK_ASSIGNMENT.getNodeTypeCode());
	}

	public static boolean isCommitmentNode(String aNodeIdString) {
		return COMMITMENT__NODE_LIST.contains(NodeId.getNodeTypeCodeFromNodeIdString(aNodeIdString));
	}




    private static final ArrayList<FmmNodeDefinition> ALPHA_SORT__NODE_LIST = new ArrayList<FmmNodeDefinition>();
    static {
        ALPHA_SORT__NODE_LIST.add(FmmNodeDefinition.BOOKSHELF);
        ALPHA_SORT__NODE_LIST.add(FmmNodeDefinition.DISCUSSION_TOPIC);
        ALPHA_SORT__NODE_LIST.add(FmmNodeDefinition.FISCAL_YEAR);
        ALPHA_SORT__NODE_LIST.add(FmmNodeDefinition.PORTFOLIO);
        ALPHA_SORT__NODE_LIST.add(FmmNodeDefinition.PROJECT);
    }
	
	static {  // initialize nounStateBitmapTable(s); Tiny, Small, Medium and Large
		FmmNodeDefinition.BOOKSHELF.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__bookshelf__gray__tiny) );
		FmmNodeDefinition.BOOKSHELF.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__bookshelf__yellow__tiny) );
		FmmNodeDefinition.BOOKSHELF.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__bookshelf__orange__tiny) );
		FmmNodeDefinition.BOOKSHELF.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__bookshelf__pink__tiny) );
		FmmNodeDefinition.BOOKSHELF.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__bookshelf__green__tiny) );

		FmmNodeDefinition.COMMUNITY_MEMBER.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__community_member__active__tiny) );

		FmmNodeDefinition.DISCUSSION_TOPIC.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__discussion_topic__gray__tiny) );
		FmmNodeDefinition.DISCUSSION_TOPIC.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__discussion_topic__yellow__tiny) );
		FmmNodeDefinition.DISCUSSION_TOPIC.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__discussion_topic__orange__tiny) );
		FmmNodeDefinition.DISCUSSION_TOPIC.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__discussion_topic__pink__tiny) );
		FmmNodeDefinition.DISCUSSION_TOPIC.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__discussion_topic__green__tiny) );

		FmmNodeDefinition.FACILITATION_ISSUE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__facilitation_issue__gray__tiny) );
		FmmNodeDefinition.FACILITATION_ISSUE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__facilitation_issue__yellow__tiny) );
		FmmNodeDefinition.FACILITATION_ISSUE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__facilitation_issue__orange__tiny) );
		FmmNodeDefinition.FACILITATION_ISSUE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__facilitation_issue__pink__tiny) );
		FmmNodeDefinition.FACILITATION_ISSUE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__facilitation_issue__green__tiny) );

		FmmNodeDefinition.FISCAL_YEAR.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__fiscal_year__gray__tiny) );
		FmmNodeDefinition.FISCAL_YEAR.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__fiscal_year__yellow__tiny) );
		FmmNodeDefinition.FISCAL_YEAR.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__fiscal_year__orange__tiny) );
		FmmNodeDefinition.FISCAL_YEAR.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__fiscal_year__pink__tiny) );
		FmmNodeDefinition.FISCAL_YEAR.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__fiscal_year__green__tiny) );

		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__flywheel_milestone__gray__tiny) );
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__flywheel_milestone__yellow__tiny) );
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__flywheel_milestone__orange__tiny) );
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__flywheel_milestone__pink__tiny) );
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__flywheel_milestone__green__tiny) );

		FmmNodeDefinition.FLYWHEEL_TEAM.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__flywheel_team__tiny) );

		FmmNodeDefinition.FUNCTIONAL_TEAM.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__functional_team__tiny) );

		FmmNodeDefinition.NOTEBOOK.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__notebook__gray__tiny) );
		FmmNodeDefinition.NOTEBOOK.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__notebook__yellow__tiny) );
		FmmNodeDefinition.NOTEBOOK.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__notebook__orange__tiny) );
		FmmNodeDefinition.NOTEBOOK.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__notebook__pink__tiny) );
		FmmNodeDefinition.NOTEBOOK.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__notebook__green__tiny) );

		FmmNodeDefinition.FMS_ORGANIZATION.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__organization__tiny) );

		FmmNodeDefinition.PORTFOLIO.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__portfolio__gray__tiny) );
		FmmNodeDefinition.PORTFOLIO.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__portfolio__yellow__tiny) );
		FmmNodeDefinition.PORTFOLIO.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__portfolio__orange__tiny) );
		FmmNodeDefinition.PORTFOLIO.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__portfolio__pink__tiny) );
		FmmNodeDefinition.PORTFOLIO.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__portfolio__green__tiny) );

		FmmNodeDefinition.PROJECT.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__project__gray__tiny) );
		FmmNodeDefinition.PROJECT.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__project__yellow__tiny) );
		FmmNodeDefinition.PROJECT.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__project__orange__tiny) );
		FmmNodeDefinition.PROJECT.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__project__pink__tiny) );
		FmmNodeDefinition.PROJECT.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__project__green__tiny) );

		FmmNodeDefinition.PROJECT_ASSET.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__project_asset__gray__tiny) );
		FmmNodeDefinition.PROJECT_ASSET.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__project_asset__yellow__tiny) );
		FmmNodeDefinition.PROJECT_ASSET.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__project_asset__orange__tiny) );
		FmmNodeDefinition.PROJECT_ASSET.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__project_asset__pink__tiny) );
		FmmNodeDefinition.PROJECT_ASSET.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__project_asset__green__tiny) );

		FmmNodeDefinition.SERVICE_OFFERING.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_offering__gray__tiny) );
		FmmNodeDefinition.SERVICE_OFFERING.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_offering__yellow__tiny) );
		FmmNodeDefinition.SERVICE_OFFERING.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_offering__orange__tiny) );
		FmmNodeDefinition.SERVICE_OFFERING.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_offering__pink__tiny) );
		FmmNodeDefinition.SERVICE_OFFERING.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_offering__green__tiny) );

		FmmNodeDefinition.SERVICE_OFFERING_SLA.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_level_agreement__gray__tiny) );
		FmmNodeDefinition.SERVICE_OFFERING_SLA.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_level_agreement__yellow__tiny) );
		FmmNodeDefinition.SERVICE_OFFERING_SLA.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_level_agreement__orange__tiny) );
		FmmNodeDefinition.SERVICE_OFFERING_SLA.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_level_agreement__pink__tiny) );
		FmmNodeDefinition.SERVICE_OFFERING_SLA.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_level_agreement__green__tiny) );

		FmmNodeDefinition.SERVICE_REQUEST.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_request__gray__tiny) );
		FmmNodeDefinition.SERVICE_REQUEST.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_request__yellow__tiny) );
		FmmNodeDefinition.SERVICE_REQUEST.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_request__orange__tiny) );
		FmmNodeDefinition.SERVICE_REQUEST.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_request__pink__tiny) );
		FmmNodeDefinition.SERVICE_REQUEST.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__service_request__green__tiny) );

		FmmNodeDefinition.STRATEGIC_MILESTONE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__strategic_milestone__gray__tiny) );
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__strategic_milestone__yellow__tiny) );
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__strategic_milestone__orange__tiny) );
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__strategic_milestone__pink__tiny) );
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__strategic_milestone__green__tiny) );

		FmmNodeDefinition.STRATEGY_TEAM.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__strategy_team__tiny) );

		FmmNodeDefinition.WORK_PACKAGE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_package__gray__tiny) );
		FmmNodeDefinition.WORK_PACKAGE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_package__yellow__tiny) );
		FmmNodeDefinition.WORK_PACKAGE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_package__orange__tiny) );
		FmmNodeDefinition.WORK_PACKAGE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_package__pink__tiny) );
		FmmNodeDefinition.WORK_PACKAGE.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_package__green__tiny) );

		FmmNodeDefinition.WORK_PLAN.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_plan__gray__tiny) );
		FmmNodeDefinition.WORK_PLAN.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_plan__yellow__tiny) );
		FmmNodeDefinition.WORK_PLAN.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_plan__orange__tiny) );
		FmmNodeDefinition.WORK_PLAN.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_plan__pink__tiny) );
		FmmNodeDefinition.WORK_PLAN.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_plan__green__tiny) );

		FmmNodeDefinition.WORK_TASK.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GRAY, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_task__gray__tiny) );
		FmmNodeDefinition.WORK_TASK.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.YELLOW, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_task__yellow__tiny) );
		FmmNodeDefinition.WORK_TASK.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.ORANGE, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_task__orange__tiny) );
		FmmNodeDefinition.WORK_TASK.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.PINK, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_task__pink__tiny) );
		FmmNodeDefinition.WORK_TASK.getNounStateBitmapTableTiny().put(
				DecKanGlNounStateColor.GREEN, (BitmapDrawable) GcgApplication.getAppResources().getDrawable(R.drawable.fmm_noun__work_task__green__tiny) );

	}
	
	static {  // initialize nodeGlyphResourceIdMap(s)
		//  BOOKSHELF
		FmmNodeDefinition.BOOKSHELF.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD_TO, R.drawable.fmm_noun__bookshelf__add_to);
		FmmNodeDefinition.BOOKSHELF.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ALL, R.drawable.fmm_noun__bookshelf__all_notebooks);
		FmmNodeDefinition.BOOKSHELF.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.DESTROY, R.drawable.fmm_noun__bookshelf__delete);
		FmmNodeDefinition.BOOKSHELF.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_noun__bookshelf__edit);
		FmmNodeDefinition.BOOKSHELF.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__bookshelf__gray);
		FmmNodeDefinition.BOOKSHELF.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__bookshelf__green);
		FmmNodeDefinition.BOOKSHELF.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.NEW, R.drawable.fmm_noun__bookshelf__new);
		FmmNodeDefinition.BOOKSHELF.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__bookshelf__orange);
		FmmNodeDefinition.BOOKSHELF.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORPHAN, R.drawable.fmm_noun__bookshelf__orphan_notebooks);
		FmmNodeDefinition.BOOKSHELF.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__bookshelf__pink);
		FmmNodeDefinition.BOOKSHELF.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.REMOVE, R.drawable.fmm_noun__bookshelf__remove_notebook);
		FmmNodeDefinition.BOOKSHELF.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.RENAME, R.drawable.fmm_noun__bookshelf__rename);
		FmmNodeDefinition.BOOKSHELF.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__bookshelf__yellow);
		//  DISCUSSION TOPIC
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD_TO, R.drawable.fmm_noun__discussion_topic__add_item);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD, R.drawable.fmm_noun__discussion_topic__add);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.COUNT_GREEN, R.drawable.fmm_noun__discussion_topic_count__green_of_total);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.DESTROY, R.drawable.fmm_noun__discussion_topic__delete);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_noun__discussion_topic__edit);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__discussion_topic__gray);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__discussion_topic__green);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.MOVE, R.drawable.fmm_noun__discussion_topic__move);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.NEW, R.drawable.fmm_noun__discussion_topic__new);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__discussion_topic__orange);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__discussion_topic__pink);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.REMOVE, R.drawable.fmm_noun__discussion_topic__remove);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.RENAME, R.drawable.fmm_noun__discussion_topic__rename);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__discussion_topic__yellow);
		//  FACILITATION ISSUE
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.DESTROY, R.drawable.fmm_noun__facilitation_issue__delete);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_noun__facilitation_issue__edit);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__facilitation_issue__gray);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__facilitation_issue__green);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.MOVE, R.drawable.fmm_noun__facilitation_issue__move);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.NEW, R.drawable.fmm_noun__facilitation_issue__new);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__facilitation_issue__orange);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__facilitation_issue__pink);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.RENAME, R.drawable.fmm_noun__facilitation_issue__rename);
		FmmNodeDefinition.FACILITATION_ISSUE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__facilitation_issue__yellow);
		//  FISCAL YEAR
		FmmNodeDefinition.FISCAL_YEAR.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.DESTROY, R.drawable.fmm_noun__fiscal_year__delete);
		FmmNodeDefinition.FISCAL_YEAR.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_noun__fiscal_year__edit);
		FmmNodeDefinition.FISCAL_YEAR.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__fiscal_year__gray);
		FmmNodeDefinition.FISCAL_YEAR.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__fiscal_year__green);
		FmmNodeDefinition.FISCAL_YEAR.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.NEW, R.drawable.fmm_noun__fiscal_year__new);
		FmmNodeDefinition.FISCAL_YEAR.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__fiscal_year__orange);
		FmmNodeDefinition.FISCAL_YEAR.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__fiscal_year__pink);
		FmmNodeDefinition.FISCAL_YEAR.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.RENAME, R.drawable.fmm_noun__fiscal_year__rename);
		FmmNodeDefinition.FISCAL_YEAR.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__fiscal_year__yellow);
		//  FLYWHEEL MILESTONE
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.DESTROY, R.drawable.fmm_noun__flywheel_milestone__delete);
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_noun__flywheel_milestone__edit);
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__flywheel_milestone__gray);
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__flywheel_milestone__green);
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.MOVE, R.drawable.fmm_noun__flywheel_milestone__move);
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.NEW, R.drawable.fmm_noun__flywheel_milestone__new);
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__flywheel_milestone__orange);
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__flywheel_milestone__pink);
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.QUESTION, R.drawable.fmm_noun__flywheel_milestone__question);
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.RENAME, R.drawable.fmm_noun__flywheel_milestone__rename);
		FmmNodeDefinition.FLYWHEEL_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__flywheel_milestone__yellow);
		//  FMM_CONFIGURATION
		FmmNodeDefinition.FMM_CONFIGURATION.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_repository__edit);
		//  FSE_DOCUMENT
		FmmNodeDefinition.FSE_DOCUMENT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fse_document__edit);
		FmmNodeDefinition.FSE_DOCUMENT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fse_document__gray);
		FmmNodeDefinition.FSE_DOCUMENT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fse_document__green);
		FmmNodeDefinition.FSE_DOCUMENT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LOCK, R.drawable.fse_document__locked);
		FmmNodeDefinition.FSE_DOCUMENT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fse_document__orange);
		FmmNodeDefinition.FSE_DOCUMENT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fse_document__pink);
		FmmNodeDefinition.FSE_DOCUMENT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fse_document__yellow);
		//  FSE_PARAGRAPH
		FmmNodeDefinition.FSE_PARAGRAPH.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fse_document__gray);
		//  NOTEBOOK
		FmmNodeDefinition.NOTEBOOK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD_ITEM, R.drawable.fmm_noun__notebook__add_item);
		FmmNodeDefinition.NOTEBOOK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD, R.drawable.fmm_noun__notebook__add);
		FmmNodeDefinition.NOTEBOOK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.DESTROY, R.drawable.fmm_noun__notebook__delete);
		FmmNodeDefinition.NOTEBOOK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_noun__notebook__edit);
		FmmNodeDefinition.NOTEBOOK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__notebook__gray);
		FmmNodeDefinition.NOTEBOOK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__notebook__green);
		FmmNodeDefinition.NOTEBOOK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.MOVE, R.drawable.fmm_noun__notebook__move);
		FmmNodeDefinition.NOTEBOOK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.NEW, R.drawable.fmm_noun__notebook__new);
		FmmNodeDefinition.NOTEBOOK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__notebook__orange);
		FmmNodeDefinition.NOTEBOOK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__notebook__pink);
		FmmNodeDefinition.NOTEBOOK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.QUESTION, R.drawable.fmm_noun__notebook__question);
		FmmNodeDefinition.NOTEBOOK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.REMOVE, R.drawable.fmm_noun__notebook__remove);
		FmmNodeDefinition.NOTEBOOK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.RENAME, R.drawable.fmm_noun__notebook__rename);
		FmmNodeDefinition.NOTEBOOK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__notebook__yellow);
		// FMM_EVENT
		// no 'events' on FmmEvent
		//  PORTFOLIO
		FmmNodeDefinition.PORTFOLIO.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD_ITEM, R.drawable.fmm_noun__portfolio__add_item);
		FmmNodeDefinition.PORTFOLIO.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ALL, R.drawable.fmm_noun__portfolio__all);
		FmmNodeDefinition.PORTFOLIO.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.DESTROY, R.drawable.fmm_noun__portfolio__delete);
		FmmNodeDefinition.PORTFOLIO.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_noun__portfolio__edit);
		FmmNodeDefinition.PORTFOLIO.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__portfolio__gray);
		FmmNodeDefinition.PORTFOLIO.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__portfolio__green);
		FmmNodeDefinition.PORTFOLIO.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.NEW, R.drawable.fmm_noun__portfolio__new);
		FmmNodeDefinition.PORTFOLIO.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__portfolio__orange);
		FmmNodeDefinition.PORTFOLIO.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__portfolio__pink);
		FmmNodeDefinition.PORTFOLIO.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.QUESTION, R.drawable.fmm_noun__portfolio__question);
		FmmNodeDefinition.PORTFOLIO.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.REMOVE, R.drawable.fmm_noun__portfolio__remove);
		FmmNodeDefinition.PORTFOLIO.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.RENAME, R.drawable.fmm_noun__portfolio__rename);
		FmmNodeDefinition.PORTFOLIO.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__portfolio__yellow);
		//  PROJECT
		FmmNodeDefinition.PROJECT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD_ITEM, R.drawable.fmm_noun__project__add_item);
		FmmNodeDefinition.PROJECT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD, R.drawable.fmm_noun__project__add);
		FmmNodeDefinition.PROJECT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.DESTROY, R.drawable.fmm_noun__project__delete);
		FmmNodeDefinition.PROJECT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_noun__project__edit);
		FmmNodeDefinition.PROJECT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__project__gray);
		FmmNodeDefinition.PROJECT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__project__green);
		FmmNodeDefinition.PROJECT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.MOVE, R.drawable.fmm_noun__project__move);
		FmmNodeDefinition.PROJECT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.NEW, R.drawable.fmm_noun__project__new);
		FmmNodeDefinition.PROJECT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__project__orange);
		FmmNodeDefinition.PROJECT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__project__pink);
		FmmNodeDefinition.PROJECT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.QUESTION, R.drawable.fmm_noun__project__question);
		FmmNodeDefinition.PROJECT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.REMOVE, R.drawable.fmm_noun__project__remove);
		FmmNodeDefinition.PROJECT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.RENAME, R.drawable.fmm_noun__project__rename);
		FmmNodeDefinition.PROJECT.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__project__yellow);
		//  PROJECT ASSET
		FmmNodeDefinition.PROJECT_ASSET.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD, R.drawable.fmm_noun__project_asset__add);
		FmmNodeDefinition.PROJECT_ASSET.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.DESTROY, R.drawable.fmm_noun__project_asset__delete);
		FmmNodeDefinition.PROJECT_ASSET.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_noun__project_asset__edit);
		FmmNodeDefinition.PROJECT_ASSET.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__project_asset__gray);
		FmmNodeDefinition.PROJECT_ASSET.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__project_asset__green);
		FmmNodeDefinition.PROJECT_ASSET.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.MOVE, R.drawable.fmm_noun__project_asset__move);
		FmmNodeDefinition.PROJECT_ASSET.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.NEW, R.drawable.fmm_noun__project_asset__new);
		FmmNodeDefinition.PROJECT_ASSET.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__project_asset__orange);
		FmmNodeDefinition.PROJECT_ASSET.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__project_asset__pink);
		FmmNodeDefinition.PROJECT_ASSET.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.QUESTION, R.drawable.fmm_noun__project_asset__question);
		FmmNodeDefinition.PROJECT_ASSET.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.REMOVE, R.drawable.fmm_noun__project_asset__remove);
		FmmNodeDefinition.PROJECT_ASSET.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.RENAME, R.drawable.fmm_noun__project_asset__rename);
		FmmNodeDefinition.PROJECT_ASSET.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__project_asset__yellow);
		FmmNodeDefinition.PROJECT_ASSET.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.COUNT_GREEN, R.drawable.fmm_noun__project_asset_count__green);
		//  SERVICE REQUEST
		FmmNodeDefinition.SERVICE_REQUEST.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD, R.drawable.fmm_noun__service_request__add);
		FmmNodeDefinition.SERVICE_REQUEST.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.DESTROY, R.drawable.fmm_noun__service_request__delete);
		FmmNodeDefinition.SERVICE_REQUEST.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_noun__service_request__edit);
		FmmNodeDefinition.SERVICE_REQUEST.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__service_request__gray);
		FmmNodeDefinition.SERVICE_REQUEST.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__service_request__green);
		FmmNodeDefinition.SERVICE_REQUEST.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.MOVE, R.drawable.fmm_noun__service_request__move);
		FmmNodeDefinition.SERVICE_REQUEST.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.NEW, R.drawable.fmm_noun__service_request__new);
		FmmNodeDefinition.SERVICE_REQUEST.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__service_request__orange);
		FmmNodeDefinition.SERVICE_REQUEST.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__service_request__pink);
		FmmNodeDefinition.SERVICE_REQUEST.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.REMOVE, R.drawable.fmm_noun__service_request__remove);
		FmmNodeDefinition.SERVICE_REQUEST.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.RENAME, R.drawable.fmm_noun__service_request__rename);
		FmmNodeDefinition.SERVICE_REQUEST.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__service_request__yellow);
		//  SERVICE REQUEST TRIAGE LOG	
		FmmNodeDefinition.SERVICE_REQUEST_TRIAGE_LOG.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__service_request_triage_log__gray);
		FmmNodeDefinition.SERVICE_REQUEST_TRIAGE_LOG.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__service_request_triage_log__green);
		FmmNodeDefinition.SERVICE_REQUEST_TRIAGE_LOG.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__service_request_triage_log__orange);
		FmmNodeDefinition.SERVICE_REQUEST_TRIAGE_LOG.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__service_request_triage_log__pink);
		FmmNodeDefinition.SERVICE_REQUEST_TRIAGE_LOG.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__service_request_triage_log__yellow);
		//  FLYWHEEL MILESTONE	
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD_ITEM, R.drawable.fmm_noun__strategic_milestone__add_item);
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD, R.drawable.fmm_noun__strategic_milestone__add);
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.DESTROY, R.drawable.fmm_noun__strategic_milestone__delete);
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_noun__strategic_milestone__edit);
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__strategic_milestone__gray);
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__strategic_milestone__green);
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.MOVE, R.drawable.fmm_noun__strategic_milestone__move);
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.NEW, R.drawable.fmm_noun__strategic_milestone__new);
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__strategic_milestone__orange);
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__strategic_milestone__pink);
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.REMOVE, R.drawable.fmm_noun__strategic_milestone__remove);
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.RENAME, R.drawable.fmm_noun__strategic_milestone__rename);
		FmmNodeDefinition.STRATEGIC_MILESTONE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__strategic_milestone__yellow);
		//  WORK PACKAGE	
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD_ITEM, R.drawable.fmm_noun__work_package__add_item);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD, R.drawable.fmm_noun__work_package__add);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.COPY, R.drawable.fmm_noun__work_package__copy);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.DESTROY, R.drawable.fmm_noun__work_package__delete);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_noun__work_package__edit);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__work_package__gray);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__work_package__green);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.MOVE, R.drawable.fmm_noun__work_package__move);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.NEW, R.drawable.fmm_noun__work_package__new);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__work_package__orange);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__work_package__pink);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.REMOVE, R.drawable.fmm_noun__work_package__remove);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.RENAME, R.drawable.fmm_noun__work_package__rename);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__work_package__yellow);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.COUNT_GREEN, R.drawable.fmm_noun__work_package_count__green);
		
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_ALL, R.drawable.fmm_noun__work_package_list__all);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_GRAY, R.drawable.fmm_noun__work_package_list__gray);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_GREEN, R.drawable.fmm_noun__work_package_list__green);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_MOVE, R.drawable.fmm_noun__work_package_list__move);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_NO_COMMITMENT, R.drawable.fmm_noun__work_package_list__no_commitment);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_PINK, R.drawable.fmm_noun__work_package_list__pink);
		FmmNodeDefinition.WORK_PACKAGE.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_YELLOW, R.drawable.fmm_noun__work_package_list__yellow);
		//  WORK PLAN
		FmmNodeDefinition.WORK_PLAN.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD, R.drawable.fmm_noun__work_plan__add);
		FmmNodeDefinition.WORK_PLAN.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.DESTROY, R.drawable.fmm_noun__work_plan__delete);
		FmmNodeDefinition.WORK_PLAN.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_noun__work_plan__edit);
		FmmNodeDefinition.WORK_PLAN.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__work_plan__gray);
		FmmNodeDefinition.WORK_PLAN.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__work_plan__green);
		FmmNodeDefinition.WORK_PLAN.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.MOVE, R.drawable.fmm_noun__work_plan__move);
		FmmNodeDefinition.WORK_PLAN.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.NEW, R.drawable.fmm_noun__work_plan__new);
		FmmNodeDefinition.WORK_PLAN.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__work_plan__orange);
		FmmNodeDefinition.WORK_PLAN.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__work_plan__pink);
		FmmNodeDefinition.WORK_PLAN.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.REMOVE, R.drawable.fmm_noun__work_plan__remove);
		FmmNodeDefinition.WORK_PLAN.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.RENAME, R.drawable.fmm_noun__work_plan__rename);
		FmmNodeDefinition.WORK_PLAN.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__work_plan__yellow);
		FmmNodeDefinition.WORK_PLAN.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.COUNT_GREEN, R.drawable.fmm_noun__work_plan_count__green);
		//  WORK TASK
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ADD, R.drawable.fmm_noun__work_task__add);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.DESTROY, R.drawable.fmm_noun__work_task__delete);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.EDIT, R.drawable.fmm_noun__work_task__edit);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GRAY, R.drawable.fmm_noun__work_task__gray);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.GREEN, R.drawable.fmm_noun__work_task__green);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.MOVE, R.drawable.fmm_noun__work_task__move);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.NEW, R.drawable.fmm_noun__work_task__new);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.ORANGE, R.drawable.fmm_noun__work_task__orange);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.PINK, R.drawable.fmm_noun__work_task__pink);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.REMOVE, R.drawable.fmm_noun__work_task__remove);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.RENAME, R.drawable.fmm_noun__work_task__rename);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.YELLOW, R.drawable.fmm_noun__work_task__yellow);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.COUNT_GREEN, R.drawable.fmm_noun__work_task_count__green);

		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_ALL, R.drawable.fmm_noun__work_task_list__all);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_GRAY, R.drawable.fmm_noun__work_task_list__gray);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_GREEN, R.drawable.fmm_noun__work_task_list__green);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_MOVE_GRAY, R.drawable.fmm_noun__work_task_list__move_gray);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_MOVE_NOT_GREEN, R.drawable.fmm_noun__work_task_list__move_not_green);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_MOVE_PINK, R.drawable.fmm_noun__work_task_list__move_pink);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_MOVE_YELLOW, R.drawable.fmm_noun__work_task_list__move_yellow);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_MOVE, R.drawable.fmm_noun__work_task_list__move);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_PINK, R.drawable.fmm_noun__work_task_list__pink);
		FmmNodeDefinition.WORK_TASK.getNodeGlyphResourceIdMap().put(
				FmmNodeGlyphType.LIST_YELLOW, R.drawable.fmm_noun__work_task_list__yellow);
	}
	static {  // initialize node relationships
		BOOKSHELF.setPrimaryParentNodeDefinition(FmmNodeDefinition.FMS_ORGANIZATION);
		BOOKSHELF.setPrimaryChildNodeDefinition(FmmNodeDefinition.NOTEBOOK);
		COMMUNITY_MEMBER.setPrimaryParentNodeDefinition(FmmNodeDefinition.FMS_ORGANIZATION);
		COMMUNITY_MEMBER.setSecondaryParentNodeDefinition(FmmNodeDefinition.FLYWHEEL_TEAM);
		DISCUSSION_TOPIC.setPrimaryParentNodeDefinition(FmmNodeDefinition.NOTEBOOK);
		DISCUSSION_TOPIC.setSecondaryParentNodeDefinition(FmmNodeDefinition.DISCUSSION_TOPIC);
		FACILITATION_ISSUE.setPrimaryParentNodeDefinition(FmmNodeDefinition.FLYWHEEL_MILESTONE);
		FACILITATION_ISSUE.setSecondaryParentNodeDefinition(FmmNodeDefinition.COMMUNITY_MEMBER);
		FISCAL_YEAR.setPrimaryParentNodeDefinition(FmmNodeDefinition.FMS_ORGANIZATION);
		FISCAL_YEAR.setPrimaryChildNodeDefinition(FmmNodeDefinition.STRATEGIC_MILESTONE);
		FISCAL_YEAR.setSecondaryChildNodeDefinition(FmmNodeDefinition.FLYWHEEL_MILESTONE);
		FLYWHEEL_MILESTONE.setPrimaryParentNodeDefinition(FmmNodeDefinition.FISCAL_YEAR);
		FLYWHEEL_MILESTONE.setPrimaryChildNodeDefinition(FmmNodeDefinition.WORK_PLAN);
		FLYWHEEL_TEAM.setPrimaryParentNodeDefinition(FmmNodeDefinition.FMS_ORGANIZATION);
		FUNCTIONAL_TEAM.setPrimaryParentNodeDefinition(FmmNodeDefinition.FMS_ORGANIZATION);
		NOTEBOOK.setPrimaryParentNodeDefinition(FmmNodeDefinition.BOOKSHELF);
		FMS_ORGANIZATION.setPrimaryParentNodeDefinition(FmmNodeDefinition.FMM_CONFIGURATION);
		PORTFOLIO.setPrimaryParentNodeDefinition(FmmNodeDefinition.FMS_ORGANIZATION);
		PROJECT.setPrimaryParentNodeDefinition(FmmNodeDefinition.PORTFOLIO);
		PROJECT.setPrimaryChildNodeDefinition(FmmNodeDefinition.PROJECT_ASSET);
		PROJECT_ASSET.setPrimaryParentNodeDefinition(FmmNodeDefinition.PROJECT);
		PROJECT_ASSET.setSecondaryParentNodeDefinition(FmmNodeDefinition.STRATEGIC_MILESTONE);
		PROJECT_ASSET.setSecondaryLinkNodeDefinition(FmmNodeDefinition.STRATEGIC_COMMITMENT);
		PROJECT_ASSET.setPrimaryChildNodeDefinition(FmmNodeDefinition.WORK_PACKAGE);
		SERVICE_OFFERING.setPrimaryParentNodeDefinition(FmmNodeDefinition.SERVICE_OFFERING_SLA);
		SERVICE_OFFERING.setPrimaryParentNodeDefinition(FmmNodeDefinition.FLYWHEEL_MILESTONE);
		SERVICE_OFFERING.setPrimaryChildNodeDefinition(FmmNodeDefinition.SERVICE_REQUEST);
		SERVICE_OFFERING_SLA.setPrimaryParentNodeDefinition(FmmNodeDefinition.FISCAL_YEAR);
		SERVICE_OFFERING_SLA.setPrimaryChildNodeDefinition(FmmNodeDefinition.SERVICE_OFFERING);
		SERVICE_REQUEST.setPrimaryParentNodeDefinition(FmmNodeDefinition.SERVICE_OFFERING);
		SERVICE_REQUEST.setPrimaryChildNodeDefinition(FmmNodeDefinition.WORK_TASK);
		STRATEGIC_MILESTONE.setPrimaryParentNodeDefinition(FmmNodeDefinition.FISCAL_YEAR);
		STRATEGIC_MILESTONE.setPrimaryLinkNodeDefinition(FmmNodeDefinition.PROJECT_ASSET);
		STRATEGY_TEAM.setPrimaryParentNodeDefinition(FmmNodeDefinition.FMS_ORGANIZATION);
		WORK_PACKAGE.setPrimaryParentNodeDefinition(FmmNodeDefinition.PROJECT_ASSET);
		WORK_PACKAGE.setSecondaryParentNodeDefinition(FmmNodeDefinition.FLYWHEEL_MILESTONE);
		WORK_PACKAGE.setSecondaryLinkNodeDefinition(FmmNodeDefinition.FLYWHEEL_WORK_PACKAGE_COMMITMENT);
		WORK_PACKAGE.setPrimaryChildNodeDefinition(FmmNodeDefinition.WORK_TASK);
		WORK_PLAN.setPrimaryParentNodeDefinition(FmmNodeDefinition.FLYWHEEL_MILESTONE);
		WORK_TASK.setPrimaryParentNodeDefinition(FmmNodeDefinition.WORK_PACKAGE);
		WORK_TASK.setSecondaryParentNodeDefinition(FmmNodeDefinition.WORK_PLAN);
	}
		
	
	//////////////////////////////////////////////////////////////////////////////

	private Date timestamp = new Date(0);
	private final Class<? extends FmmNode> nodeClass;
	private final String nodeTypeCode;
	private final String className;
	private final int labelTextResourceId;
	private final String labelText;
	private final int labelDrawableResourceId;
	private final Drawable labelDrawable;
	private final int pickerDrawableResourceId;
	private final int activityDrawableResourceId;
	private final int dialogDrawableResourceId;
	private final HashMap<FmmNodeGlyphType, Integer> nodeGlyphResourceIdMap;
	private final Hashtable<DecKanGlNounStateColor, BitmapDrawable> nounStateBitmapTableTiny; 
	private final Hashtable<DecKanGlNounStateColor, BitmapDrawable> nounStateBitmapTableSmall; 
	private final Hashtable<DecKanGlNounStateColor, BitmapDrawable> nounStateBitmapTableMedium; 
	private final Hashtable<DecKanGlNounStateColor, BitmapDrawable> nounStateBitmapTableLarge;
	private final int editorActivityRequestCode;
	private FmmNodeDefinition primaryParentNodeDefinition;
	private FmmNodeDefinition secondaryParentNodeDefinition;
	private FmmNodeDefinition primaryLinkNodeDefinition;  // a LOGICAL node, not the actual node
	private FmmNodeDefinition secondaryLinkNodeDefinition;  // a LOGICAL node, not the actual node
	private FmmNodeDefinition primaryChildNodeDefinition;
	private FmmNodeDefinition secondaryChildNodeDefinition;

	FmmNodeDefinition(
			Class<? extends FmmNode> aNodeClass,
			String aNodeTypeCode,
			int aLabelTextResourceId,
			int aLabelDrawableResourceId,
			int aPickerDrawableResourceId,
			int anActivityDrawableResourceId,
			int aDialogDrawableResourceId,
			HashMap<FmmNodeGlyphType, Integer> aNodeGlyphResourceIdMap,
			Hashtable<DecKanGlNounStateColor, BitmapDrawable> aNounStateBitmapTableTiny, 
			Hashtable<DecKanGlNounStateColor, BitmapDrawable> aNounStateBitmapTableSmall, 
			Hashtable<DecKanGlNounStateColor, BitmapDrawable> aNounStateBitmapTableMedium, 
			Hashtable<DecKanGlNounStateColor, BitmapDrawable> aNounStateBitmapTableLarge,
			int anActivityRequestCode ) {
		this.nodeClass = aNodeClass;
		this.nodeTypeCode = aNodeTypeCode;
		this.className = aNodeClass.getSimpleName();
		this.labelTextResourceId = aLabelTextResourceId;
		this.labelText = FmmHelper.getContext().getResources().getString(this.labelTextResourceId);
		this.labelDrawableResourceId = aLabelDrawableResourceId;
		this.labelDrawable = FmmHelper.getContext().getResources().getDrawable(this.labelDrawableResourceId);
		this.pickerDrawableResourceId = aPickerDrawableResourceId;
		this.activityDrawableResourceId = anActivityDrawableResourceId;
		this.dialogDrawableResourceId = aDialogDrawableResourceId;
		this.nodeGlyphResourceIdMap = aNodeGlyphResourceIdMap;
		this.nounStateBitmapTableTiny= aNounStateBitmapTableTiny;
    	this.nounStateBitmapTableSmall = aNounStateBitmapTableSmall;
    	this.nounStateBitmapTableMedium = aNounStateBitmapTableMedium;
    	this.nounStateBitmapTableLarge = aNounStateBitmapTableLarge;
		this.editorActivityRequestCode = anActivityRequestCode;
	}

	public String getName() {
		return getClassName();
	}

	public String getClassName() {
		return this.className;
	}
	
	@Override
	public NodeId getNodeId() {
		return null;
	}

	@Override
	public Date getRowTimestamp() {
		return this.timestamp;
	}

	@Override
	public void setRowTimestamp(Date aTimestamp) {
		this.timestamp = aTimestamp;
	}
	
	@Override
	public String getNodeIdString() {
		return getName();
	}
	
	@Override
	public String getAbbreviatedNodeIdString() {
		return getNodeIdString();
	}
	
	@Override
	public String getNodeTypeName() {
		return getName();
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	public Class<? extends FmmNode> getNodeClass() {
		return this.nodeClass;
	}
	
	@Override
	public String getLabelText() {
		return this.labelText;
	}
	
	public int getLabelTextResourceId() {
		return this.labelTextResourceId;
	}

	@Override
	public Drawable getLabelDrawable() {
		return this.labelDrawable;
	}
	
	@Override
	public int getLabelDrawableResourceId() {
		return this.labelDrawableResourceId;
	}
	
	@Override
	public String getNodeTypeCode() {
		return this.nodeTypeCode;
	}
	
	@Override
	public int getNodeEditorActivityRequestCode() {
		return this.editorActivityRequestCode;
	}
	
	@Override
	public int getNodeCreateActivityRequestCode() {
		return this.editorActivityRequestCode * 10;
	}
	
	@Override
	public int getNodePickerActivityRequestCode() {
		return this.editorActivityRequestCode * 100;
	}
	
	public boolean isDeKanGlNoun() {
		return FmmNodeDefinition.DECKANGL__NODE_LIST.indexOf(this) > -1;
	}

	public String getViewLabel() {
		return getLabelText();
	}
	
	public HashMap<FmmNodeGlyphType, Integer> getNodeGlyphResourceIdMap() {
		return this.nodeGlyphResourceIdMap;
	}
	
	public int getNodeGlyphResourceId(FmmNodeGlyphType aNodeGlyphType) {
		return this.nodeGlyphResourceIdMap.get(aNodeGlyphType);
	}
	
	public int getUndecoratedGlyphResourceId(FmmNodeGlyphType anFmmNodeGlyphType) {
		Integer theInteger = getNodeGlyphResourceIdMap().get(anFmmNodeGlyphType);
		return theInteger == null
				? R.drawable.icon_undefined
				: theInteger;
	}

	public static String getLabelTextForClass(Class<? extends FmmNode> aNodeClass) {
		FmmNodeDefinition anFmmNodeDefinition = FmmNodeDefinition.getEntryForClass(aNodeClass);
		return anFmmNodeDefinition.getLabelText();
	}
	
	@Override
	public String getDataText() {
		return "";
	}
	
	@Override
	public Drawable getDataDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(getUndecoratedGlyphResourceId(FmmNodeGlyphType.GRAY));
	}

	@Override
	public int getDataDrawableResourceId() {
		return getUndecoratedGlyphResourceId(FmmNodeGlyphType.GRAY);
	}

	@Override
	public FmmNodeDefinition getFmmNodeDefinition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isModified(String aSerializedObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FmmNodeImpl getClone() {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPickerDrawableResourceId() {
		return this.pickerDrawableResourceId;
	}

	public BitmapDrawable getWorkStateDrawableForColor(String theNounStateColorName) {
		return this.nounStateBitmapTableSmall.get(DecKanGlNounStateColor.getObjectForName(theNounStateColorName));
	}

	public Hashtable<DecKanGlNounStateColor, BitmapDrawable> getNounStateBitmapTableTiny() {
		return this.nounStateBitmapTableTiny;
	}

	public Hashtable<DecKanGlNounStateColor, BitmapDrawable> getNounStateBitmapTableSmall() {
		return this.nounStateBitmapTableSmall;
	}

	public Hashtable<DecKanGlNounStateColor, BitmapDrawable> getNounStateBitmapTableMedium() {
		return this.nounStateBitmapTableMedium;
	}

	public Hashtable<DecKanGlNounStateColor, BitmapDrawable> getNounStateBitmapTableLarge() {
		return this.nounStateBitmapTableLarge;
	}

	public int getDataDrawableResourceId(CompletableWorkStatus aCompletableWorkStatus) {
		return this.nodeGlyphResourceIdMap.get(aCompletableWorkStatus.getFmmNodeGlyphType());
	}
	
	public FmmNodeDefinition getPrimaryParentNodeDefinition() {
		return this.primaryParentNodeDefinition;
	}

	public void setPrimaryParentNodeDefinition(FmmNodeDefinition aPrimaryParentNodeDefinition) {
		this.primaryParentNodeDefinition = aPrimaryParentNodeDefinition;
	}

	public FmmNodeDefinition getSecondaryParentNodeDefinition() {
		return this.secondaryParentNodeDefinition;
	}

	public void setSecondaryParentNodeDefinition(FmmNodeDefinition aSecondaryParentNodeDefinition) {
		this.secondaryParentNodeDefinition = aSecondaryParentNodeDefinition;
	}

	public FmmNodeDefinition getPrimaryLinkNodeDefinition() {
		return this.primaryLinkNodeDefinition;
	}

	public void setPrimaryLinkNodeDefinition(FmmNodeDefinition aPrimaryLinkNodeDefinition) {
		this.primaryLinkNodeDefinition = aPrimaryLinkNodeDefinition;
	}

	public FmmNodeDefinition getSecondaryLinkNodeDefinition() {
		return this.secondaryLinkNodeDefinition;
	}

	public void setSecondaryLinkNodeDefinition(FmmNodeDefinition aSecondaryLinkNodeDefinition) {
		this.secondaryLinkNodeDefinition = aSecondaryLinkNodeDefinition;
	}

	@Override
	public Date updateRowTimestamp() { return null; }

	public boolean isPrimarySequenceNode(FmmNodeDefinition anFmmNodeDefinition) {
		return anFmmNodeDefinition == getPrimaryParentNodeDefinition();
	}

	public boolean isSecondarySequenceNode(FmmNodeDefinition anFmmNodeDefinition) {
		return anFmmNodeDefinition == getSecondaryParentNodeDefinition();
	}

	public FmmNodeDefinition getPrimaryChildNodeDefinition() {
		return this.primaryChildNodeDefinition;
	}

	public void setPrimaryChildNodeDefinition(FmmNodeDefinition childNodeDefinition) {
		this.primaryChildNodeDefinition = childNodeDefinition;
	}

	public FmmNodeDefinition getSecondaryChildNodeDefinition() {
		return this.secondaryChildNodeDefinition;
	}

	public void setSecondaryChildNodeDefinition(FmmNodeDefinition secondaryyChildNodeDefinition) {
		this.secondaryChildNodeDefinition = secondaryyChildNodeDefinition;
	}

	public int getActivityDrawableResourceId() {
		return this.activityDrawableResourceId;
	}

	public int getDialogDrawableResourceId() {
		return this.dialogDrawableResourceId;
	}

    public boolean isAlphaSort() {
        return FmmNodeDefinition.ALPHA_SORT__NODE_LIST.contains(this);
    }
}
