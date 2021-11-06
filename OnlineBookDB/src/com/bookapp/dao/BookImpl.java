package com.bookapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.bookapp.bean.Book;
import com.bookapp.exception.AuthorNotFoundException;
import com.bookapp.exception.BookNotFoundException;
import com.bookapp.exception.CategoryNotFoundException;

public class BookImpl implements BookInter {
	String readQuery = "select * from books";
	String insertQuery = "insert into books values(?,?,?,?,?)";
	String updateQuery = "update books set price=? where bookId=?";
	String deleteQuery = "delete from books where bookId = ?";
	String searchByAuthor = "select * from books where authorName=?";
	String searchById = "select * from books where bookId=?";
	String searchByCategory = "select * from books where category=?";

	@Override
	public void addBook(Book book) {

		Connection connection = ModelDAO.openConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(insertQuery);
			statement.setString(1, book.getTitle());
			statement.setString(2, book.getAuthor());
			statement.setString(3, book.getCategory());
			statement.setInt(4, book.getBookid());
			statement.setInt(5, book.getPrice());
			statement.execute();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public boolean deleteBook(int bookId) throws BookNotFoundException {
		Connection connection = ModelDAO.openConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(deleteQuery);
			statement.setInt(1, bookId);
			statement.execute();
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Book getBookById(int bookId) {
		Connection connection = ModelDAO.openConnection();
		PreparedStatement statement = null;
		System.out.println();
		try {
			statement = connection.prepareStatement(searchById);
			statement.setInt(1, bookId);
			ResultSet result = statement.executeQuery();
			if (result != null) {
				while (result.next()) {
					String title = result.getString(1);
					String author = result.getString(2);
					String category = result.getString(3);
					int id = result.getInt(4);
					int price = result.getInt(5);
					System.out.println(title + "\t" + "| " + author + "\t" + "| " + category + "\t" + "| " + id + "\t"
							+ "| " + price);
				}
				result.close();

			} else {
				throw new BookNotFoundException("Book not Found please check the input");
			}

		}

		catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public boolean updateBook(int bookId, int price) {
		Connection connection = ModelDAO.openConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(updateQuery);
			statement.setInt(1, price);
			statement.setInt(2, bookId);
			statement.execute();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Book> getAllBooks() {
		Connection connection = ModelDAO.openConnection();
		PreparedStatement statement = null;
		System.out.println();
		try {
			statement = connection.prepareStatement(readQuery);
			ResultSet result = statement.executeQuery();

			while (result.next()) {
				String title = result.getString(1);
				String author = result.getString(2);
				String category = result.getString(3);
				int id = result.getInt(4);
				int price = result.getInt(5);
				System.out.println(
						title + "\t" + "| " + author + "\t" + "| " + category + "\t" + "| " + id + "\t" + "| " + price);
			}
			result.close();

		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> getBookbyAuthor(String authorName) throws AuthorNotFoundException {
		Connection connection = ModelDAO.openConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(searchByAuthor);
			statement.setString(1, authorName);
			ResultSet result = statement.executeQuery();
			if (result != null) {
				while (result.next()) {
					String title = result.getString(1);
					String author = result.getString(2);
					String category = result.getString(3);
					int id = result.getInt(4);
					int price = result.getInt(5);
					System.out.println(title + "\t" + "| " + author + "\t" + "| " + category + "\t" + "| " + id + "\t"
							+ "| " + price);
				}
				result.close();

			} else {
				throw new BookNotFoundException("Author not Found please check the input");
			}

		}

		catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> getBookbycategory(String categoryName) throws CategoryNotFoundException {
		Connection connection = ModelDAO.openConnection();
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(searchByCategory);
			statement.setString(1, categoryName);
			ResultSet result = statement.executeQuery();
			System.out.println();
			if (result != null) {
				while (result.next()) {
					String title = result.getString(1);
					String author = result.getString(2);
					String category = result.getString(3);
					int id = result.getInt(4);
					int price = result.getInt(5);
					System.out.println(title + "\t" + "| " + author + "\t" + "| " + category + "\t" + "| " + id + "\t"
							+ "| " + price);
				}
				result.close();

			} else {
				throw new BookNotFoundException("Category not Found please check the input");
			}

		}

		catch (BookNotFoundException e) {
			System.out.println(e.getMessage());
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
