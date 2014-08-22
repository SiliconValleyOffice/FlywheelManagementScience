/* @(#)ImageCacheKeys.java
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

package com.flywheelms.library.fms.pdf.publication.image_cache;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

import android.util.Log;

import com.flywheelms.library.deckangl.enumerator.DecKanGlAnnotatedGlyphSize;
import com.flywheelms.library.deckangl.enumerator.DecKanGlDecoratedGlyphSize;
import com.flywheelms.library.fmm.node.impl.headline.FmmHeadlineNodeImpl;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublication;
import com.flywheelms.library.fms.pdf.publication.HeadlineNodePublicationComposite;
import com.flywheelms.library.fse.enumerator.FseContentModificationState;
import com.flywheelms.library.fse.enumerator.FseLockModificationState;
import com.flywheelms.library.fse.enumerator.FseNumberingModificationState;
import com.flywheelms.library.fse.enumerator.FseSequenceModificationState;
import com.flywheelms.library.fse.enumerator.FseStyleModificationState;
import com.flywheelms.library.fse.enumerator.ModificationState;
import com.flywheelms.library.fse.model.FseParagraph;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfTemplate;

public final class HeadlineNodePublicationImageCache extends HeadlineNodePublicationComposite {

	private static final String TAG = HeadlineNodePublicationImageCache.class.getCanonicalName();

	private static final String PREFIX_MODIFICATION_SUMMARY = "modificationSummary";
	private static final String PREFIX_MODIFICATION_STATE = "modificationState";

	private final HashMap<StaticImageBuilder, Image> imageMapStatic = new HashMap<StaticImageBuilder, Image>();
	private final HashMap<HeadlineNodeImageBuilder, Image> imageMapHeadlineNode = new HashMap<HeadlineNodeImageBuilder, Image>();
	private final HashMap<String, Image> imageMapComposite = new HashMap<String, Image>();
	private final HashMap<ModificationState, Image> imageMapModificationState = new HashMap<ModificationState, Image>();

	private Image[] summaryIcons = null;

	public HeadlineNodePublicationImageCache(HeadlineNodePublication headlineNodePublication) {
		super(headlineNodePublication);
	}

	private HashMap<StaticImageBuilder, Image> getImageMapStatic() {
		return this.imageMapStatic;
	}

	private HashMap<HeadlineNodeImageBuilder, Image> getImageMapHeadlineNode() {
		return this.imageMapHeadlineNode;
	}

	private HashMap<String, Image> getImageMapComposite() {
		return this.imageMapComposite;
	}

	private HashMap<ModificationState, Image> getImageMapModificationState() {
		return this.imageMapModificationState;
	}

	public Image getHeaderModificationSummarySequence() {
		return getImageStatic(StaticImageBuilder.HEADER_SUMMARY_ICON_MOD_STATE_SEQUENCE);
	}

	public Image getHeaderModificationSummaryLock() {
		return getImageStatic(StaticImageBuilder.HEADER_SUMMARY_ICON_MOD_STATE_LOCK);
	}

	public Image getHeaderModificationSummaryContentModified() {
		return getImageStatic(StaticImageBuilder.HEADER_SUMMARY_ICON_MOD_STATE_CONTENT_MODIFIED);
	}

	public Image getHeaderModificationSummaryContentNew() {
		return getImageStatic(StaticImageBuilder.HEADER_SUMMARY_ICON_MOD_STATE_CONTENT_NEW);
	}

	public Image getHeaderModificationSummaryNumbering() {
		return getImageStatic(StaticImageBuilder.HEADER_SUMMARY_ICON_MOD_STATE_NUMBERING);
	}

	public Image getHeaderModificationSummaryStyle() {
		return getImageStatic(StaticImageBuilder.HEADER_SUMMARY_ICON_MOD_STATE_STYLE);
	}

	public Image getHeaderAsset() {
		return getImageHeadlineNode(HeadlineNodeImageBuilder.HEADER_ASSET);
	}

	public Image getDecKanGLTitlePage() {
		return getImageHeadlineNode(HeadlineNodeImageBuilder.DECKANGL_TITLE_PAGE);
	}

	public Image getDecKanGLAnnotated() {
		return getImageHeadlineNode(HeadlineNodeImageBuilder.DECKANGL_ANNOTATED);
	}

	private Image getImageStatic(StaticImageBuilder imageBuilder) {
		Image image = getImageMapStatic().get(imageBuilder);
		if (image == null) {
			try {
				image = imageBuilder.buildImage();
			} catch (IOException e) {
				Log.e(TAG, "Failed to load image:" + imageBuilder.name(), e);
			}
			getImageMapStatic().put(imageBuilder, image);
		}
		return image;
	}

	private Image getImageHeadlineNode(HeadlineNodeImageBuilder imageBuilder) {
		Image image = getImageMapHeadlineNode().get(imageBuilder);
		if (image == null) {
			try {
				image = imageBuilder.buildImage(getHeadlineNode());
			} catch (IOException e) {
				Log.e(TAG, "Failed to load image:" + imageBuilder.name(), e);
			}
			getImageMapHeadlineNode().put(imageBuilder, image);
		}
		return image;
	}

	public Image getImage(ModificationState modificationState) {
		Image image = getImageMapModificationState().get(modificationState);
		if (image == null) {
			image = ImageCacheUtil.getImage(modificationState.getPdfMarkupDrawableResourceId());
			getImageMapModificationState().put(modificationState, image);
		}
		return image;
	}

	private enum HeadlineNodeImageBuilder {
		HEADER_ASSET {
			@Override
			public Image buildImage(FmmHeadlineNodeImpl headlineNode) throws IOException {
				return ImageCacheUtil.getImage(headlineNode.getDecKanGlGlyph().getDecoratedNounBitmap(DecKanGlDecoratedGlyphSize.TINY));
			}
		},

		DECKANGL_TITLE_PAGE {
			@Override
			public Image buildImage(FmmHeadlineNodeImpl headlineNode) throws IOException {
				return ImageCacheUtil.getImage(headlineNode.getDecKanGlGlyph().getDecoratedNounBitmap(DecKanGlDecoratedGlyphSize.LARGE));
			}
		},

		DECKANGL_ANNOTATED {
			@Override
			public Image buildImage(FmmHeadlineNodeImpl headlineNode) throws IOException {
				Image pngImage = ImageCacheUtil.getImage(headlineNode.getDecKanGlGlyph().getAnnotatedGlyphBitmap(DecKanGlAnnotatedGlyphSize.MEDIUM));
				float desiredWidth = 540f;
				pngImage.scaleAbsoluteWidth(desiredWidth);
				pngImage.scaleAbsoluteHeight(pngImage.getHeight() * desiredWidth / pngImage.getWidth());
				pngImage.setAlignment(Element.ALIGN_CENTER);
				return pngImage;
			}
		};

		public abstract Image buildImage(FmmHeadlineNodeImpl headlineNode) throws IOException;

	}

	private enum StaticImageBuilder {

		HEADER_SUMMARY_ICON_MOD_STATE_SEQUENCE {
			@Override
			public Image buildImage() throws IOException {
				return ImageCacheUtil.getImageScaledToFit(FseSequenceModificationState.BLOCK_MOVE_DOWN.getSummaryDrawableResourceId(), HEADER_SUMMARY_ICON_WIDTH, HEADER_SUMMARY_ICON_HEIGHT);
			}
		},

		HEADER_SUMMARY_ICON_MOD_STATE_LOCK {
			@Override
			public Image buildImage() throws IOException {
				return ImageCacheUtil.getImageScaledToFit(FseLockModificationState.MODIFIED.getSummaryDrawableResourceId(), HEADER_SUMMARY_ICON_WIDTH, HEADER_SUMMARY_ICON_HEIGHT);
			}
		},

		HEADER_SUMMARY_ICON_MOD_STATE_CONTENT_MODIFIED {
			@Override
			public Image buildImage() throws IOException {
				return ImageCacheUtil.getImageScaledToFit(FseContentModificationState.MODIFIED.getSummaryDrawableResourceId(), HEADER_SUMMARY_ICON_WIDTH, HEADER_SUMMARY_ICON_HEIGHT);
			}
		},

		HEADER_SUMMARY_ICON_MOD_STATE_CONTENT_NEW {
			@Override
			public Image buildImage() throws IOException {
				return ImageCacheUtil.getImageScaledToFit(FseContentModificationState.NEW.getSummaryDrawableResourceId(), HEADER_SUMMARY_ICON_WIDTH, HEADER_SUMMARY_ICON_HEIGHT);
			}
		},

		HEADER_SUMMARY_ICON_MOD_STATE_NUMBERING {
			@Override
			public Image buildImage() throws IOException {
				return ImageCacheUtil.getImageScaledToFit(FseNumberingModificationState.MODIFIED.getSummaryDrawableResourceId(), HEADER_SUMMARY_ICON_WIDTH, HEADER_SUMMARY_ICON_HEIGHT);
			}
		},

		HEADER_SUMMARY_ICON_MOD_STATE_STYLE {
			@Override
			public Image buildImage() throws IOException {
				return ImageCacheUtil.getImageScaledToFit(FseStyleModificationState.MODIFIED.getSummaryDrawableResourceId(), HEADER_SUMMARY_ICON_WIDTH, HEADER_SUMMARY_ICON_HEIGHT);
			}
		};

		private static final float HEADER_SUMMARY_ICON_WIDTH = 30f;
		private static final float HEADER_SUMMARY_ICON_HEIGHT = 15f;

		public abstract Image buildImage() throws IOException;

	}

	public Image getModificationStateMarkupImage(ModificationState modificationState) {
		String key = PREFIX_MODIFICATION_STATE + modificationState.getClass().getName() + modificationState.toString();
		Image image = getImageMapComposite().get(key);
		if (image == null) {
			image = ImageCacheUtil.getImage(modificationState.getPdfMarkupDrawableResourceId());
			getImageMapComposite().put(key, image);
		}
		return image;
	}

	public Image getModificationSummaryImage(boolean[] pageModificationIcons) {
		String key = PREFIX_MODIFICATION_SUMMARY + Arrays.toString(pageModificationIcons);
		Image summaryImage = getImageMapComposite().get(key);
		if (summaryImage == null) {
			summaryImage = buildSummaryImage(pageModificationIcons);
			getImageMapComposite().put(key, summaryImage);
		}
		return summaryImage;
	}

	private Image buildSummaryImage(boolean[] pageModificationIcons) {
		Image summaryImage = null;
		PdfTemplate template = getDirectContent().createTemplate(6 * StaticImageBuilder.HEADER_SUMMARY_ICON_WIDTH, StaticImageBuilder.HEADER_SUMMARY_ICON_HEIGHT);
		for (int i = 0; i < 6; i++) {
			if (pageModificationIcons[i]) {
				Image image = getSummaryIcons()[i];
				image.setAbsolutePosition(StaticImageBuilder.HEADER_SUMMARY_ICON_WIDTH * i, 0f);// this operates on the instance, so I assume you'd really only need to set it first time (and potentially clear it for Image reuse elsewhere)
				try {
					template.addImage(image);
				} catch (DocumentException e) {
					Log.e(TAG, "Failed to add Modification State summary icon template.", e);
				}
			}
		}
		try {
			summaryImage = Image.getInstance(template);
		} catch (BadElementException e) {
			Log.e(TAG, "Failed to convert Modification State summary template to image.", e);
		}
		return summaryImage;
	}

	private Image[] getSummaryIcons() {
		if (this.summaryIcons == null) {
			this.summaryIcons = new Image[] { getHeaderModificationSummarySequence(), getHeaderModificationSummaryLock(), getHeaderModificationSummaryContentModified(), getHeaderModificationSummaryContentNew(), getHeaderModificationSummaryNumbering(),
					getHeaderModificationSummaryStyle() };
		}
		return this.summaryIcons;
	}

	public Image getMarkupImage(FseParagraph fseParagraph, int height) {
		int adjustedHeight = Math.max(height, 20);
		String key = PREFIX_MODIFICATION_STATE + buildKey(fseParagraph, adjustedHeight);
		Image image = getImageMapComposite().get(key);
		if (image == null) {
			image = buildMarkupImage(fseParagraph, adjustedHeight);
			getImageMapComposite().put(key, image);
		}
		return image;
	}

	@SuppressWarnings("static-method")
	public String buildKey(FseParagraph fseParagraph, int height) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(height);
		stringBuilder.append(fseParagraph.getSequenceModificationState().ordinal());
		stringBuilder.append(fseParagraph.getLockModificationState().ordinal());
		stringBuilder.append(fseParagraph.getContentModificationState().ordinal());
		stringBuilder.append(fseParagraph.getNumberingModificationState().ordinal());
		stringBuilder.append(fseParagraph.getStyleModificationState().ordinal());
		return stringBuilder.toString();
	}

	private Image buildMarkupImage(FseParagraph fseParagraph, int height) {
		PdfTemplate template = PdfTemplate.createTemplate(getWriter(), 44, height);
		addModificationMarkupToTemplate(template, fseParagraph.getSequenceModificationState(), height, 0, 0);
		addModificationMarkupToTemplate(template, fseParagraph.getLockModificationState(), height, 6, 0);
		addModificationMarkupToTemplate(template, fseParagraph.getContentModificationState(), null, 12, (height - 20) / 2);
		addModificationMarkupToTemplate(template, fseParagraph.getNumberingModificationState(), height, 32, 0);
		addModificationMarkupToTemplate(template, fseParagraph.getStyleModificationState(), height, 38, 0);
		Image image = null;
		try {
			image = Image.getInstance(template);
		} catch (BadElementException e) {
			Log.e(TAG, "Failed to build Modification State Markup image.", e);
		}
		return image;
	}

	private void addModificationMarkupToTemplate(PdfTemplate template, ModificationState modificationState, Integer height, int x, int y) {
		if (!modificationState.isUnchanged()) {
			Image image = getImageCache().getModificationStateMarkupImage(modificationState);
			image.setAbsolutePosition(x, y);
			if (height != null) {
				image.scaleAbsoluteHeight(height);
			}
			try {
				template.addImage(image);
			} catch (DocumentException e) {
				Log.e(TAG, "Failed to add Modification State image to template.", e);
			}
		}
	}

}
