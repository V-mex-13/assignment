package com.artisthub.dto;

import com.artisthub.entity.Artist;
import lombok.Data;

@Data
public class ArtistDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String category;
    private Integer experienceYears;
    private Double pricePerEvent;
    private String description;
    private Artist.ApprovalStatus approvalStatus;
}
