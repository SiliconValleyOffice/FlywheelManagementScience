/* @(#)FseStoryTemplate.java
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

package com.flywheelms.library.fse.enumerator;

import java.util.Hashtable;

import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.governable.FiscalYear;
import com.flywheelms.library.fmm.node.impl.governable.Project;
import com.flywheelms.library.fmm.node.impl.governable.ProjectAsset;
import com.flywheelms.library.fmm.node.impl.governable.StrategicMilestone;
import com.flywheelms.library.fmm.node.impl.governable.WorkPackage;
import com.flywheelms.library.fmm.node.impl.governable.WorkTask;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.fse.model.FseTemplateParagraph;

public enum FseStoryTemplate {
	
	FISCAL_YEAR (1, 0, new FseTemplateParagraph[] {
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.ROMAN, true, "Strategic Theme"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Describe the strategic theme for this year."),
			
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.ROMAN, true, "Opportunities"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "General overview of our opportunities."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.ALPHA, false, "Strategic Opportunities"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Overview of the strategic opportunities for this year."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_3, FseParagraphNumberingStyle.NUMBERED, false, "1st Strategic Opportunity"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_3, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st strategic opportunity."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_3, FseParagraphNumberingStyle.NUMBERED, false, "2nd Strategic Opportunity"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_3, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd strategic opportunity."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.ALPHA, false, "Tactical Opportunities"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Overview of the tactical opportunities for this year."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_3, FseParagraphNumberingStyle.NUMBERED, false, "1st Tactical Opportunity"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_3, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st strategic opportunity."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_3, FseParagraphNumberingStyle.NUMBERED, false, "2nd Tactical Opportunity"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_3, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd strategic opportunity."),

			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.ROMAN, true, "Challenges"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "General overview of our challenges for this year."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.ALPHA, true, "Strategic Challenges"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Overview of the strategic challenges."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_3, FseParagraphNumberingStyle.NUMBERED, false, "1st strategic Challenge"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_3, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st strategic challenge."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_3, FseParagraphNumberingStyle.NUMBERED, false, "2nd strategic Challenge"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_3, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd strategic challenge."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.ALPHA, true, "Tactical Challenges"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Overview of the tactical challenges."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_3, FseParagraphNumberingStyle.NUMBERED, false, "1st tactical Challenge"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_3, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st tactical challenge."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_3, FseParagraphNumberingStyle.NUMBERED, false, "2nd tactical Challenge"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_3, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd tactical challenge."),

			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.ROMAN, true, "Key Events and Dates"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "General overview of our key events and dates for this year."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.NUMBERED, true, "First Key Event"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe this key event and any associated date(s)."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.NUMBERED, true, "Second Key Event"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe this key event and any associated date(s).")
	} ),
	STRATEGIC_MILESTONE (1, 0, new FseTemplateParagraph[] {
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.ROMAN, true, "Strategic Opportunity"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the strategic opportunity."),
			
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.ROMAN, true, "Strategic Benefit"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Overview of the strategic benefits."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.NUMBERED, false, "1st strategic Benefit"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st strategic benefit."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.NUMBERED, false, "2nd strategic Benefit"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd strategic benefit."),
			
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.ROMAN, true, "Strategic Risks"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Overview of the strategic risks."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.NUMBERED, false, "1st strategic Risk"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st strategic risk."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.NUMBERED, false, "2nd strategic Risk"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd strategic risk.")
	} ),
	COMMUNITY_MEMBER (1, 0, new FseTemplateParagraph[] {
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Overview & History"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, ""),
			
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Skills"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Summary of skills."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "1st Skill"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, ""),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "2nd Skill"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, ""),
			
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Frequent Collaborators"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Range of collaborations..."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "Collaborator 1"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, ""),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "Collaborator 2"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, ""),
			
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Collaboration Highlights"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Summary of collaborations."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "Collaboration 1"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, ""),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "Collaboration 2"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "")
	} ),
	DEFAULT (1, 0, new FseTemplateParagraph[] {
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Default"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Need to create a story template for this FMM Node")
	} ),
	PROJECT_ASSET (1, 0, new FseTemplateParagraph[] {
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Overview"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the project asset."),
			
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Features"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Brief overview of the features."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "1st feature"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st feature."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "2nd feature"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd feature."),
			
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Benefits"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Brief overview of the benefits."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "1st benefit"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st benefit."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "2nd tactical Challenge"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd benefit.")
	} ),
	WORK_PACKAGE (1, 0, new FseTemplateParagraph[] {
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Overview"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Overview of any new processes or technology that must be invented to complete this work package."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "1st new science"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st new science."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "2nd new science"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd new science."),
			
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Deliverables"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Overview of the deliverables from this work package."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "1st deliverable"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st deliverable."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "2nd deliverable"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd deliverable."),
			
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Dependencies"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Overview of any dependencies."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "1st dependency"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st dependency."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "2nd dependency"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd dependency.")
	} ),
	WORK_TASK (1, 0, new FseTemplateParagraph[] {
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Overview"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Overview of the activities."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "1st activity"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st strategic challenge."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "2nd strategic Challenge"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd strategic challenge."),
			
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Deliverables"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Overview of the deliverables from this work package."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "1st deliverable"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st deliverable."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "2nd deliverable"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd deliverable."),
			
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Dependencies"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Overview of any dependencies."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "1st dependency"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st dependency."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "2nd dependency"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd dependency.")
	} ),
	PROJECT (1, 0, new FseTemplateParagraph[] {
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Overview"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the project."),
			
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Features"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Brief overview of the features."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "1st feature"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st feature."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "2nd feature"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd feature."),
			
			new FseTemplateParagraph(FseParagraphStyle.HEADING_1, FseParagraphNumberingStyle.DEFAULT, true, "Benefits"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_1, FseParagraphNumberingStyle.DEFAULT, false, "Brief overview of the benefits."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "1st benefit"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 1st benefit."),
			new FseTemplateParagraph(FseParagraphStyle.HEADING_2, FseParagraphNumberingStyle.DEFAULT, false, "2nd tactical Challenge"),
			new FseTemplateParagraph(FseParagraphStyle.BODY_2, FseParagraphNumberingStyle.DEFAULT, false, "Describe and explain the 2nd benefit.")
	} );
	
	private static final Hashtable<Class<? extends FmmNode>, FseStoryTemplate> fmmNodeMap = new Hashtable<Class<? extends FmmNode>, FseStoryTemplate>();
	
	static {
		FseStoryTemplate.fmmNodeMap.put(CommunityMember.class, COMMUNITY_MEMBER);
		FseStoryTemplate.fmmNodeMap.put(FiscalYear.class, FISCAL_YEAR);
		FseStoryTemplate.fmmNodeMap.put(Project.class, PROJECT);
		FseStoryTemplate.fmmNodeMap.put(ProjectAsset.class, PROJECT_ASSET);
		FseStoryTemplate.fmmNodeMap.put(StrategicMilestone.class, STRATEGIC_MILESTONE);
		FseStoryTemplate.fmmNodeMap.put(WorkPackage.class, WORK_PACKAGE);
		FseStoryTemplate.fmmNodeMap.put(WorkTask.class, WORK_TASK);
	}
	
	public static FseStoryTemplate getTemplateForClass(Class<? extends FmmNode> aClass) {
		return FseStoryTemplate.fmmNodeMap.get(aClass) == null ? DEFAULT : FseStoryTemplate.fmmNodeMap.get(aClass);
	}
	
	private final FseTemplateParagraph[] fseTemplateParagraphArray;
	private final int initialParagraphFocusIndex;
	private final int initialParagraphFocusCursorPosition;
	
	private FseStoryTemplate(
			int anInitialParagraphFocusIndex,
			int anInitialParagraphFocusCursorPosition,
			FseTemplateParagraph[] anFseTemplateParagraph) {
		this.initialParagraphFocusIndex = anInitialParagraphFocusIndex;
		this.initialParagraphFocusCursorPosition = anInitialParagraphFocusCursorPosition;
		this.fseTemplateParagraphArray = anFseTemplateParagraph;
	}

	public FseTemplateParagraph[] getFseTemplateParagraphArray() {
		return this.fseTemplateParagraphArray;
	}
	
	public int getInitialParagraphFocusIndex() {
		return this.initialParagraphFocusIndex;
	}

	public int getInitialParagraphFocusCursorPosition() {
		return this.initialParagraphFocusCursorPosition;
	}

}
