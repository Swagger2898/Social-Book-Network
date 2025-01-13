import { Component, OnInit } from '@angular/core';
import { BorrowedBookResponse, FeedBackRequest, PageResponseBorrowedBookResponse } from '../../services/models';
import { BookService, FeedBackService } from '../../services/services';

@Component({
  selector: 'app-return-books',
  templateUrl: './return-books.component.html',
  styleUrl: './return-books.component.scss'
})
export class ReturnBooksComponent implements OnInit {

   returnedBooks: PageResponseBorrowedBookResponse ={};
     page:number = 0;
     size:number = 5;
    feedBackRequest: FeedBackRequest={
      bookId: 0,
      comment: '',
      note: 0
    }
message:string = '';
level: string='success';
   
    constructor(
      private bookService: BookService,
   
    ) { }
   
    ngOnInit(): void {
    this.findAllReturnedBooks();
    }
    
    
    findAllReturnedBooks() {
      this.bookService.findAllReturnedBooks({
        page: this.page,
        size:this.size
      }).subscribe({
        next:(response: PageResponseBorrowedBookResponse) => {
          this.returnedBooks = response;
        }
      }) 
      }  


      approveBookReturn(book: BorrowedBookResponse) {
if(!book.returned){
  this.level='error',
  this.message = 'Book not returned yet';
}       
this.bookService.approveReturnBorrowBook({
  'book-id':book.id as number
}).subscribe({
  next: () => {
    this.level='success';
    this.message = 'Book return approved';
    this.findAllReturnedBooks()
  }
});
}
        






  goToFirstPage() {
    this.page=0;
    this.findAllReturnedBooks();
    }
    
    goToPreviousPage() {
    this.page--;
    this.findAllReturnedBooks(); 
     }
    
    
    goToPage(page:number) {
    this.page=page;  
    this.findAllReturnedBooks();
    }
    
    
      goToLastPage() {
    
        this.page = this.returnedBooks.totalPages as number - 1;
        this.findAllReturnedBooks();
      }
      goToNextPage() {
      this.page++;
      }
      
    
    
    
    
    get isLastPage() :boolean{
      return this.page == this.returnedBooks.totalPages as number - 1;
    }
    
  




}
