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
package com.sibvisions.kitchensink.samples.containers;

import javax.rad.genui.container.UIPanel;
import javax.rad.genui.container.UIScrollPanel;
import javax.rad.genui.layout.UIBorderLayout;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * Shows the {@link UIScrollPanel}.
 * 
 * @author Robert Zenz
 */
public class ScrollPanelSample extends AbstractSample implements ISample
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
		return "Containers";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPanel getContent()
	{
		UIPanel content = new UIPanel();
		content.setLayout(new UIBorderLayout());
		
		UIScrollPanel scrollPanel = new UIScrollPanel();
		scrollPanel.setLayout(new UIFormLayout());
		
		scrollPanel.add(createCenteredLabel("This thing goes on", Tango.BUTTER_1));
		
		for (int count = 0; count < 774; count++)
		{
			scrollPanel.add(createCenteredLabel("and on and on and on", Tango.CHAMELEON_1));
		}
		
		scrollPanel.add(createCenteredLabel("all the way down here...", Tango.SKY_BLUE_1));
		
		content.add(scrollPanel, UIBorderLayout.CENTER);
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Scroll";
	}
	
}	// ScrollPanelSample
