/* @(#)GlyphDictionary.java
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
import java.util.Collection;
import java.util.HashMap;

import com.flywheelms.library.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.library.deckangl.interfaces.DecKanGlApplicationNoun;

public abstract class DecKanGlDictionary {

	// DecKanGL meta-data mappings for the Dictionary activity
	protected HashMap<String, DecKanGlElementNoun> nounMap;
	protected HashMap<String, DecKanGlElementNounState> nounStateMap;
	protected HashMap<String, DecKanGlElementNounQualityMetric> nounQualityMetricMap;
	protected ArrayList<DecKanGlCanvasZone> disabledDecoratorZones = new ArrayList<DecKanGlCanvasZone>();
	protected DecKanGlLogicalGlyphCache decKanGlLogicalGlyphCache = new DecKanGlLogicalGlyphCache();
	
	protected static DecKanGlDictionary instance;
	
	protected DecKanGlDictionary() {
		this.nounMap = buildNounMap();
		this.nounStateMap = getNounStateMap();
	}
	
	public static DecKanGlDictionary getInstance() {
		return DecKanGlDictionary.instance;
	}

	protected abstract HashMap<String, DecKanGlElementNoun> buildNounMap();

	public Collection<DecKanGlElementNoun> getNounCollection() {
		return this.nounMap.values();
	}

	public DecKanGlElementNoun getNoun(String aNounName) {
		return this.nounMap.get(aNounName);
	}

	protected abstract HashMap<String, DecKanGlElementNounState> getNounStateMap();

	public Collection<DecKanGlElementNounState> getNounStateCollection() {
		return this.nounStateMap.values();
	}

	public DecKanGlElementNounState getNounState(String aNounStateName) {
		return this.nounStateMap.get(aNounStateName);
	}

	public DecKanGlElementNounState getNounStateForColor(String aNounStateColorName) {
		for(DecKanGlElementNounState theNounState : this.nounStateMap.values()) {
			if(theNounState.getNounStateColor().getName().equals(aNounStateColorName)) {
				return theNounState;
			}
		}
		return null;
	}

	public abstract HashMap<String, DecKanGlElementNounQualityMetric> buildNounQualityMetricMap();

	public Collection<DecKanGlElementNounQualityMetric> getNounQualityMetricCollection() {
		return getNounQualityMetricMap().values();
	}
	
	private HashMap<String, DecKanGlElementNounQualityMetric> getNounQualityMetricMap() {
		if(this.nounQualityMetricMap == null) {
			this.nounQualityMetricMap = buildNounQualityMetricMap();
			disableUnusedDecoratorZones();
		}
		return this.nounQualityMetricMap;
	}

	public DecKanGlElementNounQualityMetric getNounQualityMetric(String aNounQualityMetric) {
		return getNounQualityMetricMap().get(aNounQualityMetric);
	}
	
	public abstract String getDecoratorsPackageName();
	
	public void disableUnusedDecoratorZones() {
		if (	!decoratorsUseCanvasLocation(DecKanGlDecoratorCanvasLocation.left_zone_TOP) &&
				!decoratorsUseCanvasLocation(DecKanGlDecoratorCanvasLocation.left_zone_MIDDLE) &&
				!decoratorsUseCanvasLocation(DecKanGlDecoratorCanvasLocation.left_zone_BOTTOM) ) {
			this.disabledDecoratorZones.add(DecKanGlCanvasZone.LEFT_ZONE);
		}
		if (	!decoratorsUseCanvasLocation(DecKanGlDecoratorCanvasLocation.right_zone_TOP) &&
				!decoratorsUseCanvasLocation(DecKanGlDecoratorCanvasLocation.right_zone_MIDDLE) &&
				!decoratorsUseCanvasLocation(DecKanGlDecoratorCanvasLocation.right_zone_BOTTOM) ) {
			this.disabledDecoratorZones.add(DecKanGlCanvasZone.RIGHT_ZONE);
		}
		if (	!decoratorsUseCanvasLocation(DecKanGlDecoratorCanvasLocation.top_zone_LEFT) &&
				!decoratorsUseCanvasLocation(DecKanGlDecoratorCanvasLocation.top_zone_MIDDLE) &&
				!decoratorsUseCanvasLocation(DecKanGlDecoratorCanvasLocation.top_zone_RIGHT) ) {
			this.disabledDecoratorZones.add(DecKanGlCanvasZone.TOP_ZONE);
		}
		if (	!decoratorsUseCanvasLocation(DecKanGlDecoratorCanvasLocation.bottom_zone_LEFT) &&
				!decoratorsUseCanvasLocation(DecKanGlDecoratorCanvasLocation.bottom_zone_MIDDLE) &&
				!decoratorsUseCanvasLocation(DecKanGlDecoratorCanvasLocation.bottom_zone_RIGHT) ) {
			this.disabledDecoratorZones.add(DecKanGlCanvasZone.BOTTOM_ZONE);
		}
	}

	private boolean decoratorsUseCanvasLocation(DecKanGlDecoratorCanvasLocation theCanvasLocation) {
		for (DecKanGlElementNounQualityMetric theNounQualityMetric : getNounQualityMetricMap().values()) {
			if (theNounQualityMetric.getLogicalCanvasLocation() == theCanvasLocation) {
				return true;
			}
		}
		return false;
	}
	
	public boolean decoratorsUseZone(DecKanGlCanvasZone aCanvasZone) {
		return this.disabledDecoratorZones.indexOf(aCanvasZone) < 0 ? true : false;
	}

	public DecKanGlGlyph getDecKanGlGlyph(DecKanGlApplicationNoun aDecKanGlApplicationNoun) {
		String theSyntheticGlyphKey = DecKanGlLogicalGlyphCache.synthesizeGlyphKey(aDecKanGlApplicationNoun);
		DecKanGlGlyph theDecKanGlGlyph = DecKanGlLogicalGlyphCache.get(theSyntheticGlyphKey);
		if (theDecKanGlGlyph == null) {
			theDecKanGlGlyph = new DecKanGlGlyph(aDecKanGlApplicationNoun);
			DecKanGlLogicalGlyphCache.put(
					theSyntheticGlyphKey,
					theDecKanGlGlyph );
		}
		return theDecKanGlGlyph;
	}
	
}