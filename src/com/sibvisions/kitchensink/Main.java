/*
 * Copyright 2015 SIB Visions GmbH
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.sibvisions.kitchensink;

import jvx.rad.genui.UIFactoryManager;
import jvx.rad.genui.celleditor.UICheckBoxCellEditor;
import jvx.rad.ui.IFactory;
import javax.swing.UIManager;

/**
 * The main class.
 */
public final class Main
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Initialization
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * The main method.
	 *
	 * @param args the arguments, expected is either none or one, the factory.
	 */
	public static void main(String[] args)
	{
		// Note that the following is a quick and easy way to launch a JVx
		// application without too much context. The preferred way is
		// to use the appropriate Launcher for starting applications and setting
		// up everything necessary.
		// But this way is fine for quick tests or very simple applications.
		
		try
		{
			Class<?> factoryClass;
			
			try
			{
				// Use provided factory and fall-back to Swing
				factoryClass = Main.class.getClassLoader().loadClass(args[0]);
			}
			catch (Exception e)
			{
				factoryClass = Main.class.getClassLoader().loadClass("com.sibvisions.rad.ui.swing.impl.SwingFactory");
				
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			}
			
			// This will set and initialize the factory that is used by JVx
			// for creating all the GUI controls (and some other stuff, like
			// threading).
			UIFactoryManager.getFactoryInstance(factoryClass);
			
			// The invokeAndWait(Runnable) call is necessary so that we are
			// on the UI thread of the specified technology.
			UIFactoryManager.getFactory().invokeAndWait(() ->
			{
				// Register the default cell editors.
				registerDefaultCellEditors();
				
				// Create the main frame.
				KitchenSinkFrame frame = new KitchenSinkFrame();
				frame.pack();
				frame.setVisible(true);
				
				// Since we do not know anything technology specific,
				// we only can exit when the main window has closed.
				// Not invoking exit might result in that the application
				// will not exit.
				frame.eventWindowClosed().addListener(pEvent -> System.exit(0));
			});
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
			System.exit(1);
		}
	}
	
	/**
	 * Registers the default cell editors.
	 */
	private static void registerDefaultCellEditors()
	{
		IFactory factory =UIFactoryManager.getFactory();
		
		UICheckBoxCellEditor checkBoxCellEditor = new UICheckBoxCellEditor(Boolean.TRUE, Boolean.FALSE);
		checkBoxCellEditor.setText("");
		factory.setDefaultCellEditor(Boolean.class, checkBoxCellEditor);
	}
	
}	// Main
