package com.practico.core.miapi.controller.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class HolidayDTO {
    private LocalDate date;
    private String name;
}