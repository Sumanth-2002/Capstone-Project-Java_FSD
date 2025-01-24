import { provideRouter, Routes } from '@angular/router';
import { HomeAdminComponent } from './component/admin/home-admin/home-admin.component';
import { LoginComponent } from './component/login/login.component';
import { RegionalManagerComponent } from './component/admin/regional-manager/regional-manager.component';
import { CustomerComponent } from './component/admin/customer/customer.component';
import { MetricsComponent } from './component/admin/metrics/metrics.component';
import { ProductsComponent } from './component/admin/products/products.component';

export const routes: Routes = [
    {path: 'login', component:LoginComponent},
    {path : 'admin-home',component:HomeAdminComponent},
    {path : 'admin-regManager',component:RegionalManagerComponent},
    {path : 'admin-customer',component:CustomerComponent},
    {path : 'admin-metrics',component:MetricsComponent},
    {path : 'admin-products',component:ProductsComponent}
];
export const appRouter = provideRouter(routes);