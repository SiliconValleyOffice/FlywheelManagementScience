/* @(#)DecKanGlTribKnQualityNormalizedDescription.java
** 
** Copyright (C) 2012 by Steven D. Stamps
**
**             Trademarks & Copyrights
** Flywheel Management Science(TM), Flywheel Management Model(TM),
** Flywheel Story Editor(TM) and FlywheelMS(TM) are exclusive trademarks
** of Steven D. Stamps and may only be used freely for the purpose of
** identifying the unforked version of this software.  Subsequent forks
** may not use these trademarks.  All other rights are reserved.
** and FlywheelMS(TM) are exclusive trademarks of Steven D. Stamps
** and may only be used freely for the purpose of identifying the
** unforked version of this software.  Subsequent forks (if any) may
** not use these trademarks.  All other rights are reserved.
**
** DecKanGL (Decorated Kanban Glyph Language) and TribKn (Tribal Knowledge)
** are also exclusive trademarks of Steven D. Stamps.  These may be used
** freely within the unforked FlywheelMS application and documentation.
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

package com.flywheelms.library.deckangl.glyph;

import android.graphics.drawable.Drawable;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.GcgApplication;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class DecKanGlTribKnQualityNormalizedDescription {

    protected static String labelText = GcgApplication.getAppResources().getString(R.string.deckangl__tribkn_quality__description);
    protected static int labelDrawableResourceId = R.drawable.gcg__null_drawable;
    protected static Drawable labelDrawable = GcgApplication.getAppResources().getDrawable(DecKanGlTribKnQualityNormalizedDescription.labelDrawableResourceId);

    protected static Class<? extends DecKanGlTribKnQualityNormalizedDescription> subClass;  // must initialize in static block of sub-class

    private static Method getImplementationObjectForNameMethod;  // Must implement in sub-class
    private static Method getImplementationObjectForNameMethod() {
        if(DecKanGlTribKnQualityNormalizedDescription.getImplementationObjectForNameMethod == null) {
            try {
                DecKanGlTribKnQualityNormalizedDescription.getImplementationObjectForNameMethod =
                        DecKanGlTribKnQualityNormalizedDescription.subClass.getMethod("getImplementationObjectForName", String.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            }
        }
        return DecKanGlTribKnQualityNormalizedDescription.getImplementationObjectForNameMethod;
    }

    public static DecKanGlTribKnQualityNormalizedDescription getObjectForName(String aName) {
        Object theObject = null;
        try {
            theObject = getImplementationObjectForNameMethod().invoke(null, aName);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (DecKanGlTribKnQualityNormalizedDescription) theObject;
    }

    private static Method getStaticSubclassInstanceMethod;  // Must implement in sub-class
    private static Method getStaticSubclassInstanceMethod() {
        if(DecKanGlTribKnQualityNormalizedDescription.getStaticSubclassInstanceMethod == null) {
            try {
                DecKanGlTribKnQualityNormalizedDescription.getStaticSubclassInstanceMethod =
                        DecKanGlTribKnQualityNormalizedDescription.subClass.getMethod("getStaticSubclassInstance");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            }
        }
        return DecKanGlTribKnQualityNormalizedDescription.getStaticSubclassInstanceMethod;
    }

    public static DecKanGlTribKnQualityNormalizedDescription getStaticInstance() {
        Object theObject = null;
        try {
            theObject = getStaticSubclassInstanceMethod().invoke(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (DecKanGlTribKnQualityNormalizedDescription) theObject;
    }

    private static Method getImplementationValuesMethod;  // Must implement in sub-class
    private static Method getImplementationValuesMethod() {
        if(DecKanGlTribKnQualityNormalizedDescription.getImplementationValuesMethod == null) {
            try {
                DecKanGlTribKnQualityNormalizedDescription.getImplementationValuesMethod =
                        DecKanGlTribKnQualityNormalizedDescription.subClass.getMethod("getImplementationValues");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            }
        }
        return DecKanGlTribKnQualityNormalizedDescription.getImplementationValuesMethod;
    }

    public static ArrayList<DecKanGlTribKnQualityNormalizedDescription> values() {
        Object theObject = null;
        try {
            theObject = getImplementationValuesMethod().invoke(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return (ArrayList<DecKanGlTribKnQualityNormalizedDescription>) theObject;
    }

    protected int nameResourceId;
    protected String name;

    public DecKanGlTribKnQualityNormalizedDescription(int aNameResourceId) {
        this.nameResourceId = aNameResourceId;
        this.name = GcgApplication.getAppResources().getString(this.nameResourceId);
    }

    public String getName() {
        return this.name;
    }

    public Drawable getDataDrawable() {
        return GcgApplication.getContext().getResources().getDrawable(R.drawable.gcg__null_drawable);
    }

    public String getDataText() {
        return getName();
    }

    public String getLabelText() {
        return DecKanGlTribKnQualityNormalizedDescription.labelText;
    }

    public Drawable getLabelDrawable() {
        return DecKanGlTribKnQualityNormalizedDescription.labelDrawable;
    }

    public int getLabelDrawableResourceId() {
        return DecKanGlTribKnQualityNormalizedDescription.labelDrawableResourceId;
    }

    public int getDataDrawableResourceId() {
        return R.drawable.gcg__null_drawable;
    }
}
