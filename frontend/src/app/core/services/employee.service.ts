import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Employee} from '../../models/employee.model';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private readonly API_URL = 'http://localhost:8080/api/v1';
  private employeeList = new BehaviorSubject<Employee[]>([]);
  employeeList$ = this.employeeList.asObservable();

  constructor(private http: HttpClient) {
  }

  getAll(): void {
    this.http
      .get<Employee[]>(`${this.API_URL}/employee`)
      .subscribe((data) => this.employeeList.next(data));
  }

  create(departmentId: string, data: Partial<Employee>): Observable<Employee> {
    return this.http.post<Employee>(
      `${this.API_URL}/employee/create/${departmentId}`,
      data
    );
  }

  delete(id: number): Observable<void> {
    return this.http.post<void>(`${this.API_URL}/employee/delete/${id}`, {});
  }

  highestSalary(): Observable<Employee> {
    return this.http.get<Employee>(
      `${this.API_URL}/employee/highestSalary`
    );
  }

  lowerAge(): Observable<Employee> {
    return this.http.get<Employee>(
      `${this.API_URL}/employee/lowerAge`
    );
  }

  countLastMonth(): Observable<{ count: number }> {
    return this.http.get<{ count: number }>(
      `${this.API_URL}/employee/countLastMonth`
    );
  }
}
