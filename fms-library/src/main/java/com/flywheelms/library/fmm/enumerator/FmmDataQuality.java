/*
 * Copyright (C) 2012 by Steven D. Stamps
 *
 *              Trademarks & Copyrights
 *  Flywheel Management Science(TM), Flywheel Management Model(TM),
 *  Flywheel Story Editor(TM) and FlywheelMS(TM) are exclusive trademarks
 *  of Steven D. Stamps and may only be used freely for the purpose of
 *  identifying the unforked version of this software.  Subsequent forks
 *  may not use these trademarks.  All other rights are reserved.
 *
 *  DecKanGL (Decorated Kanban Glyph Language) and TribKn (Tribal Knowledge)
 *  are also exclusive trademarks of Steven D. Stamps.  These may be used
 *  freely within the unforked FlywheelMS application and documentation.
 *  All other rights are reserved.
 *
 *  gConGUI (game Controller Graphical User Interface) is an exclusive
 *  trademark of Steven D. Stamps.  This trademark may be used freely
 *  within the unforked FlywheelMS application and documentation.
 *  All other rights are reserved.
 *
 * * Trademark information is available at
 * * <http://www.flywheelms.com/trademarks>
 * *
 * * Flywheel Management Science(TM) is a copyrighted body of management
 * * metaphors, governance processes, and leadership techniques that is
 * * owned by Steven D. Stamps.  These copyrighted materials may be freely
 * * used, without alteration, by the community (users and developers)
 * * surrounding this GPL3-licensed software.  Additional copyright
 * * information is available at <http://www.flywheelms.org/copyrights>
 * *
 * *              GPL3 Software License
 * * This program is free software: you can use it, redistribute it and/or
 * * modify it under the terms of the GNU General Public License, version 3,
 * * as published by the Free Software Foundation. This program is distributed
 * * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 * * PURPOSE.  See the GNU General Public License for more details. You should
 * * have received a copy of the GNU General Public License, in a file named
 * * COPYING, along with this program.  If you cannot find your copy, see
 * * <http://www.gnu.org/licenses/gpl-3.0.html>.
 */

package com.flywheelms.library.fmm.enumerator;

import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.R;

public enum FmmDataQuality implements GcgGuiable {

    NONE (R.string.data_quality__none, R.drawable.data_quality__none),
    SUGGESTED (R.string.data_quality__suggested, R.drawable.data_quality__suggested),
    SWAG (R.string.data_quality__swag, R.drawable.data_quality__swag),
    PROPOSED (R.string.data_quality__proposed, R.drawable.data_quality__proposed),
    CONFIRMED (R.string.data_quality__confirmed, R.drawable.data_quality__confirmed);

    public static FmmDataQuality getObjectForName(String aName) {
        for(FmmDataQuality anFmmDataQuality : values()) {
            if(anFmmDataQuality.getName().equals(aName)) {
                return anFmmDataQuality;
            }
        }
        return null;
    }

    private int nameResourceId;
    private String name;
    private int drawableResourceId;

    private FmmDataQuality(int aNameResourceId, int aDrawableResourceId) {
        this.nameResourceId = aNameResourceId;
        this.name = GcgApplication.getAppResources().getString(this.nameResourceId);
        this.drawableResourceId = aDrawableResourceId;
    }

    @Override
    public String getLabelText() {
        return "Data Quality";
    }

    @Override
    public Drawable getLabelDrawable() {
        return null;
    }

    @Override
    public int getLabelDrawableResourceId() {
        return 0;
    }

    @Override
    public String getDataText() {
        return this.name;
    }

    @Override
    public Drawable getDataDrawable() {
        return GcgApplication.getAppResources().getDrawable(this.drawableResourceId);
    }

    @Override
    public int getDataDrawableResourceId() {
        return this.drawableResourceId;
    }

    public String getName() {
        return this.name;
    }


}
