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
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.control.UIChart;
import jvx.rad.genui.layout.UIGridLayout;
import jvx.rad.model.ColumnDefinition;
import jvx.rad.model.IDataBook;
import jvx.rad.model.datatype.BigDecimalDataType;
import jvx.rad.model.datatype.StringDataType;
import jvx.rad.model.datatype.TimestampDataType;
import jvx.rad.ui.container.IPanel;
import jvx.rad.ui.control.IChart;

import com.sibvisions.kitchensink.ISample;
import com.sibvisions.kitchensink.samples.AbstractSample;
import com.sibvisions.rad.model.mem.MemDataBook;

/**
 * Demonstrates the Master-Detail capabilities of JVx.
 * 
 * @author Robert Zenz
 */
public class ChartSample extends AbstractSample implements ISample
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
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("VALUE", new BigDecimalDataType()));
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("VALUEB", new BigDecimalDataType()));
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("TIMESTAMP", new TimestampDataType()));
		dataBook.getRowDefinition().addColumnDefinition(new ColumnDefinition("STRING", new StringDataType()));
		dataBook.setName("DATA");
		dataBook.open();
		
		Random random = new Random(0);
		
		for (int counter = 0; counter < 10; counter++)
		{
			BigDecimal value = BigDecimal.valueOf(random.nextInt(100));
			BigDecimal valueB = BigDecimal.valueOf(random.nextInt(100));
			
			Calendar timestamp = Calendar.getInstance();
			timestamp.add(Calendar.DAY_OF_YEAR, counter);
			
			dataBook.insert(false);
			dataBook.setValues(null, new Object[] { value, valueB, new Timestamp(timestamp.getTime().getTime()), "" + timestamp.get(Calendar.DAY_OF_YEAR) });
		}
		
		dataBook.saveAllRows();
		dataBook.setSelectedRow(0);
		
		UIGridLayout contentLayout = new UIGridLayout(2, 4);
		
		UIPanel content = new UIPanel();
		content.setLayout(contentLayout);
		content.add(createChart(dataBook, "Area", UIChart.STYLE_AREA, "TIMESTAMP", new String[] { "VALUE", "VALUEB" }), contentLayout.getConstraints(0, 0));
		content.add(createChart(dataBook, "Bars", UIChart.STYLE_BARS, "TIMESTAMP", new String[] { "VALUE", "VALUEB" }), contentLayout.getConstraints(1, 0));
		content.add(createChart(dataBook, "Lines", UIChart.STYLE_LINES, "TIMESTAMP", new String[] { "VALUE", "VALUEB" }), contentLayout.getConstraints(0, 1));
		content.add(createChart(dataBook, "Pie", UIChart.STYLE_PIE, "TIMESTAMP", new String[] { "VALUE", "VALUEB" }), contentLayout.getConstraints(1, 1));
		content.add(createChart(dataBook, "Area", UIChart.STYLE_AREA, "STRING", new String[] { "VALUE", "VALUEB" }), contentLayout.getConstraints(0, 2));
		content.add(createChart(dataBook, "Bars", UIChart.STYLE_BARS, "STRING", new String[] { "VALUE", "VALUEB" }), contentLayout.getConstraints(1, 2));
		content.add(createChart(dataBook, "Lines", UIChart.STYLE_LINES, "STRING", new String[] { "VALUE", "VALUEB" }), contentLayout.getConstraints(0, 3));
		content.add(createChart(dataBook, "Pie", UIChart.STYLE_PIE, "STRING", new String[] { "VALUE" }), contentLayout.getConstraints(1, 3));
		
		return content;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName()
	{
		return "Chart";
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	private IChart createChart(IDataBook pDataBook, String pTitle, int pChartStyle, String pXColumnName, String[] pYColumnNames)
	{
		UIChart chart = new UIChart();
		chart.setChartStyle(pChartStyle);
		chart.setDataBook(pDataBook);
		chart.setTitle(pTitle);
		chart.setXAxisTitle("Date");
		chart.setXColumnName(pXColumnName);
		chart.setYAxisTitle("Value");
		chart.setYColumnNames(pYColumnNames);
		
		return chart;
	}
	
}	// MasterDetailSample
