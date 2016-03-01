package br.com.richardcsantana;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.richardcsantana.avaliation.impl.ThreatIndexAvaliationTest;
import br.com.richardcsantana.generator.impl.RandomGeneratorTest;

@RunWith(Suite.class)
@SuiteClasses({ ThreatIndexAvaliationTest.class, RandomGeneratorTest.class })
public class AllTests {

}
