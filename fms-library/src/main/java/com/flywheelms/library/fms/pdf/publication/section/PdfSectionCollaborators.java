/* @(#)PdfSectionCollaborators.java
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

import com.flywheelms.library.R;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fms.activity.FmsActivity;
import com.flywheelms.library.fms.pdf.PdfColors;
import com.flywheelms.library.fms.pdf.PdfFonts;
import com.flywheelms.library.fms.pdf.PdfPCellStyleApplicator;
import com.flywheelms.library.fms.pdf.PdfUtil;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublication;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublicationSection;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.model.FseCommunityMemberCollaborationSummary;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;
import java.util.Collection;

public class PdfSectionCollaborators extends HeadlineNodePublicationSection {

	private final PdfPCellStyleApplicator styleApplicator = new PdfPCellStyleApplicator() {
		@Override
		public void styleCell(PdfPCell cell) {
			cell.setBorderWidth(1f);
			PdfUtil.setCellPadding(cell, 4f, 2f, 6f);
		}
	};

	public PdfSectionCollaborators(HeadlineNodePublication headlineNodePublication) {
		super(headlineNodePublication);
	}

	@Override
	protected String getHeaderLabel() {
		return FseDocumentSectionType.COMMUNITY.getName();
	}

	@Override
	protected void writeContent() throws IOException, DocumentException {
		PdfPTable tableAuditBlock = buildTableAuditBlock();
		tableAuditBlock.setSpacingAfter(24f);
		getDocument().add(tableAuditBlock);

		getDocument().add(buildTableCollaboratorSummary());
	}

	private PdfPTable buildTableAuditBlock() {
		PdfPTable table = PdfUtil.newTable(324f, new int[] { 162, 162 });
		this.styleApplicator.styleCell(table.getDefaultCell());
		table.setHeaderRows(1);

		table.addCell(buildAuditBlockTitleCell());
		addTableRowsAuditBlock(table);

		return table;
	}

	@SuppressWarnings("static-method")
	private PdfPCell buildAuditBlockTitleCell() {
		PdfPCell titleCell = PdfUtil.newCellStringResource(R.string.fmm_node_definition__node_frag__audit_block__term, PdfFonts.PLAIN_10_WHITE);
		titleCell.setColspan(2);
		titleCell.setBackgroundColor(BaseColor.DARK_GRAY);
		titleCell.setBorderWidth(1f);
		titleCell.setBorderColor(BaseColor.DARK_GRAY);
		PdfUtil.setCellPadding(titleCell, 4f, 2f, 6f);
		return titleCell;
	}

	private void addTableRowsAuditBlock(PdfPTable table) {
		NodeFragAuditBlock auditBlock = getHeadlineNode().getFseDocument().getNodeFragAuditBlock();

		table.addCell(PdfUtil.newParagraphResource(R.string.audit_block__created_by, PdfFonts.BOLD_10));
		table.addCell(PdfUtil.newParagraph(auditBlock.getCreatedByCommunityMember().getName(), PdfFonts.PLAIN_10));

		table.addCell(PdfUtil.newParagraphResource(R.string.audit_block__created_timestamp, PdfFonts.BOLD_10));
		table.addCell(PdfUtil.newParagraph(PdfUtil.DATE_FORMAT_GMT_TIMESTAMP_VERBOSE.format(auditBlock.getCreatedTimestamp()), PdfFonts.PLAIN_10));

		table.addCell(PdfUtil.newParagraphResource(R.string.audit_block__last_updated_by, PdfFonts.BOLD_10));
		table.addCell(PdfUtil.newParagraph(auditBlock.getLastUpdatedByCommunityMember().getName(), PdfFonts.PLAIN_10));

		table.addCell(PdfUtil.newParagraphResource(R.string.audit_block__last_updated_timestamp, PdfFonts.BOLD_10));
		table.addCell(PdfUtil.newParagraph(PdfUtil.DATE_FORMAT_GMT_TIMESTAMP_VERBOSE.format(auditBlock.getRowTimestamp()), PdfFonts.PLAIN_10));

		table.addCell(PdfUtil.newParagraphResource(R.string.audit_block__last_updated_by, PdfFonts.BOLD_10));
		table.addCell(PdfUtil.newParagraph(auditBlock.getLockedByCommunityMember().getName(), PdfFonts.PLAIN_10));

		table.addCell(PdfUtil.newParagraphResource(R.string.audit_block__locked_timestamp, PdfFonts.BOLD_10));
		table.addCell(PdfUtil.newParagraph(PdfUtil.DATE_FORMAT_GMT_TIMESTAMP_VERBOSE.format(auditBlock.getLockedTimestamp()), PdfFonts.PLAIN_10));

		table.addCell(PdfUtil.newParagraphResource(R.string.audit_block__locked_status, PdfFonts.BOLD_10));
		table.addCell(PdfUtil.newParagraph(auditBlock.getLockStatus().getName(), PdfFonts.PLAIN_10));
	}

	private PdfPTable buildTableCollaboratorSummary() {
		PdfPTable table = PdfUtil.newTable(540f, new int[] { 132, 84, 84, 40, 40, 40, 40, 40, 40 });
		PdfUtil.setCellPadding(table.getDefaultCell(), 4f, 2f, 6f);
		table.setHeaderRows(2);

		addHeaderRowsCollaboratorSummary(table);
		addTableRowsCollaboratorSummary(table);

		return table;
	}

	@SuppressWarnings("static-method")
	private void addHeaderRowsCollaboratorSummary(PdfPTable table) {
		PdfPCell titleCell = PdfUtil.newCellStringResource(R.string.fse__collaborator_summmary__heading, PdfFonts.PLAIN_10_WHITE);
		titleCell.setColspan(9);
		titleCell.setBackgroundColor(BaseColor.DARK_GRAY);
		titleCell.setBorderWidth(1f);
		titleCell.setBorderColor(BaseColor.DARK_GRAY);
		PdfUtil.setCellPadding(titleCell, 4f, 2f, 6f);
		table.addCell(titleCell);

		PdfPCell defaultCell = table.getDefaultCell();
		defaultCell.setBackgroundColor(PdfColors.DARK_BLUE);
		defaultCell.setBorderWidth(1f);
		defaultCell.setBorderColor(PdfColors.DARK_BLUE);

		table.addCell(PdfUtil.newParagraphResource(R.string.community_member__name, PdfFonts.PLAIN_8_WHITE));
		table.addCell(PdfUtil.newParagraphResource(R.string.fse__first_contribution_timestamp__heading, PdfFonts.PLAIN_8_WHITE));
		table.addCell(PdfUtil.newParagraphResource(R.string.fse__last_contribution_timestamp__heading, PdfFonts.PLAIN_8_WHITE));
		table.addCell(PdfUtil.newParagraphResource(R.string.fse__document_section__heading, PdfFonts.PLAIN_8_WHITE));
		table.addCell(PdfUtil.newParagraphResource(R.string.fse__transaction_paragraphs_new__heading, PdfFonts.PLAIN_8_WHITE));
		table.addCell(PdfUtil.newParagraphResource(R.string.fse__transaction_paragraphs_modified__heading, PdfFonts.PLAIN_8_WHITE));
		table.addCell(PdfUtil.newParagraphResource(R.string.fse__transaction_paragraphs_deleted__heading, PdfFonts.PLAIN_8_WHITE));
		table.addCell(PdfUtil.newParagraphResource(R.string.fse__transaction_paragraphs_locked__heading, PdfFonts.PLAIN_8_WHITE));
		table.addCell(PdfUtil.newParagraphResource(R.string.fse__transaction_paragraphs_unlocked__heading, PdfFonts.PLAIN_8_WHITE));

		defaultCell.setBackgroundColor(BaseColor.WHITE);
		defaultCell.setBorderColor(BaseColor.BLACK);
	}

	private void addTableRowsCollaboratorSummary(PdfPTable table) {
		Collection<FseCommunityMemberCollaborationSummary> summaries = getHeadlineNode().getFseDocument().getDocumentSectionCollaborators().getCollaboratorSummary().getCommunityMemberParticipationList();
		for (FseCommunityMemberCollaborationSummary summary : summaries) {
			addTableRowCollaboratorSummary(table, summary);
		}
	}

	@SuppressWarnings("static-method")
	private void addTableRowCollaboratorSummary(PdfPTable table, FseCommunityMemberCollaborationSummary summary) {
		table.getDefaultCell().setBorderWidth(1f);
		table.addCell(PdfUtil.newParagraph(FmsActivity.getActiveDatabaseMediator().retrieveCommunityMember(summary.getCommunityMemberNodeIdString()).getName(), PdfFonts.PLAIN_8));
		table.addCell(PdfUtil.newParagraph(PdfUtil.DATE_FORMAT_GMT_TIMESTAMP_VERBOSE.format(summary.getFirstContributionTimestamp()), PdfFonts.PLAIN_8));
		table.addCell(PdfUtil.newParagraph(PdfUtil.DATE_FORMAT_GMT_TIMESTAMP_VERBOSE.format(summary.getLastContributionTimestamp()), PdfFonts.PLAIN_8));

		table.addCell(PdfUtil.newCellCounts(FseDocumentSectionType.STORY.getName(), FseDocumentSectionType.NOTES.getName()));
		table.addCell(PdfUtil.newCellCounts(summary.getCountStoryParagraphsNew(), summary.getCountNotesParagraphsNew()));
		table.addCell(PdfUtil.newCellCounts(summary.getCountStoryParagraphsModified(), summary.getCountNotesParagraphsModified()));
		table.addCell(PdfUtil.newCellCounts(summary.getCountStoryParagraphsDeleted(), summary.getCountNotesParagraphsDeleted()));
		table.addCell(PdfUtil.newCellCounts(summary.getCountStoryParagraphsLocked(), summary.getCountNotesParagraphsLocked()));
		table.addCell(PdfUtil.newCellCounts(summary.getCountStoryParagraphsUnlocked(), summary.getCountNotesParagraphsUnlocked()));
	}
}
