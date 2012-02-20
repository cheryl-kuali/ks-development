/**
 * Copyright 2010 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.0 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.10.21 at 02:14:18 PM PDT 
//


package org.kuali.student.common.olddictionary.old.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;


// TODO KSCM I am being used

/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.dto.dictionary.student.kuali.org}fieldDescriptor"/>
 *         &lt;element name="readOnly" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *       &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */

@Deprecated
@XmlAccessorType(XmlAccessType.FIELD)
public class Field implements Serializable{

    private static final long serialVersionUID = 1L;

    @XmlAttribute(required = true)
    protected String key;

    @XmlAttribute 
    protected String id;
    
    @XmlElement //TODO there is no required here but there should be
    protected FieldDescriptor fieldDescriptor;
    
    @XmlElement
    protected ConstraintDescriptor constraintDescriptor;
    
    @XmlElement
    protected boolean selector;
    
    @XmlElement
    protected boolean dynamic;
    
    
    /**
     * Gets the value of the fieldDescriptor property.
     * 
     * @return
     *     possible object is
     *     {@link FieldDescriptor }
     *     
     */
    public FieldDescriptor getFieldDescriptor() {
        return fieldDescriptor;
    }

    /**
     * Sets the value of the fieldDescriptor property.
     * 
     * @param value
     *     allowed object is
     *     {@link FieldDescriptor }
     *     
     */
    public void setFieldDescriptor(FieldDescriptor value) {
        this.fieldDescriptor = value;
    }

	public boolean isSelector() {
		return selector;
	}

	/**
	 * 	Indicates if the field is a selector field, which indicates that changes 
	 *  to this field may alter the structure of the object type. This may provide 
	 *  insight as to frequency of calls to a validate operation, as the 
	 *  constraints will not be externalized.
	 *  
	 * @param isSelector
	 */
	public void setSelector(boolean isSelector) {
		this.selector = isSelector;
	}

	/**
     * Gets the value of the key property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKey() {
        return key;
    }

    /**
     * Sets the value of the key property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKey(String value) {
        this.key = value;
    }

	/**
	 * @return the constraintDescriptor
	 */
	public ConstraintDescriptor getConstraintDescriptor() {
		return constraintDescriptor;
	}

	/**
	 * @param constraintDescriptor the constraintDescriptor to set
	 */
	public void setConstraintDescriptor(ConstraintDescriptor constraintDescriptor) {
		this.constraintDescriptor = constraintDescriptor;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the dynamic
	 */
	public boolean isDynamic() {
		return dynamic;
	}

	/**
	 * @param dyncamic the dynamic to set
	 */
	public void setDynamic(boolean dynamic) {
		this.dynamic = dynamic;
	}

}
