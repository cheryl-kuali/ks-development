/**
 * Copyright 2014 The Kuali Foundation Licensed under the
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
 *
 * Created by venkat on 3/19/14
 */
package org.kuali.student.cm.maintenance;

import org.kuali.rice.kew.framework.postprocessor.ActionTakenEvent;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteLevelChange;
import org.kuali.rice.kew.framework.postprocessor.DocumentRouteStatusChange;
import org.kuali.student.common.uif.service.KSMaintainable;

/**
 *
 * Provides contract for implementing a maintenance object within CM.
 *
 * @author Kuali Student Team
 */
public interface CMMaintainable extends KSMaintainable {

    public void doActionTaken(ActionTakenEvent actionTakenEvent) throws Exception;
    public void doRouteLevelChange(DocumentRouteLevelChange documentRouteLevelChange) throws Exception;
    public void doRouteStatusChange(DocumentRouteStatusChange documentRouteStatusChange) throws Exception;
    public void retrieveDataObject();

}
