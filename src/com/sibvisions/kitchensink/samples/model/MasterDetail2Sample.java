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

import java.math.BigDecimal;

import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.container.UISplitPanel;
import jvx.rad.genui.control.UITable;
import jvx.rad.genui.control.UITree;
import jvx.rad.genui.layout.UIBorderLayout;
import jvx.rad.model.ColumnDefinition;
import jvx.rad.model.IDataBook;
import jvx.rad.model.ModelException;
import jvx.rad.model.SortDefinition;
import jvx.rad.model.datatype.BigDecimalDataType;
import jvx.rad.model.datatype.StringDataType;
import jvx.rad.model.reference.ReferenceDefinition;
import jvx.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.samples.AbstractSample;
import com.sibvisions.rad.model.mem.MemDataBook;

/**
 * Demonstrates the Master-Detail capabilities of JVx.
 * 
 * @author Robert Zenz
 */
public class MasterDetail2Sample extends AbstractSample implements ISample
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
		
		IDataBook masterDataBook = new MemDataBook();
		masterDataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("ID", new BigDecimalDataType()));
		masterDataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("PARENT_ID", new BigDecimalDataType()));
		masterDataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("NAME", new StringDataType()));
		masterDataBook.setMasterReference(new ReferenceDefinition(new String[] { "PARENT_ID" }, masterDataBook, new String[] { "ID" }));
		masterDataBook.setName("MASTER");
		masterDataBook.setSort(new SortDefinition("ID"));
		masterDataBook.open();
		
		IDataBook detailDataBook = new MemDataBook();
		detailDataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("ID", new BigDecimalDataType()));
		detailDataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("MASTER_ID", new BigDecimalDataType()));
		detailDataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("NAME", new StringDataType()));
		detailDataBook.setMasterReference(new ReferenceDefinition(new String[] { "MASTER_ID" }, masterDataBook, new String[] { "ID" }));
		detailDataBook.setName("DETAIL");
		detailDataBook.open();
		
		insert(masterDataBook, BigDecimal.valueOf(1), null, "Hardware");
		insert(masterDataBook, BigDecimal.valueOf(2), BigDecimal.valueOf(1), "CPU");
		insert(masterDataBook, BigDecimal.valueOf(3), BigDecimal.valueOf(1), "RAM");
		insert(masterDataBook, BigDecimal.valueOf(4), BigDecimal.valueOf(1), "Storage");
		insert(masterDataBook, BigDecimal.valueOf(5), null, "Software");
		insert(masterDataBook, BigDecimal.valueOf(6), BigDecimal.valueOf(5), "Useful");
		insert(masterDataBook, BigDecimal.valueOf(7), BigDecimal.valueOf(5), "Not useful");
		masterDataBook.saveAllRows();
		
		insert(detailDataBook, BigDecimal.valueOf(1), BigDecimal.valueOf(2), "Fast");
		insert(detailDataBook, BigDecimal.valueOf(2), BigDecimal.valueOf(2), "Not so fast");
		insert(detailDataBook, BigDecimal.valueOf(3), BigDecimal.valueOf(2), "Slow");
		insert(detailDataBook, BigDecimal.valueOf(4), BigDecimal.valueOf(3), "Lots");
		insert(detailDataBook, BigDecimal.valueOf(5), BigDecimal.valueOf(3), "Really lots");
		insert(detailDataBook, BigDecimal.valueOf(6), BigDecimal.valueOf(3), "Few");
		insert(detailDataBook, BigDecimal.valueOf(7), BigDecimal.valueOf(4), "Spinning");
		insert(detailDataBook, BigDecimal.valueOf(8), BigDecimal.valueOf(4), "Not-Spinning");
		insert(detailDataBook, BigDecimal.valueOf(9), BigDecimal.valueOf(4), "Floating");
		insert(detailDataBook, BigDecimal.valueOf(10), BigDecimal.valueOf(6), "Java");
		insert(detailDataBook, BigDecimal.valueOf(11), BigDecimal.valueOf(6), "JVx");
		insert(detailDataBook, BigDecimal.valueOf(12), BigDecimal.valueOf(6), "VisionX");
		insert(detailDataBook, BigDecimal.valueOf(13), BigDecimal.valueOf(7), "sl");
		insert(detailDataBook, BigDecimal.valueOf(14), BigDecimal.valueOf(7), "gti");
		insert(detailDataBook, BigDecimal.valueOf(15), BigDecimal.valueOf(7), "Solitaire");
		detailDataBook.saveAllRows();
		
		UITree tree = new UITree();
		tree.setDataBooks(masterDataBook);
		
		UITable table = new UITable(detailDataBook);
		table.setEditable(false);
		
		UISplitPanel container = new UISplitPanel();
		container.add(tree, UISplitPanel.FIRST_COMPONENT);
		container.add(table, UISplitPanel.SECOND_COMPONENT);
		
		UIPanel content = new UIPanel();
		content.setLayout(new UIBorderLayout());
		content.add(container, UIBorderLayout.CENTER);
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Master Detail 2";
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Inserts the given data into the given {@link IDataBook}.
	 * 
	 * @param pDataBook the {@link IDataBook}.
	 * @param pValues the values.
	 * @throws ModelException if inserting failed.
	 */
	private void insert(IDataBook pDataBook, Object... pValues) throws ModelException
	{
		pDataBook.insert(false);
		pDataBook.setValues(null, pValues);
	}
	
}	// MasterDetailSample
