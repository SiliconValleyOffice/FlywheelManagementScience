/* @(#)GcgNavigationTarget.java
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

import com.flywheelms.library.fmm.context.GcgImplementationHelper;
import com.flywheelms.library.gcg.interfaces.GcgPerspective;

import org.json.JSONException;
import org.json.JSONObject;

public class GcgNavigationTarget {

	private int drawableResourceId;
	private String headline;
	private boolean isPerspective;
	private boolean isContextRoot;
	private GcgActivityBreadcrumb gcgActivityBreadcrumb;
	public static final String bundle_key__GCG_NAVIGATION_TARGET = "GcgNavigationTarget";
	public static final String bundle_key__DRAWABLE_RESOURCE_ID = "DrawableResourceId";
	public static final String bundle_key__HEADLINE = "Headline";
	public static final String bundle_key__IS_PERSPECTIVE = "IsPerspective";
	public static final String bundle_key__IS_CONTEXT_ROOT = "IsContextRoot";
	public static final String bundle_key__GCG_APPLICATION_BREADCRUMB = "GcgApplicationContextBreadcrumb";
	public static final int request_code__NAVIGATE = 131313;
	
	public GcgNavigationTarget(int aDrawableResourceId, String aHeadline, boolean bPerspective, boolean bContextRoot) {
		this(aDrawableResourceId, aHeadline, bPerspective, bContextRoot, null);
	}
	
	public GcgNavigationTarget(int aDrawableResourceId, String aHeadline, boolean bPerspective, boolean bContextRoot, GcgActivityBreadcrumb anActivityBreadcrumb) {
		this.drawableResourceId = aDrawableResourceId;
		this.headline = aHeadline;
		this.isPerspective = bPerspective;
		this.isContextRoot = bContextRoot;
		this.gcgActivityBreadcrumb = anActivityBreadcrumb;
	}
	
	public GcgNavigationTarget(JSONObject aJsonObject) {
		try {
			this.drawableResourceId = aJsonObject.getInt(bundle_key__DRAWABLE_RESOURCE_ID);
			this.headline = aJsonObject.getString(bundle_key__HEADLINE);
			this.isPerspective = aJsonObject.getBoolean(bundle_key__IS_PERSPECTIVE);
			this.isContextRoot = aJsonObject.getBoolean(bundle_key__IS_CONTEXT_ROOT);
			JSONObject theJsonObject = aJsonObject.getJSONObject(bundle_key__GCG_APPLICATION_BREADCRUMB);
			if(theJsonObject != null) {
				this.gcgActivityBreadcrumb = new GcgActivityBreadcrumb(theJsonObject);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(bundle_key__DRAWABLE_RESOURCE_ID, this.drawableResourceId);
			theJsonObject.put(bundle_key__HEADLINE, this.headline);
			theJsonObject.put(bundle_key__IS_PERSPECTIVE, this.isPerspective);
			theJsonObject.put(bundle_key__IS_CONTEXT_ROOT, this.isContextRoot);
			if(this.gcgActivityBreadcrumb != null) {
				theJsonObject.put(bundle_key__GCG_APPLICATION_BREADCRUMB, this.gcgActivityBreadcrumb.getJsonObject());
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public int getDrawableResourceId() {
		return this.drawableResourceId;
	}

	public String getHeadline() {
		return this.headline;
	}

	public boolean isPerspective() {
		return this.isPerspective;
	}

	public boolean isContextRoot() {
		return this.isContextRoot;
	}

	public GcgActivityBreadcrumb getGcgApplicationContextBreadcrumb() {
		return this.gcgActivityBreadcrumb;
	}

	public GcgPerspective getGcgPerspective() {
		if(! isPerspective()) {
			return null;
		}
		return GcgImplementationHelper.getPerspectiveObjectForName(this.headline);
	}
	
}