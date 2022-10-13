package libmgmt

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PublicationHouseController {

    PublicationHouseService publicationHouseService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond publicationHouseService.list(params), model:[publicationHouseCount: publicationHouseService.count()]
    }

    def show(Long id) {
        respond publicationHouseService.get(id)
    }

    def create() {
        respond new PublicationHouse(params)
    }

    def save(PublicationHouse publicationHouse) {
        if (publicationHouse == null) {
            notFound()
            return
        }

        try {
            publicationHouseService.save(publicationHouse)
        } catch (ValidationException e) {
            respond publicationHouse.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'publicationHouse.label', default: 'PublicationHouse'), publicationHouse.id])
                redirect publicationHouse
            }
            '*' { respond publicationHouse, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond publicationHouseService.get(id)
    }

    def update(PublicationHouse publicationHouse) {
        if (publicationHouse == null) {
            notFound()
            return
        }

        try {
            publicationHouseService.save(publicationHouse)
        } catch (ValidationException e) {
            respond publicationHouse.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'publicationHouse.label', default: 'PublicationHouse'), publicationHouse.id])
                redirect publicationHouse
            }
            '*'{ respond publicationHouse, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        publicationHouseService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'publicationHouse.label', default: 'PublicationHouse'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'publicationHouse.label', default: 'PublicationHouse'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
