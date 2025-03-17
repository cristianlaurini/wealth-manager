import { Routes } from '@angular/router';
import { HomepageComponent } from './pages/homepage/homepage.component';
import { LoginComponent } from './pages/login/login.component';
import { AuthGuardService } from './services/auth-guard.service';

export const routes: Routes = [
    { path: '', component: HomepageComponent, title: 'Wealth Manager', canActivate: [AuthGuardService] },
    { path: 'login', component: LoginComponent, title: 'Accedi - Wealth Manager' }
];
