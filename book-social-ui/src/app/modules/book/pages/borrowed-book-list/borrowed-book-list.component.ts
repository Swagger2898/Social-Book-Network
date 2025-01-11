import { Component, OnInit } from '@angular/core';
import { BorrowedBookResponse, FeedBackRequest, PageResponseBorrowedBookResponse } from '../../../../services/models';
import { BookService } from '../../../../services/services';
import { response } from 'express';

@Component({
  selector: 'app-borrowed-book-list',
  templateUrl: './borrowed-book-list.component.html',
  styleUrl: './borrowed-book-list.component.scss'
})
export class BorrowedBookListComponent implements OnInit {

  borrowedBooks: PageResponseBorrowedBookResponse ={};
   page:number = 0;
   size:number = 5;
  selectedBook: BorrowedBookResponse={};
  feedBackRequest: FeedBackRequest={
    bookId: 0,
    comment: ''
  }
 
  constructor(
    private bookService: BookService,
  ) { }
 
ngOnInit(): void {
this.findAllBorrowedBooks();
}

  findAllBorrowedBooks() {
  this.bookService.findAllBorrowedBooks({
    page: this.page,
    size:this.size
  }).subscribe({
    next:(response: PageResponseBorrowedBookResponse) => {
      this.borrowedBooks = response;
    }
  }) 
  }  
  

//pagination
  
goToFirstPage() {
  this.page=0;
  this.findAllBorrowedBooks();
  }
  
  goToPreviousPage() {
  this.page--;
  this.findAllBorrowedBooks(); 
   }
  
  
  goToPage(page:number) {
  this.page=page;  
  this.findAllBorrowedBooks();
  }
  
  
    goToLastPage() {
  
      this.page = this.borrowedBooks.totalPages as number - 1;
      this.findAllBorrowedBooks();
    }
    goToNextPage() {
    this.page++;
    }
    
  
  
  
  
  get isLastPage() :boolean{
    return this.page == this.borrowedBooks.totalPages as number - 1;
  }
  


  returnBorrowedBook(book: BorrowedBookResponse) {
    this.selectedBook = book;
    
}

}
