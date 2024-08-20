package com.necomplus.estacionamientopago.services;

import com.necomplus.estacionamientopago.dao.VehiculoNoResidenteDao;
import com.necomplus.estacionamientopago.dao.VehiculoResidenteDao;
import com.necomplus.estacionamientopago.dao.VehiculoOficialDao;
import com.necomplus.estacionamientopago.entities.VehiculoOficial;
import com.necomplus.estacionamientopago.entities.VehiculoResidente;
import com.necomplus.estacionamientopago.entities.VehiculoNoResidente;
import com.necomplus.estacionamientopago.common.Common;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EntradaServiceTest {
    private VehiculoOficialDao vehiculoOficialDao = new VehiculoOficialDao();
    private VehiculoResidenteDao vehiculoResidenteDao = new VehiculoResidenteDao();
    private VehiculoNoResidenteDao vehiculoNoResidenteDao = new VehiculoNoResidenteDao();

    @Test
    void registrarEntradaResidente() throws Exception {
        Common.reiniciar();

        String placa = "ABC599";

        try{
            VehiculoResidenteService service = new VehiculoResidenteService();
            service.altaVehiculoResidente(placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try{
            EntradaService service = new EntradaService();
            service.registrar(placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        VehiculoResidente vehiculoResidente = vehiculoResidenteDao.buscarPorPlaca(placa);
        if(vehiculoResidente != null){
            Assertions.assertNotEquals(null, vehiculoResidente.getHoraEntrada());
        }
        else{
            throw new Exception("El vehículo de placa " + placa + " no tiene registro de entrada.");
        }
    }

    @Test
    void registrarEntradaOficial() throws Exception {
        Common.reiniciar();

        String placa = "ABC999";

        try{
            VehiculoOficialService service = new VehiculoOficialService();
            service.altaVehiculoOficial(placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try{
            EntradaService service = new EntradaService();
            service.registrar(placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        VehiculoOficial vehiculoOficial = vehiculoOficialDao.buscarPorPlaca(placa);
        if(vehiculoOficial != null) {
            Assertions.assertNotEquals(null, vehiculoOficial.getHoraEntrada());
        }
        else{
            throw new Exception("El vehículo de placa " + placa + " no tiene registro de entrada.");
        }
    }

    @Test
    void registrarEntradaNoResidente() throws Exception {
        Common.reiniciar();

        String placa = "ABC299";

        try{
            EntradaService service = new EntradaService();
            service.registrar(placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        VehiculoNoResidente vehiculoNoResidente = vehiculoNoResidenteDao.buscarPorPlaca(placa);
        if(vehiculoNoResidente != null){
            Assertions.assertNotEquals(null, vehiculoNoResidente.getHoraEntrada());
        }
        else{
            throw new Exception("El vehículo de placa " + placa + " no tiene registro de entrada.");
        }
    }
}