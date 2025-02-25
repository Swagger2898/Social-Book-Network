import { Component, OnInit } from '@angular/core'; // Component and lifecycle hooks
import { BookService } from '../../../../services/services/book.service'; // Book service
import { Router } from '@angular/router'; // Router for navigation
import { BookResponse, PageResponseBookResponse } from '../../../../services/models'; // Models for book and pagination responses

@Component({
  selector: 'app-book-list', // Component selector
  templateUrl: './book-list.component.html', // Template file
  styleUrls: ['./book-list.component.scss'] // Style file
})
export class BookListComponent implements OnInit {
  bookResponse: PageResponseBookResponse = {}; // Response for paginated books
  page = 0; // Current page
  size = 5; // Books per page
  pages: any = [];
  message: string = ''; // Message to display
  level: string = 'success'; // Message level (success or error)

  constructor(
    private bookService: BookService, // Inject BookService to interact with the API
    private router: Router // Inject Router to navigate between pages
  ) {}

  ngOnInit(): void {
    this.findAllBooks(); // Fetch books when the component initializes
  }

  // Fetch books for the current page
  private findAllBooks() { 
    this.bookService.findAllBooks({
      page: this.page,
      size: this.size
    }).subscribe({
      next: (books) => {
        this.bookResponse = books;
        this.pages = Array(this.bookResponse.totalPages)
        .fill(0)
        .map((x, i) => i);
        console.log('Fetched books:', this.bookResponse); // Debugging API Response
      },
      error: (err) => {
        console.log('Error fetching books:', err); // Debugging error in fetching
      }
    });
  }

  // Go to the first page
  goToFirstPage() {
    this.page = 0;
    this.findAllBooks();
  }

  // Go to the previous page
  goToPreviousPage() {
    if (this.page > 0) {
      this.page--;
      this.findAllBooks();
    }
  }

  // Go to a specific page
  goToPage(page: number) {
    this.page = page;
    this.findAllBooks();
  }

  // Go to the last page
  goToLastPage() { debugger;
    if (this.bookResponse.totalPages) {
      this.page = this.bookResponse.totalPages - 1;
      this.findAllBooks();
    }
  }

  // Go to the next page
  goToNextPage() {
      this.page++;
      this.findAllBooks();
    
  }

  // Check if the current page is the last page
  get isLastPage(): boolean {
    return this.page === (this.bookResponse.totalPages || 0) - 1;
  }

  // Borrow a book
  borrowBook(book: BookResponse) {
    this.message = '';
    this.bookService.borrowBook({
      'book-id': book.id as number
    }).subscribe({
      next: () => {
        this.level = 'success';
        this.message = 'Book borrowed successfully';
      },
      error: (err) => {
        console.log(err);
        this.level = 'error';
        this.message = err.error.error;
      }
    });
  }
}
