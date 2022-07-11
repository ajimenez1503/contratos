import { Component, OnInit } from '@angular/core';
import { AgreementService } from '../agreement.service';
import { CategoryService } from "../category.service";
import { Category } from "../category";
import { InstituteService } from "../institute.service";
import { Institute } from "../institute";
import { AgreementRequest } from '../agreementRequest';
import { Router } from '@angular/router';
import { Observable } from "rxjs";

@Component({
  selector: 'app-create-agreement',
  templateUrl: './create-agreement.component.html',
  styleUrls: ['./create-agreement.component.css']
})
export class CreateAgreementComponent implements OnInit {

  categories!: Observable<Category[]>;
  institutes!: Observable<Institute[]>;
  provinces!: Observable<String[]>;
  provinceChoose: String = '';
  instituteKinds!: Observable<String[]>;
  instituteKindChoose: String = '';
  instituteIdChoose: String = '';

  agreement: AgreementRequest = new AgreementRequest();
  submitted = false;
  submittedSuccessful = true;

  constructor(
      private categoryService: CategoryService,
      private instituteService: InstituteService,
      private agreementService: AgreementService,
      private router: Router) {
  }

  ngOnInit(): void {
    this.reloadData();
  }

  updateInstitutes() {
    this.institutes = this.instituteService.getInstitutesListBy(this.provinceChoose, this.instituteKindChoose);
    this.provinces = this.instituteService.getProvinces(this.instituteKindChoose);
    this.instituteKinds = this.instituteService.getKinds(this.provinceChoose);
  }

  reloadData() {
    this.categories = this.categoryService.getCategoriesList();
    this.updateInstitutes();
  }

  newAgreement(): void {
    this.submitted = false;
    this.submittedSuccessful = true;
    this.agreement = new AgreementRequest();
  }

  save() {
    this.agreementService.createAgreement(this.agreement).subscribe(
      data => {
        console.log(data);
        this.agreement = new AgreementRequest();
        this.gotoList();
      },
      error => {
        console.log(error);
        this.submittedSuccessful = false;
      }
    )
  }

  onSubmit() {
    this.submitted = true;
    this.agreement.instituteId = Number(this.instituteIdChoose);
    this.save();
  }

  gotoList() {
    this.router.navigate(['/agreements']);
  }
}
