package com.necomplus.estacionamientopago.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("RESIDENTE")
public class VehiculoResidente extends Vehiculo {

    @Column(name = "minutos_acumulados")
    private long minutosAcumulados;

    public VehiculoResidente(String placa) {
        super(placa);
    }

    public VehiculoResidente() {
        super();
    }

    public long getMinutosAcumulados() {
        return minutosAcumulados;
    }

    public void setMinutosAcumulados(long minutosAcumulados) {
        this.minutosAcumulados = minutosAcumulados;
    }
}
