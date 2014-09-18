/* @(#)HeadlineNodePublication.java
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

import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fms.pdf.PdfUtil;
import com.flywheelms.library.fms.pdf.publication.image_cache.HeadlineNodePublicationImageCache;
import com.flywheelms.library.fms.pdf.publication.section.PdfSectionCollaborators;
import com.flywheelms.library.fms.pdf.publication.section.PdfSectionDecKanGL;
import com.flywheelms.library.fms.pdf.publication.section.PdfSectionHistory;
import com.flywheelms.library.fms.pdf.publication.section.PdfSectionModificationMarkupLegend;
import com.flywheelms.library.fms.pdf.publication.section.PdfSectionNotes;
import com.flywheelms.library.fms.pdf.publication.section.PdfSectionStory;
import com.flywheelms.library.fms.pdf.publication.section.PdfSectionTableOfContents;
import com.flywheelms.library.fms.pdf.publication.section.PdfSectionTitlePage;
import com.flywheelms.library.fms.wizard.step.FmsNodePublishingContentSelectionWizardStepView;
import com.flywheelms.library.fse.model.FseDocument;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.BadPdfFormatException;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HeadlineNodePublication {
	private final FmmHeadlineNodeImpl headlineNode;
	private final FmsNodePublishingContentSelectionWizardStepView contentSelectionView;
	private final FseDocument fseDocumentForPublication;

	private Document document;
	private PdfWriter writer;
	private HeadlineNodePublicationImageCache imageCache;
	private HeadlineNodePdfPageEventHandler headlineNodePdfPageEventHandler;
	private ByteArrayOutputStream documentOutputStream;
	private PdfSectionTableOfContents tableOfContents;

	public HeadlineNodePublication(FmmHeadlineNodeImpl headlineNode, FmsNodePublishingContentSelectionWizardStepView contentSelectionView) {
		super();
		this.headlineNode = headlineNode;
		this.fseDocumentForPublication = headlineNode.getFseDocumentForPublication();
		this.contentSelectionView = contentSelectionView;
	}

	public ByteArrayOutputStream buildPdf() throws DocumentException, IOException {
		initPdfObjects();
		buildPdfOutputStream();
		return getDocumentOutputStream();
	}

	private void buildPdfOutputStream() throws BadPdfFormatException, DocumentException, IOException {
		getWriter().open();
		getDocument().open();
		writeContent();
		getDocument().close();
		getWriter().close();

		HeadlineNodePublicationFooter.addFooter(getDocumentOutputStream(), getHeadlineNode().getAbbreviatedNodeIdString());
		getDocumentOutputStream().close();
	}

	private void initPdfObjects() throws DocumentException {
		// construct
		HeadlineNodePublicationImageCache newImageCache = new HeadlineNodePublicationImageCache(this);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Document newDocument = PdfUtil.newDocument();
		HeadlineNodePdfPageEventHandler newHeadlineNodePdfPageEventHandler = new HeadlineNodePdfPageEventHandler(getHeadlineNode(), newImageCache);
		PdfWriter newWriter = PdfWriter.getInstance(newDocument, byteArrayOutputStream);
		newWriter.setPageEvent(newHeadlineNodePdfPageEventHandler);

		// set
		setDocumentOutputStream(byteArrayOutputStream);
		setDocument(newDocument);
		setWriter(newWriter);
		setImageCache(newImageCache);
		setHeadlineNodePdfPageEventHandler(newHeadlineNodePdfPageEventHandler);
	}

	private void addSection(HeadlineNodePublicationSection section) throws DocumentException, IOException {
		getHeadlineNodePdfPageEventHandler().setSectionTypeLabel(section.getHeaderLabel());
		section.writeContent();
		getDocument().newPage();
	}

	private void writeContent() throws BadPdfFormatException, DocumentException, IOException {
		addTitlePage();
		addTableOfContents();

		ParagraphDocumentHeaderContentProvider headerContentProvider = null;
		if (getContentSelectionView().getContentModification().isChecked()) {
			headerContentProvider = new ParagraphDocumentHeaderContentProvider(this);
			getHeadlineNodePdfPageEventHandler().setContentProvider(headerContentProvider);
		}

		addStory(headerContentProvider);
		addNotes(headerContentProvider);

		getHeadlineNodePdfPageEventHandler().setContentProvider(null);// get rid of this headline content provider

		addMarkupLegend();
		addHistory();
		addCollaborators();
		addDecKanGL();

	}

	private void addDecKanGL() throws DocumentException, IOException {
		if (getContentSelectionView().getDecKanGl().isChecked()) {
			if (hasTableOfContents()) {
				getTableOfContents().setPageNumberDecKanGL(getWriter().getPageNumber());
			}
			addSection(new PdfSectionDecKanGL(this));
		}
	}

	private void addCollaborators() throws DocumentException, IOException {
		if (getContentSelectionView().getContributors().isChecked()) {
			if (hasTableOfContents()) {
				getTableOfContents().setPageNumberCollaborators(getWriter().getPageNumber());
			}
			addSection(new PdfSectionCollaborators(this));
		}
	}

	private void addHistory() throws DocumentException, IOException {
		if (getContentSelectionView().getHistory().isChecked()) {
			if (hasTableOfContents()) {
				getTableOfContents().setPageNumberHistory(getWriter().getPageNumber());
			}
			addSection(new PdfSectionHistory(this));
		}
	}

	private void addMarkupLegend() throws DocumentException, IOException {
		if (getContentSelectionView().getContentModification().isChecked()) {
			if (hasTableOfContents()) {
				getTableOfContents().setPageNumberMarkupLegend(getWriter().getPageNumber());
			}
			addSection(new PdfSectionModificationMarkupLegend(this));
		}
	}

	private void addNotes(ParagraphDocumentHeaderContentProvider headerContentProvider) throws DocumentException, IOException {
		if (getContentSelectionView().getNotes().isChecked()) {
			if (hasTableOfContents()) {
				getTableOfContents().setPageNumberNotes(getWriter().getPageNumber());
			}
			addSection(new PdfSectionNotes(this, headerContentProvider));

		}
	}

	private void addStory(ParagraphDocumentHeaderContentProvider headerContentProvider) throws DocumentException, IOException {
		if (hasTableOfContents()) {
			getTableOfContents().setPageNumberStory(getWriter().getPageNumber());
		}
		addSection(new PdfSectionStory(this, headerContentProvider));
	}

	private void addTableOfContents() throws DocumentException, IOException {
		if (getContentSelectionView().getTableOfContents().isChecked()) {
			PdfSectionTableOfContents toc = new PdfSectionTableOfContents(this);
			setTableOfContents(toc);
			addSection(toc);
		}
	}

	private void addTitlePage() throws DocumentException, IOException {
		getHeadlineNodePdfPageEventHandler().setEnabled(false);

		if (getContentSelectionView().getTitlePage().isChecked()) {
			addSection(new PdfSectionTitlePage(this));
		}

		getHeadlineNodePdfPageEventHandler().setEnabled(true);
	}

	protected boolean isShowContentModifcationMarkup() {
		return getContentSelectionView().getContentModification().isChecked();
	}

	protected boolean isShowParagraphNumbering() {
		return getContentSelectionView().getParagraphNumbering().isChecked();
	}

	protected boolean isShowParagraphStyle() {
		return getContentSelectionView().getLocks().isChecked();
	}

	protected FmmHeadlineNodeImpl getHeadlineNode() {
		return this.headlineNode;
	}

	protected FseDocument getFseDocumentForPublication() {
		return this.fseDocumentForPublication;
	}

	protected FmsNodePublishingContentSelectionWizardStepView getContentSelectionView() {
		return this.contentSelectionView;
	}

	protected ByteArrayOutputStream getDocumentOutputStream() {
		return this.documentOutputStream;
	}

	private void setDocumentOutputStream(ByteArrayOutputStream documentOutputStream) {
		this.documentOutputStream = documentOutputStream;
	}

	protected Document getDocument() {
		return this.document;
	}

	private void setDocument(Document document) {
		this.document = document;
	}

	protected PdfWriter getWriter() {
		return this.writer;
	}

	protected PdfContentByte getDirectContent() {
		return getWriter().getDirectContent();
	}

	private void setWriter(PdfWriter writer) {
		this.writer = writer;
	}

	protected HeadlineNodePublicationImageCache getImageCache() {
		return this.imageCache;
	}

	private void setImageCache(HeadlineNodePublicationImageCache imageCache) {
		this.imageCache = imageCache;
	}

	protected HeadlineNodePdfPageEventHandler getHeadlineNodePdfPageEventHandler() {
		return this.headlineNodePdfPageEventHandler;
	}

	private void setHeadlineNodePdfPageEventHandler(HeadlineNodePdfPageEventHandler headlineNodePdfPageEventHandler) {
		this.headlineNodePdfPageEventHandler = headlineNodePdfPageEventHandler;
	}

	private boolean hasTableOfContents() {
		return getTableOfContents() != null;
	}

	private PdfSectionTableOfContents getTableOfContents() {
		return this.tableOfContents;
	}

	private void setTableOfContents(PdfSectionTableOfContents tableOfContents) {
		this.tableOfContents = tableOfContents;
	}

}
