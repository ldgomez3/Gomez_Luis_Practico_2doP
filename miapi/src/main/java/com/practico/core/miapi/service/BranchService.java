package com.practico.core.miapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.practico.core.miapi.controller.dto.BranchDTO;
import com.practico.core.miapi.model.Branch;
import com.practico.core.miapi.repository.BranchRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;

    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    public Branch createBranch(BranchDTO branchDTO) {
        Branch branch = new Branch();
        branch.setEmailAddress(branchDTO.getEmailAddress());
        branch.setName(branchDTO.getName());
        branch.setPhoneNumber(branchDTO.getPhoneNumber());
        branch.setState(branchDTO.getState());
        branch.setCreationDate(LocalDateTime.now());
        branch.setLastModifiedDate(LocalDateTime.now());
        return branchRepository.save(branch);
    }

    public Optional<Branch> getBranchById(String id) {
        return branchRepository.findById(id);
    }

    // En BranchService.java
    public Branch updateBranchPhoneNumber(String id, String newPhoneNumber) {
        return branchRepository.findById(id)
                .map(branch -> {
                    branch.setPhoneNumber(newPhoneNumber);
                    branch.setLastModifiedDate(LocalDateTime.now());
                    return branchRepository.save(branch);
                })
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
    }

    
}
