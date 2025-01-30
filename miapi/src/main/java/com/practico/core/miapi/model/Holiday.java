package com.practico.core.miapi.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class Holiday {
    private LocalDate date;
    private String name;
}