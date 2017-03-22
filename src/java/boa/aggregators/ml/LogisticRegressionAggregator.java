/*
 * Copyright 2014, Hridesh Rajan, Robert Dyer,
 *                 and Iowa State University of Science and Technology
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
package boa.aggregators.ml;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import org.apache.commons.lang.math.NumberUtils;

import boa.BoaTup;
import boa.aggregators.AggregatorSpec;

import weka.classifiers.functions.Logistic;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.DenseInstance;
import weka.core.Instances;
import weka.core.Utils;
import weka.filters.Filter;
import weka.filters.supervised.attribute.NominalToBinary;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;

/**
 * A Boa aggregator for training the model using Logistic.
 * 
 * @author ankuraga
 */
@AggregatorSpec(name = "logistic", formalParameters = {"string"})
public class LogisticRegressionAggregator extends MLAggregator {
	private ArrayList<String> vector = new ArrayList<String>();
	private ArrayList<BoaTup> tuples = new ArrayList<BoaTup>();
	private String[] options;
	private int count = 0;
	private Logistic model;
	private int NumOfAttributes;
	private ArrayList<Attribute> fvAttributes;
	private Instances trainingSet;
	private boolean flag;

	public LogisticRegressionAggregator() {
	}

	public LogisticRegressionAggregator(final String s) {
		super(s);

		try {
			options = Utils.splitOptions(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void aggregate(final String data, final String metadata) throws IOException, InterruptedException {
		if(this.count != this.getVectorSize()) {
			this.vector.add(data);
			this.count++;
		}

		if(this.count == this.getVectorSize()) {
			if(this.flag != true)
				attributeCreation();
			instanceCreation(this.vector);
			this.vector = new ArrayList<String>();
			this.count = 0;
		}
	}

	public void aggregate(final BoaTup data, final String metadata) throws IOException, InterruptedException {
		if(this.flag != true)
			attributeCreation(data);
		instanceCreation(data);
	}

	/** {@inheritDoc} */
	@Override
	public void finish() throws IOException, InterruptedException {
		try {
			this.model = new Logistic();
			this.model.setOptions(options);
			this.model.buildClassifier(this.trainingSet);
		} catch(Exception e) {
			e.printStackTrace();
		}

		this.saveModel(this.model);
		this.saveTrainingSet(this.trainingSet);
		this.collect(this.model.toString());
	}

	public void attributeCreation(BoaTup data) {
		this.fvAttributes = new ArrayList<Attribute>();
		try {
			String[] fieldNames = data.getFieldNames();
			int count = 0;
			for(int i = 0; i < fieldNames.length; i++) {
				if(data.getValue(fieldNames[i]).getClass().isEnum()) {
					ArrayList<String> fvNominalVal = new ArrayList<String>();
					for(Object obj: data.getValue(fieldNames[i]).getClass().getEnumConstants())
						fvNominalVal.add(obj.toString());
					this.fvAttributes.add(new Attribute("Nominal" + count, fvNominalVal));
					count++;
				}
				else if(data.getValue(fieldNames[i]).getClass().isArray()) {
					int l = Array.getLength(data.getValue(fieldNames[i])) - 1;
					for(int j = 0; j <= l; j++) {
						this.fvAttributes.add(new Attribute("Attribute" + count));
						count++;
					}
				}
				else {
					this.fvAttributes.add(new Attribute("Attribute" + count));
					count++;
				}
			}
			this.NumOfAttributes = count;
			this.flag = true;
			this.trainingSet = new Instances("Logistic", this.fvAttributes, 1);
			this.trainingSet.setClassIndex(this.NumOfAttributes-1);

			this.unFilteredInstances = new Instances("LinearRegression", this.fvAttributes, 1);
			this.unFilteredInstances.setClassIndex(this.NumOfAttributes-1);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void attributeCreation() {
		this.fvAttributes = new ArrayList<Attribute>();
		this.NumOfAttributes = this.getVectorSize();
		try {
			for(int i = 0; i < this.NumOfAttributes; i++)
				this.fvAttributes.add(new Attribute("Attribute" + i));

			this.flag = true;
			this.trainingSet = new Instances("Logistic", this.fvAttributes, 1);
			this.trainingSet.setClassIndex(this.NumOfAttributes-1);

			this.unFilteredInstances = new Instances("LinearRegression", this.fvAttributes, 1);
			this.unFilteredInstances.setClassIndex(this.NumOfAttributes-1);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void instanceCreation(BoaTup data){
		try {
			int count=0;
			Instance instance = new DenseInstance(this.NumOfAttributes);
			String[] fieldNames = data.getFieldNames();
			for(int i = 0; i < fieldNames.length; i++) {
				if(data.getValue(fieldNames[i]).getClass().isEnum()) {
					instance.setValue((Attribute)this.fvAttributes.get(count), String.valueOf(data.getValue(fieldNames[i])));
					count++;
				}
				else if(data.getValue(fieldNames[i]).getClass().isArray()) {
					int x = Array.getLength(data.getValue(fieldNames[i])) - 1;
					Object o = data.getValue(fieldNames[i]);
					for(int j = 0; j <= x; j++) {
						instance.setValue((Attribute)this.fvAttributes.get(count), Double.parseDouble(String.valueOf(Array.get(o, j))));
						count++;
					}
				}
				else {
					if(NumberUtils.isNumber(String.valueOf(data.getValue(fieldNames[i]))))
						instance.setValue((Attribute)this.fvAttributes.get(count),  Double.parseDouble(String.valueOf(data.getValue(fieldNames[i]))));
					else
						instance.setValue((Attribute)this.fvAttributes.get(count),  String.valueOf(data.getValue(fieldNames[i])));
					count++;
				}
			}
			handleDataInstances(instance);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void instanceCreation(ArrayList<String> data){
		try {
			Instance instance = new DenseInstance(this.NumOfAttributes);
			for(int i=0; i < this.NumOfAttributes; i++)
				instance.setValue((Attribute)this.fvAttributes.get(i), Double.parseDouble(data.get(i)));

			handleDataInstances(instance);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void handleDataInstances(Instance instance) throws Exception {
		if(unFilteredInstances.size() < maxUnfilteredThreshold) {
			unFilteredInstances.add(instance);
		} else {
			model.getCapabilities().testWithFail(unFilteredInstances);
			Filter filter = new NominalToBinary();
			filter.setInputFormat(unFilteredInstances);
			applyFilterToUnfilteredInstances(filter);

			filter = new ReplaceMissingValues();
			filter.setInputFormat(unFilteredInstances);
			applyFilterToUnfilteredInstances(filter, trainingSet);
		}
	}
}
