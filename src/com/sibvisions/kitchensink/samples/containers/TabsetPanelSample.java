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

import javax.rad.genui.component.UICheckBox;
import javax.rad.genui.component.UILabel;
import javax.rad.genui.component.UITextField;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.container.UISplitPanel;
import javax.rad.genui.container.UITabsetPanel;
import javax.rad.genui.control.UIEditor;
import javax.rad.genui.control.UITable;
import javax.rad.genui.layout.UIBorderLayout;
import javax.rad.genui.layout.UIFlowLayout;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.model.ColumnDefinition;
import javax.rad.model.IDataBook;
import javax.rad.model.IDataRow;
import javax.rad.model.ModelException;
import javax.rad.model.datatype.BigDecimalDataType;
import javax.rad.model.datatype.BooleanDataType;
import javax.rad.model.ui.ICellEditor;
import javax.rad.ui.IColor;
import javax.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.kitchensink.samples.AbstractSample;
import com.sibvisions.rad.genui.celleditor.UIEnumCellEditor;
import com.sibvisions.rad.model.mem.MemDataBook;

/**
 * Shows the {@link UITabsetPanel}.
 * 
 * @author Robert Zenz
 */
public class TabsetPanelSample extends AbstractSample implements ISample
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
		UITabsetPanel tabset = new UITabsetPanel();
		tabset.setDragable(true);
		tabset.add(createTab(Tango.BUTTER_1), "First");
		tabset.add(createTab(Tango.CHAMELEON_1), "Second");
		tabset.add(createTab(Tango.SKY_BLUE_1), "Third");
		tabset.setIconAt(tabset.getComponentCount() - 1, getImage("emblem-important.png"));
		
		tabset.add(createTab(Tango.PLUM_1), "Fourth");
		tabset.add(createTab(Tango.SCARLET_RED_1), "Fifth");
		
		tabset.add(createTab(Tango.ALUMINIUM_1), "Can be closed.");
		tabset.setClosableAt(tabset.getComponentCount() - 1, true);
		
		tabset.add(new UILabel("Oh-oh oh oh oh-oh-oh"), "You can't click this");
		tabset.setEnabledAt(tabset.getComponentCount() - 1, false);
		tabset.setIconAt(tabset.getComponentCount() - 1, getImage("emblem-unreadable.png"));
		
		tabset.add(new UILabel("Sorry for the bad joke."), "Neither this");
		tabset.setEnabledAt(tabset.getComponentCount() - 1, false);
		
		tabset.add(createTab(Tango.ALUMINIUM_1), "Can not be closed.");
		tabset.setEnabledAt(tabset.getComponentCount() - 1, false);
		tabset.setClosableAt(tabset.getComponentCount() - 1, true);
		
		IDataBook eventsDataBook = createEventsDataBook();
		
		tabset.eventTabActivated().addListener(pTabsetEvent -> insertEvent(eventsDataBook, "Tab Activated - Old: " + pTabsetEvent.getOldIndex() + " - New: " + pTabsetEvent.getNewIndex()));
		tabset.eventTabClosed().addListener(pTabsetEvent -> insertEvent(eventsDataBook, "Tab Closed - Old: " + pTabsetEvent.getOldIndex() + " - New: " + pTabsetEvent.getNewIndex()));
		tabset.eventTabDeactivated().addListener(pTabsetEvent -> insertEvent(eventsDataBook, "Tab Deactivated - Old: " + pTabsetEvent.getOldIndex() + " - New: " + pTabsetEvent.getNewIndex()));
		tabset.eventTabMoved().addListener(pTabsetEvent -> insertEvent(eventsDataBook, "Tab Moved - Old: " + pTabsetEvent.getOldIndex() + " - New: " + pTabsetEvent.getNewIndex()));
		
		UITable eventsTable = new UITable(eventsDataBook);
		
		UISplitPanel mainPanel = new UISplitPanel();
		mainPanel.setDividerAlignment(UISplitPanel.DIVIDER_BOTTOM_RIGHT);
		mainPanel.setFirstComponent(tabset);
		mainPanel.setSecondComponent(eventsTable);
		
		IDataBook controlsBook = new MemDataBook();
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("PLACEMENT", new BigDecimalDataType(createTabPlacementCellEditor())));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("NAVIGATION_KEYS", new BooleanDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("DRAGGABLE", new BooleanDataType()));
		controlsBook.setName("CONTROLS");
		controlsBook.open();
		
		controlsBook.insert(false);
		controlsBook.setValue("PLACEMENT", new BigDecimal(tabset.getTabPlacement()));
		controlsBook.setValue("NAVIGATION_KEYS", Boolean.valueOf(tabset.isNavigationKeysEnabled()));
		controlsBook.setValue("DRAGGABLE", Boolean.valueOf(tabset.isDragable()));
		
		controlsBook.eventValuesChanged().addListener(pDataRowEvent ->
		{
			IDataRow dataRow = pDataRowEvent.getChangedDataRow();
			
			tabset.setTabPlacement(((BigDecimal) dataRow.getValue("PLACEMENT")).intValue());
			tabset.setNavigationKeysEnabled(((Boolean) dataRow.getValue("NAVIGATION_KEYS")).booleanValue());
			tabset.setDragable(((Boolean) dataRow.getValue("DRAGGABLE")).booleanValue());
		});
		
		UIFormLayout controlsLayout = new UIFormLayout();
		
		UIPanel controls = new UIPanel();
		controls.setLayout(controlsLayout);
		controls.add(new UILabel("Tab Placement"), controlsLayout.getConstraints(0, 0));
		controls.add(new UIEditor(controlsBook, "PLACEMENT"), controlsLayout.getConstraints(1, 0));
		controls.add(new UILabel("Navigation Keys"), controlsLayout.getConstraints(2, 0));
		controls.add(new UIEditor(controlsBook, "NAVIGATION_KEYS"), controlsLayout.getConstraints(3, 0));
		controls.add(new UILabel("Draggable"), controlsLayout.getConstraints(4, 0));
		controls.add(new UIEditor(controlsBook, "DRAGGABLE"), controlsLayout.getConstraints(5, 0));
		
		UIPanel content = new UIPanel();
		content.setLayout(new UIBorderLayout());
		content.add(mainPanel, UIBorderLayout.CENTER);
		content.add(controls, UIBorderLayout.SOUTH);
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Tabs";
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a tab.
	 *
	 * @param pColor the color.
	 * @return the tab.
	 */
	private UIPanel createTab(IColor pColor)
	{
		UIPanel tab = new UIPanel();
		tab.setLayout(new UIFlowLayout());
		tab.add(createCenteredLabel("First", pColor));
		tab.add(createCenteredLabel("Second", pColor));
		tab.add(createCenteredLabel("Third", pColor));
		tab.add(createCenteredLabel("Fourth", pColor));
		tab.add(createCenteredLabel("Fifth", pColor));
		tab.add(new UITextField("Some text"));
		tab.add(new UICheckBox("CheckBox"));
		
		return tab;
	}
	
	/**
	 * Creates the tab placement cell editor.
	 *
	 * @return the cell editor.
	 */
	private ICellEditor createTabPlacementCellEditor()
	{
		Object[] allowedValues = new Object[] {
				BigDecimal.valueOf(UITabsetPanel.PLACEMENT_BOTTOM),
				BigDecimal.valueOf(UITabsetPanel.PLACEMENT_LEFT),
				BigDecimal.valueOf(UITabsetPanel.PLACEMENT_RIGHT),
				BigDecimal.valueOf(UITabsetPanel.PLACEMENT_TOP)
		};
		
		String[] displayValues = new String[] {
				"Bottom",
				"Left",
				"Right",
				"Top"
		};
		
		return new UIEnumCellEditor(allowedValues, displayValues);
	}
	
}	// TabsetPanelSample
