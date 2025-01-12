import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LayoutComponent } from './component/layout/layout.component';
import { SignupComponent } from './component/signup/signup.component';
import { PageNotFoundComponent } from './component/page-not-found/page-not-found.component';
import { LoginComponent } from './component/login/login.component';
import { LandingComponent } from './component/pages/landing/landing.component';
import { FeedComponent } from './component/pages/feed/feed.component';
import { PostCreationComponent } from './post-creation/post-creation.component';
import { ProfileComponent } from './component/pages/profile/profile.component';
import { EditProfileComponent } from './component/pages/edit-profile/edit-profile.component';

const routes: Routes = [
	{ path: '', component: LandingComponent },
	{ path: 'signup', component: SignupComponent },
	{ path: 'login', component: LoginComponent },
	{
		path: 'home', component: LayoutComponent,
		children: [
			{ path: 'feed', component: FeedComponent },
			{ path: 'post', component: PostCreationComponent },
			{
				path: 'profile', component: ProfileComponent,
				children: [
					{ path: 'edit', component: EditProfileComponent }
				]
			}
		]
	},
	{ path: '**', component: PageNotFoundComponent }
];

@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule { }
