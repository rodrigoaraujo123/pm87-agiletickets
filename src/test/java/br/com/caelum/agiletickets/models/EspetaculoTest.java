package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.ArrayList;

import org.hibernate.validator.internal.constraintvalidators.AssertTrueValidator;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Test;

public class EspetaculoTest {

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	@Test
	public void cria1SessaoDiariaSeComecaETerminaHoje(){
		Espetaculo espetaculo = new Espetaculo();
		
		LocalDate dataInicio = new LocalDate(2015,05,11);
		LocalDate dataFim = new LocalDate(2015,05,11);
		LocalTime horario = new LocalTime(20, 00, 00, 00);
		
		List<Sessao> sessoesLista = new ArrayList<Sessao>();
		
		sessoesLista =	espetaculo.criaSessoes(dataInicio, dataFim, horario, Periodicidade.DIARIA);
		assertTrue(sessoesLista.size()==1);
		assertTrue(sessoesLista.get(0).getInicio().equals(dataInicio.toDateTime(horario)));
	}
	
	@Test
	public void cria3SessaoDiariaSeComecaHojeETerminaDepoisDeAmanha(){
		Espetaculo espetaculo = new Espetaculo();
		
		LocalDate dataInicio = new LocalDate(2015,05,11);
		LocalDate dataFim = new LocalDate(2015,05,13);
		LocalTime horario = new LocalTime(20, 00, 00, 00);
		
		List<Sessao> sessoesLista = new ArrayList<Sessao>();
		
		sessoesLista =	espetaculo.criaSessoes(dataInicio, dataFim, horario, Periodicidade.DIARIA);
		assertTrue(sessoesLista.size()==3);
		assertTrue(sessoesLista.get(0).getInicio().equals(dataInicio.toDateTime(horario)));
		assertTrue(sessoesLista.get(1).getInicio().equals(dataInicio.plusDays(1).toDateTime(horario)));
		assertTrue(sessoesLista.get(2).getInicio().equals(dataInicio.plusDays(2).toDateTime(horario)));
	}
	
	/*
	@Test
	public void cria1SessaoSemanalComecaHojeETerminaAmanha(){
		Espetaculo espetaculo = new Espetaculo();
		
		LocalDate dataInicio = new LocalDate(2015,05,11);
		LocalDate dataFim = new LocalDate(2015,05,12);
		LocalTime horario = new LocalTime(20, 00, 00, 00);
		
		List<Sessao> sessoesLista = new ArrayList<Sessao>();
		
		sessoesLista =	espetaculo.criaSessoes(dataInicio, dataFim, horario, Periodicidade.SEMANAL);
		assertEquals(1,sessoesLista.size());
		assertEquals(dataInicio.toDateTime(horario), sessoesLista.get(0).getInicio());
	}
	*/
	
}
