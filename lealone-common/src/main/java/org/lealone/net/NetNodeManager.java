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
package org.lealone.net;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.lealone.db.IDatabase;
import org.lealone.db.session.Session;
import org.lealone.storage.replication.ReplicationSession;

public interface NetNodeManager {

    Set<NetNode> getLiveNodes();

    default long getRpcTimeout() {
        return 0;
    }

    String[] assignNodes(IDatabase db);

    default String[] getReplicationNodes(IDatabase db) {
        return new String[0];
    }

    default String[] getShardingNodes(IDatabase db) {
        return new String[0];
    }

    default ReplicationSession createReplicationSession(Session session, Collection<NetNode> replicationNodes) {
        return null;
    }

    default ReplicationSession createReplicationSession(Session session, Collection<NetNode> replicationNodes,
            Boolean remote) {
        return null;
    }

    default ReplicationSession createReplicationSession(Session s, Session[] sessions) {
        return null;
    }

    default NetNode getNode(String hostId) {
        return null;
    }

    default String getHostId(NetNode node) {
        return null;
    }

    default List<NetNode> getReplicationNodes(IDatabase db, Set<NetNode> oldReplicationNodes,
            Set<NetNode> candidateNodes) {
        return null;
    }

    default Collection<String> getRecognizedReplicationStrategyOptions(String strategyName) {
        return null;
    }

    default Collection<String> getRecognizedNodeAssignmentStrategyOptions(String strategyName) {
        return null;
    }

    default String getDefaultReplicationStrategy() {
        return null;
    }

    default int getDefaultReplicationFactor() {
        return 1;
    }

    default String getDefaultNodeAssignmentStrategy() {
        return null;
    }

    default int getDefaultNodeAssignmentFactor() {
        return 1;
    }
}
