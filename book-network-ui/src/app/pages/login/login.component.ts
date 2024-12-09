import { Component } from '@angular/core';
import { AuthenticationRequest, AuthenticationResponse } from '../../services/models';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common'; 
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule , CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {


constructor(
  private router: Router,
  private authService: AuthenticationService
){

}


  authRequest: AuthenticationRequest = {email: '', password: ''};
  errorMsg: Array<String> = [];


  register() {
   this.router.navigate(['register'])
      

  }
  login() {
    
    this.errorMsg=[];
    this.authService.authenticate({
      body:this.authRequest
    }).subscribe({
      next: (res:AuthenticationResponse) =>{
        //save token
        this.router.navigate(['books']);
      },
      error:(err):void =>{
        console.log(err);
      }
    })

  }

}
