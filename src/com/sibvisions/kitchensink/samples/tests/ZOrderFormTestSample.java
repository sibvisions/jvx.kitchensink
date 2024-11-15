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
package com.sibvisions.kitchensink.samples.tests;

import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.layout.UIFormLayout;
import jvx.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * A simple Z-Order for the {@link UIFormLayout}.
 * 
 * @author Robert Zenz
 */
public class ZOrderFormTestSample extends AbstractSample implements ISample
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
		return "Tests";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPanel getContent()
	{
		UIFormLayout contentLayout = new UIFormLayout();
		
		UIPanel content = new UIPanel();
		content.setLayout(contentLayout);
		
		content.add(createCenteredLabel("Z-Order is correct.", Tango.CHAMELEON_1), contentLayout.getConstraints(0, 0, -1, -1));
		content.add(createCenteredLabel("If you see this label, Z-Ordering is broken and not correct.", Tango.SCARLET_RED_1), contentLayout.getConstraints(0, 0, -1, -1));
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Z-Order Form";
	}
	
}	// ZOrderFormTestSample
