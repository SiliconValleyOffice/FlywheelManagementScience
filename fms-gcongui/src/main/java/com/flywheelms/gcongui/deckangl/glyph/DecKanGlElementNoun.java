/* @(#)DecKanGlElementNoun.java
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
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlNounStateColor;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlNounStateDrawableSize;
import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;

import java.util.Hashtable;

/*
 * ASSUMPTION - we always have a complete set of tiny drawables to support PDF generation, one for
 * each DecKanGlNounStateColor.
 * This will enable the gradual authoring of high fidelity images for each of the 4 sizes, increasing
 * the application's gui fidelity over time.
 * 
 * Provides a natural caching mechanism for these bitmaps.
 */
public class DecKanGlElementNoun extends DecKanGlElement {
	
	protected static GcgGuiable staticInstance;
	
	private final Hashtable<DecKanGlNounStateColor, BitmapDrawable> nounStateBitmapTableTiny; 
	private final Hashtable<DecKanGlNounStateColor, BitmapDrawable> nounStateBitmapTableSmall; 
	private final Hashtable<DecKanGlNounStateColor, BitmapDrawable> nounStateBitmapTableMedium; 
	private final Hashtable<DecKanGlNounStateColor, BitmapDrawable> nounStateBitmapTableLarge;

	private DecKanGlElementNoun() {  // "static" Guiable instance for the Dictionary Activity
		super("", null);
		this.nounStateBitmapTableTiny = null;
    	this.nounStateBitmapTableSmall = null;
    	this.nounStateBitmapTableMedium = null;
    	this.nounStateBitmapTableLarge = null;
	}
	
    public DecKanGlElementNoun(
    		String aName,
    		Hashtable<DecKanGlNounStateColor, BitmapDrawable> aNounStateBitmapTableTiny,
    		Hashtable<DecKanGlNounStateColor, BitmapDrawable> aNounStateBitmapTableSmall,
    		Hashtable<DecKanGlNounStateColor, BitmapDrawable> aNounStateBitmapTableMedium,
    		Hashtable<DecKanGlNounStateColor, BitmapDrawable> aNounStateBitmapTableLarge ) {
    	super(aName, aNounStateBitmapTableTiny.get(DecKanGlNounStateColor.GRAY));
    	this.nounStateBitmapTableTiny= aNounStateBitmapTableTiny;
    	this.nounStateBitmapTableSmall = aNounStateBitmapTableSmall;
    	this.nounStateBitmapTableMedium = aNounStateBitmapTableMedium;
    	this.nounStateBitmapTableLarge = aNounStateBitmapTableLarge;
//    	initializeScalingDimensions();
    }

    /*
     * Assumption:
     *   - we always have a high fidelity small gray BitmapDrawable
     */
//	private void initializeScalingDimensions() {
//		BitmapDrawable theSmallGrayBitmapDrawable = this.nounStateBitmapTableSmall.get(DecKanGlNounStateColor.GRAY);
//		this.scalingDimensions.put(
//				DecKanGlNounStateDrawableSize.SMALL,
//				new Point(theSmallGrayBitmapDrawable.getIntrinsicWidth(), theSmallGrayBitmapDrawable.getIntrinsicHeight()) );
//		this.scalingDimensions.put(
//				DecKanGlNounStateDrawableSize.MEDIUM,
//				whatsThePoint(theSmallGrayBitmapDrawable, DecKanGlNounStateDrawableSize.MEDIUM) );
//		this.scalingDimensions.put(
//				DecKanGlNounStateDrawableSize.LARGE,
//				whatsThePoint(theSmallGrayBitmapDrawable, DecKanGlNounStateDrawableSize.LARGE) );
//	}
//
//	private static Point whatsThePoint(BitmapDrawable theSmallGrayBitmapDrawable, DecKanGlNounStateDrawableSize aDrawableTargetSize) {
//		Point thePoint;
//		if(theSmallGrayBitmapDrawable.getIntrinsicWidth() == theSmallGrayBitmapDrawable.getIntrinsicHeight()) {
//			thePoint = new Point(aDrawableTargetSize.getWidth(), aDrawableTargetSize.getHeight());
//		} else if(theSmallGrayBitmapDrawable.getIntrinsicWidth() > theSmallGrayBitmapDrawable.getIntrinsicHeight()) {
//			thePoint = setScalingDimensionsWidthConstrained(aDrawableTargetSize, theSmallGrayBitmapDrawable);
//		} else {
//			thePoint = setScalingDimensionsHeightConstrained(aDrawableTargetSize, theSmallGrayBitmapDrawable);
//		}
//		return thePoint;
//	}
//
//	private static Point setScalingDimensionsWidthConstrained(DecKanGlNounStateDrawableSize aDrawableTargetSize, BitmapDrawable aSmallGrayBitmapDrawable) {
//		float theHeightFactor = aDrawableTargetSize.getWidth() / aSmallGrayBitmapDrawable.getIntrinsicWidth();
//		return new Point(aDrawableTargetSize.getWidth(), (int) (aSmallGrayBitmapDrawable.getIntrinsicHeight() * theHeightFactor));
//	}
//
//	private static Point setScalingDimensionsHeightConstrained(DecKanGlNounStateDrawableSize aDrawableTargetSize, BitmapDrawable aSmallGrayBitmapDrawable) {
//		float theWidthFactor = aDrawableTargetSize.getHeight() / aSmallGrayBitmapDrawable.getIntrinsicHeight();
//		return new Point((int) (aSmallGrayBitmapDrawable.getIntrinsicWidth() * theWidthFactor), aDrawableTargetSize.getHeight());
//	}

