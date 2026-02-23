package com.artisthub.service;

import com.artisthub.dto.ReviewDto;
import com.artisthub.entity.Artist;
import com.artisthub.entity.Customer;
import com.artisthub.entity.Review;
import com.artisthub.repository.ArtistRepository;
import com.artisthub.repository.CustomerRepository;
import com.artisthub.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ArtistRepository artistRepository;

    public void submitReview(Long customerId, Long artistId, Integer rating, String comment) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Artist artist = artistRepository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Artist not found"));

        Review review = new Review();
        review.setCustomer(customer);
        review.setArtist(artist);
        review.setRating(rating);
        review.setComment(comment);

        reviewRepository.save(review);
    }

    public List<ReviewDto> getReviewsByArtist(Long artistId) {
        return reviewRepository.findByArtistId(artistId).stream().map(review -> {
            ReviewDto dto = new ReviewDto();
            dto.setId(review.getId());
            dto.setRating(review.getRating());
            dto.setComment(review.getComment());
            dto.setArtistId(review.getArtist().getId());
            dto.setCustomerId(review.getCustomer().getId());
            dto.setCustomerName(review.getCustomer().getName());
            return dto;
        }).collect(Collectors.toList());
    }
}
