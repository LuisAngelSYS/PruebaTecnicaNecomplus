package com.necomplus.estacionamientopago.services;

import com.necomplus.estacionamientopago.common.Common;
import com.necomplus.estacionamientopago.entities.VehiculoResidente;
import com.necomplus.estacionamientopago.dao.VehiculoResidenteDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

class InformeServiceTest {

    private VehiculoResidenteDao vehiculoResidenteDao = new VehiculoResidenteDao();

    @Test
    void generarInformePagoResidentes() {
        Common.reiniciar();

        String placa = "RES008";
        try{
            VehiculoResidenteService service = new VehiculoResidenteService();
            service.altaVehiculoResidente(placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        VehiculoResidente vehiculoResidente = vehiculoResidenteDao.buscarPorPlaca(placa);
        vehiculoResidente.setMinutosAcumulados(10);
        vehiculoResidenteDao.salvar(vehiculoResidente);

        String nombreArchivo = "ReporteCarteraResidentes.txt";
        try{
            InformeService service = new InformeService();
            service.generarInformePagoResidentes(nombreArchivo);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }

        File file = new File(nombreArchivo);
        Assertions.assertTrue(file.exists());

        file.delete();
    }
}