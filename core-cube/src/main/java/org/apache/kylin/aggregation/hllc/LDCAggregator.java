/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.apache.kylin.aggregation.hllc;

import org.apache.kylin.aggregation.MeasureAggregator;
import org.apache.kylin.common.util.LongMutable;

/**
 * Long Distinct Count
 */
@SuppressWarnings("serial")
public class LDCAggregator extends MeasureAggregator<LongMutable> {

    private static LongMutable ZERO = new LongMutable(0);

    private HLLCAggregator hllAgg = null;
    private LongMutable state = new LongMutable(0);

    @SuppressWarnings("rawtypes")
    public void setDependentAggregator(MeasureAggregator agg) {
        this.hllAgg = (HLLCAggregator) agg;
    }

    @Override
    public void reset() {
    }

    @Override
    public void aggregate(LongMutable value) {
    }

    @Override
    public LongMutable getState() {
        if (hllAgg == null) {
            return ZERO;
        } else {
            state.set(hllAgg.getState().getCountEstimate());
            return state;
        }
    }

    @Override
    public int getMemBytesEstimate() {
        return guessLongMemBytes();
    }

}