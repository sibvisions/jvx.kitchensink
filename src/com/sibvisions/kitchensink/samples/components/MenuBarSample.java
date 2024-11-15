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
package com.sibvisions.kitchensink.samples.components;

import jvx.rad.genui.UIColor;
import jvx.rad.genui.component.UILabel;
import jvx.rad.genui.container.UIDesktopPanel;
import jvx.rad.genui.container.UIInternalFrame;
import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.layout.UIBorderLayout;
import jvx.rad.genui.menu.UICheckBoxMenuItem;
import jvx.rad.genui.menu.UIMenu;
import jvx.rad.genui.menu.UIMenuBar;
import jvx.rad.genui.menu.UIMenuItem;
import jvx.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * Demonstrates the {@link UIMenuBar}.
 * 
 * @author Robert Zenz
 */
public class MenuBarSample extends AbstractSample implements ISample
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Interface implementation
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCategory()
	{
		return "Components";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPanel getContent() throws Throwable
	{
		UIMenu namesMenu = new UIMenu("Names");
		namesMenu.add(new UIMenuItem("Rick"));
		namesMenu.add(new UIMenuItem("John"));
		namesMenu.add(new UIMenuItem("Gonzo"));
		namesMenu.addSeparator();
		namesMenu.add(new UIMenuItem("Martin"));
		namesMenu.add(new UIMenuItem("Mike"));
		
		UIMenu alabamaMenu = new UIMenu("Alabama");
		alabamaMenu.add(new UIMenuItem("Birmingham"));
		alabamaMenu.add(new UIMenuItem("Oxford"));
		alabamaMenu.add(new UIMenuItem("Aliceville"));
		
		UIMenu kansasMenu = new UIMenu("Kansas");
		kansasMenu.add(new UIMenuItem("Hutchinson"));
		kansasMenu.add(new UIMenuItem("Wichita"));
		kansasMenu.add(new UIMenuItem("Topeka"));
		kansasMenu.add(new UIMenuItem("Hays"));
		
		UIMenu checkBoxMenu = new UIMenu("CheckBox");
		checkBoxMenu.add(new UICheckBoxMenuItem("Check it!"));
		checkBoxMenu.add(new UICheckBoxMenuItem("With image!"));
		((UICheckBoxMenuItem) checkBoxMenu.getComponent(1)).setPressedImage(getImage("emblem-favorite.png"));
		
		UIMenu southDakotaMenu = new UIMenu("South Dakota (empty)");
		
		UIMenu placesMenu = new UIMenu("Places");
		placesMenu.add(alabamaMenu);
		placesMenu.add(kansasMenu);
		placesMenu.add(southDakotaMenu);
		
		UIMenuBar menuBar = new UIMenuBar();
		menuBar.add(namesMenu);
		menuBar.add(placesMenu);
		menuBar.add(checkBoxMenu);
		
		UILabel placeholder = new UILabel("Placeholder");
		placeholder.setBackground(new UIColor(64, 128, 255));
		
		UIDesktopPanel desktopPanel = new UIDesktopPanel();
		
		UIInternalFrame internalFrame = new UIInternalFrame(desktopPanel);
		internalFrame.setLayout(new UIBorderLayout());
		internalFrame.setPreferredSize(400, 300);
		
		internalFrame.setMenuBar(menuBar);
		internalFrame.add(placeholder, UIBorderLayout.CENTER);
		
		internalFrame.pack();
		internalFrame.setVisible(true);
		
		UIPanel content = new UIPanel();
		content.setLayout(new UIBorderLayout());
		content.add(desktopPanel, UIBorderLayout.CENTER);
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "MenuBar";
	}
	
} // MenuBarSample
