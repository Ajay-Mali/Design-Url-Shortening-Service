package com.api.urlshortening.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.urlshortening.model.Url;


@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
	
	public Url findByShortLink(String shortLink);
	
	

}
