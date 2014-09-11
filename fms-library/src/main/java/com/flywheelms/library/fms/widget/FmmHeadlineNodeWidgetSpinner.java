/* @(#)FmmHeadlineNodeWidgetSpinner.java
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

package com.flywheelms.library.fms.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.fms.dialog.HeadlineNodeListZoomDialog;
import com.flywheelms.library.gcg.GcgApplication;
import com.flywheelms.library.gcg.helper.GcgGuiableSpinnerArrayAdapter;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.gcg.widget.GcgWidgetSpinner;

import java.util.ArrayList;

public abstract class FmmHeadlineNodeWidgetSpinner extends GcgWidgetSpinner {
	
	public static final String filter_id__DEFAULT = "filter_id__default";
	public static final String filter_id__PRIMARY_PARENT = GcgApplication.getStringResource(R.string.filter_id__primary_parent);
	public static final String filter_id__PRIMARY_PARENT__PRIMARY_CHILD__MOVE_TARGET = GcgApplication.getStringResource(R.string.filter_id__primary_parent__primary_child__move_target);
	public static final String filter_id__PRIMARY_PARENT__PRIMARY_CHILD__PRIMARY_CHILD__MOVE_TARGET = GcgApplication.getStringResource(R.string.filter_id__primary_parent__primary_child__primary_child__move_target);
	public static final String filter_id__PRIMARY_PARENT__PRIMARY_CHILD__PRIMARY_CHILD__PRIMARY_CHILD__MOVE_TARGET = GcgApplication.getStringResource(R.string.filter_id__primary_parent__primary_child__primary_child__primary_child__move_target);
	public static final String filter_id__PRIMARY_PARENT__PRIMARY_CHILD__PRIMARY_CHILD__PRIMARY_CHILD__PRIMARY_CHILD__MOVE_TARGET = GcgApplication.getStringResource(R.string.filter_id__primary_parent__primary_child__primary_child__primary_child__primary_child__move_target);
	public static final String filter_id__PRIMARY_PARENT__SECONDARY_CHILD__MOVE_TARGET = GcgApplication.getStringResource(R.string.filter_id__primary_parent__secondary_child__move_target);
	public static final String filter_id__PRIMARY_PARENT__SECONDARY_CHILD__PRIMARY_CHILD__MOVE_TARGET = GcgApplication.getStringResource(R.string.filter_id__primary_parent__secondary_child__primary_child__move_target);
	public static final String filter_id__PRIMARY_PARENT__SECONDARY_CHILD__SECONDARY_CHILD__MOVE_TARGET = GcgApplication.getStringResource(R.string.filter_id__primary_parent__secondary_child__secondary_child__move_target);
	public static final String filter_id__SECONDARY_PARENT = GcgApplication.getStringResource(R.string.filter_id__secondary_parent);
	public static final String filter_id__SECONDARY_PARENT__PRIMARY_CHILD__MOVE_TARGET = GcgApplication.getStringResource(R.string.filter_id__secondary_parent__primary_child__move_target);
	public static final String filter_id__SECONDARY_PARENT__SECONDARY_CHILD__MOVE_TARGET = GcgApplication.getStringResource(R.string.filter_id__secondary_parent__secondary_child__move_target);
	public static final String filter_id__ORPHAN_NODES__PRIMARY_PARENT = GcgApplication.getStringResource(R.string.filter_id__orphan_nodes__primary_parent);
	public static final String filter_id__ORPHAN_NODES__SECONDARY_PARENT = GcgApplication.getStringResource(R.string.filter_id__orphan_nodes__secondary_parent);

	public FmmHeadlineNodeWidgetSpinner(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	@Override
	protected ArrayList<? extends GcgGuiable> updateGuiableList() {
		ArrayList<? extends GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		if(this.getFilterId() == null || this.getFilterId().equals(filter_id__NO_FILTER)) {
			this.setFilterId(filter_id__DEFAULT);
			theGuiableList = getDefaultGuiableList();
		} else if(this.getFilterId().equals(filter_id__PRIMARY_PARENT)) {
			theGuiableList = getPrimaryParentGuiableList();
		} else if(this.getFilterId().equals(filter_id__PRIMARY_PARENT__PRIMARY_CHILD__MOVE_TARGET)) {
			theGuiableList = getPrimaryParentPrimaryChildMoveTargetGuiableList();
		} else if(this.getFilterId().equals(filter_id__PRIMARY_PARENT__PRIMARY_CHILD__PRIMARY_CHILD__MOVE_TARGET)) {
			theGuiableList = getPrimaryParentPrimaryChildPrimaryChildMoveTargetGuiableList();
		} else if(this.getFilterId().equals(filter_id__PRIMARY_PARENT__PRIMARY_CHILD__PRIMARY_CHILD__PRIMARY_CHILD__MOVE_TARGET)) {
			theGuiableList = getPrimaryParentPrimaryChildPrimaryChildPrimaryChildMoveTargetGuiableList();
        } else if(this.getFilterId().equals(filter_id__PRIMARY_PARENT__PRIMARY_CHILD__PRIMARY_CHILD__PRIMARY_CHILD__PRIMARY_CHILD__MOVE_TARGET)) {
            theGuiableList = getPrimaryParentPrimaryChildPrimaryChildPrimaryChildPrimaryChildMoveTargetGuiableList();
		} else if(this.getFilterId().equals(filter_id__PRIMARY_PARENT__SECONDARY_CHILD__MOVE_TARGET)) {
			theGuiableList = getPrimaryParentSecondaryChildMoveTargetGuiableList();
		} else if(this.getFilterId().equals(filter_id__PRIMARY_PARENT__SECONDARY_CHILD__PRIMARY_CHILD__MOVE_TARGET)) {
			theGuiableList = getPrimaryParentSecondaryChildPrimaryChildMoveTargetGuiableList();
		} else if(this.getFilterId().equals(filter_id__PRIMARY_PARENT__SECONDARY_CHILD__SECONDARY_CHILD__MOVE_TARGET)) {
			theGuiableList = getPrimaryParentSecondaryChildSecondaryChildMoveTargetGuiableList();
		} else if(this.getFilterId().equals(filter_id__SECONDARY_PARENT)) {
			theGuiableList = getSecondaryParentGuiableList();
		} else if(this.getFilterId().equals(filter_id__SECONDARY_PARENT__PRIMARY_CHILD__MOVE_TARGET)) {
			theGuiableList = getSecondaryParentPrimaryChildMoveTargetGuiableList();
		} else if(this.getFilterId().equals(filter_id__SECONDARY_PARENT__SECONDARY_CHILD__MOVE_TARGET)) {
			theGuiableList = getSecondaryParentSecondaryChildMoveTargetGuiableList();
		} else if(this.getFilterId().equals(filter_id__ORPHAN_NODES__PRIMARY_PARENT)) {
			theGuiableList = getOrphanNodesPrimaryParentGuiableList();
		} else if(this.getFilterId().equals(filter_id__ORPHAN_NODES__SECONDARY_PARENT)) {
			theGuiableList = getOrphanNodesSecondaryParentGuiableList();
		}
		return theGuiableList;
	}

	@Override
	protected int getWidgetLayoutResourceId() {
		int theResourceId = R.layout.fms__widget__headline_node__spinner__horizontal;
		if(this.containerLayout.equals(container_layout__MENU_PARAMETER)) {
			theResourceId = R.layout.fms__widget__headline_node__spinner__menu_parameter;
		} else if(this.containerLayout.equals(container_layout__VERTICAL)) {
			theResourceId = R.layout.fms__widget__headline_node__spinner__vertical;
		} else if(this.containerLayout.equals(container_layout__VERTICAL__NO_LABEL_DRAWABLE)) {
			theResourceId = R.layout.fms__widget__headline_node__spinner__vertical;
		} else if(this.containerLayout.equals(container_layout__NO_LABEL)) {
			theResourceId = R.layout.fms__widget__headline_node__spinner__no_label;
		}
		return theResourceId;
	}

	@Override
	public void setup() {
		super.setup();
		this.zoomButton = (Button) this.widgetContainer.findViewById(R.id.headline__zoom__button);
		if(this.zoomButton != null) {
			if(this.zoomable) {
				this.zoomButton.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(FmmHeadlineNodeWidgetSpinner.this.getHeadlineNode() != null) {
							FmmHeadlineNodeWidgetSpinner.this.getGcgActivity().startDialog(new HeadlineNodeListZoomDialog(
									getGcgActivity(),
									FmmHeadlineNodeWidgetSpinner.this,
									FmmHeadlineNodeWidgetSpinner.this.getFmmHeadlineNodeList(),
									FmmHeadlineNodeWidgetSpinner.this.getSelectedItemPosition() ));
						}
					}
				});
                this.zoomButton.setVisibility(isMinimumInput() ? View.VISIBLE : View.INVISIBLE);
			} else {
				this.zoomButton.setVisibility(View.GONE);
			}
		}
	}

    private void enableZoomButton(boolean bEnable) {
        if(this.zoomButton != null) {
            this.zoomButton.setVisibility(bEnable ? View.VISIBLE : View.INVISIBLE);
        }
    }

	protected ArrayList<FmmHeadlineNode> getFmmHeadlineNodeList() {
		ArrayList<FmmHeadlineNode> theFmmHeadlineNodeList = new ArrayList<FmmHeadlineNode>();
		for(GcgGuiable theGcgGuiable : getGcgGuiableList()) {
			theFmmHeadlineNodeList.add((FmmHeadlineNode) theGcgGuiable);
		}
		return theFmmHeadlineNodeList;
	}

	protected FmmHeadlineNode getHeadlineNode() {
		return (FmmHeadlineNode) getSelectedItem();
	}

	@Override
	protected void setLabelWidth() {
		RelativeLayout theRelativeLayout = (RelativeLayout)  this.widgetContainer.findViewById(R.id.headline__zoom__layout);
		android.view.ViewGroup.LayoutParams theLayoutParams = theRelativeLayout.getLayoutParams();
		theLayoutParams.width = GcgHelper.getPixelsForDp(getContext(), this.labelWidth);
		theRelativeLayout.setLayoutParams(theLayoutParams);
	}
	
	protected ArrayList<? extends GcgGuiable> getOrphanNodesPrimaryParentGuiableList() {
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		return theGuiableList;
	}
	
	protected ArrayList<? extends GcgGuiable> getOrphanNodesSecondaryParentGuiableList() {
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		return theGuiableList;
	}

	protected ArrayList<? extends GcgGuiable> getDefaultGuiableList() {
		return getPrimaryParentGuiableList();
	}
	
	protected abstract ArrayList<? extends GcgGuiable> getPrimaryParentGuiableList();
	
	protected ArrayList<? extends GcgGuiable> getSecondaryParentGuiableList() {  // widget broken/unfinished, bad XML, or method called too early
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		return theGuiableList;
	}
	
	protected ArrayList<? extends GcgGuiable> getPrimaryParentPrimaryChildMoveTargetGuiableList() {  // widget broken/unfinished, bad XML, or method called too early
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		return theGuiableList;
	}
	
	protected ArrayList<? extends GcgGuiable> getPrimaryParentPrimaryChildPrimaryChildMoveTargetGuiableList() {  // widget broken/unfinished, bad XML, or method called too early
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		return theGuiableList;
	}

    protected ArrayList<? extends GcgGuiable> getPrimaryParentPrimaryChildPrimaryChildPrimaryChildPrimaryChildMoveTargetGuiableList() {  // widget broken/unfinished, bad XML, or method called too early
        ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
        return theGuiableList;
    }
	
	protected ArrayList<? extends GcgGuiable> getPrimaryParentPrimaryChildPrimaryChildPrimaryChildMoveTargetGuiableList() {  // widget broken/unfinished, bad XML, or method called too early
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		return theGuiableList;
	}
	
	protected ArrayList<? extends GcgGuiable> getPrimaryParentSecondaryChildMoveTargetGuiableList() {  // widget broken/unfinished, bad XML, or method called too early
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		return theGuiableList;
	}
	
	protected ArrayList<? extends GcgGuiable> getPrimaryParentSecondaryChildPrimaryChildMoveTargetGuiableList() {  // widget broken/unfinished, bad XML, or method called too early
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		return theGuiableList;
	}
	
	protected ArrayList<? extends GcgGuiable> getPrimaryParentSecondaryChildSecondaryChildMoveTargetGuiableList() {  // widget broken/unfinished, bad XML, or method called too early
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		return theGuiableList;
	}
	
	protected ArrayList<? extends GcgGuiable> getSecondaryParentPrimaryChildMoveTargetGuiableList() {  // widget broken/unfinished, bad XML, or method called too early
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		return theGuiableList;
	}
	
	protected ArrayList<? extends GcgGuiable> getSecondaryParentPrimaryChildPrimaryGrandchildMoveTargetGuiableList() {  // widget broken/unfinished, bad XML, or method called too early
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		return theGuiableList;
	}
	
	protected ArrayList<? extends GcgGuiable> getSecondaryParentSecondaryChildMoveTargetGuiableList() {  // widget broken/unfinished, bad XML, or method called too early
		ArrayList<GcgGuiable> theGuiableList = new ArrayList<GcgGuiable>();
		return theGuiableList;
	}

	public void removeFmmHeadlineNode(String aNodeIdString, int aLogicalNextPosition) {
		if(this.gcgGuiableList.size() == 0) {
			return;
		}
		FmmHeadlineNode theFmmHeadlineNode = getHeadlineNodeFromGuiableList(aNodeIdString);
		if(theFmmHeadlineNode == null) {
			return;
		}
		int thePositionAnchor = this.gcgGuiableList.indexOf(theFmmHeadlineNode);
		this.gcgGuiableList.remove(theFmmHeadlineNode);
		this.arrayAdapter = new GcgGuiableSpinnerArrayAdapter(getContext(), new ArrayList<GcgGuiable>(this.gcgGuiableList), isDisplayOnlyDrawable());
		this.spinner.setAdapter(this.arrayAdapter);
		this.spinner.setSelection(getLogicalPosition(aLogicalNextPosition, thePositionAnchor));
	}

	private FmmHeadlineNode getHeadlineNodeFromGuiableList(String aNodeIdString) {
		FmmHeadlineNode theFmmHeadlineNode = null;
		for(GcgGuiable theGcgGuiable : this.gcgGuiableList) {
			if(((FmmHeadlineNode) theGcgGuiable).getNodeIdString().equals(aNodeIdString)) {
				theFmmHeadlineNode = (FmmHeadlineNode) theGcgGuiable;
				break;
			}
		}
		return theFmmHeadlineNode;
	}

	public FmmNode getFmmNode() {
		return (FmmNode) this.spinner.getSelectedItem();
	}

    protected boolean isMinimumInput() {
        boolean theBoolean = getListSize() > 0;
        enableZoomButton(theBoolean);
        return theBoolean && getSelectedItem() != null;
    }

}
