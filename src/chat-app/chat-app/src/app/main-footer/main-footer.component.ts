import {Component, OnInit} from '@angular/core';
import { RoomService } from "../services/room.service";
import { PostMessage } from "../interfaces/PostMessage";
import { ActivatedRoute } from "@angular/router";

@Component({
  selector: 'app-main-footer',
  templateUrl: './main-footer.component.html',
  styleUrls: ['./main-footer.component.css']
})
export class MainFooterComponent implements OnInit{

  message: PostMessage ={
    message:"",
    userId: parseInt(<string>localStorage.getItem("id")),
    roomId: parseInt(<string>this.route.snapshot.paramMap.get('id'))
  };
  values = '';
  roomName: string = '';
  constructor(private roomService: RoomService,private route: ActivatedRoute) { }

  onKey(input: HTMLInputElement) {
    if(input.value){
      this.values = input.value;
    }
    else{
      this.values='';
    }
  }


  submitForm(input: HTMLInputElement) {
    this.message.message = input.value;
    this.roomService.postMessage(this.message).subscribe(() => input.value='');

  }

  getStyleList() {
    if (this.values) {
      return {'background-color' : 'lightgreen'};
    } else {
      return {'background-color' : 'white'};
    }
  }

  ngOnInit(): void {
    this.roomService.sharedMessage.subscribe(data =>this.roomName = data)
  }

}

