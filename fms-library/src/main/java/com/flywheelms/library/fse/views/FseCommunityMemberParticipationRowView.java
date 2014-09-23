/* @(#)FseCommunityMemberParticipationRowView.java
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

package com.flywheelms.library.fse.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flywheelms.gcongui.gcg.helper.GcgHelper;
import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.R;
import com.flywheelms.library.fca.FlywheelCommunityAuthentication;
import com.flywheelms.library.fse.model.FseCommunityMemberCollaborationSummary;

public class FseCommunityMemberParticipationRowView extends LinearLayout {
	
	FseCommunityMemberCollaborationSummary communityMemberParticipation;

	public FseCommunityMemberParticipationRowView(Context aContext, AttributeSet anAttributeSet) {
		super(aContext, anAttributeSet);
	}

	public void initialize(FseCommunityMemberCollaborationSummary aCommunityMemberParticipation) {
		this.communityMemberParticipation = aCommunityMemberParticipation;
		((TextView) findViewById(R.id.fse__contribution__community_member__data)).setText(
				(FlywheelCommunityAuthentication.getInstance().getCommunityMember(aCommunityMemberParticipation.getCommunityMemberNodeIdString())).getName());
		((TextView) findViewById(R.id.fse__contribution__first_timestamp__data)).setText(GcgDateHelper.getGuiDateString2(aCommunityMemberParticipation.getFirstContributionTimestamp()));
		((TextView) findViewById(R.id.fse__contribution__last_timestamp__data)).setText(GcgDateHelper.getGuiDateString2(aCommunityMemberParticipation.getLastContributionTimestamp()));
		((TextView) findViewById(R.id.fse__contribution__paragraph_new__data__story)).setText(getEmptyStringForZero(aCommunityMemberParticipation.getCountStoryParagraphsNew()));
		((TextView) findViewById(R.id.fse__contribution__paragraph_modified__data__story)).setText(getEmptyStringForZero(aCommunityMemberParticipation.getCountStoryParagraphsModified()));
		((TextView) findViewById(R.id.fse__contribution__paragraph_deleted__data__story)).setText(getEmptyStringForZero(aCommunityMemberParticipation.getCountStoryParagraphsDeleted()));
		((TextView) findViewById(R.id.fse__contribution__paragraph_new__data__notes)).setText(getEmptyStringForZero(aCommunityMemberParticipation.getCountNotesParagraphsNew()));
		((TextView) findViewById(R.id.fse__contribution__paragraph_modified__data__notes)).setText(getEmptyStringForZero(aCommunityMemberParticipation.getCountNotesParagraphsModified()));
		((TextView) findViewById(R.id.fse__contribution__paragraph_deleted__data__notes)).setText(getEmptyStringForZero(aCommunityMemberParticipation.getCountNotesParagraphsDeleted()));
		if(GcgHelper.isLandscapeMode()) {
			((TextView) findViewById(R.id.fse__contribution__paragraph_locked__data__story)).setText(getEmptyStringForZero(aCommunityMemberParticipation.getCountStoryParagraphsLocked()));
			((TextView) findViewById(R.id.fse__contribution__paragraph_unlocked__data__story)).setText(getEmptyStringForZero(aCommunityMemberParticipation.getCountStoryParagraphsUnlocked()));
			((TextView) findViewById(R.id.fse__contribution__paragraph_locked__data__notes)).setText(getEmptyStringForZero(aCommunityMemberParticipation.getCountNotesParagraphsLocked()));
			((TextView) findViewById(R.id.fse__contribution__paragraph_unlocked__data__notes)).setText(getEmptyStringForZero(aCommunityMemberParticipation.getCountNotesParagraphsUnlocked()));
		}
	}
	
	private static String getEmptyStringForZero(int anInt) {
		return anInt == 0 ? "" : "" + anInt;
	}

}
