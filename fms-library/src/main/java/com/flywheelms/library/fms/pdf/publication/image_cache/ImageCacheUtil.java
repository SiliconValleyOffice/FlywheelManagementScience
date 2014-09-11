/* @(#)AbstractImageCache.java
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
 ** modify it under the terms of the GNU General protected License, version 3,
 ** as published by the Free Software Foundation. This program is distributed
 ** in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 ** even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 ** PURPOSE.  See the GNU General protected License for more details. You should
 ** have received a copy of the GNU General protected License, in a file named
 ** COPYING, along with this program.  If you cannot find your copy, see
 ** <http://www.gnu.org/licenses/gpl-3.0.html>.
 */

package com.flywheelms.library.fms.pdf.publication.image_cache;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;

import com.flywheelms.library.fmm.helper.FmmHelper;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.codec.PngImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class ImageCacheUtil {

	private static final String TAG = ImageCacheUtil.class.getCanonicalName();

	private static Options bitmapDecodeOptions = new Options();
	static {
		bitmapDecodeOptions.inScaled = false;
	}

	protected static Image getImage(Bitmap aBitmap) {
		Image image = null;
		try {
			ByteArrayOutputStream imageOutputStream = new ByteArrayOutputStream();
			aBitmap.compress(CompressFormat.PNG, 100, imageOutputStream);
			imageOutputStream.close();
			image = PngImage.getImage(imageOutputStream.toByteArray());
		} catch (IOException e) {
			Log.e(TAG, "Failed to load image.", e);
		}
		return image;
	}

	protected static Image getImage(BitmapDrawable aDrawable) {
		return getImage(aDrawable.getBitmap());
	}

	protected static Image getImage(int drawableResourceId) {
		return getImage(BitmapFactory.decodeResource(FmmHelper.getContext().getResources(), drawableResourceId, bitmapDecodeOptions));
	}

	protected static Image getImageScaledToFit(int resourceId, float w, float h) {
		Image image = getImage(resourceId);
		image.scaleToFit(w, h);
		return image;
	}

	private ImageCacheUtil() {
		super();
	}
}
