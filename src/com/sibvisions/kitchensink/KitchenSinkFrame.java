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
package com.sibvisions.kitchensink;

import java.util.ArrayList;
import java.util.List;

import com.sibvisions.kitchensink.samples.components.ButtonSample;
import com.sibvisions.kitchensink.samples.components.CheckBoxSample;
import com.sibvisions.kitchensink.samples.components.IconSample;
import com.sibvisions.kitchensink.samples.components.LabelSample;
import com.sibvisions.kitchensink.samples.components.MenuBarSample;
import com.sibvisions.kitchensink.samples.components.PasswordFieldSample;
import com.sibvisions.kitchensink.samples.components.RadioButtonSample;
import com.sibvisions.kitchensink.samples.components.TextAreaSample;
import com.sibvisions.kitchensink.samples.components.TextFieldSample;
import com.sibvisions.kitchensink.samples.components.ToggleButtonSample;
import com.sibvisions.kitchensink.samples.components.ToolBarSample;
import com.sibvisions.kitchensink.samples.containers.GroupPanelSample;
import com.sibvisions.kitchensink.samples.containers.MdiSample;
import com.sibvisions.kitchensink.samples.containers.PanelSample;
import com.sibvisions.kitchensink.samples.containers.ScrollPanelSample;
import com.sibvisions.kitchensink.samples.containers.SplitPanelSample;
import com.sibvisions.kitchensink.samples.containers.TabsetPanelSample;
import com.sibvisions.kitchensink.samples.layouts.BorderLayoutSample;
import com.sibvisions.kitchensink.samples.layouts.FlowLayoutSample;
import com.sibvisions.kitchensink.samples.layouts.FormLayoutSample;
import com.sibvisions.kitchensink.samples.layouts.GridLayoutSample;
import com.sibvisions.kitchensink.samples.layouts.NullLayoutSample;
import com.sibvisions.kitchensink.samples.model.ChartSample;
import com.sibvisions.kitchensink.samples.model.DataBindingSample;
import com.sibvisions.kitchensink.samples.model.MasterDetailSample;
import com.sibvisions.kitchensink.samples.model.SelfJoinedSample;
import com.sibvisions.kitchensink.samples.other.CaptureSample;
import com.sibvisions.kitchensink.samples.other.CursorSample;
import com.sibvisions.kitchensink.samples.other.EventsSample;
import com.sibvisions.kitchensink.samples.other.FontSample;
import com.sibvisions.kitchensink.samples.other.TabIndexSample;
import com.sibvisions.kitchensink.samples.other.TooltipSample;
import com.sibvisions.kitchensink.samples.other.TranslationSample;
import com.sibvisions.kitchensink.samples.tests.ZOrderFormTestSample;

import jvx.rad.genui.UICursor;
import jvx.rad.genui.component.UIButton;
import jvx.rad.genui.container.UIFrame;
import jvx.rad.genui.container.UIGroupPanel;
import jvx.rad.genui.container.UIPanel;
import jvx.rad.genui.container.UIScrollPanel;
import jvx.rad.genui.layout.UIBorderLayout;
import jvx.rad.genui.layout.UIFormLayout;

/**
 * The {@link KitchenSinkFrame} is our main {@link UIFrame} which will be used
 * to navigate and display all samples that this application contains.
 * 
 * @author Robert Zenz
 */
