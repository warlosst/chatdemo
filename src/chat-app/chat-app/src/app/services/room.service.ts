import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from 'rxjs';
import {Room} from '../interfaces/Room';
import {Message} from "../interfaces/Message";


@Injectable({
  providedIn: 'root'
})
export class RoomService {

  url: string = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  private message = new BehaviorSubject('');
  sharedMessage = this.message.asObservable();

  nextMessage(message: string) {
    this.message.next(message)
  }

  getRooms(): Observable<Room[]>{
    return this.http.get<Room[]>(this.url+"api/rooms");
  }

  getMessagesByRoomId(roomId: number | undefined): Observable<Message[]>{
    return this.http.get<Message[]>(this.url+"api/rooms/"+roomId+"/messages");
  }

  postMessage(message: any): Observable<Message>{
    return this.http.post<Message>(this.url+"api/messages",JSON.stringify(message));
  }

}
