/* @(#)DiscussionTopicLinkToNodeFragAuditBlock.java
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

import com.flywheelms.library.fmm.FmmDatabaseMediator;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.governable.DiscussionTopic;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;

/*
 * a wonky class to enable the Discussion Topic to link to anything
 */
public class DiscussionTopicLinkToNodeFragAuditBlock extends FmmSequencedLinkNodeImpl {

    private DiscussionTopic discussionTopic;
    private NodeFragAuditBlock nodeFragAuditBlock;

    public DiscussionTopicLinkToNodeFragAuditBlock(
            String aParentNodeId,
            String aChildNodeId,
            int aSequence ) {
        super(
                NotebookLinkToDiscussionTopic.class,
                aParentNodeId,
                aChildNodeId,
                aSequence );
    }

    public DiscussionTopicLinkToNodeFragAuditBlock(
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
        return DiscussionTopic.class;
    }

    @Override
    public Class<? extends FmmNode> getChildClass() {
        return NodeFragAuditBlock.class;
    }

    public String getDiscussionTopicNodeId() {
        return getParentNodeIdString();
    }

    public DiscussionTopic getDiscussionTopic() {
        if(this.discussionTopic == null && getParentNodeIdString() != null) {
            this.discussionTopic =
                    FmmDatabaseMediator.getActiveMediator().retrieveDiscussionTopic(getParentNodeIdString());
        }
        return this.discussionTopic;
    }

    public void setDiscussionTopicNodeId(String aDiscussionTopicNodeId) {
        setParentNodeIdString(aDiscussionTopicNodeId);
        this.discussionTopic = null;
    }

    public void setDiscussionTopic(DiscussionTopic aDiscussionTopic) {
        this.discussionTopic = aDiscussionTopic;
        setParentNodeIdString(aDiscussionTopic.getNodeIdString());
    }

    public String getNodeFragAuditBlockNodeId() {
        return getChildNodeIdString();
    }

    public NodeFragAuditBlock getNodeFragAuditBlock() {
        if(this.nodeFragAuditBlock == null && getChildNodeIdString() != null) {
            this.nodeFragAuditBlock =
                    FmmDatabaseMediator.getActiveMediator().retrieveNodeFragAuditBlock(getChildNodeIdString());
        }
        return this.nodeFragAuditBlock;
    }

    public void setNodeFragAuditBlockNodeId(String aNodeFragAuditBlockNodeId) {
        setChildNodeIdString(aNodeFragAuditBlockNodeId);
        this.nodeFragAuditBlock = null;
    }

    public void setNodeFragAuditBlock(NodeFragAuditBlock aNodeFragAuditBlock) {
        this.nodeFragAuditBlock = aNodeFragAuditBlock;
        setChildNodeIdString(aNodeFragAuditBlock.getNodeIdString());
    }

}
