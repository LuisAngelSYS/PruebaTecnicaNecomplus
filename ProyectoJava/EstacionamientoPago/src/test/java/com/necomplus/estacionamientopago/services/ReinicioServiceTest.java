package com.necomplus.estacionamientopago.services;

import com.necomplus.estacionamientopago.common.Common;
import com.necomplus.estacionamientopago.dao.EstanciaDao;
import com.necomplus.estacionamientopago.dao.VehiculoResidenteDao;
import com.necomplus.estacionamientopago.entities.Estancia;
import com.necomplus.estacionamientopago.entities.VehiculoResidente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ReinicioServiceTest {

    private VehiculoResidenteDao vehiculoResidenteDao = new VehiculoResidenteDao();
    private EstanciaDao estanciaDao = new EstanciaDao();

    @Test
    void reiniciarMes() {
        Common.reiniciar();

        String placaResidente = "RES010";

        try{
            VehiculoResidenteService service = new VehiculoResidenteService();
            service.altaVehiculoResidente(placaResidente);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        String placaOficial = "ABC596";
        try{
            VehiculoOficialService service = new VehiculoOficialService();
            service.altaVehiculoOficial(placaOficial);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        VehiculoResidente vehiculoResidente = vehiculoResidenteDao.buscarPorPlaca(placaResidente);
        vehiculoResidente.setMinutosAcumulados(10);
        vehiculoResidenteDao.salvar(vehiculoResidente);

        try{
            EntradaService service = new EntradaService();
            service.registrar(placaOficial);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try{
            SalidaService service = new SalidaService();
            service.registrar(placaOficial);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }


        ReinicioService reinicioService = new ReinicioService();
        try{
            reinicioService.reiniciarMes();
            System.out.println("El mes ya fue reiniciado");
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        vehiculoResidente = vehiculoResidenteDao.buscarPorPlaca(placaResidente);
        Assertions.assertEquals(0, vehiculoResidente.getMinutosAcumulados());

        List<Estancia> estancias = estanciaDao.buscarTodo();
        Assertions.assertTrue(estancias == null || estancias.isEmpty());
    }
}