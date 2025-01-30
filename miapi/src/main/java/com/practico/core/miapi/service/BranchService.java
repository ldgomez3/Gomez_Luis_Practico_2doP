package com.practico.core.miapi.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.practico.core.miapi.controller.dto.BranchDTO;
import com.practico.core.miapi.controller.dto.HolidayDTO;
import com.practico.core.miapi.model.Branch;
import com.practico.core.miapi.model.Holiday;
import com.practico.core.miapi.repository.BranchRepository;

import java.time.LocalDate;
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

    public Branch updateBranchPhoneNumber(String id, String newPhoneNumber) {
        return branchRepository.findById(id)
                .map(branch -> {
                    branch.setPhoneNumber(newPhoneNumber);
                    branch.setLastModifiedDate(LocalDateTime.now());
                    return branchRepository.save(branch);
                })
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
    }

    public Branch addHolidayToBranch(String id, HolidayDTO holidayDTO) {
        return branchRepository.findById(id)
                .map(branch -> {
                    Holiday holiday = new Holiday();
                    holiday.setDate(holidayDTO.getDate());
                    holiday.setName(holidayDTO.getName());
                    branch.getBranchHolidays().add(holiday);
                    branch.setLastModifiedDate(LocalDateTime.now());
                    return branchRepository.save(branch);
                })
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
    }

    public Branch removeHolidayFromBranch(String id, LocalDate holidayDate) {
        return branchRepository.findById(id)
                .map(branch -> {
                    branch.getBranchHolidays().removeIf(holiday -> holiday.getDate().equals(holidayDate));
                    branch.setLastModifiedDate(LocalDateTime.now());
                    return branchRepository.save(branch);
                })
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
    }

    public List<Holiday> getBranchHolidays(String id) {
        return branchRepository.findById(id)
                .map(Branch::getBranchHolidays)
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
    }

    public boolean isHoliday(String id, LocalDate date) {
        return branchRepository.findById(id)
                .map(branch -> branch.getBranchHolidays().stream()
                        .anyMatch(holiday -> holiday.getDate().equals(date)))
                .orElseThrow(() -> new RuntimeException("Sucursal no encontrada"));
    }

}
