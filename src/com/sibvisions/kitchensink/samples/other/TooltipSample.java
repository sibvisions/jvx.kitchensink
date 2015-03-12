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

import javax.rad.genui.component.UIButton;
import javax.rad.genui.component.UIIcon;
import javax.rad.genui.component.UILabel;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * Shows that tooltips can be attached to any control.
 * 
 * @author Robert Zenz
 */
public class TooltipSample extends AbstractSample implements ISample
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
		UILabel label = new UILabel("Some label");
		label.setToolTipText("What did you expect?");
		
		UIButton button = new UIButton("A button");
		button.setToolTipText("You can click it.");
		
		UIIcon icon = new UIIcon(getImage("weather-overcast.png"));
		icon.setToolTipText("Todays forecast is that it will rain (somewhere in Europe).");
		
		UIFormLayout contentLayout = new UIFormLayout();
		contentLayout.setNewlineCount(4);
		
		UIPanel content = new UIPanel();
		content.setLayout(contentLayout);
		
		content.add(label);
		content.add(button);
		content.add(icon);
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Tooltip";
	}
	
}	// TooltipSample
