import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatTableModule} from '@angular/material/table';
import {EmployeeListComponent} from '../employee-list/employee-list.component';
import {EmployeeService} from '../../core/services/employee.service';

@Component({
  selector: 'app-employee-search',
  standalone: true,
  imports: [CommonModule, MatTableModule, EmployeeListComponent],
  templateUrl: './employee-search.component.html',
  styleUrl: './employee-search.component.css'
})
export class EmployeeSearchComponent {
  info = '';

  constructor(public employeeService: EmployeeService) {
  }

  showHighestSalary(): void {
    this.employeeService.highestSalary().subscribe((data) => {
      this.info = `Empleado con salario más alto: ${data.firstName} ${data.lastName} - $${data.salary}`;
    });
  }

  showLowerAge(): void {
    this.employeeService.lowerAge().subscribe((data) => {
      this.info = `Empleado más joven: ${data.firstName} ${data.lastName} - ${data.age} años`;
    });
  }

  showCountLastMonth(): void {
    this.employeeService.countLastMonth().subscribe((data) => {
      this.info = `Empleados ingresados el último mes: ${data.count}`;
    });
  }
}
