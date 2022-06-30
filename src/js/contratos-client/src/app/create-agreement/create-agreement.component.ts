import { Component, OnInit } from '@angular/core';
import { AgreementService } from '../agreement.service';
import { CategoryService } from "../category.service";
import { Category } from "../category";
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
  agreement: AgreementRequest = new AgreementRequest();
  submitted = false;
  submittedSuccessful = true;

  constructor(
      private categoryService: CategoryService,
      private agreementService: AgreementService,
      private router: Router) {
  }

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    this.categories = this.categoryService.getCategoriesList();
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
    this.save();
  }

  gotoList() {
    this.router.navigate(['/agreements']);
  }
}
