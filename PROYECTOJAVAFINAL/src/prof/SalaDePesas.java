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
public class SalaDePesas {

    private String[] idsSocios = new String[75];
    private boolean[] reservas = new boolean[75];
    private int contadorReservas = 0;

    public void menuSala(Socio socio) {
        while (true) {
            String opcion = JOptionPane.showInputDialog(
                    "Sala de Pesas - Menú\n"
                    + "1. Reservar espacio\n"
                    + "2. Ver reservas actuales\n"
                    + "3. Liberar reserva\n"
                    + "4. Volver al menú principal"
            );
            if (opcion == null || opcion.equals("4")) {
                break;
            }

            switch (opcion) {
                case "1":
                    reservar(socio);
                    break;
                case "2":
                    mostrarReservasSala();
                    break;
                case "3":
                    liberarReserva();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    private void reservar(Socio socio) {
        if (contadorReservas >= idsSocios.length) {
            JOptionPane.showMessageDialog(null, "La sala de pesas está llena.");
            return;
        }
        idsSocios[contadorReservas] = socio.getId();
        reservas[contadorReservas] = true;
        contadorReservas++;
        JOptionPane.showMessageDialog(null, "Reserva exitosa para " + socio.getId());
    }

    public void mostrarReservasSala() {
        if (contadorReservas == 0) {
            JOptionPane.showMessageDialog(null, "No se han hecho reservas.");
            return;
        }
        StringBuilder sb = new StringBuilder("Reservas actuales en Sala de Pesas:\n");
        for (int i = 0; i < contadorReservas; i++) {
            sb.append("- ").append(idsSocios[i]).append("\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void liberarReserva() {
        if (contadorReservas == 0) {
            JOptionPane.showMessageDialog(null, "No hay reservas para liberar.");
            return;
        }
        String id = JOptionPane.showInputDialog("Ingrese ID para liberar reserva:");
        if (id == null) {
            return;
        }

        boolean encontrado = false;
        for (int i = 0; i < contadorReservas; i++) {
            if (idsSocios[i].equals(id)) {
                idsSocios[i] = idsSocios[contadorReservas - 1];
                reservas[i] = reservas[contadorReservas - 1];
                idsSocios[contadorReservas - 1] = null;
                reservas[contadorReservas - 1] = false;
                contadorReservas--;
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Reserva liberada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "ID no encontrado.");
        }
    }
}
