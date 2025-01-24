// home-admin.component.ts
import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { StatBoxesComponent } from "../stat-boxes/stat-boxes.component";
import { AdminBarchartComponent } from '../admin-barchart/admin-barchart.component';

@Component({
  selector: 'app-home-admin',
  templateUrl: './home-admin.component.html',
  styleUrls: ['./home-admin.component.css'],
  standalone: true,
  imports: [HeaderComponent, SidebarComponent, StatBoxesComponent,AdminBarchartComponent] 
})
export class HomeAdminComponent {
  // Your logic here
}
