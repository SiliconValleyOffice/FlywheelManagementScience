/* @(#)GcgCancelOkFdkDialog.java
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

package com.flywheelms.gcongui.gcg.dialog;

import android.content.Context;
import android.os.IBinder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.fdk.FdkHostSupport;
import com.flywheelms.gcongui.fdk.enumerator.FdkKeyboardState;
import com.flywheelms.gcongui.fdk.enumerator.FdkKeyboardStyle;
import com.flywheelms.gcongui.fdk.interfaces.FdkDictationResultsConsumer;
import com.flywheelms.gcongui.fdk.interfaces.FdkHost;
import com.flywheelms.gcongui.fdk.interfaces.FdkListener;
import com.flywheelms.gcongui.fdk.widget.FdkKeyboard;
import com.flywheelms.gcongui.gcg.activity.GcgActivity;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public abstract class GcgCancelOkFdkDialog extends GcgCancelOkDialog  implements FdkHost, FdkListener {

    private FdkHostSupport fdkHostSupport;
    protected LinkedHashMap<View, FdkDictationResultsConsumer> fdkDictationResultsConsumerMap = new LinkedHashMap<View, FdkDictationResultsConsumer>();
    ArrayList<FdkDictationResultsConsumer> fdkDictationResultsConsumerList = new ArrayList<FdkDictationResultsConsumer>();
    protected FdkDictationResultsConsumer currentFdkDictationResultsConsumer;


    public GcgCancelOkFdkDialog(GcgActivity aGcgActivity) {
        super(aGcgActivity);
    }

    public GcgCancelOkFdkDialog(GcgActivity aGcgActivity, String aTargetDetail, String aMessageString) {
        super(aGcgActivity, aTargetDetail, aMessageString);
    }

    protected void initFdkHostSupport() {
        this.fdkHostSupport = new FdkHostSupport(this);
        setFdkKeyboard(new FdkKeyboard(
                FdkKeyboardStyle.widget_input__EDIT_TEXT,
                FdkKeyboardState.ALWAYS_ACTIVE,
                getGcgThumbpadLeft(),
                null,  // thumbpad only used for FDK
                getGcgThumbpadRight(),
                null,  // thumbpad only used for FDK
                this,
                this ));
        initFdkDictationResultsConsumerMap();
        fdkFocusFirstConsumer();
        this.fdkHostSupport.activateFdkKeyboard(true);
    }

    @Override
    protected int getDialogBodyLayoutResourceId() {
        return R.layout.fdk_dialog__cancel_ok__base_layout;
    }

    /////  FDK Host wrapper

    @Override
    public boolean fdkSpeechRecognitionSupported() {
        return this.fdkHostSupport.fdkSpeechRecognitionSupported();
    }

    @Override
    public FdkKeyboard getFdkKeyboard() {
        return this.fdkHostSupport.getFdkKeyboard();
    }

    @Override
    public void setFdkKeyboard(FdkKeyboard anFdkKeyboard) {
        this.fdkHostSupport.setFdkKeyboard(anFdkKeyboard);
    }

    @Override
    public void activateFdkKeyboard(boolean bActivate) {
        this.fdkHostSupport.activateFdkKeyboard(bActivate);
    }

    @Override
    public void startDictation() {
        this.fdkHostSupport.startDictation();
    }

    @Override
    public void stopDictation() {
        this.fdkHostSupport.stopDictation();
    }

    @Override
    public void cancelDictation() {
        this.fdkHostSupport.cancelDictation();
    }

    @Override
    public Context getContext() {
        return this.gcgActivity;
    }

//	public void setSoftKeyboardEnabled(boolean aBoolean) {
//		this.currentFdkDictationResultsConsumer.disableSoftKeyboard(! aBoolean);
//		this.fdkHostSupport.setSoftKeyboardEnabled(aBoolean);
//	}

    /////  end FDK Host wrapper

    public FrameLayout getGcgThumbpadLeft() {
        this.fdkHostSupport.initGcgThumbpadLeft(this.dialogBodyView);
        return this.fdkHostSupport.getGcgThumbpadLeft();
    }

    public ViewGroup getFdkKeypadPeerViewLeft() {
        this.fdkHostSupport.initFdkKeypadPeerViewLeft(this.dialogBodyView);
        return this.fdkHostSupport.getFdkKeypadPeerViewLeft();
    }

    public FrameLayout getGcgThumbpadRight() {
        this.fdkHostSupport.initGcgThumbpadRight(this.dialogBodyView);
        return this.fdkHostSupport.getGcgThumbpadRight();
    }

    public ViewGroup getFdkKeypadPeerViewRight() {
        this.fdkHostSupport.initFdkKeypadPeerViewRight(this.dialogBodyView);
        return this.fdkHostSupport.getFdkKeypadPeerViewRight();
    }

    ///// FdkListener wrapper  //////////////////

    protected void addFdkDictationResultsConsumer(FdkDictationResultsConsumer aDictationResultsConsumer) {
        this.fdkDictationResultsConsumerMap.put(aDictationResultsConsumer.getViewToFocus(), aDictationResultsConsumer);
        this.fdkDictationResultsConsumerList.add(aDictationResultsConsumer);
        aDictationResultsConsumer.setFdkCursorEnabled(true);
        aDictationResultsConsumer.setFdkListener(this);
    }

    protected void addFdkDictationResultsConsumer(FdkDictationResultsConsumer aDictationResultsConsumer, int aMapPosition) {
        insertIntoMapAtPosition(aDictationResultsConsumer, aMapPosition);
        aDictationResultsConsumer.setFdkCursorEnabled(true);
        aDictationResultsConsumer.setFdkListener(this);
    }

    private void insertIntoMapAtPosition(FdkDictationResultsConsumer aDictationResultsConsumer, int aMapPosition) {
        LinkedHashMap<View, FdkDictationResultsConsumer> theTemporaryConsumerMap = new LinkedHashMap<View, FdkDictationResultsConsumer>();
        ArrayList<FdkDictationResultsConsumer> theTemporaryConsumerList = new ArrayList<FdkDictationResultsConsumer>();
        int theNewMapIndex = 0;
        // copy first segment and insert new Consumer
        if(aMapPosition == 0) {
            theTemporaryConsumerMap.put(aDictationResultsConsumer.getViewToFocus(), aDictationResultsConsumer);
            theTemporaryConsumerList.add(aDictationResultsConsumer);
            ++theNewMapIndex;
        } else {
            for(int theIndex = theNewMapIndex; theIndex < aMapPosition; ++theIndex) {
                FdkDictationResultsConsumer theConsumer = this.fdkDictationResultsConsumerList.get(theIndex);
                theTemporaryConsumerMap.put(theConsumer.getViewToFocus(), theConsumer);
                theTemporaryConsumerList.add(theConsumer);
                ++theNewMapIndex;
            }
            theTemporaryConsumerMap.put(aDictationResultsConsumer.getViewToFocus(), aDictationResultsConsumer);
            theTemporaryConsumerList.add(aDictationResultsConsumer);
            ++theNewMapIndex;
        }
        // copy last segment
        for(int theIndex = theNewMapIndex - 1; theIndex < this.fdkDictationResultsConsumerList.size(); ++theIndex) {
            FdkDictationResultsConsumer theConsumer = this.fdkDictationResultsConsumerList.get(theIndex);
            theTemporaryConsumerMap.put(theConsumer.getViewToFocus(), theConsumer);
            theTemporaryConsumerList.add(theConsumer);
        }
        this.fdkDictationResultsConsumerMap = theTemporaryConsumerMap;
        this.fdkDictationResultsConsumerList = theTemporaryConsumerList;
    }

    protected void removeFdkDictationResultsConsumer(FdkDictationResultsConsumer aDictationResultsConsumer) {
        this.fdkDictationResultsConsumerMap.remove(aDictationResultsConsumer.getViewToFocus());
        this.fdkDictationResultsConsumerList.remove(aDictationResultsConsumer);
        aDictationResultsConsumer.setFdkCursorEnabled(false);
        aDictationResultsConsumer.setFdkListener(null);
    }

    @Override
    public void fdkDispatchDeleteAll() {
        this.fdkHostSupport.dispatchDeleteAll(this.alertDialog);
    }

    @Override
    public void fdkDispatchDeleteWord() {
        FdkHostSupport.deleteWord((EditText) this.currentFdkDictationResultsConsumer.getViewToFocus());
    }

    @Override
    public void fdkDispatchKeyEvent(int aKeycode) {
        FdkHostSupport.dispatchKeyEventDownUp(this.alertDialog, aKeycode);
    }

    @Override
    public void fdkDispatchShiftedKeyEvent(int aKeycode) {
        FdkHostSupport.dispatchShiftedKeyEvent(this.alertDialog, aKeycode);
    }

    @Override
    public void fdkDispatchControlShiftedKeyEvent(int aKeycode) {
        FdkHostSupport.dispatchControlShiftedKeyEvent(this.alertDialog, aKeycode);
    }

    @Override
    public void fdkDispatchAltKeyEvent(int aKeycode) {
        FdkHostSupport.dispatchAltKeyEvent(this.alertDialog, aKeycode);
    }

    @Override
    public void fdkDispatchAltShiftedKeyEvent(int aKeycode) {
        FdkHostSupport.dispatchAltShiftedKeyEvent(this.alertDialog, aKeycode);
    }

    @Override
    public void fdkDispatchControlKeyEvent(int aKeycode) {
        FdkHostSupport.dispatchControlKeyEvent(this.alertDialog, aKeycode);
    }

    private ArrayList<FdkDictationResultsConsumer> getFdkDictationResultsConsumerList() {
        if(this.fdkDictationResultsConsumerList == null) {
            this.fdkDictationResultsConsumerList = new ArrayList<FdkDictationResultsConsumer>(this.fdkDictationResultsConsumerMap.values());
        }
        return this.fdkDictationResultsConsumerList;
    }

    @Override
    public void fdkDictationResults(ArrayList<String> aWordList) {
        this.currentFdkDictationResultsConsumer.onDictationResults(aWordList);
        this.currentFdkDictationResultsConsumer.getViewToFocus().requestFocusFromTouch();
    }

    @Override
    public void fdkFocusFirstConsumer() {
        fdkFocusConsumer(this.fdkDictationResultsConsumerList.get(0));
    }

    @Override
    public void fdkFocusNextConsumer() {
        FdkDictationResultsConsumer theNextConsumer = FdkHostSupport.getNextFdkDictationResultsConsumer(this.currentFdkDictationResultsConsumer, getFdkDictationResultsConsumerList());
        fdkFocusConsumer(theNextConsumer);
    }

    @Override
    public void fdkFocusPreviousConsumer() {
        FdkDictationResultsConsumer theNextPreviousConsumer = FdkHostSupport.getPreviousFdkDictationResultsConsumer(this.currentFdkDictationResultsConsumer, getFdkDictationResultsConsumerList());
        fdkFocusConsumer(theNextPreviousConsumer);
    }

    @Override
    public void fdkFocusConsumer(FdkDictationResultsConsumer anFdkDictationResultsConsumer) {
        fdkFocusConsumerStuff(anFdkDictationResultsConsumer, false);
    }

    @Override
    public void fdkFocusConsumerFromTouch(FdkDictationResultsConsumer anFdkDictationResultsConsumer) {
        fdkFocusConsumerStuff(anFdkDictationResultsConsumer, true);
    }

    public void fdkFocusConsumerStuff(FdkDictationResultsConsumer anFdkDictationResultsConsumer, boolean bFromTouch) {
        if(anFdkDictationResultsConsumer == null) {
            return;
        }
        if(this.currentFdkDictationResultsConsumer != null && this.currentFdkDictationResultsConsumer != anFdkDictationResultsConsumer) {
            this.currentFdkDictationResultsConsumer.setMultiShiftState(this.fdkHostSupport.getFdkKeyboard().getMultiShiftState());
        }
        this.currentFdkDictationResultsConsumer = anFdkDictationResultsConsumer;
        if(! bFromTouch) {
            this.currentFdkDictationResultsConsumer.getViewToFocus().requestFocusFromTouch();
        }
        this.fdkHostSupport.getFdkKeyboard().changeKeyboardStyle(this.currentFdkDictationResultsConsumer.getKeyboardStyle());
        this.fdkHostSupport.getFdkKeyboard().resetMultiShiftStateAfterDictation(this.currentFdkDictationResultsConsumer.isResetMultiShiftStateAfterDictation());
        this.fdkHostSupport.getFdkKeyboard().setMultiShiftState(this.currentFdkDictationResultsConsumer.getMultiShiftState());
    }

    @Override
    public void fdkResetMultiShiftState() {
        if(this.fdkHostSupport.getFdkKeyboard() == null || this.currentFdkDictationResultsConsumer == null) {
            return;
        }
        this.fdkHostSupport.getFdkKeyboard().setMultiShiftState(this.currentFdkDictationResultsConsumer.getInitialMultiShiftState());
    }

    @Override
    public void toggleSoftKeyboardActive() {
        if(this.fdkHostSupport != null) {
            this.fdkHostSupport.toggleSoftKeyboardActive();
        }
    }

    public IBinder getWindowToken() {
        ViewGroup theContentView = (ViewGroup) this.alertDialog.findViewById(android.R.id.content);
        return theContentView.getWindowToken();
    }

}