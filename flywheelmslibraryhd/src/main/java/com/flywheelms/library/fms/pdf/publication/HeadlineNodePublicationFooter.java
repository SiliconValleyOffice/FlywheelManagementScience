/* @(#)Footer.java
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

package com.flywheelms.library.fms.pdf.publication;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.flywheelms.library.fms.pdf.PdfColors;
import com.flywheelms.library.fms.pdf.PdfFonts;
import com.flywheelms.library.fms.pdf.PdfUtil;
import com.flywheelms.library.gcg.widget.date.GcgDateHelper;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class HeadlineNodePublicationFooter
{

	private HeadlineNodePublicationFooter()
	{
		super();
		throw new IllegalAccessError("Cannot instantiate");
	}

	protected static void addFooter(ByteArrayOutputStream byteArrayOutputStream, String abbreviatedNodeId) throws DocumentException, IOException, BadPdfFormatException
	{
		String dateText = PdfUtil.DATE_FORMAT_GMT_TIMESTAMP.format(GcgDateHelper.getCurrentDateTime());

		PdfReader reader = new PdfReader(byteArrayOutputStream.toByteArray());
		PdfStamper pdfStamper = new PdfStamper(reader, byteArrayOutputStream);
		int totalPageCount = reader.getNumberOfPages();
		for (int i = 1; i <= totalPageCount; i++)
		{
			buildTable(i, totalPageCount, dateText, abbreviatedNodeId).writeSelectedRows(0, -1, 36, 36, pdfStamper.getOverContent(i));
		}
		pdfStamper.close();
		reader.close();
	}

	private static PdfPTable buildTable(int pageNumber, int totalPageCount, String dateText, String abbreviatedNodeId)
	{
		PdfPTable table = PdfUtil.newTable(540f, new int[] { 180, 180, 180 });
		PdfPCell defaultCell = table.getDefaultCell();
		defaultCell.setBorderColor(PdfColors.DARK_BLUE);
		defaultCell.setBackgroundColor(PdfColors.DARK_BLUE);
		defaultCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		defaultCell.setPadding(4f);
		defaultCell.setPaddingTop(2f);

		table.addCell(new Phrase(dateText, PdfFonts.PLAIN_10_WHITE));

		defaultCell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(new Phrase(abbreviatedNodeId, PdfFonts.PLAIN_10_WHITE));

		defaultCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		table.addCell(new Phrase(String.format("Page %d of %d", pageNumber, totalPageCount), PdfFonts.PLAIN_10_WHITE));

		return table;
	}

}
