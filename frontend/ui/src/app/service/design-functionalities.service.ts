import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DesignFunctionalitiesService {

  constructor() { }

  ngOnInit() :void {

  }

  openPopup(elementId: string) {
    let popup = document.getElementById(elementId);
    popup?.classList.add("open-popup");
  }


  closePopup(elementId: string) {
    let popup = document.getElementById(elementId);
    popup?.classList.remove("close-popup");
  }
}
