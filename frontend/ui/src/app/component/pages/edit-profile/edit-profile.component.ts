import { Component, OnInit } from '@angular/core';
import { Post } from 'src/app/class/post';
import { Profile } from 'src/app/class/profile';
import { BackendService } from 'src/app/service/backend.service';
import { ProfileComponent } from '../profile/profile.component';
import { Router } from '@angular/router';

@Component({
	selector: 'app-edit-profile',
	templateUrl: './edit-profile.component.html',
	styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
	public profile: Profile = new Profile();
	private userId: string = '';

	constructor(private _service: BackendService, private router: Router, private _profile: ProfileComponent) { }

	ngOnInit(): void {
		this.profile = this._profile.sharedProfile;
		console.log("hey this is edit profile", this.profile);
		this.userId = this._service.getSignedinUserID();
	}

	public updateProfile(profile: Profile) {
		console.log(profile);
		this._service.updateProfile(this.profile, this.userId).subscribe({
			next: (resp: Profile) => console.log(resp),
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
		this.router.navigate(['home/profile'])
		// close popup and refresh
	}

	public refreshPage() {
		window.location.reload();
	}

	public close() {
		this.router.navigate(['home/profile'])
	}


}