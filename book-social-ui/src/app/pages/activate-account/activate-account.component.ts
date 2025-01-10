import { Component } from '@angular/core';
import { AuthenticationService } from '../../services/services';
import { Router } from '@angular/router';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.scss'
})
export class ActivateAccountComponent {
redirectToLogin() {
this.router.navigate(['/login']);
}
  
  message: string = '';
  isOkay: boolean = true;
  submitted: boolean = false;
  
  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {}


onCodeCompleted(token:string){

this.confirmAccount(token);

}


  confirmAccount(token: any) {
    this.authService.confirm({
      token
    }).subscribe({
      next: () => {
        this.message = 'Your account has been successfully activated, you can now login.';
        this.submitted=true;
        this.isOkay = true;
      },
      error:() =>{
        this.message = 'Token has been expired or invalid.';
        this.isOkay = false;
        this.submitted=true;
      }

      });
  }

}
