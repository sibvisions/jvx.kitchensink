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

import jvx.rad.genui.UIDimension;
import jvx.rad.genui.component.UILabel;
import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.layout.UIFormLayout;
import jvx.rad.ui.IColor;
import jvx.rad.ui.component.ILabel;
import jvx.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * Demonstrates the {@link UILabel}.
 * 
 * @author Robert Zenz
 */
public class LabelSample extends AbstractSample implements ISample
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
		return "Components";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPanel getContent() throws Throwable
	{
		UIPanel content = new UIPanel();
		content.setLayout(new UIFormLayout());
		
		content.add(createCenteredLabel("This is a label.", Tango.BUTTER_1));
		content.add(createCenteredLabel("This too.", Tango.BUTTER_1));
		
		content.add(createCenteredLabel("The following label does not have a text:", Tango.CHAMELEON_1));
		content.add(createCenteredLabel("", Tango.CHAMELEON_1));
		
		content.add(createCenteredLabel("Top-Left Aligned:", Tango.SKY_BLUE_1));
		content.add(createLabel("Some text", Tango.SKY_BLUE_1, ILabel.ALIGN_LEFT, ILabel.ALIGN_TOP));
		
		content.add(createCenteredLabel("Top-Center Aligned:", Tango.SKY_BLUE_1));
		content.add(createLabel("Some text", Tango.SKY_BLUE_1, ILabel.ALIGN_CENTER, ILabel.ALIGN_TOP));
		
		content.add(createCenteredLabel("Top-Right Aligned:", Tango.SKY_BLUE_1));
		content.add(createLabel("Some text", Tango.SKY_BLUE_1, ILabel.ALIGN_RIGHT, ILabel.ALIGN_TOP));
		
		content.add(createCenteredLabel("Center-Left Aligned:", Tango.CHAMELEON_1));
		content.add(createLabel("Some text", Tango.CHAMELEON_1, ILabel.ALIGN_LEFT, ILabel.ALIGN_CENTER));
		
		content.add(createCenteredLabel("Center-Center Aligned:", Tango.CHAMELEON_1));
		content.add(createLabel("Some text", Tango.CHAMELEON_1, ILabel.ALIGN_CENTER, ILabel.ALIGN_CENTER));
		
		content.add(createCenteredLabel("Center-Right Aligned:", Tango.CHAMELEON_1));
		content.add(createLabel("Some text", Tango.CHAMELEON_1, ILabel.ALIGN_RIGHT, ILabel.ALIGN_CENTER));
		
		content.add(createCenteredLabel("Bottom-Left Aligned:", Tango.ORANGE_1));
		content.add(createLabel("Some text", Tango.ORANGE_1, ILabel.ALIGN_LEFT, ILabel.ALIGN_BOTTOM));
		
		content.add(createCenteredLabel("Bottom-Center Aligned:", Tango.ORANGE_1));
		content.add(createLabel("Some text", Tango.ORANGE_1, ILabel.ALIGN_CENTER, ILabel.ALIGN_BOTTOM));
		
		content.add(createCenteredLabel("Bottom-Right Aligned:", Tango.ORANGE_1));
		content.add(createLabel("Some text", Tango.ORANGE_1, ILabel.ALIGN_RIGHT, ILabel.ALIGN_BOTTOM));
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Label";
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates an {@link ILabel} with the given properties.
	 *
	 * @param pText the text.
	 * @param pColor the color.
	 * @param pHorizontalAlignment the horizontal alignment.
	 * @param pVerticalAlignment the vertical alignment.
	 * @return the {@link ILabel}.
	 */
	private ILabel createLabel(String pText, IColor pColor, int pHorizontalAlignment, int pVerticalAlignment)
	{
		ILabel label = createCenteredLabel(pText, pColor);
		label.setHorizontalAlignment(pHorizontalAlignment);
		label.setPreferredSize(new UIDimension(128, 32));
		label.setVerticalAlignment(pVerticalAlignment);
		
		return label;
	}
	
}	// LabelSample
