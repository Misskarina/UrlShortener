package com.affordmed.urlshortener.service;

import com.affordmed.urlshortener.model.ClickEvent;
import com.affordmed.urlshortener.model.ShortUrl;
import com.affordmed.urlshortener.repository.ClickEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class ClickEventService {

    @Autowired
    private ClickEventRepository clickEventRepository;

    public void logClick(ShortUrl shortUrl, String referrer, String geographicalLocation) {
        ClickEvent clickEvent = new ClickEvent();
        clickEvent.setShortUrl(shortUrl);
        clickEvent.setClickTimestamp(LocalDateTime.now());
        clickEvent.setReferrer(referrer);
        clickEvent.setGeographicalLocation(geographicalLocation);
        clickEventRepository.save(clickEvent);

        // Update click count on the parent ShortUrl entity
        shortUrl.setClickCount(shortUrl.getClickCount() + 1);
    }
}