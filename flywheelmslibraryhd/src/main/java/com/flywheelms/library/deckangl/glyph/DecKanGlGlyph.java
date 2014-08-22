/* @(#)DecKanGlGlyph.java
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

import java.util.HashMap;
import java.util.Hashtable;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.flywheelms.library.deckangl.enumerator.DecKanGlAnnotatedGlyphSize;
import com.flywheelms.library.deckangl.enumerator.DecKanGlDecoratedGlyphSize;
import com.flywheelms.library.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.library.deckangl.enumerator.DecKanGlNounStateDrawableSize;
import com.flywheelms.library.deckangl.interfaces.DecKanGlApplicationNoun;
import com.flywheelms.library.deckangl.interfaces.DecKanGlDecorator;

/*
 * a "logical" glyph, containing all scaled and annotated versions of the glyph
 * 
 * provides caching for all versions of a logical glyph
 */
public class DecKanGlGlyph {
	
	private final DecKanGlElementNoun elementNoun;
	private final DecKanGlElementNounState elementNounState;
	private final HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> decoratorMap;
	private final Hashtable<DecKanGlDecoratedGlyphSize, Bitmap> decoratedNounHashtable = new Hashtable<DecKanGlDecoratedGlyphSize, Bitmap>();
	private final Hashtable<DecKanGlAnnotatedGlyphSize, Bitmap> annotatedGlyphHashtable = new Hashtable<DecKanGlAnnotatedGlyphSize, Bitmap>();
	private final DecKanGlGlyphCanvas glyphCanvas;

	public DecKanGlGlyph(DecKanGlApplicationNoun aDecKanGlApplicationNoun ) {  // we definitely don't want to hang onto a copy of ApplicationNoun
		this.elementNoun = aDecKanGlApplicationNoun.getDecKanGlElementNoun();
		this.elementNounState = aDecKanGlApplicationNoun.getDecKanGlNounState();
		this.decoratorMap = aDecKanGlApplicationNoun.getDecKanGlDecoratorMap();
		this.glyphCanvas = new DecKanGlGlyphCanvas(this);
	}

	// inflate from serialization
	public DecKanGlGlyph(
			DecKanGlElementNoun anElementNoun,
			DecKanGlElementNounState anElementNounState,
			HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> aDecoratorMap ) {
		this.elementNoun = anElementNoun;
		this.elementNounState = anElementNounState;
		this.decoratorMap = aDecoratorMap;
		this.glyphCanvas = new DecKanGlGlyphCanvas(this);
	}

	public Bitmap getAnnotatedGlyphBitmap() {
		return getAnnotatedGlyphBitmap(DecKanGlAnnotatedGlyphSize.SMALL);
	}

	public Bitmap getAnnotatedGlyphBitmap(DecKanGlAnnotatedGlyphSize aGlyphScaling) {
		Bitmap theBitmap = this.annotatedGlyphHashtable.get(aGlyphScaling);
		if(theBitmap == null) {
			theBitmap = this.glyphCanvas.drawAnnotatedGlyphBitmapDrawable(aGlyphScaling);
			this.annotatedGlyphHashtable.put(aGlyphScaling, theBitmap);
		}
		return theBitmap;
	}

	public Bitmap getDecoratedNounBitmapDrawable() {
		return getDecoratedNounBitmap(DecKanGlDecoratedGlyphSize.SMALL);
	}

	public Bitmap getDecoratedNounBitmap(DecKanGlDecoratedGlyphSize aGlyphSize) {
		Bitmap theBitmap = this.decoratedNounHashtable.get(aGlyphSize);
		if(theBitmap == null) {
			theBitmap = this.glyphCanvas.drawDecoratedNounBitmapDrawable(aGlyphSize);
			this.decoratedNounHashtable.put(aGlyphSize, theBitmap);
		}
		return theBitmap;
	}

	public BitmapDrawable getNounStateDrawable() {
		return getNounStateDrawable(DecKanGlNounStateDrawableSize.SMALL);
	}
	
	public int getNounStateDrawableResourceId(DecKanGlNounStateDrawableSize aDrawableSize) {
		// TODO
		return 0;
	}

	public BitmapDrawable getNounStateDrawable(DecKanGlNounStateDrawableSize aDrawableSize) {
		return this.elementNoun.getNounStateBitmapDrawable(aDrawableSize, this.elementNounState.getNounStateColor());
	}

	public DecKanGlElementNoun getElementNoun() {
		return this.elementNoun;
	}

	public DecKanGlElementNounState getElementNounState() {
		return this.elementNounState;
	}

	public HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> getDecoratorMap() {
		return this.decoratorMap;
	}

	public DecKanGlDecorator getDecoratorForLocation(DecKanGlDecoratorCanvasLocation aCanvasLocation) {
		return this.decoratorMap.get(aCanvasLocation);
	}
	
	public int getNounQualityIndex() {
		int theQualityIndex = 0;
		for(DecKanGlDecorator theDecorator : this.decoratorMap.values()) {
			theQualityIndex += theDecorator.getNounQualityIndex();
		}
		return theQualityIndex;
	}
	
	public DecKanGlGlyphCanvas getGlyphCanvas() {
		return this.glyphCanvas;
	}

}