/**
 *
 */
package br.com.richardcsantana.algorithm;

import br.com.richardcsantana.model.Neighbour;

/**
 * @author richardsantana
 *
 */
public interface Algorithm {

    Neighbour run(Neighbour inicialState, int numberOfQueens);

}
