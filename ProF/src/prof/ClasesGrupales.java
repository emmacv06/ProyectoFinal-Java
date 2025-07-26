/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prof;

import javax.swing.JOptionPane;

/**
 *
 * @author emmac
 */
public class ClasesGrupales {

    private static ClaseGrupal[] clases = new ClaseGrupal[6]; // 3 mañana, 3 noche
    private static boolean precargado = false;

    static class ClaseGrupal {

        String nombre;
        String horario;
        int capacidadMaxima;
        int cuposDisponibles;

        public ClaseGrupal(String nombre, String horario, int capacidadMaxima) {
            this.nombre = nombre;
            this.horario = horario;
            this.capacidadMaxima = capacidadMaxima;
            this.cuposDisponibles = capacidadMaxima;
        }

        public boolean reservar() {
            if (cuposDisponibles > 0) {
                cuposDisponibles--;
                return true;
            }
            return false;
        }

        public void modificar(String nuevoNombre, String nuevoHorario, int nuevaCapacidad) {
            int diferencia = nuevaCapacidad - this.capacidadMaxima;
            this.nombre = nuevoNombre;
            this.horario = nuevoHorario;
            this.capacidadMaxima = nuevaCapacidad;
            this.cuposDisponibles += diferencia;
        }

        public String resumen() {
            return nombre + " (" + horario + ") - Cupos disponibles: " + cuposDisponibles;
        }
    }

    public void menuClases(Socio socio) {
        if (!precargado) {
            precargarClases();
            precargado = true;
        }
        while (true) {
            String opcion = JOptionPane.showInputDialog(
                    "Clases Grupales - Menú\n"
                    + "1. Ver clases disponibles\n"
                    + "2. Reservar clase\n"
                    + "3. Agregar/Modificar clase\n"
                    + "4. Volver al menú principal"
            );
            if (opcion == null || opcion.equals("4")) {
                break;
            }

            switch (opcion) {
                case "1":
                    mostrarClases();
                    break;
                case "2":
                    reservarClase(socio);
                    break;
                case "3":
                    modificarClase();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    private void precargarClases() {
        clases[0] = new ClaseGrupal("Yoga", "mañana", 10);
        clases[1] = new ClaseGrupal("Crossfit", "mañana", 10);
        clases[2] = new ClaseGrupal("Funcional", "mañana", 10);
        clases[3] = new ClaseGrupal("Pilates", "noche", 10);
        clases[4] = new ClaseGrupal("Zumba", "noche", 10);
        clases[5] = new ClaseGrupal("Cardio Dance", "noche", 10);
    }

    public void mostrarClases() {
        StringBuilder mensaje = new StringBuilder("CLASES DISPONIBLES:\n");
        for (int i = 0; i < clases.length; i++) {
            mensaje.append(i).append(". ").append(clases[i].resumen()).append("\n");
        }
        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    private void reservarClase(Socio socio) {
        mostrarClases();
        String input = JOptionPane.showInputDialog("Ingrese el número de la clase a reservar:");
        if (input == null) {
            return;
        }
        int opcion = -1;
        try {
            opcion = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida");
            return;
        }

        if (opcion >= 0 && opcion < clases.length) {
            if (clases[opcion].reservar()) {
                JOptionPane.showMessageDialog(null, "Reserva exitosa para " + socio.getId());
            } else {
                JOptionPane.showMessageDialog(null, "No hay cupos disponibles.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Número de clase inválido.");
        }
    }

    private void modificarClase() {
        mostrarClases();
        String input = JOptionPane.showInputDialog("Ingrese el número de la clase a modificar:");
        if (input == null) {
            return;
        }

        int opcion = -1;
        try {
            opcion = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida");
            return;
        }

        if (opcion >= 0 && opcion < clases.length) {
            String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre:");
            String nuevoHorario = JOptionPane.showInputDialog("Nuevo horario (mañana/noche):");
            String capacidadStr = JOptionPane.showInputDialog("Nueva capacidad máxima:");
            if (nuevoNombre == null || nuevoHorario == null || capacidadStr == null) {
                return;
            }

            int nuevaCapacidad = 0;
            try {
                nuevaCapacidad = Integer.parseInt(capacidadStr);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Capacidad inválida");
                return;
            }

            clases[opcion].modificar(nuevoNombre, nuevoHorario, nuevaCapacidad);
            JOptionPane.showMessageDialog(null, "Clase modificada exitosamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Número de clase inválido.");
        }
    }

    public void liberarReserva() {
        String input = JOptionPane.showInputDialog("Ingrese el número de la clase para liberar reserva:");
        if (input == null) {
            return;
        }
        int opcion = -1;
        try {
            opcion = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida");
            return;
        }

        if (opcion >= 0 && opcion < clases.length) {
            if (clases[opcion].cuposDisponibles < clases[opcion].capacidadMaxima) {
                clases[opcion].cuposDisponibles++;
                JOptionPane.showMessageDialog(null, "Reserva liberada exitosamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No hay reservas para liberar en esa clase.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Número de clase inválido.");
        }
    }
}
