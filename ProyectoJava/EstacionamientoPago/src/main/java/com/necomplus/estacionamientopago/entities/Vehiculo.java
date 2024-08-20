package com.necomplus.estacionamientopago.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import java.time.LocalDateTime;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_vehiculo")
public abstract class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa")
    private String placa;

    @Column(name = "hora_entrada", nullable = true)
    private LocalDateTime horaEntrada;

    public Vehiculo(String placa) {
        this.placa = placa.toUpperCase();
    }

    public Vehiculo() {
    }

    public String getPlaca() {
        return placa;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalDateTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }
}
