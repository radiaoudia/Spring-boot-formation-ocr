package com.openclassrooms.webapp.repository;

import com.openclassrooms.webapp.CustomProperties;
import com.openclassrooms.webapp.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class EmployeeProxy {
    @Autowired
    private CustomProperties customProperties;

    public Iterable<Employee> getEmployees(){
        // grace a notre objet CustomProperties on recupere l'url de l'api
        String baseApiUrl =customProperties.getApiUrl();
        // on complete l'url de l'api par le path de l'endpoint a joindre
        String getEmployeeUrl=baseApiUrl+"/employees";
        // on instancie notre objet RestTemplate
        RestTemplate restTemplate=new RestTemplate();
        // on appelle la methode exchange en transmettant (URL, methode HTTP,)
        ResponseEntity<Iterable<Employee>> response=restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {}
                );

        log.debug("Get Employees call " + response.getStatusCode().toString());
        // recuperer lobjet iterable<Employee> grace a la methode getBody() de lobjet Response
        return response.getBody();
    }
    public Employee getEmployee(Long id){
        String baseApiUrl=customProperties.getApiUrl();
        String getEmployeeUrl=baseApiUrl+"employee";
        RestTemplate restTemplate=new RestTemplate();
        HttpEntity<Long> request=new HttpEntity<Long>(id);
        ResponseEntity<Employee> response=restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                request,
                Employee.class
        );
        log.debug("get Employee call"+response.getStatusCode().toString());
        return response.getBody();
    }
    public void deleteEmployee(Long id){
        String baseApiUrl=customProperties.getApiUrl();
        String deleteEmployeeUrl=baseApiUrl+"employee";
        RestTemplate restTemplate=new RestTemplate();
        HttpEntity<Long> request=new HttpEntity<Long>(id);
        ResponseEntity<Employee> response=restTemplate.exchange(
                deleteEmployeeUrl,
                HttpMethod.DELETE,
                request,
                Employee.class
        );
        log.debug("delete Employee call"+response.getStatusCode().toString());
    }

    public Employee createEmployee(Employee e){
        String baseApiUrl=customProperties.getApiUrl();
        String createEmployeeUrl=baseApiUrl+"/employee";

        RestTemplate restTemplate=new RestTemplate();
        HttpEntity<Employee> request=new HttpEntity<Employee>(e);
        ResponseEntity<Employee> response=restTemplate.exchange(
                createEmployeeUrl,
                HttpMethod.POST,
                request,
                Employee.class
        );
        log.debug("Create employee call"+response.getStatusCode().toString());
        return response.getBody();
    }
    public Employee updateEmployee(Employee e){
        String baseApiUrl=customProperties.getApiUrl();
        String createEmployeeUrl=baseApiUrl+"/employee";

        RestTemplate restTemplate=new RestTemplate();
        HttpEntity<Employee> request=new HttpEntity<Employee>(e);
        ResponseEntity<Employee> response=restTemplate.exchange(
                createEmployeeUrl,
                HttpMethod.PUT,
                request,
                Employee.class
        );
        log.debug("Updated employee call"+response.getStatusCode().toString());
        return response.getBody();
    }
}
