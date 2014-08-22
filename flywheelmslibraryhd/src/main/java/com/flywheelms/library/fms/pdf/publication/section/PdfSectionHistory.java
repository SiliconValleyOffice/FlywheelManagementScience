/* @(#)History.java
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

import com.flywheelms.library.R;
import com.flywheelms.library.fca.FlywheelCommunityAuthentication;
import com.flywheelms.library.fms.pdf.PdfColors;
import com.flywheelms.library.fms.pdf.PdfFonts;
import com.flywheelms.library.fms.pdf.PdfPCellStyleApplicator;
import com.flywheelms.library.fms.pdf.PdfUtil;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublication;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublicationSection;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.model.FseDocumentSectionHistory;
import com.flywheelms.library.fse.model.FseDocumentTransaction;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PdfSectionHistory extends HeadlineNodePublicationSection
{

	private final PdfPCellStyleApplicator styleApplicator = new PdfPCellStyleApplicator() {
		@Override
		public void styleCell(PdfPCell cell) {
			cell.setBorderWidth(1f);
			PdfUtil.setCellPadding(cell, 4f, 2f, 6f);
		}
	};
	
	public PdfSectionHistory(HeadlineNodePublication headlineNodePublication)
	{
		super(headlineNodePublication);
	}

	@Override
	protected String getHeaderLabel()
	{
		return FseDocumentSectionType.HISTORY.getName();
	}

	@Override
	protected void writeContent() throws IOException, DocumentException
	{
		PdfPTable table = PdfUtil.newTable(540, new int[] { 20, 64, 132, 50, 40, 38, 38, 38, 50, 32, 38 });
		this.styleApplicator.styleCell(table.getDefaultCell());

		table.setHeaderRows(1);
		addTableHeader(table);
		addTableRows(table);

		getDocument().add(table);
	}

	private void addTableHeader(PdfPTable table)
	{
		PdfPCell defaultCell = table.getDefaultCell();
		defaultCell.setBackgroundColor(PdfColors.DARK_BLUE);
		defaultCell.setBorderColor(PdfColors.DARK_BLUE);
		
		addHeaderCell(table, R.string.fse__transaction_number__heading);
		addHeaderCell(table, R.string.fse__transaction_timestamp__heading);
		addHeaderCell(table, R.string.fse__transaction_community_member__heading);
		addHeaderCell(table, R.string.fse__transaction_type__heading);
		addHeaderCell(table, R.string.fse__document_section__heading);
		addHeaderCell(table, R.string.fse__transaction_paragraphs_new__heading);
		addHeaderCell(table, R.string.fse__transaction_paragraphs_modified__heading);
		addHeaderCell(table, R.string.fse__transaction_paragraphs_deleted__heading);
		addHeaderCell(table, R.string.fse__transaction_paragraphs_unchanged__heading);
		addHeaderCell(table, R.string.fse__transaction_paragraphs_locked__heading);
		addHeaderCell(table, R.string.fse__transaction_paragraphs_unlocked__heading);
		
		defaultCell.setBackgroundColor(BaseColor.WHITE);
		defaultCell.setBorderColor(BaseColor.BLACK);
	}

	@SuppressWarnings("static-method")
	private void addHeaderCell(PdfPTable table, int resourceId)
	{
		table.addCell(PdfUtil.newParagraphResource(resourceId, PdfFonts.PLAIN_8_WHITE));
	}

	private void addTableRows(PdfPTable table)
	{
		FseDocumentSectionHistory documentSectionHistory = getHeadlineNode().getFseDocument().getDocumentSectionHistory();
		for (FseDocumentTransaction transaction : documentSectionHistory.getDocumentTransactionList())
		{
			addTableRow(table, transaction);
		}
	}

	@SuppressWarnings("static-method")
	private void addTableRow(PdfPTable table, FseDocumentTransaction transaction)
	{
		table.addCell(PdfUtil.newParagraph(Integer.toString(transaction.getTransactionNumber()), PdfFonts.PLAIN_8));
		table.addCell(PdfUtil.newParagraph(PdfUtil.DATE_FORMAT_GMT_TIMESTAMP_VERBOSE.format(transaction.getRowTimestamp()), PdfFonts.PLAIN_8));
		table.addCell(PdfUtil.newParagraph(FlywheelCommunityAuthentication.getInstance().getCommunityMember(transaction.getCommunityMemberNodeIdString()).getName(), PdfFonts.PLAIN_8));
		table.addCell(PdfUtil.newParagraph(transaction.getTransactionType().getDocumentTransactionType(), PdfFonts.PLAIN_8));

		table.addCell(PdfUtil.newCellCounts(FseDocumentSectionType.STORY.getName(), FseDocumentSectionType.NOTES.getName()));
		table.addCell(PdfUtil.newCellCounts(transaction.getParagraphNewCountStory(), transaction.getParagraphNewCountNotes()));
		table.addCell(PdfUtil.newCellCounts(transaction.getParagraphModifiedCountStory(), transaction.getParagraphModifiedCountNotes()));
		table.addCell(PdfUtil.newCellCounts(transaction.getParagraphDeletedCountStory(), transaction.getParagraphDeletedCountNotes()));
		table.addCell(PdfUtil.newCellCounts(transaction.getParagraphUnchangedCountStory(), transaction.getParagraphUnchangedCountNotes()));
		table.addCell(PdfUtil.newCellCounts(transaction.getParagraphLockedCountStory(), transaction.getParagraphLockedCountNotes()));
		table.addCell(PdfUtil.newCellCounts(transaction.getParagraphUnlockedCountStory(), transaction.getParagraphUnlockedCountNotes()));
	}

}
