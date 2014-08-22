/* @(#)FmmFrame.java
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

package com.flywheelms.library.fmm.context;

import java.util.ArrayList;
import java.util.Arrays;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.helper.FmmHelper;
import com.flywheelms.library.gcg.GcgApplication;
import com.flywheelms.library.gcg.interfaces.GcgFrame;
import com.flywheelms.library.gcg.interfaces.GcgPerspective;

public enum FmmFrame implements GcgFrame {

	COMMITMENT(R.string.fmm_frame__commitment, R.string.fmm_frame__commitment__abreviated, new FmmPerspective[] {
			FmmPerspective.CONFIRMED_COMMITMENTS,
			FmmPerspective.PROPOSED_COMMITMENTS,
			FmmPerspective.SUGGESTED_COMMITMENTS,
			FmmPerspective.DECLINED_COMMITMENTS,
			FmmPerspective.WITHDRAWN_COMMITMENTS }),
	CONTEXT_WORKBENCH(R.string.fmm_frame__context, new FmmPerspective[] {
			FmmPerspective.STRATEGIC_PLANNING,
			FmmPerspective.SERVICE_DELIVERY,
			FmmPerspective.WORK_BREAKDOWN,
			FmmPerspective.WORK_PLANNING,
			FmmPerspective.ANALYSIS }),
	CONTEXT_NODE(R.string.fmm_frame__context, new FmmPerspective[] {
			FmmPerspective.STRATEGIC_PLANNING,
			FmmPerspective.WORK_BREAKDOWN,
			FmmPerspective.WORK_PLANNING,
			FmmPerspective.ANALYSIS }),
	FSE(R.string.fmm_frame__fse, new FmmPerspective[] {
			FmmPerspective.STORY,
			FmmPerspective.NOTES,
			FmmPerspective.HISTORY,
			FmmPerspective.COMMUNITY }),
	QUALITY(R.string.fmm_frame__quality, new FmmPerspective[] {
			FmmPerspective.STRATEGIC_PLANNING,
			FmmPerspective.WORK_BREAKDOWN,
			FmmPerspective.WORK_PLANNING,
			FmmPerspective.SERVICE_DELIVERY }),
	TEAM(R.string.fmm_frame__teams, new FmmPerspective[] {
			FmmPerspective.STRATEGY_TEAMS,
			FmmPerspective.FLYWHEEL_TEAMS,
			FmmPerspective.FUNCTIONAL_TEAMS,
			FmmPerspective.GOVERNANCE_TEAMS }),
	TRIBKN(R.string.fmm_frame__tribkn, new FmmPerspective[] {  // SWAG
			FmmPerspective.DECKANGL,
			FmmPerspective.GOVERNANCE,
			FmmPerspective.COMMITMENTS,
			FmmPerspective.COMMUNITY }),
	VELOCITY(R.string.fmm_frame__velocity, new FmmPerspective[] {
			FmmPerspective.DECKANGL,
			FmmPerspective.STORY,
			FmmPerspective.GOVERNANCE,
			FmmPerspective.COMMUNITY,
			FmmPerspective.ANALYSIS });

	public static FmmFrame getObjectForName(String aName) {
		FmmFrame theFmmFrame = null;
		for(FmmFrame theInstance : FmmFrame.values()) {
			if(theInstance.getName().equals(aName)) {
				theFmmFrame = theInstance;
				break;
			}
		}
		return theFmmFrame;
	}
		
//		// These need to be added to the array in menu order.
//	public static final FmmPerspective[] CONTEXT_MENU_ARRAY = {
//		STRATEGIC_PLANNING,
//		WORK_BREAKDOWN,
//		WORK_PLANNING,
//		ANALYSIS,
//	};
//	
//	// These need to be added to the array in menu order.
//	public static final FmmPerspective[] MENU_ARRAY = {
//		STRATEGIC_PLANNING,
//		COMMITMENTS,
//		WORK_BREAKDOWN,
//		WORK_PLANNING,
//		SERVICE_DELIVERY,
//		GOVERNANCE,
//		ANALYSIS,
//		COMMUNITY,
//		FACILITATION_ISSUES
//	};
//	
//	public static String getName(int aMenuSequence) {
//		for (FmmPerspective theFmmPerspective : MENU_ARRAY) {
//			if (theFmmPerspective.getMenuSequence() == aMenuSequence) {
//				return theFmmPerspective.getName();
//			}
//		}
//		return null;
//	}
//	
//	private static final HashMap<String, FmmPerspective> nameMap = new HashMap<String, FmmPerspective>();
//	static {
//		FmmPerspective.nameMap.put(COMMITMENTS.getName(), COMMITMENTS);
//		FmmPerspective.nameMap.put(COMMUNITY.getName(), COMMUNITY);
//		FmmPerspective.nameMap.put(ANALYSIS.getName(), ANALYSIS);
//		FmmPerspective.nameMap.put(FACILITATION_ISSUES.getName(), FACILITATION_ISSUES);
//		FmmPerspective.nameMap.put(GOVERNANCE.getName(), GOVERNANCE);
//		FmmPerspective.nameMap.put(SERVICE_DELIVERY.getName(), SERVICE_DELIVERY);
//		FmmPerspective.nameMap.put(STRATEGIC_PLANNING.getName(), STRATEGIC_PLANNING);
//		FmmPerspective.nameMap.put(WORK_BREAKDOWN.getName(), WORK_BREAKDOWN);
//		FmmPerspective.nameMap.put(WORK_PLANNING.getName(), WORK_PLANNING);
//	}
	
	private final int nameResourceId;
	private String name;
	private final int headingResourceId;
	private CharSequence heading;
	private ArrayList<FmmPerspective> fmmPerspectiveList;
	
	private FmmFrame(int aNameResourceId, FmmPerspective[] anFmmPerspectiveArray) {
		this.nameResourceId = aNameResourceId;
		this.headingResourceId = aNameResourceId;
		this.fmmPerspectiveList = new ArrayList<FmmPerspective>(Arrays.asList(anFmmPerspectiveArray));
	}
	
	private FmmFrame(int aNameResourceId, int aHeadingResourceId, FmmPerspective[] anFmmPerspectiveArray) {
		this.nameResourceId = aNameResourceId;
		this.headingResourceId = aHeadingResourceId;
		this.fmmPerspectiveList = new ArrayList<FmmPerspective>(Arrays.asList(anFmmPerspectiveArray));
	}
	
	@Override
	public int getNameResourceId() {
		return this.nameResourceId;
	}
	
	@Override
	public int getHeadingResourceId() {
		return this.headingResourceId;
	}

	@Override
	public CharSequence getHeadingCharSequence() {
		if(this.heading == null) {
			this.heading = GcgApplication.getAppResources().getText(getHeadingResourceId());
		}
		return this.heading;
	}
	
	@Override
	public String getName() {
		if(this.name == null) {
			this.name = FmmHelper.getContext().getResources().getString(this.nameResourceId);
		}
		return this.name;
	}
	
	@Override
	public ArrayList<FmmPerspective> getPerspectiveList() {
		return this.fmmPerspectiveList;
	}
	
	@Override
	public int getPerspectiveMenuItemPosition(GcgPerspective aPerspective) {
		return this.fmmPerspectiveList.indexOf(aPerspective);
	}

	@Override
	public GcgPerspective getPerspectiveAt(int aPerspectiveIndex) {
		return this.fmmPerspectiveList.get(aPerspectiveIndex);
	}

}
