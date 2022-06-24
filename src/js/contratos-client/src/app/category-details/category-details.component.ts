import { Component, OnInit } from '@angular/core';
import { Category } from '../category';
import { CategoryService } from '../category.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-category-details',
  templateUrl: './category-details.component.html',
  styleUrls: ['./category-details.component.css']
})
export class CategoryDetailsComponent implements OnInit {
  id: number;
  category: Category;

  constructor(
        private route: ActivatedRoute,
        private router: Router,
        private categoryService: CategoryService) {
      this.id = 0;
      this.category = new Category();
   }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.categoryService.getCategory(this.id).subscribe(
      data => {
        console.log(data)
        this.category = data;
      },
      error => console.log(error)
    );
  }

  list(){
    this.router.navigate(['categories']);
  }

}
