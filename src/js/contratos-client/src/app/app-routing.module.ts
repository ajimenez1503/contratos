import { CategoryDetailsComponent } from './category-details/category-details.component';
import { CreateCategoryComponent } from './create-category/create-category.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CategoryListComponent } from './category-list/category-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'category', pathMatch: 'full' },
  { path: 'categories', component: CategoryListComponent },
  { path: 'add', component: CreateCategoryComponent },
  { path: 'details/:id', component: CategoryDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
