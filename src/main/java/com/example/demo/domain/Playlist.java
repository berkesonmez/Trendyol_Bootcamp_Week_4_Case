package com.example.demo.domain;


import com.example.demo.exceptions.ArgumentEmptyException;
import com.example.demo.exceptions.ArgumentNullException;
import com.example.demo.exceptions.PlaylistEmptyTracksException;
import com.example.demo.exceptions.TrackNotFoundException;
import io.swagger.annotations.ApiModelProperty;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@NonNull
public class Playlist {
    @ApiModelProperty(readOnly = true)
    private String uuid;
    private String name;
    private String description;
    private int followersCount;
    private List<Track> tracks;
    private int trackCount;
    private int userId;

    public String getUuid() {
        return uuid;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null) {
            throw new ArgumentNullException("Playlist name cannot be null");
        }
        if(name.isEmpty()) {
            throw new ArgumentEmptyException("Playlist name cannot be empty");
        }
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if(description == null) {
            throw new ArgumentNullException("Playlist description cannot be null");
        }
        if(description.isEmpty()) {
            throw new ArgumentEmptyException("Playlist description cannot be empty");
        }
        this.description = description;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        if(tracks == null) {
            throw new ArgumentNullException("Tracks cannot be null");
        }
        if(tracks.isEmpty()) {
            throw new ArgumentEmptyException("Tracks cannot be empty");
        }
        this.setTrackCount(tracks.size());
        this.tracks = tracks;
    }

    public void removeTrack(String trackId) {
        if(tracks.size() <= 1) {
            throw new PlaylistEmptyTracksException("Playlist tracks cannot be empty");
        }
        Boolean foundTrack = false;

        Iterator<Track> iterator = tracks.iterator();
        while(iterator.hasNext()) {
            Track track = iterator.next();
            if(track.getId().equals(trackId) && tracks.size() > 1) {
                foundTrack = true;
                iterator.remove();
            }

        }
        if(foundTrack == false) {
            throw new TrackNotFoundException("Track not found with given id");
        }
        this.trackCount -= 1;
    }

    public void addToTracks(Track track) {
        if(track == null) {
            throw new ArgumentNullException("Track cannot be null");
        }
        this.tracks.add(track);
        this.trackCount += 1;
    }

    public void addToTracksWithIndex(Track track, int trackIndex) {
        if(trackIndex > tracks.size()) {
            throw new TrackNotFoundException("Track index out of range");
        }
        this.tracks.add(trackIndex, track);
        this.trackCount += 1;
    }

    public int getTrackCount() {
        return trackCount;
    }

    public void setTrackCount(int trackCount) {
        this.trackCount = trackCount;
    }

    public Playlist() {
        this.uuid = UUID.randomUUID().toString();
        this.tracks = new ArrayList<>();
        this.trackCount = tracks.size();
    }


}
