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

package name.dlazerka.gm.ui;

import name.dlazerka.gm.GraphsContainer;
import name.dlazerka.gm.pluginloader.PluginLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Dzmitry Lazerka www.dlazerka.name
 */
public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	private static final Config config = new Config();

	private static final String MESSAGES_FILENAME = "messages";

	private static ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES_FILENAME);
	
	private static Locale currentLocale = Locale.ENGLISH;
	private static GraphsContainer graphMagicAPI = new GraphsContainer();
	private static PluginLoader pluginLoader = new PluginLoader(graphMagicAPI);

	public static String getString(String key, String... params) {
		String text = resourceBundle.getString(key);

		if(params != null){
			MessageFormat mf = new MessageFormat(text);
			text = mf.format(params);
		}

		return text;
	}

	public static Config getConfig() {
		return config;
	}

	public static void main(String[] args) throws Exception {
		config.load();

		Locale.setDefault(getCurrentLocale());

		UI.show();

		pluginLoader.loadDefaultPlugins();
	}

	public static Locale getCurrentLocale() {
		return currentLocale;
	}

	public static GraphsContainer getGraphMagicAPI() {
		return graphMagicAPI;
	}

	public static PluginLoader getPluginLoader() {
		return pluginLoader;
	}
}