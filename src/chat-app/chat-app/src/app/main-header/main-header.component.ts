import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {Room} from "../interfaces/Room";
import {RoomService} from "../services/room.service";
import {AuthenticationService} from "../services/authentication.service";

@Component({
  selector: 'app-main-header',
  templateUrl: './main-header.component.html',
  styleUrls: ['./main-header.component.css']
})
export class MainHeaderComponent implements OnInit {
  constructor(private router:Router, private roomService: RoomService, private authService: AuthenticationService) { }
  public rooms: Room[] = [];
  ngOnInit(): void {
    this.roomService.getRooms().subscribe(data => this.rooms = data);
  }


  displayNav: boolean = false;
  displayChannels: boolean = true;

  toggleNav() {
    this.displayNav = !this.displayNav;
  }

  toggleChannel() {
    this.displayChannels = !this.displayChannels;
  }

  navigate(room: { id: number; }) {
    this.router.navigate(['/home',room.id]);

  }

  logOut() {
    this.authService.logOut();
    this.router.navigate(['/login']);
  }
}
