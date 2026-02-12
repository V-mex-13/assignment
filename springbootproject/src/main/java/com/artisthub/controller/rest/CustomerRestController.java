package com.artisthub.controller.rest;

import com.artisthub.dto.BookingDto;
import com.artisthub.entity.Artist;
import com.artisthub.entity.Booking;
import com.artisthub.service.ArtistService;
import com.artisthub.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
@PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
public class CustomerRestController {

    @Autowired
    ArtistService artistService;

    @Autowired
    BookingService bookingService;

    @GetMapping("/artists")
    @PreAuthorize("permitAll()") // Allow everyone to see artists 
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok(artistService.getApprovedArtists());
    }

    @PostMapping("/book")
    public ResponseEntity<?> bookArtist(@Valid @RequestBody BookingDto bookingDto) {
        bookingService.createBooking(bookingDto);
        return ResponseEntity.ok("Booking created successfully!");
    }

    @GetMapping("/bookings/{customerId}")
    public ResponseEntity<List<Booking>> getMyBookings(@PathVariable Long customerId) {
        return ResponseEntity.ok(bookingService.getBookingsByCustomer(customerId));
    }
}
