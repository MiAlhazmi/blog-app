import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { BackendService } from 'src/app/service/backend.service';

interface LoginRequest {
	username: string,
	password: string
}
@Component({
	selector: 'app-login',
	templateUrl: './login.component.html',
	styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
	public loginRequest: LoginRequest = {
		username: '',
		password: ''
	};
	public form!: FormGroup;
	public username!: FormControl;
	public password!: FormControl;
	private isFormSubmitted: boolean = false;

	constructor(private _service: BackendService, private router: Router) { }

	ngOnInit(): void {
		console.log("Login Component for blog user");
		this.createControls();
		this.createForm();
	}

	public createControls(): void {
		this.username = new FormControl('', Validators.required);
		this.password = new FormControl('', Validators.required);
	}

	public createForm(): void {
		this.form = new FormGroup({
			username: this.username,
			password: this.password,
		});
	}

	public login(): void {
		this.isFormSubmitted = true;
		if (this.username.value == null ||
			this.password.value == null
		) {
		} else {
			this.loginRequest.username = this.username.value;
			this.loginRequest.password = this.password.value;
		}
		console.log(this.loginRequest)
		this._service.userLogin(this.loginRequest).subscribe({
			next: (resp: any) => console.log(resp),
			error: () => {
				console.error('HTTP Error');
				// TODO: figure out a way to display error message to the user
			},
			complete: () => {
				console.info('completed');
				this.router.navigate(['/home']);
			}
		})
	}
}
