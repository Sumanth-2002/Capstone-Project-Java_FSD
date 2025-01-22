import { Routes } from '@angular/router';
import { SidebarComponent } from './component/side-bar/side-bar.component';
import { HeaderComponent } from './component/header/header.component';
import { HomeAdminComponent } from './component/home-admin/home-admin.component';

export const routes: Routes = [
    //{path: '', component:SidebarComponent},
    //{path : '',component:HeaderComponent},
    {path : '',component:HomeAdminComponent}

];
