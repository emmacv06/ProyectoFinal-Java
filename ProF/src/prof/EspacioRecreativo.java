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

    private static String[] horarios = {"9am", "10am", "11am", "12pm", "1pm", "2pm"};
    private static String[] reservas = new String[horarios.length];

    public EspacioRecreativo() {
        for (int i = 0; i < reservas.length; i++) {
            reservas[i] = "";
        }
    }

    public void menuEspacioRecreativo() {
        while (true) {
            String opcion = JOptionPane.showInputDialog(
                    "Espacio Recreativo - Menú\n"
                    + "1. Ver horarios\n"
                    + "2. Reservar horario\n"
                    + "3. Volver al menú principal"
            );
            if (opcion == null || opcion.equals("3")) {
                break;
            }

            switch (opcion) {
                case "1":
                    mostrarReservas();
                    break;
                case "2":
                    reservarHorario();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    private void mostrarReservas() {
        StringBuilder sb = new StringBuilder("Horarios de Espacio Recreativo:\n");
        int disponibles = 0;
        for (int i = 0; i < horarios.length; i++) {
            if (reservas[i].isEmpty()) {
                sb.append(horarios[i]).append(" - LIBRE\n");
                disponibles++;
            } else {
                sb.append(horarios[i]).append(" - RESERVADO por ").append(reservas[i]).append("\n");
            }
        }
        sb.append("\nTotal horarios libres: ").append(disponibles);
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void reservarHorario() {
        String hora = JOptionPane.showInputDialog("Ingrese hora a reservar (ejemplo: 9am):");
        if (hora == null) {
            return;
        }
        hora = hora.trim().toLowerCase();

        int pos = -1;
        for (int i = 0; i < horarios.length; i++) {
            if (horarios[i].equalsIgnoreCase(hora)) {
                pos = i;
                break;
            }
        }

        if (pos == -1) {
            JOptionPane.showMessageDialog(null, "Hora inválida.");
            return;
        }

        if (reservas[pos].isEmpty()) {
            reservas[pos] = "Reservado";
            JOptionPane.showMessageDialog(null, "Reserva exitosa a las " + horarios[pos]);
        } else {
            JOptionPane.showMessageDialog(null, "Hora ya reservada.");
        }
    }

    public void mostrarReservas1() {
        StringBuilder sb = new StringBuilder("Reservas Espacio Recreativo:\n");
        boolean alguna = false;
        for (int i = 0; i < horarios.length; i++) {
            if (!reservas[i].isEmpty()) {
                sb.append(horarios[i]).append(" - ").append(reservas[i]).append("\n");
                alguna = true;
            }
        }
        if (!alguna) {
            sb.append("No hay reservas.\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void liberarReserva() {
        String hora = JOptionPane.showInputDialog("Ingrese hora para liberar reserva:");
        if (hora == null) {
            return;
        }
        hora = hora.trim().toLowerCase();

        int pos = -1;
        for (int i = 0; i < horarios.length; i++) {
            if (horarios[i].equalsIgnoreCase(hora)) {
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
