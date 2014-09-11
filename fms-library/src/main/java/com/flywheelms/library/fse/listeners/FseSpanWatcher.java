/* @(#)FseSpanWatcher.java
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
package com.flywheelms.library.fse.listeners;

import android.text.SpanWatcher;
import android.text.Spannable;

import com.flywheelms.library.fse.views.FseParagraphView;

/*
 * Used to manage span change events. TRY TO KEEP THIS CLASS LIGHT-WEIGHT.
 */
public class FseSpanWatcher implements SpanWatcher {

	@Override
	public void onSpanAdded(Spannable aSpannableString, Object anObject, int aStartPosition, int anEndPosition) {
			//  ADAM CODE - needs logical review 
		// TODO if (fmsStoryParagraph.getStyles() == null) condition is a hack
		// onSpanAdded() gets fired when I manually add the span as intended
		// but the framework is calling it a 2nd time as a built in function of
		// the "replacing" of text
		// leverage styles are set upon adding so if the span has already been
		// added styles != null
		if (anObject.getClass().equals(FseParagraphView.class)) {
			FseParagraphView theFseParagraph = (FseParagraphView) anObject;
			if (theFseParagraph.getStyleFormattingSpanArray() == null) {
//				theFseParagraph.addFormatting(aSpannableString, aStartPosition, anEndPosition);
			}
		}
	}

	@Override
	public void onSpanRemoved(Spannable aSpannableString, Object anObject, int aStartPosition, int anEndPosition) {
//		if (anObject != null && anObject.getClass().equals(FseParagraph.class)) {
//			((FseParagraph) anObject).remove(aSpannableString);
//		}
	}

	@Override
	public void onSpanChanged(Spannable aSpannableString, Object anObject, int anOldStartPosition,
			int anOldEndPosition, int aNewStartPosition, int aNewEndPosition) {
//		if (anObject != null && FseParagraph.class.equals(anObject.getClass()))// when
//																			// you
//																			// remove
//																			// the
//																			// span
//																			// apparently
//																			// that
//																			// triggers
//																			// an
//																			// "onChange"
//																			// event
//		{
//			int theOldLength = anOldEndPosition - anOldStartPosition;
//			int theNewLength = aNewEndPosition - aNewStartPosition;
//			if (theNewLength == 0 || theOldLength > theNewLength) {
//				FseParagraph[] theParagraphArray = aSpannableString.getSpans(aNewStartPosition, aNewStartPosition,
//						FseParagraph.class);
//				if (theParagraphArray.length > 1) {
//					FseParagraph theRootParagraph = (FseParagraph) anObject;
//					int theEndPosition = 0;
//					for (FseParagraph theParagraph : theParagraphArray) {
//						if (!theParagraph.equals(theRootParagraph)) {
//							if (theParagraph.isIndependent()) {    // it got removed in a prior step
//								theParagraph.remove(aSpannableString);
//							} else if (theParagraph.isDescendant(theRootParagraph)) {
//								theEndPosition = Math.max(theEndPosition, aSpannableString.getSpanEnd(theParagraph));
//								theParagraph.remove(aSpannableString);
//							} else {
//								theEndPosition = Math.max(theEndPosition, aSpannableString.getSpanEnd(theRootParagraph));
//								theRootParagraph.remove(aSpannableString);
//								theRootParagraph = theParagraph;
//							}
//						}
//					}
//					aSpannableString.setSpan(
//							theRootParagraph,
//							aSpannableString.getSpanStart(theRootParagraph),
//							theRootParagraph.hasNext() ? aSpannableString.getSpanStart(theRootParagraph.getNext()) - 1
//									: theEndPosition, FlywheelStoryEditorFragment.SPAN_MODE);
//				}
//			} else {
//				((FseParagraph) anObject).updateStyles(aSpannableString, aNewStartPosition, aNewEndPosition);
//			}
//		}
	}
	
}
