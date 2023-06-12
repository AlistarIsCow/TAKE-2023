/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data;

import entities.Complaint;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaQuery;

@ApplicationScoped
public class ComplaintRepository {

    @Inject
    private EntityManager em;

    public void create(Complaint entity) {
        em.persist(entity);
    }

    public void edit(Complaint entity) {
        em.merge(entity);
    }

    public void remove(Complaint entity) {
        em.remove(em.merge(entity));
    }

    public Complaint find(Object id) {
        return em.find(Complaint.class, id);
    }

    public List<Complaint> findAll(String status) {
        if (status != null && !status.isEmpty()) {
            return em.createNamedQuery("Complaint.findByStatus")
                    .setParameter("status", status)
                    .getResultList();
        }
        
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Complaint.class));        
        return em.createQuery(cq).getResultList();
    }
}
