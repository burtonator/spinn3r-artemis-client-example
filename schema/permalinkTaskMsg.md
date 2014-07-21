
Table permalinkTaskMsg
====================

Message to a robot via apollo requesting a permalink to be indexed by our crawler.

# permalink

**type:**: text
**java type**: String

The unique URL to the content.

# source_hashcode

**type:**: ascii
**java type**: String

base64filesafe(sha1(resource)) ... Essentially the base 64 (filesafe) encoding of the sha1 of the tokenized permalink/url

# source_resource

**type:**: text
**java type**: String

The tokenized URL for this source.

# source_link

**type:**: text
**java type**: String

The non-tokenized URL for this source.  Use this URL if you would like to fetch this source via HTTP.

# source_publisher_type

**type:**: enum
**java type**: int

The publisher type (mainstream news, weblog, forum, etc) of this source encoded as an int.

This is an enum type.  The following values are accepted:

*UNKNOWN*
Unknown publisher type.

*WEBLOG*
Weblog.  Defined as a smaller site, usually owned by an individual.

*MAINSTREAM_NEWS*
Mainstream news source.  Generally owned by a corporation with multiple paid writers.

*CLASSIFIED*
Classified site.  Craigslist, Backpage, etc.

*FORUM*
Forum sites like phpBB, phorum, vbulletin, etc

*REVIEW*
Review site.  Like epinions, amazon reviews, etc.

*MEMETRACKER*
Memetracker like reddit, digg, techmeme, google news, etc

*MICROBLOG*
Microblog content such as Twitter, identi.ca, etc.

*SOCIAL_MEDIA*
Social media sites (facebook, instagram, etc).

# source_date_found

**type:**: timestamp
**java type**: Date

The time we added this to our index.

# source_last_updated

**type:**: timestamp
**java type**: Date

The last time our crawler visited the source and processed it with a task.  This is always incremented even if the site isn't updated or even if the site is HTTP 500 or other network/transient errors.

# source_last_published

**type:**: timestamp
**java type**: Date

The last time this source published a new HTML file (as measured by content_sha1)

# source_last_posted

**type:**: timestamp
**java type**: Date

The last time this source posted a new piece of content

# source_update_interval

**type:**: varint
**java type**: int

The number of milliseconds between updates to re-fetch this source.  This is used to for cyclical updates of sources and usually depends on how often the source posts updates.

# source_http_status

**type:**: varint
**java type**: int

The HTTP status code of the last request to this source.

# source_spam_probability

**type:**: float
**java type**: float

The probability, between 0 and 1, that this source is a spam source.  -1.0 if we have not yet classified it.

# source_content_length

**type:**: varint
**java type**: int

The length, in bytes, of this HTML from the last time we fetched the page.

# source_content_checksum

**type:**: text
**java type**: String

The SHA1 checksum of the content.

# source_title

**type:**: text
**java type**: String

The title of the source. 

# source_description

**type:**: text
**java type**: String

A short description of the source.

# source_followers

**type:**: int
**java type**: int

The number of followers this source has according to the website or social network.

# source_profiles

**type:**: set<text>
**java type**: Set<String>

Set of URLs on other social networking sites and weblogs for this user.  These are essentially alternate profiles for the user.  Their twitter site, facebook site, etc.

# source_location

**type:**: text
**java type**: String

The human readable location of the source.  Example: 'Washington, DC'
