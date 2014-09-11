/* @(#)GcgHelper.java
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

package com.flywheelms.library.gcg.helper;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.GcgApplication;
import com.flywheelms.library.gcg.interfaces.GcgFlingController;
import com.flywheelms.library.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.gcg.interfaces.GcgGuiableImpl;
import com.flywheelms.library.gcg.listeners.GcgFlingListener;
import com.flywheelms.library.gcg.listeners.GcgTouchFlipListener;
import com.flywheelms.library.gcg.viewflipper.GcgViewFlipper;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class GcgHelper {

//	private static MotionEvent genericMotionEventActionUp;
	private static GcgHelper singleton = null;
	private static int touchSlop = 0;
	private static int minimumTouchVelocity = 0;
	public static final String gesture__FLING_RIGHT = "FlingRight";
	public static final String gesture__FLING_LEFT= "FlingLeft";
	public static final String gesture__FLING_UP= "FlingUp";
	public static final String gesture__FLING_DOWN= "FlingDown";
	public static final String gesture__FLING_NORTH_EAST = "FlingNorthEast";
	public static final String gesture__FLING_NORTH_WEST= "FlingNorthWest";
	public static final String gesture__FLING_SOUTH_EAST= "FlingSouthEast";
	public static final String gesture__FLING_SOUTH_WEST= "FlingSouthWest";
	public static final long TOUCH_STUTTER = 300;
	public static final int transition__LEFT_TO_RIGHT = 0;
	public static final int transition__RIGHT_TO_LEFT = 1;
	public static final int transition__TOP_TO_BOTTOM = 2;
	public static final int transition__BOTTOM_TO_TOP = 3;
	public static final String text__NEW_LINE = "\r\n";
	public static final String html__NEW_LINE = "<br/>";
	public static final String text__INDENT = "    ";
	public static final String html__INDENT = "&nbsp;&nbsp;&nbsp;&nbsp;";
	public static final String html__ERROR = "<b><font color='#FF0000'>ERROR</font></b>";
	public static final String html__WARNING = "<b><font color='#000000'>Warning</font></b>";
	public static final int view_resource_id__WIDGET_FDK_CURSOR = R.id.widget_fdk_cursor;

	private GcgHelper() {
	}

	public static GcgHelper getInstance() {
		if(GcgHelper.singleton == null) {
			GcgHelper.singleton = new GcgHelper();
		}
		return GcgHelper.singleton;
	}

//	public static boolean isTouchStutter(long aLong) {
//		boolean theBoolean = false;
//		long theCurrentTime = System.currentTimeMillis();
//		if(theCurrentTime - aLong < TOUCH_STUTTER) {
//			theBoolean = true;
//		}
//		aLong = theCurrentTime;
//		return theBoolean;
//	}

	public static int getPixelsForDp(Context aContext, int aDp) {
		DisplayMetrics theDisplayMetrics = new DisplayMetrics();
		((WindowManager) aContext.getSystemService(Context.WINDOW_SERVICE)).
		getDefaultDisplay().getMetrics(theDisplayMetrics);
		return (int) (aDp * theDisplayMetrics.density + 0.5);
	}

	public static void makeToast(String aString) {
		makeToast(aString, Toast.LENGTH_LONG, Gravity.CENTER);
	}

	public static void makeToast(String aString, int aDuration) {
		makeToast(aString, aDuration, Gravity.CENTER);
	}

	public static void makeToast(String aString, int aGravity, int aDuration) {
		Toast theToast = Toast.makeText(GcgApplication.getContext(), aString, aDuration);
		theToast.setGravity(aGravity, 0, 0);
		theToast.show();
	}

//	public static MotionEvent getGenericMotionEventActionUp() {
//		if(GcgHelper.genericMotionEventActionUp == null) {
//			GcgHelper.genericMotionEventActionUp = getGenericMotionEvent(MotionEvent.ACTION_UP);
//		}
//		return GcgHelper.genericMotionEventActionUp;
//	}

//	private static MotionEvent getGenericMotionEvent(int aMotionEvent) {
//		long downTime = SystemClock.uptimeMillis();
//		long eventTime = downTime + 100;
//		float x = 0.0f;
//		float y = 0.0f;
//		int metaState = 0;
//		MotionEvent motionEvent = MotionEvent.obtain(
//				downTime, 
//				eventTime, 
//				aMotionEvent, 
//				x, 
//				y, 
//				metaState
//				);
//		return motionEvent;
//	}

//	public void alertDialog(
//			Context aContext,
//			GcgDialogType aDialogType,
//			String aTitle,
//			String aMessage) {
//		Builder theAlertDialog = new AlertDialog.Builder(aContext);
//		theAlertDialog.setTitle(aTitle);
//		theAlertDialog.setMessage(aMessage);
//		theAlertDialog.setIcon(GcgDialogType.CONFIRMATION.getDrawableResourceId());
//		theAlertDialog.show();
//	}

	public static boolean isLandscapeMode() {
		return GcgApplication.getContext().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
	}

	public static boolean notArrowKeys(int aKeyCode) {
		if(
				aKeyCode==KeyEvent.KEYCODE_DPAD_DOWN ||
				aKeyCode==KeyEvent.KEYCODE_DPAD_UP ||
				aKeyCode==KeyEvent.KEYCODE_DPAD_LEFT ||
				aKeyCode==KeyEvent.KEYCODE_DPAD_RIGHT) {
			return false;
		}
		return true;
	}

	public static OnTouchListener getFlingListener(Context aContext, final GcgFlingController aFlingController) {
		final GestureDetector theFlingDetector = new GestureDetector(aContext, new GcgFlingListener(aFlingController));
		return new OnTouchListener() {

			@Override
			public boolean onTouch(View aView, MotionEvent aMotionEvent) {
				return theFlingDetector.onTouchEvent(aMotionEvent);
			}
		};
	}

	public static OnTouchListener getTouchFlipListener(
			Context aContext, final GcgViewFlipper aGcgViewFlipper, boolean bFlipNext) {
		final GestureDetector theTouchFlipDetector =
				new GestureDetector(aContext, new GcgTouchFlipListener(aGcgViewFlipper, bFlipNext));
		return new OnTouchListener() {

			@Override
			public boolean onTouch(View aView, MotionEvent aMotionEvent) {
				return theTouchFlipDetector.onTouchEvent(aMotionEvent);
			}
		};
	}

	public static int getSwipeMinimumDistance() {
		if(GcgHelper.touchSlop == 0) {
			ViewConfiguration theViewConfiguration = ViewConfiguration.get(GcgApplication.getContext());
			GcgHelper.touchSlop = theViewConfiguration.getScaledTouchSlop() * 15;
		}
		return GcgHelper.touchSlop;
	}

	public static int getSwipeMinimumVelocity() {
		if(GcgHelper.minimumTouchVelocity == 0) {
			ViewConfiguration theViewConfiguration = ViewConfiguration.get(GcgApplication.getContext());
			GcgHelper.minimumTouchVelocity = theViewConfiguration.getScaledMinimumFlingVelocity();
		}
		return GcgHelper.minimumTouchVelocity;
	}

	public static int getScreenHeight() {
		DisplayMetrics theDisplayMetrics = GcgApplication.getContext().getResources().getDisplayMetrics();
		return theDisplayMetrics.heightPixels;
	}

	public static int getScreenWidth() {
		DisplayMetrics theDisplayMetrics = GcgApplication.getContext().getResources().getDisplayMetrics();
		return theDisplayMetrics.widthPixels;
	}

	public static <T extends View> boolean isViewFullyVisibleInScrollView(T aView, ScrollView aScrollView) {
		Rect theScrollViewRect = new Rect();
		aScrollView.getHitRect(theScrollViewRect);
		if (!aView.getLocalVisibleRect(theScrollViewRect) || theScrollViewRect.height() < aView.getHeight()) {
			return false;
		}
		return true;
	}

	public static <T extends View> void keepViewFullyVisibleInScrollView(T aView, ScrollView aScrollView) {
		if(isViewFullyVisibleInScrollView(aView, aScrollView)) {
			return;
		}
		if(aView.getTop() < aScrollView.getTop()) {
			aScrollView.smoothScrollTo(0, aView.getTop());
		} else {
			aScrollView.smoothScrollTo(0, aView.getBottom());
		}
	}

	public static int getIntegerFromKeycode(int aKeycode) {
		switch (aKeycode) {
		case KeyEvent.KEYCODE_0:
		case KeyEvent.KEYCODE_NUMPAD_0:
			return 0;
		case KeyEvent.KEYCODE_1:
		case KeyEvent.KEYCODE_NUMPAD_1:
			return 1;
		case KeyEvent.KEYCODE_2:
		case KeyEvent.KEYCODE_NUMPAD_2:
			return 2;
		case KeyEvent.KEYCODE_3:
		case KeyEvent.KEYCODE_NUMPAD_3:
			return 3;
		case KeyEvent.KEYCODE_4:
		case KeyEvent.KEYCODE_NUMPAD_4:
			return 4;
		case KeyEvent.KEYCODE_5:
		case KeyEvent.KEYCODE_NUMPAD_5:
			return 5;
		case KeyEvent.KEYCODE_6:
		case KeyEvent.KEYCODE_NUMPAD_6:
			return 6;
		case KeyEvent.KEYCODE_7:
		case KeyEvent.KEYCODE_NUMPAD_7:
			return 7;
		case KeyEvent.KEYCODE_8:
		case KeyEvent.KEYCODE_NUMPAD_8:
			return 8;
		case KeyEvent.KEYCODE_9:
		case KeyEvent.KEYCODE_NUMPAD_9:
			return 9;
		default:
			return 0;
		}
	}

	public static int getSwipeMaxOffPath() {
		return 250;
	}

	public static void initializeViewFlingListener(Context aContext, GcgViewFlipper aViewFlipper, final View aView) {
		aView.setOnTouchListener(GcgHelper.getFlingListener(aContext, aViewFlipper));
	}

	public static Drawable getDrawable(int aDrawableResourceId) {
		return GcgApplication.getAppResources().getDrawable(aDrawableResourceId);
	}

	public static Resources getAppResources() {
		return GcgApplication.getAppResources();
	}

	public static void setDrawableLeft(TextView aTextView, Drawable aDrawable) {
		if(aDrawable == null) {
			return;
		}
		aDrawable.setBounds( 0, 0, aDrawable.getIntrinsicWidth(), aDrawable.getIntrinsicHeight() );
		aTextView.setCompoundDrawablesWithIntrinsicBounds( aDrawable, null, null, null );
	}

	public static void animateViewBackgroundTransitionHorizontal(final View aView, final int aNewBackgroundResourceId) {
		animateViewBackgroundTransitionHorizontal(aView, aNewBackgroundResourceId, 100, 10);
	}

	public static void animateViewBackgroundTransitionHorizontal(final View aView, final int aNewBackgroundResourceId, final int aCoveragePercentage, final int aDuration) {
		Animation theAnimation = new Animation(){

			@Override
			protected void applyTransformation(float anInterpolatedTime, Transformation aTransformation) {
				super.applyTransformation(anInterpolatedTime, aTransformation);
				aView.setBackgroundResource(aNewBackgroundResourceId);
				LayoutParams theLayoutParams = aView.getLayoutParams();
				theLayoutParams.width = (int)(aCoveragePercentage * anInterpolatedTime);
				aView.setLayoutParams(theLayoutParams);
			}};
			theAnimation.setDuration(aDuration);
			aView.startAnimation(theAnimation);
	}

	public static void delayedViewBackgroundUpdate(final View aView, final int aBackgroundResourceId, final int aDelayInterval) {
		final Handler theHandler = new Handler();
		final Runnable theRunnable = new Runnable() {

			@Override
			public void run() {
				aView.setBackgroundResource(aBackgroundResourceId);
			}
		};
		Timer theTimer = new Timer();
		theTimer.schedule(new TimerTask() {

			@Override
			public void run() {
				theHandler.post(theRunnable);
			}
		}, 0, aDelayInterval);

	}
	
	public static String getColoredHtmlString(String aString, String aHexColor) {
		return "<font color='#" + aHexColor + "'>" + aString + "</font>";
	}
	
	public static Spanned getLabelHintHtmlString(String aString) {
		return Html.fromHtml("<font color='#666666'>" + aString + "</font>");
	}
	
	public static JSONObject getGcgGuiableJsonObject(GcgGuiable aGuiable) {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(GcgGuiableImpl.key__LABEL_TEXT, aGuiable.getLabelText());
			theJsonObject.put(GcgGuiableImpl.key__LABEL_DRAWABLE_RESOURCE_ID, aGuiable.getLabelDrawableResourceId());
			theJsonObject.put(GcgGuiableImpl.key__DATA_TEXT, aGuiable.getDataText());
			theJsonObject.put(GcgGuiableImpl.key__DATA_DRAWABLE_RESOURCE_ID, aGuiable.getDataDrawableResourceId());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}
	
	public static int getBestDrawableResourceId(GcgGuiable theGuiable) {
		int theResourceId = theGuiable.getDataDrawableResourceId();
		if(theResourceId == 0 || theResourceId == R.drawable.gcg__null_drawable) {
			theResourceId = theGuiable.getLabelDrawableResourceId();
		}
		return theResourceId;
	}
	
	private static View inflateView(Context aContext, int aLayoutResourceId, ViewGroup aViewGroupParent) {
		LayoutInflater.from(aContext).inflate(aLayoutResourceId, aViewGroupParent);
		View theView = aViewGroupParent.getChildAt(aViewGroupParent.getChildCount() - 1);
		return theView;
	}
	
	public static TextView inflateTextView(Context aContext, int aLayoutResourceId, ViewGroup aViewGroupParent) {
		return (TextView) inflateView(aContext, aLayoutResourceId, aViewGroupParent);
	}
	
	public static ImageView inflateImagetView(Context aContext, int aLayoutResourceId, ViewGroup aViewGroupParent) {
		return (ImageView) inflateView(aContext, aLayoutResourceId, aViewGroupParent);
	}
	
	public static EditText inflateEditText(Context aContext, int aLayoutResourceId, ViewGroup aViewGroupParent) {
		return (EditText) inflateView(aContext, aLayoutResourceId, aViewGroupParent);
	}
	
	public static Spinner inflateSpinner(Context aContext, int aLayoutResourceId, ViewGroup aViewGroupParent) {
		return (Spinner) inflateView(aContext, aLayoutResourceId, aViewGroupParent);
	}
	
	public static LinearLayout inflateLinearLayout(Context aContext, int aLayoutResourceId, ViewGroup aViewGroupParent) {
		return (LinearLayout) inflateView(aContext, aLayoutResourceId, aViewGroupParent);
	}
	
	public static RelativeLayout inflateRelativeLayout(Context aContext, int aLayoutResourceId, ViewGroup aViewGroupParent) {
		return (RelativeLayout) inflateView(aContext, aLayoutResourceId, aViewGroupParent);
	}
	
	public static FrameLayout inflateFrameLayout(Context aContext, int aLayoutResourceId, ViewGroup aViewGroupParent) {
		return (FrameLayout) inflateView(aContext, aLayoutResourceId, aViewGroupParent);
	}
	
	public static TableLayout inflateTableLayout(Context aContext, int aLayoutResourceId, ViewGroup aViewGroupParent) {
		return (TableLayout) inflateView(aContext, aLayoutResourceId, aViewGroupParent);
	}
	
	public static void enableNavigationButton(Button aButton, boolean aBoolean) {
		aButton.setEnabled(aBoolean);
		aButton.setVisibility(aBoolean ? View.VISIBLE : View.INVISIBLE);
	}
	
}
