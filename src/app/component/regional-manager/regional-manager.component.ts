import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';

@Component({
  selector: 'app-regional-manager',
  standalone: true,
  imports: [HeaderComponent,SidebarComponent],
  templateUrl: './regional-manager.component.html',
  styleUrl: './regional-manager.component.css'
})
export class RegionalManagerComponent {

}
