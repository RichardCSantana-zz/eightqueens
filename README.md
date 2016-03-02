# eightqueens

A software to solve the **n-queens problem**(see https://en.wikipedia.org/wiki/Eight_queens_puzzle ).
Implemented using **java8** and **spring-boot**.

## Build

To build this run "mvn clean package" and get the EightQueens-0.0.1-SNAPSHOT.jar at the target folder.

## Configuration

To edit configurations of the software, change that on src/main/resource/application.yml.
List of configurations:

Configuration | Description
------------- | -----------
initialTemperature | temperature to start the algorithm
maxSuccessPerIteration | maximum number of success obtained at each iteration
maxGenerationsPerIteration | maximum number of generation of neighbours obtained at each iteration
temperatureReductionFactor | reduction factor of temperature at each iteration, must be less than 1
maxIterations | maximum number of iterations
numberOfQueens | number of queens to te algorithm solve
logging.level.root | level of details about the algorithm running
