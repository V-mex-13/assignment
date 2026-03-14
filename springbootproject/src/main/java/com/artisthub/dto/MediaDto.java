package com.artisthub.dto;

import lombok.Data;

@Data
public class MediaDto {
    private Long id;
    private String fileUrl;
    private String type; // IMAGE or VIDEO
    private Long artistId;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getFileUrl() { return fileUrl; }
    public void setFileUrl(String fileUrl) { this.fileUrl = fileUrl; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Long getArtistId() { return artistId; }
    public void setArtistId(Long artistId) { this.artistId = artistId; }
}
