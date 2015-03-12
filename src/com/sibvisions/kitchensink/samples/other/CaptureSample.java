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
import javax.rad.genui.component.UITextField;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.ui.IImage;
import javax.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * A simple demonstration of the capability to take pictures of any control.
 * 
 * @author Robert Zenz
 */
public class CaptureSample extends AbstractSample implements ISample
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
		UITextField textField = new UITextField("You can take an image of this element by pressing the button.");
		
		UIIcon icon = new UIIcon();
		
		UIButton button = new UIButton();
		button.setImage(getImage("camera-photo.png"));
		button.eventAction().addListener(pActionEvent ->
		{
			int width = textField.getBounds().getWidth();
			int height = textField.getBounds().getHeight();
			
			// Take the picture of the control.
			IImage image = textField.capture(width, height);
			
			icon.setImage(image);
		});
		
		UIFormLayout contentLayout = new UIFormLayout();
		
		UIPanel content = new UIPanel();
		content.setLayout(contentLayout);
		
		content.add(textField, contentLayout.getConstraints(0, 0));
		content.add(button, contentLayout.getConstraints(1, 0));
		content.add(icon, contentLayout.getConstraints(0, 1, -1, -1));
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Capture";
	}
	
}	// CaptureSample
