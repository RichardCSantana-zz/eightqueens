/**
 *
 */
package br.com.richardcsantana.generator.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.richardcsantana.EightQueensSolver;
import br.com.richardcsantana.generator.NeighbourGenerator;
import br.com.richardcsantana.model.Neighbour;

/**
 * @author richardsantana
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(EightQueensSolver.class)
public class RandomGeneratorTest {

	@Autowired
	private NeighbourGenerator neighbourGenerator;

	/**
	 * Test method for
	 * {@link br.com.richardcsantana.generator.impl.RandomGenerator#generate(int)}
	 * .
	 */
	@Test
	public void testGenerate() {
		final int size = 8;
		final Neighbour neighbour = neighbourGenerator.generate(size);
		int totalGeral = 0;
		for (int col = 0; col < size; col++) {
			int totalLocal = 0;
			for (int row = 0; row < size; row++) {
				if (neighbour.isOcupated(col, row)) {
					totalLocal++;
				}
			}
			totalGeral += totalLocal;
			Assert.assertEquals(String.format("Validando coluna %d", col), 1, totalLocal);
		}
		Assert.assertEquals("Validando total geral", size, totalGeral);
	}

}
