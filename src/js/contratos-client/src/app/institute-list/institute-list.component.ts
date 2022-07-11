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
  provinces!: Observable<String[]>;
  provinceChoose: String = '';
  instituteKinds!: Observable<String[]>;
  instituteKindChoose: String = '';

  constructor(private instituteService: InstituteService,
        private router: Router) {}

  ngOnInit(): void {
    this.reloadData();
  }

  updateInstitutes() {
    this.institutes = this.instituteService.getInstitutesListBy(this.provinceChoose, this.instituteKindChoose);
    this.provinces = this.instituteService.getProvinces(this.instituteKindChoose);
    this.instituteKinds = this.instituteService.getKinds(this.provinceChoose);
  }

  reloadData() {
    this.updateInstitutes();
  }

}
