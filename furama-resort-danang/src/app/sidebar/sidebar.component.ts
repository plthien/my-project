import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {EmployeeListComponent} from '../employee/employee-list/employee-list.component';

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  constructor(private router: Router) {
  }

  ngOnInit(): void {
  }


}
