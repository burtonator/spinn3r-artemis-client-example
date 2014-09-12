Spinn3r (artemis) client
========================

The Spinn3r artemis client is used to connect to Spinn3r and fetch content from
our servers.  The content is then spooled to disk enabling efficient transfer 
and recovery. 

This project provides both an open source example of how to use the client 
in your application as well to provide the download for the official client.

Support and Questions
========================

As always, if you have any questions on this document, you can send inquiries
to support@spinn3r.com or create a ticket by visiting our website.

It's our goal to get you up and running ASAP and sometimes text documents may
not explain all issues.

Getting started
===============

First download the client here:

https://github.com/burtonator/spinn3r-artemis-client-example/releases

Design and Architecture
=======================

The client works by reading new data via HTTP, encoded as JSON, and writes the
data to a local directory you specify via the command line.

Your app just needs to watch the directory for new files, parse them, and import
them into your database.

Custom clients that connect to Spinn3r are NOT supported and we consider it
an anti-pattern that will eventually break.  Unfortunately, there are far too
many issues with implementing HTTP correctly that will eventually cause custom
clients to fail [1].

Daemons
=======

There are two daemons:

spinn3r-artemis-client-fetcher:
===============================

Fetches content via HTTP and writes it to a local spool directory.
 
spinn3r-artemis-client-watcher:
 
Watches files in the spool directory and then executes code you provide to import
the content into your database.

File format
===========

All files are formatted as JSON with UTF8 encoding.

Requirements
============

- A vendor code provided by Spinn3r.

- Linux (or a platform that can run Java.  Windows will probably work but we
  recommend Linux, OpenSolaris, *BSD)

- Java >= 1.7 (untested on Java 8 but it should work)

- 20-50 megabit connection to the Internet...  Note that this needs to be 2-4x
  faster than the content you are actually acquiring so that you can resume
  quickly.

- A hard drive with at least 100GB of free space.

Usage
=====

You first need to provision a new client in a given directory.  The client will
write a resume.checkpoint file so that you can start/stop the client and it
will automatically resume.

If a client fails, just restart it to fetch new content.

Provision
=========

Run:

```bash
java -cp "/usr/share/spinn3r-artemis-client-fetcher/lib/*" com.spinn3r.artemis.client.fetcher.Provisioner \
     --dir=/var/spool/spinn3r-artemis-client/default \
     --vendor=MY_VENDOR_CODE \
     --after=-1hour \
     --processPolicy=DELETE \
     --fetchListenerClassName=com.spinn3r.artemis.client.watcher.LoggingFetcherListener
```

The fetchListenerClassName is the name of your the class used to parse the JSON 
if you're using our watcher package.  You can just specify the "LoggingFetcherListener"
if you're going to be using a language other than java.

NOTE: Make sure the quotes are included in the Java classpath or the command
won't run due to bash file name expansion.

The 'after' parameter accepts both absolute time if specified in ISO8601 as well
as relative time.

Relative time is in the format:

```
-1hour
-2hours
-1day
+1hour
+1day
```

The - (negative) prefix is used to denote time in the past.

The + (positive) prefix is used to denote time in the future.

If you would like to start from the current moment in time you could specify:

 +0minutes

After if you 'ls' the directory you will see:

```
root@my-host:/usr/share/spinn3r-artemis-client# ls -al /var/spool/spinn3r-artemis-client/default
total 20
drwxr-xr-x 4 root root 4096 Jun 28 18:05 .
drwxr-xr-x 3 root root 4096 Jun 28 18:05 ..
drwxr-xr-x 2 root root 4096 Jun 28 18:05 data
drwxr-xr-x 2 root root 4096 Jun 28 18:05 logs
drwxr-xr-x 2 root root 4096 Jun 28 18:05 processed
-rw-r--r-- 1 root root  185 Jun 28 18:05 resume.checkpoint
drwxr-xr-x 2 root root 4096 Jun 28 18:05 tmp
```

The data directory stores all the json files that the client fetches.

The logs directory contains the logs of the client running in the background.

Run the client
==============

Now just run the client.

```bash
/etc/init.d/spinn3r-artemis-client-fetcher start
```

Archives
========

By default all Spinn3r clients have access to the last 4 hours worth of content.

We optimize our stack to keep hot data in cache and highly available.

It's your responsibility to keep a client up and running and listening to data.

Do not run a client via crontab. Instead.  Keep it running in the background
as a daemon.

If you require access to data older than 4 hours you can purchase an updated SLA
to cover that window.

Designing your parser
====================

Here are some rules to design a parser / data importer that handles changes to 
our API moving forward.

- The format will always be UTF8.

- The JSON output will never change to another format unless it's on another 
  endpoint.

- You MUST handle additional fields. Just ignore them until you have a chance
  to review the documentation and see how they can benefit your application.

- You MUST NOT rely on the output format being pretty printed.  In general it's
  best to use a real JSON parser like Jackson so that any idiosyncrasies in
  parsing are transparently handled.

Importing data
==============

You SHOULD make your data import idempotent so that if you import data twice, you
don't have two records.
  
You will find that it's much easier to process data this way.  You can easily 
recover a client by just replaying JSON from archived content or contact us to 
re-download it.

Potential Issues and Warnings
=============================

* The client *may* write the same content twice to the local spool.  This is
  very rare but if your client crashes it won't have written a recent checkpoint
  and it's going to re-fetch the same content.  Your database should use a primary
  key to prevent this from happening. You can use our 'sequence' value in the
  stream as a primary key as that's gauranteed to always be unique on our end.

Footnotes
=========

1.  HTTP connect and read timeout, gzip encoding, DNS load balancing and
    caching,  resume, HTTP headers and encoding all amount to a very complicated
    implementation which we don't to break for our customers.  Further, this
    allows us to push out clients with new features without asking our customers
    to implement them.