/* @(#)GcgIncrementalStateButton.java
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
package com.flywheelms.gcongui.gcg.button;

import android.content.Context;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.SoundEffectConstants;
import android.widget.Button;
// com.flywheelms.gcongui.gcg.button.GcgIncrementalStateButton
public class GcgIncrementalStateButton extends Button {
	
	protected boolean INCREMENT_STATE_ON_PERFORM_CLICK = true;
	protected int state;
	public static final int state__ON = 1;
	public static final int state__LEVEL_2 = 2;
	public static final int state__LEVEL_3 = 3;
	public static final int state__LEVEL_4 = 4;
	private int MAX_STATES;
	private int state__OFF;
	protected int[] drawableResourceIdArray;

    public GcgIncrementalStateButton(Context aContext) {
    	super(aContext);
    }
    
    public GcgIncrementalStateButton(Context aContext, int[] aDrawableResourceIdArray) {
        super(aContext);
        initialize(aDrawableResourceIdArray, 1);
    }

	public GcgIncrementalStateButton(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	public GcgIncrementalStateButton(Context aContext, AttributeSet anAttributeSet, int[] aDrawableResourceIdArray) {
        super(aContext, anAttributeSet);
        initialize(aDrawableResourceIdArray, 1);
	}

	public GcgIncrementalStateButton(Context aContext, AttributeSet anAttributeSet, int aDefStyle) {
		super(aContext, anAttributeSet, aDefStyle);
	}

	public GcgIncrementalStateButton(Context aContext, AttributeSet anAttributeSet, int aDefStyle, int[] aDrawableResourceIdArray) {
        super(aContext, anAttributeSet, aDefStyle);
        initialize(aDrawableResourceIdArray, 1);
	}

	public void initialize(int[] aDrawableResourceIdArray, int anInitialButtonState) {
		this.drawableResourceIdArray = aDrawableResourceIdArray;
		this.MAX_STATES = this.drawableResourceIdArray.length;
		this.state__OFF = this.MAX_STATES;
		setState(anInitialButtonState);
	}
	
	public void setIncrementSateOnPerformClick(boolean aBoolean) {
		this.INCREMENT_STATE_ON_PERFORM_CLICK = aBoolean;
	}
    
    @Override
    public boolean performClick() {
            if(this.INCREMENT_STATE_ON_PERFORM_CLICK) {
            	incrementState();
            }
            return super.performClick();
    }
    
    public void performQuietClick() {
            if(this.INCREMENT_STATE_ON_PERFORM_CLICK) {
            	incrementState();
            }
    }
	
    @Override
    public boolean performLongClick() {
    	playSoundEffect(SoundEffectConstants.CLICK);
    	decrimentState();
    	return super.performLongClick();
    }
    
    public boolean isOff() {
    	return this.state == this.state__OFF ? true : false;
    }
    
    public boolean isOn() {
    	return this.state == GcgIncrementalStateButton.state__ON ? true : false;
    }
    
    public void setState(int aState) {
            if((aState > 0) && (aState <= this.MAX_STATES)) {
                    this.state = aState;
                    setBackgroundDrawable();
            }
    }
    
    public int getState() {
            return this.state;
    }

    public void incrementState() {
            this.state++;
            if(this.state > this.MAX_STATES) {
                    this.state = GcgIncrementalStateButton.state__ON;
            }
            setBackgroundDrawable();
    }
    
    public void decrimentState() {
            this.state--;
            if(this.state < GcgIncrementalStateButton.state__ON) {
                    this.state = this.MAX_STATES;
            }
            setBackgroundDrawable();
    }
    
    public int getMaxStates() {
    	return this.MAX_STATES;
    }
    
    protected void setBackgroundDrawable() {
    	this.setBackgroundResource(this.drawableResourceIdArray[this.state - 1]);
    }
    
	public void performTouchDown() {
		StateListDrawable theStateListDrawable = (StateListDrawable) this.getBackground();
		theStateListDrawable.setState(PRESSED_ENABLED_STATE_SET);
	}

}
