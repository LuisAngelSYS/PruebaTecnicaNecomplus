package com.necomplus.estacionamientopago.common;

import com.necomplus.estacionamientopago.dao.VehiculoNoResidenteDao;
import com.necomplus.estacionamientopago.dao.VehiculoResidenteDao;
import com.necomplus.estacionamientopago.dao.VehiculoOficialDao;
import com.necomplus.estacionamientopago.dao.EstanciaDao;

public class Common {
    public static void reiniciar() {
        VehiculoOficialDao vehiculoOficialDao = new VehiculoOficialDao();
        VehiculoResidenteDao vehiculoResidenteDao = new VehiculoResidenteDao();
        VehiculoNoResidenteDao vehiculoNoResidenteDao = new VehiculoNoResidenteDao();
        EstanciaDao estanciaDao = new EstanciaDao();

        estanciaDao.reiniciar();
        vehiculoOficialDao.reiniciar();
        vehiculoResidenteDao.reiniciar();
        vehiculoNoResidenteDao.reiniciar();
    }
}
