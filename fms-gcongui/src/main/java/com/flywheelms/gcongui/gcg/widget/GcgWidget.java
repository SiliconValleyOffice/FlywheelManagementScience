/* @(#)GcgWidget.java
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
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.SoundEffectConstants;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.fdk.interfaces.FdkDictationResultsConsumer;
import com.flywheelms.gcongui.fdk.interfaces.FdkListener;
import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.button.multi_shift.GcgMultiShiftState;
import com.flywheelms.gcongui.gcg.dialog.GcgDialog;
import com.flywheelms.gcongui.gcg.helper.GcgGuiHelper;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;

import java.util.ArrayList;

public abstract class GcgWidget extends RelativeLayout implements FdkDictationResultsConsumer {
	
	protected GcgActivity gcgActivity;
	protected GcgDialog gcgDialog;
	private FdkListener fdkListener;
	protected ArrayList<GcgOnSetTextListener> onSetTextListenerList = new ArrayList<GcgOnSetTextListener>(); 
	public static final String container_layout__MENU_PARAMETER = "menu_parameter";
	public static final String container_layout__VERTICAL = "vertical";
	public static final String container_layout__VERTICAL__NO_LABEL_DRAWABLE = "vertical__no_label_drawable";
	public static final String container_layout__HORIZONTAL = "horizontal";
	public static final String container_layout__HORIZONTAL__NO_LABEL_DRAWABLE = "horizontal__no_label_drawable";
	public static final String container_layout__NO_LABEL = "no_label";
	public static final String on_click__DEFAULT = "default";
	public static final String on_click__CUSTOM = "custom";
	public static final String on_click__NONE = "disables";
	private static final int resource_id__WIDGET_LABEL = R.id.widget__label;
	protected String containerLayout = container_layout__HORIZONTAL;
	protected boolean scrollableContent = false;
	protected int labelWidth;
	protected boolean isTransparentBackground = false;
	protected boolean inputEnabled = true;
	protected TextView widgetFdkCursor;
	private boolean fdkCursorEnabled = false;
	protected boolean isTransparentBackgroundParameter = false;
//	protected boolean isTransparentBackgroundParameter = false;
	protected String onClick = on_click__DEFAULT;
	protected String onLongClick = on_click__DEFAULT;
	protected String widgetHint;
	protected String dataType;
	protected ViewGroup widgetContainer;
	protected Button copyButton;
	protected Button zoomButton;
	protected boolean zoomable = true;
	protected boolean enableCopyButton = false;
	protected RelativeLayout labelContainer;
	protected TextView labelTextView;
	protected String labelTextString;
	protected Object baselineValue;
	protected String labelPrefix = "";
	protected String labelSuffix = "";
	protected String labelHint = "";
	protected GcgMultiShiftState initialMultiShiftState;
	protected GcgMultiShiftState activeMultiShiftState;
	protected boolean resetMultiShiftStateAfterDictation = true;
	protected boolean inputRequired = true;
	protected int minimumDataLength;
	protected int requestCode = 0;
	protected int requestCode2 = 0;
	protected boolean deferDataInitialization = false;
	protected boolean isFiltered = false;
	protected boolean noLabel = false;
    protected int dataFormat = 1;
    protected boolean manageLabelGuiState = false;
    protected String modifiedLabelDecorator = "> ";

	public int getInputType() {
		return InputType.TYPE_NULL;
	}

	public GcgWidget(Context aContext) {
		super(aContext);
		if(! deferredSetup()) {
			setup();
		}
	}

	public GcgWidget(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		processCustomAttributes(aContext, anAttributeSet);
		if(! deferredSetup()) {
			setup();
		}
	}

	public GcgWidget(Context aContext, AttributeSet anAttributeSet, int aStyleDefinition) {
		super(aContext, anAttributeSet, aStyleDefinition);
		processCustomAttributes(aContext, anAttributeSet);
		if(! deferredSetup()) {
			setup();
		}
	}
	
	protected boolean deferredSetup() {
		return false;
	}
	
	protected boolean deferDataInitialization() {
		return this.deferDataInitialization;
	}
	
	protected boolean inputRequired() {
		return this.inputRequired;
	}

    protected void manageWidgetGuiState() {
        manageLabelGuiState();
    }

    protected void setManageLabelGuiState(boolean aBoolean) {
        this.manageLabelGuiState = aBoolean;
        manageLabelGuiState();
    }

    protected void manageLabelGuiState() {
        if(this.labelTextView != null && this.manageLabelGuiState) {
            boolean hasDecorator = this.labelTextView.getText().subSequence(0,this.modifiedLabelDecorator.length()).equals(this.modifiedLabelDecorator);
            if(isModified()) {
                this.labelTextView.setTextColor(GcgApplication.getAppResources().getColor(R.color.gcg__widget_label__text_color__modified));
                if(!hasDecorator) {
                    this.labelTextView.setText(this.modifiedLabelDecorator + this.labelTextView.getText());
                }

            } else {
                this.labelTextView.setTextColor(GcgApplication.getAppResources().getColor(R.color.gcg__widget_label__text_color__unchanged));
                if(hasDecorator) {
                    this.labelTextView.setText(this.labelTextView.getText().toString().substring(this.modifiedLabelDecorator.length()));
                }
            }
        }
    }

	protected void processCustomAttributes(Context aContext, AttributeSet anAttributeSet) {
		processGcgLabelAttributes(aContext, anAttributeSet);
		TypedArray aTypedArray = aContext.obtainStyledAttributes(anAttributeSet, R.styleable.GcgWidget);
		final int theArraySize = aTypedArray.getIndexCount();
		for (int theIndex = 0; theIndex < theArraySize; ++theIndex) {
			int theAttributeIndex = aTypedArray.getIndex(theIndex);
            if (theAttributeIndex == R.styleable.GcgWidget_containerLayout) {
                this.containerLayout = aTypedArray.getString(theAttributeIndex);

            } else if (theAttributeIndex == R.styleable.GcgWidget_scrollableContent) {
                this.scrollableContent = aTypedArray.getBoolean(theAttributeIndex, false);

            } else if (theAttributeIndex == R.styleable.GcgWidget_onClick) {
                this.onClick = aTypedArray.getString(theAttributeIndex);

            } else if (theAttributeIndex == R.styleable.GcgWidget_onLongClick) {
                this.onClick = aTypedArray.getString(theAttributeIndex);

            } else if (theAttributeIndex == R.styleable.GcgWidget_transparentBackground) {
                this.isTransparentBackground = aTypedArray.getBoolean(theAttributeIndex, false);

            } else if (theAttributeIndex == R.styleable.GcgWidget_inputEnabled) {
                this.inputEnabled = aTypedArray.getBoolean(theAttributeIndex, true);

            } else if (theAttributeIndex == R.styleable.GcgWidget_enableCopyButton) {
                this.enableCopyButton = aTypedArray.getBoolean(theAttributeIndex, false);

            } else if (theAttributeIndex == R.styleable.GcgWidget_fdkCursorEnabled) {
                this.fdkCursorEnabled = aTypedArray.getBoolean(theAttributeIndex, true);

            } else if (theAttributeIndex == R.styleable.GcgWidget_minimumDataLength) {
                this.minimumDataLength = aTypedArray.getInteger(theAttributeIndex, 0);

            } else if (theAttributeIndex == R.styleable.GcgWidget_inputRequired) {
                this.inputRequired = aTypedArray.getBoolean(theAttributeIndex, false);

            } else if (theAttributeIndex == R.styleable.GcgWidget_deferDataInitialization) {
                this.deferDataInitialization = aTypedArray.getBoolean(theAttributeIndex, false);

            } else if (theAttributeIndex == R.styleable.GcgWidget_requestCode) {
                this.requestCode = aTypedArray.getInt(theAttributeIndex, 0);

            } else if (theAttributeIndex == R.styleable.GcgWidget_requestCode2) {
                this.requestCode2 = aTypedArray.getInt(theAttributeIndex, 0);

            } else if (theAttributeIndex == R.styleable.GcgWidget_dataFormat) {
                this.dataFormat = aTypedArray.getInt(theAttributeIndex, 1);

            } else if (theAttributeIndex == R.styleable.GcgWidget_zoomable) {
                this.zoomable = aTypedArray.getBoolean(theAttributeIndex, true);

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

			} else if (theAttributeIndex == R.styleable.GcgLabel_labelText) {
				this.labelTextString = aTypedArray.getString(theAttributeIndex);

			} else if (theAttributeIndex == R.styleable.GcgLabel_labelPrefix) {
				this.labelPrefix = aTypedArray.getString(theAttributeIndex);

			} else if (theAttributeIndex == R.styleable.GcgLabel_labelSuffix) {
				this.labelSuffix = aTypedArray.getString(theAttributeIndex);

			} else if (theAttributeIndex == R.styleable.GcgLabel_labelHint) {
				this.labelHint = aTypedArray.getString(theAttributeIndex);

			} else if (theAttributeIndex == R.styleable.GcgLabel_noLabel) {
				this.noLabel = aTypedArray.getBoolean(theAttributeIndex, false);

			}
		}
		aTypedArray.recycle();
	}
	
	protected void playClick() {
		playSoundEffect(SoundEffectConstants.CLICK);
	}

	protected void setup() {
		inflate(getContext(), getWidgetLayoutResourceId(), this);
		this.widgetContainer = (ViewGroup) getChildAt(0);
		this.labelContainer = (RelativeLayout) this.widgetContainer.findViewById(R.id.label__container);
		if(this.enableCopyButton) {
			this.copyButton = (Button) this.widgetContainer.findViewById(R.id.copy_button);
			this.copyButton.setVisibility(View.VISIBLE);
		}
		if(hasLabel()) {
			this.labelTextView = (TextView) this.widgetContainer.findViewById(resource_id__WIDGET_LABEL);
			if(this.labelWidth > 0) {
				setLabelWidth();
			}
			if(this.labelHint.length() > 0) {
				this.labelTextView.setText(GcgHelper.getLabelHintHtmlString(this.labelHint));
				this.labelTextView.append( " " +
						this.labelPrefix + getLabelText() + this.labelSuffix);
			} else {
				this.labelTextView.setText(this.labelPrefix + getLabelText() + this.labelSuffix);
			}
			GcgHelper.setDrawableLeft(this.labelTextView, getPrimaryLabelDrawable());
			this.labelTextView.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					GcgWidget.this.playClick();
					GcgWidget.this.requestFdkConsumerFocus();
				}
			});
//			this.labelTextView.setOnTouchListener(new OnTouchListener() {
//				
//				@Override
//				public boolean onTouch(View v, MotionEvent event) {
//					GcgWidget.this.requestFdkConsumerFocus();
//					return true;
//				}
//			});
		}
	}

	protected void setLabelWidth() {
		android.view.ViewGroup.LayoutParams theLayoutParams = this.labelTextView.getLayoutParams();
		theLayoutParams.width = GcgHelper.getPixelsForDp(getContext(), this.labelWidth);
		this.labelTextView.setLayoutParams(theLayoutParams);
	}
	
	public void setLabelText(String aString) {
		this.labelTextView.setText(aString);
	}
	
	protected Drawable getPrimaryLabelDrawable() {
		return null;
	}

	public GcgActivity getGcgActivity() {
		return this.gcgActivity;
	}
	
	public void setGcgActivity(GcgActivity aGcgActivity) {
		this.gcgActivity = aGcgActivity;
	}
	
	public void setGcgDialog(GcgDialog aGcgDialog) {
		this.gcgDialog = aGcgDialog;
	}
	
	public FdkListener getFdkListener() {
		return this.fdkListener;
	}
	
	@Override
	public void setFdkListener(FdkListener anFdkListener) {
		this.fdkListener = anFdkListener;
	}

	protected void setWidgetCursor(int aVisibility) {
		if(this.widgetFdkCursor != null && fdkCursorEnabled()) {
			this.widgetFdkCursor.setVisibility(aVisibility);
		}
	}

	protected void getWidgetFdkCursorView(int aViewIndex) {
		View theView = this.widgetContainer.getChildAt(aViewIndex);
		if(theView != null && theView.getId() == GcgHelper.view_resource_id__WIDGET_FDK_CURSOR) {
			this.widgetFdkCursor = (TextView) theView;
		}
	}
	
	protected boolean fdkCursorEnabled() {
		return this.fdkCursorEnabled;
	}
	
	@Override
	public void setFdkCursorEnabled(boolean aBoolean) {
		this.fdkCursorEnabled = aBoolean;
	}

	protected boolean hasLabel() {
		return ! this.noLabel && ! this.containerLayout.equals(container_layout__NO_LABEL) && ! this.containerLayout.equals(container_layout__MENU_PARAMETER);
	}
	
	protected boolean isMenuParameter() {
		return this.containerLayout.equals(container_layout__MENU_PARAMETER);
	}
	
	protected String getLabelText() {
		return this.labelTextString;
	}
	
	protected abstract int getWidgetLayoutResourceId();

	protected void setTransparentBackground() {
		return;
	}

	public void setInitialValue(@SuppressWarnings("unused") Object anObject) {
		return;
	}

	public void setInputType(@SuppressWarnings("unused") int anInputType) {
		return;
	}
	
	public abstract void setHint(int aResourceId);
	
	public abstract void setHint(String aString);

	public void setInitialValue() {
		return;
	}
	
	public abstract Object getData();
	
	public abstract void setData(Object anObject);
	
	public Object getBaselineValue() {
		return this.baselineValue;
	}
	
	public void setBaselineValue(Object anObject) {
		this.baselineValue = anObject;
	}

    public void setBaselineValue() {
        // copy from data view into this.baselineValue;
    }
	
	public void setContainerWidth(int aContainerWidthInDp) {
		LayoutParams theLayoutParams = (LayoutParams) this.widgetContainer.getLayoutParams();
		theLayoutParams.width = GcgGuiHelper.getPixelsForDp(aContainerWidthInDp);
		this.widgetContainer.setLayoutParams(theLayoutParams);
	}
	
	public void setContainerHeight(int aContainerHeightInDp) {
		LayoutParams theLayoutParams = (LayoutParams) this.widgetContainer.getLayoutParams();
		theLayoutParams.height = GcgGuiHelper.getPixelsForDp(aContainerHeightInDp);
		this.widgetContainer.setLayoutParams(theLayoutParams);
	}
	
	public void setContainerDimensions(int aContainerWidthInDp, int aContainerHeightInDp) {
		LayoutParams theLayoutParams = (LayoutParams) this.widgetContainer.getLayoutParams();
		theLayoutParams.width = GcgGuiHelper.getPixelsForDp(aContainerWidthInDp);
		theLayoutParams.height = GcgGuiHelper.getPixelsForDp(aContainerHeightInDp);
		this.widgetContainer.setLayoutParams(theLayoutParams);
	}
	
	public void setContainerBackground(Drawable aDrawable) {
		this.widgetContainer.setBackground(aDrawable);
	}

	public void requestFdkConsumerFocus() {
		if(this.fdkListener != null) {
			this.fdkListener.fdkFocusConsumer(this);
		}
	}

	public void requestFdkConsumerFocusFromTouch() {
		if(this.fdkListener != null) {
			this.fdkListener.fdkFocusConsumerFromTouch(this);
		}
	}

	@Override
	public GcgMultiShiftState getInitialMultiShiftState() {
		return this.initialMultiShiftState;
	}
	
	@Override
	public void setInitialMultiShiftState(GcgMultiShiftState aGcgMultiShiftState) {
		this.initialMultiShiftState = aGcgMultiShiftState;
		this.activeMultiShiftState = aGcgMultiShiftState;
	}

	@Override
	public GcgMultiShiftState getMultiShiftState() {
		return this.activeMultiShiftState;
	}

	@Override
	public void setMultiShiftState(GcgMultiShiftState aGcgMultiShiftState) { return; }

	@Override
	public boolean isResetMultiShiftStateAfterDictation() {
		return this.resetMultiShiftStateAfterDictation;
	}

	@Override
	public void resetMultiShiftStateAfterDictation(boolean aBoolean) {
		this.resetMultiShiftStateAfterDictation = aBoolean;
	}

	protected int getRequestCode() {
		return this.requestCode;
	}

	protected int getRequestCode2() {
		return this.requestCode2;
	}
	
	public Button getCopyButton() {
		return this.copyButton;
	}
	
	@Override
	public void setFirstMultiShiftState() { return; }
	
	public boolean isFiltered() {
		return this.isFiltered;
	}

	public void isFiltered(boolean bFiltered) {
		this.isFiltered = bFiltered;
	}

	protected void updateFilterResults() { return; }
    
    protected class WrappedOnClickListener implements OnClickListener {

    	private final GcgWidget widgetView;
    	private final OnClickListener wrappedOnClickListener;
    	
    	public WrappedOnClickListener(GcgWidget aWidgetView, OnClickListener anOnClickListener) {
    		this.widgetView = aWidgetView;
    		this.wrappedOnClickListener = anOnClickListener;
    	}
		
		@Override
		public void onClick(View aView) {
			this.widgetView.playClick();
			this.wrappedOnClickListener.onClick(aView);
		}
    }
    
    public void setOnSetTextListener(GcgOnSetTextListener anOnSetTextListener) {
    	this.onSetTextListenerList.add(anOnSetTextListener);
    }
    
    protected void notifyOnSetTextListenerList(String aString) {
    	for(GcgOnSetTextListener theOnSetTextListener : this.onSetTextListenerList) {
    		theOnSetTextListener.onSetText(this, aString);
    	}
    }

    public boolean isModified() { return false; }

}
