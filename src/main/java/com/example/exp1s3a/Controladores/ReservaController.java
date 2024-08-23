package com.example.exp1s3a.Controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.LocalDate;

import org.springframework.web.bind.annotation.RestController;

import com.example.exp1s3a.Modelos.Hotel;
import com.example.exp1s3a.Modelos.Reserva;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ReservaController {
    private HashMap<Integer, Hotel> diccionarioHoteles = new HashMap<Integer, Hotel>();
    private HashMap<Integer, List<Reserva>> diccionarioReservasHotel1 = new HashMap<Integer, List<Reserva>>();
    private HashMap<Integer, List<Reserva>> diccionarioReservasHotel2 = new HashMap<Integer, List<Reserva>>();

    ReservaController() {
        Hotel hotel1 = new Hotel();
        Hotel hotel2 = new Hotel();
        List<Reserva> reservasHotel1Habitacion1 = new ArrayList<Reserva>();
        List<Reserva> reservasHotel1Habitacion2 = new ArrayList<Reserva>();
        List<Reserva> reservasHotel2Habitacion1 = new ArrayList<Reserva>();
        List<Reserva> reservasHotel2Habitacion2 = new ArrayList<Reserva>();

        // Datos: 10 (mínimo 8 según rúbrica)
        // Hotel 1
        reservasHotel1Habitacion1.add(new Reserva(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 7)));
        reservasHotel1Habitacion1.add(new Reserva(LocalDate.of(2024, 1, 8), LocalDate.of(2024, 1, 10)));

        reservasHotel1Habitacion2.add(new Reserva(LocalDate.of(2024, 2, 1), LocalDate.of(2024, 1, 2)));
        reservasHotel1Habitacion2.add(new Reserva(LocalDate.of(2024, 2, 2), LocalDate.of(2024, 1, 3)));
        reservasHotel1Habitacion2.add(new Reserva(LocalDate.of(2024, 2, 5), LocalDate.of(2024, 1, 6)));

        // Hotel 2
        reservasHotel2Habitacion1.add(new Reserva(LocalDate.of(2024, 3, 1), LocalDate.of(2024, 1, 2)));
        reservasHotel2Habitacion1.add(new Reserva(LocalDate.of(2024, 3, 2), LocalDate.of(2024, 1, 3)));
        reservasHotel2Habitacion1.add(new Reserva(LocalDate.of(2024, 3, 5), LocalDate.of(2024, 1, 6)));

        reservasHotel2Habitacion2.add(new Reserva(LocalDate.of(2024, 3, 1), LocalDate.of(2024, 1, 2)));
        reservasHotel2Habitacion2.add(new Reserva(LocalDate.of(2024, 3, 2), LocalDate.of(2024, 1, 3)));
        reservasHotel2Habitacion2.add(new Reserva(LocalDate.of(2024, 3, 5), LocalDate.of(2024, 1, 6)));

        diccionarioReservasHotel1.put(1, reservasHotel1Habitacion1);
        diccionarioReservasHotel1.put(2, reservasHotel1Habitacion2);
        diccionarioReservasHotel2.put(1, reservasHotel2Habitacion1);
        diccionarioReservasHotel2.put(2, reservasHotel2Habitacion2);

        hotel1.setReservas(diccionarioReservasHotel1);
        hotel2.setReservas(diccionarioReservasHotel2);

        diccionarioHoteles.put(1, hotel1);
        diccionarioHoteles.put(2, hotel2);
    }

    @GetMapping("/reservas/{hotelId}")
    public HashMap<Integer, List<Reserva>> getReservasHotel(@PathVariable int hotelId) {
        return diccionarioHoteles.get(hotelId).getReservas();
    }

    @GetMapping("/reservas/{hotelId}/{numeroHabitacion}")
    public List<Reserva> getReservasHotelHabitacion(@PathVariable int hotelId, @PathVariable int numeroHabitacion) {
        return diccionarioHoteles.get(hotelId).getReservas().get(numeroHabitacion);
    }

    @GetMapping("/disponibilidad/{hotelId}/{numeroHabitacion}/{fechaInicial}/{fechaFinal}")
    public List<LocalDate> getDisponibilidad(@PathVariable int hotelId, @PathVariable int numeroHabitacion, @PathVariable LocalDate fechaInicial, @PathVariable LocalDate fechaFinal) {
        List<Reserva> reservas = diccionarioHoteles.get(hotelId).getReservas().get(numeroHabitacion);
        List<LocalDate> disponibilidad = new ArrayList<LocalDate>();

        for (LocalDate fecha = fechaInicial; !fecha.isAfter(fechaFinal); fecha = fecha.plusDays(1)) {
            boolean disponible = true;
            for (Reserva reserva : reservas) {
                if ((fecha.isEqual(reserva.getFechaCheckIn()) || fecha.isAfter(reserva.getFechaCheckIn())) &&
                    fecha.isBefore(reserva.getFechaCheckOut())) {
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