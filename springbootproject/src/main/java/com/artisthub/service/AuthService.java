package com.artisthub.service;

import com.artisthub.dto.AuthDto;
import com.artisthub.entity.Artist;
import com.artisthub.entity.Customer;
import com.artisthub.entity.User;
import com.artisthub.repository.ArtistRepository;
import com.artisthub.repository.CustomerRepository;
import com.artisthub.repository.UserRepository;
import com.artisthub.security.JwtUtils;
import com.artisthub.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    public AuthDto.JwtResponse authenticateUser(AuthDto.LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(item -> item.getAuthority())
                .orElse("ROLE_CUSTOMER");

        return new AuthDto.JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getEmail(),
                role);
    }

    @Transactional
    public void registerUser(AuthDto.RegisterRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RuntimeException("Error: Email is already in use!");
        }

        // Create new user's account based on role
        if ("artist".equalsIgnoreCase(signUpRequest.getRole())) {
            Artist artist = new Artist();
            setCommonDetails(artist, signUpRequest);
            artist.setCategory(signUpRequest.getCategory());
            artist.setExperienceYears(signUpRequest.getExperienceYears());
            artist.setPricePerEvent(signUpRequest.getPricePerEvent());
            artist.setDescription(signUpRequest.getDescription());
            artist.setProfilePicUrl(signUpRequest.getProfilePicUrl());
            artist.setRole(User.Role.ARTIST);
            artistRepository.save(artist);
        } else if ("customer".equalsIgnoreCase(signUpRequest.getRole())) {
            Customer customer = new Customer();
            setCommonDetails(customer, signUpRequest);
            customer.setAddress(signUpRequest.getAddress());
            customer.setRole(User.Role.CUSTOMER);
            customerRepository.save(customer);
        } else {
            // Default or Admin (Admin creation usually restricted, but for simplicity allowing here with caution or just defaulting to Customer if unknown)
            // For now, let's allow Admin if requested, or just Customer
            // TODO: Restrict Admin registration
             Customer customer = new Customer();
             setCommonDetails(customer, signUpRequest);
             if ("admin".equalsIgnoreCase(signUpRequest.getRole())) {
                 customer.setRole(User.Role.ADMIN); // Simplification: Storing admin in Customer table or generic User?
                 // Note: Inherited strategies might require specific tables. 
                 // If Admin doesn't have extra fields, User is enough, but we established Joined. 
                 // Let's assume Admin is just a User base class instance or strictly handled.
                 // Ideally, separate Admin entity or just User. 
                 // Let's stick to User for Admin. 
                 User admin = new User();
                 admin.setName(signUpRequest.getName());
                 admin.setEmail(signUpRequest.getEmail());
                 admin.setPhone(signUpRequest.getPhone());
                 admin.setPassword(encoder.encode(signUpRequest.getPassword()));
                 admin.setRole(User.Role.ADMIN);
                 userRepository.save(admin);
                 return;
             }
             customer.setRole(User.Role.CUSTOMER);
             customerRepository.save(customer);
        }
    }

    private void setCommonDetails(User user, AuthDto.RegisterRequest request) {
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(encoder.encode(request.getPassword()));
    }
}
