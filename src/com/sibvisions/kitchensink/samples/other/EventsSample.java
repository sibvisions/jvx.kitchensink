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

import javax.rad.genui.component.UITextField;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.control.UITable;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.model.IDataBook;
import javax.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.samples.AbstractSample;

/**
 * An {@link ISample} that logs different events.
 * 
 * @author Robert Zenz
 */
public class EventsSample extends AbstractSample implements ISample
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
		IDataBook dataBook = createEventsDataBook();
		
		UITextField textField = new UITextField("Try typing here.");
		
		textField.eventFocusGained().addListener(pEvent -> insertEvent(dataBook, "Focus Gained"));
		textField.eventFocusLost().addListener(pEvent -> insertEvent(dataBook, "Focus Lost"));
		textField.eventKeyPressed().addListener(pEvent -> insertEvent(dataBook, "Key Pressed"));
		textField.eventKeyReleased().addListener(pEvent -> insertEvent(dataBook, "Key Released"));
		textField.eventKeyTyped().addListener(pEvent -> insertEvent(dataBook, "Key Typed"));
		textField.eventMouseClicked().addListener(pEvent -> insertEvent(dataBook, "Mouse Clicked"));
		textField.eventMouseEntered().addListener(pEvent -> insertEvent(dataBook, "Mouse Entered"));
		textField.eventMouseExited().addListener(pEvent -> insertEvent(dataBook, "Mouse Exited"));
		textField.eventMousePressed().addListener(pEvent -> insertEvent(dataBook, "Mouse Pressed"));
		textField.eventMouseReleased().addListener(pEvent -> insertEvent(dataBook, "Mouse Released"));
		
		UITable eventsTable = new UITable(dataBook);
		
		UIFormLayout contentLayout = new UIFormLayout();
		
		UIPanel content = new UIPanel();
		content.setLayout(contentLayout);
		
		content.add(textField, contentLayout.getConstraints(0, 0));
		content.add(eventsTable, contentLayout.getConstraints(1, 0, -1, -1));
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Events";
	}
	
}	// EventsSample
