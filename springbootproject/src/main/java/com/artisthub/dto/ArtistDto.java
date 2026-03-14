package com.artisthub.dto;

import com.artisthub.entity.Artist;
import lombok.Data;

@Data
public class ArtistDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String category;
    private Integer experienceYears;
    private Double pricePerEvent;
    private String description;
    private Artist.ApprovalStatus approvalStatus;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Integer getExperienceYears() { return experienceYears; }
    public void setExperienceYears(Integer experienceYears) { this.experienceYears = experienceYears; }
    public Double getPricePerEvent() { return pricePerEvent; }
    public void setPricePerEvent(Double pricePerEvent) { this.pricePerEvent = pricePerEvent; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Artist.ApprovalStatus getApprovalStatus() { return approvalStatus; }
    public void setApprovalStatus(Artist.ApprovalStatus approvalStatus) { this.approvalStatus = approvalStatus; }
}
