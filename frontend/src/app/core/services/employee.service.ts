import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Employee} from '../../models/employee.model';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private readonly API_URL = `${environment.apiUrl}/employee`;
  private employeeList = new BehaviorSubject<Employee[]>([]);
  employeeList$ = this.employeeList.asObservable();

  constructor(private http: HttpClient) {
  }

  getAll(): void {
    this.http
      .get<Employee[]>(`${this.API_URL}`)
      .subscribe((data) => this.employeeList.next(data));
  }

  create(departmentId: string, data: Partial<Employee>): Observable<Employee> {
    return this.http.post<Employee>(
      `${this.API_URL}/create/${departmentId}`,
      data
    );
  }

  delete(id: number): Observable<void> {
    return this.http.post<void>(`${this.API_URL}/delete/${id}`, {});
  }

  highestSalary(): Observable<Employee> {
    return this.http.get<Employee>(
      `${this.API_URL}/highestSalary`
    );
  }

  lowerAge(): Observable<Employee> {
    return this.http.get<Employee>(
      `${this.API_URL}/lowerAge`
    );
  }

  countLastMonth(): Observable<{ count: number }> {
    return this.http.get<{ count: number }>(
      `${this.API_URL}/countLastMonth`
    );
  }
}
