import {Room} from "./Room";
import {User} from "./User";

export interface Message{
  id: number,
  message: string,
  room: Room,
  user: User
}
