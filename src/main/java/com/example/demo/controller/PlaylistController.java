package com.example.demo.controller;

import com.example.demo.domain.Playlist;
import com.example.demo.domain.Track;
import com.example.demo.services.PlaylistService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/playlists")
public class PlaylistController {

    private final PlaylistService playlistService;

    public PlaylistController(PlaylistService playlistService) {
        this.playlistService = playlistService;
    }

    @ApiOperation(value = "Creates Playlist with given fields")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created Playlist"),
            @ApiResponse(code = 409, message = "Playlist with given id already exists")
    })
    @PostMapping
    public ResponseEntity<Void> createPlaylist(@RequestBody Playlist playlist) {
        playlistService.create(playlist);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(playlist.getUuid())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @ApiOperation(value = "Adds track to end of playlist")
    @PatchMapping
    public ResponseEntity<Void> addTrackToPlaylist(@RequestBody Track track, @RequestParam String playlistId) {
        playlistService.addTrackToPlaylist(playlistId, track);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Adds track to playlist with given index")
    @PatchMapping("{playlistId}/{trackIndex}")
    public ResponseEntity<Void> addTrackToPlaylistWithIndex(@RequestParam String playlistId, @RequestParam int trackIndex, @RequestBody Track track) {
        playlistService.addTrackToPlaylistWithIndex(playlistId, trackIndex, track);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "Returns playlist with given playlist id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Playlist not found")
    })
    @GetMapping
    public ResponseEntity<Playlist> findByPlaylistId(@RequestParam String playlistId) {
        return ResponseEntity.ok(playlistService.findByPlaylistId(playlistId));
    }

    @ApiOperation(value = "Returns users all playlists with given id")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Playlist not found")
    })
    @GetMapping("{userId}/all")
    public ResponseEntity<List<Playlist>> findAllByUserId(@RequestParam int userId) {
        return ResponseEntity.ok(playlistService.findAllByUserId(userId));
    }

    @ApiOperation(value = "Deletes playlist with given playlist Id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 404, message = "Playlist not found")
    })
    @DeleteMapping
    public ResponseEntity<Void> deletePlaylist(@RequestParam String playlistId) {
        playlistService.delete(playlistId);
        return ResponseEntity.noContent().build();
    }

    @ApiOperation(value = "Removes track from playlist with given id")
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "No content"),
            @ApiResponse(code = 400, message = "Bad request")
    })
    @DeleteMapping("{playlistId}/removeTrack/{trackId}")
    public ResponseEntity<Void> removeTrackFromPlaylist(@RequestParam String playlistId, @RequestParam String trackId) {
        playlistService.removeTrack(playlistId, trackId);
        return ResponseEntity.noContent().build();
    }

}
