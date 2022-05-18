/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.seatunnel.core.flink.command;

import org.apache.seatunnel.core.base.command.Command;
import org.apache.seatunnel.core.base.config.ConfigBuilder;
import org.apache.seatunnel.core.base.exception.ConfigCheckException;
import org.apache.seatunnel.core.base.utils.FileUtils;
import org.apache.seatunnel.core.flink.args.FlinkCommandArgs;
import org.apache.seatunnel.core.flink.config.SeaTunnelApiConfigChecker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Path;

/**
 * Use to validate the configuration of the SeaTunnel API.
 */
public class SeaTunnelApiConfValidateCommand implements Command<FlinkCommandArgs> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SeaTunnelApiConfValidateCommand.class);

    private final FlinkCommandArgs flinkCommandArgs;

    public SeaTunnelApiConfValidateCommand(FlinkCommandArgs flinkCommandArgs) {
        this.flinkCommandArgs = flinkCommandArgs;
    }

    @Override
    public void execute() throws ConfigCheckException {
        Path configPath = FileUtils.getConfigPath(flinkCommandArgs);
        // todo: validate the config by new api
        ConfigBuilder configBuilder = new ConfigBuilder(configPath);
        new SeaTunnelApiConfigChecker().checkConfig(configBuilder.getConfig());
        LOGGER.info("config OK !");
    }
}