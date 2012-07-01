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
package ik.am.springmvc.gen.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slim3.gen.generator.Generator;
import org.slim3.gen.printer.Printer;

@javax.annotation.Generated(value = "", comments = "", date = "")
public class GeneratedUtil {
    public static void printGeneratedAnnotation(Generator generator, Printer p) {
        SimpleDateFormat sdfIso8601BasicFormat = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String date = sdfIso8601BasicFormat.format(new Date());
        p.println(
                "@javax.annotation.Generated(value = \"%s\", comments = \"You can freely modify this generated code.\", date = \"%s\")",
                generator.getClass().getName(), date);
    }
}
