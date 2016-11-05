package com.openmind.bookstore

class Author {

    String firstName
    String lastName

    static constraints = {
        firstName blank: false
        lastName blank: false
    }

    @Override
    String toString() {
        "$firstName $lastName"
    }
}
