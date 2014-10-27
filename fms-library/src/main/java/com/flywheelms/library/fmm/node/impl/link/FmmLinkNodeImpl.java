/* @(#)FmmLinkNodeImpl.java
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

package com.flywheelms.library.fmm.node.impl.link;

import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.FmmNodeImpl;
import com.flywheelms.library.fmm.node.interfaces.FmmLinkNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;

import java.util.Date;

public abstract class FmmLinkNodeImpl extends FmmNodeImpl implements FmmLinkNode  {
	
	private String parentNodeIdString;
	private String childNodeIdString;
//	private String fmmMapIndex;
	
	public FmmLinkNodeImpl(NodeId aNodeId , String aParentNodeIdString, String aChildNodeIdString) {
		super(aNodeId);
		this.parentNodeIdString = aParentNodeIdString;
		this.childNodeIdString = aChildNodeIdString;
//		this.fmmMapIndex = buildFmmMapIndex();
	}

    public FmmLinkNodeImpl(Class<? extends FmmLinkNodeImpl> aLinkNodeClass , String aParentNodeIdString, String aChildNodeIdString) {
        super(new NodeId(aLinkNodeClass));
        this.parentNodeIdString = aParentNodeIdString;
        this.childNodeIdString = aChildNodeIdString;
//        this.fmmMapIndex = buildFmmMapIndex();
    }
	
	@Override
	public String getLinkedByNodeId() {
		// get from NodeFragAuditBlock
		return null;
	}

	@Override
	public Date getLinkedDateTime() {
		// get from NodeFragAuditBlock
		return null;
	}

	@Override
	public String getParentNodeIdString() {
		return this.parentNodeIdString;
	}

	@Override
	public void setParentNodeIdString(String aNodeIdString) {
		this.parentNodeIdString = aNodeIdString;
//		this.fmmMapIndex = buildFmmMapIndex();
	}

	@Override
	public String getChildNodeIdString() {
		return this.childNodeIdString;
	}
	
	@Override
	public void setChildNodeIdString(String aNodeIdString) {
		this.childNodeIdString = aNodeIdString;
	}

//	@Override
//	public String buildFmmMapIndex() {
//		return this.parentNodeIdString + "-" + this.childNodeIdString;
//	}

//	@Override
//	public String getFmmMapIndex() {
//		return this.fmmMapIndex;
//	}
//
//	public static String getFmmMapIndex(String aParentNodeIdString, String aChildNodeIdString) {
//		return aParentNodeIdString + "-" + aChildNodeIdString;
//	}

	@Override
	public abstract Class<? extends FmmNode> getParentClass();

	@Override
	public abstract Class<? extends FmmNode> getChildClass();

}
