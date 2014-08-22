/* @(#)ParagraphDocumentSection.java
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

package com.flywheelms.library.fms.pdf.publication.section;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.flywheelms.library.fms.pdf.PdfColors;
import com.flywheelms.library.fms.pdf.PdfFonts;
import com.flywheelms.library.fms.pdf.PdfUtil;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublication;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublicationSection;
import com.flywheelms.library.fms.pdf.publication.ParagraphDocumentHeaderContentProvider;
import com.flywheelms.library.fse.enumerator.FseParagraphStyle;
import com.flywheelms.library.fse.model.FseDocumentSectionParagraphEditorContent;
import com.flywheelms.library.fse.model.FseParagraph;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public abstract class PdfSectionParagraphDocument extends HeadlineNodePublicationSection {

	private int[] columnWidths;

	private final ParagraphDocumentHeaderContentProvider headerContentProvider;

	public PdfSectionParagraphDocument(HeadlineNodePublication headlineNodePublication, ParagraphDocumentHeaderContentProvider headerContentProvider) {
		super(headlineNodePublication);
		this.headerContentProvider = headerContentProvider;
	}

	public abstract FseDocumentSectionParagraphEditorContent getDocumentSection();

	@Override
	protected String getHeaderLabel() {
		return getDocumentSection().getSectionType().getName();
	}

	@Override
	protected void writeContent() throws IOException, DocumentException {
		PdfPTable table = PdfUtil.newTable(540f, getColumnWidths());
		addParagraphRows(table);
		getDocument().add(table);
	}

	protected PdfPCell buildCellParagraphText(FseParagraph anFseParagraph) {
		FseParagraphStyle theParagraphStyle = anFseParagraph.getStyle();
		PdfPCell cell = new PdfPCell(new Paragraph(anFseParagraph.getTextForPublication(), anFseParagraph.getStyle().getpdfFont()));
		cell.setBorder(0);
		cell.setPaddingTop(PdfUtil.convertToPt(theParagraphStyle.getTopMargin()));
		cell.setPaddingLeft(PdfUtil.convertToPt(theParagraphStyle.getLeftMargin()));
		cell.setHorizontalAlignment(anFseParagraph.getStyle().getPdfParagraphAlignment());
		return cell;
	}

	private void addParagraphRows(PdfPTable table) {
		int[] rowHeights = computeRowHeights();
		final int pageHeightLimit = 630;
		int currentPageHeight = 0;
		List<boolean[]> pageModificationIconList = new ArrayList<boolean[]>();
		boolean modificationStateSequnce = false;
		boolean modificationStateLocking = false;
		boolean modificationStateModified = false;
		boolean modificationStateNew = false;
		boolean modificationStateNumbering = false;
		boolean modificationStateStyle = false;
		int index = 0;
		Collection<FseParagraph> fseParagraphs = getDocumentSection().getParagraphList().values();

		PdfPCell emptyCell = PdfUtil.newCell("", PdfFonts.PLAIN_10);
		emptyCell.setBackgroundColor(PdfColors.MARKUP_BACKGROUND_GREY);

		for (FseParagraph fseParagraph : fseParagraphs) {
			int currentRowHeight = rowHeights[index];
			currentPageHeight += currentRowHeight;
			if (currentPageHeight > pageHeightLimit || index == fseParagraphs.size() - 1) {
				pageModificationIconList.add(new boolean[] { modificationStateSequnce, modificationStateLocking, modificationStateModified, modificationStateNew, modificationStateNumbering, modificationStateStyle });
				modificationStateSequnce = false;
				modificationStateLocking = false;
				modificationStateModified = false;
				modificationStateNew = false;
				modificationStateNumbering = false;
				modificationStateStyle = false;
				currentPageHeight = currentRowHeight;
			}
			modificationStateSequnce = modificationStateSequnce || !fseParagraph.getSequenceModificationState().isUnchanged();
			modificationStateLocking = modificationStateLocking || !fseParagraph.getLockModificationState().isUnchanged();
			modificationStateModified = modificationStateModified || fseParagraph.getContentModificationState().isModified();
			modificationStateNew = modificationStateNew || fseParagraph.getContentModificationState().isNew();
			modificationStateNumbering = modificationStateNumbering || !fseParagraph.getNumberingModificationState().isUnchanged();
			modificationStateStyle = modificationStateStyle || !fseParagraph.getStyleModificationState().isUnchanged();

			if (isShowParagraphNumbering()) {
				PdfPCell newCellCount = buildCellCount(index + 1);
				table.addCell(newCellCount);
			}

			if (isShowContentModificationMarkup()) {
				table.addCell(fseParagraph.isModified() ? buildCellMarkupImage(fseParagraph, currentRowHeight) : emptyCell);
			}

			if (isShowParagraphStyle()) {
				table.addCell(buildCellParagraphStyle(fseParagraph));
			}

			table.addCell(buildCellParagraphText(fseParagraph));

			index++;
		}

		if (isShowContentModificationMarkup()) {
			this.headerContentProvider.setPageModificationIconList(pageModificationIconList);
		}
	}

	private int[] computeRowHeights() {
		Collection<FseParagraph> paragraphList = getDocumentSection().getParagraphList().values();

		int[] rowHeights = new int[paragraphList.size()];
		int[] colWidths = getColumnWidths();
		PdfPTable tempTable = PdfUtil.newTable(colWidths[colWidths.length - 1], new int[]{1});

		int index = 0;
		int totalTableHeight = 0;
		for (FseParagraph fseParagraph : paragraphList) {
			tempTable.addCell(buildCellParagraphText(fseParagraph));
			int newTableHeight = Math.round(tempTable.calculateHeights());
			int rowHeight = (int) ((newTableHeight - totalTableHeight) + PdfUtil.convertToPt(fseParagraph.getStyle().getTopMargin()));
			rowHeights[index] = rowHeight;
			totalTableHeight = newTableHeight;
			index++;
		}
		return rowHeights;
	}

	// iText handles the column widths as proportions but if the returned array to sums to 540 it will correspond to the width of the page
	private int[] getColumnWidths() {
		if (this.columnWidths == null) {
			this.columnWidths = computeColumnWidths();
		}
		return this.columnWidths;
	}

	private int[] computeColumnWidths() {
		List<Integer> widthList = new ArrayList<Integer>();
		if (isShowParagraphNumbering()) {
			int paragraphCount = getDocumentSection().getParagraphList().size();
			int digits = Double.valueOf(Math.floor(Math.log10(paragraphCount))).intValue() + 1;
			widthList.add(9 * digits);
		}
		if (isShowContentModificationMarkup()) {
			widthList.add(44);
		}
		if (isShowParagraphStyle()) {
			widthList.add(50);
		}

		int textColumnWidth = 540;
		int widthListSize = widthList.size();
		int[] theColumnWidths = new int[widthListSize + 1];
		int index = 0;
		for (Integer w : widthList) {
			theColumnWidths[index] = w;
			textColumnWidth -= w;
			index++;
		}
		theColumnWidths[widthListSize] = textColumnWidth;

		return theColumnWidths;
	}

	private PdfPCell buildCellCount(int count) {
		return newCellRightAligned(Integer.toString(count));
	}

	private PdfPCell buildCellParagraphStyle(FseParagraph fseParagraph) {
		PdfPCell newCellRightAligned = newCellRightAligned(fseParagraph.getStyle().getName());
		newCellRightAligned.setBackgroundColor(fseParagraph.isLocked() ? PdfColors.LOCKED_PARAGRAPH_GREEN : PdfColors.MARKUP_BACKGROUND_GREY);
		return newCellRightAligned;
	}

	@SuppressWarnings("static-method")
	private PdfPCell newCellRightAligned(String text) {
		PdfPCell cell = new PdfPCell(new Paragraph(text, PdfFonts.PLAIN_8));
		cell.setBorder(0);
		cell.setPaddingRight(2f);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
		return cell;
	}

	private PdfPCell buildCellMarkupImage(FseParagraph fseParagraph, int height) {
		Image markupImage = getImageCache().getMarkupImage(fseParagraph, height);
		PdfPCell cell = new PdfPCell(markupImage);
		cell.setBackgroundColor(PdfColors.MARKUP_BACKGROUND_GREY);
		cell.setPadding(0);
		cell.setBorder(0);
		return cell;
	}

}
