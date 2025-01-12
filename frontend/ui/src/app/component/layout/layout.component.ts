import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BackendService } from 'src/app/service/backend.service';

@Component({
	selector: 'app-layout',
	templateUrl: './layout.component.html',
	styleUrls: ['./layout.component.css']
})
export class LayoutComponent {

	public pageState: string = 'My feed';

	constructor(private router: Router, private _service: BackendService) {

	}

	public onPageStateChanged(data: string): void {
		this.pageState = data;
		console.log("From layout.onPageStateChanged() -> " + this.pageState)
		switch (this.pageState.toLowerCase()) {
			case 'my feed':
				this.router.navigate(['/home/feed']);
				break;
			case 'bookmark':
				this.router.navigate(['/home']);
				break;
			case 'my profile':
				this._service.isUserSignedin()
					? this.router.navigate(['home/profile'])
					: this.router.navigate(['login']);
				break;
			case 'liked posts':
				this.router.navigate(['/home']);
				break;
			case 'settings':
				this.router.navigate(['/home']);
				break;
		}
	}
}
