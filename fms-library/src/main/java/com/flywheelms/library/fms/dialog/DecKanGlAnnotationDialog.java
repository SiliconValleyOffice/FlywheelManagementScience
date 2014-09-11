/* @(#)DecKanGlAnnotationDialog.java
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

package com.flywheelms.library.fms.dialog;

import android.widget.ImageView;

import com.flywheelms.library.R;
import com.flywheelms.library.deckangl.enumerator.DecKanGlAnnotatedGlyphSize;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmHeadlineNode;
import com.flywheelms.library.fms.widget.text_view.FmmNodeTypeWidgetTextView;
import com.flywheelms.library.fms.widget.text_view.HeadlineWidgetTextView;
import com.flywheelms.library.gcg.GcgActivity;

public class DecKanGlAnnotationDialog extends FmsCancelDialog {
	
	public DecKanGlAnnotationDialog(GcgActivity aGcgActivity, FmmHeadlineNode aHeadlineNode) {
		super(aGcgActivity, aHeadlineNode);
	}

	@Override
	protected int getDialogTitleStringResourceId() {
		return R.string.deckangl_tm__annotated_glyph;
	}

	@Override
	protected int getDialogTitleIconResourceId() {
		return R.drawable.deckangl;
	}

	@Override
	protected void initializeDialogBody() {
		super.initializeDialogBody();
		FmmNodeTypeWidgetTextView theFmmNodeTypeWidgetTextView = (FmmNodeTypeWidgetTextView) this.dialogBodyView.findViewById(R.id.fmm_node__type);
		theFmmNodeTypeWidgetTextView.setText(this.headlineNode.getNodeTypeName());
		HeadlineWidgetTextView theHeadlineWidget = (HeadlineWidgetTextView) this.dialogBodyView.findViewById(R.id.headline);
		theHeadlineWidget.setText(this.headlineNode.getHeadline());
		ImageView theImageView = (ImageView) this.dialogBodyView.findViewById(R.id.annotated_deckangl);
		theImageView.setImageBitmap(this.headlineNode.getDecKanGlGlyph().getAnnotatedGlyphBitmap(DecKanGlAnnotatedGlyphSize.MEDIUM));
	}

	@Override
	protected int getCustomDialogContentsResourceId() {
		return R.layout.deckangl__annotation__dialog;
	}

}
