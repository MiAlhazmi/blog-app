import { AbstractControl, ValidationErrors, ValidatorFn } from "@angular/forms";


export const matchPassword: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
	let password = control.get('password');
	let confirmPassword = control.get('confirmPassword');
	return password
		&& confirmPassword
		&& password?.value
		!= confirmPassword?.value
		? { passwordMatchError: true }
		: null;
}