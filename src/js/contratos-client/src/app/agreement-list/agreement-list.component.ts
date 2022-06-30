import { AgreementDetailsComponent } from '../agreement-details/agreement-details.component';
import { Observable } from "rxjs";
import { AgreementService } from "../agreement.service";
import { Agreement } from "../agreement";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { AgreementDurationType } from "../agreementDurationType";

@Component({
  selector: 'app-agreement-list',
  templateUrl: './agreement-list.component.html',
  styleUrls: ['./agreement-list.component.css']
})
export class AgreementListComponent implements OnInit {
  agreements!: Observable<Agreement[]>;
  agreementDurationType = AgreementDurationType;

  constructor(private agreementService: AgreementService,
        private router: Router) {}

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    this.agreements = this.agreementService.getAgreementsList();
  }

  agreementDetails(id: number){
    this.router.navigate(['agreement-details', id]);
  }

}
