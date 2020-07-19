package com.co.cuadrangular.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.co.cuadrangular.datatransfer.EquipoDTO;
import com.co.cuadrangular.datatransfer.JuegoDTO;
import com.co.cuadrangular.datatransfer.LigaDTO;
import com.co.cuadrangular.datatransfer.PartidoDTO;

@Component
public class LigaAdapter {
	
	public LigaDTO crearEncuantro(List<EquipoDTO> listaEquipo){
		
		return ligaOrdenada(calcularLiga(listaEquipo.size()), listaEquipo);
		
	}
	
	private JuegoDTO[][] calcularLiga(int numEquipos) {
		if (numEquipos % 2 == 0)
			return calcularLigaNumEquiposPar(numEquipos);
		else
			return calcularLigaNumEquiposImpar(numEquipos);
	}
	
	private JuegoDTO[][] calcularLigaNumEquiposPar(int numEquipos) {
		int numRondas = numEquipos - 1;
		int numPartidosPorRonda = numEquipos / 2;

		JuegoDTO[][] rondas = new JuegoDTO[numRondas][numPartidosPorRonda];

		crearLocalVisitantePar(numEquipos, numRondas, numPartidosPorRonda, rondas);

		int equipoMasAlto = numEquipos - 1;
		int equipoImparMasAlto = equipoMasAlto - 1;

		for (int i = 0, k = equipoImparMasAlto; i < numRondas; i++) {
			for (int j = 1; j < numPartidosPorRonda; j++) {
				rondas[i][j].setVisitante(k);
				k--;
				if (k == -1)
					k = equipoImparMasAlto;
			}
		}

		return rondas;
	}
	
	private JuegoDTO[][] calcularLigaNumEquiposImpar(int numEquipos) {
		int numRondas = numEquipos;
		int numPartidosPorRonda = numEquipos / 2;

		JuegoDTO[][] rondas = new JuegoDTO[numRondas][numPartidosPorRonda];

		for (int i = 0, k = 0; i < numRondas; i++) {
			for (int j = -1; j < numPartidosPorRonda; j++) {
				if (j >= 0) {
					rondas[i][j] = new JuegoDTO(-1, -1);

					rondas[i][j].setLocal(k);
				}
				k++;
				if (k == numRondas)
					k = 0;
			}
		}

		int equipoMasAlto = numEquipos - 1;

		for (int i = 0, k = equipoMasAlto; i < numRondas; i++) {
			for (int j = 0; j < numPartidosPorRonda; j++) {
				rondas[i][j].setVisitante(k);
				k--;
				if (k == -1)
					k = equipoMasAlto;
			}
		}

		return rondas;
	}
	

	private void crearLocalVisitantePar(int numEquipos, int numRondas, int numPartidosPorRonda, JuegoDTO[][] rondas) {
		for (int i = 0, k = 0; i < numRondas; i++) {
			for (int j = 0; j < numPartidosPorRonda; j++) {
				rondas[i][j] = new JuegoDTO(-1, -1);
				rondas[i][j].setLocal(k);
				k++;
				if (k == numRondas)
					k = 0;
			}
		}

		for (int i = 0; i < numRondas; i++) {
			if (i % 2 == 0) {
				rondas[i][0].setVisitante(numEquipos - 1);
			} else {
				rondas[i][0].setVisitante(rondas[i][0].getLocal());
				rondas[i][0].setVisitante(numEquipos - 1);
			}
		}
	}
	
	private LigaDTO ligaOrdenada(JuegoDTO[][] rondas, List<EquipoDTO> listaEquipo) {
		
		LigaDTO listaLiga = new LigaDTO();
		List<PartidoDTO> listaPartido = new ArrayList<>();

		for (int i = 0; i < rondas.length; i++) {

			for (int j = 0; j < rondas[i].length; j++) {
				
				List<EquipoDTO> equipoLista = new ArrayList<>();
				EquipoDTO equipoDTO1 = listaEquipo.get(rondas[i][j].getLocal());
				EquipoDTO equipoDTO2 = listaEquipo.get(rondas[i][j].getVisitante());
				equipoLista.add(equipoDTO1);
				equipoLista.add(equipoDTO2);
				
//				List<PartidoDTO> listaPartido = new ArrayList<>();
				PartidoDTO partidoDTO = new PartidoDTO();
				partidoDTO.setPartidoList(equipoLista);
				listaPartido.add(partidoDTO);
				
//				LigaDTO ligaDto = new LigaDTO();
//				ligaDto.setLigaList(listaPartido);
//				listaLiga.add(ligaDto);
			}
		}
		listaLiga.setLigaList(listaPartido);
		return listaLiga;
	}

}
