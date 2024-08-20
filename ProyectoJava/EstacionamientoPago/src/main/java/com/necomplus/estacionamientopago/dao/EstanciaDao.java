package com.necomplus.estacionamientopago.dao;

import com.necomplus.estacionamientopago.entities.Estancia;
import com.necomplus.estacionamientopago.entities.VehiculoNoResidente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;

import java.util.List;

public class EstanciaDao {

    public void salvar(Estancia estancia) {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")){
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            em.merge(estancia);
            estancia.getVehiculo().setHoraEntrada(null);
            em.merge(estancia.getVehiculo());
            em.getTransaction().commit();
        }
    }

    public List<Estancia> buscarTodo() {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")){
            EntityManager em = emf.createEntityManager();
            List<Estancia> estancias = em.createQuery("SELECT est FROM Estancia est", Estancia.class).getResultList();
            return estancias;
        }
    }

    public void reiniciar() {
        try(EntityManagerFactory emf = Persistence.createEntityManagerFactory("my-persistence-unit")){
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("DELETE FROM Estancia");
            query.executeUpdate();
            em.getTransaction().commit();
        }
    }
}
