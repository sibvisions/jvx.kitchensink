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

import javax.rad.genui.container.UIPanel;
import javax.rad.genui.control.UIChart;
import javax.rad.genui.layout.UIGridLayout;
import javax.rad.model.ColumnDefinition;
import javax.rad.model.IDataBook;
import javax.rad.model.datatype.BigDecimalDataType;
import javax.rad.model.datatype.TimestampDataType;
import javax.rad.ui.container.IPanel;
import javax.rad.ui.control.IChart;

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
		dataBook.setName("DATA");
		dataBook.open();
		
		Random random = new Random(0);
		
		for (int counter = 0; counter < 25; counter++)
		{
			BigDecimal value = BigDecimal.valueOf(random.nextInt(100));
			BigDecimal valueB = BigDecimal.valueOf(random.nextInt(100));
			
			Calendar timestamp = Calendar.getInstance();
			timestamp.add(Calendar.DAY_OF_YEAR, counter);
			
			dataBook.insert(false);
			dataBook.setValues(null, new Object[] { value, valueB, new Timestamp(timestamp.getTime().getTime()) });
		}
		
		dataBook.saveAllRows();
		dataBook.setSelectedRow(0);
		
		UIGridLayout contentLayout = new UIGridLayout(2, 2);
		
		UIPanel content = new UIPanel();
		content.setLayout(contentLayout);
		content.add(createChart(dataBook, "Area", UIChart.STYLE_AREA), contentLayout.getConstraints(0, 0));
		content.add(createChart(dataBook, "Bars", UIChart.STYLE_BARS), contentLayout.getConstraints(1, 0));
		content.add(createChart(dataBook, "Lines", UIChart.STYLE_LINES), contentLayout.getConstraints(0, 1));
		content.add(createChart(dataBook, "Pie", UIChart.STYLE_PIE), contentLayout.getConstraints(1, 1));
		
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
	
	private IChart createChart(IDataBook pDataBook, String pTitle, int pChartStyle)
	{
		UIChart chart = new UIChart();
		chart.setChartStyle(pChartStyle);
		chart.setDataBook(pDataBook);
		chart.setTitle(pTitle);
		chart.setXAxisTitle("Date");
		chart.setXColumnName("TIMESTAMP");
		chart.setYAxisTitle("Value");
		chart.setYColumnNames(new String[] { "VALUE", "VALUEB" });
		
		return chart;
	}
	
}	// MasterDetailSample
