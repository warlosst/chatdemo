import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { RegisterComponent } from './register/register.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MainHeaderComponent } from './main-header/main-header.component';
import { MainBodyComponent } from './main-body/main-body.component';
import { MainFooterComponent } from './main-footer/main-footer.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AuthGuard } from "./auth.guard";
import { TokenInterceptorService } from "./token-interceptor.service";


@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    RegisterComponent,
    PageNotFoundComponent,
    MainHeaderComponent,
    MainBodyComponent,
    MainFooterComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    [FormsModule],
    AppRoutingModule,
    ReactiveFormsModule

  ],
  providers: [AuthGuard,{
    provide: HTTP_INTERCEPTORS,
    useClass: TokenInterceptorService,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