	// implemented by all children of DecKanGlElement
	public static String getMetaDescription() {
		return "Noun";
	}
	
	public String getViewLabel() {
		return getName() + ":";
	}

	@Override
	public String getLabelText() {
		return GcgApplication.getContext().getResources().getString(R.string.deckangl__language_element__noun);
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgApplication.getContext().getResources().getDrawable(R.drawable.gcg__null_drawable);
	}

	public static GcgGuiable getStaticInstance() {  // return a Guiable instance, since this is not a "real" NounDefinition
		if(staticInstance == null) {
			staticInstance = new DecKanGlElementNoun();
		}
		return staticInstance;
	}
	
	public BitmapDrawable getNounStateBitmapDrawable(DecKanGlNounStateDrawableSize aDrawableSize, DecKanGlNounStateColor aStateColor) {
		BitmapDrawable theBitmapDrawable = null;
		switch (aDrawableSize) {
			case LARGE:
				theBitmapDrawable = getLargeNounStateBitmapDrawable(aStateColor);
				break;
			case MEDIUM:
				theBitmapDrawable = getMediumNounStateBitmapDrawable(aStateColor);
				break;
			case SMALL:
				theBitmapDrawable = getSmallNounStateBitmapDrawable(aStateColor);
				break;
			case TINY:
			default:
				theBitmapDrawable = this.nounStateBitmapTableTiny.get(aStateColor);  // we always have a complete set of tiny drawables for PDF
		}
        return theBitmapDrawable;
	}

	private BitmapDrawable getLargeNounStateBitmapDrawable(DecKanGlNounStateColor aStateColor) {
		BitmapDrawable theBitmapDrawable = this.nounStateBitmapTableLarge.get(aStateColor);
		if(theBitmapDrawable == null) {
			BitmapDrawable theSmallerDrawable = getMediumNounStateBitmapDrawable(aStateColor);
			if(theSmallerDrawable != null) {
				theBitmapDrawable = scaleBitmapDrawable(DecKanGlNounStateDrawableSize.LARGE, theSmallerDrawable);
				this.nounStateBitmapTableLarge.put(aStateColor, theBitmapDrawable);
			}
		}
		return theBitmapDrawable;
	}

	private BitmapDrawable getMediumNounStateBitmapDrawable(DecKanGlNounStateColor aStateColor) {
		BitmapDrawable theBitmapDrawable = this.nounStateBitmapTableMedium.get(aStateColor);
		if(theBitmapDrawable == null) {
			BitmapDrawable theSmallerDrawable = getSmallNounStateBitmapDrawable(aStateColor);
			if(theSmallerDrawable != null) {
				theBitmapDrawable = scaleBitmapDrawable(DecKanGlNounStateDrawableSize.MEDIUM, theSmallerDrawable);
				this.nounStateBitmapTableMedium.put(aStateColor, theBitmapDrawable);
			}
		}
		return theBitmapDrawable;
	}

	private BitmapDrawable getSmallNounStateBitmapDrawable(DecKanGlNounStateColor aStateColor) {
		BitmapDrawable theBitmapDrawable = this.nounStateBitmapTableSmall.get(aStateColor);
		if(theBitmapDrawable == null) {
			BitmapDrawable theSmallerDrawable = this.nounStateBitmapTableTiny.get(aStateColor);
			if(theSmallerDrawable != null) {
				theBitmapDrawable = scaleBitmapDrawable(DecKanGlNounStateDrawableSize.SMALL, theSmallerDrawable);
				this.nounStateBitmapTableSmall.put(aStateColor, theBitmapDrawable);
			}
		}
		return theBitmapDrawable;
	}

	private static BitmapDrawable scaleBitmapDrawable(DecKanGlNounStateDrawableSize aDrawableSize, BitmapDrawable aBitmapDrawable) {
		Bitmap theBitmap = aBitmapDrawable.getBitmap().copy(Bitmap.Config.ARGB_8888, true);
		Bitmap theScaledBitmap = Bitmap.createScaledBitmap(
			theBitmap, aDrawableSize.getScaledMeasurement(theBitmap.getWidth()), aDrawableSize.getScaledMeasurement(theBitmap.getHeight()),
			true );
		return new BitmapDrawable(GcgApplication.getAppResources(), theScaledBitmap);
	}
	
}