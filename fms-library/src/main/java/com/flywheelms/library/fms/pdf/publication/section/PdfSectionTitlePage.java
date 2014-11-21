/* @(#)PdfSectionTitlePage.java
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

import com.flywheelms.library.fms.activity.FmsActivity;
import com.flywheelms.library.fms.pdf.PdfColors;
import com.flywheelms.library.fms.pdf.PdfFonts;
import com.flywheelms.library.fms.pdf.PdfUtil;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublication;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublicationSection;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import java.io.IOException;

public class PdfSectionTitlePage extends HeadlineNodePublicationSection
{

	public PdfSectionTitlePage(HeadlineNodePublication headlineNodePublication)
	{
		super(headlineNodePublication);
	}

	@Override
	public String getHeaderLabel()
	{
		return "";
	}

	@Override
	protected void writeContent() throws DocumentException, IOException
	{
		PdfPTable table = PdfUtil.newTable(540, new int[] { 540 });
		table.getDefaultCell().setPadding(6f);

		table.addCell(buildCellOrganizationName());
		table.addCell(buildCellNodeType());
		table.addCell(new Paragraph(getHeadlineNode().getHeadline(), PdfFonts.BOLD_24_BLUE));
		table.addCell(buildCellDecKanGL());

		getDocument().add(table);
	}

	@SuppressWarnings("static-method")
	private PdfPCell buildCellOrganizationName()
	{
		String organizationName = FmsActivity.getActiveDatabaseMediator().getFmmOwner().getName();
		PdfPCell cell = PdfUtil.newCell(organizationName, PdfFonts.BOLD_24_WHITE);
		cell.setPaddingLeft(4f);
		cell.setPaddingBottom(8f);
		cell.setBackgroundColor(PdfColors.DARK_BLUE);
		return cell;
	}

	private PdfPCell buildCellNodeType()
	{
		String nodeType = getHeadlineNode().getNodeTypeName();
		PdfPCell cell = PdfUtil.newCell(nodeType, PdfFonts.PLAIN_20);
		cell.setBorderWidthBottom(1f);
		cell.setPadding(6f);
		return cell;
	}

	private PdfPCell buildCellDecKanGL()
	{
		PdfPCell cell = new PdfPCell(getImageCache().getDecKanGLTitlePage());
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPaddingTop(72f);
		cell.setBorder(0);
		return cell;
	}

}
