import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';

@Component({
  selector: 'app-metrics',
  standalone: true,
  imports: [HeaderComponent,SidebarComponent],
  templateUrl: './metrics.component.html',
  styleUrl: './metrics.component.css'
})
export class MetricsComponent {

}
