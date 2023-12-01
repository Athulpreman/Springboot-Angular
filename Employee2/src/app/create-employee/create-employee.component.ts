import { Component } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrl: './create-employee.component.css'
})
export class CreateEmployeeComponent {

  employee: Employee=new Employee();
  constructor(private employeeService: EmployeeService, private router: Router)
  {

  }
  saveEmploee()
  {
    this.employeeService.createEmployee(this.employee).subscribe(data=>{
      console.log(data);
    },error=>console.log(error));
    this.goToEmployeeList()
  }
  goToEmployeeList()
  {
    this.router.navigate(['/employees'])
  }
  ngOnint():void{

  }
  onSubmit()
  {
    this.saveEmploee();
    console.log("employee=",this.employee);
  }
}
