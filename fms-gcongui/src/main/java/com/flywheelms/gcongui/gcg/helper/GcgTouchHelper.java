package com.flywheelms.gcongui_light.gcg.helper;

import android.graphics.Rect;
import android.view.View;

/**
 * Created by sstamps on 1/4/15.
 */
public class GcgTouchHelper {

    public static boolean inViewBounds(View aView, int anX, int aY){
        Rect theRectangle = new Rect();
        int[] theLocation = new int[2];
        aView.getDrawingRect(theRectangle);
        aView.getLocationOnScreen(theLocation);
        theRectangle.offset(theLocation[0], theLocation[1]);
        return theRectangle.contains(anX, aY);
    }
}
