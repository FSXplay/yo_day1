package com.yo.day1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yo.day1.domain.entity.Parent;

public interface ParentRepository extends JpaRepository<Parent, Long> {
}
