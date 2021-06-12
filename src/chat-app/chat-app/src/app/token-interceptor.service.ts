import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { AuthenticationService } from "./services/authentication.service";
import { Observable } from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  constructor(private authService: AuthenticationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    if(req.url.includes('/auth')|| (req.url.includes('/api/clients')&&req.method==="POST"))
    {
      const headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
      const reqWithAuth = req.clone(
        {
          headers
        }
      );
      return next.handle(reqWithAuth);

    }
    else{
      const token = this.authService.getToken();
      const headers = new HttpHeaders({
        'Authorization': 'Bearer '+token,
        'Content-Type': 'application/json'
      });
      const reqWithAuth = req.clone(
        {
          headers
        }
      );
      return next.handle(reqWithAuth);
    }
  }

}
