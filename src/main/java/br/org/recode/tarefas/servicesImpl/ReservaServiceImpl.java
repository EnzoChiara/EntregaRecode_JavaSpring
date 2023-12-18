package br.org.recode.tarefas.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.org.recode.tarefas.entities.Reserva;
import br.org.recode.tarefas.repositories.ReservaRepository;
import br.org.recode.tarefas.services.ReservaService;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Override
    public List<Reserva> getAllReservas() {
        return reservaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Reserva getReservaById(int id) {
        return reservaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Reserva saveReserva(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    @Override
    @Transactional
    public Reserva updateReserva(int id, Reserva reservaAtualizada) {
        Reserva reservaExistente = reservaRepository.findById(id).orElse(null);
        if (reservaExistente != null) {
            reservaExistente.setStatusReserva(reservaAtualizada.getStatusReserva());
            reservaExistente.setDataHoraReserva(reservaAtualizada.getDataHoraReserva());
            reservaExistente.setUsuario(reservaAtualizada.getUsuario());
            reservaExistente.setVoo(reservaAtualizada.getVoo());
            return reservaRepository.save(reservaExistente);
        } else {
            throw new RuntimeException("Reserva com o ID " + id + " não encontrada!");
        }
    }

    @Override
    @Transactional
    public void deleteReserva(int id) {
        reservaRepository.deleteById(id);
    }
}
