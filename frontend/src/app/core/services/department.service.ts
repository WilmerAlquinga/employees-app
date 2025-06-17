import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';
import {Department} from '../../models/department.model';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';

@Injectable({providedIn: 'root'})
export class DepartmentService {
  private readonly API_URL = `${environment.apiUrl}/department`;
  private departments = new BehaviorSubject<Department[]>([]);
  departments$ = this.departments.asObservable();

  constructor(private http: HttpClient) {
  }

  getAll(): void {
    this.http.get<Department[]>(this.API_URL).subscribe(response => this.departments.next(response));
  }

  getAllActive(): void {
    this.http.get<Department[]>(`${this.API_URL}/active`).subscribe(response => this.departments.next(response));
  }

  create(name: string): Observable<Department> {
    return this.http.post<Department>(`${this.API_URL}/create`, {name});
  }

  delete(id: number): Observable<void> {
    return this.http.post<void>(`${this.API_URL}/delete/${id}`, {});
  }
}
