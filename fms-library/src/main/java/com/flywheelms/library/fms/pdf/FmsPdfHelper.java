/* @(#)FmsPdfHelper.java
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

package com.flywheelms.library.fms.pdf;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fms.helper.FmsFileHelper;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublication;
import com.flywheelms.library.fms.wizard_step_flipper.step.FmsNodePublishingContentSelectionWizardStepView;
import com.flywheelms.library.gcg.helper.GcgHelper;
import com.itextpdf.text.DocumentException;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public class FmsPdfHelper {

	private static final String TAG = FmsPdfHelper.class.getCanonicalName();

	public static File generatePdfFile(FmsNodePublishingContentSelectionWizardStepView aContentSelectionStepView, String anFmmNodeIdString, String anAbbreviatedNodeIdString) {
		try {
			FmmHeadlineNodeImpl headlineNode = FmmDatabaseMediator.getActiveMediator().getHeadlineNode(anFmmNodeIdString);
			ByteArrayOutputStream theDocumentStream = new HeadlineNodePublication(headlineNode, aContentSelectionStepView).buildPdf();
			theDocumentStream.close();
			return FmsFileHelper.writeFileToDownloadsDirectory(anAbbreviatedNodeIdString + ".pdf", theDocumentStream);
		} catch (DocumentException e) {
			Log.e(TAG, "Failed to generate PDF.", e);
		} catch (IOException e) {
			Log.e(TAG, "Failed to generate PDF.", e);
		}
		return null;
	}

	public static void viewPdfFile(Context aContext, File aFile) {
		Intent theIntent = new Intent(Intent.ACTION_VIEW);
		theIntent.setDataAndType(Uri.fromFile(aFile), "application/pdf");
		theIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		try {
			aContext.startActivity(theIntent);
		} catch (ActivityNotFoundException e) {
			GcgHelper.makeToast("No Application Available to View PDF");
		}
	}

}
