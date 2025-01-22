// home-admin.component.ts
import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';

@Component({
  selector: 'app-home-admin',
  templateUrl: './home-admin.component.html',
  styleUrls: ['./home-admin.component.css'],
  standalone: true,  // Mark this component as standalone as well
  imports: [HeaderComponent,SidebarComponent]  // Import HeaderComponent into this component
})
export class HomeAdminComponent {
  // Your logic here
}
