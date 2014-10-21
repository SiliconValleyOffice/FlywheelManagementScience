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

import com.flywheelms.gcongui.gcg.context.GcgFrame;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.library.R;

import java.util.ArrayList;

public class FmmFrame extends GcgFrame {

	public static FmmFrame COMMITMENT = new FmmFrame(R.string.fmm_frame__commitment, R.string.fmm_frame_definition__commitment, R.string.fmm_frame__commitment__abreviated, new FmmPerspective[] {
			FmmPerspective.CONFIRMED_COMMITMENTS,   // 1
            FmmPerspective.PROPOSED_COMMITMENTS,    // 2
            FmmPerspective.SUGGESTED_COMMITMENTS,   // 3
            FmmPerspective.DECLINED_COMMITMENTS,    // 4
            FmmPerspective.WITHDRAWN_COMMITMENTS });//5

    public static FmmFrame CONTEXT_FOR_WORKBENCH = new FmmFrame(R.string.fmm_frame__context, R.string.fmm_frame_definition__context, new FmmPerspective[] {
			FmmPerspective.STRATEGIC_PLANNING,  // 1
            FmmPerspective.WORK_BREAKDOWN,      // 2
            FmmPerspective.WORK_PLANNING,       // 3
            FmmPerspective.SERVICE_DELIVERY,    // 4
            FmmPerspective.NOTEBOOK});         // 5
    
    public static FmmFrame CONTEXT_FOR_NODE = new FmmFrame(R.string.fmm_frame__context, R.string.fmm_frame_definition__context, new FmmPerspective[] {
			FmmPerspective.STRATEGIC_PLANNING,  // 1
            FmmPerspective.WORK_BREAKDOWN,      // 2
            FmmPerspective.WORK_PLANNING,       // 3
            FmmPerspective.NOTEBOOK});         // 4
    
    public static FmmFrame FSE = new FmmFrame(R.string.fmm_frame__fse, R.string.fmm_frame_definition__fse, new FmmPerspective[] {
			FmmPerspective.STORY,        // 1
            FmmPerspective.NOTES,        // 2
            FmmPerspective.HISTORY,      // 3
            FmmPerspective.COMMUNITY }); // 4
    
    public static FmmFrame QUALITY = new FmmFrame(R.string.fmm_frame__quality, R.string.fmm_frame_definition__quality, new FmmPerspective[] {
			FmmPerspective.STRATEGIC_PLANNING,  // 1
            FmmPerspective.WORK_BREAKDOWN,      // 2
            FmmPerspective.WORK_PLANNING,       // 3
            FmmPerspective.SERVICE_DELIVERY }); // 4
    
    public static FmmFrame TEAM = new FmmFrame(R.string.fmm_frame__teams, R.string.fmm_frame_definition__team, new FmmPerspective[] {
			FmmPerspective.STRATEGY_TEAMS,      // 1
            FmmPerspective.FLYWHEEL_TEAMS,      // 2
            FmmPerspective.FUNCTIONAL_TEAMS,    // 3
            FmmPerspective.GOVERNANCE_TEAMS }); // 4
    
    public static FmmFrame TRIBKN = new FmmFrame(R.string.fmm_frame__tribkn, R.string.fmm_frame_definition__tribkn, new FmmPerspective[] {  // SWAG
			FmmPerspective.DECKANGL,      // 1
            FmmPerspective.GOVERNANCE,    // 2
            FmmPerspective.COMMITMENTS,   // 3
            FmmPerspective.COMMUNITY });  // 4
    
    public static FmmFrame VELOCITY = new FmmFrame(R.string.fmm_frame__velocity, R.string.fmm_frame_definition__velocity, new FmmPerspective[] {
			FmmPerspective.DECKANGL,    // 1
            FmmPerspective.STORY,       // 2
            FmmPerspective.GOVERNANCE,  // 3
            FmmPerspective.COMMUNITY,   // 4
            FmmPerspective.NOTEBOOK}); // 5

    static {
        GcgFrame.VALUES.add(COMMITMENT);
        GcgFrame.VALUES.add(CONTEXT_FOR_WORKBENCH);
        GcgFrame.VALUES.add(CONTEXT_FOR_NODE);
        GcgFrame.VALUES.add(FSE);
        GcgFrame.VALUES.add(QUALITY);
        GcgFrame.VALUES.add(TEAM);
        GcgFrame.VALUES.add(TRIBKN);
        GcgFrame.VALUES.add(VELOCITY);
    }

    private static final ArrayList<FmmFrame> FMM_VALUES = new ArrayList<FmmFrame>();

    public static ArrayList<FmmFrame> getFmmValues() {
        if(FmmFrame.FMM_VALUES.size() == 0) {
            for(GcgFrame theGcgFrame : GcgFrame.values()) {
                FmmFrame.FMM_VALUES.add((FmmFrame) theGcgFrame);
            }
        }
        return FMM_VALUES;
    }

    public static FmmFrame getFmmObjectForName(String aName) {
        return (FmmFrame) getObjectForName(aName);
    }

    private FmmFrame(int aNameResourceId, int aDictionaryDefinitionResourceId, GcgPerspective[] aGcgPerspectiveArray) {
       super(aNameResourceId, aDictionaryDefinitionResourceId, aGcgPerspectiveArray);
    }

    private FmmFrame(int aNameResourceId, int aDictionaryDefinitionResourceId, int aHeadingResourceId, GcgPerspective[] aGcgPerspectiveArray) {
        super(aNameResourceId, aDictionaryDefinitionResourceId, aHeadingResourceId, aGcgPerspectiveArray);
        
    }
}
