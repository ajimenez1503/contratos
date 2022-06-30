import { Observable } from "rxjs";
import { CategoryService } from "../category.service";
import { Category } from "../category";
import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';

@Component({
  selector: 'app-category-list',
  templateUrl: './category-list.component.html',
  styleUrls: ['./category-list.component.css']
})
export class CategoryListComponent implements OnInit {

  categories!: Observable<Category[]>;

  constructor(private categoryService: CategoryService,
        private router: Router) {}

  ngOnInit(): void {
    this.reloadData();
  }

  reloadData() {
    this.categories = this.categoryService.getCategoriesList();
  }

}
