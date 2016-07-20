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

import javax.rad.genui.UIImage;
import javax.rad.genui.component.UILabel;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * Shows the {@link UIPanel}.
 * 
 * @author Robert Zenz
 */
public class PanelSample extends AbstractSample implements ISample
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
		UIFormLayout contentLayout = new UIFormLayout();
		
		UIPanel content = new UIPanel();
		content.setBackgroundImage(UIImage.getImage("/com/sibvisions/kitchensink/images/20101020_Sheep_shepherd_at_Vistonida_lake_Glikoneri_Rhodope_Prefecture_Thrace_Greece.jpg"));
		content.setLayout(contentLayout);
		
		content.add(new UILabel("This whole area is a Panel with a background image."), contentLayout.getConstraints(0, 0));
		content.add(createCenteredLabel("The image you see in the background was taken by \"Ggia\"", Tango.BUTTER_1), contentLayout.getConstraints(0, -2));
		content.add(createCenteredLabel("and is available under CC-BY-SA 3.0 from WikiMedia.", Tango.BUTTER_1), contentLayout.getConstraints(0, -1));
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Panel";
	}
	
}	// PanelSample
