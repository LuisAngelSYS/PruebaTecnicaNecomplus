package com.necomplus.estacionamientopago.services;

import com.necomplus.estacionamientopago.common.Common;
import com.necomplus.estacionamientopago.entities.VehiculoOficial;
import com.necomplus.estacionamientopago.dao.VehiculoOficialDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class VehiculoOficialServiceTest {
    private VehiculoOficialDao vehiculoOficialDao = new VehiculoOficialDao();

    @Test
    void altaVehiculoOficial() {
        Common.reiniciar();

        String placa = "ABC596";
        try{
            VehiculoOficialService service = new VehiculoOficialService();
            service.altaVehiculoOficial(placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        VehiculoOficial vehiculoOficial = vehiculoOficialDao.buscarPorPlaca(placa);

        Assertions.assertNotEquals(null, vehiculoOficial);
        Assertions.assertEquals(null, vehiculoOficial.getHoraEntrada());
    }
}