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
package com.sibvisions.kitchensink.samples.other;

import java.util.Random;

import javax.rad.genui.IFontAwesome;
import javax.rad.genui.UIImage;
import javax.rad.genui.celleditor.UIDateCellEditor;
import javax.rad.genui.component.UILabel;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.control.UIEditor;
import javax.rad.genui.control.UITable;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.genui.menu.UIMenuItem;
import javax.rad.genui.menu.UIPopupMenu;
import javax.rad.model.ColumnDefinition;
import javax.rad.model.IDataBook;
import javax.rad.model.datatype.StringDataType;
import javax.rad.model.datatype.TimestampDataType;
import javax.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.components.NoteLabel;
import com.sibvisions.kitchensink.samples.AbstractSample;
import com.sibvisions.rad.model.mem.MemDataBook;

/**
 * A simple demonstration of the capability to attach popup menus to components.
 * 
 * @author Robert Zenz
 */
public class PopupMenuSample extends AbstractSample implements ISample
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Class members
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	private Random random = new Random(0);
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Interface implementation
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCategory()
	{
		return "Other";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPanel getContent() throws Throwable
	{
		IDataBook dataBook = new MemDataBook();
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("FIRST_NAME", new StringDataType()));
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("LAST_NAME", new StringDataType()));
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("BIRTHDAY", new TimestampDataType(new UIDateCellEditor("dd.MM.yyyy"))));
		dataBook.setName("data");
		dataBook.open();
		
		dataBook.insert(false);
		dataBook.setValues(null, new Object[] { "Cayla", "Barth", "18.02.1988" });
		dataBook.insert(false);
		dataBook.setValues(null, new Object[] { "Clyde", "Stephens", "30.06.1976" });
		dataBook.insert(false);
		dataBook.setValues(null, new Object[] { "Marcel", "Valdivia", "14.12.1980" });
		dataBook.insert(false);
		dataBook.setValues(null, new Object[] { "Andree", "Daring", "07.05.1991" });
		dataBook.insert(false);
		dataBook.setValues(null, new Object[] { "Jake", "Victorino", "01.01.2017" });
		dataBook.saveAllRows();
		
		UIPopupMenu tablePopupMenu = new UIPopupMenu();
		tablePopupMenu.add(new UIMenuItem("Add new line before", UIImage.getImage(IFontAwesome.PLUS_CIRCLE_SMALL), (pEvent) -> dataBook.insert(true)));
		tablePopupMenu.add(new UIMenuItem("Add new line after", UIImage.getImage(IFontAwesome.PLUS_CIRCLE_SMALL), (pEvent) -> dataBook.insert(false)));
		tablePopupMenu.add(new UIMenuItem("Remove current line", UIImage.getImage(IFontAwesome.MINUS_CIRCLE_SMALL), (pEvent) -> dataBook.delete()));
		tablePopupMenu.addSeparator();
		tablePopupMenu.add(new UIMenuItem("Randomize name", UIImage.getImage(IFontAwesome.FONT_SMALL), (pEvent) ->
		{
			dataBook.setValue("FIRST_NAME", getRandomString());
			dataBook.setValue("LAST_NAME", getRandomString());
		}));
		
		UITable table = new UITable(dataBook);
		table.setMinimumSize(0, 320);
		table.setPopupMenu(tablePopupMenu);
		
		UIPopupMenu firstNamePopupMenu = new UIPopupMenu();
		firstNamePopupMenu.add(new UIMenuItem("Randomize", UIImage.getImage(IFontAwesome.FONT_SMALL), (pEvent) -> dataBook.setValue("FIRST_NAME", getRandomString())));
		firstNamePopupMenu.add(new UIMenuItem("Clear", UIImage.getImage(IFontAwesome.ERASER_SMALL), (pEvent) -> dataBook.setValue("FIRST_NAME", null)));
		
		UIEditor firstNameEditor = new UIEditor(dataBook, "FIRST_NAME");
		firstNameEditor.setPopupMenu(firstNamePopupMenu);
		
		UIPopupMenu lastNamePopupMenu = new UIPopupMenu();
		lastNamePopupMenu.add(new UIMenuItem("Randomize", UIImage.getImage(IFontAwesome.FONT_SMALL), (pEvent) -> dataBook.setValue("LAST_NAME", getRandomString())));
		lastNamePopupMenu.add(new UIMenuItem("Clear", UIImage.getImage(IFontAwesome.ERASER_SMALL), (pEvent) -> dataBook.setValue("LAST_NAME", null)));
		
		UIEditor lastNameEditor = new UIEditor(dataBook, "LAST_NAME");
		lastNameEditor.setPopupMenu(lastNamePopupMenu);
		
		UIFormLayout contentLayout = new UIFormLayout();
		
		UIPanel content = new UIPanel();
		content.setLayout(contentLayout);
		content.add(new NoteLabel("Right click any of the components below to open the popup menu."), contentLayout.getConstraints(0, 0, -1, 0));
		content.add(table, contentLayout.getConstraints(0, 1, -3, 5));
		content.add(new UILabel("First Name"), contentLayout.getConstraints(-2, 2));
		content.add(firstNameEditor, contentLayout.getConstraints(-1, 2));
		content.add(new UILabel("Last Name"), contentLayout.getConstraints(-2, 3));
		content.add(lastNameEditor, contentLayout.getConstraints(-1, 3));
		content.add(new UILabel("Birthday"), contentLayout.getConstraints(-2, 4));
		content.add(new UIEditor(dataBook, "BIRTHDAY"), contentLayout.getConstraints(-1, 4));
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Popup Menu";
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a random string.
	 * 
	 * @return a random string.
	 */
	private String getRandomString()
	{
		int length = random.nextInt(5) + 4;
		byte[] bytes = new byte[length];
		
		bytes[0] = (byte)(random.nextInt(26) + 65);
		
		for (int index = 1; index < length; index++)
		{
			bytes[index] = (byte)(random.nextInt(26) + 97);
		}
		
		return new String(bytes);
	}
	
}	// CaptureSample
