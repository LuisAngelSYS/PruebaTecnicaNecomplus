package com.necomplus.estacionamientopago.services;

import com.necomplus.estacionamientopago.dao.VehiculoResidenteDao;
import com.necomplus.estacionamientopago.dao.EstanciaDao;

public class ReinicioService {

    private VehiculoResidenteDao vehiculoResidenteDao = new VehiculoResidenteDao();
    private EstanciaDao estanciaDao = new EstanciaDao();

    public void reiniciarMes(){
        vehiculoResidenteDao.reiniciarTiempos();
        estanciaDao.reiniciar();
    }
}
