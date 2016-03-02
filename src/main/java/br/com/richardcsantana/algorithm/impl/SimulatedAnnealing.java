/**
 *
 */
package br.com.richardcsantana.algorithm.impl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.richardcsantana.algorithm.Algorithm;
import br.com.richardcsantana.avaliation.Avaliation;
import br.com.richardcsantana.generator.NeighbourGenerator;
import br.com.richardcsantana.model.Neighbour;

/**
 * @author richardsantana
 *
 */
@Component
public class SimulatedAnnealing implements Algorithm {

    private static final Logger log = LoggerFactory.getLogger(SimulatedAnnealing.class);

    private final NeighbourGenerator generator;
    private final Avaliation avaliation;

    @Value("${initialTemperature:0}")
    private double initialTemperature;
    @Value("${maxSuccessPerIteration:100}")
    private int maxSuccessPerIteration;
    @Value("${maxGenerationsPerIteration:1000}")
    private int maxGenerationsPerIteration;
    @Value("${temperatureReductionFactor:0.2}")
    private double temperatureReductionFactor;
    @Value("${maxIterations:10000}")
    private int maxIterations;

    @Autowired
    public SimulatedAnnealing(final NeighbourGenerator generator, final Avaliation avaliation) {
        this.generator = generator;
        this.avaliation = avaliation;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * br.com.richardcsantana.algorithm.Algorithm#run(br.com.richardcsantana.
     * model.Neighbour)
     */
    public Neighbour run(final Neighbour inicialState, final int numberOfQueens) {
        Neighbour currentState = inicialState;
        double currentTemperature = initialTemperature;
        int i = 1;
        int numberSuccess = 0;
        final Random random = new Random();
        do {
            int j = 1;
            numberSuccess = 0;
            do {
                final Neighbour newState = generator.generate(numberOfQueens);
                final int avaliationNewState = avaliation.avaliate(newState);
                final int avaliationCurrentState = avaliation.avaliate(currentState);
                final int delta = avaliationNewState - avaliationCurrentState;
                log.debug(String.format("Iteration %d, generation %d", i, j));
                log.debug(String.format("avaliation: currentState %d, newState %d", avaliationCurrentState,
                        avaliationNewState));
                log.debug(String.format("delta %d", delta));
                log.debug(String.format("success %d", numberSuccess));

                final double randomDouble = random.nextDouble();
                final double calculation = Math.pow(Math.E, (-delta / currentTemperature));
                final boolean changeValues = changeValues(delta, randomDouble, calculation);
                if (changeValues) {
                    log.debug(String.format("values: exponetial^(delta/currentTemperature) %f, random: %f, change %b",
                            calculation, randomDouble, changeValues));
                    currentState = newState;
                    numberSuccess++;
                }
                j++;
            } while ((numberSuccess < maxSuccessPerIteration) && (j <= maxGenerationsPerIteration));
            currentTemperature *= temperatureReductionFactor;
            log.debug(String.format("currentTemperature %f", currentTemperature));
            i++;
        } while ((numberSuccess > 0) && (i <= maxIterations));
        return currentState;
    }

    private boolean changeValues(final int delta, final double randomDouble, final double calculation) {
        return (delta <= 0) || (calculation > randomDouble);
    }

}
