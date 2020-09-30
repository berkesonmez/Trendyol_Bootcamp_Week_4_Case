package com.example.demo.repository;

import com.couchbase.client.core.error.DocumentExistsException;
import com.couchbase.client.core.error.DocumentNotFoundException;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.kv.GetResult;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryResult;
import com.example.demo.domain.Playlist;
import com.example.demo.domain.Track;
import com.example.demo.exceptions.PlaylistAlreadyExistsException;
import com.example.demo.exceptions.PlaylistNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlaylistRepository {
    private final Cluster couchbaseCluster;
    private final Collection playlistCollection;

    public PlaylistRepository(Cluster couchbaseCluster, Collection playlistCollection) {
        this.couchbaseCluster = couchbaseCluster;
        this.playlistCollection = playlistCollection;
    }

    public void insert(Playlist playlist) {
        try {
            playlistCollection.insert(playlist.getUuid(), playlist);
        } catch (DocumentExistsException e) {
            throw new PlaylistAlreadyExistsException("Playlist with given id already exists");
        }
    }

    public Playlist findById(String playlistId) {
        try {
            GetResult getResult = playlistCollection.get(playlistId);
            Playlist playlist = getResult.contentAs(Playlist.class);
            return playlist;
        } catch (DocumentNotFoundException e){
            throw new PlaylistNotFoundException("Playlist not found");
        }
    }

    public void delete(String playlistId) {
        playlistCollection.remove(playlistId);
    }

    public void update(Playlist playlist) {
        playlistCollection.replace(playlist.getUuid(), playlist);
    }

    public List<Playlist> findAllByUserId(int userId) {
        String statement = "Select playlist .* from playlist where userId = $1";
        QueryResult query = couchbaseCluster.query(statement, QueryOptions.queryOptions().parameters(JsonArray.from(userId)));
        if(query.equals(null) || query.rowsAsObject().isEmpty()) {
            throw new PlaylistNotFoundException("Playlist not found");
        }
        return query.rowsAs(Playlist.class);
    }

    public void removeTrack(String playlistId, String trackId) {
        Playlist foundPlaylist = findById(playlistId);
        foundPlaylist.removeTrack(trackId);
        update(foundPlaylist);
    }

    public void addTrack(String playlistId, Track track) {
        Playlist foundPlaylist = findById(playlistId);
        foundPlaylist.addToTracks(track);
        update(foundPlaylist);
    }

    public void addTrackWithIndex(String playlistId, int trackIndex, Track track) {
        Playlist foundPlaylist = findById(playlistId);
        foundPlaylist.addToTracksWithIndex(track, trackIndex);
        update(foundPlaylist);
    }
}
