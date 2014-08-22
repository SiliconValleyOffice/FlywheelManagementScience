/* @(#)DecKanGlGlyphCanvas.java
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

package com.flywheelms.library.deckangl.glyph;

import java.util.ArrayList;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

import com.flywheelms.library.deckangl.DecKanGlHelper;
import com.flywheelms.library.deckangl.enumerator.DecKanGlAnnotatedGlyphSize;
import com.flywheelms.library.deckangl.enumerator.DecKanGlDecoratedGlyphSize;
import com.flywheelms.library.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.library.deckangl.interfaces.DecKanGlDecorator;

/*
 * Responsible for generating the DecKanGL glyphs
 */
public class DecKanGlGlyphCanvas {
	
	private final DecKanGlGlyph decKanGlGlyph;
	private DecKanGlDecoratedGlyphSize decoratedGlyphSize;
	private DecKanGlAnnotatedGlyphSize  annotatedGlyphSize;

	public DecKanGlGlyphCanvas(DecKanGlGlyph aDecKanGlGlyph) {
		this.decKanGlGlyph = aDecKanGlGlyph;
	}
	
	public DecKanGlDecoratedGlyphSize getDecoratedGlyphSize() {
		return this.decoratedGlyphSize;
	}
	
	public void setDecoratedGlyphSize(DecKanGlDecoratedGlyphSize aGlyphSize) {
		this.decoratedGlyphSize = aGlyphSize;
	}
	
	public DecKanGlAnnotatedGlyphSize getAnnotatedGlyphSize() {
		return this.annotatedGlyphSize;
	}
	
	public void setAnnotatedGlyphSize(DecKanGlAnnotatedGlyphSize aGlyphSize) {
		this.annotatedGlyphSize = aGlyphSize;
	}
	
	public HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> getDecoratorMap() {
		return this.decKanGlGlyph.getDecoratorMap();
	}
	
	
	public ArrayList<String> getCanvasZoneAnnotations(DecKanGlCanvasZone aCanvasZone) {
		ArrayList<String> theStringArrayList = new ArrayList<String>();
		if (DecKanGlDictionary.getInstance().decoratorsUseZone(aCanvasZone)) {
			switch(aCanvasZone) {
				case BOTTOM_ZONE:
					theStringArrayList.add(getAnnotation(getDecoratorForLocation(DecKanGlDecoratorCanvasLocation.bottom_zone_LEFT)));
					theStringArrayList.add(getAnnotation(getDecoratorForLocation(DecKanGlDecoratorCanvasLocation.bottom_zone_MIDDLE)));
					theStringArrayList.add(getAnnotation(getDecoratorForLocation(DecKanGlDecoratorCanvasLocation.bottom_zone_RIGHT)));
					break;
				case LEFT_ZONE:
					theStringArrayList.add(getAnnotation(getDecoratorForLocation(DecKanGlDecoratorCanvasLocation.left_zone_TOP)));
					theStringArrayList.add(getAnnotation(getDecoratorForLocation(DecKanGlDecoratorCanvasLocation.left_zone_MIDDLE)));
					theStringArrayList.add(getAnnotation(getDecoratorForLocation(DecKanGlDecoratorCanvasLocation.left_zone_BOTTOM)));
					break;
				case RIGHT_ZONE:
					theStringArrayList.add(getAnnotation(getDecoratorForLocation(DecKanGlDecoratorCanvasLocation.right_zone_TOP)));
					theStringArrayList.add(getAnnotation(getDecoratorForLocation(DecKanGlDecoratorCanvasLocation.right_zone_MIDDLE)));
					theStringArrayList.add(getAnnotation(getDecoratorForLocation(DecKanGlDecoratorCanvasLocation.right_zone_BOTTOM)));
					break;
				case TOP_ZONE:
					theStringArrayList.add(getAnnotation(getDecoratorForLocation(DecKanGlDecoratorCanvasLocation.top_zone_LEFT)));
					theStringArrayList.add(getAnnotation(getDecoratorForLocation(DecKanGlDecoratorCanvasLocation.top_zone_MIDDLE)));
					theStringArrayList.add(getAnnotation(getDecoratorForLocation(DecKanGlDecoratorCanvasLocation.top_zone_RIGHT)));
					break;
				default:
					// TODO - throw a nasty exception about bad programmers!  ;-)
			}
		}
		return theStringArrayList;
	}

	public DecKanGlDecorator getDecorator(String aDecoratorName) {
		return getDecoratorMap().get(aDecoratorName);
	}

