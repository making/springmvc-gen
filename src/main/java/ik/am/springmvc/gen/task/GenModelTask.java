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
import ik.am.springmvc.gen.desc.ModelDesc;
import ik.am.springmvc.gen.generator.ModelGenerator;
import ik.am.springmvc.gen.generator.ModelTestCaseGenerator;

import java.util.regex.Pattern;

import org.slim3.gen.generator.Generator;
import org.slim3.gen.task.AbstractGenJavaFileTask;
import org.slim3.gen.task.ClassNameBuilder;
import org.slim3.gen.task.JavaFile;
import org.slim3.gen.util.StringUtil;

public class GenModelTask extends AbstractGenJavaFileTask {
    /** the packageName */
    protected String basePackageName;

    protected String modelDefinition;

    protected String classNameSuffix = "";

    protected String packageNameSuffix = "";

    /**
     * Sets the packageName.
     * 
     * @param basePackageName
     *            the base packageName to set
     */
    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }

    public void setModelDefinition(String modelDefinition) {
        this.modelDefinition = modelDefinition;
    }

    public void setClassNameSuffix(String classNameSuffix) {
        this.classNameSuffix = classNameSuffix;
    }

    public void setPackageNameSuffix(String packageNameSuffix) {
        this.packageNameSuffix = packageNameSuffix;
    }

    @Override
    public void doExecute() throws Exception {
        super.doExecute();
        if (modelDefinition == null) {
            throw new IllegalStateException(
                    "The modelDefinition parameter is null.");
        }

        ModelDesc modelDesc = createModelDesc();

        JavaFile javaFile = createJavaFile(modelDesc);
        Generator generator = createModelGenerator(modelDesc);
        generateJavaFile(generator, javaFile);

        JavaFile testCaseJavaFile = createTestCaseJavaFile(modelDesc);
        Generator testCaseGenerator = createModelTestCaseGenerator(modelDesc);
        generateJavaFile(testCaseGenerator, testCaseJavaFile);
    }

    private ModelDesc createModelDesc() {
        ModelDesc modelDesc = new ModelDesc();
        ClassNameBuilder nameBuilder = new ClassNameBuilder();
        if (basePackageName != null) {
            nameBuilder.append(basePackageName);
        }
        String[] packages = modelDefinition.split(Pattern.quote("."));
        for (int i = 0; i < packages.length - 1; i++) {
            nameBuilder.append(packages[i]);
        }
        nameBuilder.append(Constants.MODEL_PACKAGE);
        if (!StringUtil.isEmpty(packageNameSuffix)) {
            nameBuilder.append(packageNameSuffix);
        }
        nameBuilder.append(StringUtil.capitalize(packages[packages.length - 1])
                + classNameSuffix);

        modelDesc.setPackageName(nameBuilder.getPackageName());
        modelDesc.setSimpleName(nameBuilder.getSimpleName());
        return modelDesc;
    }

    protected Generator createModelGenerator(ModelDesc modelDesc) {
        return new ModelGenerator(modelDesc);
    }

    protected Generator createModelTestCaseGenerator(ModelDesc modelDesc) {
        return new ModelTestCaseGenerator(modelDesc);
    }

}
