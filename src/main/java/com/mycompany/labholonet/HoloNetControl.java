package com.mycompany.Labholonet;

import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

/**
 *
 * @author USUARIO
 */

public class HoloNetControl {

    //INNER JOIN con filtros condicionales
    public List<Nave> obtenerNavesPorBandoPlaneta(Session session, String bando) {
        String hql = "SELECT n FROM Nave n "
                   + "INNER JOIN n.planetaOrigen p "
                   + "WHERE p.afiliacion = :bando "
                   + "AND n.estadoSistema <> 'Mantenimiento'";

        Query<Nave> query = session.createQuery(hql, Nave.class);
        query.setParameter("bando", bando);
        return query.getResultList();
    }

    //JOIN FETCH
    public List<Nave> obtenerFlotaCriticaConPlaneta(Session session) {
        // LEFT JOIN FETCH: no se pierden naves críticas sin planeta asignado
        String hql = "SELECT n FROM Nave n "
                   + "LEFT JOIN FETCH n.planetaOrigen "
                   + "WHERE n.estadoSistema = :estado";

        Query<Nave> query = session.createQuery(hql, Nave.class);
        query.setParameter("estado", "Critico");
        return query.getResultList();
    }

    //LEFT JOIN con proyección (nave, planeta)
    public List<Object[]> obtenerReportePlanetasAsignados(Session session) {
        String hql = "SELECT n.nombre, p.nombre FROM Nave n "
                   + "LEFT JOIN n.planetaOrigen p "
                   + "ORDER BY n.id";

        Query<Object[]> query = session.createQuery(hql, Object[].class);
        return query.getResultList();
    }
}
