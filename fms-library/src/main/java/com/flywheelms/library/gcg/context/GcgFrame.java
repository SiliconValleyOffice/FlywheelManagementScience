/* @(#)GcgFrame.java
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

package com.flywheelms.library.gcg.context;

import com.flywheelms.library.gcg.GcgApplication;
import com.flywheelms.library.gcg.interfaces.GcgPerspective;

import java.util.ArrayList;
import java.util.Arrays;

public class GcgFrame {

    protected static final ArrayList<GcgFrame> VALUES = new ArrayList<GcgFrame>();

    public static ArrayList<GcgFrame> values() {
        return GcgFrame.VALUES;
    }

    public static GcgFrame getObjectForName(String aName) {
        GcgFrame theGcgFrame = null;
        for(GcgFrame theInstance : GcgFrame.VALUES) {
            if(theInstance.getName().equals(aName)) {
                theGcgFrame = theInstance;
                break;
            }
        }
        return theGcgFrame;
    }

    private final int nameResourceId;
    private String name;
    private final int headingResourceId;
    private CharSequence heading;
    private ArrayList<GcgPerspective> gcgPerspectiveList;

    protected GcgFrame(int aNameResourceId, GcgPerspective[] anGcgPerspectiveArray) {
        this.nameResourceId = aNameResourceId;
        this.headingResourceId = aNameResourceId;
        this.gcgPerspectiveList = new ArrayList<GcgPerspective>(Arrays.asList(anGcgPerspectiveArray));
    }

    protected GcgFrame(int aNameResourceId, int aHeadingResourceId, GcgPerspective[] anGcgPerspectiveArray) {
        this.nameResourceId = aNameResourceId;
        this.headingResourceId = aHeadingResourceId;
        this.gcgPerspectiveList = new ArrayList<GcgPerspective>(Arrays.asList(anGcgPerspectiveArray));
    }

    public int getNameResourceId() {
        return this.nameResourceId;
    }

    public int getHeadingResourceId() {
        return this.headingResourceId;
    }

    public CharSequence getHeadingCharSequence() {
        if(this.heading == null) {
            this.heading = GcgApplication.getAppResources().getText(getHeadingResourceId());
        }
        return this.heading;
    }

    public String getName() {
        if(this.name == null) {
            this.name = GcgApplication.getAppResources().getString(this.nameResourceId);
        }
        return this.name;
    }

    public ArrayList<GcgPerspective> getPerspectiveList() {
        return this.gcgPerspectiveList;
    }

    public int getPerspectiveMenuItemPosition(GcgPerspective aPerspective) {
        // TODO - may need to return the goofy view flipper order
        return this.gcgPerspectiveList.indexOf(aPerspective);
    }

    public GcgPerspective getPerspectiveAt(int aPerspectiveIndex) {
        // TODO - may need to return the perspective based upon goofy view flipper order
        return this.gcgPerspectiveList.get(aPerspectiveIndex);
    }
}
