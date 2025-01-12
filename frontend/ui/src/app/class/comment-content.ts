import { User } from "./user";

export class CommentContent {
	id: string = '';
	content: string = '';
	user?: User;
}
