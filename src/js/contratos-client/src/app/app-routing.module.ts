import { AgreementDetailsComponent } from './agreement-details/agreement-details.component';
import { CreateAgreementComponent } from './create-agreement/create-agreement.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CategoryListComponent } from './category-list/category-list.component';
import { AgreementListComponent } from './agreement-list/agreement-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'category', pathMatch: 'full' },
  { path: 'categories', component: CategoryListComponent },
  { path: 'agreements', component: AgreementListComponent },
  { path: 'add-agreement', component: CreateAgreementComponent },
  { path: 'agreement-details/:id', component: AgreementDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
