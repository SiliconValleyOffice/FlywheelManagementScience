/* @(#)GcgPerspective.java
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

package com.flywheelms.gcongui.gcg.interfaces;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.gcg.GcgApplication;

import java.util.ArrayList;

public class GcgPerspective implements GcgGuiable {

    protected static final ArrayList<GcgPerspective> VALUES = new ArrayList<GcgPerspective>();
    private static ArrayList<GcgGuiable> gcgGuiableList;

    public static ArrayList<GcgPerspective> values() {
        return GcgPerspective.VALUES;
    }

    public static GcgPerspective getObjectForName(String aName) {
        GcgPerspective theGcgPerspective = null;
        for(GcgPerspective theInstance : GcgPerspective.VALUES) {
            if(theInstance.getName().equals(aName)) {
                theGcgPerspective = theInstance;
                break;
            }
        }
        return theGcgPerspective;
    }

    private int nameStringResourceId;
    private String name;
    private int dictionaryDefinitionResourceId;
    private String dictionaryDefinitionString;
    private int iconDrawableResourceId;
    private Drawable iconDrawable;
    private int buttonResourceId;
    private Button button;
    private int menuSequence;
    private boolean enabled;
    private boolean isShowPerspective;
    private boolean showNoOpPrototype = false;
    private int prototypeResourceId = 0;

    protected GcgPerspective(
            int aNameStringResourceId,
            int aDictionaryDefinitionResourceId,
            int anIconDrawableResourceId,
            int aButtonResourceId,
            int aMenuSequence,
            boolean bEnabled,
            boolean bShowPerspective,
            boolean bShowNoOpPrototype ) {
        this.nameStringResourceId = aNameStringResourceId;
        this.name = GcgApplication.getAppResources().getString(this.nameStringResourceId);
        this.dictionaryDefinitionResourceId = aDictionaryDefinitionResourceId;
        this.iconDrawableResourceId = anIconDrawableResourceId;
        this.iconDrawable = GcgApplication.getAppResources().getDrawable(this.iconDrawableResourceId);
        this.buttonResourceId = aButtonResourceId;
        this.menuSequence = aMenuSequence;
        this.enabled = bEnabled;
        this.isShowPerspective = bEnabled == true ? bShowPerspective : false;
        this.showNoOpPrototype = bShowNoOpPrototype;
    }

    public String getName() {
        return this.name;
    }

    public int getIconDrawableResourceId() {
        return this.iconDrawableResourceId;
    }

    public Drawable getIconDrawable() {
        return this.iconDrawable;
    }

    public int getButtonDrawableResourceId() {
        return this.buttonResourceId;
    }

    public Button getButton(View aView) {
        if(this.button == null) {
            this.button = (Button) aView.findViewById(this.buttonResourceId);
        }
        return this.button;
    }

    public int getMenuSequence() {
        return this.menuSequence;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean aBoolean) {
        this.enabled = aBoolean;
    }

    public boolean isShowPerspective() {
        return this.isShowPerspective;
    }

    public void setShowPerspective(boolean aBoolean) {
        this.isShowPerspective = aBoolean;
    }

    public boolean showNoOpPerspectivePrototype() {
        return this.showNoOpPrototype;
    }

    public Drawable getPrototypeDrawable() {
        return GcgApplication.getAppResources().getDrawable(this.prototypeResourceId);
    }

    public String toString() {
        return this.name;
    }

    public GcgPerspective getPerspectiveForName(String aPerspectiveName) {
        for (GcgPerspective thePerspective : GcgPerspective.values()) {
            if (thePerspective.toString().equals(aPerspectiveName)) {
                return thePerspective;
            }
        }
        return null;
    }

    public static ArrayList<GcgGuiable> getGcgGuiableList() {
        if(GcgPerspective.gcgGuiableList == null) {
            GcgPerspective.gcgGuiableList = new ArrayList<GcgGuiable>();
            GcgPerspective.gcgGuiableList.addAll(values());
        }
        return gcgGuiableList;
    }

    @Override
    public String getLabelText() {
        return GcgApplication.getAppResources().getString(R.string.gcg__perspective);
    }

    @Override
    public Drawable getLabelDrawable() {
        return null;
    }

    @Override
    public int getLabelDrawableResourceId() {
        return R.drawable.gcg__perspective;
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
        return getIconDrawableResourceId();
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
}
