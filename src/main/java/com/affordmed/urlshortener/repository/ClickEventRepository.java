package com.affordmed.urlshortener.repository;

import com.affordmed.urlshortener.model.ClickEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClickEventRepository extends JpaRepository<ClickEvent, Long> {
}