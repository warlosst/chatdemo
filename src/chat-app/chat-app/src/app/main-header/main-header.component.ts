import {Component, OnInit, Output} from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { Room } from "../interfaces/Room";
import { RoomService } from "../services/room.service";
import { AuthenticationService } from "../services/authentication.service";

var roomName = "";

@Component({
  selector: 'app-main-header',
  templateUrl: './main-header.component.html',
  styleUrls: ['./main-header.component.css']
})
export class MainHeaderComponent implements OnInit {



  constructor(private router:Router,
              private roomService: RoomService,
              private activatedRoute: ActivatedRoute,
              private authService: AuthenticationService) {
  }
  roomname:string = roomName;
  rooms: Room[] = [];
  displayNav: boolean = false;
  displayChannels: boolean = true;


  ngOnInit(): void {
    this.roomService.getRooms().subscribe(data => {
      this.rooms = data;
    });
  }

  toggleNav() {
    this.displayNav = !this.displayNav;
  }

  toggleChannel() {
    this.displayChannels = !this.displayChannels;
  }

  navigate(room: { id: number; name: string}) {
    roomName = room.name;
    this.roomService.nextMessage(roomName);
    this.router.navigate(['/home',room.id]);
  }

  logOut() {
    this.authService.logOut();
    this.router.navigate(['/login']);
  }
}
