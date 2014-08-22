/* @(#)BluetoothHeadset.java
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


/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.flywheelms.library.gcg.headset;

import java.util.ArrayList;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flywheelms.library.R;


/**
 * The Android Bluetooth API is not finalized, and *will* change. Use at your
 * own risk.
 *
 * Public API for controlling the Bluetooth Headset Service. This includes both
 * Bluetooth Headset and Handsfree (v1.5) profiles. The Headset service will
 * attempt a handsfree connection first, and fall back to headset.
 *
 * BluetoothHeadset is a proxy object for controlling the Bluetooth Headset
 * Service via IPC.
 *
 * Creating a BluetoothHeadset object will create a binding with the
 * BluetoothHeadset service. Users of this object should call close() when they
 * are finished with the BluetoothHeadset, so that this proxy object can unbind
 * from the service.
 *
 * This BluetoothHeadset object is not immediately bound to the
 * BluetoothHeadset service. Use the ServiceListener interface to obtain a
 * notification when it is bound, this is especially important if you wish to
 * immediately call methods on BluetoothHeadset after construction.
 *
 * Android only supports one connected Bluetooth Headset at a time.
 *
 * @hide
 */
public final class BluetoothHeadsetHelper {
	
	// SpeechToText
	
//	public void speech1(View view) {
//	    final int REQUEST_CODE = 1;
//	    Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//	    i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
//	    try {
//	        startActivityForResult(i, REQUEST_CODE);
//	    } catch (Exception e) {
//	    Toast.makeText(this, "Error initializing speech to text engine.", Toast.LENGTH_LONG).show();
//	    }
//	}
//	public void speech2(View view) {
//	    final int REQUEST_CODE = 2;
//	    Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//	    i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
//	    try {
//	        startActivityForResult(i, REQUEST_CODE);
//	    } catch (Exception e) {
//	        Toast.makeText(this, "Error initializing speech to text engine.",        Toast.LENGTH_LONG).show();
//	    }
//	}
//
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//	    super.onActivityResult(requestCode, resultCode, data);
//	    ArrayList<String> thingsYouSaid = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//	    if(REQUEST_CODE==1){
//	        EditText editText = (EditText)findViewById(R.id.speechText1);
//	        editText.setText(thingsYouSaid.get(0), TextView.BufferType.EDITABLE);
//	    }
//
//	    if(REQUEST_CODE==2){        
//	        EditText editText = (EditText)findViewById(R.id.speechText2);
//	        editText.setText(thingsYouSaid.get(0), TextView.BufferType.EDITABLE);
//	    }
//	}
	
	
}
