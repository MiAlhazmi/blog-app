import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class BreadcrumbService {

	private clickedIconTextSubject = new BehaviorSubject<string>('');
	clickedIconText$ = this.clickedIconTextSubject.asObservable();

	public setClickedIconText(text: string) {
		this.clickedIconTextSubject.next(text);
	}
}
