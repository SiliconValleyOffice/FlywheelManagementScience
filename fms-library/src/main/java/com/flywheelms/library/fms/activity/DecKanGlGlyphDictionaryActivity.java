/* @(#)DecKanGlGlyphDictionaryActivity.java
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
package com.flywheelms.library.fms.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlAnnotatedGlyphSize;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratedGlyphSize;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDecoratorCanvasLocation;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlDictionaryActivityGlyphSize;
import com.flywheelms.gcongui.deckangl.enumerator.DecKanGlNounStateColor;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlDictionary;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlElementNoun;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlElementNounState;
import com.flywheelms.gcongui.deckangl.glyph.DecKanGlGlyph;
import com.flywheelms.gcongui.deckangl.interfaces.DecKanGlApplicationNoun;
import com.flywheelms.gcongui.deckangl.interfaces.DecKanGlDecorator;
import com.flywheelms.gcongui.gcg.helper.GcgGuiHelper;
import com.flywheelms.gcongui.gcg.helper.GcgSpinnerHelper;
import com.flywheelms.gcongui.gcg.interfaces.GcgGuiable;
import com.flywheelms.library.R;
import com.flywheelms.library.fmm.deckangl.FmmDecKanGlDictionary;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorCadenceCommitment;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorCompletion;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitationIssue;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorFacilitator;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorGovernance;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorProjectManagement;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorSequence;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorStory;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorStrategicCommitment;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkBreakdown;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkTaskBudget;
import com.flywheelms.library.fmm.deckangl.FmsDecoratorWorkTeam;
import com.flywheelms.library.fmm.node.impl.enumerator.CompletableWorkStatus;
import com.flywheelms.library.fms.helper.FmsHelpIndex;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class DecKanGlGlyphDictionaryActivity extends Activity implements DecKanGlApplicationNoun {
	
	private DecKanGlGlyph decKanGlGlyph;
	private OnItemSelectedListener sharedSpinnerListener = new SharedSpinnerListener();
	private int NUMBER_OF_INITIALIZED_SPINNERS = 15;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.deckangl_glyph_dictionary__activity);
		initializeSpinners();
		initializeDecKanGlImageView();
		getActionBar().setDisplayHomeAsUpEnabled(true);  // Show the Up button in the action bar.
	}
	
	private void initializeDecKanGlImageView() {
		updateDecKanGlImageView(getUpdatedDecKanGlGlyph());
		ImageView theImageView = (ImageView) this.findViewById(R.id.deckangl_glyph__image_view);
		theImageView.setOnClickListener(new View.OnClickListener() {
			
	        @Override
	           public void onClick(View view) {
	            incrementImageView();
	            }
	        });
	}
	
	private void incrementImageView() {
		DecKanGlDictionaryActivityGlyphSize theGlyphScale = getGlyphScale();
		switch(theGlyphScale) {
			case Tiny:
				setGlyphScale(DecKanGlDictionaryActivityGlyphSize.Small);
				break;
			case Small:
				setGlyphScale(DecKanGlDictionaryActivityGlyphSize.Medium);
				break;
			case Medium:
				setGlyphScale(DecKanGlDictionaryActivityGlyphSize.Large);
				break;
			case Large:
				setGlyphScale(DecKanGlDictionaryActivityGlyphSize.ANNOTATED);
				break;
			case ANNOTATED:
				setGlyphScale(DecKanGlDictionaryActivityGlyphSize.Tiny);
				break;
			default:
				// TODO - throw a nasty exception about bad programmers!  ;-)
		}
		updateDecKanGlImageView(getUpdatedDecKanGlGlyph());
	}

	private void setGlyphScale(DecKanGlDictionaryActivityGlyphSize theGlyphScale) {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.deckangl_glyph__scale__spinner);
		@SuppressWarnings("unchecked")
		ArrayAdapter<DecKanGlDictionaryActivityGlyphSize> theArrayAdapter = (ArrayAdapter<DecKanGlDictionaryActivityGlyphSize>) theSpinner.getAdapter();
		int theSpinnerPosition = theArrayAdapter.getPosition(theGlyphScale);
		theSpinner.setSelection(theSpinnerPosition);
	}

	private void updateDecKanGlImageView(DecKanGlGlyph aDecKanGlGlyph) {
		DecKanGlDictionaryActivityGlyphSize theGlyphScale = getGlyphScale();
		ImageView theImageView = (ImageView) this.findViewById(R.id.deckangl_glyph__image_view);
		Bitmap theBitmap;
		if(theGlyphScale == DecKanGlDictionaryActivityGlyphSize.ANNOTATED) {
			theBitmap = aDecKanGlGlyph.getAnnotatedGlyphBitmap(DecKanGlAnnotatedGlyphSize.SMALL);
		} else if(theGlyphScale == DecKanGlDictionaryActivityGlyphSize.Large) {
			theBitmap = aDecKanGlGlyph.getDecoratedNounBitmap(DecKanGlDecoratedGlyphSize.LARGE);
		} else if(theGlyphScale == DecKanGlDictionaryActivityGlyphSize.Medium) {
			theBitmap = aDecKanGlGlyph.getDecoratedNounBitmap(DecKanGlDecoratedGlyphSize.MEDIUM);
		} else if(theGlyphScale == DecKanGlDictionaryActivityGlyphSize.Small) {
			theBitmap = aDecKanGlGlyph.getDecoratedNounBitmap(DecKanGlDecoratedGlyphSize.SMALL);
		} else {
			theBitmap = aDecKanGlGlyph.getDecoratedNounBitmap(DecKanGlDecoratedGlyphSize.TINY);
		}
		GcgGuiHelper.updateScaledImageViewInTableRow(theImageView, theBitmap);
		updateNodeQualityView();
	}

	private void updateNodeQualityView() {
		TextView theLabelView = (TextView) this.findViewById(R.id.deckangl_noun__noun_quality__label);
		Drawable theDrawable;
		if(getDecKanGlGlyph().getNounQualityIndex() < 0) {
			theDrawable = getResources().getDrawable(R.drawable.deckangl__noun_quality__bad);
		} else {
			theDrawable = getResources().getDrawable(R.drawable.deckangl__noun_quality__good);
		}
		theDrawable.setBounds(0, 0, theDrawable.getIntrinsicWidth(), theDrawable.getIntrinsicHeight());
		theLabelView.setCompoundDrawablesWithIntrinsicBounds(theDrawable, null, null, null);
		TextView theIntegerView = (TextView) this.findViewById(R.id.deckangl_noun__noun_quality__data);
		GcgGuiHelper.updateIntegerView(theIntegerView, getDecKanGlGlyph().getNounQualityIndex());
	}

	private void initializeSpinners() {
		/* 
		 * must be initialized in the order in which they are defined in the layout.xml
		 */
		// Noun Status
		Collection<DecKanGlElementNoun> theNounCollection = FmmDecKanGlDictionary.getInstance().getNounCollection();
		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.deckangl_noun__label,
				R.id.deckangl_noun__spinner,
				R.string.deckangl__language_element__decorated_noun,
				R.drawable.deckangl,
				theNounCollection,
				(GcgGuiable) theNounCollection.toArray()[0],
				30 );
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.deckangl_noun__spinner,
				this.sharedSpinnerListener);
		
		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.deckangl__noun_state__label,
				R.id.deckangl__noun_state__spinner,
				CompletableWorkStatus.getStaticInstance(),
				CompletableWorkStatus.values(),
				CompletableWorkStatus.NOT_STARTED,
				30 );
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.deckangl__noun_state__spinner,
				this.sharedSpinnerListener);
		
		// DecKanGL Glyph
		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.deckangl_glyph__scale__label,
				R.id.deckangl_glyph__scale__spinner,
				R.string.deckangl__glyph_size,
				R.drawable.gcg__null_drawable,
				Arrays.asList(DecKanGlDictionaryActivityGlyphSize.values()) );
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.deckangl_glyph__scale__spinner,
				this.sharedSpinnerListener);
		
		// TribKn Decorators
