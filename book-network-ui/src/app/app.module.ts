import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppComponent } from './app.component'; // Import AppComponent
import { RouterModule } from '@angular/router'; // Import RouterModule
import { routes } from './app.routes'; // Import your routes

@NgModule({
  declarations: [AppComponent], // Declare AppComponent here
  imports: [BrowserModule, RouterModule.forRoot(routes)], // Import RouterModule with routes
  providers: [],
  bootstrap: [AppComponent] // Bootstrap the AppComponent
})
export class AppModule {}
