/*
 * Copyright 2019, Robert Dyer, Che Shian Hung
 *                 and Bowling Green State University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package boa.compiler;

import java.util.List;
import java.util.ArrayList;

import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STGroupDir;
import org.stringtemplate.v4.STGroupFile;
import org.stringtemplate.v4.STRawGroupDir;

/**
 * 
 * @author hungc
 * @author rdyer
 * 
 */

public class WorkflowGenerator {
	public static STGroup workflowStg;
	protected String workflow;

	private String jobName;
	private String main;
	private List<String> subViews;
	private List<String> args;
	private List<String> subWorkflowPaths;

	public WorkflowGenerator () {
		this(null, null, new ArrayList<String>(), new ArrayList<String>(), new ArrayList<String>());
	}

	public WorkflowGenerator(String jobName, String main, List<String> subViews, List<String> subWorkflowPaths, List<String> args) {
		workflowStg = new STGroupDir("templates");
		workflowStg.importTemplates(new STGroupFile("Views.stg"));

		this.workflow = "";
		this.jobName = jobName;
		this.main = main;
		this.subViews = subViews == null ? new ArrayList<String>() : subViews;
		this.args = args == null ? new ArrayList<String>() : args;
		this.subWorkflowPaths = subWorkflowPaths == null ? new ArrayList<String>() : subWorkflowPaths;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public void setSubViews(List<String> subViews) {
		this.subViews = subViews == null ? new ArrayList<String>() : subViews;
	}

	public void setMain(String main) {
		this.main = main;
	}

	public void setArgs(List<String> args) {
		this.args = args == null ? new ArrayList<String>() : args;
	}

	public void setSubWorkflowPaths(List<String> subWorkflowPaths) {
		this.subWorkflowPaths = subWorkflowPaths == null ? new ArrayList<String>() : subWorkflowPaths;
	}

	public String getWorkflows() {
		return this.workflow;
	}

	public void createWorkflows() {
		workflow = "";
		if (jobName == null || main == null)
			return;

		final ST st = workflowStg.getInstanceOf("Workflow");

		List<String> views = new ArrayList<String>();

		for (int i = 0; i < subViews.size(); i++)
			views.add(createSubWorkflow(subViews.get(i), subWorkflowPaths.get(i)));

		st.add("jobName", jobName);
		st.add("viewnames", subViews);
		st.add("views", views);
		st.add("main", main);
		st.add("args", args);

		workflow = st.render();
	}

	public String createSubWorkflow(String jobName, String path) {
		final ST st = workflowStg.getInstanceOf("ViewWorkflow");
		st.add("jobName", jobName);
		st.add("path", path);

		return st.render();
	}
}