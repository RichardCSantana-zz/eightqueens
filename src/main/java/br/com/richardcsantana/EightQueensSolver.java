/**
 *
 */
package br.com.richardcsantana;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.richardcsantana.algorithm.Algorithm;
import br.com.richardcsantana.avaliation.Avaliation;
import br.com.richardcsantana.generator.NeighbourGenerator;
import br.com.richardcsantana.model.Neighbour;

/**
 * @author richardsantana
 *
 */
@SpringBootApplication
public class EightQueensSolver implements CommandLineRunner {

	@Autowired
	private Algorithm algorithm;

	@Autowired
	private NeighbourGenerator generator;

	@Autowired
	private Avaliation avaliation;

	public static void main(final String[] args) {
		SpringApplication.run(EightQueensSolver.class, args);
	}

	public void run(final String... args) throws Exception {
		final Neighbour result = algorithm.run(generator.generate(8));
		System.out.println(result);
		System.out.println(avaliation.avaliate(result));
	}

}
