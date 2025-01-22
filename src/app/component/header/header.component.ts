import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-header',
  standalone: true,  // Mark this component as standalone
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  
  username: string = 'John Doe';  // You can fetch this dynamically from the authentication service
  role: string = 'Administrator'; // Similarly, you can fetch the role dynamically

  constructor() { }

  ngOnInit(): void {
    // You can use Angular services to get the logged-in user and role dynamically
    // Example: this.username = authService.getUsername();
    // Example: this.role = authService.getUserRole();
  }
}
