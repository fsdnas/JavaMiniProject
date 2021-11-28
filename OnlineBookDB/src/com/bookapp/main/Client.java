package com.bookapp.main;

import java.util.Scanner;

import com.bookapp.bean.Book;
import com.bookapp.dao.BookImpl;
import com.bookapp.dao.BookInter;

import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class Client {

	public static void main(String[] args)
			throws AuthorNotFoundException, CategoryNotFoundException, BookNotFoundException {

		BookInter bookInter = new BookImpl();

		Scanner scanner = new Scanner(System.in);

		int k = 0;// k is for looping if input is wrong
		System.out.println("Add Books {Press 1}" + "\n" + "Filter books  {Press 2}" + "\n" + "Update Books {Press 3}"
				+ "\n" + "Delete Books {Press 4}" + "\n" + "Exit {Press 0}");
		int searchFilter = scanner.nextInt();

		switch (searchFilter) {
		case 1:
			// Number of books to insert
			System.out.println("Number of books to store in DB: ");
			int numberOfBooks = scanner.nextInt();
			for (int i = 0; i < numberOfBooks; i++) {
				Book book = new Book();

				scanner.nextLine();
				System.out.println("Enter Book Title: ");
				String title = scanner.nextLine();

				book.setTitle(title);

				System.out.println("Enter Author Name: ");
				String author = scanner.nextLine();

				book.setAuthor(author);

				System.out.println("Enter Book Category: ");
				String category = scanner.nextLine();

				book.setCategory(category);

				System.out.println("Enter BookId: ");
				int bookId = scanner.nextInt();

				book.setBookid(bookId);

				System.out.println("Enter Book Price: ");
				int price = scanner.nextInt();
				System.out.println();

				book.setPrice(price);

				bookInter.addBook(book);

			}
			bookInter.getAllBooks();
			break;

		case 2:// Read books from DB
			k = 0;
			while (k < 1) {
				System.out.println("Search By Author (or) Category (or) Id");
				String decision1 = scanner.next();

				if (decision1.equalsIgnoreCase("Author")) {
					System.out.println("Enter author name to search: ");
					scanner.nextLine();
					String authorNameSearch = scanner.nextLine();
					System.out.println(bookInter.getBookbyAuthor(authorNameSearch));
					k += 1;
					break;

				}

				else if (decision1.equalsIgnoreCase("Category")) {
					System.out.println("Enter category name to search: ");
					String categorySearch = scanner.next();
					System.out.println(bookInter.getBookbycategory(categorySearch));
					k += 1;
					break;

				}

				else if (decision1.equalsIgnoreCase("Id")) {
					System.out.println("Enter bookId: ");
					int idSearch = scanner.nextInt();
					System.out.println(bookInter.getBookById(idSearch));
					k += 1;
					break;
				}

				else {
					System.out.println("Please enter correct input");
					System.out.println();
				}

			}
			break;

		case 3: // Update books from DB
			k = 0;
			System.out.println("Enter book Id to update");
			int updateId = scanner.nextInt();
			System.out.println("Enter new Price");
			int price = scanner.nextInt();
			bookInter.updateBook(updateId, price);

			System.out.println(bookInter.getAllBooks());
			break;
		case 4: // Delete books from DB
			k = 0;
			System.out.println("Enter book Id to delete");
			int delId = scanner.nextInt();
			bookInter.deleteBook(delId);
			
			System.out.println(bookInter.getAllBooks());
			break;
		case 0: // Exit Application
			k = 0;
			System.exit(0);
			break;
		default:
			System.out.println("Please enter the correct input");
			k += 1;
		}

		scanner.close();

	}

}
