/*
 * Copyright 2017 SIB Visions GmbH
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
package com.sibvisions.kitchensink.components;

import jvx.rad.genui.IFontAwesome;
import jvx.rad.genui.UIColor;
import jvx.rad.genui.UIComponent;
import jvx.rad.genui.UIFont;
import jvx.rad.genui.UIImage;
import jvx.rad.genui.component.UIIcon;
import jvx.rad.genui.component.UILabel;
import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.layout.UIFormLayout;
import jvx.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.Tango;

/**
 * A simple {@link UIComponent} extension which shows up as a visible note.
 * 
 * @author Robert Zenz
 */
public class NoteLabel extends UIComponent<IPanel>
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Initialization
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a new instance of {@link NoteLabel}.
	 * 
	 * @param pMessage the message to display.
	 */
	public NoteLabel(String pMessage)
	{
		super(new UIPanel());
		
		UIImage image = UIImage.getImage(IFontAwesome.COMMENTING_SMALL + ";size=24;color=" + UIColor.toHex(Tango.ALUMINIUM_6));
		
		UIIcon icon = new UIIcon(image);
		
		UILabel messageLabel = new UILabel(pMessage);
		messageLabel.setBackground(null);
		messageLabel.setFont(UIFont.getDefaultFont().deriveFont(UIFont.BOLD));
		messageLabel.setForeground(Tango.ALUMINIUM_6);
		messageLabel.setHorizontalAlignment(UILabel.ALIGN_CENTER);
		
		UIFormLayout layout = new UIFormLayout();
		layout.setHorizontalAlignment(UIFormLayout.ALIGN_CENTER);
		layout.setVerticalAlignment(UIFormLayout.ALIGN_CENTER);
		
		uiResource.setLayout(layout);
		uiResource.setBackground(Tango.BUTTER_1);
		uiResource.add(icon, layout.getConstraints(0, 0));
		uiResource.add(messageLabel, layout.getConstraints(1, 0));
	}
	
}	// NoteLabel
