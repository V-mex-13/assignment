package com.artisthub.controller.web;

import com.artisthub.security.UserDetailsImpl;
import com.artisthub.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping("/media/upload")
    public String uploadMedia(@RequestParam String fileUrl,
                              @RequestParam String typeStr,
                              RedirectAttributes redirectAttributes) {
        Long artistId = getCurrentUserId();
        if (artistId == null) {
            return "redirect:/login";
        }
        
        try {
            mediaService.uploadMedia(artistId, fileUrl, typeStr);
            redirectAttributes.addFlashAttribute("successMessage", "Media uploaded successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error uploading media: " + e.getMessage());
        }
        return "redirect:/artist/dashboard";
    }

    private Long getCurrentUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            return ((UserDetailsImpl) principal).getId();
        }
        return null;
    }
}
