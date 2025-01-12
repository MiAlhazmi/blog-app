import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IconDefinition, faComment, faHeart } from '@fortawesome/free-regular-svg-icons';
import { faChartSimple, faHeartCrack, faPen } from '@fortawesome/free-solid-svg-icons';
import { Post } from 'src/app/class/post';
import { BackendService } from 'src/app/service/backend.service';

@Component({
	selector: 'app-feed',
	templateUrl: './feed.component.html',
	styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {
	public like: IconDefinition = faHeart;
	public dislike: IconDefinition = faHeartCrack;
	public views: IconDefinition = faChartSimple;
	public comment: IconDefinition = faComment;
	public pen: IconDefinition = faPen;
	// public more: IconDefinition = faBars;
	public posts: Post[] = [];
	public post?: Post;
	private userId: string = '';

	constructor(private _service: BackendService, private router: Router) { }

	ngOnInit(): void {
		this.fetchPosts();
		this.userId = this._service.getSignedinUserID();
	}

	public gotoEditor(): void {
		this.router.navigate(['/home/post'])
	}

	private fetchPosts(): void {
		this._service.getPosts().subscribe({
			next: (resp: Post[]) => {
				console.log(resp);
				this.posts = resp;
				console.log("resp: " + resp[0].user?.username);
			},
			error: (err: any) => {
				console.error(err);
			},
			complete: () => {
				console.info('completed');
				console.log(this.posts);
			}
		});
	}

	private getClickedPost(id: string): Post {
		console.log("id from click", id);
		// this map conversion should happen either in a service or under onInit()
		// TODO: fix this shit
		const postMap = new Map<string, Post>();
		this.posts.forEach(p => postMap.set(p.id, p));
		// get returns val | undefined, cast the return to val
		this.post = postMap.get(id) as Post;
		if (this.post) {
			console.log("clicked post = ", this.post);
		} else {
			console.log(`post with id ${id} not found`);
		}
		return this.post;
	}

	private incrementLikeCount(id: string): void | null {
		let p = this.getClickedPost(id);
		p ? p.likes++ : null;
	}

	private incrementDislikeCount(id: string): void | null {
		let p = this.getClickedPost(id);
		p ? p.dislikes++ : null;
	}

	private decrementLikeCount(id: string): void | null {
		let p = this.getClickedPost(id);
		p ? p.likes-- : null;
	}

	private decrementDislikeCount(id: string): void | null {
		let p = this.getClickedPost(id);
		p ? p.dislikes-- : null;
	}

	public _likePost(id: string): void {
		this._service.likePost(id, this.userId).subscribe({
			next: (resp: any) => {
				console.log(resp);
			},
			error: (err: any) => {
				console.error(err);
			},
			complete: () => {
				console.info('completed');
				this.incrementLikeCount(id);
				this.decrementDislikeCount(id);
			}
		});
	}

	public _dislikePost(id: string): void {
		this._service.dislikePost(id, this.userId).subscribe({
			next: (resp: any) => {
				console.log(resp);
			},
			error: (err: any) => {
				console.error(err);
			},
			complete: () => {
				console.info('completed');
				this.incrementDislikeCount(id);
				this.decrementLikeCount(id);
			}
		});
	}
}
