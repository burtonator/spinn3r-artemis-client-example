package com.spinn3r.artemis.client.example;

import com.spinn3r.artemis.client.json.Content;
import com.spinn3r.artemis.client.watcher.FetcherListener;
import com.spinn3r.artemis.client.watcher.Parse;

/**
 *
 */

public class MyFetcherListener implements FetcherListener {

    @Override
    public void onContent(Parse parse) {

        for (Content content : parse.getContent()) {

            // handle your content here...  Import it into Hadoop, S3, Cassandra,
            // etc.

            System.out.printf( "Found %s\n", content.getPermalink() );

        }

    }

}
