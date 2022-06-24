import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {
  private baseUrl = 'http://localhost:8080/api/categories';

  constructor(private http: HttpClient) { }

  getCategory(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  createCategory(category: Object): Observable<Object> {
    return this.http.post(`${this.baseUrl}`, category);
  }

  deleteCategory(id: String): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

  getCategoriesList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }
}

