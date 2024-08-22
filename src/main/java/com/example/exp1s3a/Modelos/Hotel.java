package com.example.exp1s3a.Modelos;

import java.util.HashMap;
import java.util.List;

public class Hotel {
    String nombre;
    HashMap<Integer, List<Reserva>> reservas; // Núm. Habitación -> Lista de reservas

    public HashMap<Integer, List<Reserva>> getReservas() {
        return reservas;
    }
    public void setReservas(HashMap<Integer, List<Reserva>> reservas) {
        this.reservas = reservas;
    }
}
