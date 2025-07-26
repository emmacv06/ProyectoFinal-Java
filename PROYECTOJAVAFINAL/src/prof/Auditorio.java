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
public class Auditorio {

    private String[] participantes10am = new String[30];
    private String[] participantes3pm = new String[30];
    private int contador10am = 0;
    private int contador3pm = 0;

    public void menuAuditorio(Socio socio) {
        while (true) {
            String opcion = JOptionPane.showInputDialog(
                    "Auditorio - Menú\n"
                    + "1. Inscribir participante\n"
                    + "2. Mostrar lista de inscritos\n"
                    + "3. Volver al menú principal"
            );
            if (opcion == null || opcion.equals("3")) {
                break;
            }

            switch (opcion) {
                case "1":
                    inscribir(socio);
                    break;
                case "2":
                    mostrarListaParticipantes();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida");
            }
        }
    }

    private void inscribir(Socio socio) {
        String horario = JOptionPane.showInputDialog("Ingrese horario (10am o 3pm):");
        if (horario == null) {
            return;
        }

        horario = horario.trim().toLowerCase();
        if (!horario.equals("10am") && !horario.equals("3pm")) {
            JOptionPane.showMessageDialog(null, "Horario inválido");
            return;
        }

        if (horario.equals("10am")) {
            if (contador10am < participantes10am.length) {
                participantes10am[contador10am++] = socio.getId();
                JOptionPane.showMessageDialog(null, "Inscripción exitosa para " + socio.getId());
            } else {
                JOptionPane.showMessageDialog(null, "Aforo lleno para 10am");
            }
        } else {
            if (contador3pm < participantes3pm.length) {
                participantes3pm[contador3pm++] = socio.getId();
                JOptionPane.showMessageDialog(null, "Inscripción exitosa para " + socio.getId());
            } else {
                JOptionPane.showMessageDialog(null, "Aforo lleno para 3pm");
            }
        }
    }

    public void mostrarListaParticipantes() {
        StringBuilder sb = new StringBuilder("Participantes 10am:\n");
        if (contador10am == 0) {
            sb.append("No hay inscritos.\n");
        } else {
            for (int i = 0; i < contador10am; i++) {
                sb.append("- ").append(participantes10am[i]).append("\n");
            }
        }
        sb.append("\nParticipantes 3pm:\n");
        if (contador3pm == 0) {
            sb.append("No hay inscritos.\n");
        } else {
            for (int i = 0; i < contador3pm; i++) {
                sb.append("- ").append(participantes3pm[i]).append("\n");
            }
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void liberarParticipante() {
        String horario = JOptionPane.showInputDialog("Ingrese horario para liberar (10am o 3pm):");
        if (horario == null) {
            return;
        }

        horario = horario.trim().toLowerCase();
        String id = JOptionPane.showInputDialog("Ingrese ID para liberar:");
        if (id == null) {
            return;
        }
        boolean encontrado = false;

        if (horario.equals("10am")) {
            for (int i = 0; i < contador10am; i++) {
                if (participantes10am[i].equals(id)) {
                    participantes10am[i] = participantes10am[contador10am - 1];
                    participantes10am[contador10am - 1] = null;
                    contador10am--;
                    encontrado = true;
                    break;
                }
            }
        } else if (horario.equals("3pm")) {
            for (int i = 0; i < contador3pm; i++) {
                if (participantes3pm[i].equals(id)) {
                    participantes3pm[i] = participantes3pm[contador3pm - 1];
                    participantes3pm[contador3pm - 1] = null;
                    contador3pm--;
                    encontrado = true;
                    break;
                }
            }
        }

        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Participante liberado correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "ID no encontrado.");
        }
    }
}
