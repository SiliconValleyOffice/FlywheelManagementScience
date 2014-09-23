/* @(#)CanvasZone.java
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
import android.graphics.BitmapFactory;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.deckangl.DecKanGlHelper;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratedGlyphSize;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorColor;

import java.util.Hashtable;

public enum DecKanGlCanvasZone {
	
	/* There are 4 possible canvas zones in a DecKanGL Glyph.
	 * Many applications do not utilize all zones.
	 */
	LEFT_ZONE(
			"Left Zone",
			new Hashtable<DecKanGlDecoratorColor, Bitmap> () {
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__tiny__left_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__tiny__left_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__tiny__left_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__tiny__left_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__tiny__left_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__tiny__left_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__tiny__left_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__tiny__left_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__tiny__left_zone__yellow));
				}
			},
			new Hashtable<DecKanGlDecoratorColor, Bitmap> (){
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__small__left_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__small__left_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__small__left_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__small__left_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__small__left_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__small__left_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__small__left_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__small__left_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__small__left_zone__yellow));
				}
			},
			new Hashtable<DecKanGlDecoratorColor, Bitmap> (){
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__medium__left_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__medium__left_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__medium__left_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__medium__left_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__medium__left_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__medium__left_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__medium__left_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__medium__left_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__medium__left_zone__yellow));
				}
			},
			new Hashtable<DecKanGlDecoratorColor, Bitmap> (){
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__large__left_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__large__left_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__large__left_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__large__left_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__large__left_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__large__left_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__large__left_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__large__left_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__large__left_zone__yellow));
				}
			} ),
	RIGHT_ZONE(
			"Right Zone",
			new Hashtable<DecKanGlDecoratorColor, Bitmap> () {
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__tiny__right_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__tiny__right_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__tiny__right_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__tiny__right_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__tiny__right_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__tiny__right_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__tiny__right_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__tiny__right_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__tiny__right_zone__yellow));
				}
			},
			new Hashtable<DecKanGlDecoratorColor, Bitmap> (){
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__small__right_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__small__right_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__small__right_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__small__right_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__small__right_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__small__right_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__small__right_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__small__right_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__small__right_zone__yellow));
				}
			},
			new Hashtable<DecKanGlDecoratorColor, Bitmap> (){
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__medium__right_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__medium__right_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__medium__right_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__medium__right_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__medium__right_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__medium__right_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__medium__right_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__medium__right_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__medium__right_zone__yellow));
				}
			},
			new Hashtable<DecKanGlDecoratorColor, Bitmap> (){
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__large__right_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__large__right_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__large__right_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__large__right_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__large__right_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__large__right_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__large__right_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__large__right_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__large__right_zone__yellow));
				}
			} ),
	TOP_ZONE(
			"Top Zone",
			new Hashtable<DecKanGlDecoratorColor, Bitmap> () {
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__tiny__top_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__tiny__top_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__tiny__top_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__tiny__top_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__tiny__top_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__tiny__top_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__tiny__top_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__tiny__top_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__tiny__top_zone__yellow));
				}
			},
			new Hashtable<DecKanGlDecoratorColor, Bitmap> (){
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__small__top_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__small__top_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__small__top_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__small__top_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__small__top_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__small__top_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__small__top_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__small__top_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__small__top_zone__yellow));
				}
			},
			new Hashtable<DecKanGlDecoratorColor, Bitmap> (){
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__medium__top_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__medium__top_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__medium__top_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__medium__top_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__medium__top_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__medium__top_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__medium__top_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__medium__top_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__medium__top_zone__yellow));
				}
			},
			new Hashtable<DecKanGlDecoratorColor, Bitmap> (){
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__large__top_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__large__top_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__large__top_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__large__top_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__large__top_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__large__top_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__large__top_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__large__top_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__large__top_zone__yellow));
				}
			} ),
	BOTTOM_ZONE(
			"Bottom Zone",
			new Hashtable<DecKanGlDecoratorColor, Bitmap> () {
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__tiny__bottom_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__tiny__bottom_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__tiny__bottom_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__tiny__bottom_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__tiny__bottom_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__tiny__bottom_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__tiny__bottom_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__tiny__bottom_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__tiny__bottom_zone__yellow));
				}
			},
			new Hashtable<DecKanGlDecoratorColor, Bitmap> (){
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__small__bottom_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__small__bottom_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__small__bottom_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__small__bottom_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__small__bottom_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__small__bottom_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__small__bottom_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__small__bottom_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__small__bottom_zone__yellow));
				}
			},
			new Hashtable<DecKanGlDecoratorColor, Bitmap> (){
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__medium__bottom_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__medium__bottom_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__medium__bottom_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__medium__bottom_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__medium__bottom_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__medium__bottom_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__medium__bottom_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__medium__bottom_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__medium__bottom_zone__yellow));
				}
			},
			new Hashtable<DecKanGlDecoratorColor, Bitmap> (){
				private static final long serialVersionUID = 1L;
				{
					put(DecKanGlDecoratorColor.BLUE, getBitmap(R.drawable.deckangl__large__bottom_zone__blue));
					put(DecKanGlDecoratorColor.DISABLED, getBitmap(R.drawable.deckangl__large__bottom_zone__disabled));
					put(DecKanGlDecoratorColor.GRAY, getBitmap(R.drawable.deckangl__large__bottom_zone__gray));
					put(DecKanGlDecoratorColor.GREEN, getBitmap(R.drawable.deckangl__large__bottom_zone__green));
					put(DecKanGlDecoratorColor.ORANGE, getBitmap(R.drawable.deckangl__large__bottom_zone__orange));
					put(DecKanGlDecoratorColor.PINK, getBitmap(R.drawable.deckangl__large__bottom_zone__pink));
					put(DecKanGlDecoratorColor.RED, getBitmap(R.drawable.deckangl__large__bottom_zone__red));
					put(DecKanGlDecoratorColor.TRANSPARENT, getBitmap(R.drawable.deckangl__large__bottom_zone__transparent));
					put(DecKanGlDecoratorColor.YELLOW, getBitmap(R.drawable.deckangl__large__bottom_zone__yellow));
				}
			} );
	
	static {
//		LEFT_ZONE.getBitmapHashTable().put(DecKanGlGlyphSize.TINY, R.drawable.dec)
	}
	
	private static Bitmap getBitmap(int aResourceId) {
		// BitmapFactory.decodeResource(DecKanGlHelper.getResources(), this.glyphSize.getBackgroundResourceId(), DecKanGlHelper.getBitmapOptions());
		return BitmapFactory.decodeResource(DecKanGlHelper.getResources(), aResourceId, null);
	}

