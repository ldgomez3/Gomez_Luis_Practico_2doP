package com.practico.core.miapi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.*;

import com.practico.core.miapi.controller.dto.BranchDTO;
import com.practico.core.miapi.controller.dto.HolidayDTO;
import com.practico.core.miapi.model.Branch;
import com.practico.core.miapi.model.Holiday;
import com.practico.core.miapi.service.BranchService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/branches")
@RequiredArgsConstructor
@Slf4j
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
        // log.info("Actualizando número de teléfono de la sucursal con ID: {}", id);
        return branchService.updateBranchPhoneNumber(id, newPhoneNumber);
    }

    @PostMapping("/{id}/holidays")
    public Branch addHolidayToBranch(@PathVariable String id, @RequestBody HolidayDTO holidayDTO) {
        log.info("Agregando feriado a la sucursal con ID: {}", id);
        return branchService.addHolidayToBranch(id, holidayDTO);
    }

    @DeleteMapping("/{id}/holidays")
    public Branch removeHolidayFromBranch(@PathVariable String id, @RequestParam LocalDate holidayDate) {
        log.info("Eliminando feriado de la sucursal con ID: {}", id);
        return branchService.removeHolidayFromBranch(id, holidayDate);
    }

    @GetMapping("/{id}/holidays")
    public List<Holiday> getBranchHolidays(@PathVariable String id) {
        log.info("Obteniendo feriados de la sucursal con ID: {}", id);
        return branchService.getBranchHolidays(id);
    }

    @GetMapping("/{id}/is-holiday")
    public boolean isHoliday(@PathVariable String id, @RequestParam LocalDate date) {
        log.info("Verificando si la fecha {} es feriado en la sucursal con ID: {}", date, id);
        return branchService.isHoliday(id, date);
    }
}