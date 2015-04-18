# Guiding Principles #

These principles should guide all decisions regarding design and style of the various FlywheelMS code bases.  Principles are listed in order of importance/precedence.

  * establish and maintain **Strong Metaphors**
  * code should **Read Like a Book**
  * design for Extensibility
  * use enumerators wherever there is a finite list of known objects
  * never pass up an opportunity to refactor
  * don't outsmart yourself (or the next programmer)
  * avoid Premature Optimization (which leads to unsatisfying software)

## establish and maintain **Strong Metaphors** ##

Creation and enforcement of strong metaphors will yield the following benefits:

  * the perception of complexity is greatly diminished
  * extensibility is  greatly improved
  * code "reads" much faster and more accurately
  * new team members can more quickly _come up to speed_ and make quality contributions to the code base

## code should **Read Like a Book** ##

  * code should be self documenting.  documentation and comments should rarely be needed
  * code should be pleasant to read as a narrative
  * long names are cool (and often required), as long as they read well and follow the rest of the rules

# Variable Names #

## Reuse the Class Name ##

Reuse the class name if it is unique to the application (98% of the time).  If the class name doesn't seem to work well, then it is not a properly named class.  Refactor the name.

Examples:
```
TeamRole		- reuse this class name
                                      Examples:
				Data Members
					teamRole
					teamRoleList
				Method Parameters
                                               		aTeamRole
                                               		aTeamRoleList
				Local Variables
                                               		theTeamRole
                                               		theTeamRoleList
String			- do not use this
			   use a logical name like “firstName” or “description”
```

## Collections ##

Do not just make the variable name plural.

Name collections blahList
> This does not imply it is a Java List class, only a convenient way to indicate that it is a collection.
> > We use List because it is a shorter word than collection and “reads” well.
> > If you feel compelled, use the Java class instead; Vector, HashTable, Collection

The main point is, that you do not just make the variable name plural.
Examples:
```
	theRepositoryList
	anImageHashTable
```

## Class Data Members ##

No special prefix.

Reuse the class name if it is unique to the application (98% of the time), but make the first letter lower case.
```
TeamRole		- reuse this class name for the name “teamRole”
String			- do not use this
			   use a logical name like “firstName” or “fileName”
```

## Method Parameters ##

Begin with “a” or “an” (or "b" for boolean) to indicate it is a parameter.

Reuse the class name if it is unique to the application (98% of the time)
TeamRole		- use this in the name
String			- do not use this

> use a logical name like “FirstName” or “Description”

Examples:
```
	aTeamRole
	aFirstName
        anExampleVariable
        bSomeCondition
```

## Local Variables ##

Begin with “the” to indicate it is a local variable, unless boolean.  Boolean variables start with "is" or "has".

Reuse the class name if it is unique to the application (98% of the time)
TeamRole		- use this in the name
String			- do not use this
> use a logical name like “FirstName” or “Description”

Examples:
```
	theTeamRole
	theFirstName
	hasAttribute
        isTrueCondition
```

## Static Variables ##

Should be all caps, with tokens separated by underscore, except for the special case.  In that case, first token should be lower case.

Special case is when the first condition and at least one other is met:
  * the first token is a noun.
  * noun is the same as the data type
  * there is a list (usually 2 or more) of these static variables

Example:
```
	ImageIcon icon_SMILEY_FACE
	File file_ERROR_LOG
	String path_IMAGE_FILE_1
	String path_IMAGE_FILE_2
	String path_IMAGE_FILE_3
```

## Method Names ##

[Action](Action.md)[Object](Object.md)
```
    AddPerson()
    DisablePerson()
```

## Feature Names ##

[Object](Object.md)[Action](Action.md)
```
    OrganizationNew
    FlywheelNew
    FlywheelRename
```

# Blank Lines #

We do not want “sparse” code listings.  No blank lines in the code, except for a single line after:
  * package name
  * imports
  * class declaration
  * data member declarations
  * final curly brace of method declaration.

Exceptions:
  * logical groupings of data member declarations
  * long/complex switch statements

# Curly Braces #

  * Opening brace on same line as the declaration.
  * Closing brace directly “under” the beginning of the code block.

```
Public void someMethod() {
    doSomething();
}

if (true) {
    doSomething();
}
If Blocks
Example:
if(true) {
    doSomething();
}
else if(true) {
    doSomethingElse();
}
else {
    doSomethingSilly();
}
```