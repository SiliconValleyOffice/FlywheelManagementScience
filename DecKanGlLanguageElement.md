# Introduction #

DecKanGL is a visual language for quickly communicating the status and quality of piece of data in a sophisticated application.

DecKanGlElement is the super class of all DecKanGL language elements.  The DecKanGlElement collaboration is used by the DecKanGlDictionaryActivity to provide an interactive tool for exploring an application's specific implementation of its DecKanGL dictionary.

This collaboration of classes is also used to maintain the "meta" nature of the DecKanGlDictionary and decouple the dictionary implementation (for the application) from the glyph generation.

# DecKanGlElement #

All language elements subclass DecKanGlElement.  DecKanGlElement attributes include:
  * element name (with a backing ResourceID)
  * drawable (with a backing ResourceID)

Subclasses of DecKanGlElement include:
  * DecKanGlElementNoun
  * DecKanGlElementNounState
  * DecKanGlElementNounQualityMetric

## DecKanGlNoun ##

Defines nouns in the application-specific DecKanGL dictionary

## DecKanGlNounState ##

Defines the overall state of the noun.  In FlywheelMS, this is the work state and include:
  * gray - no work
  * yellow - work started
  * orange - work on hold
  * pink - work needs governance review
  * green - work is complete

## DecKanGlNounQualityMetric ##

Defines each quality metric for nouns, as defined by the application.  In FlywheelMS these metrics are defines as:
  * child fractals
  * completion
  * facilitation issue
  * facilitator
  * flywheel commitment
  * governance
  * parent fractals
  * story
  * strategic commitment
  * work task budget
  * work team