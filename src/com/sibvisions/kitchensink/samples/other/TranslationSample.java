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
import javax.rad.genui.component.UILabel;
import javax.rad.genui.container.UIGroupPanel;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.layout.UIBorderLayout;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.ui.container.IPanel;
import javax.rad.util.TranslationMap;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * A simple demonstration of the capability to take pictures of any control.
 * 
 * @author Robert Zenz
 */
public class TranslationSample extends AbstractSample implements ISample
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
		// Normally these maps would be loaded from XML files,
		// but for this demonstration we'll create them by hand.
		TranslationMap german = new TranslationMap();
		german.put("Press one of the buttons below to change the language.", "Drücken Sie einen der Knöpfe um die Sprache zu wechseln.");
		german.put("English", "Englisch");
		german.put("German", "Deutsch");
		german.put("Group", "Gruppe");
		german.put("Center", "Mitte");
		german.put("North", "Norden");
		german.put("East", "Osten");
		german.put("South", "Süden");
		german.put("West", "Westen");
		
		UIPanel content = new UIPanel();
		
		UILabel infoLabel = new UILabel("Press one of the buttons below to change the language.");
		
		UIButton englishButton = new UIButton("English");
		englishButton.eventAction().addListener((pActionEvent) -> content.setTranslation(null));
		
		UIButton germanButton = new UIButton("German");
		
		// As you can see, setting the translation of the parent is enough
		// to translate everything down to the last child.
		germanButton.eventAction().addListener((pActionEvent) -> content.setTranslation(german));
		
		UIGroupPanel group = new UIGroupPanel("Group");
		group.setLayout(new UIBorderLayout());
		group.add(createCenteredLabel("Center", Tango.SCARLET_RED_1), UIBorderLayout.CENTER);
		group.add(createCenteredLabel("North", Tango.CHAMELEON_1), UIBorderLayout.NORTH);
		group.add(createCenteredLabel("East", Tango.SKY_BLUE_1), UIBorderLayout.EAST);
		group.add(createCenteredLabel("South", Tango.BUTTER_1), UIBorderLayout.SOUTH);
		group.add(createCenteredLabel("West", Tango.PLUM_1), UIBorderLayout.WEST);
		
		UIFormLayout contentLayout = new UIFormLayout();
		
		content.setLayout(contentLayout);
		content.add(infoLabel, contentLayout.getConstraints(0, 0, -1, 0));
		content.add(englishButton, contentLayout.getConstraints(0, 1));
		content.add(germanButton, contentLayout.getConstraints(1, 1));
		content.add(group, contentLayout.getConstraints(0, 2, -1, -1));
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Translation";
	}
	
}	// CaptureSample
