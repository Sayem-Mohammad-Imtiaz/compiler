/*
 * Copyright 2014, Anthony Urso, Hridesh Rajan, Robert Dyer, 
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
package boa.aggregators;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer.Context;

import boa.io.EmitKey;
import boa.io.EmitValue;

/**
 * A container for one or more Boa aggregators.
 * 
 * @author anthonyu
 */
public class Table {
	private final Aggregator[] aggregators;
	private boolean combining;
	@SuppressWarnings("rawtypes")
	private Context context;
	private EmitKey key;

	public Table(final Aggregator... aggregators) {
		this.aggregators = aggregators;
		this.combining = false;
	}

	public Aggregator getAggregator(final int index) {
		return this.aggregators[index];
	}

	public Aggregator[] getAggregators() {
		return this.aggregators;
	}

	public void setCombining(final boolean combining) {
		this.combining = combining;

		for (final Aggregator a : this.aggregators)
			a.setCombining(combining);
	}

	public void start(final EmitKey key) {
		this.key = key;

		for (final Aggregator a : this.aggregators)
			a.start(key);
	}

	public void setContext(@SuppressWarnings("rawtypes") final org.apache.hadoop.mapreduce.Reducer.Context context) {
		this.context = context;

		for (final Aggregator a : this.aggregators)
			a.setContext(context);
	}

	public void aggregate(final String[] data, final String metadata) throws IOException, InterruptedException, FinishedException {
		for (int i = 0; i < data.length && i < this.aggregators.length; i++)
			this.aggregators[i].aggregate(data[i], metadata);
	}

	@SuppressWarnings("unchecked")
	public void finish() throws IOException, InterruptedException {
		// FIXME rdyer
		// - adds a ", null" to the output
		if (this.aggregators.length > 1) {
			final StringBuilder sb = new StringBuilder();

			for (final Aggregator a : this.aggregators) {
				if (sb.length() > 0)
					sb.append(", ");
				sb.append(a.getResult().getData()[0]);
			}

			final String data = sb.toString();

			if (this.combining)
				this.context.write(this.key, new EmitValue(data));
			else
				this.context.write(new Text(this.key + " = " + data), NullWritable.get());
		} else {
			this.aggregators[0].finish();
		}
	}
}
