package dev.alecar.parquesalitremagico;

import dev.alecar.parquesalitremagico.model.*;
import dev.alecar.parquesalitremagico.service.AtraccionService;
import dev.alecar.parquesalitremagico.service.ClienteService;
import dev.alecar.parquesalitremagico.service.EmpleadoService;
import dev.alecar.parquesalitremagico.service.EstacionService;
import dev.alecar.parquesalitremagico.service.ParqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private AtraccionService atraccionService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private EstacionService estacionService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ParqueService parqueService;

    @Override
    public void run(String... args) throws Exception {
        cargarDatosIniciales();
    }

    private void cargarDatosIniciales() {
        // Crear 5 estaciones
        if (estacionService.getAllEstaciones().isEmpty()) {
            for (int i = 1; i <= 5; i++) {
                Estacion estacion = new Estacion();
                estacion.setNombre("Estación " + i);
                estacion.setHabilitada(true);
                estacionService.saveEstacion(estacion);
            }
        }

        // Crear empleados de prueba para cada rol
        Empleado empleado1 = new Empleado();
        empleado1.setNombre("Juan Pérez");
        empleado1.setCedula("123456789");
        empleado1.setTelefono("3001234567");
        empleado1.setCorreo("juan@parque.com");
        empleado1.setCargo(Cargo.ADMINISTRATIVO.toString());
        empleado1.setHorario("8:00 - 17:00");
        empleadoService.saveEmpleado(empleado1);

        Empleado empleado2 = new Empleado();
        empleado2.setNombre("María López");
        empleado2.setCedula("987654321");
        empleado2.setTelefono("3007654321");
        empleado2.setCorreo("maria@parque.com");
        empleado2.setCargo(Cargo.LOGISTICA.name());
        empleado2.setHorario("9:00 - 18:00");
        empleadoService.saveEmpleado(empleado2);

        Empleado empleado3 = new Empleado();
        empleado3.setNombre("Carlos Rodríguez");
        empleado3.setCedula("456789123");
        empleado3.setTelefono("3009876543");
        empleado3.setCorreo("carlos@parque.com");
        empleado3.setCargo(Cargo.PUBLICIDAD.name());
        empleado3.setHorario("10:00 - 19:00");
        empleadoService.saveEmpleado(empleado3);

        Empleado empleado4 = new Empleado();
        empleado4.setNombre("Ana Martínez");
        empleado4.setCedula("789123456");
        empleado4.setTelefono("3002345678");
        empleado4.setCorreo("ana@parque.com");
        empleado4.setCargo(Cargo.OPERADOR.name());
        empleado4.setHorario("7:00 - 16:00");
        empleadoService.saveEmpleado(empleado4);

        Empleado empleado5 = new Empleado();
        empleado5.setNombre("Luis González");
        empleado5.setCedula("321654987");
        empleado5.setTelefono("3005678901");
        empleado5.setCorreo("luis@parque.com");
        empleado5.setCargo(Cargo.MANTENIMIENTO.name());
        empleado5.setHorario("6:00 - 15:00");
        empleadoService.saveEmpleado(empleado5);

        // Crear algunas atracciones
        if (atraccionService.getAllAtracciones().isEmpty()) {
            Atraccion atraccion1 = new Atraccion();
            atraccion1.setNombre("Montaña Rusa");
            atraccion1.setDescripcion("Una emocionante montaña rusa");
            atraccion1.setClasificacion("Familiar");
            atraccion1.setCondicionesUso("No apto para cardiacos");
            atraccion1.setEstaturaMinima(120);
            atraccion1.setDisponible(true);
            atraccionService.saveAtraccion(atraccion1);

            Atraccion atraccion2 = new Atraccion();
            atraccion2.setNombre("Carrusel");
            atraccion2.setDescripcion("Un carrusel clásico");
            atraccion2.setClasificacion("Infantil");
            atraccion2.setCondicionesUso("Apto para todas las edades");
            atraccion2.setEstaturaMinima(80);
            atraccion2.setDisponible(true);
            atraccionService.saveAtraccion(atraccion2);

            Atraccion atraccion3 = new Atraccion();
            atraccion3.setNombre("Sillas Voladoras");
            atraccion3.setDescripcion("Sillas voladoras para toda la familia");
            atraccion3.setClasificacion("Familiar");
            atraccion3.setCondicionesUso("No apto para personas con vértigo");
            atraccion3.setEstaturaMinima(100);
            atraccion3.setDisponible(false);
            atraccionService.saveAtraccion(atraccion3);
        }

        // Crear algunos clientes
        if (clienteService.getAllClientes().isEmpty()) {
            // Obtener las estaciones creadas
            List<Estacion> estaciones = estacionService.getAllEstaciones();

            // Cliente 1 (menor de edad)
            Cliente cliente1 = new Cliente();
            cliente1.setNombre("Santiago Ramírez");
            cliente1.setCedula("1007845962");
            cliente1.setTelefono("2854697");
            cliente1.setCorreo("santiago.ramirez@gmail.com");
            cliente1.setEdad(15);
            cliente1.setEstatura(165);
            cliente1.setFamiliarContacto("Martha Gómez");
            cliente1.setPrimeraVisita(true);
            cliente1.setEstacionRegistro(estaciones.get(0));
            clienteService.saveCliente(cliente1);
            parqueService.registrarEntradaCliente(cliente1);

            // Cliente 2 (menor de edad)
            Cliente cliente2 = new Cliente();
            cliente2.setNombre("Valentina Torres");
            cliente2.setCedula("1006789432");
            cliente2.setTelefono("2789654");
            cliente2.setCorreo("vale.torres@hotmail.com");
            cliente2.setEdad(12);
            cliente2.setEstatura(145);
            cliente2.setFamiliarContacto("Carlos Torres");
            cliente2.setPrimeraVisita(true);
            cliente2.setEstacionRegistro(estaciones.get(1));
            clienteService.saveCliente(cliente2);
            parqueService.registrarEntradaCliente(cliente2);

            // Cliente 3 (mayor de edad)
            Cliente cliente3 = new Cliente();
            cliente3.setNombre("Andrés Martínez");
            cliente3.setCedula("1004567891");
            cliente3.setTelefono("2456789");
            cliente3.setCorreo("andres.martinez@outlook.com");
            cliente3.setEdad(28);
            cliente3.setEstatura(175);
            cliente3.setFamiliarContacto("No Aplica");
            cliente3.setPrimeraVisita(false);
            cliente3.setEstacionRegistro(estaciones.get(2));
            clienteService.saveCliente(cliente3);
            parqueService.registrarEntradaCliente(cliente3);

            // Cliente 4 (mayor de edad)
            Cliente cliente4 = new Cliente();
            cliente4.setNombre("Carolina López");
            cliente4.setCedula("1003456789");
            cliente4.setTelefono("2345678");
            cliente4.setCorreo("caro.lopez@gmail.com");
            cliente4.setEdad(25);
            cliente4.setEstatura(160);
            cliente4.setFamiliarContacto("No Aplica");
            cliente4.setPrimeraVisita(true);
            cliente4.setEstacionRegistro(estaciones.get(3));
            clienteService.saveCliente(cliente4);
            parqueService.registrarEntradaCliente(cliente4);

            // Cliente 5 (menor de edad)
            Cliente cliente5 = new Cliente();
            cliente5.setNombre("Juan Pablo Herrera");
            cliente5.setCedula("1005678943");
            cliente5.setTelefono("2567891");
            cliente5.setCorreo("juanpa.herrera@gmail.com");
            cliente5.setEdad(14);
            cliente5.setEstatura(155);
            cliente5.setFamiliarContacto("Ana María Herrera");
            cliente5.setPrimeraVisita(true);
            cliente5.setEstacionRegistro(estaciones.get(4));
            clienteService.saveCliente(cliente5);
            parqueService.registrarEntradaCliente(cliente5);

            // Cliente 6 (mayor de edad)
            Cliente cliente6 = new Cliente();
            cliente6.setNombre("María Fernanda Sánchez");
            cliente6.setCedula("1002345678");
            cliente6.setTelefono("2234567");
            cliente6.setCorreo("mafe.sanchez@hotmail.com");
            cliente6.setEdad(32);
            cliente6.setEstatura(168);
            cliente6.setFamiliarContacto("No Aplica");
            cliente6.setPrimeraVisita(false);
            cliente6.setEstacionRegistro(estaciones.get(0));
            clienteService.saveCliente(cliente6);
            parqueService.registrarEntradaCliente(cliente6);

            // Cliente 7 (menor de edad)
            Cliente cliente7 = new Cliente();
            cliente7.setNombre("Daniel Ospina");
            cliente7.setCedula("1008912345");
            cliente7.setTelefono("2678912");
            cliente7.setCorreo("daniel.ospina@gmail.com");
            cliente7.setEdad(16);
            cliente7.setEstatura(170);
            cliente7.setFamiliarContacto("Patricia Mendoza");
            cliente7.setPrimeraVisita(true);
            cliente7.setEstacionRegistro(estaciones.get(1));
            clienteService.saveCliente(cliente7);
            parqueService.registrarEntradaCliente(cliente7);

            // Cliente 8 (mayor de edad)
            Cliente cliente8 = new Cliente();
            cliente8.setNombre("Laura Jiménez");
            cliente8.setCedula("1001234567");
            cliente8.setTelefono("2123456");
            cliente8.setCorreo("laura.jimenez@outlook.com");
            cliente8.setEdad(29);
            cliente8.setEstatura(165);
            cliente8.setFamiliarContacto("No Aplica");
            cliente8.setPrimeraVisita(true);
            cliente8.setEstacionRegistro(estaciones.get(2));
            clienteService.saveCliente(cliente8);
            parqueService.registrarEntradaCliente(cliente8);

            // Cliente 9 (menor de edad)
            Cliente cliente9 = new Cliente();
            cliente9.setNombre("Samuel Duarte");
            cliente9.setCedula("1009876543");
            cliente9.setTelefono("2789123");
            cliente9.setCorreo("samuel.duarte@gmail.com");
            cliente9.setEdad(13);
            cliente9.setEstatura(150);
            cliente9.setFamiliarContacto("Ricardo Duarte");
            cliente9.setPrimeraVisita(false);
            cliente9.setEstacionRegistro(estaciones.get(3));
            clienteService.saveCliente(cliente9);
            parqueService.registrarEntradaCliente(cliente9);

            // Cliente 10 (mayor de edad)
            Cliente cliente10 = new Cliente();
            cliente10.setNombre("Isabella Vargas");
            cliente10.setCedula("1000123456");
            cliente10.setTelefono("2901234");
            cliente10.setCorreo("isabella.vargas@gmail.com");
            cliente10.setEdad(23);
            cliente10.setEstatura(162);
            cliente10.setFamiliarContacto("No Aplica");
            cliente10.setPrimeraVisita(true);
            cliente10.setEstacionRegistro(estaciones.get(4));
            clienteService.saveCliente(cliente10);
            parqueService.registrarEntradaCliente(cliente10);
        }
    }
}