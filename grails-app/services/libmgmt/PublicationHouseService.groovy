package libmgmt

import grails.gorm.services.Service

@Service(PublicationHouse)
interface PublicationHouseService {

    PublicationHouse get(Serializable id)

    List<PublicationHouse> list(Map args)

    Long count()

    void delete(Serializable id)

    PublicationHouse save(PublicationHouse publicationHouse)

}