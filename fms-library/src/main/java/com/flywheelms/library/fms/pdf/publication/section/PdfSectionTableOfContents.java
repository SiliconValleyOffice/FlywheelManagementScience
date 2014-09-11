/* @(#)TableOfContents.java
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
import com.flywheelms.library.fms.pdf.PdfFonts;
import com.flywheelms.library.fms.pdf.PdfUtil;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublication;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublicationSection;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;

import java.io.IOException;

public class PdfSectionTableOfContents extends HeadlineNodePublicationSection
{

	private PdfTemplate templateStory;
	private PdfTemplate templateNotes;
	private PdfTemplate templateMarkupLegend;
	private PdfTemplate templateCollaborators;
	private PdfTemplate templateHistory;
	private PdfTemplate templateDecKanGL;

	public PdfSectionTableOfContents(HeadlineNodePublication headlineNodePublication)
	{
		super(headlineNodePublication);
	}

	@Override
	protected String getHeaderLabel()
	{
		return PdfUtil.getResource(R.string.table_of_contents);
	}

	@Override
	protected void writeContent() throws IOException, DocumentException
	{
		PdfPTable table = PdfUtil.newTable(324, new int[] { 252, 72 });
		addSectionFseDocument(table);
		addSectionTribkn(table);
		getDocument().add(table);
	}

	private void addSectionFseDocument(PdfPTable table) throws BadElementException
	{
		table.addCell(newCellSectionLabel(R.string.fse__document));
		addEntryStory(table);
		addEntryNotes(table);
		addEntryMarkupLegend(table);
		addEntryHistory(table);
		addEntryCollaborators(table);
	}

	private void addEntry(PdfPTable table, int resourceId, PdfTemplate template) throws BadElementException
	{
		PdfPCell textCell = PdfUtil.newCellStringResource(resourceId, PdfFonts.PLAIN_12);
		setPadding(textCell);
		table.addCell(textCell);

		PdfPCell numberCell = new PdfPCell(Image.getInstance(template));
		numberCell.setBorder(0);
		setPadding(numberCell);
		table.addCell(numberCell);
	}

	private void addEntryCollaborators(PdfPTable table) throws BadElementException
	{
		if (getContentSelectionView().getContributors().isChecked())
		{
			PdfTemplate template = createBlankPageNumberTemplate();
			setTemplateCollaborators(template);
			addEntry(table, R.string.fse__document_section__community, template);
		}
	}

	private void addEntryHistory(PdfPTable table) throws BadElementException
	{
		if (getContentSelectionView().getHistory().isChecked())
		{
			PdfTemplate template = createBlankPageNumberTemplate();
			setTemplateHistory(template);
			addEntry(table, R.string.fse__document_section__history, template);
		}
	}

	private void addEntryMarkupLegend(PdfPTable table) throws BadElementException
	{
		if (getContentSelectionView().getContentModification().isChecked())
		{
			PdfTemplate template = createBlankPageNumberTemplate();
			setTemplateMarkupLegend(template);
			addEntry(table, R.string.modification_markup_legend, template);
		}
	}

	private void addEntryNotes(PdfPTable table) throws BadElementException
	{
		if (getContentSelectionView().getNotes().isChecked())
		{
			PdfTemplate template = createBlankPageNumberTemplate();
			setTemplateNotes(template);
			addEntry(table, R.string.fse__document_section__notes, template);
		}
	}

	private void addEntryStory(PdfPTable table) throws BadElementException
	{
		PdfTemplate template = createBlankPageNumberTemplate();
		setTemplateStory(template);
		addEntry(table, R.string.fse__document_section__story, template);
	}

	private void addSectionTribkn(PdfPTable table) throws BadElementException
	{
		if (getContentSelectionView().getDecKanGl().isChecked())
		{
			table.addCell(newCellSectionLabel(R.string.fse__document));
		}
		addEntryDecKanGL(table);
	}

	private void addEntryDecKanGL(PdfPTable table) throws BadElementException
	{
		if (getContentSelectionView().getDecKanGl().isChecked())
		{
			PdfTemplate template = createBlankPageNumberTemplate();
			setTemplateDecKanGL(template);
			addEntry(table, R.string.deckangl_tm, template);
		}
	}

	@SuppressWarnings("static-method")
	private PdfPCell newCellSectionLabel(int resourceId)
	{
		PdfPCell cell = PdfUtil.newCellStringResource(resourceId, PdfFonts.BOLD_12);
		cell.setColspan(2);
		cell.setPaddingBottom(10f);
		return cell;
	}

	@SuppressWarnings("static-method")
	private void setPadding(PdfPCell cell)
	{
		cell.setPaddingLeft(36);
		cell.setPaddingBottom(10f);
	}

	private PdfTemplate createBlankPageNumberTemplate()
	{
		return getDirectContent().createTemplate(36f, 12f);
	}

	@SuppressWarnings("static-method")
	private void updateTemplatePageNumber(PdfTemplate template, int pageNumber)
	{
		ColumnText.showTextAligned(template, Element.ALIGN_RIGHT, new Phrase(Integer.toString(pageNumber), PdfFonts.PLAIN_12), 36f, 2f, 0f);
	}

	public void setPageNumberStory(int pageNumber)
	{
		updateTemplatePageNumber(getTemplateStory(), pageNumber);
	}

	public void setPageNumberNotes(int pageNumber)
	{
		updateTemplatePageNumber(getTemplateNotes(), pageNumber);
	}

	public void setPageNumberMarkupLegend(int pageNumber)
	{
		updateTemplatePageNumber(getTemplateMarkupLegend(), pageNumber);
	}

	public void setPageNumberCollaborators(int pageNumber)
	{
		updateTemplatePageNumber(getTemplateCollaborators(), pageNumber);
	}

	public void setPageNumberHistory(int pageNumber)
	{
		updateTemplatePageNumber(getTemplateHistory(), pageNumber);
	}

	public void setPageNumberDecKanGL(int pageNumber)
	{
		updateTemplatePageNumber(getTemplateDecKanGL(), pageNumber);
	}

	protected PdfTemplate getTemplateStory()
	{
		return this.templateStory;
	}

	protected PdfTemplate getTemplateNotes()
	{
		return this.templateNotes;
	}

	protected PdfTemplate getTemplateMarkupLegend()
	{
		return this.templateMarkupLegend;
	}

	protected PdfTemplate getTemplateCollaborators()
	{
		return this.templateCollaborators;
	}

	protected PdfTemplate getTemplateHistory()
	{
		return this.templateHistory;
	}

	protected PdfTemplate getTemplateDecKanGL()
	{
		return this.templateDecKanGL;
	}

	private void setTemplateStory(PdfTemplate templateStory)
	{
		this.templateStory = templateStory;
	}

	private void setTemplateNotes(PdfTemplate templateNotes)
	{
		this.templateNotes = templateNotes;
	}

	private void setTemplateMarkupLegend(PdfTemplate templateMarkupLegend)
	{
		this.templateMarkupLegend = templateMarkupLegend;
	}

	private void setTemplateCollaborators(PdfTemplate templateCollaborators)
	{
		this.templateCollaborators = templateCollaborators;
	}

	private void setTemplateHistory(PdfTemplate templateHistory)
	{
		this.templateHistory = templateHistory;
	}

	private void setTemplateDecKanGL(PdfTemplate templateDecKanGL)
	{
		this.templateDecKanGL = templateDecKanGL;
	}

}
