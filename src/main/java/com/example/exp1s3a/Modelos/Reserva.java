package com.example.exp1s3a.Modelos;

import java.time.LocalDate;

public class Reserva {
    LocalDate fechaCheckIn;
    LocalDate fechaCheckOut;

    public Reserva(LocalDate fechaCheckIn, LocalDate fechaCheckOut) {
        this.fechaCheckIn = fechaCheckIn;
        this.fechaCheckOut = fechaCheckOut;
    }

    public LocalDate getFechaCheckIn() {
        return fechaCheckIn;
    }
    public void setFechaCheckIn(LocalDate fechaCheckIn) {
        this.fechaCheckIn = fechaCheckIn;
    }
    public LocalDate getFechaCheckOut() {
        return fechaCheckOut;
    }
    public void setFechaCheckOut(LocalDate fechaCheckOut) {
        this.fechaCheckOut = fechaCheckOut;
    }
}
