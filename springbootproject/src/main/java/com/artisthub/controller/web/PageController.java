package com.artisthub.controller.web;

import com.artisthub.entity.Artist;
import com.artisthub.entity.Customer;
import com.artisthub.security.UserDetailsImpl;
import com.artisthub.service.ArtistService;
import com.artisthub.service.BookingService;
import com.artisthub.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.artisthub.service.ReviewService;
import com.artisthub.service.MediaService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Controller
public class PageController {

    @Autowired
    ArtistService artistService;

    @Autowired
    BookingService bookingService;
    
    @Autowired
    CustomerService customerService;

    @Autowired
    com.artisthub.service.AuthService authService;

    @Autowired
    ReviewService reviewService;

    @Autowired
    MediaService mediaService;

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
    
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        model.addAttribute("artists", artistService.getAllArtists());
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "admin/dashboard";
    }

    @GetMapping("/artist/dashboard")
    public String artistDashboard(Model model) {
        Long userId = getCurrentUserId();
        // Since Artist IS A User, usually same ID if Joined.
        // But need to ensure we fetch Artist entity.
        // Assuming userId matches artistId due to InheritanceType.JOINED constraints (PKs shared).
        try {
            Artist artist = artistService.getArtistById(userId);
            model.addAttribute("artist", artist);
            model.addAttribute("bookings", bookingService.getBookingsByArtist(userId));
            model.addAttribute("reviews", reviewService.getReviewsByArtist(userId));
            model.addAttribute("mediaList", mediaService.getMediaByArtist(userId));
        } catch (Exception e) {
            // Handle case where user is not an artist logically (shouldn't happen due to Role check)
        }
        return "artist/dashboard";
    }

    @GetMapping("/customer/dashboard")
    public String customerDashboard(Model model) {
        Long userId = getCurrentUserId();
        try {
             Customer customer = customerService.getCustomerById(userId);
             model.addAttribute("customer", customer);
             model.addAttribute("bookings", bookingService.getBookingsByCustomer(userId));
        } catch (Exception e) {
            // Handle error
        }
        return "customer/dashboard";
    }
    
    @GetMapping("/artist-list")
    public String artistList(Model model) {
        model.addAttribute("artists", artistService.getApprovedArtists());
        return "artist-list";
    }
    
    @PostMapping("/admin/approve-artist")
    public String approveArtist(@RequestParam Long id) {
        artistService.approveArtist(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/admin/reject-artist")
    public String rejectArtist(@RequestParam Long id) {
        artistService.rejectArtist(id);
        return "redirect:/admin/dashboard";
    }
    
    @PostMapping("/register-action")
    public String registerUser(
            @ModelAttribute com.artisthub.dto.AuthDto.RegisterRequest request,
            @RequestParam(required = false, name = "profileImageFile") MultipartFile profileImageFile,
            Model model) {
        
        try {
            // Handle Profile Image Upload
            if (profileImageFile != null && !profileImageFile.isEmpty()) {
                String originalFileName = profileImageFile.getOriginalFilename();
                if (originalFileName != null && (originalFileName.toLowerCase().endsWith(".jpg") || originalFileName.toLowerCase().endsWith(".jpeg") || originalFileName.toLowerCase().endsWith(".png"))) {
                    Path uploadDir = Paths.get("uploads");
                    if (!Files.exists(uploadDir)) {
                        Files.createDirectories(uploadDir);
                    }
                    String fileName = UUID.randomUUID().toString() + "_" + originalFileName;
                    Path filePath = uploadDir.resolve(fileName);
                    Files.copy(profileImageFile.getInputStream(), filePath);
                    request.setProfileImage(fileName);
                } else {
                    model.addAttribute("error", "Only JPG and PNG files are allowed for profile picture");
                    return "register";
                }
            } else {
                request.setProfileImage("default-avatar.png");
            }

            System.out.println("====== REGISTRATION DEBUG ======");
            System.out.println("Role: " + request.getRole());
            System.out.println("Email: " + request.getEmail());
            System.out.println("Name: " + request.getName());
            System.out.println("=================================");

            authService.registerUser(request);
            
            // Redirect based on current user role
            org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null && authentication.getAuthorities().stream()
                    .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
                return "redirect:/admin/dashboard?success=User Registered Successfully";
            }
            
            return "redirect:/login?success";
            
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error", e.getMessage() != null ? e.getMessage() : "An error occurred during registration. Please try again.");
            return "register";
        }
    }

    @PostMapping("/book-artist")
    public String bookArtist(@RequestParam Long artistId, @RequestParam("eventDate") @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE) java.time.LocalDate eventDate, Model model) {
        Long customerId = getCurrentUserId();
        if (customerId == null) {
            return "redirect:/login";
        }
        
        try {
            com.artisthub.dto.BookingDto bookingDto = new com.artisthub.dto.BookingDto();
            bookingDto.setArtistId(artistId);
            bookingDto.setCustomerId(customerId);
            bookingDto.setEventDate(eventDate);
            
            bookingService.createBooking(bookingDto);
            
            return "redirect:/customer/dashboard?booked=true";
        } catch (Exception e) {
            return "redirect:/artist-list?error=" + e.getMessage();
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
