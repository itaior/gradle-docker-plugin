/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.bmuschko.gradle.docker.tasks.image

import com.bmuschko.gradle.docker.tasks.AbstractDockerRemoteApiTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional

/**
 * Created by oritai on 14/05/2015.
 */
class DockerIdByNameImage extends AbstractDockerRemoteApiTask {


    @Input
    String tag



    String imageId

    DockerIdByNameImage() {
        ext.getImageId = { imageId }
    }



    @Override
    void runRemoteCommand(dockerClient) {
        logger.quiet "Get Image id by name:'${getTag()}'"
        def listImagesCmd = dockerClient.listImagesCmd()//.withShowAll(true)
        def images = listImagesCmd.exec()
        def iID = images.findResults{ it.repoTags.contains(getTag()) ? it.id : null }
        imageId=iID[0]
        logger.quiet "The Image id:'$imageId'"
    }
}
