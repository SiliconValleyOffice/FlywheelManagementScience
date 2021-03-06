/* @(#)FseNotesTemplate.java
** 
** Copyright (C) 2012 by Steven D. Stamps
**
**             Trademarks & Copyrights
** Flywheel Management Science(TM), Flywheel Management Model(TM),
** Flywheel Notes Editor(TM) and FlywheelMS(TM) are exclusive trademarks
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

package com.flywheelms.library.fse.enumerator;

import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.Project;
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fmm.node.impl.governable.StrategicMilestone;
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fmm.node.impl.governable.WorkTask;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.fse.model.FseTemplateParagraph;

import java.util.Hashtable;

public enum FseNotesTemplate {
	
	GENERIC (1, 0, new FseTemplateParagraph[] {
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "NOTES"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, ""),

			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, false, "TODO"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, ""),

			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, false, "OUTSTANDING ISSUES"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "")
	} );
	
	private static final Hashtable<Class<? extends FmmNode>, FseNotesTemplate> fmmNodeMap = new Hashtable<Class<? extends FmmNode>, FseNotesTemplate>();
	
	static {
		FseNotesTemplate.fmmNodeMap.put(FiscalYear.class, GENERIC);
		FseNotesTemplate.fmmNodeMap.put(Project.class, GENERIC);
		FseNotesTemplate.fmmNodeMap.put(ProjectAsset.class, GENERIC);
		FseNotesTemplate.fmmNodeMap.put(StrategicMilestone.class, GENERIC);
		FseNotesTemplate.fmmNodeMap.put(WorkPackage.class, GENERIC);
		FseNotesTemplate.fmmNodeMap.put(WorkTask.class, GENERIC);
	}
	
	public static FseNotesTemplate getTemplateForClass(Class<? extends FmmNode> aClass) {
		return FseNotesTemplate.fmmNodeMap.get(aClass) == null ? GENERIC : FseNotesTemplate.fmmNodeMap.get(aClass);
	}
	
	private final FseTemplateParagraph[] fseTemplateParagraphArray;
	private final int initialParagraphFocusIndex;
	private final int initialParagraphFocusCursorPosition;
	
	private FseNotesTemplate(int anInitialParagraphFocusIndex, int anInitialParagraphFocusCursorPosition, FseTemplateParagraph[] anFseTemplateParagraph) {
		this.initialParagraphFocusIndex = anInitialParagraphFocusIndex;
		this.initialParagraphFocusCursorPosition = anInitialParagraphFocusCursorPosition;
		this.fseTemplateParagraphArray = anFseTemplateParagraph;
	}

	public FseTemplateParagraph[] getFseTemplateParagraphArray() {
		return this.fseTemplateParagraphArray;
	}
	
	public int getInitialParagraphFocusIndex() {
		return this.initialParagraphFocusIndex;
	}

	public int getInitialParagraphFocusCursorPosition() {
		return this.initialParagraphFocusCursorPosition;
	}

}
