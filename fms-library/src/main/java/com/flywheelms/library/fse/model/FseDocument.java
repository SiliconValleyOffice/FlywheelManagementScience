/* @(#)FseDocument.java
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

import com.flywheelms.gcongui.deckangl.glyph.DecKanGlGlyph;
import com.flywheelms.gcongui.gcg.widget.date.GcgDateHelper;
import com.flywheelms.library.fca.FlywheelCommunityAuthentication;
import com.flywheelms.library.fmm.enumerator.FmmLockStatus;
import com.flywheelms.library.fmm.meta_data.IdNodeMetaData;
import com.flywheelms.library.fmm.meta_data.NodeFragMetaData;
import com.flywheelms.library.fmm.node.NodeId;
import com.flywheelms.library.fmm.node.impl.enumerator.FmmNodeDefinition;
import com.flywheelms.library.fmm.node.impl.governable.CommunityMember;
import com.flywheelms.library.fmm.node.impl.nodefrag.FmmNodeFragImpl;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragAuditBlock;
import com.flywheelms.library.fmm.node.impl.nodefrag.NodeFragGovernance;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNode;
import com.flywheelms.library.fmm.node.interfaces.horizontal.FmmNodeAudit;
import com.flywheelms.library.fse.FseDocumentSectionType;
import com.flywheelms.library.fse.enumerator.FseContentModificationState;
import com.flywheelms.library.fse.enumerator.FseLockModificationState;
import com.flywheelms.library.fse.enumerator.FseNotesTemplate;
import com.flywheelms.library.fse.enumerator.FseStoryTemplate;
import com.flywheelms.library.fse.enumerator.FseStyleModificationState;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionCommunityPerspective;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionHistoryPerspective;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionNotesPerspective;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionStoryPerspective;
import com.flywheelms.library.fse.perspective_flipper.perspective.FseDocumentSectionTribKnPerspective;
import com.flywheelms.library.fse.util.FseDocumentSerialization;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

public class FseDocument extends FmmNodeFragImpl implements FmmNodeAudit, Cloneable {
	
	private String nodeFragNodeIdString;
	@SuppressWarnings("unused")
	private String serializationFormatVersion = "0.1";  // TODO
	private boolean forExport = false;  // publication requires a Headline and TribKn
	private boolean forOfflineCollaboration = false;  // may be modified by the FlywheelStoryEditor
	private int documentMarginLeft = 10;
	private FseDocumentSectionType initialDocumentSectionType;
	private HashMap<FseDocumentSectionType, FseDocumentSection> documentSections = new HashMap<FseDocumentSectionType, FseDocumentSection>();
	private String serializedBaselineDocument;

	// de-serialize a document from transaction, without the history section
	public FseDocument(String aSerializedDocument, @SuppressWarnings("unused") FseDocumentTransaction aBogusArg) {
		super(NodeId.hydrate(FseDocument.class, getDocumentIdFromSerializedDocument(aSerializedDocument)), getParentIdFromSerializedDocument(aSerializedDocument));
//		inflateForTransactionFromSerialization(aSerializedDocument);
		try {
			JSONObject theJsonObject = new JSONObject(aSerializedDocument);
			this.serializationFormatVersion = theJsonObject.getString(FseDocumentSerialization.key__SERIALIZATION_FORMAT_VERSION);
			this.nodeFragNodeIdString = theJsonObject.getString(FseDocumentSerialization.key__NODE_FRAG_NODE_ID);
			this.forExport = theJsonObject.getBoolean(FseDocumentSerialization.key__FOR_EXPORT);
			this.documentMarginLeft = theJsonObject.getInt(FseDocumentSerialization.key__DOCUMENT_MARGIN__LEFT);
			this.initialDocumentSectionType = FseDocumentSectionType.getTypeForName(theJsonObject.getString(FseDocumentSerialization.key__DOCUMENT_INITIAL_SECTION_TAB));
			if(this.forExport) {
				this.documentSections.put(
						FseDocumentSectionType.TRIBKN,
						new FseDocumentSectionTribKn(theJsonObject.getJSONObject(FseDocumentSerialization.key__DOCUMENT_SECTION__TRIBKN)) );
			}
			this.documentSections.put(
					FseDocumentSectionType.COMMUNITY,
					new FseDocumentSectionCollaborators(theJsonObject.getJSONObject(FseDocumentSerialization.key__DOCUMENT_SECTION__COLLABORATORS)) );
			this.documentSections.put(
					FseDocumentSectionType.NOTES,
					new FseDocumentSectionNotes(theJsonObject.getJSONObject(FseDocumentSerialization.key__DOCUMENT_SECTION__NOTES)) );
			this.documentSections.put(
					FseDocumentSectionType.STORY,
					new FseDocumentSectionStory(theJsonObject.getJSONObject(FseDocumentSerialization.key__DOCUMENT_SECTION__STORY)) );
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// de-serialize an existing document
	public FseDocument(String aSerializedDocument) {
		this(aSerializedDocument, aSerializedDocument);
	}
	
	// de-serialize an existing document
	public FseDocument(String aSerializedDocument, String aSerializedBaselineDocuument) {
		super(NodeId.hydrate(FseDocument.class, getDocumentIdFromSerializedDocument(aSerializedDocument)), getParentIdFromSerializedDocument(aSerializedBaselineDocuument));
		this.serializedBaselineDocument = aSerializedBaselineDocuument;
		try {
			JSONObject theJsonObject = new JSONObject(aSerializedDocument);
			this.serializationFormatVersion = theJsonObject.getString(FseDocumentSerialization.key__SERIALIZATION_FORMAT_VERSION);
			this.nodeFragNodeIdString = theJsonObject.getString(FseDocumentSerialization.key__NODE_FRAG_NODE_ID);
			this.forExport = theJsonObject.getBoolean(FseDocumentSerialization.key__FOR_EXPORT);
			this.documentMarginLeft = theJsonObject.getInt(FseDocumentSerialization.key__DOCUMENT_MARGIN__LEFT);
			this.initialDocumentSectionType = FseDocumentSectionType.getTypeForName(theJsonObject.getString(FseDocumentSerialization.key__DOCUMENT_INITIAL_SECTION_TAB));
			if(this.forExport) {
				this.documentSections.put(
						FseDocumentSectionType.TRIBKN,
						new FseDocumentSectionTribKn(theJsonObject.getJSONObject(FseDocumentSerialization.key__DOCUMENT_SECTION__TRIBKN)) );
			}
			this.documentSections.put(
					FseDocumentSectionType.COMMUNITY,
					new FseDocumentSectionCollaborators(theJsonObject.getJSONObject(FseDocumentSerialization.key__DOCUMENT_SECTION__COLLABORATORS)) );
			this.documentSections.put(
					FseDocumentSectionType.HISTORY,
					new FseDocumentSectionHistory(theJsonObject.getJSONObject(FseDocumentSerialization.key__DOCUMENT_SECTION__HISTORY)) );
			this.documentSections.put(
					FseDocumentSectionType.NOTES,
					new FseDocumentSectionNotes(theJsonObject.getJSONObject(FseDocumentSerialization.key__DOCUMENT_SECTION__NOTES)) );
			this.documentSections.put(
					FseDocumentSectionType.STORY,
					new FseDocumentSectionStory(theJsonObject.getJSONObject(FseDocumentSerialization.key__DOCUMENT_SECTION__STORY)) );
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// create a new document, for publication, that has never been serialized/persisted
	public FseDocument(
			String aParentNodeIdString,
			String aHeadline,
			DecKanGlGlyph aDecKanGlGlyph,
			NodeFragGovernance aNodeFragGovernance,
			FseStoryTemplate anFseStoryTemplate,
			FseNotesTemplate anFseNotesTemplate ) {
		super(NodeId.hydrate(FseDocument.class, generatePublicationDocumentId()), aParentNodeIdString);
		aNodeFragGovernance.getNodeId().replaceTemporaryNodeId(getNodeIdString());
		this.forExport = true;
		this.initialDocumentSectionType = FseDocumentSectionType.NOTES;
		this.documentSections.put(FseDocumentSectionType.TRIBKN, new FseDocumentSectionTribKn(aHeadline, aDecKanGlGlyph, aNodeFragGovernance));
		this.documentSections.put(FseDocumentSectionType.COMMUNITY, new FseDocumentSectionCollaborators(new FseAuditBlock(getDocumentId(), "", GcgDateHelper.getCurrentDateTime()), new FseCollaboratorSummary()));
		this.documentSections.put(FseDocumentSectionType.HISTORY, new FseDocumentSectionHistory());
		this.documentSections.put(FseDocumentSectionType.NOTES, new FseDocumentSectionNotes());
		this.documentSections.put(FseDocumentSectionType.STORY, new FseDocumentSectionStory());
		newStorySection(anFseStoryTemplate);
		newNotesSection(anFseNotesTemplate);
		this.serializedBaselineDocument = getSerializedBaseline();  // template is our baseline
	}
	
	// Used to create an FseDocument from an FseDocumentView, without coupling
	public FseDocument(String aDocumentId, String aParentNodeIdString, int aDocumentMarginLeft) {
		super(NodeId.hydrate(FseDocument.class, aDocumentId), aParentNodeIdString);
		this.documentMarginLeft = aDocumentMarginLeft;
	}
	
	// create a new FseDocument for a parent
    public FseDocument(String aParentNodeIdString, boolean bIsForExportPublishing) {
		super(new NodeId(FseDocument.class, aParentNodeIdString, true), aParentNodeIdString);
		this.forExport = bIsForExportPublishing;
		this.initialDocumentSectionType = FseDocumentSectionType.STORY;
		Class<? extends FmmNode> theParentClass = FmmNodeDefinition.getEntryForNodeIdString(aParentNodeIdString).getNodeClass();
		this.documentSections.put(FseDocumentSectionType.COMMUNITY, new FseDocumentSectionCollaborators(new FseAuditBlock(getDocumentId(), "", GcgDateHelper.getCurrentDateTime()), new FseCollaboratorSummary()));
		this.documentSections.put(FseDocumentSectionType.HISTORY, new FseDocumentSectionHistory());
		this.documentSections.put(FseDocumentSectionType.NOTES, new FseDocumentSectionNotes());
		this.documentSections.put(FseDocumentSectionType.STORY, new FseDocumentSectionStory());
		newStorySection(FseStoryTemplate.getTemplateForClass(theParentClass));
		newNotesSection(FseNotesTemplate.getTemplateForClass(theParentClass));
		this.serializedBaselineDocument = getSerializedBaseline();  // template is our baseline
		initializeHistory();
	}

	@Override
	public FseDocument getClone() {
    	FseDocument theDocumentClone = new FseDocument(getSerialized());
        return theDocumentClone;
    }
	
	public static String generatePublicationDocumentId() {
		StringBuilder theStringBuilder = new StringBuilder(FmmNodeDefinition.getEntryForClass(FseDocument.class).getNodeTypeCode() + "-");
		theStringBuilder.append(UUID.randomUUID());
		return theStringBuilder.toString();
	}

	public String getDocumentId() {
		return getNodeIdString();
	}

	public FseDocumentSectionType getInitialDocumentSectionType() {
		return this.initialDocumentSectionType;
	}

	public void setInitialDocumentSectionType(
			FseDocumentSectionType initialDocumentSectionType) {
		this.initialDocumentSectionType = initialDocumentSectionType;
	}

	public FseDocumentSectionTribKn getDocumentSectionTribKn() {
		return (FseDocumentSectionTribKn) this.documentSections.get(FseDocumentSectionType.TRIBKN);
	}

	public FseDocumentSectionCollaborators getDocumentSectionCollaborators() {
		return (FseDocumentSectionCollaborators) this.documentSections.get(FseDocumentSectionType.COMMUNITY);
	}

	public FseDocumentSectionHistory getDocumentSectionHistory() {
		return (FseDocumentSectionHistory) this.documentSections.get(FseDocumentSectionType.HISTORY);
	}

	public FseDocumentSectionStory getDocumentSectionStory() {
		return (FseDocumentSectionStory) this.documentSections.get(FseDocumentSectionType.STORY);
	}
	
	public FseDocumentSectionNotes getDocumentSectionNotes() {
		return (FseDocumentSectionNotes) this.documentSections.get(FseDocumentSectionType.NOTES);
	}

	private void newStorySection(FseStoryTemplate anFseStoryTemplate) {
		getDocumentSectionStory().setParagraphList(new LinkedHashMap<String, FseParagraph>());
		String theInitialParagraphFocusId = "";
		int theIndex = -1;
		for(FseTemplateParagraph theFseTemplateParagraph : anFseStoryTemplate.getFseTemplateParagraphArray()) {
			FseParagraph theFseParagraph = new FseParagraph(theFseTemplateParagraph);
			getDocumentSectionStory().getParagraphList().put(theFseParagraph.getParagraphId(), theFseParagraph);
			if(++theIndex == anFseStoryTemplate.getInitialParagraphFocusIndex()) {
				theInitialParagraphFocusId = theFseParagraph.getParagraphId();
			}
		}
		getDocumentSectionStory().renumberAll();
		getDocumentSectionStory().setInitialParagraphFocusId(theInitialParagraphFocusId);
		getDocumentSectionStory().setInitialParagraphFocusCursorPosition(anFseStoryTemplate.getInitialParagraphFocusCursorPosition());
		getDocumentSectionStory().setLockModificationState(FseLockModificationState.UNCHANGED);
		getDocumentSectionStory().setContentModificationState(FseContentModificationState.NEW);
		getDocumentSectionStory().setStyleModificationState(FseStyleModificationState.UNCHANGED);
	}

	private void newNotesSection(FseNotesTemplate anFseNotesTemplate) {
		getDocumentSectionNotes().setParagraphList(new LinkedHashMap<String, FseParagraph>());
		String theInitialParagraphFocusId = "";
		int theIndex = -1;
		for(FseTemplateParagraph theFseTemplateParagraph : anFseNotesTemplate.getFseTemplateParagraphArray()) {
			FseParagraph theFseParagraph = new FseParagraph(theFseTemplateParagraph);
			getDocumentSectionNotes().getParagraphList().put(theFseParagraph.getParagraphId(), theFseParagraph);
			if(++theIndex == anFseNotesTemplate.getInitialParagraphFocusIndex()) {
				theInitialParagraphFocusId = theFseParagraph.getParagraphId();
			}
		}
		getDocumentSectionNotes().renumberAll();
		getDocumentSectionNotes().setInitialParagraphFocusId(theInitialParagraphFocusId);
		getDocumentSectionNotes().setInitialParagraphFocusCursorPosition(anFseNotesTemplate.getInitialParagraphFocusCursorPosition());
		getDocumentSectionNotes().setLockModificationState(FseLockModificationState.UNCHANGED);
		getDocumentSectionNotes().setContentModificationState(FseContentModificationState.NEW);
		getDocumentSectionNotes().setStyleModificationState(FseStyleModificationState.UNCHANGED);
	}
	
	private static String getDocumentIdFromSerializedDocument(String aSerializedDocument) {
		try {
			JSONObject theJsonObject = new JSONObject(aSerializedDocument);
			return theJsonObject.getString(IdNodeMetaData.column_ID);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";	
	}
	
	private static String getParentIdFromSerializedDocument(String aSerializedDocument) {
		try {
			JSONObject theJsonObject = new JSONObject(aSerializedDocument);
			return theJsonObject.getString(NodeFragMetaData.column_PARENT_ID);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";	
	}
	
	public boolean forExport() {
		return this.forExport;
	}
	
	public void setForExport(boolean aBoolean) {
		this.forExport = aBoolean;
	}

	public String getHeadline() {
		return getDocumentSectionTribKn().getHeadline();
	}
	
	public int getDocumentMarginLeft() {
		return this.documentMarginLeft;
	}

	/**
	 * at this point, FseDocument is only created from a serialized source
	 * or from a template.  FseDocument is currently (functionally) immutable.
	 */
	public String getSerializedBaseline() {
		if(this.serializedBaselineDocument == null) {  // null if this is a new document from template
			this.serializedBaselineDocument = getJsonObject().toString();
		}
		return this.serializedBaselineDocument;
	}
	
	public String getDocumentTransactionTypeString() {
		return getDocumentSectionHistory().getDocumentTransactionList().get(
					getDocumentSectionHistory().getDocumentTransactionList().size() - 1).getTransactionType().getDocumentTransactionType();
	}
	
	public FseDocumentTransactionType getFseDocumentTransactionType() {
		return FseDocumentTransactionType.getObjectForName(getDocumentTransactionTypeString());
	}
	
	public JSONObject getJsonTransactionObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(FseDocumentSerialization.key__SERIALIZATION_FORMAT_VERSION, FseDocumentSerialization.SERIALIZATION_FORMAT_VERSION);
			theJsonObject.put(FseDocumentSerialization.key__NODE_FRAG_NODE_ID, getNodeFragNodeIdString());
			theJsonObject.put(FseDocumentSerialization.key__FOR_EXPORT, this.forExport);
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_MARGIN__LEFT, this.documentMarginLeft);
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_INITIAL_SECTION_TAB, this.initialDocumentSectionType.toString());
			if(this.forExport) {
				theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__TRIBKN, ((FseDocumentSectionTribKn) this.documentSections.get(FseDocumentSectionType.TRIBKN)).getJsonObject());
			}
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__COLLABORATORS, ((FseDocumentSectionCollaborators) this.documentSections.get(FseDocumentSectionType.COMMUNITY)).getJsonObject());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__STORY, ((FseDocumentSectionParagraphEditorContent) this.documentSections.get(FseDocumentSectionType.STORY)).getJsonObject());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__NOTES, ((FseDocumentSectionParagraphEditorContent) this.documentSections.get(FseDocumentSectionType.NOTES)).getJsonObject());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}
	
	@Override
	public JSONObject getJsonObject() {
		JSONObject theJsonObject = super.getJsonObject();
		try {
			theJsonObject.put(FseDocumentSerialization.key__SERIALIZATION_FORMAT_VERSION, FseDocumentSerialization.SERIALIZATION_FORMAT_VERSION);
			theJsonObject.put(FseDocumentSerialization.key__NODE_FRAG_NODE_ID, getNodeFragNodeIdString());
			theJsonObject.put(FseDocumentSerialization.key__FOR_EXPORT, this.forExport);
			theJsonObject.put(FseDocumentSerialization.key__FOR_OFFLINE_COLLABORATION, this.forOfflineCollaboration);
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_MARGIN__LEFT, this.documentMarginLeft);
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_INITIAL_SECTION_TAB, this.initialDocumentSectionType.toString());
			if(this.forExport) {
				theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__TRIBKN, ((FseDocumentSectionTribKn) this.documentSections.get(FseDocumentSectionType.TRIBKN)).getJsonObject());
			}
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__COLLABORATORS, ((FseDocumentSectionCollaborators) this.documentSections.get(FseDocumentSectionType.COMMUNITY)).getJsonObject());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__STORY, ((FseDocumentSectionParagraphEditorContent) this.documentSections.get(FseDocumentSectionType.STORY)).getJsonObject());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__NOTES, ((FseDocumentSectionParagraphEditorContent) this.documentSections.get(FseDocumentSectionType.NOTES)).getJsonObject());
			theJsonObject.put(FseDocumentSerialization.key__DOCUMENT_SECTION__HISTORY, ((FseDocumentSectionHistory) this.documentSections.get(FseDocumentSectionType.HISTORY)).getJsonObject());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return theJsonObject;
	}
	
	public LinkedHashMap<String, FseParagraph> getParagraphList(FseDocumentSectionType aSectionType) {
		LinkedHashMap<String, FseParagraph> theParagraphList;
		switch (aSectionType) {
		case STORY:
			theParagraphList = getDocumentSectionStory().getParagraphList();
			break;
		case NOTES:
			theParagraphList = getDocumentSectionNotes().getParagraphList();
			break;
		default:
			theParagraphList = null;
		}
		return theParagraphList;
	}
	
	public String getInitialParagraphFocusId(FseDocumentSectionType aSectionType) {
		String theParagraphId;
		switch (aSectionType) {
		case STORY:
			theParagraphId = getDocumentSectionStory().getInitialParagraphFocusId();
			break;
		case NOTES:
			theParagraphId = getDocumentSectionNotes().getInitialParagraphFocusId();
			break;
		default:
			theParagraphId = "";
		}
		return theParagraphId;
	}
	
	public FseParagraph getInitialParagraphFocus(FseDocumentSectionType aSectionType) {
		FseParagraph theParagraph;
		switch (aSectionType) {
		case STORY:
			theParagraph = getDocumentSectionStory().getParagraphList().get(getInitialParagraphFocusId(aSectionType));
			break;
		case NOTES:
			theParagraph = getDocumentSectionNotes().getParagraphList().get(getInitialParagraphFocusId(aSectionType));
			break;
		default:
			theParagraph = null;
		}
		return theParagraph;
	}
	
	public int getInitialParagraphFocusCursorPosition(FseDocumentSectionType aSectionType) {
		int theCursorPosition;
		switch (aSectionType) {
		case STORY:
			theCursorPosition = getDocumentSectionStory().getInitialParagraphFocusCursorPosition();
			break;
		case NOTES:
			theCursorPosition = getDocumentSectionNotes().getInitialParagraphFocusCursorPosition();
			break;
		default:
			theCursorPosition = 0;
		}
		return theCursorPosition;
	}

	public void setDocumentSectionTribKn(FseDocumentSectionTribKnPerspective aDocumentSectionTribKnView) {
		FseDocumentSectionTribKn theDocumentSectionTribKn = aDocumentSectionTribKnView.getDocumentSectionClone();
		this.documentSections.put(FseDocumentSectionType.TRIBKN, theDocumentSectionTribKn);
	}

	public void setDocumentSectionCollaborators(FseDocumentSectionCommunityPerspective aDocumentSectionCollaboratorsView) {
		FseDocumentSectionCollaborators theDocumentSectionCollaborators = aDocumentSectionCollaboratorsView.getDocumentSectionClone();
		this.documentSections.put(FseDocumentSectionType.COMMUNITY, theDocumentSectionCollaborators);
	}

	public void setDocumentSectionHistory(FseDocumentSectionHistoryPerspective aDocumentSectionHistoryView) {
		this.documentSections.put(FseDocumentSectionType.HISTORY, aDocumentSectionHistoryView.getDocumentSectionClone());
	}

	public void setDocumentSectionStory(FseDocumentSectionStoryPerspective aDocumentSectionStoryView) {
		this.documentSections.put(FseDocumentSectionType.STORY, aDocumentSectionStoryView.getDocumentSectionClone());
	}

	public void setDocumentSectionStory(FseDocumentSectionStory aDocumentSectionStory) {
		this.documentSections.put(FseDocumentSectionType.STORY, aDocumentSectionStory.getClone());
	}

	public void setDocumentSectionNotes(FseDocumentSectionNotesPerspective aDocumentSectionNotesView) {
		this.documentSections.put(FseDocumentSectionType.NOTES, aDocumentSectionNotesView.getDocumentSectionClone());
	}

	public void setDocumentSectionNotes(FseDocumentSectionNotes aDocumentSectionNotes) {
		this.documentSections.put(FseDocumentSectionType.NOTES, aDocumentSectionNotes.getClone());
	}
	
	public void setDocumentMarginLeft(int aDocumentMarginLeft) {
		this.documentMarginLeft = aDocumentMarginLeft;
	}
	
	////  START Audit Block  Wrapper ////
	
	@Override
	public NodeFragAuditBlock getNodeFragAuditBlock() {
		return getDocumentSectionCollaborators().getFseParagraphAuditBlock();
	}

    @Override
    public NodeFragAuditBlock getUpdatedNodeFragAuditBlock() {
        return null;
    }

    @Override
    public void setNodeFragAuditBlock(NodeFragAuditBlock auditBlock) {  /*  N/A  */  }

    public void setNodeFragAuditBlock(FseAuditBlock anAuditBlock) {
		getDocumentSectionCollaborators().setFseParagraphAuditBlock(anAuditBlock);
	}
	
	//  Created  //
	
	@Override
	public String getCreatedByNodeIdString() {
		return getNodeFragAuditBlock().getCreatedByNodeIdString();
	}
	
	@Override
	public void setCreatedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setCreatedBy(aNodeIdString);
	}
	
	@Override
	public CommunityMember getCreatedByCommunityMember() {
		return getNodeFragAuditBlock().getCreatedByCommunityMember();
	}

	@Override
	public void setCreatedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setCreatedBy(aCommunityMember);
	}

	@Override
	public Date getCreatedTimestamp() {
		return getNodeFragAuditBlock().getCreatedTimestamp();
	}
	
	@Override
	public void setCreatedTimestamp(Date aCreatedTimestamp) {
		getNodeFragAuditBlock().setCreatedTimestamp(aCreatedTimestamp);
	}
	
	//  Updated  //

	@Override
	public String getLastUpdatedByNodeIdString() {
		return getNodeFragAuditBlock().getLastUpdatedByNodeIdString();
	}

	@Override
	public void setLastUpdatedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setLastUpdatedBy(aNodeIdString);
	}

	@Override
	public CommunityMember getLastUpdatedByCommunityMember() {
		return getNodeFragAuditBlock().getLastUpdatedByCommunityMember();
	}

	@Override
	public void setLastUpdatedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setLastUpdatedBy(aCommunityMember);
	}

	@Override
	public Date getLastUpdatedTimestamp() {
		return getNodeFragAuditBlock().getRowTimestamp();
	}

	public void setLastUpdatedTimestamp(Date aLastUpdate) {
		getNodeFragAuditBlock().setRowTimestamp(aLastUpdate);
	}
	
	//  Locked  //

	@Override
	public NodeId getLockedByNodeId() {
		return getNodeFragAuditBlock().getLockedByNodeId();
	}

	@Override
	public String getLockedByNodeIdString() {
		return getNodeFragAuditBlock().getLockedByNodeId().getNodeIdString();
	}

	@Override
	public CommunityMember getLockedByCommunityMember() {
		return getNodeFragAuditBlock().getLockedByCommunityMember();
	}
	
	@Override
	public void setLockedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setLockedBy(aNodeIdString);
	}

	@Override
	public void setLockedBy(NodeId aNodeId) {
		getNodeFragAuditBlock().setLockedBy(aNodeId);
	}

	@Override
	public void setLockedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setLockedBy(aCommunityMember);
	}

	@Override
	public Date getLockedTimestamp() {
		return getNodeFragAuditBlock().getLockedTimestamp();
	}
	
	@Override
	public void setLockedTimestamp(Date aLockedTimestamp) {
		getNodeFragAuditBlock().setLockedTimestamp(aLockedTimestamp);
	}
	
	////  Unlock  ////

	@Override
	public String getUnlockedByNodeIdString() {
		return getNodeFragAuditBlock().getUnlockedByNodeId().getNodeIdString();
	}

	@Override
	public NodeId getUnlockedByNodeId() {
		return getNodeFragAuditBlock().getUnlockedByNodeId();
	}

	@Override
	public CommunityMember getUnlockedByCommunityMember() {
		return getNodeFragAuditBlock().getUnlockedByCommunityMember();
	}
	
	@Override
	public void setUnlockedBy(String aNodeIdString) {
		getNodeFragAuditBlock().setUnlockedBy(aNodeIdString);
	}

	@Override
	public void setUnlockedBy(NodeId aNodeId) {
		getNodeFragAuditBlock().setUnlockedBy(aNodeId);
	}

	@Override
	public void setUnlockedBy(CommunityMember aCommunityMember) {
		getNodeFragAuditBlock().setUnlockedBy(aCommunityMember);
	}

	@Override
	public Date getUnlockedTimestamp() {
		return getNodeFragAuditBlock().getUnlockedTimestamp();
	}
	
	@Override
	public void setUnlockedTimestamp(Date anUnlockedTimestamp) {
		getNodeFragAuditBlock().setUnlockedTimestamp(anUnlockedTimestamp);
	}

	@Override
	public void unlock() {
		// we want to know who unlocked it, and when
		getNodeFragAuditBlock().setUnlockedBy(FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString());
		getNodeFragAuditBlock().setUnlockedTimestamp(GcgDateHelper.getCurrentDateTime());
		getNodeFragAuditBlock().setIsLocked(false);
	}
	
	/////////////////

	@Override
	public void lock() {
		getNodeFragAuditBlock().setLockedBy(FlywheelCommunityAuthentication.getInstance().getFcaUserCredentials().getCommunityMemberNodeIdString());
		getNodeFragAuditBlock().setLockedTimestamp(GcgDateHelper.getCurrentDateTime());
		getNodeFragAuditBlock().setIsLocked(true);
	}
	
	@Override
	public void setIsLocked(boolean aBoolean) {
		getNodeFragAuditBlock().setIsLocked(aBoolean);
	}

	@Override
	public boolean isLocked() {
		return getNodeFragAuditBlock().isLocked();
	}
	
	@Override
	public FmmLockStatus getLockStatus() {
		return getNodeFragAuditBlock().getLockStatus();
	}

	public void resetModificationState() {
		getDocumentSectionStory().resetModificationState();
		getDocumentSectionNotes().resetModificationState();
	}

	public void initializeHistory() {
		FseDocumentSectionParagraphAudit theStoryAudit = getDocumentSectionStory().getInitialParagraphAudit();
		FseDocumentSectionParagraphAudit theNotesAudit = getDocumentSectionNotes().getInitialParagraphAudit();
		getDocumentSectionHistory().updateHistory(GcgDateHelper.getCurrentDateTime(), FseDocumentTransactionType.CREATE, "", theStoryAudit, theNotesAudit, this);
		getDocumentSectionCollaborators().getCollaboratorSummary().updateCommunityMemberParticipationList(getDocumentSectionHistory());
	}
	
	public boolean equalsContent(FseDocument anFseDocument) {
		return getDocumentSectionStory().equalsContent(anFseDocument.getDocumentSectionStory()) &&
				getDocumentSectionNotes().equalsContent(anFseDocument.getDocumentSectionNotes());
	}

	public ArrayList<FseDocumentTransaction> getTransactionList() {
		return getDocumentSectionHistory().getDocumentTransactionList();
	}
	
	public FseDocument getLastTransactionWithMarkup() {
		return getTransactionList().get(getTransactionList().size()-1).getFseDocument();
	}

	public String getNodeFragNodeIdString() {
		return this.nodeFragNodeIdString;
	}
	
	public void setNodeFragNodeIdString(String aNodeIdString) {
		this.nodeFragNodeIdString = aNodeIdString;
	}

}
