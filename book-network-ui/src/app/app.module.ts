import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component';
import { RouterModule } from '@angular/router';  // Import RouterModule

import { CommonModule } from '@angular/common';
import { HttpClientModule ,HttpClient } from '@angular/common/http';
import { FormsModule } from '@angular/forms'; 
// Import standalone components
import { HomeComponent } from './home/home.component';
import { AboutComponent } from './about/about.component';
import { routes } from './app-routing.module';
import { LoginComponent } from './pages/login/login.component';

@NgModule({
  declarations: [
    AppComponent,  // Keep AppComponent in declarations
    
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(routes),
    HomeComponent,  // Import standalone components here
    AboutComponent,
    HttpClientModule,
    FormsModule,
    LoginComponent
  ],
  providers: [
    HttpClient
    ],
  bootstrap: [AppComponent]
})
export class AppModule { }


