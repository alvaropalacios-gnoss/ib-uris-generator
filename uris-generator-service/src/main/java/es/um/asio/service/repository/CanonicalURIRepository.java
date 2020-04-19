package es.um.asio.service.repository;

import es.um.asio.service.model.CanonicalURI;
import es.um.asio.service.model.URIMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data repository for {@link User}
 */
public interface CanonicalURIRepository extends JpaRepository<CanonicalURI, String>, JpaSpecificationExecutor<CanonicalURI> {
    /**
     * Finds a CanonicalURI using the username field.
     *
     * @param fullURI
     *            The ontologyURI to search for
     * @return an {@link CanonicalURI} entity stored in the database or {@literal Optional#empty()} if none found
     */
    Optional<List<CanonicalURI>> findByFullURI(String fullURI);

    /**
     * Finds a CanonicalURI using the entityName field and propertyName field.
     *
     * @param entityName
     *            The entityName to search for
     * @param propertyName
     *            The propertyName to search for
     * @return an {@link CanonicalURI} entity stored in the database or {@literal Optional#empty()} if none found
     */
    Optional<List<CanonicalURI>> findByEntityNameAndPropertyName(String entityName, String propertyName);

    /**
     * Finds a CanonicalURI using the entityName field and propertyName field.
     *
     * @param entityName
     *            The entityName to search for
     * @param propertyName
     *            The propertyName to search for
     * @return an {@link CanonicalURI} entity stored in the database or {@literal Optional#empty()} if none found
     */
    Optional<List<CanonicalURI>> findByEntityNameAndReference(String entityName, String reference);

    /**
     * Finds a CanonicalURI using  propertyName field.
     *
     * @param entityName
     *            The entityName to search for
     * @return an {@link CanonicalURI} entity stored in the database or {@literal Optional#empty()} if none found
     */
    Optional<List<CanonicalURI>> findByEntityName(String entityName);

    /**
     * Finds a CanonicalURI using  propertyName field.
     *
     * @param propertyName
     *            The propertyName to search for
     * @return an {@link CanonicalURI} entity stored in the database or {@literal Optional#empty()} if none found
     */
    Optional<List<CanonicalURI>> findByPropertyName(String propertyName);

}
