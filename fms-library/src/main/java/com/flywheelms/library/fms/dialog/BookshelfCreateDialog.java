/* @(#)PortfolioCreateDialog.java
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

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;

import com.flywheelms.gcongui.gcg.activity.GcgActivity;
import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.Bookshelf;
import com.flywheelms.library.fms.treeview.filter.FmsTreeViewAdapter;
import com.flywheelms.library.fms.widget.edit_text.HeadlineWidgetEditText;
import com.flywheelms.library.fms.widget.text_view.FmmNodeTypeWidgetTextView;

public class BookshelfCreateDialog extends FmsCancelOkApplyFdkDialog {

	FmsTreeViewAdapter treeViewAdapter;
	protected FmmNodeTypeWidgetTextView fmmNodeTypeWidget;
    protected HeadlineWidgetEditText headlineWidget;
	protected CheckBox editNewFmmNode;

	public BookshelfCreateDialog(GcgActivity aLibraryActivity, FmsTreeViewAdapter aTreeViewAdapter) {
		super(aLibraryActivity, FmmNodeDefinition.BOOKSHELF);
		this.treeViewAdapter = aTreeViewAdapter;
        initializeDialogBodyLate();
        initFdkHostSupport();
        manageButtonState();
	}

	@Override
	protected int getDialogTitleStringResourceId() {
		return R.string.fms__create;
	}

    @Override
    protected int getCustomDialogContentsResourceId() {
        return R.layout.root_tree_node__create__dialog;
    }

    @Override
    protected void initializeDialogBody() {
        return;
    }

    protected void initializeDialogBodyLate() {
		super.initializeDialogBody();
		this.fmmNodeTypeWidget = (FmmNodeTypeWidgetTextView) this.dialogBodyView.findViewById(R.id.fmm_node__type);
		this.fmmNodeTypeWidget.setText(getFmmNodeDefinition().getLabelTextResourceId());
        this.headlineWidget = (HeadlineWidgetEditText) this.dialogBodyView.findViewById(R.id.headline);
        this.headlineWidget.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                return;
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                return;
            }

            @Override
            public void afterTextChanged(Editable s) {
                BookshelfCreateDialog.this.manageButtonState();
            }
        });
		this.editNewFmmNode = (CheckBox) this.dialogBodyView.findViewById(R.id.edit_new_fmm_node);
        this.editNewFmmNode.setText("Edit new " + getFmmNodeDefinition().getLabelText());
	}

	@Override
	protected void manageButtonState() {
        if(this.buttonApply == null) {  // to support late initialization
            return;
        }
        this.buttonApply.setVisibility(isMinimumInput() ? View.VISIBLE : View.INVISIBLE);
        this.buttonOk.setVisibility(isMinimumInput() ? View.VISIBLE : View.INVISIBLE);
	}

	protected boolean isMinimumInput() {
        return this.headlineWidget.isMinimumInput();
	}

	@Override
	protected void onClickButtonApply() {
        createBookshelf();
        this.headlineWidget.setText("");
	}

    private void createBookshelf() {
        Bookshelf theBookshelf =
                FmmDatabaseMediator.getActiveMediator().createBookshelf(this.headlineWidget.getData());
        if(theBookshelf != null) {
            this.treeViewAdapter.addNewHeadlineNode(theBookshelf);
            GcgHelper.makeToast(this.fmmNodeTypeWidget.getText() + " created: " + this.headlineWidget.getData());
            manageButtonState();
        } else {
            GcgHelper.makeToast("ERROR:  Unable to create " + this.fmmNodeTypeWidget.getText() + " " + this.headlineWidget.getData());
        }
    }

    @Override
	protected void onClickButtonOk() {
        createBookshelf();
		this.gcgActivity.stopDialog();
	}

    @Override
    public void initFdkDictationResultsConsumerMap() {
        addFdkDictationResultsConsumer(this.headlineWidget);
        this.currentFdkDictationResultsConsumer = this.headlineWidget;
        fdkFocusConsumer(this.currentFdkDictationResultsConsumer);
    }
}
