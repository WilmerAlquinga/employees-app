import {CommonModule} from '@angular/common';
import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators,} from '@angular/forms';
import {EmployeeService} from '../../core/services/employee.service';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import {EmployeeListComponent} from '../employee-list/employee-list.component';
import {Department} from '../../models/department.model';
import {DepartmentService} from '../../core/services/department.service';

@Component({
  selector: 'app-employee-form',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    EmployeeListComponent,
  ],
  templateUrl: './employee-form.component.html',
  styleUrl: './employee-form.component.css',
})
export class EmployeeFormComponent implements OnInit {
  departments: Department[] = [];
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private employeeService: EmployeeService,
    private departmentService: DepartmentService
  ) {
    this.form = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      age: ['', [Validators.required, Validators.min(18)]],
      role: ['Desarrollador', Validators.required],
      salary: ['', [Validators.pattern(/^\d+$/)]],
      startDate: ['', Validators.required],
      endDate: [''],
      status: ['A', Validators.required],
      departmentId: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    this.employeeService.getAll();
    this.departmentService.departments$.subscribe(data => this.departments = data);
    this.departmentService.getAll();
  }

  onSubmit(): void {
    if (this.form.valid) {
      const {departmentId, ...data} = this.form.value;
      this.employeeService.create(departmentId, data).subscribe({
        next: () => {
          this.snackBar.open('Empleado creado exitosamente', 'Cerrar', {
            duration: 3000,
          });
          this.employeeService.getAll();
          this.form.reset();
        },
        error: () => {
          this.snackBar.open('Error al crear empleado', 'Cerrar', {
            duration: 3000,
          });
        },
      });
    }
  }
}
