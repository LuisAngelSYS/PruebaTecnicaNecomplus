package com.necomplus.estacionamientopago.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;

@Entity
@DiscriminatorValue("NO_RESIDENTE")
public class VehiculoNoResidente extends Vehiculo {

    public VehiculoNoResidente(String placa) {
        super(placa);
    }

    public VehiculoNoResidente() {
        super();
    }
}