import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';  // Import the HomeComponent
import { AboutComponent } from './about/about.component';  // Import the AboutComponent

export const routes: Routes = [
  { path: '', component: HomeComponent },  // Default route: load HomeComponent
  { path: 'about', component: AboutComponent }  // Route to AboutComponent
];
