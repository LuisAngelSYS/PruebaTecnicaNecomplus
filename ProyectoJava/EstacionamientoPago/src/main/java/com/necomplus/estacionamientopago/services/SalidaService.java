package com.necomplus.estacionamientopago.services;

import com.necomplus.estacionamientopago.dao.VehiculoNoResidenteDao;
import com.necomplus.estacionamientopago.dao.VehiculoResidenteDao;
import com.necomplus.estacionamientopago.dao.VehiculoOficialDao;
import com.necomplus.estacionamientopago.dao.EstanciaDao;

import com.necomplus.estacionamientopago.entities.VehiculoOficial;
import com.necomplus.estacionamientopago.entities.VehiculoResidente;
import com.necomplus.estacionamientopago.entities.VehiculoNoResidente;
import com.necomplus.estacionamientopago.entities.Estancia;
import com.necomplus.estacionamientopago.util.Calculador;
import com.necomplus.estacionamientopago.util.Formato;

import java.time.LocalDateTime;


public class SalidaService {
    private VehiculoOficialDao vehiculoOficialDao = new VehiculoOficialDao();
    private VehiculoResidenteDao vehiculoResidenteDao = new VehiculoResidenteDao();
    private VehiculoNoResidenteDao vehiculoNoResidenteDao = new VehiculoNoResidenteDao();
    private EstanciaDao estanciaDao = new EstanciaDao();


    public void registrar(String placa) throws Exception {

        VehiculoOficial vehiculoOficial = vehiculoOficialDao.buscarPorPlaca(placa);
        if(vehiculoOficial != null) {
            if(vehiculoOficial.getHoraEntrada() == null) {
                throw new Exception("El vehículo de placa " + placa + " no tiene registro de entrada.");
            }
            Estancia estancia = new Estancia(vehiculoOficial, vehiculoOficial.getHoraEntrada(), LocalDateTime.now());
            estanciaDao.salvar(estancia);
            System.out.println("Salida registrada para el vehículo oficial con placa: " + placa);
        }
        else{
            VehiculoResidente vehiculoResidente = vehiculoResidenteDao.buscarPorPlaca(placa);
            if(vehiculoResidente != null){
                if(vehiculoResidente.getHoraEntrada() == null) {
                    throw new Exception("El vehículo de placa " + placa + " no tiene registro de entrada.");
                }
                vehiculoResidente.setMinutosAcumulados(Calculador.minutosEstacionados(vehiculoResidente.getHoraEntrada(), LocalDateTime.now()));
                vehiculoResidente.setHoraEntrada(null);
                vehiculoResidenteDao.salvar(vehiculoResidente);
                System.out.println("Salida registrada para el vehículo residente con placa: " + placa);
            }
            else{
                VehiculoNoResidente vehiculoNoResidente = vehiculoNoResidenteDao.buscarPorPlaca(placa);
                if(vehiculoNoResidente != null){
                    if(vehiculoNoResidente.getHoraEntrada() == null) {
                        throw new Exception("El vehículo de placa " + placa + " no tiene registro de entrada.");
                    }
                    long minutos = Calculador.minutosEstacionados(vehiculoNoResidente.getHoraEntrada(), LocalDateTime.now());
                    double importaAPagar = Calculador.importeNoResidentes(minutos);
                    System.out.println("El importa a pagar es : $" + Formato.formatearImporte(importaAPagar) + " por " + minutos + " minutos.");
                    System.out.println("Salida registrada para el vehículo no residente con placa: " + placa);
                    vehiculoNoResidente.setHoraEntrada(null);
                    vehiculoNoResidenteDao.salvar(vehiculoNoResidente);
                }
                else{
                    throw new Exception("El vehículo de placa " + placa + " no tiene registro de entrada.");
                }
            }
        }
    }
}
