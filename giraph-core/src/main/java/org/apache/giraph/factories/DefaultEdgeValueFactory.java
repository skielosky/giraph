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
package org.apache.giraph.factories;

import org.apache.giraph.conf.GiraphConfigurationSettable;
import org.apache.giraph.conf.ImmutableClassesGiraphConfiguration;
import org.apache.giraph.utils.WritableUtils;
import org.apache.hadoop.io.Writable;

/**
 * Factory class to create default edge values.
 * A user can extend this class in order to customize the creation of new
 * edge values when an edge is created by the infrastructure.
 *
 * @param <E> Edge Value
 */
public class DefaultEdgeValueFactory<E extends Writable>
    implements EdgeValueFactory<E>, GiraphConfigurationSettable {
  /** Cached edge value class. */
  private Class<E> edgeValueClass;
  /** Configuration */
  private ImmutableClassesGiraphConfiguration conf;

  @Override
  public void setConf(ImmutableClassesGiraphConfiguration conf) {
    this.conf = conf;
    edgeValueClass = conf.getEdgeValueClass();
  }

  @Override public E newInstance() {
    return WritableUtils.createWritable(edgeValueClass, conf);
  }
}
