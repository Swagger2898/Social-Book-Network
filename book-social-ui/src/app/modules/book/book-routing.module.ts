import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainComponent } from './pages/main/main.component';
import { BookListComponent } from './pages/book-list/book-list.component';
import { MyBooksComponent } from './pages/my-books/my-books.component';
import { ManageBookComponent } from './pages/manage-book/manage-book.component';
import { BorrowedBookListComponent } from './pages/borrowed-book-list/borrowed-book-list.component';
import { ReturnBooksComponent } from '../../pages/return-books/return-books.component';
import { authGuard } from '../../services/guard/auth.guard';

const routes: Routes = [
{
  path: '',
  component  : MainComponent,
  canActivate: [authGuard],
  children:[
    {
      path:'',
      canActivate: [authGuard],
      component: BookListComponent
    },
    {
    path:'my-books',
    canActivate: [authGuard],
    component:MyBooksComponent

    },
    {
      path: 'my-borrowed-books',
      canActivate: [authGuard],
      component:BorrowedBookListComponent
    },
    {
      path: 'manage/:id',
      canActivate: [authGuard],
      component: ManageBookComponent
    },
    {
      path: 'manage',
      canActivate: [authGuard],
      component: ManageBookComponent
    },
      {
        path: 'my-returned-books',
        canActivate: [authGuard],
        component:ReturnBooksComponent
      }

  ]
}

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BookRoutingModule { }
