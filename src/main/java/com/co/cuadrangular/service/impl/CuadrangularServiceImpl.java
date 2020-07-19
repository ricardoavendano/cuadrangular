package com.co.cuadrangular.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.co.cuadrangular.adapter.EquipoAdapter;
import com.co.cuadrangular.adapter.LigaAdapter;
import com.co.cuadrangular.datatransfer.EquipoDTO;
import com.co.cuadrangular.datatransfer.LigaDTO;
import com.co.cuadrangular.datatransfer.PartidoDTO;
import com.co.cuadrangular.domain.Equipo;
import com.co.cuadrangular.enums.NombreEquipo;
import com.co.cuadrangular.enums.Resultado;
import com.co.cuadrangular.exception.ErrorGuardarException;
import com.co.cuadrangular.exception.ErrorMostrandoPosicion;
import com.co.cuadrangular.repository.EquipoRepository;
import com.co.cuadrangular.service.CuadrangularService;

import fj.data.Either;

@Service
public class CuadrangularServiceImpl implements CuadrangularService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CuadrangularServiceImpl.class);

	@Autowired
	private EquipoRepository equipoRepository;

	@Autowired
	private LigaAdapter ligaAdapter;

	@Autowired
	private EquipoAdapter equipoAdapter;

	@Transactional
	public Either<Exception, LigaDTO> crearTorneo(Integer cantidadEquipo) {
		try {
			
			equipoRepository.deleteAll();

			List<EquipoDTO> listEquipo = guardarEquipo(cantidadEquipo);

			return Either.right(ligaAdapter.crearEncuantro(listEquipo));

		} catch (ErrorGuardarException e) {
			LOGGER.error("CuadrangularServiceImpl:crearTorneo", e);
			return Either.left(e);
		} catch (Exception e) {

			return Either.left(e);
		}
	}

	private List<EquipoDTO> guardarEquipo(Integer cantidadEquipo) throws ErrorGuardarException {
		try {
			List<EquipoDTO> listEquipo = new ArrayList<>();
			Long cont = Long.valueOf(1);
			for (int x = 0; x < cantidadEquipo; x++) {
				Equipo equipo = new Equipo();
				equipo.setIdEquipo(cont);
				equipo.setNombre(NombreEquipo.NOMBRE.getNombre() + cont);
				equipo.setPuntuacion(Long.valueOf(0));
				equipoRepository.save(equipo);
				cont++;

				EquipoDTO equipoDTO = new EquipoDTO();
				equipoDTO.setNombre(equipo.getNombre());
				equipoDTO.setPuntuacion(equipo.getPuntuacion());
				listEquipo.add(equipoDTO);
			}

			return listEquipo;
		} catch (Exception e) {
			LOGGER.error("CuadrangularServiceImpl:crearTorneo", e);
			throw new ErrorGuardarException();
		}

	}

	@Transactional
	public Either<Exception, List<EquipoDTO>> crearPuntuacion(LigaDTO liga) {
		try {
			List<EquipoDTO> listaEquipoDTO = new ArrayList<>();
			List<Equipo> equipo = (List<Equipo>) equipoRepository.findAll();

				for (PartidoDTO partido : liga.getLigaList()) {

					if (partido.getPartidoList().get(0).getPuntuacion() > partido.getPartidoList().get(1)
							.getPuntuacion()) {

						equipo.stream().filter(p -> p.getNombre().equals(partido.getPartidoList().get(0).getNombre()))
								.forEach(p -> p.setPuntuacion(p.getPuntuacion() + Resultado.GANO.getEstado()));

						equipo.stream().filter(p -> p.getNombre().equals(partido.getPartidoList().get(1).getNombre()))
								.forEach(p -> p.setPuntuacion(p.getPuntuacion() + Resultado.PERDIO.getEstado()));

					} else if (partido.getPartidoList().get(0).getPuntuacion() < partido.getPartidoList().get(1)
							.getPuntuacion()) {

						equipo.stream().filter(p -> p.getNombre().equals(partido.getPartidoList().get(1).getNombre()))
								.forEach(p -> p.setPuntuacion(p.getPuntuacion() + Resultado.GANO.getEstado()));

						equipo.stream().filter(p -> p.getNombre().equals(partido.getPartidoList().get(0).getNombre()))
								.forEach(p -> p.setPuntuacion(p.getPuntuacion() + Resultado.PERDIO.getEstado()));

					} else {

						equipo.stream()
								.filter(p -> p.getNombre().equals(partido.getPartidoList().get(0).getNombre())
										|| p.getNombre().equals(partido.getPartidoList().get(1).getNombre()))
								.forEach(p -> p.setPuntuacion(p.getPuntuacion() + Resultado.EMPATO.getEstado()));
					}

				}
			
			getPuntuacion(listaEquipoDTO, equipo);

			return Either.right(listaEquipoDTO);
		} catch (ErrorMostrandoPosicion e) {

			LOGGER.error("CuadrangularServiceImpl:crearTorneo", e);
			return Either.left(e);
		} catch (Exception e) {

			LOGGER.error("CuadrangularServiceImpl:crearTorneo", e);
			return Either.left(e);
		}
	}

	private void getPuntuacion(List<EquipoDTO> listaEquipoDTO, List<Equipo> equipo) throws ErrorMostrandoPosicion {
		List<Equipo> equipoOrdenado = guardarResultado(equipo);

		for (Equipo equipoBD : equipoOrdenado) {
			EquipoDTO equipoDTO = equipoAdapter.equipoAdp(equipoBD);
			listaEquipoDTO.add(equipoDTO);
		}
	}

	private List<Equipo> guardarResultado(List<Equipo> equipo) throws ErrorMostrandoPosicion {
		try {
			equipoRepository.saveAll(equipo);
			return equipoRepository.equipoOrdenadoPuntuacion();
		} catch (Exception e) {
			LOGGER.error("CuadrangularServiceImpl:crearTorneo", e);
			throw new ErrorMostrandoPosicion();
		}
	}

}
