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
package com.sibvisions.kitchensink.samples.layouts;

import javax.rad.genui.component.UILabel;
import javax.rad.genui.container.UIDesktopPanel;
import javax.rad.genui.container.UIInternalFrame;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.control.UIEditor;
import javax.rad.genui.layout.UIBorderLayout;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.model.IDataBook;
import javax.rad.model.IDataRow;
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
		return "Layouts";
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
		
		// Our first internal frame/window.
		UIInternalFrame labelFrame = new UIInternalFrame(desktop);
		labelFrame.setLayout(new UIBorderLayout());
		labelFrame.setTitle("Label");
		labelFrame.add(createCenteredLabel("This is a simple window.", Tango.BUTTER_1));
		labelFrame.pack();
		labelFrame.setVisible(true);
		
		// Our second internal frame/window.
		UIInternalFrame borderFrame = new UIInternalFrame(desktop);
		borderFrame.setLayout(new UIBorderLayout());
		borderFrame.setTitle("Border");
		borderFrame.add(createCenteredLabel("North", Tango.BUTTER_1), UIBorderLayout.NORTH);
		borderFrame.add(createCenteredLabel("West", Tango.CHAMELEON_1), UIBorderLayout.WEST);
		borderFrame.add(createCenteredLabel("South", Tango.SKY_BLUE_1), UIBorderLayout.SOUTH);
		borderFrame.add(createCenteredLabel("East", Tango.PLUM_1), UIBorderLayout.EAST);
		borderFrame.add(createCenteredLabel("Center", Tango.SCARLET_RED_1), UIBorderLayout.CENTER);
		borderFrame.pack();
		borderFrame.setVisible(true);
		
		IDataBook controlsBook = new MemDataBook();
		controlsBook.getRowDefinition().addColumnDefinition(createBooleanColumn("TAB_MODE"));
		controlsBook.setName("CONTROLS");
		controlsBook.open();
		
		controlsBook.insert(false);
		controlsBook.setValue("TAB_MODE", Boolean.valueOf(desktop.isTabMode()));
		
		controlsBook.eventValuesChanged().addListener(pDataRowEvent ->
		{
			IDataRow dataRow = pDataRowEvent.getChangedDataRow();
			
			desktop.setTabMode(((Boolean) dataRow.getValue("TAB_MODE")).booleanValue());
		});
		
		UIFormLayout controlsLayout = new UIFormLayout();
		controlsLayout.setNewlineCount(4);
		
		UIPanel controls = new UIPanel();
		controls.setLayout(controlsLayout);
		controls.add(new UILabel("Tab Mode"));
		controls.add(new UIEditor(controlsBook, "TAB_MODE"));
		
		UIPanel content = new UIPanel();
		content.setLayout(new UIBorderLayout());
		content.add(desktop, UIBorderLayout.CENTER);
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
	
}	// MdiSample