	public DecKanGlElementNoun getDecKanGlElementNoun() {
		return this.decKanGlGlyph.getElementNoun();
	}

	private static String getAnnotation(DecKanGlDecorator aDecorator) {
		if (aDecorator == null) {
			return "";
		}
		return aDecorator.getAnnotation();
	}

	private DecKanGlDecorator getDecoratorForLocation(DecKanGlDecoratorCanvasLocation aCanvasLocation) {
		for (DecKanGlDecorator theDecKanGlDecorator : this.decKanGlGlyph.getDecoratorMap().values()) {
			if (theDecKanGlDecorator.getDecKanGlElementNounQualityMetric().getLogicalCanvasLocation() == aCanvasLocation) {
				return theDecKanGlDecorator;
			}
		}
		return null;
	}
	
	public int getNodeQualityIndex() {
		int theNodeQualityIndex = 0;
		for (DecKanGlDecorator theDecKanGlDecorator : getDecoratorMap().values()) {
			theNodeQualityIndex += theDecKanGlDecorator.getNounQualityIndex();
		}
		return theNodeQualityIndex;
	}
	
	public String getNodeQualityIndexString() {
		return "<< " + getNodeQualityIndex() + " >>";
	}
	
	float getNounStateImageCanvasPositionX() {
		float theFloat =
				this.decoratedGlyphSize.getLeftRightZoneWidth() +
				this.decoratedGlyphSize.getNounStatusFrameWidth() +
				this.decoratedGlyphSize.getNounStatusMargin();
		return theFloat;
	}

	float getNounStateImageCanvasPositionY() {
		float theFloat =
				this.decoratedGlyphSize.getTopBottomZoneHeight() +
				this.decoratedGlyphSize.getNounStatusFrameWidth() +
				this.decoratedGlyphSize.getNounStatusMargin();
		return theFloat;
	}

	public Bitmap drawDecoratedNounBitmapDrawable(DecKanGlDecoratedGlyphSize aGlyphSize) {
		this.decoratedGlyphSize = aGlyphSize;
		Bitmap theDecoratedNounBitmap = BitmapFactory.decodeResource(DecKanGlHelper.getResources(), this.decoratedGlyphSize.getBackgroundResourceId(), DecKanGlHelper.getBitmapOptions());
		Canvas theCanvas = new Canvas(theDecoratedNounBitmap);
		Bitmap theNounStateBitmap = this.decKanGlGlyph.getNounStateDrawable(this.decoratedGlyphSize.getNounStateDrawableSize()).getBitmap();
		theCanvas.drawBitmap(
				theNounStateBitmap,
				getNounStateImageCanvasPositionX(),
				getNounStateImageCanvasPositionY(),
				null );
		for(DecKanGlDecorator theDecorator : this.decKanGlGlyph.getDecoratorMap().values()) {
			theCanvas.drawBitmap(
					theDecorator.getBitmap(this.decoratedGlyphSize),
					theDecorator.getCanvasPositionX(this.decoratedGlyphSize),
					theDecorator.getCanvasPositionY(this.decoratedGlyphSize),
					null );
		}
		return theDecoratedNounBitmap;
	}

