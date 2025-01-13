import { Component, OnInit, Inject } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { PLATFORM_ID } from '@angular/core';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.scss']
})
export class MenuComponent implements OnInit {

  constructor(@Inject(PLATFORM_ID) private platformId: Object) {}

  ngOnInit(): void {
    // Check if the code is running in the browser
    if (isPlatformBrowser(this.platformId)) {
      const linkColor = document.querySelectorAll('.nav-link');

      // Loop through each link to check if it matches the current URL and add "active" class
      linkColor.forEach(link => {
        if (window.location.href.endsWith(link.getAttribute('href') || '')) {
          link.classList.add('active');
        }

        // Add click event to change "active" class when clicked
        link.addEventListener('click', () => {
          linkColor.forEach(nav => nav.classList.remove('active'));
          link.classList.add('active');
        });
      });
    }
  }

  logout() {
    // Your logout logic here
localStorage.removeItem('token'); 
window.location.reload(); }
}
