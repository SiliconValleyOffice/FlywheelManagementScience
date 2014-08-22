/* @(#)GhettoStyleUtils.java
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
package com.flywheelms.library.fse.util;

import java.util.HashMap;

import android.text.Editable;
import android.text.Spannable;
import android.widget.EditText;

import com.flywheelms.library.fse.views.FseParagraphView;

@Deprecated
@SuppressWarnings("nls")
public class GhettoStyleUtils {

	public static void printSpanSummary(EditText editText) {
		Editable text = editText.getText();
		String string = text.toString();
		FseParagraphView[] allSpans = text.getSpans(0, text.length(),
				FseParagraphView.class);
		System.out
				.println("\n\n*******************************************\n\n");

		System.out.println(allSpans.length + " total FMS paragraph spans");
		HashMap<FseParagraphView, String> map = new HashMap<FseParagraphView, String>();
		for (int i = 0; i < allSpans.length; i++) {
			FseParagraphView span = allSpans[i];
			map.put(span, Integer.toString(i));

			StringBuffer sb = new StringBuffer();
			sb.append(i);
			sb.append("   ");
			sb.append(span.getStyle());
			sb.append("   ");
			sb.append(text.getSpanStart(span));
			sb.append("   ");
			sb.append(text.getSpanEnd(span));
			sb.append("   ");
			sb.append(text.getSpanFlags(span));
			System.out.println(sb);
		}

		System.out.println("-------------------------------------------");

		char[] charArray = string.toCharArray();
		for (int i = 0; i < charArray.length; i++) {
			FseParagraphView[] spans = text.getSpans(i, i,
					FseParagraphView.class);
			char c = charArray[i];
			StringBuffer stringBuffer = new StringBuffer(i + " :: "
					+ spans.length + " :: " + (c == '\n' ? "_" : c) + " :: ");
			for (FseParagraphView p : spans) {
				stringBuffer.append(map.get(p));
				stringBuffer.append(" ");
				stringBuffer.append(p.getStyle().toString());
			}
			// ParcelableSpan[] spans2 = text.getSpans(i, i,
			// ParcelableSpan.class);
			// for (ParcelableSpan p : spans2)
			// {
			// stringBuffer.append(p.toString());
			// stringBuffer.append(" ");
			// stringBuffer.append(", ");
			// }

			System.out.println(stringBuffer);
		}

		System.out
				.println("\n\n*******************************************\n\n");
	}

	public static void printSpanSummary(Spannable s) {
		FseParagraphView[] allSpans = s.getSpans(0, s.length(),
				FseParagraphView.class);
		System.out
				.println("\n\n*******************************************\n\n");

		System.out.println(allSpans.length + " total FMS paragraph spans");
		HashMap<FseParagraphView, String> map = new HashMap<FseParagraphView, String>();
		for (int i = 0; i < allSpans.length; i++) {
			FseParagraphView span = allSpans[i];
			map.put(span, Integer.toString(i));

			StringBuffer sb = new StringBuffer();
			sb.append(i);
			sb.append("   ");
			sb.append(span.getStyle());
			sb.append("   ");
			sb.append(s.getSpanStart(span));
			sb.append("   ");
			sb.append(s.getSpanEnd(span));
			sb.append("   ");
			sb.append(s.getSpanFlags(span));
			System.out.println(sb);
		}

		System.out.println("-------------------------------------------\n\n");

	}

	public static void printSpanSummary(@SuppressWarnings("unused") FseParagraphView paragraph) {
		System.out
				.println("\n\n**************  BROKEN Utility  *****************************\n\n");
//		FseParagraphView node = paragraph.getRootParagraph();
//		do {
//			System.out.println(node.toString());
//			node = node.getNext();
//		} while (node.hasNext());
		System.out
				.println("\n\n*******************************************\n\n");
	}
}
