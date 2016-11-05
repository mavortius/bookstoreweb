package com.openmind.bookstore

class BookController {

    static scaffold = Book

    def showEditAjax() {
        render view: 'editAjax', model: [bookInstance: Book.get(params.id)]
    }

    def showListSelect() {
        render view: 'listSelect', model: [bookInstanceList: Book.list()]
    }

    def showTitleSearch() {
        render view: 'titleSearchAjax'
    }

    def showRemoteLink() {
        render view: 'remoteLink', model: [bookInstanceList: Book.list()]
    }

    def showDetails() {
        render template: 'bookDetails', model: [bookInstance: Book.get(params.id)]
    }

    def showSearch() {
        render view: 'searchAjax'
    }

    def searchTitle() {
        def list = Book.findByTitleIlike(wrapSearchParam(params.searchvalue))

        render template: 'searchResults', model: [searchResults: list]
    }

    def search() {
        def list

        if(params.publisher && params.title) {
            list = Book.findByPublisherIlikeAndTitleIlike(wrapSearchParam(params.publisher), wrapSearchParam(params.title))
        } else if(params.publisher) {
            list = Book.findByPublisherIlike(wrapSearchParam(params.publisher))
        } else if(params.title) {
            list = Book.findByTitleIlike(wrapSearchParam(params.title))
        }

        render template: 'searchResults', model: [searchResults: list]
    }

    def listByPublisher() {
        def list

        if(params.filter == 'ALL') {
            list = Book.list()
        } else {
            list = Book.findAllByPublisher(params.filter)
        }

        render template: 'searchResults', model: [searchResults: list]
    }

    def update() {
        def bookInstance = Book.get(params.id)

        if(bookInstance) {
            bookInstance.properties = params

            if(!bookInstance.hasErrors() && bookInstance.save()) {
                render "Book ${params.title} updated with Ajax using FormRemote"
            } else {
                render view: 'edit', model: [bookInstance: bookInstance]
            }
        } else {
            flash.message = "Book not found with id ${params.id}"

            redirect action: 'edit', id: params.id
        }
    }

    private String wrapSearchParam(value) {
        "%$value%"
    }
}
