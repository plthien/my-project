import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
import {EmployeeService} from '../../service/employee.service';
import {EmployeeDegree} from '../employee-degree';
import {EmployeeOffice} from '../employee-office';
import {EmployeeDepartment} from '../employee-department';
import {Subscription} from 'rxjs';
import {Router} from '@angular/router';

export function dateOfBirthMoreThan18(c: AbstractControl) {
  const v = c.value;
  const date = new Date(v.dateOfBirth);
  // @ts-ignore
  const timeDiff = Math.abs(Date.now() - date );
  const age = Math.floor((timeDiff / (1000 * 3600 * 24)) / 365);
  return (age >= 18) ? null : {dateofbirthmorethan18: true};
}

@Component({
  selector: 'app-employee-create',
  templateUrl: './employee-create.component.html',
  styleUrls: ['./employee-create.component.css']
})
export class EmployeeCreateComponent implements OnInit {
  employeeForm: FormGroup;
  employeeDegree: EmployeeDegree[];
  employeeOffice: EmployeeOffice[];
  employeeDepartment: EmployeeDepartment[];
  subscription: Subscription;

  constructor(private fb: FormBuilder,
              private employeeService: EmployeeService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.employeeForm = this.fb.group({
      name: ['', [Validators.required]],
      dateOfBirth: ['', [Validators.required]],
      gender: ['', [Validators.required]],
      personalID: ['', [Validators.required, Validators.pattern('(^\\d{9}$)|(^\\d{12}$)')]],
      phoneNumber: ['', [Validators.required, Validators.pattern('^\\+84\\d{9}$')]],
      address: ['', [Validators.required]],
      employeeDegree: ['', [Validators.required]],
      employeeOffice: ['', [Validators.required]],
      employeeDepartment: ['', [Validators.required]],
      salary: ['', [Validators.required, Validators.min(0)]],
      email: ['', [Validators.required, Validators.email]]
    }, {
      validators: dateOfBirthMoreThan18
    });

    this.getAllEmployeeDegree();
    this.getAllEmployeeOffice();
    this.getAllEmployeeDepartment();
  }

  getAllEmployeeDegree() {
    this.subscription = this.employeeService.getEmployeeDegree().subscribe(value => {
        this.employeeDegree = value;
      }, error => {
        console.log(error);
      }
    );
  }

  getAllEmployeeOffice() {
    this.subscription = this.employeeService.getEmployeeOffice().subscribe(value => {
        this.employeeOffice = value;
      }, error => {
        console.log(error);
      }
    );
  }

  getAllEmployeeDepartment() {
    this.subscription = this.employeeService.getEmployeeDepartment().subscribe(value => {
        this.employeeDepartment = value;
      }, error => {
        console.log(error);
      }
    );
  }

  onSubmit() {
    if (this.employeeForm.valid) {
      const mess = 'Add new Employee successfuly!';
      this.employeeService.saveEmployee(this.employeeForm.value).subscribe(value => {
          this.router.navigate(['/employee/list', mess]);
        },
        error => {
          console.log(error);
        });
    } else {
      console.log(this.employeeForm);
    }

  }
}
