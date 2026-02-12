package com.artisthub.controller.rest;

import com.artisthub.entity.Artist;
import com.artisthub.entity.Booking;
import com.artisthub.service.ArtistService;
import com.artisthub.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminRestController {

    @Autowired
    ArtistService artistService;

    @Autowired
    BookingService bookingService;

    @GetMapping("/artists")
    public ResponseEntity<List<Artist>> getAllArtists() {
        return ResponseEntity.ok(artistService.getAllArtists());
    }

    @PutMapping("/approve-artist/{id}")
    public ResponseEntity<?> approveArtist(@PathVariable Long id) {
        artistService.approveArtist(id);
        return ResponseEntity.ok("Artist approved!");
    }

    @PutMapping("/reject-artist/{id}")
    public ResponseEntity<?> rejectArtist(@PathVariable Long id) {
        artistService.rejectArtist(id);
        return ResponseEntity.ok("Artist rejected!");
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<Booking>> getAllBookings() {
        return ResponseEntity.ok(bookingService.getAllBookings());
    }
}
