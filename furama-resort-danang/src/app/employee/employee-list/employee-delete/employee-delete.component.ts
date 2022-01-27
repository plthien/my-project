import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Employee} from '../../employee';
import {EmployeeService} from '../../../service/employee.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-employee-delete',
  templateUrl: './employee-delete.component.html',
  styleUrls: ['./employee-delete.component.css']
})
export class EmployeeDeleteComponent implements OnInit {
  constructor(
    public dialogRef: MatDialogRef<EmployeeDeleteComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private employeeService: EmployeeService,
  ) {
  }

  ngOnInit(): void {
  }

  delete(id: any) {
    this.employeeService.deleteEmployeeById(id).subscribe(value => {
      this.dialogRef.close();
    });
  }
}
