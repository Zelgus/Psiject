-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 30-03-2015 a las 20:41:55
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `psiject`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `campos`
--

CREATE TABLE IF NOT EXISTS `campos` (
`ID_Campo` bigint(20) NOT NULL,
  `Descripcion` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Nombre` varchar(255) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `campos`
--

INSERT INTO `campos` (`ID_Campo`, `Descripcion`, `Nombre`) VALUES
(1, '', 'Comentarios psicólogo'),
(2, 'Descripción del momento en que ocurre el acontecimiento.', 'Situación'),
(3, '¿Qué estoy pensando en ese momento? ¿Qué es lo que se me viene a la cabeza?', 'Pensamiento'),
(4, '¿Qué siento físicamente en el cuerpo? ¿En qué parte?', 'Sensación'),
(5, '¿Cómo me siento: triste, rabioso/a, nervioso/a, hundido/a, etc.?', 'Emoción'),
(6, '¿Qué es lo que hago o dejo de hacer cuando esto me ocurre?', 'Comportamiento'),
(7, '¿Qué creo que si pensara y/o hiciera me serviría para estar mejor en esta situación? ¿Lo llevo a cabo?', 'Alternativa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE IF NOT EXISTS `comentarios` (
`ID_Comentario` bigint(20) NOT NULL,
  `Comentario` longtext COLLATE utf8_bin NOT NULL,
  `Hora` datetime NOT NULL,
  `Visto` tinyint(1) NOT NULL,
  `ID_Tarea_Campo` bigint(20) DEFAULT NULL,
  `ID_Usuario_Creador` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `comentarios`
--

INSERT INTO `comentarios` (`ID_Comentario`, `Comentario`, `Hora`, `Visto`, `ID_Tarea_Campo`, `ID_Usuario_Creador`) VALUES
(1, 'Es recomendable que duermas con una libreta cerca para acceder a ella nada más despertar.', '2015-03-30 19:50:53', 0, 12, 3),
(2, 'Son las 11 de la mañana y en la panadería hay mucha gente.', '2015-03-30 20:04:04', 1, 4, 8),
(3, 'Me puse muy nerviosa...', '2015-03-30 20:04:24', 1, 2, 8),
(4, 'Si llevara lo que quiero comprar apuntado en un papel no me pondría tan nerviosa.', '2015-03-30 20:04:04', 1, 3, 8),
(5, 'Estoy muy cansado, quiero seguir durmiendo', '2015-03-30 20:04:42', 1, 10, 11),
(6, 'Me vuelvo a dormir, no tengo ganas de levantarme', '2015-03-30 20:04:42', 1, 11, 11),
(7, 'Me siento relajada y feliz con mi perro.', '2015-03-30 20:05:23', 1, 6, 8),
(8, 'Feliz.', '2015-03-30 20:05:23', 1, 7, 8),
(9, 'No hay forma humana de mejorar este momento.', '2015-03-30 20:05:23', 1, 8, 8),
(10, 'Necesitaba dormir, por eso me encuentro mejor ahora', '2015-03-30 20:05:56', 1, 14, 11),
(11, 'Me siento pesado, tras dormir un poco mas ya me encuentro mejor', '2015-03-30 20:05:56', 1, 13, 11),
(12, 'Echo mucho de menos a mi perro...', '2015-03-30 20:06:43', 1, 30, 14),
(13, 'Siento ganas de llorar', '2015-03-30 20:06:43', 1, 31, 14),
(14, 'Muy triste :(', '2015-03-30 20:06:43', 1, 32, 14),
(15, 'Por la tarde, sobre las 6pm.', '2015-03-30 20:07:49', 1, 16, 5),
(16, '-', '2015-03-30 20:07:50', 1, 17, 5),
(17, '-', '2015-03-30 20:07:50', 1, 18, 5),
(18, '-', '2015-03-30 20:07:50', 1, 19, 5),
(19, 'Subo la Giralda mecánicamente y sin detenerme.', '2015-03-30 20:07:50', 1, 20, 5),
(20, '-', '2015-03-30 20:07:50', 1, 21, 5),
(21, 'Voy por la primera planta.', '2015-03-30 20:09:02', 1, 23, 5),
(22, 'De momento no siento nada especial', '2015-03-30 20:09:02', 1, 24, 5),
(23, 'Sigo subiendo sin miedo.', '2015-03-30 20:09:02', 1, 25, 5),
(24, '-', '2015-03-30 20:09:02', 1, 26, 5),
(25, 'Segundo piso.', '2015-03-30 20:10:01', 1, 23, 5),
(26, 'Sigo sin sentir miedo', '2015-03-30 20:10:02', 1, 24, 5),
(27, 'Pienso que voy a pasar miedo en los pisos siguientes...', '2015-03-30 20:10:02', 1, 25, 5),
(28, 'Si hubiera subido en ascensor todo sería más fácil...', '2015-03-30 20:10:02', 1, 26, 5),
(29, 'Estoy en el sofa despues de un largo dia en casa', '2015-03-30 20:11:03', 1, 34, 12),
(30, 'Hecho de menos a mi madre, la estuve añorando todo el dia', '2015-03-30 20:11:04', 1, 35, 12),
(31, 'Siento un frio por los brazos, como cuando ella me acariciaba. Llevo todo el dia como que me falta su presencia.', '2015-03-30 20:11:04', 1, 36, 12),
(32, 'Quiero llorar', '2015-03-30 20:11:04', 1, 37, 12),
(33, 'Me tumbo a llorar, ahora me voy a la cama a seguir haciendolo e intentar que pase este martirio de dia', '2015-03-30 20:11:04', 1, 38, 12),
(34, 'Tercer, cuarto y quinto piso...', '2015-03-30 20:11:08', 1, 23, 5),
(35, 'He comenzado a temblar al aproximarme al tercer piso', '2015-03-30 20:11:08', 1, 24, 5),
(36, 'Debido al miedo, he decidido subir corriendo los pisos tercero, cuarto y quinto.', '2015-03-30 20:11:08', 1, 25, 5),
(37, '-', '2015-03-30 20:11:08', 1, 26, 5),
(38, 'Ya estoy en mi casa', '2015-03-30 20:12:38', 1, 23, 5),
(39, 'Me siento relajado al llegar a mi casa', '2015-03-30 20:12:39', 1, 24, 5),
(40, '-', '2015-03-30 20:12:39', 1, 25, 5),
(41, '-', '2015-03-30 20:12:39', 1, 26, 5),
(42, 'Dolor de barriga y fatiga', '2015-03-30 20:14:13', 1, 28, 5),
(43, 'Intenta acercarte poco a poco a las ventanas la proxima vez, mirando el paisaje a los lejos.', '2015-03-30 20:16:03', 0, 15, 2),
(44, 'Recuerda que es muy importante seguir las indicaciones del psicólogo. Debes hacer un esfuerzo mayor.', '2015-03-30 20:16:20', 0, 9, 1),
(45, 'Bien hecho, has conseguido subir al menos corriendo, el proximo dia sube acompañado de alguien y vuelve a mirar como hasta ahora', '2015-03-30 20:19:47', 1, 22, 2),
(46, 'Pásate por la consulta mañana a las 10:30 am.\r\nYa me encargo yo de comunicarle a Zelgus que os véis a esa hora.', '2015-03-30 20:24:48', 1, 33, 1),
(47, 'Retrasa la hora a las 10:30 y continua con la tarea', '2015-03-30 20:26:49', 0, 9, 2),
(48, 'Estoy de acuerdo con mi compañero.', '2015-03-30 20:36:16', 0, 15, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `expedientes`
--

CREATE TABLE IF NOT EXISTS `expedientes` (
`ID_Expediente` bigint(20) NOT NULL,
  `Apellidos` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Cerrado` tinyint(1) NOT NULL,
  `Nombre` varchar(255) COLLATE utf8_bin NOT NULL,
  `ID_Usuario` bigint(20) DEFAULT NULL,
  `ID_Usuario_Creador` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `expedientes`
--

INSERT INTO `expedientes` (`ID_Expediente`, `Apellidos`, `Cerrado`, `Nombre`, `ID_Usuario`, `ID_Usuario_Creador`) VALUES
(1, 'Perez Martin', 0, 'Pedro', 5, 2),
(2, 'López', 1, 'Marta', 8, 1),
(3, 'Lobo Fernandez', 0, 'Marta', 9, 2),
(4, 'Chaves Talero', 0, 'Ramon', 10, 2),
(5, 'Del Bosque', 0, 'Jacinto', 11, 3),
(6, 'Hernandez Corento', 0, 'Laura', 12, 4),
(7, 'Díaz', 0, 'Juan', 13, 3),
(8, 'Fal-Conde Terrero', 1, 'Juan Antonio', 14, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `expedientes_psicologos`
--

CREATE TABLE IF NOT EXISTS `expedientes_psicologos` (
`ID_Expediente_Psicologo` bigint(20) NOT NULL,
  `ID_Expediente` bigint(20) DEFAULT NULL,
  `ID_Usuario` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `expedientes_psicologos`
--

INSERT INTO `expedientes_psicologos` (`ID_Expediente_Psicologo`, `ID_Expediente`, `ID_Usuario`) VALUES
(1, 1, 2),
(2, 2, 1),
(3, 3, 2),
(4, 4, 2),
(5, 5, 1),
(6, 6, 4),
(7, 7, 3),
(8, 8, 4),
(9, 8, 1),
(11, 6, 1),
(12, 6, 2),
(13, 1, 1),
(14, 3, 4),
(15, 5, 4),
(16, 5, 2),
(17, 5, 3),
(18, 2, 2),
(19, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareas`
--

CREATE TABLE IF NOT EXISTS `tareas` (
`ID_Tarea` bigint(20) NOT NULL,
  `Completada` tinyint(1) NOT NULL,
  `Conclusiones_Paciente` longtext COLLATE utf8_bin,
  `Conclusiones_Psicologo` longtext COLLATE utf8_bin,
  `Descripcion` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Importante` tinyint(1) NOT NULL,
  `Titulo` varchar(255) COLLATE utf8_bin NOT NULL,
  `Vista` tinyint(1) NOT NULL,
  `ID_Expediente` bigint(20) DEFAULT NULL,
  `ID_Usuario_Creador` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `tareas`
--

INSERT INTO `tareas` (`ID_Tarea`, `Completada`, `Conclusiones_Paciente`, `Conclusiones_Psicologo`, `Descripcion`, `Importante`, `Titulo`, `Vista`, `ID_Expediente`, `ID_Usuario_Creador`) VALUES
(1, 1, 'Todo tus miedos y nervios al comprar están en tu cabeza, la panadería no supone ningún peligro.', '', 'Debes ir a la panadería más cercana sola y pedir un bollo.', 0, 'Ve a comprar', 1, 2, 1),
(2, 1, 'Enhorabuena los has hecho muy bien', 'Parece que con tratamiento animal ha mejorado bastante la paciente, considero que podria estar ya bien', 'Da una vuelta a la manza con tu perro durante una semana', 1, 'Pasea a tu perro', 1, 2, 2),
(3, 0, NULL, NULL, 'Durante 3 o 4 dias de esta semana madruga un poco mas de lo habitual', 0, 'Levantate a las 9:30', 1, 5, 2),
(4, 1, 'La vida se te escapa durmiendo...', 'Tiene un serio problema de motivación personal.', 'Cada mañana al levantarte escribe como te sientes.', 0, 'Apunta tus sentimientos.', 1, 5, 3),
(5, 1, '', 'Va superando sus miedos poco a poco.', '', 1, 'Sube la Giralda', 1, 1, 1),
(6, 0, NULL, NULL, 'Sube las escaleras de tu bloque y en cada piso mira por el hueco, describiendo tus sensaciones.', 0, 'Sube las escaleras', 1, 1, 2),
(7, 1, 'Lo has hecho bien, procura hacer esto de vez en cuando, cierra los ojos o prueba el columpio con tus hijos', 'Presenta signos de vertigo severo, aunque no da la sensacion de miedo. A alturas bajas parece que va mejorando poco a poco', 'Ve al parque y utiliza el columpio, que alguien te empuje cada mas fuerte', 0, 'Utiliza el columpio', 1, 1, 2),
(8, 1, 'Avanzas adecuadamente, sigue saliendo con tu amigo y su perro cuando te apetezca.', 'Ha superado la pérdida de su perro.', 'Acompaña a tu amigo cuando saque a su perro a pasear.', 1, 'Pasea al perro de tu amigo', 1, 8, 1),
(9, 0, NULL, NULL, 'Explica durante una semana tus sensaciones y emociones al final de cada dia', 1, 'Rellena los campos cada dia', 1, 6, 2),
(10, 0, NULL, NULL, 'Recuerda mirarte cada mañana al espejo y darte ánimos.', 0, 'Tu reflejo en el espejo', 1, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tareas_campos`
--

CREATE TABLE IF NOT EXISTS `tareas_campos` (
`ID_Tarea_Campo` bigint(20) NOT NULL,
  `ID_Campo` bigint(20) DEFAULT NULL,
  `ID_Tarea` bigint(20) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `tareas_campos`
--

INSERT INTO `tareas_campos` (`ID_Tarea_Campo`, `ID_Campo`, `ID_Tarea`) VALUES
(1, 1, 1),
(2, 5, 1),
(3, 7, 1),
(4, 2, 1),
(5, 1, 2),
(6, 4, 2),
(7, 5, 2),
(8, 7, 2),
(9, 1, 3),
(10, 3, 3),
(11, 6, 3),
(12, 1, 4),
(13, 4, 4),
(14, 3, 4),
(15, 1, 5),
(16, 2, 5),
(17, 3, 5),
(18, 4, 5),
(19, 5, 5),
(20, 6, 5),
(21, 7, 5),
(22, 1, 6),
(23, 2, 6),
(24, 4, 6),
(25, 6, 6),
(26, 7, 6),
(27, 1, 7),
(28, 4, 7),
(29, 1, 8),
(30, 3, 8),
(31, 4, 8),
(32, 5, 8),
(33, 1, 9),
(34, 2, 9),
(35, 3, 9),
(36, 4, 9),
(37, 5, 9),
(38, 6, 9),
(39, 1, 10);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE IF NOT EXISTS `usuarios` (
`ID_Usuario` bigint(20) NOT NULL,
  `Contrasena` varchar(255) COLLATE utf8_bin NOT NULL,
  `Correo` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `Envio` datetime DEFAULT NULL,
  `Permiso` tinyint(1) NOT NULL,
  `Usuario` varchar(20) COLLATE utf8_bin NOT NULL,
  `Verificacion` varchar(255) COLLATE utf8_bin DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`ID_Usuario`, `Contrasena`, `Correo`, `Envio`, `Permiso`, `Usuario`, `Verificacion`) VALUES
(1, '39d1037b3cfb5c8ff79c44e43e86dbc9c67488e221ac438f05f31957acb115d547929188d447222af722df2de21ee62f1ac083f163199962a7529614016253fd', 'jukin4@gmail.com', NULL, 1, 'Jukin', NULL),
(2, '39d1037b3cfb5c8ff79c44e43e86dbc9c67488e221ac438f05f31957acb115d547929188d447222af722df2de21ee62f1ac083f163199962a7529614016253fd', 'ivanom90@hotmail.com', NULL, 1, 'Zelgus', NULL),
(3, '39d1037b3cfb5c8ff79c44e43e86dbc9c67488e221ac438f05f31957acb115d547929188d447222af722df2de21ee62f1ac083f163199962a7529614016253fd', 'jukin4@hotmail.com', NULL, 1, 'Jukin4', NULL),
(4, '39d1037b3cfb5c8ff79c44e43e86dbc9c67488e221ac438f05f31957acb115d547929188d447222af722df2de21ee62f1ac083f163199962a7529614016253fd', 'ivaortmun90@gmail.com', NULL, 1, 'Zelgus90', NULL),
(5, '39d1037b3cfb5c8ff79c44e43e86dbc9c67488e221ac438f05f31957acb115d547929188d447222af722df2de21ee62f1ac083f163199962a7529614016253fd', 'psiject@psiject.com', NULL, 0, 'PedPer', NULL),
(8, '39d1037b3cfb5c8ff79c44e43e86dbc9c67488e221ac438f05f31957acb115d547929188d447222af722df2de21ee62f1ac083f163199962a7529614016253fd', 'psiject1@psiject.com', NULL, 0, 'MarLop', NULL),
(9, '39d1037b3cfb5c8ff79c44e43e86dbc9c67488e221ac438f05f31957acb115d547929188d447222af722df2de21ee62f1ac083f163199962a7529614016253fd', 'psiject2@psiject.com', NULL, 0, 'MarLob', NULL),
(10, '39d1037b3cfb5c8ff79c44e43e86dbc9c67488e221ac438f05f31957acb115d547929188d447222af722df2de21ee62f1ac083f163199962a7529614016253fd', 'psiject4@psiject.com', NULL, 0, 'RamCha', NULL),
(11, '39d1037b3cfb5c8ff79c44e43e86dbc9c67488e221ac438f05f31957acb115d547929188d447222af722df2de21ee62f1ac083f163199962a7529614016253fd', 'psiject3@psiject.com', NULL, 0, 'JacDel', NULL),
(12, '39d1037b3cfb5c8ff79c44e43e86dbc9c67488e221ac438f05f31957acb115d547929188d447222af722df2de21ee62f1ac083f163199962a7529614016253fd', 'psiject6@psiject.com', NULL, 0, 'LauHer', NULL),
(13, '39d1037b3cfb5c8ff79c44e43e86dbc9c67488e221ac438f05f31957acb115d547929188d447222af722df2de21ee62f1ac083f163199962a7529614016253fd', 'psiject5@psiject.com', NULL, 0, 'JuaDia', NULL),
(14, '39d1037b3cfb5c8ff79c44e43e86dbc9c67488e221ac438f05f31957acb115d547929188d447222af722df2de21ee62f1ac083f163199962a7529614016253fd', 'psiject7@psiject.com', NULL, 0, 'JuaFal', NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `campos`
--
ALTER TABLE `campos`
 ADD PRIMARY KEY (`ID_Campo`), ADD UNIQUE KEY `UK_94vntyhjgxbpbf926schoff5r` (`Nombre`);

--
-- Indices de la tabla `comentarios`
--
ALTER TABLE `comentarios`
 ADD PRIMARY KEY (`ID_Comentario`), ADD KEY `FK_dkv6un4jthmsyfa0qbmoib5f7` (`ID_Tarea_Campo`), ADD KEY `FK_on2romdtuq04wu6mw5jskf5ne` (`ID_Usuario_Creador`);

--
-- Indices de la tabla `expedientes`
--
ALTER TABLE `expedientes`
 ADD PRIMARY KEY (`ID_Expediente`), ADD KEY `FK_af4na5948mb3emvxege0peod7` (`ID_Usuario`), ADD KEY `FK_q98ktbmndx5u9fq41hes50ifr` (`ID_Usuario_Creador`);

--
-- Indices de la tabla `expedientes_psicologos`
--
ALTER TABLE `expedientes_psicologos`
 ADD PRIMARY KEY (`ID_Expediente_Psicologo`), ADD KEY `FK_foukmkeq9xn1xa04yhg2oeef6` (`ID_Expediente`), ADD KEY `FK_n0jrsvlfdgy5lqewx5ib2ixic` (`ID_Usuario`);

--
-- Indices de la tabla `tareas`
--
ALTER TABLE `tareas`
 ADD PRIMARY KEY (`ID_Tarea`), ADD KEY `FK_3pwdxajpukfyncrevvvr1yp7k` (`ID_Expediente`), ADD KEY `FK_ck5u9p6vxil85214l5uxvi4wg` (`ID_Usuario_Creador`);

--
-- Indices de la tabla `tareas_campos`
--
ALTER TABLE `tareas_campos`
 ADD PRIMARY KEY (`ID_Tarea_Campo`), ADD KEY `FK_99i6bbmxxlws3wxc16tyrome6` (`ID_Campo`), ADD KEY `FK_28tp3k88d63eyk791sctxd2m0` (`ID_Tarea`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
 ADD PRIMARY KEY (`ID_Usuario`), ADD UNIQUE KEY `UK_5kjyyubw71u4j97xxe1ejmt73` (`Usuario`), ADD UNIQUE KEY `UK_5f8b41l5628x03u9ba8t809eu` (`Correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `campos`
--
ALTER TABLE `campos`
MODIFY `ID_Campo` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT de la tabla `comentarios`
--
ALTER TABLE `comentarios`
MODIFY `ID_Comentario` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=49;
--
-- AUTO_INCREMENT de la tabla `expedientes`
--
ALTER TABLE `expedientes`
MODIFY `ID_Expediente` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=9;
--
-- AUTO_INCREMENT de la tabla `expedientes_psicologos`
--
ALTER TABLE `expedientes_psicologos`
MODIFY `ID_Expediente_Psicologo` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT de la tabla `tareas`
--
ALTER TABLE `tareas`
MODIFY `ID_Tarea` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT de la tabla `tareas_campos`
--
ALTER TABLE `tareas_campos`
MODIFY `ID_Tarea_Campo` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=40;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
MODIFY `ID_Usuario` bigint(20) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=15;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentarios`
--
ALTER TABLE `comentarios`
ADD CONSTRAINT `FK_dkv6un4jthmsyfa0qbmoib5f7` FOREIGN KEY (`ID_Tarea_Campo`) REFERENCES `tareas_campos` (`ID_Tarea_Campo`),
ADD CONSTRAINT `FK_on2romdtuq04wu6mw5jskf5ne` FOREIGN KEY (`ID_Usuario_Creador`) REFERENCES `usuarios` (`ID_Usuario`);

--
-- Filtros para la tabla `expedientes`
--
ALTER TABLE `expedientes`
ADD CONSTRAINT `FK_af4na5948mb3emvxege0peod7` FOREIGN KEY (`ID_Usuario`) REFERENCES `usuarios` (`ID_Usuario`),
ADD CONSTRAINT `FK_q98ktbmndx5u9fq41hes50ifr` FOREIGN KEY (`ID_Usuario_Creador`) REFERENCES `usuarios` (`ID_Usuario`);

--
-- Filtros para la tabla `expedientes_psicologos`
--
ALTER TABLE `expedientes_psicologos`
ADD CONSTRAINT `FK_foukmkeq9xn1xa04yhg2oeef6` FOREIGN KEY (`ID_Expediente`) REFERENCES `expedientes` (`ID_Expediente`),
ADD CONSTRAINT `FK_n0jrsvlfdgy5lqewx5ib2ixic` FOREIGN KEY (`ID_Usuario`) REFERENCES `usuarios` (`ID_Usuario`);

--
-- Filtros para la tabla `tareas`
--
ALTER TABLE `tareas`
ADD CONSTRAINT `FK_3pwdxajpukfyncrevvvr1yp7k` FOREIGN KEY (`ID_Expediente`) REFERENCES `expedientes` (`ID_Expediente`),
ADD CONSTRAINT `FK_ck5u9p6vxil85214l5uxvi4wg` FOREIGN KEY (`ID_Usuario_Creador`) REFERENCES `usuarios` (`ID_Usuario`);

--
-- Filtros para la tabla `tareas_campos`
--
ALTER TABLE `tareas_campos`
ADD CONSTRAINT `FK_28tp3k88d63eyk791sctxd2m0` FOREIGN KEY (`ID_Tarea`) REFERENCES `tareas` (`ID_Tarea`),
ADD CONSTRAINT `FK_99i6bbmxxlws3wxc16tyrome6` FOREIGN KEY (`ID_Campo`) REFERENCES `campos` (`ID_Campo`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
