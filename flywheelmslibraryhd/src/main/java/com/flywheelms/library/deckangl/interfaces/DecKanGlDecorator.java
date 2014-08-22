/* @(#)Decorator.java
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

package com.flywheelms.library.deckangl.interfaces;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.flywheelms.library.deckangl.enumerator.DecKanGlDecoratedGlyphSize;
import com.flywheelms.library.deckangl.enumerator.DecKanGlDecoratorColor;
import com.flywheelms.library.deckangl.glyph.DecKanGlElementNounQualityMetric;
import com.flywheelms.library.fmm.node.impl.enumerator.TribKnQualityNormalizedDescription;
import com.flywheelms.library.gcg.interfaces.GcgGuiable;

/**
 * All of an application's Noun Quality decorators must implement this interface.
 */
public interface DecKanGlDecorator extends GcgGuiable {

	@Override
	public String toString();
	
//	public static String getQualityMetricName();

	public String getName();
	
	public String getAnnotation();

	public boolean isTransparent();

	public DecKanGlDecoratorColor getColor();
	
	public String getDescription();
	
	public TribKnQualityNormalizedDescription getNormalizedDescription();
	
	public Bitmap getBitmap();
	
	public Drawable getDrawable();
	
	public DecKanGlElementNounQualityMetric getDecKanGlElementNounQualityMetric();
	
//	public static DecKanGlElementNounQualityMetric getNounQualityMetric();

	public int getNounQualityIndex();
	
	public boolean isEnabled();

	public boolean isDisabled();

	public Bitmap getBitmap(DecKanGlDecoratedGlyphSize aGlyphSize);

	public int getCanvasPositionX(DecKanGlDecoratedGlyphSize aGlyphSize);

	public int getCanvasPositionY(DecKanGlDecoratedGlyphSize aGlyphSize);
	
// 	public static List<DecKanGlDecoratorEnumeration> values() {}

}