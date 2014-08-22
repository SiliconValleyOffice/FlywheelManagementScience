/* @(#)GcgActivityStatusIndicator.java
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

package com.flywheelms.library.gcg.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.SystemClock;

import com.flywheelms.library.R;

public class GcgActivityStatusIndicator extends GcgSurfaceView {
	
	private static final ArrayList<Bitmap> greenBitmapList = new ArrayList<Bitmap>();
	private static final ArrayList<Bitmap> magentaBitmapList = new ArrayList<Bitmap>();
	private static final ArrayList<Bitmap> orangeBitmapList = new ArrayList<Bitmap>();
	private static final ArrayList<Bitmap> blueBitmapList = new ArrayList<Bitmap>();
	private static final ArrayList<Bitmap> yellowBitmapList = new ArrayList<Bitmap>();
	private ArrayList<Bitmap> activeBitmapList;
	private Thread animationThread;
	private static final int iteration__STANDARD_MAX = 999;
	private static final int iteration__BLUE_MAX = 4;
	private static final int iteration__PAUSE_DURATION = 85;
	private static final int iteration__CYCLE_SIZE = 4;
	private static final int iteration__CYCLE_PAUSE_DURATION = 85;
	private int maximumIterations = iteration__STANDARD_MAX;
	private boolean stopThread = false;

	public GcgActivityStatusIndicator(Context aContext) {
		super(aContext);
		initializeBitmapLists();
	}
	
	/**
	 * may want to use Android animation lists.
	 * They have already been created in anim.activity_status_indicator__green and etc.
	 */
	private void initializeBitmapLists() {
		if(GcgActivityStatusIndicator.orangeBitmapList.size() > 0) {
			return;
		}
		GcgActivityStatusIndicator.greenBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__green__1));
		GcgActivityStatusIndicator.greenBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__green__2));
		GcgActivityStatusIndicator.greenBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__green__3));
		GcgActivityStatusIndicator.greenBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__green__4));
		GcgActivityStatusIndicator.greenBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__green__5));
		GcgActivityStatusIndicator.greenBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__green__6));
		GcgActivityStatusIndicator.greenBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__green__7));
		GcgActivityStatusIndicator.greenBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__green__8));

		GcgActivityStatusIndicator.magentaBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__magenta__1));
		GcgActivityStatusIndicator.magentaBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__magenta__2));
		GcgActivityStatusIndicator.magentaBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__magenta__3));
		GcgActivityStatusIndicator.magentaBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__magenta__4));
		GcgActivityStatusIndicator.magentaBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__magenta__5));
		GcgActivityStatusIndicator.magentaBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__magenta__6));
		GcgActivityStatusIndicator.magentaBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__magenta__7));
		GcgActivityStatusIndicator.magentaBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__magenta__8));

		GcgActivityStatusIndicator.orangeBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__orange__1));
		GcgActivityStatusIndicator.orangeBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__orange__2));
		GcgActivityStatusIndicator.orangeBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__orange__3));
		GcgActivityStatusIndicator.orangeBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__orange__4));
		GcgActivityStatusIndicator.orangeBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__orange__5));
		GcgActivityStatusIndicator.orangeBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__orange__6));
		GcgActivityStatusIndicator.orangeBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__orange__7));
		GcgActivityStatusIndicator.orangeBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__orange__8));

		GcgActivityStatusIndicator.blueBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__blue__1));
		GcgActivityStatusIndicator.blueBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__blue__2));
		GcgActivityStatusIndicator.blueBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__blue__3));
		GcgActivityStatusIndicator.blueBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__blue__4));
		GcgActivityStatusIndicator.blueBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__blue__5));
		GcgActivityStatusIndicator.blueBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__blue__6));
		GcgActivityStatusIndicator.blueBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__blue__7));
		GcgActivityStatusIndicator.blueBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__blue__8));

		GcgActivityStatusIndicator.yellowBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__yellow__1));
		GcgActivityStatusIndicator.yellowBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__yellow__2));
		GcgActivityStatusIndicator.yellowBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__yellow__3));
		GcgActivityStatusIndicator.yellowBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__yellow__4));
		GcgActivityStatusIndicator.yellowBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__yellow__5));
		GcgActivityStatusIndicator.yellowBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__yellow__6));
		GcgActivityStatusIndicator.yellowBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__yellow__7));
		GcgActivityStatusIndicator.yellowBitmapList.add(BitmapFactory.decodeResource(getResources(), R.drawable.gcg__activity_status_indicator__yellow__8));
	}
	
	public void startGreenAnimation() {
		this.maximumIterations = iteration__STANDARD_MAX;
		startAnimation(GcgActivityStatusIndicator.greenBitmapList);
	}
	
	public void startMagentaAnimation() {
		this.maximumIterations = iteration__STANDARD_MAX;
		startAnimation(GcgActivityStatusIndicator.magentaBitmapList);
	}
	
	public void startOrangeAnimation() {
		this.maximumIterations = iteration__STANDARD_MAX;
		startAnimation(GcgActivityStatusIndicator.orangeBitmapList);
	}
	
	public void startBlueAnimation(boolean aBoolean) {
		if(aBoolean) {
			this.maximumIterations = iteration__BLUE_MAX + 2;
		} else {
			this.maximumIterations = iteration__BLUE_MAX;
		}
		startAnimation(GcgActivityStatusIndicator.blueBitmapList);
	}
	
	public void startYellowAnimation() {
		this.maximumIterations = iteration__STANDARD_MAX;
		startAnimation(GcgActivityStatusIndicator.yellowBitmapList);
	}
	
	public void startAnimation(ArrayList<Bitmap> aBitmapList) {
//		stopAnimation();
		this.activeBitmapList = aBitmapList;
		this.animationThread = new Thread() {

			@Override
			public void run() {
				boolean isClockwise = true;
				int theCycleCount = 0;
				int theIterationCount = 1;
				while(GcgActivityStatusIndicator.this.stopThread != true) {
					for(int theIndex = isClockwise ? 0 : GcgActivityStatusIndicator.this.activeBitmapList.size() - 1;
							theIndex > -1 && theIndex < GcgActivityStatusIndicator.this.activeBitmapList.size() && (GcgActivityStatusIndicator.this.stopThread != true);
							theIndex = isClockwise ? theIndex + 1 : theIndex - 1 ) {
						drawBitmap(GcgActivityStatusIndicator.this.activeBitmapList.get(theIndex));
						SystemClock.sleep(GcgActivityStatusIndicator.iteration__PAUSE_DURATION);
					}
					if(++theIterationCount > GcgActivityStatusIndicator.this.maximumIterations) {
						break;
					}
					++theCycleCount;
					if(theCycleCount > GcgActivityStatusIndicator.iteration__CYCLE_SIZE) {
						if(isClockwise) {
							drawBitmap(GcgActivityStatusIndicator.this.activeBitmapList.get(0));
							SystemClock.sleep(GcgActivityStatusIndicator.iteration__PAUSE_DURATION);
						}
						isClockwise = isClockwise ? false : true;
						theCycleCount = 0;
						SystemClock.sleep(GcgActivityStatusIndicator.iteration__CYCLE_PAUSE_DURATION);
					}
				}
				GcgActivityStatusIndicator.this.stopThread = false;
				GcgActivityStatusIndicator.super.clear();
			}
		};
		this.animationThread.start();
	}

	public void stopAnimation() {
		this.stopThread = true;
	}

}
