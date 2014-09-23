/* @(#)GcgTreeNodeInfo.java
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

package com.flywheelms.gcongui.gcg.treeview.node;

import android.graphics.Bitmap;

import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratedGlyphSize;
import com.flywheelms.gcongui.gcg.interfaces.GcgPerspective;

import java.io.Serializable;
import java.util.UUID;

public class GcgTreeNodeInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private final String mapKey = UUID.randomUUID().toString();
	private transient final GcgTreeNodeTargetObject targetObject;
    private final int level;
    private boolean visible;
    private final boolean withChildren;
    private boolean expanded;
    private DecKanGlDecoratedGlyphSize decKanGlGlyphSize;
    private GcgPerspective GcgPerspective;

    public GcgTreeNodeInfo(
    		final GcgTreeNodeTargetObject aTreeNodeTargetObject,
    		final int aLevel,
            final boolean bHasChildren,
            final boolean bVisible,
            final boolean bExpanded,
            DecKanGlDecoratedGlyphSize aDecKanGlGlyphSize,
            final GcgPerspective anGcgPerspective ) {
        super();
        this.targetObject = aTreeNodeTargetObject;
        this.level = aLevel;
        this.withChildren = bHasChildren;
        this.visible = bVisible;
        this.expanded = bExpanded;
        this.decKanGlGlyphSize = aDecKanGlGlyphSize;
        this.GcgPerspective = anGcgPerspective;
    }

    public GcgTreeNodeInfo(
    		final GcgTreeNodeTargetObject aTreeNodeTargetObject,
    		final int aLevel,
            final boolean bHasChildren,
            final GcgPerspective anGcgPerspective) {
    	this(aTreeNodeTargetObject, aLevel, bHasChildren, true, bHasChildren, DecKanGlDecoratedGlyphSize.SMALL, anGcgPerspective);
    }

    public GcgPerspective getGcgPerspective() {
		return this.GcgPerspective;
	}

	public void setGcgPerspective(GcgPerspective GcgPerspective) {
		this.GcgPerspective = GcgPerspective;
	}

	public boolean hasChildren() {
        return this.withChildren;
    }

    public boolean isVisible() {
        return this.visible;
    }

    public boolean isExpanded() {
        return this.expanded;
    }
    
    public void setExpanded(boolean aBoolean) {
    	this.expanded = aBoolean;
    }
    
    public void setVisible(boolean aBoolean) {
    	this.visible = aBoolean;
    }
    
    public boolean isExpandable() {
    	return hasChildren() == true && isExpanded() == false;
    }
    
    public boolean isCollapsible() {
    	return hasChildren() == true && isExpanded() == true;
    }

    public int getLevel() {
        return this.level;
    }
    
    public DecKanGlDecoratedGlyphSize getDecKanGlGlyphSize() {
    	return this.decKanGlGlyphSize;
    }
    
    public void setDecKanGlGlyphSize(DecKanGlDecoratedGlyphSize aGlyphSize) {
    	this.decKanGlGlyphSize = aGlyphSize;
    }

    public GcgTreeNodeTargetObject getTargetObject() {
		return this.targetObject;
	}
    
    public String getMapKey() {
    	return this.mapKey;
    }

	@Override
    public String toString() {
        return "TreeNode [" + "level=" + this.level
                + ", withChildren=" + this.withChildren
                + ", visible=" + this.visible
                + ", expanded=" + this.expanded
                + ", targetObjectClass=" + this.targetObject.getClass().getSimpleName()
                + ", headline=" + this.targetObject.getHeadline() + "]";
	}

	@Override
	public boolean equals(Object o) {
		return (o instanceof GcgTreeNodeInfo)
				&& getMapKey().equals(((GcgTreeNodeInfo)o).getMapKey());
	}

	@Override
	public int hashCode() {
		return getMapKey().hashCode();
	}
	
	public Bitmap getDecKanGlBitmap() {
		return getTargetObject().getDecKanGlBitmap(this.decKanGlGlyphSize);
	}
	
	public String getHeadline() {
		return getTargetObject().getHeadline();
	}
	
	public String getNodeSummaryPrefix() {
		return getTargetObject().getNodeSummaryPrefix(getGcgPerspective());
	}
	
	public int getNodeSummaryDrawableResourceId() {
		return getTargetObject().getNodeSummaryDrawableResourceId(getGcgPerspective());
	}
	
	public String getNodeSummarySuffix() {
		return getTargetObject().getNodeSummarySuffix(getGcgPerspective());
	}

	public boolean hasNodeQuality() {
		return getTargetObject().hasNodeQuality();
	}

	public boolean hasNodeSummary() {
		return getTargetObject().hasNodeSummary(getGcgPerspective());
	}

	public void setDecKanGlGlyphSize(int theEmphasisLevel) {
		this.decKanGlGlyphSize = theEmphasisLevel == getLevel() + 1 ?
				DecKanGlDecoratedGlyphSize.MEDIUM : DecKanGlDecoratedGlyphSize.SMALL;
	}

    public boolean hasSecondaryHeadline() {
        return this.targetObject.hasSecondaryHeadline();
    }

}
