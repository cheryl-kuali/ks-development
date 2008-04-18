package org.kuali.student.poc.common.util;

import java.util.Properties;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class ModPropertyPlaceholderConfigurer extends
		PropertyPlaceholderConfigurer {
	public static final String KS_MVN_ARTIFACTID_INITIALCAPS = "ks.mvn.artifactId.initialCaps";
	public static final String KS_MVN_ARTIFACTID_LOWERCASE = "ks.mvn.artifactId.lowercase";
	public static final String KS_MVN_PARENT_ARTIFACTID_LOWERCASE = "ks.mvn.parent.artifactId.lowercase";

	@Override
	protected String resolvePlaceholder(String placeholder, Properties props) {
		
		/*
		 *For the properties ks.mvn.artifactId.initialCaps and
		 *ks.mvn.artifactId.initialCaps, we need to process the strings to convert from 
		 *the maven artifact id to what we assume to be the class name and remove the "-ri"
		 */
		if (KS_MVN_ARTIFACTID_INITIALCAPS.equals(placeholder)) {
			String resolved = super.resolvePlaceholder(placeholder, props);
			if(resolved.toLowerCase().endsWith("-ri")){
				resolved = resolved.substring(0, resolved.length()-3);
			}
			return resolved;
		}
		
		if (KS_MVN_ARTIFACTID_LOWERCASE.equals(placeholder) ||
			KS_MVN_PARENT_ARTIFACTID_LOWERCASE.equals(placeholder)	) {
			String resolved = super.resolvePlaceholder(placeholder, props);
			if(resolved.toLowerCase().endsWith("-ri")){
				resolved = resolved.substring(0, resolved.length()-3);
			}
			return resolved.toLowerCase();
		}

		return super.resolvePlaceholder(placeholder, props);
	}

}
