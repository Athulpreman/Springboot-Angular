package com.example.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.emp.exception.ResourceNotFoundException;
import com.example.emp.model.Employee;
import com.example.emp.repository.EmployeeRepository;


@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/")
public class EmployeeController 
{
	@Autowired
	private EmployeeRepository emprep;
	
	//get all employee
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return emprep.findAll();
	}
	
	//create employee
	@PostMapping("/employees")
	public Employee createEmployees(@RequestBody Employee employee)
	{
		return emprep.save(employee);
	}
	
	//getEmployee by id
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id)
	{
		Employee employee=emprep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with id : "+id));
		return ResponseEntity.ok(employee);
	}
	
	//Edit employee
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmplyee(@PathVariable Long id, @RequestBody Employee empl)
	{
		Employee employee=emprep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with id : "+id));
		employee.setFname(empl.getFname());
		employee.setLname(empl.getLname());
		employee.setEmail(empl.getEmail());
		
		Employee updatedEmployee = emprep.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	//delete employee
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id)
	{
		Employee employee=emprep.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee does not exists with id : "+id));
		emprep.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", true);
		return ResponseEntity.ok(response);
	}
}
