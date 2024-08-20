package com.necomplus.estacionamientopago.dao;

import com.necomplus.estacionamientopago.entities.VehiculoOficial;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class VehiculoOficialDao {

    public void salvar(VehiculoOficial oficial) {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")){
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(oficial);
            em.getTransaction().commit();
        }
    }

    public VehiculoOficial buscarPorPlaca(String placa) {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")){
            EntityManager em = emf.createEntityManager();
            List<VehiculoOficial> vehiculos = em.createQuery("SELECT veh FROM VehiculoOficial veh WHERE veh.placa = '" + placa + "'", VehiculoOficial.class).getResultList();
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
            Query query = em.createQuery("DELETE FROM VehiculoOficial");
            query.executeUpdate();
            em.getTransaction().commit();
        }
    }
}
