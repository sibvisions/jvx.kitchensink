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

import jvx.rad.genui.component.UILabel;
import jvx.rad.genui.component.UIPasswordField;
import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.control.UIEditor;
import jvx.rad.genui.layout.UIBorderLayout;
import jvx.rad.genui.layout.UIFormLayout;
import jvx.rad.model.ColumnDefinition;
import jvx.rad.model.IDataBook;
import jvx.rad.model.IDataRow;
import jvx.rad.model.datatype.StringDataType;
import jvx.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.rad.model.mem.MemDataBook;
import com.sibvisions.util.type.StringUtil;

/**
 * Demonstrates the {@link UIPasswordField}.
 * 
 * @author Robert Zenz
 */
public class PasswordFieldSample implements ISample
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
		UIPasswordField passwordField = new UIPasswordField();
		passwordField.setColumns(15);
		
		UIPanel container = new UIPanel();
		container.setLayout(new UIFormLayout());
		container.add(passwordField);
		
		IDataBook controlsBook = new MemDataBook();
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("MASK", new StringDataType(1)));
		controlsBook.setName("CONTROL");
		controlsBook.open();
		
		controlsBook.insert(false);
		controlsBook.setValue("MASK", Character.valueOf(passwordField.getEchoChar()));
		
		controlsBook.eventValuesChanged().addListener(pDataRowEvent ->
		{
			IDataRow dataRow = pDataRowEvent.getChangedDataRow();
			
			if (!StringUtil.isEmpty((String) dataRow.getValue("MASK")))
			{
				passwordField.setEchoChar(dataRow.getValue("MASK").toString().charAt(0));
			}
		});
		
		UIFormLayout controlsLayout = new UIFormLayout();
		
		UIPanel controls = new UIPanel();
		controls.setLayout(controlsLayout);
		controls.add(new UILabel("Mask/Echo Char"), controlsLayout.getConstraints(0, 0));
		controls.add(new UIEditor(controlsBook, "MASK"), controlsLayout.getConstraints(1, 0));
		
		UIPanel content = new UIPanel();
		content.setLayout(new UIBorderLayout());
		content.add(container, UIBorderLayout.CENTER);
		content.add(controls, UIBorderLayout.SOUTH);
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "PasswordField";
	}
	
}	// PasswordFieldSample
