/* @(#)ParagraphEditorPdfHeaderEventHelper.java
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

import android.util.Log;

import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fms.pdf.PdfColors;
import com.flywheelms.library.fms.pdf.PdfFonts;
import com.flywheelms.library.fms.pdf.PdfUtil;
import com.flywheelms.library.fms.pdf.publication.image_cache.HeadlineNodePublicationImageCache;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;

public class HeadlineNodePdfPageEventHandler extends PdfPageEventHelper
{
	private static final String TAG = HeadlineNodePdfPageEventHandler.class.getCanonicalName();

	private final FmmHeadlineNodeImpl headlineNode;
	private final HeadlineNodePublicationImageCache imageCache;
	private PdfContentByte directContent;
	private String sectionTypeLabel;
	private boolean enabled;
	private HeaderContentProvider contentProvider;

	public HeadlineNodePdfPageEventHandler(FmmHeadlineNodeImpl headlineNode, HeadlineNodePublicationImageCache imageCache)
	{
		super();
		this.headlineNode = headlineNode;
		this.imageCache = imageCache;
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document)
	{
		if (isEnabled())
		{
			try
			{
				PdfContentByte theDirectContent = writer.getDirectContent();
				setDirectContent(theDirectContent);
				buildHeaderTable().writeSelectedRows(0, -1, 36, 756, theDirectContent);
			}
			catch (DocumentException e)
			{
				Log.e(TAG, "DocumentException onEndPage()", e);
			}
			catch (IOException e)
			{
				Log.e(TAG, "IOException onEndPage()", e);
			}
		}
	}

	private PdfPTable buildHeaderTable() throws DocumentException, IOException
	{
		PdfPTable table = PdfUtil.newTable(540, new int[] { 1 });
		table.getDefaultCell().setPaddingBottom(4f);
		table.addCell(buildContentCompanyRow());
		table.addCell(buildContentHeaderDetails());
		return table;
	}

	private PdfPTable buildContentCompanyRow() throws IOException, DocumentException
	{
		boolean hasAdditionalContentProvider = hasContentProvider();
		PdfPTable table = PdfUtil.newTable(540, new int[] { 324, 216 });

		PdfPCell companyCell = buildCellCompany();
		companyCell.setColspan(hasAdditionalContentProvider ? 1 : 2);
		table.addCell(companyCell);

		if (hasAdditionalContentProvider)
		{
			table.addCell(getContentProvider().buildCell());
		}

		return table;
	}

	protected PdfPCell buildCellCompany()
	{
		String companyName = FmmDatabaseMediator.getActiveMediator().getFmmOwner().getName();
		PdfPCell cell = PdfUtil.newCell(companyName, PdfFonts.BOLD_12_WHITE);
		cell.setBackgroundColor(PdfColors.DARK_BLUE);
		cell.setPaddingLeft(6f);
		cell.setPaddingBottom(6f);
		return cell;
	}

	private PdfPTable buildContentHeaderDetails()
	{
		PdfPTable table = PdfUtil.newTable(540, new int[] { 44, 496 });
		table.getDefaultCell().setPaddingBottom(4f);
		table.addCell(buildCellAssetImage());
		table.addCell(buildTableAssetDetails());
		return table;
	}

	private PdfPCell buildCellAssetImage()
	{
		PdfPCell cell = new PdfPCell(getImageCache().getHeaderAsset());
		cell.setBackgroundColor(BaseColor.WHITE);
		cell.setBorder(0);
		cell.setPaddingTop(2f);
		cell.setPaddingLeft(4f);
		cell.setPaddingRight(4f);
		return cell;
	}

	private PdfPTable buildTableAssetDetails()
	{
		PdfPTable table = PdfUtil.newTable(496, new int[] { 1, 1 });
		table.addCell(buildCellNounName());
		table.addCell(buildCellSectionTypeLabel());
		table.addCell(buildCellHeadline());
		return table;
	}

	private PdfPCell buildCellNounName()
	{
		PdfPCell cell = PdfUtil.newCell(getHeadlineNode().getDecKanGlNounName(), PdfFonts.PLAIN_10);
		cell.setBorderWidthBottom(1f);
		cell.setBorderColor(BaseColor.GRAY);
		cell.setPaddingTop(4f);
		cell.setPaddingLeft(4f);
		cell.setPaddingBottom(6f);
		return cell;
	}

	private PdfPCell buildCellSectionTypeLabel()
	{
		PdfPCell cell = PdfUtil.newCell(getSectionTypeLabel(), PdfFonts.ITALIC_10_GRAY);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setBorderColor(BaseColor.GRAY);
		cell.setBorderWidthBottom(1f);
		cell.setPaddingTop(4f);
		cell.setPaddingRight(4f);
		cell.setPaddingBottom(6f);
		return cell;
	}

	private PdfPCell buildCellHeadline()
	{
		PdfPCell cell = PdfUtil.newCell(getHeadlineNode().getHeadline(), PdfFonts.BOLD_12_BLUE);
		cell.setColspan(2);
		cell.setPaddingTop(2f);
		cell.setPaddingLeft(4f);
		return cell;
	}

	private FmmHeadlineNodeImpl getHeadlineNode()
	{
		return this.headlineNode;
	}

	protected PdfContentByte getDirectContent()
	{
		return this.directContent;
	}

	private void setDirectContent(PdfContentByte directContent)
	{
		this.directContent = directContent;
	}

	private String getSectionTypeLabel()
	{
		return this.sectionTypeLabel;
	}

	public void setSectionTypeLabel(String sectionTypeLabel)
	{
		this.sectionTypeLabel = sectionTypeLabel;
	}

	public void setEnabled(boolean aBoolean)
	{
		this.enabled = aBoolean;
	}

	public boolean isEnabled()
	{
		return this.enabled;
	}

	public boolean hasContentProvider()
	{
		return getContentProvider() != null;
	}

	public HeaderContentProvider getContentProvider()
	{
		return this.contentProvider;
	}

	public void setContentProvider(HeaderContentProvider contentProvider)
	{
		this.contentProvider = contentProvider;
	}

	public HeadlineNodePublicationImageCache getImageCache()
	{
		return this.imageCache;
	}

}
