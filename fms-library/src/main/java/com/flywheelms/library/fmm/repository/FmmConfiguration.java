/* @(#)FmmConfiguration.java
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

package com.flywheelms.library.fmm.repository;

import android.content.Intent;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.meta_data.HeadlineNodeMetaData;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.FmmGovernableNodeImpl;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fmm.persistence.PersistenceServiceProvider;
import com.flywheelms.library.fmm.persistence.PersistenceTechnology;
import com.flywheelms.library.fmm.persistence.PersistenceTechnologyDelegate;
import com.flywheelms.library.fms.helper.FmsActivityHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public abstract class FmmConfiguration extends FmmGovernableNodeImpl {

	private static final long serialVersionUID = 1L;
	protected static final String attribute__ACCESS_SCOPE = "accessScope";
	protected static final String attribute__PERSISTENCE_SERVICE_PROVIDER = "serviceProvider";
	protected static final String attribute__FILE_NAME = "fileName";
	protected static final String attribute__DB_NAME = "dbName";
	protected static final String attribute__DB_VERSION = "dbVersion";
	protected FmmAccessScope accessScope;
	protected PersistenceServiceProvider persistenceServiceProvider;
	protected String fileName;
	protected String dbName;
	protected int dbVersion = 1;
	protected String templateDirectoryName;
	protected File templateFileName;
	protected String organizationNodeIdString;
	protected boolean forThisFmm = false;
	private Date lastSyncDate;

	public FmmConfiguration(String aHeadline) {
		super(new NodeId(FmmConfiguration.class));
		this.headline = aHeadline;
	}
	
	public FmmConfiguration(String aHeadline, String aDbName) {
		super(new NodeId(FmmConfiguration.class));
		this.headline = aHeadline;
		this.dbName = aDbName;
	}

	public FmmConfiguration(JSONObject aJsonObject) {
		super(FmmConfiguration.class, aJsonObject);
		try {
			setDbName(aJsonObject.getString(attribute__DB_NAME));
			setFmmAccessScope(FmmAccessScope.getObjectForName(aJsonObject.getString(attribute__ACCESS_SCOPE)));
			setPersistenceServiceProvider(PersistenceServiceProvider.getObjectForName(aJsonObject.getString(attribute__PERSISTENCE_SERVICE_PROVIDER)));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(IdNodeMetaData.column_ID, getNodeIdString());
			theJsonObject.put(HeadlineNodeMetaData.column_HEADLINE, getHeadline());
			theJsonObject.put(attribute__DB_NAME, getDbName());
			theJsonObject.put(attribute__ACCESS_SCOPE, getFmmAccessScope().getName());
			theJsonObject.put(attribute__PERSISTENCE_SERVICE_PROVIDER, getPersistenceServiceProvider().getName());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public FmmAccessScope getFmmAccessScope() {
		return this.accessScope;
	}
	
	public void setFmmAccessScope(FmmAccessScope anAccessScope) {
		this.accessScope = anAccessScope;
	}
	
	public boolean isFmmAccessScopePrivate() {
		return this.accessScope == FmmAccessScope.PRIVATE;
	}
	
	public boolean isFmmAccessScopeShared() {
		return this.accessScope == FmmAccessScope.SHARED;
	}
	
	public boolean isFmmAccessScopeTeam() {
		return this.accessScope == FmmAccessScope.TEAM;
	}
	
	public PersistenceTechnologyDelegate getPersistenceTechnologyDelegate() {
		return getPersistenceServiceProvider().getPersistenceTechnologyDelegate();
	}
	
	public PersistenceServiceProvider getPersistenceServiceProvider() {
		return this.persistenceServiceProvider;
	}
	
	public void setPersistenceServiceProvider(PersistenceServiceProvider aPersistenceServiceProvider) {
		this.persistenceServiceProvider = aPersistenceServiceProvider;
	}

	public PersistenceTechnology getPersistenceTechnology() {
		return getPersistenceServiceProvider().getPersistenceTechnology();
	}

	public String getDbName() {
		return this.dbName;
	}

	public String getFileName() {
		return this.fileName;
	}
	
	public void setFileName(String aFileName) {
		this.fileName = aFileName;
	}

	public void setDbName(String aDbName) {
		this.dbName = aDbName;
	}
	
	@Override
	public String getDataText() {
		return getHeadline();
	}
	
	public boolean getForThisFmm() {
		return this.forThisFmm;
	}
	
	public void setForThisFmm(boolean aBoolean) {
		this.forThisFmm = aBoolean;
	}

	public void setForThisFmm(int anInteger) {
		setForThisFmm(anInteger != 0);
	}
	
	public Date getLastSyncDate() {
		return this.lastSyncDate;
	}

	public void setLastSyncDate(Date aDate) {
		this.lastSyncDate = aDate;
	}

	public String getOrganizationNodeIdString() {
		return this.organizationNodeIdString;
	}

	public void setOrganizationNodeIdString(String anFmsOrganizationNodeIdString) {
		this.organizationNodeIdString = anFmsOrganizationNodeIdString;
	}

	public int getDbVersion() {
		return this.dbVersion;
	}
	
	public void setDbVersion(int aVersion) {
		this.dbVersion = aVersion;
	}
	
	public static void startNodeEditorActivity(GcgActivity anActivity, String aNodeListParentNodeId, ArrayList<FmmHeadlineNodeShallow> aHeadlineNodeShallowList, String anInitialNodeIdToDisplay) {
		FmmHeadlineNodeImpl.startNodeEditorActivity(
				anActivity,
				aNodeListParentNodeId,
				aHeadlineNodeShallowList,
				anInitialNodeIdToDisplay,
				FmmNodeDefinition.FMM_CONFIGURATION );
	}
	
	public static void startNodePickerActivity(GcgActivity anActivity, ArrayList<String> aNodeIdExclusionList, String aWhereClause, String aListActionLabel) {
		FmsActivityHelper.startHeadlineNodePickerActivity(anActivity, FmmNodeDefinition.COMMUNITY_MEMBER, aNodeIdExclusionList, aWhereClause, aListActionLabel);
	}
	
	public static FmmConfiguration getFmmConfiguration(Intent anIntent) {
		return FmmDatabaseMediator.getActiveMediator().getFmmConfiguration(NodeId.getNodeIdString(anIntent));
	}
	
	@Override
	public String toString() {
		return getHeadline();
	}

	public static String getDbName(String theDbFileName) {
		return theDbFileName.substring(0, theDbFileName.length() - 3);
	}

}
