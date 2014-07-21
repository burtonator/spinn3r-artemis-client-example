
Table content_idx_resource
====================

An index of content by the resource URL

# bucket

**type:**: bigint
**java type**: long

The bucket to write this content (timestamp prefix and suffix valued from 0-99).  This allows us to use the random partitioner and still get decent parallel client read performance.

# sequence

**type:**: bigint
**java type**: long

The time our robot saw the post and wrote it to the database.  This is a sequence timestamp and supports distributed write.  This can be used as an external primary key as it's gauranteed to always be unique.  The value is opaque and not designed to be readable by humans and the format can change at any time.

# resource

**type:**: text
**java type**: String

Tokenized form of the permalink.
