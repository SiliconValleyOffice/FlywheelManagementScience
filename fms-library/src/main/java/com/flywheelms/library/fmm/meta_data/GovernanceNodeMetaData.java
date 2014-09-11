/* @(#)GovernanceNodeMetaData.java
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

public class GovernanceNodeMetaData extends IdNodeMetaData {
	
	// This is a node fragment, so it only extends the IdNodeColumnDefinition
	
	public static final String column_FMS_GOVERNANCE_TARGET = "fms_governance_target";
	
	public static final String column_SPONSOR_ID = "sponsor_id";
	public static final String column_SPONSOR_COMMITMENT = "sponsor_commitment";
	public static final String column_SPONSOR_COMMITMENT_RESPONSE_DATETIME = "sponsor_commitment_response_datetime";
	public static final String column_SPONSOR_PROPOSED_BY = "sponsor_proposed_by_id";
	public static final String column_SPONSOR_PROPOSED_DATETIME = "sponsor_proposed_datetime";
	public static final String column_SPONSOR_LOCKED = "sponsor_locked";
	public static final String column_SPONSOR_LOCKED_BY = "sponsor_locked_by";
	public static final String column_SPONSOR_LOCKED_DATETIME = "sponsor_locked_datetime";

	public static final String column_FACILITATOR_ID = "facilitator_id";
	public static final String column_FACILITATOR_COMMITMENT = "facilitator_commitment";
	public static final String column_FACILITATOR_COMMITMENT_RESPONSE_DATETIME = "facilitator_commitment_response_datetime";
	public static final String column_FACILITATOR_PROPOSED_BY = "facilitator_proposed_by_id";
	public static final String column_FACILITATOR_PROPOSED_DATETIME = "facilitator_proposed_datetime";
	public static final String column_FACILITATOR_LOCKED = "facilitator_locked";
	public static final String column_FACILITATOR_LOCKED_BY = "facilitator_locked_by";
	public static final String column_FACILITATOR_LOCKED_DATETIME = "facilitator_locked_datetime";
	
	public static final String column_CUSTOMER_ID = "customer_id";
	public static final String column_CUSTOMER_COMMITMENT = "customer_commitment";
	public static final String column_CUSTOMER_COMMITMENT_RESPONSE_DATETIME = "customer_commitment_response_datetime";
	public static final String column_CUSTOMER_PROPOSED_BY = "customer_proposed_by_id";
	public static final String column_CUSTOMER_PROPOSED_DATETIME = "customer_proposed_datetime";
	public static final String column_CUSTOMER_LOCKED = "customer_locked";
	public static final String column_CUSTOMER_LOCKED_BY = "customer_locked_by";
	public static final String column_CUSTOMER_LOCKED_DATETIME = "customer_locked_datetime";
	
	public static final String column_ADMINISTRATOR_ID = "administrator_id";
	public static final String column_ADMINISTRATOR_COMMITMENT = "administrator_commitment";
	public static final String column_ADMINISTRATOR_COMMITMENT_RESPONSE_DATETIME = "administrator_commitment_response_datetime";
	public static final String column_ADMINISTRATOR_PROPOSED_BY = "administrator_proposed_by_id";
	public static final String column_ADMINISTRATOR_PROPOSED_DATETIME = "administrator_proposed_datetime";
	public static final String column_ADMINISTRATOR_LOCKED = "administrator_locked";
	public static final String column_ADMINISTRATOR_LOCKED_BY = "administrator_locked_by";
	public static final String column_ADMINISTRATOR_LOCKED_DATETIME = "administrator_locked_datetime";
	
	public static void init() { return; }

}
