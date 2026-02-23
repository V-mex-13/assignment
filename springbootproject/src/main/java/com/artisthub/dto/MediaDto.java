package com.artisthub.dto;

import lombok.Data;

@Data
public class MediaDto {
    private Long id;
    private String fileUrl;
    private String type; // IMAGE or VIDEO
    private Long artistId;
}
