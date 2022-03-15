package usecases;

import entities.Employee;
import lombok.Getter;
import lombok.Setter;
import persistence.EmployeesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Employees {

    @Inject
    private EmployeesDAO employeesDAO;

    @Getter @Setter
    private Employee employeeToRegister = new Employee();

    @Getter
    private List<Employee> allEmployees;

//    TODO how does the list refresh on the page?
    @PostConstruct
    public void init()
    {
        this.allEmployees = employeesDAO.findAll();
    }

    @Transactional
    public void registerEmployee()
    {
        this.employeesDAO.persist(employeeToRegister);
    }
}
