import {Routes} from '@angular/router';
import {EmployeeFormComponent} from './features/employee-form/employee-form.component';
import {EmployeeSearchComponent} from './features/employee-search/employee-search.component';

export const routes: Routes = [
  {path: 'crear-empleado', component: EmployeeFormComponent},
  {path: 'buscar-empleados', component: EmployeeSearchComponent},
  {path: '', redirectTo: 'crear-empleado', pathMatch: 'full'},
  {path: '**', redirectTo: 'crear-empleado'},
];
