/* @(#)GcgApplication.java
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

package com.flywheelms.gcongui.gcg;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.media.AudioManager;
import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.inputmethod.InputMethodManager;

import com.flywheelms.gcongui.gcg.context.GcgFrame;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public abstract class GcgApplication extends Application {

	private static GcgApplication instance;
	protected int majorRelease = 0;
	protected int minorRelease = 0;
	protected int incrementalRelease = 0;
	private boolean automaticallySaveDataModifications = false;

	public GcgApplication() {
		GcgApplication.instance = this;
		setMajorRelease();
		setMinorRelease();
		setIncrementalRelease();
	}
	
	public static AudioManager getAudioManager() {
		return (AudioManager) GcgApplication.instance.getSystemService(Context.AUDIO_SERVICE);
	}
	
	public static void setAudioManagerInCall() {
		getAudioManager().setMode(AudioManager.MODE_IN_CALL);
	}
	
	public static void setAudioManagerNormal() {
		getAudioManager().setMode(AudioManager.MODE_NORMAL);
	}

    public static PackageManager getAppPackageManager() {
        return GcgApplication.instance.getPackageManager();
    }

    public static AssetManager getAssetManager() {
        return GcgApplication.instance.getAssets();
    }
    
    public static int getIntegerResource(int aResourceId) {
    	return GcgApplication.instance.getResources().getInteger(aResourceId);
    }

	public static String getStringResource(int aResourceId) {
		return GcgApplication.instance.getResources().getString(aResourceId);
	}

	public static Context getContext() {
		return GcgApplication.instance;
	}
	
	public static LayoutInflater getLayoutInflater() {
		return LayoutInflater.from(GcgApplication.instance);
	}
	
	public static Resources getAppResources() {
		return GcgApplication.instance.getResources();
	}
	
	public static InputMethodManager getInputMethodManager() {
		return (InputMethodManager) GcgApplication.instance.getSystemService(Context.INPUT_METHOD_SERVICE);
	}
	
	public static GcgApplication getInstance() {
		return GcgApplication.instance;
	}
	
	public int getMajorRelease() {
		return this.majorRelease;
	}

	public int getMinorRelease() {
		return this.minorRelease;
	}

	public int getIncrementalRelease() {
		return this.incrementalRelease;
	}
	
	public boolean automaticallySaveDataModifications() {
		return this.automaticallySaveDataModifications;
	}
	
	public void setAutomaticallySaveDataModifications(boolean aBoolean) {
		this.automaticallySaveDataModifications = aBoolean;
	}

	public abstract void setMajorRelease();
	public abstract void setMinorRelease();
	public abstract void setIncrementalRelease();

    // must initialize in a static block of the sub-class
    protected static Class<? extends GcgApplication> subClass;

    private static Method getFrameListMethod;

    private static Method getFrameListMethod() {
        if(GcgApplication.getFrameListMethod == null) {
            try {
                GcgApplication.getFrameListMethod = GcgApplication.subClass.getMethod("getFrameList");  // Must implement in sub-class
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            }
        }
        return GcgApplication.getFrameListMethod;
    }

    // must implement static getFrameList() in sub-class
    public static ArrayList<GcgFrame> getAppFrameList() {
        Object theObject = null;
        try {
            theObject = getFrameListMethod().invoke(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
        return (ArrayList<GcgFrame>) theObject;
    }

    private static Method getPerspectiveListMethod;

    private static Method getPerspectiveListMethod() {
        if(GcgApplication.getPerspectiveListMethod == null) {
            try {
                GcgApplication.getPerspectiveListMethod = GcgApplication.subClass.getMethod("getPerspectiveList");  // Must implement in sub-class
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            }
        }
        return GcgApplication.getPerspectiveListMethod;
    }

    // must implement static getPerspectiveList() in sub-class
    public static ArrayList<GcgPerspective> getAppPerspectiveList() {
        Object theObject = null;
        try {
            theObject = getPerspectiveListMethod().invoke(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
        return (ArrayList<GcgPerspective>) theObject;
    }

//    public abstract Class getDecKanGlDictionaryClass();
//    public abstract <T extends DecKanGlDictionary> T getDecKanGlDefinition(String aName);

	public static void hideSoftInputFromWindow(IBinder aWindowToken) {
		GcgApplication.getInputMethodManager().hideSoftInputFromWindow(aWindowToken, 0);
	}

}