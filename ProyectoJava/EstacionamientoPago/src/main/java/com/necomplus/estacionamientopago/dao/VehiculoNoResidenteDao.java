package com.necomplus.estacionamientopago.dao;

import com.necomplus.estacionamientopago.entities.VehiculoNoResidente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;


public class VehiculoNoResidenteDao {

    public void salvar(VehiculoNoResidente noResidente) {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")){
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(noResidente);
            em.getTransaction().commit();
        }
    }

    public VehiculoNoResidente buscarPorPlaca(String placa) {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")){
            EntityManager em = emf.createEntityManager();
            List<VehiculoNoResidente> vehiculos = em.createQuery("SELECT veh FROM VehiculoNoResidente veh WHERE veh.placa = '" + placa + "'", VehiculoNoResidente.class).getResultList();
            if(vehiculos != null && !vehiculos.isEmpty()){
                return vehiculos.get(0);
            }
        }

        return null;
    }

    public void reiniciar() {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")){
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM VehiculoNoResidente");
            query.executeUpdate();
            em.getTransaction().commit();
        }
    }
}
