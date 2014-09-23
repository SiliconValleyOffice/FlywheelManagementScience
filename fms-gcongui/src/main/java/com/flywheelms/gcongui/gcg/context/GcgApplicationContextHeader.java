/* @(#)GcgApplicationContextHeader.java
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

package com.flywheelms.gcongui.gcg.context;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.dialog.GcgApplicationContextNavigationDialog;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;

public class GcgApplicationContextHeader extends LinearLayout {

	public static final int index__ACTIVITY_DRAWABLE = 1;
	public static final int index__ACTIVITY_TEXT_VIEW = 2;
	public static final int index__FRAME_DRAWABLE = 3;
	public static final int index__FRAME_TEXT_VIEW = 4;
	public static final int index__PERSPECTIVE_DRAWABLE = 5;
	public static final int index__PERSPECTIVE_CONTEXT_CONTAINER = 6;
	
	private HorizontalScrollView scrollView;
	private ImageView dataSourceImageView;
	private TextView dataSourceTextView;
	private LinearLayout activityBreadcrumbListContainer;
	private Button navigationButton;
	private GcgApplicationContext applicationContext;
    private GcgActivity gcgActivity;

	public GcgApplicationContextHeader(Context aContext, AttributeSet attrs) {
		super(aContext, attrs);
		LayoutInflater.from(aContext).inflate(R.layout.gcg__application_context__header, this);
		this.scrollView = (HorizontalScrollView) findViewById(R.id.gcg__application_context__scroll_view);
		this.dataSourceImageView = (ImageView) findViewById(R.id.gcg__application_context__data_source__drawable);
		this.dataSourceTextView = (TextView) findViewById(R.id.gcg__application_context__data_source__name);
		this.activityBreadcrumbListContainer = (LinearLayout) findViewById(R.id.gcg__application_context__activity_breadcrumb_list);
		this.navigationButton = (Button) findViewById(R.id.gcg__application_context__navigation_button);
	}

	public void initialize(final GcgActivity aGcgActivity) {
        this.gcgActivity = aGcgActivity;
		this.applicationContext = aGcgActivity.getGcgApplicationContext();
		if(this.applicationContext == null) {
			this.dataSourceImageView.setVisibility(View.GONE);
			this.dataSourceTextView.setVisibility(View.GONE);
			this.activityBreadcrumbListContainer.setVisibility(View.GONE);
			this.navigationButton.setVisibility(View.GONE);
			return;
		}
		this.dataSourceImageView.setImageResource(this.applicationContext.getDataSourceDrawableResourceId());
		this.dataSourceTextView.setText(this.applicationContext.getDataSourceName());
		if(this.applicationContext.getActivityBreadcrumbList().size() < 1) {
			this.activityBreadcrumbListContainer.setVisibility(View.GONE);
			this.navigationButton.setVisibility(View.GONE);
			return;
		}
		this.navigationButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				GcgApplicationContextNavigationDialog theNavigationDialog = new GcgApplicationContextNavigationDialog(aGcgActivity);
                GcgApplicationContextHeader.this.gcgActivity.startDialog(theNavigationDialog);
			}
		});
		this.activityBreadcrumbListContainer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				GcgApplicationContextNavigationDialog theNavigationDialog = new GcgApplicationContextNavigationDialog(aGcgActivity);
                GcgApplicationContextHeader.this.gcgActivity.startDialog(theNavigationDialog);
			}
		});
		displayFirstActivityBreadcrumb();
		if(this.applicationContext.getActivityBreadcrumbList().size() != 1) {
			if(this.applicationContext.getActivityBreadcrumbList().size() > 2) {
				displayAbbreviatedActivityBreadcrumbs();
			}
			displayLastActivityBreadcrumb();
		}
		this.scrollView.scrollTo(999999, 0);  // ToDo - does not work
	}

	private void displayLastActivityBreadcrumb() {
		GcgActivityBreadcrumb theLastActivityBreadcrumb = this.applicationContext.getActivityBreadcrumbList().get(this.applicationContext.getActivityBreadcrumbList().size() - 1);
        this.gcgActivity.pruneLastApplicationContextBreadcrumb(theLastActivityBreadcrumb);
		LinearLayout theActivityBreadcrumbContainer = GcgHelper.inflateLinearLayout(getContext(), R.layout.gcg__application_context__activity_breadcrumb, this.activityBreadcrumbListContainer);
		((ImageView) theActivityBreadcrumbContainer.getChildAt(index__ACTIVITY_DRAWABLE)).setImageResource(
				theLastActivityBreadcrumb.getActivityDrawableResourceId());
		((TextView) theActivityBreadcrumbContainer.getChildAt(index__ACTIVITY_TEXT_VIEW)).setText(
				theLastActivityBreadcrumb.getActivityName());
        if(theLastActivityBreadcrumb.getFrameBreadcrumb() == null) {
            theActivityBreadcrumbContainer.removeViewAt(index__PERSPECTIVE_CONTEXT_CONTAINER);
            theActivityBreadcrumbContainer.removeViewAt(index__PERSPECTIVE_DRAWABLE);
            theActivityBreadcrumbContainer.removeViewAt(index__FRAME_TEXT_VIEW);
            theActivityBreadcrumbContainer.removeViewAt(index__FRAME_DRAWABLE);
            return;
        }
		if(theLastActivityBreadcrumb.getFrameBreadcrumb().hasText()) {
			((TextView) theActivityBreadcrumbContainer.getChildAt(index__FRAME_TEXT_VIEW)).setText(
					theLastActivityBreadcrumb.getFrameBreadcrumb().getText());
		} else {
			theActivityBreadcrumbContainer.removeViewAt(index__PERSPECTIVE_CONTEXT_CONTAINER);
			theActivityBreadcrumbContainer.removeViewAt(index__PERSPECTIVE_DRAWABLE);
			theActivityBreadcrumbContainer.removeViewAt(index__FRAME_TEXT_VIEW);
			theActivityBreadcrumbContainer.removeViewAt(index__FRAME_DRAWABLE);
			return;
		}
		if(theLastActivityBreadcrumb.getFrameBreadcrumb().hasPerspective()) {
			((ImageView) theActivityBreadcrumbContainer.getChildAt(index__PERSPECTIVE_DRAWABLE)).setImageResource(
					theLastActivityBreadcrumb.getFrameBreadcrumb().getPerspectiveBreadcrumb().getDrawableResourceId());
		} else {
			theActivityBreadcrumbContainer.removeViewAt(index__PERSPECTIVE_CONTEXT_CONTAINER);
			theActivityBreadcrumbContainer.removeViewAt(index__PERSPECTIVE_DRAWABLE);
			return;
		}
		if(! theLastActivityBreadcrumb.hasPerspectiveContext()) {
			theActivityBreadcrumbContainer.removeViewAt(index__PERSPECTIVE_CONTEXT_CONTAINER);
			return;
		}
		LinearLayout thePerspectiveContextContainer = (LinearLayout) theActivityBreadcrumbContainer.getChildAt(index__PERSPECTIVE_CONTEXT_CONTAINER);
		for(GcgContextBreadcrumb thePerspectiveBreadcrumb : theLastActivityBreadcrumb.getFrameBreadcrumb().getPerspectiveContext()) {
			addPerspectiveContextSeparator(thePerspectiveContextContainer);
			addContextDrawable(thePerspectiveContextContainer, thePerspectiveBreadcrumb.getDrawableResourceId());
			addContextTextView(thePerspectiveContextContainer, thePerspectiveBreadcrumb.getText());
		}
	}

	private void displayAbbreviatedActivityBreadcrumbs() {
		for(int theIndex = 1; theIndex < this.applicationContext.getActivityBreadcrumbList().size() - 1; ++theIndex) {
			GcgActivityBreadcrumb theActivityBreadcrumb = this.applicationContext.getActivityBreadcrumbList().get(theIndex);
			LinearLayout theActivityBreadcrumbContainer = GcgHelper.inflateLinearLayout(getContext(), R.layout.gcg__application_context__activity_breadcrumb, this.activityBreadcrumbListContainer);
			theActivityBreadcrumbContainer.removeViewAt(index__PERSPECTIVE_CONTEXT_CONTAINER);
			((ImageView) theActivityBreadcrumbContainer.getChildAt(index__ACTIVITY_DRAWABLE)).setImageResource(
					theActivityBreadcrumb.getActivityDrawableResourceId());
			((TextView) theActivityBreadcrumbContainer.getChildAt(index__ACTIVITY_TEXT_VIEW)).setText(
					theActivityBreadcrumb.getActivityName());
			if(theActivityBreadcrumb.hasFrameBreadcrumb()) {
				((TextView) theActivityBreadcrumbContainer.getChildAt(index__FRAME_TEXT_VIEW)).setText(
						theActivityBreadcrumb.getFrameBreadcrumb().getText());
			} else {
				theActivityBreadcrumbContainer.removeViewAt(index__PERSPECTIVE_DRAWABLE);
				theActivityBreadcrumbContainer.removeViewAt(index__FRAME_TEXT_VIEW);
				theActivityBreadcrumbContainer.removeViewAt(index__FRAME_DRAWABLE);
				return;
			}
			if(theActivityBreadcrumb.getFrameBreadcrumb().hasPerspective()) {
				((ImageView) theActivityBreadcrumbContainer.getChildAt(index__PERSPECTIVE_DRAWABLE)).setImageResource(
						theActivityBreadcrumb.getFrameBreadcrumb().getPerspectiveBreadcrumb().getDrawableResourceId());
			} else {
				theActivityBreadcrumbContainer.removeViewAt(index__PERSPECTIVE_DRAWABLE);
				return;
			}
		}
	}

	private void displayFirstActivityBreadcrumb() {
		GcgActivityBreadcrumb theFirstActivityBreadcrumb = this.applicationContext.getActivityBreadcrumbList().get(0);
		this.activityBreadcrumbListContainer.removeAllViews();
		if(theFirstActivityBreadcrumb.getFrameBreadcrumb().hasText()) {
			GcgHelper.inflateImagetView(getContext(), R.layout.gcg__application_context__frame__image_view, this.activityBreadcrumbListContainer);
			GcgHelper.inflateTextView(getContext(), R.layout.gcg__application_context__frame__text_view, this.activityBreadcrumbListContainer).setText(
					theFirstActivityBreadcrumb.getFrameBreadcrumb().getText() );
			if(theFirstActivityBreadcrumb.getFrameBreadcrumb().hasPerspectiveBreadcrumb()) {
				addContextDrawable(this.activityBreadcrumbListContainer, theFirstActivityBreadcrumb.getFrameBreadcrumb().getPerspectiveDrawableResourceId());
				if(this.applicationContext.getActivityBreadcrumbList().size() == 1 && theFirstActivityBreadcrumb.getFrameBreadcrumb().hasPerspectiveContext()) {
					LinearLayout thePerspectiveContextContainer = GcgHelper.inflateLinearLayout(
							getContext(), R.layout.gcg__application_context__perspective_context__container, this.activityBreadcrumbListContainer);
					for(GcgContextBreadcrumb theContextBreadcrumb : theFirstActivityBreadcrumb.getFrameBreadcrumb().getPerspectiveContext()) {
						addPerspectiveContextSeparator(thePerspectiveContextContainer);
						addContextDrawable(thePerspectiveContextContainer, theContextBreadcrumb.getDrawableResourceId());
						addContextTextView(thePerspectiveContextContainer, theContextBreadcrumb.getText());
					}
				}
			}
		}
	}

	private void addPerspectiveContextSeparator(LinearLayout aContainer) {
		inflate(getContext(), R.layout.gcg__application_context__perspective_context__separator, aContainer);
	}

	private void addContextDrawable(LinearLayout aContainer, int aDrawableResourceId) {
		ImageView theImageView = GcgHelper.inflateImagetView(getContext(), R.layout.gcg__application_context__image_view, aContainer);
		theImageView.setImageResource(aDrawableResourceId);
	}

	private void addContextTextView(LinearLayout aContainer, String aString) {
		TextView theTextView = GcgHelper.inflateTextView(getContext(), R.layout.gcg__application_context__text_view, aContainer);
		theTextView.setText(aString);
	}
	
	public void resetApplicationContext() {
		this.applicationContext = null;
		this.dataSourceImageView.setVisibility(View.GONE);
		this.dataSourceTextView.setVisibility(View.GONE);
		this.activityBreadcrumbListContainer.setVisibility(View.GONE);
		this.navigationButton.setVisibility(View.GONE);
	}

	public void removeNavigationButton() {
		GcgHelper.enableNavigationButton(this.navigationButton, false);
		if(this.activityBreadcrumbListContainer != null) {
			this.activityBreadcrumbListContainer.setOnClickListener(null);
		}
	}

	public HorizontalScrollView getScrollView() {
		return this.scrollView;
	}

}
