/* @(#)DecKanGlHelper.java
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

package com.flywheelms.gcongui.deckangl;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;

import com.flywheelms.gcongui.gcg.GcgApplication;

public class DecKanGlHelper {

	private static final BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
	static {
		DecKanGlHelper.bitmapOptions.inScaled = false;
		DecKanGlHelper.bitmapOptions.inDensity = Bitmap.DENSITY_NONE;
		DecKanGlHelper.bitmapOptions.inMutable = true;
		DecKanGlHelper.bitmapOptions.inPreferredConfig = Bitmap.Config.ARGB_8888;
	}

	public static Context getContext() {
		return GcgApplication.getContext();
	}

	public static Resources getResources() {
		return GcgApplication.getContext().getResources();
	}
	
	public static LayoutInflater getLayoutInflator() {
		return (LayoutInflater)getContext().getSystemService
			      (Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public static BitmapFactory.Options getBitmapOptions() {
		return DecKanGlHelper.bitmapOptions;
	}
	
	public static BitmapDrawable getNounStateBitmapDrawable(int aResourceId) {
		BitmapFactory.Options theBitmapOptions = new BitmapFactory.Options();
		theBitmapOptions.inScaled = false;
		Bitmap theBitmap = BitmapFactory.decodeResource(
					DecKanGlHelper.getResources(),
					aResourceId,
					theBitmapOptions);
		return new BitmapDrawable(DecKanGlHelper.getResources(), theBitmap);
	}
	
	public static Bitmap bitmapFromDrawable(Drawable aDrawable) {
	    if (aDrawable instanceof BitmapDrawable) {
	        return ((BitmapDrawable)aDrawable).getBitmap();
	    }
	    Bitmap theBitmap = Bitmap.createBitmap(aDrawable.getIntrinsicWidth(), aDrawable.getIntrinsicHeight(), Config.ARGB_8888);
	    Canvas theCanvas = new Canvas(theBitmap); 
	    aDrawable.setBounds(0, 0, theCanvas.getWidth(), theCanvas.getHeight());
	    aDrawable.draw(theCanvas);
	    return theBitmap;
	}
	
}
