/* @(#)FdkHostSupport.java
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

package com.flywheelms.gcongui.fdk;

import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.IBinder;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.flywheelms.gcongui.R;
import com.flywheelms.gcongui.fdk.enumerator.FdkKeyboardState;
import com.flywheelms.gcongui.fdk.interfaces.FdkDictationResultsConsumer;
import com.flywheelms.gcongui.fdk.interfaces.FdkHost;
import com.flywheelms.gcongui.fdk.widget.FdkKeyboard;
import com.flywheelms.gcongui.fdk.widget.FdkLeftKeypad;
import com.flywheelms.gcongui.fdk.widget.FdkRightKeypad;
import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.gcongui.gcg.widget.GcgWidgetSpinner;

import java.util.ArrayList;
import java.util.Locale;

public class FdkHostSupport implements FdkHost {

	public static final int menu_item__FDK = R.id.action__flywheel_dictation_keyboard;
	private FdkHost fdkHost;
	private int globalLayoutCounter = 0;
	protected boolean softKeyboardActive = false;
	protected boolean softKeyboardEnabled = true;  // TODO - legacy soft keyboard management
	private BluetoothHeadset bluetoothHeadset;
	private boolean useBluetoothHeadset;
	private boolean useWiredHeadset;
	private AudioManager audioManager;
	private boolean speechRecognitionSupported = false;
	private FdkDictationListener dictationListener;
	private SpeechRecognizer speechRecognizer;
	private Intent speechRecognizerIntent;
	protected MenuItem fdkKeyboardMenuItem;
	protected FrameLayout gcgThumbpadLeft;
	protected ViewGroup fdkKeypadPeerViewLeft;
	protected ViewGroup fdkKeypadContainerLeft;
	protected FdkLeftKeypad fdkLeftKeypad;
	// the Right variables are not used by view flippers
	protected FrameLayout gcgThumbpadRight;
	protected ViewGroup fdkKeypadPeerViewRight;
	protected ViewGroup fdkKeypadContainerRight;
	protected FdkRightKeypad fdkRightKeypad;
	protected FdkKeyboard fdkKeyboard;
	
	public FdkHostSupport(FdkHost anFdkHost) {
        this(anFdkHost, true);
    }

    public FdkHostSupport(FdkHost anFdkHost, boolean bInitSpeechRecognition) {
		this.fdkHost = anFdkHost;
		this.audioManager = (AudioManager) this.fdkHost.getContext().getSystemService(Context.AUDIO_SERVICE);
        if(bInitSpeechRecognition) {
            initSpeechRecognition();
        }
//		initializeBluetoothHeadset();  // TODO
	}

	///////   start of FDK code   ////////////

	public FrameLayout initGcgThumbpadLeft(ViewGroup aViewGroup) {
		this.gcgThumbpadLeft = (FrameLayout) aViewGroup.findViewById(R.id.gcg__thumbpad__left);
		return this.gcgThumbpadLeft;
	}
	
	public FrameLayout initGcgThumbpadLeft(AlertDialog anAlertDialog) {
		this.gcgThumbpadLeft = (FrameLayout) anAlertDialog.findViewById(R.id.gcg__thumbpad__left);
		return this.gcgThumbpadLeft;
	}

	public FrameLayout initGcgThumbpadLeft(Window aWindow) {
		this.gcgThumbpadLeft = (FrameLayout) aWindow.findViewById(R.id.gcg__thumbpad__left);
		return this.gcgThumbpadLeft;
	}

	public FrameLayout initGcgThumbpadRight(ViewGroup aViewGroup) {
		this.gcgThumbpadRight = (FrameLayout) aViewGroup.findViewById(R.id.gcg__thumbpad__right);
		return this.gcgThumbpadRight;
	}

	public FrameLayout initGcgThumbpadRight(Window aWindow) {
		this.gcgThumbpadRight = (FrameLayout) aWindow.findViewById(R.id.gcg__thumbpad__right);
		return this.gcgThumbpadRight;
	}
	
	public FrameLayout initGcgThumbpadRight(AlertDialog anAlertDialog) {
		this.gcgThumbpadRight = (FrameLayout) anAlertDialog.findViewById(R.id.gcg__thumbpad__right);
		return this.gcgThumbpadRight;
	}
	
	public ViewGroup initFdkKeypadPeerViewLeft(ViewGroup aViewGroup) {
		this.fdkKeypadPeerViewLeft = (ViewGroup) aViewGroup.findViewById(R.id.fdk__left_keypad__peer_view);
		return this.fdkKeypadPeerViewLeft;
	}
	
	public ViewGroup initFdkKeypadPeerViewLeft(AlertDialog anAlertDialog) {
		this.fdkKeypadPeerViewLeft = (ViewGroup) anAlertDialog.findViewById(R.id.fdk__left_keypad__peer_view);
		return this.fdkKeypadPeerViewLeft;
	}
	
	public ViewGroup initFdkKeypadPeerViewLeft(Window aWindow) {
		this.fdkKeypadPeerViewLeft = (ViewGroup) aWindow.findViewById(R.id.fdk__left_keypad__peer_view);
		return this.fdkKeypadPeerViewLeft;
	}
	
	public ViewGroup initFdkKeypadContainerLeft(ViewGroup aViewGroup) {
		this.fdkKeypadContainerLeft = (ViewGroup) aViewGroup.findViewById(R.id.fdk__left_keypad__container);
		return this.fdkKeypadContainerLeft;
	}
	
	public ViewGroup initFdkKeypadPeerViewRight(ViewGroup aViewGroup) {
//		this.fdkKeypadPeerViewRight = (ViewGroup) aViewGroup.findViewById(R.id.fdk__right_keypad__peer_view);
		return this.fdkKeypadPeerViewRight;
	}
	
	public ViewGroup initFdkKeypadPeerViewRight(AlertDialog anAlertDialog) {
//		this.fdkKeypadPeerViewRight = (ViewGroup) anAlertDialog.findViewById(R.id.fdk__right_keypad__peer_view);
		return this.fdkKeypadPeerViewRight;
	}
	
	public ViewGroup initFdkKeypadPeerViewRight(Window aWindow) {
//		this.fdkKeypadPeerViewRight = (ViewGroup) aWindow.findViewById(R.id.fdk__right_keypad__peer_view);
		return this.fdkKeypadPeerViewRight;
	}
	
	public ViewGroup initFdkKeypadContainerRight(ViewGroup aViewGroup) {
		this.fdkKeypadContainerRight = (ViewGroup) aViewGroup.findViewById(R.id.fdk__right_keypad__container);
		return this.fdkKeypadContainerRight;
	}

	private void initializeBluetoothHeadset() {  // TODO
		BluetoothAdapter theBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		BluetoothProfile.ServiceListener mProfileListener = new BluetoothProfile.ServiceListener() {

			@Override
			public void onServiceConnected(int aProfileType, BluetoothProfile aBluetoothProfile) {
				if (aProfileType == BluetoothProfile.HEADSET) {
					FdkHostSupport.this.bluetoothHeadset = (BluetoothHeadset) aBluetoothProfile;
				}
			}

			@Override
			public void onServiceDisconnected(int aProfileType) {
				if (aProfileType == BluetoothProfile.HEADSET) {
					FdkHostSupport.this.bluetoothHeadset = null;
				}
			}
		};
		theBluetoothAdapter.getProfileProxy(this.fdkHost.getContext(), mProfileListener, BluetoothProfile.HEADSET);  // connect to the proxy
	}

	private boolean isBluetoothHeadsetConnected() {
		return this.bluetoothHeadset == null ? false : this.bluetoothHeadset.getConnectedDevices().size() > 0;
	}

    public void createServices() {
        this.speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this.fdkHost.getContext());
    }
	
	@SuppressWarnings("deprecation")
	public void initSpeechRecognition() {
		if(! SpeechRecognizer.isRecognitionAvailable(this.fdkHost.getContext())) {
			this.speechRecognitionSupported = false;
			return;
		}
        createServices();
		this.dictationListener = new FdkDictationListener(this.fdkHost);
		this.speechRecognizer.setRecognitionListener(this.dictationListener);
		this.speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		this.speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		this.speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,
				this.fdkHost.getContext().getPackageName());
		this.speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 20);
		//		this.speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 100000);
		this.speechRecognitionSupported = true;
		this.useBluetoothHeadset = isBluetoothHeadsetConnected();
		if(this.useBluetoothHeadset) {
			this.useWiredHeadset = false;
			this.audioManager.startBluetoothSco();
		} else {
			this.useWiredHeadset = this.audioManager.isWiredHeadsetOn();  // not really deprecated, just a change in purpose
			if(this.useWiredHeadset) {
				this.audioManager.setSpeakerphoneOn(true);
			}
		}
		this.audioManager.setMode(AudioManager.STREAM_DTMF);
		this.audioManager.setMode(AudioManager.STREAM_NOTIFICATION);
		this.audioManager.setMode(AudioManager.STREAM_SYSTEM);
		this.audioManager.setMode(AudioManager.MODE_NORMAL);
	}

	public FdkKeyboardState getKeyboardState() {
		return this.fdkKeyboard.getKeyboardState();
	}

	public void setKeyboardState(FdkKeyboardState aKeyboardState) {
		this.fdkKeyboard.setKeyboardState(aKeyboardState);
	}
	
	public boolean softKeyboardActive() {
		return this.softKeyboardActive;
	}
	
	public boolean softKeyboardEnabled() {
		return this.softKeyboardEnabled;
	}
	
	@Override
	public void toggleSoftKeyboardActive() {
		this.softKeyboardActive = ! this.softKeyboardActive;
		GcgApplication.getInputMethodManager().toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
	}

	public void resetSoftKeyboard(IBinder anIBinder) {
		this.softKeyboardActive = false;
		GcgApplication.hideSoftInputFromWindow(anIBinder);
	}

	private void dismissSoftKeyboard() {
		if(this.softKeyboardActive) {
			toggleSoftKeyboardActive();
		}
	}
	
	@Override
	public boolean fdkSpeechRecognitionSupported() {
		return this.speechRecognitionSupported;
	}

	//	@Override
	//	public void onConfigurationChanged(Configuration newConfig) {
	//	    super.onConfigurationChanged(newConfig);
	//
	//
	//	    if (newConfig.hardKeyboardHidden == Configuration.HARDKEYBOARDHIDDEN_NO) {
	//	        Toast.makeText(this, "keyboard visible", Toast.LENGTH_SHORT).show();
	//	    }
	//	}

	@Override
	public void startDictation() {
		if(this.useBluetoothHeadset) {
//			this.audioManager.startBluetoothSco();
			this.audioManager.setBluetoothScoOn(true);
		}
//		} else if(this.useWiredHeadset) {  // not really deprecated, just changed purpose
////			this.audioManager.setSpeakerphoneOn(true);
//		}

		dismissSoftKeyboard();
		this.audioManager.setMode(AudioManager.MODE_IN_CALL);
//		this.audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
		this.speechRecognizer.startListening(this.speechRecognizerIntent);
	}

	@Override
	public void stopDictation() {
		this.speechRecognizer.stopListening();
		this.audioManager.setMode(AudioManager.MODE_NORMAL);
		if(this.useBluetoothHeadset) {
//			this.audioManager.stopBluetoothSco();
			this.audioManager.setBluetoothScoOn(false);
		}
//		} else if(this.useWiredHeadset) {  // not really deprecated, just changed purpose
//			this.audioManager.setSpeakerphoneOn(false);
//		}
	}

	@Override
	public void cancelDictation() {
		this.speechRecognizer.cancel();
		GcgHelper.makeToast("Cancel dictation...");
	}
	
	/////////  end of FDK code  ////////////


	/////  getters and setters

	public BluetoothHeadset getBluetoothHeadset() {
		return this.bluetoothHeadset;
	}

	public void setBluetoothHeadset(BluetoothHeadset bluetoothHeadset) {
		this.bluetoothHeadset = bluetoothHeadset;
	}

	public boolean isUseBluetoothHeadset() {
		return this.useBluetoothHeadset;
	}

	public void setUseBluetoothHeadset(boolean useBluetoothHeadset) {
		this.useBluetoothHeadset = useBluetoothHeadset;
	}

	public boolean isUseWiredHeadset() {
		return this.useWiredHeadset;
	}

	public void setUseWiredHeadset(boolean useWiredHeadset) {
		this.useWiredHeadset = useWiredHeadset;
	}

	public AudioManager getAudioManager() {
		return this.audioManager;
	}

	public void setAudioManager(AudioManager audioManager) {
		this.audioManager = audioManager;
	}

	public boolean isSpeechRecognitionSupported() {
		return this.speechRecognitionSupported;
	}

	public void setSpeechRecognitionSupported(boolean speechRecognitionSupported) {
		this.speechRecognitionSupported = speechRecognitionSupported;
	}

	public FdkDictationListener getDictationListener() {
		return this.dictationListener;
	}

	public void setDictationListener(FdkDictationListener dictationListener) {
		this.dictationListener = dictationListener;
	}

	public SpeechRecognizer getSpeechRecognizer() {
		return this.speechRecognizer;
	}

	public void setSpeechRecognizer(SpeechRecognizer speechRecognizer) {
		this.speechRecognizer = speechRecognizer;
	}

	public Intent getSpeechRecognizerIntent() {
		return this.speechRecognizerIntent;
	}

	public void setSpeechRecognizerIntent(Intent speechRecognizerIntent) {
		this.speechRecognizerIntent = speechRecognizerIntent;
	}

	public MenuItem getFdkKeyboardMenuItem() {
		return this.fdkKeyboardMenuItem;
	}

	public void setFdkKeyboardMenuItem(MenuItem fdkKeyboardMenuItem) {
		this.fdkKeyboardMenuItem = fdkKeyboardMenuItem;
	}

	public FrameLayout getGcgThumbpadLeft() {
		return this.gcgThumbpadLeft;
	}

	public void setGcgThumbpadLeft(FrameLayout gcgThumbpadLeft) {
		this.gcgThumbpadLeft = gcgThumbpadLeft;
	}

	public FrameLayout getGcgThumbpadRight() {
		return this.gcgThumbpadRight;
	}

	public void setGcgThumbpadRight(FrameLayout gcgThumbpadRight) {
		this.gcgThumbpadRight = gcgThumbpadRight;
	}

	public ViewGroup getFdkKeypadPeerViewLeft() {
		return this.fdkKeypadPeerViewLeft;
	}

	public void setFdkKeypadPeerViewLeft(ViewGroup fdkKeypadPeerViewLeft) {
		this.fdkKeypadPeerViewLeft = fdkKeypadPeerViewLeft;
	}

	public ViewGroup getFdkKeypadContainerLeft() {
		return this.fdkKeypadContainerLeft;
	}

	public void setFdkKeypadContainerLeft(ViewGroup fdkKeypadContainerLeft) {
		this.fdkKeypadContainerLeft = fdkKeypadContainerLeft;
	}

	public FdkLeftKeypad getFdkLeftKeypad() {
		return this.fdkLeftKeypad;
	}

	public void setFdkLeftKeypad(FdkLeftKeypad fdkLeftKeypad) {
		this.fdkLeftKeypad = fdkLeftKeypad;
	}

	public ViewGroup getFdkKeypadPeerViewRight() {
		return this.fdkKeypadPeerViewRight;
	}

	public void setFdkKeypadPeerViewRight(ViewGroup fdkKeypadPeerViewRight) {
		this.fdkKeypadPeerViewRight = fdkKeypadPeerViewRight;
	}

	public ViewGroup getFdkKeypadContainerRight() {
		return this.fdkKeypadContainerRight;
	}

	public void setFdkKeypadContainerRight(ViewGroup fdkKeypadContainerRight) {
		this.fdkKeypadContainerRight = fdkKeypadContainerRight;
	}

	public FdkRightKeypad getFdkRightKeypad() {
		return this.fdkRightKeypad;
	}

	public void setFdkRightKeypad(FdkRightKeypad fdkRightKeypad) {
		this.fdkRightKeypad = fdkRightKeypad;
	}

	@Override
	public FdkKeyboard getFdkKeyboard() {
		return this.fdkKeyboard;
	}

	@Override
	public void setFdkKeyboard(FdkKeyboard aKeyboard) {
		if(this.speechRecognitionSupported) {
			this.fdkKeyboard = aKeyboard;
			if(this.fdkKeyboardMenuItem != null) {
				if(this.fdkKeyboard != null) {
					this.fdkKeyboardMenuItem.setIcon(this.fdkKeyboard.getKeyboardState().getDrawable());
					this.fdkKeyboard.reactivate();
				} else {
					this.fdkKeyboardMenuItem.setIcon(FdkKeyboardState.DISABLED.getDrawable());
				}
			}
		}
	}
	
	//////////////////////////////////////////////////
	///  STATIC Consumer Support

	public static void insertDictationResultsIntoEditText(ArrayList<String> aDictationResultsList, EditText anEditText) {
		insertDictationResultsIntoEditText(aDictationResultsList, anEditText, true, false);
	}

	public static void insertDictationResultsIntoEditText(
			ArrayList<String> aDictationResultsList, EditText anEditText, boolean bDictationAssistance, boolean bRemoveSpaces) {
		String theDictationString = getLikelyDictationResults(aDictationResultsList);
		if(theDictationString.length() == 0) {
			return;
		}
		if(bRemoveSpaces) {
			theDictationString = theDictationString.replace(" ", "");
		}
		if(anEditText.getSelectionStart() != anEditText.getSelectionEnd()) {
			anEditText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_FORWARD_DEL));
		}
		String theOriginalText = anEditText.getText().toString();
		if(theOriginalText.length() == 0) {
			anEditText.append(theDictationString);
			anEditText.setSelection(anEditText.getText().length());
			return;
		}
		if(! bDictationAssistance) {
			StringBuilder theStringBuilder = new StringBuilder(theOriginalText.substring(0, anEditText.getSelectionStart()));
			theStringBuilder.append(theDictationString);
			int theNewSelectionStart = theStringBuilder.toString().length();
			String theOriginalTextToAppend = theOriginalText.substring(anEditText.getSelectionStart(), theOriginalText.length());
			if(theOriginalTextToAppend.length() > 0) {
				theStringBuilder.append(theOriginalTextToAppend);
			}
			anEditText.setText(theStringBuilder.toString());
			anEditText.setSelection(theNewSelectionStart);
			return;
		}
		if(anEditText.getSelectionStart() == 0) {
			StringBuffer theStringBuffer = new StringBuffer();
			theStringBuffer.append(theDictationString);
			theStringBuffer.append(" ");
			int theCursorPosition = theStringBuffer.length();
			theStringBuffer.append(theOriginalText);
			anEditText.setText(theStringBuffer.toString());
			anEditText.setSelection(theCursorPosition);
			return;
		}
		if(anEditText.getSelectionStart() == anEditText.getText().length()) {
			StringBuilder theStringBuilder = new StringBuilder(theOriginalText);
			insertWhiteSpace(theStringBuilder);
			theStringBuilder.append(theDictationString);
			anEditText.setText(theStringBuilder.toString());
			anEditText.setSelection(anEditText.getText().length());
			return;
		}
		StringBuilder theStringBuilder = new StringBuilder(theOriginalText.substring(0, anEditText.getSelectionStart()));
		insertWhiteSpace(theStringBuilder);
		theStringBuilder.append(theDictationString);
		int theNewSelectionStart = theStringBuilder.toString().length();
		String theOriginalTextToAppend = theOriginalText.substring(anEditText.getSelectionStart(), theOriginalText.length());
		if(theOriginalTextToAppend.length() > 0) {
			theStringBuilder.append(" ");
			theStringBuilder.append(theOriginalTextToAppend);
		}
		anEditText.setText(theStringBuilder.toString());
		anEditText.setSelection(theNewSelectionStart);
	}

	public static String getLikelyDictationResults(ArrayList<String> aDictionResultsList) {
		return aDictionResultsList.get(0).replaceFirst("\\s+$", "");
	}

	public static String capitalizeFirstLetter(String aDictationResult, String thePreceedingString) {
		char theLastChar = thePreceedingString.charAt(thePreceedingString.length() -1);
		if(theLastChar == '.' || theLastChar == '?' || theLastChar == '!') {
			return "  " + aDictationResult.substring(0, 1).toUpperCase(Locale.ENGLISH) + aDictationResult.substring(1, aDictationResult.length());
		}
		if(theLastChar != ' ') {
			return " " + aDictationResult;
		}
		return aDictationResult;
	}

	public static String capitalizeFirstLetter(String aDictationResult) {
		return aDictationResult != null && aDictationResult.length() > 0 ?
				aDictationResult.substring(0, 1).toUpperCase(Locale.ENGLISH) + aDictationResult.substring(1, aDictationResult.length()) :
					aDictationResult;
	}

	public static String capitalizeAllLetters(String aDictationResult) {
		return aDictationResult != null && aDictationResult.length() > 0 ?
				aDictationResult.toUpperCase(Locale.ENGLISH) :
					aDictationResult;
	}

	public static String capitalizeCamelCase(String aDictationResult) {
		String[] theTokens = aDictationResult.split(" ");
		String theCamelCaseString = "";
		for (String theStringToken : theTokens) {
			theCamelCaseString = theCamelCaseString + capitalizeFirstLetter(theStringToken) + " ";
		}
		return theCamelCaseString;
	}

	private static void insertWhiteSpace(StringBuilder theStringBuilder) {
		CharSequence theLastChar = theStringBuilder.subSequence(theStringBuilder.length() -1, theStringBuilder.length());
		if("abcdefghijklmnopqrstuvwxyz".contains(theLastChar)) {
			theStringBuilder.append(" ");
		}
	}

	private static void dispatchMetaKeyEvent(View aView, int aKeycode, int aMetaEvent) {
		KeyEvent theKeyEvent = new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
				aKeycode, 0, aMetaEvent); 
		aView.dispatchKeyEvent(theKeyEvent);
		theKeyEvent = new KeyEvent(0, 0, KeyEvent.ACTION_UP,
				aKeycode, 0, aMetaEvent); 
		aView.dispatchKeyEvent(theKeyEvent);
	}
	
	private static void dispatchMetaKeyEvent(AlertDialog anAlertDialog, int aKeycode, int aMetaEvent) {
		KeyEvent theKeyEvent = new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
				aKeycode, 0, aMetaEvent); 
		anAlertDialog.dispatchKeyEvent(theKeyEvent);
		theKeyEvent = new KeyEvent(0, 0, KeyEvent.ACTION_UP,
				aKeycode, 0, aMetaEvent); 
		anAlertDialog.dispatchKeyEvent(theKeyEvent);
	}

	private static void dispatchMetaKeyEvent(Activity anActivity, int aKeycode, int aMetaEvent) {
		KeyEvent theKeyEvent = new KeyEvent(0, 0, KeyEvent.ACTION_DOWN,
				aKeycode, 0, aMetaEvent); 
		anActivity.dispatchKeyEvent(theKeyEvent);
		theKeyEvent = new KeyEvent(0, 0, KeyEvent.ACTION_UP,
				aKeycode, 0, aMetaEvent); 
		anActivity.dispatchKeyEvent(theKeyEvent);
	}

	public static void dispatchShiftedKeyEvent(Activity anActivity, int aKeycode) {
		dispatchMetaKeyEvent(anActivity, aKeycode, KeyEvent.META_SHIFT_ON);
	}
	
	public static void dispatchShiftedKeyEvent(AlertDialog anAlertDialog, int aKeycode) {
		dispatchMetaKeyEvent(anAlertDialog, aKeycode, KeyEvent.META_SHIFT_ON);
	}

	public static void dispatchShiftedKeyEvent(View aView, int aKeycode) {
		dispatchMetaKeyEvent(aView, aKeycode, KeyEvent.META_SHIFT_ON);
	}

	public static void dispatchAltKeyEvent(Activity anActivity, int aKeycode) {
		dispatchMetaKeyEvent(anActivity, aKeycode, KeyEvent.META_ALT_ON);
	}
	
	public static void dispatchAltKeyEvent(AlertDialog anAlertDialog, int aKeycode) {
		dispatchMetaKeyEvent(anAlertDialog, aKeycode, KeyEvent.META_ALT_ON);
	}
	
	public static void dispatchAltKeyEvent(View aView, int aKeycode) {
		dispatchMetaKeyEvent(aView, aKeycode, KeyEvent.META_ALT_ON);
	}

	public static void dispatchControlKeyEvent(Activity anActivity, int aKeycode) {
		dispatchMetaKeyEvent(anActivity, aKeycode, KeyEvent.META_CTRL_ON);
	}
	
	public static void dispatchControlKeyEvent(AlertDialog anAlertDialog, int aKeycode) {
		dispatchMetaKeyEvent(anAlertDialog, aKeycode, KeyEvent.META_CTRL_ON);
	}

	public static void dispatchControlKeyEvent(View aView, int aKeycode) {
		dispatchMetaKeyEvent(aView, aKeycode, KeyEvent.META_CTRL_ON);
	}

	public static void dispatchAltShiftedKeyEvent(Activity anActivity, int aKeycode) {
		dispatchMetaKeyEvent(anActivity, aKeycode, KeyEvent.META_ALT_ON|KeyEvent.META_SHIFT_ON);
	}
	
	public static void dispatchAltShiftedKeyEvent(AlertDialog anAlertDialog, int aKeycode) {
		dispatchMetaKeyEvent(anAlertDialog, aKeycode, KeyEvent.META_ALT_ON|KeyEvent.META_SHIFT_ON);
	}

	public static void dispatchControlShiftedKeyEvent(Activity anActivity, int aKeycode) {
		dispatchMetaKeyEvent(anActivity, aKeycode, KeyEvent.META_CTRL_ON|KeyEvent.META_SHIFT_ON);
	}
	
	public static void dispatchControlShiftedKeyEvent(AlertDialog anAlertDialog, int aKeycode) {
		dispatchMetaKeyEvent(anAlertDialog, aKeycode, KeyEvent.META_CTRL_ON|KeyEvent.META_SHIFT_ON);
	}

	public static void dispatchControlShiftedKeyEvent(View aView, int aKeycode) {
		dispatchMetaKeyEvent(aView, aKeycode, KeyEvent.META_CTRL_ON|KeyEvent.META_SHIFT_ON);
	}

	public static void dispatchControlAltShiftKeyEvent(Activity anActivity, int aKeycode) {
		dispatchMetaKeyEvent(anActivity, aKeycode, KeyEvent.META_CTRL_ON|KeyEvent.META_ALT_ON|KeyEvent.META_SHIFT_ON);
	}

	public static void dispatchControlAltShiftKeyEvent(View aView, int aKeycode) {
		dispatchMetaKeyEvent(aView, aKeycode, KeyEvent.META_CTRL_ON|KeyEvent.META_ALT_ON|KeyEvent.META_SHIFT_ON);
	}

	public static void dispatchKeyEventDownUp(Activity anActivity, int aKeycode) {
		anActivity.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, aKeycode));
		anActivity.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, aKeycode));
	}
	
	public static void dispatchKeyEventDownUp(AlertDialog anAlertDialog, int aKeycode) {
		anAlertDialog.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, aKeycode));
		anAlertDialog.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, aKeycode));
	}

	public static void dispatchKeyEventDownUp(View aView, int aKeycode) {
		aView.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, aKeycode));
		aView.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, aKeycode));
	}

	public void dispatchDeleteAll(Activity anActivity) {
		this.fdkKeyboard.setFirstMultiShiftState();
        // TODO - why doesn't option 1 or 2 work ???
        // Option 1
//        anActivity.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ALT_LEFT));
//        dispatchKeyEventDownUp(anActivity, KeyEvent.KEYCODE_DPAD_LEFT);
//        anActivity.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT));
//        dispatchKeyEventDownUp(anActivity, KeyEvent.KEYCODE_DPAD_RIGHT);
//        anActivity.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SHIFT_LEFT));
//        anActivity.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ALT_LEFT));
//        dispatchKeyEventDownUp(anActivity, KeyEvent.KEYCODE_DEL);
        // Option 2
//        anActivity.dispatchKeyEvent(new KeyEvent(0, 0,
//                KeyEvent.ACTION_DOWN,
//                KeyEvent.KEYCODE_DPAD_LEFT, 0,
//                KeyEvent.META_ALT_ON) );
//        anActivity.dispatchKeyEvent(new KeyEvent(0, 0,
//                KeyEvent.ACTION_UP,
//                KeyEvent.KEYCODE_DPAD_LEFT, 0,
//                KeyEvent.META_ALT_ON) );
//        anActivity.dispatchKeyEvent(new KeyEvent(0, 0,
//                KeyEvent.ACTION_DOWN,
//                KeyEvent.KEYCODE_DPAD_RIGHT, 0,
//                KeyEvent.META_SHIFT_ON|KeyEvent.META_ALT_ON) );
//        anActivity.dispatchKeyEvent(new KeyEvent(0, 0,
//                KeyEvent.ACTION_UP,
//                KeyEvent.KEYCODE_DPAD_RIGHT, 0,
//                KeyEvent.META_SHIFT_ON|KeyEvent.META_ALT_ON) );
//        dispatchKeyEventDownUp(anActivity, KeyEvent.KEYCODE_DEL);
        // TODO - HACK ALERT !!!
        for(int theIndex = 0; theIndex < 500; ++theIndex) {
            dispatchKeyEventDownUp(anActivity, KeyEvent.KEYCODE_DPAD_LEFT);
        }
        anActivity.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT));
        for(int theIndex = 0; theIndex < 500; ++theIndex) {
            dispatchKeyEventDownUp(anActivity, KeyEvent.KEYCODE_DPAD_RIGHT);
        }
        anActivity.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SHIFT_LEFT));
        dispatchKeyEventDownUp(anActivity, KeyEvent.KEYCODE_DEL);
	}
	
	public void dispatchDeleteAll(AlertDialog anAlertDialog) {
		this.fdkKeyboard.setFirstMultiShiftState();
//		dispatchAltKeyEvent(anAlertDialog, KeyEvent.KEYCODE_DPAD_LEFT);
//		anAlertDialog.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT));
//		anAlertDialog.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_ALT_LEFT));
//		dispatchKeyEventDownUp(anAlertDialog, KeyEvent.KEYCODE_DPAD_RIGHT);
//		anAlertDialog.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_ALT_LEFT));
//		anAlertDialog.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SHIFT_LEFT));
//		dispatchKeyEventDownUp(anAlertDialog, KeyEvent.KEYCODE_DEL);

        // TODO - HACK ALERT !!!
        for(int theIndex = 0; theIndex < 500; ++theIndex) {
            dispatchKeyEventDownUp(anAlertDialog, KeyEvent.KEYCODE_DPAD_LEFT);
        }
        anAlertDialog.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_SHIFT_LEFT));
        for(int theIndex = 0; theIndex < 500; ++theIndex) {
            dispatchKeyEventDownUp(anAlertDialog, KeyEvent.KEYCODE_DPAD_RIGHT);
        }
        anAlertDialog.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_UP, KeyEvent.KEYCODE_SHIFT_LEFT));
        dispatchKeyEventDownUp(anAlertDialog, KeyEvent.KEYCODE_DEL);
	}

	public static void dispatchDeleteWord(View aView) {
		dispatchKeyEventDownUp(aView, KeyEvent.KEYCODE_DPAD_LEFT);
		dispatchControlKeyEvent(aView, KeyEvent.KEYCODE_DPAD_RIGHT);
		dispatchControlShiftedKeyEvent(aView, KeyEvent.KEYCODE_DPAD_LEFT);
		dispatchKeyEventDownUp(aView, KeyEvent.KEYCODE_DEL);
	}

	public static void dispatchDeleteWord(Activity anActivity) {
		dispatchKeyEventDownUp(anActivity, KeyEvent.KEYCODE_DPAD_LEFT);
		dispatchControlKeyEvent(anActivity, KeyEvent.KEYCODE_DPAD_RIGHT);
		dispatchControlShiftedKeyEvent(anActivity, KeyEvent.KEYCODE_DPAD_LEFT);
		dispatchKeyEventDownUp(anActivity, KeyEvent.KEYCODE_DEL);
	}

	public static void clearContents(EditText anEditText) {
		anEditText.setText("");
	}

	public static void deleteWord(EditText anEditText) {
		int theStartPosition;
		int theEndPosition;
		if(anEditText.getSelectionStart() == 0) {
			theStartPosition = 0;
//			FdkHelper.leaveWhiteSpaceAtEndOfWord = false;
		} else {
			theStartPosition = getBeginningOfWordPosition(anEditText);
		}
		if(anEditText.getSelectionStart() == anEditText.getText().length()) {
			theEndPosition = anEditText.getText().length();
		} else {
			theEndPosition = getEndOfWordPosition(anEditText);
		}
//		FdkHelper.leaveWhiteSpaceAtEndOfWord = true;
		anEditText.setSelection(theStartPosition, theEndPosition);
		anEditText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
	}

	private static int getBeginningOfWordPosition(EditText anEditText) {
		int theBeginningPosition = 0;
		for(int i = anEditText.getSelectionStart(); i > 0; --i) {
			if(anEditText.getText().toString().substring(i - 1, i).equals(" ")) {
				theBeginningPosition = i;
				break;
			}
		}
		if(theBeginningPosition == 0) {
			return 0;
		}
		for(int i = theBeginningPosition; i > 0; --i) {
			if(anEditText.getText().toString().substring(i - 1, i).equals(" ")) {
				--theBeginningPosition;
			} else {
				break;
			}
		}
//		FdkHelper.leaveWhiteSpaceAtEndOfWord = false;
		return theBeginningPosition;
	}

	private static int getEndOfWordPosition(EditText anEditText) {
		int theEndingPosition = anEditText.getText().length();
		// find first white space
		for(int i = anEditText.getSelectionStart(); i < anEditText.getText().length(); ++i) {
			if(anEditText.getText().toString().substring(i, i + 1).equals(" ")) {
				theEndingPosition = i;
				break;
			}
		}
		// consume additional white space
		if(theEndingPosition != anEditText.getText().length()) {
			for(int i = theEndingPosition; i < anEditText.getText().length(); ++i) {
				if(anEditText.getText().toString().substring(i, i + 1).equals(" ")) {
					++theEndingPosition;
				} else {
					break;
				}
			}
		}
		return theEndingPosition;
	}

	public static FdkDictationResultsConsumer getNextFdkDictationResultsConsumer(
			FdkDictationResultsConsumer aCurrentConsumer, ArrayList<FdkDictationResultsConsumer> anFdkDictationResultsConsumerList) {
		FdkDictationResultsConsumer theNextConsumer = anFdkDictationResultsConsumerList.get(0);
		if(aCurrentConsumer != null && anFdkDictationResultsConsumerList.size() > 1) {
			int theIndex = anFdkDictationResultsConsumerList.indexOf(aCurrentConsumer);
			if(theIndex < anFdkDictationResultsConsumerList.size() - 1) {
				theNextConsumer = anFdkDictationResultsConsumerList.get(theIndex + 1);
			}
		}
		return theNextConsumer;
	}

	public static FdkDictationResultsConsumer getPreviousFdkDictationResultsConsumer(
			FdkDictationResultsConsumer aCurrentConsumer, ArrayList<FdkDictationResultsConsumer> anFdkDictationResultsConsumerList) {
		FdkDictationResultsConsumer thePreviousConsumer = anFdkDictationResultsConsumerList.get(anFdkDictationResultsConsumerList.size() - 1);
		if(aCurrentConsumer != null && anFdkDictationResultsConsumerList.size() > 1) {
			int theIndex = anFdkDictationResultsConsumerList.indexOf(aCurrentConsumer);
			if(theIndex > 0) {
				thePreviousConsumer = anFdkDictationResultsConsumerList.get(theIndex - 1);
			}
		}
		return thePreviousConsumer;
	}

	public static void positionSpinnerListRow(ArrayList<String> aDictationResultsList, GcgWidgetSpinner aWidgetSpinner) {
		for(String theString : aDictationResultsList) {
			theString = theString.replaceFirst("\\s+$", "");
			for(GcgGuiable theGcgGuiable : aWidgetSpinner.getGcgGuiableList()) {
				if(theGcgGuiable.getDataText().toLowerCase(Locale.ENGLISH).contains(theString.toLowerCase(Locale.ENGLISH))) {
					aWidgetSpinner.setSelection(theGcgGuiable);
					return;
				}
			}
		}
	}
	
	////////////////////////////////////////

	public void destroyServices() {
		if(this.speechRecognizer != null) {
			this.speechRecognizer.destroy();
			this.audioManager.stopBluetoothSco();
		}
	}

	public void initOptionsMenu(Menu aMenu) {
		this.fdkKeyboardMenuItem = aMenu.findItem(R.id.action__flywheel_dictation_keyboard);
		if(this.fdkKeyboard != null) {
			this.fdkKeyboardMenuItem.setIcon(this.fdkKeyboard.getKeyboardState().getDrawable());
		} else {
			this.fdkKeyboardMenuItem.setIcon(FdkKeyboardState.DISABLED.getDrawable());
		}
	}

	public void setMenuItemIcon() {
		this.fdkKeyboardMenuItem.setIcon(this.fdkKeyboard.getKeyboardState().getDrawable());
	}

	@Override
	public void activateFdkKeyboard(boolean bActivate) {
		this.fdkKeyboard.activate(bActivate);
	}

	@Override
	public Context getContext() {
		return this.fdkHost.getContext();
	}

//	/*
//	 * harvested from paragraph editor, but not used
//	 */
//	private static boolean leaveWhiteSpaceAtEndOfWord = true;
//
//	public static void insertDictationResultsIntoParagraph(ArrayList<String> aWordList, EditText anEditText, boolean bCamelCase, boolean bSentenceMarkup) {
//		String theDictationString = aWordList.get(0);
//		if(theDictationString.length() == 0) {
//			return;
//		}
//		if(anEditText.getSelectionStart() != anEditText.getSelectionEnd()) {
//			anEditText.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_FORWARD_DEL));
//		}
//		String theOriginalText = anEditText.getText().toString();
//		if(theOriginalText.length() == 0) {
//			StringBuffer theStringBuffer = new StringBuffer();
//			theStringBuffer.append(theDictationString.substring(0, 1).toUpperCase(Locale.ENGLISH));
//			theStringBuffer.append(theDictationString.substring(1));
//			anEditText.append(theStringBuffer.toString());
//			anEditText.setSelection(anEditText.getText().length());
//			return;
//		}
//		if(anEditText.getSelectionStart() == 0) {
//			StringBuffer theStringBuffer = new StringBuffer();
//			theStringBuffer.append(theDictationString.substring(0, 1).toUpperCase(Locale.ENGLISH));
//			theStringBuffer.append(theDictationString.substring(1));
//			int theCursorPosition = theStringBuffer.length();
//			theStringBuffer.append(theOriginalText);
//			anEditText.setText(theStringBuffer.toString());
//			anEditText.setSelection(theCursorPosition);
//			return;
//		}
//		if(anEditText.getSelectionStart() == anEditText.getText().length()) {
//			StringBuilder theStringBuilder = new StringBuilder(theOriginalText);
//			if(bSentenceMarkup) {
//				theStringBuilder.append(capitalizeFirstLetter(theDictationString, theStringBuilder.toString()));
//			} else {
//				theStringBuilder.append(theDictationString);
//			}
//			anEditText.setText(theStringBuilder.toString());
//			anEditText.setSelection(anEditText.getText().length());
//			return;
//		}
//		StringBuilder theStringBuilder = new StringBuilder(theOriginalText.substring(0, anEditText.getSelectionStart()));
//		if(bSentenceMarkup) {
//			insertWhiteSpace(theStringBuilder);
//		}
//		theStringBuilder.append(capitalizeFirstLetter(theDictationString, theStringBuilder.toString()));
//		theStringBuilder.append(theOriginalText.substring(anEditText.getSelectionEnd(), theOriginalText.length()));
//		anEditText.setText(theStringBuilder.toString());
//	}

}
