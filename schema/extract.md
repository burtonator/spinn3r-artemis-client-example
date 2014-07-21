
Table extract
====================

High level extract fields parsed using semantic markup  and other techniques. These fields are populated by  multiple algorithms executing over the same HTML which  attempt to pick the best value from all alternatives.

# shortlink

**type:**: text
**java type**: String

The shortlink URL, if known.  This is the prefered 'short' URL discovered from either the content itself or through metdata.

# canonical

**type:**: text
**java type**: String

The canonical URL to the content (as specified by the publisher) in rel=canonical (and other specs such as og:url).

# main

**type:**: text
**java type**: String

The actual main content of the article.  The authoritative 'main' of the post derived by removing sidebar content. (html).  This content is sanitized, cleaned so that javascript, event handlers, etc are removed.  This is analagous to the HTML5 main element.  IE the main content of the page, with no header, footer, or sidebar content.

# main_length

**type:**: int
**java type**: int

The length of the main field, in bytes.

# main_checksum

**type:**: text
**java type**: String

The checksum of the main field..

# title

**type:**: text
**java type**: String

The title of the post.  

# publisher

**type:**: text
**java type**: String

The publisher name.  (CNN, MSNBC, Techcrunch, etc)

# section

**type:**: text
**java type**: String

Articles may belong to one or more 'sections' in a magazine or newspaper, such as Sports, Lifestyle, etc.

# description

**type:**: text
**java type**: String

A short description of the item (htmll)

# tags

**type:**: set<text>
**java type**: Set<String>

Tags for the item.  

# published

**type:**: timestamp
**java type**: Date

Date of first broadcast/publication.

# modified

**type:**: timestamp
**java type**: Date

The date on which the content was most recently modified.
