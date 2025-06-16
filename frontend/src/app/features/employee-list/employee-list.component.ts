import {Component, inject, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {MatTableModule} from '@angular/material/table';
import {EmployeeService} from '../../core/services/employee.service';
import {map} from 'rxjs/operators';
import {Employee} from '../../models/employee.model';
import {MatDialog} from '@angular/material/dialog';
import {MatSnackBar} from '@angular/material/snack-bar';
import {ConfirmDialogComponent} from '../../components/confirm-dialog/confirm-dialog.component';
import {MatIcon} from '@angular/material/icon';
import {MatIconButton} from '@angular/material/button';

@Component({
  selector: 'app-employee-list',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatIcon, MatIconButton],
  templateUrl: './employee-list.component.html',
  styleUrl: './employee-list.component.css'
})
export class EmployeeListComponent implements OnInit {
  columns: string[] = [
    'firstName',
    'lastName',
    'age',
    'role',
    'salary',
    'startDate',
    'endDate',
    'status',
    'actions'
  ];
  private dialog = inject(MatDialog);
  private snackBar = inject(MatSnackBar);
  employeeList$;

  constructor(private employeeService: EmployeeService) {
    this.employeeList$ = this.employeeService.employeeList$.pipe(
      map((list: Employee[] | null) => list ?? [])
    );
  }

  ngOnInit(): void {
    this.employeeService.getAll();
  }

  openDeleteDialog(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '320px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.employeeService.delete(id).subscribe({
          next: () => {
            this.snackBar.open('Empleado eliminado exitosamente', 'Cerrar', {
              duration: 3000,
            });
            this.employeeService.getAll();
          },
          error: () => {
            this.snackBar.open('Error al eliminar empleado', 'Cerrar', {
              duration: 3000,
            });
          }
        });
      }
    });
  }
}
