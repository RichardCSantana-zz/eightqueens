/**
 *
 */
package br.com.richardcsantana;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    private static final Logger log = LoggerFactory.getLogger(EightQueensSolver.class);

    @Autowired
    private Algorithm algorithm;

    @Autowired
    private NeighbourGenerator generator;

    @Autowired
    private Avaliation avaliation;

    @Value("${numberOfQueens:8}")
    private int numberOfQueens;

    public static void main(final String[] args) {
        SpringApplication.run(EightQueensSolver.class, args);
    }

    public void run(final String... args) throws Exception {
        final Neighbour result = algorithm.run(generator.generate(numberOfQueens), numberOfQueens);// TODO
                                                                                                   // rethink
        log.info(String.format("Final Result:\n %s", result));
        log.info(String.format("Avaliation: %s", avaliation.avaliate(result)));
    }

}
