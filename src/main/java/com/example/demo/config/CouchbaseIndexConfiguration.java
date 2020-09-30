package com.example.demo.config;

import com.couchbase.client.core.error.IndexExistsException;
import com.couchbase.client.java.Cluster;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CouchbaseIndexConfiguration {

    private final Cluster couchbaseCluster;

    public CouchbaseIndexConfiguration(Cluster couchbaseCluster) {
        this.couchbaseCluster = couchbaseCluster;
    }

    @Bean
    public void createIndexes() {
        try {
            couchbaseCluster.query("CREATE INDEX playlistArrayTracks ON `playlist`(tracks)");
        } catch (IndexExistsException e) {
            System.out.println("Index already exists");
        }
    }
}
