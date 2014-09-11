/* @(#)PdfSection.java
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

import android.util.Log;

import com.flywheelms.library.fmm.helper.FmmHelper;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public final class PdfUtil {

	private static final String TAG = PdfUtil.class.getCanonicalName();

	public static final SimpleDateFormat DATE_FORMAT_GMT_TIMESTAMP = new SimpleDateFormat("yyyy-MM-dd HH:mm  z");
	public static final SimpleDateFormat DATE_FORMAT_GMT_TIMESTAMP_VERBOSE = new SimpleDateFormat("EEE MMM dd yyyy HH:mm  z");

	public static int appendDocument(PdfCopy aPdfCopy, ByteArrayOutputStream aByteArrayOutputStream) throws DocumentException, IOException, BadPdfFormatException {
		PdfReader theReader = new PdfReader(aByteArrayOutputStream.toByteArray());
		int theNumberOfPages = theReader.getNumberOfPages();
		for (int i = 1; i <= theNumberOfPages; i++) {
			aPdfCopy.addPage(aPdfCopy.getImportedPage(theReader, i));
		}
		return theNumberOfPages;
	}

	public static float convertToPt(float dpi) {
		return dpi * 72f / 213f;
	}

	public static String getResource(int resourceId) {
		return FmmHelper.getContext().getResources().getString(resourceId);
	}

	public static Paragraph newParagraph(String text, Font font) {
		return new Paragraph(text, font);
	}

	public static Paragraph newParagraphResource(int resourceId, Font font) {
		return newParagraph(getResource(resourceId), font);
	}

	public static PdfPCell newCell(String text, Font font) {
		return newCell(text, font, null);
	}

	public static PdfPCell newCell(String text, Font font, PdfPCellStyleApplicator styleApplicator) {
		return newCell(newParagraph(text, font), styleApplicator);
	}

	public static PdfPCell newCell(Paragraph paragraph, PdfPCellStyleApplicator styleApplicator) {
		PdfPCell cell = new PdfPCell(paragraph);
		cell.setBorderWidth(0);
		cell.setPadding(0);
		if (styleApplicator != null) {
			styleApplicator.styleCell(cell);
		}
		return cell;
	}

	public static PdfPCell newCellStringResource(int resourceId, Font font, PdfPCellStyleApplicator styleApplicator) {
		return newCell(getResource(resourceId), font, styleApplicator);
	}

	public static PdfPCell newCellStringResource(int resourceId, Font font) {
		return newCell(getResource(resourceId), font, null);
	}

	public static PdfPCell newCellCounts(String storyRowText, String notesRowText) {
		PdfPCell pdfPCell = new PdfPCell();
		pdfPCell.setHorizontalAlignment(Element.ALIGN_RIGHT);

		Paragraph paragraph = new Paragraph(storyRowText, PdfFonts.PLAIN_8);
		paragraph.setAlignment(Element.ALIGN_RIGHT);
		pdfPCell.addElement(paragraph);

		Paragraph paragraph2 = new Paragraph(notesRowText, PdfFonts.PLAIN_8);
		paragraph2.setAlignment(Element.ALIGN_RIGHT);
		pdfPCell.addElement(paragraph2);

		pdfPCell.setPaddingTop(0f);
		pdfPCell.setBorderWidth(1f);

		return pdfPCell;
	}

	public static PdfPCell newCellCounts(int storyCount, int notesCount) {
		return newCellCounts(storyCount > 0 ? Integer.toString(storyCount) : "", notesCount > 0 ? Integer.toString(notesCount) : "");
	}

	public static Document newDocument() {
		Document document = new Document();
		setMetaData(document);
		return document;
	}

	public static PdfPTable newTable(float totalWidth, int[] columnProportions) {
		PdfPTable table = new PdfPTable(columnProportions.length);
		table.setTotalWidth(totalWidth);
		table.setLockedWidth(true);
		try {
			table.setWidths(columnProportions);
		} catch (DocumentException e) {
			Log.e(TAG, "Cannot build table with width: " + totalWidth + " columnProportions: " + columnProportions.toString(), e);
		}
		PdfPCell defaultCell = table.getDefaultCell();
		defaultCell.setBorderWidth(0);
		defaultCell.setPadding(0);
		return table;
	}

	public static void setCellPadding(PdfPCell pdfPCell, float topAndBottom, float sides) {
		setCellPadding(pdfPCell, topAndBottom, sides, topAndBottom, sides);
	}

	public static void setCellPadding(PdfPCell pdfPCell, float top, float sides, float bottom) {
		setCellPadding(pdfPCell, top, sides, bottom, sides);
	}

	public static void setCellPadding(PdfPCell pdfPCell, float top, float right, float bottom, float left) {
		pdfPCell.setPaddingTop(top);
		pdfPCell.setPaddingRight(right);
		pdfPCell.setPaddingBottom(bottom);
		pdfPCell.setPaddingLeft(left);
	}

	public static void setMetaData(Document aPdfDocument) {
		aPdfDocument.setPageSize(PageSize.LETTER);
		aPdfDocument.setMargins(0f, 0f, 114f, 48f);// left, right, top, bottom
	}

	{
		DATE_FORMAT_GMT_TIMESTAMP.setTimeZone(TimeZone.getTimeZone("Greenwich Mean Time"));
		DATE_FORMAT_GMT_TIMESTAMP_VERBOSE.setTimeZone(TimeZone.getTimeZone("Greenwich Mean Time"));
	}

	private PdfUtil() {
		super();
	}

}
