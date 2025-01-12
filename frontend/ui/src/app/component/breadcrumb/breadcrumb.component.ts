import { Component, Input, OnInit } from '@angular/core';
import { BreadcrumbService } from 'src/app/service/breadcrumb.service';

@Component({
	selector: 'app-breadcrumb',
	template: `
	<div class="breadcrumb">
  		<span>app / {{ clickedIconText }}</span>
	</div>
	`,
	styleUrls: ['./breadcrumb.component.css']
})
export class BreadcrumbComponent implements OnInit {
	public clickedIconText: string = '';

	constructor(private breadcrumbService: BreadcrumbService) { }

	ngOnInit() {
		this.breadcrumbService.clickedIconText$.subscribe((text) => {
			this.clickedIconText = text;
		});
		console.log(this.clickedIconText)
	}
}