package com.distribuida.servicios;

import com.distribuida.db.Persona;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class ServicioPersonaImpl implements ServicioPersona {
    @Inject
    EntityManager em;

    @Override
    public List<Persona> findAll() {
        return em.createQuery("select o from Persona o")
                .getResultList();
    }

    public Persona findById(Integer id) {
        return em.find(Persona.class, id);
    }

    @Override
    public void remove(Integer id) {
        var tx = em.getTransaction();

        try {
            tx.begin();
            var obj = em.getReference(Persona.class, id);
            em.remove(obj);
            tx.commit();
        }
        catch(Exception ex) {
            tx.rollback();
        }
    }

    public void insert(Persona obj) {
        var tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(obj);
            tx.commit();
        }
        catch(Exception ex) {
            tx.rollback();
        }
    }
}
