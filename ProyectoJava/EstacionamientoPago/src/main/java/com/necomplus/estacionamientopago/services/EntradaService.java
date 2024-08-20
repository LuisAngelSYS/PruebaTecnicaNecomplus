package com.necomplus.estacionamientopago.services;

import com.necomplus.estacionamientopago.dao.VehiculoNoResidenteDao;
import com.necomplus.estacionamientopago.dao.VehiculoResidenteDao;
import com.necomplus.estacionamientopago.dao.VehiculoOficialDao;

import com.necomplus.estacionamientopago.entities.VehiculoOficial;
import com.necomplus.estacionamientopago.entities.VehiculoResidente;
import com.necomplus.estacionamientopago.entities.VehiculoNoResidente;
import java.time.*;

public class EntradaService {

    private VehiculoOficialDao vehiculoOficialDao = new VehiculoOficialDao();
    private VehiculoResidenteDao vehiculoResidenteDao = new VehiculoResidenteDao();
    private VehiculoNoResidenteDao vehiculoNoResidenteDao = new VehiculoNoResidenteDao();

    public void registrar(String placa){

        VehiculoOficial vehiculoOficial = vehiculoOficialDao.buscarPorPlaca(placa);
        if(vehiculoOficial != null) {
            vehiculoOficial.setHoraEntrada(LocalDateTime.now());
            vehiculoOficialDao.salvar(vehiculoOficial);
        }
        else{
            VehiculoResidente vehiculoResidente = vehiculoResidenteDao.buscarPorPlaca(placa);
            if(vehiculoResidente != null){
                vehiculoResidente.setHoraEntrada(LocalDateTime.now());
                vehiculoResidenteDao.salvar(vehiculoResidente);
            }
            else{
                VehiculoNoResidente vehiculoNoResidente = vehiculoNoResidenteDao.buscarPorPlaca(placa);
                if(vehiculoNoResidente == null){
                    vehiculoNoResidente = new VehiculoNoResidente(placa);
                }

                vehiculoNoResidente.setHoraEntrada(LocalDateTime.now());
                vehiculoNoResidenteDao.salvar(vehiculoNoResidente);
            }
        }
    }
}
