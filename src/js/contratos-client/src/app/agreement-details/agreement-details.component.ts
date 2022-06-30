import { Component, OnInit } from '@angular/core';
import { Agreement } from '../agreement';
import { AgreementDurationType } from "../agreementDurationType";
import { AgreementService } from '../agreement.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-agreement-details',
  templateUrl: './agreement-details.component.html',
  styleUrls: ['./agreement-details.component.css']
})
export class AgreementDetailsComponent implements OnInit {
  id: number;
  agreement: Agreement;
  agreementDurationType = AgreementDurationType;

  constructor(
        private route: ActivatedRoute,
        private router: Router,
        private agreementService: AgreementService) {
      this.id = 0;
      this.agreement = new Agreement();
   }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.agreementService.getAgreement(this.id).subscribe(
      data => {
        console.log(data)
        this.agreement = data;
      },
      error => console.log(error)
    );
  }

  list(){
    this.router.navigate(['agreements']);
  }
}
