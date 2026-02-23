package com.artisthub.controller.web;

import com.artisthub.security.UserDetailsImpl;
import com.artisthub.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @GetMapping("/feedback")
    public String showFeedbackForm() {
        return "feedback";
    }

    @PostMapping("/feedback/submit")
    public String submitFeedback(@RequestParam String message, Model model) {
        Long userId = getCurrentUserId();
        if (userId == null) {
            return "redirect:/login";
        }
        try {
            feedbackService.submitFeedback(userId, message);
            return "redirect:/?feedbackSuccess=true";
        } catch (Exception e) {
            model.addAttribute("error", "Could not submit feedback: " + e.getMessage());
            return "feedback";
        }
    }

    @GetMapping("/admin/feedback")
    public String adminFeedbackList(Model model) {
        model.addAttribute("feedbacks", feedbackService.getAllFeedback());
        return "admin/feedback";
    }

    private Long getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) principal).getId();
        }
        return null;
    }
}
