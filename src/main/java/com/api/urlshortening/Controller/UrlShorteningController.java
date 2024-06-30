package com.api.urlshortening.Controller;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.urlshortening.model.Url;
import com.api.urlshortening.model.UrlDto;
import com.api.urlshortening.model.UrlErrorResponseDto;
import com.api.urlshortening.model.UrlResponseDto;
import com.api.urlshortening.service.UrlSerivce;

import ch.qos.logback.core.util.StringUtil;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class UrlShorteningController {

	@Autowired
	private UrlSerivce urlSerivce;

	@GetMapping("/testing")
	public String Testing() {
		return "Testing.....";
	}

	@PostMapping("/generate")
	public ResponseEntity<?> generateShortLink(@RequestBody UrlDto urlDto) {

		Url urlToRet = urlSerivce.generateShortLink(urlDto);

		if (urlToRet != null) {
			UrlResponseDto urlResponseDto = new UrlResponseDto();
			urlResponseDto.setOriginalUrl(urlToRet.getoriginalUrl());
			urlResponseDto.setExpirationDate(urlToRet.getExpirationDate());
			urlResponseDto.setShortLine(urlToRet.getShortLink());
			return new ResponseEntity<UrlResponseDto>(urlResponseDto, HttpStatus.OK);
		}

		UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
		urlErrorResponseDto.setStatus("404");
		urlErrorResponseDto.setError("There was an error processing your request. please try again.");
		return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
	}

	@GetMapping("/{shortLink}")
	public ResponseEntity<?> redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response)
			throws IOException {
		if (StringUtils.isEmpty(shortLink)) {
			UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
			urlErrorResponseDto.setError("Invalid Url");
			urlErrorResponseDto.setStatus("400");
			return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
		}

		Url urlToRet = urlSerivce.getEncodedUrl(shortLink);
		if (urlToRet == null) {
			UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
			urlErrorResponseDto.setError("Url does not exist or it might have expired!");
			urlErrorResponseDto.setStatus("400");
			return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
		}

		if (urlToRet.getExpirationDate().isBefore(LocalDateTime.now())) {
			//urlSerivce.deleteShortLink(urlToRet);
			UrlErrorResponseDto urlErrorResponseDto = new UrlErrorResponseDto();
			urlErrorResponseDto.setError("Url Expired. Please try generating a fresh one.");
			urlErrorResponseDto.setStatus("200");
			return new ResponseEntity<UrlErrorResponseDto>(urlErrorResponseDto, HttpStatus.OK);
		}

		response.sendRedirect(urlToRet.getoriginalUrl());
		return null;
	}

}
