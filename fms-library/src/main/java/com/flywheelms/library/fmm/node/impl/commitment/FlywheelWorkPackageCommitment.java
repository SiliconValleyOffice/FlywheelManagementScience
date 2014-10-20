/* @(#)FlywheelWorkPackageCommitment.java
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

package com.flywheelms.library.fmm.node.impl.commitment;

import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.impl.governable.Cadence;
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;

import java.util.Date;

public class FlywheelWorkPackageCommitment extends FmmCommitmentNodeImpl {

	private Cadence flywheelCadence;
	private WorkPackage workPackage;

	public FlywheelWorkPackageCommitment(String aCadenceNodeIdString, String aWorkPackageNodeIdString) {
		super(FlywheelWorkPackageCommitment.class, aCadenceNodeIdString, aWorkPackageNodeIdString);
	}

	public String getCadenceNodeId() {
		return this.parentNodeIdString;
	}
	
//	public Cadence getCadence() {
//		if(this.Cadence == null && this.parentNodeIdString != null) {
//			this.Cadence =
//					FmmDatabaseMediator.getActiveMediator().getCadence(this.parentNodeIdString);
//		}
//		return this.Cadence;
//	}

	public void setCadenceNodeId(String aCadenceNodeId) {
		this.parentNodeIdString = aCadenceNodeId;
		this.flywheelCadence = null;
	}

	public void setCadence(Cadence aCadence) {
		this.flywheelCadence = aCadence;
		this.parentNodeIdString = aCadence.getNodeIdString();
	}

	public String getWorkPackageNodeIdString() {
		return this.childNodeIdString;
	}
	
	public WorkPackage getWorkPackage() {
		if(this.workPackage == null) {
			this.workPackage =
					FmmDatabaseMediator.getActiveMediator().retrieveWorkPackage(this.childNodeIdString);
		}
		return this.workPackage;
	}

	public void setWorkPackageNodeId(String aWorkPackageNodeId) {
		this.childNodeIdString = aWorkPackageNodeId;
		this.workPackage = null;
	}

	public void setWorkPackage(WorkPackage aWorkPackage) {
		this.workPackage = aWorkPackage;
		this.childNodeIdString = aWorkPackage.getNodeIdString();
	}

	public Cadence getCadence() {
		return this.flywheelCadence;
	}

	@Override
	public String getLinkedByNodeId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getLinkedDateTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<? extends FmmNode> getParentClass() {
		return Cadence.class;
	}

	@Override
	public Class<? extends FmmNode> getChildClass() {
		return WorkPackage.class;
	}
	
}
