import { Component, OnInit } from '@angular/core';
import { AgreementService } from '../agreement.service';
import { AgreementRequest } from '../agreementRequest';
import { Router } from '@angular/router';
import {NgbDate, NgbCalendar, NgbDateParserFormatter} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-create-agreement',
  templateUrl: './create-agreement.component.html',
  styleUrls: ['./create-agreement.component.css']
})
export class CreateAgreementComponent implements OnInit {

  agreement: AgreementRequest = new AgreementRequest();
  submitted = false;
  submittedSuccessful = true;
  hoveredDate: NgbDate | null = null;
  initialDate: NgbDate | null;
  endDate: NgbDate | null;

  constructor(
      private agreementService: AgreementService,
      private router: Router,
      private calendar: NgbCalendar,
      public formatter: NgbDateParserFormatter) {
    this.initialDate = calendar.getToday();
    this.endDate = calendar.getNext(calendar.getToday(), 'd', 10);
  }

  onDateSelection(date: NgbDate) {
    if (!this.initialDate && !this.endDate) {
      this.initialDate = date;
    } else if (this.initialDate && !this.endDate && date && date.after(this.initialDate)) {
      this.endDate = date;
    } else {
      this.endDate = null;
      this.initialDate = date;
    }
  }

  isHovered(date: NgbDate) {
    return this.initialDate && !this.endDate && this.hoveredDate && date.after(this.initialDate) &&
        date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) { return this.endDate && date.after(this.initialDate) && date.before(this.endDate); }

  isRange(date: NgbDate) {
    return date.equals(this.initialDate) || (this.endDate && date.equals(this.endDate)) || this.isInside(date) ||
        this.isHovered(date);
  }

  validateInput(currentValue: NgbDate | null, input: string): NgbDate | null {
    const parsed = this.formatter.parse(input);
    return parsed && this.calendar.isValid(NgbDate.from(parsed)) ? NgbDate.from(parsed) : currentValue;
  }

  padNumber(value: number) {
    if (!isNaN(value) && value !== null) {
      return `0${value}`.slice(-2);
    }
    return '';
  }

  format(date: NgbDate | null): string {
    return date
        ? `${date.year}-${this.padNumber(date.month)}-${this.padNumber(date.day)}`
        : '';
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
    console.log(this.format(this.initialDate));
    console.log(this.format(this.endDate));
    this.agreement.initialDate = this.format(this.initialDate);
    this.agreement.endDate = this.format(this.endDate);
    this.save();
  }

  gotoList() {
    this.router.navigate(['/agreements']);
  }
}
