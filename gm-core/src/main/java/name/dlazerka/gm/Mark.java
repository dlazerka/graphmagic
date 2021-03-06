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

import java.util.HashMap;
import java.util.Map;

/**
 * @author Dzmitry Lazerka www.dlazerka.name
 */
public class Mark extends HashMap<Object, Object> {
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Object, Object> entry : this.entrySet()) {
			sb.append(',');
			Object key = entry.getKey();
			Object value = entry.getValue();
			if (key != null) {
				sb.append(key).append('=');
			}
			sb.append(value);
		}
		sb.deleteCharAt(0);
		return sb.toString();
	}
}
