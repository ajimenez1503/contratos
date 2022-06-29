import { Observable } from "rxjs";
import { InstituteService } from "../institute.service";
import { Institute } from "../institute";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: 'app-institute-list',
  templateUrl: './institute-list.component.html',
  styleUrls: ['./institute-list.component.css']
})
export class InstituteListComponent implements OnInit {
  institutes!: Observable<Institute[]>;

  constructor(private instituteService: InstituteService,
        private router: Router) {}

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    this.institutes = this.instituteService.getInstitutesList();
  }

}
