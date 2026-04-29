package com.yo.day1.service;

import java.util.List;
import java.util.Optional;

import com.yo.day1.domain.entity.Parent;

public interface ParentService {
    List<Parent> findAll();

    Optional<Parent> findById(Long id);

    Parent save(Parent parent);
    
    Optional<Parent> updateById(Long id, Parent updatedParent);

    Optional<Parent> deleteById(Long id);
}
