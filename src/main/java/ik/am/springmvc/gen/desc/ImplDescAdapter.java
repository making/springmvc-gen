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
package ik.am.springmvc.gen.desc;

import org.slim3.gen.desc.ClassDesc;

public class ImplDescAdapter extends SimpleClassDesc {
    private final ClassDesc interfaceDesc;

    public ImplDescAdapter(ClassDesc interfaceDesc) {
        this.interfaceDesc = interfaceDesc;
    }

    @Override
    public String getPackageName() {
        String ifPkg = interfaceDesc.getPackageName();
        if (ifPkg.length() == 0) {
            return "impl";
        }
        return ifPkg + ".impl";
    }

    @Override
    public String getSimpleName() {
        return interfaceDesc.getSimpleName() + "Impl";
    }

}
