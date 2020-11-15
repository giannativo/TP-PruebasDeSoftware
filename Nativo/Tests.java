package Nativo;

import avion.*;
import copControl.*;
import pista.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class Tests {
	
	
	//Test Unitarios

	@Test
	public void TestCrearPosicion() {
		int i = 0;
		int j = 1;
		Posicion posicion = new Posicion(i, j);
		Assert.assertEquals(posicion.getCoordenadaX(), i, 0);
		Assert.assertEquals(posicion.getCoordenadaY(), j, 0);
	}

	@Test
	public void TestPosicionesIguales() {
		int i = 0;
		int j = 1;
		Posicion posicion = new Posicion(i, j);
		Posicion posicion2 = new Posicion(i, j);
		Assert.assertEquals(posicion.igualA(posicion2), true);
	}
	
	@Test
	public void TestPosicionVecinaMinima() {
		Posicion posicion = new Posicion(0, 1);
		Posicion destino = new Posicion(9, 10);
		Posicion posicionEsperada = new Posicion(1, 2);
		Assert.assertEquals(posicion.getVecinoDeDistanciaMinima(destino).getCoordenadaX(), posicionEsperada.getCoordenadaX(), 0);
		Assert.assertEquals(posicion.getVecinoDeDistanciaMinima(destino).getCoordenadaY(), posicionEsperada.getCoordenadaY(), 0);
	}

	@Test
	public void TestDistanciaEntrePosiciones() {
		Posicion posicionA = new Posicion(0, 1);
		Posicion posicionB = new Posicion(0, 6);
		Assert.assertEquals(posicionA.distanciaHasta(posicionB), 5, 0);
	}
	
	@Test
	public void TestStringPosicion() {
		int i = 0;
		int j = 1;
		Posicion posicion = new Posicion(i, j);
		Assert.assertEquals(posicion.toString(), "Las coordenadas son: 0.0,1.0");
	}	
	
	
	// Tests de Integracion
	
	@Test
	public void TestCrearMapaConPistas() {
		Posicion posicionEntrada = new Posicion(10, 10);
		try {
			PistaSimple pista = new PistaSimple(posicionEntrada);
			List<Pista> pistas = new ArrayList<Pista>();
			pistas.add(pista);
			Mapa mapa = new Mapa(pistas);
			Assert.assertEquals(mapa.getPistas(), pistas);
		}
		catch (PosicionesEntradaVaciaException e) {
			System.out.println("La pista no puede crearse sin su posicion de entrada.");
		}
	}	
	
	@Test
	public void TestCrearHelicoptero() {
		Mapa mapa = new Mapa();
		Posicion posIni = new Posicion(0, 0);
		Posicion posFin = new Posicion(10, 10);
		Helicoptero helicoptero = new Helicoptero(posIni, posFin, mapa);
		Assert.assertEquals(helicoptero.getPosicionActual(), posIni);
		Assert.assertEquals(helicoptero.getDestinoActual(), posFin);		
	}
	
	@Test
	public void TestMoverHelicoptero() {
		Mapa mapa = new Mapa();
		Posicion posIni = new Posicion(0, 0);
		Posicion posFin = new Posicion(10, 10);
		Helicoptero helicoptero = new Helicoptero(posIni, posFin, mapa);
		Posicion movimiento = new Posicion(1, 1);
		helicoptero.arrancar();
		helicoptero.avanzar();
		Assert.assertEquals(helicoptero.getPosicionActual().getCoordenadaX(), movimiento.getCoordenadaX(), 0);
		Assert.assertEquals(helicoptero.getPosicionActual().getCoordenadaY(), movimiento.getCoordenadaY(), 0);
	}	

	@Test
	public void TestDetenerHelicoptero() {
		Mapa mapa = new Mapa();
		Posicion posIni = new Posicion(0, 0);
		Posicion posFin = new Posicion(10, 10);
		Helicoptero helicoptero = new Helicoptero(posIni, posFin, mapa);
		helicoptero.arrancar();
		helicoptero.detener();
		Assert.assertEquals(helicoptero.estaDetenido(), true);
	}
	
	@Test
	public void TestPuedeAterrizarHelicoptero() {
		Mapa mapa = new Mapa();
		Posicion posIni = new Posicion(0, 0);
		Posicion posFin = new Posicion(10, 10);
		Helicoptero helicoptero = new Helicoptero(posIni, posFin, mapa);
		try {
			PistaSimple pista = new PistaSimple(posFin);
			Assert.assertEquals(helicoptero.puedeAterrizar(pista), false);
		}
		catch (PosicionesEntradaVaciaException e) {
			System.out.println("La pista no puede crearse sin su posicion de entrada.");
		}
	}	
}
