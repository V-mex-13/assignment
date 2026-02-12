package com.artisthub.controller.web;

import com.artisthub.entity.Artist;
import com.artisthub.entity.Customer;
import com.artisthub.entity.User;
import com.artisthub.security.UserDetailsImpl;
import com.artisthub.service.ArtistService;
import com.artisthub.service.BookingService;
import com.artisthub.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String phone,
            @RequestParam String role,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Integer experienceYears,
            @RequestParam(required = false) Double pricePerEvent,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String address,
            Model model) {
        
        try {
            com.artisthub.dto.AuthDto.RegisterRequest request = new com.artisthub.dto.AuthDto.RegisterRequest();
            request.setName(name);
            request.setEmail(email);
            request.setPassword(password);
            request.setPhone(phone);
            request.setRole(role);
            
            if ("artist".equals(role)) {
                request.setCategory(category);
                request.setExperienceYears(experienceYears);
                request.setPricePerEvent(pricePerEvent);
                request.setDescription(description);
            } else {
                request.setAddress(address);
            }
            
            authService.registerUser(request);
            return "redirect:/login?success";
            
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
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
