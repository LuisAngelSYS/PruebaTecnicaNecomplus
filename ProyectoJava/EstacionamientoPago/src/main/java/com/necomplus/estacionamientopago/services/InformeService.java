package com.necomplus.estacionamientopago.services;

import com.necomplus.estacionamientopago.entities.VehiculoResidente;
import com.necomplus.estacionamientopago.dao.VehiculoResidenteDao;
import com.necomplus.estacionamientopago.util.Calculador;
import com.necomplus.estacionamientopago.util.Formato;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class InformeService {

    public void generarInformePagoResidentes(String nombreArchivo) {
        List<VehiculoResidente> residentesConPagoPendiente =  new VehiculoResidenteDao().buscarResidentesConPagoPendiente();
        if(residentesConPagoPendiente != null && !residentesConPagoPendiente.isEmpty()){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {

                String encabezado = "Núm. placa\tTiempo estacionado (min.)\tCantidad a pagar";
                writer.write(encabezado);
                writer.newLine();
                for(VehiculoResidente veh : residentesConPagoPendiente){
                    writer.write(veh.getPlaca() + "\t" +
                                     veh.getMinutosAcumulados() + "\t" +
                                     Formato.formatearImporte(Calculador.importeResidentes(veh.getMinutosAcumulados())));
                    writer.newLine();
                }
                System.out.println("Informe de pagos de residentes generado en el archivo: " + nombreArchivo);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("ADVERTENCIA: No hay vehículos residentes con pagos pendientes");
        }
    }
}
