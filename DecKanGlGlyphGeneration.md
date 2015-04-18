# Introduction #

DecKanGL glyphs are generated at run time based upon application-specific:

  * noun definitions
  * noun state definitions
  * noun quality definitions (decorators)
  * data contents

## Collaborating Classes ##

  * DecKanGlDictionary
  * DecKanGlGlyph

# DecKanGL Dictionary #

DecKanGlDictionary is the super class for all application/domain instances of a DecKanGl dictionary.  Each application defines its own DecKanGlDictionary subclass.

This DecKanGlDictionary subclass contains the application's unique visual language.  In the case of FlywheelMS, the subclass is FmsDecKanGlDictionary.

# DecKanGlGlyph #

This is the logical glyph generated for a specific noun "instance" in an application.  The resulting glyph includes a stateful noun image in the center, surrounded by quality "decorators".  Some experienced executives have commented that it looks like a "mini dashboard" for the data item.

A glyph may optionally be annotated, which includes:
  * description of each decorator
  * quality index for decorator
  * the aggregate quality index for that instance of the noun

Collaborating classes include:
  * DecKanGlGlyphCache
  * GlyphCanvas
  * DecKanGlGlyphSerialization

## DecKanGlGlyphCache ##

DecKanGl glyphs may be generated for thousands of nouns in an application that has accumulated lots of data.  Rather than re-generate these glyphs (expensive) they are cached and re-used.

These glyphs are cached using a synthesized glyph key generated from the following information:
  * noun name
  * glyph scaling
  * noun state
  * the value of each quality metric (decorator)

The synthetic glyph key is obtained using a static method of DecKanGlGlyphCache.

```
String theSyntheticGlyphKey = DecKanGlGlyphCache.synthesizeGlyphKey(aGlyphScaling, aDecKanGlNoum);
```

## GlyphCanvas ##

The GlyphCanvas is responsible for drawing one instance of a DecKanGL glyph bitmap, based upon the following parameters:
  * glyph scaling
  * noun state
  * the value of each quality metric (decorator)

These parameters are final and never updated.  The GlyphCanvas object is never re-used to draw another version of the glyph.

## DecKanGlGlyphSerialization ##

Used to create JSON objects which are in turn used for various serialization purposes.