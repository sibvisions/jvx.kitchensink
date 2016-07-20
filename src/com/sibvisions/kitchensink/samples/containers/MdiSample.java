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

import javax.rad.genui.component.UILabel;
import javax.rad.genui.container.UIDesktopPanel;
import javax.rad.genui.container.UIInternalFrame;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.container.UISplitPanel;
import javax.rad.genui.control.UIEditor;
import javax.rad.genui.control.UITable;
import javax.rad.genui.layout.UIBorderLayout;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.model.ColumnDefinition;
import javax.rad.model.IDataBook;
import javax.rad.model.IDataRow;
import javax.rad.model.datatype.BooleanDataType;
import javax.rad.ui.container.IDesktopPanel;
import javax.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.kitchensink.samples.AbstractSample;
import com.sibvisions.rad.model.mem.MemDataBook;

/**
 * Shows the MDI (multi-document interface) system that is available in JVx.
 * 
 * @author Robert Zenz
 */
public class MdiSample extends AbstractSample implements ISample
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
	public IPanel getContent() throws Throwable
	{
		UIDesktopPanel desktop = new UIDesktopPanel();
		
		// Internal windows can be treated as any other container available
		// in JVx, with the exception that they can/should only be added to
		// a UIDesktopPanel.
		
		IDataBook eventsDataBook = createEventsDataBook();
		
		// Our first internal frame/window.
		UIInternalFrame labelFrame = createInternalFrame(desktop, "Label", eventsDataBook);
		labelFrame.add(createCenteredLabel("This is a simple window.", Tango.BUTTER_1));
		labelFrame.pack();
		labelFrame.setVisible(true);
		
		// Our second internal frame/window.
		UIInternalFrame borderFrame = createInternalFrame(desktop, "Border", eventsDataBook);
		borderFrame.add(createCenteredLabel("North", Tango.BUTTER_1), UIBorderLayout.NORTH);
		borderFrame.add(createCenteredLabel("West", Tango.CHAMELEON_1), UIBorderLayout.WEST);
		borderFrame.add(createCenteredLabel("South", Tango.SKY_BLUE_1), UIBorderLayout.SOUTH);
		borderFrame.add(createCenteredLabel("East", Tango.PLUM_1), UIBorderLayout.EAST);
		borderFrame.add(createCenteredLabel("Center", Tango.SCARLET_RED_1), UIBorderLayout.CENTER);
		borderFrame.pack();
		borderFrame.setVisible(true);
		
		IDataBook controlsBook = new MemDataBook();
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("TAB_MODE", new BooleanDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("NAVIGATION_KEYS", new BooleanDataType()));
		controlsBook.setName("CONTROLS");
		controlsBook.open();
		
		controlsBook.insert(false);
		controlsBook.setValue("TAB_MODE", Boolean.valueOf(desktop.isTabMode()));
		controlsBook.setValue("NAVIGATION_KEYS", Boolean.valueOf(desktop.isNavigationKeysEnabled()));
		
		controlsBook.eventValuesChanged().addListener(pDataRowEvent ->
		{
			IDataRow dataRow = pDataRowEvent.getChangedDataRow();
			
			desktop.setTabMode(((Boolean)dataRow.getValue("TAB_MODE")).booleanValue());
			desktop.setNavigationKeysEnabled(((Boolean)dataRow.getValue("NAVIGATION_KEYS")).booleanValue());
		});
		
		UIFormLayout controlsLayout = new UIFormLayout();
		controlsLayout.setNewlineCount(4);
		
		UIPanel controls = new UIPanel();
		controls.setLayout(controlsLayout);
		controls.add(new UILabel("Tab Mode"));
		controls.add(new UIEditor(controlsBook, "TAB_MODE"));
		controls.add(new UILabel("Navigation Keys"));
		controls.add(new UIEditor(controlsBook, "NAVIGATION_KEYS"));
		
		UITable eventsTable = new UITable(eventsDataBook);
		
		UISplitPanel mainPanel = new UISplitPanel();
		mainPanel.setDividerAlignment(UISplitPanel.DIVIDER_BOTTOM_RIGHT);
		mainPanel.setFirstComponent(desktop);
		mainPanel.setSecondComponent(eventsTable);
		
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
		return "MDI";
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates an {@link UIInternalFrame}.
	 * 
	 * @param pDesktopPanel the {@link IDesktopPanel}.
	 * @param pTitle the title.
	 * @param pEventsDataBook the {@link IDataBook} for events.
	 * @return the {@link UIInternalFrame}.
	 */
	private UIInternalFrame createInternalFrame(IDesktopPanel pDesktopPanel, String pTitle, IDataBook pEventsDataBook)
	{
		UIInternalFrame frame = new UIInternalFrame(pDesktopPanel);
		frame.setLayout(new UIBorderLayout());
		frame.setTitle(pTitle);
		
		frame.eventWindowActivated().addListener(pWindowEvent -> insertEvent(pEventsDataBook, pTitle + ": Window Activated"));
		frame.eventWindowClosed().addListener(pWindowEvent -> insertEvent(pEventsDataBook, pTitle + ": Window Closed"));
		frame.eventWindowClosing().addListener(pWindowEvent ->
		{
			insertEvent(pEventsDataBook, pTitle + ": Window Closing");
			frame.dispose();
		});
		frame.eventWindowDeactivated().addListener(pWindowEvent -> insertEvent(pEventsDataBook, pTitle + ": Window Deactivated"));
		frame.eventWindowDeiconified().addListener(pWindowEvent -> insertEvent(pEventsDataBook, pTitle + ": Window Deiconified"));
		frame.eventWindowIconified().addListener(pWindowEvent -> insertEvent(pEventsDataBook, pTitle + ": Window Iconified"));
		frame.eventWindowOpened().addListener(pWindowEvent -> insertEvent(pEventsDataBook, pTitle + ": Window Opened"));
		
		return frame;
	}
	
}	// MdiSample