//		DecoratorChildFractals
		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.tribkn_quality_metric__governance__label,
				R.id.tribkn_quality_metric__governance__spinner,
				FmsDecoratorGovernance.getStaticInstance(),
				FmsDecoratorGovernance.values(),
				FmsDecoratorGovernance.NO_GOVERNANCE );
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.tribkn_quality_metric__governance__spinner,
				this.sharedSpinnerListener );

		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.tribkn_quality_metric__facilitation_issue__label,
				R.id.tribkn_quality_metric__facilitation_issue__spinner,
				FmsDecoratorFacilitationIssue.getStaticInstance(),
				FmsDecoratorFacilitationIssue.values(),
				FmsDecoratorFacilitationIssue.NO_FACILITATION_ISSUE );
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.tribkn_quality_metric__facilitation_issue__spinner,
				this.sharedSpinnerListener );

		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.tribkn_quality_metric__facilitator__label,
				R.id.tribkn_quality_metric__facilitator__spinner,
				FmsDecoratorFacilitator.getStaticInstance(),
				FmsDecoratorFacilitator.values(),
				FmsDecoratorFacilitator.PROPOSED_FACILITATOR );
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.tribkn_quality_metric__facilitator__spinner,
				this.sharedSpinnerListener );

		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.tribkn_quality_metric__story__label,
				R.id.tribkn_quality_metric__story__spinner,
				FmsDecoratorStory.getStaticInstance(),
				FmsDecoratorStory.values(),
				FmsDecoratorStory.STORY_SWAG );
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.tribkn_quality_metric__story__spinner,
				this.sharedSpinnerListener );

		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.tribkn_quality_metric__completion__label,
				R.id.tribkn_quality_metric__completion__spinner,
				FmsDecoratorCompletion.getStaticInstance(),
				FmsDecoratorCompletion.values(),
				FmsDecoratorCompletion.COMPLETION_NOT_SCHEDULED );
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.tribkn_quality_metric__completion__spinner,
				this.sharedSpinnerListener );

		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.tribkn_quality_metric__sequence__label,
				R.id.tribkn_quality_metric__sequence__spinner,
				FmsDecoratorSequence.getStaticInstance(),
				FmsDecoratorSequence.values(),
				FmsDecoratorSequence.SEQUENCE_SWAG );
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.tribkn_quality_metric__sequence__spinner,
				this.sharedSpinnerListener );
		
		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.tribkn_quality_metric__child_fractals__label,
				R.id.tribkn_quality_metric__child_fractals__spinner,
				FmsDecoratorWorkBreakdown.getStaticInstance(),
				FmsDecoratorWorkBreakdown.values(),
				FmsDecoratorWorkBreakdown.CHILD_FRACTALS_SWAG );
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.tribkn_quality_metric__child_fractals__spinner,
				this.sharedSpinnerListener );

		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.tribkn_quality_metric__parent_fractals__label,
				R.id.tribkn_quality_metric__parent_fractals__spinner,
				FmsDecoratorProjectManagement.getStaticInstance(),
				FmsDecoratorProjectManagement.values(),
				FmsDecoratorProjectManagement.PARENT_FRACTALS_SWAG );
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.tribkn_quality_metric__parent_fractals__spinner,
				this.sharedSpinnerListener );

		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.tribkn_quality_metric__strategic_commitment__label,
				R.id.tribkn_quality_metric__strategic_commitment__spinner,
				FmsDecoratorStrategicCommitment.getStaticInstance(),
				FmsDecoratorStrategicCommitment.values(),
				FmsDecoratorStrategicCommitment.NO_STRATEGIC_COMMITMENT );
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.tribkn_quality_metric__strategic_commitment__spinner,
				this.sharedSpinnerListener );

		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.tribkn_quality_metric__flywheel_commitment__label,
				R.id.tribkn_quality_metric__flywheel_commitment__spinner,
				FmsDecoratorCadenceCommitment.getStaticInstance(),
				FmsDecoratorCadenceCommitment.values(),
				FmsDecoratorCadenceCommitment.NO_CADENCE_COMMITMENT);
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.tribkn_quality_metric__flywheel_commitment__spinner,
				this.sharedSpinnerListener );

		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.tribkn_quality_metric__work_task_budget__label,
				R.id.tribkn_quality_metric__work_task_budget__spinner,
				FmsDecoratorWorkTaskBudget.getStaticInstance(),
				FmsDecoratorWorkTaskBudget.values(),
				FmsDecoratorWorkTaskBudget.TASK_POINTS_BUDGET_SWAG);
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.tribkn_quality_metric__work_task_budget__spinner,
				this.sharedSpinnerListener );

		GcgSpinnerHelper.initializeGuiableSpinner(
				this,
				R.id.tribkn_quality_metric__work_team__label,
				R.id.tribkn_quality_metric__work_team__spinner,
				FmsDecoratorWorkTeam.getStaticInstance(),
				FmsDecoratorWorkTeam.values(),
				FmsDecoratorWorkTeam.WORK_TEAM_SWAG );
		GcgSpinnerHelper.setOnItemSelectedListener(
				this,
				R.id.tribkn_quality_metric__work_team__spinner,
				this.sharedSpinnerListener );
	}
	
