import { Component } from '@angular/core';
import { ClientService } from "../services/client.service";
import { Router } from "@angular/router";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['../login/login.component.css']
})
export class RegisterComponent  {

  message:string |undefined;
  user = {
    username : '',
    email: '',
    password:'',
    repeatPassword:''
  }

  constructor(private clientService: ClientService,
              private router: Router) { }

  onSubmit() {
    if(this.user.password===this.user.repeatPassword){
        this.clientService.registerClient(this.user).subscribe(
          () => this.router.navigate(["/login"]) );
    }
    else{
      this.message = "Password and Repeat password do not match. Please write it again carefully!"
    }
  }
}
