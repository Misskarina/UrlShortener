package com.affordmed.urlshortener.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClickEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "short_url_id", nullable = false)
    private ShortUrl shortUrl;

    private LocalDateTime clickTimestamp;

    private String referrer;

    private String geographicalLocation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ShortUrl getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(ShortUrl shortUrl) {
		this.shortUrl = shortUrl;
	}

	public LocalDateTime getClickTimestamp() {
		return clickTimestamp;
	}

	public void setClickTimestamp(LocalDateTime clickTimestamp) {
		this.clickTimestamp = clickTimestamp;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getGeographicalLocation() {
		return geographicalLocation;
	}

	public void setGeographicalLocation(String geographicalLocation) {
		this.geographicalLocation = geographicalLocation;
	}
    
}