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
 * A simple {@link UIComponent} extension which displays information about JVx.
 * 
 * @author Robert Zenz
 */
public class AboutJVxComponent extends UIComponent<IPanel>
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Initialization
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a new instance of {@link AboutJVxComponent}.
	 */
	public AboutJVxComponent()
	{
		super(new UIPanel());
		
		UILabel descriptionLabel = new UILabel("The Java Enterprise Application Framework");
		descriptionLabel.setFont(UIFont.getDefaultFont().deriveFont(UIFont.BOLD));
		descriptionLabel.setHorizontalAlignment(UILabel.ALIGN_CENTER);
		
		UIFormLayout layout = new UIFormLayout();
		layout.setHorizontalAlignment(UIFormLayout.ALIGN_CENTER);
		layout.setVerticalAlignment(UIFormLayout.ALIGN_CENTER);
		
		uiResource.setLayout(layout);
		uiResource.setBackground(UIColor.white);
		uiResource.add(new UIIcon(UIImage.getImage("/com/sibvisions/kitchensink/images/jvx.png")), layout.getConstraints(0, 0));
		uiResource.add(descriptionLabel, layout.getConstraints(0, 1));
		uiResource.add(new UILabel("     • Fast and simple GUI creation"), layout.getConstraints(0, 2));
		layout.setVerticalGap(-4);
		uiResource.add(new UILabel("     • Single source for multiple GUI technologies"), layout.getConstraints(0, 3));
		uiResource.add(new UILabel("     • Multi-tier by default"), layout.getConstraints(0, 4));
		uiResource.add(new UILabel("     • Easy to use databinding"), layout.getConstraints(0, 5));
		uiResource.add(new UILabel("     • Active data model"), layout.getConstraints(0, 6));
		uiResource.add(new UILabel("     • Free Software (Apache License 2.0)"), layout.getConstraints(0, 7));
	}
	
}	// AboutJVxComponent
