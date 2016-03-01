/**
 *
 */
package br.com.richardcsantana.algorithm.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
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

	private final NeighbourGenerator generator;
	private final Avaliation avaliation;
	private final double initialTemperature = 0;
	private final int maxSuccessPerIteration = 100;
	private final int maxGenerationsPerIteration = 10000;
	private final double temperatureReductionFactor = 0.7;
	private final int maxIterations = 1000;

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
	public Neighbour run(final Neighbour inicialState) {
		Neighbour currentState = inicialState;
		double currentTemperature = initialTemperature;
		int i = 1;
		int numberSuccess = 0;
		final Random random = new Random();
		do {
			int j = 1;
			numberSuccess = 0;
			do {
				final Neighbour newState = generator.generate(8);
				final int avaliationNewState = avaliation.avaliate(newState);
				final int avaliationCurrentState = avaliation.avaliate(currentState);
				final int delta = avaliationNewState - avaliationCurrentState;
				System.out.println(String.format("Iteration %d, generation %d", i, j));
				System.out.println(String.format("avaliation: currentState %d, newState %d", avaliationCurrentState,
						avaliationNewState));
				System.out.println(String.format("delta %d", delta));
				System.out.println(String.format("success %d", numberSuccess));

				final double randomDouble = random.nextDouble();
				final double calculation = Math.pow(Math.E, (-delta / currentTemperature));
				final boolean changeValues = changeValues(delta, randomDouble, calculation);
				if (changeValues) {
					System.out.println(
							String.format("values: exponetial^(delta/currentTemperature) %f, random: %f, change %b",
									calculation, randomDouble, changeValues));
					currentState = newState;
					numberSuccess++;
				}
				j++;
			} while ((numberSuccess < maxSuccessPerIteration) && (j <= maxGenerationsPerIteration));
			currentTemperature *= temperatureReductionFactor;
			System.out.println(String.format("currentTemperature %f", currentTemperature));
			i++;
		} while ((numberSuccess > 0) && (i <= maxIterations));
		return currentState;
	}

	private boolean changeValues(final int delta, final double randomDouble, final double calculation) {
		return (delta <= 0) || (calculation > randomDouble);
	}

}
