import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { Post } from 'src/app/class/post';
import { Profile } from 'src/app/class/profile';
import { BackendService } from 'src/app/service/backend.service';
import { DesignFunctionalitiesService } from 'src/app/service/design-functionalities.service';

@Component({
	selector: 'app-profile',
	templateUrl: './profile.component.html',
	styleUrls: ['./profile.component.css']
})
export class ProfileComponent {
	@Input() sharedProfile!: Profile;
	public profile: Profile = new Profile();
	public post?: Post;
	public posts: Post[] = [];
	private userId: string = '';
	public username: string = '';
	public isLoggedIn!: boolean;


	constructor(private _service: BackendService, private _designService: DesignFunctionalitiesService, private router: Router) { }

	ngOnInit(): void {
		this.isLoggedIn = this._service.isUserSignedin();
		this.userId = this._service.getSignedinUserID();
		this.username = this._service.getSignedinUsername();
		this.getProfile();
	}

	public openPopup() {
		// let popup = document.getElementById(elementId);
		let popup = document.getElementById('edit-profile-container');
		// popup!.style.cssText = 'visibility: hidden;'
		// popup?.classList.add("open-popup");
		popup!.style.cssText += 'visibility: visible;';
		this.router.navigate(['home/profile/settings/profile'])

	}

	public navigateToEdit() {
		this.router.navigate(['/home/profile/edit']);
	}
	public closePopup() {
		let popup = document.getElementById('edit-profile-container');
		// popup?.classList.remove("open-popup");
		popup!.style.cssText += 'visibility: hidden;';
	}

	private getProfile() {
		console.log("profile: " + this.profile.id + this.profile.userTag + this.profile.bio);
		this._service.getProfile(this.userId).subscribe({
			next: (resp: any) => {
				console.log("resp: " + resp.userTag);
				this.profile = resp.data;
				this.sharedProfile = this.profile;
				console.log("profile: " + this.profile.id + this.profile.userTag + this.profile.bio);
			},
			error: (err: any) => {
				let e = err['error']
				console.error('HTTP Error', e['message']);
				let message = e['message'];
				// TODO: figure out a way to display error message to the user
			},
			complete: () => {
				console.info('completed');
			}
		})
	}
}
