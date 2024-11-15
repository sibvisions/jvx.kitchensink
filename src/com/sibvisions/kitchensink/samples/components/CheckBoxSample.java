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

import jvx.rad.genui.component.UICheckBox;
import jvx.rad.ui.component.IButton;

import com.sibvisions.kitchensink.ISample;

/**
 * Demonstrates the capabilities of the {@link UICheckBox}.
 * 
 * @author Robert Zenz
 */
public class CheckBoxSample extends ButtonSample implements ISample
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Overwritten methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "CheckBox";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected IButton createButtonComponent()
	{
		return new UICheckBox("This is a checkbox");
	}
	
}	// CheckBoxSample
