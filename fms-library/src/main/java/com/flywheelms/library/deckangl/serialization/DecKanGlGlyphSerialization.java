/* @(#)DecKanGlGlyphSerialization.java
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

package com.flywheelms.library.deckangl.serialization;

import com.flywheelms.library.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.library.deckangl.glyph.DecKanGlDictionary;
import com.flywheelms.library.deckangl.glyph.DecKanGlElementNoun;
import com.flywheelms.library.deckangl.glyph.DecKanGlElementNounState;
import com.flywheelms.library.deckangl.glyph.DecKanGlGlyph;
import com.flywheelms.library.deckangl.interfaces.DecKanGlDecorator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class DecKanGlGlyphSerialization {
	
	public static final String key__DECKANGL_GLYPH = "DecKanGlGlyph";
	
	public static final String key__NOUN_NAME = "NounName";
	public static final String key__WORK_STATE = "WorkState";
	public static final String key__DECORATOR_MAP = "DecoratorMap";
	
	public static final String key__DECORATOR_CANVAS_LOCATION = "DecoratorCanvasLocation";
	public static final String key__DECKANGL_DECORATOR_NAME = "DecKanGlDecoratorName";
	public static final String key__DECKANGL_DECORATOR_ENUMERATION_NAME = "DecKanGlDecoratorEnumerationName";
	
	public static DecKanGlGlyph getDecKanGlGlyph(JSONObject aJsonObject, DecKanGlDictionary aDecKanGlDictionary) {
		String theNounName = null;
		String theWorkStatusColorName = null;
		HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> theDecoratorMap = null;
		try {
			theNounName = aJsonObject.getString(key__NOUN_NAME);
			theWorkStatusColorName = aJsonObject.getString(key__WORK_STATE);
			theDecoratorMap = getDecoratorMap(aJsonObject.getJSONArray(key__DECORATOR_MAP), aDecKanGlDictionary);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DecKanGlElementNoun theDecKanGlElementNoun = aDecKanGlDictionary.getNoun(theNounName);
		DecKanGlElementNounState theDecKanGlElementNounState = aDecKanGlDictionary.getNounState(theWorkStatusColorName);
		DecKanGlGlyph theDecKanGlGlyph = new DecKanGlGlyph(
				theDecKanGlElementNoun,
				theDecKanGlElementNounState,
				theDecoratorMap );
		return theDecKanGlGlyph;
	}
	
	private static HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> getDecoratorMap(JSONArray aJsonArray, DecKanGlDictionary aDecKanGlDictionary) {
		HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> theDecoratorMap = new HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator>();
		try {
			for(int i=0; i < aJsonArray.length(); ++i) {
				JSONObject theJsonObject = aJsonArray.getJSONObject(i);
				DecKanGlDecoratorCanvasLocation theDecoratorCanvasLocation = DecKanGlDecoratorCanvasLocation.getObjectForName(theJsonObject.getString(key__DECORATOR_CANVAS_LOCATION));
				String theDecoratorClassName = theJsonObject.getString(key__DECKANGL_DECORATOR_NAME);
				@SuppressWarnings("unchecked")
				Class<? extends DecKanGlDecorator> theDecoratorClass = 
						(Class<? extends DecKanGlDecorator>) Class.forName(aDecKanGlDictionary.getDecoratorsPackageName() + "." + theDecoratorClassName);
				String theEnumerationName = theJsonObject.getString(key__DECKANGL_DECORATOR_ENUMERATION_NAME);
				Method theMethod = theDecoratorClass.getDeclaredMethod("getObjectForName", String.class);
				DecKanGlDecorator theDecKanGlDecorator = (DecKanGlDecorator) theMethod.invoke(null, theEnumerationName);
				theDecoratorMap.put(
						theDecoratorCanvasLocation, 
						theDecKanGlDecorator );
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theDecoratorMap;
	}

	public static JSONObject getJsonObject(DecKanGlGlyph aDecKanGlGlyph) {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(key__NOUN_NAME, aDecKanGlGlyph.getElementNoun().getName());
			theJsonObject.put(key__WORK_STATE, aDecKanGlGlyph.getElementNounState().getName());
			theJsonObject.put(key__DECORATOR_MAP, getDecoratorMapArray(aDecKanGlGlyph));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}

	private static JSONArray getDecoratorMapArray(DecKanGlGlyph aDecKanGlGlyph) {
		JSONArray theJsonArray = new JSONArray();
		HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> theDecoratorMap = aDecKanGlGlyph.getDecoratorMap();
		for(DecKanGlDecoratorCanvasLocation theDecoratorCanvasLocation : aDecKanGlGlyph.getDecoratorMap().keySet()) {
			JSONObject theJsonObject = new JSONObject();
			try {
				theJsonObject.put(key__DECORATOR_CANVAS_LOCATION, theDecoratorCanvasLocation);
				theJsonObject.put(key__DECKANGL_DECORATOR_NAME, theDecoratorMap.get(theDecoratorCanvasLocation).getClass().getSimpleName());
				theJsonObject.put(key__DECKANGL_DECORATOR_ENUMERATION_NAME, theDecoratorMap.get(theDecoratorCanvasLocation).getName());
				theJsonArray.put(theJsonObject);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return theJsonArray;
	}

}
