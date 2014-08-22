/* @(#)GcgContainerTabbedLayout.java
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

package com.flywheelms.library.gcg.container;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.container.tabbed.GcgTabSpec;

/*
 * - tabs always vertical
 * - tabs may be either right or left or both (GcgComponent_thumbPad attribute)
 * - tabs on left by default
 */

// com.flywheelms.library.gcg.container.GcgContainerTabbedLayout
public class GcgContainerTabbedLayout extends GcgContainer {

	private LinearLayout tabsLayout;
	private FrameLayout tabsContentViewFrame;
	private ArrayList<GcgTabSpec> tabSpecList = new ArrayList<GcgTabSpec>();
	private boolean leftTabAlignment = true;
	private int customTabbedLayoutResourceId = 0;
	private int initialTabSelection;

	public GcgContainerTabbedLayout(Context aContext) {
		super(aContext);
	}

	public GcgContainerTabbedLayout(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}
	
	public GcgContainerTabbedLayout(Context aContext, AttributeSet anAttributeSet, int aDefaultStyle) {
		super(aContext, anAttributeSet, aDefaultStyle);
	}
	
	@Override
	protected boolean isManualSetup() {
		return true;
	}

	@Override
	@SuppressWarnings("incomplete-switch")
	protected void processCustomAttributes(Context aContext, AttributeSet anAttributeSet) {
		super.processCustomAttributes(aContext, anAttributeSet);
		TypedArray aTypedArray = aContext.obtainStyledAttributes(anAttributeSet, R.styleable.GcgTabContainer);
		final int theArraySize = aTypedArray.getIndexCount();
		for (int theIndex = 0; theIndex < theArraySize; ++theIndex) {
			int theAttributeIndex = aTypedArray.getIndex(theIndex);
			switch (theAttributeIndex) {
				case R.styleable.GcgTabContainer_tabAlignment:
					if(aTypedArray.getString(theAttributeIndex).equals("left")) {
						this.leftTabAlignment = true;
					} else {
						this.leftTabAlignment = false;
					}
					break;
				case R.styleable.GcgTabContainer_customTabbedLayout:
					this.customTabbedLayoutResourceId = aTypedArray.getInteger(theAttributeIndex, 0);
					break;
				case R.styleable.GcgTabContainer_initialTabSelection:
					this.initialTabSelection = aTypedArray.getInteger(theAttributeIndex, 0);
					break;
			}
		}
		aTypedArray.recycle();
	}
	
	@Override
	protected int getContainerLayoutResourceId() {
		if(this.customTabbedLayoutResourceId != 0) {
			return this.customTabbedLayoutResourceId;
		}
		return isLeftTabAlignment() ? R.layout.gcg__container__tabbed_layout__left : R.layout.gcg__container__tabbed_layout__right;
	}

	@Override
	public void setup() {
		super.setup();
		this.tabsLayout = (LinearLayout) findViewById(R.id.gcg__tabs);
		this.tabsContentViewFrame = (FrameLayout) findViewById(R.id.gcg__tab_content_views);
	}
	
	public void addTab(final GcgTabSpec aGcgTabSpec) {
		this.tabSpecList.add(aGcgTabSpec);
		this.tabsLayout = (LinearLayout) LayoutInflater.from(this.tabsLayout.getContext()).inflate(getTabLayout(), this.tabsLayout);
		final int theTabPosition = this.tabsLayout.getChildCount() - 1;
		TextView theTabView = (TextView) this.tabsLayout.getChildAt(theTabPosition);
		theTabView.setBackgroundResource(getTabNotSelectedBackgroundResourceId(aGcgTabSpec));
		theTabView.setCompoundDrawablesWithIntrinsicBounds(aGcgTabSpec.getLabelDrawable(), null, null, null);
		theTabView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				GcgContainerTabbedLayout.this.setCurrentTab(theTabPosition);
			}
		});
		aGcgTabSpec.setTabView(theTabView);
		aGcgTabSpec.getTabContentView().setVisibility(View.INVISIBLE);
		this.tabsContentViewFrame.addView(aGcgTabSpec.getTabContentView());
	}
	
	public void activateInitialTab() {
		setCurrentTab(this.initialTabSelection);
	}
	
	public void setCurrentTab(int aPosition) {
		for(GcgTabSpec theTabSpec : this.tabSpecList) {
			theTabSpec.setTabBackgroundResourceId(getTabNotSelectedBackgroundResourceId(theTabSpec));
			theTabSpec.getTabContentView().setVisibility(View.INVISIBLE);
		}
		activateCurrentTab(this.tabSpecList.get(aPosition));
	}
	
	public void setCurrentTab(String aTag) {
		GcgTabSpec theSelectedTabSpec = null;
		for(GcgTabSpec theTabSpec : this.tabSpecList) {
			theTabSpec.setTabBackgroundResourceId(getTabNotSelectedBackgroundResourceId(theTabSpec));
			theTabSpec.getTabContentView().setVisibility(View.INVISIBLE);
			if(theTabSpec.getTabContentView().getTag().equals(aTag)) {
				theSelectedTabSpec = theTabSpec;
			}
		}
		activateCurrentTab(theSelectedTabSpec);
	}

	private void activateCurrentTab(GcgTabSpec aGcgTabSpec) {
		aGcgTabSpec.setTabBackgroundResourceId(getTabSelectedBackgroundResourceId());
		aGcgTabSpec.getTabContentView().setVisibility(View.VISIBLE);
		aGcgTabSpec.setHasBeenViewed(true);
	}
	
	private int getTabNotSelectedBackgroundResourceId(GcgTabSpec aGcgTabSpec) {
		int theResource;
		if(aGcgTabSpec.mustStillView()) {
			theResource = isLeftTabAlignment() ? R.drawable.gcg__container__tab_must_view__left : R.drawable.gcg__container__tab_must_view__right;
		} else {
			theResource = isLeftTabAlignment() ? R.drawable.gcg__container__tab_not_selected__left : R.drawable.gcg__container__tab_not_selected__right;
		}
		return theResource;
	}
	
	private int getTabSelectedBackgroundResourceId() {
		return isLeftTabAlignment() ? R.drawable.gcg__container__tab_selected__left : R.drawable.gcg__container__tab_selected__right;
	}

	private int getTabLayout() {
		return this.leftTabAlignment ? R.layout.gcg__container__tab__left : R.layout.gcg__container__tab__right;
	}

	public int getTabsViewLayout() {
		return this.customTabbedLayoutResourceId;
	}

	public boolean isLeftTabAlignment() {
		return this.leftTabAlignment;
	}
	
	public ArrayList<GcgTabSpec> getTabSpecList() {
		return this.tabSpecList;
	}

}
