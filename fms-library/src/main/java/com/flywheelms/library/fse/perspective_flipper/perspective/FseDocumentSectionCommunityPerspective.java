/* @(#)FseDocumentSectionCommunityPerspective.java
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

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.listeners.FmsNodeActivityGenericKeyListener;
import com.flywheelms.library.fms.view.FmsAuditBlockView;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.model.FseCollaboratorSummary;
import com.flywheelms.library.fse.model.FseDocumentSection;
import com.flywheelms.library.fse.model.FseDocumentSectionCollaborators;
import com.flywheelms.library.fse.model.FseDocumentTransactionType;
import com.flywheelms.library.fse.perspective_flipper.FsePerspectiveFlipper;
import com.flywheelms.library.fse.views.FseCollaboratorSummaryView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

public class FseDocumentSectionCommunityPerspective extends FseDocumentSectionPerspective {
	
	private GcgPerspective gcgPerspective = FmmPerspective.COMMUNITY;

	@Override
	public GcgPerspective getGcgPerspective() {
		return this.gcgPerspective;
	}
	
	private LinearLayout documentSectionLayout;
	private FseDocumentSectionCollaborators documentSectionCollaborators;
	private FmsAuditBlockView auditBlockView;
	private FseCollaboratorSummaryView collaboratorSummaryView;
	protected EditText hiddenInput;
	
	public FseDocumentSectionCommunityPerspective(Context context, AttributeSet attrs) {
		super(context, attrs);
		setSectionType(FseDocumentSectionType.COMMUNITY);
		this.leftMenuSpacerResourceId = R.drawable.left_menu__filler_3;
		initializeHiddenInput();
	}

	@Override
	protected void initLocalFdkKeyboard() {
		return;
	}

	private void initializeHiddenInput() {
		this.hiddenInput = (EditText) findViewById(R.id.hidden_input);
		this.hiddenInput.setKeyListener(new FmsNodeActivityGenericKeyListener(getDocumentPerspectiveFlipper(), true));
//		this.hiddenInput.setOnFocusChangeListener(new OnFocusChangeListener() {
//			
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				if(! FseDocumentSectionCollaboratorsView.this.hiddenInput.hasFocus()) {
//					FseDocumentSectionCollaboratorsView.this.hiddenInput.requestFocus();
//				}
//			}
//		});
	}

	@Override
	public void fdkDictationResults(ArrayList<String> aWordList) {
		return;
	}

	@Override
	protected int getPageTitleResourceId() {
		return R.string.fse__document_section__community;
	}

	@Override
	protected int getViewLayoutResourceId() {
		return R.layout.fms_view__document_section__community;
	}
	
	@Override
	protected void initialize(FsePerspectiveFlipper aDocumentView) {
		super.initialize(aDocumentView);
		initializeHiddenInput();
		setDocumentSectionLayout();
//		sizeLayoutToDisplay();  // HACK ALERT - SDS
	}

//	private void sizeLayoutToDisplay() {
//		RelativeLayout theRelativeLayout = (RelativeLayout) findViewById(R.id.fse__collaborators_view__body);
//		LinearLayout.LayoutParams params =
//				(LinearLayout.LayoutParams) theRelativeLayout.getLayoutParams();
//		int theTabRowHeight = GcgHelper.getPixelsForDp(GcgApplication.getContext(), 100);
////		int theTargetSize = GcgHelper.getPixelsForDp(GcgApplication.getContext(), 394);
//		int theOtherViewHeights = GcgHelper.getPixelsForDp(GcgApplication.getContext(), 26);
//		int theLayoutHeight = GcgHelper.getScreenHeight() - (theTabRowHeight + theOtherViewHeights) + 4;
//		params.height = theLayoutHeight;
//		theRelativeLayout.setLayoutParams(params);
//	}

//	@Override
//	public void activateDocumentSectionView() {
//		super.activateDocumentSectionView();
//		FrameLayout theParentFrameLayout = (FrameLayout) getParent();
//		theParentFrameLayout.bringChildToFront(this.documentSectionLayout);
//		theParentFrameLayout.invalidate();
//		this.tabControlAuditButton.requestFocus();
//	}
	
	public FseDocumentSectionCollaborators getDocumentSectionCollaborators() {
		return this.documentSectionCollaborators;
	}

	public void setDocumentSectionParticipation(FseDocumentSectionCollaborators documentSectionParticipation) {
		this.documentSectionCollaborators = documentSectionParticipation;
	}

	private NodeFragAuditBlock getAuditBlock() {
		return this.documentSectionCollaborators.getAuditBlock();
	}

	@Override
	public String getHelpContextUrlString() {
		return FmsHelpIndex.FSE__DOCUMENT_SECTION__AUDIT;
	}

	@Override
	public ViewGroup getDocumentSectionLayout() {
		return this.documentSectionLayout;
	}

	@Override
	public void setDocumentSectionLayout() {
				inflate(this.context, getDocumentSectionViewResourceId(), null);
//				(LinearLayout) this.documentView.getGcgActivity().findViewById(getDocumentSectionViewResourceId());
		this.auditBlockView = 
				(FmsAuditBlockView) findViewById(R.id.fms__audit_block__view);
		this.collaboratorSummaryView = 
				(FseCollaboratorSummaryView) findViewById(R.id.fse__collaborator_summary__view);
	}

	@Override
	public
	void viewDocumentSection(FseDocumentSection aDocumentSection) {
		this.documentSectionCollaborators = (FseDocumentSectionCollaborators) aDocumentSection;
		this.auditBlockView.viewAuditBlock(this.documentSectionCollaborators.getAuditBlock());
		this.collaboratorSummaryView.viewCollaboratorSummary(this.documentSectionCollaborators.getCollaboratorSummary());
	}

	@Override
	protected String sectionIsModified() {
		return null;
	}

	public FseDocumentSectionCollaborators getDocumentSectionClone() {
		FseDocumentSectionCollaborators theDocumentSectionCollaborators = new FseDocumentSectionCollaborators(
				getAuditBlock().getClone(), new FseCollaboratorSummary(getDocumentPerspectiveFlipper().getHistorySectionPerspective().getDocumentSectionHistory()) );
		return theDocumentSectionCollaborators.getClone();
	}

	public void updateAuditBlock(Date aTimestamp, FseDocumentTransactionType aDocumentTransactionType) {
		this.auditBlockView.update(aTimestamp, aDocumentTransactionType);
	}
	
	public FseCollaboratorSummaryView getCollaboratorSummaryView() {
		return this.collaboratorSummaryView;
	}
	
	@Override
	protected void activateView() {
		super.activateView();
		this.hiddenInput.requestFocus();
	}

	public JSONObject getJsonObject() {
		return this.documentSectionCollaborators.getJsonObject();
	}

	@Override
	public int getFrameMenuSpacerBackgroundResourceId() {
		return R.color.gcg__activity_background_1;
	}

	@Override
	protected void viewDocumentSectionAsHistory(
			FseDocumentSection aDocumentSection) { /* N/A */ }

}
