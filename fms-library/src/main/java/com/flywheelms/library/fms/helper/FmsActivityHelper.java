/* @(#)FmsActivityHelper.java
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

package com.flywheelms.library.fms.helper;

import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Contacts;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.context.FmmFrame;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.enumerator.FmmNodeTransactionType;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.repository.FmmAccessScope;
import com.flywheelms.library.fms.activity.CreateFmmWizard;
import com.flywheelms.library.fms.activity.DecKanGlDictionaryActivity;
import com.flywheelms.library.fms.activity.FiscalYearEditorActivity;
import com.flywheelms.library.fms.activity.PdfPublicationWizard;
import com.flywheelms.library.fse.activity.FseDocumentHistoryBrowserActivity;
import com.flywheelms.library.gcg.GcgApplication;
import com.flywheelms.library.gcg.activity.GcgActivity;
import com.flywheelms.library.gcg.helper.GcgHelper;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

public class FmsActivityHelper {

	public static final String bundle_key__LIST_ACTION_LABEL = "ListActionLabel";
	public static final String bundle_key__INITIAL_NODE_TO_DISPLAY = "InitialNodeToDisplay";
	public static final String bundle_key__INITIAL_FRAME_TO_DISPLAY = "InitialFrameToDisplay";
	public static final String bundle_key__INITIAL_PERSPECTIVE_TO_DISPLAY = "InitialPerspectiveToDisplay";
	public static final String bundle_key__FSE_DOCUMENT_NODE_ID = "FseDocumentNodeId";
	public static final String bundle_key__FSE_DOCUMENT_TRANSACTION_INDEX = "FseDocumentTransactionIndex";
	public static final String bundle_key__NAVIGATION_NODE_ID_LIST = "NavigationNodeIdList";
	public static final String bundle_key__FMM_HEADLINE_NODE_SHALLOW_LIST = "FmmHeadlineNodeShallowList";
	public static final String bundle_key__MODIFIED_FMM_NODE__MAP = "ModifiedFmmNodeIdList";
	public static final String bundle_key__NODE_ID_EXLUSION_LIST = "NodeIdExclusionList";
	public static final String bundle_key__INITIAL_WHERE_CLAUSE = "WhereClause";
	public static final String bundle_key__MUST_SELECT_DATA_SOURCE = "MustSelectDataSource";
	public static final String bundle_key__NAVIGATION_PARENT_NODE_ID = "NavigationParentNodeId";
	public static final String bundle_key__NAVIGATION_PARENT_CLASS_NAME = "NavigationParentClassName";
	public static final String bundle_key__FMS_CONTEXT = "FmsApplicationContext";
	public static final String bundle_key__FMM_CONFIGURATION_SCOPE = "FmmAccessScope";
	public static final String bundle_key__FMM_NODE__NAME = "FmmNodeName";  // TODO - duplicate of CLASS_NAME ???
	public static final String bundle_key__FMM_NODE__CLASS_NAME = "FmmNodeClassName";
	public static final String bundle_key__FMM_NODE__TYPE_CODE = "FmmNodeTypeCode";
	public static final String bundle_key__FMM_NODE__ID_STRING = "FmmNodeIdString";
	public static final String bundle_key__FSE_DOCUMENT = "FseDocument";
	public static final String bundle_key__FSE_DOCUMENT_SECTION_TYPE = "FseDocumentSectionType";
	public static final String bundle_key__DISPLAYED_FSE_DOCUMENT = "DisplayedFseDocument";
	public static final String bundle_key__FSE_DOCUMENT_VIEW = "FseDocumentView";
	public static final String bundle_key__DATA_REFRESH__ALL = "DataRefreshAll";
	public static final String bundle_key__DATA_REFRESH__NOTICE_LIST = "DataRefreshNoticeList";
	public static final int request_code__ANDROID_CONTACT_PICKER = 100;
	public static final int request_code__ANDROID_CONTACT_EDITOR = 200;
	public static final int request_code__ANDROID_CONTACT_PICKER_FOR_SPONSOR = GcgApplication.getIntegerResource(R.integer.request_code__android_contact_picker__sponsor);
	public static final int request_code__ANDROID_CONTACT_PICKER_FOR_CUSTOMER = GcgApplication.getIntegerResource(R.integer.request_code__android_contact_picker__customer);
	public static final int request_code__ANDROID_CONTACT_PICKER_FOR_FACILITATOR = GcgApplication.getIntegerResource(R.integer.request_code__android_contact_picker__facilitator);
	public static final int request_code__ANDROID_CONTACT_PICKER_FOR_ADMINISTRATOR = GcgApplication.getIntegerResource(R.integer.request_code__android_contact_picker__administrator);
	public static final int request_code__ANDROID_CONTACT_PICKER_FOR_ORGANIZATION = GcgApplication.getIntegerResource(R.integer.request_code__android_contact_picker__organization);
	public static final int request_code__ANDROID_CONTACT_EDITOR_FOR_SPONSOR = GcgApplication.getIntegerResource(R.integer.request_code__android_contact_editor__sponsor);
	public static final int request_code__ANDROID_CONTACT_EDITOR_FOR_CUSTOMER = GcgApplication.getIntegerResource(R.integer.request_code__android_contact_editor__customer);
	public static final int request_code__ANDROID_CONTACT_EDITOR_FOR_FACILITATOR = GcgApplication.getIntegerResource(R.integer.request_code__android_contact_editor__facilitator);
	public static final int request_code__ANDROID_CONTACT_EDITOR_FOR_ADMINISTRATOR = GcgApplication.getIntegerResource(R.integer.request_code__android_contact_editor__administrator);
	public static final int request_code__ANDROID_CONTACT_EDITOR_FOR_ORGANIZATION = GcgApplication.getIntegerResource(R.integer.request_code__android_contact_editor__organization);
	public static final int request_code__PRINT = 111;
	public static final int request_code__PRINTER_INSTALLATION = 112;
	////  request codes for FMM nodes are defined in the FmmNodeDictionary entry for the node  ////
	private static final String class_path_ACTIVITY_PACKAGE = FiscalYearEditorActivity.class.getName().substring(0, 1 + FiscalYearEditorActivity.class.getName().lastIndexOf("."));

	public static void startDecKanGlDictionaryActivity(GcgActivity aParentActivity) {
		aParentActivity.startBlueActivityStatusAnimation();
		Intent theIntent = new Intent(aParentActivity, DecKanGlDictionaryActivity.class);
		aParentActivity.startActivity(theIntent);
	}

	public static void startHeadlineNodeEditorActivity(
			GcgActivity aParentActivity,
			ArrayList<FmmHeadlineNodeShallow> anFmmHeadlineNodeShallowList,
			String aNodeListParentNodeIdString,
			String anInitialNodeIdStringToDisplay ) {
		startHeadlineNodeEditorActivity(
				aParentActivity,
				anFmmHeadlineNodeShallowList,
				aNodeListParentNodeIdString,
				anInitialNodeIdStringToDisplay,
				null,
				null );
	}

	public static void startHeadlineNodeEditorActivity(
			GcgActivity aParentActivity,
			ArrayList<FmmHeadlineNodeShallow> anFmmHeadlineNodeShallowList,
			String aNodeListParentNodeIdString,
			String anInitialNodeIdStringToDisplay,
			FmmFrame anInitialFrame,
			FmmPerspective anInitialPerspective) {
		FmmNodeDefinition theDictionaryEntry = FmmNodeDefinition.getEntryForNodeIdString(anInitialNodeIdStringToDisplay);
		if(theDictionaryEntry == null) {
			GcgHelper.makeToast("ERROR - Unknown FMM Node = " + anInitialNodeIdStringToDisplay);
			return;
		}
		startHeadlineNodeEditorActivity(
				aParentActivity,
				aNodeListParentNodeIdString,
				anFmmHeadlineNodeShallowList,
				anInitialNodeIdStringToDisplay,
				theDictionaryEntry,
				anInitialFrame,
				anInitialPerspective );
	}

	public static void startHeadlineNodeEditorActivity(
			GcgActivity aParentActivity,
			String aNodeListParentNodeId,
			ArrayList<FmmHeadlineNodeShallow> aPeerHeadlineNodeShallowList,
			String anInitialNodeIdToDisplay,
			FmmNodeDefinition anFmmNodeDefinition,
			FmmFrame anInitialFrame,
			FmmPerspective anInitialPerspective ) {
		aParentActivity.startBlueActivityStatusAnimation();
		String theActivityClassName = anFmmNodeDefinition.getNodeClass().getSimpleName() + "EditorActivity";
		Intent theIntent;
		try {
			theIntent = new Intent(aParentActivity, Class.forName(class_path_ACTIVITY_PACKAGE + theActivityClassName));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		theIntent.putExtra(bundle_key__FMM_HEADLINE_NODE_SHALLOW_LIST,
				FmmHeadlineNodeShallow.getSerializationArrayList(aPeerHeadlineNodeShallowList));
		theIntent.putExtra(bundle_key__INITIAL_NODE_TO_DISPLAY, anInitialNodeIdToDisplay);
		theIntent.putExtra(bundle_key__INITIAL_FRAME_TO_DISPLAY, anInitialFrame == null ? FmmFrame.FSE.getName() : anInitialFrame.getName());
		theIntent.putExtra(bundle_key__INITIAL_PERSPECTIVE_TO_DISPLAY, anInitialPerspective == null ? FmmPerspective.STORY.getName() : anInitialPerspective.getName());
		theIntent.putExtra(bundle_key__NAVIGATION_PARENT_NODE_ID, aNodeListParentNodeId);
		theIntent.putExtra(bundle_key__FMS_CONTEXT, aParentActivity.getChildGcgApplicationContext().getSerialized());
		aParentActivity.startActivityForResult(theIntent, anFmmNodeDefinition.getNodeEditorActivityRequestCode());
	}

	public static void startHeadlineNodePickerActivity(
			GcgActivity aParentActivity,
			FmmNodeDefinition anFmmNodeDefinition,
			ArrayList<String> aNodeIdExclusionList,
			String aWhereClause,
			String aListActionLabel ) {
		aParentActivity.startBlueActivityStatusAnimation();
		String theActivityClassName = anFmmNodeDefinition.getNodeClass().getSimpleName() + "PickerActivity";
		Intent theIntent;
		try {
			theIntent = new Intent(aParentActivity, Class.forName(class_path_ACTIVITY_PACKAGE + theActivityClassName));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		theIntent.putExtra(bundle_key__FMS_CONTEXT, aParentActivity.getChildGcgApplicationContext().getSerialized());
		theIntent.putExtra(bundle_key__INITIAL_WHERE_CLAUSE, aWhereClause);
		theIntent.putExtra(bundle_key__NODE_ID_EXLUSION_LIST, aNodeIdExclusionList);
		theIntent.putExtra(bundle_key__LIST_ACTION_LABEL, aListActionLabel);
		aParentActivity.startActivityForResult(theIntent, anFmmNodeDefinition.getNodePickerActivityRequestCode());
	}

	public static void startPdfPublicationWizard(
			GcgActivity aParentActivity,
			String anFmmNodeIdString ) {
		aParentActivity.startBlueActivityStatusAnimation();
		Intent theIntent = new Intent(aParentActivity, PdfPublicationWizard.class);
		theIntent.putExtra(bundle_key__FMM_NODE__ID_STRING, anFmmNodeIdString);
		theIntent.putExtra(bundle_key__FMS_CONTEXT, aParentActivity.getChildGcgApplicationContext().getSerialized());
		aParentActivity.startActivityForResult(theIntent, FmmNodeDefinition.PDF_PUBLICATION.getNodeEditorActivityRequestCode());
	}

	public static void startCreateFmmWizard(
			GcgActivity aParentActivity,
			FmmAccessScope anAccessScope ) {
		aParentActivity.startBlueActivityStatusAnimation();
		Intent theIntent = new Intent(aParentActivity, CreateFmmWizard.class);
		if(aParentActivity.getChildGcgApplicationContext() != null) {
			theIntent.putExtra(bundle_key__FMS_CONTEXT, aParentActivity.getChildGcgApplicationContext().getSerialized());
		}
		theIntent.putExtra(bundle_key__FMM_CONFIGURATION_SCOPE, anAccessScope.getName());
		aParentActivity.startActivityForResult(theIntent, FmmNodeDefinition.FMM_CONFIGURATION.getNodeCreateActivityRequestCode());
	}

	public static void startContactPicker(GcgActivity aParentActivity ) {
		aParentActivity.startBlueActivityStatusAnimation();
		Intent theIntent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
		aParentActivity.startActivityForResult(theIntent, request_code__ANDROID_CONTACT_PICKER);
	}

	public static void startContactPicker(GcgActivity aParentActivity, int aRequestCode) {
		aParentActivity.startBlueActivityStatusAnimation();
		Intent theIntent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
		aParentActivity.startActivityForResult(theIntent, aRequestCode);
	}

	public static void startContactEditor(GcgActivity aParentActivity, long anId, int aRequestCode) {
		aParentActivity.startBlueActivityStatusAnimation();
		Intent theIntent = new Intent(Intent.ACTION_EDIT);
		Uri theContactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, anId); 
		theIntent.setData(theContactUri);
		theIntent.putExtra("finishActivityOnSaveCompleted", true);  // Sets the special extended data for navigation
		aParentActivity.startActivityForResult(theIntent, aRequestCode);
	}

	public static void startPrintJob(GcgActivity aParentActivity, File aFile, String aMimeType) {
		aParentActivity.startBlueActivityStatusAnimation();
		Uri theUri = Uri.fromFile(aFile);
		Intent theIntent = new Intent ("org.androidprinting.intent.action.PRINT");
		theIntent.setDataAndType( theUri, aMimeType );
		if(intentServiceIsAvailable(aParentActivity, theIntent)) {
			aParentActivity.startActivityForResult(theIntent, request_code__PRINT);
		} else {
			GcgHelper.makeToast("ERROR: No printing application installed");
			installHpEprint(aParentActivity);
			if(intentServiceIsAvailable(aParentActivity, theIntent)) {
				aParentActivity.startActivityForResult(theIntent, request_code__PRINT);
			}
		}
	}

	public static boolean intentServiceIsAvailable(GcgActivity aParentActivity, Intent anIntent) {
		ResolveInfo theResolveInfo = aParentActivity.getPackageManager().resolveActivity(
				anIntent, 
				0 );
		return theResolveInfo != null;
	}

	public static void installHpEprint(GcgActivity aParentActivity) {
		aParentActivity.startBlueActivityStatusAnimation();
		installMarketplaceApplication(aParentActivity, "com.hp.android.print&hl=en");
	}

	public static void installMarketplaceApplication(GcgActivity aParentActivity, String anApplicationName) {
		aParentActivity.startBlueActivityStatusAnimation();
		Intent theIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + anApplicationName));
		aParentActivity.startActivityForResult(theIntent, request_code__PRINTER_INSTALLATION);
	}

	public static void startFseDocumentHistoryBrowserActivity(
			GcgActivity aParentActivity,
			String anFseDocumentNodeIdString,
			int anInitialTransactionIndexToDisplay ) {
		aParentActivity.startBlueActivityStatusAnimation();
		Intent theIntent = new Intent(aParentActivity, FseDocumentHistoryBrowserActivity.class);
		theIntent.putExtra(bundle_key__FSE_DOCUMENT_NODE_ID, anFseDocumentNodeIdString);
		theIntent.putExtra(bundle_key__FSE_DOCUMENT_TRANSACTION_INDEX, anInitialTransactionIndexToDisplay);
		theIntent.putExtra(bundle_key__FMS_CONTEXT, aParentActivity.getChildGcgApplicationContext().getSerialized());
		aParentActivity.startActivityForResult(theIntent, FmmNodeDefinition.FSE_DOCUMENT_TRANSACTION.getNodeEditorActivityRequestCode());
	}

	public static Hashtable<String, FmmNodeTransactionType> getModifiedNodeHashTable(Intent anIntent) {
		Hashtable<String, FmmNodeTransactionType> theHashtable = new Hashtable<String, FmmNodeTransactionType>();
		if(anIntent == null) {
			return theHashtable;
		}
		String theString = anIntent.getStringExtra(bundle_key__MODIFIED_FMM_NODE__MAP);
		if(theString == null || theString.length() < 1) {
			return theHashtable;
		}
		try {
			JSONArray theJsonArray = new JSONArray(theString);
			for(int theIndex=0; theIndex < theJsonArray.length(); ++theIndex) {
				JSONArray theMapEntry = theJsonArray.getJSONArray(theIndex);
				theHashtable.put(theMapEntry.getString(0), FmmNodeTransactionType.getTypeForName(theMapEntry.getString(1)));
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theHashtable;
	}

	public static String getSerializedModifiedNodeTable(Hashtable<String, FmmNodeTransactionType> aModifiedFmmNodeIdTable) {
		JSONArray theJsonArray = new JSONArray();
		Iterator<Map.Entry<String, FmmNodeTransactionType>> theIterator = aModifiedFmmNodeIdTable.entrySet().iterator();
		while(theIterator.hasNext()) {
			Map.Entry<String, FmmNodeTransactionType> theMapEntry = theIterator.next();
			JSONArray theJsonMapEntry = new JSONArray();
			theJsonMapEntry.put(theMapEntry.getKey());
			theJsonMapEntry.put(theMapEntry.getValue().getName());
			theJsonArray.put(theJsonMapEntry);
		}
		return theJsonArray.toString();
	}

}
