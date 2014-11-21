/* @(#)FmmTableMediator.java
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

package com.flywheelms.library.fmm;

import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

public class FmmTableMediator<T extends FmmNode> extends HashMap<String, T> {

	private static final long serialVersionUID = 1L;
	public static final int transaction__NEW = 1;
	public static final int transaction__DELETE = 2;
	public static final int transaction__HEADLINE = 3;
	public static final int transaction__DECKANGL = 4;
	private final FmmDatabaseMediator databaseMediator;
	@SuppressWarnings("unused")
	private final FmmNodeDefinition nodeDictionaryEntry;  // TODO
	private Date lastTableUpdateTimestamp = GcgDateHelper.getCurrentDateTime();
	private HashSet<FmmTableListener> tableListenerHashSet = new HashSet<FmmTableListener>();
	private HashSet<RowListener> rowListenerHashSet = new HashSet<RowListener>(); 
	
	public FmmTableMediator(FmmDatabaseMediator aDatabaseMediator, FmmNodeDefinition anFmmNodeDefinition) {
		super();
		this.databaseMediator = aDatabaseMediator;
		this.nodeDictionaryEntry = anFmmNodeDefinition;
	}
	
	public void addTableListener(FmmTableListener aTableListener) {
		this.tableListenerHashSet.add(aTableListener);
	}
	
	public void removeTableListener(FmmTableListener aTableListener) {
		this.tableListenerHashSet.remove(aTableListener);
	}
	
	public void addRowListener(String aNodeIdString, FmmRowListener aRowListener) {
		this.rowListenerHashSet.add(new RowListener(aNodeIdString, aRowListener));
	}
	
	public void removeRowListener(String aNodeIdString, FmmRowListener aRowListener) {
		this.rowListenerHashSet.remove(new RowListener(aNodeIdString, aRowListener));
	}
	
	/**
	 * add row and listener (), do not notify this source
	 * remove row and listener (), do not notify this source
	 */
	
	@Override
	public T put(String aNodeIdString, T anFmmNode) {
		super.put(aNodeIdString, anFmmNode);
		updateTableTimestamp();
		notifyListenersOnPut(anFmmNode);
		return anFmmNode;
	}

	@Override
	public T remove(Object aNodeIdString) {
		T theFmmNode = super.remove(aNodeIdString);
		updateTableTimestamp();
		notifyListenersOnRemove((String)aNodeIdString);
		return theFmmNode;
	}
	
	public void updateTableTimestamp() {
		long theTime = System.currentTimeMillis();
		this.lastTableUpdateTimestamp.setTime(theTime);
		this.databaseMediator.updateFmmTimestamp(theTime);
	}
	
	private void notifyListenersOnPut(T anFmmNode) {
		notifyTableListenersOnPut(anFmmNode);
		notifyRowListenersOnPut(anFmmNode);
	}

	private void notifyTableListenersOnPut(@SuppressWarnings("unused") T anFmmNode) {
		// TODO Auto-generated method stub
		// include dictionaryEntry and table timestamp
	}
	
	private void notifyRowListenersOnPut(@SuppressWarnings("unused") T anFmmNode) {
		// TODO Auto-generated method stub
		// include dictionaryEntry and table timestamp
	}
	
	private void notifyListenersOnRemove(String aNodeIdString) {
		notifyTableListenersOnRemove(aNodeIdString);
		notifyRowListenersOnRemove(aNodeIdString);
	}

	private void notifyTableListenersOnRemove(@SuppressWarnings("unused") String aNodeIdString) {
		// TODO Auto-generated method stub
		// include dictionaryEntry and table timestamp
	}
	
	private void notifyRowListenersOnRemove(@SuppressWarnings("unused") String aNodeIdString) {
		// TODO Auto-generated method stub
		// include dictionaryEntry and table timestamp
	}
	
	private class RowListener implements Comparable<RowListener> {
		final String nodeIdString;
		final FmmRowListener rowListener;
		
		public RowListener(String aNodeIdString, FmmRowListener aRowListener) {
			this.nodeIdString = aNodeIdString;
			this.rowListener = aRowListener;
		}

		public String getNodeIdString() {
			return this.nodeIdString;
		}

		public FmmRowListener getRowListener() {
			return this.rowListener;
		}

		@Override
		public int compareTo(RowListener anotherRowListener) {
			if(this.nodeIdString.equals(anotherRowListener.getNodeIdString()) && this.rowListener == anotherRowListener.getRowListener()) {
				return 0;
			}
			return -1;
		}
	}

}
