// src/main/java/com/pruebas/dto/LineaDTO.java
package com.pruebas.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineaDTO {
    private double fromLat;
    private double fromLon;
    private double toLat;
    private double toLon;
}
