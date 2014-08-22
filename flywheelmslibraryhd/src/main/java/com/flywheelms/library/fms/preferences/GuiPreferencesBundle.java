/* @(#)GuiPreferencesBundle.java
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

import java.util.ArrayList;
import java.util.Arrays;

public enum GuiPreferencesBundle {

	//  DO NOT CHANGE these bundle key values.  User preferences will be lost !!!
	FWB__CONTEXT__ANALYSIS__GOVERNANCE ("com.flywheelms.fwb.context.analysis.governance", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__ANALYSIS__SHOW ("com.flywheelms.fwb.context.analysis.show", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__ANALYSIS__TEAM ("com.flywheelms.fwb.context.analysis.team", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__ANALYSIS__WORK_STATUS ("com.flywheelms.fwb.context.analysis.work_status", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__SERVICE_DELIVERY__GOVERNANCE ("com.flywheelms.fwb.context.service_delivery.governance", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__SERVICE_DELIVERY__SHOW ("com.flywheelms.fwb.context.service_delivery.show", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__SERVICE_DELIVERY__TEAM ("com.flywheelms.fwb.context.service_delivery.team", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__SERVICE_DELIVERY__WORK_STATUS ("com.flywheelms.fwb.context.service_delivery.work_status", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__STRATEGIC_PLANNING__GOVERNANCE ("com.flywheelms.fwb.context.strategic_planning.governance", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__STRATEGIC_PLANNING__SHOW ("com.flywheelms.fwb.context.strategic_planning.show", new GuiPreferenceAttribute[] {
			GuiPreferenceAttribute.CHILD_SUMMARY,
			GuiPreferenceAttribute.NODE_QUALITY,
			GuiPreferenceAttribute.TREE_DEPTH,
			GuiPreferenceAttribute.EMPHASIS_LEVEL
	}),
	FWB__CONTEXT__STRATEGIC_PLANNING__TEAM ("com.flywheelms.fwb.context.strategic_planning.team", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__STRATEGIC_PLANNING__WORK_STATUS ("com.flywheelms.fwb.context.strategic_planning.work_status", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__WORK_BREAKDOWN__GOVERNANCE ("com.flywheelms.fwb.context.work_breakdown.governance", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__WORK_BREAKDOWN__SHOW ("com.flywheelms.fwb.context.work_breakdown.show", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__WORK_BREAKDOWN__TEAM ("com.flywheelms.fwb.context.work_breakdown.team", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__WORK_BREAKDOWN__WORK_STATUS ("com.flywheelms.fwb.context.work_breakdown.work_status", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__WORK_PLANNING__GOVERNANCE ("com.flywheelms.fwb.context.work_planning.governance", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__WORK_PLANNING__SHOW ("com.flywheelms.fwb.context.work_planning.show", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__WORK_PLANNING__TEAM ("com.flywheelms.fwb.context.work_planning.team", new GuiPreferenceAttribute[] {
	}),
	FWB__CONTEXT__WORK_PLANNING__WORK_STATUS ("com.flywheelms.fwb.context.work_planning.work_status", new GuiPreferenceAttribute[] {
	}),
	PUBLISH_PDF__CONTENT ("com.flywheelms.publish_pdf.content", new GuiPreferenceAttribute[] {
			GuiPreferenceAttribute.SHOW__ANALYSIS,
			GuiPreferenceAttribute.SHOW__COMMITMENTS,
			GuiPreferenceAttribute.SHOW__COMMUNITY,
			GuiPreferenceAttribute.SHOW__CONTENT_MODIFICATION,
			GuiPreferenceAttribute.SHOW__CONTRIBUTORS,
			GuiPreferenceAttribute.SHOW__DECKANGL,
			GuiPreferenceAttribute.SHOW__GOVERNANCE,
			GuiPreferenceAttribute.SHOW__HISTORY,
			GuiPreferenceAttribute.SHOW__NOTES,
			GuiPreferenceAttribute.SHOW__PARAGRAPH_NUMBERING,
			GuiPreferenceAttribute.SHOW__STORY,
			GuiPreferenceAttribute.SHOW__STRATEGIC_PLANNING,
			GuiPreferenceAttribute.SHOW__TABLE_OF_CONTENTS,
			GuiPreferenceAttribute.SHOW__TITLE_PAGE,
			GuiPreferenceAttribute.SHOW__WORK_BREAKDOWN,
			GuiPreferenceAttribute.SHOW__WORK_PLANNING }),
	PUBLISH_PDF__DESTINATION ("com.flywheelms.publish_pdf.destination", new GuiPreferenceAttribute[] {
			GuiPreferenceAttribute.CONTACTS__ANDROID,
			GuiPreferenceAttribute.DROPBOX__DIRECTORY_NAME,
			GuiPreferenceAttribute.DROPBOX__PERSISTENCE,
			GuiPreferenceAttribute.PRINT
	}),
	PUBLISH_PDF__WORK_BREAKDOWN ("com.flywheelms.publish_pdf.work_breakdown", new GuiPreferenceAttribute[] {
	}),
	PUBLISH_PDF__WORK_PLANNING ("com.flywheelms.publish_pdf.work_planning", new GuiPreferenceAttribute[] {
	});
	
	private final String key;
	private final ArrayList<GuiPreferenceAttribute> attributeList;
	
	private GuiPreferencesBundle(String aKey, GuiPreferenceAttribute[] anAttributeArray) {
		this.key = aKey;
		this.attributeList = new ArrayList<GuiPreferenceAttribute>(Arrays.asList(anAttributeArray));
	}

	public String getKey() {
		return this.key;
	}

	public ArrayList<GuiPreferenceAttribute> getAttributeList() {
		return this.attributeList;
	}

}
