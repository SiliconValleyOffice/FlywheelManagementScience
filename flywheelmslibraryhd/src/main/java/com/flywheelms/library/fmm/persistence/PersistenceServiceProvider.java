/* @(#)PersistenceServiceProvider.java
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

package com.flywheelms.library.fmm.persistence;

import java.util.ArrayList;
import java.util.Arrays;

import android.graphics.drawable.Drawable;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.repository.FmmAccessScope;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.interfaces.GcgGuiable;

public enum PersistenceServiceProvider implements GcgGuiable {
	
	AMAZON ("Amazon.com", PersistenceTechnology.MYSQL, R.drawable.amazon__16, "", ""),
	ANDROID ("Android", PersistenceTechnology.SQLITE, R.drawable.android__16, "", ""),
	DROPBOX ("Dropbox", PersistenceTechnology.SQLITE, R.drawable.dropbox__16, "", ""),
	FAT_COW ("Fat Cow", PersistenceTechnology.MYSQL, R.drawable.gcg__unspecified_glyph, "", "http://www.fatcow.com/fatcow/special-promo.bml?LinkName=No_Name"),
	HOSTIGATOR ("Hostigator", PersistenceTechnology.MYSQL, R.drawable.gcg__unspecified_glyph, "", "http://www.hostgator.com/promo/hostingreview"),
	IPAGE ("iPage", PersistenceTechnology.MYSQL, R.drawable.gcg__unspecified_glyph, "", "http://www.ipage.com/ipage/index.html?source=ELI&PPCPN="),
	JUST_HOST ("Just Host", PersistenceTechnology.MYSQL, R.drawable.gcg__unspecified_glyph, "", "http://www.justhost.com/"),
	YAHOO ("Yahoo", PersistenceTechnology.MYSQL, R.drawable.gcg__unspecified_glyph, "", "http://smallbusiness.yahoo.com/webhosting/getwebhostingfiveyear/?p=CABIN50&s_affiliate=wh_acq_q22013_50off5yr175_20130410&AID=11421207&PID=1238355&SID=");
	
	private static final ArrayList<GcgGuiable> guiableList = new ArrayList<GcgGuiable>(Arrays.asList(values()));
//	static {
//		guiableList.add(AMAZON);
//		guiableList.add(ANDROID);
//		guiableList.add(DROPBOX);
//		guiableList.add(FAT_COW);
//		guiableList.add(HOSTIGATOR);
//		guiableList.add(IPAGE);
//		guiableList.add(JUST_HOST);
//		guiableList.add(YAHOO);
//	}
	
	public static ArrayList<GcgGuiable> getGuiableList() {
		return PersistenceServiceProvider.guiableList;
	}
	
	public static ArrayList<GcgGuiable> getGuiableList(FmmAccessScope anAccessScope) {
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>(getProviderForScope(anAccessScope));
		return theGuiableList;
	}
	
	public static PersistenceServiceProvider getObjectForName(String aName) {
		for(PersistenceServiceProvider theServiceProvider : values()) {
			if(theServiceProvider.getName().equals(aName)) {
				return theServiceProvider;
			}
		}
		return null;
	}
	
	@SuppressWarnings("incomplete-switch")
	public static ArrayList<PersistenceServiceProvider> getProviderForScope(FmmAccessScope anAccessScope) {
		ArrayList<PersistenceServiceProvider> theProviderList = new ArrayList<PersistenceServiceProvider>();
		switch(anAccessScope) {
			case PRIVATE:
				theProviderList.add(ANDROID);
				break;
			case SHARED:
				theProviderList.add(DROPBOX);
				break;
			case TEAM:
				theProviderList.add(AMAZON);
				theProviderList.add(FAT_COW);
				theProviderList.add(HOSTIGATOR);
				theProviderList.add(IPAGE);
				theProviderList.add(JUST_HOST);
				theProviderList.add(YAHOO);
				break;
		}
		return theProviderList;
	}
	
	private final String name;
	private final PersistenceTechnology persistenceTechnology;
	private String uriBase;
	private final String signupPageUriString;
	private final int drawableResourceId;

	PersistenceServiceProvider(
			String aName,
			PersistenceTechnology aDatabaseTechnology,
			int aDrawableResourceId,
			String aUriBase,
			String aSignupPageUriString ) {
		this.name = aName;
		this.persistenceTechnology = aDatabaseTechnology;
		this.drawableResourceId = aDrawableResourceId;
		this.uriBase = aUriBase;
		this.signupPageUriString = aSignupPageUriString;
	}

	public PersistenceTechnology getPersistenceTechnology() {
		return this.persistenceTechnology;
	}

	public String getUriUndecorated() {
		return this.uriBase;
	}

	public void setUriBase(String uriBase) {
		this.uriBase = uriBase;
	}

	public String getName() {
		return this.name;
	}

	public PersistenceTechnologyDelegate getPersistenceTechnologyDelegate() {
		return this.persistenceTechnology.getPersistenceTechnologyDelegate();
	}
	
	public String getSignupPageUriString() {
		return this.signupPageUriString;
	}
	
	public int getDrawableResourceId() {
		return this.drawableResourceId;
	}

	@Override
	public String getLabelText() {
		return "Service Provider";
	}

	@Override
	public Drawable getLabelDrawable() {
		return GcgHelper.getDrawable(R.drawable.persistence_service_provider);
	}

	@Override
	public int getLabelDrawableResourceId() {
		return R.drawable.persistence_service_provider;
	}

	@Override
	public int getDataDrawableResourceId() {
		return getDrawableResourceId();
	}

	@Override
	public String getDataText() {
		return this.name;
	}

	@Override
	public Drawable getDataDrawable() {
		return GcgHelper.getDrawable(getDrawableResourceId());
	}

}
