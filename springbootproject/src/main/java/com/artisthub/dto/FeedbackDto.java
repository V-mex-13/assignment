package com.artisthub.dto;

import lombok.Data;

@Data
public class FeedbackDto {
    private Long id;
    private String message;
    private Long userId;
    private String userName;
}
