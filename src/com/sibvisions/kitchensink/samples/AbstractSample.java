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
package com.sibvisions.kitchensink.samples;

import java.math.BigDecimal;
import java.util.Date;

import javax.rad.genui.UIColor;
import javax.rad.genui.UIImage;
import javax.rad.genui.component.UILabel;
import javax.rad.model.ColumnDefinition;
import javax.rad.model.IDataBook;
import javax.rad.model.ModelException;
import javax.rad.model.datatype.StringDataType;
import javax.rad.model.datatype.TimestampDataType;
import javax.rad.model.ui.ICellEditor;
import javax.rad.ui.IAlignmentConstants;
import javax.rad.ui.IColor;
import javax.rad.ui.IImage;
import javax.rad.ui.component.ILabel;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.rad.genui.celleditor.UIEnumCellEditor;
import com.sibvisions.rad.model.mem.MemDataBook;

/**
 * The {@link AbstractSample} is a an abstract extension of {@link ISample} and
 * provides a few convenience methods.
 * 
 * @author Robert Zenz
 */
public abstract class AbstractSample implements ISample
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates an {@link UILabel} which has the text centered.
	 *
	 * @param pText the text.
	 * @param pColor the {@link UIColor}.
	 * @return the {@link UILabel}.
	 */
	protected ILabel createCenteredLabel(String pText, IColor pColor)
	{
		UILabel label = new UILabel(pText);
		label.setBackground(pColor);
		label.setHorizontalAlignment(IAlignmentConstants.ALIGN_CENTER);
		label.setVerticalAlignment(IAlignmentConstants.ALIGN_CENTER);
		
		return label;
	}
	
	/**
	 * Creates an {@link UILabel} which has the text centered.
	 *
	 * @param pText the text.
	 * @param pRed red color-part.
	 * @param pGreen green color-part.
	 * @param pBlue blue color-part.
	 * @return the {@link UILabel}.
	 */
	protected ILabel createCenteredLabel(String pText, int pRed, int pGreen, int pBlue)
	{
		return createCenteredLabel(pText, new UIColor(pRed, pGreen, pBlue));
	}
	
	/**
	 * Creates an {@link IDataBook} that can be used for event logging. It has
	 * two columns, {@code TIMESTAMP} and {@code EVENT}.
	 *
	 * @return the {@link IDataBook}.
	 * @throws ModelException if the creation of the {@link IDataBook} failed.
	 * @see #insertEvent(IDataBook, String)
	 */
	protected IDataBook createEventsDataBook() throws ModelException
	{
		IDataBook dataBook = new MemDataBook();
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("TIMESTAMP", new TimestampDataType()));
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("EVENT", new StringDataType()));
		dataBook.setName("EVENTS");
		dataBook.open();
		
		return dataBook;
	}
	
	/**
	 * Creates an {@link ICellEditor} that can be used for editing the
	 * horizontal alignment of controls.
	 *
	 * @return the {@link ICellEditor}.
	 */
	protected ICellEditor createHorizontalAlignmentCellEditor()
	{
		
		UIEnumCellEditor cellEditor = new UIEnumCellEditor();
		cellEditor.setAllowedValues(new Object[] {
				new BigDecimal(IAlignmentConstants.ALIGN_DEFAULT),
				new BigDecimal(IAlignmentConstants.ALIGN_LEFT),
				new BigDecimal(IAlignmentConstants.ALIGN_CENTER),
				new BigDecimal(IAlignmentConstants.ALIGN_RIGHT),
				new BigDecimal(IAlignmentConstants.ALIGN_STRETCH)
		});
		cellEditor.setDisplayValues(new String[] {
				"Default",
				"Left",
				"Center",
				"Right",
				"Stretch"
		});
		
		return cellEditor;
	}
	
	/**
	 * Creates an {@link ICellEditor} that can be used for editing the vertical
	 * alignment of controls.
	 *
	 * @return the {@link ICellEditor}.
	 */
	protected ICellEditor createVerticalAlignmentCellEditor()
	{
		UIEnumCellEditor cellEditor = new UIEnumCellEditor();
		cellEditor.setAllowedValues(new Object[] {
				new BigDecimal(IAlignmentConstants.ALIGN_DEFAULT),
				new BigDecimal(IAlignmentConstants.ALIGN_TOP),
				new BigDecimal(IAlignmentConstants.ALIGN_CENTER),
				new BigDecimal(IAlignmentConstants.ALIGN_BOTTOM),
				new BigDecimal(IAlignmentConstants.ALIGN_STRETCH)
		});
		cellEditor.setDisplayValues(new String[] {
				"Default",
				"Top",
				"Center",
				"Bottom",
				"Stretch"
		});
		return cellEditor;
	}
	
	/**
	 * Gets the {@link IImage} with the given name.
	 *
	 * @param pName the name.
	 * @return the {@link IImage}.
	 */
	protected IImage getImage(String pName)
	{
		return UIImage.getImage(getImagePath(pName));
	}
	
	/**
	 * Gets the full image path.
	 *
	 * @param pName the name.
	 * @return the image path.
	 */
	protected String getImagePath(String pName)
	{
		return "/com/sibvisions/kitchensink/images/" + pName;
	}
	
	/**
	 * Inserts the given event into the given {@link IDataBook}.
	 *
	 * @param pDataBook the {@link IDataBook}.
	 * @param pEvent the event.
	 * @see #createEventsDataBook()
	 */
	protected void insertEvent(IDataBook pDataBook, String pEvent)
	{
		try
		{
			if (pDataBook.getRowCount() > 0)
			{
				pDataBook.setSelectedRow(pDataBook.getRowCount() - 1);
			}
			
			pDataBook.insert(false);
			pDataBook.setValue("TIMESTAMP", new Date());
			pDataBook.setValue("EVENT", pEvent);
			pDataBook.saveSelectedRow();
			
			pDataBook.setSelectedRow(pDataBook.getRowCount() - 1);
		}
		catch (ModelException e)
		{
			e.printStackTrace();
		}
	}
	
}	// AbstractSample
