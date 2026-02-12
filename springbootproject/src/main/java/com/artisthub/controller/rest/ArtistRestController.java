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
@RequestMapping("/api/artist")
@PreAuthorize("hasRole('ARTIST') or hasRole('ADMIN')")
public class ArtistRestController {

    @Autowired
    ArtistService artistService;

    @Autowired
    BookingService bookingService;

    @GetMapping("/profile/{id}")
    public ResponseEntity<Artist> getProfile(@PathVariable Long id) {
        return ResponseEntity.ok(artistService.getArtistById(id));
    }

    @GetMapping("/bookings/{artistId}")
    public ResponseEntity<List<Booking>> getBookings(@PathVariable Long artistId) {
        return ResponseEntity.ok(bookingService.getBookingsByArtist(artistId));
    }
}
