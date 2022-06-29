import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { CreateAgreementComponent } from './create-agreement/create-agreement.component';
import { CategoryListComponent } from './category-list/category-list.component';
import { AgreementDetailsComponent } from './agreement-details/agreement-details.component';
import { AgreementListComponent } from './agreement-list/agreement-list.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';


@NgModule({
  declarations: [
    AppComponent,
    CreateAgreementComponent,
    CategoryListComponent,
    AgreementDetailsComponent,
    AgreementListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
