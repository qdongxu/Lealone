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
package org.lealone.server.protocol.dt;

import java.io.IOException;

import org.lealone.net.NetInputStream;
import org.lealone.net.NetOutputStream;
import org.lealone.server.protocol.NoAckPacket;
import org.lealone.server.protocol.PacketDecoder;
import org.lealone.server.protocol.PacketType;

public class DistributedTransactionCommit implements NoAckPacket {

    public final String allLocalTransactionNames;

    public DistributedTransactionCommit(String allLocalTransactionNames) {
        this.allLocalTransactionNames = allLocalTransactionNames;
    }

    @Override
    public PacketType getType() {
        return PacketType.DISTRIBUTED_TRANSACTION_COMMIT;
    }

    @Override
    public void encode(NetOutputStream out, int version) throws IOException {
        out.writeString(allLocalTransactionNames);
    }

    public static final Decoder decoder = new Decoder();

    private static class Decoder implements PacketDecoder<DistributedTransactionCommit> {
        @Override
        public DistributedTransactionCommit decode(NetInputStream in, int version) throws IOException {
            String allLocalTransactionNames = in.readString();
            return new DistributedTransactionCommit(allLocalTransactionNames);
        }
    }
}
