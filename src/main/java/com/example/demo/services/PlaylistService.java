package com.example.demo.services;

import com.example.demo.domain.Playlist;
import com.example.demo.domain.Track;
import com.example.demo.repository.PlaylistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistService(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist findByPlaylistId(String userId) {
        return playlistRepository.findById(userId);
    }

    public void create(Playlist playlist) {
        playlistRepository.insert(playlist);
    }


    public void delete(String playlistId) {
        String foundPlaylistsId = playlistRepository.findById(playlistId).getUuid();
        playlistRepository.delete(foundPlaylistsId);
    }

    public void addTrackToPlaylist(String playlistId, Track track) {
        playlistRepository.addTrack(playlistId, track);
    }

    public void removeTrack(String playlistId, String trackId) {
        playlistRepository.removeTrack(playlistId, trackId);

    }

    public List<Playlist> findAllByUserId(int userId) {
        return playlistRepository.findAllByUserId(userId);
    }

    public void addTrackToPlaylistWithIndex(String playlistId, int trackIndex, Track track) {
        playlistRepository.addTrackWithIndex(playlistId, trackIndex, track);
    }
}
