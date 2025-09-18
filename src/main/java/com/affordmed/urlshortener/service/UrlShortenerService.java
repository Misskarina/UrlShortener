package com.affordmed.urlshortener.service;

import com.affordmed.urlshortener.model.ShortUrl;
import com.affordmed.urlshortener.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UrlShortenerService {

    @Autowired
    private ShortUrlRepository shortUrlRepository;

    private static final String ALPHANUMERIC_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORTCODE_LENGTH = 7;

    public ShortUrl createShortUrl(String longUrl, Integer validity, String customShortCode) {
        if (customShortCode != null && !customShortCode.isEmpty()) {
            if (shortUrlRepository.findByShortCode(customShortCode).isPresent()) {
                throw new IllegalArgumentException("Custom shortcode already in use");
            }
        } else {
            customShortCode = generateUniqueShortCode();
        }

        LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(validity != null ? validity : 30);

        ShortUrl shortUrl = new ShortUrl();
        shortUrl.setLongUrl(longUrl);
        shortUrl.setShortCode(customShortCode);
        shortUrl.setCreatedAt(LocalDateTime.now());
        shortUrl.setExpiresAt(expiryDate);

        return shortUrlRepository.save(shortUrl);
    }

    public Optional<ShortUrl> getByShortCode(String shortCode) {
        return shortUrlRepository.findByShortCode(shortCode);
    }

    private String generateUniqueShortCode() {
        String shortCode;
        do {
            shortCode = generateRandomString();
        } while (shortUrlRepository.findByShortCode(shortCode).isPresent());
        return shortCode;
    }

    private String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(SHORTCODE_LENGTH);
        for (int i = 0; i < SHORTCODE_LENGTH; i++) {
            sb.append(ALPHANUMERIC_CHARS.charAt(random.nextInt(ALPHANUMERIC_CHARS.length())));
        }
        return sb.toString();
    }
}