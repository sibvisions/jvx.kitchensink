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

import javax.rad.genui.UIColor;
import javax.rad.genui.UIFont;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.control.UICellFormat;
import javax.rad.genui.control.UITable;
import javax.rad.genui.layout.UIBorderLayout;
import javax.rad.model.ColumnDefinition;
import javax.rad.model.IDataBook;
import javax.rad.model.IDataPage;
import javax.rad.model.IDataRow;
import javax.rad.ui.IColor;
import javax.rad.ui.IFont;
import javax.rad.ui.container.IPanel;
import javax.rad.ui.control.ICellFormat;
import javax.rad.ui.control.ICellFormatter;

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
			dataBook.setValues(null, new Object[] { "Something", "Something", "Something" });
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
			IColor backgroundColor = UIColor.white;
			IColor foregroundColor = UIColor.black;
			
			if (pRow % 2 == 0)
			{
				backgroundColor = Tango.ALUMINIUM_6;
				foregroundColor = Tango.ALUMINIUM_1;
			}
			else
			{
				backgroundColor = Tango.ALUMINIUM_1;
				foregroundColor = Tango.ALUMINIUM_6;
			}
			
			return new UICellFormat(backgroundColor, foregroundColor, new UIFont("Courier New", IFont.BOLD, 12), null, 10);
		}
		
	}	// CellFormatter
	
}	// StyledTableSample
