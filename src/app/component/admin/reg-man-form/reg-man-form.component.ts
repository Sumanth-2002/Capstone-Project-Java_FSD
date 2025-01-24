import { Component, EventEmitter, Output } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-reg-man-form',
  standalone: true,
  imports: [FormsModule],
  template: `
    <div class="form-container">
      <h2>Add New Regional Manager</h2>
      <form (ngSubmit)="onSubmit()">
        <div class="form-group">
          <label for="name">Name:</label>
          <input type="text" id="name" [(ngModel)]="newManager.name" name="name" required>
        </div>
        <div class="form-group">
          <label for="region">Region:</label>
          <input type="text" id="region" [(ngModel)]="newManager.region" name="region" required>
        </div>
        <div class="form-group">
          <label for="number">Number:</label>
          <input type="text" id="number" [(ngModel)]="newManager.number" name="number" required>
        </div>
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" id="email" [(ngModel)]="newManager.email" name="email" required>
        </div>
        <div class="form-group">
          <label for="password">Password:</label>
          <input type="password" id="password" [(ngModel)]="newManager.password" name="password" required>
        </div>
        <button type="submit">Save</button>
      </form>
    </div>
  `,
  styleUrls: ['./reg-man-form.component.css'],
})
export class RegManFormComponent {
  @Output() save = new EventEmitter<any>();

  newManager = {
    name: '',
    region: '',
    number: '',
    email: '',
    password: '',
  };

  onSubmit() {
    this.save.emit(this.newManager);
    this.newManager = { name: '', region: '', number: '', email: '', password: '' }; // Reset form
  }
}