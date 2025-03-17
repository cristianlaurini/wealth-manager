import { Component } from '@angular/core';
import { AuthFormComponent } from "../../components/auth-form/auth-form.component";

@Component({
    selector: 'wlt-login',
    imports: [AuthFormComponent],
    templateUrl: './login.component.html',
    styleUrl: './login.component.scss'
})
export class LoginComponent {

}
