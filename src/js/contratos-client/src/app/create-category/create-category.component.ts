import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../category.service';
import { Category } from '../category';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-category',
  templateUrl: './create-category.component.html',
  styleUrls: ['./create-category.component.css']
})
export class CreateCategoryComponent implements OnInit {

  category: Category = new Category();
  submitted = false;

  constructor(private categoryService: CategoryService,
    private router: Router) { }

  ngOnInit(): void {
  }
  newCategory(): void {
    this.submitted = false;
    this.category = new Category();
  }

  save() {
    this.categoryService
    .createCategory(this.category).subscribe(data => {
      console.log(data)
      this.category = new Category();
      this.gotoList();
    },
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/categories']);
  }
}
