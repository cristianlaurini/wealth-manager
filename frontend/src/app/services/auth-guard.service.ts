import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentication.service';
import { Router } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthGuardService {

    constructor(private auth: AuthenticationService, private router: Router) { }

    canActivate(): boolean {
        if (this.auth.isAuthenticated()) return true;
        this.router.navigate(['login']);
        return false;
    }

}
