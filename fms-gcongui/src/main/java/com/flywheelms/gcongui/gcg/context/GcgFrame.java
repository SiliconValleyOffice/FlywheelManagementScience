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

package com.flywheelms.gcongui.gcg.context;

import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;

import java.util.ArrayList;
import java.util.Arrays;

public class GcgFrame implements GcgGuiable {

    protected static final ArrayList<GcgFrame> VALUES = new ArrayList<GcgFrame>();
    private static ArrayList<GcgGuiable> fmmGuiableList;

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
    private int dictionaryDefinitionResourceId;
    private String dictionaryDefinitionString;
    private final int headingResourceId;
    private CharSequence heading;
    private ArrayList<GcgPerspective> gcgPerspectiveList;

    protected GcgFrame(int aNameResourceId, int aDictionaryDefinitionResourceId, GcgPerspective[] anGcgPerspectiveArray) {
        this.nameResourceId = aNameResourceId;
        this.dictionaryDefinitionResourceId = aDictionaryDefinitionResourceId;
        this.headingResourceId = aNameResourceId;
        this.gcgPerspectiveList = new ArrayList<GcgPerspective>(Arrays.asList(anGcgPerspectiveArray));
    }

    protected GcgFrame(int aNameResourceId, int aDictionaryDefinitionResourceId, int aHeadingResourceId, GcgPerspective[] anGcgPerspectiveArray) {
        this.nameResourceId = aNameResourceId;
        this.dictionaryDefinitionResourceId = aDictionaryDefinitionResourceId;
        this.headingResourceId = aHeadingResourceId;
        this.gcgPerspectiveList = new ArrayList<GcgPerspective>(Arrays.asList(anGcgPerspectiveArray));
    }

    public static ArrayList<GcgGuiable> getGcgGuiableList() {
        if(GcgFrame.fmmGuiableList == null) {
            GcgFrame.fmmGuiableList = new ArrayList<GcgGuiable>();
            GcgFrame.fmmGuiableList.addAll(values());
        }
        return fmmGuiableList;
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
        return this.gcgPerspectiveList.indexOf(aPerspective);
    }

    public GcgPerspective getPerspectiveAt(int aPerspectiveIndex) {
        return this.gcgPerspectiveList.get(aPerspectiveIndex);
    }

    public static int getPerspectiveIndexForViewFlipperIndex(int aPerspectiveCount, int aFrameViewIndex) {  // correct for goofy view flipper order
        int theAdjustedIndex = 0;
        if(aFrameViewIndex != 0) {
            theAdjustedIndex = aPerspectiveCount - aFrameViewIndex;
        }
        return theAdjustedIndex;
    }

    @Override
    public String getLabelText() {
        return GcgApplication.getAppResources().getString(R.string.gcg__frame);
    }

    @Override
    public Drawable getLabelDrawable() {
        return GcgApplication.getAppResources().getDrawable(getLabelDrawableResourceId());
    }

    @Override
    public int getLabelDrawableResourceId() {
        return R.drawable.gcg__frame;
    }

    @Override
    public String getDataText() {
        return getName();
    }

    @Override
    public Drawable getDataDrawable() {
        return GcgApplication.getAppResources().getDrawable(getDataDrawableResourceId());
    }

    @Override
    public int getDataDrawableResourceId() {
        return R.drawable.gcg__null_drawable;
    }

    public String getDictionaryDefinitionString() {
        if(this.dictionaryDefinitionString == null) {
            this.dictionaryDefinitionString = GcgApplication.getAppResources().getString(getDictionaryDefinitionResourceId());
        }
        return this.dictionaryDefinitionString;
    }

    public int getDictionaryDefinitionResourceId() {
        return this.dictionaryDefinitionResourceId;
    }

    @Override
    public String toString() {
        return getName();
    }
}
