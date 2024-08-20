package com.necomplus.estacionamientopago.services;

import com.necomplus.estacionamientopago.entities.VehiculoOficial;
import com.necomplus.estacionamientopago.dao.VehiculoOficialDao;

public class VehiculoOficialService {
    private VehiculoOficialDao vehiculoOficialDao = new VehiculoOficialDao();

    public void altaVehiculoOficial(String placa) {
        VehiculoOficial newVehiculoOficial = new VehiculoOficial(placa);
        vehiculoOficialDao.salvar(newVehiculoOficial);

        VehiculoOficial vehiculoOficialABuscar = new VehiculoOficial(placa);



    }
}
