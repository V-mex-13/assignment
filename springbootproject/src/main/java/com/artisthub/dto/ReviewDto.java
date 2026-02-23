package com.artisthub.dto;

import lombok.Data;

@Data
public class ReviewDto {
    private Long id;
    private Integer rating;
    private String comment;
    private Long artistId;
    private Long customerId;
    private String customerName;
}
