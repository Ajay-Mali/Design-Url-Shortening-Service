package com.api.urlshortening.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.urlshortening.model.Url;
import com.api.urlshortening.model.UrlDto;
import com.api.urlshortening.repository.UrlRepository;
import com.google.common.hash.Hashing;

@Service
public class UrlServiceImpl implements UrlSerivce {

	@Autowired
	private UrlRepository urlRepository;

	@Override
	public Url generateShortLink(UrlDto urlDto) {

		if (StringUtils.isNotEmpty(urlDto.getUrl())) {
			String encodedUrl = encodeUrl(urlDto.getUrl());
			Url urlToPersist = new Url();
			urlToPersist.setCreationDate(LocalDateTime.now());
			urlToPersist.setoriginalUrl(urlDto.getUrl());
			urlToPersist.setShortLink(encodedUrl);
			urlToPersist
					.setExpirationDate(getExpirationDate(urlDto.getExpirationDate(), urlToPersist.getCreationDate()));
			Url urlToRet = persistShortLink(urlToPersist);

			if (urlToRet != null) {
				return urlToRet;
			}
			return null;
		}
		return null;

	}

	private LocalDateTime getExpirationDate(String expirationDate, LocalDateTime creationDate) {

		if (StringUtils.isBlank(expirationDate)) {
			return creationDate.plusSeconds(60);
		}
		LocalDateTime expirationDateToRet = LocalDateTime.parse(expirationDate);
		return expirationDateToRet;
	}

	private String encodeUrl(String url) {
		String encodedUrl = "";
		LocalDateTime time = LocalDateTime.now();
		encodedUrl = Hashing.murmur3_32().hashString(url.concat(time.toString()), StandardCharsets.UTF_8).toString();
		return encodedUrl;
	}

	@Override
	public Url persistShortLink(Url url) {
		Url urlToRet = urlRepository.save(url);
		return urlToRet;
	}

	@Override
	public Url getEncodedUrl(String url) {
		Url urlToRet = urlRepository.findByShortLink(url);
		return urlToRet;
	}

	@Override
	public void deleteShortLink(Url url) {
		urlRepository.delete(url);
	}

}
