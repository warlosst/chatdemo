import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private http: HttpClient) { }

  headers:any = new HttpHeaders({'Content-Type':'application/json; charset=utf-8'});
  url: string = "http://localhost:8080/";

  authenticate(user:any): Observable<any>{
    return this.http.post(this.url+"authentication",JSON.stringify(user),
      {headers: this.headers});
  }

  isLoggedIn(){
    return !!localStorage.getItem("token");
  }
  getToken(){
    return localStorage.getItem("token");
  }
  logOut(){
    localStorage.clear();
  }
}
