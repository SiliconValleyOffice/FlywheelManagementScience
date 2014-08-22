/* @(#)FseParagraphNumberingStyle.java
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

public enum FseParagraphNumberingStyle {
	
	NONE("None"),
	ALPHA("Alpha"),
	BULLET("Bullet"),
	DEFAULT("Default"),
	NUMBERED("Numbers"),
	ROMAN("Roman");
	
	private final String name;
	
	private FseParagraphNumberingStyle(String aName) {
		this.name = aName;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	public static FseParagraphNumberingStyle getStyleForString(String aStyleToString) {
		for(FseParagraphNumberingStyle theStyle : values()) {
			if(theStyle.toString().equals(aStyleToString)) {
				return theStyle;
			}
		}
		return null;
	}

	public String getNumberingString(int aNumberingSequence) {
		if(aNumberingSequence == 0) {
			return "";
		}
		switch (this) {
			case NONE:
				return "";
			case ALPHA:
				return "ABCDEFGHIJKLMNOPQRTSUVWXYZ".substring(aNumberingSequence - 1, aNumberingSequence) + ".  ";
			case BULLET:
//				return '\u2022' + "  ";  // small bullet
//				return '\u26AB' + "  ";  // medium
				return '\u25CF' + "  ";  // large
			case NUMBERED:
				return aNumberingSequence + ".  ";
			case ROMAN:
				return getRoman(aNumberingSequence) + ".  ";
			default:
		}
		return "";
	}

	private static String getRoman(int aNumberingSequence) {
		switch(aNumberingSequence) {
			case 1:
				return "I";
			case 2:
				return "II";
			case 3:
				return "III";
			case 4:
				return "IV";
			case 5:
				return "V";
			case 6:
				return "VI";
			case 7:
				return "VII";
			case 8:
				return "VIII";
			case 9:
				return "IX";
			case 10:
				return "X";
			case 11:
				return "XI";
			case 12:
				return "XII";
			case 13:
				return "XIII";
			case 14:
				return "XIV";
			case 15:
				return "XV";
			case 16:
				return "XVI";
			case 17:
				return "XVII";
			case 18:
				return "XVIII";
			case 19:
				return "XIX";
			case 20:
				return "XX";
			case 21:
				return "XXI";
			case 22:
				return "XII";
			case 23:
				return "XXIII";
			case 24:
				return "XXIV";
			case 25:
				return "XXV";
			case 26:
				return "XXVI";
			case 27:
				return "XXVII";
			case 28:
				return "XXVIII";
			case 29:
				return "XXIX";
			case 30:
				return "XXX";
			case 31:
				return "XXXI";
			case 32:
				return "XXXII";
			case 33:
				return "XXXIII";
			case 34:
				return "XXXIV";
			case 35:
				return "XXXV";
			case 36:
				return "XXXVI";
			case 37:
				return "XXXVII";
			case 38:
				return "XXXVIII";
			case 39:
				return "XXXIX";
			case 40:
				return "XL";
			case 41:
				return "XLI";
			case 42:
				return "XLII";
			case 43:
				return "XLIII";
			case 44:
				return "XLIV";
			case 45:
				return "XLV";
			case 46:
				return "XLVI";
			case 47:
				return "XLVII";
			case 48:
				return "XLVIII";
			case 49:
				return "XLIX";
			case 50:
				return "L";
			default:
				return "Z";
		}
	}
	
}
