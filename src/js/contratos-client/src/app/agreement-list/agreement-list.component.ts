import { AgreementDetailsComponent } from '../agreement-details/agreement-details.component';
import { Observable } from "rxjs";
import { AgreementService } from "../agreement.service";
import { Agreement } from "../agreement";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { AgreementDurationType } from "../agreementDurationType";
import { InstituteService } from "../institute.service";
import { Institute } from "../institute";
import { CategoryService } from "../category.service";
import { Category } from "../category";

@Component({
  selector: 'app-agreement-list',
  templateUrl: './agreement-list.component.html',
  styleUrls: ['./agreement-list.component.css']
})
export class AgreementListComponent implements OnInit {
  agreements!: Observable<Agreement[]>;
  agreementDurationType = AgreementDurationType;
  institutes!: Observable<Institute[]>;
  provinces!: Observable<String[]>;
  provinceChosen: String = '';
  instituteKinds!: Observable<String[]>;
  instituteKindChosen: String = '';
  instituteIdChosen: String = '';
  categories!: Observable<Category[]>;
  categoryIdChosen: String = '';
  durationTypeChosen: AgreementDurationType = AgreementDurationType.SHORT;

  constructor(
      private categoryService: CategoryService,
      private agreementService: AgreementService,
      private instituteService: InstituteService,
      private router: Router) {
  }

  ngOnInit(): void {
    this.reloadData();
  }

  updateList() {
    this.institutes = this.instituteService.getInstitutesListBy(this.provinceChosen, this.instituteKindChosen);
    this.provinces = this.instituteService.getProvinces(this.instituteKindChosen);
    this.instituteKinds = this.instituteService.getKinds(this.provinceChosen);
    this.agreements = this.agreementService.getAgreementsList(this.durationTypeChosen, this.categoryIdChosen);
  }

  reloadData() {
    this.updateList();
    this.categories = this.categoryService.getCategoriesList();
  }

  agreementDetails(id: number){
    this.router.navigate(['agreement-details', id]);
  }

}
