import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { faSearch, faHashtag, faBell, faUserCircle, faMoon, faSun, IconDefinition, faUserLarge } from '@fortawesome/free-solid-svg-icons';
import { BackendService } from 'src/app/service/backend.service';
@Component({
	selector: 'app-topbar',
	templateUrl: './topbar.component.html',
	styleUrls: ['./topbar.component.css']
})
export class TopbarComponent {
	public search: IconDefinition = faSearch;
	public hashtag: IconDefinition = faHashtag;
	public bell: IconDefinition = faBell;
	public userCircle: IconDefinition = faUserLarge;
	public moon: IconDefinition = faMoon;
	public sun: IconDefinition = faSun;
	public darkTheme: boolean = false;
	public show: boolean = false;
	public menu: any = null;

	constructor(public _service: BackendService, private router: Router) { }

	public handleMode(): void {
		this.darkTheme = !this.darkTheme;
	}

	public handleClick(val: number): void {
		switch (val) {
			case 0:
				this.router.navigate(['login']);
				break;
			case 1:
				this.router.navigate(['signup']);
				break;
			case 2:
				this._service.logOut();
				this.router.navigate(['']);
				break;
		}
	}

	public myfunction() {
		// I want this to close the dropdown when we click anything else
		// todo so we should check if the dropdown is not focused
	}

}
