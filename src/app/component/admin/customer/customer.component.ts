import { Component } from '@angular/core';
import { HeaderComponent } from '../header/header.component';
import { SidebarComponent } from '../side-bar/side-bar.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-customer',
  standalone: true,
  imports: [HeaderComponent,SidebarComponent,CommonModule],
  templateUrl: './customer.component.html',
  styleUrl: './customer.component.css'
})
export class CustomerComponent {
  customers = [
    { id: 1, name: 'John Doe', contact: '123-456-7890' },
    { id: 2, name: 'Jane Smith', contact: '987-654-3210' },
    { id: 3, name: 'Alice Johnson', contact: '555-555-5555' },
  ];

  selectedCustomer: any = null;

  openModal(customer: any) {
    this.selectedCustomer = customer;
  }

  closeModal() {
    this.selectedCustomer = null;
  }
}