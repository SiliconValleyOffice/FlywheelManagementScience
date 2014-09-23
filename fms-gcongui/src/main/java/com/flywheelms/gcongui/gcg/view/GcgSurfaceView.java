/* @(#)GcgSurfaceView.java
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

package com.flywheelms.gcongui.gcg.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

import com.flywheelms.gcongui.gcg.helper.GcgHelper;

public class GcgSurfaceView extends SurfaceView {
	
	private static final int default__DP_VIEW_WIDTH = 74;
	private static final int default__DP_VIEW_HEIGHT = 32;
	protected Canvas canvas;
	protected SurfaceHolder surfaceHolder;
	protected Paint paint;

	public GcgSurfaceView(Context aContext) {
		this(aContext, default__DP_VIEW_WIDTH, default__DP_VIEW_HEIGHT);
	}

	public GcgSurfaceView(Context aContext, int aDpViewWidth, int aDpViewHeight) {
		super(aContext);
		int theWidthNumberOfPixels = GcgHelper.getPixelsForDp(aContext, aDpViewWidth);
		int theHeightNumberOfPixels = GcgHelper.getPixelsForDp(aContext, aDpViewHeight);
		setLayoutParams(new ViewGroup.LayoutParams(theWidthNumberOfPixels, theHeightNumberOfPixels));
		setZOrderOnTop(true);
		this.surfaceHolder = getHolder();
		this.surfaceHolder.setFormat(PixelFormat.TRANSPARENT);
//		this.surfaceHolder.setFormat(PixelFormat.RGBA_8888);
		this.paint = new Paint(Paint.FILTER_BITMAP_FLAG | Paint.DITHER_FLAG | Paint.ANTI_ALIAS_FLAG);
	}
	
	public void setSizeInDp(int anXvalue, int aYvalue) {
		setLayoutParams(new ViewGroup.LayoutParams(
				GcgHelper.getPixelsForDp(getContext(), anXvalue),
				GcgHelper.getPixelsForDp(getContext(), aYvalue) ));
	}
	
	public void clear() {
		this.canvas = this.surfaceHolder.lockCanvas();
		if(this.canvas != null) {
			this.canvas.drawColor( 0, PorterDuff.Mode.CLEAR );
			draw(this.canvas);
			this.surfaceHolder.unlockCanvasAndPost(this.canvas);
		} // else surface is not yet ready
	}

	public void drawBitmap(Bitmap aBitmap) {
		this.canvas = this.surfaceHolder.lockCanvas();
		if(this.canvas != null) {
			this.canvas.drawColor( 0, PorterDuff.Mode.CLEAR );
	        this.canvas.drawBitmap(aBitmap, 0, 0, this.paint);			
	        this.surfaceHolder.unlockCanvasAndPost(this.canvas);
		}
	}

}
