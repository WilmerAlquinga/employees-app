import {Routes} from '@angular/router';
import {EmployeeFormComponent} from './features/employee-form/employee-form.component';
import {EmployeeSearchComponent} from './features/employee-search/employee-search.component';
import {DepartmentComponent} from './features/department/department.component';

export const routes: Routes = [
  {path: 'crear-empleado', component: EmployeeFormComponent},
  {path: 'buscar-empleados', component: EmployeeSearchComponent},
  {path: 'departamentos', component: DepartmentComponent},
  {path: '', redirectTo: 'crear-empleado', pathMatch: 'full'},
  {path: '**', redirectTo: 'crear-empleado'},
];
