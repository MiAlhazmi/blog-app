import { Component, EventEmitter, Output } from '@angular/core';
import { faSquareRss, faUser, faBookmark, faHeart, faBars, IconDefinition, faHashtag } from '@fortawesome/free-solid-svg-icons';
import { BreadcrumbService } from 'src/app/service/breadcrumb.service';
@Component({
	selector: 'app-sidebar',
	templateUrl: './sidebar.component.html',
	styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
	public feed: IconDefinition = faSquareRss;
	public profile: IconDefinition = faUser;
	public bookmark: IconDefinition = faBookmark;
	public like: IconDefinition = faHeart;
	public hashtag: IconDefinition = faHashtag;
	public more: IconDefinition = faBars;
	public iconClickedText = '';
	@Output()
	public iconTextChanged: EventEmitter<string> = new EventEmitter<string>;

	constructor(private breadcrumbService: BreadcrumbService) { }

	public onIconClick(iconText: string): void {
		this.breadcrumbService.setClickedIconText(iconText);
		console.log("from sidebar, clicked icon is :", iconText);

		this.iconClickedText = iconText;
		this.onIconTextChanged();
	}

	private onIconTextChanged(): void {
		this.iconTextChanged.emit(this.iconClickedText);
	}
}
