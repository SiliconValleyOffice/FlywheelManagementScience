/* @(#)DecKanGlDecoratorCanvasLocation.java
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

package com.flywheelms.library.deckangl.enumerator;

import com.flywheelms.library.R;
import com.flywheelms.library.deckangl.DecKanGlHelper;
import com.flywheelms.library.deckangl.glyph.DecKanGlCanvasZone;

import java.util.HashMap;

public enum DecKanGlDecoratorCanvasLocation {
	
	/* There are 12 possible decorator locations in a DecKanGL Glyph.
	 * Many applications (language designs) do not utilize all decorator locations.
	 * 
	 * Decorators are contained within 1 to 4 possible zones.
	 */
	bottom_zone_LEFT(
			R.string.deckangl__glyph_zone__bottom__left,
			0,
			DecKanGlCanvasZone.BOTTOM_ZONE ),
	bottom_zone_MIDDLE(
			R.string.deckangl__glyph_zone__bottom__middle,
			1,
			DecKanGlCanvasZone.BOTTOM_ZONE ),
	bottom_zone_RIGHT(
			R.string.deckangl__glyph_zone__bottom__right,
			2,
			DecKanGlCanvasZone.BOTTOM_ZONE ),
	left_zone_BOTTOM(
			R.string.deckangl__glyph_zone__left__bottom,
			2,
			DecKanGlCanvasZone.LEFT_ZONE ),
	left_zone_MIDDLE(
			R.string.deckangl__glyph_zone__left__middle,
			1,
			DecKanGlCanvasZone.LEFT_ZONE ),
	left_zone_TOP(
			R.string.deckangl__glyph_zone__left__top,
			0,
			DecKanGlCanvasZone.LEFT_ZONE ),
	right_zone_BOTTOM(
			R.string.deckangl__glyph_zone__right__bottom,
			2,
			DecKanGlCanvasZone.RIGHT_ZONE ),
	right_zone_MIDDLE(
			R.string.deckangl__glyph_zone__right__middle,
			1,
			DecKanGlCanvasZone.RIGHT_ZONE ),
	right_zone_TOP(
			R.string.deckangl__glyph_zone__right__top,
			0,
			DecKanGlCanvasZone.RIGHT_ZONE ),
	top_zone_LEFT(
			R.string.deckangl__glyph_zone__top__left,
			0,
			DecKanGlCanvasZone.TOP_ZONE ),
	top_zone_MIDDLE(
			R.string.deckangl__glyph_zone__top__middle,
			1,
			DecKanGlCanvasZone.TOP_ZONE ),
	top_zone_RIGHT(
			R.string.deckangl__glyph_zone__top__right,
			2,
			DecKanGlCanvasZone.TOP_ZONE );
	
	private static HashMap<String, DecKanGlDecoratorCanvasLocation> nameMap = new HashMap<String, DecKanGlDecoratorCanvasLocation>();
	static {
		DecKanGlDecoratorCanvasLocation.nameMap.put(bottom_zone_LEFT.getName(), bottom_zone_LEFT);
		DecKanGlDecoratorCanvasLocation.nameMap.put(bottom_zone_MIDDLE.getName(), bottom_zone_MIDDLE);
		DecKanGlDecoratorCanvasLocation.nameMap.put(bottom_zone_RIGHT.getName(), bottom_zone_RIGHT);
		DecKanGlDecoratorCanvasLocation.nameMap.put(left_zone_BOTTOM.getName(), left_zone_BOTTOM);
		DecKanGlDecoratorCanvasLocation.nameMap.put(left_zone_MIDDLE.getName(), left_zone_MIDDLE);
		DecKanGlDecoratorCanvasLocation.nameMap.put(left_zone_TOP.getName(), left_zone_TOP);
		DecKanGlDecoratorCanvasLocation.nameMap.put(right_zone_BOTTOM.getName(), right_zone_BOTTOM);
		DecKanGlDecoratorCanvasLocation.nameMap.put(right_zone_MIDDLE.getName(), right_zone_MIDDLE);
		DecKanGlDecoratorCanvasLocation.nameMap.put(right_zone_TOP.getName(), right_zone_TOP);
		DecKanGlDecoratorCanvasLocation.nameMap.put(top_zone_LEFT.getName(), top_zone_LEFT);
		DecKanGlDecoratorCanvasLocation.nameMap.put(top_zone_MIDDLE.getName(), top_zone_MIDDLE);
		DecKanGlDecoratorCanvasLocation.nameMap.put(top_zone_RIGHT.getName(), top_zone_RIGHT);
	}

	public static DecKanGlDecoratorCanvasLocation getObjectForName(String aName) {
		return DecKanGlDecoratorCanvasLocation.nameMap.get(aName);
	}

	private final int nameResourceId;
	private String name = null;
	private final DecKanGlCanvasZone glyphCanvasZone;
	private final int zonePosition;
	
	private DecKanGlDecoratorCanvasLocation(
			int aNameResourceId,
			int aZonePosition,
			DecKanGlCanvasZone aGlyphCanvasZone ) {
		this.nameResourceId = aNameResourceId;
		this.zonePosition = aZonePosition;
		this.glyphCanvasZone = aGlyphCanvasZone;
	}

	@Override
	public String toString() {
		return getName();
	}
	
	public String getName() {
		if(this.name == null && this.nameResourceId != 0)  {
			this.name = DecKanGlHelper.getResources().getString(this.nameResourceId);
		}
		return this.name;
	}
	
	public DecKanGlCanvasZone getGlyphCanvasZone() {
		return this.glyphCanvasZone;
	}

	@SuppressWarnings("incomplete-switch")
	public int getCanvasPositionX(DecKanGlDecoratedGlyphSize aGlyphSize) {
		int thePositionX = 0;
		switch (this.glyphCanvasZone) {
			case LEFT_ZONE:
				break;
			case RIGHT_ZONE:
				thePositionX =
					aGlyphSize.getLeftRightZoneWidth() +
					aGlyphSize.getNounStatusSquare();
				break;
			case BOTTOM_ZONE:
			case TOP_ZONE:
				thePositionX =
					aGlyphSize.getLeftRightZoneWidth() +
					(this.zonePosition * aGlyphSize.getTopBottomZoneDecoratorWidth()) -
					this.zonePosition * aGlyphSize.getNounStatusFrameWidth();
				break;
		}
		return thePositionX;
	}

	@SuppressWarnings("incomplete-switch")
	public int getCanvasPositionY(DecKanGlDecoratedGlyphSize aGlyphSize) {
		int thePositionY = 0;
		switch (this.glyphCanvasZone) {
			case LEFT_ZONE:
			case RIGHT_ZONE:
					thePositionY =
					aGlyphSize.getTopBottomZoneHeight() +
					(this.zonePosition * aGlyphSize.getLeftRigtZoneDecoratorHeight()) -
					this.zonePosition * aGlyphSize.getNounStatusFrameWidth();
				break;
			case TOP_ZONE:
				break;
			case BOTTOM_ZONE:
				thePositionY =
					aGlyphSize.getTopBottomZoneHeight() +
					aGlyphSize.getNounStatusSquare();
		}
		return thePositionY;
	}
	
}