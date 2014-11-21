/* @(#)NotebookLinkDiscussionTopic.java
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
import com.flywheelms.library.fmm.node.impl.governable.DiscussionTopic;
import com.flywheelms.library.fmm.node.impl.governable.Notebook;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.fms.activity.FmsActivity;

public class NotebookLinkToDiscussionTopic extends FmmSequencedLinkNodeImpl {
    
    private Notebook notebook;
    private DiscussionTopic discussionTopic;

	public NotebookLinkToDiscussionTopic(
			String aParentNodeId,
			String aChildNodeId,
            int aSequence ) {
		super(
				NotebookLinkToDiscussionTopic.class,
				aParentNodeId,
				aChildNodeId,
                aSequence );
	}

    public NotebookLinkToDiscussionTopic(
            String anExistingNodeIdString,
            String aParentNodeId,
            String aChildNodeId,
            int aSequence ) {
        super(   NodeId.hydrate(
                        NotebookLinkToDiscussionTopic.class,
                        anExistingNodeIdString),
                aParentNodeId,
                aChildNodeId,
                aSequence );
    }

	@Override
	public Class<? extends FmmNode> getParentClass() {
		return Notebook.class;
	}

	@Override
	public Class<? extends FmmNode> getChildClass() {
		return DiscussionTopic.class;
	}

    public String getNotebookNodeId() {
        return getParentNodeIdString();
    }

    public Notebook getNotebook() {
        if(this.notebook == null && getParentNodeIdString() != null) {
            this.notebook =
                    FmsActivity.getActiveDatabaseMediator().retrieveNotebook(getParentNodeIdString());
        }
        return this.notebook;
    }

    public void setNotebookNodeId(String aNotebookNodeId) {
        setParentNodeIdString(aNotebookNodeId);
        this.notebook = null;
    }

    public void setNotebook(Notebook aNotebook) {
        this.notebook = aNotebook;
        setParentNodeIdString(aNotebook.getNodeIdString());
    }

    public String getDiscussionTopicNodeId() {
        return getChildNodeIdString();
    }

    public DiscussionTopic getDiscussionTopic() {
        if(this.discussionTopic == null && getChildNodeIdString() != null) {
            this.discussionTopic =
                    FmsActivity.getActiveDatabaseMediator().retrieveDiscussionTopic(getChildNodeIdString());
        }
        return this.discussionTopic;
    }

    public void setDiscussionTopicNodeId(String aDiscussionTopicNodeId) {
        setChildNodeIdString(aDiscussionTopicNodeId);
        this.discussionTopic = null;
    }

    public void setDiscussionTopic(DiscussionTopic aDiscussionTopic) {
        this.discussionTopic = aDiscussionTopic;
        setChildNodeIdString(aDiscussionTopic.getNodeIdString());
    }
	
}
