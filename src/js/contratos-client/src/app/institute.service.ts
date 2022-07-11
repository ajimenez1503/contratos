import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';;

@Injectable({
  providedIn: 'root'
})
export class InstituteService {
  private baseUrl = 'http://localhost:8080/api/institutes';

  constructor(private http: HttpClient) { }

  getInstitutesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  getInstitutesListBy(province: String, kind: String): Observable<any> {
    return this.http.get(`${this.baseUrl}?province=${province}&kind=${kind}`);
  }

  getProvinces(kind: String): Observable<any> {
    return this.http.get(`${this.baseUrl}/provinces?kind=${kind}`);
  }

  getKinds(province: String): Observable<any> {
    return this.http.get(`${this.baseUrl}/kinds?province=${province}`);
  }
}
