/*
 * Copyright 2009 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may	obtain a copy of the License at
 *
 * 	http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.student.orchestration.base;


import org.kuali.student.common.assembly.client.Data;
import org.kuali.student.lum.lu.assembly.data.client.PropertyEnum;
import org.kuali.student.lum.lu.dto.ReqCompFieldTypeInfo;


public class ReqCompFieldTypeInfoData
	extends Data
{
	private static final long serialVersionUID = 1;
	
	public enum Properties implements PropertyEnum
	{
		FIELD_DESCRIPTOR ("fieldDescriptor"),
		KEY ("key");
		
		private final String key;
		
		private Properties (final String key)
		{
			this.key = key;
		}
		
		@Override
		public String getKey ()
		{
			return this.key;
		}
	}
	
	public ReqCompFieldTypeInfoData ()
	{
		// TODO: ask Wil if we want to really use the class name as the key
		super (ReqCompFieldTypeInfo.class.getName ());
	}
	
	public void setFieldDescriptor (FieldDescriptorInfoData value)
	{
		super.set (Properties.FIELD_DESCRIPTOR.getKey (), value);
	}
	
	
	public FieldDescriptorInfoData getFieldDescriptor ()
	{
		return super.get (Properties.FIELD_DESCRIPTOR.getKey ());
	}
	
	
	public void setKey (String value)
	{
		super.set (Properties.KEY.getKey (), value);
	}
	
	
	public String getKey ()
	{
		return super.get (Properties.KEY.getKey ());
	}
	
}

