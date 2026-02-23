package com.artisthub.service;

import com.artisthub.dto.MediaDto;
import com.artisthub.entity.Artist;
import com.artisthub.entity.Media;
import com.artisthub.repository.ArtistRepository;
import com.artisthub.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaService {

    @Autowired
    private MediaRepository mediaRepository;

    @Autowired
    private ArtistRepository artistRepository;

    public void uploadMedia(Long artistId, String fileUrl, String typeStr) {
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        Media.MediaType type;
        try {
            type = Media.MediaType.valueOf(typeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid media type");
        }

        Media media = new Media();
        media.setArtist(artist);
        media.setFileUrl(fileUrl);
        media.setType(type);

        mediaRepository.save(media);
    }

    public List<MediaDto> getMediaByArtist(Long artistId) {
        return mediaRepository.findByArtistId(artistId).stream().map(media -> {
            MediaDto dto = new MediaDto();
            dto.setId(media.getId());
            dto.setFileUrl(media.getFileUrl());
            dto.setType(media.getType().name());
            dto.setArtistId(media.getArtist().getId());
            return dto;
        }).collect(Collectors.toList());
    }
}
