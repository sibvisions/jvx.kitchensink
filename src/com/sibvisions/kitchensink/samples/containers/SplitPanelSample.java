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
import javax.rad.genui.container.UISplitPanel;
import javax.rad.genui.layout.UIBorderLayout;
import javax.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * Shows the {@link UISplitPanel}.
 * 
 * @author Robert Zenz
 */
public class SplitPanelSample extends AbstractSample implements ISample
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
		UISplitPanel splitPanelFirstSecond = new UISplitPanel(UISplitPanel.SPLIT_LEFT_RIGHT);
		splitPanelFirstSecond.setFirstComponent(createCenteredLabel("First", Tango.BUTTER_1));
		splitPanelFirstSecond.setSecondComponent(createCenteredLabel("Second", Tango.CHAMELEON_1));
		
		UISplitPanel splitPanelThird = new UISplitPanel(UISplitPanel.SPLIT_TOP_BOTTOM);
		splitPanelThird.setFirstComponent(splitPanelFirstSecond);
		splitPanelThird.setSecondComponent(createCenteredLabel("Third", Tango.SKY_BLUE_1));
		
		UIPanel content = new UIPanel();
		content.setLayout(new UIBorderLayout());
		content.add(splitPanelThird, UIBorderLayout.CENTER);
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Split";
	}
	
}	// SplitPanelSample
