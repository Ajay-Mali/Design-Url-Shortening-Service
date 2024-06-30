package com.api.urlshortening.model;

public class UrlDto {
	private String url;
	private String expirationDate;

	public UrlDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UrlDto(String url, String expirationDate) {
		super();
		this.url = url;
		this.expirationDate = expirationDate;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	@Override
	public String toString() {
		return "UrlDto [url=" + url + ", expirationDate=" + expirationDate + "]";
	}

}
