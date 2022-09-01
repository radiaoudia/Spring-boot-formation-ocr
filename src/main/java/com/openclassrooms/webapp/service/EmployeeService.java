package com.openclassrooms.webapp.service;

import com.openclassrooms.webapp.model.Employee;
import com.openclassrooms.webapp.repository.EmployeeProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Data
@Service
public class EmployeeService {
    @Autowired
    private EmployeeProxy employeeProxy;

    public Employee getEmployee(final Long id){
        return employeeProxy.getEmployee(id);
    }

    public Iterable<Employee> getEmployees(){
        return employeeProxy.getEmployees();
    }
    public void deleteEmployee(final Long id){
        employeeProxy.deleteEmployee(id);
    }
    public Employee saveEmployee(Employee employee){
        Employee savedEmployee;
        employee.setNom(employee.getNom().toUpperCase());

        if(employee.getId()==null){
            savedEmployee=employeeProxy.createEmployee(employee);
        }else{
            savedEmployee=employeeProxy.updateEmployee(employee);
        }
        return savedEmployee;
    }

}
