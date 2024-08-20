package com.necomplus.estacionamientopago.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.DiscriminatorValue;


@Entity
@DiscriminatorValue("OFICIAL")
public class VehiculoOficial extends Vehiculo {

    public VehiculoOficial(String placa) {
        super(placa);
    }

    public VehiculoOficial() {
        super();
    }
}
