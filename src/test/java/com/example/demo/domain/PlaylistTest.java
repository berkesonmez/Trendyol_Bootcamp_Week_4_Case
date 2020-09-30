package com.example.demo.domain;

import com.example.demo.exceptions.ArgumentEmptyException;
import com.example.demo.exceptions.ArgumentNullException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlaylistTest {

    @Test
    void should_create_playlist() {
        Playlist sut = new Playlist();
        sut.setName("New Playlist");
        assertEquals("New Playlist", sut.getName());
    }

    @Test
    void should_throw_when_creating_playlist_with_null_playlist_name() {
        Playlist sut = new Playlist();
        Exception exception = assertThrows(ArgumentNullException.class, () -> {
            sut.setName(null);
        });
        String expectedMessage = "Playlist name cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }
    @Test
    void should_throw_when_creating_playlist_with_empty_playlist_name() {
        Playlist sut = new Playlist();
        Exception exception = assertThrows(ArgumentEmptyException.class, () -> {
            sut.setName("");
        });
        String expectedMessage = "Playlist name cannot be empty";
        assertEquals(expectedMessage,exception.getMessage());
    }

    @Test
    void should_throw_when_creating_playlist_with_null_playlist_description() {
        Playlist sut = new Playlist();
        Exception exception = assertThrows(ArgumentNullException.class, () -> {
            sut.setDescription(null);
        });
        String expectedMessage = "Playlist description cannot be null";
        assertEquals(expectedMessage,exception.getMessage());
    }
    @Test
    void should_throw_when_creating_playlist_with_empty_playlist_description() {
        Playlist sut = new Playlist();
        Exception exception = assertThrows(ArgumentEmptyException.class, () -> {
            sut.setDescription("");
        });
        String expectedMessage = "Playlist description cannot be empty";
        assertEquals(expectedMessage,exception.getMessage());
    }


    @Test
    void should_add_track() {
        Playlist sut = new Playlist();
        Track newTrack = new Track();

        newTrack.setArtist("Tarkan");
        newTrack.setName("Kuzu Kuzu");
        newTrack.setLength("5:20");

        sut.addToTracks(newTrack);
        List tracks = sut.getTracks();
        assertEquals(newTrack, tracks.get(0));
    }

    @Test
    void should_add_track_with_index() {
        Playlist sut = new Playlist();
        Track trackOne = new Track();
        Track trackTwo = new Track();

        trackOne.setArtist("Tarkan");
        trackOne.setName("Kuzu Kuzu");
        trackOne.setLength("5:20");

        trackTwo.setArtist("Tarkan");
        trackTwo.setName("Kuzu Kuzu Dilleri");
        trackTwo.setLength("2:20");

        sut.addToTracks(trackOne);
        sut.addToTracksWithIndex(trackTwo, 0);

        List<Track> tracks = Arrays.asList(trackTwo, trackOne);

        assertEquals(tracks, sut.getTracks());
    }
    @Test
    void should_return_track_with_id() {
        Playlist sut = new Playlist();
        Track trackOne = new Track();
        Track trackTwo = new Track();

        trackOne.setArtist("Tarkan");
        trackOne.setName("Kuzu Kuzu");
        trackOne.setLength("5:20");

        trackTwo.setArtist("Tarkan");
        trackTwo.setName("Kuzu Kuzu Dilleri");
        trackTwo.setLength("2:20");

        sut.addToTracks(trackOne);
        sut.addToTracksWithIndex(trackTwo, 0);

        List<Track> tracks = Arrays.asList(trackTwo, trackOne);

        assertEquals(tracks, sut.getTracks());
    }

    @Test
    void should_return_tracks() {
        Playlist sut = new Playlist();
        Track trackOne = new Track();
        Track trackTwo = new Track();

        trackOne.setArtist("Tarkan");
        trackOne.setName("Kuzu Kuzu");
        trackOne.setLength("5:20");

        trackTwo.setArtist("Tarkan");
        trackTwo.setName("Kuzu Kuzu Dilleri");
        trackTwo.setLength("2:20");

        sut.addToTracks(trackOne);
        sut.addToTracks(trackTwo);

        List<Track> tracks = Arrays.asList(trackOne, trackTwo);

        assertEquals(tracks, sut.getTracks());
    }

    @Test
    void should_delete_track() {
        Playlist sut = new Playlist();
        Track trackOne = new Track();
        Track trackTwo = new Track();

        trackOne.setArtist("Tarkan");
        trackOne.setName("Kuzu Kuzu");
        trackOne.setLength("5:20");

        trackTwo.setArtist("Tarkan");
        trackTwo.setName("Dudu");
        trackTwo.setLength("5:20");

        sut.addToTracks(trackOne);
        sut.addToTracks(trackTwo);
        sut.removeTrack(trackOne.getId());

        assertEquals(1, sut.getTracks().size());

    }


}
