import { Injectable } from '@angular/core';
import { getCookie } from '../utils/functions/cookie.function';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {

    constructor() { }

    public isAuthenticated() : boolean {
        var token = getCookie('JWT');
        return token != null;
    }

}
