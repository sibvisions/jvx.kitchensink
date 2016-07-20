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

import javax.rad.genui.UIColor;
import javax.rad.genui.UICursor;
import javax.rad.genui.UIDimension;
import javax.rad.genui.component.UILabel;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.ui.IColor;
import javax.rad.ui.ICursor;
import javax.rad.ui.component.ILabel;
import javax.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * An {@link ISample} showing the different cursors that are available.
 * 
 * @author Robert Zenz
 */
public class CursorSample extends AbstractSample implements ISample
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
		UIFormLayout contentLayout = new UIFormLayout();
		contentLayout.setNewlineCount(4);
		
		UIPanel content = new UIPanel();
		content.setLayout(contentLayout);
		
		content.add(createLabel("Default", Tango.BUTTER_1, ICursor.DEFAULT_CURSOR));
		content.add(createLabel("Crosshair", Tango.BUTTER_1, ICursor.CROSSHAIR_CURSOR));
		content.add(createLabel("Hand", Tango.BUTTER_1, ICursor.HAND_CURSOR));
		content.add(createLabel("Wait", Tango.BUTTER_1, ICursor.WAIT_CURSOR));
		
		content.add(createLabel("North Resize", Tango.CHAMELEON_1, ICursor.N_RESIZE_CURSOR));
		content.add(createLabel("West Resize", Tango.CHAMELEON_1, ICursor.W_RESIZE_CURSOR));
		content.add(createLabel("South Resize", Tango.CHAMELEON_1, ICursor.S_RESIZE_CURSOR));
		content.add(createLabel("East Resize", Tango.CHAMELEON_1, ICursor.E_RESIZE_CURSOR));
		
		content.add(createLabel("Move", Tango.SKY_BLUE_1, ICursor.MOVE_CURSOR));
		content.add(createLabel("Text", Tango.ORANGE_1, ICursor.TEXT_CURSOR));
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Cursor";
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates an {@link UILabel} with the specified {@link UICursor}.
	 *
	 * @param pText the text of the {@link UILabel}.
	 * @param pColor the {@link UIColor}.
	 * @param pCursor the cursor.
	 * @return the {@link UILabel}.
	 */
	private ILabel createLabel(String pText, IColor pColor, int pCursor)
	{
		ILabel label = createCenteredLabel(pText, pColor);
		label.setCursor(UICursor.getPredefinedCursor(pCursor));
		label.setPreferredSize(new UIDimension(128, 48));
		return label;
	}
	
}	// CursorSample
