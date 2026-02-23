package com.artisthub.controller.web;

import com.artisthub.security.UserDetailsImpl;
import com.artisthub.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/review/submit")
    public String submitReview(@RequestParam Long artistId,
                               @RequestParam Integer rating,
                               @RequestParam String comment,
                               RedirectAttributes redirectAttributes) {
        Long customerId = getCurrentUserId();
        if (customerId == null) {
            return "redirect:/login";
        }
        
        try {
            reviewService.submitReview(customerId, artistId, rating, comment);
            redirectAttributes.addFlashAttribute("successMessage", "Review submitted successfully.");
            // Assuming customer dashboard is where they leave reviews from for now
            return "redirect:/customer/dashboard";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error submitting review: " + e.getMessage());
            return "redirect:/customer/dashboard";
        }
    }

    private Long getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) principal).getId();
        }
        return null;
    }
}
