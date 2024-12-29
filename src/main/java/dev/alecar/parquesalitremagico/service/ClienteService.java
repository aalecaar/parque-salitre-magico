package dev.alecar.parquesalitremagico.service;

import dev.alecar.parquesalitremagico.model.Cliente;
import dev.alecar.parquesalitremagico.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    // Registra la primera visita de un cliente y guarda sus datos
    public Cliente registrarPrimeraVisita(Cliente cliente) {
        cliente.setPrimeraVisita(false);
        return clienteRepository.save(cliente);
    }
}