# Introduction #

GcgWidgets are custom user interface classes that can be referenced in layout.xml files.  Some of them also support custom attributes that can be set via xml.

## Examples ##

  * GcgWidgetEditText
  * GcgWidgetSpinner
  * GcgWidgetTextView

## Responsibilities ##

  * ViewGroup layout
  * focus management between widget elements
  * focus management in parent container
  * tab order management
  * initial data value
  * data validation
  * label text
  * data state management (modified?)
  * enable/disable soft keyboard (EditText)

# Base Class #

All widgets are subclasses of GcgWidget.

# GUI Elements #

All widgets include a label and a data input and/or display control.

# Custom Attributes #

Custom attributes are defined in gcgattrs.xml and include:
  * container layout
  * transparent background
  * label
  * number of states
  * initial state