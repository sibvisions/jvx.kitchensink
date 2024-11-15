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
import jvx.rad.genui.container.UISplitPanel;
import jvx.rad.genui.control.UIEditor;
import jvx.rad.genui.layout.UIBorderLayout;
import jvx.rad.genui.layout.UIFormLayout;
import jvx.rad.model.ColumnDefinition;
import jvx.rad.model.IDataBook;
import jvx.rad.model.IDataRow;
import jvx.rad.model.ModelException;
import jvx.rad.model.datatype.BigDecimalDataType;
import jvx.rad.ui.container.IPanel;
import jvx.rad.ui.container.ISplitPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.kitchensink.samples.AbstractSample;
import com.sibvisions.rad.model.mem.MemDataBook;

/**
 * Demonstrates the {@link UIFormLayout}.
 *
 * @author Robert Zenz
 */
public class FormLayoutSample extends AbstractSample implements ISample
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
		UISplitPanel splitter = new UISplitPanel(ISplitPanel.SPLIT_TOP_BOTTOM);
		
		UIFormLayout constraintBasedLayout = new UIFormLayout();
		
		UIPanel constraintBased = new UIPanel();
		constraintBased.setLayout(constraintBasedLayout);
		
		constraintBased.add(createCenteredLabel("0, 0", Tango.BUTTER_1), constraintBasedLayout.getConstraints(0, 0));
		constraintBased.add(createCenteredLabel("1, 0", Tango.CHAMELEON_1), constraintBasedLayout.getConstraints(1, 0));
		constraintBased.add(createCenteredLabel("2, 0", Tango.SKY_BLUE_1), constraintBasedLayout.getConstraints(2, 0));
		constraintBased.add(createCenteredLabel("0, 1", Tango.PLUM_1), constraintBasedLayout.getConstraints(0, 1));
		constraintBased.add(createCenteredLabel("0, 2", Tango.SCARLET_RED_1), constraintBasedLayout.getConstraints(0, 2));
		
		constraintBased.add(createCenteredLabel("-1, 0", Tango.BUTTER_1), constraintBasedLayout.getConstraints(-1, 0));
		constraintBased.add(createCenteredLabel("-2, 0", Tango.CHAMELEON_1), constraintBasedLayout.getConstraints(-2, 0));
		constraintBased.add(createCenteredLabel("-3, 0", Tango.SKY_BLUE_1), constraintBasedLayout.getConstraints(-3, 0));
		constraintBased.add(createCenteredLabel("-1, 1", Tango.PLUM_1), constraintBasedLayout.getConstraints(-1, 1));
		constraintBased.add(createCenteredLabel("-1, 2", Tango.SCARLET_RED_1), constraintBasedLayout.getConstraints(-1, 2));
		
		constraintBased.add(createCenteredLabel("0, -1", Tango.BUTTER_1), constraintBasedLayout.getConstraints(0, -1));
		constraintBased.add(createCenteredLabel("1, -1", Tango.CHAMELEON_1), constraintBasedLayout.getConstraints(1, -1));
		constraintBased.add(createCenteredLabel("2, -1", Tango.SKY_BLUE_1), constraintBasedLayout.getConstraints(2, -1));
		constraintBased.add(createCenteredLabel("0, -2", Tango.PLUM_1), constraintBasedLayout.getConstraints(0, -2));
		constraintBased.add(createCenteredLabel("0, -3", Tango.SCARLET_RED_1), constraintBasedLayout.getConstraints(0, -3));
		
		constraintBased.add(createCenteredLabel("-1, -1", Tango.BUTTER_1), constraintBasedLayout.getConstraints(-1, -1));
		constraintBased.add(createCenteredLabel("-2, -1", Tango.CHAMELEON_1), constraintBasedLayout.getConstraints(-2, -1));
		constraintBased.add(createCenteredLabel("-3, -1", Tango.SKY_BLUE_1), constraintBasedLayout.getConstraints(-3, -1));
		constraintBased.add(createCenteredLabel("-1, -2", Tango.PLUM_1), constraintBasedLayout.getConstraints(-1, -2));
		constraintBased.add(createCenteredLabel("-1, -3", Tango.SCARLET_RED_1), constraintBasedLayout.getConstraints(-1, -3));
		
		constraintBased.add(createCenteredLabel("2, 2, -2, -2", Tango.BUTTER_1), constraintBasedLayout.getConstraints(1, 1, -2, -2));
		
		UIFormLayout lineBasedLayout = new UIFormLayout();
		lineBasedLayout.setNewlineCount(5);
		
		UIPanel lineBased = new UIPanel();
		lineBased.setLayout(lineBasedLayout);
		
		lineBased.add(createCenteredLabel("0, 0", Tango.BUTTER_1));
		lineBased.add(createCenteredLabel("1, 0", Tango.CHAMELEON_1));
		lineBased.add(createCenteredLabel("2, 0", Tango.SKY_BLUE_1));
		lineBased.add(createCenteredLabel("3, 0", Tango.PLUM_1));
		lineBased.add(createCenteredLabel("4, 0", Tango.SCARLET_RED_1));
		
		lineBased.add(createCenteredLabel("0, 1", Tango.BUTTER_1));
		lineBased.add(createCenteredLabel("1, 1", Tango.CHAMELEON_1));
		lineBased.add(createCenteredLabel("2, 1", Tango.SKY_BLUE_1));
		lineBased.add(createCenteredLabel("3, 1", Tango.PLUM_1));
		lineBased.add(createCenteredLabel("4, 1", Tango.SCARLET_RED_1));
		
		lineBased.add(createCenteredLabel("0, 2", Tango.BUTTER_1));
		lineBased.add(createCenteredLabel("1, 2", Tango.CHAMELEON_1));
		lineBased.add(createCenteredLabel("2, 2", Tango.SKY_BLUE_1));
		lineBased.add(createCenteredLabel("3, 2", Tango.PLUM_1));
		lineBased.add(createCenteredLabel("4, 2", Tango.SCARLET_RED_1));
		
		lineBased.add(createCenteredLabel("0, 3", Tango.BUTTER_1));
		lineBased.add(createCenteredLabel("1, 3", Tango.CHAMELEON_1));
		lineBased.add(createCenteredLabel("2, 3", Tango.SKY_BLUE_1));
		lineBased.add(createCenteredLabel("3, 3", Tango.PLUM_1));
		lineBased.add(createCenteredLabel("4, 3", Tango.SCARLET_RED_1));
		
		splitter.setFirstComponent(constraintBased);
		splitter.setSecondComponent(lineBased);
		
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
		controlsBook.setValue("HORIZONTAL_GAP", BigDecimal.valueOf(constraintBasedLayout.getHorizontalGap()));
		controlsBook.setValue("MARGIN_BOTTOM", BigDecimal.valueOf(constraintBasedLayout.getMargins().getBottom()));
		controlsBook.setValue("MARGIN_LEFT", BigDecimal.valueOf(constraintBasedLayout.getMargins().getLeft()));
		controlsBook.setValue("MARGIN_RIGHT", BigDecimal.valueOf(constraintBasedLayout.getMargins().getRight()));
		controlsBook.setValue("MARGIN_TOP", BigDecimal.valueOf(constraintBasedLayout.getMargins().getTop()));
		controlsBook.setValue("VERTICAL_GAP", BigDecimal.valueOf(constraintBasedLayout.getVerticalGap()));
		
		controlsBook.eventValuesChanged().addListener(pDataRowEvent ->
		{
			IDataRow dataRow = pDataRowEvent.getChangedDataRow();
			
			constraintBasedLayout.setHorizontalGap(((BigDecimal) dataRow.getValue("HORIZONTAL_GAP")).intValue());
			constraintBasedLayout.setVerticalGap(((BigDecimal) dataRow.getValue("VERTICAL_GAP")).intValue());
			constraintBasedLayout.setMargins(new UIInsets(
					((BigDecimal) dataRow.getValue("MARGIN_TOP")).intValue(),
					((BigDecimal) dataRow.getValue("MARGIN_LEFT")).intValue(),
					((BigDecimal) dataRow.getValue("MARGIN_BOTTOM")).intValue(),
					((BigDecimal) dataRow.getValue("MARGIN_RIGHT")).intValue()));
			
			lineBasedLayout.setHorizontalGap(((BigDecimal) dataRow.getValue("HORIZONTAL_GAP")).intValue());
			lineBasedLayout.setVerticalGap(((BigDecimal) dataRow.getValue("VERTICAL_GAP")).intValue());
			lineBasedLayout.setMargins(new UIInsets(
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
		content.add(splitter, UIBorderLayout.CENTER);
		content.add(controls, UIBorderLayout.SOUTH);
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Form";
	}
	
}	// FormLayoutSample
