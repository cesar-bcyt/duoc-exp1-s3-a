package com.example.exp1s3a.Controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.RestController;

import com.example.exp1s3a.Modelos.Reserva;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ReservaController {
    private HashMap<Integer, List<Reserva>> diccionarioReservas = new HashMap<Integer, List<Reserva>>();

    ReservaController() {
        List<Reserva> reservasHabitacion1 = new ArrayList<Reserva>();
        List<Reserva> reservasHabitacion2 = new ArrayList<Reserva>();
        List<Reserva> reservasHabitacion3 = new ArrayList<Reserva>();

        // Datos: 10 (mínimo 8 según rúbrica)
        reservasHabitacion1.add(new Reserva(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 2)));
        reservasHabitacion1.add(new Reserva(LocalDate.of(2024, 1, 2), LocalDate.of(2024, 1, 3)));
        reservasHabitacion1.add(new Reserva(LocalDate.of(2024, 1, 5), LocalDate.of(2024, 1, 6)));
        reservasHabitacion1.add(new Reserva(LocalDate.of(2024, 1, 8), LocalDate.of(2024, 1, 10)));

        reservasHabitacion2.add(new Reserva(LocalDate.of(2024, 2, 1), LocalDate.of(2024, 1, 2)));
        reservasHabitacion2.add(new Reserva(LocalDate.of(2024, 2, 2), LocalDate.of(2024, 1, 3)));
        reservasHabitacion2.add(new Reserva(LocalDate.of(2024, 2, 5), LocalDate.of(2024, 1, 6)));

        reservasHabitacion3.add(new Reserva(LocalDate.of(2024, 3, 1), LocalDate.of(2024, 1, 2)));
        reservasHabitacion3.add(new Reserva(LocalDate.of(2024, 3, 2), LocalDate.of(2024, 1, 3)));
        reservasHabitacion3.add(new Reserva(LocalDate.of(2024, 3, 5), LocalDate.of(2024, 1, 6)));

        diccionarioReservas.put(1, reservasHabitacion1);
        diccionarioReservas.put(2, reservasHabitacion2);
        diccionarioReservas.put(3, reservasHabitacion3);
    }

    // Ejemplo: /reservas/1
    @GetMapping("/reservas/{numeroHabitacion}")
    public List<Reserva> getReservas(@PathVariable int numeroHabitacion) {
        return diccionarioReservas.get(numeroHabitacion);
    }

    // Ejemplo: /disponibilidad/1/2024-01-01/2024-01-10
    @GetMapping("/disponibilidad/{numeroHabitacion}/{fechaInicial}/{fechaFinal}")
    public List<LocalDate> getDisponibilidad(@PathVariable int numeroHabitacion, @PathVariable LocalDate fechaInicial, @PathVariable LocalDate fechaFinal) {
        List<Reserva> reservas = diccionarioReservas.get(numeroHabitacion);
        List<LocalDate> disponibilidad = new ArrayList<LocalDate>();

        for (LocalDate fecha = fechaInicial; !fecha.isAfter(fechaFinal); fecha = fecha.plusDays(1)) {
            boolean disponible = true;
            for (Reserva reserva : reservas) {
                if ((fecha.isEqual(reserva.getFechaCheckIn()) || fecha.isAfter(reserva.getFechaCheckIn())) &&
                    (fecha.isEqual(reserva.getFechaCheckOut()) || fecha.isBefore(reserva.getFechaCheckOut()))) {
                    disponible = false;
                    break;
                }
            }
            if (disponible) {
                disponibilidad.add(fecha);
            }
        }
        
        return disponibilidad;
    }
}