/**
 *
 */
package br.com.richardcsantana.avaliation.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.richardcsantana.EightQueensSolver;
import br.com.richardcsantana.avaliation.Avaliation;
import br.com.richardcsantana.model.Neighbour;

/**
 * @author richardsantana
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(EightQueensSolver.class)
public class ThreatIndexAvaliationTest {

	@Autowired
	private Avaliation avaliation;

	/**
	 * Test method for
	 * {@link br.com.richardcsantana.avaliation.impl.ThreatIndexAvaliation#avaliate(br.com.richardcsantana.model.Neighbour)}
	 * .
	 */
	@Test
	public void validateLineAvaliate() {
		final Neighbour ng = new Neighbour(8);
		ng.put(0, 0);
		ng.put(0, 7);
		ng.put(1, 3);
		ng.put(1, 5);
		ng.put(2, 1);
		ng.put(3, 6);
		ng.put(6, 2);
		ng.put(7, 4);
		final int avaliationRate = avaliation.avaliate(ng);
		Assert.assertEquals("Avaliando número de ameaças", 4, avaliationRate);
	}

	/**
	 * Test method for
	 * {@link br.com.richardcsantana.avaliation.impl.ThreatIndexAvaliation#avaliate(br.com.richardcsantana.model.Neighbour)}
	 * .
	 */
	@Test
	public void validateDiagonalAvaliate() {
		final Neighbour ng = new Neighbour(8);
		ng.put(0, 0);
		ng.put(2, 1);
		ng.put(3, 5);
		ng.put(3, 7);
		ng.put(4, 2);
		ng.put(5, 6);
		ng.put(6, 3);
		ng.put(7, 4);
		final int avaliationRate = avaliation.avaliate(ng);
		Assert.assertEquals("Avaliando número de ameaças", 6, avaliationRate);
	}

}
