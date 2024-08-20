package com.necomplus.estacionamientopago.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.*;
import java.time.*;

@Entity
public class Estancia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "vehiculo_id", nullable = false)
    private Vehiculo vehiculo;

    @Column(name = "hora_entrada", nullable = false)
    private LocalDateTime horaEntrada;

    @Column(name = "hora_salida")
    private LocalDateTime horaSalida;

    public Estancia() {}

    public Estancia(Vehiculo vehiculo, LocalDateTime horaEntrada, LocalDateTime horaSalida  ) {
        this.vehiculo = vehiculo;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }
}