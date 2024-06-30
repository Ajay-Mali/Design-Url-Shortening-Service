package com.api.urlshortening.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Url {

	@Id
	@GeneratedValue
	private long id;
	@Lob
	private String originalUrl;
	private String shortLink;
	private LocalDateTime creationDate;
	private LocalDateTime expirationDate;

	public Url() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Url(long id, String originalUrl, String shortLink, LocalDateTime creationDate, LocalDateTime expirationDate) {
		super();
		this.id = id;
		this.originalUrl = originalUrl;
		this.shortLink = shortLink;
		this.creationDate = creationDate;
		this.expirationDate = expirationDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getoriginalUrl() {
		return originalUrl;
	}

	public void setoriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortLink() {
		return shortLink;
	}

	public void setShortLink(String shortLink) {
		this.shortLink = shortLink;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDateTime getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDateTime expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "Url [id=" + id + ", originalUrl=" + originalUrl + ", shortLink=" + shortLink + ", creationDate="
				+ creationDate + ", expirationDate=" + expirationDate + "]";
	}

}
