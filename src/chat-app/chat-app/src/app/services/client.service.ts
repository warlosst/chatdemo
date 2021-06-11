import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from 'rxjs';
import {User} from "../interfaces/User";

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  constructor(private http: HttpClient) { }
  url: string = "http://localhost:8080/";

  registerClient(user:any): Observable<User>{
    return this.http.post<User>(this.url+"api/clients",JSON.stringify(user));
  }
}
