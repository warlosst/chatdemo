import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {RoomService} from "../services/room.service";
import {interval} from "rxjs";
import {Message} from "../interfaces/Message";

@Component({
  selector: 'app-main-body',
  templateUrl: './main-body.component.html',
  styleUrls: ['./main-body.component.css']
})
export class MainBodyComponent implements OnInit {

  public messages: Message[] = [];
  public roomId: number | undefined;
  displayPopUp: boolean = false;
  constructor(private route: ActivatedRoute, private router: Router, private roomService: RoomService) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnInit(): void {
    let id:number = parseInt(<string>this.route.snapshot.paramMap.get('id'));
    this.roomId = id;
    this.getMessages();

  }
  popUp(){
    this.displayPopUp = !this.displayPopUp;
  }
  getMessages(){
    if(this.roomId){
      const source = interval(1000);
      source.subscribe(() =>
        this.roomService.getMessagesByRoomId(this.roomId)
          .subscribe(data => this.messages = data)
      )
    }
  }

}
