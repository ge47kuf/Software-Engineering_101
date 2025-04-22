package de.tum.in.ase.eist;

public class Book {

	private String title;
	private String author;
	private String[] pages;

	public Book(String title, String author, String[] pages) {
		this.title = title;
		this.author = author;
		this.pages = pages;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public int getPageCount() {
		return pages.length;
	}
}
