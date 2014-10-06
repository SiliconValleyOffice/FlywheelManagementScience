/* @(#)GcgGuiHelper.java
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

package com.flywheelms.gcongui.gcg.helper;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.SoundEffectConstants;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;

public class GcgGuiHelper {

	public static final int MAX_DRAWABLE_WIDTH =
			GcgApplication.getAppResources().getDrawable(R.drawable.gcg__null_drawable_16).getIntrinsicWidth();
	public static final int MINIMUM_PADDING_BEFORE_DRAWABLE =
			GcgApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.gcg__data__minimum_padding_before_drawable);
	public static final int MINIMUM_PADDING_BEFORE_TEXT =
			GcgApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.gcg__data__minimum_padding_before_text);

	public static void initializeTextViewLabelForGuiable(GcgGuiable aGuiableInstance, TextView aTextViewLabel) {
		aTextViewLabel.setText(aGuiableInstance.getLabelText());
		aTextViewLabel.setCompoundDrawablesWithIntrinsicBounds(aGuiableInstance.getLabelDrawable(), null, null, null);
		aTextViewLabel.setPadding(10, 0, 10, 0);
		aTextViewLabel.setCompoundDrawablePadding(GcgApplication.getContext().getResources().getDimensionPixelOffset(R.dimen.gcg__padding_after_drawable));
	}

	public static void initializeTextViewLabel(TextView aTextViewLabel, int aDrawableResourceId, int aTextResourceId) {
		aTextViewLabel.setText(
				GcgApplication.getInstance().getResources().getString(aTextResourceId));
		aTextViewLabel.setCompoundDrawablesWithIntrinsicBounds(
				GcgApplication.getInstance().getResources().getDrawable(aDrawableResourceId),
				null, null, null);
		aTextViewLabel.setPadding(0, 0, 0, 0);
		aTextViewLabel.setCompoundDrawablePadding(GcgApplication.getContext().getResources().getDimensionPixelOffset(R.dimen.gcg__padding_after_drawable));
	}

	public static void setCompoundDrawablePadding(TextView theTextView, Drawable dataDrawable) {
		int theDrawableWidth = dataDrawable.getIntrinsicWidth();
		int theLeftPadding = 0;
		int theRightPadding = 0;
		if(theDrawableWidth < MAX_DRAWABLE_WIDTH) {
			theLeftPadding = (MAX_DRAWABLE_WIDTH - theDrawableWidth) / 2;
			theRightPadding = MAX_DRAWABLE_WIDTH - theLeftPadding - theDrawableWidth;
		} 
		theTextView.setPadding(theLeftPadding + MINIMUM_PADDING_BEFORE_DRAWABLE, 0, 0, 0);
		theTextView.setCompoundDrawablePadding(theRightPadding + MINIMUM_PADDING_BEFORE_TEXT);
	}

	public static void setCompoundDrawablePadding(TextView theTextView) {
		theTextView.setPadding(
				GcgApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.gcg__data__standard_padding_before_drawable),0 ,0 ,0);
		theTextView.setCompoundDrawablePadding(
				GcgApplication.getInstance().getResources().getDimensionPixelOffset(R.dimen.gcg__data__standard_padding_before_text));
	}
	
	public static void updateScaledImageViewInTableRow(ImageView anImageView, BitmapDrawable aBitmapDrawable) {
		anImageView.setImageDrawable(aBitmapDrawable);
		TableRow.LayoutParams theLayoutParams = new TableRow.LayoutParams(
				aBitmapDrawable.getBitmap().getWidth() + 10,
				aBitmapDrawable.getBitmap().getHeight() + 10 );
		anImageView.setLayoutParams(theLayoutParams);
	}
	
	public static void updateScaledImageViewInTableRow(ImageView anImageView, Bitmap aBitmap) {
		anImageView.setImageBitmap(aBitmap);
		TableRow.LayoutParams theLayoutParams = new TableRow.LayoutParams(
				aBitmap.getWidth() + 10,
				aBitmap.getHeight() + 10 );
		anImageView.setLayoutParams(theLayoutParams);
	}

	public static void updateIntegerView(TextView anIntegerView, int aNodeQualityIndex) {
		if(aNodeQualityIndex < 0) {
			anIntegerView.setTextColor(Color.RED);
		} else {
			anIntegerView.setTextColor(GcgApplication.getInstance().getResources().getColor(R.color.gcg__widget_data__text_color));
		}
		anIntegerView.setText(Integer.toString(aNodeQualityIndex));
	}
	
	public static int getPixelsForDp(int aDpInteger) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, aDpInteger, GcgApplication.getAppResources().getDisplayMetrics());
	}
	
	public static SpannableString getColorString(String aString, int aColorConstant) {
	    SpannableString theSpannableString =  new SpannableString(aString);                
	    theSpannableString.setSpan(new ForegroundColorSpan(aColorConstant), 0, aString.length(), 0);
	    return theSpannableString;
	}

    public static void playSystemClick(View aView) {
        aView.playSoundEffect(SoundEffectConstants.CLICK);
    }

}
