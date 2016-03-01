/**
 *
 */
package br.com.richardcsantana.generator;

import br.com.richardcsantana.model.Neighbour;

/**
 * @author richardsantana
 *
 */
public interface NeighbourGenerator {

	Neighbour generate(final int size);

}
