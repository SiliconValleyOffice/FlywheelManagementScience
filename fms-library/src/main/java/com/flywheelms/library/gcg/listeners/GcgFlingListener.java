/* @(#)GcgFlingableListener.java
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

package com.flywheelms.library.gcg.listeners;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.interfaces.GcgFlingController;

public class GcgFlingListener extends SimpleOnGestureListener {

	private final int SWIPE_MIN_DISTANCE = GcgHelper.getSwipeMinimumDistance();
	private final int SWIPE_MAX_OFF_PATH = GcgHelper.getSwipeMaxOffPath();
	private final int SWIPE_THRESHOLD_VELOCITY = GcgHelper.getSwipeMinimumVelocity();
	private final GcgFlingController flingController;

	public GcgFlingListener(GcgFlingController aFlingController) {
		this.flingController = aFlingController;
	}

	@Override
	public boolean onFling(MotionEvent aFirstMotionEvent, MotionEvent aSecondMotionEvent, float aVelocityX, float aVelocityY) {
		float theFirstEventX = aFirstMotionEvent.getX();
		float theSecondEventX = aSecondMotionEvent.getX();
		float theFirstEventY = aFirstMotionEvent.getY();
		float theSecondEventY = aSecondMotionEvent.getY();
		if (Math.abs(theFirstEventY - theSecondEventY) > this.SWIPE_MAX_OFF_PATH) {
		    return false;
		}
		if(theFirstEventX - theSecondEventX > this.SWIPE_MIN_DISTANCE && Math.abs(aVelocityX) > this.SWIPE_THRESHOLD_VELOCITY) {
			this.flingController.onFlingLeft();
			return true;
		} else if (theSecondEventX - theFirstEventX > this.SWIPE_MIN_DISTANCE && Math.abs(aVelocityX) >  this.SWIPE_THRESHOLD_VELOCITY) {
			this.flingController.onFlingRight();
			return true;
		}
		if(theFirstEventY - theSecondEventY > this.SWIPE_MIN_DISTANCE && Math.abs(aVelocityY) > this.SWIPE_THRESHOLD_VELOCITY) {
			this.flingController.onFlingUp();
			return true;
		} else if (theSecondEventY - theFirstEventY > this.SWIPE_MIN_DISTANCE && Math.abs(aVelocityY) > this.SWIPE_THRESHOLD_VELOCITY) {
			this.flingController.onFlingDown();
			return true;
		}
		return false;
	}
}