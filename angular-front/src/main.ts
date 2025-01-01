import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import { AppModule } from './app/rout_modules/app.module';

platformBrowserDynamic().bootstrapModule(AppModule, {
  ngZoneEventCoalescing: true,
})
  .catch(err => console.error(err));
