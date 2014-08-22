/* @(#)GcgFrameBreadcrumb.java
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

package com.flywheelms.library.gcg.context;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.drawable.Drawable;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.gcg.interfaces.GcgPerspective;

public class GcgFrameBreadcrumb extends GcgContextBreadcrumb {

	private static final String key__PERSPECTIVE_BREADCRUMB = "PerspectiveBreadcrumb";
	private static final String key__PERSPECTIVE_CONTEXT = "PerspectiveContext";
	@SuppressWarnings("hiding")
	public static final GcgFrameBreadcrumb NULL = new GcgFrameBreadcrumb(R.drawable.gcg__null_drawable, "");
	private GcgContextBreadcrumb perspectiveBreadcrumb = GcgContextBreadcrumb.NULL;
	private ArrayList<GcgContextBreadcrumb> perspectiveContext = new ArrayList<GcgContextBreadcrumb>();

	public GcgFrameBreadcrumb(int aBreadcrumbDrawableResourceId, String aBreadcrumbText) {
		super(aBreadcrumbDrawableResourceId, aBreadcrumbText);
	}
	
	public GcgFrameBreadcrumb(JSONObject aJsonObject) {
		super(aJsonObject);
		try {
			this.perspectiveBreadcrumb = new GcgContextBreadcrumb(
					aJsonObject.getJSONObject(key__PERSPECTIVE_BREADCRUMB));
			JSONArray theNodeArray = aJsonObject.getJSONArray(key__PERSPECTIVE_CONTEXT);
			for(int i=0; i < theNodeArray.length(); i++){                                     
				JSONObject theJsonObject = theNodeArray.getJSONObject(i);
				this.perspectiveContext.add(new GcgContextBreadcrumb(theJsonObject));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}         
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(key__PERSPECTIVE_BREADCRUMB, this.perspectiveBreadcrumb.getJsonObject());
			JSONArray theJsonArray = new JSONArray();
			for (GcgContextBreadcrumb theContextBreadcrumb : this.perspectiveContext) {
				theJsonArray.put(theContextBreadcrumb.getJsonObject());
			}
			theJsonObject.put(key__PERSPECTIVE_CONTEXT, theJsonArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public GcgContextBreadcrumb getPerspectiveBreadcrumb() {
		return this.perspectiveBreadcrumb;
	}

	public void setPerspectiveBreadcrumb(GcgContextBreadcrumb perspectiveBreadcrumb) {
		this.perspectiveBreadcrumb = perspectiveBreadcrumb;
	}

	public ArrayList<GcgContextBreadcrumb> getPerspectiveContext() {
		return this.perspectiveContext;
	}
	
	public int getFrameDrawableResourceId() {
		return getDrawableResourceId();
	}
	
	public Drawable getFrameDrawable() {
		return getDrawable();
	}

	public CharSequence getFrameName() {
		return getText();
	}
	
	public int getPerspectiveDrawableResourceId() {
		return this.perspectiveBreadcrumb.getDrawableResourceId();
	}
	
	public Drawable getPerspectiveDrawable() {
		return this.perspectiveBreadcrumb.getDrawable();
	}

	public CharSequence getPerspectiveName() {
		return this.perspectiveBreadcrumb.getText();
	}
	
	public boolean hasPerspectiveContext() {
		return this.perspectiveContext.size() > 0;
	}

	public void setPerspective(GcgPerspective aGcgPerspective) {
		this.perspectiveBreadcrumb = new GcgContextBreadcrumb(aGcgPerspective.getIconDrawableResourceId(), aGcgPerspective.getName());
	}

	public void setPerspectiveContext(ArrayList<GcgGuiable> aGcgGuiableList) {
		this.perspectiveContext = new ArrayList<GcgContextBreadcrumb>();
		if(aGcgGuiableList == null) {
			return;
		}
		for(GcgGuiable theGcgGuiable : aGcgGuiableList) {
			this.perspectiveContext.add(new GcgContextBreadcrumb(
				theGcgGuiable.getDataDrawableResourceId(), theGcgGuiable.getDataText() ));
		}
	}

	public boolean hasPerspectiveBreadcrumb() {
		return this.perspectiveBreadcrumb != null;
	}

	public boolean hasPerspective() {
		return this.perspectiveBreadcrumb != null && this.perspectiveBreadcrumb != GcgContextBreadcrumb.NULL;
	}
	
}
