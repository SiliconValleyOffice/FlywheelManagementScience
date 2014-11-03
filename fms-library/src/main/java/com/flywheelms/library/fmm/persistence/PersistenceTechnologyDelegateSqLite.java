/* @(#)PersistenceTechnologyDelegateSqLite.java
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

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fmm.database.sqlite.dao.BookshelfDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.BookshelfLinkToNotebookDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.CadenceDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.CadenceWorkPackageCommitmentDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.CommunityMemberDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.CommunityMemberOrganizationGovernanceAuthorityDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.DiscussionTopicDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.DiscussionTopicLinkToNodeFragAuditBlockDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.FiscalYearDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.FiscalYearHolidayBreakDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.FlywheelTeamDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.FmmConfigurationDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.FmmNodeDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.FmsOrganizationDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.FragLockDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.HeadlineNodeTrashDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.NodeFragAuditBlockDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.NodeFragCompletionDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.NodeFragFseDocumentDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.NodeFragGovernanceDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.NodeFragTribKnQualityDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.NodeFragWorkTaskBudgetDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.NotebookDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.NotebookLinkToDiscussionTopicDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.OrganizationCommunityMemberDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.PdfPublicationDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.PortfolioDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.ProjectAssetDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.ProjectDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.StrategicAssetDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.StrategicCommitmentDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.StrategicMilestoneDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.WorkAssetDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.WorkPackageDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.WorkPlanDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.WorkTaskDaoSqLite;
import com.flywheelms.library.fmm.helper.FmmOpenHelper;
import com.flywheelms.library.fmm.interfaces.WorkAsset;
import com.flywheelms.library.fmm.meta_data.CadenceMetaData;
import com.flywheelms.library.fmm.meta_data.CadenceWorkPackageCommitmentMetaData;
import com.flywheelms.library.fmm.meta_data.CommunityMemberMetaData;
import com.flywheelms.library.fmm.meta_data.CommunityMemberOrganizationGovernanceAuthorityMetaData;
import com.flywheelms.library.fmm.meta_data.CompletableNodeMetaData;
import com.flywheelms.library.fmm.meta_data.FiscalYearHolidayBreakMetaData;
import com.flywheelms.library.fmm.meta_data.FiscalYearMetaData;
import com.flywheelms.library.fmm.meta_data.FmmConfigurationMetaData;
import com.flywheelms.library.fmm.meta_data.HeadlineNodeMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragFseDocumentMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragMetaData;
import com.flywheelms.library.fmm.meta_data.OrganizationCommunityMemberMetaData;
import com.flywheelms.library.fmm.meta_data.PdfPublicationMetaData;
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
import com.flywheelms.library.fmm.node.impl.commitment.StrategicCommitment;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceRole;
import com.flywheelms.library.fmm.node.impl.enumerator.GovernanceTarget;
import com.flywheelms.library.fmm.node.impl.event.PdfPublication;
import com.flywheelms.library.fmm.node.impl.governable.Cadence;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
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
import com.flywheelms.library.fmm.node.impl.link.OrganizationCommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.FragLock;
import com.flywheelms.library.fmm.node.impl.nodefrag.HeadlineNodeTrash;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragCompletion;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragFseDocument;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragGovernance;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragTribKnQuality;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragWorkTaskBudget;
import com.flywheelms.library.fmm.node.interfaces.FmmNodeFrag;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.fmm.repository.FmmConfiguration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;

public class PersistenceTechnologyDelegateSqLite extends PersistenceTechnologyDelegate {

    private static final String sort_spec__HEADLINE = " LOWER (" + HeadlineNodeMetaData.column_HEADLINE + ") ASC";
    private static final String sort_spec__SEQUENCE = CompletableNodeMetaData.column_SEQUENCE + " ASC";
	private SQLiteDatabase sqLiteDatabase;
	private ContentValues contentValues = new ContentValues();
    private HashMap<FmmNodeDefinition, FmmNodeDaoSqLite> daoMap = new HashMap<FmmNodeDefinition, FmmNodeDaoSqLite>();

	public PersistenceTechnologyDelegateSqLite() {
		this(PersistenceTechnology.SQLITE);
	}

	public PersistenceTechnologyDelegateSqLite(PersistenceTechnology aPersistenceTechnology) {
		super(aPersistenceTechnology);
        initializeDaoMap();
	}
    
    private void initializeDaoMap() {
            this.daoMap.put(FmmNodeDefinition.BOOKSHELF, BookshelfDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.BOOKSHELF_LINK_TO_NOTEBOOK, BookshelfLinkToNotebookDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.CADENCE, CadenceDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.CADENCE_WORK_PACKAGE_COMMITMENT, CadenceWorkPackageCommitmentDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.COMMUNITY_MEMBER, CommunityMemberDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.COMMUNITY_MEMBER_ORGANIZATION_GOVERNANCE_AUTHORITY, CommunityMemberOrganizationGovernanceAuthorityDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.HEADLINE_NODE_TRASH, HeadlineNodeTrashDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.DISCUSSION_TOPIC, DiscussionTopicDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.DISCUSSION_TOPIC_LINK_TO_NODE_FRAG_AUDIT_BLOCK, DiscussionTopicLinkToNodeFragAuditBlockDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.FISCAL_YEAR, FiscalYearDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.FISCAL_YEAR_HOLIDAY_BREAK, FiscalYearHolidayBreakDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.FLYWHEEL_TEAM, FlywheelTeamDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.FMM_CONFIGURATION, FmmConfigurationDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.FMS_ORGANIZATION, FmsOrganizationDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.FRAG_LOCK, FragLockDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.NODE_FRAG__AUDIT_BLOCK, NodeFragAuditBlockDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.NODE_FRAG__COMPLETION, NodeFragCompletionDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT, NodeFragFseDocumentDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.NODE_FRAG__GOVERNANCE, NodeFragGovernanceDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.NODE_FRAG__TRIBKN_QUALITY, NodeFragTribKnQualityDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.NODE_FRAG__WORK_TASK_BUDGET, NodeFragWorkTaskBudgetDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.NOTEBOOK, NotebookDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.NOTEBOOK_LINK_TO_DISCUSSION_TOPIC, NotebookLinkToDiscussionTopicDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER, OrganizationCommunityMemberDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.PORTFOLIO, PortfolioDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.PDF_PUBLICATION, PdfPublicationDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.PROJECT, ProjectDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.PROJECT_ASSET, ProjectAssetDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.STRATEGIC_ASSET, StrategicAssetDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.STRATEGIC_COMMITMENT, StrategicCommitmentDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.STRATEGIC_MILESTONE, StrategicMilestoneDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.WORK_ASSET, WorkAssetDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.WORK_PACKAGE, WorkPackageDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.WORK_PLAN, WorkPlanDaoSqLite.getInstance());
            this.daoMap.put(FmmNodeDefinition.WORK_TASK, WorkTaskDaoSqLite.getInstance());
    }

    public FmmNodeDaoSqLite getDao(FmmNodeDefinition anFmmNodeDefinition) {
        return this.daoMap.get(anFmmNodeDefinition);
    }

    public FmmNodeDaoSqLite getDao(FmmNode anFmmNode) {
        return this.daoMap.get(anFmmNode.getFmmNodeDefinition());
    }

    @Override
	public <T extends FmmConfiguration> void setActiveDatabase(T anFmmConfiguration) {
		if(this.sqLiteDatabase != null) {
			this.sqLiteDatabase.close();
		}
		this.activeDatabaseName = anFmmConfiguration.getDbName();
		this.sqLiteDatabase = FmmOpenHelper.getInstance(anFmmConfiguration.getFileName()).getWritableDatabase();
	}

	private SQLiteDatabase getSqLiteDatabase() {
        // TODO - need to save config name onPause() and restore config name onResume()
        // then use config name to initialize sqLiteDatabase
		return this.sqLiteDatabase;
	}

	@Override
	public void dbPragma() {
        this.sqLiteDatabase.execSQL("PRAGMA foreign_keys=ON;");
	}

	@Override
	public void dbClose() {
		if(this.sqLiteDatabase != null) {
			this.sqLiteDatabase.close();
		}
	}
	
	@Override
	public void startTransaction() {
		getSqLiteDatabase().beginTransaction();
	}
	
	@Override
	public void endTransaction(boolean bSuccess) {
		if(bSuccess) {
			getSqLiteDatabase().setTransactionSuccessful();
		}
		getSqLiteDatabase().endTransaction();
	}


    ////////////////////////////////////////////////////
    ////////  RETRIEVE DATA - PUBLIC - start  //////////

    public Date getRowTimestamp(String aClassName, String aNodeIdString) {
        Cursor theCursor = getSqLiteDatabase().rawQuery(
                "SELECT " + IdNodeMetaData.column_ROW_TIMESTAMP + " FROM " + aClassName +
                        "WHERE " + IdNodeMetaData.column_ID + " = " + aNodeIdString,
                null );
        Date theDate = null;
        if(theCursor.getCount() > 0) {
            long theLong = theCursor.getLong(theCursor.getColumnIndex(IdNodeMetaData.column_ROW_TIMESTAMP));
            theDate = GcgDateHelper.getDateFromFormattedUtcLong(theLong);
        }
        theCursor.close();
        return theDate;
    }

    private String getStringValue(FmmNodeDefinition anFmmNodeDefinition, String aSourceColumnName, String aWhereSpec) {
        String theString = null;
        Cursor theCursor = getSqLiteDatabase().rawQuery(
                "SELECT " + aSourceColumnName + " FROM " + anFmmNodeDefinition.getTableName() +
                        " WHERE " + aWhereSpec, null );
        if(theCursor.moveToFirst()) {
            theString = theCursor.getString(0);
        }
        theCursor.close();
        return theString;
    }
	
	@Override
	public ArrayList<String> getFmmNodeIdList(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aColumnValue) {
		ArrayList<String> theRowIdList = new ArrayList<String>();
		Cursor theCursor = getSqLiteDatabase().rawQuery(
				"SELECT " + IdNodeMetaData.column_ID + " FROM " + anFmmNodeDefinition.getTableName() +
				" WHERE " + aColumnName + " = '" + aColumnValue + "'", null );
		if(theCursor.getCount() == 0) {
			theCursor.close();
			return theRowIdList;
		}
		while(theCursor.moveToNext()) {
			theRowIdList.add(theCursor.getString(0));
		}
		theCursor.close();
		return theRowIdList;
	}
	
	@Override
	public ArrayList<String> getFmmNodeIdList(FmmNodeDefinition anFmmNodeDefinition, String aWhereClause) {
		ArrayList<String> theRowIdList = new ArrayList<String>();
		Cursor theCursor = getSqLiteDatabase().rawQuery(
				"SELECT " + IdNodeMetaData.column_ID + " FROM " + anFmmNodeDefinition.getTableName() +
				" WHERE " + aWhereClause, null );
		if(theCursor.getCount() == 0) {
			theCursor.close();
			return theRowIdList;
		}
		while(theCursor.moveToNext()) {
			theRowIdList.add(theCursor.getString(0));
		}
		theCursor.close();
		return theRowIdList;
	}
	
	@Override
	public ArrayList<String> dbGetRowIdListSorted(FmmNodeDefinition anFmmNodeDefinition, String aWhereColumnValue, String aWhereSortColumnName, String anOrderBySpec) {
		ArrayList<String> theRowIdList = new ArrayList<String>();
		Cursor theCursor = getSqLiteDatabase().rawQuery(
				"SELECT " + IdNodeMetaData.column_ID + " FROM " + anFmmNodeDefinition.getTableName() +
				" WHERE " + aWhereColumnValue + " = '" + aWhereColumnValue + "'" +
				" ORDER BY " + anOrderBySpec, null );
		if(theCursor.getCount() == 0) {
			theCursor.close();
			return theRowIdList;
		}
		while(theCursor.moveToNext()) {
			theRowIdList.add(theCursor.getString(0));
		}
		theCursor.close();
		return theRowIdList;
	}
	
	@Override
	public ArrayList<String> dbGetRowIdListSorted(FmmNodeDefinition anFmmNodeDefinition, String aWhereClause, String anOrderBySpec) {
		ArrayList<String> theRowIdList = new ArrayList<String>();
		Cursor theCursor = getSqLiteDatabase().rawQuery(
				"SELECT " + IdNodeMetaData.column_ID + " FROM " + anFmmNodeDefinition.getTableName() +
				" WHERE " + aWhereClause +
				" ORDER BY " + anOrderBySpec, null );
		if(theCursor.getCount() == 0) {
			theCursor.close();
			return theRowIdList;
		}
		while(theCursor.moveToNext()) {
			theRowIdList.add(theCursor.getString(0));
		}
		theCursor.close();
		return theRowIdList;
	}

    ////////  RETRIEVE DATA - PUBLIC - end  ////////////
    ////////////////////////////////////////////////////


    /////////////////////////////////////////////////////
    //////// GENERATE SQL - PRIVATE - start  ////////////

	private static String getInnerJoinQueryOnLeftKeySorted(
			String aLeftTableName, String aLeftColumnName, String aRightTableName, String aRightColumnName, String aColumnValue, String anOrderBySpec) {
		return "SELECT DISTINCT " + aLeftTableName + ".* FROM " + aLeftTableName +
				" INNER JOIN " + aRightTableName +
				" ON " + aLeftTableName + "." + aLeftColumnName + " = " + aRightTableName + "." + aRightColumnName +
				" AND " + aLeftTableName + "." + aLeftColumnName + " = '" + aColumnValue + "'" +
				" ORDER BY " + anOrderBySpec;
	}

	public static String getInnerJoinQueryWithAndSpecSorted(
			String aLeftTableName, String aLeftColumnName, String aRightTableName, String aRightColumnName, String aAndSpec, String anOrderBySpec) {
		return "SELECT DISTINCT " + aLeftTableName + ".* FROM " + aLeftTableName +
				" INNER JOIN " + aRightTableName +
				" ON " + aLeftTableName + "." + aLeftColumnName + " = " + aRightTableName + "." + aRightColumnName +
				" AND " + aAndSpec +
				" ORDER BY " + anOrderBySpec;
	}

    //////// GENERATE SQL - PRIVATE - end  //////////////
    /////////////////////////////////////////////////////


    /////////////////////////////////////////////////////
    //////// LINK TABLES - start  ///////////////////////

    private boolean deleteAllParentRowsFromSequencedLinkTable(
            FmmNodeDefinition anFmmNodeDefinition,
            String aParentColumnName,
            String aParentId,
            boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean theBoolean = getSqLiteDatabase().delete(
                anFmmNodeDefinition.getTableName(),
                aParentColumnName + " = '" + aParentId  + "'", null) > 0;
        if(bAtomicTransaction) {
            endTransaction(theBoolean);
        }
        return theBoolean;
    }

    private boolean deleteAllChildRowsFromSequencedLinkTable(
            FmmNodeDefinition anFmmNodeDefinition,
            String aParentColumnName,
            String aChildColumnName,
            String aChildId,
            boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        Cursor theCursor = getSqLiteDatabase().rawQuery(
                "SELECT " + aParentColumnName + " FROM " + anFmmNodeDefinition.getTableName() +
                        " WHERE " + aChildColumnName + " = '" + aChildId + "'", null );
        if(theCursor.getCount() == 0) {
            theCursor.close();
            return true;
        }
        String theParentId;
        while(theCursor.moveToNext()) {
            theParentId = theCursor.getString(0);
            deleteRowFromSequencedLinkTable(
                    anFmmNodeDefinition,
                    aParentColumnName,
                    theParentId,
                    aChildColumnName,
                    aChildId,
                    bAtomicTransaction);
        }
        theCursor.close();
        if(bAtomicTransaction) {
            endTransaction(true);
        }
        return true;
    }

    private boolean deleteRowFromSequencedLinkTable(
            FmmNodeDefinition anFmmNodeDefinition,
            String aParentColumnName,
            String aParentId,
            String aChildColumnName,
            String aChildId,
            boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean theBoolean = getSqLiteDatabase().delete(
                anFmmNodeDefinition.getTableName(),
                aParentColumnName + " = '" + aParentId  + "' AND " + aChildColumnName + " = '" + aChildId + "'", null) > 0;
        if(theBoolean) {
            reSequenceRows(
                    anFmmNodeDefinition.getTableName(),
                    aParentColumnName,
                    aParentId );
        }
        if(bAtomicTransaction) {
            endTransaction(theBoolean);
        }
        return theBoolean;
    }

    @Override
    public int getLinkTableNodeSequence(
            FmmNodeDefinition aLinkTableFmmNodeDefinition,
            String aParentIdColumnName,
            String aParentNodeId,
            String aChildIdColumnName,
            String aChildNodeId ) {
        String theQuery =
                "SELECT " + SequencedLinkNodeMetaData.column_SEQUENCE +
                        " FROM " + aLinkTableFmmNodeDefinition.getTableName() +
                        " WHERE " + aParentIdColumnName + " = '" + aParentNodeId + "' " +
                        " AND " + aChildIdColumnName + " = '" + aChildNodeId + "' ";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theQuery, null);
        if(theCursor.getCount() == 0) {
            theCursor.close();
            return 0;
        }
        theCursor.moveToFirst();
        int theSequence = theCursor.getInt(0);
        theCursor.close();
        return theSequence;
    }

	private boolean moveAllLinkNodes(FmmNodeDefinition anFmmNodeDefinition, String aParentIdColumnName, String anOldParentId, String anNewParentID, boolean bSequenceAtEnd) {
		ArrayList<String> theMovingLinkNodeIdList = dbGetRowIdListSorted(
				anFmmNodeDefinition, aParentIdColumnName, anOldParentId, SequencedLinkNodeMetaData.column_SEQUENCE, true);
		boolean isTransactionSuccess = true;
		if(theMovingLinkNodeIdList.size() > 0){
			if(bSequenceAtEnd) {
				int theMaxSequence = dbGetLastSequence(anFmmNodeDefinition.getTableName(), aParentIdColumnName, anNewParentID);
				int theRowCount = 0;
				for(String theLinkNodeId : theMovingLinkNodeIdList) {
					++theMaxSequence;
					this.contentValues.clear();
					this.contentValues.put(aParentIdColumnName, anNewParentID);
					this.contentValues.put(SequencedLinkNodeMetaData.column_SEQUENCE, theMaxSequence);
					theRowCount += getSqLiteDatabase().update(anFmmNodeDefinition.getTableName(), this.contentValues,
							IdNodeMetaData.column_ID + " = '" + theLinkNodeId + "'", null);
				}
				isTransactionSuccess &= theRowCount == theMovingLinkNodeIdList.size();
			} else {
				ArrayList<String> theExistingLinkNodeIdList = dbGetRowIdListSorted(
						anFmmNodeDefinition, aParentIdColumnName, anNewParentID, SequencedLinkNodeMetaData.column_SEQUENCE, true);
				int theOffset = theMovingLinkNodeIdList.size();
				int theRowCount = 0;
				for(String theLinkNodeId : theExistingLinkNodeIdList) {
					getSqLiteDatabase().execSQL(
							"UPDATE " + anFmmNodeDefinition.getTableName() +
							" SET "+ SequencedLinkNodeMetaData.column_SEQUENCE + " = " + SequencedLinkNodeMetaData.column_SEQUENCE + " + " + theOffset +
							" WHERE " + IdNodeMetaData.column_ID + " = '" + theLinkNodeId + "'" );
				}
				int theNewSequence = 0;
				theRowCount = 0;
				for(String theLinkNodeId : theMovingLinkNodeIdList) {
					++theNewSequence;
					this.contentValues.clear();
					this.contentValues.put(aParentIdColumnName, anNewParentID);
					this.contentValues.put(SequencedLinkNodeMetaData.column_SEQUENCE, theNewSequence);
					theRowCount += getSqLiteDatabase().update(anFmmNodeDefinition.getTableName(), this.contentValues,
							IdNodeMetaData.column_ID + " = '" + theLinkNodeId + "'", null);
				}
				isTransactionSuccess &= theRowCount == theMovingLinkNodeIdList.size();
			}
		}
		return isTransactionSuccess;
	}

	private boolean moveSingleLinkNode(
			FmmNodeDefinition anFmmNodeDefinition,
			String aMoveNodeColumnName,
			String aMoveNodeId,
			String aParentIdColumnName,
			String anOriginalParentId,
			String aNewParentID,
			boolean bSequenceAtEnd ) {
		if(bSequenceAtEnd) {
			int theNewSequence = dbGetLastSequence(anFmmNodeDefinition.getTableName(), aParentIdColumnName, aNewParentID);
			++theNewSequence;
			this.contentValues.clear();
			this.contentValues.put(aParentIdColumnName, aNewParentID);
			this.contentValues.put(SequencedLinkNodeMetaData.column_SEQUENCE, theNewSequence);
		} else {
			ArrayList<String> theExistingLinkNodeIdList = dbGetRowIdListSorted(
					anFmmNodeDefinition, aParentIdColumnName, aNewParentID, SequencedLinkNodeMetaData.column_SEQUENCE, true);
			int theOffset = 1;
			for(String theLinkNodeId : theExistingLinkNodeIdList) {
				getSqLiteDatabase().execSQL(
						"UPDATE " + anFmmNodeDefinition.getTableName() +
						" SET "+ SequencedLinkNodeMetaData.column_SEQUENCE + " = " + SequencedLinkNodeMetaData.column_SEQUENCE + " + " + theOffset +
						" WHERE " + IdNodeMetaData.column_ID + " = '" + theLinkNodeId + "'" );
			}
			this.contentValues.clear();
			this.contentValues.put(aParentIdColumnName, aNewParentID);
			this.contentValues.put(SequencedLinkNodeMetaData.column_SEQUENCE, 1);
		}
		int theRowCount = getSqLiteDatabase().update(anFmmNodeDefinition.getTableName(), this.contentValues,
				aMoveNodeColumnName + " = '" + aMoveNodeId + "'", null);
		reSequenceRows(anFmmNodeDefinition.getTableName(), aParentIdColumnName, anOriginalParentId);
		return theRowCount == 1;
	}

	private boolean adoptOrphanLinkNode(
			FmmNodeDefinition anFmmNodeDefinition,
			String aMoveNodeColumnName,
			String aMoveNodeId,
			String aParentIdColumnName,
			String aNewParentID,
			boolean bSequenceAtEnd ) {
		if(bSequenceAtEnd) {
			int theNewSequence = dbGetLastSequence(anFmmNodeDefinition.getTableName(), aParentIdColumnName, aNewParentID);
			++theNewSequence;
			this.contentValues.clear();
			this.contentValues.put(aParentIdColumnName, aNewParentID);
			this.contentValues.put(SequencedLinkNodeMetaData.column_SEQUENCE, theNewSequence);
		} else {
			ArrayList<String> theExistingLinkNodeIdList = dbGetRowIdListSorted(
					anFmmNodeDefinition, aParentIdColumnName, aNewParentID, SequencedLinkNodeMetaData.column_SEQUENCE, true);
			int theOffset = 1;
			for(String theLinkNodeId : theExistingLinkNodeIdList) {
				getSqLiteDatabase().execSQL(
						"UPDATE " + anFmmNodeDefinition.getTableName() +
						" SET "+ SequencedLinkNodeMetaData.column_SEQUENCE + " = " + SequencedLinkNodeMetaData.column_SEQUENCE + " + " + theOffset +
						" WHERE " + IdNodeMetaData.column_ID + " = '" + theLinkNodeId + "'" );
			}
			this.contentValues.clear();
			this.contentValues.put(aParentIdColumnName, aNewParentID);
			this.contentValues.put(SequencedLinkNodeMetaData.column_SEQUENCE, 1);
		}
		int theRowCount = getSqLiteDatabase().update(anFmmNodeDefinition.getTableName(), this.contentValues,
				aMoveNodeColumnName + " = '" + aMoveNodeId + "'", null);
		return theRowCount == 1;
	}

    /////////////////////////////////////////////////////
    //////// LINK TABLES - end // ///////////////////////


    /////////////////////////////////////////////////////
    //////// SEQUENCE TABLES - start  ///////////////////

    private boolean orphanSequenceRows(String aTableName, String anUpdateColumnName, String aWhereClause, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean theBoolean = true;
        getSqLiteDatabase().execSQL(
                "UPDATE " + aTableName +
                        " SET "+ anUpdateColumnName + " = NULL " +
                        " , " + CompletableNodeMetaData.column_SEQUENCE + " = '0'" +
                        " WHERE " + aWhereClause );
        if(bAtomicTransaction) {
            endTransaction(theBoolean);
        }
        return theBoolean;
    }
	
	@Override
	public void dbSwapSequence(FmmHeadlineNode aParentNode, FmmHeadlineNode aTargetNode, FmmHeadlineNode aPeerNode) {
		if(aTargetNode.getFmmNodeDefinition().isPrimarySequenceNode(aParentNode.getFmmNodeDefinition())) {
			swapPrimarySequence(aTargetNode, aPeerNode);
		} else if(aTargetNode.getFmmNodeDefinition().getSecondaryLinkNodeDefinition() != null) {
			swapLinkNodeSequence(aTargetNode, aPeerNode);
		} else {
			swapSecondarySequence(aTargetNode, aPeerNode);
		}
	}
	
	//-->>  Primary

	private void swapPrimarySequence(FmmHeadlineNode aTargetNode,
			FmmHeadlineNode aPeerNode) {
		int theOriginalTargetSequence = dbGetPrimarySequence(aTargetNode);
		int theOriginalPeerSequence = dbGetPrimarySequence(aPeerNode);
		dbSetPrimarySequence(aTargetNode, theOriginalPeerSequence);
		dbSetPrimarySequence(aPeerNode, theOriginalTargetSequence);
	}
	
	@Override
	public int dbGetPrimarySequence(FmmHeadlineNode aTargetNode) {
		Cursor theCursor = getSqLiteDatabase().rawQuery(
				"SELECT " + SequencedLinkNodeMetaData.column_SEQUENCE +
				" FROM " + aTargetNode.getFmmNodeDefinition().getTableName() +
				" WHERE " + IdNodeMetaData.column_ID + " = '" + aTargetNode.getNodeIdString() + "'" , null);
		theCursor.moveToFirst();
		int theSequence = theCursor.getInt(theCursor.getColumnIndex(SequencedLinkNodeMetaData.column_SEQUENCE));
		theCursor.close();
		return theSequence;
	}

	@Override
	public void dbSetPrimarySequence(FmmHeadlineNode aHeadlineNode, int aNewSequence) {
		getSqLiteDatabase().execSQL(
				"UPDATE " + aHeadlineNode.getFmmNodeDefinition().getTableName() +
				" SET "+ SequencedLinkNodeMetaData.column_SEQUENCE + " = " + aNewSequence +
				" WHERE " + IdNodeMetaData.column_ID + " = '" + aHeadlineNode.getNodeIdString() + "'" );
	}
	
	//-->>  Secondary

	private void swapSecondarySequence(FmmHeadlineNode aTargetNode, FmmHeadlineNode aPeerNode) {
		int theOriginalTargetSequence = dbGetSecondarySequence(aTargetNode);
		int theOriginalPeerSequence = dbGetSecondarySequence(aPeerNode);
		dbSetSecondarySequence(aTargetNode, theOriginalPeerSequence);
		dbSetSecondarySequence(aPeerNode, theOriginalTargetSequence);
	}
	
	@Override
	public int dbGetSecondarySequence(FmmHeadlineNode aTargetNode) {
		Cursor theCursor = getSqLiteDatabase().rawQuery(
				"SELECT " + SequencedLinkNodeMetaData.column_SEQUENCE +
				" FROM " + aTargetNode.getFmmNodeDefinition().getSecondaryParentNodeDefinition().getTableName() +
				" WHERE " + aTargetNode.getFmmNodeDefinition().getClassName() + "__id = '" + aTargetNode.getNodeIdString() + "'" , null);
		theCursor.moveToFirst();
		int theSequence = theCursor.getInt(theCursor.getColumnIndex(SequencedLinkNodeMetaData.column_SEQUENCE));
		theCursor.close();
		return theSequence;
	}

	@Override
	public void dbSetSecondarySequence(FmmHeadlineNode aTargetNode, int aNewSequence) {
		getSqLiteDatabase().execSQL(
				"UPDATE " + aTargetNode.getFmmNodeDefinition().getSecondaryParentNodeDefinition().getTableName() +
				" SET "+ SequencedLinkNodeMetaData.column_SEQUENCE + " = " + aNewSequence +
				" WHERE " + aTargetNode.getFmmNodeDefinition().getClassName() + "__id = '" + aTargetNode.getNodeIdString() + "'" );
	}
	
	//-->>  Link Node

	private void swapLinkNodeSequence(FmmHeadlineNode aTargetNode, FmmHeadlineNode aPeerNode) {
		int theOriginalTargetSequence = dbGetLinkNodeSequence(aTargetNode);
		int theOriginalPeerSequence = dbGetLinkNodeSequence(aPeerNode);
		dbSetLinkNodeSequence(aTargetNode, theOriginalPeerSequence);
		dbSetLinkNodeSequence(aPeerNode, theOriginalTargetSequence);
	}
	
	@Override
	public int dbGetLinkNodeSequence(FmmHeadlineNode aTargetNode) {
		Cursor theCursor = getSqLiteDatabase().rawQuery(
				"SELECT " + SequencedLinkNodeMetaData.column_SEQUENCE +
				" FROM " + aTargetNode.getFmmNodeDefinition().getSecondaryLinkNodeDefinition().getTableName() +
				" WHERE " + aTargetNode.getFmmNodeDefinition().getClassName() + "__id = '" + aTargetNode.getNodeIdString() + "'" , null);
		theCursor.moveToFirst();
		int theSequence = theCursor.getInt(theCursor.getColumnIndex(SequencedLinkNodeMetaData.column_SEQUENCE));
		theCursor.close();
		return theSequence;
	}

	@Override
	public void dbSetLinkNodeSequence(FmmHeadlineNode aTargetNode, int aNewSequence) {
		getSqLiteDatabase().execSQL(
				"UPDATE " + aTargetNode.getFmmNodeDefinition().getSecondaryLinkNodeDefinition().getTableName() +
				" SET "+ SequencedLinkNodeMetaData.column_SEQUENCE + " = " + aNewSequence +
				" WHERE " + aTargetNode.getFmmNodeDefinition().getClassName() + "__id = '" + aTargetNode.getNodeIdString() + "'" );
	}

    //////// SEQUENCE TABLES - end  /////////////////////
    /////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////
    //////////  PRIVATE Cursor METHODS  - start  ////////////

    private Cursor retrieveAllRowsFromTableForColumnValue(FmmNodeDefinition anFmmNodeDefinition, String aWhereColumnName, String aWhereColumnValue ) {
        return getSqLiteDatabase().rawQuery("SELECT * FROM " + anFmmNodeDefinition.getTableName() +
                " WHERE " + aWhereColumnName + " = '" + aWhereColumnValue + "'", null);
    }

    private Cursor retrieveAllRowsFromTableForColumnValueSorted(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aColumnValue, String aSortColumnName ) {
        return getSqLiteDatabase().rawQuery("SELECT * FROM " + anFmmNodeDefinition.getTableName() +
                " WHERE " + aColumnName + " = '" + aColumnValue + "'" +
                " ORDER BY " + aSortColumnName + " ASC ", null);
    }

    //////////  PRIVATE Cursor METHODS  - end  ////////////
    /////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////
    //////////  PUBLIC GENERIC METHODS  - start  ////////////

    //  EXISTS and COUNT

    @Override
    @SuppressWarnings({"resource",  "rawtypes" })
    public boolean existsSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aNodeIdString) {
        Cursor theCursor = getSqLiteDatabase().rawQuery("SELECT * FROM " + anFmmNodeDefinition.getTableName() +
                " WHERE " + IdNodeMetaData.column_ID + " = '" + aNodeIdString + "'", null);
        return theCursor.getCount() > 0;
    }

    @Override
    @SuppressWarnings({"resource",  "rawtypes" })
    public boolean existsSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aWhereColumnName, String aWhereColumnValue) {
        Cursor theCursor = getSqLiteDatabase().rawQuery("SELECT * FROM " + anFmmNodeDefinition.getTableName() +
                " WHERE " + aWhereColumnName + " = '" + aWhereColumnValue + "'", null);
        return theCursor.getCount() > 0;
    }

    // RETRIEVE

    @Override
    @SuppressWarnings({"resource",  "rawtypes" })
    public <T extends FmmNode> T retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aNodeIdString) {
        Cursor theCursor = getSqLiteDatabase().rawQuery("SELECT * FROM " + anFmmNodeDefinition.getTableName() +
                " WHERE " + IdNodeMetaData.column_ID + " = '" + aNodeIdString + "'", null);
        return (T) getDao(anFmmNodeDefinition).getSingleObjectFromCursor(theCursor);
    }

    @Override
    @SuppressWarnings({"resource",  "rawtypes" })
    public <T extends FmmNode> T retrieveFmmNodeFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aColumnValue) {
        Cursor theCursor = getSqLiteDatabase().rawQuery("SELECT * FROM " + anFmmNodeDefinition.getTableName() +
                " WHERE " + aColumnName + " = '" + aColumnValue + "'", null);
        return (T) getDao(anFmmNodeDefinition).getSingleObjectFromCursor(theCursor);
    }

    @SuppressWarnings("resource")
    @Override
    public <T extends FmmNode> ArrayList<T> listSimpleIdTable(
            FmmNodeDefinition aNodeDefinition,
            String aWhereColumnName,
            String aWhereColumnValue,
            String anExceptionId,
            String anOrderBySpec) {
        String theRawQuery = "SELECT * FROM " + aNodeDefinition.getTableName();
        if(aWhereColumnName != null) {
            theRawQuery += " WHERE " + aWhereColumnName + " = '" + aWhereColumnValue + "' ";
        }
        if(anExceptionId != null) {
            theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + anExceptionId + "' ";
        }
        if(anOrderBySpec != null) {
            theRawQuery += " ORDER BY " + anOrderBySpec;
        }
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return getDao(aNodeDefinition).getObjectListFromCursor(theCursor);
    }

    @SuppressWarnings("resource")
    @Override
    public <V extends FmmNode> ArrayList<V> listSimpleIdLeftTableFromLink(
            FmmNodeDefinition aLeftTableDefinition,
            String aLeftColumnName,
            String aLefgColumnExceptionValue,
            FmmNodeDefinition aRightTableDefinition,
            String aRightColumnName,
            String anAndSpec,
            String anOrderBySpec) {
        Cursor theCursor = getSqLiteDatabase().rawQuery(getInnerJoinQueryWithAndSpecSorted(
                        aLeftTableDefinition,
                        aLeftColumnName,
                        aLefgColumnExceptionValue,
                        aRightTableDefinition,
                        aRightColumnName,
                        anAndSpec,
                        anOrderBySpec ),
                null );
        return getDao(aLeftTableDefinition).getObjectListFromCursor(theCursor);
    }

    @SuppressWarnings({"resource",  "rawtypes" })
    public <T extends FmmNode> T retrieveFmmNodeFromTableForParent(FmmNodeDefinition anFmmNodeDefinition, String aParentId) {
        Cursor theCursor = getSqLiteDatabase().rawQuery("SELECT * FROM " + anFmmNodeDefinition.getTableName() +
                " WHERE " + NodeFragMetaData.column_PARENT_ID + " = '" + aParentId + "'", null);
        return (T) getDao(anFmmNodeDefinition).getSingleObjectFromCursor(theCursor);
    }

    // INSERT

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public boolean insertSimpleIdTable(FmmNode anFmmNode, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        anFmmNode.setRowTimestamp(GcgDateHelper.getCurrentDateTime());
        FmmNodeDaoSqLite theDao = getDao(anFmmNode);
        this.contentValues = theDao.buildContentValues(anFmmNode);
        boolean theBoolean = getSqLiteDatabase().insert(anFmmNode.getFmmNodeDefinition().getTableName(), null, this.contentValues) > 0;
        if(bAtomicTransaction) {
            endTransaction(theBoolean);
        }
        return theBoolean;
    }

    // UPDATE

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public <V extends FmmNode> boolean updateSimpleIdTable(V anFmmNode, boolean bAtomicTransaction) {
        FmmNodeDaoSqLite theDaoInstance = getDao(anFmmNode.getFmmNodeDefinition());
        if(bAtomicTransaction) {
            startTransaction();
        }
        anFmmNode.setRowTimestamp(GcgDateHelper.getCurrentDateTime());
        this.contentValues = theDaoInstance.buildUpdateContentValues(anFmmNode);
        boolean theBoolean = getSqLiteDatabase().update(anFmmNode.getFmmNodeDefinition().getTableName(), this.contentValues,
                IdNodeMetaData.column_ID + " = '" + anFmmNode.getNodeIdString() + "'", null) > 0;
        if(bAtomicTransaction) {
            endTransaction(theBoolean);
        }
        return theBoolean;
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public boolean fractalUpdateNodeHeadline(FmmHeadlineNode anFmmHeadlineNode) {
        startTransaction();
        anFmmHeadlineNode.setRowTimestamp(GcgDateHelper.getCurrentDateTime());
        this.contentValues.clear();
        this.contentValues.put(IdNodeMetaData.column_ROW_TIMESTAMP, GcgDateHelper.getFormattedUtcLong(anFmmHeadlineNode.getRowTimestamp()));
        this.contentValues.put(HeadlineNodeMetaData.column_HEADLINE, anFmmHeadlineNode.getHeadline());
        boolean isSuccess = getSqLiteDatabase().update(anFmmHeadlineNode.getFmmNodeDefinition().getTableName(), this.contentValues,
                IdNodeMetaData.column_ID + " = '" + anFmmHeadlineNode.getNodeIdString() + "'", null) > 0;
        anFmmHeadlineNode.getNodeFragAuditBlock().setRowTimestamp(anFmmHeadlineNode.getRowTimestamp());
        anFmmHeadlineNode.getNodeFragAuditBlock().setSearchableHeadline(anFmmHeadlineNode.getHeadline());
        isSuccess &= updateSimpleIdTable(anFmmHeadlineNode.getNodeFragAuditBlock(), false);
        endTransaction(isSuccess);
        return isSuccess;
    }

    private boolean updateRows(String aTableName, String anUpdateColumnName, String  anUpdateColumnValue, String aWhereClause, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean theBoolean = true;
        getSqLiteDatabase().execSQL(
                "UPDATE " + aTableName +
                        " SET "+ anUpdateColumnName + " = '" + anUpdateColumnValue + "'" +
                        " WHERE " + aWhereClause );
        if(bAtomicTransaction) {
            endTransaction(theBoolean);
        }
        return theBoolean;
    }

    private boolean updateRowsWithNull(String aTableName, String anUpdateColumnName, String aWhereClause, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean theBoolean = true;
        getSqLiteDatabase().execSQL(
                "UPDATE " + aTableName +
                        " SET "+ anUpdateColumnName + " = NULL " +
                        " WHERE " + aWhereClause );
        if(bAtomicTransaction) {
            endTransaction(theBoolean);
        }
        return theBoolean;
    }

    // DELETE

    @Override
    public boolean deleteRowFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aNodeIdString, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean theBoolean = getSqLiteDatabase().delete(anFmmNodeDefinition.getTableName(), IdNodeMetaData.column_ID + " = '" + aNodeIdString  + "'", null) > 0;
        if(bAtomicTransaction) {
            endTransaction(theBoolean);
        }
        return theBoolean;
    }

    @Override
    public boolean deleteRowFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aWhereColumnName, String aWhereColumnValue, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean theBoolean = getSqLiteDatabase().delete(anFmmNodeDefinition.getTableName(), aWhereColumnName + " = '" + aWhereColumnValue  + "'", null) > 0;
        if(bAtomicTransaction) {
            endTransaction(theBoolean);
        }
        return theBoolean;
    }

    private boolean deleteAllRowFromSimpleIdTable(FmmNodeDefinition anFmmNodeDefinition, String aWhereClause, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean theBoolean = getSqLiteDatabase().delete(anFmmNodeDefinition.getTableName(), aWhereClause, null) > 0;
        if(bAtomicTransaction) {
            endTransaction(theBoolean);
        }
        return theBoolean;
    }

    private boolean deleteRows(FmmNodeDefinition anFmmNodeDefinition, String aColumnValue, String aColumnName, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean theBoolean = getSqLiteDatabase().delete(anFmmNodeDefinition.getTableName(), aColumnName + " = '" + aColumnValue  + "'", null) > 0;
        if(bAtomicTransaction) {
            endTransaction(theBoolean);
        }
        return theBoolean;
    }
    public boolean deleteRowFromTableForParent(String aParentId, FmmNodeDefinition anFmmNodeDefinition, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean theBoolean = getSqLiteDatabase().delete(anFmmNodeDefinition.getTableName(), NodeFragMetaData.column_PARENT_ID + " = '" + aParentId  + "'", null) > 0;
        if(bAtomicTransaction) {
            endTransaction(theBoolean);
        }
        return theBoolean;
    }

    @Override
    public boolean dbDeleteRowsWithValue(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aValue, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        boolean theBoolean = getSqLiteDatabase().delete(anFmmNodeDefinition.getTableName(), aColumnName + " = '" + aValue  + "'", null) > 0;
        if(bAtomicTransaction) {
            endTransaction(theBoolean);
        }
        return theBoolean;
    }

    //////////  PUBLIC GENERIC METHODS  - end  //////////////
    /////////////////////////////////////////////////////////



























	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - COMMUNITY MEMBER  ////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("resource")
	@Override
	public ArrayList<CommunityMember> dbRetrieveCommunityMemberList() {
		Cursor theCursor = getSqLiteDatabase().rawQuery("SELECT * FROM " + FmmNodeDefinition.COMMUNITY_MEMBER.getTableName(), null);
		return CommunityMemberDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@SuppressWarnings("resource")
	@Override
	public ArrayList<CommunityMember> dbRetrieveCommunityMemberListForOrganization(String anOrganizationId) {
		Cursor theCursor = getSqLiteDatabase().rawQuery(getInnerJoinQueryWithAndSpecSorted(
				FmmNodeDefinition.COMMUNITY_MEMBER.getTableName(),
				IdNodeMetaData.column_ID,
				FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER.getTableName(),
				OrganizationCommunityMemberMetaData.column_COMMUNITY_MEMBER_ID,
				FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER.getTableName() + "." + OrganizationCommunityMemberMetaData.column_ORGANIZATION_ID + " = '" + anOrganizationId + "'",
				FmmNodeDefinition.COMMUNITY_MEMBER.getTableName() + "." + CommunityMemberMetaData.column_FAMILY_NAME + ", " + CommunityMemberMetaData.column_GIVEN_NAME), null);
		return CommunityMemberDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@Override
	public CommunityMember dbRetrieveCommunityMember(String aNodeIdString) {
		return (CommunityMember) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.COMMUNITY_MEMBER);
	}
	
	@Override
	public boolean dbInsertCommunityMember(CommunityMember aCommunityMember, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(
                aCommunityMember, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteCommunityMember(CommunityMember aCommunityMember, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aCommunityMember.getNodeIdString(), FmmNodeDefinition.COMMUNITY_MEMBER, bAtomicTransaction);
	}

	@Override
	public boolean dbExistsCommunityMember(String aNodeIdString) {
		return dbRetrieveCommunityMember(aNodeIdString) != null;
	}
	

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - COMMUNITY MEMBER, ORGANIZATION, GOVERNANCE AUTHORITY  ////////////////////////////////////////

	@SuppressWarnings("resource")
	@Override
	public ArrayList<CommunityMember> getGovernanceCandidates(FmsOrganization anFmsOrganization, GovernanceTarget aGovernanceTarget, GovernanceRole aGovernanceRole) {
		String theRoleColumnName;
		switch(aGovernanceRole) {
			case SPONSOR:
				theRoleColumnName = CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_SPONSOR;
				break;
			case CUSTOMER:
				theRoleColumnName = CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_CUSTOMER;
				break;
			case FACILITATOR:
				theRoleColumnName = CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_FACILITATOR;
				break;
			case ADMINISTRATOR:
				theRoleColumnName = CommunityMemberOrganizationGovernanceAuthorityMetaData.column_CAN_BE_ADMINISTRATOR;
				break;
			default:
				theRoleColumnName = "ERROR";
		}
		String theQuery = "SELECT " + FmmNodeDefinition.COMMUNITY_MEMBER.getTableName() + ".* FROM " + FmmNodeDefinition.COMMUNITY_MEMBER.getTableName() +
				" INNER JOIN " + FmmNodeDefinition.COMMUNITY_MEMBER_ORGANIZATION_GOVERNANCE_AUTHORITY.getTableName() +
				" ON " + FmmNodeDefinition.COMMUNITY_MEMBER.getTableName() + "." + IdNodeMetaData.column_ID + " = " + FmmNodeDefinition.COMMUNITY_MEMBER_ORGANIZATION_GOVERNANCE_AUTHORITY.getTableName() + "." + CommunityMemberOrganizationGovernanceAuthorityMetaData.column_COMMUNITY_MEMBER + " AND " +
				FmmNodeDefinition.COMMUNITY_MEMBER_ORGANIZATION_GOVERNANCE_AUTHORITY.getTableName() + "." + CommunityMemberOrganizationGovernanceAuthorityMetaData.column_ORGANIZATION + " = '" + anFmsOrganization.getNodeIdString() + "' AND " +
				FmmNodeDefinition.COMMUNITY_MEMBER_ORGANIZATION_GOVERNANCE_AUTHORITY.getTableName() + "." + CommunityMemberOrganizationGovernanceAuthorityMetaData.column_GOVERNANCE_TARGET + " = '" + aGovernanceTarget.getName() + "' AND " +
				FmmNodeDefinition.COMMUNITY_MEMBER_ORGANIZATION_GOVERNANCE_AUTHORITY.getTableName() + "." + theRoleColumnName + " = 1";
		Cursor theCursor = getSqLiteDatabase().rawQuery(theQuery, null);
		return CommunityMemberDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - COMPLETION NODE TRASH  /////////////////////////////////////////////////////////////////////////////////////

	@Override
	public Collection<HeadlineNodeTrash> dbRetrieveCompletionNodeTrashList() {
		return retrieveAllFmmNodesFromTable(HeadlineNodeTrashDaoSqLite.getInstance());
	}

	@Override
	public HeadlineNodeTrash dbRetrieveCompletionNodeTrash(String aNodeIdString) {
		return (HeadlineNodeTrash) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.HEADLINE_NODE_TRASH);
	}

	@Override
	public boolean dbInsertCompletionNodeTrash(HeadlineNodeTrash aHeadlineNodeTrash, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(
                aHeadlineNodeTrash, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteCompletionNodeTrash(
			HeadlineNodeTrash aHeadlineNodeTrash,
			boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aHeadlineNodeTrash.getNodeIdString(), FmmNodeDefinition.HEADLINE_NODE_TRASH, bAtomicTransaction);
	}


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - BOOKSHELF  ////////////////////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - NOTEBOOK  ////////////////////////////////////////////////////////////////////////////////


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - DISCUSSION TOPIC  ////////////////////////////////////////////////////////////////////////////


    //////  Node - PORTFOLIO  ////////////////////////////////////////////////////////////////////////////////

    @Override
    public int dbCountPortfolioForProjectMoveTarget(FmsOrganization anFmsOrganization, Portfolio aPortfolioException) {
        return 0;
    }

    @Override
    public ArrayList<? extends GcgGuiable> dbListPortfolioForProjectMoveTarget(FmsOrganization anFmsOrganization, Portfolio aPortfolioException) {
        return null;
    }

    // TODO - should use MOVE_TARGET view and not include confirmed or proposed completions
    @SuppressWarnings("resource")
    @Override
    public ArrayList<Portfolio> dbListPortfolioForWorkAssetMoveTarget(FmsOrganization anFmsOrganization, Project aProjectException) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.PORTFOLIO.getTableName() +
                " WHERE " + IdNodeMetaData.column_ID +
                " IN (" +
                " SELECT " + ProjectMetaData.column_PORTFOLIO_ID + " FROM " + FmmNodeDefinition.PROJECT.getTableName();
        if(aProjectException != null) {
            theRawQuery += " WHERE " + IdNodeMetaData.column_ID + " != '" + aProjectException.getNodeIdString() + "'";
        }
        theRawQuery += ") ";
        theRawQuery += " ORDER BY LOWER(" + HeadlineNodeMetaData.column_HEADLINE + ") ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return PortfolioDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @Override
    public ArrayList<Portfolio> dbListPortfolioForWorkPackageMoveTarget(FmsOrganization anFmsOrganization, WorkAsset aWorkAssetException) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.PORTFOLIO.getTableName() +
            " WHERE " + IdNodeMetaData.column_ID +
            " IN (" +

            " SELECT " + ProjectMetaData.column_PORTFOLIO_ID + " FROM " + FmmNodeDefinition.PROJECT.getTableName() +
            " WHERE " + IdNodeMetaData.column_ID +
            " IN (" +

            " SELECT " + ProjectAssetMetaData.column_PROJECT_ID + " FROM " + FmmNodeDefinition.PROJECT_ASSET.getTableName();
        if(aWorkAssetException != null) {
            theRawQuery += " WHERE " + IdNodeMetaData.column_ID + " != '" + aWorkAssetException.getNodeIdString() + "' ";
        }
        theRawQuery += ")) ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return PortfolioDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @Override
    public ArrayList<? extends GcgGuiable> dbListPortfolioForWorkTaskMoveTarget(FmsOrganization anFmsOrganization, WorkPackage aWorkPackageException) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.PORTFOLIO.getTableName() +
                " WHERE " + IdNodeMetaData.column_ID +
                " IN (" +

                " SELECT " + ProjectMetaData.column_PORTFOLIO_ID + " FROM " + FmmNodeDefinition.PROJECT.getTableName() +
                " WHERE " + IdNodeMetaData.column_ID +
                " IN (" +

                " SELECT " + ProjectAssetMetaData.column_PROJECT_ID + " FROM " + FmmNodeDefinition.PROJECT_ASSET.getTableName() +
                " WHERE " + IdNodeMetaData.column_ID +
                " IN (" +

                " SELECT " + WorkPackageMetaData.column_WORK_ASSET_ID + " FROM " + FmmNodeDefinition.WORK_PACKAGE.getTableName();
        if(aWorkPackageException != null) {
            theRawQuery += " WHERE " + IdNodeMetaData.column_ID + " != '" + aWorkPackageException.getNodeIdString() + "' ";
        }
        theRawQuery += "))) ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return PortfolioDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @Override
    public boolean dbUpdatePortfolio(Portfolio aPortfolio, boolean bAtomicTransaction) {
        return updateSimpleIdTable(aPortfolio, bAtomicTransaction);
    }

    @Override
    public boolean dbAdoptOrphanProjectIntoPortfolio(
            String aProjectId,
            String aPortfolioId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction ) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        this.contentValues.clear();
        this.contentValues.put(ProjectMetaData.column_PORTFOLIO_ID, aPortfolioId);
        int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.PROJECT.getTableName(), this.contentValues,
                IdNodeMetaData.column_ID + " = '" + aProjectId + "'", null);
        if(bAtomicTransaction) {
            endTransaction(theRowCount > 0);
        }
        return theRowCount > 0;
    }

    @Override
    public boolean dbDeletePortfolio(Portfolio aPortfolio, boolean bAtomicTransaction) {
        return deleteRowFromSimpleIdTable(aPortfolio.getNodeIdString(), FmmNodeDefinition.PORTFOLIO, bAtomicTransaction);
    }

    @Override
    public Portfolio dbRetrievePortfolio(String aNodeIdString) {
        return (Portfolio) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.PORTFOLIO);
    }


    //////  Node - PROJECT  ////////////////////////////////////////////////////////////////////////////////

    @Override
    public ArrayList<Project> dbListProject(Portfolio aPortfolio) {
        return dbListProject(aPortfolio.getNodeIdString(), null);
    }

    @Override
    public ArrayList<Project> dbListProject(Portfolio aPortfolio, Project aProjectException) {
        return dbListProject(aPortfolio.getNodeIdString(), aProjectException == null ? null : aProjectException.getNodeIdString());
    }

    @Override
    public ArrayList<Project> dbListProject(String aPortfolioId) {
        return dbListProject(aPortfolioId, null);
    }

    @SuppressWarnings("resource")
    @Override
    public ArrayList<Project> dbListProject(String aPortfolioId, String aProjectExceptionId) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.PROJECT.getTableName() +
                " WHERE " + ProjectMetaData.column_PORTFOLIO_ID + " = '" + aPortfolioId + "'";
        if(aProjectExceptionId != null) {
            theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aProjectExceptionId + "'";
        }
        theRawQuery += " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return ProjectDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @Override
    public ArrayList<Project> dbListProjectOrphansFromPortfolio() {
        String theRawQuery =
                "SELECT * FROM " + FmmNodeDefinition.PROJECT.getTableName() +
                        " WHERE " + ProjectMetaData.column_PORTFOLIO_ID + " IS NULL" +
                        " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return ProjectDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @Override
    public int dbCountProjectsForProjectAssetMoveTarget(Portfolio aPortfolio, Project aProjectException) {
        return 0;
    }

    @Override
    public ArrayList<Project> dbListProjectsForProjectAssetMoveTarget(Portfolio aPortfolio, Project aProjectException) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.PROJECT.getTableName() +
                " WHERE " + ProjectMetaData.column_PORTFOLIO_ID + " = '" + aPortfolio.getNodeIdString() + "'";
        if(aProjectException != null) {
            theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aProjectException.getNodeIdString() + "'";
        }
        theRawQuery += " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return ProjectDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @Override
    public ArrayList<Project> dbListProjectsForWorkPackageMoveTarget(Portfolio aPortfolio, WorkAsset aWorkAssetException) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.PROJECT.getTableName() +
                " WHERE " + ProjectMetaData.column_PORTFOLIO_ID + " = '" + aPortfolio.getNodeIdString() + "' AND " + IdNodeMetaData.column_ID +
                " IN (" +
                " SELECT " + ProjectAssetMetaData.column_PROJECT_ID + " FROM " + FmmNodeDefinition.PROJECT_ASSET.getTableName();
        if(aWorkAssetException != null) {
            theRawQuery += " WHERE " + IdNodeMetaData.column_ID + " != '" + aWorkAssetException.getNodeIdString() + "'";
        }
        theRawQuery += ") ";
        theRawQuery += " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return ProjectDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @Override
    public ArrayList<Project> dbListProjectsForWorkTaskMoveTarget(Portfolio aPortfolio, WorkPackage aWorkPackageException) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.PROJECT.getTableName() +
                " WHERE " + ProjectMetaData.column_PORTFOLIO_ID + " = '" + aPortfolio.getNodeIdString() + "' AND " + IdNodeMetaData.column_ID +
                " IN (" +
                    " SELECT " + ProjectAssetMetaData.column_PROJECT_ID + " FROM " + FmmNodeDefinition.PROJECT_ASSET.getTableName() +
                    " WHERE " + IdNodeMetaData.column_ID + " IN (" +
                        " SELECT " + WorkPackageMetaData.column_WORK_ASSET_ID + " FROM " + FmmNodeDefinition.WORK_PACKAGE.getTableName();
        if(aWorkPackageException != null) {
            theRawQuery += " WHERE " + IdNodeMetaData.column_ID + " != '" + aWorkPackageException.getNodeIdString() + "'";
        }
        theRawQuery += ")) ";
        theRawQuery += " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return ProjectDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @Override
    public boolean dbOrphanAllProjectsFromPortfolio(String aPortfolioId, boolean bAtomicTransaction) {
        return updateRowsWithNull(
                FmmNodeDefinition.PROJECT.getTableName(),
                ProjectMetaData.column_PORTFOLIO_ID,
                ProjectMetaData.column_PORTFOLIO_ID+ " = '" + aPortfolioId + "'",
                bAtomicTransaction );
    }

    @Override
    public boolean dbMoveSingleProjectIntoPortfolio(String aProjectId, String aPortfolioId, boolean bAtomicTransaction) {
        return updateRows(FmmNodeDefinition.PROJECT.getTableName(),
                ProjectMetaData.column_PORTFOLIO_ID,
                aPortfolioId,
                IdNodeMetaData.column_ID + " = '" + aProjectId + "'",
                bAtomicTransaction);
    }

    public boolean dbMoveAllProjectsIntoPortfolio(String aCurrentPortfolioId, String aTargetPortfolioId, boolean bAtomicTransaction) {
        return updateRows(FmmNodeDefinition.PROJECT.getTableName(),
                ProjectMetaData.column_PORTFOLIO_ID,
                aTargetPortfolioId,
                ProjectMetaData.column_PORTFOLIO_ID + " = '" + aCurrentPortfolioId + "'",
                bAtomicTransaction);

    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FISCAL YEAR  /////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings("resource")
	@Override
	public ArrayList<FiscalYear> dbListFiscalYear(FmsOrganization anOrganization, FiscalYear aFiscalYearException) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.FISCAL_YEAR.getTableName() +
				" WHERE " + FiscalYearMetaData.column_ORGANIZATION_ID + " = '" + anOrganization.getNodeIdString() + "'";
		if(aFiscalYearException != null) {
			theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aFiscalYearException.getNodeIdString() + "'";
		}
		theRawQuery += " ORDER BY " + FiscalYearMetaData.column_YEAR_NUMBER;
		Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
		return FiscalYearDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	// TODO - should use MOVE_TARGET view and not include confirmed or proposed completions
	@Override
	public int dbCountFiscalYearForStrategicMilestoneMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.FISCAL_YEAR.getTableName() +
				" WHERE " + FiscalYearMetaData.column_ORGANIZATION_ID + " = '" + anOrganization.getNodeIdString() + "'";
		if(aFiscalYearException != null) {
			theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aFiscalYearException.getNodeIdString() + "'";
		}
		theRawQuery += " ORDER BY " + FiscalYearMetaData.column_YEAR_NUMBER;
		return countRows(theRawQuery);
	}

	// TODO - should use MOVE_TARGET view and not include confirmed or proposed completions
	@SuppressWarnings("resource")
	@Override
	public ArrayList<FiscalYear> dbListFiscalYearForStrategicMilestoneMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.FISCAL_YEAR.getTableName() +
				" WHERE " + FiscalYearMetaData.column_ORGANIZATION_ID + " = '" + anOrganization.getNodeIdString() + "'";
		if(aFiscalYearException != null) {
			theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aFiscalYearException.getNodeIdString() + "'";
		}
		theRawQuery += " ORDER BY " + FiscalYearMetaData.column_YEAR_NUMBER;
		Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
		return FiscalYearDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@Override
	public int dbCountFiscalYearForProjectAssetMoveTarget(FmsOrganization anOrganization, StrategicMilestone aStrategicMilestonException) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.FISCAL_YEAR.getTableName() +
			" WHERE " + IdNodeMetaData.column_ID +
			" IN (" +
				" SELECT " + StrategicMilestoneMetaData.column_FISCAL_YEAR_ID + " FROM " + FmmNodeDefinition.STRATEGIC_MILESTONE.getTableName() +
			") ";
		if(aStrategicMilestonException != null) {
			theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aStrategicMilestonException.getNodeIdString() + "'";
		}
		theRawQuery += " ORDER BY " + FiscalYearMetaData.column_YEAR_NUMBER;
		return countRows(theRawQuery);
	}

	@SuppressWarnings("resource")
	@Override
	public ArrayList<FiscalYear> dbListFiscalYearForProjectAssetMoveTarget(FmsOrganization anOrganization, StrategicMilestone aStrategicMilestonException) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.FISCAL_YEAR.getTableName() +
			" WHERE " + IdNodeMetaData.column_ID +
			" IN (" +
				" SELECT " + StrategicMilestoneMetaData.column_FISCAL_YEAR_ID + " FROM " + FmmNodeDefinition.STRATEGIC_MILESTONE.getTableName();
				if(aStrategicMilestonException != null) {
					theRawQuery += " WHERE " + IdNodeMetaData.column_ID + " != '" + aStrategicMilestonException.getNodeIdString() + "'";
				}
			theRawQuery += ") ";
		theRawQuery += " ORDER BY " + FiscalYearMetaData.column_YEAR_NUMBER;
		Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
		return FiscalYearDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}
	
	@Override
	public int dbCountFiscalYearForWorkPackageMoveTarget(FmsOrganization anOrganization, ProjectAsset aProjectAssetException) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.FISCAL_YEAR.getTableName() +
				" WHERE " + IdNodeMetaData.column_ID +
				" IN (" +
					" SELECT " + StrategicMilestoneMetaData.column_FISCAL_YEAR_ID + " FROM " + FmmNodeDefinition.STRATEGIC_MILESTONE.getTableName() +
					" WHERE " + IdNodeMetaData.column_ID + 
					" IN (" +
						" SELECT " + StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID + " FROM " + FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName();
						if(aProjectAssetException != null) {
							theRawQuery += " WHERE " + StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID + " != '" + aProjectAssetException.getNodeIdString() + "'";
						}
						theRawQuery += ") " +
				") ";
		theRawQuery += theRawQuery + " ORDER BY " + FiscalYearMetaData.column_YEAR_NUMBER;
		return countRows(theRawQuery);
	}
	
	@SuppressWarnings("resource")
	@Override
	public ArrayList<FiscalYear> dbListFiscalYearForWorkPackageMoveTarget(FmsOrganization anOrganization, ProjectAsset aProjectAssetException) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.FISCAL_YEAR.getTableName() +
				" WHERE " + IdNodeMetaData.column_ID +
				" IN (" +
					" SELECT " + StrategicMilestoneMetaData.column_FISCAL_YEAR_ID + " FROM " + FmmNodeDefinition.STRATEGIC_MILESTONE.getTableName() +
					" WHERE " + IdNodeMetaData.column_ID + 
					" IN (" +
						" SELECT " + StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID + " FROM " + FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName();
						if(aProjectAssetException != null) {
							theRawQuery += " WHERE " + StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID + " != '" + aProjectAssetException.getNodeIdString() + "'";
						}
						theRawQuery += ") " +
				") ";
			theRawQuery += " ORDER BY " + FiscalYearMetaData.column_YEAR_NUMBER;
			Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
			return FiscalYearDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	// TODO - should use MOVE_TARGET view and not include confirmed or proposed completions
	@Override
	public int dbCountFiscalYearForCadenceMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.FISCAL_YEAR.getTableName() +
				" WHERE " + FiscalYearMetaData.column_ORGANIZATION_ID + " = '" + anOrganization.getNodeIdString() + "'";
		if(aFiscalYearException != null) {
			theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aFiscalYearException.getNodeIdString() + "'";
		}
		theRawQuery += " ORDER BY " + FiscalYearMetaData.column_YEAR_NUMBER;
		return countRows(theRawQuery);
	}

	// TODO - should use MOVE_TARGET view and not include confirmed or proposed completions
	@SuppressWarnings("resource")
	@Override
	public ArrayList<FiscalYear> dbListFiscalYearForCadenceMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.FISCAL_YEAR.getTableName() +
				" WHERE " + FiscalYearMetaData.column_ORGANIZATION_ID + " = '" + anOrganization.getNodeIdString() + "'";
		if(aFiscalYearException != null) {
			theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aFiscalYearException.getNodeIdString() + "'";
		}
		theRawQuery += " ORDER BY " + FiscalYearMetaData.column_YEAR_NUMBER;
		Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
		return FiscalYearDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}


	@Override
	public FiscalYear dbRetrieveFiscalYear(String aNodeIdString) {
		return (FiscalYear) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.FISCAL_YEAR);
	}
	
	@Override
	public boolean dbInsertFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(
    			aFiscalYear, bAtomicTransaction);
	}
	
	@Override
	public boolean dbUpdateFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(
    			aFiscalYear, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteFiscalYear(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aFiscalYear.getNodeIdString(), FmmNodeDefinition.FISCAL_YEAR, bAtomicTransaction);
	}

    @Override
    public boolean dbExistsFiscalYear(String aNodeIdString) {
        return dbRetrieveFiscalYear(aNodeIdString) != null;
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FISCAL YEAR HOLIDAY BREAK  /////////////////////////////////////////////////////////////////////////////////////

    @Override
    public ArrayList<FiscalYearHolidayBreak> dbGetFiscalYearHolidayBreakList(String aFiscalYearId) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.FISCAL_YEAR_HOLIDAY_BREAK.getTableName() +
                " WHERE " + FiscalYearHolidayBreakMetaData.column_FISCAL_YEAR_ID + " = '" + aFiscalYearId + "'";
        theRawQuery += " ORDER BY " + FiscalYearHolidayBreakMetaData.column_HOLIDAY_DATE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return FiscalYearHolidayBreakDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @Override
    public FiscalYearHolidayBreak dbRetrieveFiscalYearHolidayBreak(String aNodeIdString) {
        return (FiscalYearHolidayBreak) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.FISCAL_YEAR_HOLIDAY_BREAK);
    }

    @Override
    public boolean dbInsertFiscalYearHolidayBreak(FiscalYearHolidayBreak aFiscalYearHolidayBreak, boolean bAtomicTransaction) {
        return insertSimpleIdTable(
                aFiscalYearHolidayBreak, bAtomicTransaction);
    }

    @Override
    public boolean dbUpdateFiscalYearHolidayBreak(FiscalYearHolidayBreak aFiscalYearHolidayBreak, boolean bAtomicTransaction) {
        return updateSimpleIdTable(
                aFiscalYearHolidayBreak, bAtomicTransaction);
    }

    @Override
    public boolean dbDeleteFiscalYearHolidayBreak(FiscalYearHolidayBreak aFiscalYearHolidayBreak, boolean bAtomicTransaction) {
        return deleteRowFromSimpleIdTable(aFiscalYearHolidayBreak.getNodeIdString(), FmmNodeDefinition.FISCAL_YEAR_HOLIDAY_BREAK, bAtomicTransaction);
    }

    @Override
    public boolean dbDeleteAllFiscalYearHolidayBreaks(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
        return deleteAllRowFromSimpleIdTable(
                FiscalYearHolidayBreakMetaData.column_FISCAL_YEAR_ID + " = '" + aFiscalYear.getNodeIdString() + "'",
                FmmNodeDefinition.FISCAL_YEAR_HOLIDAY_BREAK, bAtomicTransaction);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FLYWHEEL CADENCE  ////////////////////////////////////////////////////////////////////////////////

    @Override
    public ArrayList<Cadence> dbGetCadenceList(FiscalYear aFiscalYear) {
        return dbGetCadenceListForFiscalYear(aFiscalYear.getNodeIdString());
    }

    @Override
    public ArrayList<Cadence> dbGetCadenceListForFiscalYear(String aFiscalYearId) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.CADENCE.getTableName() +
                " WHERE " + CadenceMetaData.column_FISCAL_YEAR_ID + " = '" + aFiscalYearId + "'";
        theRawQuery += " ORDER BY " + CadenceMetaData.column_SCHEDULED_END_DATE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return CadenceDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @Override
    public Cadence dbRetrieveCadence(String aNodeIdString) {
        return (Cadence) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.CADENCE);
    }

    @Override
    public boolean dbInsertCadence(Cadence aCadence, boolean bAtomicTransaction) {
        return insertSimpleIdTable(
                aCadence, bAtomicTransaction);
    }

    @Override
    public boolean dbUpdateCadence(Cadence aCadence, boolean bAtomicTransaction) {
        return updateSimpleIdTable(
                aCadence, bAtomicTransaction);
    }

    @Override
    public boolean dbDeleteCadence(Cadence aCadence, boolean bAtomicTransaction) {
        return deleteRowFromSimpleIdTable(aCadence.getNodeIdString(), FmmNodeDefinition.CADENCE, bAtomicTransaction);
    }

    @Override
    public boolean dbDeleteAllCadences(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
        return dbDeleteAllCadencesForFiscalYear(aFiscalYear.getNodeIdString(), bAtomicTransaction);
    }

    @Override
    public boolean dbDeleteAllCadencesForFiscalYear(String aFiscalYearId, boolean bAtomicTransaction) {
        return deleteAllRowFromSimpleIdTable(
                CadenceMetaData.column_FISCAL_YEAR_ID + " = '" + aFiscalYearId + "'",
                FmmNodeDefinition.CADENCE, bAtomicTransaction);
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - WORK PLAN  ///////////////////////////////////////////////////////////////////////////////////////

    @Override
    public ArrayList<WorkPlan> dbGetWorkPlanList(Cadence aCadence) {
        return dbGetWorkPlanListForCadence(aCadence.getNodeIdString());
    }

    @Override
    public ArrayList<WorkPlan> dbGetWorkPlanListForCadence(String aCadenceId) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.WORK_PLAN.getTableName() +
                " WHERE " + WorkPlanMetaData.column_CADENCE_ID + " = '" + aCadenceId + "'";
        theRawQuery += " ORDER BY " + WorkPlanMetaData.column_SCHEDULED_END_DATE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return WorkPlanDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @Override
    public WorkPlan dbRetrieveWorkPlan(String aNodeIdString) {
        return (WorkPlan) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.WORK_PLAN);
    }

    @Override
    public boolean dbInsertWorkPlan(WorkPlan aWorkPlan, boolean bAtomicTransaction) {
        return insertSimpleIdTable(
                aWorkPlan, bAtomicTransaction);
    }

    @Override
    public boolean dbUpdateWorkPlan(WorkPlan aWorkPlan, boolean bAtomicTransaction) {
        return updateSimpleIdTable(
                aWorkPlan, bAtomicTransaction);
    }

    @Override
    public boolean dbDeleteWorkPlan(WorkPlan aWorkPlan, boolean bAtomicTransaction) {
        return deleteRowFromSimpleIdTable(aWorkPlan.getNodeIdString(), FmmNodeDefinition.WORK_PLAN, bAtomicTransaction);
    }

    @Override
    public boolean dbDeleteAllWorkPlans(Cadence aCadence, boolean bAtomicTransaction) {
        return deleteAllRowFromSimpleIdTable(
                WorkPlanMetaData.column_CADENCE_ID + " = '" + aCadence.getNodeIdString() + "'",
                FmmNodeDefinition.WORK_PLAN, bAtomicTransaction);
    }

    @Override
    public boolean dbDeleteAllWorkPlans(FiscalYear aFiscalYear, boolean bAtomicTransaction) {
        return dbDeleteAllWorkPlansForFiscalYear(aFiscalYear.getNodeIdString(), bAtomicTransaction);
    }

    @Override
    public boolean dbDeleteAllWorkPlansForFiscalYear(String aFiscalYearId, boolean bAtomicTransaction) {
        return deleteAllRowFromSimpleIdTable(
                WorkPlanMetaData.column_CADENCE_ID + " IN (" +
                    " SELECT " + IdNodeMetaData.column_ID + " FROM " + FmmNodeDefinition.CADENCE.getTableName() +
                    " WHERE " + CadenceMetaData.column_FISCAL_YEAR_ID + " = '" + aFiscalYearId + "'" +
                ")",
                FmmNodeDefinition.WORK_PLAN, bAtomicTransaction);
    }


	////  Node - FLYWHEEL TEAM  ////////////////////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<FlywheelTeam> dbRetrieveFlywheelTeamList() {
		return retrieveAllFmmNodesFromTable(FlywheelTeamDaoSqLite.getInstance());
	}

	@Override
	public ArrayList<FlywheelTeam> dbRetrieveFlywheelTeamList(FmsOrganization anOrganization) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<FlywheelTeam> dbRetrieveFlywheelTeamList(String anOrganizationId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FlywheelTeam dbRetrieveFlywheelTeam(String aNodeIdString) {
		return (FlywheelTeam) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.FLYWHEEL_TEAM);
	}

	@Override
	public boolean dbInsertFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(aFlywheelTeam, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdateFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(aFlywheelTeam, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteFlywheelTeam(FlywheelTeam aFlywheelTeam, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aFlywheelTeam.getNodeIdString(), FmmNodeDefinition.FLYWHEEL_TEAM, bAtomicTransaction);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG WORK TASK BUDGET  //////////////////////////////////////////////////////////////////////


	@Override
	public ArrayList<NodeFragWorkTaskBudget> dbRetrieveNodeFragWorkTaskBudgetList() {
		return retrieveAllFmmNodesFromTable(NodeFragWorkTaskBudgetDaoSqLite.getInstance());
	}

	@Override
	public NodeFragWorkTaskBudget dbRetrieveNodeFragWorkTaskBudget(String aNodeIdString) {
		return (NodeFragWorkTaskBudget) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.NODE_FRAG__WORK_TASK_BUDGET);
	}

	@Override
	public NodeFragWorkTaskBudget dbRetrieveNodeFragWorkTaskBudgetForParent(String aParentId) {
		return (NodeFragWorkTaskBudget) retrieveFmmNodeFromTableForParent(aParentId, NodeFragWorkTaskBudgetDaoSqLite.getInstance());
	}
	
	@Override
	public boolean dbInsertNodeFragWorkTaskBudget(NodeFragWorkTaskBudget aNodeFragWorkTaskBudget, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(aNodeFragWorkTaskBudget, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdateNodeFragWorkTaskBudget(NodeFragWorkTaskBudget aNodeFragWorkTaskBudget, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(aNodeFragWorkTaskBudget, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteNodeFragWorkTaskBudget(NodeFragWorkTaskBudget aNodeFragWorkTaskBudget, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aNodeFragWorkTaskBudget.getNodeIdString(), FmmNodeDefinition.NODE_FRAG__WORK_TASK_BUDGET, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteNodeFragWorkTaskBudgetForParent(String aParentId, boolean bAtomicTransaction) {
    	return deleteRowFromTableForParent(aParentId, FmmNodeDefinition.NODE_FRAG__WORK_TASK_BUDGET, bAtomicTransaction);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG GOVERNANCE  ////////////////////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<NodeFragGovernance> dbRetrieveNodeFragGovernanceList() {
		return retrieveAllFmmNodesFromTable(NodeFragGovernanceDaoSqLite.getInstance());
	}

	@Override
	public NodeFragGovernance dbRetrieveNodeFragGovernance(String aNodeIdString) {
		return (NodeFragGovernance) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.NODE_FRAG__GOVERNANCE);
	}

	@Override
	public NodeFragGovernance dbRetrieveNodeFragGovernanceForParent(String aParentId) {
		return (NodeFragGovernance) retrieveFmmNodeFromTableForParent(aParentId, NodeFragGovernanceDaoSqLite.getInstance());
	}
	
	@Override
	public boolean dbInsertNodeFragGovernance(NodeFragGovernance aNodeFragGovernance, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(
    			aNodeFragGovernance, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdateNodeFragGovernance(NodeFragGovernance aNodeFragGovernance, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aNodeFragGovernance.getNodeIdString(), FmmNodeDefinition.NODE_FRAG__GOVERNANCE, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteNodeFragGovernance(NodeFragGovernance aNodeFragGovernance, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aNodeFragGovernance.getNodeIdString(), FmmNodeDefinition.NODE_FRAG__GOVERNANCE, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteNodeFragGovernanceForParent(String aParentId, boolean bAtomicTransaction) {
    	return deleteRowFromTableForParent(aParentId, FmmNodeDefinition.NODE_FRAG__GOVERNANCE, bAtomicTransaction);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FMS ORGANIZATION  ////////////////////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<FmsOrganization> dbRetrieveFmsOrganizationList() {
		return retrieveAllFmmNodesFromTable(FmsOrganizationDaoSqLite.getInstance());
	}

	@Override
	public FmsOrganization dbRetrieveFmsOrganization(String aNodeIdString) {
		return (FmsOrganization) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.FMS_ORGANIZATION);
	}

	@Override
	public boolean dbInsertFmsOrganization(FmsOrganization anFmsOrganization, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(
    			anFmsOrganization, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdateFmsOrganization(FmsOrganization anFmsOrganization, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(
    			anFmsOrganization, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteFmsOrganization(FmsOrganization anFmsOrganization, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(anFmsOrganization.getNodeIdString(), FmmNodeDefinition.FMS_ORGANIZATION, bAtomicTransaction);
	}

	@Override
	public FmsOrganization dbGetFmmOwner() {
		String theQueryString = "SELECT " + FmmNodeDefinition.FMS_ORGANIZATION.getTableName() + ".* FROM " + FmmNodeDefinition.FMS_ORGANIZATION.getTableName() +
				" JOIN " + FmmNodeDefinition.FMM_CONFIGURATION.getTableName() +
				" ON " + FmmNodeDefinition.FMS_ORGANIZATION.getTableName() + "." + IdNodeMetaData.column_ID +
				" = " + FmmNodeDefinition.FMM_CONFIGURATION.getTableName() + "." + FmmConfigurationMetaData.column_ORGANIZATION_ID +
			    " AND " + FmmNodeDefinition.FMM_CONFIGURATION.getTableName() + "." + FmmConfigurationMetaData.column_FOR_THIS_FMM + " = 1";
		Cursor theCursor = getSqLiteDatabase().rawQuery(theQueryString, null);
		FmsOrganization theFmsOrganization = FmsOrganizationDaoSqLite.getInstance().getSingleObjectFromCursor(theCursor);
		theCursor.close();
		return theFmsOrganization;
	}

	@Override
	public void dbSetFmmOwner(FmsOrganization anOrganization) {
		FmsOrganization theCurrentFmmOwner = dbGetFmmOwner();
		if(anOrganization.getNodeIdString().equals(theCurrentFmmOwner.getNodeIdString())) {
			return;
		}
		getSqLiteDatabase().execSQL("UPDATE " + FmmNodeDefinition.FMM_CONFIGURATION.getTableName() +
				" SET " + FmmConfigurationMetaData.column_ORGANIZATION_ID + " = '" + anOrganization.getNodeIdString() + "'" +
				" WHERE " + FmmConfigurationMetaData.column_FOR_THIS_FMM + " = 1;");
	}

	@Override
	public void dbSetFmmOwnership(FmsOrganization anOrganization) {
		String theExistingFmsOrganizationId = getStringValue(
				FmmNodeDefinition.FMM_CONFIGURATION,
				FmmConfigurationMetaData.column_ORGANIZATION_ID,
				FmmConfigurationMetaData.column_FOR_THIS_FMM + " = 1");
		if(theExistingFmsOrganizationId == null || theExistingFmsOrganizationId.equals("")) {
			setOwnerForNewFmm(anOrganization);
		} else {
			replaceFmmOwner(anOrganization,theExistingFmsOrganizationId);
		}
	}

	private void setOwnerForNewFmm(FmsOrganization anOrganization) {
		startTransaction();
		dbInsertFmsOrganization(anOrganization, false);
		dbSetFmmOwner(anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.FISCAL_YEAR, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.FLYWHEEL_TEAM, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.STRATEGY_TEAM, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.FUNCTIONAL_TEAM, anOrganization);
		endTransaction(true);
	}

	private void replaceFmmOwner(FmsOrganization anOrganization,String anExistingFmsOrganizationId) {
		startTransaction();
		dbInsertFmsOrganization(anOrganization, false);
		dbSetFmmOwner(anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.FISCAL_YEAR, anExistingFmsOrganizationId, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER, anExistingFmsOrganizationId, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.FLYWHEEL_TEAM, anExistingFmsOrganizationId, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.STRATEGY_TEAM, anExistingFmsOrganizationId, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.FUNCTIONAL_TEAM, anExistingFmsOrganizationId, anOrganization);
		endTransaction(true);
	}

	private void moveRowsToOrganization(FmmNodeDefinition anFmmNodeDefinition, FmsOrganization anOrganization) {
		getSqLiteDatabase().execSQL("UPDATE " + anFmmNodeDefinition.getTableName() +
				" SET " + FiscalYearMetaData.column_ORGANIZATION_ID + " = '" + anOrganization.getNodeIdString() + "';");
	}

	private void moveRowsToOrganization(
			FmmNodeDefinition anFmmNodeDefinition,
			String anExistingFmsOrganizationId,
			FmsOrganization anOrganization) {
		getSqLiteDatabase().execSQL("UPDATE " + anFmmNodeDefinition.getTableName() +
				" SET " + FiscalYearMetaData.column_ORGANIZATION_ID + " = '" + anOrganization.getNodeIdString() + "'" +
				" WHERE " + FiscalYearMetaData.column_ORGANIZATION_ID + " = '" + anExistingFmsOrganizationId + "';");
	}

	////  Node - PROJECT  ////////////////////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<Project> dbRetrieveProjectList() {
		return retrieveAllFmmNodesFromTable(ProjectDaoSqLite.getInstance());
	}

	@Override
	public ArrayList<Project> dbRetrieveProjectList(StrategicMilestone aStrategicMilestone) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Project dbRetrieveProject(String aNodeIdString) {
		return (Project) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.PROJECT);
	}
	
	@Override
	public boolean dbInsertProject(Project aProject, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(aProject, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdateProject(Project aProject, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(aProject, bAtomicTransaction);
	}

    public boolean dbOrphanSingleProjectFromPortfolio(String aProjectNodeIdString, String aPortfolioNodeIdString, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        this.contentValues.clear();
        this.contentValues.putNull(ProjectMetaData.column_PORTFOLIO_ID);
        int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.PROJECT.getTableName(), this.contentValues,
                ProjectMetaData.column_ID + " = '" + aProjectNodeIdString + "'", null);
        if(bAtomicTransaction) {
            endTransaction(theRowCount > 0);
        }
        return theRowCount > 0;
    }

    public boolean dbOrphanAllProjectsFromPortfolio(String aProjectNodeIdString, String aPortfolioNodeIdString, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        this.contentValues.clear();
        this.contentValues.putNull(ProjectMetaData.column_PORTFOLIO_ID);
        int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.PROJECT.getTableName(), this.contentValues,
                ProjectMetaData.column_PORTFOLIO_ID + " = '" + aPortfolioNodeIdString + "'", null);
        if(bAtomicTransaction) {
            endTransaction(theRowCount > 0);
        }
        return theRowCount > 0;
    }

	@Override
	public boolean dbDeleteProject(Project aProject, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aProject.getNodeIdString(), FmmNodeDefinition.PROJECT, bAtomicTransaction);
	}


    ////  Node - WORK ASSET  ////////////////////////////////////////////////////////////////////////////////

    @SuppressWarnings("resource")
    @Override
    public ArrayList<WorkAsset> dbListWorkAssets() {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.WORK_ASSET.getTableName();
        theRawQuery += " ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return WorkAssetDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @SuppressWarnings("resource")
    @Override
    public ArrayList<WorkAsset> dbListWorkAssetsForProject(String aProjectId, String aWorkAssetExceptionId) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.WORK_ASSET.getTableName() +
                " WHERE " + WorkAssetMetaData.column_PROJECT_ID + " = '" + aProjectId + "'";
        if(aWorkAssetExceptionId != null) {
            theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aWorkAssetExceptionId + "'";
        }
        theRawQuery += " ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return WorkAssetDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }


	////  Node - PROJECT ASSET  ////////////////////////////////////////////////////////////////////////////////

    @SuppressWarnings("resource")
    @Override
    public ArrayList<ProjectAsset> dbListProjectAssets() {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.PROJECT_ASSET.getTableName() +
                " WHERE " + ProjectAssetMetaData.column_IS_STRATEGIC + " = 0" +
                " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return ProjectAssetDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

	@Override
	public ArrayList<ProjectAsset> dbListProjectAssets(Project aProject, ProjectAsset aProjectAssetException) {
		return dbListProjectAssetsForProject(aProject.getNodeIdString(), aProjectAssetException == null ? null : aProjectAssetException.getNodeIdString());
	}

	@SuppressWarnings("resource")
	@Override
	public ArrayList<ProjectAsset> dbListProjectAssetsForProject(String aProjectId, String aProjectAssetExceptionId) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.PROJECT_ASSET.getTableName() +
			" WHERE " + ProjectAssetMetaData.column_PROJECT_ID + " = '" + aProjectId + "'" +
            " AND " + ProjectAssetMetaData.column_IS_STRATEGIC + " = 0";
			if(aProjectAssetExceptionId != null) {
				theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aProjectAssetExceptionId + "'";
			}
		theRawQuery += " ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
		Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
		return ProjectAssetDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@Override
	public ArrayList<ProjectAsset> dbListProjectAsset(StrategicMilestone aStrategicMilestone) {
		return dbListProjectAssets(aStrategicMilestone, null);
	}

	@Override
	public ArrayList<ProjectAsset> dbListProjectAssets(StrategicMilestone aStrategicMilestone, ProjectAsset aProjectAssetException) {
		return dbListProjectAssetsForStrategicMilestone(aStrategicMilestone.getNodeIdString(), aProjectAssetException == null ? null : aProjectAssetException.getNodeIdString());
	}

	@SuppressWarnings("resource")
	@Override
	public ArrayList<ProjectAsset> dbListProjectAssetsForStrategicMilestone(String aStrategicMilestoneId, String aProjectAssetExceptionId) {
		Cursor theCursor = getSqLiteDatabase().rawQuery(getInnerJoinQueryWithAndSpecSorted(
				FmmNodeDefinition.PROJECT_ASSET.getTableName(),
				IdNodeMetaData.column_ID,
				FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName(),
				StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID,
				FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName() + "." + StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID + " = '" + aStrategicMilestoneId + "'",
				FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName() + "." + SequencedLinkNodeMetaData.column_SEQUENCE), null);
		return ProjectAssetDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@SuppressWarnings("resource")
	@Override
	public ArrayList<ProjectAsset> dbListProjectAssetInStrategicPlanningForWorkPackageMoveTarget(String aParentId, String aProjectAssetExceptionId) {
        String theAndClause = FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName() + "." + StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID + " = '" + aParentId + "'";
        if(aProjectAssetExceptionId != null) {
            theAndClause += " AND " + FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName() + "." + StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID + " != '" + aProjectAssetExceptionId + "'";
        }
        Cursor theCursor = getSqLiteDatabase().rawQuery(getInnerJoinQueryWithAndSpecSorted(
                FmmNodeDefinition.PROJECT_ASSET.getTableName(),
                IdNodeMetaData.column_ID,
                FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName(),
                StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID,
                theAndClause,
                FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName() + "." + SequencedLinkNodeMetaData.column_SEQUENCE), null);
		return ProjectAssetDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

    public ArrayList<ProjectAsset> dbListProjectAssetInWorkBreakdownForWorkTaskMoveTarget(String aProjectId, String aWorkPackageException) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.PROJECT_ASSET.getTableName() +
                " WHERE " + ProjectAssetMetaData.column_PROJECT_ID + " = '" + aProjectId + "' AND " + IdNodeMetaData.column_ID +
                " IN (" +
                " SELECT " + WorkPackageMetaData.column_WORK_ASSET_ID + " FROM " + FmmNodeDefinition.WORK_PACKAGE.getTableName();
        if(aWorkPackageException != null) {
            theRawQuery += " WHERE " + IdNodeMetaData.column_ID + " != '" + aWorkPackageException + "'";
        }
        theRawQuery += ") ";
        theRawQuery += " ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return ProjectAssetDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

	@SuppressWarnings("resource")
	@Override
	public ArrayList<ProjectAsset> dbListProjectAssetOrphansFromProject() {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.PROJECT_ASSET.getTableName() +
				" WHERE " + ProjectAssetMetaData.column_PROJECT_ID + " IS NULL" +
                " AND " + ProjectAssetMetaData.column_IS_STRATEGIC + " = 0";
			theRawQuery += " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
			Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
			return ProjectAssetDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

    @SuppressWarnings("resource")
    @Override
    public ArrayList<StrategicAsset> dbListStrategicAssetOrphansFromProject() {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.PROJECT_ASSET.getTableName() +
                " WHERE " + StrategicAssetMetaData.column_PROJECT_ID + " IS NULL" +
                " AND " + StrategicAssetMetaData.column_IS_STRATEGIC + " = 1";
        theRawQuery += " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return StrategicAssetDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

	@Override
	public ProjectAsset dbRetrieveProjectAsset(String aNodeIdString) {
		return (ProjectAsset) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.PROJECT_ASSET);
	}
	
	@Override
	public boolean dbInsertProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(aProjectAsset, bAtomicTransaction);
	}
	
	@Override
	public boolean dbUpdateProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(aProjectAsset, bAtomicTransaction);
	}

	@Override
	public boolean dbMoveSingleProjectAssetIntoProject(
            String aProjectAssetId,
            String aSourceProjectId,
            String aDestinationProjectId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        this.contentValues.clear();
        this.contentValues.put(ProjectAssetMetaData.column_PROJECT_ID, aDestinationProjectId);
        this.contentValues.put(CompletableNodeMetaData.column_SEQUENCE, updateSequenceBeforeAddingNewPeer(
                FmmNodeDefinition.PROJECT_ASSET,
                ProjectAssetMetaData.column_PROJECT_ID,
                aDestinationProjectId,
                bSequenceAtEnd ));
        int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.PROJECT_ASSET.getTableName(), this.contentValues,
                IdNodeMetaData.column_ID + " = '" + aProjectAssetId + "'", null);
        if(bAtomicTransaction) {
            endTransaction(theRowCount > 0);
        }
        return theRowCount > 0;
	}

	@Override
	public boolean dbMoveAllProjectAssetsIntoProject(
            String aSourceProjectId,
            String aDestinationProjectId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		this.contentValues.clear();
		this.contentValues.put(ProjectAssetMetaData.column_PROJECT_ID, aDestinationProjectId);
		this.contentValues.put(CompletableNodeMetaData.column_SEQUENCE, updateSequenceBeforeAddingNewPeer(
				FmmNodeDefinition.PROJECT_ASSET,
				ProjectAssetMetaData.column_PROJECT_ID,
				aDestinationProjectId,
				bSequenceAtEnd ));
		int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.PROJECT_ASSET.getTableName(), this.contentValues,
				ProjectAssetMetaData.column_PROJECT_ID + " = '" + aSourceProjectId + "'", null);
		if(bAtomicTransaction) {
			endTransaction(theRowCount > 0);
		}
		return theRowCount > 0;
	}

	@Override
	public boolean dbMoveAllProjectAssetsIntoStrategicMilestone(
            String aSourceStrategicMilestoneId,
            String aDestinationStrategicMilestoneId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
		return moveAllLinkNodes(FmmNodeDefinition.STRATEGIC_COMMITMENT, StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID, aSourceStrategicMilestoneId, aDestinationStrategicMilestoneId, bSequenceAtEnd);
	}

	@Override
	public boolean dbMoveSingleProjectAssetIntoStrategicMilestone(
            String aProjectAssetId,
            String anOriginalStrategicMilestoneId,
            String aDestinationStrategicMilestoneId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
		return moveSingleLinkNode(
				FmmNodeDefinition.STRATEGIC_COMMITMENT,
				StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID,
				aProjectAssetId,
				StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID,
				anOriginalStrategicMilestoneId,
				aDestinationStrategicMilestoneId,
				bSequenceAtEnd);
	}

	@Override
	public boolean dbOrphanSingleProjectAssetFromStrategicMilestone(String aProjectAssetId, String aStrategicMilestoneId, boolean bAtomicTransaction) {
		boolean theResult = deleteRows(aProjectAssetId, StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID, FmmNodeDefinition.STRATEGIC_COMMITMENT, bAtomicTransaction);
		reSequenceRows(FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName(), StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID, aStrategicMilestoneId);
		return theResult;
	}

	@Override
	public boolean dbOrphanAllProjectAssetsFromStrategicMilestone(String aStrategicMilestoneId, boolean bAtomicTransaction) {
		return deleteRows(aStrategicMilestoneId, StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID, FmmNodeDefinition.STRATEGIC_COMMITMENT, bAtomicTransaction);
	}

	@Override
	public boolean dbOrphanSingleProjectAssetFromProject(String aProjectAssetId, String aProjectId, boolean bAtomicTransaction) {
        boolean bSuccess = orphanSequenceRows(
                FmmNodeDefinition.PROJECT_ASSET.getTableName(),
                ProjectAssetMetaData.column_PROJECT_ID,
                IdNodeMetaData.column_ID + " = '" + aProjectAssetId + "'",
                bAtomicTransaction);
        reSequenceRows(
                FmmNodeDefinition.PROJECT_ASSET.getTableName(),
                ProjectAssetMetaData.column_PROJECT_ID,
                aProjectId );
        return bSuccess;
	}

	@Override
	public boolean dbOrphanAllProjectAssetsFromProject(String aProjectId, boolean bAtomicTransaction) {
		return orphanSequenceRows(
                FmmNodeDefinition.PROJECT_ASSET.getTableName(),
                ProjectAssetMetaData.column_PROJECT_ID,
                ProjectAssetMetaData.column_PROJECT_ID + " = '" + aProjectId + "'",
                bAtomicTransaction);
	}

//	@Override
//	public boolean dbAdoptOrphanProjectAssetIntoProject(
//			String aProjectAssetId,
//			String aProjectId,
//			boolean bSequenceAtEnd,
//			boolean bAtomicTransaction ) {
//        if(bAtomicTransaction) {
//            startTransaction();
//        }
//        this.contentValues.clear();
//        this.contentValues.put(ProjectAssetMetaData.column_PROJECT_ID, aProjectId);
//        this.contentValues.put(CompletableNodeMetaData.column_SEQUENCE, updateSequenceBeforeAddingNewPeer(
//                FmmNodeDefinition.PROJECT_ASSET,
//                ProjectAssetMetaData.column_PROJECT_ID,
//                aProjectId,
//                bSequenceAtEnd ));
//        int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.PROJECT_ASSET.getTableName(), this.contentValues,
//                IdNodeMetaData.column_ID + " = '" + aProjectAssetId + "'", null);
//        if(bAtomicTransaction) {
//            endTransaction(theRowCount > 0);
//        }
//        return theRowCount > 0;
//	}

	@Override
	public boolean dbDeleteProjectAsset(ProjectAsset aProjectAsset, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aProjectAsset.getNodeIdString(), FmmNodeDefinition.PROJECT_ASSET, bAtomicTransaction);
	}

	@Override
	public boolean dbExistsProjectAsset(String aNodeIdString) {
		return dbRetrieveProjectAsset(aNodeIdString) != null;
	}

	@Override
	public int dbGetMoveTargetWorkPackageCount(ProjectAsset aProjectAsset, WorkPackage aWorkPackageException) {
		// TODO Auto-generated method stub
		return 0;
	}




    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - STRATEGIC ASSET  ///////////////////////////////////////////////////////////////////////////////////

    @Override
    public ArrayList<StrategicAsset> dbListStrategicAssets(StrategicMilestone aStrategicMilestone) {
        return dbListStrategicAssets(aStrategicMilestone, null);
    }

    @Override
    public ArrayList<StrategicAsset> dbListStrategicAssets(StrategicMilestone aStrategicMilestone, StrategicAsset aStrategicAssetException) {
        return dbListStrategicAssetsForStrategicMilestone(aStrategicMilestone.getNodeIdString(), aStrategicAssetException == null ? null : aStrategicAssetException.getNodeIdString());
    }

    @SuppressWarnings("resource")
    @Override
    public ArrayList<StrategicAsset> dbListStrategicAssetsForStrategicMilestone(String aStrategicMilestoneId, String aStrategicAssetExceptionId) {
        Cursor theCursor = getSqLiteDatabase().rawQuery(getInnerJoinQueryWithAndSpecSorted(
                FmmNodeDefinition.STRATEGIC_ASSET.getTableName(),
                IdNodeMetaData.column_ID,
                FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName(),
                StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID,
                FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName() + "." + StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID + " = '" + aStrategicMilestoneId + "'",
                FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName() + "." + SequencedLinkNodeMetaData.column_SEQUENCE), null);
        return StrategicAssetDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @SuppressWarnings("resource")
    @Override
    public ArrayList<StrategicAsset> dbListStrategicAssetsForProject(String aProjectId, String aStrategicAssetExceptionId) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.STRATEGIC_ASSET.getTableName() +
                " WHERE " + StrategicAssetMetaData.column_PROJECT_ID + " = '" + aProjectId + "'" +
                " AND " + StrategicAssetMetaData.column_IS_STRATEGIC + " = 1 ";
        if(aStrategicAssetExceptionId != null) {
            theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aStrategicAssetExceptionId + "'";
        }
        theRawQuery += " ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return StrategicAssetDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @Override
    public boolean dbMoveSingleStrategicAssetIntoProject(
            String aStrategicAssetId,
            String aSourceProjectId,
            String aDestinationProjectId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        this.contentValues.clear();
        this.contentValues.put(StrategicAssetMetaData.column_PROJECT_ID, aDestinationProjectId);
        this.contentValues.put(CompletableNodeMetaData.column_SEQUENCE, updateSequenceBeforeAddingNewPeer(
                FmmNodeDefinition.PROJECT_ASSET,
                StrategicAssetMetaData.column_PROJECT_ID,
                aDestinationProjectId,
                bSequenceAtEnd ));
        int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.PROJECT_ASSET.getTableName(), this.contentValues,
                IdNodeMetaData.column_ID + " = '" + aStrategicAssetId + "'", null);
        if(bAtomicTransaction) {
            endTransaction(theRowCount > 0);
        }
        return theRowCount > 0;
    }

    @Override
    public boolean dbOrphanSingleStrategicAssetFromProject(String aStrategicAssetId, String aProjectId, boolean bAtomicTransaction) {
        boolean bSuccess = orphanSequenceRows(
                FmmNodeDefinition.STRATEGIC_ASSET.getTableName(),
                StrategicAssetMetaData.column_PROJECT_ID,
                IdNodeMetaData.column_ID + " = '" + aStrategicAssetId + "'",
                bAtomicTransaction);
        reSequenceRows(
                FmmNodeDefinition.STRATEGIC_ASSET.getTableName(),
                StrategicAssetMetaData.column_PROJECT_ID,
                aProjectId );
        return bSuccess;
    }

    public boolean dbMoveAllStrategicAssetsIntoStrategicMilestone(String aSourceStrateticMilestoneId, String aDestinationStrategicMilestoneId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {         return false;     }

    public boolean dbMoveSingleStrategicAssetIntoStrategicMilestone(String aStrategicAssetId, String anOriginalStrategicMilestoneId, String aDestinationStrategicMilestoneId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        return moveSingleLinkNode(
                FmmNodeDefinition.STRATEGIC_COMMITMENT,
                StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID,
                aStrategicAssetId,
                StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID,
                anOriginalStrategicMilestoneId,
                aDestinationStrategicMilestoneId,
                bSequenceAtEnd);
    }

    public ArrayList<StrategicAsset> dbListStrategicAssetsWithNoProject() {         return null;     }

    public boolean dbUpdateProjectAssetIsStrategic(String aProjectAssetId, boolean bStrategic) {         return false;     }


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////Node - STRATEGIC COMMITMENT  //////////////////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<StrategicCommitment> dbRetrieveStrategicCommitmentList() {
		return retrieveAllFmmNodesFromTable(StrategicCommitmentDaoSqLite.getInstance());
	}

	@Override
	public ArrayList<StrategicCommitment> dbRetrieveStrategicCommitmentList(FiscalYear aStrategicCommitment) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("resource")
	@Override
	public ArrayList<StrategicCommitment> dbRetrieveStrategicCommitmentListForStrategicMilestone(String aStrategicMilestoneNodeId) {
		Cursor theCursor = getSqLiteDatabase().rawQuery("SELECT * FROM " + FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName() +
				" WHERE " + StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID + " = '" + aStrategicMilestoneNodeId + "';", null);
		return StrategicCommitmentDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@Override
	public StrategicCommitment dbRetrieveStrategicCommitment(String aNodeIdString) {
		return (StrategicCommitment) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.STRATEGIC_COMMITMENT);
	}

	@SuppressWarnings("resource")
	@Override
	public StrategicCommitment dbRetrieveStrategicCommitment(String aStrategicMilestoneId, String aProjectAssetId) {
		Cursor theCursor = getSqLiteDatabase().rawQuery("SELECT * FROM " + FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName() +
				" WHERE " + StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID + " = '" + aStrategicMilestoneId + "'" +
				" AND " + StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID + " = '" + aProjectAssetId + "'", null);
		return StrategicCommitmentDaoSqLite.getInstance().getSingleObjectFromCursor(theCursor);
	}

	@SuppressWarnings("resource")
	@Override
	public StrategicCommitment dbRetrieveStrategicCommitmentForProjectAsset(String aProjectAssetId) {
		Cursor theCursor = getSqLiteDatabase().rawQuery("SELECT * FROM " + FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName() +
				" WHERE " + StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID + " = '" + aProjectAssetId + "'", null);
		return StrategicCommitmentDaoSqLite.getInstance().getSingleObjectFromCursor(theCursor);
	}

	@Override
	public boolean dbUpdateStrategicCommitment(StrategicCommitment aStrategicCommitment, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(
                aStrategicCommitment, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteStrategicCommitment(StrategicCommitment aStrategicCommitment, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aStrategicCommitment.getNodeIdString(), FmmNodeDefinition.STRATEGIC_COMMITMENT, bAtomicTransaction);
	}

    @Override
    public boolean dbDeleteStrategicCommitment(String aStrategicAssetId, boolean bAtomicTransaction) {
        return dbDeleteRowsWithValue(FmmNodeDefinition.STRATEGIC_COMMITMENT, StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID, aStrategicAssetId, bAtomicTransaction);
    }

    @Override
    public boolean dbDeleteStrategicCommitment(String aStrategicCommitmentId, String aProjectAssetId, boolean bAtomicTransaction) {
        return false;
    }

    ////  Node - STRATEGIC MILESTONE  ////////////////////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<StrategicMilestone> dbListStrategicMilestone(FiscalYear aFiscalYear) {
		return dbListStrategicMilestone(aFiscalYear.getNodeIdString(), null);
	}

	@Override
	public ArrayList<StrategicMilestone> dbListStrategicMilestone(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestoneException) {
		return dbListStrategicMilestone(aFiscalYear.getNodeIdString(), aStrategicMilestoneException == null ? null : aStrategicMilestoneException.getNodeIdString());
	}
	
	@Override
	public ArrayList<StrategicMilestone> dbListStrategicMilestone(String aFiscalYearId) {
		return dbListStrategicMilestone(aFiscalYearId, null);
	}

	@SuppressWarnings("resource")
	@Override
	public ArrayList<StrategicMilestone> dbListStrategicMilestone(String aFiscalYearId, String aStrategicMilestoneExceptionId) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.STRATEGIC_MILESTONE.getTableName() +
			" WHERE " + StrategicMilestoneMetaData.column_FISCAL_YEAR_ID + " = '" + aFiscalYearId + "'";
			if(aStrategicMilestoneExceptionId != null) {
				theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aStrategicMilestoneExceptionId + "'";
			}
		theRawQuery += " ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
		Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
		return StrategicMilestoneDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@Override
	public int dbCountStrategicMilestoneForProjectAssetMoveTarget(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestoneException) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.STRATEGIC_MILESTONE.getTableName() +
			" WHERE " + StrategicMilestoneMetaData.column_FISCAL_YEAR_ID + " = '" + aFiscalYear.getNodeIdString() + "'";
			if(aStrategicMilestoneException != null) {
				theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aStrategicMilestoneException.getNodeIdString() + "'";
			}
		theRawQuery += " ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
		return countRows(theRawQuery);
	}

	@SuppressWarnings("resource")
	@Override
	public ArrayList<StrategicMilestone> dbListStrategicMilestoneForProjectAssetMoveTarget(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestoneException) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.STRATEGIC_MILESTONE.getTableName() +
			" WHERE " + StrategicMilestoneMetaData.column_FISCAL_YEAR_ID + " = '" + aFiscalYear.getNodeIdString() + "'";
			if(aStrategicMilestoneException != null) {
				theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aStrategicMilestoneException.getNodeIdString() + "'";
			}
		theRawQuery += " ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
		Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
		return StrategicMilestoneDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@Override
	public int dbCountStrategicMilestoneForWorkPackageMoveTarget(FiscalYear aFiscalYear, ProjectAsset aProjectAssetException) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.STRATEGIC_MILESTONE.getTableName() +
			" WHERE " + StrategicMilestoneMetaData.column_FISCAL_YEAR_ID + " = '" + aFiscalYear.getNodeIdString() + "'" +
			" AND " + IdNodeMetaData.column_ID + 
			" IN (" +
				" SELECT " + StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID + " FROM " + FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName();
				if(aProjectAssetException != null) {
					theRawQuery += " WHERE " + StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID + " != '" + aProjectAssetException.getNodeIdString() + "'";
				}
			theRawQuery += ") " +
				" ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
			return countRows(theRawQuery);
	}

	@SuppressWarnings("resource")
	@Override
	public ArrayList<StrategicMilestone> dbListStrategicMilestoneForWorkPackageMoveTarget(FiscalYear aFiscalYear, ProjectAsset aProjectAssetException) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.STRATEGIC_MILESTONE.getTableName() +
				" WHERE " + StrategicMilestoneMetaData.column_FISCAL_YEAR_ID + " = '" + aFiscalYear.getNodeIdString() + "'" +
				" AND " + IdNodeMetaData.column_ID + 
				" IN (" +
					" SELECT " + StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID + " FROM " + FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName();
					if(aProjectAssetException != null) {
						theRawQuery += " WHERE " + StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID + " != '" + aProjectAssetException.getNodeIdString() + "'";
					}
		theRawQuery += ") " +
			" ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
		Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
		return StrategicMilestoneDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@Override
	public StrategicMilestone dbRetrieveStrategicMilestone(String aNodeIdString) {
		return (StrategicMilestone) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.STRATEGIC_MILESTONE);
	}
	
	@Override
	public boolean dbInsertStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(aStrategicMilestone, bAtomicTransaction);
	}
	
	@Override
	public boolean dbUpdateStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(aStrategicMilestone, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdateTargetDate(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
		ContentValues theContentValues = new ContentValues();
		theContentValues.put(StrategicMilestoneMetaData.column_TARGET_MONTH_END, aStrategicMilestone.getTargetMonthEnd());
		theContentValues.put(StrategicMilestoneMetaData.column_TARGET_DATE, aStrategicMilestone.getTargetDateFormattedUtcLong());
		theContentValues.put(StrategicMilestoneMetaData.column_TARGET_IS_REVERSE_PLANNING, aStrategicMilestone.targetIsReversePlanningAsInt());
		boolean theBoolean = getSqLiteDatabase().update(
				FmmNodeDefinition.STRATEGIC_MILESTONE.getTableName(),
				theContentValues,
    			IdNodeMetaData.column_ID + " = '" + aStrategicMilestone.getNodeIdString() + "'",
    			null ) > 0;
		return theBoolean;
	}

	@Override
	public boolean dbDeleteStrategicMilestone(StrategicMilestone aStrategicMilestone, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aStrategicMilestone.getNodeIdString(), FmmNodeDefinition.STRATEGIC_MILESTONE, bAtomicTransaction);
	}

	@Override
	public boolean dbExistsStrategicMilestone(String aNodeIdString) {
		return dbRetrieveStrategicMilestone(aNodeIdString) != null;
	}

	@Override
	public int dbMoveAllStrategicMilestonesIntoFiscalYear(
            String aCurrentFiscalYearId,
            String aDestinationFiscalYearId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		this.contentValues.clear();
		this.contentValues.put(StrategicMilestoneMetaData.column_FISCAL_YEAR_ID, aDestinationFiscalYearId);
		this.contentValues.put(CompletableNodeMetaData.column_SEQUENCE, updateSequenceBeforeAddingNewPeer(
				FmmNodeDefinition.STRATEGIC_MILESTONE,
				StrategicMilestoneMetaData.column_FISCAL_YEAR_ID,
				aDestinationFiscalYearId,
				bSequenceAtEnd ));
		int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.STRATEGIC_MILESTONE.getTableName(), this.contentValues,
				StrategicMilestoneMetaData.column_FISCAL_YEAR_ID + " = '" + aCurrentFiscalYearId + "'", null);
		if(bAtomicTransaction) {
			endTransaction(theRowCount > 0);
		}
		return theRowCount;
	}

	@Override
	public boolean dbMoveSingleStrategicMilestoneIntoFiscalYear(
            String aStrategicMilestoneId,
            String anOriginalFiscalYearId,
            String aDestinationFiscalYearId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		this.contentValues.clear();
		this.contentValues.put(StrategicMilestoneMetaData.column_FISCAL_YEAR_ID, aDestinationFiscalYearId);
		this.contentValues.put(CompletableNodeMetaData.column_SEQUENCE, updateSequenceBeforeAddingNewPeer(
				FmmNodeDefinition.STRATEGIC_MILESTONE,
				StrategicMilestoneMetaData.column_FISCAL_YEAR_ID,
				aDestinationFiscalYearId,
				bSequenceAtEnd ));
		int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.STRATEGIC_MILESTONE.getTableName(), this.contentValues,
				IdNodeMetaData.column_ID + " = '" + aStrategicMilestoneId + "'", null);
		reSequenceRows(FmmNodeDefinition.STRATEGIC_MILESTONE.getTableName(), StrategicMilestoneMetaData.column_FISCAL_YEAR_ID, anOriginalFiscalYearId);
		if(bAtomicTransaction) {
			endTransaction(theRowCount > 0);
		}
		return theRowCount > 0;
	}

	private int countRows(String aRawQuery) {
		Cursor theCursor = getSqLiteDatabase().rawQuery(aRawQuery, null);
		int theCount = theCursor.getCount();
		theCursor.close();
		return theCount;
	}

	private int countRows(String aTableName, String aColumnName, String aColumnValue) {
	    String countQuery = "SELECT * FROM " + aTableName + " WHERE " + aColumnName + " = '" + aColumnValue + "'";
	    return countRows(countQuery);
	}

	private int rowCountWhere(String aTableName, String aWhereClause) {
	    String countQuery = "SELECT * FROM " + aTableName + " WHERE " + aWhereClause;
	    return countRows(countQuery);
	}

	private int updateSequenceBeforeAddingNewPeer(
			FmmNodeDefinition anFmmNodeDefinition,
			String aParentIdColumnName,
			String aParentId,
			boolean bSequenceAtEnd) {
		int theSequence = 1;
		if(bSequenceAtEnd) {
			theSequence = countRows(anFmmNodeDefinition.getTableName(), aParentIdColumnName, aParentId) + 1;
		} else {
			dbIncrementSequence(anFmmNodeDefinition.getTableName(), aParentIdColumnName, aParentId);
		}
		return theSequence;
	}
	
	///////  SEQUENCE  ////////////////////

    @Override
    public void dbIncrementSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue ) {
        dbIncrementSequence(aTableName, aWhereColumnName, aWhereColumnValue, CompletableNodeMetaData.column_SEQUENCE);
    }
	
	@Override
	public void dbIncrementSequence(
			String aTableName,
			String aWhereColumnName,
			String aWhereColumnValue,
            String aSequenceColumnName ) {
		getSqLiteDatabase().execSQL(
				"UPDATE " + aTableName +
				" SET "+ aSequenceColumnName + " = " + aSequenceColumnName + " + 1 " +
				" WHERE " + aWhereColumnName + " = '" + aWhereColumnValue + "'" );
	}
	
	@Override
	public void dbIncrementSequence(
			String aTableName,
			String aWhereColumnName,
			String aWhereColumnValue,
			int aFirstSequenceToIncrement ) {
        dbIncrementSequence(aTableName, aWhereColumnName, aWhereColumnValue, aFirstSequenceToIncrement, CompletableNodeMetaData.column_SEQUENCE);
	}

    @Override
    public void dbIncrementSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue,
            int aFirstSequenceToIncrement,
            String aSequenceColumnName ) {
        getSqLiteDatabase().execSQL(
                "UPDATE " + aTableName +
                        " SET "+ aSequenceColumnName + " = " + aSequenceColumnName + " + 1 " +
                        " WHERE " + aWhereColumnName + " = '" + aWhereColumnValue + "'" +
                        " AND " + aSequenceColumnName + " >= " + aFirstSequenceToIncrement);
    }
	
	private void decrementSequence(
			String aTableName,
			String aWhereColumnName,
			String aWhereColumnValue ) {
		getSqLiteDatabase().execSQL(
				"UPDATE " + aTableName +
				" SET "+ SequencedLinkNodeMetaData.column_SEQUENCE + " = " + SequencedLinkNodeMetaData.column_SEQUENCE + " - 1 " +
				" WHERE " + aWhereColumnName + " = '" + aWhereColumnValue + "'" );
	}
	
	public void reSequenceRows(
			String aTableName,
			String aWhereColumnName,
			String aWhereColumnValue ) {
		int theMissingSequence = getFirstMissingSequence(aTableName, aWhereColumnName, aWhereColumnValue);
		dbResequenceOnRemove(aTableName, aWhereColumnName, aWhereColumnValue, theMissingSequence);
	}
	
	@Override
	public void dbResequenceOnRemove(
			String aTableName,
			String aWhereColumnName,
			String aWhereColumnValue,
			int aMissingSequence) {
		if(aMissingSequence == 0) {
			return;
		}
		getSqLiteDatabase().execSQL(
				"UPDATE " + aTableName +
				" SET "+ SequencedLinkNodeMetaData.column_SEQUENCE + " = " + SequencedLinkNodeMetaData.column_SEQUENCE + " - 1 " +
				" WHERE " + aWhereColumnName + " = '" + aWhereColumnValue + "' " +
				" AND " + SequencedLinkNodeMetaData.column_SEQUENCE + " > " + aMissingSequence );
	}

    @Override
    public int dbGetLastSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue ) {
        return dbGetLastSequence(
                aTableName,
                aWhereColumnName,
                aWhereColumnValue,
                SequencedLinkNodeMetaData.column_SEQUENCE );
    }
	
	@Override
	public int dbGetLastSequence(
			String aTableName,
			String aWhereColumnName,
			String aWhereColumnValue,
            String aSequenceColumnName ) {
		String theQuery =
				"SELECT MAX(" + aSequenceColumnName + ") AS last_sequence_number" +
						" FROM " + aTableName +
						" WHERE " + aWhereColumnName + " = '" + aWhereColumnValue + "'";
		Cursor theCursor = getSqLiteDatabase().rawQuery(theQuery, null);
		if(theCursor.getCount() == 0) {
			theCursor.close();
			return 0;
		}
		theCursor.moveToFirst();
		int theLastSequence = theCursor.getInt(0);
		theCursor.close();
		return theLastSequence;
	}
	
	private int getFirstMissingSequence(
	String aTableName,
	String aColumnName,
	String aColumnValue ) {
	    String theQuery =
	    		"SELECT DISTINCT " + SequencedLinkNodeMetaData.column_SEQUENCE + " + 1 " +
	    		" FROM " + aTableName +
	    		" WHERE " + aColumnName + " = '" + aColumnValue + "' " +
	    		" AND " + SequencedLinkNodeMetaData.column_SEQUENCE + " + 1 " +
	    			" NOT IN (SELECT DISTINCT " + SequencedLinkNodeMetaData.column_SEQUENCE + " FROM " + aTableName + ")" +
	    		" ORDER BY " + SequencedLinkNodeMetaData.column_SEQUENCE;
	    Cursor theCursor = getSqLiteDatabase().rawQuery(theQuery, null);
	    if(theCursor.getCount() == 0) {
	    	theCursor.close();
	    	return 0;
	    }
	    theCursor.moveToFirst();
	    int theMissingSequence = theCursor.getInt(0);
	    theCursor.close();
	    return theMissingSequence;
	}

	////  Node - WORK PACKAGE  ////////////////////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<WorkPackage> dbRetrieveWorkPackageList() {
		return retrieveAllFmmNodesFromTable(WorkPackageDaoSqLite.getInstance());
	}

	@SuppressWarnings("resource")
	@Override
	public ArrayList<WorkPackage> dbListWorkPackagesForProjectAsset(String aProjectAssetId) {
		Cursor theCursor = retrieveAllRowsFromTableForColumnValueSorted(
				FmmNodeDefinition.WORK_PACKAGE, WorkPackageMetaData.column_WORK_ASSET_ID, aProjectAssetId, CompletableNodeMetaData.column_SEQUENCE );
		return WorkPackageDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@Override
	public ArrayList<WorkPackage> dbListWorkPackageForCadence(String aCadenceId) {
//				FmmNodeDefinition.CADENCE_WORK_PACKAGE_COMMITMENT
		return null;
	}

	@SuppressWarnings("resource")
	@Override
	public ArrayList<WorkPackage> dbListWorkPackagesForWorkTaskMoveTarget(String aParentNodeId, String aWorkPackageExceptionId, boolean bPrimaryParent) {
		Cursor theCursor;
		if(bPrimaryParent) {
			String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.WORK_PACKAGE.getTableName() +
					" WHERE " + WorkPackageMetaData.column_WORK_ASSET_ID + " = '" + aParentNodeId + "'";
					if(aWorkPackageExceptionId != null) {
						theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aWorkPackageExceptionId + "'";
					}
				theRawQuery += " ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
				theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
		} else {
			String theAndClause = FmmNodeDefinition.CADENCE_WORK_PACKAGE_COMMITMENT.getTableName() + "." + CadenceWorkPackageCommitmentMetaData.column_FLYWHEEL_CADENCE_ID + " = '" + aParentNodeId + "'";
			if(aWorkPackageExceptionId != null) {
				theAndClause += " AND " + FmmNodeDefinition.CADENCE_WORK_PACKAGE_COMMITMENT.getTableName() + "." + CadenceWorkPackageCommitmentMetaData.column_WORK_PACKAGE_ID + " != '" + aWorkPackageExceptionId + "'";
			}
			theCursor = getSqLiteDatabase().rawQuery(getInnerJoinQueryWithAndSpecSorted(
					FmmNodeDefinition.WORK_PACKAGE.getTableName(),
					IdNodeMetaData.column_ID,
					FmmNodeDefinition.CADENCE_WORK_PACKAGE_COMMITMENT.getTableName(),
					CadenceWorkPackageCommitmentMetaData.column_WORK_PACKAGE_ID,
					theAndClause,
					FmmNodeDefinition.CADENCE_WORK_PACKAGE_COMMITMENT.getTableName() + "." + SequencedLinkNodeMetaData.column_SEQUENCE), null);
		}
		return WorkPackageDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@Override
	public WorkPackage dbRetrieveWorkPackage(String aNodeIdString) {
		return (WorkPackage) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.WORK_PACKAGE);
	}
	
	@Override
	public boolean dbInsertWorkPackage(WorkPackage aWorkPackage, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(aWorkPackage, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdateWorkPackage(WorkPackage aWorkPackage, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(aWorkPackage, bAtomicTransaction);
	}

	@Override
	public boolean dbOrphanAllWorkPackagesFromProjectAsset(String aProjectAssetNodeId, boolean bAtomicTransaction) {
        return orphanSequenceRows(
                FmmNodeDefinition.WORK_PACKAGE.getTableName(),
                WorkPackageMetaData.column_WORK_ASSET_ID,
                WorkPackageMetaData.column_WORK_ASSET_ID + " = '" + aProjectAssetNodeId + "'",
                bAtomicTransaction);
	}

    @Override
    public boolean dbOrphanAllWorkPackagesFromCadence(String aCadenceId, boolean bAtomicTransaction) {
        return false;
    }

    public boolean dbOrphanSingleWorkPackageFromProjectAsset(String aWorkPackageId, String aProjectAssetId, boolean bAtomicTransaction) {
        boolean bSuccess = orphanSequenceRows(
                FmmNodeDefinition.WORK_PACKAGE.getTableName(),
                WorkPackageMetaData.column_WORK_ASSET_ID,
                IdNodeMetaData.column_ID + " = '" + aWorkPackageId + "'",
                bAtomicTransaction);
        reSequenceRows(
                FmmNodeDefinition.WORK_PACKAGE.getTableName(),
                WorkPackageMetaData.column_WORK_ASSET_ID,
                aProjectAssetId );
        return bSuccess;
    }

    public boolean dbOrphanSingleWorkPackageFromCadence(String aWorkPackageId, String aCadenceId, boolean bAtomicTransaction) {
        return false;
    }

	@SuppressWarnings("resource")
	@Override
	public ArrayList<WorkPackage> dbListWorkPackageOrphansFromProjectAsset() {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.WORK_PACKAGE.getTableName() +
				" WHERE " + WorkPackageMetaData.column_WORK_ASSET_ID + " IS NULL";
			theRawQuery += " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
			Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
			return WorkPackageDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@Override
	public ArrayList<WorkPackage> dbListWorkPackageOrphansFromCadence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean dbAdoptOrphanWorkPackageIntoProjectAsset(
			String aWorkPackageId,
			String aProjectAssetId,
			boolean bSequenceAtEnd,
			boolean bAtomicTransaction ) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		this.contentValues.clear();
		this.contentValues.put(WorkPackageMetaData.column_WORK_ASSET_ID, aProjectAssetId);
		this.contentValues.put(CompletableNodeMetaData.column_SEQUENCE, updateSequenceBeforeAddingNewPeer(
				FmmNodeDefinition.WORK_PACKAGE,
				WorkPackageMetaData.column_WORK_ASSET_ID,
				aProjectAssetId,
				bSequenceAtEnd ));
		int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.WORK_PACKAGE.getTableName(), this.contentValues,
				IdNodeMetaData.column_ID + " = '" + aWorkPackageId + "'", null);
		if(bAtomicTransaction) {
			endTransaction(theRowCount > 0);
		}
		return theRowCount > 0;
	}

	@Override
	public boolean dbMoveAllWorkPackagesIntoProjectAsset(
            String aSourceProjectAssetId,
            String aDestinationProjectAssetId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
		if(bAtomicTransaction) {
			startTransaction();
		}
		this.contentValues.clear();
		this.contentValues.put(WorkPackageMetaData.column_WORK_ASSET_ID, aDestinationProjectAssetId);
		this.contentValues.put(CompletableNodeMetaData.column_SEQUENCE, updateSequenceBeforeAddingNewPeer(
				FmmNodeDefinition.WORK_PACKAGE,
				WorkPackageMetaData.column_WORK_ASSET_ID,
				aDestinationProjectAssetId,
				bSequenceAtEnd ));
		int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.WORK_PACKAGE.getTableName(), this.contentValues,
				WorkPackageMetaData.column_WORK_ASSET_ID + " = '" + aSourceProjectAssetId + "'", null);
		if(bAtomicTransaction) {
			endTransaction(theRowCount > 0);
		}
		return theRowCount > 0;
	}

    public boolean dbMoveSingleWorkPackageIntoProjectAsset(
            String aWorkPackageId,
            String anOriginalProjectAssetId,
            String aDestinationProjectAssetId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction ) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        this.contentValues.clear();
        this.contentValues.put(WorkPackageMetaData.column_WORK_ASSET_ID, aDestinationProjectAssetId);
        this.contentValues.put(CompletableNodeMetaData.column_SEQUENCE, updateSequenceBeforeAddingNewPeer(
                FmmNodeDefinition.WORK_PACKAGE,
                WorkPackageMetaData.column_WORK_ASSET_ID,
                aDestinationProjectAssetId,
                bSequenceAtEnd ));
        int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.WORK_PACKAGE.getTableName(), this.contentValues,
                IdNodeMetaData.column_ID + " = '" + aWorkPackageId + "'", null);
        if(bAtomicTransaction) {
            endTransaction(theRowCount > 0);
        }
        return theRowCount > 0;
        
    }

	@Override
	public boolean dbDeleteWorkPackage(WorkPackage aWorkPackage, boolean bAtomicTransaction) {
    	boolean theBoolean = getSqLiteDatabase().delete(FmmNodeDefinition.WORK_PACKAGE.getTableName(), IdNodeMetaData.column_ID + " = '" + aWorkPackage.getNodeIdString()  + "'", null) > 0;
    	return theBoolean;
	}

	////  Node - NODE FRAG FSE DOCUMENT  ////////////////////////////////////////////////////////////////////////////////


	@Override
	public ArrayList<NodeFragFseDocument> dbRetrieveNodeFragFseDocumentList() {
		return retrieveAllFmmNodesFromTable(NodeFragFseDocumentDaoSqLite.getInstance());
	}

	@Override
	public NodeFragFseDocument dbRetrieveNodeFragFseDocument(String aNodeIdString) {
		return (NodeFragFseDocument) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT);
	}

	@SuppressWarnings("resource")
	@Override
	public NodeFragFseDocument dbRetrieveNodeFragFseDocumentForDocumentId(String aDocumentId) {
		Cursor theCursor = getSqLiteDatabase().rawQuery("SELECT * FROM " + FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT.getTableName() +
			" WHERE " + NodeFragFseDocumentMetaData.column_DOCUMENT_ID + " = '" + aDocumentId + "'", null);
		return NodeFragFseDocumentDaoSqLite.getInstance().getSingleObjectFromCursor(theCursor);
	}

	@Override
	public NodeFragFseDocument dbRetrieveNodeFragFseDocumentForParent(String aParentId) {
		return (NodeFragFseDocument) retrieveFmmNodeFromTableForParent(aParentId, NodeFragFseDocumentDaoSqLite.getInstance());
	}
	
	@Override
	public boolean dbInsertNodeFragFseDocument(NodeFragFseDocument aNodeFragFseDocument, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(aNodeFragFseDocument, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdateNodeFragFseDocument(NodeFragFseDocument aNodeFragFseDocument, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(aNodeFragFseDocument, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteNodeFragFseDocument(String aNodeIdString, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteNodeFragFseDocumentForParent(String aParentId, boolean bAtomicTransaction) {
    	return deleteRowFromTableForParent(aParentId, FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteNodeFragFseDocument(NodeFragFseDocument aNodeFragFseDocument, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aNodeFragFseDocument.getNodeIdString(), FmmNodeDefinition.NODE_FRAG__FSE_DOCUMENT, bAtomicTransaction);
	}

	@Override
	public boolean dbExistsNodeFragFseDocument(String aDocumentId) {
		return dbRetrieveNodeFragFseDocumentForDocumentId(aDocumentId) != null;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - PDF PUBLICATION  /////////////////////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<PdfPublication> dbRetrievePdfPublicationList() {
		return retrieveAllFmmNodesFromTable(PdfPublicationDaoSqLite.getInstance());
	}

	@SuppressWarnings("resource")
	@Override
	public ArrayList<PdfPublication> dbRetrievePdfPublicationLisForTargetNode(String aTargetId) {
		Cursor theCursor = getSqLiteDatabase().rawQuery("SELECT * FROM " + FmmNodeDefinition.PDF_PUBLICATION.getTableName() +
				"WHERE " + PdfPublicationMetaData.column_TARGET_NODE_ID + " = '" + aTargetId + "';", null);
		return PdfPublicationDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@SuppressWarnings("resource")
	@Override
	public ArrayList<PdfPublication> dbRetrievePdfPublicationLisForTargetNodeAndCommunityMember(String aTargetId, String aCommunityMemberId) {
		Cursor theCursor = getSqLiteDatabase().rawQuery("SELECT * FROM " + FmmNodeDefinition.PDF_PUBLICATION.getTableName() +
				"WHERE " + PdfPublicationMetaData.column_TARGET_NODE_ID + " = '" + aTargetId +
				"' AND " + PdfPublicationMetaData.column_COMMUNITY_MEMBER_ID + " = '" + aCommunityMemberId + "';", null);
		return PdfPublicationDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@Override
	public PdfPublication dbRetrievePdfPublication(String aNodeIdString) {
		return (PdfPublication) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.PDF_PUBLICATION);
	}

	@Override
	public boolean dbInsertPdfPublication(PdfPublication aPdfPublication, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(aPdfPublication, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdatePdfPublication(PdfPublication aPdfPublication, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(aPdfPublication, bAtomicTransaction);
	}

	@Override
	public boolean dbDeletePdfPublication(PdfPublication aPdfPublication, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aPdfPublication.getNodeIdString(), FmmNodeDefinition.PDF_PUBLICATION, bAtomicTransaction);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - ORGANIZATION COMMUNITY MEMBER  ///////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<OrganizationCommunityMember> dbRetrieveOrganizationCommunityMemberList() {
		return retrieveAllFmmNodesFromTable(OrganizationCommunityMemberDaoSqLite.getInstance());
	}

	@Override
	public OrganizationCommunityMember dbRetrieveOrganizationCommunityMember(String aNodeIdString) {
		return (OrganizationCommunityMember) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER);
	}

	@Override
	public boolean dbInsertOrganizationCommunityMember(OrganizationCommunityMember anOrganizationCommunityMember, boolean bAtomicTransaction) {
		this.contentValues = OrganizationCommunityMemberDaoSqLite.getInstance().buildContentValues(anOrganizationCommunityMember);
    	boolean theBoolean = getSqLiteDatabase().insert(FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER.getTableName(), null, this.contentValues) > 0;
    	return theBoolean;
	}

	@Override
	public boolean dbUpdateOrganizationCommunityMember(OrganizationCommunityMember anOrganizationCommunityMember, boolean bAtomicTransaction) {
    	boolean theBoolean = false; // TODO
    	return theBoolean;
	}

	@Override
	public boolean dbDeleteOrganizationCommunityMember(OrganizationCommunityMember anOrganizationCommunityMember, boolean bAtomicTransaction) {
    	boolean theBoolean = getSqLiteDatabase().delete(FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER.getTableName(),
    			OrganizationCommunityMemberMetaData.column_ORGANIZATION_ID + " = '" + anOrganizationCommunityMember.getOrganizationNodeIdString()  + "'" +
    					OrganizationCommunityMemberMetaData.column_COMMUNITY_MEMBER_ID + " = '" + anOrganizationCommunityMember.getCommunityMemberNodeIdString()  + "';",
    					null ) > 0;
    	return theBoolean;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FRAG LOCK  ///////////////////////////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<FragLock> dbRetrieveFragLockList() {
		return retrieveAllFmmNodesFromTable(FragLockDaoSqLite.getInstance());
	}

	@Override
	public FragLock dbRetrieveFragLock(String aNodeIdString) {
		return (FragLock) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.FRAG_LOCK);
	}

	@Override
	public FragLock dbRetrieveFragLockForParent(String aParentId) {
		return (FragLock) retrieveFmmNodeFromTableForParent(aParentId, FragLockDaoSqLite.getInstance());
	}

	@Override
	public FragLock dbRetrieveFragLockForGrandparent(String aGrandparentId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean dbInsertFragLock(FragLock aFragLock, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(aFragLock, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdateFragLock(FragLock aFragLock, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(aFragLock, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteFragLock(FragLock aFragLock, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aFragLock.getNodeIdString(), FmmNodeDefinition.FRAG_LOCK, bAtomicTransaction);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG - GENERIC  //////////////////////////////////////////////////////////////////////////

    public FmmNodeFrag dbRetrieveFmmNodeFragForParent(String aParentId, FmmNodeDefinition anFmmNodeDefinition) {
        return (FmmNodeFrag) retrieveFmmNodeFromSimpleIdTable(anFmmNodeDefinition.getPrimaryParentIdColumnName(), aParentId, anFmmNodeDefinition);
    }


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG AUDIT BLOCK  ///////////////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<NodeFragAuditBlock> dbRetrieveNodeFragAuditBlockList() {
		return retrieveAllFmmNodesFromTable(NodeFragAuditBlockDaoSqLite.getInstance());
	}

	@Override
	public NodeFragAuditBlock dbRetrieveNodeFragAuditBlock(String aNodeIdString) {
		return (NodeFragAuditBlock) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.NODE_FRAG__AUDIT_BLOCK);
	}

	@Override
	public NodeFragAuditBlock dbRetrieveNodeFragAuditBlockForParent(String aParentId) {
		return (NodeFragAuditBlock) retrieveFmmNodeFromTableForParent(aParentId, NodeFragAuditBlockDaoSqLite.getInstance());
	}

	@Override
	public boolean dbInsertNodeFragAuditBlock(NodeFragAuditBlock aNodeFragAuditBlock, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(aNodeFragAuditBlock, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdateNodeFragAuditBlock(NodeFragAuditBlock aNodeFragAuditBlock, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(aNodeFragAuditBlock, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteNodeFragAuditBlock(NodeFragAuditBlock aNodeFragAuditBlock, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aNodeFragAuditBlock.getNodeIdString(), FmmNodeDefinition.NODE_FRAG__AUDIT_BLOCK, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteNodeFragAuditBlockForParent(String aParentId, boolean bAtomicTransaction) {
    	return deleteRowFromTableForParent(aParentId, FmmNodeDefinition.NODE_FRAG__AUDIT_BLOCK, bAtomicTransaction);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG COMPLETION  ////////////////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<NodeFragCompletion> dbRetrieveNodeFragCompletionList() {
		return retrieveAllFmmNodesFromTable(NodeFragCompletionDaoSqLite.getInstance());
	}

	@Override
	public NodeFragCompletion dbRetrieveNodeFragCompletion(String aNodeIdString) {
		return (NodeFragCompletion) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.NODE_FRAG__COMPLETION);
	}

	@Override
	public NodeFragCompletion dbRetrieveNodeFragCompletionForParent(String aParentId) {
		return (NodeFragCompletion) retrieveFmmNodeFromTableForParent(aParentId, NodeFragCompletionDaoSqLite.getInstance());
	}
	
	@Override
	public boolean dbInsertNodeFragCompletion(NodeFragCompletion aNodeFragCompletion, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(aNodeFragCompletion, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdateNodeFragCompletion(NodeFragCompletion aNodeFragCompletion, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(aNodeFragCompletion, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteNodeFragCompletion(NodeFragCompletion aNodeFragCompletion, boolean bAtomicTransaction) {
    	return deleteRowFromTableForParent(aNodeFragCompletion.getNodeIdString(), FmmNodeDefinition.NODE_FRAG__COMPLETION, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteNodeFragCompletionForParent(String aParentId, boolean bAtomicTransaction) {
    	return deleteRowFromTableForParent(aParentId, FmmNodeDefinition.NODE_FRAG__COMPLETION, bAtomicTransaction);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - NODE FRAG TRIBKN QUALITY  ////////////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<NodeFragTribKnQuality> dbRetrieveNodeFragTribKnQualityList() {
		return retrieveAllFmmNodesFromTable(NodeFragTribKnQualityDaoSqLite.getInstance());
	}

	@Override
	public NodeFragTribKnQuality dbRetrieveNodeFragTribKnQuality(
			String aNodeIdString) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NodeFragTribKnQuality dbRetrieveNodeFragTribKnQualityForParent(String aParentId) {
		return (NodeFragTribKnQuality) retrieveFmmNodeFromTableForParent(aParentId, NodeFragTribKnQualityDaoSqLite.getInstance());
	}
	
	@Override
	public boolean dbInsertNodeFragTribKnQuality(NodeFragTribKnQuality aNodeFragTribKnQuality, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(aNodeFragTribKnQuality, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdateNodeFragTribKnQuality(NodeFragTribKnQuality aNodeFragTribKnQuality, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(aNodeFragTribKnQuality, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteNodeFragTribKnQuality(NodeFragTribKnQuality aNodeFragTribKnQuality, boolean bAtomicTransaction) {
    	return deleteRowFromTableForParent(aNodeFragTribKnQuality.getNodeIdString(), FmmNodeDefinition.NODE_FRAG__TRIBKN_QUALITY, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteNodeFragTribKnQualityForParent(String aParentId, boolean bAtomicTransaction) {
    	return deleteRowFromTableForParent(aParentId, FmmNodeDefinition.NODE_FRAG__TRIBKN_QUALITY, bAtomicTransaction);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	////  FMMM CONFIGURATION  ///////////////////////////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<FmmConfiguration> dbRetrieveFmmConfigurationList() {
		return retrieveAllFmmNodesFromTable(FmmConfigurationDaoSqLite.getInstance());
	}

	@Override
	public FmmConfiguration dbRetrieveFmmConfiguration(String aNodeIdString) {
		return (FmmConfiguration) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.FMM_CONFIGURATION);
	}

	@Override
	public boolean dbInsertFmmConfiguration(FmmConfiguration anFmmConfiguration, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(anFmmConfiguration, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdateFmmConfiguration(FmmConfiguration anFmmConfiguration, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(anFmmConfiguration, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteFmmConfiguration(FmmConfiguration anFmmConfiguration, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(anFmmConfiguration.getNodeIdString(), FmmNodeDefinition.FMM_CONFIGURATION, bAtomicTransaction);
	}

	@Override
	public FmmConfiguration dbGetConfigurationForFmm() {
		Cursor theCursor = getSqLiteDatabase().rawQuery("SELECT * FROM " + FmmNodeDefinition.FMM_CONFIGURATION.getTableName() +
				" WHERE " + FmmConfigurationMetaData.column_FOR_THIS_FMM + " = 1;", null);
		FmmConfiguration theFmmConfiguration = FmmConfigurationDaoSqLite.getInstance().getNextObjectFromCursor(theCursor);
		theCursor.close();
		return theFmmConfiguration;
	}

	// used when copying an FMM
	@Override
	public void dbSetConfigurationForFmm(FmmConfiguration anFmmConfiguration) {
		FmmConfiguration theFmmConfiguration = dbGetConfigurationForFmm();
		if(anFmmConfiguration.getNodeIdString().equals(theFmmConfiguration.getNodeIdString())) {
			return;
		}
		startTransaction();
		// reset data
		getSqLiteDatabase().execSQL("UPDATE " + FmmNodeDefinition.FMM_CONFIGURATION.getTableName() +
			" SET " + FmmConfigurationMetaData.column_FOR_THIS_FMM + " = 0" +
			" WHERE " + FmmConfigurationMetaData.column_FOR_THIS_FMM + " = 1;");
		// set new configuration
		getSqLiteDatabase().execSQL("UPDATE " + FmmNodeDefinition.FMS_ORGANIZATION.getTableName() +
				" SET " + FmmConfigurationMetaData.column_FOR_THIS_FMM + " = 1" +
				" WHERE " + IdNodeMetaData.column_ID + " = " + anFmmConfiguration.getNodeIdString() + ";");
		endTransaction(true);
	}

	@SuppressWarnings("resource")
	public static FmsOrganization getFirstOrganizationRow(SQLiteDatabase aDatabase) {
		Cursor theCursor = aDatabase.rawQuery("SELECT * FROM " + FmmNodeDefinition.FMS_ORGANIZATION.getTableName(), null);
		return FmsOrganizationDaoSqLite.getInstance().getSingleObjectFromCursor(theCursor);
	}

	@Override
	public <T extends FmmConfiguration> void synchronizeFmmConfigurationRowWithConfigFile(T fmmConfiguration) {
		// TODO Auto-generated method stub
		
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - WORK TASK  ///////////////////////////////////////////////////////////////////////////////////

    public ArrayList<WorkTask> dbListWorkTasksForWorkPackage(String aWorkPackageId, String aWorkTaskExceptionId) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.WORK_TASK.getTableName() +
                " WHERE " + WorkTaskMetaData.column_WORK_PACKAGE__ID + " = '" + aWorkPackageId + "'";
        if(aWorkTaskExceptionId != null) {
            theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aWorkTaskExceptionId + "'";
        }
        theRawQuery += " ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return WorkTaskDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    public ArrayList<WorkTask> dbListWorkTasksForWorkPlan(String aWorkPlanId, String aWorkTaskExceptionId) {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.WORK_TASK.getTableName() +
                " WHERE " + WorkTaskMetaData.column_WORK_PLAN__ID + " = '" + aWorkPlanId + "'";
        if(aWorkTaskExceptionId != null) {
            theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aWorkTaskExceptionId + "'";
        }
        theRawQuery += " ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return WorkTaskDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

	@Override
	public WorkTask dbRetrieveWorkTask(String aNodeIdString) {
		return (WorkTask) retrieveFmmNodeFromSimpleIdTable(aNodeIdString, FmmNodeDefinition.WORK_TASK);
	}

	@Override
	public boolean dbInsertWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction) {
    	return insertSimpleIdTable(aWorkTask, bAtomicTransaction);
	}

	@Override
	public boolean dbDeleteWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction) {
    	return deleteRowFromSimpleIdTable(aWorkTask.getNodeIdString(), FmmNodeDefinition.WORK_TASK, bAtomicTransaction);
	}

	@Override
	public boolean dbUpdateWorkTask(WorkTask aWorkTask, boolean bAtomicTransaction) {
    	return updateSimpleIdTable(aWorkTask, bAtomicTransaction);
	}

    // TODO - RESEQUENCE BOTH Source and Destination
    public boolean dbMoveSingleWorkTaskIntoWorkPackage(String aSourceWorkPackageId, String aDestinationWorkPackageId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
//        if(bAtomicTransaction) {
//            startTransaction();
//        }
//        this.contentValues.clear();
//        this.contentValues.put(WorkTaskMetaData.column_WORK_PACKAGE__ID, aDestinationWorkPackageId);
//        this.contentValues.put(CompletableNodeMetaData.column_SEQUENCE, updateSequenceBeforeAddingNewPeer(
//                FmmNodeDefinition.WORK_TASK,
//                WorkTaskMetaData.column_WORK_PACKAGE__ID,
//                aDestinationWorkPackageId,
//                bSequenceAtEnd ));
//        int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.WORK_TASK.getTableName(), this.contentValues,
//                IdNodeMetaData.column_ID + " = '" + aWorkTaskId + "'", null);
//        if(bAtomicTransaction) {
//            endTransaction(theRowCount > 0);
//        }
//        return theRowCount > 0;
        return false;
    }

    public boolean dbMoveAllWorkTasksIntoWorkPackage(String aSourceWorkPackageId, String aDestinationWorkPackageId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        this.contentValues.clear();
        this.contentValues.put(WorkTaskMetaData.column_WORK_PACKAGE__ID, aDestinationWorkPackageId);
        this.contentValues.put(CompletableNodeMetaData.column_SEQUENCE, updateSequenceBeforeAddingNewPeer(
                FmmNodeDefinition.WORK_TASK,
                WorkTaskMetaData.column_WORK_PACKAGE__ID,
                aDestinationWorkPackageId,
                bSequenceAtEnd ));
        int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.WORK_TASK.getTableName(), this.contentValues,
                WorkTaskMetaData.column_WORK_PACKAGE__ID + " = '" + aSourceWorkPackageId+ "'", null);
        if(bAtomicTransaction) {
            endTransaction(theRowCount > 0);
        }
        return theRowCount > 0;
    }

    public boolean dbOrphanAllWorkTasksFromWorkPackage(String aWorkPackageId, boolean bAtomicTransaction) {
        return orphanSequenceRows(
                FmmNodeDefinition.WORK_TASK.getTableName(),
                WorkTaskMetaData.column_WORK_PACKAGE__ID,
                WorkTaskMetaData.column_WORK_PACKAGE__ID + " = '" + aWorkPackageId + "'",
                bAtomicTransaction);
    }

    public boolean dbOrphanSingleWorkTaskFromWorkPackage(String aWorkTaskId, String aWorkPackageId, boolean bAtomicTransaction) {
        boolean bSuccess = orphanSequenceRows(
                FmmNodeDefinition.WORK_TASK.getTableName(),
                WorkTaskMetaData.column_WORK_PACKAGE__ID,
                IdNodeMetaData.column_ID + " = '" + aWorkTaskId + "'",
                bAtomicTransaction);
        reSequenceRows(
                FmmNodeDefinition.WORK_TASK.getTableName(),
                WorkTaskMetaData.column_WORK_PACKAGE__ID,
                aWorkPackageId );
        return bSuccess;
    }

    public ArrayList<WorkTask> dbListWorkTaskOrphansFromWorkPackage() {
        String theRawQuery =
                "SELECT * FROM " + FmmNodeDefinition.WORK_TASK.getTableName() +
                        " WHERE " + WorkTaskMetaData.column_WORK_PACKAGE__ID + " IS NULL" +
                        " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return WorkTaskDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    public ArrayList<WorkTask> dbListWorkTaskOrphansFromWorkPlan() {
        String theRawQuery =
                "SELECT * FROM " + FmmNodeDefinition.WORK_TASK.getTableName() +
                        " WHERE " + WorkTaskMetaData.column_WORK_PLAN__ID + " IS NULL" +
                        " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return WorkTaskDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    public boolean dbAdoptOrphanWorkTaskIntoWorkPackage(String aWorkTaskId, String aWorkPackageId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        if(bAtomicTransaction) {
            startTransaction();
        }
        this.contentValues.clear();
        this.contentValues.put(WorkTaskMetaData.column_WORK_PACKAGE__ID, aWorkPackageId);
        this.contentValues.put(CompletableNodeMetaData.column_SEQUENCE, updateSequenceBeforeAddingNewPeer(
                FmmNodeDefinition.WORK_TASK,
                WorkTaskMetaData.column_WORK_PACKAGE__ID,
                aWorkPackageId,
                bSequenceAtEnd ));
        int theRowCount = getSqLiteDatabase().update(FmmNodeDefinition.WORK_TASK.getTableName(), this.contentValues,
                IdNodeMetaData.column_ID + " = '" + aWorkTaskId + "'", null);
        if(bAtomicTransaction) {
            endTransaction(theRowCount > 0);
        }
        return theRowCount > 0;   
    }


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FLYWHEEL MILESTONE  ///////////////////////////////////////////////////////////////////////////////////


//    @Override
//    public ArrayList<Cadence> dbListCadenceForFiscalYear(FiscalYear aFiscalYear, Cadence aCadenceException) {
//        return dbListCadenceForFiscalYear(aFiscalYear.getNodeIdString(), aCadenceException == null ? null : aCadenceException.getNodeIdString());
//    }
//
//    @SuppressWarnings("resource")
//    @Override
//    public ArrayList<StrategicMilestone> dbListStrategicMilestone(String aFiscalYearId, String aStrategicMilestoneExceptionId) {
//        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.STRATEGIC_MILESTONE.getTableName() +
//                " WHERE " + StrategicMilestoneMetaData.column_FISCAL_YEAR_ID + " = '" + aFiscalYearId + "'";
//        if(aStrategicMilestoneExceptionId != null) {
//            theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aStrategicMilestoneExceptionId + "'";
//        }
//        theRawQuery += " ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
//        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
//        return StrategicMilestoneDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
//    }



    public ArrayList<Cadence> dbListCadence(FiscalYear aFiscalYear) {
        return dbListCadenceForFiscalYear(aFiscalYear.getNodeIdString(), null);
    }
    
    public ArrayList<Cadence> dbListCadence(String aFiscalYearId) {
        return dbListCadenceForFiscalYear(aFiscalYearId, null);
    }

    public ArrayList<Cadence> dbListCadence(FiscalYear aFiscalYear, Cadence aCadenceException) {
        return dbListCadenceForFiscalYear(aFiscalYear.getNodeIdString(), aCadenceException == null ? null : aCadenceException.getNodeIdString());
    }

    public ArrayList<Cadence> dbListCadenceForFiscalYear(String aFiscalYearId, String aCadenceExceptiionId) {
//        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.CADENCE.getTableName() +
//                " WHERE " + CadenceMetaData.column_FISCAL_YEAR_ID + " = '" + aFiscalYearId + "'";
//        if(aCadenceExceptionId != null) {
//            theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aCadenceExceptionId + "'";
//        }
//        theRawQuery += " ORDER BY " + CompletableNodeMetaData.column_SEQUENCE + " ASC";
//        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
//        return CadenceDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
        return new ArrayList<Cadence>();
    }
	
}
