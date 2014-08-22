/* @(#)FseParagraphStyleType.java
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

package com.flywheelms.library.fse.enumerator;

import java.util.Arrays;
import java.util.List;


public enum FseParagraphStyleType {
	
	HEADING(FseParagraphContentType.TEXT, new FseParagraphStyle[] {
			FseParagraphStyle.HEADING_1,
			FseParagraphStyle.HEADING_2,
			FseParagraphStyle.HEADING_3,
			FseParagraphStyle.HEADING_4,
			FseParagraphStyle.HEADING_5,
			FseParagraphStyle.HEADING_6
	} ),
	BODY(FseParagraphContentType.TEXT, new FseParagraphStyle[] {
			FseParagraphStyle.BODY_1,
			FseParagraphStyle.BODY_2,
			FseParagraphStyle.BODY_3,
			FseParagraphStyle.BODY_4,
			FseParagraphStyle.BODY_5,
			FseParagraphStyle.BODY_6
	} ),
	IMAGE(FseParagraphContentType.IMAGE, new FseParagraphStyle[] {
			FseParagraphStyle.IMAGE_1,
			FseParagraphStyle.IMAGE_2,
			FseParagraphStyle.IMAGE_3,
			FseParagraphStyle.IMAGE_4,
			FseParagraphStyle.IMAGE_5,
			FseParagraphStyle.IMAGE_6
	} ),
	DRAWING(FseParagraphContentType.DRAWING, new FseParagraphStyle[] {
			FseParagraphStyle.DRAWING_1,
			FseParagraphStyle.DRAWING_2,
			FseParagraphStyle.DRAWING_3,
			FseParagraphStyle.DRAWING_4,
			FseParagraphStyle.DRAWING_5,
			FseParagraphStyle.DRAWING_6
	} ),
	LIST(FseParagraphContentType.TEXT, new FseParagraphStyle[] {
			FseParagraphStyle.LIST_1,
			FseParagraphStyle.LIST_2,
			FseParagraphStyle.LIST_3,
			FseParagraphStyle.LIST_4,
			FseParagraphStyle.LIST_5,
			FseParagraphStyle.LIST_6
	} );
	
	private final FseParagraphContentType paragraphContentType;
	private FseParagraphStyle[] paragraphStyleArray;
	
	private FseParagraphStyleType(
			FseParagraphContentType anFseParagraphContentType,
			FseParagraphStyle[] aParagraphStyleArray) {
		this.paragraphContentType = anFseParagraphContentType;
		this.paragraphStyleArray = aParagraphStyleArray;
	}

	public List<FseParagraphStyle> getStyleList() {
		return Arrays.asList(this.paragraphStyleArray);
	}
	
	public void setStyleArray(FseParagraphStyle[] anFseParagraphStyleArray) {
		this.paragraphStyleArray = anFseParagraphStyleArray;
	}
	
	public FseParagraphContentType getParagraphContentType() {
		return this.paragraphContentType;
	}
	
	public boolean isDrawing() {
		return this.paragraphContentType == FseParagraphContentType.DRAWING;
	}
	
	public boolean isImage() {
		return this.paragraphContentType == FseParagraphContentType.IMAGE;
	}

	public boolean isText() {
		return this.paragraphContentType == FseParagraphContentType.TEXT;
	}
	
}
