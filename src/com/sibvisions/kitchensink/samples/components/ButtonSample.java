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

import javax.rad.genui.UIDimension;
import javax.rad.genui.UIImage;
import javax.rad.genui.UIInsets;
import javax.rad.genui.component.UIButton;
import javax.rad.genui.component.UILabel;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.control.UIEditor;
import javax.rad.genui.layout.UIBorderLayout;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.model.ColumnDefinition;
import javax.rad.model.IDataBook;
import javax.rad.model.IDataRow;
import javax.rad.model.datatype.BigDecimalDataType;
import javax.rad.model.datatype.BooleanDataType;
import javax.rad.ui.component.IButton;
import javax.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.samples.AbstractSample;
import com.sibvisions.rad.model.mem.MemDataBook;

/**
 * Demonstrates the capabilities of the {@link UIButton}.
 * 
 * @author Robert Zenz
 */
public class ButtonSample extends AbstractSample implements ISample
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
		UILabel label = new UILabel("You haven't clicked the button.");
		
		IButton button = createButtonComponent();
		button.eventAction().addListener((pActionEvent) ->
		{
			label.setText("You clicked the button!");
		});
		button.setPreferredSize(new UIDimension(128, 64));
		
		UIFormLayout mainLayout = new UIFormLayout();
		
		UIPanel main = new UIPanel();
		main.setLayout(mainLayout);
		main.add(button);
		main.add(label);
		
		IDataBook controlsBook = new MemDataBook();
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("BORDER", new BooleanDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("BORDER_MOUSE_ENTERED", new BooleanDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("DEFAULT", new BooleanDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("HORIZONTAL_TEXT_ALIGNMENT", new BigDecimalDataType(createHorizontalAlignmentCellEditor())));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("IMAGE_TEXT_GAP", new BigDecimalDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("IMAGES", new BooleanDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("MARGIN_BOTTOM", new BigDecimalDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("MARGIN_LEFT", new BigDecimalDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("MARGIN_RIGHT", new BigDecimalDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("MARGIN_TOP", new BigDecimalDataType()));
		controlsBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("VERTICAL_TEXT_ALIGNMENT", new BigDecimalDataType(createVerticalAlignmentCellEditor())));
		controlsBook.setName("CONTROLS");
		controlsBook.open();
		
		controlsBook.insert(false);
		controlsBook.setValue("BORDER", Boolean.valueOf(button.isBorderPainted()));
		controlsBook.setValue("BORDER_MOUSE_ENTERED", Boolean.valueOf(button.isBorderOnMouseEntered()));
		controlsBook.setValue("DEFAULT", Boolean.valueOf(button.isDefaultButton()));
		controlsBook.setValue("HORIZONTAL_TEXT_ALIGNMENT", BigDecimal.valueOf(button.getHorizontalTextPosition()));
		controlsBook.setValue("IMAGE_TEXT_GAP", BigDecimal.valueOf(button.getImageTextGap()));
		controlsBook.setValue("IMAGES", Boolean.FALSE);
		controlsBook.setValue("MARGIN_BOTTOM", BigDecimal.valueOf(button.getMargins().getBottom()));
		controlsBook.setValue("MARGIN_LEFT", BigDecimal.valueOf(button.getMargins().getLeft()));
		controlsBook.setValue("MARGIN_RIGHT", BigDecimal.valueOf(button.getMargins().getRight()));
		controlsBook.setValue("MARGIN_TOP", BigDecimal.valueOf(button.getMargins().getTop()));
		controlsBook.setValue("VERTICAL_TEXT_ALIGNMENT", BigDecimal.valueOf(button.getVerticalTextPosition()));
		
		controlsBook.eventValuesChanged().addListener(pDataRowEvent ->
		{
			IDataRow dataRow = pDataRowEvent.getChangedDataRow();
			
			button.setBorderPainted(((Boolean)dataRow.getValue("BORDER")).booleanValue());
			button.setBorderOnMouseEntered(((Boolean)dataRow.getValue("BORDER_MOUSE_ENTERED")).booleanValue());
			button.setDefaultButton(((Boolean)dataRow.getValue("DEFAULT")).booleanValue());
			button.setHorizontalTextPosition(((BigDecimal)dataRow.getValue("HORIZONTAL_TEXT_ALIGNMENT")).intValue());
			button.setImageTextGap(((BigDecimal)dataRow.getValue("IMAGE_TEXT_GAP")).intValue());
			button.setVerticalTextPosition(((BigDecimal)dataRow.getValue("VERTICAL_TEXT_ALIGNMENT")).intValue());
			
			if (((Boolean)dataRow.getValue("IMAGES")).booleanValue())
			{
				button.setImage(UIImage.getImage("/com/sibvisions/kitchensink/images/weather-clear.png"));
				button.setMouseOverImage(UIImage.getImage("/com/sibvisions/kitchensink/images/weather-few-clouds.png"));
				button.setPressedImage(UIImage.getImage("/com/sibvisions/kitchensink/images/weather-overcast.png"));
			}
			else
			{
				button.setImage(null);
				button.setMouseOverImage(null);
				button.setPressedImage(null);
			}
			
			button.setMargins(new UIInsets(
					((BigDecimal)dataRow.getValue("MARGIN_TOP")).intValue(),
					((BigDecimal)dataRow.getValue("MARGIN_LEFT")).intValue(),
					((BigDecimal)dataRow.getValue("MARGIN_BOTTOM")).intValue(),
					((BigDecimal)dataRow.getValue("MARGIN_RIGHT")).intValue()));
		});
		
		UIFormLayout controlsLayout = new UIFormLayout();
		controlsLayout.setNewlineCount(4);
		
		UIPanel controls = new UIPanel();
		controls.setLayout(controlsLayout);
		controls.add(new UILabel("Is Default"));
		controls.add(new UIEditor(controlsBook, "DEFAULT"));
		controls.add(new UILabel("Image-Text Gap"));
		controls.add(new UIEditor(controlsBook, "IMAGE_TEXT_GAP"));
		
		controls.add(new UILabel("Horizontal Text Position"));
		controls.add(new UIEditor(controlsBook, "HORIZONTAL_TEXT_ALIGNMENT"));
		controls.add(new UILabel("Vertical Text Position"));
		controls.add(new UIEditor(controlsBook, "VERTICAL_TEXT_ALIGNMENT"));
		
		controls.add(new UILabel("Border"));
		controls.add(new UIEditor(controlsBook, "BORDER"));
		controls.add(new UILabel("Border Only on Mouse Entered"));
		controls.add(new UIEditor(controlsBook, "BORDER_MOUSE_ENTERED"));
		
		controls.add(new UILabel("Margin Top"));
		controls.add(new UIEditor(controlsBook, "MARGIN_TOP"));
		controls.add(new UILabel("Margin Left"));
		controls.add(new UIEditor(controlsBook, "MARGIN_LEFT"));
		
		controls.add(new UILabel("Margin Bottom"));
		controls.add(new UIEditor(controlsBook, "MARGIN_BOTTOM"));
		controls.add(new UILabel("Margin Right"));
		controls.add(new UIEditor(controlsBook, "MARGIN_RIGHT"));
		
		controls.add(new UILabel("Display Images"));
		controls.add(new UIEditor(controlsBook, "IMAGES"));
		
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
		return "Button";
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates the {@link IButton} component.
	 *
	 * @return the {@link IButton}.
	 */
	protected IButton createButtonComponent()
	{
		return new UIButton("The Button");
	}
	
}	// ButtonSample
