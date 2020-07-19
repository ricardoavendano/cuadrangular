# Cuadrangular
Ricardo Avendaño Casas

Para ejecutar el proyecto se deben realizar los siguientes pasos
1. descargar fuente de github: git clone 
2. ir al directorio donde se encuentra el fuente y crear jar: mvn clean install (se crea la carpeta target)
3. ir al directorio donde se encuentra el fuente y dirigirse a la carpeta target; por linea de comandos ejecutar jar: java -jar cuadrangular-0.0.1-SNAPSHOT.jar
4. La aplicacion ya se encuentra desplegada localmente en la url (http://localhost:8081)
5. endpoint de la aplicacion
	1. POST http://localhost:8081/cuadrangular/cargar/equipos 
			body ejemplo:
			{
			  "numeroEquipo": 3
			}
	2. POST http://localhost:8081/cuadrangular/guardar/puntos
			body ejemplo:
			{
			  "ligaList": [
				{
				  "partidoList": [
					{
					  "nombre": "Equipo2",
					  "puntuacion": 0
					},
					{
					  "nombre": "Equipo3",
					  "puntuacion": 0
					}
				  ]
				},
				{
				  "partidoList": [
					{
					  "nombre": "Equipo1",
					  "puntuacion": 0
					},
					{
					  "nombre": "Equipo2",
					  "puntuacion": 0
					}
				  ]
				},
				{
				  "partidoList": [
					{
					  "nombre": "Equipo3",
					  "puntuacion": 0
					},
					{
					  "nombre": "Equipo1",
					  "puntuacion": 0
					}
				  ]
				}
			  ]
			}
6. Ingreso a la BD H2
	url: http://localhost:8081/cuadrangular/h2-console/login.jsp
	JDBC URL: jdbc:h2:mem:cuadrangular
	User name: cuadrangular
	Password: cuadrangular
	
	Tabla: EQUIPO
7. Uso de Swagger
	http://localhost:8081/cuadrangular/swagger-ui.html#/
