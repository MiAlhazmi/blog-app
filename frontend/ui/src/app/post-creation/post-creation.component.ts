import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AngularEditorConfig } from '@kolkov/angular-editor';
import { Post } from 'src/app/class/post';
import { BackendService } from 'src/app/service/backend.service';

@Component({
	selector: 'app-post-creation',
	template: `
	<h2>Your post subject?</h2>
	<div class="form">
		<div class="input-box">
			<input type="text" placeholder="Write subject" (ngModel)="post.content.subject">
		</div>
	</div>
	<h2>add some hashtags?</h2>
	<div class="form">
		<div class="input-box">
			<input type="text" placeholder="Write tag" (ngModel)="post.hashTags">
		</div>
	</div>
	<h2>Post some shit</h2>
	<div class="container">
		<angular-editor id="editor1" [(ngModel)]="htmlContent" [config]="editorConfig" (ngModelChange)="onChange($event)"(blur)="onBlur($event)">
				<!-- <ng-template #customButtons let-executeCommandFn="executeCommandFn">
					<ae-toolbar-set>
						<ae-button iconClass="fa fa-html5" title="Angular editor logo" (buttonClick)="executeCommandFn('insertHtml')"></ae-button>
					</ae-toolbar-set>
				</ng-template> -->
		</angular-editor>
		<p class="html">HTML Output:  {{ htmlContent }}</p>
		<button class="post-button" (click)="_createPost()">Post</button>
	</div>
	`,
	styles: [`
	.form {
		display: flex;
		flex-direction: column;
        align-items: center;
	}
	
	h2 {
		margin-left: 6rem;
		margin-top: 1.5rem;
		font-size: 19px;
		display: flex;
		justify-content: start;
		align-items: start;
		flex-direction: row;
	}

	.form .input-box {
		/* margin-bottom: 15px; */
		margin-top: 1.5rem;
		margin-left: -24rem;
		width: calc(100% / 2 - 20px);
	}
	.input-box input {
		height: 70px;
		width: 100%;
		outline: none;
		font-size: 16px;
		border-radius: 5px;
		padding-left: 15px;
		border: 1px solid #E4E9EC;
		border-bottom-width: 2px;
		transition: all 0.3s ease;
	}

	.input-box input::placeholder {
		color: #6b7479;
	}

	input[type="text"] {
		background-color: #E4E9EC;
	}
	.container{
		margin-top: 1rem;
		display: flex;
		justify-content: center;
		align-items: center;
		flex-direction: column;
		/* height: 100vh; */
		/* padding-top: 2rem; */
		/* max-width: 1500px; */
		margin-left: 100px;
		overflow: hidden;
	}
	.post-button {
        margin-top: 1rem;
        padding: 0.5rem 1rem;
        background-color: #729FCF;
        color: white;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }
	`]
})
export class PostCreationComponent implements OnInit {

	public form!: FormGroup;
	public htmlContent: string = '';
	public userId: string = '';
	public post!: Post;

	public editorConfig: AngularEditorConfig = {
		editable: true,
		spellcheck: true,
		translate: 'no',
		// minHeight: '40rem',
		// maxHeight: '41rem',
		height: '50rem',
		width: 'auto',
		placeholder: 'Enter text here...',
		enableToolbar: true,
		showToolbar: true,
		toolbarPosition: 'top',
		sanitize: false,
		// toolbarPosition: 'top',
		outline: true,
		defaultFontName: 'Comic Sans MS',
		defaultFontSize: '5',
		// showToolbar: false,
		defaultParagraphSeparator: 'p',
		customClasses: [
			{
				name: 'quote',
				class: 'quote',
			},
			{
				name: 'redText',
				class: 'redText'
			},
			{
				name: 'titleText',
				class: 'titleText',
				tag: 'h1',
			},
		],
		toolbarHiddenButtons: [
			['bold', 'italic'],
			['fontSize']
		]
	};

	constructor(private formBuilder: FormBuilder, private _service: BackendService) { }

	ngOnInit() {
		this.form = this.formBuilder.group({
			signature: ['', Validators.required]
		});
		console.log(this.htmlContent);
		this.userId = this._service.getSignedinUserID();
	}

	public onChange(event: any): void {
		console.log('changed');
	}

	public onBlur(event: any): void {
		console.log('blur ' + event);
	}

	public _createPost() {
		this.post.content.content = this.htmlContent;
		this._service.createPost(this.post, this.userId).subscribe({
			next: (resp: any) => console.log(resp),
			error: (err: any) => {
				let e = err['error']
				console.error('HTTP Error', e['message']);
				let message = e['message'];
				// TODO: figure out a way to display error message to the user
			},
			complete: () => {
				console.info('completed');
				// this.router.navigate(['/']);
			}
		})
	}
}