package com.artisthub.dto;

import com.artisthub.entity.Booking;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingDto {
    private Long id;
    private LocalDate bookingDate;
    private LocalDate eventDate;
    private Booking.BookingStatus status;
    private Long artistId;
    private String artistName;
    private Long customerId;
    private String customerName;
}
