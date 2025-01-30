package com.practico.core.miapi.controller.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class BranchDTO {
    private String id;
    private String emailAddress;
    private String name;
    private String phoneNumber;
    private String state;
    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;
    private List<HolidayDTO> branchHolidays;
}
