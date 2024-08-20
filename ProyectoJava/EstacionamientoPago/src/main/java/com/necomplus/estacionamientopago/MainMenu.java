package com.necomplus.estacionamientopago;

import java.util.Scanner;
import com.necomplus.estacionamientopago.services.*;

public class MainMenu {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            clearScreen();
            System.out.println("******************************************");
            System.out.println("          ESTACIONAMIENTO PAGO");
            System.out.println("             Menú Principal");
            System.out.println("******************************************");
            System.out.println("1. Registrar entrada");
            System.out.println("2. Registrar salida");
            System.out.println("3. Dar de alta vehículo oficial");
            System.out.println("4. Dar de alta vehículo de residente");
            System.out.println("5. Comienza mes");
            System.out.println("6. Pagos de residentes");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            try{
                option = scanner.nextInt();
            } catch (Exception e) {
                option = -1;
            }
            scanner.nextLine();
            System.out.println("******************************************");

            switch (option) {
                case 1:
                    registrarEntrada();
                    break;

                case 2:
                    registrarSalida();
                    break;

                case 3:
                    darAltaVehiculoOficial();
                    break;

                case 4:
                    darAltaVehiculoResidente();
                    break;

                case 5:
                    reiniciarMes();
                    break;

                case 6:
                    generarInformePagosResidentes();
                    break;

                case 0:
                    System.out.println("Saliendo del sistema . . .");
                    break;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (option != 0);

        scanner.close();
    }

    public static void registrarEntrada() {
        clearScreen();
        System.out.println("******************************************");
        System.out.println("          REGISTRO DE ENTRADAS");
        System.out.println("******************************************");
        System.out.print("Ingrese el número de placa: ");
        Scanner scanner = new Scanner(System.in);
        String placa = scanner.nextLine();
        try{
            EntradaService service = new EntradaService();
            service.registrar(placa);
            System.out.println("Entrada registrada para el vehículo con placa: " + placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void registrarSalida() {
        clearScreen();
        System.out.println("******************************************");
        System.out.println("          REGISTRO DE SALIDAS");
        System.out.println("******************************************");
        System.out.print("Ingrese el número de placa: ");
        Scanner scanner = new Scanner(System.in);
        String placa = scanner.nextLine();
        try{
            SalidaService service = new SalidaService();
            service.registrar(placa);
            //System.out.println("Salida registrada para el vehículo con placa: " + placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void darAltaVehiculoOficial() {
        clearScreen();
        System.out.println("******************************************");
        System.out.println("        ALTA DE VEHÍCULO OFICIAL");
        System.out.println("******************************************");
        System.out.print("Ingrese el número de placa del vehículo oficial: ");
        Scanner scanner = new Scanner(System.in);
        String placa = scanner.nextLine();
        try{
            VehiculoOficialService service = new VehiculoOficialService();
            service.altaVehiculoOficial(placa);
            System.out.println("Vehículo oficial dado de alta con placa: " + placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void darAltaVehiculoResidente() {
        clearScreen();
        System.out.println("******************************************");
        System.out.println("        ALTA DE VEHÍCULO RESIDENTE");
        System.out.println("******************************************");
        System.out.print("Ingrese el número de placa del vehículo residente: ");
        Scanner scanner = new Scanner(System.in);
        String placa = scanner.nextLine();
        try{
            VehiculoResidenteService service = new VehiculoResidenteService();
            service.altaVehiculoResidente(placa);
            System.out.println("Vehículo residente dado de alta con placa: " + placa);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void reiniciarMes() {
        clearScreen();
        System.out.println("******************************************");
        System.out.println("Reiniciando mes . . . .");
        ReinicioService reinicioService = new ReinicioService();
        try{
            reinicioService.reiniciarMes();
            System.out.println("El mes ya fue reiniciado");
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void generarInformePagosResidentes() {
        clearScreen();
        System.out.println("******************************************");
        System.out.println("           PAGOS DE RESIDENTES");
        System.out.println("******************************************");
        System.out.print("Ingrese el nombre del archivo que contiene el informne de pago de residentes: ");
        Scanner scanner = new Scanner(System.in);
        String nombreArchivo = scanner.nextLine();
        try{
            InformeService service = new InformeService();
            service.generarInformePagoResidentes(nombreArchivo);
        }
        catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                // Comando para limpiar pantalla en Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Comando para limpiar pantalla en Unix/Linux/MacOS
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Error al limpiar la pantalla.");
        }
    }
}
