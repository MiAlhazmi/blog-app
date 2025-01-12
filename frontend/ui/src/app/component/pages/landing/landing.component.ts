import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing',
  templateUrl: './landing.component.html',
  styleUrls: ['./landing.component.css'],
})
export class LandingComponent {

  constructor(private router: Router) { }
  
  public onGetStartedClick(): void {
    this.router.navigate(['/signup'])
  }

  public onLoginClick(): void {
    this.router.navigate(['/login']);
  }

  public onBlogWebClick(): void {
    this.router.navigate([''])
  }
}
