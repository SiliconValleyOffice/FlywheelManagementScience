/* @(#)FseDocumentSectionCollaborators.java
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

package com.flywheelms.library.fse.model;

import com.flywheelms.library.fmm.meta_data.NodeFragAuditBlockMetaData;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.util.FseDocumentSerialization;

import org.json.JSONException;
import org.json.JSONObject;

public class FseDocumentSectionCollaborators extends FseDocumentSection {
	
	private FseAuditBlock fseParagraphAuditBlock;
	private FseCollaboratorSummary collaboratorSummary;

	public FseDocumentSectionCollaborators() {
		super(FseDocumentSectionType.COMMUNITY);
	}

	public FseDocumentSectionCollaborators(FseAuditBlock anAuditBlock, FseCollaboratorSummary aCommunityMemberParticipationSummary) {
		super(FseDocumentSectionType.COMMUNITY);
		this.fseParagraphAuditBlock = anAuditBlock;
		this.collaboratorSummary = aCommunityMemberParticipationSummary;
	}

	public FseDocumentSectionCollaborators(JSONObject aJsonObject) {
		super(FseDocumentSectionType.COMMUNITY);
		try {
			this.fseParagraphAuditBlock =
					new FseAuditBlock(aJsonObject.getJSONObject(NodeFragAuditBlockMetaData.key__NODE_FRAG__AUDIT_BLOCK));
			this.collaboratorSummary =
					new FseCollaboratorSummary(aJsonObject.getJSONObject(FseDocumentSerialization.key__COLLABORATOR_SUMMARY));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = new JSONObject();
		try {
			theJsonObject.put(NodeFragAuditBlockMetaData.key__NODE_FRAG__AUDIT_BLOCK, this.fseParagraphAuditBlock.getJsonObject());
			theJsonObject.put(FseDocumentSerialization.key__COLLABORATOR_SUMMARY, this.collaboratorSummary.getJsonObject());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}

	public FseAuditBlock getFseParagraphAuditBlock() {
		return this.fseParagraphAuditBlock;
	}

	public void setFseParagraphAuditBlock(FseAuditBlock anFseParagraphAuditBlock) {
		this.fseParagraphAuditBlock = anFseParagraphAuditBlock;
	}
	
	public FseCollaboratorSummary getCollaboratorSummary() {
		return this.collaboratorSummary;
	}

	public void setCollaboratorSummary(FseCollaboratorSummary aCollaboratorSummary) {
		this.collaboratorSummary = aCollaboratorSummary;
	}

	@Override
	public FseDocumentSectionCollaborators getClone() {
		return new FseDocumentSectionCollaborators(this.getJsonObject());
	}

	@Override
	public boolean validateSerializationFormatVersion(String aString) {
		// TODO Auto-generated method stub
		return false;
	}

}
