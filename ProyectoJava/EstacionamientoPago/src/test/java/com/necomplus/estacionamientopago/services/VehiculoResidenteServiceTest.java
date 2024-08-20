package com.necomplus.estacionamientopago.services;

import com.necomplus.estacionamientopago.common.Common;
import com.necomplus.estacionamientopago.entities.VehiculoResidente;
import com.necomplus.estacionamientopago.dao.VehiculoResidenteDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehiculoResidenteServiceTest {
    private VehiculoResidenteDao vehiculoResidenteDao = new VehiculoResidenteDao();

    @Test
    void altaVehiculoResidente() {
        Common.reiniciar();

        String placa = "RES001";
        try{
            VehiculoResidenteService service = new VehiculoResidenteService();
            service.altaVehiculoResidente(placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        VehiculoResidente vehiculoResidente = vehiculoResidenteDao.buscarPorPlaca(placa);

        Assertions.assertNotEquals(null, vehiculoResidente);
        Assertions.assertEquals(null, vehiculoResidente.getHoraEntrada());

    }
}