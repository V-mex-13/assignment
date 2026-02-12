package com.artisthub.repository;

import com.artisthub.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findByApprovalStatus(Artist.ApprovalStatus status);
    List<Artist> findByCategoryContainingIgnoreCase(String category);
}
