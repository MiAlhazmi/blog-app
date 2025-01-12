import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SidebariconComponent } from './sidebaricon.component';

describe('SidebariconComponent', () => {
  let component: SidebariconComponent;
  let fixture: ComponentFixture<SidebariconComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SidebariconComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SidebariconComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
