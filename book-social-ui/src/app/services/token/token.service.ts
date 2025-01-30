import { Injectable, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  isTokenNotValid() {
return !this.isTokenValid();  
}
  isTokenValid() {
const token = this.token;  

if(!token){
  return false;
}
const jwtHelper = new JwtHelperService();
const isTokenExpired=jwtHelper.isTokenExpired(token);
if(isTokenExpired){
  localStorage.clear();
  return false;
}
return true;
}

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  // Get the current token from localStorage (only in browser)
  get token(): string | null {
    if (isPlatformBrowser(this.platformId)) {
      const token = localStorage.getItem('token');
      console.log('Token retrieved from localStorage:', token);  // Log the retrieved token
      return token;
    }
    return null; // Return null for SSR
  }

  // Set the new token and remove the old one
  set token(value: string | null) {
    if (isPlatformBrowser(this.platformId)) {
      console.log('Setting new token in localStorage:', value);  // Log the token being set

      if (value) {
        // Remove the old token from localStorage first
        localStorage.removeItem('token');
        // Now set the new token
        localStorage.setItem('token', value);
      } else {
        // If value is null, remove the token from localStorage
        localStorage.removeItem('token');
      }
    }
  }
}
