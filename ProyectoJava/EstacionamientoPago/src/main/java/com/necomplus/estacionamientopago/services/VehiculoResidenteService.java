package com.necomplus.estacionamientopago.services;

import com.necomplus.estacionamientopago.dao.VehiculoResidenteDao;
import com.necomplus.estacionamientopago.entities.VehiculoResidente;

public class VehiculoResidenteService {
    private VehiculoResidenteDao vehiculoResidenteDao = new VehiculoResidenteDao();

    public void altaVehiculoResidente(String placa) {
        VehiculoResidente vehiculoResidente = new VehiculoResidente(placa);
        vehiculoResidenteDao.salvar(vehiculoResidente);
    }
}

