# Introduction #

GCG Applications support deep and wide navigation through complex data models.  Displaying the GcgApplicationContext enables the end user to maintain "positional awareness" of their current navigation context within the application.

## GcgApplicationContext versus GcgApplicationContextView ##

GcgApplicationContext is the context from which the current Activity was launched.

GcgApplicationContextView is the GUI container for the GcgApplicationContext.

# GcgApplicationContext #

GcgApplicationContext is the parent container for all elements of the application context.

## Data Members ##

  * dataSourceBreadcrumb
  * activityBreakcrumbList

## GcgApplicationContext Collaborating Classes ##

  * GcgDataSourceBreadcrumb
  * GcgActivityBreadcrumb
  * GcgFrameBreadcrumb
  * GcgPerspeciveBreadcrumb

# GcgDataSourceBreadcrumb #

A glyph and text that describe the source of the application's data, if applicable.

## Data Members ##

  * dataSourceBreadcrumb
  * activityBreakcrumbList

= GcgDataSourceBreadcrumb

## Data Members ##

  * drawableResourceId
  * drawable
  * dataText

# GcgActivityBreadcrumb #

Provides all of the context local to a single activity.  Serves as a Container for the following data members:
  * frameBreadcrumb
  * perspectiveBreadcrumbList

# GcgFrameBreadcrumb #

An activity's local frame context.

These are optional.  Only if they provide significant meaning.

## Data Members ##
  * frameDrawableResoueceId
  * frameDrawable
  * frameDataText
  * perspectiveDrawableResoueceId
  * perspectiveDrawable

# GcgPerspectiveContext #

For each element of context within the frame.

These are optional.  Only if there is a GcgFrameContext and there is context within the perspective that adds significant meaning.

## Data Members ##

  * drawableResourceId
  * drawable
  * dataText

# GcgBreadcrumb #

Base abstract class for other Breadcrumb implementations.

## Data Members ##

  * drawableResourceId
  * drawable
  * dataText

# GcgActivity #

This is the client class for this collaboration/functionality.

## Data Members ##
  * gcgApplicationContext


## Methods ##

  * getGcgApplicationContext()
> > get the data member
  * getGcgActivityBreadcrumb()
> > construct the local context as a breadcrumb
  * getChildContext()
> > a new instance of the GcgApplicationContext updated
> > with the current activity's GcgActivityBreadcrumb