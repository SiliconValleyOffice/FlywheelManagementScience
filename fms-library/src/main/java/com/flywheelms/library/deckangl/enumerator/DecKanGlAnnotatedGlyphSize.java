/* @(#)DecKanGlAnnotatedGlyphSize.java
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

package com.flywheelms.library.deckangl.enumerator;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.flywheelms.library.R;
import com.flywheelms.library.deckangl.DecKanGlHelper;

public enum DecKanGlAnnotatedGlyphSize {
	SMALL (
			R.drawable.deckangl__small__annotation__background,
			DecKanGlDecoratedGlyphSize.MEDIUM,
			14, // annotation text size,
			14, // node quality text size
			5,  // annotation character width
			15, // annotation character height
			12, // nounQualityGlyphLeftMargin
			10, // nounQualityGlyphTopMargin
			55, // nounQualityTextLeftMargin
			30, // nounQualityTextTopMargin
			100, // bottomTopZoneLeftMarginOffset
			53, // bottomZoneTopMarginOffset
			15, // leftZoneLeftMargin
			12, // leftRightZoneTopMarginOffset
			53, // rightZoneLeftMarginAdjjustment
			18 ), // topZoneTopMargin
	MEDIUM (
			R.drawable.deckangl__medium__annotation__background,
			DecKanGlDecoratedGlyphSize.LARGE,
			18, // annotation text size,
			18, // node quality text size
			8,  // annotation character width
			21, // annotation character height
			19, // nounQualityGlyphLeftMargin
			15, // nounQualityGlyphTopMargin
			64, // nounQualityTextLeftMargin
			33, // nounQualityTextTopMargin
			125, // bottomTopZoneLeftMarginOffset
			91, // bottomZoneTopMarginOffset
			27, // leftZoneLeftMargin
			24, // leftRightZoneTopMarginOffset
			90, // rightZoneLeftMarginAdjjustment
			25 ); // topZoneTopMargin

	private final int backgroundResourceId;
	private final DecKanGlDecoratedGlyphSize decoratedGlyphSize;
	private static final int nodeQualityBadGlyphResourceId = R.drawable.deckangl__annotation__noun_quality__bad;
	private static final Bitmap nodeQualityBadGlyphBitmap = BitmapFactory.decodeResource(DecKanGlHelper.getResources(), DecKanGlAnnotatedGlyphSize.nodeQualityBadGlyphResourceId);
	private static final int nodeQualityGoodGlyphResourceId = R.drawable.deckangl__annotation__noun_quality__good;
	private static final Bitmap nodeQualityGoodGlyphBitmap = BitmapFactory.decodeResource(DecKanGlHelper.getResources(), DecKanGlAnnotatedGlyphSize.nodeQualityGoodGlyphResourceId);
	private final int annotationTextSize;
	private final int nodeQualityTextSize;
	private final int annotationCharacterWidth;
	private final int annotationCharacterHeight;
	private final int nounQualityGlyphLeftMargin;
	private final int nounQualityGlyphTopMargin;
	private final int nounQualityTextLeftMargin;
	private final int nounQualityTextTopMargin;
	private final int bottomTopZoneLeftMarginOffset;
	private final int bottomZoneTopMarginOffset;
	private final int leftZoneLeftMargin;
	private final int leftRightZoneTopMarginOffset;
	private final int rightZoneLeftMarginOffset;
	private final int topZoneTopMargin;
	
	private DecKanGlAnnotatedGlyphSize(
			int anBackgroundResourceId,
			DecKanGlDecoratedGlyphSize aDecoratedGlyphSize,
			int anAnnotationTextSize,
			int aNodeQualityTextSize,
			int anAnnotationCharacterWidth,
			int anAnnotationCharacterHeight,
			int aNounQualityGlyphLeftMargin,
			int aNounQualityGlyphTopMargin,
			int aNounQualityTextLeftMargin,
			int aNounQualityTextTopMargin,
			int aBottomTopZoneLeftMarginOffset,
			int aBottomZoneTopMarginOffset,
			int aLeftZoneLeftMargin,
			int aLeftRightZoneTopMarginOffset,
			int aRightZoneLeftMarginOffset,
			int aTopZoneTopMargin ) {
		this.backgroundResourceId = anBackgroundResourceId;
		this.decoratedGlyphSize = aDecoratedGlyphSize;
		this.annotationTextSize = anAnnotationTextSize;
		this.nodeQualityTextSize = aNodeQualityTextSize;
		this.annotationCharacterWidth = anAnnotationCharacterWidth;
		this.annotationCharacterHeight = anAnnotationCharacterHeight;
		this.nounQualityGlyphLeftMargin = aNounQualityGlyphLeftMargin;
		this.nounQualityGlyphTopMargin = aNounQualityGlyphTopMargin;
		this.nounQualityTextLeftMargin = aNounQualityTextLeftMargin;
		this.nounQualityTextTopMargin = aNounQualityTextTopMargin;
		this.bottomTopZoneLeftMarginOffset = aBottomTopZoneLeftMarginOffset;
		this.bottomZoneTopMarginOffset = aBottomZoneTopMarginOffset;
		this.leftZoneLeftMargin = aLeftZoneLeftMargin;
		this.leftRightZoneTopMarginOffset = aLeftRightZoneTopMarginOffset;
		this.rightZoneLeftMarginOffset = aRightZoneLeftMarginOffset;
		this.topZoneTopMargin = aTopZoneTopMargin;
	}
	
	public int getBackgroundResourceId() {
		return this.backgroundResourceId;
	}
	
	public DecKanGlDecoratedGlyphSize getDecKanGlDecoratedGlyphSize() {
		return this.decoratedGlyphSize;
	}

	public int getAnnotationTextSize() {
		return this.annotationTextSize;
	}

	public int getNodeQualityTextSize() {
		return this.nodeQualityTextSize;
	}

	public int getAnnotationCharacterWidth() {
		return this.annotationCharacterWidth;
	}

	public int getAnnotationCharacterHeight() {
		return this.annotationCharacterHeight;
	}

	public static int getNodeQualityGlyphResourceId(int aNodeQualityIndex) {
		return aNodeQualityIndex < 0 ? DecKanGlAnnotatedGlyphSize.nodeQualityBadGlyphResourceId : DecKanGlAnnotatedGlyphSize.nodeQualityGoodGlyphResourceId;
	}

	public static Bitmap getNodeQualityGlyphBitmap(int aNodeQualityIndex) {
		return aNodeQualityIndex < 0 ? DecKanGlAnnotatedGlyphSize.nodeQualityBadGlyphBitmap : DecKanGlAnnotatedGlyphSize.nodeQualityGoodGlyphBitmap;
	}

	public DecKanGlDecoratedGlyphSize getDecoratedGlyphSize() {
		return this.decoratedGlyphSize;
	}
	
	public int getNounQualityGlyphLeftMargin() {
		return this.nounQualityGlyphLeftMargin;
	}
	
	public int getNounQualityGlyphTopMargin() {
		return this.nounQualityGlyphTopMargin;
	}

	public int getBottomZoneTopMargin(Bitmap theAnnotatedGlyphBitmap) {
		return (theAnnotatedGlyphBitmap.getHeight() / 2) + this.bottomZoneTopMarginOffset;
	}

	public int getLeftZoneLeftMargin() {
		return this.leftZoneLeftMargin;
	}

	public int getLeftRightZoneTopMargin(Bitmap theAnnotatedGlyphBitmap) {
		return (theAnnotatedGlyphBitmap.getHeight() / 2) - this.leftRightZoneTopMarginOffset;
	}

	public int getRightZoneLeftMargin(Bitmap theAnnotatedGlyphBitmap) {
		return (theAnnotatedGlyphBitmap.getWidth() / 2) + this.rightZoneLeftMarginOffset;
	}

	public int getTopZoneTopMargin() {
		return this.topZoneTopMargin;
	}

	public float getBottomTopZoneLeftMargin(Bitmap theAnnotatedGlyphBitmap) {
		return (theAnnotatedGlyphBitmap.getWidth() / 2) - this.bottomTopZoneLeftMarginOffset;
	}

	public float getNounQualityTextLeftMargin() {
		return this.nounQualityTextLeftMargin;
	}

	public float getNounQualityTextTopMargin() {
		return this.nounQualityTextTopMargin;
	}
	
}
