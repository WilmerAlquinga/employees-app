import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Department} from '../../models/department.model';
import {HttpClient} from '@angular/common/http';

@Injectable({providedIn: 'root'})
export class DepartmentService {
  private readonly API_URL = 'http://localhost:8080/api/v1';
  private departments = new BehaviorSubject<Department[]>([]);
  departments$ = this.departments.asObservable();

  constructor(private http: HttpClient) {
  }

  getAll(): void {
    this.http.get<Department[]>(`${this.API_URL}/department`).subscribe(response => this.departments.next(response));
  }

  getAllActive(): void {
    this.http.get<Department[]>(`${this.API_URL}/department/active`).subscribe(response => this.departments.next(response));
  }

  create(name: string): Observable<Department> {
    return this.http.post<Department>(`${this.API_URL}/department/create`, {name});
  }

  delete(id: number): Observable<void> {
    return this.http.post<void>(`${this.API_URL}/department/delete/${id}`, {});
  }
}
