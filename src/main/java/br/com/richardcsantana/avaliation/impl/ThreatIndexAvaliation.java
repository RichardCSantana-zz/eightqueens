/**
 *
 */
package br.com.richardcsantana.avaliation.impl;

import org.springframework.stereotype.Component;

import br.com.richardcsantana.avaliation.Avaliation;
import br.com.richardcsantana.model.Neighbour;

/**
 * @author richardsantana
 *
 */
@Component
public class ThreatIndexAvaliation implements Avaliation {

	private Neighbour neighbour;
	private int size;

	/*
	 * (non-Javadoc)
	 *
	 * @see br.com.richardcsantana.avaliation.Avaliation#avaliate(br.com.
	 * richardcsantana.model.Neighbour)
	 */
	public int avaliate(final Neighbour neighbour) {
		this.neighbour = neighbour;
		this.size = neighbour.getSize();
		return rowAvaliation() + diagonalAvaliation();
	}

	private int diagonalAvaliation() {
		int total = 0;
		total += (coverDiagonalToRight(0, 0) > 1) ? coverDiagonalToRight(0, 0) : 0;
		for (int current = 1; current < size; current++) {
			total += (coverDiagonalToRight(0, current) > 1) ? (coverDiagonalToRight(0, current)) : 0;
			total += (coverDiagonalToRight(current, 0) > 1) ? (coverDiagonalToRight(current, 0)) : 0;
		}
		total += (coverDiagonalToLeft(size - 1, 0) > 1) ? (coverDiagonalToLeft(size - 1, 0)) : 0;
		for (int current = size - 2; current >= 0; current--) {
			total += (coverDiagonalToLeft(current, 0) > 1) ? (coverDiagonalToLeft(current, 0)) : 0;
			total += (coverDiagonalToLeft(size - 1, size - 1 - current) > 1)
					? (coverDiagonalToLeft(size - 1, size - 1 - current)) : 0;
		}
		return total;
	}

	private int coverDiagonalToRight(int col, int row) {
		int total = 0;
		while ((col < size) && (row < size)) {
			if (neighbour.isOcupated(row, col)) {
				total++;
			}
			col++;
			row++;
		}
		return total;
	}

	private int coverDiagonalToLeft(int col, int row) {
		int total = 0;
		while ((col >= 0) && (row < size)) {
			if (neighbour.isOcupated(row, col)) {
				total++;
			}
			col--;
			row++;
		}
		return total;
	}

	private int rowAvaliation() {
		int total = 0;
		for (int col = 0; col < size; col++) {
			int totalColuna = 0;
			for (int row = 0; row < size; row++) {
				if (neighbour.isOcupated(col, row)) {
					totalColuna++;
				}
			}
			if (totalColuna > 1) {
				total += totalColuna;
			}
		}
		return total;
	}

}
