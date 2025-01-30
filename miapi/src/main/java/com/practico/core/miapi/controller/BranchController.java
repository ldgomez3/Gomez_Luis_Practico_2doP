package com.practico.core.miapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.practico.core.miapi.controller.dto.BranchDTO;
import com.practico.core.miapi.model.Branch;
import com.practico.core.miapi.service.BranchService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    @PostMapping
    public Branch createBranch(@RequestBody BranchDTO branchDTO) {
        return branchService.createBranch(branchDTO);
    }

    @GetMapping("/{id}")
    public Optional<Branch> getBranchById(@PathVariable String id) {
        return branchService.getBranchById(id);
    }

    @PutMapping("/{id}/phone")
    public Branch updateBranchPhoneNumber(@PathVariable String id, @RequestParam String newPhoneNumber) {
        log.info("Actualizando número de teléfono de la sucursal con ID: {}", id);
        return branchService.updateBranchPhoneNumber(id, newPhoneNumber);
    }
}