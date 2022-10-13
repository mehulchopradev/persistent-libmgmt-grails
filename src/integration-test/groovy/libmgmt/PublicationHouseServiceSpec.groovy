package libmgmt

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class PublicationHouseServiceSpec extends Specification {

    PublicationHouseService publicationHouseService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new PublicationHouse(...).save(flush: true, failOnError: true)
        //new PublicationHouse(...).save(flush: true, failOnError: true)
        //PublicationHouse publicationHouse = new PublicationHouse(...).save(flush: true, failOnError: true)
        //new PublicationHouse(...).save(flush: true, failOnError: true)
        //new PublicationHouse(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //publicationHouse.id
    }

    void "test get"() {
        setupData()

        expect:
        publicationHouseService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<PublicationHouse> publicationHouseList = publicationHouseService.list(max: 2, offset: 2)

        then:
        publicationHouseList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        publicationHouseService.count() == 5
    }

    void "test delete"() {
        Long publicationHouseId = setupData()

        expect:
        publicationHouseService.count() == 5

        when:
        publicationHouseService.delete(publicationHouseId)
        sessionFactory.currentSession.flush()

        then:
        publicationHouseService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        PublicationHouse publicationHouse = new PublicationHouse()
        publicationHouseService.save(publicationHouse)

        then:
        publicationHouse.id != null
    }
}