public class KitchenSinkFrame extends UIFrame
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Class members
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/** The {@link List} of {@link ISample}s. */
	private List<ISample> samples;
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Initialization
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Creates a new instance of {@link KitchenSinkFrame}.
	 */
	public KitchenSinkFrame()
	{
		super();
		
		collectAndAddSamples();
		
		initializeUI();
	}
	
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// User-defined methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Collects and adds all {@link ISample}s to the {@link #samples list of
	 * samples}.
	 */
	private void collectAndAddSamples()
	{
		samples = new ArrayList<>();
		
		// Components
		samples.add(new ButtonSample());
		samples.add(new CheckBoxSample());
		samples.add(new IconSample());
		samples.add(new LabelSample());
		samples.add(new MenuBarSample());
		samples.add(new PasswordFieldSample());
		samples.add(new RadioButtonSample());
		samples.add(new TextAreaSample());
		samples.add(new TextFieldSample());
		samples.add(new ToggleButtonSample());
		samples.add(new ToolBarSample());
		
		// Containers
		samples.add(new GroupPanelSample());
		samples.add(new PanelSample());
		samples.add(new ScrollPanelSample());
		samples.add(new SplitPanelSample());
		samples.add(new TabsetPanelSample());
		samples.add(new MdiSample());
		
		// Layouts
		samples.add(new BorderLayoutSample());
		samples.add(new FlowLayoutSample());
		samples.add(new FormLayoutSample());
		samples.add(new GridLayoutSample());
		samples.add(new NullLayoutSample());
		
		// Model
		samples.add(new ChartSample());
		samples.add(new DataBindingSample());
		samples.add(new MasterDetailSample());
		samples.add(new SelfJoinedSample());
		
		// Other
		samples.add(new CaptureSample());
		samples.add(new CursorSample());
		samples.add(new EventsSample());
		samples.add(new FontSample());
		samples.add(new TabIndexSample());
		samples.add(new TooltipSample());
		samples.add(new TranslationSample());
		
		// Tests
		samples.add(new ZOrderFormTestSample());
	}
	
	/**
	 * Initializes the UI.
	 */
	private void initializeUI()
	{
		// Setting up the Frame.
		setLayout(new UIBorderLayout());
		setPreferredSize(800, 600);
		setTitle("JVx KitchenSink");
		
		// The layout for the panel of buttons of the samples.
		UIFormLayout samplePanelLayout = new UIFormLayout();
		
		// The panel of buttons of the samples.
		UIScrollPanel samplePanel = new UIScrollPanel();
		samplePanel.setLayout(samplePanelLayout);
		
		// The panel that is used to display the content of the sample.
		UIPanel contentPanel = new UIPanel();
		contentPanel.setLayout(new UIBorderLayout());
		
		// Now we're setting up the list of buttons that you see on the left.
		UIFormLayout categoryPanelLayout = null;
		UIGroupPanel categoryPanel = null;
		
		// Assuming that the buttons are in the correct order and are grouped
		// by their group.
		for (ISample sample : samples)
		{
			// Category setup.
			if (categoryPanel == null || !categoryPanel.getText().equals(sample.getCategory()))
			{
				categoryPanelLayout = new UIFormLayout();
				categoryPanel = new UIGroupPanel(sample.getCategory());
				categoryPanel.setLayout(categoryPanelLayout);
				
				samplePanel.add(categoryPanel, samplePanelLayout.getConstraints(0, samplePanel.getComponentCount(), -1, samplePanel.getComponentCount()));
			}
			
			// Create the button of the sample.
			UIButton button = new UIButton(sample.getName());
			button.eventAction().addListener(pActionEvent ->
			{
				setCursor(UICursor.getPredefinedCursor(UICursor.WAIT_CURSOR));
				
				contentPanel.removeAll();
				
				try
				{
					contentPanel.add(sample.getContent(), UIBorderLayout.CENTER);
				}
				catch (Throwable th)
				{
					th.printStackTrace();
				}
				
				setCursor(UICursor.getPredefinedCursor(UICursor.DEFAULT_CURSOR));
			});
			
			// And now add it.
			int count = categoryPanel.getComponentCount();
			categoryPanel.add(button, categoryPanelLayout.getConstraints(0, count, -1, count));
		}
		
		// Add the two panels to the frame.
		add(contentPanel, UIBorderLayout.CENTER);
		add(samplePanel, UIBorderLayout.WEST);
	}
	
}	// KitchenSinkFrame
