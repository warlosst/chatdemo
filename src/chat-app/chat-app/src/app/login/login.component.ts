import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AuthenticationService} from "../services/authentication.service";


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private route: Router, private authorizationService: AuthenticationService) { }

  ngOnInit(): void {
  }
  user ={
    username:'',
    password:''
  }
  authorization = {
    jwt:'',
    id:''
  }

  onSubmit() {
    this.authorizationService.authenticate(this.user)
        .subscribe(data =>
        {
          this.authorization = data;
          localStorage.setItem('token',data.jwt);
          localStorage.setItem('id',data.id);
          this.route.navigate(["/home"]);
        });
  }
}
