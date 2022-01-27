import {Component, OnInit} from '@angular/core';
import {Employee} from '../employee';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {EmployeeService} from '../../service/employee.service';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {dateOfBirthMoreThan18} from '../employee-create/employee-create.component';
import {EmployeeDegree} from '../employee-degree';
import {EmployeeOffice} from '../employee-office';
import {EmployeeDepartment} from '../employee-department';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-employee-edit',
  templateUrl: './employee-edit.component.html',
  styleUrls: ['./employee-edit.component.css']
})
export class EmployeeEditComponent implements OnInit {
  employee: Employee;
  employeeForm: FormGroup;
  employeeDegree: EmployeeDegree[];
  employeeOffice: EmployeeOffice[];
  employeeDepartment: EmployeeDepartment[];
  subscription: Subscription;

  constructor(private activatedRoute: ActivatedRoute,
              private employeeService: EmployeeService,
              private fb: FormBuilder,
              private router: Router) {
  }

  ngOnInit(): void {
    this.employeeForm = this.fb.group({
        id: [''],
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
      }
    );

    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      let id = +paramMap.get('id');
      this.employeeService.getEmployeeById(id).subscribe(value => {
          this.employee = value;
          this.employeeForm.setValue(this.employee);
          console.log(this.employee.dateOfBirth);
        },
        error => {
          console.log(error);
        });
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

  compareFn(c1: any, c2: any): boolean {
    return c1 && c2 ? c1.id === c2.id : c1 === c2;
  }

  onSubmit() {
    if (this.employeeForm.valid) {
      const mess = 'Edit Employee successfuly!';
      this.employeeService.updateEmployee(this.employeeForm.value).subscribe(value => {
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
