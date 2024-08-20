package com.necomplus.estacionamientopago.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculadorTest {

    @Test
    void minutosEstacionados() {
        String txtFechaInicial = "2024-08-19 20:11:53";
        String txtFechaFinal = "2024-08-19 20:19:53";
        DateTimeFormatter formateador = new DateTimeFormatterBuilder().parseCaseInsensitive().append(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).toFormatter();
        LocalDateTime fechaInicial = LocalDateTime.parse(txtFechaInicial, formateador);
        LocalDateTime fechaFinal = LocalDateTime.parse(txtFechaFinal, formateador);

        long minutosEstacionados = Calculador.minutosEstacionados(fechaInicial, fechaFinal);
        Assertions.assertEquals(8, minutosEstacionados);
    }

    @Test
    void importeResidentes() {
        double importeAPagarResidentes = Calculador.importeResidentes(15);
        Assertions.assertEquals(1500, importeAPagarResidentes);
    }

    @Test
    void importeNoResidentes() {
        double importeAPagarNoResidentes = Calculador.importeNoResidentes(10);
        Assertions.assertEquals(5000, importeAPagarNoResidentes);
    }
}