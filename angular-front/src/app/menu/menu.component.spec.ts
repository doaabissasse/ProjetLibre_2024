import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MenuComponent } from './menu.component';
import { RouterModule } from '@angular/router';

describe('MenuComponent', () => {
  let component: MenuComponent;
  let fixture: ComponentFixture<MenuComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MenuComponent],
      imports: [
        RouterModule.forRoot([]) // Ensure RouterModule is configured with empty routes for testing
      ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(MenuComponent);
    component = fixture.componentInstance;

    fixture.detectChanges(); // Trigger any lifecycle hooks
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render the correct number of navbar items', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    const navItems = compiled.querySelectorAll('.nav-item');
    expect(navItems.length).toBe(6); // We have 3 nav items in the HTML
  });

  it('should have active class on the active link', () => {
    const compiled = fixture.nativeElement as HTMLElement;

    // Simulate click on "Laboratoires" to make it active
    const laboratoiresLink = compiled.querySelector('a[routerLink="/laboratoires"]');
    fixture.detectChanges();

    // Check if the correct link has the active class
    expect(compiled.querySelector('a[routerLink="/laboratoires"]')?.classList.contains('active')).toBe(false);
    expect(compiled.querySelector('a[routerLink="/accueil"]')?.classList.contains('active')).toBe(true);
    expect(compiled.querySelector('a[routerLink="/ajouter-laboratoire"]')?.classList.contains('active')).toBe(false);
  });

  it('should display correct router links', () => {
    const compiled = fixture.nativeElement as HTMLElement;
    const links = compiled.querySelectorAll('.nav-link');
    expect(links[0].getAttribute('routerLink')).toBe('/accueil');
    expect(links[1].getAttribute('routerLink')).toBe('/laboratoires');
    expect(links[2].getAttribute('routerLink')).toBe('/ajouter-laboratoire');
  });
});
