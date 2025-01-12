import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { User } from '../class/user';
import { Observable, map } from 'rxjs';
import { Post } from '../class/post';
import { Profile } from '../class/profile';
@Injectable({
	providedIn: 'root'
})
export class BackendService {

	private url: string = 'http://localhost:8080';
	private _auth_api: string = `${this.url}/api/v1/auth`
	private _user_api: string = `${this.url}/api/v1/user`
	private _post_api: string = `${this.url}/api/v1/post`
	private _profile_api: string = `${this.url}/api/v1/profile`

	private headers: HttpHeaders = new HttpHeaders({
		'Content-Type': 'application/json',
		'Authorization': this.getToken()
	});

	constructor(private _http: HttpClient, private router: Router) { }

	public createProfile(profile: Profile, id: string): Observable<Profile> {
		return this._http.post<Profile>(`${this._profile_api}/create/${id}`, profile, {
			headers: new HttpHeaders(
				{
					'Content-Type': 'application/json',
					'Authorization': this.getToken()
				}
			)
		});
	}

	public updateProfile(profile: Profile, id: string): Observable<Profile> {
		return this._http.patch<Profile>(`${this._profile_api}/update/${id}`, profile, {
			headers: new HttpHeaders(
				{
					'Content-Type': 'application/json',
					'Authorization': this.getToken()
				}
			)
		});
	}

	public getProfile(id: string): Observable<Profile> {
		return this._http.get<Profile>(`${this._profile_api}/get/${id}`, {
			headers: new HttpHeaders(
				{
					'Content-Type': 'application/json',
					'Authorization': this.getToken()
				}
			)
		});
	}
	
	public getPosts(): Observable<Post[]> {
		return this._http.get<Post[]>(`${this._post_api}/get`, {
			headers: new HttpHeaders(
				{
					'Content-Type': 'application/json',
					'Authorization': this.getToken()
				}
			)
		});
	}

	public createPost(post: Post, id: string): Observable<Post> {
		return this._http.post<Post>(`${this._post_api}/create/${id}`, post, {
			headers: new HttpHeaders(
				{
					'Content-Type': 'application/json',
					'Authorization': this.getToken()
				}
			)
		});
	}

	public followProfile(profileId: number, userId: number): Observable<any> {
		return this._http.post<any>(`${this._user_api}/${userId}/follow/profile/${profileId}`, {}, {
			headers: new HttpHeaders(
				{
					'Content-Type': 'application/json',
					'Authorization': this.getToken()
				}
			)
		});
	}

	public unfollowProfile(profileId: number, userId: number): Observable<any> {
		return this._http.post<any>(`${this._user_api}/${userId}/unfollow/profile/${profileId}`, {}, {
			headers: new HttpHeaders(
				{
					'Content-Type': 'application/json',
					'Authorization': this.getToken()
				}
			)
		});
	}

	public likePost(postId: string, userId: string): Observable<any> {
		console.log(this.getToken())
		return this._http.post<any>(`${this._user_api}/${userId}/like/post/${postId}`, {}, {
			headers: new HttpHeaders(
				{
					'Content-Type': 'application/json',
					'Authorization': this.getToken()
				}
			)
		});
	}

	public dislikePost(postId: string, userId: string): Observable<any> {
		return this._http.post<any>(`${this._user_api}/${userId}/dislike/post/${postId}`, {}, {
			headers: new HttpHeaders(
				{
					'Content-Type': 'application/json',
					'Authorization': this.getToken()
				}
			)
		});
	}

	public followHashtag(tagId: number, userId: number): Observable<any> {
		return this._http.post<any>(`${this._user_api}/${userId}/follow/hashtag/${tagId}`, {}, {
			headers: new HttpHeaders(
				{
					'Content-Type': 'application/json',
					'Authorization': this.getToken()
				}
			)
		});
	}

	public unfollowHashtag(tagId: number, userId: number): Observable<any> {
		return this._http.post<any>(`${this._user_api}/${userId}/unfollow/hashtag/{tagId}`, {}, {
			headers: new HttpHeaders(
				{
					'Content-Type': 'application/json',
					'Authorization': this.getToken()
				}
			)
		});
	}

	public getFollowHashtag(userId: number): Observable<any> {
		return this._http.get<any>(`${this._user_api}/${userId}/followedTags`, {
			headers: new HttpHeaders(
				{
					'Content-Type': 'application/json',
					'Authorization': this.getToken()
				}
			)
		});
	}

	public getFollowProfiles(userId: number): Observable<any> {
		return this._http.get<any>(`${this._user_api}/${userId}/followedProfiles`, {
			headers: new HttpHeaders(
				{
					'Content-Type': 'application/json',
					'Authorization': this.getToken()
				}
			)
		});
	}

	public getLikedPosts(userId: number): Observable<any> {
		return this._http.get<any>(`${this._user_api}/${userId}/likedPosts`, {
			headers: new HttpHeaders(
				{
					'Content-Type': 'application/json',
					'Authorization': this.getToken()
				}
			)
		});
	}
	public userSignup(user: User): Observable<any> {
		return this._http.post<any>(`${this._auth_api}/signup`, user, {
			headers: new HttpHeaders(
				{ 'Content-Type': 'application/json' }
			)
		});
	}

	public userLogin(user: any): Observable<any> {
		console.log(user);
		return this._http.post<any>(`${this._auth_api}/login`, user, {
			headers: new HttpHeaders(
				{ 'Content-Type': 'application/json' }
			)
		}).pipe(map((resp) => {
			console.log(resp);
			const userID = resp.data.id;
			const username = resp.data.username;
			let token: string = 'Bearer ' + resp.data.jwt;
			const role = resp.data.role;

			// Store values in local storage
			localStorage.setItem('user', username);
			localStorage.setItem('token', token);
			localStorage.setItem('id', userID);
			localStorage.setItem('user_type', role);

			// You can return any additional data or simply indicate success
			return resp;
		}));

		//   {
		//     "data": {
		//         "username": "Shob",
		//         "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjpbeyJhdXRob3JpdHkiOiJVU0VSX1JPTEUifV0sInVzZXJuYW1lIjoiU2hvYiIsInN1YiI6IlNob2IiLCJpYXQiOjE2ODc2MzA1NDQsImV4cCI6MTY4NzcxNjk0NH0.ONbKBoZBSNiXQDFGNxPYW1n-QQjVGIB6knmv8rpZW2E",
		//         "email": "shob@gmail.com",
		//         "role": "USER_ROLE",
		//         "id": 1
		//     },
		//     "message": "Success"
		// }
	}
	public logOut(): void {
		// if user log out, redirect to /login page
		this.router.navigate(['/login']);
		// then delete session cache in browser
		// NOTE: most browsers give you 10MB of storage for sessions
		// 10 MB should be enough
		localStorage.removeItem('user');
		localStorage.removeItem('token');
		localStorage.removeItem('id');
		localStorage.removeItem('user_type');
	}

	// if token isn't found, user is not signed in
	public isUserSignedin(): boolean {
		return localStorage.getItem('token') !== null;
	}

	// self Explanatory
	public getSignedinUserID(): string {
		return localStorage.getItem('id') as string;
	}

	// self Explanatory
	public setSessionID(id: string) {
		localStorage.setItem('id', id);
	}

	// self Explanatory
	public getSignedinUsername(): string {
		return localStorage.getItem('user') as string;
	}

	// self Explanatory
	public getToken(): string {
		return localStorage.getItem('token') as string;
	}

	// self Explanatory
	public getActiveUserType(): string {
		return localStorage.getItem('user_type') as string;
	}
}
