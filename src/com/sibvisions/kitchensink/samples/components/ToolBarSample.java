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
import jvx.rad.genui.component.UIButton;
import jvx.rad.genui.component.UILabel;
import jvx.rad.genui.container.UIDesktopPanel;
import jvx.rad.genui.container.UIInternalFrame;
import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.container.UIToolBar;
import jvx.rad.genui.container.UIToolBarPanel;
import jvx.rad.genui.layout.UIBorderLayout;
import jvx.rad.genui.menu.UISeparator;
import jvx.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * Demonstrates the {@link UIToolBar}.
 * 
 * @author Robert Zenz
 */
public class ToolBarSample extends AbstractSample implements ISample
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
		UIToolBar toolBar = new UIToolBar();
		toolBar.add(new UIButton("Simple button"));
		toolBar.add(new UISeparator());
		toolBar.add(new UIButton("Simple button"));
		
		UIToolBarPanel toolBarPanel = new UIToolBarPanel();
		toolBarPanel.addToolBar(toolBar);
		
		UILabel placeholder = new UILabel("Placeholder");
		placeholder.setBackground(new UIColor(64, 128, 255));
		
		UIDesktopPanel desktopPanel = new UIDesktopPanel();
		
		UIInternalFrame internalFrame = new UIInternalFrame(desktopPanel);
		internalFrame.setLayout(new UIBorderLayout());
		internalFrame.setPreferredSize(400, 300);
		
		internalFrame.addToolBar(toolBar);
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
		return "ToolBar";
	}
	
}	// ToolBarSample
