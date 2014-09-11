/* @(#)SerializableBitmap.java
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

package com.flywheelms.library.deckangl.glyph;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
// SerialBitmap
	public class SerialBitmap implements Serializable {

		private static final long serialVersionUID = 1L;
		public Bitmap bitmap;

	    // TODO: Finish this constructor
	    SerialBitmap(Bitmap aBitmap) {
	        this.bitmap = aBitmap;
	    }

	    // Converts the Bitmap into a byte array for serialization
	    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
	        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	        this.bitmap.compress(Bitmap.CompressFormat.PNG, 0, byteStream);
	        byte bitmapBytes[] = byteStream.toByteArray();
	        out.write(bitmapBytes, 0, bitmapBytes.length);
	    }

	    // Deserializes a byte array representing the Bitmap and decodes it
	    private void readObject(java.io.ObjectInputStream in) throws IOException {
	        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
	        int b;
	        while((b = in.read()) != -1)
	            byteStream.write(b);
	        byte bitmapBytes[] = byteStream.toByteArray();
	        this.bitmap = BitmapFactory.decodeByteArray(bitmapBytes, 0, bitmapBytes.length);
	    }
	
//	private String title;
//	private int sourceWidth, currentWidth;
//	private int sourceHeight, currentHeight;
//	private Bitmap sourceImage;
//	private Canvas sourceCanvas;        
//	private Bitmap currentImage;
//	private Canvas currentCanvas;   
//	private Paint currentPaint; 
//
//	protected class BitmapDataObject implements Serializable {
//	    private static final long serialVersionUID = 111696345129311948L;
//	    public byte[] imageByteArray;
//	}
//
//	/** Included for serialization - write this layer to the output stream. */
//	private void writeObject(ObjectOutputStream out) throws IOException{
//	    out.writeObject(title);
//	    out.writeInt(currentWidth);
//	    out.writeInt(currentHeight);
//
//	    ByteArrayOutputStream stream = new ByteArrayOutputStream();
//	    currentImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
//	    BitmapDataObject bitmapDataObject = new BitmapDataObject();     
//	    bitmapDataObject.imageByteArray = stream.toByteArray();
//
//	    out.writeObject(bitmapDataObject);
//	}
//
//	/** Included for serialization - read this object from the supplied input stream. */
//	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
//	    title = (String)in.readObject();
//	    sourceWidth = currentWidth = in.readInt();
//	    sourceHeight = currentHeight = in.readInt();
//
//	    BitmapDataObject bitmapDataObject = (BitmapDataObject)in.readObject();
//	    Bitmap image = BitmapFactory.decodeByteArray(bitmapDataObject.imageByteArray, 0, bitmapDataObject.imageByteArray.length);
//
//	    sourceImage = Bitmap.createBitmap(sourceWidth, sourceHeight, Bitmap.Config.ARGB_8888);
//	    currentImage = Bitmap.createBitmap(sourceWidth, sourceHeight, Bitmap.Config.ARGB_8888);
//
//	    sourceCanvas = new Canvas(sourceImage);
//	    currentCanvas = new Canvas(currentImage);
//
//	    currentPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//	    thumbnailPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//	    thumbnailPaint.setARGB(255, 200, 200, 200);
//	    thumbnailPaint.setStyle(Paint.Style.FILL);
//	}

}
