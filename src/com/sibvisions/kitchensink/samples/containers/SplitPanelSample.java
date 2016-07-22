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

import javax.rad.genui.component.UILabel;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.container.UISplitPanel;
import javax.rad.genui.control.UIEditor;
import javax.rad.genui.layout.UIBorderLayout;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.model.ColumnDefinition;
import javax.rad.model.IDataBook;
import javax.rad.model.IDataRow;
import javax.rad.model.ModelException;
import javax.rad.model.datatype.BigDecimalDataType;
import javax.rad.model.ui.ICellEditor;
import javax.rad.ui.container.IPanel;
import javax.rad.ui.container.ISplitPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.kitchensink.samples.AbstractSample;
import com.sibvisions.rad.genui.celleditor.UIEnumCellEditor;
import com.sibvisions.rad.model.mem.MemDataBook;

/**
 * Shows the {@link UISplitPanel}.
 * 
 * @author Robert Zenz
 */
public class SplitPanelSample extends AbstractSample implements ISample
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
		UISplitPanel splitPanelFirstSecond = new UISplitPanel(UISplitPanel.SPLIT_LEFT_RIGHT);
		splitPanelFirstSecond.setFirstComponent(createCenteredLabel("First", Tango.BUTTER_1));
		splitPanelFirstSecond.setSecondComponent(createCenteredLabel("Second", Tango.CHAMELEON_1));
		
		UISplitPanel splitPanelThird = new UISplitPanel(UISplitPanel.SPLIT_TOP_BOTTOM);
		splitPanelThird.setFirstComponent(splitPanelFirstSecond);
		splitPanelThird.setSecondComponent(createCenteredLabel("Third", Tango.SKY_BLUE_1));
		
		IDataBook controlsBook = new MemDataBook();
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("ORIENTATION_SPLITPANELFIRSTSECOND", new BigDecimalDataType(createOrientationCellEditor())));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("ORIENTATION_SPLITPANELTHIRD", new BigDecimalDataType(createOrientationCellEditor())));
		controlsBook.setName("CONTROLS");
		controlsBook.open();
		
		controlsBook.insert(false);
		controlsBook.setValue("ORIENTATION_SPLITPANELFIRSTSECOND", BigDecimal.valueOf(splitPanelFirstSecond.getOrientation()));
		controlsBook.setValue("ORIENTATION_SPLITPANELTHIRD", BigDecimal.valueOf(splitPanelThird.getOrientation()));
		
		controlsBook.eventValuesChanged().addListener(pDataRowEvent ->
		{
			IDataRow dataRow = pDataRowEvent.getChangedDataRow();
			
			splitPanelFirstSecond.setOrientation(((BigDecimal) dataRow.getValue("ORIENTATION_SPLITPANELFIRSTSECOND")).intValue());
			splitPanelThird.setOrientation(((BigDecimal) dataRow.getValue("ORIENTATION_SPLITPANELTHIRD")).intValue());
		});
		
		UIFormLayout controlsLayout = new UIFormLayout();
		controlsLayout.setNewlineCount(4);
		
		UIPanel controls = new UIPanel();
		controls.setLayout(controlsLayout);
		controls.add(new UILabel("Orientation of first Panel"));
		controls.add(new UIEditor(controlsBook, "ORIENTATION_SPLITPANELFIRSTSECOND"));
		
		controls.add(new UILabel("Orientation of second Panel"));
		controls.add(new UIEditor(controlsBook, "ORIENTATION_SPLITPANELTHIRD"));
		
		UIPanel content = new UIPanel();
		content.setLayout(new UIBorderLayout());
		content.add(splitPanelThird, UIBorderLayout.CENTER);
		content.add(controls, UIBorderLayout.SOUTH);
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Split";
	}
	
	/** 
	 * Creates an {@link ICellEditor} that can be used for editing the
	 * Orientation of controls.
	 *
	 * @return the {@link ICellEditor}.
	 */
	private ICellEditor createOrientationCellEditor()
	{
		UIEnumCellEditor cellEditor = new UIEnumCellEditor();
		cellEditor.setAllowedValues(new Object[] {
				new BigDecimal(ISplitPanel.SPLIT_LEFT_RIGHT),
				new BigDecimal(ISplitPanel.SPLIT_TOP_BOTTOM)
				
		});
		cellEditor.setDisplayValues(new String[] {
				"Left/Right",
				"Top/Bottom"
		});
		
		return cellEditor;
	}
	
}	// SplitPanelSample
