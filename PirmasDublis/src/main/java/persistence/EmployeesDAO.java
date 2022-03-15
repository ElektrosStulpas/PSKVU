package persistence;

import entities.Employee;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class EmployeesDAO {

    @Inject
    private EntityManager em;

    public void persist(Employee employee) { this.em.persist(employee); }

    public List<Employee> findAll()
    {
        return em.createQuery("SELECT e FROM Employee as e", Employee.class)
                .getResultList();
    }
}
