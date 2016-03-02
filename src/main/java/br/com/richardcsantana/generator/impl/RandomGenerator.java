/**
 *
 */
package br.com.richardcsantana.generator.impl;

import java.util.Random;

import org.springframework.stereotype.Component;

import br.com.richardcsantana.generator.NeighbourGenerator;
import br.com.richardcsantana.model.Neighbour;

/**
 * @author richardsantana
 *
 */
@Component
public class RandomGenerator implements NeighbourGenerator {

	public Neighbour generate(final int size) {
		final Neighbour neighbour = new Neighbour(size);
		final Random r = new Random();
		for (int col = 0; col < size; col++) {
			final int row = r.nextInt(size);
			neighbour.put(row, col);
		}
		return neighbour;
	}

}
