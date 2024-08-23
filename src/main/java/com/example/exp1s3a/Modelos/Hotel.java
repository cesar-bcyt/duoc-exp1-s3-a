package com.example.exp1s3a.Modelos;

import java.util.HashMap;
import java.util.List;

public class Hotel {
    int id;
    private HashMap<Integer, List<Reserva>> reservas = new HashMap<Integer, List<Reserva>>();

    public void setReservas(int id) {
        this.id = id;
    }
    public HashMap<Integer, List<Reserva>> getReservas() {
        return reservas;
    }
    public void setReservas(HashMap<Integer, List<Reserva>> reservas) {
        this.reservas = reservas;
    }
}
