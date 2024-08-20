package com.necomplus.estacionamientopago.services;

import com.necomplus.estacionamientopago.common.Common;
import com.necomplus.estacionamientopago.dao.EstanciaDao;
import com.necomplus.estacionamientopago.dao.VehiculoNoResidenteDao;
import com.necomplus.estacionamientopago.dao.VehiculoOficialDao;
import com.necomplus.estacionamientopago.dao.VehiculoResidenteDao;
import com.necomplus.estacionamientopago.entities.Estancia;
import com.necomplus.estacionamientopago.entities.VehiculoNoResidente;
import com.necomplus.estacionamientopago.entities.VehiculoOficial;
import com.necomplus.estacionamientopago.entities.VehiculoResidente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalidaServiceTest {

    private VehiculoOficialDao vehiculoOficialDao = new VehiculoOficialDao();
    private VehiculoResidenteDao vehiculoResidenteDao = new VehiculoResidenteDao();
    private VehiculoNoResidenteDao vehiculoNoResidenteDao = new VehiculoNoResidenteDao();
    private EstanciaDao estanciaDao = new EstanciaDao();

    @Test
    void registrarSalidaResidente() throws Exception {
        Common.reiniciar();

        String placa = "ABC015";

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

        try{
            SalidaService service = new SalidaService();
            service.registrar(placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        VehiculoResidente vehiculoResidente = vehiculoResidenteDao.buscarPorPlaca(placa);
        if(vehiculoResidente != null){
            Assertions.assertEquals(null, vehiculoResidente.getHoraEntrada());
            Assertions.assertEquals(0, vehiculoResidente.getMinutosAcumulados());
        }
        else{
            throw new Exception("El vehículo de placa " + placa + " no tiene registro de entrada.");
        }

        List<Estancia> estancias = estanciaDao.buscarTodo();
        Assertions.assertTrue(estancias == null || estancias.isEmpty());
    }

    @Test
    void registrarSalidaOficial() throws Exception {
        Common.reiniciar();

        String placa = "CBA019";

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

        try{
            SalidaService service = new SalidaService();
            service.registrar(placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        VehiculoOficial vehiculoOficial = vehiculoOficialDao.buscarPorPlaca(placa);
        if(vehiculoOficial != null){
            Assertions.assertEquals(null, vehiculoOficial.getHoraEntrada());
        }
        else{
            throw new Exception("El vehículo de placa " + placa + " no tiene registro de entrada.");
        }

        List<Estancia> estancias = estanciaDao.buscarTodo();
        Assertions.assertTrue(estancias != null && !estancias.isEmpty());
    }

    @Test
    void registrarSalidaNoResidente() throws Exception {
        Common.reiniciar();

        String placa = "HIQ935";

        try{
            EntradaService service = new EntradaService();
            service.registrar(placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        try{
            SalidaService service = new SalidaService();
            service.registrar(placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        VehiculoNoResidente vehiculoNoResidente = vehiculoNoResidenteDao.buscarPorPlaca(placa);
        if(vehiculoNoResidente != null){
            Assertions.assertEquals(null, vehiculoNoResidente.getHoraEntrada());
        }
        else{
            throw new Exception("El vehículo de placa " + placa + " no tiene registro de entrada.");
        }
    }
}