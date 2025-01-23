import { provideRouter, Routes } from '@angular/router';
import { HomeAdminComponent } from './component/home-admin/home-admin.component';
import { LoginComponent } from './component/login/login.component';
import { RegionalManagerComponent } from './component/regional-manager/regional-manager.component';
import { CustomerComponent } from './component/customer/customer.component';
import { MetricsComponent } from './component/metrics/metrics.component';

export const routes: Routes = [
    {path: 'login', component:LoginComponent},
    {path : 'home',component:HomeAdminComponent},
    {path : 'regManager',component:RegionalManagerComponent},
    {path : 'customer',component:CustomerComponent},
    {path : 'metrics',component:MetricsComponent}
];
export const appRouter = provideRouter(routes);