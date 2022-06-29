import { Component, OnInit } from '@angular/core';
import { AgreementService } from '../agreement.service';
import { AgreementRequest } from '../agreementRequest';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-agreement',
  templateUrl: './create-agreement.component.html',
  styleUrls: ['./create-agreement.component.css']
})
export class CreateAgreementComponent implements OnInit {

  agreement: AgreementRequest = new AgreementRequest();
  submitted = false;
  submittedSuccessful = true;

  constructor(
      private agreementService: AgreementService,
      private router: Router) {
  }

  ngOnInit(): void {
  }
  newAgreement(): void {
    this.submitted = false;
    this.submittedSuccessful = true;
    this.agreement = new AgreementRequest();
  }

  save() {
    this.agreementService.createAgreement(this.agreement).subscribe(
      data => {
        console.log(data)
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
