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

import java.math.BigDecimal;

import javax.rad.genui.component.UILabel;
import javax.rad.genui.component.UITextArea;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.control.UIEditor;
import javax.rad.genui.layout.UIBorderLayout;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.model.ColumnDefinition;
import javax.rad.model.IDataBook;
import javax.rad.model.IDataRow;
import javax.rad.model.datatype.BigDecimalDataType;
import javax.rad.model.datatype.BooleanDataType;
import javax.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.samples.AbstractSample;
import com.sibvisions.rad.model.mem.MemDataBook;

/**
 * Demonstrates the {@link UITextArea}.
 * 
 * @author Robert Zenz
 */
public class TextAreaSample extends AbstractSample implements ISample
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
		UITextArea textArea = new UITextArea("This is some text.\nIn multiple lines!\nReally.");
		
		UIFormLayout containerLayout = new UIFormLayout();
		
		UIPanel container = new UIPanel();
		container.setLayout(containerLayout);
		container.add(textArea, containerLayout.getConstraints(0, 0, -1, -1));
		
		IDataBook controlsBook = new MemDataBook();
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("BORDER", new BooleanDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("EDITABLE", new BooleanDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("HORIZONTAL_ALIGNMENT", new BigDecimalDataType(createHorizontalAlignmentCellEditor())));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("VERTICAL_ALIGNMENT", new BigDecimalDataType(createVerticalAlignmentCellEditor())));
		controlsBook.setName("CONTROLS");
		controlsBook.open();
		
		controlsBook.insert(false);
		controlsBook.setValue("BORDER", Boolean.valueOf(textArea.isBorderVisible()));
		controlsBook.setValue("EDITABLE", Boolean.valueOf(textArea.isEditable()));
		controlsBook.setValue("HORIZONTAL_ALIGNMENT", new BigDecimal(textArea.getHorizontalAlignment()));
		controlsBook.setValue("VERTICAL_ALIGNMENT", new BigDecimal(textArea.getVerticalAlignment()));
		
		controlsBook.eventValuesChanged().addListener(pDataRowEvent ->
		{
			IDataRow dataRow = pDataRowEvent.getChangedDataRow();
			
			textArea.setBorderVisible(((Boolean) dataRow.getValue("BORDER")).booleanValue());
			textArea.setEditable(((Boolean) dataRow.getValue("EDITABLE")).booleanValue());
			textArea.setHorizontalAlignment(((BigDecimal) dataRow.getValue("HORIZONTAL_ALIGNMENT")).intValue());
			textArea.setVerticalAlignment(((BigDecimal) dataRow.getValue("VERTICAL_ALIGNMENT")).intValue());
		});
		
		UIFormLayout controlsLayout = new UIFormLayout();
		
		UIPanel controls = new UIPanel();
		controls.setLayout(controlsLayout);
		controls.add(new UILabel("Horizontal Alignment"), controlsLayout.getConstraints(0, 0));
		controls.add(new UIEditor(controlsBook, "HORIZONTAL_ALIGNMENT"), controlsLayout.getConstraints(1, 0));
		
		controls.add(new UILabel("Vertical Alignment"), controlsLayout.getConstraints(2, 0));
		controls.add(new UIEditor(controlsBook, "VERTICAL_ALIGNMENT"), controlsLayout.getConstraints(3, 0));
		
		controls.add(new UILabel("Border"), controlsLayout.getConstraints(0, 1));
		controls.add(new UIEditor(controlsBook, "BORDER"), controlsLayout.getConstraints(1, 1));
		
		controls.add(new UILabel("Editable"), controlsLayout.getConstraints(2, 1));
		controls.add(new UIEditor(controlsBook, "EDITABLE"), controlsLayout.getConstraints(3, 1));
		
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
		return "TextArea";
	}
	
}	// TextAreaSample
