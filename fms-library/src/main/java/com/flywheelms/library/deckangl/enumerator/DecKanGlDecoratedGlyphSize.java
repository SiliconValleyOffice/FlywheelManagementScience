/* @(#)DecKanGlDecoratedGlyphSize.java
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

import com.flywheelms.library.R;

public enum DecKanGlDecoratedGlyphSize {
	TINY (
			DecKanGlNounStateDrawableSize.TINY,
			R.drawable.deckangl__tiny__background,
			34, // canvas width
			34, // canvas height
			22, // noun status square
			2,  // noun status margins
			1,  // noun status frame width
			6,  // width - left and right zones
			8,  // height - left and right decorators
			6,  // height - top and bottom zones
			8 ),  // width - top and bottom  decorators
	SMALL (
			DecKanGlNounStateDrawableSize.SMALL,
			R.drawable.deckangl__small__background,
			49, // canvas width
			49, // canvas height
			31, // noun status square
			3,  // noun status margins
			1,  // noun status frame width
			9,  // width - left and right zones
			11,  // height - left and right decorators
			9,  // height - top and bottom zones
			11 ),  // width - top and bottom decorators
	MEDIUM (
			DecKanGlNounStateDrawableSize.MEDIUM,
			R.drawable.deckangl__medium__background,
			67, // canvas width
			67, // canvas height
			43, // noun status square
			4,  // noun status margins
			1,  // noun status frame width
			12, // width - left and right zones
			15, // height - left and right decorators
			12, // height - top and bottom zones
			15 ), // width - top and bottom decorators
	LARGE (
			DecKanGlNounStateDrawableSize.LARGE,
			R.drawable.deckangl__large__background,
			134, // canvas width
			134, // canvas height
			86, // noun status square
			7,  // noun status margins
			2,  // noun status frame width
			24, // width - left and right zones
			30, // height - left and right decorators
			24, // height - top and bottom zones
			30 ); // width - top and bottom decorators

	private final DecKanGlNounStateDrawableSize nounStateDrawableSize;
	private final int backgroundResourceId;
	private final int canvasWidth;
	private final int canvasHeight;
	private final int nounStatusSquare;
	private final int nounStatusMargins;
	private final int nounStatusFrame;
	private final int leftRightZoneWidth;
	private final int leftRigtZoneDecoratorHeight;
	private final int topBottomZoneHeight;
	private final int topBottomZoneDecoratorWidth;
	
	private DecKanGlDecoratedGlyphSize(
			DecKanGlNounStateDrawableSize aNounStateDrawableSize,
			int aBackgroundResourceId,
			int aCanvasWidth,
			int aCanvasHeight,
			int aNounStatusSquare,
			int aNounStatusMargins,
			int aNounStatusFrame,
			int aLeftRightZoneWidth,
			int aLeftRigtZoneDecoratorHeight,
			int aTopBottomZoneHeight,
			int aTopBottomZoneDecoratorWidth ) {
		this.nounStateDrawableSize = aNounStateDrawableSize;
		this.backgroundResourceId = aBackgroundResourceId;
		this.canvasWidth = aCanvasWidth;
		this.canvasHeight = aCanvasHeight;
		this.nounStatusSquare = aNounStatusSquare;
		this.nounStatusMargins = aNounStatusMargins;
		this.nounStatusFrame = aNounStatusFrame;
		this.leftRightZoneWidth = aLeftRightZoneWidth;
		this.leftRigtZoneDecoratorHeight = aLeftRigtZoneDecoratorHeight;
		this.topBottomZoneHeight = aTopBottomZoneHeight;
		this.topBottomZoneDecoratorWidth = aTopBottomZoneDecoratorWidth;
	}
	
	public DecKanGlNounStateDrawableSize getNounStateDrawableSize() {
		return this.nounStateDrawableSize;
	}
	
	public int getBackgroundResourceId() {
		return this.backgroundResourceId;
	}

	public int getCanvasWidth() {
		return this.canvasWidth;
	}

	public int getCanvasHeight() {
		return this.canvasHeight;
	}

	public int getNounStatusSquare() {
		return this.nounStatusSquare;
	}

	public int getNounStatusMargin() {
		return this.nounStatusMargins;
	}

	public int getNounStatusFrameWidth() {
		return this.nounStatusFrame;
	}

	public int getLeftRightZoneWidth() {
		return this.leftRightZoneWidth;
	}

	public int getLeftRigtZoneDecoratorHeight() {
		return this.leftRigtZoneDecoratorHeight;
	}

	public int getTopBottomZoneHeight() {
		return this.topBottomZoneHeight;
	}

	public int getTopBottomZoneDecoratorWidth() {
		return this.topBottomZoneDecoratorWidth;
	}
	
}
