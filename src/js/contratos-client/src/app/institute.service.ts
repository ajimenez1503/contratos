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
}
