package com.affordmed.urlshortener.controller;

import com.affordmed.urlshortener.dto.ShortenRequest;
import com.affordmed.urlshortener.model.ShortUrl;
import com.affordmed.urlshortener.service.UrlShortenerService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;
   /* @RequestMapping("/api")
    public String test() {
    	return"program tested";
    }*/

    @PostMapping("/shorturls")
    public ResponseEntity<?> createShortUrl(@RequestBody ShortenRequest request, HttpServletRequest servletRequest) {
        // Your code remains the same, but now it correctly uses the imported DTO.
        if (request.getUrl() == null || request.getUrl().isEmpty()) {
            return ResponseEntity.badRequest().body("URL is required");
        }

        try {
            ShortUrl newShortUrl = urlShortenerService.createShortUrl(request.getUrl(), request.getValidity(), request.getShortcode());

            Map<String, String> response = new HashMap<>();
            String fullShortLink = servletRequest.getRequestURL().toString().replace("/api/shorturls", "/") + newShortUrl.getShortCode();
            response.put("shortLink", fullShortLink);
            response.put("expiry", newShortUrl.getExpiresAt().toString());

            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @GetMapping("/{shortCode}")
    public RedirectView redirectToLongUrl(@PathVariable String shortCode) {
        return urlShortenerService.getByShortCode(shortCode)
                .map(shortUrl -> {
                    if (shortUrl.getExpiresAt().isBefore(LocalDateTime.now())) {
                        throw new RuntimeException("Link expired"); // Example error handling
                    }
                    // Log click event here
                    return new RedirectView(shortUrl.getLongUrl());
                })
                .orElseThrow(() -> new RuntimeException("Shortcode not found")); // Example error handling
    }
}