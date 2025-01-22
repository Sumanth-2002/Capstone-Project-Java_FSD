import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true, 
  imports: [FormsModule, CommonModule], 
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  email: string = '';
  password: string = '';
  errorMessage: string = '';

  onLogin() {
    if (this.email === '' || this.password === '') {
      this.errorMessage = 'All fields are required.';
    } else {
      this.errorMessage = '';
      console.log('Email:', this.email);
      console.log('Password:', this.password);
    }
  }
}