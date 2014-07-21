
Table source
====================

Stores metadata for representing a source in our index.  Weblog, twitter, mainstream news, etc.

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

# publisher_type

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

# date_found

**type:**: timestamp
**java type**: Date

The time we added this to our index.

# last_updated

**type:**: timestamp
**java type**: Date

The last time our crawler visited the source and processed it with a task.  This is always incremented even if the site isn't updated or even if the site is HTTP 500 or other network/transient errors.

# last_published

**type:**: timestamp
**java type**: Date

The last time this source published a new HTML file (as measured by content_sha1)

# last_posted

**type:**: timestamp
**java type**: Date

The last time this source posted a new piece of content

# update_interval

**type:**: varint
**java type**: int

The number of milliseconds between updates to re-fetch this source.  This is used to for cyclical updates of sources and usually depends on how often the source posts updates.

# http_status

**type:**: varint
**java type**: int

The HTTP status code of the last request to this source.

# spam_probability

**type:**: float
**java type**: float

The probability, between 0 and 1, that this source is a spam source.  -1.0 if we have not yet classified it.

# content_length

**type:**: varint
**java type**: int

The length, in bytes, of this HTML from the last time we fetched the page.

# content_checksum

**type:**: text
**java type**: String

The SHA1 checksum of the content.

# robot_link_filter

**type:**: set<blob>
**java type**: BlobSet

Set of encoded hashcodes for URLs that were present on the page during the last fetch.  Used to prevent duplicate indexing.

# setting_trace

**type:**: boolean
**java type**: boolean

When true, tracing is enabled on this source to write log messages to cassandra for debug purposes.

# setting_crawl_template

**type:**: text
**java type**: String

JSON parser definition for our robot parsing rules for extracting content from the raw HTML

# setting_crawl_permalink_pattern

**type:**: text
**java type**: String

Regular expression for matching URLs to crawl and index.

# setting_user_agent_id

**type:**: boolean
**java type**: boolean

Override the default user agent.  These are present in a global repository or user agents which robots cache locally.

# setting_proxy

**type:**: boolean
**java type**: boolean

Override the default proxy setting (which is probably true).

# setting_proxy_host

**type:**: ascii
**java type**: String

Override the default proxy host.

# setting_disabled

**type:**: enum
**java type**: int

When greater than zero, this source is marked as disabled.

This is an enum type.  The following values are accepted:

*ENABLED*
Default state.  The souce is enabled.

*DISABLED*
The source is disabled but without a specific reason.

*SPAM*
The source has been marked as spam.

*DUPLICATE*
Duplicate of another source.

*INVALID*
Invalid source.  Not anything we are interested in indexing.

# setting_update_strategy

**type:**: enum
**java type**: int

The update stratey for computing the update interval.

This is an enum type.  The following values are accepted:

*CYCLICAL*
Default update strategy.  Essentially just update the source at a regular rate.

*ADAPTIVE*
Adapt the update interval based on the posting frequency of the source.  This way we update sources less frequently if they post once per month compared to sources that update once per hour.

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
