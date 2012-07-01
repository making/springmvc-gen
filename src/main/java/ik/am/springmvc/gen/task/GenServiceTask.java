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
import ik.am.springmvc.gen.desc.ServiceDesc;
import ik.am.springmvc.gen.generator.ServiceGenerator;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.xml.xpath.XPathExpressionException;

import org.slim3.gen.generator.Generator;
import org.slim3.gen.task.AbstractGenJavaFileTask;
import org.slim3.gen.task.ClassNameBuilder;
import org.slim3.gen.task.JavaFile;

public class GenServiceTask extends AbstractGenJavaFileTask {
    /** the packageName */
    protected String basePackageName;

    /** the serviceDefinition */
    protected String serviceDefinition;

    /**
     * Sets the packageName.
     * 
     * @param basePackageName
     *            the baes packageName to set
     */
    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }

    /**
     * Sets the serviceDefinition.
     * 
     * @param serviceDefinition
     *            the serviceDefinition to set
     */
    public void setServiceDefinition(String serviceDefinition) {
        this.serviceDefinition = serviceDefinition;
    }

    @Override
    public void doExecute() throws Exception {
        super.doExecute();
        if (serviceDefinition == null) {
            throw new IllegalStateException(
                    "The serviceDefinition parameter is null.");
        }

        ServiceDesc serviceDesc = createServiceDesc();
        doExecuteService(serviceDesc);
    }

    protected void doExecuteService(ServiceDesc serviceDesc) throws Exception {
        JavaFile javaFile = createJavaFile(serviceDesc);
        Generator generator = createServiceGenerator(serviceDesc);
        generateJavaFile(generator, javaFile);
    }

    /**
     * Creates a service description.
     * 
     * @return a service implementation description
     * @throws IOException
     * @throws XPathExpressionException
     */
    protected ServiceDesc createServiceDesc() throws IOException,
            XPathExpressionException {
        ClassNameBuilder nameBuilder = new ClassNameBuilder();
        if (basePackageName != null) {
            nameBuilder.append(basePackageName);
        }
        String[] packages = serviceDefinition.split(Pattern.quote("."));
        for (int i = 0; i < packages.length - 1; i++) {
            nameBuilder.append(packages[i]);
        }
        nameBuilder.append(Constants.SERVICE_PACKAGE);
        nameBuilder.append(packages[packages.length - 1]
                + Constants.SERVICE_SUFFIX);

        ServiceDesc serviceDesc = new ServiceDesc();
        serviceDesc.setPackageName(nameBuilder.getPackageName());
        serviceDesc.setSimpleName(nameBuilder.getSimpleName());
        return serviceDesc;
    }

    /**
     * Creates a {@link Generator}.
     * 
     * @param serviceDesc
     *            the service description
     * @return a generator
     */
    protected Generator createServiceGenerator(ServiceDesc serviceDesc) {
        return new ServiceGenerator(serviceDesc);
    }

}
