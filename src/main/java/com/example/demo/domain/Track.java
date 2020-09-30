package com.example.demo.domain;

import com.example.demo.exceptions.ArgumentEmptyException;
import com.example.demo.exceptions.ArgumentNullException;
import lombok.ToString;

import java.util.UUID;

@ToString
public class Track {

    private String id;
    private String name;
    private String length;
    private String artist;

    public Track() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() { return id; }

    public void setId(String id) {
        if(id == null) {
            throw new ArgumentNullException("Track id cannot be null");
        }
        if(id.isEmpty()) {
            throw new ArgumentEmptyException("Track id cannot be empty");
        }
        this.id = id;
    }

    public String getName() { return name; }

    public void setName(String name) {
        if(name == null) {
            throw new ArgumentNullException("Track name cannot be null");
        }
        if(name.isEmpty()) {
            throw new ArgumentEmptyException("Track name cannot be empty");
        }
        this.name = name;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        if(length == null) {
            throw new ArgumentNullException("Track length cannot be null");
        }
        if(length.isEmpty()) {
            throw new ArgumentEmptyException("Track length cannot be empty");
        }
        this.length = length;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        if(artist == null) {
            throw new ArgumentNullException("Artist cannot be null");
        }
        if(artist.isEmpty()) {
            throw new ArgumentEmptyException("Artist cannot be empty");
        }
        this.artist = artist;
    }


}
