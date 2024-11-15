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
package com.sibvisions.kitchensink.samples.other;

import jvx.rad.genui.component.UIButton;
import jvx.rad.genui.container.UIGroupPanel;
import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.layout.UIFormLayout;
import jvx.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * A {@link ISample} that shows the capability to set a custom tab-order.
 * 
 * @author Robert Zenz
 */
public class TabIndexSample extends AbstractSample implements ISample
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
		return "Other";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPanel getContent() throws Throwable
	{
		// Add a group that shows the default tab order.
		UIFormLayout defaultGroupLayout = new UIFormLayout();
		defaultGroupLayout.setNewlineCount(7);
		
		UIGroupPanel defaultGroup = new UIGroupPanel("Default");
		defaultGroup.setLayout(defaultGroupLayout);
		
		for (int counter = 0; counter < 7; counter++)
		{
			UIButton button = new UIButton("Button: " + Integer.toString(counter));
			defaultGroup.add(button);
		}
		
		// Add a group that shows the custom tab order.
		UIFormLayout customGroupLayout = new UIFormLayout();
		customGroupLayout.setNewlineCount(7);
		
		UIGroupPanel customGroup = new UIGroupPanel("Custom");
		customGroup.setLayout(customGroupLayout);
		
		for (int counter : new int[] { 5, 2, 6, 4, 1, 0, 3 })
		{
			UIButton button = new UIButton("Button: " + Integer.toString(counter));
			
			// Set the tab index of this control.
			button.setTabIndex(Integer.valueOf(counter));
			
			customGroup.add(button);
		}
		
		UIFormLayout contentLayout = new UIFormLayout();
		
		UIPanel content = new UIPanel();
		content.setLayout(contentLayout);
		
		content.add(defaultGroup, contentLayout.getConstraints(0, 0, -1, 0));
		content.add(customGroup, contentLayout.getConstraints(0, 1, -1, 1));
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Tab Index";
	}
	
}	// TabIndexSample
