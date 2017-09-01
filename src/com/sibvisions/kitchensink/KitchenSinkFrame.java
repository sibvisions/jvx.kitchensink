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

import javax.rad.genui.IFontAwesome;
import javax.rad.genui.UIColor;
import javax.rad.genui.UICursor;
import javax.rad.genui.UIImage;
import javax.rad.genui.component.UIButton;
import javax.rad.genui.component.UIIcon;
import javax.rad.genui.container.UIFrame;
import javax.rad.genui.container.UIGroupPanel;
import javax.rad.genui.container.UIPanel;
import javax.rad.genui.container.UIScrollPanel;
import javax.rad.genui.layout.UIBorderLayout;
import javax.rad.genui.layout.UIFormLayout;
import javax.rad.ui.IAlignmentConstants;
import javax.rad.ui.IContainer;
import javax.rad.ui.layout.IFormLayout.IConstraints;

import com.sibvisions.kitchensink.components.AboutJVxComponent;
import com.sibvisions.kitchensink.components.AboutSIBVisionsComponent;
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
import com.sibvisions.kitchensink.samples.model.MasterDetail2Sample;
import com.sibvisions.kitchensink.samples.model.MasterDetailSample;
import com.sibvisions.kitchensink.samples.model.SelfJoinedSample;
import com.sibvisions.kitchensink.samples.model.StyledTableSample;
import com.sibvisions.kitchensink.samples.model.StyledTreeSample;
import com.sibvisions.kitchensink.samples.other.CaptureSample;
import com.sibvisions.kitchensink.samples.other.CursorSample;
import com.sibvisions.kitchensink.samples.other.EventsSample;
import com.sibvisions.kitchensink.samples.other.FontAwesomeSample;
import com.sibvisions.kitchensink.samples.other.FontSample;
import com.sibvisions.kitchensink.samples.other.TabIndexSample;
import com.sibvisions.kitchensink.samples.other.TooltipSample;
import com.sibvisions.kitchensink.samples.other.TranslationSample;
import com.sibvisions.kitchensink.samples.tests.ZOrderFormTestSample;

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
	
	private static void addBorder(IContainer pContainer, int pHorizontalAlignment, int pVerticalAlignment)
	{
		UIFormLayout layout = (UIFormLayout)pContainer.getLayout();
		IConstraints constraints = layout.getConstraints(layout.getTopAnchor(), layout.getLeftAnchor(), layout.getBottomAnchor(), layout.getRightAnchor());
		
		UIIcon border = new UIIcon(UIImage.getImage("/com/sibvisions/kitchensink/images/border-pixel.png"));
		border.setHorizontalAlignment(pHorizontalAlignment);
		border.setVerticalAlignment(pVerticalAlignment);
		
		pContainer.add(border, constraints);
	}
	
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
		samples.add(new MdiSample());
		samples.add(new PanelSample());
		samples.add(new ScrollPanelSample());
		samples.add(new SplitPanelSample());
		samples.add(new TabsetPanelSample());
		
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
		samples.add(new MasterDetail2Sample());
		samples.add(new SelfJoinedSample());
		samples.add(new StyledTableSample());
		samples.add(new StyledTreeSample());
		
		// Other
		samples.add(new CaptureSample());
		samples.add(new CursorSample());
		samples.add(new EventsSample());
		samples.add(new FontSample());
		samples.add(new FontAwesomeSample());
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
		// The layout for the panel of buttons of the samples.
		UIFormLayout samplePanelLayout = new UIFormLayout();
		
		// The panel of buttons of the samples.
		UIScrollPanel samplePanel = new UIScrollPanel();
		samplePanel.setLayout(samplePanelLayout);
		samplePanel.setBackground(new UIColor(0xf4f8fa));
		
		// The panel that is used to display the content of the sample.
		UIPanel contentPanel = new UIPanel();
		contentPanel.setLayout(new UIBorderLayout());
		contentPanel.add(new AboutJVxComponent(), UIBorderLayout.CENTER);
		
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
				categoryPanel.setBackground(new UIColor(0xf4f8fa));
				
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
		
		// The "About JVx" button in the top bar.
		UIButton aboutJvxButton = new UIButton();
		aboutJvxButton.setBorderOnMouseEntered(true);
		aboutJvxButton.setFocusable(false);
		aboutJvxButton.setHorizontalTextPosition(UIButton.ALIGN_CENTER);
		aboutJvxButton.setImage(UIImage.getImage(IFontAwesome.INFO_CIRCLE_LARGE));
		aboutJvxButton.setText("About JVx");
		aboutJvxButton.setVerticalTextPosition(UIButton.ALIGN_BOTTOM);
		aboutJvxButton.eventAction().addListener(pActionEvent ->
		{
			contentPanel.removeAll();
			contentPanel.add(new AboutJVxComponent(), UIBorderLayout.CENTER);
		});
		
		// The "About SIB Visions" button in the top bar.
		UIButton aboutSibVisionsButton = new UIButton();
		aboutSibVisionsButton.setBorderOnMouseEntered(true);
		aboutSibVisionsButton.setFocusable(false);
		aboutSibVisionsButton.setHorizontalTextPosition(UIButton.ALIGN_CENTER);
		aboutSibVisionsButton.setImage(UIImage.getImage(IFontAwesome.INFO_CIRCLE_LARGE));
		aboutSibVisionsButton.setText("About SIB Visions");
		aboutSibVisionsButton.setVerticalTextPosition(UIButton.ALIGN_BOTTOM);
		aboutSibVisionsButton.eventAction().addListener(pActionEvent ->
		{
			contentPanel.removeAll();
			contentPanel.add(new AboutSIBVisionsComponent(), UIBorderLayout.CENTER);
		});
		
		// The layout for the header panel.
		UIFormLayout headerPanelLayout = new UIFormLayout();
		
		// The panel used for the top bar.
		UIPanel headerPanel = new UIPanel();
		headerPanel.setLayout(headerPanelLayout);
		headerPanel.setBackground(UIColor.white);
		headerPanel.add(new UIIcon(new UIImage("/com/sibvisions/kitchensink/images/jvx.png")), headerPanelLayout.getConstraints(0, 0));
		headerPanel.add(aboutJvxButton, headerPanelLayout.getConstraints(-2, 0));
		headerPanel.add(aboutSibVisionsButton, headerPanelLayout.getConstraints(-1, 0));
		addBorder(headerPanel, IAlignmentConstants.ALIGN_STRETCH, IAlignmentConstants.ALIGN_BOTTOM);
		
		// Setting up the Frame.
		setLayout(new UIBorderLayout());
		setPreferredSize(800, 600);
		setTitle("JVx KitchenSink");
		
		// Add the panels to the frame.
		add(headerPanel, UIBorderLayout.NORTH);
		add(contentPanel, UIBorderLayout.CENTER);
		add(samplePanel, UIBorderLayout.WEST);
	}
	
}	// KitchenSinkFrame
