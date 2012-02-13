/*
 * Copyright 2010 The Kuali Foundation 
 *
 * Licensed under the Educational Community License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.student.core.search.dto;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.kuali.student.core.search.infc.SearchParam;
import org.w3c.dom.Element;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SearchParamInfo", propOrder = {
                 "key", "value", "_futureElements" })

public class SearchParamInfo 
    implements SearchParam, Serializable {

    private static final long serialVersionUID = 1L;
    
    @XmlAttribute
    private String key;
    
    @XmlElement
    private Object value;

    @XmlAnyElement
    private List<Element> _futureElements;

    
    /**
     * Constructs a new SearchParamInfo.
     */
    public SearchParamInfo() {
    }

    /**
     * Constructs a new SearchParamInfo from
     * another SearchParam.
     *
     * @param param the SearchParam to copy
     */
    public SearchParamInfo(SearchParam param) {
        if (param != null) {
            this.key = param.getKey();
            this.value = param.getValue();
        }
    }
    
    /**
     * Constructs a new SearchParamInfo from
     * a pair of key/value strings.
     *
     * @param key the key for the parameter
     * @param value the value for the parameter
     */
    public SearchParamInfo(String key, String value) {
        this.key = key;
        this.value = value;
    }
    
    /**
     * Constructs a new SearchParamInfo for a list
     * of string values.
     *
     * @param key the key for the parameter
     * @param values a list of values for the parameter
     */
    public SearchParamInfo(String key, List<String> values) {
        this.key = key;
        this.value = values;
    }
    
    @Override
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    @Override
    public Object getValue() {
        return value;
    }
    
    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SearchParam[key=" + key + ", value=" + value + "]";
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        SearchParamInfo that = (SearchParamInfo) o;

        if (key != null ? !key.equals(that.key) : that.key != null) return false;
        if (value != null ? !value.equals(that.value) : that.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (key != null ? key.hashCode() : 0);
        return result;
    }
}
