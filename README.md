# MutantDNA
Magneto busca mutantes

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar
contra los X-Men.
Te ha contratado a ti para que desarrolles un proyecto que detecte si un
humano es mutante basándose en su secuencia de ADN.

# Title

La API cuenta con dos métodos (el primero POST y el segundo GET), encargados de satisfar tanto la evaluación de del ADN mutante, como las estadisticas de toda la información recolectada.

# Métodos

## /mutant

Servicio expuesto en la el endpoint https://mutantdna-env.eba-iwmu82sw.us-east-2.elasticbeanstalk.com/mutant encargado de evaluar y guardar la inforamción de un ADN.

para consumir el servicio se debe enviar realizar una petición POST con un JSON así:

{
“dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
}

* Para una respuesta satisfactoria el servicio retornará HTTP 200-OK
* Para las respuestas incorrectas el servicio retornará 403-Forbidden

## /stats

Servicio expuesto en la el endpoint https://mutantdna-env.eba-iwmu82sw.us-east-2.elasticbeanstalk.com/stats encargado de responder con las estadisticas de la información guardada en el servicio /mutant.

* la respuesta en formato JSON de este servicio se debe dar de la siguiente manera:
  {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}
  
# Arquitectura y definición técnica de la API

El software está construido con las siguientes técnologías:
* Java 1.8 Spring Boot
* Maven
* Tomcat 9
* AWS Elastic Beanstalk
* AWS DynamoDB
* JUnit
* Mockito

# Test de la aplicación

Se utilizó JUnit para las pruebas unitarias de los diferentes eventos que se estimaron para la solución construida, teniendo un 82% de code average
