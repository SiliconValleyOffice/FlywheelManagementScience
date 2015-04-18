# Note #

Although originally intended as a state management document, it has morphed into an architectural overview of the FSE.  May want to split into 2 documents in the future.

# Introduction #

An important feature of FSE is its ability to track and efficiently communicate the modification state of documents and their individual paragraphs.

Modifications to any document section that is of type FseDocumentSectionParagraphEditorContent are tracked.

This programmer document will explore managing modification state in the GUI at run time.

# Modification State Events #

Modification state events can be described in terms of:
  * the type of modification
  * how the modification state change was initiated
  * where the state information is maintained

## Type of Modifications ##

These are the types of modifications that are tracked in an FSE Document.

**style** content
**insert** lock
**sequence** numbering
**other**

# Where Change Modification States are Maintained #

  * FseParagraphView (contained by paragraph editor)
  * FseParagraphEditor (contained by document section)
  * FseDocumentSectionParagraphEditorView (contained by document)
  * FseDocumentViewFlipper (contains all document sections, including Story and Notes)

## FseParagraphView ##

Contains the paragraph content (whatever type it may be; text, image, drawing, etc), paragraph state information and an AuditBlock.

Each paragraph is a first-class entity within a paragraph editor document section.

  * style
  * content
  * insert (derived)
  * lock
  * sequence
  * numbering

In addition to tracking the listed modification states, each paragraph view has a NodeFragAuditBlock that tracks other modification information such as create time, modification time and the users involved.  These can be used to generate transient databases/structures for in-depth document analysis; either within documents or across a population of documents.

## FseParagraphEditor ##

The Paragraph Editor contains a list of FseParagraphView and some editor state information.

The Paragraph Editor is a special case in regards to state management.  It only tracks the following state information:

  1. initial paragraph focus
  1. initial cursor position
  1. current paragraph focus
  1. paragraph selection list

Only the first 2 state information are persisted, and only when the document is saved/updated.

The paragraph editor does not track document or paragraph state directly, but can derive document state information as a "service" to other classes.  Document states which can be derived include:

  * style
  * content
  * insert ?
  * lock
  * sequence
  * numbering

The Paragraph Editor's role is to manage, modify (not content), reset and mediate paragraphs.

Manage:
  * paragraph list
  * paragraph focus
  * paragraph selection list
  * paragraph spinners
  * paragraph markup

Modify paragraph (single or multiple-select):
  * style
  * lock
  * sequence
  * numbering

Reset paragraph state after a document is saved.

Mediate between paragraph views and the document section view:
  * state changes
  * processing initiated by keyboard shortcut commands
  * route FDK input to paragraph with focus

### Manage Paragraph List ###
One purpose of the Paragraph Editor is to maintain the list of paragraphs in that document section.  This includes the following functions:
  * add paragraphs to the list
  * create new paragraphs in the list
  * merge paragraphs
  * remove paragraphs from the list
  * maintain paragraph sequence
  * change paragraph sequence

### Manage Paragraph Selection List ###
Another important function of the editor is to maintain a paragraph selection list, on which operations are performed.  The list frequently only includes the current paragraph focus, but can also include all paragraphs in a multiple-select situation.

### Manage/Modify Paragraph Styles ###
Paragraph editor also understands document structure from the perspective of paragraph style hierarchy.
  * Peers
  * Parents
  * Children

## FseDocumentSectionParagraphEditorView ##

Purpose is to:
  * contain paragraph editor content (versus a drawing or image or etc)
  * maintain aggregate state information for the entire document section
  * display aggregate document status
  * manage multi-shift controller state and provide its state information (upon request) to the Paragraph Editor
  * manage right thumb-menu state and forward menu item selection events to the Paragraph Editor

Contains:
  * Paragraph Editor
  * aggregate state information for all paragraphs in this document section
  * multi-shift controller
  * right thumb-menu for this content type (paragraph editor)

Aggregate state information that is maintained includes:
  * style
  * content
  * insert
  * lock
  * sequence
  * numbering

## FseDocumentViewFlipper ##

Contains:
  * all document section views, including Story and Notes (the only sections that contain Paragraph Editor content)
  * document baseline (before modifications since last save/read)
  * document margins

Document state is derived; currently from the Story and Notes document sections.

# How Modification State is Changed and Reported #

Propogate ?

  * FseParagraphView
  * FseParagraphEditor
  * FseDocumentSectionParagraphEditorView
  * FseDocumentViewFlipper

## FseDocumentViewFlipper ##



## FseDocumentSectionParagraphEditorView ##

This is an example of the state management pattern in the Document Section.  This pattern must be implemented for each managed state:

  * style
  * content
  * insert
  * lock
  * sequence
  * numbering

A note on method names:

  * setBlah() does not initiate a state change process
  * updateBlah() implies a state change that should be propagated

```
public void updateLockModificationState() {
    setLockModificationState(determineParagraphLockModificationState());
}

public void updateLockModificationState(FseLockModificationState aModificationState) {
    if(this.lockModificationState == aModificationState) {
        return;
    }
    setLockModificationState(aModificationState == FseLockModificationState.MODIFIED ? aModificationState : determineParagraphLockModificationState());
    onDataStateChange();
}

public void setLockModificationState(FseLockModificationState aModificationState) {
    if(this.lockModificationState == aModificationState) {
        return;
    }
    this.lockModificationState = aModificationState;
    this.lockModificationImage.setImageDrawable(aModificationState.getSummaryDrawable());
}

public FseLockModificationState determineParagraphLockModificationState() {
    for(FseParagraphView theParagraphView : this.paragraphEditor.getParagraphViewList()) {
        if(theParagraphView.isLockModified()) {
           return FseLockModificationState.MODIFIED;
        }
   }
   return FseLockModificationState.UNCHANGED;
}
```