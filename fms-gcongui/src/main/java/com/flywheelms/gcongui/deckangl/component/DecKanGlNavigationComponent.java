/* @(#)DecKanGlNavigationComponent.java
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

package com.flywheelms.gcongui.deckangl.component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlAnnotatedGlyphSize;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;

public abstract class DecKanGlNavigationComponent extends LinearLayout {

	protected DecKanGlNavigationComponentParent decKanGlNavigationParent;
	private ImageView annotatedDecKanGlGlyphView;
	private Button topLeftNavigationButton;  // navigtion_button__top__left
	private Button topMiddleNavigationButton;
	private Button topRightNavigationButton;
	private Button bottomLeftNavigationButton;
	private Button bottomMiddleNavigationButton;
	private Button bottomRightNavigationButton;
	private Button leftTopNavigationButton;
	private Button leftMiddleNavigationButton;
	private Button leftBottomNavigationButton;
	private Button rightTopNavigationButton;
	private Button rightMiddleNavigationButton;
	private Button rightBottomNavigationButton;
	
	public DecKanGlNavigationComponent(Context context, AttributeSet attrs) {
		super(context, attrs);
		inflate(context, R.layout.deckangl__navigation__component, this);
		initializeViewBody();
	}

    protected ImageView getDecKanGlGlyphView() {
        return this.annotatedDecKanGlGlyphView;
    }
	
	public void setParent(DecKanGlNavigationComponentParent aNodeNavigationParent) {
		this.decKanGlNavigationParent = aNodeNavigationParent;
	}

	protected void initializeViewBody() {
		this.annotatedDecKanGlGlyphView = (ImageView) findViewById(R.id.annotated_deckangl);
		this.topLeftNavigationButton = (Button) findViewById(R.id.navigtion_button__top__left);
		this.topLeftNavigationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToDecoratorDetails(DecKanGlDecoratorCanvasLocation.top_zone_LEFT);
			}
		});
		this.topMiddleNavigationButton = (Button) findViewById(R.id.navigtion_button__top__middle);
		this.topMiddleNavigationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToDecoratorDetails(DecKanGlDecoratorCanvasLocation.top_zone_MIDDLE);
			}
		});
		this.topRightNavigationButton = (Button) findViewById(R.id.navigtion_button__top__right);
		this.topRightNavigationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToDecoratorDetails(DecKanGlDecoratorCanvasLocation.top_zone_RIGHT);
			}
		});
		this.bottomLeftNavigationButton = (Button) findViewById(R.id.navigtion_button__bottom__left);
		this.bottomLeftNavigationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToDecoratorDetails(DecKanGlDecoratorCanvasLocation.bottom_zone_LEFT);
			}
		});
		this.bottomMiddleNavigationButton = (Button) findViewById(R.id.navigtion_button__bottom__middle);
		this.bottomMiddleNavigationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToDecoratorDetails(DecKanGlDecoratorCanvasLocation.bottom_zone_MIDDLE);
			}
		});
		this.bottomRightNavigationButton = (Button) findViewById(R.id.navigtion_button__bottom__right);
		this.bottomRightNavigationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToDecoratorDetails(DecKanGlDecoratorCanvasLocation.bottom_zone_RIGHT);
			}
		});
		this.leftTopNavigationButton = (Button) findViewById(R.id.navigtion_button__left__top);
		this.leftTopNavigationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToDecoratorDetails(DecKanGlDecoratorCanvasLocation.left_zone_TOP);
			}
		});
		this.leftMiddleNavigationButton = (Button) findViewById(R.id.navigtion_button__left__middle);
		this.leftMiddleNavigationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToDecoratorDetails(DecKanGlDecoratorCanvasLocation.left_zone_MIDDLE);
			}
		});
		this.leftBottomNavigationButton = (Button) findViewById(R.id.navigtion_button__left__bottom);
		this.leftBottomNavigationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToDecoratorDetails(DecKanGlDecoratorCanvasLocation.left_zone_BOTTOM);
			}
		});
		this.rightTopNavigationButton = (Button) findViewById(R.id.navigtion_button__right__top);
		this.rightTopNavigationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToDecoratorDetails(DecKanGlDecoratorCanvasLocation.right_zone_TOP);
			}
		});
		this.rightMiddleNavigationButton = (Button) findViewById(R.id.navigtion_button__right__middle);
		this.rightMiddleNavigationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToDecoratorDetails(DecKanGlDecoratorCanvasLocation.right_zone_MIDDLE);
			}
		});
		this.rightBottomNavigationButton = (Button) findViewById(R.id.navigtion_button__right__bottom);
		this.rightBottomNavigationButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				navigateToDecoratorDetails(DecKanGlDecoratorCanvasLocation.right_zone_BOTTOM);
			}
		});
	}

	private void setButtonBackground(Button aNavigationButton, DecKanGlDecoratorCanvasLocation aCanvasLocation, boolean bIsHorizontal) {
		int theDrawableResourceId = 0;
		boolean isEnabled = false;
		switch(this.decKanGlNavigationParent.getDecKanGlGlyph().getDecoratorForLocation(aCanvasLocation).getColor()) {
		case BLUE:
			if(bIsHorizontal) {
				theDrawableResourceId = R.drawable.gcg__button_state_list__deckangl_navigation__horizontal___blue;
			} else {
				theDrawableResourceId = R.drawable.gcg__button_state_list__deckangl_navigation__vertical___blue;
			}
			isEnabled = true;
			break;
		case DISABLED:
			if(bIsHorizontal) {
				theDrawableResourceId = R.drawable.gcg__deckangl_navigation_button__horizontal__transparent;
			} else {
				theDrawableResourceId = R.drawable.gcg__deckangl_navigation_button__vertical__transparent;
			}
			isEnabled = false;
			break;
		case GRAY:
			if(bIsHorizontal) {
				theDrawableResourceId = R.drawable.gcg__button_state_list__deckangl_navigation__horizontal___gray;
			} else {
				theDrawableResourceId = R.drawable.gcg__button_state_list__deckangl_navigation__vertical___gray;
			}
			isEnabled = true;
			break;
		case GREEN:
			if(bIsHorizontal) {
				theDrawableResourceId = R.drawable.gcg__deckangl_navigation_button__horizontal__transparent;
			} else {
				theDrawableResourceId = R.drawable.gcg__deckangl_navigation_button__vertical__transparent;
			}
			isEnabled = false;
			break;
		case ORANGE:
			if(bIsHorizontal) {
				theDrawableResourceId = R.drawable.gcg__button_state_list__deckangl_navigation__horizontal___orange;
			} else {
				theDrawableResourceId = R.drawable.gcg__button_state_list__deckangl_navigation__vertical___orange;
			}
			isEnabled = true;
			break;
		case PINK:
			if(bIsHorizontal) {
				theDrawableResourceId = R.drawable.gcg__button_state_list__deckangl_navigation__horizontal___pink;
			} else {
				theDrawableResourceId = R.drawable.gcg__button_state_list__deckangl_navigation__vertical___pink;
			}
			isEnabled = true;
			break;
		case RED:
			if(bIsHorizontal) {
				theDrawableResourceId = R.drawable.gcg__button_state_list__deckangl_navigation__horizontal___red;
			} else {
				theDrawableResourceId = R.drawable.gcg__button_state_list__deckangl_navigation__vertical___red;
			}
			isEnabled = true;
			break;
		case TRANSPARENT:
			if(bIsHorizontal) {
				theDrawableResourceId = R.drawable.gcg__button_state_list__deckangl_navigation__horizontal___portobello;
			} else {
				theDrawableResourceId = R.drawable.gcg__button_state_list__deckangl_navigation__vertical___portobello;
			}
			isEnabled = true;
			break;
		case YELLOW:
			if(bIsHorizontal) {
				theDrawableResourceId = R.drawable.gcg__button_state_list__deckangl_navigation__horizontal___yellow;
			} else {
				theDrawableResourceId = R.drawable.gcg__button_state_list__deckangl_navigation__vertical___yellow;
			}
			isEnabled = true;
			//$FALL-THROUGH$
		default:
		}
		aNavigationButton.setBackgroundResource(theDrawableResourceId);
		aNavigationButton.setEnabled(isEnabled);
	}

	protected abstract void navigateToDecoratorDetails(DecKanGlDecoratorCanvasLocation aCanvasLocation);
	
	public void viewData() {
		this.annotatedDecKanGlGlyphView.setImageBitmap(this.decKanGlNavigationParent.getDecKanGlGlyph().getAnnotatedGlyphBitmap(DecKanGlAnnotatedGlyphSize.MEDIUM));
		setButtonBackground(this.topLeftNavigationButton, DecKanGlDecoratorCanvasLocation.top_zone_LEFT, true);
		setButtonBackground(this.topMiddleNavigationButton, DecKanGlDecoratorCanvasLocation.top_zone_MIDDLE, true);
		setButtonBackground(this.topRightNavigationButton, DecKanGlDecoratorCanvasLocation.top_zone_RIGHT, true);
		setButtonBackground(this.bottomLeftNavigationButton, DecKanGlDecoratorCanvasLocation.bottom_zone_LEFT, true);
		setButtonBackground(this.bottomMiddleNavigationButton, DecKanGlDecoratorCanvasLocation.bottom_zone_MIDDLE, true);
		setButtonBackground(this.bottomRightNavigationButton, DecKanGlDecoratorCanvasLocation.bottom_zone_RIGHT, true);
		setButtonBackground(this.leftTopNavigationButton, DecKanGlDecoratorCanvasLocation.left_zone_TOP, false);
		setButtonBackground(this.leftMiddleNavigationButton, DecKanGlDecoratorCanvasLocation.left_zone_MIDDLE, false);
		setButtonBackground(this.leftBottomNavigationButton, DecKanGlDecoratorCanvasLocation.left_zone_BOTTOM, false);
		setButtonBackground(this.rightTopNavigationButton, DecKanGlDecoratorCanvasLocation.right_zone_TOP, false);
		setButtonBackground(this.rightMiddleNavigationButton, DecKanGlDecoratorCanvasLocation.right_zone_MIDDLE, false);
		setButtonBackground(this.rightBottomNavigationButton, DecKanGlDecoratorCanvasLocation.right_zone_BOTTOM, false);
	}

}
