/* @(#) GcgDisplayUrlInsideImageView.java
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

package com.flywheelms.gcongui.gcg.rest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.flywheelms.gcongui.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class GcgDisplayUrlInsideImageView extends AsyncTask<Void, Void, Void> {

    public enum ImageStyle {DEFAULT, ROUND, OVAL}
    private final String mUrl;
    private final ImageView mImageView;
    private Bitmap mBitmap;
    private static final HashMap<String, Bitmap> mBitmapCache = new HashMap<String, Bitmap>();
    private static final int BITMAP_CACHE_SIZE = 30;
    private ImageStyle mImageStyle = ImageStyle.DEFAULT;  // TODO

    public GcgDisplayUrlInsideImageView(String aUrl, ImageView anImageView) {
        this.mUrl = aUrl;
        this.mImageView = anImageView;
    }

    public GcgDisplayUrlInsideImageView(String aUrl, ImageView anImageView, ImageStyle anImageStyle) {
        this.mUrl = aUrl;
        this.mImageView = anImageView;
        this.mImageStyle = anImageStyle;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.mBitmap = GcgDisplayUrlInsideImageView.mBitmapCache.get(this.mUrl);
        if(this.mBitmap == null) {
            this.mImageView.setImageResource(R.drawable.gcg__null_drawable);
        } else {
            this.mImageView.setImageBitmap(this.mBitmap);
            cancel(true);
        }
        System.gc();
    }

    @Override
    protected Void doInBackground(Void... params) {
        if(! isCancelled()) {
            try {
                URL theUrl = new URL(this.mUrl);
                HttpURLConnection theHttpURLConnection = (HttpURLConnection) theUrl.openConnection();
                theHttpURLConnection.setDoInput(true);
                theHttpURLConnection.connect();
                InputStream theInputStream = theHttpURLConnection.getInputStream();
                this.mBitmap = BitmapFactory.decodeStream(theInputStream);
                theInputStream.close();
                theHttpURLConnection.disconnect();
                updateBitmapCache();
            } catch (IOException e) {
                // Log exception
                return null;
            }
        }
        return null;
    }

    private void updateBitmapCache() {
        if(GcgDisplayUrlInsideImageView.mBitmapCache.size() > GcgDisplayUrlInsideImageView.BITMAP_CACHE_SIZE) {
            GcgDisplayUrlInsideImageView.mBitmapCache.clear();
            System.gc();
        }
        GcgDisplayUrlInsideImageView.mBitmapCache.put(this.mUrl, this.mBitmap);
    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        this.mImageView.setImageBitmap(this.mBitmap);
    }

}
