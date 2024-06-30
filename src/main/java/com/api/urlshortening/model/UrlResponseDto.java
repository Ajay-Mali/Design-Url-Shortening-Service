package com.api.urlshortening.model;

import java.time.LocalDateTime;

public class UrlResponseDto {

	private String originalUrl;
	private String shortLine;
	private LocalDateTime expirationDate;
	
	public UrlResponseDto() {
		
	}

	public UrlResponseDto(String originalUrl, String shortLine, LocalDateTime expirationDate) {
		super();
		this.originalUrl = originalUrl;
		this.shortLine = shortLine;
		this.expirationDate = expirationDate;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortLine() {
		return shortLine;
	}

	public void setShortLine(String shortLine) {
		this.shortLine = shortLine;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "UrlResponseDto [originalUrl=" + originalUrl + ", shortLine=" + shortLine + ", expirationDate="
				+ expirationDate + "]";
	}
	
	
}
