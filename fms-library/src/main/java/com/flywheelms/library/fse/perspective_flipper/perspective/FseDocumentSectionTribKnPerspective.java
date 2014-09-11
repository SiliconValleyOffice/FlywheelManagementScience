/* @(#)FseDocumentSectionTribKnPerspective.java
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flywheelms.library.R;
import com.flywheelms.library.deckangl.enumerator.DecKanGlAnnotatedGlyphSize;
import com.flywheelms.library.fmm.context.FmmPerspective;
import com.flywheelms.library.fms.helper.FmsHelpIndex;
import com.flywheelms.library.fms.view.FmsGovernanceView;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.model.FseDocumentSection;
import com.flywheelms.library.fse.model.FseDocumentSectionTribKn;
import com.flywheelms.library.fse.perspective_flipper.FsePerspectiveFlipper;
import com.flywheelms.library.gcg.GcgApplication;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.flywheelms.library.gcg.interfaces.GcgPerspective;

import java.util.ArrayList;

public class FseDocumentSectionTribKnPerspective extends FseDocumentSectionPerspective {
	
	private LinearLayout documentSectionLayout;
	private FseDocumentSectionTribKn documentSectionTribKn;
	private TextView headlineView;
	private ImageView decKanGlImageView;
	private FmsGovernanceView governanceView;

	public FseDocumentSectionTribKnPerspective(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
		setSectionType(FseDocumentSectionType.TRIBKN);
		this.leftMenuSpacerResourceId = R.drawable.left_menu__filler_2;
	}

	@Override
	protected int getPageTitleResourceId() {
//		return R.string.fse__document_section__tribkn;
		return 0;
	}

	@Override
	protected int getViewLayoutResourceId() {
//		return R.layout.fms_view__document_section__tribkn;
		return 0;
	}

	@Override
	protected void initLocalFdkKeyboard() {
		return;
	}

	@Override
	public void fdkDictationResults(ArrayList<String> aWordList) {
		return;
	}
	
	@Override
	protected void initialize(FsePerspectiveFlipper aDocumentView) {
		super.initialize(aDocumentView);
		setDocumentSectionLayout();
		sizeLayoutToDisplay();  // HACK ALERT - SDS
	}

	private void sizeLayoutToDisplay() {  // HACK ALERT
		RelativeLayout theRelativeLayout = (RelativeLayout) findViewById(R.id.fse__tribkn_view__body);
		LinearLayout.LayoutParams params =
				(LinearLayout.LayoutParams) theRelativeLayout.getLayoutParams();
		int theTabRowHeight = GcgHelper.getPixelsForDp(GcgApplication.getContext(), 100);
//		int theTargetSize = GcgHelper.getPixelsForDp(GcgApplication.getContext(), 394);
		int theOtherViewHeights = GcgHelper.getPixelsForDp(GcgApplication.getContext(), 26);
		int theLayoutHeight = GcgHelper.getScreenHeight() - (theTabRowHeight + theOtherViewHeights) + 4;
		params.height = theLayoutHeight;
		theRelativeLayout.setLayoutParams(params);
	}

	@Override
	public String getHelpContextUrlString() {
		return FmsHelpIndex.FSE__DOCUMENT_SECTION__TRIBKN;
	}

	@Override
	public ViewGroup getDocumentSectionLayout() {
		return this.documentSectionLayout;
	}

	@Override
	public void setDocumentSectionLayout() {
				inflate(this.context, getDocumentSectionViewResourceId(), null);
//				(LinearLayout) this.documentView.findViewById(getDocumentSectionViewResourceId());
		this.headlineView = 
				(TextView) findViewById(R.id.tribkn__headline__data);
		this.governanceView = 
				(FmsGovernanceView) findViewById(R.id.fms__governance__view);
		this.decKanGlImageView = (ImageView) findViewById(R.id.deckangl_glyph_image);
	}

	@Override
	public
	void viewDocumentSection(FseDocumentSection aDocumentSection) {
		this.documentSectionTribKn = (FseDocumentSectionTribKn) aDocumentSection;
		this.headlineView.setText(this.documentSectionTribKn.getHeadline());
		this.decKanGlImageView.setImageBitmap(this.documentSectionTribKn.getDecKanGlGlyph().getAnnotatedGlyphBitmap(DecKanGlAnnotatedGlyphSize.MEDIUM));
		this.governanceView.viewGovernance(this.documentSectionTribKn.getNodeFragGovernance());
	}

	@Override
	protected String sectionIsModified() {
		return null;
	}

	public FseDocumentSectionTribKn getDocumentSectionClone() {
		return this.documentSectionTribKn.getClone();
	}

	public void setDocumentSectionTribKn(FseDocumentSectionTribKn documentSectionTribKn) {
		this.documentSectionTribKn = documentSectionTribKn;
	}

	@Override
	protected void viewDocumentSectionAsHistory(
			FseDocumentSection aDocumentSection) { /* N/A */ }
	
	@Override
	public GcgPerspective getGcgPerspective() {
		return FmmPerspective.TRIBKN;
	}

}
