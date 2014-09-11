/* @(#)FmmTeamRepository.java
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

package com.flywheelms.library.fmm.repository;

import com.flywheelms.library.fmm.persistence.PersistenceServiceProvider;

import org.json.JSONException;
import org.json.JSONObject;

public class FmmConfigurationTeam extends FmmConfiguration {
	
	private static final long serialVersionUID = 1L;
	private static final String attribute__USER_NAME = "userName";
	private static final String attribute__PASSWORD = "password";
	private static final String attribute__URI_PREFIX = "uriPrefix";
	private static final String attribute__URI_SUFFIX = "uriSuffix";
	private String userName;
	private String password;
	private String uriPrefix;
	private String uriSuffix;

//	public FmmTeamRepository(String aNodeIdString, String aHeadline) {
//		super(aNodeIdString, aHeadline);
//	}
	
	public FmmConfigurationTeam(String aHeadline, PersistenceServiceProvider aPersistenceServiceProvider) {
		super(aHeadline);
		this.accessScope = FmmAccessScope.TEAM;
		this.persistenceServiceProvider = aPersistenceServiceProvider;
	}

	public FmmConfigurationTeam(JSONObject aJsonObject) {
		super(aJsonObject);
		try {
			setUserName(aJsonObject.getString(attribute__USER_NAME));
			setPassword(aJsonObject.getString(attribute__PASSWORD));
			setUriPrefix(aJsonObject.getString(attribute__URI_PREFIX));
			setUriSuffix(aJsonObject.getString(attribute__URI_SUFFIX));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// used by CreateFmmConfigurationWizard
	public FmmConfigurationTeam(String aHeadline) {
		super(aHeadline);
		this.accessScope = FmmAccessScope.TEAM;
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(attribute__USER_NAME, getUserName());
			theJsonObject.put(attribute__PASSWORD, getPassword());
			theJsonObject.put(attribute__URI_PREFIX, getUriPrefix());
			theJsonObject.put(attribute__URI_SUFFIX, getUriSuffix());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUri() {
		String theUri = getPersistenceServiceProvider().getUriUndecorated();
		if(getUriPrefix() != null) {
			theUri += getUriPrefix();
		}
		if(getUriSuffix() != null) {
			theUri += getUriSuffix();
		}
		return theUri;
	}
	
	public String getUriPrefix() {
		return this.uriPrefix;
	}

	public void setUriPrefix(String aUriPrefix) {
		this.uriPrefix = aUriPrefix;
	}

	public String getUriSuffix() {
		return this.uriSuffix;
	}
	
	public void setUriSuffix(String aUriSuffix) {
		this.uriPrefix = aUriSuffix;
	}

}
