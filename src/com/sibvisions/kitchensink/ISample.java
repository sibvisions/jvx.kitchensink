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

import javax.rad.ui.container.IPanel;

/**
 * An {@link ISample} is a simple interface that is used to encapsualte the
 * different showcases of this application.
 * 
 * @author Robert Zenz
 */
public interface ISample
{
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// Abstract methods
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	/**
	 * Gets the category of this {@link ISample}.
	 * 
	 * @return the category.
	 */
	public String getCategory();
	
	/**
	 * Gets the {@link IPanel content} of this {@link ISample}.
	 * 
	 * @return the {@link IPanel content}.
	 */
	public IPanel getContent() throws Throwable;
	
	/**
	 * Gets the name of this {@link ISample}.
	 * 
	 * @return the name.
	 */
	public String getName();
	
}	// ISample
