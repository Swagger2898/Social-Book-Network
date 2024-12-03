import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router'; // Import RouterOutlet for routing

@Component({
  selector: 'app-root',
  template: `<router-outlet></router-outlet>`, // Use RouterOutlet for routing
  // No need for standalone: true here
})
export class AppComponent {}
