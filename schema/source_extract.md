
Table source_extract
====================

Keep extracted metadata about a source including title, description, location, followers, etc.

# title

**type:**: text
**java type**: String

The title of the source. 

# description

**type:**: text
**java type**: String

A short description of the source.

# followers

**type:**: int
**java type**: int

The number of followers this source has according to the website or social network.

# profiles

**type:**: set<text>
**java type**: Set<String>

Set of URLs on other social networking sites and weblogs for this user.  These are essentially alternate profiles for the user.  Their twitter site, facebook site, etc.

# location

**type:**: text
**java type**: String

The human readable location of the source.  Example: 'Washington, DC'
