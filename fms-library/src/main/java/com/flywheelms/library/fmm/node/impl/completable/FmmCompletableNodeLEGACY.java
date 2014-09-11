/* @(#)CompletableFmmObject.java  Jan 17, 2006
** 
** Copyright (C) 2001-2011 by Steven D. Stamps
**
**             Trademarks & Copyrights
** Flywheel Management Science(TM) and FlywheelMS(TM) are exclusive
** trademarks of Steven D. Stamps and may only be used freely for
** the purpose of identifying the unforked version of this software.
** Subsequent forks (if any) may not use these trademarks.  All other
** rights are reserved.
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

package com.flywheelms.library.fmm.node.impl.completable;


/**
 * @author tvahl
 */
public abstract class FmmCompletableNodeLEGACY {
	
	// for review only
	
}
//public abstract class FmmCompletableNodeJUNK extends AbstractPersistentFmmObject
//        implements Completable, Governable, ColorInterface, FmsDecoratedNoun {
//    
//    private Boolean autoCompletable;
//    private Boolean completed;
//    private String completionStatusChangedByID;
//    private CommunityMember completionStatusChangedBy;
//    private Date completionStatusChangeDate;
//    private Governance governance;
//	public abstract boolean canComplete();
//    protected abstract boolean canUncomplete();
//    protected abstract Boolean getAutoCompletableDefault();
//    
//    FmmCompletableNodeJUNK(GovernanceType aGovernanceType) {
//    	super();
//    	governance = new Governance(aGovernanceType);
//    }
//    
//    @Override
//    public boolean isComplete() {
//        if (getAutoCompletable()) {
//            return canComplete();
//        }
//        return getCompleted().booleanValue();
//    }
//    
//    public boolean canManuallyChangeCompletionStatus() {
//        if (getAutoCompletable()) {
//            return false;
//        }
//        
//        return canCompletionStatusChange();
//    }
//    
//    protected boolean canCompletionStatusChange() {
//        return isComplete () ? canUncomplete() : canComplete();
//    }
//    
//    protected final boolean hasCompletionLock() {
//        if (isManuallyCompleted()) {
//            return true;
//        }
//        
//        if (!isComplete()) {
//            return false;
//        }
//        
//        return !canUncomplete();
//    }
//    
//    public final boolean isManuallyCompleted() {
//        return isManualCompletable() && getCompleted();
//    }
//
//    public final Boolean getAutoCompletable() {
//        if (autoCompletable == null) {
//            autoCompletable = getAutoCompletableDefault();
//        }
//        return autoCompletable;
//    }
//
//    public final void setAutoCompletable(Boolean b) {
//        autoCompletable = b;
//        if (autoCompletable) {
//            setCompleted(Boolean.FALSE);
//            setCompletionStatusChangedBy(null);
//            setCompletionStatusChangeDate(null);
//        }
//    }
//    
//    /* Convenience method. */
//    public boolean isManualCompletable() {
//        return !getAutoCompletable();
//    }
//    
//    public void setCompleted(Boolean b) {
//        completed = b;
//    }
//    
//    public Boolean getCompleted() {
//        if (completed == null) {
//            completed = Boolean.FALSE;
//        }
//        return completed;
//    }
//
//    public Date getCompletionStatusChangeDate() {
//        return completionStatusChangeDate;
//    }
//
//    public void setCompletionStatusChangeDate(Date date) {
//        completionStatusChangeDate = date;
//    }
//    
//    public String getCompletionStatusChangedByID() {
//        if (completionStatusChangedByID == null) {
//            return "";
//        }
//        return completionStatusChangedByID;
//    }
//
//    public final void setCompletionStatusChangedByID(String completionStatusChangedByID) {
//        this.completionStatusChangedByID = completionStatusChangedByID;
//    }
//    
//    public CommunityMember getCompletionStatusChangedBy() {
//        if (completionStatusChangedBy == null && getCompletionStatusChangedByID().length() > 0) {
//            completionStatusChangedBy = getFmm().getCommunityMember(getCompletionStatusChangedByID());
//        }
//        return completionStatusChangedBy;
//    }
//    
//    public void setCompletionStatusChangedBy(CommunityMember communityMember) {
//        setCompletionStatusChangedByID(communityMember != null ? communityMember.getOid() : null);
//        completionStatusChangedBy = communityMember;
//    }
//    
//    // ColorInterface implementation ===================================================
//    // isGray() left to subclasses
//    
//    public boolean isGreen() {
//        return isComplete();
//    }
//    
//    public boolean isPink() {
//        return !isComplete() && canComplete();
//    }
//    
//    public boolean isYellow() {
//        return !isGreen() && !isGray() && !isPink();
//    }
//    
//    public final FlywheelColor getColor() {
//        if (isGreen()) {
//            return ColorInterface.FlywheelColor.GREEN;
//        }
//        
//        if (isPink()) {
//            return ColorInterface.FlywheelColor.PINK;
//        }
//        
//        if (isGray()) {
//            return ColorInterface.FlywheelColor.GRAY;
//        }
//        
//        return ColorInterface.FlywheelColor.YELLOW;
//    }
//
//    public Governance getGovernance() {
//    	if (this.governance == null) {
//    		this.governance = FmmObjectFactory.createGovernance(this);
//    	}
//		return governance;
//	}
//    
//	public void setGovernance(Governance governance) {
//		this.governance = governance;
//	}
//	
//	public void setOid(String anOid) {
//		super.setOid(anOid);
//		FmmObjectFactory.setAggregatordObjectOid(this, this.getGovernance());
//	}
//	
//	public boolean isLate() {
//		return false;
//	}
//	
//	//////////////////////////////////////////////////////////////
//    /////////  DecKanGl implementation  //////////////////////////
//    
//    public Noun decKanGlNoun;
//	public Glyph decKanGlGlyph;
//
//	@Override
//	public StatusColor getDecKanGlStatusColor() {
//		return StatusColor.GRAY;
//	}
//
//	@Override
//	public HashMap<DecoratorCanvasLocation, Decorator> getDecKanGlDecoratorMap() {
//		HashMap<DecoratorCanvasLocation, Decorator> decKanGlDecoratorMap =
//				new HashMap<DecoratorCanvasLocation, Decorator>();
//		if (getChidFractalsDecorator() != null) {
//			decKanGlDecoratorMap.put(
//					FmmGlyphDictionary.getInstance().getTribKn(FmmGlyphDictionary.tribKn_CHILD_FRACTALS).getLogicalCanvasLocation(),
//					getChidFractalsDecorator() );
//		}
//		if (getCompletionDecorator() != null) {
//			decKanGlDecoratorMap.put(
//					FmmGlyphDictionary.getInstance().getTribKn(FmmGlyphDictionary.tribKn_COMPLETION).getLogicalCanvasLocation(),
//					getCompletionDecorator() );
//		}
//		if (getFacilitationDecorator() != null) {
//			decKanGlDecoratorMap.put(
//					FmmGlyphDictionary.getInstance().getTribKn(FmmGlyphDictionary.tribKn_FACILITATION).getLogicalCanvasLocation(),
//					getFacilitationDecorator() );
//		}
//		if (getGovernanceDecorator() != null) {
//			decKanGlDecoratorMap.put(
//					FmmGlyphDictionary.getInstance().getTribKn(FmmGlyphDictionary.tribKn_GOVERNANCE).getLogicalCanvasLocation(),
//					getGovernanceDecorator() );
//		}
//		if (getStoryDecorator() != null) {
//			decKanGlDecoratorMap.put(
//					FmmGlyphDictionary.getInstance().getTribKn(FmmGlyphDictionary.tribKn_STORY).getLogicalCanvasLocation(),
//					getStoryDecorator() );
//		}
//		if (getTaskBudgetDecorator() != null) {
//			decKanGlDecoratorMap.put(
//					FmmGlyphDictionary.getInstance().getTribKn(FmmGlyphDictionary.tribKn_TASK_BUDGET).getLogicalCanvasLocation(),
//					getTaskBudgetDecorator() );
//		}
//		if (getWorkTeamDecorator() != null) {
//			decKanGlDecoratorMap.put(
//					FmmGlyphDictionary.getInstance().getTribKn(FmmGlyphDictionary.tribKn_WORK_TEAM).getLogicalCanvasLocation(),
//					getWorkTeamDecorator() );
//		}
//		return decKanGlDecoratorMap;
//	}
//
//	@Override
//	public Noun getDecKanGlNoun() {
//		if (this.decKanGlNoun == null) {
//			this.decKanGlNoun =
//		    		FmmGlyphDictionary.getInstance().getNoun(getDecKanGlNounName());
//		}
//		return this.decKanGlNoun;
//	}
//	
//	@Override
//	public String getDecKanGlNounName() {
//		return FmmGlyphDictionary.noun_SPRINT;  /////////////////////////////////////////////////
//	}
//
//	@Override
//	public Glyph getDecKanGlGlyph() {
//		if (this.decKanGlGlyph == null) {
//			this.updateDecKanGlGlyph();
//		}
//		return this.decKanGlGlyph;
//	}
//
//	@Override
//	public void updateDecKanGlGlyph() {
//		this.decKanGlGlyph = FmmGlyphDictionary.getInstance().getDecKanGlGlyph(this);
//		setImageIcon(decKanGlGlyph.getImageIcon());
//	}
//
//	@Override
//	public Glyph getUpdatedDecKanGlGlyph() {
//		this.updateDecKanGlGlyph();
//		return this.decKanGlGlyph;
//	}
//	
//	//////  default decorator getters  ////
//	
//	@Override
//	public Decorator getFacilitationDecorator() {
//		if (getGovernance().isNoFacilitator()) {
//			return DecoratorFacilitation.NO_FACILITATOR;
//		}
//		return DecoratorFacilitation.UNSPECIFIED_FACILITATION_QUALITY;
//	}
//
//	@Override
//	public Decorator getCommitmentDecorator() {
//		return null;
//	}
//	
//	@Override
//	public Decorator getCompletionDecorator() {
//		if (isComplete()) {
//			return DecoratorCompletion.UNCONFIRMED_COMPLETION;
//		} else if (isLate()) {
//			return DecoratorCompletion.PAST_DUE_COMPLETION;
//		}
//		return DecoratorCompletion.UNSPECIFIED_COMPLETION_QUALITY;
//	}
//	@Override
//	public Decorator getGovernanceDecorator() {
//		return getGovernance().isNoGovernance() ?
//				DecoratorGovernance.NO_GOVERNANCE :
//				DecoratorGovernance.UNSPECIFIED_GOVERNANCE_QUALITY;
//	}
//
//	@Override
//	public Decorator getParentFractalsDecorator() {
//		return null;
//	}
//
//	@Override
//	public Decorator getStoryDecorator() {
//		if (getNoteBodyOid() == null || getNoteBodyOid().equals("") || !isNoteBodyPersisted()) {
//			return DecoratorStory.NO_STORY;
//		} else {
//			return DecoratorStory.INCOMPLETE_STORY;
//		}
//	}
//
//	private boolean isNoteBodyPersisted() {
//		return PersistenceHelper.noteBodyFileExists(getFlywheelWorkspace(), getNoteBodyOid());
//	}
//	
//	@Override
//	public Decorator getChidFractalsDecorator() {
//		return DecoratorChildFractals.UNSPECIFIED_CHILD_FRACTALS_QUALITY;
//	}
//
//	@Override
//	public Decorator getWorkTeamDecorator() {
//		return null;
//	}
//
//	@Override
//	public Decorator getTaskBudgetDecorator() {
//		return null;
//	}
//	
////	//////  decorator getters  ////
////	
////	@Override
////	public DecKanGlDecorator getFacilitationDecorator() {
////		return DecoratorFacilitation.UNSPECIFIED_FACILITATION_QUALITY;
////	}
////
////	@Override
////	public DecKanGlDecorator getCommitmentDecorator() {
////		return DecoratorCommitment.UNSPECIFIED_COMMITMENT_QUALITY;
////	}
////	
////	@Override
////	public DecKanGlDecorator getCompletionDecorator() {
////		return DecoratorCompletion.UNSPECIFIED_COMPLETION_QUALITY;
////	}
////	
////	@Override
////	public DecKanGlDecorator getGovernanceDecorator() {
////		return DecoratorGovernance.UNSPECIFIED_GOVERNANCE_QUALITY;
////	}
////
////	@Override
////	public DecKanGlDecorator getParentFractalsDecorator() {
////		return DecoratorParentFractals.UNSPECIFIED_PARENT_FRACTAL_QUALITY;
////	}
////
////	@Override
////	public DecKanGlDecorator getStoryDecorator() {
////		return DecoratorStory.UNSPECIFIED_STORY_QUALITY;
////	}
////
////	@Override
////	public DecKanGlDecorator getChidFractalsDecorator() {
////		return DecoratorChildFractals.UNSPECIFIED_CHILD_FRACTALS_QUALITY;
////	}
////
////	@Override
////	public DecKanGlDecorator getWorkTeamDecorator() {
////		return DecoratorWorkTeam.UNSPECIFIED_TEAM_QUALITY;
////	}
////
////	@Override
////	public DecKanGlDecorator getTaskBudgetDecorator() {
////		return DecoratorTaskBudget.UNSPECIFIED_TASK_BUDGET_QUALITY;
////	}
//    
//}