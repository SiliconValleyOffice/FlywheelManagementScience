/* @(#)GcgViewFlipper.java
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

package com.flywheelms.library.gcg.viewflipper;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.widget.ViewFlipper;

import com.flywheelms.library.R;
import com.flywheelms.library.gcg.GcgActivity;
import com.flywheelms.library.gcg.interfaces.GcgFlingController;

public class GcgViewFlipper extends ViewFlipper implements GcgFlingController {
	
	private GcgActivity gcgActivity;
	private final Context context;
	private Animation flipInFromLeftAnimation;
	private Animation flipOutToRightAnimation;
	private Animation flipInFromRightAnimation;
	private Animation flipOutToLeftAnimation;
	private GcgViewFlipperListener viewFlipListener;
	public static final boolean FLIP_IN_FROM_LEFT = true;
	public static final boolean FLIP_IN_FROM_RIGHT = false;
	private int previousDisplayedChild = 0;
	private boolean firstLayout = true;
	protected int spinnnableMenuIndex;
//	private OnTouchListener flingListener;

	public GcgViewFlipper(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		this.context = aContext;
		initializeAnimations();
//		this.flingListener = GcgHelper.getFlingListener(aContext, this);
//		setOnTouchListener(this.flingListener);
	}
	
	public void initialize(GcgActivity aLibraryActivity) {
		this.gcgActivity = aLibraryActivity;
	}
	
	public void initialize(GcgActivity aLibraryActivity, GcgViewFlipperListener aViewFlipListener) {
		this.gcgActivity = aLibraryActivity;
		this.viewFlipListener = aViewFlipListener;
	}
	
	public GcgActivity getGcgActivity() {
		return this.gcgActivity;
	}
	
	private void initializeAnimations() {
		setInAnimation(this.context, R.anim.rotate_in_from_left);
		this.flipInFromLeftAnimation = getInAnimation();
		this.flipInFromLeftAnimation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				GcgViewFlipper.this.beforeViewFlip();
			}
			@Override
			public void onAnimationRepeat(Animation animation) { return; }
			@Override
			public void onAnimationEnd(Animation animation) {
				GcgViewFlipper.this.afterViewFlip();
			}
		});
		setOutAnimation(this.context, R.anim.rotate_out_to_right);
		this.flipOutToRightAnimation = getOutAnimation();
		setInAnimation(this.context, R.anim.rotate_in_from_right);
		this.flipInFromRightAnimation = getInAnimation();
		this.flipInFromRightAnimation.setAnimationListener(new Animation.AnimationListener() {
			@Override
			public void onAnimationStart(Animation animation) {
				GcgViewFlipper.this.beforeViewFlip();
			}
			@Override
			public void onAnimationRepeat(Animation animation) { return; }
			@Override
			public void onAnimationEnd(Animation animation) {
				GcgViewFlipper.this.afterViewFlip();
			}
		});
		setOutAnimation(this.context, R.anim.rotate_out_to_left);
		this.flipOutToLeftAnimation = getOutAnimation();
	}

	protected void afterViewFlip() {
		int theDisplayedChild = getDisplayedChild();
//		if(theDisplayedChild != this.previousDisplayedChild) {
//			((GcgViewFlipperView) this.getChildAt(this.previousDisplayedChild)).deactivateView();
//		}
		((GcgViewFlipperChildView) this.getChildAt(theDisplayedChild)).activateView();
		if(this.viewFlipListener != null) {
			this.viewFlipListener.onViewFlip(this.previousDisplayedChild, theDisplayedChild);
		}
//		getGcgActivity().stopActivityStatusAnimation();
	}

	public void flipToIndex(int anIndex) {
		if(anIndex == getDisplayedChild()) {
			return;
		}
		if(getDisplayedChild() == 0) {
			setInAnimation(this.flipInFromRightAnimation);
			setOutAnimation(this.flipOutToLeftAnimation);
		} else if(anIndex == 0) {
			setInAnimation(this.flipInFromLeftAnimation);
			setOutAnimation(this.flipOutToRightAnimation);
		} else if(anIndex < getDisplayedChild()) {
			setInAnimation(this.flipInFromRightAnimation);
			setOutAnimation(this.flipOutToLeftAnimation);
		} else {
			setInAnimation(this.flipInFromLeftAnimation);
			setOutAnimation(this.flipOutToRightAnimation);
		}
		setDisplayedChild(anIndex);
	}
	
	@Override
	public void setDisplayedChild(int anIndex) {
		this.previousDisplayedChild = getDisplayedChild();
//		beforeFlip();
		super.setDisplayedChild(anIndex);
//		afterFlip();
	}
	
	public void reDisplayChild() {
		super.setDisplayedChild(getDisplayedChild());
	}

	protected void beforeViewFlip() {
		((GcgViewFlipperChildView) this.getChildAt(this.previousDisplayedChild)).deactivateView();
	}

	public int getFrameMenuSpacerCurtainBackgroundResourceId() {
		return R.color.gcg__frame_menu__spacer_backbround__default_curtain;
	}

	public void flipToIndex(int anIndex, boolean aBoolean) {
		if(anIndex == getDisplayedChild()) {
			return;
		}
		if(aBoolean == FLIP_IN_FROM_LEFT) {
			setInAnimation(this.flipInFromLeftAnimation);
			setOutAnimation(this.flipOutToRightAnimation);
		} else {
			setInAnimation(this.flipInFromRightAnimation);
			setOutAnimation(this.flipOutToLeftAnimation);
		}
		setDisplayedChild(anIndex);
	}

	public Animation getFlipInFromLeftAnimation() {
		return this.flipInFromLeftAnimation;
	}

	public Animation getFlipOutToRightAnimation() {
		return this.flipOutToRightAnimation;
	}

	public Animation getFlipInFromRightAnimation() {
		return this.flipInFromRightAnimation;
	}

	public Animation getFlipOutToLeftAnimation() {
		return this.flipOutToLeftAnimation;
	}

	public void flipNext() {
		int theIndex = getDisplayedChild();
		int theCount = getChildCount();
		if(theIndex == 1) {
			return;
		} else if(theIndex == 0) {
			flipToIndex(theCount - 1, false);
		} else {
			flipToIndex(theIndex - 1, false);
		}
	}

	public void flipPrevious() {
		int theIndex = getDisplayedChild();
		int theCount = getChildCount();
		if(theIndex == 0) {
			return;
		} else if(theIndex == theCount - 1) {
			flipToIndex(0, true);
		} else {
			flipToIndex(theIndex + 1, true);
		}
	}

	@Override
	public void onFlingUp() {
		return;
	}

	@Override
	public void onFlingDown() {
		return;
	}

	@Override
	public void onFlingRight() {
		flipPrevious();
	}

	@Override
	public void onFlingLeft() {
		flipNext();
	}
	
	@Override
	public void onLayout(boolean bChanges, int left, int top, int right, int bottom) {
		if(this.firstLayout) {
			initializeViewFlipperViews();
			this.firstLayout = false;
		}
		super.onLayout(bChanges, left, top, right, bottom);
	}

	protected void initializeViewFlipperViews() {
		initializePageTitles();
	}
	
	public GcgViewFlipperChildView getGcgViewFlipperView() {
		return (GcgViewFlipperChildView) getCurrentView();
	}
	
	public int getViewFlipperViewIndex() {
		return indexOfChild(getCurrentView());
	}
	
	public void initializePageTitles() {
		for(int theIndex = 0; theIndex < getChildCount(); ++theIndex) {
			((GcgViewFlipperChildView)getChildAt(theIndex)).initializeTitleBar();
		}
	}

	public String getPreviousPageTitle(GcgViewFlipperChildView aViewFlipperView) {
		int theIndex = indexOfChild(aViewFlipperView);
		int theCount = getChildCount();
		String theTitleString;
		if(theIndex == 0) {
			theTitleString = "";
		} else if(theIndex == theCount - 1) {
			theTitleString = ((GcgViewFlipperChildView)getChildAt(0)).getAbreviatedPageTitle();
		} else {
			theTitleString = ((GcgViewFlipperChildView)getChildAt(theIndex + 1)).getAbreviatedPageTitle();
		}
		return theTitleString;
	}

	public String getNextPageTitle(GcgViewFlipperChildView aViewFlipperView) {
		int theIndex = indexOfChild(aViewFlipperView);
		int theCount = getChildCount();
		String theTitleString;
		if(theIndex == 1) {
			theTitleString = "";
		} else if(theIndex == 0) {
			theTitleString = ((GcgViewFlipperChildView)getChildAt(theCount - 1)).getAbreviatedPageTitle();
		} else {
			theTitleString = ((GcgViewFlipperChildView)getChildAt(theIndex - 1)).getAbreviatedPageTitle();
		}
		return theTitleString;
	}
	
//	public OnTouchListener getFlingListener() {
//		return this.flingListener;
//	}

	public void saveGuiState() {
		int theCount = getChildCount();
		if(theCount == 0) {
			return;
		}
		for(int theIndex = 0 ; theIndex < theCount ; ++theIndex) {
			((GcgViewFlipperChildView) getChildAt(theIndex)).guiPreferencesSaveTransient();
		}
	}

	public void restoreGuiState() {
		int theCount = getChildCount();
		if(theCount == 0) {
			return;
		}
		for(int theIndex = 0 ; theIndex < theCount ; ++theIndex) {
			((GcgViewFlipperChildView) getChildAt(theIndex)).guiPreferencesRestoreTransient();
		}
	}

}
