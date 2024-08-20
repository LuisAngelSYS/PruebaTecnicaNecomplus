package com.necomplus.estacionamientopago.dao;

import com.necomplus.estacionamientopago.entities.VehiculoResidente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import java.util.List;

public class VehiculoResidenteDao {

    public void salvar(VehiculoResidente residente) {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")){
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(residente);
            em.getTransaction().commit();
        }
    }

    public VehiculoResidente buscarPorPlaca(String placa) {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")){
            EntityManager em = emf.createEntityManager();
            List<VehiculoResidente> vehiculos = em.createQuery("SELECT veh FROM VehiculoResidente veh WHERE veh.placa = '" + placa + "'", VehiculoResidente.class).getResultList();
            if(vehiculos != null && !vehiculos.isEmpty()){
                return vehiculos.get(0);
            }
        }

        return null;
    }

    public List<VehiculoResidente> buscarResidentesConPagoPendiente() {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")){
            EntityManager em = emf.createEntityManager();
            return em.createQuery("SELECT veh FROM VehiculoResidente veh WHERE veh.minutosAcumulados > 0", VehiculoResidente.class).getResultList();
        }
    }


    public void reiniciarTiempos() {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")){
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("Update VehiculoResidente veh SET veh.minutosAcumulados = 0, veh.horaEntrada = null");
            query.executeUpdate();
            em.getTransaction().commit();
        }
    }

    public void reiniciar() {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")){
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM VehiculoResidente");
            query.executeUpdate();
            em.getTransaction().commit();
        }
    }
}
