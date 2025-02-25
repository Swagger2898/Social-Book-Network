import { Component } from '@angular/core';
import { RegistrationRequest } from '../../services/models/registration-request';
import { AuthenticationService } from '../../services/services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss'
})
export class RegisterComponent {

  registerRequest: RegistrationRequest = {email: 's', firstName: '', lastName: '', password: ''};  
  errorMsg: Array<string> = [];

constructor(
  private router: Router,
private authService: AuthenticationService
) { 


}
login() {
this.router.navigate(['login']);
}
register() {
this.errorMsg = [];
this.authService.register({
body: this.registerRequest  
}).subscribe({
next: () => {
  this.router.navigate(['activate-account']);
},
error: (err) => {
  this.errorMsg = err.error.validationErrors ;

}
})

}
}