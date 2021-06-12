import { Component } from '@angular/core';
import { Router } from "@angular/router";
import { AuthenticationService } from "../services/authentication.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  user ={
    username:'',
    password:''
  }
  authorization = {
    jwt:'',
    id:''
  }

  constructor(private router: Router,
              private authorizationService: AuthenticationService) { }

  onSubmit() {
    this.authorizationService.authenticate(this.user)
        .subscribe(data =>
        {
          this.authorization = data;
          localStorage.setItem('token',data.jwt);
          localStorage.setItem('id',data.id);
          this.router.navigate(["/home"]);
        });
  }
}
