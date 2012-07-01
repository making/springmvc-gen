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
import ik.am.springmvc.gen.desc.HtmlDesc;
import ik.am.springmvc.gen.generator.HtmlGenerator;

import java.io.File;
import java.io.IOException;

import org.slim3.gen.generator.Generator;
import org.slim3.gen.task.AbstractGenFileTask;

/**
 * Represents a task to generate index.jsp.
 * 
 */
public class GenHtmlTask extends AbstractGenFileTask {

    /** the base path */
    protected String basePath;

    protected String viewPrefix = Constants.WEB_INF_VIEWS;

    protected String fileName = Constants.INDEX_JSP;

    protected String title = Constants.INDEX_TITLE;

    /**
     * Sets the basePath.
     * 
     * @param basePath
     *            the basePath to set
     */
    public void setBasePath(String basePath) {
        if (basePath.startsWith("/")) {
            this.basePath = basePath.substring(1);
        } else {
            this.basePath = basePath;
        }
    }

    public void setViewPrefix(String viewPrefix) {
        this.viewPrefix = viewPrefix;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void doExecute() throws Exception {
        super.doExecute();
        if (basePath == null) {
            throw new IllegalStateException("The basePath parameter is null.");
        }
        HtmlDesc htmlDesc = createHtmlDesc();
        generateIndexJsp(htmlDesc);
    }

    /**
     * Creates a view description.
     * 
     * @return a view description
     */
    protected HtmlDesc createHtmlDesc() {
        HtmlDesc htmlDesc = new HtmlDesc();
        htmlDesc.setDirName(viewPrefix + basePath);
        htmlDesc.setFileName(fileName);
        htmlDesc.setTitle(title);
        return htmlDesc;
    }

    /***
     * Generates a index.jsp.
     * 
     * @param htmlDesc
     *            the html description.
     * @throws IOException
     */
    protected void generateIndexJsp(HtmlDesc htmlDesc) throws IOException {
        File viewDir = new File(warDir, htmlDesc.getDirName());
        viewDir.mkdirs();
        File viewFile = new File(viewDir, htmlDesc.getFileName());
        Generator generator = careateViewGenerator(htmlDesc);
        generateFile(generator, viewFile);
    }

    /**
     * Creates a {@link Generator}.
     * 
     * @param htmlDesc
     *            the html description.
     * @return a generator
     */
    protected Generator careateViewGenerator(HtmlDesc htmlDesc) {
        return new HtmlGenerator(htmlDesc);
    }

}
