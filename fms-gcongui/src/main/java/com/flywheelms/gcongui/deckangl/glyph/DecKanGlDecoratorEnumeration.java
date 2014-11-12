/* @(#)DecKanGlDecoratorEnumeration.java
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

package com.flywheelms.gcongui.deckangl.glyph;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratedGlyphSize;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorColor;
import com.flywheelms.gcongui.deckangl.interfaces.DecKanGlDecorator;
import com.flywheelms.gcongui.gcg.GcgApplication;

public abstract class DecKanGlDecoratorEnumeration implements DecKanGlDecorator {

	private final String name;
	private final String description;
	private final int nounQualityIndex;
	private final DecKanGlDecoratorColor decoratorColor;
	private final DecKanGlTribKnQualityNormalizedDescription normalizedDescription;

	protected DecKanGlDecoratorEnumeration() {  // "static" Guiable instance
		this.name = "";
		this.description = "";
		this.nounQualityIndex = 0;
		this.decoratorColor = null;
		this.normalizedDescription = null;
	}
	
	protected DecKanGlDecoratorEnumeration(
			String aName,
			String aDescription,
			DecKanGlTribKnQualityNormalizedDescription aNormalizedDescription,
			int aNounQualityIndex,
			DecKanGlDecoratorColor aDecKanGlDecoratorColor ) {
		this.name = aName;
		this.description = aDescription;
		this.normalizedDescription = aNormalizedDescription;
		this.nounQualityIndex = aNounQualityIndex;
		this.decoratorColor = aDecKanGlDecoratorColor;
	}
	
	public abstract String getQualityMetricName();
	
	public abstract String getQualityMetricDescription();
	
	public abstract Drawable getQualityMetricDrawable();
	
	public abstract DecKanGlDecoratorCanvasLocation getDecoratorCanvasLocation();

	@Override
	public String toString() {
		return getDescription() + "  << " + getNounQualityIndex() + " >>";
	}
	
	@Override
	public String getAnnotation() {
		return toString();
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
	
	@Override
	public DecKanGlTribKnQualityNormalizedDescription getNormalizedDescription() {
		return this.normalizedDescription;
	}
	
	@Override
	public boolean isTransparent() {
		return this.decoratorColor == DecKanGlDecoratorColor.TRANSPARENT;
	}

	@Override
	public DecKanGlDecoratorColor getColor() {
		return this.decoratorColor;
	}

	@Override
	public int getCanvasPositionX(DecKanGlDecoratedGlyphSize aGlyphSize) {
		return getDecoratorCanvasLocation().getCanvasPositionX(aGlyphSize);
	}

	@Override
	public int getCanvasPositionY(DecKanGlDecoratedGlyphSize aGlyphSize) {
		return getDecoratorCanvasLocation().getCanvasPositionY(aGlyphSize);
	}

	@Override
	public boolean isEnabled() {
		return this.decoratorColor != DecKanGlDecoratorColor.DISABLED;
	}

	@Override
	public boolean isDisabled() {
		return this.decoratorColor == DecKanGlDecoratorColor.DISABLED;
	}

	@Override
	public int getNounQualityIndex() {
		return this.nounQualityIndex;
	}

	public DecKanGlElementNounQualityMetric getDecKanGlNounQualityMetric() {
		return DecKanGlDictionary.getInstance().getNounQualityMetric(getQualityMetricName());
	}
	
	@Override
	public Bitmap getBitmap() {
		return getBitmap(DecKanGlDecoratedGlyphSize.TINY);
	}

	@Override
	public Bitmap getBitmap(DecKanGlDecoratedGlyphSize aGlyphSize) {
		return getDecoratorCanvasLocation().getGlyphCanvasZone().getDecoratorBitmap(this.decoratorColor, aGlyphSize);
	}

	@Override
	public Drawable getDrawable() {
		return new BitmapDrawable(GcgApplication.getContext().getResources(), this.getBitmap());
	}

	@Override
	public String getDataText() {
		return this.toString();
	}

	@Override
	public Drawable getDataDrawable() {
		return this.getDrawable();
	}

	@Override
	public int getDataDrawableResourceId() {
		return R.drawable.gcg__null_drawable;
	}

	@Override
	public String getLabelText() {
		return GcgApplication.getContext().getResources().getString(R.string.deckangl__quality_decorators);
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.gcg__null_drawable);
	}

	@Override
	public DecKanGlElementNounQualityMetric getDecKanGlElementNounQualityMetric() {
		return DecKanGlDictionary.getInstance().getNounQualityMetric(getQualityMetricName());
	}

}