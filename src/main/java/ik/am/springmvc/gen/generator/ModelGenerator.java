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

import ik.am.springmvc.gen.desc.ModelDesc;
import ik.am.springmvc.gen.util.GeneratedUtil;

import org.slim3.gen.generator.Generator;
import org.slim3.gen.printer.Printer;
import org.slim3.gen.util.StringUtil;

public class ModelGenerator implements Generator {

    private final ModelDesc modelDesc;

    public ModelGenerator(ModelDesc modelDesc) {
        if (modelDesc == null) {
            throw new NullPointerException("The modelDesc parameter is null.");
        }
        this.modelDesc = modelDesc;
    }

    @Override
    public void generate(Printer p) {
        if (!StringUtil.isEmpty(modelDesc.getPackageName())) {
            p.println("package %s;", modelDesc.getPackageName());
            p.println();
        }
        p.println("import java.io.Serializable;");
        p.println();
        GeneratedUtil.printGeneratedAnnotation(this, p);
        p.print("public class %s implements Serializable",
                modelDesc.getSimpleName());
        p.println(" {");
        p.println();
        {
            p.indent();
            p.println("/** serialVersionUID. */");
            p.println("private static final long serialVersionUID = 1L;");
            p.unindent();
        }
        p.println("}");
    }
}
