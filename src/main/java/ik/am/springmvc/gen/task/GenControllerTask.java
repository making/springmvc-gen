/*
 * Copyright (C) 2012 Toshiaki Maki <makingx@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package ik.am.springmvc.gen.task;

import ik.am.springmvc.gen.Constants;
import ik.am.springmvc.gen.desc.ControllerDesc;
import ik.am.springmvc.gen.generator.ControllerGenerator;
import ik.am.springmvc.gen.generator.ControllerTestCaseGenerator;

import org.slim3.gen.generator.Generator;
import org.slim3.gen.task.AbstractGenJavaFileTask;
import org.slim3.gen.task.ClassNameBuilder;
import org.slim3.gen.task.JavaFile;
import org.slim3.gen.util.StringUtil;

public class GenControllerTask extends AbstractGenJavaFileTask {
    /** the packageName */
    protected String basePackageName;
    /** the basePath */
    protected String basePath;
    protected boolean generateIndex = true;

    /**
     * Sets the packageName.
     * 
     * @param basePackageName
     *            the base packageName to set
     */
    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }

    /**
     * Sets the basePath
     * 
     * @param basePath
     *            the base path for request
     */
    public void setBasePath(String basePath) {
        if (basePath.startsWith("/")) {
            this.basePath = basePath.substring(1);
        } else {
            this.basePath = basePath;
        }
    }

    public void setGenerateIndex(boolean generateIndex) {
        this.generateIndex = generateIndex;
    }

    @Override
    protected void doExecute() throws Exception {
        super.doExecute();
        if (basePath == null) {
            throw new IllegalStateException("The basePath parameter is null.");
        }
        ControllerDesc controllerDesc = createControllerDesc();
        JavaFile javaFile = createJavaFile(controllerDesc);
        Generator generator = createControllerGenerator(controllerDesc);
        generateJavaFile(generator, javaFile);

        JavaFile testCaseJavaFile = createTestCaseJavaFile(controllerDesc);
        Generator testCaseGenerator = createControllerTestCaseGenerator(controllerDesc);
        generateJavaFile(testCaseGenerator, testCaseJavaFile);
    }

    private ControllerDesc createControllerDesc() {
        ClassNameBuilder nameBuilder = new ClassNameBuilder();
        if (basePackageName != null) {
            nameBuilder.append(basePackageName);
        }
        String[] paths = basePath.split("/");
        for (int i = 0; i < paths.length - 1; i++) {
            nameBuilder.append(paths[i]);
        }
        nameBuilder.append(Constants.CONTROLLER_PACKAGE);
        nameBuilder.append(StringUtil.capitalize(paths[paths.length - 1])
                + Constants.CONTROLLER_SUFFIX);

        ControllerDesc controllerDesc = new ControllerDesc();
        controllerDesc.setBasePath(basePath);
        controllerDesc.setGenerateIndex(generateIndex);
        controllerDesc.setPackageName(nameBuilder.getPackageName());
        controllerDesc.setSimpleName(nameBuilder.getSimpleName());
        return controllerDesc;
    }

    protected Generator createControllerGenerator(ControllerDesc controllerDesc) {
        return new ControllerGenerator(controllerDesc);
    }

    protected Generator createControllerTestCaseGenerator(
            ControllerDesc controllerDesc) {
        return new ControllerTestCaseGenerator(controllerDesc);
    }
}
