import {EmployeeDegree} from './employee-degree';
import {EmployeeOffice} from './employee-office';
import {EmployeeDepartment} from './employee-department';

export interface Employee {
  id: number;
  name: string;
  dateOfBirth: Date;
  gender: string;
  personalID: string;
  phoneNumber: string;
  email: string;
  address: string;
  salary: number;
  employeeDegree: EmployeeDegree;
  employeeOffice: EmployeeOffice;
  employeeDepartment: EmployeeDepartment;
}
