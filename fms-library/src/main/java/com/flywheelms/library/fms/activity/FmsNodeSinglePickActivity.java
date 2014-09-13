/* @(#)FmsNodeSinglePickActivity.java
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

package com.flywheelms.library.fms.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.fdk.FdkHostSupport;
import com.flywheelms.library.fdk.activity.FdkActivity;
import com.flywheelms.library.fdk.enumerator.FdkKeyboardState;
import com.flywheelms.library.fdk.enumerator.FdkKeyboardStyle;
import com.flywheelms.library.fdk.widget.FdkKeyboard;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.FmmHeadlineNodeShallow;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.fms.helper.FmsSearchHelper;

import java.util.ArrayList;

public abstract class FmsNodeSinglePickActivity extends FdkActivity {

	protected String searchTypeDescription = "Must contain all...";
	protected String listActionLabel;
	protected FmmNodeDefinition nodeDictionaryEntry;
	protected String initialWhereClause;
	protected ArrayList<String> nodeIdExclusionList;
	protected EditText searchCriteria;
	protected ListView listView;
	protected ArrayAdapter<FmmHeadlineNode> listViewArrayAdapter;
	protected ArrayList<FmmHeadlineNode> initialHeadlineNodeList;
	protected ArrayList<FmmHeadlineNode> searchResultsNodeList;
	private View searchResultsListItemView;
	private int searchResultsListItemPosition;

	public FmsNodeSinglePickActivity(String anInitialHelpContextUrlString, FmmNodeDefinition anFmmNodeDefinition) {
		super(anInitialHelpContextUrlString);
		this.nodeDictionaryEntry = anFmmNodeDefinition;
	}

	@Override
	protected int getBreadcrumbDrawableResourceId() {
		return this.nodeDictionaryEntry.getPickerDrawableResourceId();
	}

	@Override
	protected void onCreate(Bundle aSavedInstanceState) {
		super.onCreate(aSavedInstanceState);
		initializeListView();
		this.initialHeadlineNodeList = new ArrayList<FmmHeadlineNode>(FmmDatabaseMediator.getActiveMediator().getHeadlineNodeList(this.nodeDictionaryEntry.getNodeTypeCode()));
		applyNodeIdExclusionList();
		this.searchResultsNodeList = new ArrayList<FmmHeadlineNode>(this.initialHeadlineNodeList);
		this.listViewArrayAdapter = new ArrayAdapter<FmmHeadlineNode>(this, android.R.layout.simple_list_item_1, this.searchResultsNodeList);
		this.listView.setAdapter(this.listViewArrayAdapter);
	}

	private void initializeListView() {
		this.listView = (ListView) findViewById(R.id.node_list);
		this.listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View aListViewItemView, int aListViewItemPosition, long anItemId) {
				Intent theIntent = new Intent();   
				FmmHeadlineNode theHeadlineNode = FmsNodeSinglePickActivity.this.searchResultsNodeList.get(aListViewItemPosition);
				theIntent.putExtra(FmsActivityHelper.bundle_key__FMM_NODE__ID_STRING, theHeadlineNode.getNodeIdString());
				finish(Activity.RESULT_OK, theIntent);
			}
		});
		this.listView.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View aListViewItemView, int aListViewItemPosition, long anItemId) {
				FmsNodeSinglePickActivity.this.searchResultsListItemView = aListViewItemView;
				FmsNodeSinglePickActivity.this.searchResultsListItemView.setBackgroundColor(
						FmsNodeSinglePickActivity.this.getResources().getColor(R.color.pdf__light_blue) );
				FmsNodeSinglePickActivity.this.searchResultsListItemPosition = aListViewItemPosition;
				aListViewItemView.setSelected(true);
				PopupMenu thePopupMenu = new PopupMenu(
						FmsNodeSinglePickActivity.this, aListViewItemView);
				thePopupMenu.getMenuInflater().inflate(R.menu.edit__menu, thePopupMenu.getMenu());
				thePopupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

					@Override
					public boolean onMenuItemClick(MenuItem aSelectedMenuItem) {
						onSearchResultsListPopup();
						return true;
					}
				});
				thePopupMenu.setOnDismissListener(new OnDismissListener() {

					@Override
					public void onDismiss(PopupMenu menu) {
						FmsNodeSinglePickActivity.this.searchResultsListItemView.setBackgroundColor(
								FmsNodeSinglePickActivity.this.getResources().getColor(R.color.pdf__transparent) );
					}
				});
				thePopupMenu.show();
				return false;
			}
		});
	}

	private void onSearchResultsListPopup() {
		FmmHeadlineNode theHeadlineNode = this.searchResultsNodeList.get(this.searchResultsListItemPosition);
		FmsActivityHelper.startHeadlineNodeEditorActivity(this, getSearchResultsFmmHeadlineNodeShallowList(), null, theHeadlineNode.getNodeIdString());
		this.searchResultsListItemView.setBackgroundColor(getResources().getColor(R.color.pdf__transparent) );
	}

	private ArrayList<FmmHeadlineNodeShallow> getSearchResultsFmmHeadlineNodeShallowList() {
		ArrayList<FmmHeadlineNodeShallow> theFmmHeadlineNodeShallowList = new ArrayList<FmmHeadlineNodeShallow>();
		for(FmmHeadlineNode theHeadlineNode : this.searchResultsNodeList) {
			theFmmHeadlineNodeShallowList.add(theHeadlineNode.getFmmHeadlineNodeShallow());
		}
		return theFmmHeadlineNodeShallowList;
	}

	private void applyNodeIdExclusionList() {
		ArrayList<FmmHeadlineNode> theTempList = new ArrayList<FmmHeadlineNode>(this.initialHeadlineNodeList);
		for(FmmHeadlineNode theHeadlineNode : theTempList) {
			if(isExcludedNode(theHeadlineNode)) {
				this.initialHeadlineNodeList.remove(theHeadlineNode);
			}
		}
	}

	private boolean isExcludedNode(FmmHeadlineNode theHeadlineNode) {
		if(this.nodeIdExclusionList == null) {
			return false;
		}
		boolean theBoolean = false;
		for(String theNodeIdString : this.nodeIdExclusionList) {
			if(theNodeIdString.equals(theHeadlineNode.getNodeIdString())) {
				theBoolean = true;
				break;
			}
		}
		return theBoolean;
	}

	protected void initFdkKeyboard() {
		setFdkKeyboard(new FdkKeyboard(
						FdkKeyboardStyle.widget_input__SEARCH,
						FdkKeyboardState.ALWAYS_ACTIVE,
						getGcgThumbpadLeft(),
						null,
						getGcgThumbpadRight(),
						null,
						this,
						this ));
		activateFdkKeyboard(true);
	}

	@Override
	protected void processExtras() {
		super.processExtras();
		this.listActionLabel = getIntent().getExtras().getString(FmsActivityHelper.bundle_key__LIST_ACTION_LABEL, "please select one");
		this.initialWhereClause = getIntent().getExtras().getString(FmsActivityHelper.bundle_key__INITIAL_WHERE_CLAUSE, "");
		this.nodeIdExclusionList = getIntent().getExtras().getStringArrayList(FmsActivityHelper.bundle_key__NODE_ID_EXLUSION_LIST);
	}

	@Override
	protected void initializeGui() {
		super.initializeGui();
		initializeGcgApplicationContextHeader();
		initFdkKeyboard();
		this.searchCriteria = (EditText) findViewById(R.id.search_criteria);
		this.searchCriteria.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
		this.searchCriteria.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {  /*  N/A */  }
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {  /*  N/A  */}
			
			@Override
			public void afterTextChanged(Editable s) {
				FmsNodeSinglePickActivity.this.updateSearchResults();
			}
		});
		this.searchCriteria.setOnKeyListener(new OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int aKeyCode, KeyEvent aKeyEvent) {
	    		if(aKeyCode==KeyEvent.KEYCODE_ENTER && aKeyEvent.getAction() == KeyEvent.ACTION_UP) {
	    			FmsNodeSinglePickActivity.this.updateSearchResults();
	    			return true;
	    		}
				return false;
			}
		});
		TextView theTextView = (TextView) findViewById(R.id.search_type__label);
		theTextView.setText(this.searchTypeDescription);
		theTextView = (TextView) findViewById(R.id.node_type__label);
		theTextView.setText(this.nodeDictionaryEntry.getNodeTypeName() + " List");
		theTextView = (TextView) findViewById(R.id.list_action__label);
		theTextView.setText("( " + this.listActionLabel + " )");
		ImageView theImageView = (ImageView) findViewById(R.id.node_list__icon);
		theImageView.setImageResource(this.nodeDictionaryEntry.getLabelDrawableResourceId());
	}

	@Override
	protected FmmNodeDefinition getDisplayedFmmNodeDefinition() {
		return this.nodeDictionaryEntry;
	}
	
	@Override
	protected View getActivityBodyView() {
		return getLayoutInflater().inflate(R.layout.fms__node_picker, this.activityBodyParent, false);
	}

	@Override
	public void fdkDictationResults(ArrayList<String> aDictationResultsList) {
		FdkHostSupport.insertDictationResultsIntoEditText(aDictationResultsList, this.searchCriteria);
		updateSearchResults();
	}

	@Override
	public void fdkDispatchShiftedKeyEvent(int aKeycode) {
		if(aKeycode == KeyEvent.KEYCODE_GRAVE) {
			FdkHostSupport.clearContents(this.searchCriteria);
			this.searchResultsNodeList.clear();
			this.searchResultsNodeList.addAll(this.initialHeadlineNodeList);
			this.listViewArrayAdapter.notifyDataSetChanged();
		} else {
			super.fdkDispatchShiftedKeyEvent(aKeycode);
		}
	}

	@Override
	public void fdkDispatchControlShiftedKeyEvent(int aKeycode) {
		if(aKeycode == KeyEvent.KEYCODE_GRAVE) {
			FdkHostSupport.deleteWord(this.searchCriteria);
			updateSearchResults();
		} else {
			super.fdkDispatchControlShiftedKeyEvent(aKeycode);
		}
	}

	private void updateSearchResults() {
		this.searchResultsNodeList.clear();
		ArrayList<String> theTokenList = FmsSearchHelper.tokenizeSearchCriteriaInLowerCase(this.searchCriteria.getText().toString());
		for(FmmHeadlineNode theHeadlineNode : this.initialHeadlineNodeList) {
			if(FmsSearchHelper.stringMatchesCriteria(theTokenList, theHeadlineNode.getHeadline())) {
				this.searchResultsNodeList.add(theHeadlineNode);
			}
		}
		this.listViewArrayAdapter.notifyDataSetChanged();
	}

//	// TODO - better regular expression for junk in the original search string
//	private ArrayList<String> tokenizeSearchCriteria() {
//		String theSearchString = this.searchCriteria.getText().toString().toLowerCase(Locale.ENGLISH);
//		ArrayList<String> theTokenList = new ArrayList<String>(
//				Arrays.asList(TextUtils.split(theSearchString, " ")) );
//		return theTokenList;
//	}

	@Override
	protected String getBreadcrumbTargetNodeIdString() {
		return "";
	}

	@Override
	protected String getBreadcrumbHeadline() {
		return getDisplayedFmmNodeDefinition().getName() + " Picker";
	}

}
