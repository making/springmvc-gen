## How to use

place these files to your project.

 * [lib/springmvc-gen-XXX.jar][1]
 * [build.xml][2] (edit properties)

run

    $ ant gen-controller

after input base-path, generates `XxxController`, `XxxControllerTest`, `index.jsp` at the package following the rule

    $ ant gen-service

after input service-name, generates `XxxService`, `XxxServiceImpl`, `XxxServiceImplTest` at the package following the rule

    $ ant gen-service-without-interface

after input service-name, generates `XxxService`, `XxxServiceTest` at the package following the rule

    $ ant gen-form-model

after input form-name, generates `XxxForm`, `XxxFormTest` at the package following the rule

    $ ant gen-output-model

after input form-name, generates `XxxOutput`, `XxxOutputTest` at the package following the rule

----
if cannot run on MacOSX with Eclipse(SWT), re-run with JVM Option "-XstartOnFirstThread"


  [1]: https://github.com/downloads/making/springmvc-gen/springmvc-gen-0.2.0.jar
  [2]: https://github.com/downloads/making/springmvc-gen/build.xml