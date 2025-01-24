import { RegManFormComponent } from './../reg-man-form/reg-man-form.component';
import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { RegManCardComponent } from '../reg-man-card/reg-man-card.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-regional-manager',
  standalone: true,
  imports: [HeaderComponent,SidebarComponent,RegManCardComponent,RegManFormComponent,CommonModule],
  templateUrl: './regional-manager.component.html',
  styleUrl: './regional-manager.component.css'
})
export class RegionalManagerComponent {
  regionalManagers = [
    { name: 'John Doe', region: 'North', number: '123-456-7890' },
    { name: 'Jane Smith', region: 'South', number: '987-654-3210' },
  ];

  showForm = false;

  addNewManager(newManager: any) {
    this.regionalManagers.push({
      name: newManager.name,
      region: newManager.region,
      number: newManager.number,
    });
    this.showForm = false;
  }
  deleteManager(index: number) {
    this.regionalManagers.splice(index, 1); // Remove the manager at the specified index
  }
}
