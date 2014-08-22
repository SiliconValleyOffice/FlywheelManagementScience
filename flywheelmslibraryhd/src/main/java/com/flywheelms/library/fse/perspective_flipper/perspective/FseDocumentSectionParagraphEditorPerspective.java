/* @(#)FseDocumentSectionParagraphEditorPerspective.java
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

package com.flywheelms.library.fse.perspective_flipper.perspective;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ListIterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.enumerator.FmmLockStatus;
import com.flywheelms.library.fse.enumerator.FseContentModificationState;
import com.flywheelms.library.fse.enumerator.FseInsertModificationState;
import com.flywheelms.library.fse.enumerator.FseLockModificationState;
import com.flywheelms.library.fse.enumerator.FseNumberingModificationState;
import com.flywheelms.library.fse.enumerator.FseParagraphNumberingStyle;
import com.flywheelms.library.fse.enumerator.FseSequenceModificationState;
import com.flywheelms.library.fse.enumerator.FseStyleModificationState;
import com.flywheelms.library.fse.model.FseDocumentSection;
import com.flywheelms.library.fse.model.FseDocumentSectionParagraphAudit;
import com.flywheelms.library.fse.model.FseDocumentSectionParagraphEditorContent;
import com.flywheelms.library.fse.model.FseParagraph;
import com.flywheelms.library.fse.perspective_flipper.FsePerspectiveFlipper;
import com.flywheelms.library.fse.util.FseDocumentSerialization;
import com.flywheelms.library.fse.views.FseParagraphEditor;
import com.flywheelms.library.fse.views.FseParagraphView;
import com.flywheelms.library.fse.widget.FseMultiShiftButton;
import com.flywheelms.library.gcg.button.multi_shift.GcgMultiShiftClientButton;
import com.flywheelms.library.gcg.button.multi_shift.GcgMultiShiftKeysetController;
import com.flywheelms.library.gcg.button.multi_shift.GcgMultiShiftState;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.interfaces.GcgMultiShiftControllerParent;
import com.flywheelms.library.gcg.menu.GcgSpinnableMenu;

public abstract class FseDocumentSectionParagraphEditorPerspective extends FseDocumentSectionPerspective
		implements GcgMultiShiftControllerParent {

	protected FseParagraphEditor paragraphEditor;
	protected boolean isBrowserMode = false;
	protected ScrollView scrollView;
	protected LinearLayout documentSectionLayout;
	protected FseSequenceModificationState sequenceModificationState = FseSequenceModificationState.UNCHANGED;
	private ImageView sequenceModificationImage;
	protected FseLockModificationState lockModificationState = FseLockModificationState.UNCHANGED;
	private ImageView lockModificationImage;
	protected FseContentModificationState contentModificationState = FseContentModificationState.UNCHANGED;
	protected ImageView contentModificationImage;
	protected FseInsertModificationState insertModificationState = FseInsertModificationState.UNCHANGED;
	private ImageView insertModificationImage;
	protected FseNumberingModificationState numberingModificationState = FseNumberingModificationState.UNCHANGED;
	private ImageView numberingModificationImage;
	protected FseStyleModificationState styleModificationState = FseStyleModificationState.UNCHANGED;
	private ImageView styleModificationImage;
	// START - data members that are NULL in portrait mode
	private GcgMultiShiftKeysetController multiShiftController;
	private ArrayList<GcgMultiShiftState> supportedMultiShiftStateList;
	protected RelativeLayout fdkKeypadPeerRight;
	private GcgSpinnableMenu rightSpinnableMenu;
	protected LinearLayout rightMenuLayout;
	private GcgMultiShiftClientButton paragraphStyleIndentButton;
	private GcgMultiShiftClientButton paragraphStyleOutdentButton;
	private GcgMultiShiftClientButton paragraphStylePromoteButton;
	private GcgMultiShiftClientButton paragraphStyleDemoteButton;
	private GcgMultiShiftClientButton paragraphSequenceUpButton;
	private GcgMultiShiftClientButton paragraphSequenceDownButton;
	private Button paragraphNumberBulletButton;
	private Button paragraphNumberNumberedButton;
	private Button paragraphNumberAlphaButton;
	private Button paragraphNumberRomanButton;
	private GcgMultiShiftClientButton paragraphLockButton;
	private GcgMultiShiftClientButton paragraphUnlockButton;
	private Button paragraphHistoryButton;
	
	// END - data members that are NULL in portrait mode
	boolean doNotChangeModificationState = true;

	public FseDocumentSectionParagraphEditorPerspective(Context context, AttributeSet attrs) {
		super(context, attrs);
		this.scrollView = (ScrollView) findViewById(R.id.fse__document__scrollview);
		this.leftMenuSpacerResourceId = R.drawable.left_menu__filler_1;
		this.enableMultiShiftControls = true;
	}

	@Override
	protected View getFdkKeypadPeerViewRight() {
		return this.fdkKeypadPeerRight;
	}

	@Override
	protected FrameLayout getGcgThumbpadRight() {
		return (FrameLayout) findViewById(R.id.gcg__thumbpad__right);
	}

	@Override
	protected View getFdkKeypadPeerViewLeft() {
		return this.gcgActivity.getFdkKeypadPeerViewLeft();
	}

	@Override
	protected FrameLayout getGcgThumbpadLeft() {
		return this.gcgActivity.getGcgThumbpadLeft();
	}

//	@Override
//	protected FdkRightKeypad fdkGetRightKeypad() {
//		FrameLayout theRigtKeypadParent = (FrameLayout) findViewById(R.id.gcg__thumbpad__right);
//		FdkRightKeypad theFdkRightKeypad = (FdkRightKeypad) theRigtKeypadParent.findViewById(R.id.fdk__right_keypad);
//		theFdkRightKeypad.initializeKeypad(FdkRightKeypad.KEYPAD_STYLE_1);
//		theFdkRightKeypad.initialize(this.rightFdkKeypadPeer);
//		return theFdkRightKeypad;
//	}

//	@Override
//	protected FdkLeftKeypad fdkGetLeftKeypad() {
//		FdkLeftKeypad theLeftKeypad = (FdkLeftKeypad) getFseDocumentViewParent().getGcgThumbpadLeft().findViewById(R.id.fdk__left_keypad);
//		theLeftKeypad.initialize(getFseDocumentViewParent().getFdkLeftKeypadPeerView());
//		return theLeftKeypad;
//	}

	@Override
	public void fdkDictationResults(ArrayList<String> aWordList) {
		this.paragraphEditor.fdkDictationResults(aWordList.get(0));
		return;
	}
	
	@Override
	protected void initialize(FsePerspectiveFlipper aDocumentView) {
		setDocumentSectionLayout();
		super.initialize(aDocumentView);
		initGuiElements();
	}

	@Override
	public ViewGroup getDocumentSectionLayout() {
		return this.documentSectionLayout;
	}

	@Override
	public void setDocumentSectionLayout() {
//		this.documentSectionLayout =
				inflate(this.context, getDocumentSectionViewResourceId(), null);
//				(LinearLayout) this.documentView.findViewById(getDocumentSectionViewResourceId());
//		sizeLayoutToDisplay();  // HACK ALERT - SDS
		this.fdkKeypadPeerRight =
				(RelativeLayout) findViewById(R.id.fdk__right_keypad_peer);
		/////////
		LinearLayout theLinearLayout = (LinearLayout) findViewById(R.id.fse__scrollable_document);
		this.sequenceModificationImage = (ImageView) theLinearLayout.findViewById(
				R.id.fse__document_body__heading__paragraph_sequence_status);
		this.lockModificationImage = (ImageView) theLinearLayout.findViewById(
				R.id.fse__document_body__heading__paragraph_lock_status);
		this.contentModificationImage = (ImageView) theLinearLayout.findViewById(
				R.id.fse__document_body__heading__paragraph_content_status);
		this.insertModificationImage = (ImageView) theLinearLayout.findViewById(
				R.id.fse__document_body__heading__paragraph_insert_status);
		this.numberingModificationImage = (ImageView) theLinearLayout.findViewById(
				R.id.fse__document_body__heading__paragraph_numbering_status);
		this.styleModificationImage = (ImageView) theLinearLayout.findViewById(
				R.id.fse__document_body__heading__paragraph_style_status);
	}

//	private void sizeLayoutToDisplay() {
//		FrameLayout theFrameLayout = (FrameLayout) findViewById(R.id.fse__document__scrollview__frame);
//		LinearLayout.LayoutParams theLayoutParams =
//				(LinearLayout.LayoutParams) theFrameLayout.getLayoutParams();
////		int theTabRowHeight = GcgHelper.getPixelsForDp(GcgApplication.getContext(), 100);
////		int theTargetSize = GcgHelper.getPixelsForDp(GcgApplication.getContext(), 394);
//		int theOtherViewHeights = GcgHelper.getPixelsForDp(GcgApplication.getContext(), 135);
//		int theLayoutHeight = GcgHelper.getScreenHeight() - theOtherViewHeights;
//		theLayoutParams.height = theLayoutHeight;
////		theFrameLayout.setLayoutParams(theLayoutParams);
//	}

	public String getInitialParagraphFocusId() {
		return this.paragraphEditor.getInitialParagraphFocusId();
	}

	public int getInitialParagraphFocusCursorPosition() {
		return this.paragraphEditor.getInitialParagraphFocusCursorPosition();
	}

	public LinkedList<FseParagraphView> getParagraphViewList() {
		return this.paragraphEditor.getParagraphViewList();
	}

	private void initGuiElements() {
		if(this.isBrowserMode) {
			initParagraphEditorView();
		} else {
			if(GcgHelper.isLandscapeMode()) {
				initParagraphEditorView();
				initRightMenu();
				initMultiShiftKeyset();
				configMultiShiftClientButtons();
				configMultiShiftStateListeners();
			} else {
				initParagraphEditorView();
				configMultiShiftStateListeners();
			}
		}
	}

	@Override
	public void viewDocumentSection(FseDocumentSection aDocumentSection) {
		this.paragraphEditor.viewDocumentSection((FseDocumentSectionParagraphEditorContent)aDocumentSection);
	}

	@Override
	public void viewDocumentSectionAsHistory(FseDocumentSection aDocumentSection) {
		this.paragraphEditor.viewDocumentSectionAsHistory((FseDocumentSectionParagraphEditorContent)aDocumentSection);
	}

	@Override
	public String sectionIsModified() {
		return null;
	}
	
	public FseParagraphEditor getFseParagraphEditor() {
		return this.paragraphEditor;
	}

	private void initParagraphEditorView() {
		this.paragraphEditor =
				(FseParagraphEditor) findViewById(R.id.fse__paragraph_editor);
		this.paragraphEditor.initialize(this);
	}
	
	@Override
	public void activateView() {
		super.activateView();
		this.paragraphEditor.resetFocus();
	}
	
	protected GcgSpinnableMenu getLeftSpinnableMenu() {
		return this.documentPerspectiveFlipper.getLeftSpinnableMenu();
	}
	
	public String getOriginalInitialParagraphFocusId() {
		return this.paragraphEditor.getOriginalInitialParagraphFocusId();
	}

	public int getOriginalInitialParagraphFocusCursorPosition() {
		return this.paragraphEditor.getOriginalInitialParagraphFocusCursorPosition();
	}
	
	/////////////////////////////////////////////////////////////////////////////////

	protected void initRightMenu() {
		int[] theMenuBodyResourceIdArray = {
				R.id.fse__style_menu__body,
				R.id.fse__sequence_menu__body,
				R.id.fse__number_menu__body,
				R.id.fse__insert_menu__body,
				R.id.fse__lock_menu__body };
		this.rightMenuLayout = (LinearLayout) this.fdkKeypadPeerRight.findViewById(R.id.fse__right_menu);
		this.rightSpinnableMenu = new GcgSpinnableMenu(
				getContext(),
				this.rightMenuLayout,
				GcgSpinnableMenu.DECORATORS_RIGHT,
				R.id.fse__right_menu__heading_spinner,
				R.array.fse__right_menu__heading_array,
				theMenuBodyResourceIdArray );
		initStyleMenu();
		initSequenceMenu();
		initListsMenu();
		initLockMenu();
	}

	private void initMultiShiftKeyset() {
		ViewGroup theLeftKeypadPeerView = getGcgActivity().getFdkKeypadPeerViewLeft();
		FseMultiShiftButton theLeftTouchShiftButton = (FseMultiShiftButton )theLeftKeypadPeerView.findViewById(R.id.multi_shift__left_button);
		theLeftTouchShiftButton.setOnClickListener(new OnClickListener() {
			
			@Override
		    public void onClick(View v) {
		        FseDocumentSectionParagraphEditorPerspective.this.getDocumentPerspectiveFlipper().getActiveDocumentSectionView().getMultiShiftController().onLeftButtonClick();
//		        FseDocumentSectionParagraphEditorView.this.multiShiftController.onLeftButtonClick();
		    } 
		});
		FseMultiShiftButton theRightTouchShiftButton =
				(FseMultiShiftButton) this.fdkKeypadPeerRight.findViewById(R.id.multi_shift__right_button);
		theRightTouchShiftButton.setOnClickListener(new OnClickListener() {
			
			@Override
		    public void onClick(View v) {
		        FseDocumentSectionParagraphEditorPerspective.this.getDocumentPerspectiveFlipper().getActiveDocumentSectionView().getMultiShiftController().onRightButtonClick();
		    } 
		});
		this.multiShiftButtonList = new ArrayList<FseMultiShiftButton>();
		this.multiShiftButtonList.add(theLeftTouchShiftButton);
		this.multiShiftButtonList.add(theRightTouchShiftButton);
		this.multiShiftController = new GcgMultiShiftKeysetController(this);
	}

	private void configMultiShiftClientButtons() {
		this.multiShiftController.addButtonListener(this.paragraphStyleIndentButton);
		this.paragraphStyleIndentButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseDocumentSectionParagraphEditorPerspective.this.onClickParagraphStyleIndent();
			}
		} );
		this.multiShiftController.addButtonListener(this.paragraphStyleOutdentButton);
		this.paragraphStyleOutdentButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseDocumentSectionParagraphEditorPerspective.this.onClickParagraphStyleOutdent();
			}
		} );
		this.multiShiftController.addButtonListener(this.paragraphStylePromoteButton);
		this.paragraphStylePromoteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseDocumentSectionParagraphEditorPerspective.this.onClickParagraphStylePromote();
			}
		} );
		this.multiShiftController.addButtonListener(this.paragraphStyleDemoteButton);
		this.paragraphStyleDemoteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseDocumentSectionParagraphEditorPerspective.this.onClickParagraphStyleDemote();
			}
		} );
		this.multiShiftController.addButtonListener(this.paragraphSequenceUpButton);
		this.paragraphSequenceUpButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseDocumentSectionParagraphEditorPerspective.this.onClickParagraphSequenceUp();
			}
		} );
		this.multiShiftController.addButtonListener(this.paragraphSequenceDownButton);
		this.paragraphSequenceDownButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseDocumentSectionParagraphEditorPerspective.this.onClickParagraphSequenceDown();
			}
		} );
		this.multiShiftController.addButtonListener(this.paragraphLockButton);
		this.paragraphLockButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseDocumentSectionParagraphEditorPerspective.this.onClickParagraphLock();
			}
		} );
		this.multiShiftController.addButtonListener(this.paragraphUnlockButton);
		this.paragraphUnlockButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseDocumentSectionParagraphEditorPerspective.this.onClickParagraphUnlock();
			}
		} );
	}
	
	private void configMultiShiftStateListeners() {
		if(this.multiShiftController != null) {
			this.multiShiftController.addMultiShiftStateListener(this.paragraphEditor);
		}
	}

	private void initStyleMenu() {
		this.paragraphStyleIndentButton = (GcgMultiShiftClientButton) findViewById(R.id.style_indent_button);
		this.paragraphStyleOutdentButton = (GcgMultiShiftClientButton) findViewById(R.id.style_outdent_button);
		this.paragraphStylePromoteButton = (GcgMultiShiftClientButton) findViewById(R.id.style_promote_button);
		this.paragraphStyleDemoteButton = (GcgMultiShiftClientButton) findViewById(R.id.style_demote_button);
	}

	private void initSequenceMenu() {
		this.paragraphSequenceUpButton = (GcgMultiShiftClientButton) findViewById(R.id.sequence_up_button);
		this.paragraphSequenceDownButton = (GcgMultiShiftClientButton) findViewById(R.id.sequence_down_button);
	}
	
	private void initListsMenu() {
		this.paragraphNumberBulletButton = (Button) findViewById(R.id.number__bullet__button);
		this.paragraphNumberBulletButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseDocumentSectionParagraphEditorPerspective.this.paragraphEditor.renumberPeerParagraphs(
						FseDocumentSectionParagraphEditorPerspective.this.paragraphEditor.getParagraphViewFocus(),
						FseParagraphNumberingStyle.BULLET,
						true );
			}
		});
		this.paragraphNumberNumberedButton = (Button) findViewById(R.id.number__numbered__button);
		this.paragraphNumberNumberedButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseDocumentSectionParagraphEditorPerspective.this.paragraphEditor.renumberPeerParagraphs(
						FseDocumentSectionParagraphEditorPerspective.this.paragraphEditor.getParagraphViewFocus(),
						FseParagraphNumberingStyle.NUMBERED,
						true );
			}
		});
		this.paragraphNumberAlphaButton = (Button) findViewById(R.id.number__alpha__button);
		this.paragraphNumberAlphaButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseDocumentSectionParagraphEditorPerspective.this.paragraphEditor.renumberPeerParagraphs(
						FseDocumentSectionParagraphEditorPerspective.this.paragraphEditor.getParagraphViewFocus(),
						FseParagraphNumberingStyle.ALPHA,
						true );
			}
		});
		this.paragraphNumberRomanButton = (Button) findViewById(R.id.fse_number__roman__button);
		this.paragraphNumberRomanButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseDocumentSectionParagraphEditorPerspective.this.paragraphEditor.renumberPeerParagraphs(
						FseDocumentSectionParagraphEditorPerspective.this.paragraphEditor.getParagraphViewFocus(),
						FseParagraphNumberingStyle.ROMAN,
						true );
			}
		});
	}

	private void initLockMenu() {
		this.paragraphLockButton = (GcgMultiShiftClientButton) findViewById(R.id.lock__lock__button);
		this.paragraphUnlockButton = (GcgMultiShiftClientButton) findViewById(R.id.lock__unlock__button);
		this.paragraphHistoryButton = (Button) findViewById(R.id.lock__history__button);
		this.paragraphHistoryButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FseDocumentSectionParagraphEditorPerspective.this.onClickParagraphHistory();
			}
		} );
	}
	
	/////  button callbacks  //////
	
	public void onClickParagraphStyleIndent() {
		LinkedList<FseParagraphView> theParagraphViewList = null;
		switch(this.paragraphStyleIndentButton.getMultiShiftState()) {
		case OFF:
			theParagraphViewList = this.paragraphEditor.getParagraphViewSelectionList();
			break;
		case CTL:
			break;
		case SHIFT:
			break;
		case ALT:
			theParagraphViewList = this.paragraphEditor.getParagraphViewDescendentList(this.paragraphEditor.getParagraphViewFocus());
			theParagraphViewList.add(0, this.paragraphEditor.getParagraphViewFocus());
			break;
//		case ALT_SHIFT:
//			break;
		default:
			theParagraphViewList = this.paragraphEditor.getParagraphViewSelectionList();
		}
		if(theParagraphViewList != null && theParagraphViewList.size() > 0 && this.paragraphEditor.canIndentList(theParagraphViewList)) {
			this.paragraphEditor.styleIndentParagraphViewList(theParagraphViewList);
			this.paragraphEditor.resetParagraphViewSelectionList(theParagraphViewList);
			this.paragraphEditor.setParagraphSpinnerFocusLost();
			this.paragraphEditor.setParagraphSpinnerFocus(theParagraphViewList);
		}
		this.multiShiftController.resetState();
	}
	
	public void onClickParagraphStyleOutdent() {
		LinkedList<FseParagraphView> theParagraphViewList = null;
		switch(this.paragraphStyleOutdentButton.getMultiShiftState()) {
		case OFF:
			theParagraphViewList = this.paragraphEditor.getParagraphViewSelectionList();
			break;
		case CTL:
			break;
		case SHIFT:
			break;
		case ALT:
			theParagraphViewList = this.paragraphEditor.getParagraphViewDescendentList(this.paragraphEditor.getParagraphViewFocus());
			theParagraphViewList.add(0, this.paragraphEditor.getParagraphViewFocus());
			break;
//		case ALT_SHIFT:
//			break;
		default:
			theParagraphViewList = this.paragraphEditor.getParagraphViewSelectionList();
		}
		if(theParagraphViewList != null && theParagraphViewList.size() > 0 && this.paragraphEditor.canOutdentList(theParagraphViewList)) {
			this.paragraphEditor.styleOutdentParagraphViewList(theParagraphViewList);
			this.paragraphEditor.resetParagraphViewSelectionList(theParagraphViewList);
			this.paragraphEditor.setParagraphSpinnerFocusLost();
			this.paragraphEditor.setParagraphSpinnerFocus(theParagraphViewList);
		}
		this.multiShiftController.resetState();
	}

	protected void onClickParagraphStylePromote() {
		LinkedList<FseParagraphView> theParagraphViewList = null;
		switch(this.paragraphStylePromoteButton.getMultiShiftState()) {
		case OFF:
			theParagraphViewList = this.paragraphEditor.getParagraphViewSelectionList();
			break;
		case CTL:
			break;
		case SHIFT:
			break;
		case ALT:
			theParagraphViewList = this.paragraphEditor.getParagraphViewDescendentList(this.paragraphEditor.getParagraphViewFocus());
			theParagraphViewList.add(0, this.paragraphEditor.getParagraphViewFocus());
			break;
//		case ALT_SHIFT:
//			break;
		default:
			theParagraphViewList = this.paragraphEditor.getParagraphViewSelectionList();
		}
		if(theParagraphViewList != null && theParagraphViewList.size() > 0 && this.paragraphEditor.canPromoteList(theParagraphViewList)) {
			this.paragraphEditor.stylePromoteParagraphViewList(theParagraphViewList);
			this.paragraphEditor.resetParagraphViewSelectionList(theParagraphViewList);
			this.paragraphEditor.setParagraphSpinnerFocusLost();
			this.paragraphEditor.setParagraphSpinnerFocus(theParagraphViewList);
		}
		this.multiShiftController.resetState();
	}
	
	protected void onClickParagraphStyleDemote() {
		LinkedList<FseParagraphView> theParagraphViewList = null;
		switch(this.paragraphStyleDemoteButton.getMultiShiftState()) {
		case OFF:
			theParagraphViewList = this.paragraphEditor.getParagraphViewSelectionList();
			break;
		case CTL:
			break;
		case SHIFT:
			break;
		case ALT:
			theParagraphViewList = this.paragraphEditor.getParagraphViewDescendentList(this.paragraphEditor.getParagraphViewFocus());
			theParagraphViewList.add(0, this.paragraphEditor.getParagraphViewFocus());
			break;
//		case ALT_SHIFT:
//			break;
		default:
			theParagraphViewList = this.paragraphEditor.getParagraphViewSelectionList();
		}
		if(theParagraphViewList != null && theParagraphViewList.size() > 0 && this.paragraphEditor.canDemoteList(theParagraphViewList)) {
			this.paragraphEditor.styleDemoteParagraphViewList(theParagraphViewList);
			this.paragraphEditor.resetParagraphViewSelectionList(theParagraphViewList);
			this.paragraphEditor.setParagraphSpinnerFocusLost();
			this.paragraphEditor.setParagraphSpinnerFocus(theParagraphViewList);
		}
		this.multiShiftController.resetState();
	}
	
	public void onClickParagraphLock() {
		LinkedList<FseParagraphView> theParagraphViewList = null;
		switch(this.paragraphStyleIndentButton.getMultiShiftState()) {
		case OFF:
			theParagraphViewList = this.paragraphEditor.getParagraphViewSelectionList();
			break;
		case CTL:
			break;
		case SHIFT:
			break;
		case ALT:
			theParagraphViewList = this.paragraphEditor.getParagraphViewDescendentList(this.paragraphEditor.getParagraphViewFocus());
			theParagraphViewList.add(0, this.paragraphEditor.getParagraphViewFocus());
			break;
//		case ALT_SHIFT:
//			break;
		default:
			theParagraphViewList = this.paragraphEditor.getParagraphViewSelectionList();
		}
		if(theParagraphViewList != null && theParagraphViewList.size() > 0) {
			this.paragraphEditor.lockParagraphViewList(theParagraphViewList);
			updateLockModificationState();
		}
		this.multiShiftController.resetState();
	}
	
	public void onClickParagraphUnlock() {
		LinkedList<FseParagraphView> theParagraphViewList = null;
		switch(this.paragraphStyleIndentButton.getMultiShiftState()) {
		case OFF:
			theParagraphViewList = this.paragraphEditor.getParagraphViewSelectionList();
			break;
		case CTL:
			break;
		case SHIFT:
			break;
		case ALT:
			theParagraphViewList = this.paragraphEditor.getParagraphViewDescendentList(this.paragraphEditor.getParagraphViewFocus());
			theParagraphViewList.add(0, this.paragraphEditor.getParagraphViewFocus());
			break;
//		case ALT_SHIFT:
//			break;
		default:
			theParagraphViewList = this.paragraphEditor.getParagraphViewSelectionList();
		}
		if(theParagraphViewList != null && theParagraphViewList.size() > 0) {
			this.paragraphEditor.unlockParagraphViewList(theParagraphViewList);
			updateLockModificationState();
		}
		this.multiShiftController.resetState();
	}
	
	public void onClickParagraphHistory() {
		this.multiShiftController.resetState();
		this.paragraphEditor.getParagraphViewFocus().showParagraphHistoryDialog();
	}

	@SuppressWarnings("unchecked")
	protected void onClickParagraphSequenceUp() {
		LinkedList<FseParagraphView> theParagraphViewList = null;
		switch(this.paragraphSequenceUpButton.getMultiShiftState()) {
		case OFF:
			theParagraphViewList = (LinkedList<FseParagraphView>) this.paragraphEditor.getParagraphViewSelectionList().clone();
			break;
		case CTL:
			break;
		case SHIFT:
			break;
		case ALT:
			theParagraphViewList = this.paragraphEditor.getParagraphViewDescendentList(this.paragraphEditor.getParagraphViewFocus());
			theParagraphViewList.add(0, this.paragraphEditor.getParagraphViewFocus());
			break;
//		case ALT_SHIFT:
//			break;
		default:
			theParagraphViewList = (LinkedList<FseParagraphView>) this.paragraphEditor.getParagraphViewSelectionList().clone();
		}
		if(theParagraphViewList != null && theParagraphViewList.size() > 0 && this.paragraphEditor.canSequenceListUp(theParagraphViewList)) {
			this.paragraphEditor.sequenceUpParagraphViewList(theParagraphViewList);
			this.paragraphEditor.resetParagraphViewSelectionList(theParagraphViewList);
			this.paragraphEditor.setParagraphSpinnerFocusLost();
			this.paragraphEditor.setParagraphSpinnerFocus(theParagraphViewList);
		}
		this.multiShiftController.resetState();
		this.paragraphEditor.resetAllParagraphSequenceState();
		updateSequenceModificationState();
		this.documentPerspectiveFlipper.onDocumentStateChange();
	}

	@SuppressWarnings("unchecked")
	protected void onClickParagraphSequenceDown() {
		LinkedList<FseParagraphView> theParagraphViewList = null;
		switch(this.paragraphSequenceDownButton.getMultiShiftState()) {
		case OFF:
			theParagraphViewList = (LinkedList<FseParagraphView>) this.paragraphEditor.getParagraphViewSelectionList().clone();
			break;
		case CTL:
			break;
		case SHIFT:
			break;
		case ALT:
			theParagraphViewList = this.paragraphEditor.getParagraphViewDescendentList(this.paragraphEditor.getParagraphViewFocus());
			theParagraphViewList.add(0, this.paragraphEditor.getParagraphViewFocus());
			break;
//		case ALT_SHIFT:
//			break;
		default:
			theParagraphViewList = (LinkedList<FseParagraphView>) this.paragraphEditor.getParagraphViewSelectionList().clone();
		}
		if(theParagraphViewList != null && theParagraphViewList.size() > 0 && this.paragraphEditor.canSequenceListDown(theParagraphViewList)) {
			this.paragraphEditor.sequenceDownParagraphViewList(theParagraphViewList);
			this.paragraphEditor.resetParagraphViewSelectionList(theParagraphViewList);
			this.paragraphEditor.setParagraphSpinnerFocusLost();
			this.paragraphEditor.setParagraphSpinnerFocus(theParagraphViewList);
		}
		this.multiShiftController.resetState();
		this.paragraphEditor.resetAllParagraphSequenceState();
		updateSequenceModificationState();
		this.documentPerspectiveFlipper.onDocumentStateChange();
	}
	
	///////  END Button Callbacks  ///////////
	
	@Override
	public GcgMultiShiftKeysetController getMultiShiftController() {
		return this.multiShiftController;
	}

	public void resetMultiShiftControllerState() {
		if(this.multiShiftController != null) {
			if(this.multiShiftController.getMultiShiftState() != GcgMultiShiftState.OFF) {
				this.multiShiftController.resetState();
				this.paragraphEditor.resetParagraphSpinnerSelectionMode();
			}
		}
	}

	public boolean isMultiShiftControllerParagraphSelectionMode() {
		if(this.multiShiftController != null) {
			return this.multiShiftController.isItemSelectionState();
		}
		return false;
	}

	@Override
	public boolean isMultipleSelections() {
		return this.paragraphEditor.isMultipleSelections();
	}

	@Override
	public ArrayList<GcgMultiShiftState> getSupportedMultiShiftStateList() {
		if(this.supportedMultiShiftStateList == null) {
			this.supportedMultiShiftStateList = new ArrayList<GcgMultiShiftState>(Arrays.asList(
				GcgMultiShiftState.OFF,
				GcgMultiShiftState.CTL,
				GcgMultiShiftState.SHIFT,
				GcgMultiShiftState.ALT,
				GcgMultiShiftState.CANCEL_SHIFT
			));
		}
		return this.supportedMultiShiftStateList;
	}

	@Override
	public ArrayList<FseMultiShiftButton> getShiftButtonList() {
		return this.multiShiftButtonList;
	}
	
	////////////////
	
	protected static void copyToDocumentSectionParagraphEditorContent(
			FseDocumentSectionParagraphEditorPerspective aDocumentSectionParagraphEditorContentView,
			FseDocumentSectionParagraphEditorContent aDocumentSectionParagraphEditorContent) {
		// TODO - can be done better?  SDS
		aDocumentSectionParagraphEditorContent.setInitialParagraphFocusId(aDocumentSectionParagraphEditorContentView.getInitialParagraphFocusId());
		aDocumentSectionParagraphEditorContent.setInitialParagraphFocusCursorPosition(aDocumentSectionParagraphEditorContentView.getInitialParagraphFocusCursorPosition());
		aDocumentSectionParagraphEditorContent.setSequenceModificationState(aDocumentSectionParagraphEditorContentView.getSequenceModificationState());
		aDocumentSectionParagraphEditorContent.setLockModificationState(aDocumentSectionParagraphEditorContentView.getLockModificationState());
		aDocumentSectionParagraphEditorContent.setContentModificationState(aDocumentSectionParagraphEditorContentView.getContentModificationState());
		aDocumentSectionParagraphEditorContent.setInsertModificationState(aDocumentSectionParagraphEditorContentView.getInsertModificationState());
		aDocumentSectionParagraphEditorContent.setNumberingModificationState(aDocumentSectionParagraphEditorContentView.getNumberingModificationState());
		aDocumentSectionParagraphEditorContent.setStyleModificationState(aDocumentSectionParagraphEditorContentView.getStyleModificationState());
		for(FseParagraphView theParagraphView : aDocumentSectionParagraphEditorContentView.getParagraphViewList()) {
			FseParagraph theFseParagraph = theParagraphView.generateFseParagraph();
			aDocumentSectionParagraphEditorContent.getParagraphList().put(theFseParagraph.getParagraphId(), theFseParagraph);
		}
	}
	
	public FseInsertModificationState getInsertModificationState() {
		return this.insertModificationState;
	}

	public FseContentModificationState getContentModificationState() {
		return this.contentModificationState;
	}
	
	public boolean isDocumentSectionModified() {
		return
			isStyleModified() ||
			isSequenceModified() ||
			isNumberingModified() ||
			isLockStateModified() ||
			isContentModified(); 
	}

	public boolean isContentModified() {
		return this.contentModificationState == FseContentModificationState.NEW || this.contentModificationState == FseContentModificationState.MODIFIED;
	}
	
	public FseLockModificationState getLockModificationState() {
		return this.lockModificationState;
	}

	public boolean isLockStateModified() {
		return this.lockModificationState != FseLockModificationState.UNCHANGED;
	}
	
	public FseStyleModificationState getStyleModificationState() {
		return this.styleModificationState;
	}

	public boolean isStyleModified() {
		return this.styleModificationState != FseStyleModificationState.UNCHANGED;
	}
	
	public FseSequenceModificationState getSequenceModificationState() {
		return this.sequenceModificationState;
	}

	public boolean isSequenceModified() {
		return this.sequenceModificationState != FseSequenceModificationState.UNCHANGED;
	}
	
	public FseNumberingModificationState getNumberingModificationState() {
		return this.numberingModificationState;
	}

	public boolean isNumberingModified() {
		return this.numberingModificationState != FseNumberingModificationState.UNCHANGED;
	}
	
	public void setAsNewBaseline() {
		String thePreviousParagraphNodeId = "";
		for(FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
			theParagraphView.setAsNewBaseline(thePreviousParagraphNodeId);
			thePreviousParagraphNodeId = theParagraphView.getParagraphId();
		}
		String theNextParagraphNodeId = "";
		ListIterator<FseParagraphView> theListIterator = this.paragraphEditor.getParagraphViewList().listIterator(this.paragraphEditor.getParagraphViewList().size());
		while(theListIterator.hasPrevious()) {
			FseParagraphView theParagraphView = theListIterator.previous(); 
			theParagraphView.setNextParagraphId(theNextParagraphNodeId);
			theParagraphView.setInitialNextParagraphId(theNextParagraphNodeId);
			theNextParagraphNodeId = theParagraphView.getParagraphId();
		}
		resetSectiontModificationState();
	}

	private void resetSectiontModificationState() {
		setSequenceModificationState(FseSequenceModificationState.UNCHANGED);
		setLockModificationState(FseLockModificationState.UNCHANGED);
		setContentModificationState(FseContentModificationState.UNCHANGED);
		setInsertModificationState(FseInsertModificationState.UNCHANGED);
		setNumberingModificationState(FseNumberingModificationState.UNCHANGED);
		setStyleModificationState(FseStyleModificationState.UNCHANGED);
	}
	
	public void enableModificationStateUpdates(boolean aBoolean) {
		this.doNotChangeModificationState = ! aBoolean;
	}

	public boolean disableModificationStateUpdates() {
		return this.doNotChangeModificationState;
	}

	public FseDocumentSectionParagraphAudit getParagraphAudit() {
		FseDocumentSectionParagraphAudit theParagraphAudit = new FseDocumentSectionParagraphAudit(this.getSectionType());
		theParagraphAudit.setParagraphsNewCount(getParagraphsNewCount());
		theParagraphAudit.setParagraphsModifiedCount(getParagraphsContentModifiedCount());
		theParagraphAudit.setParagraphsUnchangedCount(getParagraphsUnchangedCount());
		theParagraphAudit.setParagraphsLockedCount(getParagraphsLockedCount());
		theParagraphAudit.setParagraphsUnlockedCount(getParagraphsUnlockedCount());
		theParagraphAudit.setParagraphsDeletedCount(getParagraphsDeletedCount());
		return theParagraphAudit;
	}

	private Hashtable<String, FseParagraphView> getParagraphViewHashtable() {
		Hashtable<String, FseParagraphView> theParagraphViewHashtable = new Hashtable<String, FseParagraphView>();
		for(FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
			theParagraphViewHashtable.put(theParagraphView.getParagraphId(), theParagraphView);
		}
		return theParagraphViewHashtable;
	}

	private int getParagraphsUnchangedCount() {
		int theCount = 0;
		for(FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
			if(theParagraphView.getContentModificationState() == FseContentModificationState.UNCHANGED) {
				++ theCount;
			}
		}
		return theCount;
	}

	private int getParagraphsNewCount() {
		int theCount = 0;
		for(FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
			if(theParagraphView.getContentModificationState() == FseContentModificationState.NEW) {
				++ theCount;
			}
		}
		return theCount;
	}

	private int getParagraphsContentModifiedCount() {
		int theCount = 0;
		for(FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
			if(theParagraphView.getContentModificationState() == FseContentModificationState.MODIFIED) {
				++ theCount;
			}
		}
		return theCount;
	}

	public int getParagraphsLockedCount() {
		LinkedHashMap<String, FseParagraph> theBaselineDocumentParagraphList =
				getDocumentPerspectiveFlipper().getBaselineDocument().getParagraphList(getSectionType());
		int theCount = 0;
		for(FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
			if(theParagraphView.getLockStatus() == FmmLockStatus.LOCKED) {
				FseParagraph theBaselineParagraph = theBaselineDocumentParagraphList.get(theParagraphView.getParagraphId());
				if(theBaselineParagraph == null || theBaselineParagraph.getLockStatus() != FmmLockStatus.LOCKED) {
					++theCount;
				}
			}
		}
		return theCount;
	}

	public int getParagraphsUnlockedCount() {
		LinkedHashMap<String, FseParagraph> theBaselineDocumentParagraphList =
				getDocumentPerspectiveFlipper().getBaselineDocument().getParagraphList(getSectionType());
		int theCount = 0;
		for(FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
			if(theParagraphView.getLockModificationState() == FseLockModificationState.MODIFIED &&
					! theParagraphView.getNodeFragAuditBlock().isLocked()) {
				FseParagraph theBaselineParagraph = theBaselineDocumentParagraphList.get(theParagraphView.getParagraphId());
				if(theBaselineParagraph == null || theBaselineParagraph.isLocked() ) {
					++theCount;
				}
			}
		}
		return theCount;
	}

	public int getParagraphsDeletedCount() {
		LinkedHashMap<String, FseParagraph> theBaselineDocumentParagraphList =
				getDocumentPerspectiveFlipper().getBaselineDocument().getParagraphList(getSectionType());
		Hashtable<String, FseParagraphView> theParagraphViewHashtable = getParagraphViewHashtable();
		int theCount = 0;
		for(FseParagraph theParagraph : theBaselineDocumentParagraphList.values()) {
			if(! theParagraphViewHashtable.containsKey(theParagraph.getParagraphId())) {
				++theCount;
			}
		}
		return theCount;
	}
	
	////

	public void updateLockModificationState() {
		setLockModificationState(determineParagraphLockModificationState());
	}

	public void updateLockModificationState(FseLockModificationState aModificationState) {
		if(this.lockModificationState == aModificationState) {
			return;
		}
		setLockModificationState(aModificationState == FseLockModificationState.MODIFIED ? aModificationState : determineParagraphLockModificationState());
		onDataStateChange();
	}

	public void setLockModificationState(FseLockModificationState aModificationState) {
		if(this.lockModificationState == aModificationState) {
			return;
		}
		this.lockModificationState = aModificationState;
		this.lockModificationImage.setImageDrawable(aModificationState.getSummaryDrawable());
	}

	public FseLockModificationState determineParagraphLockModificationState() {
		for(FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
			if(theParagraphView.isLockModified()) {
				return FseLockModificationState.MODIFIED;
			}
		}
		return FseLockModificationState.UNCHANGED;
	}
	
	////

	public void updateContentModificationState() {
		setContentModificationState(determineParagraphContentModificationState());
	}
	
	public void updateContentModificationState(FseContentModificationState aModificationState) {
		if(this.contentModificationState == aModificationState) {
			return;
		}
		setContentModificationState(aModificationState == FseContentModificationState.MODIFIED ? aModificationState : determineParagraphContentModificationState());
		onDataStateChange();
	}
	
	public void setContentModificationState(FseContentModificationState aModificationState) {
		if(this.contentModificationState == aModificationState || aModificationState == FseContentModificationState.NEW) {
			return;
		}
		this.contentModificationState = aModificationState;
		this.contentModificationImage.setImageDrawable(aModificationState.getSummaryDrawable());
	}

	public FseContentModificationState determineParagraphContentModificationState() {
		for(FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
			if(theParagraphView.isContentModified()) {
				return FseContentModificationState.MODIFIED;
			}
		}
		return FseContentModificationState.UNCHANGED;
	}
	
	////

	private void onDataStateChange() {
		this.documentPerspectiveFlipper.onDocumentStateChange();
	}
	
	////

	public void updateStyleModificationState() {
		setStyleModificationState(determineParagraphStyleModificationState());
	}
	
	public void updateStyleModificationState(FseStyleModificationState aModificationState) {
		if(this.styleModificationState == aModificationState) {
			return;
		}
		setStyleModificationState(aModificationState == FseStyleModificationState.MODIFIED ? aModificationState : determineParagraphStyleModificationState());
		onDataStateChange();
	}
	
	public void setStyleModificationState(FseStyleModificationState aModificationState) {
		if(this.styleModificationState == aModificationState) {
			return;
		}
		this.styleModificationState = aModificationState;
		this.styleModificationImage.setImageDrawable(aModificationState.getSummaryDrawable());
	}

	public FseStyleModificationState determineParagraphStyleModificationState() {
		for(FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
			if(theParagraphView.isStyleModified()) {
				return FseStyleModificationState.MODIFIED;
			}
		}
		return FseStyleModificationState.UNCHANGED;
	}
	
	////

	public void updateSequenceModificationState() {
		setSequenceModificationState(determineParagraphSequenceModificationState());
	}
	
	public void updateSequenceModificationState(FseSequenceModificationState aModificationState) {
		if(this.sequenceModificationState == aModificationState) {
			return;
		}
		setSequenceModificationState(aModificationState != FseSequenceModificationState.UNCHANGED ? aModificationState : determineParagraphSequenceModificationState());
		onDataStateChange();
	}
	
	public void setSequenceModificationState(FseSequenceModificationState aModificationState) {
		if(this.sequenceModificationState == aModificationState && ! isBrowserMode()) {
			return;
		}
		this.sequenceModificationState = aModificationState;
		this.sequenceModificationImage.setImageDrawable(aModificationState.getSummaryDrawable());
	}

	public FseSequenceModificationState determineParagraphSequenceModificationState() {
		for(FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
			if(theParagraphView.isSequenceModified()) {
				return FseSequenceModificationState.MOVE_UP;
			}
		}
		return FseSequenceModificationState.UNCHANGED;
	}
	
	////

	public void updateNumberingModificationState() {
		setNumberingModificationState(determineParagraphNumberingModificationState());
	}
	
	public void updateNumberingModificationState(FseNumberingModificationState aModificationState) {
		if(this.numberingModificationState == aModificationState) {
			return;
		}
		setNumberingModificationState(aModificationState == FseNumberingModificationState.MODIFIED ? aModificationState : determineParagraphNumberingModificationState());
		onDataStateChange();
	}
	
	public void setNumberingModificationState(FseNumberingModificationState aModificationState) {
		if(this.numberingModificationState == aModificationState) {
			return;
		}
		this.numberingModificationState = aModificationState;
		this.numberingModificationImage.setImageDrawable(aModificationState.getSummaryDrawable());
	}

	public FseNumberingModificationState determineParagraphNumberingModificationState() {
		for(FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
			if(theParagraphView.isNumberingModified()) {
				return FseNumberingModificationState.MODIFIED;
			}
		}
		return FseNumberingModificationState.UNCHANGED;
	}
	
	////

	public void updateInsertModificationState() {
		setInsertModificationState(determineParagraphInsertModificationState());
	}
	
	public void updateInsertModificationState(FseInsertModificationState aModificationState) {
		if(this.insertModificationState == aModificationState) {
			return;
		}
		setInsertModificationState(aModificationState == FseInsertModificationState.MODIFIED ? aModificationState : determineParagraphInsertModificationState());
		onDataStateChange();
	}
	
	public void setInsertModificationState(FseInsertModificationState aModificationState) {
		if(this.insertModificationState == aModificationState) {
			return;
		}
		this.insertModificationState = aModificationState;
		this.insertModificationImage.setImageDrawable(aModificationState.getDrawable());
	}

	public FseInsertModificationState determineParagraphInsertModificationState() {
		for(FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
			if(theParagraphView.isInsertModified()) {
				return FseInsertModificationState.MODIFIED;
			}
		}
		return FseInsertModificationState.UNCHANGED;
	}
	
	////

	@Override
	protected void deactivateView() {
		super.deactivateView();
		this.paragraphEditor.updateInitialFocus();
	}

	public ScrollView getScrollView() {
		return this.scrollView;
	}

	public void leftMenuActivateSpinner() {
		getDocumentPerspectiveFlipper().getFmsNodeActivity().leftMenuClickSpinner();
	}

	@Override
	public void rightMenuActivateSpinner() {
		this.rightSpinnableMenu.clickSpinner();
	}

	public void rightMenuClickItem(int anItemNumber) {
		RelativeLayout theButtonList = null;
		switch (this.rightSpinnableMenu.getActiveMenuIndex()) {
			case 0:
				theButtonList = (RelativeLayout) findViewById(R.id.fse__style_menu__body);
				break;
			case 1:
				theButtonList = (RelativeLayout) findViewById(R.id.fse__sequence_menu__body);
				break;
			case 2:
				theButtonList = (RelativeLayout) findViewById(R.id.fse__number_menu__body);
				break;
			case 3:
				theButtonList = (RelativeLayout) findViewById(R.id.fse__insert_menu__body);
				break;
			case 4:
				theButtonList = (RelativeLayout) findViewById(R.id.fse__lock_menu__body);
				break;
			default:
				return;
		}
		if(anItemNumber > theButtonList.getChildCount()) {
			return;
		}
		((Button) theButtonList.getChildAt(anItemNumber - 1)).performClick();
	}

	public boolean isBrowserMode() {
		return this.isBrowserMode;
	}

	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(FseDocumentSerialization.key__BROWSER_MODE, this.isBrowserMode);
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_SECTION__INITIAL_PARAGRAPH_FOCUS, this.paragraphEditor.getInitialParagraphFocusId());
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_SECTION__INITIAL_CURSOR_POSITION, this.paragraphEditor.getInitialParagraphFocusCursorPosition());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__LOCK_MODIFICATION_STATE, this.lockModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__INSERT_MODIFICATION_STATE, this.insertModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__CONTENT_MODIFICATION_STATE, this.contentModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__STYLE_MODIFICATION_STATE, this.styleModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__SEQUENCE_MODIFICATION_STATE, this.sequenceModificationState.toString());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__NUMBERING_MODIFICATION_STATE, this.numberingModificationState.toString());
			JSONArray theJsonArray = new JSONArray();
			for (FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
				theJsonArray.put(theParagraphView.getJsonObject());
			}
			theJsonObject.put(FseDocumentSerialization.key__PARAGRAPH_SECTION__PARAGRAPH_ARRAY, theJsonArray);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}

	@Override
	public void fdkFocusNextConsumer() {
		getFseParagraphEditor().moveParagraphFocusToNext();
	}

	@Override
	public void fdkFocusPreviousConsumer() {
		getFseParagraphEditor().moveParagraphFocusToPrevious();
	}
	
}