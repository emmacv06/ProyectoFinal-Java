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
public class Parqueo {

    private char[][] planta1 = new char[4][5]; // 4x5
    private char[][] planta2 = new char[5][5]; // 5x5
    private char[][] planta3 = new char[6][5]; // 6x5

    public Parqueo() {
        inicializarPlantas();
    }

    /**
     * Inicializa los parqueos con 'L' (libre), 'D' (discapacitado) y 'E'
     * (entrenador senior)
     */
    private void inicializarPlantas() {
        inicializarPlanta(planta1, 3, 2); // 3 discapacitados, 2 entrenadores senior
        inicializarPlanta(planta2, 3, 2);
        inicializarPlanta(planta3, 3, 2);
    }

    private void inicializarPlanta(char[][] planta, int discapacitados, int entrenadores) {
        for (int i = 0; i < planta.length; i++) {
            for (int j = 0; j < planta[0].length; j++) {
                planta[i][j] = 'L';
            }
        }
        // asignar espacios para discapacitados al inicio de la fila 0
        for (int i = 0; i < discapacitados; i++) {
            planta[0][i] = 'D';
        }
        // asignar espacios para entrenadores senior al final de la fila 0
        for (int i = 0; i < entrenadores; i++) {
            planta[0][planta[0].length - 1 - i] = 'E';
        }
    }

    /**
     * Menú para opciones de parqueo.
     */
    public void menuParqueo(Socio socio) {
        String opcion;
        do {
            opcion = JOptionPane.showInputDialog(
                    "Parqueo - Menú\n"
                    + "1. Ver estado del parqueo\n"
                    + "2. Reservar espacio\n"
                    + "3. Volver al menú principal"
            );
            if (opcion == null || opcion.equals("3")) {
                break;
            }
            switch (opcion) {
                case "1":
                    mostrarParqueo();
                    break;
                case "2":
                    reservarEspacio(socio);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        } while (true);
    }

    /**
     * Muestra el estado de todas las plantas del parqueo.
     */
    public void mostrarParqueo() {
        String mensaje = "Estado del parqueo:\n\n";
        mensaje += "Planta 1:\n" + mostrarPlanta(planta1);
        mensaje += "Planta 2:\n" + mostrarPlanta(planta2);
        mensaje += "Planta 3:\n" + mostrarPlanta(planta3);

        JOptionPane.showMessageDialog(null, mensaje);
    }

    private String mostrarPlanta(char[][] planta) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < planta.length; i++) {
            for (int j = 0; j < planta[0].length; j++) {
                sb.append("[").append(planta[i][j]).append("]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    /**
     * Permite reservar un espacio si está libre y el socio es válido.
     */
    public void reservarEspacio(Socio socio) {
        if (!socio.isActivo()) {
            JOptionPane.showMessageDialog(null, "Su membresía no está activa.");
            return;
        }

        int plantaSeleccionada = -1;
        while (true) {
            String input = JOptionPane.showInputDialog(
                    "Ingrese la planta para reservar espacio (1, 2 o 3):"
            );
            if (input == null) {
                return;
            }

            try {
                plantaSeleccionada = Integer.parseInt(input);
                if (plantaSeleccionada < 1 || plantaSeleccionada > 3) {
                    JOptionPane.showMessageDialog(null, "Planta inválida, intente de nuevo.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida, ingrese un número.");
            }
        }

        char[][] planta = plantaSeleccionada == 1 ? planta1
                : plantaSeleccionada == 2 ? planta2 : planta3;

        for (int i = 0; i < planta.length; i++) {
            for (int j = 0; j < planta[0].length; j++) {
                if (planta[i][j] == 'L') {
                    planta[i][j] = 'O'; // O = Ocupado
                    JOptionPane.showMessageDialog(null, "Espacio reservado en planta " + plantaSeleccionada + " posición [" + i + "," + j + "]");
                    return;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "No hay espacios libres en esta planta.");
    }

    /**
     * Permite liberar un espacio ocupado en alguna planta.
     */
    public void liberarEspacio() {
        int plantaSeleccionada = -1;
        while (true) {
            String input = JOptionPane.showInputDialog("Ingrese la planta (1, 2 o 3) para liberar espacio:");
            if (input == null) {
                return;
            }
            try {
                plantaSeleccionada = Integer.parseInt(input);
                if (plantaSeleccionada < 1 || plantaSeleccionada > 3) {
                    JOptionPane.showMessageDialog(null, "Planta inválida, intente de nuevo.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida, ingrese un número.");
            }
        }

        char[][] planta = plantaSeleccionada == 1 ? planta1
                : plantaSeleccionada == 2 ? planta2 : planta3;

        String posicionInput = JOptionPane.showInputDialog("Ingrese la posición a liberar (fila,columna) ej: 0,3");
        if (posicionInput == null) {
            return;
        }

        try {
            String[] parts = posicionInput.split(",");
            int fila = Integer.parseInt(parts[0].trim());
            int columna = Integer.parseInt(parts[1].trim());

            if (fila < 0 || fila >= planta.length || columna < 0 || columna >= planta[0].length) {
                JOptionPane.showMessageDialog(null, "Posición inválida.");
                return;
            }

            if (planta[fila][columna] == 'O') {
                planta[fila][columna] = 'L';
                JOptionPane.showMessageDialog(null, "Espacio liberado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "El espacio no está ocupado o no puede ser liberado.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Formato inválido.");
        }
    }
}
