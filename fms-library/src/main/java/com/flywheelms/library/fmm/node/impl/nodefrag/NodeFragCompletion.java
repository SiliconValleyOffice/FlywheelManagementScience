/* @(#)NodeFragCompletion.java
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

package com.flywheelms.library.fmm.node.impl.nodefrag;

import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.meta_data.NodeFragCompletionMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.enumerator.CompletableWorkStatus;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.util.JsonHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Date;

// implements FmmNodeCompletion
public class NodeFragCompletion extends FmmNodeFragImpl implements Serializable { 
	
	private static final long serialVersionUID = 1L;
	private boolean autoCompletable = false;
	private CompletableWorkStatus completableWorkStatus = CompletableWorkStatus.NOT_STARTED;
	private String yellowByNodeIdString;
	private CommunityMember yellowByCommunityMember;
	private Date yellowTimestamp;
	private String orangeByNodeIdString;
	private CommunityMember orangeByCommunityMember;
	private Date orangeTimestamp;
	private String pinkByNodeIdString;
	private CommunityMember pinkByCommunityMember;
	private Date pinkTimestamp;
	private String greenByNodeIdString;
	private CommunityMember greenByCommunityMember;
	private Date greenTimestamp;
	private String completionConfirmedByNodeIdString;
	private CommunityMember completionConfirmedByCommunityMember;
	private Date completionConfirmedTimestamp;

	public NodeFragCompletion(String aParentNodeIdString) {
		super(NodeFragCompletion.class, aParentNodeIdString);
	}

	// rehydrate from database
	public NodeFragCompletion(String aNodeIdString, String aParentNodeIdString) {
		super(NodeId.hydrate(NodeFragCompletion.class, aNodeIdString), aParentNodeIdString);
	}

	// rehydrate
	public NodeFragCompletion(JSONObject aJsonObject) {
		super(NodeFragCompletion.class, aJsonObject);
		try {
			setAutoCompletable(aJsonObject.getBoolean(NodeFragCompletionMetaData.column_AUTO_COMPLETABLE));
			setCompletableWorkStatus(CompletableWorkStatus.getObjectForCode(aJsonObject.getString(NodeFragCompletionMetaData.column_WORK_STATUS)));
			setYellowBy(aJsonObject.getString(NodeFragCompletionMetaData.column_YELLOW_BY));
			setYellowTimestamp(JsonHelper.getDate(aJsonObject, NodeFragCompletionMetaData.column_YELLOW_TIMESTAMP));
			setOrangeBy(aJsonObject.getString(NodeFragCompletionMetaData.column_ORANGE_BY));
			setOrangeTimestamp(JsonHelper.getDate(aJsonObject, NodeFragCompletionMetaData.column_ORANGE_TIMESTAMP));
			setPinkBy(aJsonObject.getString(NodeFragCompletionMetaData.column_PINK_BY));
			setPinkTimestamp(JsonHelper.getDate(aJsonObject, NodeFragCompletionMetaData.column_PINK_TIMESTAMP));
			setGreenBy(aJsonObject.getString(NodeFragCompletionMetaData.column_GREEN_BY));
			setGreenTimestamp(JsonHelper.getDate(aJsonObject, NodeFragCompletionMetaData.column_GREEN_TIMESTAMP));
			setCompletionConfirmedBy(aJsonObject.getString(NodeFragCompletionMetaData.column_COMPLETION_CONFIRMED_BY));
			setCompletionConfirmedTimestamp(JsonHelper.getDate(aJsonObject, NodeFragCompletionMetaData.column_COMPLETION_CONFIRMED_TIMESTAMP));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(NodeFragCompletionMetaData.column_AUTO_COMPLETABLE, getAutocompletable());
			theJsonObject.put(NodeFragCompletionMetaData.column_WORK_STATUS, getCompletableWorkStatusCode());
			theJsonObject.put(NodeFragCompletionMetaData.column_YELLOW_BY, getYellowByNodeIdString());
			JsonHelper.putDate(theJsonObject, NodeFragCompletionMetaData.column_YELLOW_TIMESTAMP, getYellowTimestamp());
			theJsonObject.put(NodeFragCompletionMetaData.column_ORANGE_BY, getOrangeByNodeIdString());
			JsonHelper.putDate(theJsonObject, NodeFragCompletionMetaData.column_ORANGE_TIMESTAMP, getOrangeTimestamp());
			theJsonObject.put(NodeFragCompletionMetaData.column_PINK_BY, getPinkByNodeIdString());
			JsonHelper.putDate(theJsonObject, NodeFragCompletionMetaData.column_PINK_TIMESTAMP, getPinkTimestamp());
			theJsonObject.put(NodeFragCompletionMetaData.column_GREEN_BY, getGreenByNodeIdString());
			JsonHelper.putDate(theJsonObject, NodeFragCompletionMetaData.column_GREEN_TIMESTAMP, getGreenTimestamp());
			theJsonObject.put(NodeFragCompletionMetaData.column_COMPLETION_CONFIRMED_BY, getCompletionConfirmedByNodeIdString());
			JsonHelper.putDate(theJsonObject, NodeFragCompletionMetaData.column_COMPLETION_CONFIRMED_TIMESTAMP, getCompletionConfirmedTimestamp());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return theJsonObject;
	}
	
	@Override
	public NodeFragCompletion getClone() {
		return new NodeFragCompletion(getJsonObject());
	}

	public int getCompletableWorkStateDrawableResourceId() {
		return getCompletableWorkStatus().getDataDrawableResourceId();
	}

	public boolean getAutocompletable() {
		return this.autoCompletable;
	}

	public void setAutoCompletable(short aShort) {
		this.autoCompletable = aShort == 0 ? false : true;
	}

	public void setAutoCompletable(boolean aBoolean) {
		this.autoCompletable = aBoolean;
	}

	public CompletableWorkStatus getCompletableWorkStatus() {
		return this.completableWorkStatus;
	}

	public String getCompletableWorkStatusCode() {
		return this.completableWorkStatus.getWorkStatusCode();
	}

	public void setCompletableWorkStatus(CompletableWorkStatus aCompletableWorkStatus) {
		this.completableWorkStatus = aCompletableWorkStatus;
	}

	public void setCompletableWorkStatusCode(String aWorkStatusCode) {
		this.completableWorkStatus = CompletableWorkStatus.getObjectForCode(aWorkStatusCode);
	}
	
	public CommunityMember getYellowByCommunityMember() {
		if(this.yellowByCommunityMember == null) {
			if(this.yellowByNodeIdString != null &&  ! this.yellowByNodeIdString.equals("")) {
				this.yellowByCommunityMember = FmmDatabaseMediator.getActiveMediator().getCommunityMember(this.yellowByNodeIdString);

			} else {
				this.yellowByCommunityMember = CommunityMember.getNullValue();
			}
		}
		return this.yellowByCommunityMember;
	}

	public void setYellowBy(CommunityMember aCommunityMember) {
		this.yellowByCommunityMember = aCommunityMember;
		this.yellowByNodeIdString = aCommunityMember.getNodeIdString();
	}

	public String getYellowByNodeIdString() {
		return this.yellowByNodeIdString;
	}

	public void setYellowBy(String aNodeIdString) {
		this.yellowByNodeIdString = aNodeIdString;
		if(this.yellowByCommunityMember == null || this.yellowByCommunityMember.getNodeIdString().equals(aNodeIdString)) {
			return;
		}
		this.yellowByCommunityMember = null;
	}

	public Date getYellowTimestamp() {
		return this.yellowTimestamp;
	}

	public void setYellowTimestamp(Date aTimestamp) {
		this.yellowTimestamp = aTimestamp;
	}

	public CommunityMember getOrangeByCommunityMember() {
		if(this.orangeByCommunityMember == null) {
			if(this.orangeByNodeIdString != null &&  ! this.orangeByNodeIdString.equals("")) {
				this.orangeByCommunityMember = FmmDatabaseMediator.getActiveMediator().getCommunityMember(this.orangeByNodeIdString);

			} else {
				this.orangeByCommunityMember = CommunityMember.getNullValue();
			}
		}
		return this.orangeByCommunityMember;
	}

	public void setOrangeBy(CommunityMember aCommunityMember) {
		this.orangeByCommunityMember = aCommunityMember;
		this.orangeByNodeIdString = aCommunityMember.getNodeIdString();
	}

	public String getOrangeByNodeIdString() {
		return this.orangeByNodeIdString;
	}

	public void setOrangeBy(String aNodeIdString) {
		this.orangeByNodeIdString = aNodeIdString;
		if(this.orangeByCommunityMember == null || this.orangeByCommunityMember.getNodeIdString().equals(aNodeIdString)) {
			return;
		}
		this.orangeByCommunityMember = null;
	}

	public Date getOrangeTimestamp() {
		return this.orangeTimestamp;
	}

	public void setOrangeTimestamp(Date aTimestamp) {
		this.orangeTimestamp = aTimestamp;
	}

	public CommunityMember getPinkByCommunityMember() {
		if(this.pinkByCommunityMember == null) {
			if(this.pinkByNodeIdString != null &&  ! this.pinkByNodeIdString.equals("")) {
				this.pinkByCommunityMember = FmmDatabaseMediator.getActiveMediator().getCommunityMember(this.pinkByNodeIdString);

			} else {
				this.pinkByCommunityMember = CommunityMember.getNullValue();
			}
		}
		return this.pinkByCommunityMember;
	}

	public void setPinkBy(CommunityMember aCommunityMember) {
		this.pinkByCommunityMember = aCommunityMember;
		this.pinkByNodeIdString = aCommunityMember.getNodeIdString();
	}

	public String getPinkByNodeIdString() {
		return this.pinkByNodeIdString;
	}

	public void setPinkBy(String aNodeIdString) {
		this.pinkByNodeIdString = aNodeIdString;
		if(this.pinkByCommunityMember == null || this.pinkByCommunityMember.getNodeIdString().equals(aNodeIdString)) {
			return;
		}
		this.pinkByCommunityMember = null;
	}

	public Date getPinkTimestamp() {
		return this.pinkTimestamp;
	}

	public void setPinkTimestamp(Date aTimestamp) {
		this.pinkTimestamp = aTimestamp;
	}

	public CommunityMember getGreenByCommunityMember() {
		if(this.greenByCommunityMember == null) {
			if(this.greenByNodeIdString != null &&  ! this.greenByNodeIdString.equals("")) {
				this.greenByCommunityMember = FmmDatabaseMediator.getActiveMediator().getCommunityMember(this.greenByNodeIdString);

			} else {
				this.greenByCommunityMember = CommunityMember.getNullValue();
			}
		}
		return this.greenByCommunityMember;
	}

	public void setGreenBy(CommunityMember aCommunityMember) {
		this.greenByCommunityMember = aCommunityMember;
		this.greenByNodeIdString = aCommunityMember.getNodeIdString();
	}

	public String getGreenByNodeIdString() {
		return this.greenByNodeIdString;
	}

	public void setGreenBy(String aNodeIdString) {
		this.greenByNodeIdString = aNodeIdString;
		if(this.greenByCommunityMember == null || this.greenByCommunityMember.getNodeIdString().equals(aNodeIdString)) {
			return;
		}
		this.greenByCommunityMember = null;
	}

	public Date getGreenTimestamp() {
		return this.greenTimestamp;
	}

	public void setGreenTimestamp(Date aTimestamp) {
		this.greenTimestamp = aTimestamp;
	}

	public CommunityMember getCompletionConfirmedByCommunityMember() {
		if(this.completionConfirmedByCommunityMember == null) {
			if(this.completionConfirmedByNodeIdString != null &&  ! this.completionConfirmedByNodeIdString.equals("")) {
				this.completionConfirmedByCommunityMember = FmmDatabaseMediator.getActiveMediator().getCommunityMember(this.completionConfirmedByNodeIdString);

			} else {
				this.completionConfirmedByCommunityMember = CommunityMember.getNullValue();
			}
		}
		return this.completionConfirmedByCommunityMember;
	}
	
	public void setCompletionConfirmedBy(CommunityMember aCommunityMember) {
		this.completionConfirmedByCommunityMember = aCommunityMember;
		this.completionConfirmedByNodeIdString = aCommunityMember.getNodeIdString();
	}

	public String getCompletionConfirmedByNodeIdString() {
		return this.completionConfirmedByNodeIdString;
	}

	public void setCompletionConfirmedBy(String aNodeIdString) {
		this.completionConfirmedByNodeIdString = aNodeIdString;
		if(this.completionConfirmedByCommunityMember == null || this.completionConfirmedByCommunityMember.getNodeIdString().equals(aNodeIdString)) {
			return;
		}
		this.completionConfirmedByCommunityMember = null;
	}

	public Date getCompletionConfirmedTimestamp() {
		return this.completionConfirmedTimestamp;
	}

	public void setCompletionConfirmedTimestamp(Date aTimestamp) {
		this.completionConfirmedTimestamp = aTimestamp;
	}
	
	//////////////////////////////////////

	public boolean isComplete() {
		return this.completableWorkStatus == CompletableWorkStatus.COMPLETE;
	}

	public boolean isGreen() {
		return this.completableWorkStatus == CompletableWorkStatus.COMPLETE;
	}

	public boolean isPink() {
		return this.completableWorkStatus == CompletableWorkStatus.READY_FOR_REVIEW;
	}

	public boolean isOrange() {
		return this.completableWorkStatus == CompletableWorkStatus.ON_HOLD;
	}

	public boolean isYellow() {
		return this.completableWorkStatus == CompletableWorkStatus.STARTED;
	}

	public boolean isGray() {
		return this.completableWorkStatus == CompletableWorkStatus.NOT_STARTED;
	}

}
