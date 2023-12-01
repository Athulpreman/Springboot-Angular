import { Component } from '@angular/core';
import { Employee } from '../employee';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-view-employee',
  templateUrl: './view-employee.component.html',
  styleUrl: './view-employee.component.css'
})
export class ViewEmployeeComponent {
  employee: Employee=new Employee();
  id:number;
  constructor(private employeeService: EmployeeService, private route: ActivatedRoute, private router:Router)
  {
    
  }
  ngOnInit(): void
  {
    this.id=this.route.snapshot.params['id'];
    this.employeeService.getEmployeeById(this.id).subscribe(data=>{
      this.employee=data;
    }, error=>console.log(error));
  }
  goToEmployeeList()
  {
    this.router.navigate(['/employees'])
  }
  onSubmit()
  {
    this.employeeService.updateEmployee(this.id, this.employee).subscribe(data=>{
      this.goToEmployeeList();
    },error=>console.log(error));
  }
}
