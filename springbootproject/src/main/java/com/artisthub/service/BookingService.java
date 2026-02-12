package com.artisthub.service;

import com.artisthub.dto.BookingDto;
import com.artisthub.entity.Artist;
import com.artisthub.entity.Booking;
import com.artisthub.entity.Customer;
import com.artisthub.repository.ArtistRepository;
import com.artisthub.repository.BookingRepository;
import com.artisthub.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public void createBooking(BookingDto bookingDto) {
        Artist artist = artistRepository.findById(bookingDto.getArtistId())
                .orElseThrow(() -> new RuntimeException("Artist not found"));
        Customer customer = customerRepository.findById(bookingDto.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Booking booking = new Booking();
        booking.setArtist(artist);
        booking.setCustomer(customer);
        booking.setBookingDate(LocalDate.now());
        booking.setEventDate(bookingDto.getEventDate());
        booking.setStatus(Booking.BookingStatus.BOOKED);

        bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByArtist(Long artistId) {
        return bookingRepository.findByArtistId(artistId);
    }
    
    public List<Booking> getBookingsByCustomer(Long customerId) {
        return bookingRepository.findByCustomerId(customerId);
    }
    
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
}
