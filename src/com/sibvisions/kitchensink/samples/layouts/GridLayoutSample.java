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

import java.math.BigDecimal;

import jvx.rad.genui.UIInsets;
import jvx.rad.genui.component.UILabel;
import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.control.UIEditor;
import jvx.rad.genui.layout.UIBorderLayout;
import jvx.rad.genui.layout.UIFormLayout;
import jvx.rad.genui.layout.UIGridLayout;
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
 * Demonstrates the {@link UIGridLayout}.
 *
 * @author Robert Zenz
 */
public class GridLayoutSample extends AbstractSample implements ISample
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
	public IPanel getContent() throws ModelException
	{
		UIGridLayout mainLayout = new UIGridLayout(5, 5);
		
		UIPanel main = new UIPanel();
		main.setLayout(mainLayout);
		
		main.add(createCenteredLabel("0, 0", Tango.BUTTER_1), mainLayout.getConstraints(0, 0));
		main.add(createCenteredLabel("1, 0", Tango.CHAMELEON_1), mainLayout.getConstraints(1, 0));
		main.add(createCenteredLabel("2, 0", Tango.SKY_BLUE_1), mainLayout.getConstraints(2, 0));
		main.add(createCenteredLabel("3, 0", Tango.PLUM_1), mainLayout.getConstraints(3, 0));
		main.add(createCenteredLabel("4, 0", Tango.SCARLET_RED_1), mainLayout.getConstraints(4, 0));
		
		main.add(createCenteredLabel("0, 1", Tango.BUTTER_1), mainLayout.getConstraints(0, 1));
		main.add(createCenteredLabel("1, 1, 2, 1", Tango.CHAMELEON_1), mainLayout.getConstraints(1, 1, 2, 1));
		main.add(createCenteredLabel("3, 1, 1, 2", Tango.PLUM_1), mainLayout.getConstraints(3, 1, 1, 2));
		main.add(createCenteredLabel("4, 1", Tango.SCARLET_RED_1), mainLayout.getConstraints(4, 1));
		
		main.add(createCenteredLabel("0, 2, 3, 1", Tango.BUTTER_1), mainLayout.getConstraints(0, 2, 3, 1));
		main.add(createCenteredLabel("4, 2", Tango.SCARLET_RED_1), mainLayout.getConstraints(4, 2));
		
		main.add(createCenteredLabel("0, 3", Tango.BUTTER_1), mainLayout.getConstraints(0, 3));
		main.add(createCenteredLabel("1, 3", Tango.CHAMELEON_1), mainLayout.getConstraints(1, 3));
		main.add(createCenteredLabel("2, 3, 2, 1", Tango.PLUM_1), mainLayout.getConstraints(2, 3, 2, 1));
		main.add(createCenteredLabel("4, 3", Tango.SCARLET_RED_1), mainLayout.getConstraints(4, 3));
		
		main.add(createCenteredLabel("0, 4", Tango.BUTTER_1), mainLayout.getConstraints(0, 4));
		main.add(createCenteredLabel("1, 4", Tango.CHAMELEON_1), mainLayout.getConstraints(1, 4));
		main.add(createCenteredLabel("2, 4", Tango.SKY_BLUE_1), mainLayout.getConstraints(2, 4));
		main.add(createCenteredLabel("3, 4", Tango.PLUM_1), mainLayout.getConstraints(3, 4));
		main.add(createCenteredLabel("4, 4", Tango.SCARLET_RED_1), mainLayout.getConstraints(4, 4));
		
		IDataBook controlsBook = new MemDataBook();
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("HORIZONTAL_GAP", new BigDecimalDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("MARGIN_BOTTOM", new BigDecimalDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("MARGIN_LEFT", new BigDecimalDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("MARGIN_RIGHT", new BigDecimalDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("MARGIN_TOP", new BigDecimalDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("VERTICAL_GAP", new BigDecimalDataType()));
		controlsBook.setName("CONTROLS");
		controlsBook.open();
		
		controlsBook.insert(false);
		controlsBook.setValue("HORIZONTAL_GAP", BigDecimal.valueOf(mainLayout.getHorizontalGap()));
		controlsBook.setValue("MARGIN_BOTTOM", BigDecimal.valueOf(mainLayout.getMargins().getBottom()));
		controlsBook.setValue("MARGIN_LEFT", BigDecimal.valueOf(mainLayout.getMargins().getLeft()));
		controlsBook.setValue("MARGIN_RIGHT", BigDecimal.valueOf(mainLayout.getMargins().getRight()));
		controlsBook.setValue("MARGIN_TOP", BigDecimal.valueOf(mainLayout.getMargins().getTop()));
		controlsBook.setValue("VERTICAL_GAP", BigDecimal.valueOf(mainLayout.getVerticalGap()));
		
		controlsBook.eventValuesChanged().addListener(pDataRowEvent ->
		{
			IDataRow dataRow = pDataRowEvent.getChangedDataRow();
			
			mainLayout.setHorizontalGap(((BigDecimal) dataRow.getValue("HORIZONTAL_GAP")).intValue());
			mainLayout.setVerticalGap(((BigDecimal) dataRow.getValue("VERTICAL_GAP")).intValue());
			mainLayout.setMargins(new UIInsets(
					((BigDecimal) dataRow.getValue("MARGIN_TOP")).intValue(),
					((BigDecimal) dataRow.getValue("MARGIN_LEFT")).intValue(),
					((BigDecimal) dataRow.getValue("MARGIN_BOTTOM")).intValue(),
					((BigDecimal) dataRow.getValue("MARGIN_RIGHT")).intValue()));
		});
		
		UIFormLayout controlsLayout = new UIFormLayout();
		controlsLayout.setNewlineCount(4);
		
		UIPanel controls = new UIPanel();
		controls.setLayout(controlsLayout);
		controls.add(new UILabel("Margin Top"));
		controls.add(new UIEditor(controlsBook, "MARGIN_TOP"));
		controls.add(new UILabel("Margin Left"));
		controls.add(new UIEditor(controlsBook, "MARGIN_LEFT"));
		controls.add(new UILabel("Margin Bottom"));
		controls.add(new UIEditor(controlsBook, "MARGIN_BOTTOM"));
		controls.add(new UILabel("Margin Right"));
		controls.add(new UIEditor(controlsBook, "MARGIN_RIGHT"));
		controls.add(new UILabel("Horizontal Gap"));
		controls.add(new UIEditor(controlsBook, "HORIZONTAL_GAP"));
		controls.add(new UILabel("Vertical Gap"));
		controls.add(new UIEditor(controlsBook, "VERTICAL_GAP"));
		
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
		return "Grid";
	}
	
}	// GridLayoutSample
