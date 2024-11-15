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
package com.sibvisions.kitchensink.samples.model;

import jvx.rad.genui.UIFont;
import jvx.rad.genui.UIImage;
import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.control.UICellFormat;
import jvx.rad.genui.control.UITable;
import jvx.rad.genui.layout.UIBorderLayout;
import jvx.rad.model.ColumnDefinition;
import jvx.rad.model.IDataBook;
import jvx.rad.model.IDataPage;
import jvx.rad.model.IDataRow;
import jvx.rad.ui.IFont;
import jvx.rad.ui.container.IPanel;
import jvx.rad.ui.control.ICellFormat;
import jvx.rad.ui.control.ICellFormatter;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;
import com.sibvisions.rad.model.mem.MemDataBook;

/**
 * Demonstrates style capabilities of the table.
 * 
 * @author Robert Zenz
 */
public class StyledTableSample implements ISample
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Constants
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** A {@link UICellFormat} with a black background. */
	private static final UICellFormat BLACK_FORMAT = new UICellFormat(
			Tango.ALUMINIUM_6,
			Tango.ALUMINIUM_1,
			new UIFont("Courier New", IFont.PLAIN, 10),
			UIImage.getImage(UIImage.SAVE_ALL_LARGE),
			6);
	
	/** A {@link UICellFormat} with a white background and white foreground. */
	private static final UICellFormat BLUE_FORMAT = new UICellFormat(
			Tango.ALUMINIUM_1,
			Tango.SKY_BLUE_3,
			new UIFont("Verdana", IFont.ITALIC, 8),
			UIImage.getImage(UIImage.OK_LARGE),
			6);
	
	/** A {@link UICellFormat} with a white background. */
	private static final UICellFormat WHITE_FORMAT = new UICellFormat(
			Tango.ALUMINIUM_1,
			Tango.ALUMINIUM_6,
			new UIFont("Courier New", IFont.BOLD, 12),
			UIImage.getImage(UIImage.EDIT_LARGE),
			12);
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Interface implementation
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getCategory()
	{
		return "Model";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPanel getContent() throws Throwable
	{
		IDataBook dataBook = new MemDataBook();
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("FIRST"));
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("SECOND"));
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("THIRD"));
		dataBook.setName("STYLE");
		dataBook.open();
		
		for (int counter = 0; counter < 17; counter++)
		{
			dataBook.insert(false);
			dataBook.setValues(null, new Object[] { "Something", "Another column", "More values" });
		}
		
		dataBook.saveAllRows();
		
		UITable table = new UITable();
		table.setDataBook(dataBook);
		table.setCellFormatter(new CellFormatter());
		
		UIPanel content = new UIPanel();
		content.setLayout(new UIBorderLayout());
		content.add(table);
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Styled Table";
	}
	
	//****************************************************************
	// Subclass definition
	//****************************************************************
	
	/**
	 * The {@link ICellFormatter} for this example.
	 * 
	 * @author Robert Zenz
	 */
	private final class CellFormatter implements ICellFormatter
	{
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Interface implementation
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public ICellFormat getCellFormat(IDataBook pDataBook, IDataPage pDataPage, IDataRow pDataRow, String pColumnName, int pRow, int pColumn)
		{
			switch (pRow % 3)
			{
				case 0:
					return WHITE_FORMAT;
				
				case 1:
					return BLACK_FORMAT;
				
				case 2:
					return BLUE_FORMAT;
				
			}
			
			return null;
		}
		
	}	// CellFormatter
	
}	// StyledTableSample
