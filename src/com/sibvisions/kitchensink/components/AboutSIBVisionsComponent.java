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

import jvx.rad.genui.UIColor;
import jvx.rad.genui.UIComponent;
import jvx.rad.genui.UIFont;
import jvx.rad.genui.UIImage;
import jvx.rad.genui.component.UIIcon;
import jvx.rad.genui.component.UILabel;
import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.layout.UIFormLayout;
import jvx.rad.ui.container.IPanel;

/**
 * A simple {@link UIComponent} extension which displays information about SIB
 * Visions.
 * 
 * @author Robert Zenz
 */
public class AboutSIBVisionsComponent extends UIComponent<IPanel>
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Initialization
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a new instance of {@link AboutSIBVisionsComponent}.
	 */
	public AboutSIBVisionsComponent()
	{
		super(new UIPanel());
		
		UILabel descriptionLabel = new UILabel("Trust the best!");
		descriptionLabel.setFont(UIFont.getDefaultFont().deriveFont(UIFont.BOLD));
		descriptionLabel.setHorizontalAlignment(UILabel.ALIGN_CENTER);
		
		UIFormLayout layout = new UIFormLayout();
		layout.setHorizontalAlignment(UIFormLayout.ALIGN_CENTER);
		layout.setVerticalAlignment(UIFormLayout.ALIGN_CENTER);
		
		uiResource.setLayout(layout);
		uiResource.setBackground(UIColor.white);
		uiResource.add(new UIIcon(UIImage.getImage("/com/sibvisions/kitchensink/images/sib-visions.png")), layout.getConstraints(0, 0));
		uiResource.add(descriptionLabel, layout.getConstraints(0, 1));
		uiResource.add(new UILabel("     Wehlistreet 29 / Stair 1 / 2nd Floor"), layout.getConstraints(0, 2));
		layout.setVerticalGap(-4);
		uiResource.add(new UILabel("     A-1200 Vienna"), layout.getConstraints(0, 3));
		uiResource.add(new UILabel("     +43 (0) 1 934 6009"), layout.getConstraints(0, 4));
		uiResource.add(new UILabel("     office@sibvisions.com"), layout.getConstraints(0, 5));
	}
	
}	// AboutSIBVisionsComponent

