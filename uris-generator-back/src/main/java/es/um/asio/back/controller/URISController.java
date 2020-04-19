package es.um.asio.back.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import es.um.asio.back.controller.uri.CanonicalURILanguageController;
import es.um.asio.service.model.CanonicalURILanguage;
import es.um.asio.service.proxy.CanonicalURILanguageProxy;
import es.um.asio.service.util.Utils;
import es.um.asio.service.validation.group.Create;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import es.um.asio.abstractions.constants.Constants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * URIS controller.
 */
@RestController
@RequestMapping(URISController.Mappings.BASE)
public class URISController {
	
	
	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(URISController.class);



    /**
     * Proxy service implementation for {@link CanonicalURILanguage}.
     */
    @Autowired
    private CanonicalURILanguageController canonicalController;

	/**
    * Creates the resourceID URI
    * <p>
    * Example
    * <p>
    * <pre>
    * {
    *    obj: 
    *    {
	*      class=es.um.asio.service.util.data.ConceptoGrupo, 
	*      entityId=null, 
	*      version=0, 
	*      idGrupoInvestigacion=E0A6-01, 
	*      numero=5, 
	*      codTipoConcepto=DESCRIPTORES, 
	*      texto=LENGUAJES PLASTICOS
	*    },
	*    language: es,
	*    university: https://www.um.es
    * }
    * </pre>
    * @param input HashMap.
    * @return URI for the input property.
    */
    @SuppressWarnings({ "rawtypes", "unused" })
	@PostMapping(URISController.Mappings.RESOURCE_ID)
    public Map<String,String> createResourceID(
            @ApiParam( name = "domain", value = "Domain: hercules.org", defaultValue = "hercules.org", required = false)
            @RequestParam(required = true) @Validated(Create.class) final String domain,
            @ApiParam( name = "subDomain", value = "Subdomain: um (universidad de murcia)", defaultValue = "um", required = false)
            @RequestParam(required = true) @Validated(Create.class) final String subDomain,
            @ApiParam( name = "lang", value = "Language of data", defaultValue = "es-ES", required = false)
            @RequestParam(required = true) @Validated(Create.class) final String lang,
            @RequestBody final Object input) {
        logger.info(input.toString());
        try {
            HashMap map = (HashMap) input;
            String dom = (domain!=null)?domain:"hercules.org";
            String subDom = (subDomain!=null)?subDomain:"um";
            String language = (lang!=null)?lang:"es-ES";
            String type = "res";
            String entity = Utils.getClassNameFromPath( (String) (map.get("className")!=null?(map.get("className")):(map.get("class"))) );
            String pEntity = Utils.getClassNameFromPath( (String) (map.get("canonicalClassName")!=null?(map.get("canonicalClassName")):(map.get("canonicalClass"))) );
            String ref = Utils.generateUUIDFromOject(input);

            CanonicalURILanguage canonicalURILanguage = canonicalController.save(dom, subDom, language, type, entity, ref,null,(pEntity!=null)?pEntity:entity,(pEntity!=null)?pEntity:entity, true);
            Map<String,String> response = new HashMap<>();
            response.put("canonicalURI", canonicalURILanguage.getFullParentURI());
            response.put("language", lang);
            response.put("canonicalLanguageURI", canonicalURILanguage.getFullURI());
            return response;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    /** http://hercules.org/um/es-ES/rec/ConceptoGrupo/8d606c3a-13c7-3cc7-ab78-fb8f8595771b
    * Creates the property URI
    * <p>
    * Example
    * <p>
    * <pre>
    * {
    *   obj: {
    *      class=es.um.asio.service.util.data.ConceptoGrupo, 
    *      entityId=null, 
    *      version=0, 
    *      idGrupoInvestigacion=E0A6-01, 
    *      numero=5, 
    *      codTipoConcepto=DESCRIPTORES, 
    *      texto=LENGUAJES PLASTICOS
    *    },
    *    property: idGrupoInvestigacion,
    *    resourceID: http://example.org/1321/012/1/2012%2F05%2F,
    *    language: es,
    *    university: https://www.um.es
    *  }
    * </pre>
    * @param input HashMap.
    * @return URI for the input property.
    */
    @SuppressWarnings({ "rawtypes", "unused" })
    @PostMapping(URISController.Mappings.PROPERTY_URI)
    public Map<String,String> createPropertyURI(
            @ApiParam( name = "domain", value = "Domain: hercules.org", defaultValue = "hercules.org", required = false)
            @RequestParam(required = true) @Validated(Create.class) final String domain,
            @ApiParam( name = "subDomain", value = "Subdomain: um (universidad de murcia)", defaultValue = "um", required = false)
            @RequestParam(required = true) @Validated(Create.class) final String subDomain,
            @ApiParam( name = "lang", value = "Language of data", defaultValue = "es-ES", required = false)
            @RequestParam(required = true) @Validated(Create.class) final String lang,
            @RequestBody final Object input) {
        logger.info("Creating property URI");

        HashMap map = (HashMap) input;
        String dom = (domain!=null)?domain:"hercules.org";
        String subDom = (subDomain!=null)?subDomain:"um";
        String language = (lang!=null)?lang:"es-ES";
        String type = "res";
        HashMap objMap = (HashMap) map.get("obj");
        String entity = Utils.getClassNameFromPath( (String) (objMap.get("className")!=null?(objMap.get("className")):(objMap.get("class"))) );
        String pEntity = Utils.getClassNameFromPath( (String) (objMap.get("canonicalClassName")!=null?(objMap.get("canonicalClassName")):(objMap.get("canonicalClass"))) );
        String property = Utils.getClassNameFromPath( (String) map.get("property") );
        String cProperty = Utils.getClassNameFromPath( (String) map.get("canonicalProperty") );


        CanonicalURILanguage canonicalURILanguage = canonicalController.save(dom, subDom, language, type, entity, null,property,(pEntity!=null)?pEntity:entity,(cProperty!=null)?cProperty:property, true);
        Map<String,String> response = new HashMap<>();
        response.put("canonicalURI", canonicalURILanguage.getFullParentURI());
        response.put("language", lang);
        response.put("canonicalLanguageURI", canonicalURILanguage.getFullURI());
        return response;
    }
    
    /**
     * Creates the resource type URI
     * <p>
     * Example
     * <p>
     * <pre>
     * {
 	 *   className: ConceptoGrupo,
 	 *   language: es, 
 	 *   university: https://www.um.es
     * }
     * </pre>
     * @param input HashMap.
     * @return URI for the input property.
     */
    @SuppressWarnings({ "rawtypes", "unused" })
    @PostMapping(URISController.Mappings.RESOURCE_TYPE_URI)
    public Map<String,String> createResourceTypeURI(
            @ApiParam( name = "domain", value = "Domain: hercules.org", defaultValue = "hercules.org", required = false)
            @RequestParam(required = true) @Validated(Create.class) final String domain,
            @ApiParam( name = "subDomain", value = "Subdomain: um (universidad de murcia)", defaultValue = "um", required = false)
            @RequestParam(required = true) @Validated(Create.class) final String subDomain,
            @ApiParam( name = "lang", value = "Language of data", defaultValue = "es-ES", required = false)
            @RequestParam(required = true) @Validated(Create.class) final String lang,
            @RequestBody final Object input) {
    	logger.info("Creating resource type uri: ");

        HashMap map = (HashMap) input;
        String dom = (domain!=null)?domain:"hercules.org";
        String subDom = (subDomain!=null)?subDomain:"um";
        String language = (lang!=null)?lang:"es-ES";
        String type = "res";
        String entity = Utils.getClassNameFromPath( (String) (map.get("className")!=null?(map.get("className")):(map.get("class"))) );
        String pEntity = Utils.getClassNameFromPath( (String) (map.get("canonicalClassName")!=null?(map.get("canonicalClassName")):(map.get("canonicalClass"))) );
        CanonicalURILanguage canonicalURILanguage = canonicalController.save(dom, subDom, language, type, entity, null,null,(pEntity!=null)?pEntity:entity,null, true);
        Map<String,String> response = new HashMap<>();
        response.put("canonicalURI", canonicalURILanguage.getFullParentURI());
        response.put("language", lang);
        response.put("canonicalLanguageURI", canonicalURILanguage.getFullURI());
        return response;
    }
	
	 /**
     * Mappgins.
     */
    
    /**
     * Instantiates a new mappings.
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    static final class Mappings {
        

    	/** The Constant RESOURCE_ID. */
    	public static final String RESOURCE_ID = "canonical/resource";
    	
		/** The Constant PROPERTY_URI. */
		public static final String PROPERTY_URI = "canonical/property";

		/** The Constant RESOURCE_TYPE_URI. */
		public static final String RESOURCE_TYPE_URI = "canonical/entity";
		/**
         * Controller request mapping.
         */
        protected static final String BASE = "/uri-factory";

    }
}
