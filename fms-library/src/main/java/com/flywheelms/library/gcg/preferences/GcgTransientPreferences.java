/* @(#)GcgTransientPreferences.java
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

package com.flywheelms.library.gcg.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class GcgTransientPreferences implements SharedPreferences {
	
	private Bundle bundle;
	
	public GcgTransientPreferences() {
		this.bundle = new Bundle();
	}

	@Override
	public Map<String, ?> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getString(String aKey, String aDefaultValue) {
		return this.bundle.getString(aKey, aDefaultValue);
	}

	@Override
	public Set<String> getStringSet(String aKey, Set<String> aDefaultValueSet) {
		String[] theStringArray = this.bundle.getStringArray(aKey);
		return theStringArray == null ? aDefaultValueSet : new HashSet<String>(Arrays.asList(theStringArray));
	}

	public void setString(String aKey, String aString) {
		this.bundle.putString(aKey, aString);
	}

	@Override
	public int getInt(String aKey, int aDefaultValue) {
		return this.bundle.getInt(aKey, aDefaultValue);
	}

	public void setInt(String aKey, int aInt) {
		this.bundle.putInt(aKey, aInt);
	}

	@Override
	public long getLong(String aKey, long aDefaultValue) {
		return this.bundle.getLong(aKey, aDefaultValue);
	}

	public void setLong(String aKey, long aLong) {
		this.bundle.putLong(aKey, aLong);
	}

	@Override
	public float getFloat(String aKey, float aDefaultValue) {
		return this.bundle.getFloat(aKey, aDefaultValue);
	}

	public void setFloat(String aKey, float aFloat) {
		this.bundle.putFloat(aKey, aFloat);
	}

	@Override
	public boolean getBoolean(String aKey, boolean aDefaultValue) {
		return this.bundle.getBoolean(aKey, aDefaultValue);
	}

	public void setBoolean(String aKey, boolean aBoolean) {
		this.bundle.putBoolean(aKey, aBoolean);
	}

	@Override
	public boolean contains(String aKey) {
		return this.bundle.containsKey(aKey);
	}

	@Override
	public Editor edit() {
		return null;
	}

	@Override
	public void registerOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {  return;  }

	@Override
	public void unregisterOnSharedPreferenceChangeListener(OnSharedPreferenceChangeListener listener) {  return;  }

}
