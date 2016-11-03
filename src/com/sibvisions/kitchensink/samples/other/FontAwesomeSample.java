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

import java.lang.reflect.Field;

import javax.rad.genui.IFontAwesome;
import javax.rad.genui.UIImage;
import javax.rad.genui.component.UIIcon;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.container.UIScrollPanel;
import javax.rad.genui.layout.UIBorderLayout;
import javax.rad.genui.layout.UIFlowLayout;
import javax.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * An {@link ISample} showing the FontAwesome support.
 * 
 * @author Robert Zenz
 */
public class FontAwesomeSample extends AbstractSample implements ISample
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
		UIFlowLayout contentLayout = new UIFlowLayout();
		contentLayout.setAutoWrap(true);
		contentLayout.setMargins(10, 10, 10, 10);
		
		UIScrollPanel content = new UIScrollPanel();
		content.setLayout(contentLayout);
		
		for (Field field : IFontAwesome.class.getDeclaredFields())
		{
			if (field.getType() == String.class)
			{
				content.add(createIcon((String)field.get(null), field.getName()));
			}
		}
		
		UIPanel container = new UIPanel();
		container.setLayout(new UIBorderLayout());
		container.add(content, UIBorderLayout.CENTER);
		
		return container;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "FontAwesome";
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates an {@link UIIcon} with the specified {@link IFontAwesome} glyph.
	 *
	 * @param pIconName the name of the icon/glyph.
	 * @param pToolTip the tooltip to apply.
	 * @return the {@link UIIcon} with the given icon/glyph.
	 */
	private UIIcon createIcon(String pIconName, String pToolTip)
	{
		UIIcon icon = new UIIcon(UIImage.getImage(pIconName));
		icon.setToolTipText(pToolTip);
		
		return icon;
	}
	
}	// CursorSample
