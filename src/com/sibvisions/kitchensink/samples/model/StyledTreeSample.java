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
import javax.rad.genui.control.UICellFormat;
import javax.rad.model.IDataBook;
import javax.rad.model.IDataPage;
import javax.rad.model.IDataRow;
import javax.rad.ui.IColor;
import javax.rad.ui.IFont;
import javax.rad.ui.IImage;
import javax.rad.ui.container.IPanel;
import javax.rad.ui.control.ICellFormat;
import javax.rad.ui.control.ICellFormatter;
import javax.rad.ui.control.INodeFormatter;
import javax.rad.ui.control.ITree;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.Tango;

/**
 * Demonstrates style capabilities of the tree.
 * 
 * @author Robert Zenz
 */
public class StyledTreeSample extends SelfJoinedSample implements ISample
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Overwritten methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPanel getContent() throws Throwable
	{
		IPanel content = super.getContent();
		ITree tree = (ITree)content.getComponent(0);
		
		tree.setCellFormatter(new CellFormatter());
		tree.setNodeFormatter(new NodeFormatter());
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Styled Tree";
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
	
	/**
	 * The {@link INodeFormatter} used for this example.
	 * 
	 * @author Robert Zenz
	 */
	private final class NodeFormatter implements INodeFormatter
	{
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		// Interface implementation
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public IImage getNodeImage(IDataBook pDataBook, IDataPage pDataPage, IDataRow pDataRow, String pColumnName, int pRowIndex, boolean pIsExpanded, boolean pIsLeaf)
		{
			if (pIsLeaf)
			{
				return getImage("text-x-generic.png");
			}
			else
			{
				if (pIsExpanded)
				{
					return getImage("folder-open.png");
				}
				else
				{
					return getImage("folder.png");
				}
			}
		}
		
	}	// NodeFormatter
	
}	// StyledTreeSample
