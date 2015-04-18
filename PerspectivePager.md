# Introduction #

Perspectives are "paged" in both the HomeActivity and the various [Node](Node.md)Activities.  In addition to the standard Android swipable interface, the user can also navigate between pages using the vertical menu to the left of the TreeView.

GUI Elements:
  * TreeView
  * Menu
  * Filter

The TreeView is surrounded by the vertical Menu on the left and the vertical Filter view on the right.

# TreeView #

The TreeView provides  hierarchical review and navigation to the FMM nodes of interest in that perspective.

All FMS perspective TreeViews are _swipable_.  Only the TreeView swipes; the Menu and the Filter remain (visually) stationary.

## PerspectivePagerAdapter ##

This adapter is created and set on the ViewPager defined in the layout resource.  In the case of HomeActivity, the ViewPager is R.id.perspective\_view\_pager.

PerspectivePagerAdapter extends FragmentPagerAdapter.  FragmentPagerAdapter is an Android class that is a collaborator in the swipable framework.

The PerspectivePagerAdapter class provides the following:

  * initialize the perspective fragment list (Fragment for each perspective)
  * override FragmentPagerAdapter.getItem() to return the perspective _Fragment_.
  * override FragmentPagerAdapter.getCount() to get the number of perspectives
  * override getPageTitle() to return the perspective's name.