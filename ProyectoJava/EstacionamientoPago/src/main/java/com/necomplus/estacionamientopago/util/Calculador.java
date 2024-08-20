package com.necomplus.estacionamientopago.util;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Calculador {
    public static long minutosEstacionados(LocalDateTime horaEntrada, LocalDateTime horaSalida){
        return ChronoUnit.MINUTES.between(horaEntrada, horaSalida);
    }

    public static double importeResidentes(long minutos){
        return minutos * Constante.TARIFA_RESIDENTES;
    }

    public static double importeNoResidentes(long minutos){
        return minutos * Constante.TARIFA_NO_RESIDENTES;
    }
}
