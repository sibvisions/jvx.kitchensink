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
import jvx.rad.genui.control.UICellFormat;
import jvx.rad.model.IDataBook;
import jvx.rad.model.IDataPage;
import jvx.rad.model.IDataRow;
import jvx.rad.ui.IFont;
import jvx.rad.ui.IImage;
import jvx.rad.ui.container.IPanel;
import jvx.rad.ui.control.ICellFormat;
import jvx.rad.ui.control.ICellFormatter;
import jvx.rad.ui.control.INodeFormatter;
import jvx.rad.ui.control.ITree;

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
	// Class members
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** The {@link IImage} used for nodes with children. */
	private final IImage FOLDER_IMAGE = getImage("folder.png");
	
	/** The {@link IImage} used for nodes with children if it is open. */
	private final IImage FOLDER_OPEN_IMAGE = getImage("folder-open.png");
	
	/** The {@link IImage} used for nodes without children. */
	private final IImage LEAF_IMAGE = getImage("text-x-generic.png");
	
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
				return LEAF_IMAGE;
			}
			else
			{
				if (pIsExpanded)
				{
					return FOLDER_OPEN_IMAGE;
				}
				else
				{
					return FOLDER_IMAGE;
				}
			}
		}
		
	}	// NodeFormatter
	
}	// StyledTreeSample
