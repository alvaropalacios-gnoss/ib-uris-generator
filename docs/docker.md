![](./images/logos_feder.png)

| Fecha         | 25/05/2020                                                   |
| ------------- | ------------------------------------------------------------ |
| Proyecto      | [ASIO](https://www.um.es/web/hercules/proyectos/asio) (Arquitectura Semántica e Infraestructura Ontológica) en el marco de la iniciativa [Hércules](https://www.um.es/web/hercules/) para la Semántica de Datos de Investigación de Universidades que forma parte de [CRUE-TIC](http://www.crue.org/SitePages/ProyectoHercules.aspx) |
| Módulo        | Arquitectura Semántica                                       |
| Tipo          | Documentación                                                |
| Objetivo      | El presente documento describe el proceso de generación y ejecución de una imagen docker a partir de el código del proyecto |
| Documentación | [Esquema de URIs](https://github.com/HerculesCRUE/ib-asio-docs-/blob/master/entregables_hito_1/08-Esquema_de_URIs_Hércules/ASIO_Izertis_ArquitecturaDeURIs.md)<br/>[Buenas practicas para URIs Hércules](https://github.com/HerculesCRUE/ib-asio-docs-/blob/master/entregables_hito_1/09-Buenas_prácticas_para_URIs_Hércules/ASIO_Izertis_BuenasPracticasParaURIsHercules.md)<br/>[Manual de usuario](./manual_de_usuario.md) (documentación de alto nivel)<br />[Documentación técnica](./documentacion-tecnica.md) (documentación de bajo nivel)<br/>[Documentación API REST de la Factoria de URIs](./documentacion_api_rest_de_la_factoria_de_uris.md) (documentación de bajo nivel)<br/>[README](../README.md)<br/>[docker](./docker.md) |

# Generación de imagen Docker

Los artefactos bootables están diseñados para poder ser distribuidos como imagen Docker. Se indicarán a continuación las instrucciones.

## Compilación

En primer lugar es preciso [compilar el artefacto](build.md) y copiar el JAR generado en el directorio `docker-build/java` con el nombre `app.jar`

## Generación de la imagen

Para la generación de la imagen se precisa ejecutar el siguiente comando desde el directorio `docker-build` que es donde se encuentra el fichero `Dockerfile`.

```bash
docker build . -t {artifact-name}:{tag}
```

Sustituyendo `{artifact-name}` y `{tag}` por el nombre del artefacto y la versión respectivamente.

En caso que se desee distribuir la imagen a través de un Registry de Docker, se deberá hacer un `pull` mediante la ejecución el comando:

```bash
docker pull {artifact-name}:{tag}
```

Es posible que algunos Registros requieran de autenticación previa, debiendo para ello ejecutar previamente un `docker login`.
