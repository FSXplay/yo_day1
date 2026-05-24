package com.yo.day1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.repository.query.Param;

import com.yo.day1.domain.entity.Parent;

public interface ParentRepository extends JpaRepository<Parent, Long> {
    @NativeQuery(value = "SELECT (EXISTS (SELECT 1 FROM students s WHERE s.parent_id = :parentId) " +
                   "OR EXISTS (SELECT 1 FROM users u WHERE u.parent_id = :parentId))")
    int existsLinkedEntities(@Param("parentId") Long parentId);
}
