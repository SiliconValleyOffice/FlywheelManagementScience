/* @(#)GcgWidgetListView.java
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

package com.flywheelms.gcongui.gcg.widget;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnDismissListener;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;

import java.util.ArrayList;

public abstract class GcgWidgetListView <T> extends LinearLayout {

	private static final int resource_id_ADD_BUTTON = R.id.widget__add__button;
	private static final int resource_id_LIST_VIEW = R.id.widget__list_view;
	private static final int resource_id_LIST_VIEW_BACKGROUND = R.id.widget__list_view__background_menu_target;
	private static final int resource_id_WIDGET_LABEL = R.id.widget__label;
	private static final int resource_id_SUPPLEMENTAL_LABEL = R.id.widget__supplemental_label;
	protected GcgActivity gcgActivity;
	protected String labelPrefix = "";
	protected String labelSuffix = "";
	protected String secondaryLabel = "";
	protected String labelHint = "";
	protected int labelWidth;
	public static final String container_layout__VERTICAL = "vertical";
	public static final String container_layout__HORIZONTAL = "horizontal";
	public static final String container_layout__NO_LABEL = "no_label";
	protected String containerLayout = container_layout__HORIZONTAL;
	private ListView listView;
	protected View listItemView;
	protected int listItemPosition;
	protected ArrayList<T> objectList;
	protected ArrayAdapter<T> arrayAdapter;
	protected Button objectAddButton;
	protected int requestCode = 0;
	protected int requestCode2 = 0;
    private PopupMenu popupMenu;

    public GcgWidgetListView(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		processCustomAttributes(aContext, anAttributeSet);
		setup();
	}
	
	protected abstract ArrayList<T> instantiateArrayList();
	
	protected abstract ArrayAdapter<T> instantiateArrayAdapter();

	@SuppressWarnings("incomplete-switch")
	protected void processCustomAttributes(Context aContext, AttributeSet anAttributeSet) {
		processGcgLabelAttributes(aContext, anAttributeSet);
		TypedArray aTypedArray = aContext.obtainStyledAttributes(anAttributeSet, R.styleable.GcgWidget);
		final int theArraySize = aTypedArray.getIndexCount();
		for (int theIndex = 0; theIndex < theArraySize; ++theIndex) {
			int theAttributeIndex = aTypedArray.getIndex(theIndex);
			if (theAttributeIndex == R.styleable.GcgWidget_containerLayout) {
				this.containerLayout = aTypedArray.getString(theAttributeIndex);

			} else if (theAttributeIndex == R.styleable.GcgWidget_requestCode) {
				this.requestCode = aTypedArray.getInt(theAttributeIndex, 0);

			} else if (theAttributeIndex == R.styleable.GcgWidget_requestCode2) {
				this.requestCode2 = aTypedArray.getInt(theAttributeIndex, 0);

			}
		}
		aTypedArray.recycle();
	}
	
	@SuppressWarnings("incomplete-switch")
	protected void processGcgLabelAttributes(Context aContext, AttributeSet anAttributeSet) {
		TypedArray aTypedArray = aContext.obtainStyledAttributes(anAttributeSet, R.styleable.GcgLabel);
		final int theArraySize = aTypedArray.getIndexCount();
		for (int theIndex = 0; theIndex < theArraySize; ++theIndex) {
			int theAttributeIndex = aTypedArray.getIndex(theIndex);
			if (theAttributeIndex == R.styleable.GcgLabel_labelWidth) {
				this.labelWidth = aTypedArray.getInteger(theAttributeIndex, 0);

			} else if (theAttributeIndex == R.styleable.GcgLabel_labelPrefix) {
				this.labelPrefix = aTypedArray.getString(theAttributeIndex);

			} else if (theAttributeIndex == R.styleable.GcgLabel_labelSuffix) {
				this.labelSuffix = aTypedArray.getString(theAttributeIndex);

			} else if (theAttributeIndex == R.styleable.GcgLabel_labelHint) {
				this.labelHint = aTypedArray.getString(theAttributeIndex);

			}
		}
		aTypedArray.recycle();
	}

	protected void setup() {
		if(this.containerLayout.equals(container_layout__NO_LABEL)) {
			inflate(getContext(), getViewLayoutNoLabelResourceId(), this);
		} else {
			if(this.containerLayout.equals(container_layout__VERTICAL)) {
				inflate(getContext(), getViewLayoutVerticalResourceId(), this);
			} else {
				inflate(getContext(), getViewLayoutHorizontalResourceId(), this);
			}
			if(this.secondaryLabel.length() > 0) {
				TextView theSecondaryLabel = (TextView) findViewById(resource_id_SUPPLEMENTAL_LABEL);
				theSecondaryLabel.setText(this.secondaryLabel);
				if(this.labelWidth > 0) {
					android.view.ViewGroup.LayoutParams theLayoutParams = theSecondaryLabel.getLayoutParams();
					theLayoutParams.width = GcgHelper.getPixelsForDp(getContext(), this.labelWidth);
					theSecondaryLabel.setLayoutParams(theLayoutParams);
				}
			} else {
				TextView theLabelTextView = (TextView) findViewById(resource_id_WIDGET_LABEL);
				theLabelTextView.setText(getPrimaryLabelText());
				GcgHelper.setDrawableLeft(theLabelTextView, getPrimaryLabelDrawable());
				if(this.labelWidth > 0) {
					android.view.ViewGroup.LayoutParams theLayoutParams = theLabelTextView.getLayoutParams();
					theLayoutParams.width = GcgHelper.getPixelsForDp(getContext(), this.labelWidth);
					theLabelTextView.setLayoutParams(theLayoutParams);
				}
				if(this.labelHint.length() > 0) {
					if(this.labelPrefix.length() > 0 || this.labelSuffix.length() > 0) {
						theLabelTextView.setText(GcgHelper.getLabelHintHtmlString(this.labelHint) + " " +
								this.labelPrefix + theLabelTextView.getText() + this.labelSuffix);
					} else {
						theLabelTextView.setText(GcgHelper.getLabelHintHtmlString(this.labelHint) + " " +
								theLabelTextView.getText());
					}
				} else {
					if(this.labelPrefix.length() > 0 || this.labelSuffix.length() > 0) {
						theLabelTextView.setText(this.labelPrefix + theLabelTextView.getText() + this.labelSuffix);
					}
				}
			}
		}
		this.listView = (ListView) findViewById(resource_id_LIST_VIEW);
		this.objectList = instantiateArrayList();
		this.arrayAdapter = instantiateArrayAdapter();
		this.listView.setAdapter(this.arrayAdapter);
		this.listView.setOnItemLongClickListener(new OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> anAdapterView, View aListViewItemView, int aListViewItemPosition, long anItemId) {
                GcgWidgetListView.this.listItemView = aListViewItemView;
                GcgWidgetListView.this.listItemView.setBackgroundColor(
                        GcgWidgetListView.this.getResources().getColor(R.color.x11__LightSkyBlue));
                GcgWidgetListView.this.listItemPosition = aListViewItemPosition;
                aListViewItemView.setSelected(true);
                PopupMenu thePopupMenu = getPopupMenu(aListViewItemView);
                thePopupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {

                    @Override
                    public boolean onMenuItemClick(MenuItem aSelectedMenuItem) {
                        onPopupMenu(aSelectedMenuItem);
                        return true;
                    }
                });
                thePopupMenu.setOnDismissListener(new OnDismissListener() {

                    @Override
                    public void onDismiss(PopupMenu menu) {
                        GcgWidgetListView.this.listItemView.setBackgroundColor(
                                GcgWidgetListView.this.getResources().getColor(R.color.gcg__transparent));
                    }
                });
                thePopupMenu.show();
                return true;
            }
        });
		this.listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> anAdapterView, View aListViewItemView, int aListViewItemPosition, long anItemId) {
                T theObject = GcgWidgetListView.this.objectList.get(aListViewItemPosition);
                launchObjectEditorActivity(theObject);
            }
        });
        TextView theListViewBackgroundMenuTarget = (TextView) findViewById(GcgWidgetListView.resource_id_LIST_VIEW_BACKGROUND);
        if(theListViewBackgroundMenuTarget != null) {
            theListViewBackgroundMenuTarget.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View aView) {
                    PopupMenu thePopupMenu = GcgWidgetListView.this.getListViewBackgroundPopupMenu(aView);
                    if (thePopupMenu != null) {
                        thePopupMenu.show();
                        return true;
                    }
                    return false;
                }
            });
        }
		this.objectAddButton = (Button) findViewById(resource_id_ADD_BUTTON);
        this.objectAddButton.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                launchObjectAddActivity();
            }
        });
	}

    protected PopupMenu getListViewBackgroundPopupMenu(View aView) {
        return null;
    }
	
	protected int getPopupMenuResourceId() {
		return R.menu.gcg__edit_remove__menu;
	}

	protected void onPopupMenu(MenuItem aSelectedMenuItem) {
		T theObject = this.objectList.get(this.listItemPosition);
		if(aSelectedMenuItem.getTitle().equals("Remove")) {
			this.arrayAdapter.remove(theObject);
			this.arrayAdapter.notifyDataSetChanged();
		} else {
			launchObjectEditorActivity(theObject);
		}
		this.listItemView.setBackgroundColor(GcgApplication.getAppResources().getColor(R.color.gcg__transparent) );
	}

	protected abstract void launchObjectEditorActivity(T theObject);

	protected abstract CharSequence getPrimaryLabelText();

	protected abstract Drawable getPrimaryLabelDrawable();

	protected abstract void launchObjectAddActivity();
	
	public void setGcgActivity(GcgActivity aLibraryActivity) {
		this.gcgActivity = aLibraryActivity;
	}
	
	protected int getViewLayoutNoLabelResourceId() {
		return R.layout.gcg__widget__list_view__no_label;
	}
	
	protected int getViewLayoutVerticalResourceId() {
		return R.layout.gcg__widget__list_view__vertical;
	}
	
	protected int getViewLayoutHorizontalResourceId() {
		return R.layout.gcg__widget__list_view__horizontal;
	}

	public void onObjectPickerResult(Intent anIntent) {
		if(anIntent.getData() != null || ! anIntent.getExtras().isEmpty()) {
			T theObject = instantiateObject(anIntent);
			addObject(theObject);
		}
	}

	protected abstract T instantiateObject(Intent anIntent);

	protected void addObject(T anObject) {
		if(containsObject(anObject)) {
			return;
		}
		this.arrayAdapter.add(anObject);
		this.arrayAdapter.notifyDataSetChanged();
	}
	
	protected boolean containsObject(T anObject) {
		for(T theObject : this.objectList) {
			if(theObject.toString().equals(anObject.toString())) {
				return true;
			}
		}
		return false;
	}
	
	protected int getObjectIndex(T anObject) {
		for(T theObject : this.objectList) {
			if(theObject.toString().equals(anObject.toString())) {
				return this.objectList.indexOf(theObject);
			}
		}
		return -1;
	}

	public void onObjectEditorResult(Intent anIntent) {
		if(anIntent.getData() != null) {
			T theObject = this.objectList.get(this.listItemPosition);
			refreshObjectAfterEditorResult(anIntent, theObject);
			this.arrayAdapter.notifyDataSetChanged();
		}
	}

	protected abstract void refreshObjectAfterEditorResult(Intent anIntent, T theObject);

	public ArrayList<T> getObjectList() {
		return this.objectList;
	}

	public int getCount() {
		return this.objectList.size();
	}

	public void updateListData() {
		this.objectList = instantiateArrayList();
		this.arrayAdapter = instantiateArrayAdapter();
		this.listView.setAdapter(this.arrayAdapter);
	}

	protected int getRequestCode() {
		return this.requestCode;
	}

	protected int getRequestCode2() {
		return this.requestCode2;
	}

    public PopupMenu getPopupMenu(View aView) {
        PopupMenu thePopupMenu = new PopupMenu(getContext(), aView);
        thePopupMenu.getMenuInflater().inflate(getPopupMenuResourceId(), thePopupMenu.getMenu());
        return popupMenu;
    }
}
