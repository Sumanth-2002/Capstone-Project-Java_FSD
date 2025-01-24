import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { SidebarComponent } from './app/component/admin/side-bar/side-bar.component';
import { HeaderComponent } from './app/component/admin/header/header.component';

bootstrapApplication(AppComponent, appConfig)
  .catch((err) => console.error(err));

  // bootstrapApplication(SidebarComponent).catch((err) =>
  //   console.error(err)
  // );

  bootstrapApplication(HeaderComponent).catch((err) =>
    console.error(err)
  );
  
