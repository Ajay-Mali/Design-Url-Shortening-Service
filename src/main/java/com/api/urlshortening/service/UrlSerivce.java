package com.api.urlshortening.service;

import org.springframework.stereotype.Service;

import com.api.urlshortening.model.Url;
import com.api.urlshortening.model.UrlDto;

@Service
public interface UrlSerivce {

	public Url generateShortLink(UrlDto urlDto);
	public Url persistShortLink(Url url);
	public Url getEncodedUrl(String url);
	public void deleteShortLink(Url url);
	
}
