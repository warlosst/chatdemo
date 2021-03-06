import {
  Component,
  ElementRef, OnDestroy,
  OnInit,
  ViewChild
} from '@angular/core';
import { ActivatedRoute, Router } from "@angular/router";
import { RoomService } from "../services/room.service";
import {interval, Subscription} from "rxjs";
import { Message } from "../interfaces/Message";

@Component({
  selector: 'app-main-body',
  templateUrl: './main-body.component.html',
  styleUrls: ['./main-body.component.css']
})
export class MainBodyComponent implements OnInit,OnDestroy {

  myIntervalSubscription: Subscription | undefined ;
  @ViewChild('scroll') myScrollContainer: ElementRef | undefined;
  messages: Message[] = [];
  roomId: number | undefined;
  displayPopUp: boolean = false;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private roomService: RoomService) {
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
  }

  ngOnDestroy(): void {
    if (this.myIntervalSubscription) {
      this.myIntervalSubscription.unsubscribe();
    }
    }

  ngOnInit(): void {
    let id:number = parseInt(<string>this.route.snapshot.paramMap.get('id'));
    this.roomId = id;
    this.getMessages();
    setTimeout(()=>{this.scrollToBottom()},200);
  }


  popUp(){
    this.displayPopUp = !this.displayPopUp;
  }
  getMessages(){

    if(this.roomId){

      this.myIntervalSubscription = interval(500).subscribe(() => {
          this.roomService.getMessagesByRoomId(this.roomId)
            .subscribe(data => {
                if (data.length !== this.messages.length) {
                  this.messages = data;
                  setTimeout(() => {
                    this.scrollToBottom()
                  }, 0)
                }
              }
            )

        }
      )



    }
  }

  scrollToBottom(): void {
    try {
      if(this.myScrollContainer) {
        this.myScrollContainer.nativeElement.scrollTop =
          this.myScrollContainer.nativeElement.scrollHeight;
      }
    } catch(err) { }
  }


}
