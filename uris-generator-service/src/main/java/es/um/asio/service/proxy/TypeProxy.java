package es.um.asio.service.proxy;

import com.izertis.abstractions.service.DeleteService;
import com.izertis.abstractions.service.QueryService;
import com.izertis.abstractions.service.SaveService;
import es.um.asio.service.filter.CanonicalURIFilter;
import es.um.asio.service.filter.TypeFilter;
import es.um.asio.service.model.Language;
import es.um.asio.service.model.Type;
import es.um.asio.service.model.User;

/**
 * Proxy service for {@link User}. Performs DTO conversion and permission checks.
 */
public interface TypeProxy
        extends QueryService<Type, String, TypeFilter>, SaveService<Type>, DeleteService<Type, String> {

    Type findOrCreate(String type);
}
