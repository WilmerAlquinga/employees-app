import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {MatSnackBar, MatSnackBarModule} from '@angular/material/snack-bar';
import {DepartmentService} from '../../core/services/department.service';
import {map} from 'rxjs/operators';
import {Department} from '../../models/department.model';
import {ConfirmDialogComponent} from '../../components/confirm-dialog/confirm-dialog.component';
import {MatTableModule} from '@angular/material/table';
import {MatIcon} from '@angular/material/icon';
import {MatIconButton} from '@angular/material/button';
import {MatDialog} from '@angular/material/dialog';

@Component({
  selector: 'app-department',
  standalone: true,
  imports: [CommonModule,
    ReactiveFormsModule,
    MatSnackBarModule, MatTableModule, MatIcon, MatIconButton],
  templateUrl: './department.component.html',
  styleUrl: './department.component.css'
})
export class DepartmentComponent implements OnInit {
  form: FormGroup;
  departments$;
  columns: string[] = [
    'id',
    'name',
    'status',
    'actions'
  ];

  constructor(private fb: FormBuilder,
              private snackBar: MatSnackBar,
              private dialog: MatDialog,
              private departmentService: DepartmentService) {
    this.form = this.fb.group({
      name: [null, Validators.required],
      status: ['A'],
    });

    this.departments$ = this.departmentService.departments$.pipe(
      map((list: Department[] | null) => list ?? [])
    );
  }

  ngOnInit() {
    this.departmentService.getAll();
  }

  onSubmit(): void {
    if (this.form.valid) {
      const {...data} = this.form.value;
      this.departmentService.create(data?.name).subscribe({
        next: () => {
          this.snackBar.open('Departamento creado exitosamente', 'Cerrar', {
            duration: 3000,
          });
          this.departmentService.getAll();
          this.form.reset();
        },
        error: () => {
          this.snackBar.open('Error al crear el departamento', 'Cerrar', {
            duration: 3000,
          });
        },
      });
    }
  }

  openDeleteDialog(id: number): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '320px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.departmentService.delete(id).subscribe({
          next: () => {
            this.snackBar.open('Departamento eliminado exitosamente', 'Cerrar', {
              duration: 3000,
            });
            this.departmentService.getAll();
          },
          error: () => {
            this.snackBar.open('Error al eliminar el departamento', 'Cerrar', {
              duration: 3000,
            });
          }
        });
      }
    });
  }
}
