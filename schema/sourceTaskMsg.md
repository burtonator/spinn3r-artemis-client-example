
Table sourceTaskMsg
====================

Message to a robot via apollo requesting a source to be processed.  This includes most of the fields from source but only those needed for the task.

# hashcode

**type:**: ascii
**java type**: String

base64filesafe(sha1(resource)) ... Essentially the base 64 (filesafe) encoding of the sha1 of the tokenized permalink/url

# resource

**type:**: text
**java type**: String

The tokenized URL for this source.

# link

**type:**: text
**java type**: String

The non-tokenized URL for this source.  Use this URL if you would like to fetch this source via HTTP.