	public Bitmap drawAnnotatedGlyphBitmapDrawable(DecKanGlAnnotatedGlyphSize aGlyphSize) {
		this.annotatedGlyphSize = aGlyphSize;
		Bitmap theAnnotatedGlyphBitmap = BitmapFactory.decodeResource(DecKanGlHelper.getResources(), this.annotatedGlyphSize.getBackgroundResourceId(), DecKanGlHelper.getBitmapOptions());
		Canvas theCanvas = new Canvas(theAnnotatedGlyphBitmap);
		Bitmap theDecoratedNounBitmap = this.decKanGlGlyph.getDecoratedNounBitmap(aGlyphSize.getDecKanGlDecoratedGlyphSize());
		theCanvas.drawBitmap(
				theDecoratedNounBitmap,
				getDecoratedNounPositionX(theAnnotatedGlyphBitmap, theDecoratedNounBitmap),
				getDecoratedNounPositionY(theAnnotatedGlyphBitmap, theDecoratedNounBitmap),
				null );
		ArrayList<String> theBottomZoneAnnotationList = getCanvasZoneAnnotations(DecKanGlCanvasZone.BOTTOM_ZONE);
		ArrayList<String> theLeftZoneAnnotationList = getCanvasZoneAnnotations(DecKanGlCanvasZone.LEFT_ZONE);
		ArrayList<String> theRightZoneAnnotationList = getCanvasZoneAnnotations(DecKanGlCanvasZone.RIGHT_ZONE);
		ArrayList<String> theTopZoneAnnotationList = getCanvasZoneAnnotations(DecKanGlCanvasZone.TOP_ZONE);
		// draw quality glyph
		theCanvas.drawBitmap(
				DecKanGlAnnotatedGlyphSize.getNodeQualityGlyphBitmap(getNodeQualityIndex()),
				this.annotatedGlyphSize.getNounQualityGlyphLeftMargin(),
				this.annotatedGlyphSize.getNounQualityGlyphTopMargin(),
				null );
		// setup for writing
		Paint thePaint = new Paint();
		thePaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		thePaint.setXfermode(null);  // reset
		thePaint.setAntiAlias(true);
		thePaint.setStyle(Paint.Style.FILL);
		thePaint.setStrokeWidth(1);
		// write node quality index
		thePaint.setTextSize(this.annotatedGlyphSize.getNodeQualityTextSize());
		if(getNodeQualityIndex() < 0) {
			thePaint.setColor(Color.RED);
		} else {
			thePaint.setColor(Color.BLACK);
		}
		theCanvas.drawText(
				getNodeQualityIndexString(),
				this.annotatedGlyphSize.getNounQualityTextLeftMargin(),
				this.annotatedGlyphSize.getNounQualityTextTopMargin(),
				thePaint );
		// setup for writing annotations
		thePaint.setTextSize(this.annotatedGlyphSize.getAnnotationTextSize());
		thePaint.setColor(Color.BLACK);
		// Top Zone
		int theRow = 0;
		for (String theAnnotation : theTopZoneAnnotationList) {
			theCanvas.drawText(
					theAnnotation,
					this.annotatedGlyphSize.getBottomTopZoneLeftMargin(theAnnotatedGlyphBitmap),
					this.annotatedGlyphSize.getTopZoneTopMargin() + (theRow * this.annotatedGlyphSize.getAnnotationCharacterHeight()),
					thePaint );
			++theRow;
		}
		// Bottom Zone
		theRow = 0;
		for (String theAnnotation : theBottomZoneAnnotationList) {
			theCanvas.drawText(
					theAnnotation,
					this.annotatedGlyphSize.getBottomTopZoneLeftMargin(theAnnotatedGlyphBitmap),
					this.annotatedGlyphSize.getBottomZoneTopMargin(theAnnotatedGlyphBitmap) + (theRow * this.annotatedGlyphSize.getAnnotationCharacterHeight()),
					thePaint );
			++theRow;
		}
		// Left Zone
		theRow = 0;
		for (String theAnnotation : theLeftZoneAnnotationList) {
			theCanvas.drawText(
					theAnnotation,
					this.annotatedGlyphSize.getLeftZoneLeftMargin(),
					this.annotatedGlyphSize.getLeftRightZoneTopMargin(theAnnotatedGlyphBitmap) + (theRow * this.annotatedGlyphSize.getAnnotationCharacterHeight()),
					thePaint );
			++theRow;
		}
		// Right Zone
		theRow = 0;
		for (String theAnnotation : theRightZoneAnnotationList) {
			theCanvas.drawText(
					theAnnotation,
					this.annotatedGlyphSize.getRightZoneLeftMargin(theAnnotatedGlyphBitmap),
					this.annotatedGlyphSize.getLeftRightZoneTopMargin(theAnnotatedGlyphBitmap) + (theRow * this.annotatedGlyphSize.getAnnotationCharacterHeight()),
					thePaint );
			++theRow;
		}
		return theAnnotatedGlyphBitmap;
	}

	private static float getDecoratedNounPositionX(Bitmap theAnnotatedGlyphBitmap, Bitmap theDecoratedNounBitmap) {
		int thePositionX = (theAnnotatedGlyphBitmap.getWidth() / 2) - (theDecoratedNounBitmap.getWidth() / 2) - 1;
		return thePositionX;
	}

	private static float getDecoratedNounPositionY(Bitmap theAnnotatedGlyphBitmap, Bitmap theDecoratedNounBitmap) {
		int thePositionY = (theAnnotatedGlyphBitmap.getHeight() / 2) - (theDecoratedNounBitmap.getHeight() / 2) - 1;
		return thePositionY;
	}

}