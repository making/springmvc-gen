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

import ik.am.springmvc.gen.desc.HtmlDesc;

import org.slim3.gen.generator.Generator;
import org.slim3.gen.printer.Printer;

public class HtmlGenerator implements Generator {

    protected final HtmlDesc htmlDesc;

    public HtmlGenerator(HtmlDesc htmlJspDesc) {
        this.htmlDesc = htmlJspDesc;
    }

    @Override
    public void generate(Printer p) {
        p.println("<!DOCTYPE html>");
        p.println("<!--[if lt IE 7]> <html class=\"no-js lt-ie9 lt-ie8 lt-ie7\"> <![endif]-->");
        p.println("<!--[if IE 7]>    <html class=\"no-js lt-ie9 lt-ie8\"> <![endif]-->");
        p.println("<!--[if IE 8]>    <html class=\"no-js lt-ie9\"> <![endif]-->");
        p.println("<!--[if gt IE 8]><!--> <html class=\"no-js\"> <!--<![endif]-->");

        p.println("<head>");
        {
            p.indent();
            p.println("<meta charset=\"utf-8\" />");
            p.println("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,chrome=1\" />");
            p.println("<meta name=\"viewport\" content=\"width=device-width\" />");

            p.println("<link rel=\"stylesheet\" />");
            p.println("<title>%s</title>", htmlDesc.getTitle());
            p.unindent();
        }
        p.println("</head>");
        p.println("<body>");
        {
            p.indent();
            p.println("<p>Hello %s !</p>", htmlDesc.getTitle());
            p.println("<script></script>");
            p.unindent();
        }
        p.println("</body>");
        p.println("</html>");
    }

}
