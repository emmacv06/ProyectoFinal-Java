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
public class EspacioRecreativo {

    static Recreativo[] espacios = new Recreativo[7];
    static boolean precargado = false;

    public EspacioRecreativo() {
    }

    static void precargar() {
        espacios[0] = new Recreativo("Ping-pong", "Mesa 1", 2);
        espacios[1] = new Recreativo("Billar", "Mesa 2", 2);
        espacios[2] = new Recreativo("Fútbol", "Cancha 1", 12);
        espacios[3] = new Recreativo("Fútbol", "Cancha 2", 12);
        espacios[4] = new Recreativo("Baloncesto", "Cancha Principal", 10);
        espacios[5] = new Recreativo("Tenis", "Cancha 1", 2);
        espacios[6] = new Recreativo("Tenis", "Cancha 2", 2);
    }

    static class Recreativo {

        String tipo;
        String nombre;
        int capacidad;
        int disponibles;

        public Recreativo(String tipo, String nombre, int capacidad) {
            this.tipo = tipo;
            this.nombre = nombre;
            this.capacidad = capacidad;
            this.disponibles = capacidad;
        }

        public boolean reservar(int cantidad) {
            if (disponibles >= cantidad) {
                disponibles -= cantidad;
                return true;
            }
            return false;
        }

        public void liberar(int cantidad) {
            disponibles = Math.min(disponibles + cantidad, capacidad);
        }

        public String resumen() {
            return tipo + " - " + nombre + " | Cupos: " + disponibles;
        }

        public int reservados() {
            return capacidad - disponibles;
        }
    }

    public void menuEspacioRecreativo() {
        if (!precargado) {
            precargar();
            precargado = true;
        }

        String op;
        do {
            op = JOptionPane.showInputDialog("ESPACIOS RECREATIVOS\n1. Ver\n2. Reservar\n3. Volver");
            if (op == null || op.equals("3")) {
                break;
            }

            if (op.equals("1")) {
                mostrarEspacios();
            } else if (op.equals("2")) {
                reservar();
            } else {
                JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (true);
    }

    public void mostrarEspacios() {
        String msg = "Espacios disponibles:\n";
        for (int i = 0; i < espacios.length; i++) {
            msg += i + ". " + espacios[i].resumen() + "\n";
        }
        JOptionPane.showMessageDialog(null, msg);
    }

    public void mostrarReservas() {
        String mensaje = "ESPACIOS CON RESERVAS:\n\n";
        boolean hay = false;
        for (Recreativo r : espacios) {
            int res = r.reservados();
            if (res > 0) {
                mensaje += r.tipo + " - " + r.nombre + " | Reservados: " + res + "\n";
                hay = true;
            }
        }
        if (hay) {
            JOptionPane.showMessageDialog(null, mensaje);
        } else {
            JOptionPane.showMessageDialog(null, "No hay reservas realizadas.");
        }
    }

    public void reservar() {
        mostrarEspacios();
        try {
            String id = JOptionPane.showInputDialog("ID de socio:");
            int esp = Integer.parseInt(JOptionPane.showInputDialog("Espacio a reservar (número):"));
            int cant = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántas personas usarán el espacio?"));

            if (esp >= 0 && esp < espacios.length) {
                if (espacios[esp].reservar(cant)) {
                    JOptionPane.showMessageDialog(null, "Reserva hecha para socio " + id);
                } else {
                    JOptionPane.showMessageDialog(null, "No hay cupos suficientes.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Número de espacio inválido.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }

    public void liberarReserva() {
        mostrarEspacios();
        try {
            int esp = Integer.parseInt(JOptionPane.showInputDialog("Espacio a liberar (número):"));
            int cant = Integer.parseInt(JOptionPane.showInputDialog("¿Cuántos cupos desea liberar?"));

            if (esp >= 0 && esp < espacios.length) {
                espacios[esp].liberar(cant);
                JOptionPane.showMessageDialog(null, "Reserva liberada.");
            } else {
                JOptionPane.showMessageDialog(null, "Número inválido.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Entrada inválida.");
        }
    }
}

