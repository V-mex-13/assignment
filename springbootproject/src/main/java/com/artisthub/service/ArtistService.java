package com.artisthub.service;

import com.artisthub.entity.Artist;
import com.artisthub.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;

    public List<Artist> getAllArtists() {
        return artistRepository.findAll();
    }

    public List<Artist> getApprovedArtists() {
        return artistRepository.findByApprovalStatus(Artist.ApprovalStatus.APPROVED);
    }
    
    public Artist getArtistById(Long id) {
        return artistRepository.findById(id).orElseThrow(() -> new RuntimeException("Artist not found"));
    }

    public void approveArtist(Long id) {
        Artist artist = getArtistById(id);
        artist.setApprovalStatus(Artist.ApprovalStatus.APPROVED);
        artistRepository.save(artist);
    }

    public void rejectArtist(Long id) {
        Artist artist = getArtistById(id);
        artist.setApprovalStatus(Artist.ApprovalStatus.REJECTED);
        artistRepository.save(artist);
    }
}
