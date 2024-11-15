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
package com.sibvisions.kitchensink.samples.containers;

import java.math.BigDecimal;

import jvx.rad.genui.component.UILabel;
import jvx.rad.genui.container.UIGroupPanel;
import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.control.UIEditor;
import jvx.rad.genui.layout.UIBorderLayout;
import jvx.rad.genui.layout.UIFormLayout;
import jvx.rad.model.ColumnDefinition;
import jvx.rad.model.IDataBook;
import jvx.rad.model.IDataRow;
import jvx.rad.model.ModelException;
import jvx.rad.model.datatype.BigDecimalDataType;
import jvx.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.kitchensink.samples.AbstractSample;
import com.sibvisions.rad.model.mem.MemDataBook;

/**
 * Shows the {@link UIGroupPanel}.
 * 
 * @author Robert Zenz
 */
public class GroupPanelSample extends AbstractSample implements ISample
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
		return "Containers";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPanel getContent() throws ModelException
	{
		UIGroupPanel simple = new UIGroupPanel("Simple");
		simple.setLayout(new UIBorderLayout());
		simple.add(createCenteredLabel("A rather simple group panel.", Tango.BUTTER_1), UIBorderLayout.CENTER);
		
		UIGroupPanel noText = new UIGroupPanel();
		noText.setLayout(new UIBorderLayout());
		noText.add(createCenteredLabel("Doesn't have a text.", Tango.CHAMELEON_1), UIBorderLayout.CENTER);
		
		UIGroupPanel longText = new UIGroupPanel("This is a very long group panel with a very long text.");
		longText.setLayout(new UIBorderLayout());
		longText.add(createCenteredLabel("Does have a rather long text.", Tango.SKY_BLUE_1), UIBorderLayout.CENTER);
		
		UIGroupPanel testGroup = new UIGroupPanel("This is the test group");
		testGroup.setLayout(new UIBorderLayout());
		testGroup.add(createCenteredLabel("Some text.", Tango.ORANGE_1), UIBorderLayout.CENTER);
		
		UIFormLayout mainLayout = new UIFormLayout();
		
		UIPanel main = new UIPanel();
		main.setLayout(mainLayout);
		main.add(simple, mainLayout.getConstraints(0, 0));
		main.add(noText, mainLayout.getConstraints(1, 0));
		main.add(longText, mainLayout.getConstraints(2, 0));
		main.add(testGroup, mainLayout.getConstraints(0, 1, -1, -1));
		
		IDataBook controlsBook = new MemDataBook();
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("HORIZONTAL_ALIGNMENT", new BigDecimalDataType(createHorizontalAlignmentCellEditor())));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("VERTICAL_ALIGNMENT", new BigDecimalDataType(createVerticalAlignmentCellEditor())));
		controlsBook.setName("CONTROLS");
		controlsBook.open();
		
		controlsBook.insert(false);
		controlsBook.setValue("HORIZONTAL_ALIGNMENT", BigDecimal.valueOf(testGroup.getHorizontalAlignment()));
		controlsBook.setValue("VERTICAL_ALIGNMENT", BigDecimal.valueOf(testGroup.getVerticalAlignment()));
		
		controlsBook.eventValuesChanged().addListener(pDataRowEvent ->
		{
			IDataRow dataRow = pDataRowEvent.getChangedDataRow();
			
			testGroup.setHorizontalAlignment(((BigDecimal) dataRow.getValue("HORIZONTAL_ALIGNMENT")).intValue());
			testGroup.setVerticalAlignment(((BigDecimal) dataRow.getValue("VERTICAL_ALIGNMENT")).intValue());
		});
		
		UIFormLayout controlsLayout = new UIFormLayout();
		controlsLayout.setNewlineCount(4);
		
		UIPanel controls = new UIPanel();
		controls.setLayout(controlsLayout);
		controls.add(new UILabel("Horizontal Alignment"));
		controls.add(new UIEditor(controlsBook, "HORIZONTAL_ALIGNMENT"));
		controls.add(new UILabel("Vertical Alignment"));
		controls.add(new UIEditor(controlsBook, "VERTICAL_ALIGNMENT"));
		
		UIPanel content = new UIPanel();
		content.setLayout(new UIBorderLayout());
		content.add(main, UIBorderLayout.CENTER);
		content.add(controls, UIBorderLayout.SOUTH);
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Group";
	}
	
}	// GroupPanelSample
