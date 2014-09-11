/* @(#)NodeFragGovernanceMetaData.java
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

package com.flywheelms.library.fmm.meta_data;

public class NodeFragGovernanceMetaData extends NodeFragMetaData {
	
	public static final String key__NODE_FRAG__GOVERNANCE = "NodeFragGovernance";
	
	public static final String column_GOVERNANCE_TARGET = "governance_target";
	
	public static final String column_SPONSOR_ID = "sponsor_id";
	public static final String column_SPONSOR_COMMITMENT = "sponsor_commitment";
	public static final String column_SPONSOR_COMMITMENT_TIMESTAMP = "sponsor_commitment_timestamp";
	public static final String column_SPONSOR_PROPOSED_BY_ID = "sponsor_proposed_by_id";
	public static final String column_SPONSOR_PROPOSED_TIMESTAMP = "sponsor_proposed_timestamp";
	public static final String column_SPONSOR_LOCKED_BY_ID = "sponsor_locked_by_id";
	public static final String column_SPONSOR_LOCKED_TIMESTAMP = "sponsor_locked_timestamp";
	public static final String column_SPONSOR_GOVERNANCE_SATISFACTION = "sponsor_governance_satisfaction";
	
	public static final String column_CUSTOMER_ID = "customer_id";
	public static final String column_CUSTOMER_COMMITMENT = "customer_commitment";
	public static final String column_CUSTOMER_COMMITMENT_TIMESTAMP = "customer_commitment_timestamp";
	public static final String column_CUSTOMER_PROPOSED_BY_ID = "customer_proposed_by_id";
	public static final String column_CUSTOMER_PROPOSED_TIMESTAMP = "customer_proposed_timestamp";
	public static final String column_CUSTOMER_LOCKED_BY_ID = "customer_locked_by_id";
	public static final String column_CUSTOMER_LOCKED_TIMESTAMP = "customer_locked_timestamp";
	public static final String column_CUSTOMER_GOVERNANCE_SATISFACTION = "customer_governance_satisfaction";
	
	public static final String column_FACILITATOR_ID = "facilitator_id";
	public static final String column_FACILITATOR_COMMITMENT = "facilitator_commitment";
	public static final String column_FACILITATOR_COMMITMENT_TIMESTAMP = "facilitator_commitment_timestamp";
	public static final String column_FACILITATOR_PROPOSED_BY_ID = "facilitator_proposed_by_id";
	public static final String column_FACILITATOR_PROPOSED_TIMESTAMP = "facilitator_proposed_timestamp";
	public static final String column_FACILITATOR_LOCKED_BY_ID = "facilitator_locked_by_id";
	public static final String column_FACILITATOR_LOCKED_TIMESTAMP = "facilitator_locked_timestamp";
	public static final String column_FACILITATOR_GOVERNANCE_SATISFACTION = "facilitator_governance_satisfaction";
	
	public static final String column_ADMINISTRATOR_ID = "administrator_id";
	public static final String column_ADMINISTRATOR_COMMITMENT = "administrator_commitment";
	public static final String column_ADMINISTRATOR_COMMITMENT_TIMESTAMP = "administrator_commitment_timestamp";
	public static final String column_ADMINISTRATOR_PROPOSED_BY_ID = "administrator_proposed_by_id";
	public static final String column_ADMINISTRATOR_PROPOSED_TIMESTAMP = "administrator_proposed_timestamp";
	public static final String column_ADMINISTRATOR_LOCKED_BY_ID = "administrator_locked_by_id";
	public static final String column_ADMINISTRATOR_LOCKED_TIMESTAMP = "administrator_locked_timestamp";
	public static final String column_ADMINISTRATOR_GOVERNANCE_SATISFACTION = "administrator_governance_satisfaction";
	
	public static void init() { return; }

}
