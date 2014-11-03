/* @(#)FmmCompletionNode.java
** 
** Copyright (C) 2012 by Steven D. Stamps
**
**             Trademarks & Copyrights
** Flywheel Management Science(TM), Flywheel Management Model(TM),
** Flywheel Story Editor(TM) and FlywheelMS(TM) are exclusive trademarks
** of Steven D. Stamps and may only be used freely for the purpose of
** identifying the unforked version of this software.  Subsequent forks
** may not use these trademarks.  All other rights are reserved.
** and FlywheelMS(TM) are exclusive trademarks of Steven D. Stamps
** and may only be used freely for the purpose of identifying the
** unforked version of this software.  Subsequent forks (if any) may
** not use these trademarks.  All other rights are reserved.
**
** DecKanGL (Decorated Kanban Glyph Language) and TribKn (Tribal Knowledge)
** are also exclusive trademarks of Steven D. Stamps.  These may be used
** freely within the unforked FlywheelMS application and documentation.
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

package com.flywheelms.library.fmm.node.interfaces.horizontal;

import com.flywheelms.library.fmm.node.impl.enumerator.CompletableWorkStatus;
import com.flywheelms.library.fmm.node.impl.enumerator.CompletionCommitmentType;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragCompletion;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragWorkTaskBudget;
import com.flywheelms.library.fmm.node.interfaces.FmmSequencedNode;

import java.util.Date;

public interface FmmCompletionNode extends FmmGovernableNode, FmmSequencedNode {
	
	NodeFragWorkTaskBudget getNodeFragWorkTaskBudget();

    NodeFragWorkTaskBudget getUpdatedNodeFragWorkTaskBudget();
	
	void setNodeFragWorkTaskBudget(NodeFragWorkTaskBudget aNodeFragWorkTaskBudget);
	
	NodeFragCompletion getNodeFragCompletion();
	
	void setNodeFragCompletion(NodeFragCompletion aNodeFragCompletion);

    NodeFragCompletion getUpdatedNodeFragCompletion();
	
	boolean getAutocompletable();
	
	void setAutoCompletable(short shortAutoCompletable);
	
	void setAutoCompletable(boolean aBoolean);
	
	CompletionCommitmentType getCompletionCommitmentType();
	
	boolean isGreen();
	
	boolean isPink();
	
	boolean isOrange();
	
	boolean isYellow();
	
	boolean isGray();
	
	boolean isComplete();
	
	CompletableWorkStatus getCompletableWorkStatus();
	
	void setCompletableWorkStatus(CompletableWorkStatus aCompletableWorkStatus);
	
	String getCompletableWorkStatusCode();
	
	void setCompletableWorkStatusCode(String aWorkStatusCode);
	
	CommunityMember getYellowByCommunityMember();
	
	void setYellowBy(CommunityMember aCommunityMember);
	
	String getYellowByNodeIdString();
	
	void setYellowBy(String aNodeIdString);
	
	Date getYellowTimestamp();
	
	void setYellowTimestamp(Date aTimestamp);
	
	CommunityMember getOrangeByCommunityMember();
	
	void setOrangeBy(CommunityMember aCommunityMember);
	
	String getOrangeByNodeIdString();
	
	void setOrangeBy(String aNodeIdString);
	
	Date getOrangeTimestamp();
	
	void setOrangeTimestamp(Date aTimestamp);
	
	CommunityMember getPinkByCommunityMember();
	
	void setPinkBy(CommunityMember aCommunityMember);
	
	String getPinkByNodeIdString();
	
	void setPinkBy(String aNodeIdString);
	
	Date getPinkTimestamp();
	
	void setPinkTimestamp(Date aTimestamp);
	
	CommunityMember getGreenByCommunityMember();
	
	void setGreenBy(CommunityMember aCommunityMember);
	
	String getGreenByNodeIdString();
	
	void setGreenBy(String aNodeIdString);
	
	Date getGreenTimestamp();
	
	void setGreenTimestamp(Date aTimestamp);
	
	CommunityMember getCompletionConfirmedByCommunityMember();
	
	void setCompletionConfirmedBy(CommunityMember aCommunityMember);
	
	String getCompletionConfirmedByNodeIdString();
	
	void setCompletionConfirmedBy(String aNodeIdString);
	
	Date getCompletionConfirmedTimestamp();
	
	void setCompletionConfirmedTimestamp(Date aTimestamp);

    void setPrimaryParentId(String aNodeIdString);
}
