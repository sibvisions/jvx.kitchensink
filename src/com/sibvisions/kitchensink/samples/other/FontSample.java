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

import jvx.rad.genui.UIFont;
import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.layout.UIFormLayout;
import jvx.rad.ui.IColor;
import jvx.rad.ui.IFont;
import jvx.rad.ui.component.ILabel;
import jvx.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * Shows the capabilities to change the font of controls.
 * 
 * @author Robert Zenz
 */
public class FontSample extends AbstractSample implements ISample
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
		contentLayout.setNewlineCount(3);
		
		UIPanel content = new UIPanel();
		content.setLayout(contentLayout);
		
		addTestLabels(content, Tango.BUTTER_1, "Default");
		addTestLabels(content, Tango.CHAMELEON_1, "Arial");
		addTestLabels(content, Tango.ORANGE_1, "Courier New");
		addTestLabels(content, Tango.SKY_BLUE_1, "DejaVu Serif");
		addTestLabels(content, Tango.BUTTER_1, "DejaVu Sans Mono");
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Font";
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Adds the test labels.
	 *
	 * @param pPanel the panel.
	 * @param pColor the color.
	 * @param pFontName the font name.
	 */
	private void addTestLabels(IPanel pPanel, IColor pColor, String pFontName)
	{
		pPanel.add(createLabel(pFontName, pColor, pFontName, IFont.PLAIN, 0));
		pPanel.add(createLabel("Italic", pColor, pFontName, IFont.ITALIC, 0));
		pPanel.add(createLabel("Bold", pColor, pFontName, IFont.BOLD, 0));
		
		pPanel.add(createLabel(pFontName + " 6", pColor, pFontName, IFont.PLAIN, 6));
		pPanel.add(createLabel("Italic", pColor, pFontName, IFont.ITALIC, 6));
		pPanel.add(createLabel("Bold", pColor, pFontName, IFont.BOLD, 6));
		
		pPanel.add(createLabel(pFontName + " 18", pColor, pFontName, IFont.PLAIN, 18));
		pPanel.add(createLabel("Italic", pColor, pFontName, IFont.ITALIC, 18));
		pPanel.add(createLabel("Bold", pColor, pFontName, IFont.BOLD, 18));
	}
	
	/**
	 * Creates the label.
	 *
	 * @param pText the text.
	 * @param pColor the color.
	 * @param pFontName the font name.
	 * @param pFontStyle the font style.
	 * @param pFontSize the font size.
	 * @return the i label.
	 */
	private ILabel createLabel(String pText, IColor pColor, String pFontName, int pFontStyle, int pFontSize)
	{
		ILabel label = createCenteredLabel(pText, pColor);
		
		String fontName = label.getFont().getFontName();
		if (pFontName != null && !pFontName.equals("Default"))
		{
			fontName = pFontName;
		}
		
		int fontSize = label.getFont().getSize();
		if (pFontSize > 0)
		{
			fontSize = pFontSize;
		}
		
		label.setFont(new UIFont(fontName, pFontStyle, fontSize));
		return label;
	}
	
}	// FontSample
