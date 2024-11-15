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
package com.sibvisions.kitchensink.samples.layouts;

import jvx.rad.genui.UIRectangle;
import jvx.rad.genui.container.UIPanel;
import jvx.rad.model.ModelException;
import jvx.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * Shows the so called "null layout", how the {@link UIPanel} behaves if no
 * layout is set.
 *
 * @author Robert Zenz
 */
public class NullLayoutSample extends AbstractSample implements ISample
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
		return "Layouts";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPanel getContent() throws ModelException
	{
		UIPanel content = new UIPanel();
		
		// Note that setting explicit null might be/is necessary, as the default
		// layout of the UIPanel is undefined.
		content.setLayout(null);
		
		content.add(createCenteredLabel("Label at 0:0", Tango.BUTTER_1));
		content.getComponent(content.getComponentCount() - 1).setBounds(new UIRectangle(0, 0, 100, 18));
		
		content.add(createCenteredLabel("Label at 100:0", Tango.CHAMELEON_1));
		content.getComponent(content.getComponentCount() - 1).setBounds(new UIRectangle(100, 0, 100, 24));
		
		content.add(createCenteredLabel("Label at 0:100", Tango.SKY_BLUE_1));
		content.getComponent(content.getComponentCount() - 1).setBounds(new UIRectangle(0, 100, 100, 32));
		
		content.add(createCenteredLabel("Label at 100:100", Tango.PLUM_1));
		content.getComponent(content.getComponentCount() - 1).setBounds(new UIRectangle(100, 100, 100, 24));
		
		content.add(createCenteredLabel("This is a manually set layout.", Tango.SCARLET_RED_1));
		content.getComponent(content.getComponentCount() - 1).setBounds(new UIRectangle(150, 150, 250, 32));
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Null/None";
	}
	
}	// NullLayoutSample
