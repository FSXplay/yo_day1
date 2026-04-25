package com.yo.day1.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.yo.day1.domain.entity.Parent;
import com.yo.day1.repository.ParentRepository;
import com.yo.day1.service.ParentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {
    private final ParentRepository parentRepository;
    
    public List<Parent> findAll() {
        return parentRepository.findAll();
    }

    public Optional<Parent> findById(Long id) {
        return parentRepository.findById(id);
    }

    public Parent save(Parent parent) {
        return parentRepository.save(parent);
    }

    public Optional<Parent> update(Long id, Parent updatedParent) {
        return parentRepository.findById(id)
            .map(existingParent -> {
                existingParent.setFullName(updatedParent.getFullName());
                existingParent.setPhone(updatedParent.getPhone());
                existingParent.setEmail(updatedParent.getEmail());
                existingParent.setAddress(updatedParent.getAddress());
                return parentRepository.save(existingParent);
            });
    }
}
