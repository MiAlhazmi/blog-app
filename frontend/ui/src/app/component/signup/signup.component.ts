import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/class/user';
import { BackendService } from 'src/app/service/backend.service';
import { matchPassword } from './password.validator';
@Component({
	selector: 'app-signup',
	templateUrl: './signup.component.html',
	styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
	private user: User = new User();
	public form!: FormGroup;
	public username!: FormControl;
	public firstName!: FormControl;
	public lastName!: FormControl;
	public age!: FormControl;
	public gender!: FormControl;
	public email!: FormControl;
	public password!: FormControl;
	public passwordConfirm!: FormControl;
	private isFormSubmitted: boolean = false;
	public message!: string;
	private REGEX_EMAIL: string = `[a-z0-9-_]+@[a-z0-9.]+[a-z]$`;
	private REGEX_AGE: string = `^[0-9]{2}$`;
	private REGEX_OTHER: string = `^[a-zA-Z ]{1,50}$`;
	private PASS_HACK: string = '';

	constructor(private _service: BackendService, private router: Router) { }

	ngOnInit(): void {
		console.log("is this initialized????")
		this.createControls();
		this.createForm();
		this.passwordMatchValidator();
	}

	private passwordMatchValidator(): void {
		this.password.valueChanges.subscribe((value) => {
			this.PASS_HACK = `^${value}$`; // regex validation
			this.passwordConfirm.setValidators([
				Validators.required,
				Validators.pattern(this.PASS_HACK)
			]);
			this.passwordConfirm.updateValueAndValidity();
		});
	}

	private createControls() {
		this.firstName = new FormControl('', Validators.required);
		this.lastName = new FormControl('', Validators.required);
		this.password = new FormControl('', [
			Validators.required,
			Validators.minLength(4)
		]);
		this.passwordConfirm = new FormControl('', [
			Validators.required,
		]);
		this.email = new FormControl('', [
			Validators.required,
			Validators.pattern(this.REGEX_EMAIL)
		]);
		this.username = new FormControl('', Validators.required);
		this.gender = new FormControl('', Validators.required);
		this.age = new FormControl('', [
			Validators.required,
			Validators.pattern(this.REGEX_AGE)
		]);
	}


	private createForm() {
		this.form = new FormGroup({
			firstName: this.firstName,
			lastName: this.lastName,
			password: this.password,
			email: this.email,
			username: this.username,
			gender: this.gender,
			age: this.age,
			passwordConfirm: this.passwordConfirm,
		});
	}

	public signup(): void {
		this.isFormSubmitted = true;
		if (this.firstName.value == null ||
			this.lastName.value == null ||
			this.password.value == null ||
			this.email.value == null ||
			this.username.value == null ||
			this.age.value == null ||
			this.gender.value == null
		) {
			this.message = "User was not registered, Please enter valid details!"
		} else {
			this.user.firstName = this.firstName.value;
			this.user.lastName = this.lastName.value;
			this.user.email = this.email.value;
			this.user.password = this.password.value;
			this.user.age = this.age.value;
			this.user.gender = this.gender.value;
			this.user.username = this.username.value;
		}
		console.log(this.user)
		this._service.userSignup(this.user).subscribe({
			next: (resp: any) => console.log(resp),
			error: (err: any) => {
				let e = err['error']
				console.error('HTTP Error', e['message']);
				let message = e['message'];
				// TODO: figure out a way to display error message to the user
			},
			complete: () => {
				console.info('completed');
				this.router.navigate(['/']);
			}
		})
	}
}
