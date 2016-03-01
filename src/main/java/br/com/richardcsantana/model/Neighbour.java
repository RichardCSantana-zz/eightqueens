/**
 *
 */
package br.com.richardcsantana.model;

/**
 * @author richardsantana
 *
 */
public class Neighbour {

	private final boolean[][] map;
	private int currentSize;
	private final int maxSize;

	public int getSize() {
		return this.maxSize;
	}

	public Neighbour(final int size) {
		this.map = new boolean[size][size];
		this.maxSize = size;
		this.currentSize = 0;
	}

	public void put(final int row, final int col) {
		if (currentSize >= maxSize) {
			throw new IllegalStateException("More queens than permitted");
		}
		currentSize++;
		this.map[row][col] = true;
	}

	public boolean isOcupated(final int row, final int col) {
		return this.map[row][col];
	}

	@Override
	public String toString() {
		final StringBuilder result = new StringBuilder();
		for (int row = 0; row < maxSize; row++) {
			for (int col = 0; col < maxSize; col++) {
				result.append("[");
				result.append((this.map[col][row]) ? "W" : " ");
				result.append("]");
			}
			result.append("\n");
		}
		return result.toString();
	}

}
