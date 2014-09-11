/* @(#)PdfSectionModificationMarkupLegend.java
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

import android.graphics.drawable.Drawable;

import com.flywheelms.library.R;
import com.flywheelms.library.fms.pdf.PdfColors;
import com.flywheelms.library.fms.pdf.PdfFonts;
import com.flywheelms.library.fms.pdf.PdfPCellStyleApplicator;
import com.flywheelms.library.fms.pdf.PdfUtil;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublication;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublicationSection;
import com.flywheelms.library.fse.enumerator.FseContentModificationState;
import com.flywheelms.library.fse.enumerator.FseLockModificationState;
import com.flywheelms.library.fse.enumerator.FseNumberingModificationState;
import com.flywheelms.library.fse.enumerator.FseSequenceModificationState;
import com.flywheelms.library.fse.enumerator.FseStyleModificationState;
import com.flywheelms.library.fse.enumerator.ModificationState;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PdfSectionModificationMarkupLegend extends HeadlineNodePublicationSection {

	private final PdfPCellStyleApplicator styleApplicatorHeaderCell = new PdfPCellStyleApplicator() {

		@Override
		public void styleCell(PdfPCell cell) {
			cell.setBackgroundColor(PdfColors.DARK_BLUE);
			cell.setBorder(0);
			cell.setPadding(4f);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
	};

	private final PdfPCellStyleApplicator styleApplicatorLegendInnerColumn = new PdfPCellStyleApplicator() {

		@Override
		public void styleCell(PdfPCell cell) {
			cell.setBorderWidth(0f);
			cell.setBorderWidthBottom(1f);
		}
	};

	public PdfSectionModificationMarkupLegend(HeadlineNodePublication headlineNodePublication) {
		super(headlineNodePublication);
	}

	@Override
	protected String getHeaderLabel() {
		return PdfUtil.getResource(R.string.modification_markup_legend);
	}

	@Override
	protected void writeContent() throws IOException, DocumentException {
		getDocument().add(buildLegend());
	}

	private PdfPTable buildLegend() {
		PdfPTable table = PdfUtil.newTable(540, new int[] { 108, 108, 108, 108, 108 });
		addTableHeader(table);
		addColumnHeaders(table);
		addLegendContent(table);
		return table;
	}

	private void addTableHeader(PdfPTable table) {
		PdfPCell legendHeader = newHeaderCell("Modification Markup Legend");
		legendHeader.setColspan(5);
		table.addCell(legendHeader);
	}

	private void addColumnHeaders(PdfPTable table) {
		table.addCell(newHeaderCell("Sequence"));
		table.addCell(newHeaderCell("Lock State"));
		table.addCell(newHeaderCell("Content"));
		table.addCell(newHeaderCell("Numbering"));
		table.addCell(newHeaderCell("Style"));
	}

	private PdfPCell newHeaderCell(String label) {
		return PdfUtil.newCell(label, PdfFonts.BOLD_12_WHITE, this.styleApplicatorHeaderCell);
	}

	private void addLegendContent(PdfPTable table) {
		table.addCell(buildCellModificationStateSequence());
		table.addCell(buildCellModifcationStateLock());
		table.addCell(buildCellModifcationStateContent());
		table.addCell(buildCellModifcationStateNumbering());
		table.addCell(buildCellModifcationStateStyle());
	}

	private PdfPCell buildCellModifcationStateStyle() {
		PdfPCell cell = new PdfPCell(buildModificationStateTable(FseStyleModificationState.values()));
		cell.setBorder(0);
		cell.setBorderWidthRight(1f);
		cell.setBorderWidthBottom(1f);
		return cell;
	}

	private PdfPCell buildCellModifcationStateNumbering() {
		return buildLegendInnerColumn(buildModificationStateTable(FseNumberingModificationState.values()));
	}

	private PdfPCell buildCellModifcationStateContent() {
		PdfPTable modificationStateTable = buildModificationStateTable(FseContentModificationState.values(), new int[] { 24, 84 });
		PdfPCell cell = new PdfPCell(modificationStateTable);
		this.styleApplicatorLegendInnerColumn.styleCell(cell);
		return cell;
	}

	private PdfPCell buildCellModifcationStateLock() {
		return buildLegendInnerColumn(buildModificationStateTable(FseLockModificationState.values()));
	}

	private PdfPCell buildLegendInnerColumn(PdfPTable modificationStateTable) {
		PdfPCell cell = new PdfPCell(modificationStateTable);
		this.styleApplicatorLegendInnerColumn.styleCell(cell);
		return cell;
	}

	private PdfPCell buildCellModificationStateSequence() {
		PdfPCell cell = new PdfPCell(buildModificationStateTable(getModificationStateValuesSequence()));
		cell.setPaddingLeft(4f);
		cell.setBorderWidth(0f);
		cell.setBorderWidthLeft(1f);
		cell.setBorderWidthBottom(1f);
		return cell;
	}

	@SuppressWarnings("static-method")
	private List<ModificationState> getModificationStateValuesSequence() {
		List<ModificationState> sequenceModificationStateList = new ArrayList<ModificationState>(Arrays.asList(FseSequenceModificationState.values()));
		sequenceModificationStateList.remove(FseSequenceModificationState.BLOCK_MOVE_UP);
		sequenceModificationStateList.remove(FseSequenceModificationState.BLOCK_MOVE_DOWN);
		sequenceModificationStateList.add(3, ModificationStateBlockMove.BLOCK_MOVE);
		return sequenceModificationStateList;
	}

	private PdfPTable buildModificationStateTable(ModificationState[] values) {
		return buildModificationStateTable(values, new int[] { 10, 98 });
	}

	private PdfPTable buildModificationStateTable(List<ModificationState> values) {
		return buildModificationStateTable(values.toArray(new ModificationState[values.size()]));
	}

	private PdfPTable buildModificationStateTable(ModificationState[] values, int[] colWidths) {
		PdfPTable contentTable = PdfUtil.newTable(108f, colWidths);
		PdfPCell defaultCell = contentTable.getDefaultCell();
		defaultCell.setPadding(2f);
		defaultCell.setPaddingBottom(10f);
		defaultCell.setBorder(0);

		for (ModificationState modificationState : values) {
			if (!modificationState.isUnchanged()) {
				contentTable.addCell(getImageCache().getImage(modificationState));
				contentTable.addCell(PdfUtil.newParagraph(modificationState.getName(), PdfFonts.PLAIN_10));
			}
		}
		return contentTable;
	}

	private enum ModificationStateBlockMove implements ModificationState {

		BLOCK_MOVE;

		@Override
		public boolean isUnchanged() {
			return false;
		}

		@Override
		public int getSummaryDrawableResourceId() {
			return 0;
		}

		@Override
		public Drawable getSummaryDrawable() {
			return null;
		}

		@Override
		public int getPdfSummaryDrawableResourceId() {
			return 0;
		}

		@Override
		public int getPdfMarkupDrawableResourceId() {
			return FseSequenceModificationState.BLOCK_MOVE_UP.getPdfMarkupDrawableResourceId();
		}

		@Override
		public String getName() {
			return "Block Move";
		}

		@Override
		public int getMarkupDrawableResourceId() {
			return 0;
		}

	}

}
