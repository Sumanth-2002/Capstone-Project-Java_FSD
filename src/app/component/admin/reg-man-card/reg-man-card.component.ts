import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-reg-man-card',
  standalone: true,
  template: `
    <div class="card">
      <button class="delete-button" (click)="onDelete()">
        <span class="material-icons">delete</span>
      </button>
      <h3>{{ regionalManager.name }}</h3>
      <p><strong>Region:</strong> {{ regionalManager.region }}</p>
      <p><strong>Number:</strong> {{ regionalManager.number }}</p>
    </div>
  `,
  styleUrls: ['./reg-man-card.component.css'],
})
export class RegManCardComponent {
  @Input() regionalManager: {
    name: string;
    region: string;
    number: string;
  } = { name: '', region: '', number: '' };

  @Output() delete = new EventEmitter<void>();

  onDelete() {
    this.delete.emit();
  }
}