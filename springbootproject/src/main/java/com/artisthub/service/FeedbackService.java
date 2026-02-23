package com.artisthub.service;

import com.artisthub.dto.FeedbackDto;
import com.artisthub.entity.Feedback;
import com.artisthub.entity.User;
import com.artisthub.repository.FeedbackRepository;
import com.artisthub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserRepository userRepository;

    public void submitFeedback(Long userId, String message) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setMessage(message);
        
        feedbackRepository.save(feedback);
    }

    public List<FeedbackDto> getAllFeedback() {
        return feedbackRepository.findAll().stream().map(feedback -> {
            FeedbackDto dto = new FeedbackDto();
            dto.setId(feedback.getId());
            dto.setMessage(feedback.getMessage());
            dto.setUserId(feedback.getUser().getId());
            dto.setUserName(feedback.getUser().getName());
            return dto;
        }).collect(Collectors.toList());
    }
}
