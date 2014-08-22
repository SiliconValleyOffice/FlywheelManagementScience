/* @(#)PdfFonts.java
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

import java.io.IOException;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

public final class PdfFonts
{
	public static final Font PLAIN_8 = new Font();
	public static final Font PLAIN_8_WHITE = new Font();
	public static final Font PLAIN_10 = new Font();
	public static final Font PLAIN_10_WHITE = new Font();
	public static final Font PLAIN_12 = new Font();
	public static final Font PLAIN_20 = new Font();
	public static final Font BOLD_8 = new Font();
	public static final Font BOLD_10 = new Font();
	public static final Font BOLD_12 = new Font();
	public static final Font BOLD_12_BLUE = new Font();
	public static final Font BOLD_12_WHITE = new Font();
	public static final Font BOLD_24_BLUE = new Font();
	public static final Font BOLD_24_WHITE = new Font();

	public static final Font ITALIC_10_GRAY = new Font();

	public static BaseFont BASE_FONT;

	static
	{

		try
		{
			BASE_FONT = BaseFont.createFont();
		}
		// TODO proper error handling
		catch (DocumentException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		PLAIN_8.setSize(8f);

		PLAIN_8_WHITE.setSize(8f);
		PLAIN_8_WHITE.setColor(BaseColor.WHITE);

		PLAIN_10.setSize(10f);

		PLAIN_10_WHITE.setSize(10f);
		PLAIN_10_WHITE.setColor(BaseColor.WHITE);

		PLAIN_12.setSize(12f);

		PLAIN_20.setSize(20f);

		BOLD_8.setSize(8f);
		BOLD_8.setStyle(Font.BOLD);

		BOLD_10.setSize(10f);
		BOLD_10.setStyle(Font.BOLD);

		BOLD_12.setSize(12f);
		BOLD_12.setStyle(Font.BOLD);

		BOLD_12_BLUE.setColor(new BaseColor(0, 0, 165));
		BOLD_12_BLUE.setSize(12f);
		BOLD_12_BLUE.setStyle(Font.BOLD);

		BOLD_12_WHITE.setSize(12f);
		BOLD_12_WHITE.setStyle(Font.BOLD);
		BOLD_12_WHITE.setColor(BaseColor.WHITE);

		BOLD_24_BLUE.setColor(new BaseColor(0, 0, 165));
		BOLD_24_BLUE.setSize(24f);
		BOLD_24_BLUE.setStyle(Font.BOLD);

		BOLD_24_WHITE.setSize(24f);
		BOLD_24_WHITE.setStyle(Font.BOLD);
		BOLD_24_WHITE.setColor(BaseColor.WHITE);

		ITALIC_10_GRAY.setSize(10f);
		ITALIC_10_GRAY.setColor(BaseColor.GRAY);
		ITALIC_10_GRAY.setStyle(Font.ITALIC);
	}

	private PdfFonts()
	{
	}

}
