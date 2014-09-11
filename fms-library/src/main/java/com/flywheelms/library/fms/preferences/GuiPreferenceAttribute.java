/* @(#)GuiPreferenceAttribute.java
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

package com.flywheelms.library.fms.preferences;

import com.flywheelms.library.gcg.preferences.GcgPreferenceDataType;

public enum GuiPreferenceAttribute {

	//  DO NOT CHANGE these attribute key values.  User preferences will be lost !!!
	CHILD_SUMMARY ("childSummary", GcgPreferenceDataType.INTEGER),
	CONTACTS__ANDROID ("contactsAndroid", GcgPreferenceDataType.STRING_SET),
	CUSTOMER__FILTER__CHECKED ("customerFilterChecked", GcgPreferenceDataType.BOOLEAN),
	CUSTOMER__NODE_ID ("customerNodeId", GcgPreferenceDataType.STRING),
	CUSTOMER__TARGET_NODE_TYPE ("customerTargetNodeType", GcgPreferenceDataType.STRING),
	DROPBOX__DIRECTORY_NAME ("dropboxDirectoryName", GcgPreferenceDataType.STRING),
	DROPBOX__PERSISTENCE ("dropboxPersistence", GcgPreferenceDataType.BOOLEAN),
	EMPHASIS_LEVEL ("emphasisLevel", GcgPreferenceDataType.INTEGER),
	FACILITATOR__FILTER__CHECKED ("facilitatorFilterChecked", GcgPreferenceDataType.BOOLEAN),
	FACILITATOR__NODE_ID ("facilitatorNodeId", GcgPreferenceDataType.STRING),
	FACILITATOR__TARGET_NODE_TYPE ("facilitatorTargetNodeType", GcgPreferenceDataType.STRING),
	FISCAL_YEAR__WORK_STATE__CHECKED ("fiscalYearWorkStateChecked", GcgPreferenceDataType.BOOLEAN),
	FISCAL_YEAR__WORK_STATE ("fiscalYearWorkState", GcgPreferenceDataType.STRING),
	FLYWHEEL_TEAM__CHECKBOX ("flywheelTeamCheckbox", GcgPreferenceDataType.BOOLEAN),
	FLYWHEEL_TEAM__NAME ("flywheelTeamName", GcgPreferenceDataType.STRING),
	FUNCTIONAL_TEAM__CHECKBOX ("functionalTeamCheckbox", GcgPreferenceDataType.BOOLEAN),
	FUNCTIONAL_TEAM__NAME ("functionalTeamName", GcgPreferenceDataType.STRING),
	NODE_QUALITY ("nodeQuality", GcgPreferenceDataType.INTEGER),
	PRINT ("print", GcgPreferenceDataType.BOOLEAN),
	PROJECT_ASSET__WORK_STATE__CHECKED ("projectAssetWorkStateChecked", GcgPreferenceDataType.BOOLEAN),
	PROJECT_ASSET__WORK_STATE ("projectAssetWorkState", GcgPreferenceDataType.STRING),
	SHOW__ANALYSIS ("showAnalysis", GcgPreferenceDataType.BOOLEAN),
	SHOW__COMMITMENTS ("showCommitments", GcgPreferenceDataType.BOOLEAN),
	SHOW__COMMUNITY ("showCommunity", GcgPreferenceDataType.BOOLEAN),
	SHOW__CONTENT_MODIFICATION ("showContentModification", GcgPreferenceDataType.BOOLEAN),
	SHOW__CONTRIBUTORS ("showContributors", GcgPreferenceDataType.BOOLEAN),
	SHOW__DECKANGL ("showDecKanGl", GcgPreferenceDataType.BOOLEAN),
	SHOW__GOVERNANCE ("showGovernance", GcgPreferenceDataType.BOOLEAN),
	SHOW__HISTORY ("showHistory", GcgPreferenceDataType.BOOLEAN),
	SHOW__LOCKS ("showLocks", GcgPreferenceDataType.BOOLEAN),
	SHOW__NOTES ("showNotes", GcgPreferenceDataType.BOOLEAN),
	SHOW__PARAGRAPH_NUMBERING ("showNumbering", GcgPreferenceDataType.BOOLEAN),
	SHOW__STORY ("showStory", GcgPreferenceDataType.BOOLEAN),
	SHOW__STRATEGIC_PLANNING ("showStrategicPlanning", GcgPreferenceDataType.BOOLEAN),
	SHOW__TABLE_OF_CONTENTS ("showTableOfContents", GcgPreferenceDataType.BOOLEAN),
	SHOW__TITLE_PAGE ("showTitlePage", GcgPreferenceDataType.BOOLEAN),
	SHOW__WORK_BREAKDOWN ("showWorkBreakdown", GcgPreferenceDataType.BOOLEAN),
	SHOW__WORK_PLANNING ("showWorkPlanning", GcgPreferenceDataType.BOOLEAN),
	SPONSOR__FILTER__CHECKED ("sponsorFilterChecked", GcgPreferenceDataType.BOOLEAN),
	SPONSOR__NODE_ID ("sponsorNodeId", GcgPreferenceDataType.STRING),
	SPONSOR__TARGET_NODE_TYPE ("sponsorTargetNodeType", GcgPreferenceDataType.STRING),
	STRATEGIC_MILESTONE__WORK_STATE__CHECKED ("strategicMilestoneWorkStateChecked", GcgPreferenceDataType.BOOLEAN),
	STRATEGIC_MILESTONE__WORK_STATE ("strategicMilestoneWorkState", GcgPreferenceDataType.STRING),
	STRATEGY_TEAM__CHECKBOX ("strategyTeamCheckbox", GcgPreferenceDataType.BOOLEAN),
	STRATEGY_TEAM__NAME ("strategyTeamName", GcgPreferenceDataType.STRING),
	TREE_DEPTH ("treeDepth", GcgPreferenceDataType.INTEGER);

	private final String key;
	private final GcgPreferenceDataType dataType;
	
	private GuiPreferenceAttribute(String aKey, GcgPreferenceDataType aDataType) {
		this.key = aKey;
		this.dataType = aDataType;
	}

	public String getKey() {
		return this.key;
	}

	public GcgPreferenceDataType getDataType() {
		return this.dataType;
	}
}
