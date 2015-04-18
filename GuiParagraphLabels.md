# Introduction #

Paragraph labels appear in a column to the left of the document text.

Since FSE is a _paragraph editor_ at its core, these labels are used to format, organize, navigate, and modify the contents of each paragraph.


# Paragraph Label Column #

The paragraph label column is a vertical column to the left of the document text area of FSE.  The column contains a label for each paragraph in the document.  The label appears to the left of the paragraph contents, centered vertically within the paragraph boundaries.

The paragraph label column always has at least 1 paragraph label, as an FSE document cannot be completely empty.

# Paragraph Label #

The label for each paragraph indicates the type of formatting for the paragraph; indent, font size and font weight.

The label is also a _touch point_ which can be used to initiate operations on the paragraph.

The background of the Paragraph Label is set to green when the paragraph has been selected for editing, or some other (singular or group) operation.

# Paragraph Label Commands #

Paragraph Label Commands initiate operations on the paragraph.

Tapping or touching the Paragraph Label initiates a Paragraph Label Command.

## Single Tap ##

Set the focus to the paragraph.

If in normal selection mode, any previously selected paragraphs are de-selected, the label is highlighted, and the cursor will be moved to the end of the paragraph for text entry.

If in _Multi-Shift_ **CTL** mode, the paragraph will be highlighted and any other paragraphs which may be highlighted will remain so.  This allows for the ad-hoc selection of multiple paragraphs.

If in _Multi-Shift_ **SHIFT** mode, the paragraph will be highlighted, as well as any un-highlighted paragraphs between it and previously highlighted paragraphs.

## Double Tap ##

Only functional in normal selection mode.

Label is highlighted and the cursor moved to the beginning of the paragraph for text entry.

## Triple Tap ##

Only functional in normal selection mode.

Label is highlighted and the entire contents of the paragraph are selected.

## Long Hold ##

Only functional in normal selection mode.

Any previously selected paragraphs are de-selected, the label is highlighted, and the cursor will be moved to the end of the paragraph for text entry.

In addition, a pop-up menu of alternative paragraph styles will appear.  The user can change the paragraph style by selecting an item from this list.