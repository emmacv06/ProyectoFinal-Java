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
public class SistemaGimnasio {

    static Socio socioActual = null;
    static Parqueo parqueo = new Parqueo();
    static Reserva reserva = new Reserva();
    static Auditorio auditorio = new Auditorio();
    static Cabina cabina = new Cabina();
    static SalaDePesas salaDePesas = new SalaDePesas();
    static ClasesGrupales clasesGrupales = new ClasesGrupales();
    static EspacioRecreativo espacioRecreativo = new EspacioRecreativo();

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Bienvenido al Sistema del Gimnasio");
        socioActual = Socio.registrarSocio();
        JOptionPane.showMessageDialog(null, "Usted es socio con ID: " + socioActual.getId());

        boolean salir = false;

        while (!salir) {
            String opcion = JOptionPane.showInputDialog(
                    "Menú principal:\n"
                    + "1. Parqueo\n"
                    + "2. Reservar Auditorio\n"
                    + "3. Cabinas\n"
                    + "4. Sala de Pesas\n"
                    + "5. Clases Grupales\n"
                    + "6. Espacios Recreativos\n"
                    + "7. Listado de Reservas\n"
                    + "8. Liberar Reserva\n"
                    + "9. Salir"
            );

            if (opcion == null) {
                break;
            }

            switch (opcion) {
                case "1":
                    parqueo.menuParqueo(socioActual);
                    break;
                case "2":
                    auditorio.menuAuditorio(socioActual);
                    break;
                case "3":
                    cabina.menuCabina(socioActual);
                    break;
                case "4":
                    salaDePesas.menuSala(socioActual);
                    break;
                case "5":
                    clasesGrupales.menuClases(socioActual);
                    break;
                case "6":
                    espacioRecreativo.menuEspacioRecreativo();
                    break;
                case "7":
                    mostrarListadoReservas();
                    break;
                case "8":
                    liberarReserva();
                    break;
                case "9":
                    salir = true;
                    JOptionPane.showMessageDialog(null, "Gracias por usar el sistema, vuelva pronto");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida, intente de nuevo");
            }
        }
    }

    private static void mostrarListadoReservas() {
        String opcion = JOptionPane.showInputDialog(
                "Seleccione el área que desea ver:\n"
                + "1. Ver parqueo\n"
                + "2. Ver sala de pesas\n"
                + "3. Ver auditorio\n"
                + "4. Ver cabina\n"
                + "5. Ver clases grupales\n"
                + "6. Ver espacios recreativos"
        );
        if (opcion == null) {
            return;
        }
        switch (opcion) {
            case "1":
                parqueo.mostrarParqueo();
                break;
            case "2":
                salaDePesas.mostrarReservasSala();
                break;
            case "3":
                auditorio.mostrarListaParticipantes();
                break;
            case "4":
                cabina.mostrarResumenReservas();
                break;
            case "5":
                clasesGrupales.mostrarClases();
                break;
            case "6":
                espacioRecreativo.mostrarReservas1();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida");
        }
    }

    private static void liberarReserva() {
        String area = JOptionPane.showInputDialog(
                "Seleccione área para liberar reserva:\n"
                + "1. Parqueo\n"
                + "2. Sala de Pesas\n"
                + "3. Auditorio\n"
                + "4. Cabina\n"
                + "5. Clases Grupales\n"
                + "6. Espacios Recreativos"
        );
        if (area == null) {
            return;
        }

        switch (area) {
            case "1":
                parqueo.liberarEspacio();
                break;
            case "2":
                salaDePesas.liberarReserva();
                break;
            case "3":
                auditorio.liberarParticipante();
                break;
            case "4":
                cabina.liberarReserva();
                break;
            case "5":
                clasesGrupales.liberarReserva();
                break;
            case "6":
                espacioRecreativo.liberarReserva();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida");
        }
    }
}
