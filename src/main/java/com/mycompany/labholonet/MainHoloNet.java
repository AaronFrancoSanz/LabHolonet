package com.mycompany.Labholonet;

import org.hibernate.Session;
import java.util.List;
/**
 *
 * @author USUARIO
 */
public class MainHoloNet {

    public static void main(String[] args) {
        System.out.println("SISTEMA HOLONET AVANZADO");

        HoloNetControl control = new HoloNetControl();

        try {
            //INNER JOIN por bando (sin naves en Mantenimiento)
            String bando = "Imperio";
            System.out.println("\n 1. Sensor Táctico: naves del bando [" + bando + "]");

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                List<Nave> navesBando = control.obtenerNavesPorBandoPlaneta(session, bando);

                if (navesBando.isEmpty()) {
                    System.out.println("Sin naves activas para ese bando.");
                }
                for (Nave n : navesBando) {
                    System.out.println("   " + n);
                }
            } catch (Exception e) {
                System.err.println("Error en el Desafío 1: " + e.getMessage());
            }

            //JOIN FETCH. El planeta se lee DESPUÉS de cerrar la sesión

            System.out.println("\n2. Flota crítica con su planeta (JOIN FETCH)");

            List<Nave> flotaCritica;
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                flotaCritica = control.obtenerFlotaCriticaConPlaneta(session);
            } catch (Exception e) {
                System.err.println("Error en el Desafío 2: " + e.getMessage());
                flotaCritica = List.of();
            }

            //Sesión ya cerrada
            for (Nave n : flotaCritica) {
                Planeta p = n.getPlanetaOrigen();
                String origen = (p != null) ? p.getNombre() + " (" + p.getAfiliacion() + ")" : "Sin planeta asignado";
                System.out.println("   " + n.getNombre() + " -> " + origen);
            }

            //LEFT JOIN
            System.out.println("\n3. Reporte de alistamiento (todas las naves)");

            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                List<Object[]> reporte = control.obtenerReportePlanetasAsignados(session);

                System.out.println(String.format("   %-30s | %s", "NAVE", "PLANETA DE ORIGEN"));
                System.out.println("   " + "-".repeat(55));
                for (Object[] fila : reporte) {
                    String nave = (String) fila[0];
                    String planeta = (fila[1] != null) ? (String) fila[1] : "null";
                    System.out.println(String.format("   %-30s | %s", nave, planeta));
                }
            } catch (Exception e) {
                System.err.println("Error en el Desafío 3: " + e.getMessage());
            }

        } finally {
            HibernateUtil.shutdown();
        }
    }
}
