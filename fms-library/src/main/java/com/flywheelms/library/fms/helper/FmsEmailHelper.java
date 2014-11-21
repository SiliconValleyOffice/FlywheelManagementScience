/* @(#)FmsEmailHelper.java
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

package com.flywheelms.library.fms.helper;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.flywheelms.gcongui.gcg.GcgApplication;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fms.activity.FmsActivity;

import java.io.File;

public class FmsEmailHelper {

	public static void sendMail(
			Activity anActivity,
			String[] anEmailAddressList,
			String aSubjectLine,
			String aMessageBody,
			File aPdfFile ) {
		Intent theIntent = new Intent(android.content.Intent.ACTION_SEND);
		theIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		theIntent.putExtra(android.content.Intent.EXTRA_EMAIL, anEmailAddressList);
		theIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, aSubjectLine);
		theIntent.putExtra(android.content.Intent.EXTRA_TEXT, aMessageBody);
		Uri theAttachmentUri = Uri.fromFile(aPdfFile);
		theIntent.putExtra(Intent.EXTRA_STREAM, theAttachmentUri);
		theIntent.setType("application/pdf");
		anActivity.startActivity(Intent.createChooser(theIntent, "Send mail..."));
	}

	public static String getStandardNodeSubjectLine(FmmHeadlineNodeImpl anFmmHeadlineNode) {
		return 
				GcgApplication.getInstance().getResources().getString(R.string.flywheel_ms) +
				" publication of " + anFmmHeadlineNode.getNodeTypeName() + " : " + anFmmHeadlineNode.getHeadline();
	}

	public static String getStandardMessageBodyPreamble(FmmHeadlineNodeImpl anFmmHeadlineNode) {
		StringBuilder theStringBuilder = new StringBuilder();
		theStringBuilder.append("Organization:  " + FmsTextHelper.NEW_LINE + FmsTextHelper.INDENT +
				FmsActivity.getActiveDatabaseMediator().getFmmOwner().getName() + FmsTextHelper.NEW_LINE );
		theStringBuilder.append(
				anFmmHeadlineNode.getNodeTypeName() + FmsTextHelper.NEW_LINE + FmsTextHelper.INDENT +
				anFmmHeadlineNode.getHeadline() + FmsTextHelper.NEW_LINE + FmsTextHelper.INDENT +
				GcgApplication.getInstance().getResources().getString(R.string.fmm_node_id) +
				" = " + anFmmHeadlineNode.getNodeId().getAbbreviatedNodeIdString() + FmsTextHelper.NEW_LINE + FmsTextHelper.NEW_LINE );
		return theStringBuilder.toString();
	}

}
