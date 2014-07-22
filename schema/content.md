
content
==============

Stores content in our index including the full HTML as well  as the metadata we were able to extract.  Some of these  fields are HTML which are cleaned any unsafe elements which might cause cross scripting attacks or other vulnerabilites are removed.  Additionally, All URLs are fully expanded. Encoding is UTF-8. 

A basic example of data in JSON format.

```json
{
  "bucket" : 0,
  "permalink" : "http://cnn.com",
  "index_method" : "PERMALINK_TASK",
  "html" : "<html><body><p>Full HTML of the content</p></body></html>",
  "html_length" : 57,
  "source_hashcode" : "COH0cFU4G1sMlRHd9gEvS-n3FFI",
  "source_resource" : "http://cnn.com",
  "source_link" : "http://cnn.com",
  "source_publisher_type" : "MAINSTREAM_NEWS",
  "source_date_found" : "2014-06-22T01:08:52Z",
  "source_update_interval" : 900000,
  "main" : "<p>Full HTML of the content</p>",
  "title" : "CNN example post.",
  "section" : "Technology",
  "description" : "This is the description"
}
```

# bucket

**type:**: bigint
**java type**: long

The bucket to write this content (timestamp prefix and suffix valued from 0-99).  This allows us to use the random partitioner and still get decent parallel client read performance.

# sequence

**type:**: bigint
**java type**: long

The time our robot saw the post and wrote it to the database.  This is a sequence timestamp and supports distributed write.  This can be used as an external primary key as it's gauranteed to always be unique.  The value is opaque and not designed to be readable by humans and the format can change at any time.

# hashcode

**type:**: ascii
**java type**: String

base64filesafe(sha1(resource)) ... Essentially the base 64 (filesafe) encoding of the sha1 of the tokenized permalink/url

# resource

**type:**: text
**java type**: String

Tokenized form of the permalink.

# permalink

**type:**: text
**java type**: String

The unique URL to the content.

# permalink_redirect

**type:**: text
**java type**: String

Same as permalink but if the site performs a 301 or 302 redirect this is the URL we were redirected to.

# date_found

**type:**: timestamp
**java type**: Date

The time we fetched and added this content to our index.

# index_method

**type:**: enum
**java type**: int

The method that we used to discovery and index the content.  We have various algorithms to discover content and this lets the algorithm tag the content.

This is an enum type.  The following values are accepted:

**PERMALINK_TASK**
Content indexed by the permalink task.

**SOURCE_TASK**
Content indexed by the source task.

# html

**type:**: text
**java type**: String

The HTML content of this permalink as fetched by our robot.  Note that this is RAW content.  No cleanup is done.  Javascript is present, etc.  If you want to work with this content you must make sure to clean/sanitize it yourself.  See the 'body' field for a clean version of the document.  In some situations it's possible to not have any html.  An example is when we're using an API or firehose where the original full-html isn't present or not would just be wasteful.

# html_length

**type:**: varint
**java type**: int

The length of the HTML

# html_checksum

**type:**: text
**java type**: String

The SHA1 checksum of the HTML.

# lang

**type:**: ascii
**java type**: String

ISO language code for this source.  All our language codes are ISO 639 two letter lang codes. We use the special lang code of U when we are unable to determine the language from the underlying text - usually because we don't have enough data.

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

**UNKNOWN**
Unknown publisher type.

**WEBLOG**
Weblog.  Defined as a smaller site, usually owned by an individual.

**MAINSTREAM_NEWS**
Mainstream news source.  Generally owned by a corporation with multiple paid writers.

**CLASSIFIED**
Classified site.  Craigslist, Backpage, etc.

**FORUM**
Forum sites like phpBB, phorum, vbulletin, etc

**REVIEW**
Review site.  Like epinions, amazon reviews, etc.

**MEMETRACKER**
Memetracker like reddit, digg, techmeme, google news, etc

**MICROBLOG**
Microblog content such as Twitter, identi.ca, etc.

**SOCIAL_MEDIA**
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
