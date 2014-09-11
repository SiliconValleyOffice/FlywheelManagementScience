/* @(#)FmsParagraphStyle.java
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
package com.flywheelms.library.fse.enumerator;

import android.text.Layout.Alignment;
import android.text.ParcelableSpan;
import android.text.Spanned;
import android.text.style.AlignmentSpan;
import android.text.style.LeadingMarginSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;

import com.flywheelms.library.fse.interfaces.FseParagraphContentView;
import com.flywheelms.library.fse.util.FseConfig;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;

import java.io.IOException;
import java.util.ArrayList;

public enum FseParagraphStyle {

	/*
	 * using a string for name, rather than a resource ID, so that we
	 * don't need a context to use this enumerator in various applications.  SDS
	 * 
	 * Only down side is that these names will be universal across all language
	 * implementations.
	 * 
	 * FseParagraphNumberingStyle.NONE is not a valid parameter.
	 */
	BODY_1 ("Body 1", 1, 2, FseParagraphStyleType.BODY, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_1, FseConfig.TOP_MARGIN__BODY_1, new ParcelableSpan[] {
			new RelativeSizeSpan(1.2F) }, 12, Font.NORMAL, Element.ALIGN_LEFT ),
	BODY_2 ("Body 2", 2, 2, FseParagraphStyleType.BODY, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_2, FseConfig.TOP_MARGIN__BODY_2, new ParcelableSpan[] {
			new RelativeSizeSpan(1.2F) }, 12, Font.NORMAL, Element.ALIGN_LEFT ),
	BODY_3 ("Body 3", 3, 2, FseParagraphStyleType.BODY, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_3, FseConfig.TOP_MARGIN__BODY_3, new ParcelableSpan[] {
			new RelativeSizeSpan(1.2F) }, 12, Font.NORMAL, Element.ALIGN_LEFT ),
	BODY_4 ("Body 4", 4, 2, FseParagraphStyleType.BODY, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_4, FseConfig.TOP_MARGIN__BODY_4, new ParcelableSpan[] {
			new RelativeSizeSpan(1.0F) }, 10, Font.NORMAL, Element.ALIGN_LEFT ),
	BODY_5 ("Body 5", 5, 2, FseParagraphStyleType.BODY, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_5, FseConfig.TOP_MARGIN__BODY_5, new ParcelableSpan[] {
			new RelativeSizeSpan(.9F) }, 9, Font.NORMAL, Element.ALIGN_LEFT ),
	BODY_6 ("Body 6", 6, 2, FseParagraphStyleType.BODY, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_6, FseConfig.TOP_MARGIN__BODY_6, new ParcelableSpan[] {
			new RelativeSizeSpan(.7F) }, 7, Font.NORMAL, Element.ALIGN_LEFT ),
	DRAWING_1 ("Image 1", 1, 2, FseParagraphStyleType.DRAWING,  FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_1, FseConfig.TOP_MARGIN__BODY_1, null, 12, Font.NORMAL, Element.ALIGN_LEFT ),
	DRAWING_2 ("Image 2", 2, 2, FseParagraphStyleType.DRAWING,  FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_2, FseConfig.TOP_MARGIN__BODY_2, null, 12, Font.NORMAL, Element.ALIGN_LEFT ),
	DRAWING_3 ("Image 3", 3, 2, FseParagraphStyleType.DRAWING,  FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_3, FseConfig.TOP_MARGIN__BODY_3, null, 12, Font.NORMAL, Element.ALIGN_LEFT ),
	DRAWING_4 ("Image 4", 4, 2, FseParagraphStyleType.DRAWING,  FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_4, FseConfig.TOP_MARGIN__BODY_4, null, 10, Font.NORMAL, Element.ALIGN_LEFT ),
	DRAWING_5 ("Image 5", 5, 2, FseParagraphStyleType.DRAWING,  FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_5, FseConfig.TOP_MARGIN__BODY_5, null, 9, Font.NORMAL, Element.ALIGN_LEFT ),
	DRAWING_6 ("Image 6", 6, 2, FseParagraphStyleType.DRAWING,  FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_6, FseConfig.TOP_MARGIN__BODY_6, null, 7, Font.NORMAL, Element.ALIGN_LEFT ),
	HEADING_1 ("Heading 1", 1, 1, FseParagraphStyleType.HEADING, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_0, FseConfig.TOP_MARGIN__HEADING_1, new ParcelableSpan[] {
			new StyleSpan(android.graphics.Typeface.BOLD),
			new RelativeSizeSpan(1.3F),
			new AlignmentSpan.Standard(Alignment.ALIGN_CENTER) }, 14, Font.BOLD, Element.ALIGN_CENTER ),
	HEADING_2 ("Heading 2", 2, 1, FseParagraphStyleType.HEADING, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_1, FseConfig.TOP_MARGIN__HEADING_2, new ParcelableSpan[] {
			new StyleSpan(android.graphics.Typeface.BOLD),
			new RelativeSizeSpan(1.3F),
			new LeadingMarginSpan.Standard(1) }, 13, Font.BOLD, Element.ALIGN_LEFT ),
	HEADING_3 ("Heading 3", 3, 1, FseParagraphStyleType.HEADING, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_2, FseConfig.TOP_MARGIN__HEADING_3, new ParcelableSpan[] {
			new StyleSpan(android.graphics.Typeface.BOLD),
			new RelativeSizeSpan(1.3F),
			new LeadingMarginSpan.Standard(2) }, 12, Font.BOLDITALIC, Element.ALIGN_LEFT ),
	HEADING_4 ("Heading 4", 4, 1, FseParagraphStyleType.HEADING, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_3, FseConfig.TOP_MARGIN__HEADING_4, new ParcelableSpan[] {
			new StyleSpan(android.graphics.Typeface.BOLD),
			new RelativeSizeSpan(1.2F),
			new LeadingMarginSpan.Standard(3) }, 11, Font.BOLDITALIC, Element.ALIGN_LEFT ),
	HEADING_5 ("Heading 5", 5, 1, FseParagraphStyleType.HEADING, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_4, FseConfig.TOP_MARGIN__HEADING_5, new ParcelableSpan[] {
			new StyleSpan(android.graphics.Typeface.BOLD),
			new RelativeSizeSpan(1.1F),
			new LeadingMarginSpan.Standard(4) }, 10, Font.BOLDITALIC, Element.ALIGN_LEFT),
	HEADING_6 ("Heading 6", 6, 1, FseParagraphStyleType.HEADING, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_5, FseConfig.TOP_MARGIN__HEADING_6, new ParcelableSpan[] {
			new StyleSpan(android.graphics.Typeface.BOLD),
			new RelativeSizeSpan(1.0F),
			new LeadingMarginSpan.Standard(5) }, 9, Font.BOLDITALIC, Element.ALIGN_LEFT ),
	IMAGE_1 ("Image 1", 1, 2, FseParagraphStyleType.IMAGE,  FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_1, FseConfig.TOP_MARGIN__BODY_1, null, 12, Font.NORMAL, Element.ALIGN_LEFT ),
	IMAGE_2 ("Image 2", 2, 2, FseParagraphStyleType.IMAGE,  FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_2, FseConfig.TOP_MARGIN__BODY_2, null, 12, Font.NORMAL, Element.ALIGN_LEFT ),
	IMAGE_3 ("Image 3", 3, 2, FseParagraphStyleType.IMAGE,  FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_3, FseConfig.TOP_MARGIN__BODY_3, null, 12, Font.NORMAL, Element.ALIGN_LEFT ),
	IMAGE_4 ("Image 4", 4, 2, FseParagraphStyleType.IMAGE,  FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_4, FseConfig.TOP_MARGIN__BODY_4, null, 10, Font.NORMAL, Element.ALIGN_LEFT ),
	IMAGE_5 ("Image 5", 5, 2, FseParagraphStyleType.IMAGE,  FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_5, FseConfig.TOP_MARGIN__BODY_5, null, 9, Font.NORMAL, Element.ALIGN_LEFT ),
	IMAGE_6 ("Image 6", 6, 2, FseParagraphStyleType.IMAGE,  FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_6, FseConfig.TOP_MARGIN__BODY_6, null, 7, Font.NORMAL, Element.ALIGN_LEFT ),
	LIST_1 ("List 1", 1, 2, FseParagraphStyleType.LIST, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_1, FseConfig.TOP_MARGIN__BODY_1, new ParcelableSpan[] {
			new RelativeSizeSpan(1.2F) }, 12, Font.NORMAL, Element.ALIGN_LEFT ),
	LIST_2 ("List 2", 2, 2, FseParagraphStyleType.LIST, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_2, FseConfig.TOP_MARGIN__BODY_2, new ParcelableSpan[] {
			new RelativeSizeSpan(1.2F) }, 12, Font.NORMAL, Element.ALIGN_LEFT ),
	LIST_3 ("List 3", 3, 2, FseParagraphStyleType.LIST, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_3, FseConfig.TOP_MARGIN__BODY_3, new ParcelableSpan[] {
			new RelativeSizeSpan(1.2F) }, 11, Font.NORMAL, Element.ALIGN_LEFT ),
	LIST_4 ("List 4", 4, 2, FseParagraphStyleType.LIST, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_4, FseConfig.TOP_MARGIN__BODY_4, new ParcelableSpan[] {
			new RelativeSizeSpan(1.0F) }, 10, Font.NORMAL, Element.ALIGN_LEFT ),
	LIST_5 ("List 5", 5, 2, FseParagraphStyleType.LIST, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_5, FseConfig.TOP_MARGIN__BODY_5, new ParcelableSpan[] {
			new RelativeSizeSpan(.9F) }, 9, Font.NORMAL, Element.ALIGN_LEFT ),
	LIST_6 ("List 6", 6, 2, FseParagraphStyleType.LIST, FseParagraphNumberingStyle.NONE, FseConfig.LEFT_MARGIN_6, FseConfig.TOP_MARGIN__BODY_6, new ParcelableSpan[] {
			new RelativeSizeSpan(.7F) }, 7, Font.NORMAL, Element.ALIGN_LEFT );
	
	static {
		initializeParagraphStyle(BODY_1, HEADING_1, LIST_1, BODY_2, BODY_1, BODY_1);
		initializeParagraphStyle(BODY_2, HEADING_2, LIST_2, BODY_3, BODY_1, BODY_2);
		initializeParagraphStyle(BODY_3, HEADING_3, LIST_3, BODY_4, BODY_2, BODY_3);
		initializeParagraphStyle(BODY_4, HEADING_4, LIST_4, BODY_5, BODY_3, BODY_4);
		initializeParagraphStyle(BODY_5, HEADING_5, LIST_5, BODY_6, BODY_4, BODY_5);
		initializeParagraphStyle(BODY_6, HEADING_6, LIST_6, BODY_6, BODY_5, BODY_6);
		initializeParagraphStyle(DRAWING_1, DRAWING_1, DRAWING_1, DRAWING_2, DRAWING_1, BODY_1);
		initializeParagraphStyle(DRAWING_2, DRAWING_2, DRAWING_2, DRAWING_3, DRAWING_1, BODY_2);
		initializeParagraphStyle(DRAWING_3, DRAWING_3, DRAWING_3, DRAWING_4, DRAWING_2, BODY_3);
		initializeParagraphStyle(DRAWING_4, DRAWING_4, DRAWING_4, DRAWING_5, DRAWING_3, BODY_4);
		initializeParagraphStyle(DRAWING_5, DRAWING_5, DRAWING_5, DRAWING_6, DRAWING_4, BODY_5);
		initializeParagraphStyle(DRAWING_6, DRAWING_6, DRAWING_6, DRAWING_6, DRAWING_5, BODY_6);
		initializeParagraphStyle(HEADING_1, HEADING_1, BODY_1, HEADING_2, HEADING_1, BODY_1);
		initializeParagraphStyle(HEADING_2, HEADING_2, BODY_2, HEADING_3, HEADING_1, BODY_2);
		initializeParagraphStyle(HEADING_3, HEADING_2, BODY_3, HEADING_4, HEADING_2, BODY_3);
		initializeParagraphStyle(HEADING_4, HEADING_2, BODY_4, HEADING_5, HEADING_3, BODY_4);
		initializeParagraphStyle(HEADING_5, HEADING_2, BODY_5, HEADING_6, HEADING_4, BODY_5);
		initializeParagraphStyle(HEADING_6, HEADING_2, BODY_6, HEADING_6, HEADING_5, BODY_6);
		initializeParagraphStyle(IMAGE_1, IMAGE_1, IMAGE_1, IMAGE_2, IMAGE_1, BODY_1);
		initializeParagraphStyle(IMAGE_2, IMAGE_2, IMAGE_2, IMAGE_3, IMAGE_1, BODY_2);
		initializeParagraphStyle(IMAGE_3, IMAGE_3, IMAGE_3, IMAGE_4, IMAGE_2, BODY_3);
		initializeParagraphStyle(IMAGE_4, IMAGE_4, IMAGE_4, IMAGE_5, IMAGE_3, BODY_4);
		initializeParagraphStyle(IMAGE_5, IMAGE_5, IMAGE_5, IMAGE_6, IMAGE_4, BODY_5);
		initializeParagraphStyle(IMAGE_6, IMAGE_6, IMAGE_6, IMAGE_6, IMAGE_5, BODY_6);
		initializeParagraphStyle(LIST_1, BODY_1, LIST_1, LIST_2, LIST_1, LIST_1);
		initializeParagraphStyle(LIST_2, BODY_2, LIST_2, LIST_3, LIST_1, LIST_2);
		initializeParagraphStyle(LIST_3, BODY_3, LIST_3, LIST_4, LIST_2, LIST_3);
		initializeParagraphStyle(LIST_4, BODY_4, LIST_4, LIST_5, LIST_3, LIST_4);
		initializeParagraphStyle(LIST_5, BODY_5, LIST_5, LIST_6, LIST_4, LIST_5);
		initializeParagraphStyle(LIST_6, BODY_6, LIST_6, LIST_6, LIST_5, LIST_6);
		int theIndex = 0;
		for (FseParagraphStyle theFseParagraphStyle : FseParagraphStyle.values()) {
			theFseParagraphStyle.setIndex(theIndex++);
		}
		initializeAllStyleLists();
	}
	
	public static void initializeAllStyleLists() {
		FseParagraphStyleType.HEADING.setStyleArray(new FseParagraphStyle[] {
				FseParagraphStyle.HEADING_1,
				FseParagraphStyle.HEADING_2,
				FseParagraphStyle.HEADING_3,
				FseParagraphStyle.HEADING_4,
				FseParagraphStyle.HEADING_5,
				FseParagraphStyle.HEADING_6
		});
		FseParagraphStyleType.BODY.setStyleArray(new FseParagraphStyle[] {
				FseParagraphStyle.BODY_1,
				FseParagraphStyle.BODY_2,
				FseParagraphStyle.BODY_3,
				FseParagraphStyle.BODY_4,
				FseParagraphStyle.BODY_5,
				FseParagraphStyle.BODY_6
		});
		FseParagraphStyleType.IMAGE.setStyleArray(new FseParagraphStyle[] {
				FseParagraphStyle.IMAGE_1,
				FseParagraphStyle.IMAGE_2,
				FseParagraphStyle.IMAGE_3,
				FseParagraphStyle.IMAGE_4,
				FseParagraphStyle.IMAGE_5,
				FseParagraphStyle.IMAGE_6
		});
		FseParagraphStyleType.DRAWING.setStyleArray(new FseParagraphStyle[] {
				FseParagraphStyle.DRAWING_1,
				FseParagraphStyle.DRAWING_2,
				FseParagraphStyle.DRAWING_3,
				FseParagraphStyle.DRAWING_4,
				FseParagraphStyle.DRAWING_5,
				FseParagraphStyle.DRAWING_6
		});
		FseParagraphStyleType.LIST.setStyleArray(new FseParagraphStyle[] {
				FseParagraphStyle.LIST_1,
				FseParagraphStyle.LIST_2,
				FseParagraphStyle.LIST_3,
				FseParagraphStyle.LIST_4,
				FseParagraphStyle.LIST_5,
				FseParagraphStyle.LIST_6
		});
	}
	
	private static final int SPAN_MODE = Spanned.SPAN_INCLUSIVE_INCLUSIVE;
	
	private int index;
	private final String name;
	private final int indentLevel;
	private final int hierarchyLevel;
	private final FseParagraphStyleType styleType;
	private final FseParagraphNumberingStyle numberingStyle;
	private final int leftMargin;
	private final int topMargin;
	private final ParcelableSpan[] formattingArray;
	private FseParagraphStyle promotionStyle;
	private FseParagraphStyle demotionStyle;
	private FseParagraphStyle indentStyle;
	private FseParagraphStyle outdentStyle;
	private FseParagraphStyle defaultNextStyle;
	private Font pdfFont;
	private final int pdfParagraphAlignment;
	
	private FseParagraphStyle(
			String aName,
			int anIndentLevel,
			int aHierarchyLevel,
			FseParagraphStyleType anFseParagraphStyleType,
			FseParagraphNumberingStyle aNumberingStyle,
			int aLeftMargin,
			int aTopMargin,
			ParcelableSpan[] aFormattingArray ,
			float aPdfFontSize,
			int aPdfFontStyle,
			int aPdfParagraphAlignment ) {
		this.name = aName;
		this.indentLevel = anIndentLevel;
		this.hierarchyLevel = aHierarchyLevel;
		this.styleType = anFseParagraphStyleType;
		this.numberingStyle = aNumberingStyle;
		this.leftMargin = aLeftMargin;
		this.topMargin = aTopMargin;
		this.formattingArray = aFormattingArray;
		try {
			this.pdfFont = new Font(BaseFont.createFont());
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.pdfFont.setSize(aPdfFontSize);
		this.pdfFont.setStyle(aPdfFontStyle);
		this.pdfParagraphAlignment = aPdfParagraphAlignment;
	}

	private static void initializeParagraphStyle(
			FseParagraphStyle anFseParagraphStyle,
			FseParagraphStyle aPromotionStyle,
			FseParagraphStyle aDemotionStyle,
			FseParagraphStyle anIndentStyle,
			FseParagraphStyle anOutdentStyle,
			FseParagraphStyle aDefaultNextStyle ) {
		anFseParagraphStyle.promotionStyle = aPromotionStyle;
		anFseParagraphStyle.demotionStyle = aDemotionStyle;
		anFseParagraphStyle.indentStyle = anIndentStyle;
		anFseParagraphStyle.outdentStyle = anOutdentStyle;
		anFseParagraphStyle.defaultNextStyle = aDefaultNextStyle;
	}

	public FseParagraphStyleType getStyleType() {
		return this.styleType;
	}
	
	public FseParagraphNumberingStyle getNumberingStyle() {
		return this.numberingStyle;
	}

	public int getLeftMargin() {
		return this.leftMargin;
	}

	public int getTopMargin() {
		return this.topMargin;
	}

	public FseParagraphStyle getPromotionStyle() {
		return this.promotionStyle;
	}

	public FseParagraphStyle getDemotionStyle() {
		return this.demotionStyle;
	}

	public FseParagraphStyle getIndentStyle() {
		return this.indentStyle;
	}

	public FseParagraphStyle getOutdentStyle() {
		return this.outdentStyle;
	}

	public FseParagraphStyle getDefaultNextStyle() {
		return this.defaultNextStyle;
	}

	public ParcelableSpan[] getFormattingArray() {
		return this.formattingArray;
	}

	public int getIndex() {
		return this.index;
	}

	public String getName() {
		return this.name;
	}

	private void setIndex(int anIndex) {
		this.index = anIndex;
	}

	@Override
	public String toString() {
		return this.name;
	}

	public ArrayList<FseParagraphStyle> getParagraphSpinnerOptions() {
		ArrayList<FseParagraphStyle> theParagraphStyleList = new ArrayList<FseParagraphStyle>();
		switch (this.getStyleType()) {
		case DRAWING :
			theParagraphStyleList.addAll(FseParagraphStyleType.DRAWING.getStyleList());
			break;
		case IMAGE :
			theParagraphStyleList.addAll(FseParagraphStyleType.IMAGE.getStyleList());
			break;
		default :
			theParagraphStyleList.addAll(FseParagraphStyleType.HEADING.getStyleList());
			theParagraphStyleList.addAll(FseParagraphStyleType.BODY.getStyleList());
			theParagraphStyleList.addAll(FseParagraphStyleType.LIST.getStyleList());
		}
		return theParagraphStyleList;
	}

	public boolean isDrawing() {
		return this.styleType.isDrawing();
	}

	public boolean isImage() {
		return this.styleType.isImage();
	}


	public boolean isText() {
		return this.styleType.isText();
	}

	public FseParagraphContentType getParagraphContentType() {
		return this.styleType.getParagraphContentType();
	}

	public FseParagraphContentType getContentType() {
		return this.styleType.getParagraphContentType();
	}	
	
	@SuppressWarnings("static-method")
	public int getSpanMode() {
		return SPAN_MODE;
	}
	
	public Class<? extends FseParagraphContentView> getContentViewClass() {
		return getContentType().getContentViewClass();
	}

	private int getIndentLevel() {
		return this.indentLevel;
	}

	private int getHierarchyLevel() {
		return this.hierarchyLevel;
	}

	public boolean isDescendentStyle(FseParagraphStyle aStyle) {
		return this.indentLevel > aStyle.getIndentLevel() || this.hierarchyLevel > aStyle.getHierarchyLevel();
	}

	public boolean isChildStyle(FseParagraphStyle aStyle) {
		return this.indentLevel - 1 == aStyle.getIndentLevel();
	}

	public boolean isParentStyle(FseParagraphStyle aStyle) {
		return this.indentLevel + 1 == aStyle.getIndentLevel() || this.hierarchyLevel < aStyle.getHierarchyLevel();
	}

	public boolean isHierarchyLevelLower(FseParagraphStyle aStyle) {
		return aStyle.getHierarchyLevel() < this.hierarchyLevel;
	}

	public boolean isIndentLevelLower(FseParagraphStyle aStyle) {
		return aStyle.getIndentLevel() < this.indentLevel;
	}
	
	public boolean isPeerBreak(FseParagraphStyle aStyle) {
		boolean isHierarchyBreak = isHierarchyLevelLower(aStyle);
		boolean isIndentBreak = isIndentLevelLower(aStyle);
		return isHierarchyBreak || isIndentBreak;
	}

	public boolean isPeerStyle(FseParagraphStyle aStyle) {
		return this.indentLevel == aStyle.getIndentLevel();
	}

	public static FseParagraphStyle getStyleForName(String aStyleName) {
		for(FseParagraphStyle theStyle : values()) {
			if(theStyle.getName().equals(aStyleName)) {
				return theStyle;
			}
		}
		return null;
	}
	
	public Font getpdfFont() {
		return this.pdfFont;
	}
	
	public int getPdfParagraphAlignment() {
		return this.pdfParagraphAlignment;
	}
	
}
