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

import static ik.am.springmvc.gen.desc.AnnotaitonDesc.TEST;
import ik.am.springmvc.gen.Constants;
import ik.am.springmvc.gen.desc.ControllerDesc;
import ik.am.springmvc.gen.util.GeneratedUtil;

import org.slim3.gen.ClassConstants;
import org.slim3.gen.generator.Generator;
import org.slim3.gen.printer.Printer;
import org.slim3.gen.util.StringUtil;

public class ControllerTestCaseGenerator implements Generator {
    protected final ControllerDesc controllerDesc;

    public ControllerTestCaseGenerator(ControllerDesc controllerDesc) {
        if (controllerDesc == null) {
            throw new NullPointerException(
                    "The controllerDesc parameter is null.");
        }
        this.controllerDesc = controllerDesc;
    }

    @Override
    public void generate(Printer p) {
        if (!StringUtil.isEmpty(controllerDesc.getPackageName())) {
            p.println("package %s;", controllerDesc.getPackageName());
            p.println();
        }

        TEST.printImport(p);
        p.println("import static %s.*;", ClassConstants.Assert);
        p.println();
        GeneratedUtil.printGeneratedAnnotation(this, p);
        p.print("public class %s%s", controllerDesc.getSimpleName(),
                Constants.TEST_SUFFIX);
        p.println(" {");
        p.println();
        {
            p.indent();
            GeneratedUtil.printGeneratedAnnotation(this, p);
            p.println(TEST.toString());
            p.println("public void test() throws Exception {");
            {
                p.indent();
                p.println("%1$s controller = new %1$s();",
                        controllerDesc.getSimpleName());
                p.println("assertNotNull(controller);");
                p.unindent();
            }
            p.println("}");
            if (controllerDesc.isGenerateIndex()) {
                p.println();
                GeneratedUtil.printGeneratedAnnotation(this, p);
                p.println(TEST.toString());
                p.println("public void testIndex() throws Exception {");
                {
                    p.indent();
                    p.println("%1$s controller = new %1$s();",
                            controllerDesc.getSimpleName());
                    p.println(
                            "assertEquals(\"%s/index\", controller.index());",
                            controllerDesc.getBasePath());
                    p.unindent();
                }
                p.println("}");
            }
            p.unindent();
        }
        p.println("}");
    }

}
