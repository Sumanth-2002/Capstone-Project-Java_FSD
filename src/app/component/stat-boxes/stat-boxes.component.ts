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
    { title: 'Total Product Purchased', value: '100' },
    { title: 'Total Product Sold', value: '90' },
    { title: 'Total Revenue', value: '₹1,00,000' }
  ];
}
