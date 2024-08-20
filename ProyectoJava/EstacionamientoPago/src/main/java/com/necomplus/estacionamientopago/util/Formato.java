package com.necomplus.estacionamientopago.util;

import java.text.DecimalFormat;

public class Formato {

    public static String formatearImporte(double importe) {
        DecimalFormat formato = new DecimalFormat("#,###.00");
        return formato.format(importe);
    }
}