//	spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
	class SharedSpinnerListener implements OnItemSelectedListener {
		
    	Toast toast;
    	int onItemSelectedEventCounter = 0;
		
	    @Override
	    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
	    	++this.onItemSelectedEventCounter;
	    	if(this.onItemSelectedEventCounter > DecKanGlGlyphDictionaryActivity.this.NUMBER_OF_INITIALIZED_SPINNERS) {
	    		updateDecKanGlImageView(getUpdatedDecKanGlGlyph());
//		    	toast = Toast.makeText(DecKanGlDictionaryActivity.this,
//		     			"Item selected at position: " + position, Toast.LENGTH_LONG);
//		    	toast.show();
	    	}
	    }

	    @Override
	    public void onNothingSelected(AdapterView<?> parentView) {
		    	this.toast = Toast.makeText(DecKanGlGlyphDictionaryActivity.this,
			     			"Nothing selected", Toast.LENGTH_LONG);
		     	this.toast.show();
	    }

	}

	@Override
	public boolean onCreateOptionsMenu(Menu aMenu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.deckangl_menu, aMenu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem aMenuItem) {
		if(aMenuItem.getItemId() == android.R.id.home) {
			this.finish();
		} else if (aMenuItem.getItemId() == R.id.action__help) {
			Intent theIntent = new Intent(Intent.ACTION_VIEW, 
					Uri.parse(FmsHelpIndex.DECKANGL_DICTIONARY));
			startActivity(theIntent);
		}
		return super.onOptionsItemSelected(aMenuItem);
	}
	
	////////  Other Spinner getters  /////////

	@Override
	public DecKanGlElementNoun getDecKanGlElementNoun() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.deckangl_noun__spinner);
		return (DecKanGlElementNoun) theSpinner.getSelectedItem();
	}
	
	public CompletableWorkStatus getCompletableWorkState() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.deckangl__noun_state__spinner);
		return (CompletableWorkStatus) theSpinner.getSelectedItem();
	}
	
	private DecKanGlDictionaryActivityGlyphSize getGlyphScale() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.deckangl_glyph__scale__spinner);
		return (DecKanGlDictionaryActivityGlyphSize) theSpinner.getSelectedItem();
	}
	
	////////  Decorator "getters"  //////////
	
	private FmsDecoratorGovernance getDecoratorGovernance() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.tribkn_quality_metric__governance__spinner);
		return (FmsDecoratorGovernance) theSpinner.getSelectedItem();
	}
	
	private FmsDecoratorFacilitationIssue getDecoratorFacilitationIssue() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.tribkn_quality_metric__facilitation_issue__spinner);
		return (FmsDecoratorFacilitationIssue) theSpinner.getSelectedItem();
	}
	
	private FmsDecoratorFacilitator getDecoratorFacilitator() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.tribkn_quality_metric__facilitator__spinner);
		return (FmsDecoratorFacilitator) theSpinner.getSelectedItem();
	}
	
	private FmsDecoratorProjectManagement getDecoratorParentFractals() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.tribkn_quality_metric__parent_fractals__spinner);
		return (FmsDecoratorProjectManagement) theSpinner.getSelectedItem();
	}
	
	private FmsDecoratorWorkBreakdown getDecoratorChildFractals() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.tribkn_quality_metric__child_fractals__spinner);
		return (FmsDecoratorWorkBreakdown) theSpinner.getSelectedItem();
	}
	
	private FmsDecoratorStrategicCommitment getDecoratorStrategicCommitment() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.tribkn_quality_metric__strategic_commitment__spinner);
		return (FmsDecoratorStrategicCommitment) theSpinner.getSelectedItem();
	}
	
	private FmsDecoratorCadenceCommitment getDecoratorFlywheelCommitment() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.tribkn_quality_metric__flywheel_commitment__spinner);
		return (FmsDecoratorCadenceCommitment) theSpinner.getSelectedItem();
	}
	
	private FmsDecoratorWorkTaskBudget getDecoratorWorkTaskBudget() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.tribkn_quality_metric__work_task_budget__spinner);
		return (FmsDecoratorWorkTaskBudget) theSpinner.getSelectedItem();
	}
	
	private FmsDecoratorWorkTeam getDecoratorWorkTeam() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.tribkn_quality_metric__work_team__spinner);
		return (FmsDecoratorWorkTeam) theSpinner.getSelectedItem();
	}
	
	private FmsDecoratorSequence getDecoratorSequence() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.tribkn_quality_metric__sequence__spinner);
		return (FmsDecoratorSequence) theSpinner.getSelectedItem();
	}
	
	private FmsDecoratorStory getDecoratorStory() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.tribkn_quality_metric__story__spinner);
		return (FmsDecoratorStory) theSpinner.getSelectedItem();
	}
	
	private FmsDecoratorCompletion getDecoratorCompletion() {
		Spinner theSpinner = (Spinner) this.findViewById(R.id.tribkn_quality_metric__completion__spinner);
		return (FmsDecoratorCompletion) theSpinner.getSelectedItem();
	}
	
	////////  DecKanGlNoun interface  //////////

	@Override
	public DecKanGlNounStateColor getDecKanGlNounStateColor() {
		return getCompletableWorkState().getWorkStateColor();
	}

	@Override
	public HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> getDecKanGlDecoratorMap() {
		HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> decKanGlDecoratorMap =
				new HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator>();
		decKanGlDecoratorMap.put(
				getDecoratorGovernance().getDecoratorCanvasLocation(),
				getDecoratorGovernance() );
		decKanGlDecoratorMap.put(
				getDecoratorFacilitationIssue().getDecoratorCanvasLocation(),
				getDecoratorFacilitationIssue() );
		decKanGlDecoratorMap.put(
				getDecoratorFacilitator().getDecoratorCanvasLocation(),
				getDecoratorFacilitator() );
		decKanGlDecoratorMap.put(
				getDecoratorParentFractals().getDecoratorCanvasLocation(),
				getDecoratorParentFractals() );
		decKanGlDecoratorMap.put(
				getDecoratorChildFractals().getDecoratorCanvasLocation(),
				getDecoratorChildFractals() );
		decKanGlDecoratorMap.put(
				getDecoratorStrategicCommitment().getDecoratorCanvasLocation(),
				getDecoratorStrategicCommitment() );
		decKanGlDecoratorMap.put(
				getDecoratorFlywheelCommitment().getDecoratorCanvasLocation(),
				getDecoratorFlywheelCommitment() );
		decKanGlDecoratorMap.put(
				getDecoratorWorkTaskBudget().getDecoratorCanvasLocation(),
				getDecoratorWorkTaskBudget() );
		decKanGlDecoratorMap.put(
				getDecoratorWorkTeam().getDecoratorCanvasLocation(),
				getDecoratorWorkTeam() );
		decKanGlDecoratorMap.put(
				getDecoratorSequence().getDecoratorCanvasLocation(),
				getDecoratorSequence() );
		decKanGlDecoratorMap.put(
				getDecoratorStory().getDecoratorCanvasLocation(),
				getDecoratorStory() );
		decKanGlDecoratorMap.put(
				getDecoratorCompletion().getDecoratorCanvasLocation(),
				getDecoratorCompletion() );
		return decKanGlDecoratorMap;
	}

	@Override
	public String getDecKanGlNounName() {
		return getDecKanGlElementNoun().getName();
	}

	@Override
	public DecKanGlGlyph getDecKanGlGlyph() {
		if(this.decKanGlGlyph == null) {
			updateDecKanGlGlyph();
		}
		return this.decKanGlGlyph;
	}

	@Override
	public void updateDecKanGlGlyph() {
		this.decKanGlGlyph = FmmDecKanGlDictionary.getInstance().getDecKanGlGlyph(this);
	}

	@Override
	public DecKanGlGlyph getUpdatedDecKanGlGlyph() {
		updateDecKanGlGlyph();
		return this.decKanGlGlyph;
	}

	@Override
	public DecKanGlElementNounState getDecKanGlNounState() {
		return DecKanGlDictionary.getInstance().getNounState(getCompletableWorkState().getName());
	}

	@Override
	public DecKanGlElementNounState updateDecKanGlNounState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DecKanGlNounStateColor computeDecKanGlNounStateColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<DecKanGlDecoratorCanvasLocation, DecKanGlDecorator> getUpdatedDecKanGlDecoratorMap() {
		// TODO Auto-generated method stub
		return null;
	}

}
