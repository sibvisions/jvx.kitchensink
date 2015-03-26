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
import javax.rad.genui.control.UITree;
import javax.rad.genui.layout.UIBorderLayout;
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
public class SelfJoinedSample extends AbstractSample implements ISample
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Class members
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * The counter used to keep track of how many rows we've already inserted.
	 * <p/>
	 * Note that this is only a quick and dirty way to keep track of the number
	 * of inserted rows, as it simplifies the inserting code (further down)
	 * a little.
	 */
	private int rowCounter = 0;
	
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
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("ID", new BigDecimalDataType()));
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("PARENT_ID", new BigDecimalDataType()));
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("NAME", new StringDataType()));
		dataBook.setMasterReference(new ReferenceDefinition(new String[] { "PARENT_ID" }, dataBook, new String[] { "ID" }));
		dataBook.setName("MASTER");
		dataBook.open();
		
		int planetsId = insertRow(dataBook, "Planets", 0);
		int asteroidsId = insertRow(dataBook, "Asteroids", 0);
		int otherId = insertRow(dataBook, "Other Stuff", 0);
		
		// Planets
		insertRow(dataBook, "Mercury", planetsId);
		insertRow(dataBook, "Venus", planetsId);
		int earthId = insertRow(dataBook, "Earth", planetsId);
		insertRow(dataBook, "Mars", planetsId);
		insertRow(dataBook, "Jupiter", planetsId);
		insertRow(dataBook, "Saturn", planetsId);
		insertRow(dataBook, "Uranus", planetsId);
		insertRow(dataBook, "Neptune", planetsId);
		
		// Earths continents.
		insertRow(dataBook, "North-America", earthId);
		insertRow(dataBook, "South-America", earthId);
		insertRow(dataBook, "Europe", earthId);
		insertRow(dataBook, "Asia", earthId);
		insertRow(dataBook, "Africa", earthId);
		insertRow(dataBook, "Australia", earthId);
		insertRow(dataBook, "Antarctica", earthId);
		
		// Asteroids
		insertRow(dataBook, "1 Ceres", asteroidsId);
		insertRow(dataBook, "2 Pallas", asteroidsId);
		insertRow(dataBook, "4 Vesta", asteroidsId);
		insertRow(dataBook, "10 Hygiea", asteroidsId);
		insertRow(dataBook, "704 Interamnia", asteroidsId);
		insertRow(dataBook, "52 Europa", asteroidsId);
		insertRow(dataBook, "511 Davida", asteroidsId);
		insertRow(dataBook, "87 Sylvia", asteroidsId);
		insertRow(dataBook, "65 Cybele", asteroidsId);
		insertRow(dataBook, "15 Eunomia", asteroidsId);
		insertRow(dataBook, "3 Juno", asteroidsId);
		insertRow(dataBook, "31 Euphrosyne", asteroidsId);
		insertRow(dataBook, "624 Hektor", asteroidsId);
		insertRow(dataBook, "88 Thisbe", asteroidsId);
		insertRow(dataBook, "324 Bamberga", asteroidsId);
		insertRow(dataBook, "451 Patientia", asteroidsId);
		insertRow(dataBook, "532 Herculina", asteroidsId);
		insertRow(dataBook, "48 Doris", asteroidsId);
		insertRow(dataBook, "375 Ursula", asteroidsId);
		insertRow(dataBook, "107 Camilla", asteroidsId);
		insertRow(dataBook, "45 Eugenia", asteroidsId);
		insertRow(dataBook, "7 Iris", asteroidsId);
		insertRow(dataBook, "29 Amphitrite", asteroidsId);
		insertRow(dataBook, "423 Diotima", asteroidsId);
		insertRow(dataBook, "19 Fortuna", asteroidsId);
		insertRow(dataBook, "13 Egeria", asteroidsId);
		insertRow(dataBook, "24 Themis", asteroidsId);
		insertRow(dataBook, "94 Aurora", asteroidsId);
		insertRow(dataBook, "702 Alauda", asteroidsId);
		insertRow(dataBook, "121 Hermione", asteroidsId);
		insertRow(dataBook, "372 Palma", asteroidsId);
		insertRow(dataBook, "128 Nemesis", asteroidsId);
		insertRow(dataBook, "6 Hebe", asteroidsId);
		insertRow(dataBook, "16 Psyche", asteroidsId);
		insertRow(dataBook, "120 Lachesis", asteroidsId);
		insertRow(dataBook, "41 Daphne", asteroidsId);
		insertRow(dataBook, "9 Metis", asteroidsId);
		
		// Other stuff floating out there
		insertRow(dataBook, "Pluto", otherId);
		
		dataBook.saveAllRows();
		dataBook.setSelectedRow(0);
		
		UITree tree = new UITree();
		tree.setDataBooks(dataBook);
		
		UIPanel content = new UIPanel();
		content.setLayout(new UIBorderLayout());
		content.add(tree, UIBorderLayout.CENTER);
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Self-Joined";
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Inserts one row into the given {@link IDataBook}.
	 * 
	 * @param pDataBook the {@link IDataBook}.
	 * @param pValue the value to insert.
	 * @param pParentId the id of the parent row.
	 * @return the id of the inserted row.
	 * @throws ModelException if inserting fails.
	 */
	private int insertRow(IDataBook pDataBook, String pValue, int pParentId) throws ModelException
	{
		BigDecimal parentId = null;
		if (pParentId > 0)
		{
			parentId = BigDecimal.valueOf(pParentId);
		}
		
		BigDecimal id = BigDecimal.valueOf(++rowCounter);
		
		pDataBook.insert(false);
		pDataBook.setValues(null, new Object[] { id, parentId, pValue });
		
		return id.intValue();
	}
	
}	// MasterDetailSample
