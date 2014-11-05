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
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.database.sqlite.dao.BookshelfDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.BookshelfLinkToNotebookDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.CadenceCommitmentDaoSqLite;
import com.flywheelms.library.fmm.database.sqlite.dao.CadenceDaoSqLite;
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
import com.flywheelms.library.fmm.meta_data.CadenceCommitmentMetaData;
import com.flywheelms.library.fmm.meta_data.CommunityMemberOrganizationGovernanceAuthorityMetaData;
import com.flywheelms.library.fmm.meta_data.CompletableNodeMetaData;
import com.flywheelms.library.fmm.meta_data.FiscalYearMetaData;
import com.flywheelms.library.fmm.meta_data.FmmConfigurationMetaData;
import com.flywheelms.library.fmm.meta_data.HeadlineNodeMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragMetaData;
import com.flywheelms.library.fmm.meta_data.ProjectAssetMetaData;
import com.flywheelms.library.fmm.meta_data.ProjectMetaData;
import com.flywheelms.library.fmm.meta_data.SequencedLinkNodeMetaData;
import com.flywheelms.library.fmm.meta_data.StrategicAssetMetaData;
import com.flywheelms.library.fmm.meta_data.StrategicCommitmentMetaData;
import com.flywheelms.library.fmm.meta_data.StrategicMilestoneMetaData;
import com.flywheelms.library.fmm.meta_data.WorkPackageMetaData;
import com.flywheelms.library.fmm.meta_data.WorkTaskMetaData;
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
            this.daoMap.put(FmmNodeDefinition.CADENCE_COMMITMENT, CadenceCommitmentDaoSqLite.getInstance());
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
	public ArrayList<String> getFmmNodeIdList(FmmNodeDefinition anFmmNodeDefinition, String aColumnName, String aColumnValue, String aSortSpec) {
		ArrayList<String> theRowIdList = new ArrayList<String>();
        String theRawQuery =
				"SELECT " + IdNodeMetaData.column_ID + " FROM " + anFmmNodeDefinition.getTableName() +
				" WHERE " + aColumnName + " = '" + aColumnValue + "'";
        if(aSortSpec != null) {
            theRawQuery += " ORDER BY " + aSortSpec;
        }
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null );
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
	public ArrayList<String> getFmmNodeIdList(FmmNodeDefinition anFmmNodeDefinition, String aWhereClause, String aSortSpec) {
		ArrayList<String> theRowIdList = new ArrayList<String>();
        String theRawQuery =
                "SELECT " + IdNodeMetaData.column_ID + " FROM " + anFmmNodeDefinition.getTableName() +
                        " WHERE " + aWhereClause;
        if(aSortSpec != null) {
            theRawQuery += " ORDER BY " + aSortSpec;
        }
		Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null );
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
			String aLeftTableName, String aLeftColumnName, String aRightTableName, String aRightColumnName, String aColumnValue, String aSortSpec) {
		return "SELECT DISTINCT " + aLeftTableName + ".* FROM " + aLeftTableName +
				" INNER JOIN " + aRightTableName +
				" ON " + aLeftTableName + "." + aLeftColumnName + " = " + aRightTableName + "." + aRightColumnName +
				" AND " + aLeftTableName + "." + aLeftColumnName + " = '" + aColumnValue + "'" +
				" ORDER BY " + aSortSpec;
	}

	public static String getInnerJoinQueryWithAndSpecSorted(
			String aLeftTableName, String aLeftColumnName, String aRightTableName, String aRightColumnName, String aAndSpec, String aSortSpec) {
		return "SELECT DISTINCT " + aLeftTableName + ".* FROM " + aLeftTableName +
				" INNER JOIN " + aRightTableName +
				" ON " + aLeftTableName + "." + aLeftColumnName + " = " + aRightTableName + "." + aRightColumnName +
				" AND " + aAndSpec +
				" ORDER BY " + aSortSpec;
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
		ArrayList<String> theMovingLinkNodeIdList = getFmmNodeIdList(
                anFmmNodeDefinition, aParentIdColumnName, anOldParentId, FmmDatabaseMediator.sort_spec__SEQUENCE);
		boolean isTransactionSuccess = true;
		if(theMovingLinkNodeIdList.size() > 0){
			if(bSequenceAtEnd) {
				int theMaxSequence = getLastSequence(anFmmNodeDefinition.getTableName(), aParentIdColumnName, anNewParentID);
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
				ArrayList<String> theExistingLinkNodeIdList = getFmmNodeIdList(
						anFmmNodeDefinition, aParentIdColumnName, anNewParentID, FmmDatabaseMediator.sort_spec__SEQUENCE);
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
			int theNewSequence = getLastSequence(anFmmNodeDefinition.getTableName(), aParentIdColumnName, aNewParentID);
			++theNewSequence;
			this.contentValues.clear();
			this.contentValues.put(aParentIdColumnName, aNewParentID);
			this.contentValues.put(SequencedLinkNodeMetaData.column_SEQUENCE, theNewSequence);
		} else {
			ArrayList<String> theExistingLinkNodeIdList = getFmmNodeIdList(
					anFmmNodeDefinition, aParentIdColumnName, aNewParentID, FmmDatabaseMediator.sort_spec__SEQUENCE);
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
			int theNewSequence = getLastSequence(anFmmNodeDefinition.getTableName(), aParentIdColumnName, aNewParentID);
			++theNewSequence;
			this.contentValues.clear();
			this.contentValues.put(aParentIdColumnName, aNewParentID);
			this.contentValues.put(SequencedLinkNodeMetaData.column_SEQUENCE, theNewSequence);
		} else {
			ArrayList<String> theExistingLinkNodeIdList = getFmmNodeIdList(
					anFmmNodeDefinition, aParentIdColumnName, aNewParentID, FmmDatabaseMediator.sort_spec__SEQUENCE);
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
    public <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromSimpleIdTable(
            FmmNodeDefinition aNodeDefinition,
            String aWhereColumnName,
            String aWhereColumnValue,
            String anExceptionId,
            String aSortSpec) {
        String theRawQuery = "SELECT * FROM " + aNodeDefinition.getTableName();
        if(aWhereColumnName != null) {
            theRawQuery += " WHERE " + aWhereColumnName + " = '" + aWhereColumnValue + "' ";
        }
        if(anExceptionId != null) {
            theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + anExceptionId + "' ";
        }
        if(aSortSpec != null) {
            theRawQuery += " ORDER BY " + aSortSpec;
        }
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return getDao(aNodeDefinition).getObjectListFromCursor(theCursor);
    }
    @SuppressWarnings("resource")
    @Override
    public <T extends FmmNode> ArrayList<T> retrieveFmmNodeListFromSimpleIdTable(
            FmmNodeDefinition aNodeDefinition,
            String aWhereClause,
            String anExceptionId,
            String aSortSpec) {
        String theRawQuery = "SELECT * FROM " + aNodeDefinition.getTableName();
        if(aWhereClause != null) {
            theRawQuery += " WHERE " + aWhereClause;
        }
        if(anExceptionId != null) {
            theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + anExceptionId + "' ";
        }
        if(aSortSpec != null) {
            theRawQuery += " ORDER BY " + aSortSpec;
        }
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return getDao(aNodeDefinition).getObjectListFromCursor(theCursor);
    }

    @SuppressWarnings("resource")
    @Override
    public <V extends FmmNode> ArrayList<V> retrieveFmmNodeListFromSimpleIdLeftTableFromLink(
            FmmNodeDefinition aLeftTableDefinition,
            String aLeftColumnName,
            String aLefgColumnExceptionValue,
            FmmNodeDefinition aRightTableDefinition,
            String aRightColumnName,
            String anAndSpec,
            String aSortSpec) {
        Cursor theCursor = getSqLiteDatabase().rawQuery(getInnerJoinQueryWithAndSpecSorted(
                        aLeftTableDefinition,
                        aLeftColumnName,
                        aLefgColumnExceptionValue,
                        aRightTableDefinition,
                        aRightColumnName,
                        anAndSpec,
                        aSortSpec ),
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
	
	
    //////  Node - PORTFOLIO  ////////////////////////////////////////////////////////////////////////////////

    @Override
    public int countPortfolioForProjectMoveTarget(FmsOrganization anFmsOrganization, Portfolio aPortfolioException) {
        return 0;
    }

    @Override
    public ArrayList<? extends GcgGuiable> retrievePortfolioListForProjectMoveTarget(FmsOrganization anFmsOrganization, Portfolio aPortfolioException) {
        return null;
    }

    // TODO - should use MOVE_TARGET view and not include confirmed or proposed completions
    @SuppressWarnings("resource")
    @Override
    public ArrayList<Portfolio> retrievePortfolioListForWorkAssetMoveTarget(FmsOrganization anFmsOrganization, Project aProjectException) {
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
    public ArrayList<Portfolio> retrievePortfolioListForWorkPackageMoveTarget(FmsOrganization anFmsOrganization, WorkAsset aWorkAssetException) {
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
    public ArrayList<? extends GcgGuiable> retrievePortfolioListForWorkTaskMoveTarget(FmsOrganization anFmsOrganization, WorkPackage aWorkPackageException) {
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
    public boolean adoptOrphanProjectIntoPortfolio(
            String aProjectId,
            String aPortfolioId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
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


    //////  Node - PROJECT  ////////////////////////////////////////////////////////////////////////////////

    @Override
    public ArrayList<Project> retrieveProjectOrphanListFromPortfolio() {
        String theRawQuery =
                "SELECT * FROM " + FmmNodeDefinition.PROJECT.getTableName() +
                        " WHERE " + ProjectMetaData.column_PORTFOLIO_ID + " IS NULL" +
                        " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return ProjectDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    @Override
    public int countProjectsForProjectAssetMoveTarget(Portfolio aPortfolio, Project aProjectException) {
        return 0;
    }

    @Override
    public ArrayList<Project> retrieveProjectListForProjectAssetMoveTarget(Portfolio aPortfolio, Project aProjectException) {
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
    public ArrayList<Project> retrieveProjectListForWorkPackageMoveTarget(Portfolio aPortfolio, WorkAsset aWorkAssetException) {
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
    public ArrayList<Project> retrieveProjectListForWorkTaskMoveTarget(Portfolio aPortfolio, WorkPackage aWorkPackageException) {
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
    public boolean orphanAllProjectsFromPortfolio(String aPortfolioId, boolean bAtomicTransaction) {
        return updateRowsWithNull(
                FmmNodeDefinition.PROJECT.getTableName(),
                ProjectMetaData.column_PORTFOLIO_ID,
                ProjectMetaData.column_PORTFOLIO_ID+ " = '" + aPortfolioId + "'",
                bAtomicTransaction );
    }

    @Override
    public boolean moveSingleProjectIntoPortfolio(String aProjectId, String aPortfolioId, boolean bAtomicTransaction) {
        return updateRows(FmmNodeDefinition.PROJECT.getTableName(),
                ProjectMetaData.column_PORTFOLIO_ID,
                aPortfolioId,
                IdNodeMetaData.column_ID + " = '" + aProjectId + "'",
                bAtomicTransaction);
    }

    public boolean moveAllProjectsIntoPortfolio(String aCurrentPortfolioId, String aTargetPortfolioId, boolean bAtomicTransaction) {
        return updateRows(FmmNodeDefinition.PROJECT.getTableName(),
                ProjectMetaData.column_PORTFOLIO_ID,
                aTargetPortfolioId,
                ProjectMetaData.column_PORTFOLIO_ID + " = '" + aCurrentPortfolioId + "'",
                bAtomicTransaction);

    }

    public boolean orphanSingleProjectFromPortfolio(String aProjectNodeIdString, String aPortfolioNodeIdString, boolean bAtomicTransaction) {
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


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - FISCAL YEAR  /////////////////////////////////////////////////////////////////////////////////////

	// TODO - should use MOVE_TARGET view and not include confirmed or proposed completions
	@Override
	public int countFiscalYearForStrategicMilestoneMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException) {
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
	public ArrayList<FiscalYear> retrieveFiscalYearListForStrategicMilestoneMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException) {
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
	public int countFiscalYearForProjectAssetMoveTarget(FmsOrganization anOrganization, StrategicMilestone aStrategicMilestonException) {
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
	public ArrayList<FiscalYear> retrieveFiscalYearForProjectAssetMoveTarget(FmsOrganization anOrganization, StrategicMilestone aStrategicMilestonException) {
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
	public int countFiscalYearForWorkPackageMoveTarget(FmsOrganization anOrganization, ProjectAsset aProjectAssetException) {
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
	public ArrayList<FiscalYear> retrieveFiscalYearListForWorkPackageMoveTarget(FmsOrganization anOrganization, ProjectAsset aProjectAssetException) {
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
	public int countFiscalYearForCadenceMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException) {
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
	public ArrayList<FiscalYear> retrieveFiscalYearListForCadenceMoveTarget(FmsOrganization anOrganization, FiscalYear aFiscalYearException) {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.FISCAL_YEAR.getTableName() +
				" WHERE " + FiscalYearMetaData.column_ORGANIZATION_ID + " = '" + anOrganization.getNodeIdString() + "'";
		if(aFiscalYearException != null) {
			theRawQuery += " AND " + IdNodeMetaData.column_ID + " != '" + aFiscalYearException.getNodeIdString() + "'";
		}
		theRawQuery += " ORDER BY " + FiscalYearMetaData.column_YEAR_NUMBER;
		Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
		return FiscalYearDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - FMS ORGANIZATION  ////////////////////////////////////////////////////////////////////////////////

	@Override
	public FmsOrganization getFmmOwner() {
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
	public void setFmmOwnership(FmsOrganization anOrganization) {
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
        insertSimpleIdTable(anOrganization, false);
		setFmmOwner(anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.FISCAL_YEAR, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.FLYWHEEL_TEAM, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.STRATEGY_TEAM, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.FUNCTIONAL_TEAM, anOrganization);
		endTransaction(true);
	}

	private void replaceFmmOwner(FmsOrganization anOrganization,String anExistingFmsOrganizationId) {
		startTransaction();
        insertSimpleIdTable(anOrganization, false);
		setFmmOwner(anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.FISCAL_YEAR, anExistingFmsOrganizationId, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.ORGANIZATION_COMMUNITY_MEMBER, anExistingFmsOrganizationId, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.FLYWHEEL_TEAM, anExistingFmsOrganizationId, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.STRATEGY_TEAM, anExistingFmsOrganizationId, anOrganization);
		moveRowsToOrganization(FmmNodeDefinition.FUNCTIONAL_TEAM, anExistingFmsOrganizationId, anOrganization);
		endTransaction(true);
	}

    public void setFmmOwner(FmsOrganization anOrganization) {
        FmsOrganization theCurrentFmmOwner = retrieveFmmOwner();
        if(anOrganization.getNodeIdString().equals(theCurrentFmmOwner.getNodeIdString())) {
            return;
        }
        getSqLiteDatabase().execSQL("UPDATE " + FmmNodeDefinition.FMM_CONFIGURATION.getTableName() +
                " SET " + FmmConfigurationMetaData.column_ORGANIZATION_ID + " = '" + anOrganization.getNodeIdString() + "'" +
                " WHERE " + FmmConfigurationMetaData.column_FOR_THIS_FMM + " = 1;");
    }

    public FmsOrganization retrieveFmmOwner() {
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


	///////////////////////////////////////////////////////////////////////////////////////
	////////  Node - PROJECT ASSET  ///////////////////////////////////////////////////////
    
    // TODO - resolve StrategicAssets versus ProjectAssets versus WorkAssets

	@SuppressWarnings("resource")
	@Override
	public ArrayList<ProjectAsset> retrieveStrategicAssetListForWorkPackageMoveTarget(String aParentId, String aProjectAssetExceptionId) {
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

    public ArrayList<ProjectAsset> retrieveWorkAssetListForWorkTaskMoveTarget(String aProjectId, String aWorkPackageException) {
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
	public ArrayList<ProjectAsset> retrieveWorkAssetOrphanListFromProject() {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.PROJECT_ASSET.getTableName() +
				" WHERE " + ProjectAssetMetaData.column_PROJECT_ID + " IS NULL" +
                " AND " + ProjectAssetMetaData.column_IS_STRATEGIC + " = 0";
			theRawQuery += " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
			Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
			return ProjectAssetDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

    @SuppressWarnings("resource")
    @Override
    public ArrayList<StrategicAsset> retrieveStrategicAssetOrphanListFromProject() {
        String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.PROJECT_ASSET.getTableName() +
                " WHERE " + StrategicAssetMetaData.column_PROJECT_ID + " IS NULL" +
                " AND " + StrategicAssetMetaData.column_IS_STRATEGIC + " = 1";
        theRawQuery += " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return StrategicAssetDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

	@Override
	public boolean moveSingleProjectAssetIntoProject(
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
	public boolean moveAllProjectAssetsIntoProject(
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
	public boolean orphanSingleProjectAssetFromStrategicMilestone(String aProjectAssetId, String aStrategicMilestoneId, boolean bAtomicTransaction) {
		boolean theResult = deleteRows(FmmNodeDefinition.STRATEGIC_COMMITMENT, StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID, aProjectAssetId, bAtomicTransaction);
		reSequenceRows(FmmNodeDefinition.STRATEGIC_COMMITMENT.getTableName(), StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID, aStrategicMilestoneId);
		return theResult;
	}

	@Override
	public boolean orphanAllProjectAssetsFromStrategicMilestone(String aStrategicMilestoneId, boolean bAtomicTransaction) {
		return deleteRows(FmmNodeDefinition.STRATEGIC_COMMITMENT, StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID, aStrategicMilestoneId, bAtomicTransaction);
	}

	@Override
	public boolean orphanSingleProjectAssetFromProject(String aProjectAssetId, String aProjectId, boolean bAtomicTransaction) {
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
	public boolean orphanAllProjectAssetsFromProject(String aProjectId, boolean bAtomicTransaction) {
		return orphanSequenceRows(
                FmmNodeDefinition.PROJECT_ASSET.getTableName(),
                ProjectAssetMetaData.column_PROJECT_ID,
                ProjectAssetMetaData.column_PROJECT_ID + " = '" + aProjectId + "'",
                bAtomicTransaction);
	}

	@Override
	public int dbGetMoveTargetWorkPackageCount(ProjectAsset aProjectAsset, WorkPackage aWorkPackageException) {
		// TODO Auto-generated method stub
		return 0;
	}


    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //////  Node - STRATEGIC ASSET  ///////////////////////////////////////////////////////////////////////////////////


    @Override
    public boolean moveSingleStrategicAssetIntoProject(
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
    public boolean orphanSingleStrategicAssetFromProject(String aStrategicAssetId, String aProjectId, boolean bAtomicTransaction) {
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

    public boolean moveAllStrategicAssetsIntoStrategicMilestone(String aSourceStrateticMilestoneId, String aDestinationStrategicMilestoneId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {         return false;     }

    public boolean moveSingleStrategicAssetIntoStrategicMilestone(String aStrategicAssetId, String anOriginalStrategicMilestoneId, String aDestinationStrategicMilestoneId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
        return moveSingleLinkNode(
                FmmNodeDefinition.STRATEGIC_COMMITMENT,
                StrategicCommitmentMetaData.column_STRATEGIC_ASSET_ID,
                aStrategicAssetId,
                StrategicCommitmentMetaData.column_STRATEGIC_MILESTONE_ID,
                anOriginalStrategicMilestoneId,
                aDestinationStrategicMilestoneId,
                bSequenceAtEnd);
    }


    ////  Node - STRATEGIC MILESTONE  ////////////////////////////////////////////////////////////////////////////////

	@Override
	public int countStrategicMilestoneForProjectAssetMoveTarget(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestoneException) {
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
	public ArrayList<StrategicMilestone> retrieveStrategicMilestoneListForProjectAssetMoveTarget(FiscalYear aFiscalYear, StrategicMilestone aStrategicMilestoneException) {
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
	public int countStrategicMilestoneForWorkPackageMoveTarget(FiscalYear aFiscalYear, ProjectAsset aProjectAssetException) {
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
	public ArrayList<StrategicMilestone> retrieveStrategicMilestoneListForWorkPackageMoveTarget(FiscalYear aFiscalYear, ProjectAsset aProjectAssetException) {
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
	public int moveAllStrategicMilestonesIntoFiscalYear(
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
	public boolean moveSingleStrategicMilestoneIntoFiscalYear(
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

    ///////////////////////////////////////////////////////
    ///////////  SEQUENCE  ////////////////////////////////

	private int updateSequenceBeforeAddingNewPeer(
			FmmNodeDefinition anFmmNodeDefinition,
			String aParentIdColumnName,
			String aParentId,
			boolean bSequenceAtEnd) {
		int theSequence = 1;
		if(bSequenceAtEnd) {
			theSequence = countRows(anFmmNodeDefinition.getTableName(), aParentIdColumnName, aParentId) + 1;
		} else {
			incrementSequence(anFmmNodeDefinition.getTableName(), aParentIdColumnName, aParentId);
		}
		return theSequence;
	}

    @Override
    public void incrementSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue) {
        incrementSequence(aTableName, aWhereColumnName, aWhereColumnValue, CompletableNodeMetaData.column_SEQUENCE);
    }
	
	@Override
	public void incrementSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue,
            String aSequenceColumnName) {
		getSqLiteDatabase().execSQL(
				"UPDATE " + aTableName +
				" SET "+ aSequenceColumnName + " = " + aSequenceColumnName + " + 1 " +
				" WHERE " + aWhereColumnName + " = '" + aWhereColumnValue + "'" );
	}
	
	@Override
	public void incrementSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue,
            int aFirstSequenceToIncrement) {
        incrementSequence(aTableName, aWhereColumnName, aWhereColumnValue, aFirstSequenceToIncrement, CompletableNodeMetaData.column_SEQUENCE);
	}

    @Override
    public void incrementSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue,
            int aFirstSequenceToIncrement,
            String aSequenceColumnName) {
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
		resequenceOnRemove(aTableName, aWhereColumnName, aWhereColumnValue, theMissingSequence);
	}
	
	@Override
	public void resequenceOnRemove(
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
    public int getLastSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue) {
        return getLastSequence(
                aTableName,
                aWhereColumnName,
                aWhereColumnValue,
                SequencedLinkNodeMetaData.column_SEQUENCE);
    }
	
	@Override
	public int getLastSequence(
            String aTableName,
            String aWhereColumnName,
            String aWhereColumnValue,
            String aSequenceColumnName) {
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

	@SuppressWarnings("resource")
	@Override
	public ArrayList<WorkPackage> retrieveWorkPackageListForWorkTaskMoveTarget(String aParentNodeId, String aWorkPackageExceptionId, boolean bPrimaryParent) {
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
			String theAndClause = FmmNodeDefinition.CADENCE_COMMITMENT.getTableName() + "." + CadenceCommitmentMetaData.column_CADENCE_ID + " = '" + aParentNodeId + "'";
			if(aWorkPackageExceptionId != null) {
				theAndClause += " AND " + FmmNodeDefinition.CADENCE_COMMITMENT.getTableName() + "." + CadenceCommitmentMetaData.column_WORK_PACKAGE_ID + " != '" + aWorkPackageExceptionId + "'";
			}
			theCursor = getSqLiteDatabase().rawQuery(getInnerJoinQueryWithAndSpecSorted(
					FmmNodeDefinition.WORK_PACKAGE.getTableName(),
					IdNodeMetaData.column_ID,
					FmmNodeDefinition.CADENCE_COMMITMENT.getTableName(),
					CadenceCommitmentMetaData.column_WORK_PACKAGE_ID,
					theAndClause,
					FmmNodeDefinition.CADENCE_COMMITMENT.getTableName() + "." + SequencedLinkNodeMetaData.column_SEQUENCE), null);
		}
		return WorkPackageDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@Override
	public boolean orphanAllWorkPackagesFromProjectAsset(String aProjectAssetNodeId, boolean bAtomicTransaction) {
        return orphanSequenceRows(
                FmmNodeDefinition.WORK_PACKAGE.getTableName(),
                WorkPackageMetaData.column_WORK_ASSET_ID,
                WorkPackageMetaData.column_WORK_ASSET_ID + " = '" + aProjectAssetNodeId + "'",
                bAtomicTransaction);
	}

    @Override
    public boolean orphanAllWorkPackagesFromCadence(String aCadenceId, boolean bAtomicTransaction) {
        return false;
    }

    public boolean orphanSingleWorkPackageFromProjectAsset(String aWorkPackageId, String aProjectAssetId, boolean bAtomicTransaction) {
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

    public boolean orphanSingleWorkPackageFromCadence(String aWorkPackageId, String aCadenceId, boolean bAtomicTransaction) {
        return false;
    }

	@SuppressWarnings("resource")
	@Override
	public ArrayList<WorkPackage> retrieveWorkPackageOrphanListFromProjectAsset() {
		String theRawQuery = "SELECT * FROM " + FmmNodeDefinition.WORK_PACKAGE.getTableName() +
				" WHERE " + WorkPackageMetaData.column_WORK_ASSET_ID + " IS NULL";
			theRawQuery += " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
			Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
			return WorkPackageDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
	}

	@Override
	public ArrayList<WorkPackage> retrieveWorkPackageOrphanListFromCadence() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean adoptOrphanWorkPackageIntoProjectAsset(
            String aWorkPackageId,
            String aProjectAssetId,
            boolean bSequenceAtEnd,
            boolean bAtomicTransaction) {
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
	public boolean moveAllWorkPackagesIntoProjectAsset(
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

    public boolean moveSingleWorkPackageIntoProjectAsset(
            String aWorkPackageId,
            String anOriginalProjectAssetId,
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
                IdNodeMetaData.column_ID + " = '" + aWorkPackageId + "'", null);
        if(bAtomicTransaction) {
            endTransaction(theRowCount > 0);
        }
        return theRowCount > 0;
        
    }

    
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////  Node - WORK TASK  ///////////////////////////////////////////////////////////////////////////////////


    public boolean moveAllWorkTasksIntoWorkPackage(String aSourceWorkPackageId, String aDestinationWorkPackageId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
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

    public boolean orphanAllWorkTasksFromWorkPackage(String aWorkPackageId, boolean bAtomicTransaction) {
        return orphanSequenceRows(
                FmmNodeDefinition.WORK_TASK.getTableName(),
                WorkTaskMetaData.column_WORK_PACKAGE__ID,
                WorkTaskMetaData.column_WORK_PACKAGE__ID + " = '" + aWorkPackageId + "'",
                bAtomicTransaction);
    }

    public boolean orphanSingleWorkTaskFromWorkPackage(String aWorkTaskId, String aWorkPackageId, boolean bAtomicTransaction) {
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

    public ArrayList<WorkTask> retrieveWorkTaskOrphanListFromWorkPackage() {
        String theRawQuery =
                "SELECT * FROM " + FmmNodeDefinition.WORK_TASK.getTableName() +
                        " WHERE " + WorkTaskMetaData.column_WORK_PACKAGE__ID + " IS NULL" +
                        " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return WorkTaskDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    public ArrayList<WorkTask> retrieveWorkTaskOrphanListFromWorkPlan() {
        String theRawQuery =
                "SELECT * FROM " + FmmNodeDefinition.WORK_TASK.getTableName() +
                        " WHERE " + WorkTaskMetaData.column_WORK_PLAN__ID + " IS NULL" +
                        " ORDER BY " + HeadlineNodeMetaData.column_HEADLINE + " ASC";
        Cursor theCursor = getSqLiteDatabase().rawQuery(theRawQuery, null);
        return WorkTaskDaoSqLite.getInstance().getObjectListFromCursor(theCursor);
    }

    public boolean adoptOrphanWorkTaskIntoWorkPackage(String aWorkTaskId, String aWorkPackageId, boolean bSequenceAtEnd, boolean bAtomicTransaction) {
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
	
}
