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
package ik.am.springmvc.gen.generator;

import ik.am.springmvc.gen.desc.ImplDescAdapter;
import ik.am.springmvc.gen.desc.ServiceDesc;
import ik.am.springmvc.gen.util.GeneratedUtil;

import org.slim3.gen.desc.ClassDesc;
import org.slim3.gen.printer.Printer;
import org.slim3.gen.util.StringUtil;

import static ik.am.springmvc.gen.desc.AnnotaitonDesc.*;

public class ServiceImplGenerator extends ServiceGenerator {

    protected final ClassDesc serviceImplDesc;

    public ServiceImplGenerator(ServiceDesc serviceDesc) {
        super(serviceDesc);
        this.serviceImplDesc = serviceDesc.isWithoutInterface() ? serviceDesc
                : new ImplDescAdapter(serviceDesc);
    }

    @Override
    public void generate(Printer p) {
        if (!StringUtil.isEmpty(serviceImplDesc.getPackageName())) {
            p.println("package %s;", serviceImplDesc.getPackageName());
            p.println();
        }

        if (!serviceDesc.isWithoutInterface()
                && !StringUtil.isEmpty(serviceDesc.getPackageName())) {
            p.println("import %s;", serviceDesc.getQualifiedName());
            p.println();
        }
        SERVICE.printImport(p);
        p.print("//");
        TRANSCATIONAL.printImport(p);
        p.println();
        GeneratedUtil.printGeneratedAnnotation(this, p);
        p.print("//");
        p.println(TRANSCATIONAL.toString());
        if (!serviceDesc.isWithoutInterface()) {
            p.println(SERVICE.toString(serviceDesc.getSimpleName()));
        } else {
            p.println("%s", SERVICE);
        }
        p.print("public class %s", serviceImplDesc.getSimpleName());
        if (!serviceDesc.isWithoutInterface()) {
            p.print(" implements %s", serviceDesc.getSimpleName());
        }
        p.println(" {");
        p.println();
        p.println("}");
    }
}
