package br.org.recode.tarefas.services;

import java.util.List;

import br.org.recode.tarefas.entities.Reserva;

public interface ReservaService {
    List<Reserva> getAllReservas();

    Reserva getReservaById(int id);

    Reserva saveReserva(Reserva reserva);

    Reserva updateReserva(int id, Reserva reservaAtualizada);

    void deleteReserva(int id);
}
