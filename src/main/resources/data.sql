/*-------------------------------------------------------------------------------------------*/
DROP TABLE IF EXISTS equipo;
CREATE TABLE equipo (
  idequipo INT NOT NULL,
  nombre VARCHAR(45) NOT NULL,
  puntuacion INT NOT NULL,
  PRIMARY KEY (idequipo));
/*-------------------------------------------------------------------------------------------*/