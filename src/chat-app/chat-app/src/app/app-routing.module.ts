import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from "./login/login.component";
import { MainComponent } from "./main/main.component";
import { RegisterComponent } from "./register/register.component";
import { PageNotFoundComponent } from "./page-not-found/page-not-found.component";
import { MainBodyComponent } from "./main-body/main-body.component";
import { AuthGuard } from "./auth.guard";

const routes: Routes = [
  {
    path:"",
    redirectTo:"/home",
    pathMatch: "full"
  },
  {
    path:"login",
    component: LoginComponent
  },
  {
    path:"home",
    component: MainComponent,
    canActivate:[AuthGuard]
  },
  {
    path:"home/:id",
    component: MainComponent,
    canActivate:[AuthGuard]
  },
  {
    path:"register",
    component: RegisterComponent
  },
  {
    path:"**",
    component:PageNotFoundComponent
  }
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents =
    [ LoginComponent,
      MainComponent,
      RegisterComponent,
      MainBodyComponent,
    PageNotFoundComponent];