//	private static BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
	private final String name;
	private final Hashtable<DecKanGlDecoratorColor, Bitmap> bitmapHashtableTiny; 
	private final Hashtable<DecKanGlDecoratorColor, Bitmap> bitmapHashtableSmall; 
	private final Hashtable<DecKanGlDecoratorColor, Bitmap> bitmapHashtableMedium; 
	private final Hashtable<DecKanGlDecoratorColor, Bitmap> bitmapHashtableLarge; 
//	private int zoneWidth;
//	private int zoneHeight;
	
	private DecKanGlCanvasZone(
			String aName,
			Hashtable<DecKanGlDecoratorColor, Bitmap> aBitmapHashtableTiny,
			Hashtable<DecKanGlDecoratorColor, Bitmap> aBitmapHashtableSmall,
			Hashtable<DecKanGlDecoratorColor, Bitmap> aBitmapHashtableMedium,
			Hashtable<DecKanGlDecoratorColor, Bitmap> aBitmapHashtableLarge ) {
		this.name = aName;
		this.bitmapHashtableTiny = aBitmapHashtableTiny;
		this.bitmapHashtableSmall = aBitmapHashtableSmall;
		this.bitmapHashtableMedium = aBitmapHashtableMedium;
		this.bitmapHashtableLarge = aBitmapHashtableLarge;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public String getName() {
		return this.name;
	}

	@SuppressWarnings("incomplete-switch")
	public Bitmap getDecoratorBitmap(DecKanGlDecoratorColor aDecoratorColor, DecKanGlDecoratedGlyphSize aGlyphSize) {
		Bitmap theBitmap = null;
		switch (aGlyphSize) {
		case TINY:
			theBitmap = this.bitmapHashtableTiny.get(aDecoratorColor);
			break;
		case SMALL:
			theBitmap = this.bitmapHashtableSmall.get(aDecoratorColor);
			break;
		case MEDIUM:
			theBitmap = this.bitmapHashtableMedium.get(aDecoratorColor);
			break;
		case LARGE:
			theBitmap = this.bitmapHashtableLarge.get(aDecoratorColor);
			break;
		}
		return theBitmap;
	}
	
}