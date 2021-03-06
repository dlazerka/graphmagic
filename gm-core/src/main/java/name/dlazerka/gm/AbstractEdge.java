/*
 * GraphMagic, package for scientists working in graph theory.
 * Copyright (C) 2009 Dzmitry Lazerka dlazerka@dlazerka.name
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License version 2 as
 * published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Author: Dzmitry Lazerka dlazerka@dlazerka.name
 */

package name.dlazerka.gm;

/**
 * @author Dzmitry Lazerka www.dlazerka.name
 */
public abstract class AbstractEdge implements Edge {
	/**
	 * Implementation is almost ordinary, based on fields, but with two additions:<ol>
	 * <li>
	 * if {@link #getGraph()} is multi ({@link name.dlazerka.gm.Graph#isMulti()}),
	 * then returns false for different objects.
	 * <li>
	 * if {@link #getGraph()} is not directed ({@link name.dlazerka.gm.Graph#isDirected()}),
	 * then returns true for reverted (source, target) pair.
	 * </ol>
	 *
	 * @param o any object
	 * @return true if given object is equal. Equality takes in concern {@link name.dlazerka.gm.Graph#isMulti()}
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof AbstractEdge)) return false;

		AbstractEdge that = (AbstractEdge) o;

		if (getGraph() != null && getGraph().isMulti()) {
			return false;
		}

		boolean result = true;
		if (getTarget() != null ? !getTarget().equals(that.getTarget()) : that.getTarget() != null) result = false;
		if (getSource() != null ? !getSource().equals(that.getSource()) : that.getSource() != null) result = false;

		if (!result && !isDirected()) {
			result = true;
			if (getTarget() != null ? !getTarget().equals(that.getSource()) : that.getSource() != null) result = false;
			if (getSource() != null ? !getSource().equals(that.getTarget()) : that.getTarget() != null) result = false;
		}

		if (result && isDirected() != that.isDirected()) result = false;

		return result;
	}

	/**
	 * Commutative, in case of undirected.
	 * @return hash code
	 */
	@Override
	public int hashCode() {
		int result = getTarget() != null ? getTarget().hashCode() : 0;
		result += getSource() != null ? getSource().hashCode() : 0;
		return result;
	}
}
