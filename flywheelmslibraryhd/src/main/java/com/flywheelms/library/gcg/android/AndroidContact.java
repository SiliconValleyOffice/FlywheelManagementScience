/* @(#)AndroidContact.java
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

package com.flywheelms.library.gcg.android;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.gcg.GcgActivity;

public class AndroidContact {

	private long _id;
	private String displayName = "";
	private String familyName = "";
	private String givenName = "";
	private String textMessageAddress = "";
	private String emailAddress = "";
	private String cellPhoneNumber = "";
	
	public AndroidContact(Activity anActivity, Intent anIntent) {
		Uri theData = anIntent.getData();
		Cursor theCursor = anActivity.getContentResolver().query(theData, null, null, null, null);
        if (theCursor.moveToFirst()) {
        	int theIndex = theCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        	if(theIndex != -1) {
        		this.displayName = theCursor.getString(theIndex);
        	}
        	theIndex = theCursor.getColumnIndex(BaseColumns._ID);
        	if(theIndex != -1) {
        		this._id = theCursor.getLong(theIndex);
        	}
        	this.emailAddress = getEmailAddress(anActivity, this._id + "");
    		this.cellPhoneNumber = getPhoneNumber(anActivity, theCursor, this._id + "");
        }
        theCursor.close();
	}
	
	private static String getEmailAddress(Activity anActivity, String aContactId) {
		String theEmailAddress = "";
		Cursor theCursor = anActivity.getContentResolver().query( 
				ContactsContract.CommonDataKinds.Email.CONTENT_URI, 
				null,
				ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", 
				new String[]{aContactId}, null ); 
		if (theCursor.moveToNext()) { 
			theEmailAddress = theCursor.getString(theCursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA));                            
		} 
		theCursor.close(); 
		return theEmailAddress;
	}
	
	private static String getPhoneNumber(Activity anActivity, Cursor aCursor, String aContactId) {
	    if (Integer.parseInt(aCursor.getString(aCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) <= 0) {
	    	return "";
	    }
		String thePhoneNumber = "";
		Cursor theCursor = anActivity.getContentResolver().query( 
				Phone.CONTENT_URI, 
				null,
				Phone.CONTACT_ID +" = ?", 
						new String[]{aContactId}, null); 
		if (theCursor.moveToNext()) { 
			thePhoneNumber = theCursor.getString(theCursor.getColumnIndex(Phone.NUMBER));                            
		} 
		theCursor.close(); 
		return thePhoneNumber;
	}
	
	public static void startContactPicker(GcgActivity anActivity ) {
		FmsActivityHelper.startContactPicker(anActivity);
	}
	
	public static void startContactPicker(GcgActivity anActivity, int aRequestCode ) {
		FmsActivityHelper.startContactPicker(anActivity, aRequestCode);
	}

	public static void startContactEditor(GcgActivity anActivity, long anId, int aRequestCode) {
		FmsActivityHelper.startContactEditor(anActivity, anId, aRequestCode);
	}
	
	public long getId() {
		return this._id;
	}

	public String getDisplayName() {
		return this.displayName;
	}

	public String getFamilyName() {
		return this.familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return this.givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getTextMessageAddress() {
		return this.textMessageAddress;
	}

	public void setTextMessageAddress(String textMessageAddress) {
		this.textMessageAddress = textMessageAddress;
	}

	public String getEmailAddress() {
		return this.emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getCellPhoneNumber() {
		return this.cellPhoneNumber;
	}

	public void setCellPhoneNumber(String cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}
	
	@Override
	public String toString() {
		return getDisplayName();
	}
	
	public void refreshFromContactsDatabase(Activity anActivity, Intent anIntent) {
		Uri theData = anIntent.getData();
		Cursor theCursor = anActivity.getContentResolver().query(theData, null, null, null, null);
        if (theCursor.moveToFirst()) {
        	int theIndex = theCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
        	if(theIndex != -1) {
        		this.displayName = theCursor.getString(theIndex);
        	}
        	theIndex = theCursor.getColumnIndex(BaseColumns._ID);
        	if(theIndex != -1) {
        		this._id = theCursor.getLong(theIndex);
        	}
        	this.emailAddress = getEmailAddress(anActivity, this._id + "");
    		this.cellPhoneNumber = getPhoneNumber(anActivity, theCursor, this._id + "");
        }
        theCursor.close();
        
//		Uri theContactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, this._id);

//		Cursor theCursor = anActivity.getContentResolver().query( 
//				Phone.CONTENT_URI, 
//				null,
//				Phone.CONTACT_ID +" = ?", 
//						new String[]{aContactId}, null); 
//		if (theCursor.moveToNext()) { 
//			thePhoneNumber = theCursor.getString(theCursor.getColumnIndex(Phone.NUMBER));                            
//		} 
//		theCursor.close();
//		Cursor theCursor = 
	}

}
