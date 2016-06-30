#Travis 
[![Build Status](https://travis-ci.com/dds-utn/2016-jm-group-07.svg?token=WZANzTsTpqeJqzz5zjW8&branch=master)](https://travis-ci.com/dds-utn/2016-jm-group-07)


# Mails


rodrigocampassi@gmail.com

espositolucas95@gmail.com

lucasdattoli96@gmail.com

juanpadilla.jip@gmail.com

emi.tolaba95@gmail.com

# Waffle
[![Issues en waffle](https://waffle.io/dds-utn/2016-jm-group-07)](https://waffle.io/dds-utn/2016-jm-group-07)

# Manejo de archivos
[![Libreria externa para manejar archivos](https://commons.apache.org/proper/commons-io/javadocs/api-2.4/org/apache/commons/io/FileUtils.html)](https://commons.apache.org/proper/commons-io/javadocs/api-2.4/org/apache/commons/io/FileUtils.html)

## Ver paquetes en forma comoda:
En eclipse, estando con Package explore, ir a la flechita hacia abajo que aparece arriba de la lista de proyectos -> Package presentation -> Hieranchical

De esa forma las cosas, segun el orden que les puse, se ven como corresponde y quedan en subcarpetas, en vez de verse en forma rara.

# Tercera Entrega
### Diagrama de clases 3ra entrega y solucion alternativa con decorator
https://1drv.ms/f/s!Av0vPKTcwzssgTbfFJSb1x_Q5v76

Solucion 1 (Elegida): Activador booleano
![alt tag](http://i.imgur.com/yldpNMD.jpg)

Solucion 2: Decorator
![alt tag](http://i.imgur.com/oVeHxGi.jpg)


### Justificación de la solucón elegida
Tomamos la solución 1 porque es mucho mayor la simpleza de mantener un estado con dos booleanos que la de decorar un objeto y tener que decorarlo y "des"decorarlo dinámicamente.
A costa de esto, perdemos la flexibilidad que podría darnos el uso de decorators, si en un futuro se requieren mas acciones. Al ser la clase Terminal la que tenga que encargarse no sólo de lo propio de una Terminal, sino tambien de llamar a las clases que se encargan de enviar el mail o registrar busquedas, y de determinar si debe o no llamarlas, se pierde cohesividad, aunque consideramos que el impacto de esto en el sistema no es sustancial en este caso ya que la cohesividad perdida es baja, mientras que usando un decorator haríamos Terminal más cohesiva, pero a costa de complejizar el sistema. 

De acuerdo al dominio del problema, usar decorator para 2 acciones podria ser considerado un sobrediseño, asi que valoramos la simplicidad por sobre algo de cohesividad y una flexibilidad que podria nunca llegar a ser necesaria, aunque consideramos muy probable el uso de decorator en un futuro si efectivamente nos piden agregar nuevas funcionalidades.
Hicimos más cohesivo al BuscadorTexto, usando un singleton que contiene los lugares donde tiene que buscar el Buscador, y trasladando a la Terminal, al los reportes y al EmailSender las responsabilidades que a ellos les correspondian, y anteriormente eran realizadas por el buscador. Antes, el Buscador de texto conocía directamente a los medios, teniendo la responsabilidad de saber como tenia que llamar a cada uno. Ahora es mas fácil agregar nuevos medios de búsqueda, ya que en vez de tener que modificar una clase poco cohesiva, con mucho comportamiento y agregarle no solo el medio nuevo sino tambien el llamado, ahora con agregar un medio a la lista del repositorio todo funciona correctamente.
Tambien separamos la responsabilidad de buscar en los pois locales en una nueva clase BuscadorLocal, quien conoce a Mapa y le pide la lista de los poi.

Tambien convertimos la clase Mapa en un singleton para asegurar la unicidad de los datos


#Cuarta Entrega
###Diagrama de clases cuarta entrega

Solucion 1 (Elegida)
https://1drv.ms/u/s!Av0vPKTcwzssgTtJmbyCGPK_IGnk
![alt tag](http://i.imgur.com/WlosSWi.png)
