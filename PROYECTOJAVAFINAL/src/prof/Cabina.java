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
public class Cabina {

    int numero = 1; // número de cabina, puede ampliarse
    private String[] horas = {"9am", "10am", "11am", "12pm", "1pm", "2pm", "3pm", "4pm", "5pm", "6pm"};
    private String[] reservas = new String[horas.length];

    public Cabina() {
        for (int i = 0; i < reservas.length; i++) {
            reservas[i] = "";
        }
    }

    public void menuCabina(Socio socio) {
        while (true) {
            String opcion = JOptionPane.showInputDialog(
                    "Cabina - Menú\n"
                    + "1. Ver horarios disponibles\n"
                    + "2. Reservar horario\n"
                    + "3. Volver al menú principal"
            );
            if (opcion == null || opcion.equals("3")) {
                break;
            }

            switch (opcion) {
                case "1":
                    mostrarHorarios();
                    break;
                case "2":
                    reservarHorario(socio);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    private void mostrarHorarios() {
        StringBuilder sb = new StringBuilder("Horarios de Cabina " + numero + ":\n");
        int disponibles = 0;
        for (int i = 0; i < horas.length; i++) {
            if (reservas[i].isEmpty()) {
                sb.append(horas[i]).append(" - LIBRE\n");
                disponibles++;
            } else {
                sb.append(horas[i]).append(" - RESERVADO por ").append(reservas[i]).append("\n");
            }
        }
        sb.append("\nTotal horarios libres: ").append(disponibles);
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void reservarHorario(Socio socio) {
        String hora = JOptionPane.showInputDialog("Ingrese hora a reservar (ejemplo: 9am, 2pm):");
        if (hora == null) {
            return;
        }
        hora = hora.trim().toLowerCase();

        int pos = -1;
        for (int i = 0; i < horas.length; i++) {
            if (horas[i].equalsIgnoreCase(hora)) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "Hora inválida.");
            return;
        }

        if (reservas[pos].isEmpty()) {
            reservas[pos] = socio.getId();
            JOptionPane.showMessageDialog(null, "Reserva exitosa para " + socio.getId() + " a las " + horas[pos]);
        } else {
            JOptionPane.showMessageDialog(null, "Hora ya reservada.");
        }
    }

    public void mostrarResumenReservas() {
        StringBuilder sb = new StringBuilder("Resumen de reservas cabina " + numero + ":\n");
        boolean alguna = false;
        for (int i = 0; i < horas.length; i++) {
            if (!reservas[i].isEmpty()) {
                sb.append(horas[i]).append(" - ").append(reservas[i]).append("\n");
                alguna = true;
            }
        }
        if (!alguna) {
            sb.append("No hay reservas.\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void liberarReserva() {
        String hora = JOptionPane.showInputDialog("Ingrese la hora para liberar reserva (ejemplo: 9am):");
        if (hora == null) {
            return;
        }
        hora = hora.trim().toLowerCase();

        int pos = -1;
        for (int i = 0; i < horas.length; i++) {
            if (horas[i].equalsIgnoreCase(hora)) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "Hora inválida.");
            return;
        }

        if (!reservas[pos].isEmpty()) {
            reservas[pos] = "";
            JOptionPane.showMessageDialog(null, "Reserva liberada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "No hay reserva en esa hora.");
        }
    }
}
