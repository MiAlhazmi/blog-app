import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopbarComponent } from './component/topbar/topbar.component';
import { SidebarComponent } from './component/sidebar/sidebar.component';
import { SidebariconComponent } from './component/sidebaricon/sidebaricon.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { LayoutComponent } from './component/layout/layout.component';
import { SignupComponent } from './component/signup/signup.component';
import { LoginComponent } from './component/login/login.component';
import { BreadcrumbComponent } from './component/breadcrumb/breadcrumb.component';
import { FooterComponent } from './component/footer/footer.component';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BackendService } from './service/backend.service';
import { HttpClientModule } from '@angular/common/http';
import { PageNotFoundComponent } from './component/page-not-found/page-not-found.component';
import { LandingComponent } from './component/pages/landing/landing.component';
import { FeedComponent } from './component/pages/feed/feed.component';
import { PostCreationComponent } from './post-creation/post-creation.component';
import { AngularEditorModule } from '@kolkov/angular-editor';
import { ProfileComponent } from './component/pages/profile/profile.component';
import { EditProfileComponent } from './component/pages/edit-profile/edit-profile.component';
@NgModule({
	declarations: [
		AppComponent,
		TopbarComponent,
		SidebarComponent,
		SidebariconComponent,
		LayoutComponent,
		SignupComponent,
		LoginComponent,
		BreadcrumbComponent,
		FooterComponent,
		PageNotFoundComponent,
		LandingComponent,
		FeedComponent,
		PostCreationComponent,
		ProfileComponent,
  EditProfileComponent
	],
	imports: [
		BrowserModule,
		AppRoutingModule,
		FontAwesomeModule,
		FormsModule,
		ReactiveFormsModule,
		RouterModule,
		HttpClientModule,
		AngularEditorModule,
	],
	providers: [
		BackendService
	],
	bootstrap: [AppComponent]
})
export class AppModule {

}
