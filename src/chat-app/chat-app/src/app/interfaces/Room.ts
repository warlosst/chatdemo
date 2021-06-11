import {User} from "./User";

export interface Room{
  id: number,
  name: string,
  userList: Array<User>
}
