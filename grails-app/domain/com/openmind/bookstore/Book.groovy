package com.openmind.bookstore

class Book {

    String title
    String publisher
    Integer pages
    Author author

    static constraints = {
        title blank: false
        pages
        author
        publisher
    }

    @Override
    String toString() {
        title
    }
}
