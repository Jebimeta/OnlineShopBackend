# Online Shop

Back End - Online Shop.

## Configuración inicial

Para poder ejecutar la aplicación, en primer lugar es necesario compilar la aplicación haciendo uso del siguiente comando de Maven: 

* `mvn spring-javaformat:apply clean install`

Una vez el proyecto ha sido compilado se deben cambiar las rutas del apartado "upload" dentro del fichero application-local.yml por las nuevas  rutas del entorno local donde tengas guardada la aplicación.


```yml
spring:
    web:
      resources:
        static-locations: classpath:/static/, file:D:/Github/ritarouge/src/main/resources/static/products/images
    
ritarouge:
  upload:
    localDirectory: file:D:/Github/ritarouge/src/main/resources/static/products/images/

```

Por último se utilizaran las siguientes variables de entorno en la run/debug configuration de la aplicación con las credenciales necesarias:
```yml
  SMTP_USERNAME=email@email.com; SMTP_PASSWORD=passwword
```


