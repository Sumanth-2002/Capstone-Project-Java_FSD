import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

@Component({
  selector: 'app-stat-boxes',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './stat-boxes.component.html',
  styleUrl: './stat-boxes.component.css'
})
export class StatBoxesComponent {
  stats = [
    { title: 'Total Purchase', value: '₹1,50,000' },
    { title: 'Total Sales', value: '₹2,00,000' },
    { title: 'Total Revenue', value: '₹50,000' }
  ];
}
