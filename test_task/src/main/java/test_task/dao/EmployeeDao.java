package test_task.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import test_task.model.Employee;

import java.util.List;

@Repository
public interface EmployeeDao extends CrudRepository<Employee, Long> {

    //TODO Get a list of employees receiving a salary greater than that of the boss
    @Query(
            value = "select * from employee e where salary > (select salary from employee be where e.boss_id = be.id)",
            nativeQuery = true)
    List<Employee> findAllWhereSalaryGreaterThatBoss();

    //TODO Get a list of employees receiving the maximum salary in their department
    @Query(
            value = "select * from employee e where salary in ( " +
                    "select max(salary) as maxsalary from employee group by department_id " +
                    ")",
            nativeQuery = true)
    List<Employee> findAllByMaxSalary();

    //TODO Get a list of employees who do not have boss in the same department
    @Query(
            value = "select e.* from employee e " +
                    "where e.department_id <> (select be.department_id from employee be where e.boss_id = be.id)",
            nativeQuery = true)
    List<Employee> findAllWithoutBoss();
}
