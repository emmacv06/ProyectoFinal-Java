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
public class Socio {

    private String id;
    private boolean activo;

    public Socio(String id) {
        this.id = id;
        this.activo = true; // por defecto activo
    }

    public String getId() {
        return id;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * Método para registrar un socio, pidiendo ID y validando entrada básica.
     */
    public static Socio registrarSocio() {
        while (true) {
            String id = JOptionPane.showInputDialog("Ingrese su ID de socio (ejemplo: S001):");
            if (id == null || id.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "ID inválido, intente de nuevo.");
                continue;
            }
            return new Socio(id.trim());
        }
    }
}
