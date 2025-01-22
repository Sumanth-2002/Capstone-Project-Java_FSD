import { Routes } from '@angular/router';
import { HomeAdminComponent } from './component/home-admin/home-admin.component';
import { LoginComponent } from './component/login/login.component';

export const routes: Routes = [
    {path: 'login', component:LoginComponent},
    {path : '',component:HomeAdminComponent}
];
