import { BlogContent } from "./blog-content";
import { CommentContent } from "./comment-content";
import { Hashtag } from "./hashtag";
import { User } from "./user";

export class Post {
	id: string = "";
	user?: User;
	content!: BlogContent;
	comments: CommentContent[] = [];
	hashTags: Hashtag[] = [];
	likes!: number;
	dislikes!: number;
	views!: number ;
}