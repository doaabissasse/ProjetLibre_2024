import { TestBed, ComponentFixture } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { AccueilComponent } from './accueil.component';
import { By } from '@angular/platform-browser';

describe('AccueilComponent', () => {
  let component: AccueilComponent;
  let fixture: ComponentFixture<AccueilComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AccueilComponent],
      imports: [RouterTestingModule] // Import RouterTestingModule to mock Router
    }).compileComponents();

    fixture = TestBed.createComponent(AccueilComponent);
    component = fixture.componentInstance;
    fixture.detectChanges(); // Trigger change detection to update DOM
  });

  it('should create the AccueilComponent', () => {
    expect(component).toBeTruthy();
  });

  describe('Navbar and Card Structure', () => {
    it('should have the correct navbar structure', () => {
      const navbar = fixture.debugElement.query(By.css('nav.navbar'));
      expect(navbar).toBeTruthy();

      const navItems = fixture.debugElement.queryAll(By.css('.nav-item a'));
      expect(navItems.length).toBe(3);
      expect(navItems[0].nativeElement.textContent.trim()).toBe('Accueil');
      expect(navItems[1].nativeElement.textContent.trim()).toBe('Laboratoires');
      expect(navItems[2].nativeElement.textContent.trim()).toBe('Ajouter un Laboratoire');
    });

    it('should have the correct cards and their content', () => {
      const cards = fixture.debugElement.queryAll(By.css('.card-title'));
      expect(cards.length).toBe(7);

      expect(cards[0].nativeElement.textContent.trim()).toBe('Gestion Complète des Laboratoires');
      expect(cards[1].nativeElement.textContent.trim()).toBe('Administration des Utilisateurs');
      expect(cards[2].nativeElement.textContent.trim()).toBe('Gestion des Analyses de Laboratoire');
      expect(cards[3].nativeElement.textContent.trim()).toBe('Coordonnées des Contacts');
      expect(cards[4].nativeElement.textContent.trim()).toBe('Gestion des Localisations');
      expect(cards[5].nativeElement.textContent.trim()).toBe('Gestion des Dossiers Médicaux');
    });
  });

});
