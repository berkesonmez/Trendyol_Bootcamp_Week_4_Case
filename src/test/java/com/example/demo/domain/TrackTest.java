package com.example.demo.domain;

import com.example.demo.exceptions.ArgumentEmptyException;
import com.example.demo.exceptions.ArgumentNullException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TrackTest {
    @Test
    void should_create_track() {
        Track sut = new Track();
        sut.setName("New Track");
        assertEquals("New Track", sut.getName());
    }
    @Test
    void should_throw_exception_when_track_name_is_null() {
        Track sut = new Track();
        Exception exception = assertThrows(ArgumentNullException.class, () -> {
            sut.setName(null);
        });
        String expectedMessage = "Track name cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void should_throw_exception_when_track_name_is_empty() {
        Track sut = new Track();
        Exception exception = assertThrows(ArgumentEmptyException.class, () -> {
            sut.setName("");
        });
        String expectedMessage = "Track name cannot be empty";
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void should_throw_exception_when_track_id_is_null() {
        Track sut = new Track();
        Exception exception = assertThrows(ArgumentNullException.class, () -> {
            sut.setId(null);
        });
        String expectedMessage = "Track id cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void should_throw_exception_when_track_id_is_empty() {
        Track sut = new Track();
        Exception exception = assertThrows(ArgumentEmptyException.class, () -> {
            sut.setId("");
        });
        String expectedMessage = "Track id cannot be empty";
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void should_throw_exception_when_track_length_is_null() {
        Track sut = new Track();
        Exception exception = assertThrows(ArgumentNullException.class, () -> {
            sut.setLength(null);
        });
        String expectedMessage = "Track length cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void should_throw_exception_when_track_length_is_empty() {
        Track sut = new Track();
        Exception exception = assertThrows(ArgumentEmptyException.class, () -> {
            sut.setLength("");
        });
        String expectedMessage = "Track length cannot be empty";
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void should_throw_exception_when_track_artist_is_null() {
        Track sut = new Track();
        Exception exception = assertThrows(ArgumentNullException.class, () -> {
            sut.setArtist(null);
        });
        String expectedMessage = "Artist cannot be null";
        assertEquals(expectedMessage, exception.getMessage());
    }
    @Test
    void should_throw_exception_when_track_artist_is_empty() {
        Track sut = new Track();
        Exception exception = assertThrows(ArgumentEmptyException.class, () -> {
            sut.setArtist("");
        });
        String expectedMessage = "Artist cannot be empty";
        assertEquals(expectedMessage, exception.getMessage());
    }
}
