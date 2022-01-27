import {Component, OnDestroy, OnInit} from '@angular/core';
import {Employee} from '../employee';
import {EmployeeService} from '../../service/employee.service';
import {Subscription} from 'rxjs';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {MatDialog} from '@angular/material/dialog';
import {EmployeeDeleteComponent} from './employee-delete/employee-delete.component';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit, OnDestroy {
  employees: Employee[];
  subscription: Subscription | undefined;
  mess: string;
  keyword: string;
  page: number = 1;
  sortField: string = 'id';
  sortDirection: string = 'asc';

  constructor(
    private employeeService: EmployeeService,
    private activatedRoute: ActivatedRoute,
    public dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.getAll();
    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap) => {
      this.mess = paramMap.get('mess');
    });
  }

  getAll() {
    this.subscription = this.employeeService.getEmployee(this.sortField, this.sortDirection).subscribe(value => {
        this.employees = value;
        console.log(this.employees.length);
      },
      error => {
        console.log(error);
      });
  }

  ngOnDestroy(): void {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }

  openDialog(employeeId: number): void {
    this.employeeService.getEmployeeById(employeeId).subscribe(value => {
      const dialogRef = this.dialog.open(EmployeeDeleteComponent, {
        width: '500px',
        data: {employee: value},
        disableClose: true
      });

      dialogRef.afterClosed().subscribe(result => {
        this.ngOnInit();
      });
    });

  }

  searchEmployee() {
    if (this.keyword === '') {
      this.ngOnInit();
    } else {
      this.employeeService.getEmployeeByName(this.keyword).subscribe(value => {
          this.employees = value;
        },
        error => {
          console.log(error);
        });
    }
  }

  sort(sortField: string) {
    this.sortField = sortField;
    this.sortDirection = (this.sortDirection === 'asc') ? 'desc' : 'asc';
    this.ngOnInit();
  }
}
