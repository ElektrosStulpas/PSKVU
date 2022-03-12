package persistence;

import entities.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class CallsDAO {

    @Inject
    private EntityManager em;

    public void persist(Employee employee){
        this.em.persist(employee);
    }

    public Employee findOne(Integer id){
        return em.find(Employee.class, id);
    }

    public Employee update(Employee employee){
        return em.merge(employee);
    }
}