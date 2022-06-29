import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { CreateAgreementComponent } from './create-agreement/create-agreement.component';
import { CategoryDetailsComponent } from './category-details/category-details.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { AgreementDetailsComponent } from './agreement-details/agreement-details.component';
import { AgreementListComponent } from './agreement-list/agreement-list.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateAgreementComponent,
    CategoryDetailsComponent,
    CategoryListComponent,
    AgreementDetailsComponent,
    AgreementListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
