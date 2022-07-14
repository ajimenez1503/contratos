import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AgreementDurationType } from "./agreementDurationType";

@Injectable({
  providedIn: 'root'
})
export class AgreementService {
  private baseUrl = 'http://localhost:8080/api/agreements';

  constructor(private http: HttpClient) { }

  getAgreement(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  getAgreementsList(durationType: AgreementDurationType, category: String): Observable<any> {
    return this.http.get(`${this.baseUrl}?durationType=${durationType}&category=${category}`);
  }

  createAgreement(agreementRequest: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, agreementRequest);
  }
}
