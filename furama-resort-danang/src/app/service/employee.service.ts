import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Employee} from '../employee/employee';
import {EmployeeDegree} from '../employee/employee-degree';
import {EmployeeOffice} from '../employee/employee-office';
import {EmployeeDepartment} from '../employee/employee-department';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private urlEmployee = 'http://localhost:3000/employee';
  private urlEmployeeDegree = 'http://localhost:3000/employeeDegree';
  private urlEmployeeOffice = 'http://localhost:3000/employeeOffice';
  private urlEmployeeDepartment = 'http://localhost:3000/employeeDepartment';

  constructor(private http: HttpClient) {
  }

  getEmployee(fieldSort: string, directionSort: string): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.urlEmployee + '?_sort=' + fieldSort + '&_order=' + directionSort);
  }

  getEmployeeById(employeeId: number): Observable<Employee> {
    return this.http.get<Employee>(this.urlEmployee + '/' + employeeId);
  }

  saveEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(this.urlEmployee, employee);
  }

  updateEmployee(employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(this.urlEmployee + '/' + employee.id, employee);
  }

  deleteEmployeeById(employeeId: number): Observable<Employee> {
    return this.http.delete<Employee>(this.urlEmployee + '/' + employeeId);
  }

  getEmployeeByName(keyword: string): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.urlEmployee + '?name_like=' + keyword);
  }

  getEmployeeDegree(): Observable<EmployeeDegree[]> {
    return this.http.get<EmployeeDegree[]>(this.urlEmployeeDegree);
  }

  getEmployeeOffice(): Observable<EmployeeOffice[]> {
    return this.http.get<EmployeeOffice[]>(this.urlEmployeeOffice);
  }

  getEmployeeDepartment(): Observable<EmployeeDepartment[]> {
    return this.http.get<EmployeeDepartment[]>(this.urlEmployeeDepartment);
  }
}
