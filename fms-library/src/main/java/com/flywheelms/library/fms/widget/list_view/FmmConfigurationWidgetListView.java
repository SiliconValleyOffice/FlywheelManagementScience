/* @(#)FmmConfigurationWidgetListView.java
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

package com.flywheelms.library.fms.widget.list_view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.PopupMenu;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.repository.FmmAccessScope;
import com.flywheelms.library.fmm.repository.FmmConfiguration;
import com.flywheelms.library.fms.helper.FmmConfigurationHelper;
import com.flywheelms.library.fms.helper.FmsActivityHelper;
import com.flywheelms.library.fms.popup_menu.FmmPopupBuilder;
import com.flywheelms.library.fms.widget.FmsWidgetListView;
import com.flywheelms.library.gcg.GcgApplication;

import java.util.ArrayList;

public class FmmConfigurationWidgetListView extends FmsWidgetListView <FmmConfiguration> {
	
	private FmmAccessScope accessScope;

	public FmmConfigurationWidgetListView(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}
	
	@Override
	protected ArrayList<FmmConfiguration> instantiateArrayList() {
		ArrayList<FmmConfiguration> theArrayList = new ArrayList<FmmConfiguration>();
		theArrayList.addAll(FmmConfigurationHelper.getFmmConfigurationList(getAccessScope()));
		return theArrayList;
	}
	
	@Override
	protected ArrayAdapter<FmmConfiguration> instantiateArrayAdapter() {
		return new ArrayAdapter<FmmConfiguration>(getContext(), android.R.layout.simple_list_item_1, this.objectList);
	}
	
	@Override
	protected int getPopupMenuResourceId() {
		return R.menu.delete__menu;
	}

	@Override
	protected void onPopupMenu(MenuItem aSelectedMenuItem) {
		FmmConfiguration theFmmConfiguration = this.objectList.get(this.listItemPosition);
		if(aSelectedMenuItem.getTitle().equals("Delete")) {
			deleteFmmRepository(theFmmConfiguration);
			this.arrayAdapter.remove(theFmmConfiguration);
			this.arrayAdapter.notifyDataSetChanged();
		}
		this.listItemView.setBackgroundColor(GcgApplication.getAppResources().getColor(R.color.pdf__transparent) );
	}

	private void deleteFmmRepository(FmmConfiguration anFmmConfiguration) {
		if(anFmmConfiguration.isFmmAccessScopePrivate()) {
			this.gcgActivity.deleteDatabase(anFmmConfiguration.getFileName());
			FmmConfigurationHelper.deleteFmmConfiguration(anFmmConfiguration);
		}
	}

	@Override
	protected void launchObjectEditorActivity(FmmConfiguration anFmmConfiguration) {
//		FmmConfiguration.startNodeEditorActivity(this.gcgActivity, null, getFmmHeadlineNodeShallowList(), anFmmConfiguration.getNodeIdString());
	}

	@Override
	protected void refreshObjectAfterEditorResult(Intent anIntent, FmmConfiguration anObject) {
		FmmDatabaseMediator.getActiveMediator().getFmmConfiguration(anObject.getNodeIdString());
	}

	@Override
	protected void launchObjectAddActivity() {
		FmsActivityHelper.startCreateFmmWizard(this.gcgActivity, this.accessScope);
	}
	
	@Override
	protected FmmConfiguration instantiateObject(Intent anIntent) {
		return FmmConfiguration.getFmmConfiguration(anIntent);
	}

	@Override
	protected CharSequence getPrimaryLabelText() {
		return "FMM Configurations";
	}

	@Override
	protected Drawable getPrimaryLabelDrawable() {
		return getResources().getDrawable(R.drawable.gcg__null_drawable);
	}

	public FmmAccessScope getAccessScope() {
		if(this.accessScope == null) {
			this.accessScope = FmmAccessScope.PRIVATE;
		}
		return this.accessScope;
	}

	public void setAccessScope(FmmAccessScope accessScope) {
		this.accessScope = accessScope;
		if(this.accessScope == FmmAccessScope.PRIVATE) {
			this.objectAddButton.setEnabled(true);
		} else {
			this.objectAddButton.setEnabled(false);
		}
	}

	public void updateListData(FmmAccessScope anAccessScope) {
		this.accessScope = anAccessScope;
		updateListData();
	}

    protected PopupMenu getListViewBackgroundPopupMenu(View aView) {
        PopupMenu thePopupMenu = new PopupMenu(getContext(), aView);
        // TODO - conditional for API 19 and above
//		PopupMenu thePopupMenu = new PopupMenu(getContext(), aView, Gravity.CENTER | Gravity,LEFT);  // target API 19
        thePopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem aMenuItem) {
                if(aMenuItem.getTitle().equals(FmmPopupBuilder.menu_item__CREATE_FMM_CONFIGURATION)) {
                    FmmConfigurationWidgetListView.this.launchObjectAddActivity();
                }
                return true;
            }
        });
        thePopupMenu.getMenu().add(FmmPopupBuilder.menu_item__CREATE_FMM_CONFIGURATION);
        return thePopupMenu;
    }

}
