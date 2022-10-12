package libmgmt

class Contact {

    String mobile

    String address

    Student student

    static constraints = {
        mobile maxSize: 12
        address maxSize: 50
    }
}
