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

import ik.am.springmvc.gen.desc.ImplDescAdapter;
import ik.am.springmvc.gen.desc.ServiceDesc;
import ik.am.springmvc.gen.generator.ServiceImplGenerator;
import ik.am.springmvc.gen.generator.ServiceImplTestCaseGenerator;

import org.slim3.gen.desc.ClassDesc;
import org.slim3.gen.generator.Generator;
import org.slim3.gen.task.JavaFile;

public class GenServiceImplTask extends GenServiceTask {
    private boolean withoutInterface = true;

    public void setWithoutInterface(boolean withoutInterface) {
        this.withoutInterface = withoutInterface;
    }

    @Override
    protected void doExecuteService(ServiceDesc serviceDesc) throws Exception {
        super.doExecuteService(serviceDesc);

        JavaFile testCaseJavaFile = createTestCaseJavaFile(serviceDesc);
        Generator testCaseGenerator = createServiceTestCaseGenerator(serviceDesc);
        generateJavaFile(testCaseGenerator, testCaseJavaFile);
    }

    @Override
    protected JavaFile createJavaFile(ClassDesc classDesc) {
        ClassDesc target = withoutInterface ? classDesc : new ImplDescAdapter(
                classDesc);
        return super.createJavaFile(target);
    }

    @Override
    protected JavaFile createTestCaseJavaFile(ClassDesc classDesc) {
        ClassDesc target = withoutInterface ? classDesc : new ImplDescAdapter(
                classDesc);
        return super.createTestCaseJavaFile(target);
    }

    @Override
    protected Generator createServiceGenerator(ServiceDesc serviceDesc) {
        serviceDesc.setWithoutInterface(withoutInterface);
        return new ServiceImplGenerator(serviceDesc);
    }

    protected Generator createServiceTestCaseGenerator(ServiceDesc serviceDesc) {
        serviceDesc.setWithoutInterface(withoutInterface);
        return new ServiceImplTestCaseGenerator(serviceDesc);
    }
}
