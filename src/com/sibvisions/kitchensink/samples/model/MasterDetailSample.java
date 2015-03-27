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

import javax.rad.genui.container.UIPanel;
import javax.rad.genui.control.UITable;
import javax.rad.genui.control.UITree;
import javax.rad.genui.layout.UIGridLayout;
import javax.rad.model.ColumnDefinition;
import javax.rad.model.IDataBook;
import javax.rad.model.ModelException;
import javax.rad.model.datatype.BigDecimalDataType;
import javax.rad.model.datatype.StringDataType;
import javax.rad.model.reference.ReferenceDefinition;
import javax.rad.ui.container.IPanel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.samples.AbstractSample;
import com.sibvisions.rad.model.mem.MemDataBook;

/**
 * Demonstrates the Master-Detail capabilities of JVx.
 * 
 * @author Robert Zenz
 */
public class MasterDetailSample extends AbstractSample implements ISample
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
		masterDataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("NAME", new StringDataType()));
		masterDataBook.setName("MASTER");
		masterDataBook.open();
		
		IDataBook detailDataBook = new MemDataBook();
		detailDataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("ID", new BigDecimalDataType()));
		detailDataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("MASTER_ID", new BigDecimalDataType()));
		detailDataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("CATEGORY", new StringDataType()));
		detailDataBook.setMasterReference(new ReferenceDefinition(new String[] { "MASTER_ID" }, masterDataBook, new String[] { "ID" }));
		detailDataBook.setName("DETAIL");
		detailDataBook.open();
		
		IDataBook detailDetailDataBook = new MemDataBook();
		detailDetailDataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("ID", new BigDecimalDataType()));
		detailDetailDataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("DETAIL_ID", new BigDecimalDataType()));
		detailDetailDataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("ITEM", new StringDataType()));
		detailDetailDataBook.setMasterReference(new ReferenceDefinition(new String[] { "DETAIL_ID" }, detailDataBook, new String[] { "ID" }));
		detailDetailDataBook.setName("DETAILDETAIL");
		detailDetailDataBook.open();
		
		insertData(masterDataBook, detailDataBook, detailDetailDataBook,
				"Plants",
				new String[] { "Green", "Colorful", "Boring" },
				new String[][] {
						new String[] { "Grass", "Seatang", "Moss" },
						new String[] { "Flowers" },
						new String[] { "Trees", "Bushes", "Grass" }
				});
		
		insertData(masterDataBook, detailDataBook, detailDetailDataBook,
				"Metals",
				new String[] { "Shiny", "Really shiny", "Boring" },
				new String[][] {
						new String[] { "Gold", "Silver" },
						new String[] { "Plutonium", "Uranium", "Thorium" },
						new String[] { "Zinc", "Copper" }
				});
		
		insertData(masterDataBook, detailDataBook, detailDetailDataBook,
				"Objects",
				new String[] { "Round", "Not round", "Boring" },
				new String[][] {
						new String[] { "Balls", "Marbles", "Globes" },
						new String[] { "Cubes", "Different cubes" },
						new String[] { "Nails", "Ladders", "Cats" }
				});
		
		masterDataBook.saveAllRows();
		masterDataBook.setSelectedRow(0);
		
		UITable masterTable = new UITable(masterDataBook);
		masterTable.setEditable(false);
		
		UITable detailTable = new UITable(detailDataBook);
		detailTable.setEditable(false);
		
		UITable detailDetailTable = new UITable(detailDetailDataBook);
		detailDetailTable.setEditable(false);
		
		UITree tree = new UITree();
		tree.setDataBooks(masterDataBook, detailDataBook, detailDetailDataBook);
		
		UIGridLayout contentLayout = new UIGridLayout(3, 1);
		
		UIPanel content = new UIPanel();
		content.setLayout(contentLayout);
		content.add(masterTable, contentLayout.getConstraints(0, 0));
		content.add(detailTable, contentLayout.getConstraints(1, 0));
		content.add(detailDetailTable, contentLayout.getConstraints(2, 0));
		content.add(tree, contentLayout.getConstraints(3, 0));
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Master Detail";
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Inserts the given data into the Master-Detail-Detail databook.
	 *
	 * @param pMasterDataBook the master data book.
	 * @param pDetailDataBook the detail data book.
	 * @param pDetailDetailDataBook the detail detail data book.
	 * @param pMasterData the master data.
	 * @param pDetailData the detail data.
	 * @param pDetailDetailData the detail detail data.
	 * @throws ModelException the model exception
	 */
	private void insertData(IDataBook pMasterDataBook, IDataBook pDetailDataBook, IDataBook pDetailDetailDataBook, String pMasterData, String[] pDetailData,
			String[][] pDetailDetailData) throws ModelException
	{
		BigDecimal masterId = BigDecimal.valueOf(pMasterDataBook.getRowCount() + 1);
		
		pMasterDataBook.insert(false);
		pMasterDataBook.setValues(null, new Object[] { masterId, pMasterData });
		
		for (int detailCounter = 0; detailCounter < pDetailData.length; detailCounter++)
		{
			String detailData = pDetailData[detailCounter];
			
			BigDecimal detailId = BigDecimal.valueOf(masterId.intValue() * 10 + pDetailDataBook.getRowCount() + 1);
			
			pDetailDataBook.insert(false);
			pDetailDataBook.setValues(null, new Object[] { detailId, masterId, detailData });
			
			for (String detailDetailData : pDetailDetailData[detailCounter])
			{
				BigDecimal detailDetailId = BigDecimal.valueOf(detailId.intValue() * 100 + pDetailDetailDataBook.getRowCount() + 1);
				
				pDetailDetailDataBook.insert(false);
				pDetailDetailDataBook.setValues(null, new Object[] { detailDetailId, detailId, detailDetailData });
			}
			
		}
	}
	
}	// MasterDetailSample
